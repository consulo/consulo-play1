package org.napile.playJava4idea.template.base.fileViewProvider;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;

/**
 * @author VISTALL
 * @since 13:10/18.03.13
 */
public class PlayBaseTemplateFileViewProviderFactory implements FileViewProviderFactory
{
	@Override
	public FileViewProvider createFileViewProvider(@NotNull VirtualFile file, Language language, @NotNull PsiManager manager, boolean physical)
	{
		return new PlayBaseTemplateFileViewProvider(manager, file, physical);
	}
}
