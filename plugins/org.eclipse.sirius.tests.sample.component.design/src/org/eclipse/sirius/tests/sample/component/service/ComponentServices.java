/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.component.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.sirius.tests.sample.component.Component;

/**
 * Component services.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class ComponentServices {

    /**
     * Get all component children that are not payload.
     * 
     * @param component
     * @return all component children
     */
    public Collection<Component> getAllChildren(Component component) {
        Collection<Component> allChildren = new ArrayList<Component>();
        appendChildren(component, allChildren);
        return allChildren;
    }

    /**
     * Get component in column that are related to component in the line.
     * 
     * @param line
     *            component in the selected line
     * @return related component in columns
     */
    public Collection<Component> getIntersectionColumms(Component line) {
        Collection<Component> validColumns = new HashSet<Component>();
        for (Component ref : line.getReferences()) {
            if (!ref.isPayload()) {
                validColumns.add(ref);
            }
        }
        
        for (Component ref : line.getReferences2()) {
            if (!ref.isPayload()) {
                validColumns.add(ref);
            }
        }

        for (Component opposite : line.getOpposites()) {
            if (!opposite.isPayload()) {
                validColumns.add(opposite);
            }
        }

        Component ref = line.getReference();
        if (ref != null && !ref.isPayload()) {
            validColumns.add(ref);
        }

        return validColumns;
    }

    /**
     * Get intersection label.
     * 
     * @param line
     *            component on the line
     * @param column
     *            column on the line
     * @return intersection label
     */
    public String getIntersectionLabel(Component line, Component column) {
        StringBuilder result = new StringBuilder();
        boolean empty = true;

        if (line.getReferences().contains(column)) {
            result.append("R");
            empty = false;
        }

        if (line.getReferences2().contains(column)) {
        	if (empty) {
                result.append("R2");
            } else {
                result.append(", R2");
            }
            empty = false;
        }
        
        if (line.getOpposites().contains(column)) {
            if (empty) {
                result.append("O");
            } else {
                result.append(", O");
            }
            empty = false;
        }

        if (line.getReference() == column) {
            if (empty) {
                result.append("r");
            } else {
                result.append(", r");
            }
        }

        return result.toString();
    }

    private void appendChildren(Component component, Collection<Component> allChildren) {
        for (Component child : component.getChildren()) {
            if (!child.isPayload()) {
                allChildren.add(child);
                appendChildren(child, allChildren);
            }
        }
    }
}
