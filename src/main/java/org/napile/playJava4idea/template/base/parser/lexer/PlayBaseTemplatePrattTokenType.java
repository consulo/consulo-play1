package org.napile.playJava4idea.template.base.parser.lexer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import com.intellij.lang.pratt.PrattTokenType;

/**
 * @author VISTALL
 * @since 14:50/18.03.13
 */
public class PlayBaseTemplatePrattTokenType extends PrattTokenType
{
	public PlayBaseTemplatePrattTokenType(@NotNull @NonNls String debugName)
	{
		super(debugName, PlayBaseTemplateLanguage.INSTANCE);
	}
}
