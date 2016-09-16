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

package consulo.play1.route;

import org.jetbrains.annotations.NotNull;
import consulo.play1.route.psi.PlayRouteElementTypes;
import consulo.play1.route.psi.PlayRouteNodes;
import com.intellij.lang.ASTNode;
import consulo.lang.LanguageVersion;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 14:13/24.03.13
 */
public class PlayRouteParser implements PsiParser, PlayRouteElementTypes, PlayRouteNodes
{
	@NotNull
	@Override
	public ASTNode parse(IElementType root, PsiBuilder builder, LanguageVersion languageVersion)
	{
		PsiBuilder.Marker marker = builder.mark();
		while(!builder.eof())
		{
			if(builder.getTokenType() == METHOD_TYPE)
			{
				PsiBuilder.Marker lineMark = builder.mark();

				builder.advanceLexer();

				if(expect(builder, PATH))
				{
					if(builder.getTokenType() == ACTION_NAME)
					{
						PsiBuilder.Marker refMark = builder.mark();
						builder.advanceLexer();
						refMark.done(ACTION_REF);
					}
					else
					{
						builder.error("expect " + ACTION_NAME);
					}
				}

				lineMark.done(ROUTE_LINE);
			}
			else
			{
				builder.advanceLexer();
			}
		}
		marker.done(root);
		return builder.getTreeBuilt();
	}

	private static boolean expect(PsiBuilder builder, IElementType elementType)
	{
		if(builder.getTokenType() == elementType)
		{
			builder.advanceLexer();
			return true;
		}
		else
		{
			builder.error("expect " + elementType);
			return false;
		}
	}
}
