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

package consulo.play1;

import javax.annotation.Nullable;

import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.InheritanceUtil;
import consulo.play1.conf.PlayJavaConfFileType;

/**
 * @author VISTALL
 * @since 16:55/17.03.13
 */
public class PlayJavaUtil
{
	public static boolean isSuperController(@Nullable PsiClass psiClass)
	{
		return psiClass != null && InheritanceUtil.isInheritor(psiClass, PlayJavaConstants.PLAY_MVC_CONTROLLER);
	}

	@Nullable
	public static PropertiesFile getApplicationConf(@Nullable Module module)
	{
		if(module == null)
		{
			return null;
		}

		VirtualFile moduleDir = module.getModuleDir();
		if(moduleDir == null)
		{
			return null;
		}

		final VirtualFile confVirtualFile = moduleDir.findFileByRelativePath(PlayJavaConstants.CONF__APPLICATION_CONF);

		if(confVirtualFile == null || confVirtualFile.getFileType() != PlayJavaConfFileType.INSTANCE)
		{
			return null;
		}

		return (PropertiesFile) PsiManager.getInstance(module.getProject()).findFile(confVirtualFile);
	}


	public static PsiClass findClass(Project project, Module module, String className)
	{
		JavaPsiFacade facade = JavaPsiFacade.getInstance(project);

		return facade.findClass(className, module.getModuleWithDependenciesAndLibrariesScope(false));
	}
}
