package org.napile.playJava4idea;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;

/**
 * @author VISTALL
 * @since 16:55/17.03.13
 */
public class PlayJavaUtil
{
	public static PsiClass findClass(Project project, Module module, String className)
	{
		JavaPsiFacade facade = JavaPsiFacade.getInstance(project);

		return facade.findClass(className, module.getModuleWithDependenciesAndLibrariesScope(false));
	}
}
