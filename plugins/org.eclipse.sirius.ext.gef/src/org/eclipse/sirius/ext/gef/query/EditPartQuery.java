/*******************************************************************************
 * Copyright (c) 2015, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.gef.query;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.gef.EditPart;

/**
 * Queries on GEF edit parts.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EditPartQuery {
    private final EditPart part;

    /**
     * Constructor.
     * 
     * @param part
     *            the graphical edit part to query.
     */
    public EditPartQuery(EditPart part) {
        this.part = Objects.requireNonNull(part);
    }

    /**
     * Returns a list including all of the children of the edit part passed in.
     * 
     * @param includeSelf
     *            true if the edit part must be include in result, false otherwise
     * @return list of children
     */
    public Set<EditPart> getAllChildren(boolean includeSelf) {
        return getAllChildren(includeSelf, null);
    }

    /**
     * Returns a list including all of the children of the edit part passed in.
     * 
     * @param includeSelf
     *            true if the edit part must be include in result, false otherwise
     * @param includedKind
     *            List of expected editPart classes
     * @return list of children
     */
    public Set<EditPart> getAllChildren(boolean includeSelf, List<Class<?>> includedKind) {
        Set<EditPart> result = new HashSet<>();
        if (includeSelf) {
            if (includedKind == null || isAssignable(part.getClass(), includedKind)) {
                result.add(part);
            }
        }
        result.addAll(getAllChildren(part, includedKind));
        return result;
    }

    /**
     * Returns a list including all of the children of the edit part passed in.
     */
    private Set<EditPart> getAllChildren(EditPart editPart, List<Class<?>> includedKind) {
        Set<EditPart> result = new HashSet<>();
        Stream<EditPart> childrenParts = editPart.getChildren().stream().filter(EditPart.class::isInstance).map(EditPart.class::cast);
        childrenParts.forEachOrdered(child -> {
            if (includedKind == null || isAssignable(child.getClass(), includedKind)) {
                result.add(child);
            }
            result.addAll(getAllChildren(child, includedKind));
        });
        return result;
    }

    private boolean isAssignable(Class<?> aClass, List<Class<?>> assignementTypes) {
        for (Class<?> class1 : assignementTypes) {
            if (class1.isAssignableFrom(aClass)) {
                return true;
            }
        }
        return false;
    }
}
