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

package consulo.play1.route.psi;

import consulo.play1.route.PlayRouteLanguage;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 14:09/24.03.13
 */
public interface PlayRouteNodes
{
	IElementType ROUTE_LINE = new IElementType("ROUTE_LINE", PlayRouteLanguage.INSTANCE);

	IElementType ACTION_REF = new IElementType("ACTION_REF", PlayRouteLanguage.INSTANCE);
}
