/*
 * Copyright 2010-2013 napile.org
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

package org.napile.playJava4idea.conf;

import java.nio.charset.Charset;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.PlayJavaConstants;
import com.intellij.icons.AllIcons;
import com.intellij.lang.properties.PropertiesBundle;
import com.intellij.lang.properties.PropertiesLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.ex.FileTypeIdentifiableByVirtualFile;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingManager;

/**
 * @author VISTALL
 * @since 19:55/23.03.13
 */
public class PlayJavaConfFileType extends LanguageFileType implements FileTypeIdentifiableByVirtualFile
{
	public static final PlayJavaConfFileType INSTANCE = new PlayJavaConfFileType();

	private PlayJavaConfFileType()
	{
		super(PropertiesLanguage.INSTANCE);
	}

	@Override
	public Icon getIcon()
	{
		return AllIcons.FileTypes.Properties;
	}

	@Override
	public String getCharset(@NotNull VirtualFile file, final byte[] content)
	{
		Charset charset = EncodingManager.getInstance().getDefaultCharsetForPropertiesFiles(file);
		return charset == null ? CharsetToolkit.getDefaultSystemCharset().name() : charset.name();
	}

	@Override
	@NotNull
	public String getName()
	{
		return "PlayApplicationConf";
	}

	@Override
	@NotNull
	public String getDescription()
	{
		return PropertiesBundle.message("properties.files.file.type.description");
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "";
	}

	@Override
	public boolean isMyFileType(VirtualFile file)
	{
		return file.getName().equalsIgnoreCase(PlayJavaConstants.APPLICATION_CONF) && file.getParent().getName().equalsIgnoreCase(PlayJavaConstants.CONF);
	}
}
