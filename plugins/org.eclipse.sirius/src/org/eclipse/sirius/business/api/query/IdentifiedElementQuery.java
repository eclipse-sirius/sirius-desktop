/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * A class aggregating all the queries (read-only!) having a IdentifiedElement
 * as a starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class IdentifiedElementQuery {
    private IdentifiedElement element;

    /**
     * Create a new query.
     * 
     * @param element
     *            the element to query.
     */
    public IdentifiedElementQuery(IdentifiedElement element) {
        this.element = element;
    }

    /**
     * Get the label of the element. If the label is empty, the id (name) is
     * return instead.
     * 
     * @return The label of the <code>representationDescription</code>.
     */
    public String getLabel() {
        String label = this.element.getLabel();
        if (label == null || label.trim().length() == 0) {
            label = this.element.getName();
        }
        return label;
    }
}
