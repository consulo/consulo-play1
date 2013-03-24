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

package org.napile.playJava4idea.route;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.route.psi.PlayRouteElementTokenSets;
import org.napile.playJava4idea.route.psi.PlayRouteElementTypes;
import org.napile.playJava4idea.route.psi.PlayRouteFile;
import org.napile.playJava4idea.route.psi.lexer.PlayRouteLexer;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 22:09/23.03.13
 */
public class PlayRouteParserDefinition implements ParserDefinition
{
	@NotNull
	@Override
	public Lexer createLexer(Project project)
	{
		return new PlayRouteLexer();
	}

	@Override
	public PsiParser createParser(Project project)
	{
		return new PsiParser()
		{
			@NotNull
			@Override
			public ASTNode parse(IElementType root, PsiBuilder builder)
			{
				PsiBuilder.Marker marker = builder.mark();
				while(!builder.eof())
				{
					builder.advanceLexer();
				}
				marker.done(root);
				return builder.getTreeBuilt();
			}
		};
	}

	@Override
	public IFileElementType getFileNodeType()
	{
		return PlayRouteElementTypes.FILE_ELEMENT_TYPE;
	}

	@NotNull
	@Override
	public TokenSet getWhitespaceTokens()
	{
		return PlayRouteElementTokenSets.WHITESPACE_SET;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens()
	{
		return PlayRouteElementTokenSets.COMMENT_SET;
	}

	@NotNull
	@Override
	public TokenSet getStringLiteralElements()
	{
		return TokenSet.EMPTY;
	}

	@NotNull
	@Override
	public PsiElement createElement(ASTNode node)
	{
		return new ASTWrapperPsiElement(node);
	}

	@Override
	public PsiFile createFile(FileViewProvider viewProvider)
	{
		return new PlayRouteFile(viewProvider);
	}

	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
	{
		return SpaceRequirements.MAY;
	}
}
