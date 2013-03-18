package org.napile.playJava4idea.template.base;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.icons.PlayJavaIcons;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;

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

	@NotNull
	@Override
	public String getName()
	{
		return "Play";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "Play template file";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "play";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return PlayJavaIcons.ICON_16x16;
	}
}
