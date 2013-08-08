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

package org.consulo.play1.sdk;

import com.intellij.ide.highlighter.JarArchiveFileType;
import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.ArchiveFileSystem;
import org.consulo.play1.PlayJavaConstants;
import org.consulo.play1.PlayJavaIcons;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

/**
 * @author VISTALL
 * @since 15:52/28.05.13
 */
public class Play1SdkType extends SdkType
{
	public Play1SdkType()
	{
		super("Play 1.x.x");
	}

	@Nullable
	@Override
	public String suggestHomePath()
	{
		return null;
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return PlayJavaIcons.ICON_16x16;
	}

	@Nullable
	@Override
	public Icon getGroupIcon()
	{
		return getIcon();
	}

	@Override
	public void setupSdkPaths(Sdk sdk)
	{
		final SdkModificator sdkModificator = sdk.getSdkModificator();
		final ArchiveFileSystem jfs = JarArchiveFileType.INSTANCE.getFileSystem();

		File file = new File(sdk.getHomePath(), "framework");
		if(!file.exists())
		{
			return;
		}

		final File[] files = file.listFiles();
		if(files == null)
		{
			return;
		}

		for(File child : files)
		{
			final String name = child.getName();
			if(PlayJavaConstants.JAR_PATTERN.matcher(name).find())
			{
				sdkModificator.addRoot(jfs.findLocalVirtualFileByPath(child.getPath()), OrderRootType.CLASSES);
				break;
			}
		}
		sdkModificator.commitChanges();
	}

	@Override
	public boolean isValidSdkHome(String s)
	{
		File file = new File(s, "framework");
		if(!file.exists())
		{
			return false;
		}

		final File[] files = file.listFiles();
		if(files == null)
		{
			return false;
		}
		for(File child : files)
		{
			if(PlayJavaConstants.JAR_PATTERN.matcher(child.getName()).find())
			{
				return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public String getVersionString(String s)
	{
		File file = new File(s, "framework");
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
			final String name = child.getName();
			if(PlayJavaConstants.JAR_PATTERN.matcher(name).find())
			{
				String versionString = name.replace("play-", "");
				versionString = versionString.replace(".jar", "");
				return versionString;
			}
		}
		return null;
	}

	@Override
	public String suggestSdkName(String s, String s2)
	{
		return "play";
	}

	@Nullable
	@Override
	public AdditionalDataConfigurable createAdditionalDataConfigurable(SdkModel sdkModel, SdkModificator sdkModificator)
	{
		return null;
	}

	@NotNull
	@Override
	public String getPresentableName()
	{
		return getName();
	}

	@Override
	public void saveAdditionalData(SdkAdditionalData sdkAdditionalData, Element element)
	{

	}
}
