package org.napile.playJava4idea.facet;

import java.io.File;

import org.jdom.Element;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.PlayJavaConstants;
import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;

/**
 * @author VISTALL
 * @since 14:45/17.03.13
 */
public class PlayJavaFacetConfiguration implements FacetConfiguration
{
	public String playPath;

	@Nullable
	public File resolvePlayPath()
	{
		File file = null;
		if(playPath == null || !(file = new File(playPath)).exists())
		{
			return null;
		}

		return file;
	}

	public String getPlayName()
	{
		File file = new File(playPath, "framework");
		if(!file.exists())
		{
			return null;
		}

		final File[] files = file.listFiles();
		if(files == null)
		{
			return null;
		}
		for(File child : files)
		{
			if(PlayJavaConstants.JAR_PATTERN.matcher(child.getName()).find())
			{
				return child.getName().replace(".jar", "");
			}
		}
		return null;
	}

	@Override
	public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager)
	{
		return new FacetEditorTab[] {new PlayJavaFacetEditorTab(editorContext, validatorsManager)};
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException
	{
		DefaultJDOMExternalizer.readExternal(this, element);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException
	{
		DefaultJDOMExternalizer.writeExternal(this, element);
	}
}
