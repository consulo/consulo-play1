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

package consulo.play1.template.base;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 17:27/23.03.13
 */
public class PlayBaseTemplateBraceMatcher extends PairedBraceMatcherAdapter
{
	public PlayBaseTemplateBraceMatcher()
	{
		super(new PairedBraceMatcher()
		{
			@Override
			public BracePair[] getPairs()
			{
				return new BracePair[0];
			}

			@Override
			public boolean isPairedBracesAllowedBeforeType(@Nonnull IElementType lbraceType, @Nullable IElementType contextType)
			{
				return true;
			}

			@Override
			public int getCodeConstructStart(PsiFile file, int openingBraceOffset)
			{
				return openingBraceOffset;
			}
		}, PlayBaseTemplateLanguage.INSTANCE);
	}
}
