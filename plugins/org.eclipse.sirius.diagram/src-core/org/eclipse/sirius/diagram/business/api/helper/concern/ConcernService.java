/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper.concern;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;

/**
 * This class is used whenever we need to use concerns.
 * 
 * @author mporhel
 */
public final class ConcernService {

    /**
     * Avoid instantiation.
     */
    private ConcernService() {

    }

    /**
     * Defines the current concern of the specified diagram. Updates filters,
     * behaviors and rules of the diagram.
     * 
     * @param diagram
     *            the diagram.
     * @param newCurrentConcern
     *            the concern of the viewpoint.
     */
    public static void setCurrentConcern(final DDiagram diagram, final ConcernDescription newCurrentConcern) {
        diagram.getActivatedFilters().clear();
        diagram.getActivatedRules().clear();
        diagram.getActivateBehaviors().clear();
        if (newCurrentConcern != null) {
            /*
             * We want to activate the filters associated with the concern...
             */
            diagram.getActivatedFilters().addAll(newCurrentConcern.getFilters());

            /*
             * We want to activate the validation rules associated with the
             * concern...
             */
            diagram.getActivatedRules().addAll(newCurrentConcern.getRules());
            /*
             * We want to activate the behaviors associated with the concern...
             */
            diagram.getActivateBehaviors().addAll(newCurrentConcern.getBehaviors());
        }
        diagram.setCurrentConcern(newCurrentConcern);
    }

    /**
     * Reset the current concern of the specified diagram. Keep filters,
     * behaviors and rules of the diagram.
     * 
     * @param diagram
     *            the diagram.
     */
    public static void resetCurrentConcern(final DDiagram diagram) {
        diagram.setCurrentConcern(null);
    }

}
