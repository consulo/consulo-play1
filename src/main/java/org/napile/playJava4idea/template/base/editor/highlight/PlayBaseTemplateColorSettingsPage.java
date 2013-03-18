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

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;

/**
 * @author VISTALL
 * @since 20:26/18.03.13
 */
public class PlayBaseTemplateColorSettingsPage implements ColorSettingsPage
{
	@Nullable
	@Override
	public Icon getIcon()
	{
		return null;
	}

	@NotNull
	@Override
	public SyntaxHighlighter getHighlighter()
	{
		return new PlayBaseTemplateSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText()
	{
		return "<tag>#</tag>{extends 'User/main.html'/}\n" +
				"<tag>#</tag>{set title:'Libraries profile - napile' /}\n" +
				"\n" +
				"<html>\n" +
				"\t<body></body>\n" +
				"</html>\n" +
				"\n" +
				"*{ this is comment }*\n" +
				"<tag>$</tag>{expression}\n" +
				"<tag>@</tag>{Action}\n" +
				"<tag>@@</tag>{AbsoluteAction}\n" +
				"<tag>%</tag>{ this is script }<tag>%</tag>\n" +
				"<tag>#</tag>{tag /}\n" +
				"<tag>&</tag>{'message', arg}";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
	{
		Map<String, TextAttributesKey> map = new HashMap<String, TextAttributesKey>();
		map.put("tag", PlayBaseTemplateColors.PLAY_TAG_START);
		return map;
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors()
	{
		return new AttributesDescriptor[]{
				new AttributesDescriptor("Play tag start", PlayBaseTemplateColors.PLAY_TAG_START)
		};
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors()
	{
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName()
	{
		return "Play 1.x.x";
	}
}
