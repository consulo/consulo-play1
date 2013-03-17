package org.napile.playJava4idea.facet;

import org.jetbrains.annotations.NotNull;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;

/**
 * @author VISTALL
 * @since 14:44/17.03.13
 */
public class PlayJavaFacet extends Facet<PlayJavaFacetConfiguration>
{
	public PlayJavaFacet(@NotNull FacetType facetType, @NotNull Module module, @NotNull String name, @NotNull PlayJavaFacetConfiguration configuration, Facet underlyingFacet)
	{
		super(facetType, module, name, configuration, underlyingFacet);
	}
}
