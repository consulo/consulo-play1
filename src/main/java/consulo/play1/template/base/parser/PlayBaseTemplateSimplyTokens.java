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

package consulo.play1.template.base.parser;

import consulo.play1.template.base.parser.lexer.PlayBaseTemplatePrattTokenType;
import com.intellij.psi.TokenType;

/**
 * @author VISTALL
 * @since 16:54/18.03.13
 */
public interface PlayBaseTemplateSimplyTokens extends TokenType
{
	PlayBaseTemplatePrattTokenType STRING = new PlayBaseTemplatePrattTokenType("STRING");

	PlayBaseTemplatePrattTokenType LBRACE = new PlayBaseTemplatePrattTokenType("{");
	PlayBaseTemplatePrattTokenType RBRACE = new PlayBaseTemplatePrattTokenType("}");
	PlayBaseTemplatePrattTokenType COLON = new PlayBaseTemplatePrattTokenType(":");
	PlayBaseTemplatePrattTokenType COMMA = new PlayBaseTemplatePrattTokenType(",");

	PlayBaseTemplatePrattTokenType DIV = new PlayBaseTemplatePrattTokenType("/");

	PlayBaseTemplatePrattTokenType TEMPLATE_TEXT = new PlayBaseTemplatePrattTokenType("TEMPLATE_TEXT");

	// *{comments}*
	PlayBaseTemplatePrattTokenType ASTERISK = new PlayBaseTemplatePrattTokenType("*");
	// #{tags /}
	PlayBaseTemplatePrattTokenType SHARP = new PlayBaseTemplatePrattTokenType("#");
	// &{messages}
	PlayBaseTemplatePrattTokenType AND = new PlayBaseTemplatePrattTokenType("&");
	// %{script}%
	PlayBaseTemplatePrattTokenType PERC = new PlayBaseTemplatePrattTokenType("%");
	// ${expression}
	PlayBaseTemplatePrattTokenType DOLLAR = new PlayBaseTemplatePrattTokenType("$");
	// @{action}
	PlayBaseTemplatePrattTokenType AT = new PlayBaseTemplatePrattTokenType("@");
	// @@{absoluteAction}
	PlayBaseTemplatePrattTokenType ATAT = new PlayBaseTemplatePrattTokenType("@@");
}
