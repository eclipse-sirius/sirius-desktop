/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * A palette drawer associated to a section.
 * 
 * @author mchauvin
 */
public class SectionPaletteDrawer extends PaletteDrawer implements ILayerContributionRegister {
    private Set<Layer> contributingLayers = new HashSet<Layer>();

    /**
     * Construct a new instance.
     * 
     * @param label
     *            the label
     */
    public SectionPaletteDrawer(final String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.palette.ILayerContributionRegister#addLayer(org.eclipse.sirius.viewpoint.description.Layer)
     */
    public void addLayer(final Layer layer) {
        if (!EqualityHelper.contains(contributingLayers, layer)) {
            contributingLayers.add(layer);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.palette.ILayerContributionRegister#removeLayer(org.eclipse.sirius.viewpoint.description.Layer)
     */
    public void removeLayer(final Layer layer) {
        EqualityHelper.remove(contributingLayers, layer);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.palette.ILayerContributionRegister#isEmptyOfContributors()
     */
    public boolean isEmptyOfContributors() {
        return contributingLayers.isEmpty();
    }
}
