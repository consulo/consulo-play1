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

import org.consulo.play1.module.extension.Play1ModuleExtension;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;

/**
 * @author VISTALL
 * @since 22:11/17.03.13
 */
public class PlayJavaCommandLineState extends CommandLineState
{
	protected PlayJavaCommandLineState(ExecutionEnvironment environment)
	{
		super(environment);
	}

	@NotNull
	@Override
	protected ProcessHandler startProcess() throws ExecutionException
	{
		PlayJavaModuleBasedConfiguration configuration = (PlayJavaModuleBasedConfiguration) getEnvironment().getRunProfile();

		Module module = configuration.getConfigurationModule().getModule();
		if(module == null)
		{
			throw new ExecutionException("No module");
		}

		Play1ModuleExtension moduleExtension = ModuleUtilCore.getExtension(module, Play1ModuleExtension.class);
		if(moduleExtension == null)
		{
			throw new ExecutionException("No Play 1.x.x extension");
		}
		final Sdk sdk = moduleExtension.getSdk();
		if(sdk == null)
		{
			throw new ExecutionException("No Play 1.x.x bundle");
		}

		GeneralCommandLine commandLine = new GeneralCommandLine();
		StringBuilder builder = new StringBuilder(sdk.getHomePath());
		builder.append("/");
		if(SystemInfo.isWindows)
		{
			builder.append("play.bat");
		}
		else
		{
			builder.append("play");
		}

		commandLine.setWorkDirectory(module.getModuleFile().getParent().getPath());
		commandLine.setExePath(builder.toString());
		commandLine.addParameter("run");
		return new OSProcessHandler(commandLine.createProcess());
	}
}
