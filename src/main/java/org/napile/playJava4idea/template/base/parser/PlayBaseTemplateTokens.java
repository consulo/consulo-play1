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

package org.napile.playJava4idea.template.base.parser;

import org.napile.playJava4idea.template.base.PlayBaseTemplateLanguage;
import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplateElementType;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * @author VISTALL
 * @since 13:18/18.03.13
 */
public interface PlayBaseTemplateTokens extends PlayBaseTemplateSimplyTokens
{
	IElementType OUTER_ELEMENT_TYPE = new IElementType("OUTER_ELEMENT_TYPE", PlayBaseTemplateLanguage.INSTANCE);

	TemplateDataElementType TEMPLATE_DATA = new TemplateDataElementType("TEMPLATE_DATA", PlayBaseTemplateLanguage.INSTANCE, TEMPLATE_TEXT, OUTER_ELEMENT_TYPE);

	IFileElementType PLAY_BASE_TEMPLATE_FILE = new IFileElementType("PLAY_BASE_TEMPLATE_FILE", PlayBaseTemplateLanguage.INSTANCE);

	PlayBaseTemplateElementType COMMENT = new PlayBaseTemplateElementType("COMMENT");

	PlayBaseTemplateElementType TAG_NAME = new PlayBaseTemplateElementType("TAG_NAME");

	// ${ }
	PlayBaseTemplateElementType EXPRESSION_START = new PlayBaseTemplateElementType("EXPRESSION_START");



	PlayBaseTemplateElementType EXPRESSION_END = new PlayBaseTemplateElementType("EXPRESSION_END");
}
