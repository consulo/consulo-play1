package org.napile.playJava4idea.template.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
			public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType)
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
