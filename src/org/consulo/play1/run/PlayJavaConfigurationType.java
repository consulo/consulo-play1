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

package org.consulo.play1.run;

import javax.swing.Icon;

import org.consulo.play1.module.extension.Play1ModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.consulo.play1.PlayJavaIcons;
import org.mustbe.consulo.module.extension.ModuleExtensionHelper;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;

/**
 * @author VISTALL
 * @since 22:01/17.03.13
 */
public class PlayJavaConfigurationType implements ConfigurationType
{
	@Nullable
	public static PlayJavaConfigurationType getInstance()
	{
		return ContainerUtil.findInstance(Extensions.getExtensions(CONFIGURATION_TYPE_EP), PlayJavaConfigurationType.class);
	}

	private final ConfigurationFactory configurationFactory;

	PlayJavaConfigurationType()
	{
		configurationFactory = new ConfigurationFactory(this)
		{
			@Override
			public RunConfiguration createTemplateConfiguration(Project project)
			{
				return new PlayJavaModuleBasedConfiguration(project, getName(), this);
			}

			@Override
			public boolean isApplicable(@NotNull Project project)
			{
				return ModuleExtensionHelper.getInstance(project).hasModuleExtension(Play1ModuleExtension.class);
			}
		};
	}

	@Override
	public String getDisplayName()
	{
		return "Play 1.x.x";
	}

	@Override
	public String getConfigurationTypeDescription()
	{
		return "Play 1.x.x run configuration";
	}

	@Override
	public Icon getIcon()
	{
		return PlayJavaIcons.Play;
	}

	@NotNull
	@Override
	public String getId()
	{
		return "#" + getClass().getSimpleName();
	}

	@Override
	public ConfigurationFactory[] getConfigurationFactories()
	{
		return new ConfigurationFactory[] {configurationFactory};
	}
}
