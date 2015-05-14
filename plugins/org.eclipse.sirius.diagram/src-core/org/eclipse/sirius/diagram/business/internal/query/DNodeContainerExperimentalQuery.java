/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.query;

import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DNodeContainer;

/**
 * Queries for DNodeContainers.
 * 
 * @author mporhel
 */
public class DNodeContainerExperimentalQuery {
    private final DNodeContainer container;

    /**
     * Constructor.
     * 
     * @param container
     *            the DNodeContainer to query.
     */
    public DNodeContainerExperimentalQuery(DNodeContainer container) {
        this.container = container;
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a free form container,
     * according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link DNodeContainer} has a free form
     *         children presentation.
     */
    public boolean isFreeFormContainer() {
        return ContainerLayout.FREE_FORM == container.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a vertical stack
     * container, according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link DNodeContainer} has a vertical
     *         stack children presentation.
     */
    public boolean isVerticalStackContainer() {
        return ContainerLayout.VERTICAL_STACK == container.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a horizontal stack
     * container, according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link DNodeContainer} has a horizontal
     *         stack children presentation.
     */
    public boolean isHorizontaltackContainer() {
        return ContainerLayout.HORIZONTAL_STACK == container.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link DNodeContainer} is a region container,
     * according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link DNodeContainer} is a region
     *         container.
     */
    public boolean isRegionContainer() {
        return isVerticalStackContainer() || isHorizontaltackContainer();
    }
}
