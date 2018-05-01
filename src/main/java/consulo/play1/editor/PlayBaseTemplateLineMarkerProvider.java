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

package consulo.play1.editor;

import java.awt.event.MouseEvent;

import javax.annotation.Nonnull;

import consulo.play1.PlayJavaIcons;
import consulo.play1.PlayJavaUtil;
import consulo.play1.template.base.psi.PlayBaseTemplateFile;

import javax.annotation.Nullable;
import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.util.ConstantFunction;
import consulo.roots.ContentFolderScopes;

/**
 * @author VISTALL
 * @since 23:21/18.03.13
 */
public class PlayBaseTemplateLineMarkerProvider implements LineMarkerProvider
{
	public static final GutterIconNavigationHandler<PsiElement> GUTTER_ICON_NAVIGATION_HANDLER = new GutterIconNavigationHandler<PsiElement>()
	{
		@Override
		public void navigate(MouseEvent mouseEvent, PsiElement psiElement)
		{
			PlayBaseTemplateFile templateFile = findTemplateFile(psiElement);
			if(templateFile != null)
			{
				templateFile.navigate(true);
			}
		}
	};

	@Nullable
	@Override
	public LineMarkerInfo getLineMarkerInfo(@Nonnull PsiElement psiElement)
	{
		PlayBaseTemplateFile templateFile = findTemplateFile(psiElement);
		if(templateFile != null)
		{

			return new LineMarkerInfo<>(psiElement, psiElement.getTextRange(), PlayJavaIcons.PlayLineMarker, Pass.LINE_MARKERS,
					new ConstantFunction<>("Navigate to template"), GUTTER_ICON_NAVIGATION_HANDLER, GutterIconRenderer.Alignment.LEFT);
		}
		return null;
	}


	private static PlayBaseTemplateFile findTemplateFile(PsiElement element)
	{
		if(element instanceof PsiIdentifier && element.getParent() instanceof PsiMethod)
		{
			PsiMethod method = (PsiMethod) element.getParent();

			final PsiClass containingClass = method.getContainingClass();

			if(PlayJavaUtil.isSuperController(containingClass) && method.hasModifierProperty(PsiModifier.STATIC) && method.hasModifierProperty(PsiModifier.PUBLIC))
			{
				assert containingClass != null;

				String qName = StringUtil.notNullize(containingClass.getQualifiedName());
				qName = qName.replace(".", "/");

				if(qName.startsWith("controllers"))
				{
					qName = qName.replace("controllers", "views");
				}

				final Module moduleForPsiElement = ModuleUtil.findModuleForPsiElement(element);
				if(moduleForPsiElement == null)
				{
					return null;
				}

				ModuleRootManager rootManager = ModuleRootManager.getInstance(moduleForPsiElement);
				PsiManager manager = PsiManager.getInstance(element.getProject());
				for(VirtualFile file : rootManager.getContentFolderFiles(ContentFolderScopes.production()))
				{
					VirtualFile virtualFile = file.findFileByRelativePath(qName);
					if(virtualFile == null)
					{
						continue;
					}

					for(VirtualFile child : virtualFile.getChildren())
					{
						if(child.getNameWithoutExtension().equalsIgnoreCase(method.getName()))
						{
							PsiFile psiFile = manager.findFile(child);
							if(psiFile instanceof PlayBaseTemplateFile)
							{
								return (PlayBaseTemplateFile) psiFile;
							}
						}
					}
				}
			}
		}
		return null;
	}
}
