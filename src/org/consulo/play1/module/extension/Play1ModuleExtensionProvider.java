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

package org.consulo.play1.module.extension;

import javax.swing.Icon;

import org.consulo.module.extension.ModuleExtensionProvider;
import org.consulo.play1.PlayJavaIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;

/**
 * @author VISTALL
 * @since 15:50/28.05.13
 */
public class Play1ModuleExtensionProvider implements ModuleExtensionProvider<Play1ModuleExtension, Play1MutableModuleExtension>
{
	@Nullable
	@Override
	public Icon getIcon()
	{
		return PlayJavaIcons.Play;
	}

	@NotNull
	@Override
	public String getName()
	{
		return "Play 1.x.x";
	}

	@NotNull
	@Override
	public Play1ModuleExtension createImmutable(@NotNull String s, @NotNull Module module)
	{
		return new Play1ModuleExtension(s, module);
	}

	@NotNull
	@Override
	public Play1MutableModuleExtension createMutable(@NotNull String s, @NotNull Module module)
	{
		return new Play1MutableModuleExtension(s, module);
	}
}
