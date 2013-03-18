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

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import com.intellij.lang.StdLanguages;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataHighlighterWrapper;

/**
 * @author VISTALL
 * @since 19:58/18.03.13
 */
public class PlayBaseTemplateEditorHighlighter extends LayeredLexerEditorHighlighter
{
	public PlayBaseTemplateEditorHighlighter(@NotNull EditorColorsScheme scheme, @NotNull Project project, @NotNull VirtualFile virtualFile)
	{
		super(new PlayBaseTemplateSyntaxHighlighter(), scheme);

		SyntaxHighlighter htmlHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(StdLanguages.HTML, project, virtualFile);
		LayerDescriptor htmlLayer = new LayerDescriptor(new TemplateDataHighlighterWrapper(htmlHighlighter), "\n");
		registerLayer(PlayBaseTemplateTokens.TEMPLATE_TEXT, htmlLayer);
	}
}
