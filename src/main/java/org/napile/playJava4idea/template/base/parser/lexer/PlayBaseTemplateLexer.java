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

package org.napile.playJava4idea.template.base.parser.lexer;

import java.io.Reader;

import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokenSets;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 17:33/18.03.13
 */
public class PlayBaseTemplateLexer extends LookAheadLexer implements PlayBaseTemplateTokens, PlayBaseTemplateTokenSets
{
	public PlayBaseTemplateLexer()
	{
		super(new FlexAdapter(new _PlayBaseTemplateLexer((Reader) null)));
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

	protected void mergeWordsUntil(Lexer baseLexer, TokenSet until)
	{
		while(true)
		{
			if(baseLexer.getTokenType() == null || until.contains(baseLexer.getTokenType()))
			{
				break;
			}

			else if(baseLexer.getTokenType() == TEMPLATE_TEXT)
			{
				int last = baseLexer.getTokenEnd();
				while(true)
				{
					baseLexer.advance();

					if(baseLexer.getTokenType() != TEMPLATE_TEXT)
					{
						break;
					}
					else
					{
						last = baseLexer.getTokenEnd();
					}
				}

				addToken(last, TEMPLATE_TEXT);
			}
			else
			{
				advanceLexer(baseLexer);
			}
		}
	}

	private boolean wantLeftBrace(Lexer baseLexer, IElementType tokenType)
	{
		int end = baseLexer.getTokenEnd();
		baseLexer.advance();

		if(baseLexer.getTokenType() == LBRACE)
		{
			addToken(end, tokenType);

			advanceLexer(baseLexer);

			return true;
		}
		else
		{
			addToken(end, TEMPLATE_TEXT);
			return false;
		}
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		mergeTokens(baseLexer, TAG_START_SET, TEMPLATE_TEXT);

		final IElementType tokenType = baseLexer.getTokenType();
		if(tokenType == ASTERISK)
		{
			advanceComment(baseLexer);
		}
		else if(tokenType == SHARP)
		{
			if(wantLeftBrace(baseLexer, tokenType))
			{
				if(baseLexer.getTokenType() == DIV)
				{
					advanceLexer(baseLexer);

					mergeWordsUntil(baseLexer, TokenSet.create(DIV));

					if(baseLexer.getTokenType() == RBRACE)
					{
						advanceLexer(baseLexer);
					}
				}
				else
				{
					mergeWordsUntil(baseLexer, TokenSet.create(DIV));

					if(baseLexer.getTokenType() == DIV)
					{
						advanceLexer(baseLexer);
					}

					if(baseLexer.getTokenType() == RBRACE)
					{
						advanceLexer(baseLexer);
					}
				}
			}
		}
		else if(tokenType == PERC)
		{
			if(wantLeftBrace(baseLexer, tokenType))
			{
				mergeWordsUntil(baseLexer, TokenSet.create(RBRACE));

				if(baseLexer.getTokenType() == RBRACE)
				{
					advanceLexer(baseLexer);
				}

				if(baseLexer.getTokenType() == PERC)
				{
					advanceLexer(baseLexer);
				}
			}
		}
		else if(tokenType == DOLLAR || tokenType == AT || tokenType == ATAT || tokenType == AND)
		{
			if(wantLeftBrace(baseLexer, tokenType))
			{
				mergeWordsUntil(baseLexer, TokenSet.create(RBRACE));

				if(baseLexer.getTokenType() == RBRACE)
				{
					advanceLexer(baseLexer);
				}
			}
		}
		else
		{
			advanceLexer(baseLexer);
		}
	}

	private void advanceComment(Lexer baseLexer)
	{
		int end = baseLexer.getTokenEnd();
		baseLexer.advance();
		if(baseLexer.getTokenType() == LBRACE)
		{
			baseLexer.advance();

			boolean w8End = false;
			while(true)
			{
				final IElementType tokenType = baseLexer.getTokenType();
				if(tokenType == null)
				{
					break;
				}

				if(tokenType == RBRACE)
				{
					w8End = true;
				}

				baseLexer.advance();
				if(w8End && baseLexer.getTokenType() == ASTERISK)
				{
					break;
				}
			}

			addToken(baseLexer.getTokenEnd(), COMMENT);
		}
		else
		{
			addToken(end, TEMPLATE_TEXT);
		}
	}
}
