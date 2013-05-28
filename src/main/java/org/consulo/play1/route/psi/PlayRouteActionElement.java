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

package org.consulo.play1.route.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * @author VISTALL
 * @since 13:48/24.03.13
 */
public class PlayRouteActionElement extends ASTWrapperPsiElement
{
	public PlayRouteActionElement(@NotNull ASTNode node)
	{
		super(node);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences()
	{
		return new PsiReference[]
		{
			new PsiPolyVariantReferenceBase<PlayRouteActionElement>(this, getTextRange(), false)
			{
				@NotNull
				@Override
				public ResolveResult[] multiResolve(boolean incompleteCode)
				{
					final PsiMethod method = resolveMethod();
					if(method == null)
					{
						return ResolveResult.EMPTY_ARRAY;
					}
					else
					{
						return new ResolveResult[] {new PsiElementResolveResult(method, true)};
					}
				}

				private PsiMethod resolveMethod()
				{
					String text = getText();
					if(text.contains(":") || text.isEmpty())
					{
						return null;
					}

					// class cant start with any digital
					if(Character.isDigit(text.charAt(0)))
					{
						return null;
					}

					// test.Action(id)
					String actionName = text.contains("(") ? text.substring(0, text.indexOf("(")) : text;

					final int indexOfDot = actionName.lastIndexOf(".");

					final String className = actionName.substring(0, indexOfDot);
					final String methodName = actionName.substring(indexOfDot + 1, actionName.length());

					final PsiClass aClass = JavaPsiFacade.getInstance(getProject()).findClass(className, GlobalSearchScope.allScope(getProject()));
					if(aClass == null)
					{
						return null;
					}

					if(methodName.isEmpty())
					{
						return null;
					}

					final PsiMethod[] methodsByName = aClass.findMethodsByName(methodName, true);
					for(PsiMethod method : methodsByName)
					{
						if(method.hasModifierProperty(PsiModifier.STATIC) && method.hasModifierProperty(PsiModifier.PUBLIC))
						{
							return method;
						}
					}
					return null;
				}

				@NotNull
				@Override
				public Object[] getVariants()
				{
					return new Object[0];
				}
			}
		};
	}
}