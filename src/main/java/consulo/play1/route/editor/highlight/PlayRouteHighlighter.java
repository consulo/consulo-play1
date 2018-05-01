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

package consulo.play1.route.editor.highlight;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import consulo.play1.editor.highlight.PlayJavaColors;
import consulo.play1.route.psi.PlayRouteElementTypes;
import consulo.play1.route.psi.lexer.PlayRouteLexer;
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

	@Nonnull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new PlayRouteLexer();
	}

	@Nonnull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		return pack(elementToKeys.get(tokenType));
	}
}
