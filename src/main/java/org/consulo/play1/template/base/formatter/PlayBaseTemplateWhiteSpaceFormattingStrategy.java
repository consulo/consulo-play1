/*
 * Copyright 2013 Consulo.org
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

package org.consulo.play1.template.base.formatter;

import org.jetbrains.annotations.NotNull;
import org.consulo.play1.template.base.parser.PlayBaseTemplateTokens;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.formatter.WhiteSpaceFormattingStrategyAdapter;

/**
 * @author VISTALL
 * @since 18:08/26.03.13
 */
public class PlayBaseTemplateWhiteSpaceFormattingStrategy extends WhiteSpaceFormattingStrategyAdapter
{
	@Override
	public boolean containsWhitespacesOnly(@NotNull ASTNode node)
	{
		return node.getElementType() == PlayBaseTemplateTokens.TEMPLATE_TEXT && StringUtil.isEmptyOrSpaces(node.getText());
	}
}
