package org.napile.playJava4idea.template.base.parser;

import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplatePrattTokenType;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * @author VISTALL
 * @since 13:18/18.03.13
 */
public interface PlayBaseTemplateTokens
{
	IElementType OUTER_ELEMENT_TYPE = new IElementType("PLAY_BASE_TEMPLATE_FRAGMENT", PlayBaseTemplateLanguage.INSTANCE);

	PlayBaseTemplatePrattTokenType TEMPLATE_TEXT = new PlayBaseTemplatePrattTokenType("PLAY_BASE_TEMPLATE_FRAGMENT");

	TemplateDataElementType TEMPLATE_DATA = new TemplateDataElementType("PLAY_BASE_TEMPLATE_DATA", PlayBaseTemplateLanguage.INSTANCE, TEMPLATE_TEXT, OUTER_ELEMENT_TYPE);

	IFileElementType PLAY_BASE_TEMPLATE_FILE = new IFileElementType("PLAY_BASE_TEMPLATE_FILE", PlayBaseTemplateLanguage.INSTANCE);

}
