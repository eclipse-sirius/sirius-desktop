/*******************************************************************************
 * Copyright (c) 2013, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.model.business.internal.query;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Queries for DDiagramElementContainer.
 * 
 * @author mporhel
 */
public class DDiagramElementContainerExperimentalQuery {

    private final DDiagramElementContainer container;

    /**
     * Constructor.
     * 
     * @param container
     *            the DDiagramElementContainer to query.
     */
    public DDiagramElementContainerExperimentalQuery(DDiagramElementContainer container) {
        this.container = container;
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child of a region container, see
     * {@link DNodeContainerExperimentalQuery#isRegionContainer()}).
     * 
     * @return <code>true</code> if the {@link DNodeContainer} is a region.
     */
    public boolean isRegion() {
        if (container != null) {
            EObject parentContainer = container.eContainer();
            if (parentContainer instanceof DNodeContainer) {
                return new DNodeContainerExperimentalQuery((DNodeContainer) parentContainer).isRegionContainer();
            }
        }
        return false;
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child of an horizontal stack region container,
     * see {@link DNodeContainerExperimentalQuery#isHorizontaltackContainer()}).
     * 
     * @return <code>true</code> if the {@link DNodeContainer} is a region.
     */
    public boolean isRegionInHorizontalStack() {
        if (container != null) {
            EObject parentContainer = container.eContainer();
            if (parentContainer instanceof DNodeContainer) {
                return new DNodeContainerExperimentalQuery((DNodeContainer) parentContainer).isHorizontaltackContainer();
            }
        }
        return false;
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child of a vertical stack region container, see
     * {@link DNodeContainerExperimentalQuery#isVerticalStackContainer()}).
     * 
     * @return <code>true</code> if the {@link DNodeContainer} is a region.
     */
    public boolean isRegionInVerticalStack() {
        if (container != null) {
            EObject parentContainer = container.eContainer();
            if (parentContainer instanceof DNodeContainer) {
                return new DNodeContainerExperimentalQuery((DNodeContainer) parentContainer).isVerticalStackContainer();
            }
        }
        return false;
    }

    /**
     * Get the label border style of the current {@link DDiagramElementContainer} if it exists.
     * 
     * @return an {@link Option} with the found label border style if it exists.
     */
    public Optional<LabelBorderStyleDescription> getLabelBorderStyle() {
        if (container.getStyle() instanceof FlatContainerStyle) {
            StyleDescription description = container.getStyle().getDescription();
            if (description instanceof FlatContainerStyleDescription) {
                FlatContainerStyleDescription fcsd = (FlatContainerStyleDescription) description;
                return Optional.ofNullable(fcsd.getLabelBorderStyle());
            }
        }
        return Optional.empty();
    }
}
