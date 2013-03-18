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

package org.napile.playJava4idea.facet;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.napile.playJava4idea.icons.PlayJavaIcons;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeId;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;

/**
 * @author VISTALL
 * @since 14:44/17.03.13
 */
public class PlayJavaFacetType extends FacetType<PlayJavaFacet, PlayJavaFacetConfiguration>
{
	public static final FacetTypeId<PlayJavaFacet> FACET_TYPE_ID = new FacetTypeId<PlayJavaFacet>("playJavaFacet");

	public PlayJavaFacetType()
	{
		super(FACET_TYPE_ID, FACET_TYPE_ID.toString(), "Play 1.x.x", null);
	}

	@Override
	public PlayJavaFacetConfiguration createDefaultConfiguration()
	{
		return new PlayJavaFacetConfiguration();
	}

	@Override
	public PlayJavaFacet createFacet(@NotNull Module module, String name, @NotNull PlayJavaFacetConfiguration configuration, @Nullable Facet underlyingFacet)
	{
		return new PlayJavaFacet(this, module, name, configuration, underlyingFacet);
	}

	@Override
	public boolean isSuitableModuleType(ModuleType moduleType)
	{
		return moduleType == JavaModuleType.getModuleType();
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return PlayJavaIcons.ICON_16x16;
	}
}
