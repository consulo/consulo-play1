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
