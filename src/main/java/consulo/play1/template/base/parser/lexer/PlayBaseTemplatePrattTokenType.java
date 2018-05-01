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

package consulo.play1.template.base.parser.lexer;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import consulo.play1.template.base.PlayBaseTemplateLanguage;
import com.intellij.lang.pratt.PrattTokenType;

/**
 * @author VISTALL
 * @since 14:50/18.03.13
 */
public class PlayBaseTemplatePrattTokenType extends PrattTokenType
{
	public PlayBaseTemplatePrattTokenType(@Nonnull @NonNls String debugName)
	{
		super(debugName, PlayBaseTemplateLanguage.INSTANCE);
	}
}
