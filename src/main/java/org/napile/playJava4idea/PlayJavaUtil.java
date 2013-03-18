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
import com.intellij.psi.util.InheritanceUtil;

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
