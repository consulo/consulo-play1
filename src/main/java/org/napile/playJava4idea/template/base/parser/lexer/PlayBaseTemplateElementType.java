package org.napile.playJava4idea.template.base.parser.lexer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import org.napile.playJava4idea.template.base.psi.PlayBaseTemplateElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 14:58/18.03.13
 */
public class PlayBaseTemplateElementType extends IElementType
{
	public PlayBaseTemplateElementType(@NotNull @NonNls String debugName)
	{
		super(debugName, PlayBaseTemplateLanguage.INSTANCE);
	}

	public PsiElement createPsiElement(ASTNode node)
	{
		return new PlayBaseTemplateElement(node);
	}

}
