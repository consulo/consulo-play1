/*
 * Copyright 2013 Consulo.org
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

package org.consulo.play1.template.base.editor.highlight;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.consulo.play1.editor.highlight.PlayJavaColors;
import org.consulo.play1.template.base.parser.PlayBaseTemplateTokenSets;
import org.consulo.play1.template.base.parser.PlayBaseTemplateTokens;
import org.consulo.play1.template.base.parser.lexer.PlayBaseTemplateLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
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
		safeMap(map, TokenSet.create(COMMENT, COMMENT_START, COMMENT_END), DefaultLanguageHighlighterColors.LINE_COMMENT);
		safeMap(map, STRING, DefaultLanguageHighlighterColors.STRING);
		safeMap(map, COMMA, DefaultLanguageHighlighterColors.COMMA);
		safeMap(map, PlayBaseTemplateTokenSets.TEMPLATE_ELEMENTS, PlayJavaColors.TEMPLATE_ELEMENTS);
		safeMap(map, TAG_NAME, PlayJavaColors.PLAY_TAG_NAME);
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
