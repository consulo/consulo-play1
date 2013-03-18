package org.napile.playJava4idea.template.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.PlayJavaUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.LanguageSubstitutor;

/**
 * @author VISTALL
 * @since 11:56/18.03.13
 */
public class PlayHtmlLanguageSubstitutor extends LanguageSubstitutor
{
	@Nullable
	@Override
	public Language getLanguage(@NotNull VirtualFile file, @NotNull Project project)
	{
		if(PlayJavaUtil.findPlayFacet(file, project) == null)
		{
			return null;
		}

		String packageName = ProjectRootManager.getInstance(project).getFileIndex().getPackageNameByDirectory(file.getParent());
		if(packageName != null && packageName.startsWith("views"))
		{
			return PlayBaseTemplateLanguage.INSTANCE;
		}
		return null;
	}
}
