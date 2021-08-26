/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.description.ContainerMapping;

/**
 * A class aggregating all the queries (read-only!) having a ContainerMapping as
 * a starting point.
 * 
 * @author mporhel
 * 
 */
public class ContainerMappingQuery {

    private ContainerMapping mapping;

    /**
     * Create a new query.
     * 
     * @param mapping
     *            the element to query.
     */
    public ContainerMappingQuery(ContainerMapping mapping) {
        this.mapping = mapping;
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a list container,
     * according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link ContainerMapping} has a list
     *         children presentation.
     */
    public boolean isListContainer() {
        return ContainerLayout.LIST == mapping.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a free form
     * container, according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link ContainerMapping} has a free form
     *         children presentation.
     */
    public boolean isFreeFormContainer() {
        return ContainerLayout.FREE_FORM == mapping.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a vertical stack
     * container, according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link ContainerMapping} has a vertical
     *         stack children presentation.
     */
    public boolean isVerticalStackContainer() {
        return ContainerLayout.VERTICAL_STACK == mapping.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a horizontal stack
     * container, according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link ContainerMapping} has a
     *         horizontal stack children presentation.
     */
    public boolean isHorizontalStackContainer() {
        return ContainerLayout.HORIZONTAL_STACK == mapping.getChildrenPresentation();
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a region container,
     * according to the {@link ContainerLayout} specified in the VSM.
     * 
     * @return <code>true</code> if the {@link ContainerMapping} is a region
     *         container.
     */
    public boolean isRegionContainer() {
        return isVerticalStackContainer() || isHorizontalStackContainer();
    }

    /**
     * Tests whether or not the {@link ContainerMapping} is a region (e.g. child
     * of a region container, see
     * {@link ContainerMappingExperimentalQuery#isRegionContainer()}).
     * 
     * @return <code>true</code> if the {@link ContainerMapping} is a region.
     */
    public boolean isRegion() {
        if (mapping != null && mapping.eContainer() instanceof ContainerMapping) {
            return new ContainerMappingQuery((ContainerMapping) mapping.eContainer()).isRegionContainer();
        }
        return false;
    }

}
