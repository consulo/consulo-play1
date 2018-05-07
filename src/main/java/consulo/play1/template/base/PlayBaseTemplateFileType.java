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

package consulo.play1.template.base;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.play1.PlayJavaIcons;
import consulo.ui.image.Image;

/**
 * @author VISTALL
 * @since 14:53/18.03.13
 */
public class PlayBaseTemplateFileType extends LanguageFileType
{
	public static final FileType INSTANCE = new PlayBaseTemplateFileType();

	public PlayBaseTemplateFileType()
	{
		super(PlayBaseTemplateLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "Play";
	}

	@Nonnull
	@Override
	public String getDescription()
	{
		return "Play template file";
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "play";
	}

	@Nullable
	@Override
	public Image getIcon()
	{
		return PlayJavaIcons.Play;
	}
}
