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

package org.napile.playJava4idea.template.base;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateNodes;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 19:01/18.03.13
 */
public class PlayBaseTemplateParser implements PsiParser, PlayBaseTemplateTokens, PlayBaseTemplateNodes
{
	public static final PsiParser INSTANCE = new PlayBaseTemplateParser();

	@NotNull
	@Override
	public ASTNode parse(IElementType root, PsiBuilder builder)
	{
		PsiBuilder.Marker marker = builder.mark();
		while(!builder.eof())
		{
			final IElementType tokenType = builder.getTokenType();
			if(tokenType == TAG_START)
			{
				advanceTag(builder);
			}
			else if(tokenType == EXPRESSION_START)
			{
				advanceSimple(builder, CLOSE_BRACE, EXPRESSION);
			}
			else if(tokenType == ACTION_START)
			{
				advanceSimple(builder, CLOSE_BRACE, ACTION);
			}
			else if(tokenType == ABSOLUTE_ACTION_START)
			{
				advanceSimple(builder, CLOSE_BRACE, ABSOLUTE_ACTION);
			}
			else if(tokenType == MESSAGE_START)
			{
				advanceSimple(builder, CLOSE_BRACE, MESSAGE);
			}
			else if(tokenType == SCRIPT_START)
			{
				advanceSimple(builder, SCRIPT_END, SCRIPT);
			}
			else if(tokenType == COMMENT_START)
			{
				builder.advanceLexer();

				skipUntil(builder, TokenSet.create(COMMENT_END));
			}
			else
			{
				builder.advanceLexer();
			}
		}
		marker.done(root);
		return builder.getTreeBuilt();
	}


	private void advanceTag(PsiBuilder builder)
	{
		PsiBuilder.Marker m = builder.mark();
		builder.advanceLexer(); // #{

		// close tag
		if(builder.getTokenType() == DIV)
		{
			builder.advanceLexer();

			if(builder.getTokenType() == TAG_NAME)
			{
				builder.advanceLexer();
			}
			else
			{
				builder.error("Expected tag name");
			}

			expectOrError(builder, CLOSE_BRACE);
		}
		else
		{
			if(builder.getTokenType() == TAG_NAME)
			{
				builder.advanceLexer();
			}
			else
			{
				builder.error("Expected tag name");
			}

			skipUntil(builder, TokenSet.create(CLOSE_BRACE, TAG_END));

			if(builder.getTokenType() != CLOSE_BRACE && builder.getTokenType() != TAG_END)
			{
				builder.error("} expected. Found: " + builder.getTokenType());
			}
			else
			{
				builder.advanceLexer();
			}
		}

		m.done(TAG);
	}

	private void advanceSimple(PsiBuilder builder, IElementType close, IElementType node)
	{
		PsiBuilder.Marker expressionMarker = builder.mark();
		builder.advanceLexer();

		skipUntil(builder, TokenSet.create(close));

		expectOrError(builder, close);

		expressionMarker.done(node);
	}

	private static boolean expectOrError(PsiBuilder builder, IElementType node)
	{
		if(builder.getTokenType() == node)
		{
			builder.advanceLexer();
			return true;
		}
		else
		{
			builder.error("Expect " + node);
			return false;
		}
	}

	private void skipUntil(PsiBuilder builder, TokenSet tokenSet)
	{
		while(!builder.eof())
		{
			if(tokenSet.contains(builder.getTokenType()))
			{
				break;
			}

			builder.advanceLexer();
		}
	}
}
