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

package consulo.play1.run;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jdom.Element;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;

/**
 * @author VISTALL
 * @since 22:04/17.03.13
 */
public class PlayJavaModuleBasedConfiguration extends ModuleBasedConfiguration<RunConfigurationModule>
{
	public String programParameters;

	public PlayJavaModuleBasedConfiguration(Project project, String name, ConfigurationFactory f)
	{
		super(name, new RunConfigurationModule(project), f);
	}

	@Override
	public Collection<Module> getValidModules()
	{
		Module[] modules = ModuleManager.getInstance(getProject()).getModules();
		return Arrays.asList(modules);
	}

	@Override
	protected ModuleBasedConfiguration createInstance()
	{
		return new PlayJavaModuleBasedConfiguration(getProject(), getName(), getFactory());
	}

	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		return new PlayJavaConfigutationEditor();
	}

	@Nullable
	@Override
	public RunProfileState getState(@Nonnull Executor executor, @Nonnull ExecutionEnvironment env) throws ExecutionException
	{
		final PlayJavaCommandLineState state = new PlayJavaCommandLineState(env);

		final TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(getProject());

		state.setConsoleBuilder(builder);
		return state;
	}

	@Override
	public void readExternal(final Element element) throws InvalidDataException
	{
		super.readExternal(element);

		XmlSerializer.deserializeInto(this, element);
	}

	@Override
	public void writeExternal(final Element element) throws WriteExternalException
	{
		super.writeExternal(element);

		XmlSerializer.serializeInto(this, element);
	}
}
