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

package consulo.play1.template.base.groovy.file;

import org.jetbrains.plugins.groovy.lang.psi.impl.GroovyFileImpl;
import com.intellij.psi.FileViewProvider;

/**
 * @author VISTALL
 * @since 21:31/23.03.13
 */
public class PlayBaseTemplateGroovyFileImpl extends GroovyFileImpl
{
	public PlayBaseTemplateGroovyFileImpl(FileViewProvider viewProvider)
	{
		super(viewProvider);

		//init(PlayBaseTemplateTokens.PLAY_BASE_TEMPLATE_GROOVY_FILE, PlayBaseTemplateTokens.PLAY_BASE_TEMPLATE_GROOVY_FILE);
	}
}
