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
package org.eclipse.sirius.diagram.ui.business.internal.query;

import org.eclipse.sirius.diagram.DDiagramElement;

import com.google.common.base.Predicate;

/**
 * Functions which take a {@link DDiagramElement} as input.
 * 
 * @author pcdavid
 */
public final class DDiagramElementFunctions {
    /**
     * A predicate to check that a diagram element is visible.
     * 
     * @author pcdavid
     */
    private enum IsVisiblePredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        public boolean apply(DDiagramElement input) {
            return input != null && input.isVisible();
        }
    }

    private DDiagramElementFunctions() {
        // Prevents instantiation
    }

    /**
     * Returns a predicate to check that a diagram element is visible.
     * 
     * @return a predicate to check that a diagram element is visible.
     */
    public static Predicate<DDiagramElement> isVisible() {
        return IsVisiblePredicate.INSTANCE;
    }
}
