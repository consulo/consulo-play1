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

package org.napile.playJava4idea.route.editor.highlight;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.editor.highlight.PlayJavaColors;
import org.napile.playJava4idea.route.psi.PlayRouteElementTypes;
import org.napile.playJava4idea.route.psi.lexer.PlayRouteLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 13:20/24.03.13
 */
public class PlayRouteHighlighter extends SyntaxHighlighterBase implements PlayRouteElementTypes
{
	private Map<IElementType, TextAttributesKey> elementToKeys = new HashMap<IElementType, TextAttributesKey>();

	public PlayRouteHighlighter()
	{
		elementToKeys.put(COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
		elementToKeys.put(METHOD_TYPE, PlayJavaColors.ROUTE_METHOD);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new PlayRouteLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		return pack(elementToKeys.get(tokenType));
	}
}
