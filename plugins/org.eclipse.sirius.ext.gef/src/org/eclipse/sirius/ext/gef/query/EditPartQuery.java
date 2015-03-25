/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gef.query;

import java.util.List;
import java.util.Set;

import org.eclipse.gef.EditPart;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

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
        this.part = Preconditions.checkNotNull(part);
    }

    /**
     * Returns a list including all of the children of the edit part passed in.
     * 
     * @param includeSelf
     *            true if the edit part must be include in result, false
     *            otherwise
     * @return list of children
     */
    public Set<EditPart> getAllChildren(boolean includeSelf) {
        return getAllChildren(includeSelf, null);
    }

    /**
     * Returns a list including all of the children of the edit part passed in.
     * 
     * @param includeSelf
     *            true if the edit part must be include in result, false
     *            otherwise
     * @param includedKind
     *            List of expected editPart classes
     * @return list of children
     */
    public Set<EditPart> getAllChildren(boolean includeSelf, List<Class<?>> includedKind) {
        Set<EditPart> result = Sets.newHashSet();
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
        Set<EditPart> result = Sets.newHashSet();
        for (EditPart child : Iterables.filter(editPart.getChildren(), EditPart.class)) {
            if (includedKind == null || isAssignable(child.getClass(), includedKind)) {
                result.add(child);
            }
            result.addAll(getAllChildren(child, includedKind));
        }
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
