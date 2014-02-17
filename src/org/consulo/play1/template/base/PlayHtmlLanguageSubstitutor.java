/*
 * Copyright 2013 Consulo.org
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

package org.consulo.play1.template.base;

import org.consulo.play1.module.extension.Play1ModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.Language;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
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
		final Module moduleForFile = ModuleUtilCore.findModuleForFile(file, project);
		if(moduleForFile == null || ModuleUtilCore.getExtension(moduleForFile, Play1ModuleExtension.class) == null)
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
