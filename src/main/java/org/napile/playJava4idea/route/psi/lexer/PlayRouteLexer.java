/*
 * Copyright 2010-2013 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.napile.playJava4idea.route.psi.lexer;


import java.io.Reader;

import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.route.psi.PlayRouteElementTypes;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 22:01/23.03.13
 */
public class PlayRouteLexer extends LookAheadLexer implements PlayRouteElementTypes
{
	//"^({method}GET|POST|PUT|DELETE|OPTIONS|HEAD|WS|\\*)[(]?({headers}[^)]*)(\\))?\\s+({path}.*/[^\\s]*)\\s+({action}[^\\s(]+)({params}.+)?(\\s*)$";

	private static final int NO_STATE = 0;
	private static final int METHOD_ENTERED = 1;
	private static final int PATH_ENTERED = 2;
	private static final int ACTION_ENTERED = 3;

	private int state = NO_STATE;

	public PlayRouteLexer()
	{
		super(new FlexAdapter(new _PlayRouteLexer((Reader) null)));
	}

	@Nullable
	protected IElementType mergeTokens(Lexer baseLexer, TokenSet until, IElementType intoType)
	{
		if(skipTokens(baseLexer, until))
		{
			addToken(baseLexer.getTokenStart(), intoType);
		}
		return baseLexer.getTokenType();
	}

	protected boolean skipTokens(Lexer baseLexer, TokenSet until)
	{
		boolean skipped = false;
		while(true)
		{
			IElementType tokenType = baseLexer.getTokenType();
			if(tokenType == null || until.contains(tokenType))
			{
				return skipped;
			}
			skipped = true;
			baseLexer.advance();
		}
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		int newState = -1;

		switch(state)
		{
			case NO_STATE:
				if(baseLexer.getTokenType() == METHOD_TYPE)
				{
					super.lookAhead(baseLexer);

					newState = METHOD_ENTERED;
				}
				break;
			case METHOD_ENTERED:
				if(baseLexer.getTokenType() == SOME_REFERENCE)
				{
					mergeTokens(baseLexer, TokenSet.create(WHITE_SPACE), PATH);

					newState = PATH_ENTERED;
				}
				break;
			case PATH_ENTERED:
				if(baseLexer.getTokenType() == SOME_REFERENCE)
				{
					mergeTokens(baseLexer, TokenSet.create(WHITE_SPACE), ACTION_NAME);

					newState = ACTION_ENTERED;
				}
				break;
			case ACTION_ENTERED:
				if(baseLexer.getTokenType() == WHITE_SPACE && baseLexer.getTokenText().equals("\n"))
				{
					super.lookAhead(baseLexer);

					newState = NO_STATE;
				}
				break;
		}

		if(newState == -1)
		{
			super.lookAhead(baseLexer);
		}
		else
		{
			state = newState;
		}
	}
}
