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

package org.napile.playJava4idea.template.base;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplateElementType;
import org.napile.playJava4idea.template.base.psi.PlayBaseTemplateFile;
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
 * @since 12:07/18.03.13
 */
public class PlayBaseTemplateParserDefinition implements ParserDefinition
{
	@NotNull
	@Override
	public Lexer createLexer(Project project)
	{
		return null;
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
				return builder.getTreeBuilt();
			}
		};
	}

	@Override
	public IFileElementType getFileNodeType()
	{
		return PlayBaseTemplateTokens.PLAY_BASE_TEMPLATE_FILE;
	}

	@NotNull
	@Override
	public TokenSet getWhitespaceTokens()
	{
		return TokenSet.EMPTY;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens()
	{
		return TokenSet.EMPTY;
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
		final IElementType elementType = node.getElementType();

		if(elementType instanceof PlayBaseTemplateElementType)
		{
			return ((PlayBaseTemplateElementType) elementType).createPsiElement(node);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public PsiFile createFile(FileViewProvider viewProvider)
	{
		return new PlayBaseTemplateFile(viewProvider);
	}

	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
	{
		return SpaceRequirements.MAY;
	}
}
