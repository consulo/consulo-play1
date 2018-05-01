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

import consulo.play1.route.psi.PlayRouteActionElement;
import consulo.play1.route.psi.PlayRouteElementTokenSets;
import consulo.play1.route.psi.PlayRouteElementTypes;
import consulo.play1.route.psi.PlayRouteFile;
import consulo.play1.route.psi.PlayRouteNodes;
import consulo.play1.route.psi.lexer.PlayRouteLexer;
import javax.annotation.Nonnull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 22:09/23.03.13
 */
public class PlayRouteParserDefinition implements ParserDefinition
{
	@Nonnull
	@Override
	public Lexer createLexer(LanguageVersion languageVersion)
	{
		return new PlayRouteLexer();
	}

	@Override
	public PsiParser createParser(LanguageVersion languageVersion)
	{
		return new PlayRouteParser();
	}

	@Override
	public IFileElementType getFileNodeType()
	{
		return PlayRouteElementTypes.FILE_ELEMENT_TYPE;
	}

	@Nonnull
	@Override
	public TokenSet getWhitespaceTokens(LanguageVersion languageVersion)
	{
		return PlayRouteElementTokenSets.WHITESPACE_SET;
	}

	@Nonnull
	@Override
	public TokenSet getCommentTokens(LanguageVersion languageVersion)
	{
		return PlayRouteElementTokenSets.COMMENT_SET;
	}

	@Nonnull
	@Override
	public TokenSet getStringLiteralElements(LanguageVersion languageVersion)
	{
		return TokenSet.EMPTY;
	}

	@Nonnull
	@Override
	public PsiElement createElement(ASTNode node)
	{
		if(node.getElementType() == PlayRouteNodes.ACTION_REF)
		{
			return new PlayRouteActionElement(node);
		}
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
