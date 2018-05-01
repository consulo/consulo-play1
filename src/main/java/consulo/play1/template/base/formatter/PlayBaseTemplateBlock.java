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

package consulo.play1.template.base.formatter;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.play1.template.base.parser.PlayBaseTemplateSimplyTokens;
import consulo.play1.template.base.psi.PlayBaseTemplateFile;
import consulo.play1.template.base.psi.PlayBaseTemplateTag;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.templateLanguages.DataLanguageBlockWrapper;
import com.intellij.formatting.templateLanguages.TemplateLanguageBlock;
import com.intellij.formatting.templateLanguages.TemplateLanguageBlockFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 18:04/26.03.13
 */
public class PlayBaseTemplateBlock extends TemplateLanguageBlock
{
	protected PlayBaseTemplateBlock(@Nonnull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, @Nonnull TemplateLanguageBlockFactory blockFactory, @Nonnull CodeStyleSettings settings, @Nullable List<DataLanguageBlockWrapper> foreignChildren)
	{
		super(node, wrap, alignment, blockFactory, settings, foreignChildren);
	}

	@Override
	public Indent getIndent()
	{
		final PsiElement psi = getNode().getPsi();
		if(psi instanceof PlayBaseTemplateTag)
		{
			if(!(psi.getParent() instanceof PlayBaseTemplateFile))
			{
				return Indent.getNormalIndent();
			}
		}
		return Indent.getNoneIndent();
	}

	@Override
	protected IElementType getTemplateTextElementType()
	{
		return PlayBaseTemplateSimplyTokens.TEMPLATE_TEXT;
	}
}
