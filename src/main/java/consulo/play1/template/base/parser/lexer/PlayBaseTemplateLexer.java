/*
 * Copyright 2013-2016 consulo.io
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

package consulo.play1.template.base.parser.lexer;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.play1.template.base.parser.PlayBaseTemplateTokenSets;
import consulo.play1.template.base.parser.PlayBaseTemplateTokens;

/**
 * @author VISTALL
 * @since 17:33/18.03.13
 */
public class PlayBaseTemplateLexer extends LookAheadLexer implements PlayBaseTemplateTokens, PlayBaseTemplateTokenSets
{
	private static final Map<IElementType, IElementType> OPEN_MAPPING = new HashMap<IElementType, IElementType>();
	static
	{
		OPEN_MAPPING.put(DOLLAR, EXPRESSION_START);
		OPEN_MAPPING.put(PERC, SCRIPT_START);
		OPEN_MAPPING.put(AND, MESSAGE_START);
		OPEN_MAPPING.put(AT, ACTION_START);
		OPEN_MAPPING.put(ATAT, ABSOLUTE_ACTION_START);
		OPEN_MAPPING.put(SHARP, TAG_START);
		OPEN_MAPPING.put(ASTERISK, COMMENT_START);
	}

	private static final TokenSet OPEN_ELEMENT_SET = TokenSet.create(OPEN_MAPPING.keySet().toArray(new IElementType[OPEN_MAPPING.size()]));

	public PlayBaseTemplateLexer()
	{
		super(new MergingLexerAdapter(new _PlayBaseTemplateLexer(), TokenSet.create(TEMPLATE_TEXT, WHITE_SPACE)));
	}

	@Nullable
	protected IElementType mergeTokensUtil(Lexer baseLexer, TokenSet until, IElementType intoType)
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

	private void skipSpaces(Lexer baseLexer)
	{
		while(true)
		{
			if(baseLexer.getTokenType() == WHITE_SPACE)
			{
				advanceLexer(baseLexer);
			}
			else
			{
				break;
			}
		}
	}

	@Override
	protected void lookAhead(Lexer baseLexer)
	{
		mergeTokensUtil(baseLexer, OPEN_ELEMENT_SET, TEMPLATE_TEXT);

		final IElementType startElementWithBrace = OPEN_MAPPING.get(baseLexer.getTokenType());
		if(startElementWithBrace != null)
		{
			baseLexer.advance();   // advance first element

			if(baseLexer.getTokenType() == LBRACE)  // this is start element
			{
				advanceAs(baseLexer, startElementWithBrace);

				if(startElementWithBrace == TAG_START)
				{
					processTag(baseLexer);
				}
				else if(startElementWithBrace == COMMENT_START)
				{
					processComment(baseLexer);
				}
				else if(startElementWithBrace == EXPRESSION_START || startElementWithBrace == ACTION_START || startElementWithBrace == ABSOLUTE_ACTION_START || startElementWithBrace == MESSAGE_START)
				{
					processSimple(baseLexer);
				}
				else if(startElementWithBrace == SCRIPT_START)
				{
					processScript(baseLexer);
				}
			}
			else
			{
				addToken(TEMPLATE_TEXT);
			}
		}
		else
		{
			advanceLexer(baseLexer);
		}
	}

	private void processScript(Lexer baseLexer)
	{
		boolean addTokenOnEnd = false;
		while(true)
		{
			if(baseLexer.getTokenType() == TEMPLATE_TEXT)
			{
				baseLexer.advance();
			}
			else if(baseLexer.getTokenType() == RBRACE)
			{
				int tokenStart = baseLexer.getTokenStart();

				baseLexer.advance();

				if(baseLexer.getTokenType() == PERC)
				{
					addTokenOnEnd = false;
					addToken(tokenStart, GROOVY_EXPRESSION_OLD);

					advanceAs(baseLexer, SCRIPT_END);

					break;
				}
				else
				{
					baseLexer.advance();
					addTokenOnEnd = true;
				}
			}
			else
			{
				if(baseLexer.getTokenType() == null)
				{
					break;
				}
				else
				{
					baseLexer.advance();
					addTokenOnEnd = true;
				}
			}
		}

		if(addTokenOnEnd)
		{
			addToken(GROOVY_EXPRESSION_OLD);
		}
	}

	private void processSimple(Lexer baseLexer)
	{
		while(true)
		{
			if(baseLexer.getTokenType() == TEMPLATE_TEXT)
			{
				remap(baseLexer, TEMPLATE_TEXT, GROOVY_EXPRESSION_OLD);
			}
			else if(baseLexer.getTokenType() == RBRACE)
			{
				advanceAs(baseLexer, CLOSE_BRACE);

				break;
			}
			else
			{
				if(baseLexer.getTokenType() == null)
				{
					break;
				}
				else
				{
					advanceLexer(baseLexer);
				}
			}
		}
	}

	private void processComment(Lexer baseLexer)
	{
		while(true)
		{
			if(baseLexer.getTokenType() == TEMPLATE_TEXT)
			{
				remap(baseLexer, TEMPLATE_TEXT, GROOVY_EXPRESSION_OLD);
			}
			else if(baseLexer.getTokenType() == RBRACE)
			{
				baseLexer.advance();

				if(baseLexer.getTokenType() == ASTERISK)
				{
					advanceAs(baseLexer, COMMENT_END);

					break;
				}
				else
				{
					advanceAs(baseLexer, COMMENT);
				}
			}
			else
			{
				if(baseLexer.getTokenType() == null)
				{
					break;
				}
				else
				{
					advanceAs(baseLexer, COMMENT);
				}
			}
		}
	}

	private void processTag(Lexer baseLexer)
	{
		if(baseLexer.getTokenType() == DIV)
		{
			advanceLexer(baseLexer);

			if(processTagName(baseLexer))
			{
				skipSpaces(baseLexer);

				if(baseLexer.getTokenType() == RBRACE)
				{
					advanceAs(baseLexer, CLOSE_BRACE);
				}
			}
		}
		else
		{
			processTagName(baseLexer);

			skipSpaces(baseLexer);

			while(true)
			{
				if(baseLexer.getTokenType() == TEMPLATE_TEXT)
				{
					remap(baseLexer, TEMPLATE_TEXT, GROOVY_EXPRESSION_OLD);
				}
				else if(baseLexer.getTokenType() == DIV)
				{
					baseLexer.advance();

					int tokenStart = baseLexer.getTokenStart();

					if(baseLexer.getTokenType() == RBRACE)
					{
						advanceAs(baseLexer, TAG_END);

						break;
					}
					else
					{
						addToken(tokenStart, DIV);

						advanceLexer(baseLexer);
					}
				}
				else if(baseLexer.getTokenType() == RBRACE)
				{
					advanceAs(baseLexer, CLOSE_BRACE);

					break;
				}
				else
				{
					if(baseLexer.getTokenType() == null)
					{
						break;
					}
					else
					{
						advanceLexer(baseLexer);
					}
				}
			}
		}
	}

	private boolean remap(Lexer base, IElementType need, IElementType to)
	{
		if(base.getTokenType() == need)
		{
			advanceAs(base, to);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean processTagName(Lexer baseLexer)
	{
		skipSpaces(baseLexer);

		boolean t = remap(baseLexer, TEMPLATE_TEXT, TAG_NAME);

		skipSpaces(baseLexer);

		return t;
	}
}
