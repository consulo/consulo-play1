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

package org.napile.playJava4idea.route.psi;

import org.napile.playJava4idea.route.PlayRouteLanguage;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * @author VISTALL
 * @since 22:06/23.03.13
 */
public interface PlayRouteElementTypes extends TokenType
{
	IFileElementType FILE_ELEMENT_TYPE = new IFileElementType("FILE_ELEMENT_TYPE", PlayRouteLanguage.INSTANCE);

	IElementType SOME_REFERENCE = new IElementType("SOME_REFERENCE", PlayRouteLanguage.INSTANCE);

	IElementType COMMENT = new IElementType("COMMENT", PlayRouteLanguage.INSTANCE);

	IElementType METHOD_TYPE = new IElementType("METHOD_TYPE", PlayRouteLanguage.INSTANCE);

	IElementType PATH = new IElementType("PATH", PlayRouteLanguage.INSTANCE);

	IElementType ACTION_NAME = new IElementType("ACTION_NAME", PlayRouteLanguage.INSTANCE);
}
