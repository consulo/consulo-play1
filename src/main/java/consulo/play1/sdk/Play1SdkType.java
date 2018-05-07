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

package consulo.play1.sdk;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.VirtualFile;
import consulo.play1.PlayJavaConstants;
import consulo.play1.PlayJavaIcons;
import consulo.roots.types.BinariesOrderRootType;
import consulo.roots.types.SourcesOrderRootType;
import consulo.ui.image.Image;
import consulo.vfs.util.ArchiveVfsUtil;

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
	public Image getIcon()
	{
		return PlayJavaIcons.Play;
	}

	@Override
	public void setupSdkPaths(Sdk sdk)
	{
		final SdkModificator sdkModificator = sdk.getSdkModificator();

		VirtualFile homeDirectory = sdk.getHomeDirectory();
		assert homeDirectory != null;

		VirtualFile framework = homeDirectory.findChild("framework");
		assert framework != null;


		for(VirtualFile virtualFile : framework.getChildren())
		{
			final String name = virtualFile.getName();
			if(PlayJavaConstants.JAR_PATTERN.matcher(name).find())
			{
				VirtualFile archiveRootForLocalFile = ArchiveVfsUtil.getArchiveRootForLocalFile(virtualFile);
				if(archiveRootForLocalFile != null)
				{
					sdkModificator.addRoot(archiveRootForLocalFile, BinariesOrderRootType.getInstance());
				}
			}
			else if(virtualFile.isDirectory())
			{
				if(name.equals("lib") || name.equals("lib-test"))
				{
					for(VirtualFile file : virtualFile.getChildren())
					{
						VirtualFile archiveRootForLocalFile = ArchiveVfsUtil.getArchiveRootForLocalFile(file);
						if(archiveRootForLocalFile != null)
						{
							sdkModificator.addRoot(archiveRootForLocalFile, BinariesOrderRootType.getInstance());
						}
					}
				}
				else if(name.equals("src"))
				{
					sdkModificator.addRoot(virtualFile, SourcesOrderRootType.getInstance());
				}
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
	public boolean isRootTypeApplicable(OrderRootType type)
	{
		return JavaSdk.getInstance().isRootTypeApplicable(type);
	}

	@Override
	public String suggestSdkName(String s, String s2)
	{
		return "play";
	}

	@Nonnull
	@Override
	public String getPresentableName()
	{
		return getName();
	}
}
