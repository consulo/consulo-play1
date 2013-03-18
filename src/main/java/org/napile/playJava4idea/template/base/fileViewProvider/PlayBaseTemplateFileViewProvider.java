package org.napile.playJava4idea.template.base.fileViewProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.StdLanguages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.ConfigurableTemplateLanguageFileViewProvider;

/**
 * @author VISTALL
 * @since 13:06/18.03.13
 */
public class PlayBaseTemplateFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements ConfigurableTemplateLanguageFileViewProvider
{
	public PlayBaseTemplateFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical)
	{
		super(manager, virtualFile, physical);
	}

	@Nullable
	@Override
	protected PsiFile createFile(@NotNull Language lang)
	{
		if(lang == getBaseLanguage())
		{
			return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
		}

		if(lang == getTemplateDataLanguage())
		{
			PsiFileImpl file = (PsiFileImpl) LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
			file.setContentElementType(PlayBaseTemplateTokens.TEMPLATE_DATA);
			return file;
		}

		throw new UnsupportedOperationException("This is not supported: " + lang);
	}

	@Override
	@NotNull
	public Set<Language> getLanguages()
	{
		return new HashSet<Language>(Arrays.asList(PlayBaseTemplateLanguage.INSTANCE, StdLanguages.HTML/*, GroovyFileType.GROOVY_LANGUAGE*/));
	}

	@NotNull
	@Override
	public Language getBaseLanguage()
	{
		return PlayBaseTemplateLanguage.INSTANCE;
	}

	@NotNull
	@Override
	public Language getTemplateDataLanguage()
	{
		return StdLanguages.HTML;
	}

	@Override
	protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy)
	{
		return new PlayBaseTemplateFileViewProvider(getManager(), fileCopy, isPhysical());
	}
}
