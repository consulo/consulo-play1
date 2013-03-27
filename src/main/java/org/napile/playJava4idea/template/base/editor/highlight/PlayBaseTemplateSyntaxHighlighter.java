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

package org.napile.playJava4idea.template.base.editor.highlight;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.editor.highlight.PlayJavaColors;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokenSets;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplateLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.XmlHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 19:45/18.03.13
 */
public class PlayBaseTemplateSyntaxHighlighter extends SyntaxHighlighterBase implements PlayBaseTemplateTokens
{
	private static final Map<IElementType,TextAttributesKey> map = new HashMap<IElementType, TextAttributesKey>();

	static
	{
		safeMap(map, COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
		safeMap(map, STRING, DefaultLanguageHighlighterColors.STRING);
		safeMap(map, COMMA, DefaultLanguageHighlighterColors.COMMA);
		safeMap(map, TokenSet.create(LBRACE, RBRACE), DefaultLanguageHighlighterColors.BRACES);
		safeMap(map, PlayBaseTemplateTokenSets.TAG_START_SET, PlayJavaColors.PLAY_TAG_START);
		safeMap(map, TAG_NAME, XmlHighlighterColors.HTML_TAG_NAME);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new PlayBaseTemplateLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		return pack(map.get(tokenType));
	}
}
