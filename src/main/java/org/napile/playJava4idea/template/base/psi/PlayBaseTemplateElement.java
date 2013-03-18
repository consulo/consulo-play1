package org.napile.playJava4idea.template.base.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

/**
 * @author VISTALL
 * @since 14:58/18.03.13
 */
public class PlayBaseTemplateElement extends ASTWrapperPsiElement
{
	public PlayBaseTemplateElement(@NotNull ASTNode node)
	{
		super(node);
	}
}
