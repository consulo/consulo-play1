package org.napile.playJava4idea.template.base.psi;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.PlayBaseTemplateFileType;
import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;

/**
 * @author VISTALL
 * @since 14:53/18.03.13
 */
public class PlayBaseTemplateFile extends PsiFileBase
{
	public PlayBaseTemplateFile(@NotNull FileViewProvider viewProvider)
	{
		super(viewProvider, PlayBaseTemplateLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return PlayBaseTemplateFileType .INSTANCE;
	}
}
