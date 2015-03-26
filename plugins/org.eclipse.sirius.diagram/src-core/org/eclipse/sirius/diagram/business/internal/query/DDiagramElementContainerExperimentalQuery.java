/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;

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
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child
     * of a region container, see
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
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child
     * of an horizontal stack region container, see
     * {@link DNodeContainerExperimentalQuery#isHorizontaltackContainer()}).
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
     * Tests whether or not the {@link DNodeContainer} is a region (e.g. child
     * of a vertical stack region container, see
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
}
