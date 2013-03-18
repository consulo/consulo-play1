package org.napile.playJava4idea.template.base;

import com.intellij.lang.Language;
import com.intellij.psi.templateLanguages.TemplateLanguage;

/**
 * @author VISTALL
 * @since 11:57/18.03.13
 */
public class PlayBaseTemplateLanguage extends Language implements TemplateLanguage
{
	public static Language INSTANCE = new PlayBaseTemplateLanguage();

	public PlayBaseTemplateLanguage()
	{
		super("PLAY-BASE-TEMPLATE");
	}
}
