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

package org.napile.playJava4idea.template.base.groovy;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.GroovyFileType;
import org.jetbrains.plugins.groovy.lang.lexer.GroovyLexer;
import org.jetbrains.plugins.groovy.lang.parser.GroovyParser;
import org.jetbrains.plugins.groovy.lang.parser.GroovyParserDefinition;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.ILazyParseableElementType;

/**
 * @author VISTALL
 * @since 19:12/23.03.13
 */
public class GroovyExpressionElementType extends ILazyParseableElementType
{
	public GroovyExpressionElementType(@NotNull @NonNls String debugName)
	{
		super(debugName);
	}


	@Override
	public ASTNode parseContents(ASTNode chameleon)
	{
		Project project = chameleon.getTreeParent().getPsi().getProject();
		PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, new GroovyLexer(), GroovyFileType.GROOVY_LANGUAGE, chameleon.getText());

		PsiBuilder.Marker mark = builder.mark();
		GroovyParser.parseExpression(builder);
		if(!builder.eof())
		{
			builder.error("expression expected");

			while(!builder.eof())
			{
				builder.advanceLexer();
			}
		}
		mark.done(GroovyParserDefinition.GROOVY_FILE);

		return builder.getTreeBuilt().getFirstChildNode();
	}
}
