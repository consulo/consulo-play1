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

package consulo.play1.template.base.fileViewProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.plugins.groovy.GroovyLanguage;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.ConfigurableTemplateLanguageFileViewProvider;
import consulo.play1.template.base.PlayBaseTemplateLanguage;
import consulo.play1.template.base.parser.PlayBaseTemplateTokens;

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
	protected PsiFile createFile(@Nonnull Language lang)
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

		if(lang == GroovyLanguage.INSTANCE)
		{
			PsiFileImpl file = (PsiFileImpl) LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
			file.setContentElementType(PlayBaseTemplateTokens.GROOVY_TEMPLATE_DATA);
			return file;
		}

		return null;
	}

	@Override
	@Nonnull
	public Set<Language> getLanguages()
	{
		//TODO [VISTALL] uncomment then groovy ill supported
		return new HashSet<Language>(Arrays.asList(PlayBaseTemplateLanguage.INSTANCE, HTMLLanguage.INSTANCE, GroovyLanguage.INSTANCE));
	}

	@Nonnull
	@Override
	public Language getBaseLanguage()
	{
		return PlayBaseTemplateLanguage.INSTANCE;
	}

	@Nonnull
	@Override
	public Language getTemplateDataLanguage()
	{
		return HTMLLanguage.INSTANCE;
	}

	@Override
	protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy)
	{
		return new PlayBaseTemplateFileViewProvider(getManager(), fileCopy, isPhysical());
	}
}
