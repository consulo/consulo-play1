package org.napile.playJava4idea.run;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.facet.PlayJavaFacet;
import org.napile.playJava4idea.facet.PlayJavaFacetType;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.facet.FacetManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;

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

		PlayJavaFacet facetByType = FacetManager.getInstance(module).getFacetByType(PlayJavaFacetType.FACET_TYPE_ID);
		if(facetByType == null)
		{
			throw new ExecutionException("No Play 1.x.x facet");
		}

		GeneralCommandLine commandLine = new GeneralCommandLine();
		StringBuilder builder = new StringBuilder(FileUtil.toSystemIndependentName(facetByType.getConfiguration().playPath));
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
