package org.napile.playJava4idea;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.facet.PlayJavaFacet;
import org.napile.playJava4idea.facet.PlayJavaFacetType;
import com.intellij.facet.FacetManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;

/**
 * @author VISTALL
 * @since 16:55/17.03.13
 */
public class PlayJavaUtil
{
	@Nullable
	public static PlayJavaFacet findPlayFacet(VirtualFile virtualFile, Project project)
	{
		final Module moduleForFile = ModuleUtil.findModuleForFile(virtualFile, project);
		if(moduleForFile == null)
		{
			return null;
		}
		else
		{
			return findPlayFacet(moduleForFile);
		}
	}

	@Nullable
	public static PlayJavaFacet findPlayFacet(@NotNull Module module)
	{
		FacetManager manager = FacetManager.getInstance(module);

		return manager.getFacetByType(PlayJavaFacetType.FACET_TYPE_ID);
	}

	public static PsiClass findClass(Project project, Module module, String className)
	{
		JavaPsiFacade facade = JavaPsiFacade.getInstance(project);

		return facade.findClass(className, module.getModuleWithDependenciesAndLibrariesScope(false));
	}
}
