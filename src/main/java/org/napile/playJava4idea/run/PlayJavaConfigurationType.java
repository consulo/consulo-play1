package org.napile.playJava4idea.run;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.icons.PlayJavaIcons;
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
		return PlayJavaIcons.ICON_16x16;
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
