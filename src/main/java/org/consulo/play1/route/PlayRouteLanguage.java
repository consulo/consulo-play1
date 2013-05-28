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

package org.consulo.play1.route;

import com.intellij.lang.Language;

/**
 * @author VISTALL
 * @since 21:45/23.03.13
 */
public class PlayRouteLanguage extends Language
{
	public static final Language INSTANCE = new PlayRouteLanguage();

	protected PlayRouteLanguage()
	{
		super("PLAY-ROUTE");
	}
}
