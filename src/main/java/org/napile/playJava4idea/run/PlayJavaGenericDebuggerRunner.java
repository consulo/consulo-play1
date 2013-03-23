package org.napile.playJava4idea.run;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.PlayJavaConstants;
import org.napile.playJava4idea.PlayJavaUtil;
import com.intellij.debugger.impl.GenericDebuggerRunner;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RemoteConnection;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.openapi.project.Project;

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
	protected RunContentDescriptor createContentDescriptor(Project project, Executor executor, RunProfileState state, RunContentDescriptor contentToReuse, ExecutionEnvironment env) throws ExecutionException
	{
		final PlayJavaModuleBasedConfiguration runProfile = (PlayJavaModuleBasedConfiguration) env.getRunProfile();

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

		return attachVirtualMachine(project, executor, state, contentToReuse, env, connection, true);
	}
}
