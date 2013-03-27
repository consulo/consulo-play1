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

package org.napile.playJava4idea.editor.highlight;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

/**
 * @author VISTALL
 * @since 20:28/18.03.13
 */
public interface PlayJavaColors
{
	TextAttributesKey TEMPLATE_ELEMENTS = TextAttributesKey.createTextAttributesKey("PLAY_TEMPLATE_ELEMENTS", DefaultLanguageHighlighterColors.MARKUP_TAG);

	TextAttributesKey PLAY_TAG_NAME = TextAttributesKey.createTextAttributesKey("PLAY_TAG_NAME", DefaultLanguageHighlighterColors.KEYWORD);

	TextAttributesKey ROUTE_METHOD = TextAttributesKey.createTextAttributesKey("PLAY_ROUTE_METHOD", DefaultLanguageHighlighterColors.KEYWORD);
}
