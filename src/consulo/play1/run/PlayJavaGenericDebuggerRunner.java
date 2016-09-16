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

import consulo.play1.PlayJavaConstants;
import consulo.play1.PlayJavaUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.debugger.impl.GenericDebuggerRunner;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RemoteConnection;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;

/**
 * @author VISTALL
 * @since 19:35/23.03.13
 */
public class PlayJavaGenericDebuggerRunner extends GenericDebuggerRunner
{
	@Override
	public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile)
	{
		return super.canRun(executorId, profile) && profile instanceof PlayJavaModuleBasedConfiguration;
	}

	@Nullable
	@Override
	protected RunContentDescriptor createContentDescriptor(@NotNull RunProfileState state, @NotNull ExecutionEnvironment environment) throws
			ExecutionException
	{
		final PlayJavaModuleBasedConfiguration runProfile = (PlayJavaModuleBasedConfiguration) environment.getRunProfile();

		final PropertiesFile applicationConf = PlayJavaUtil.getApplicationConf(runProfile.getConfigurationModule().getModule());
		if(applicationConf == null)
		{
			throw new ExecutionException(PlayJavaConstants.CONF__APPLICATION_CONF + " not found");
		}

		final IProperty propertyByKey = applicationConf.findPropertyByKey(PlayJavaConstants.JPDA_PORT);
		if(propertyByKey == null)
		{
			throw new ExecutionException(PlayJavaConstants.JPDA_PORT + " key not found");
		}

		RemoteConnection connection = new RemoteConnection(true, "127.0.0.1", propertyByKey.getValue(), false);

		return attachVirtualMachine(state, environment, connection, true);
	}
}
