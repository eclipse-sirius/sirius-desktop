/******************************************************************************
 * Copyright (c) 2002, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

/**
 * This class encapsulates the functionality required for entering and
 * retrieving from the 'element to editparts' map required by our viewers.
 * 
 * @author chmahone
 */
public class SemanticElementToEditPartsMap {

    /**
     * A registry of editparts, mapping an element's id string to a list of
     * <code>EditParts</code>.
     */
    private Map<EObject, List<EditPart>> map = new WeakHashMap<EObject, List<EditPart>>();

    /**
     * .
     * 
     * @param <T>
     *            .
     * @param element
     *            .
     * @param editPartClass
     *            .
     * @return .
     */
    public <T extends EditPart> List<T> findEditPartsForElement(final EObject element, final Class<T> editPartClass) {

        final List<EditPart> allEPs = map.get(element);
        if (allEPs == null) {
            return Collections.emptyList();
        }
        final List<T> specificEPs = new ArrayList<>();
        for (final EditPart ep : allEPs) {
            if (editPartClass.isInstance(ep)) {
                specificEPs.add((T) ep);
            }
        }
        return specificEPs;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#registerEditPartForSemanticElement(EObject,
     *      EditPart)
     */
    public void registerEditPartForElement(final EObject element, final EditPart ep) {

        if (element == null || ep == null) {
            return;
        }

        final List<EditPart> epList = map.get(element);
        if (epList != null) {
            if (!epList.contains(ep)) {
                epList.add(ep);
            }
        } else {
            final List<EditPart> newList = new ArrayList<EditPart>(1);
            newList.add(ep);
            map.put(element, newList);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#unregisterEditPartForSemanticElement(EObject,
     *      EditPart)
     */
    public void unregisterEditPartForElement(final EObject element, final EditPart ep) {

        if (element == null || ep == null) {
            return;
        }

        final List<EditPart> epList = map.get(element);
        if (epList != null && epList.contains(ep)) {
            epList.remove(ep);
            if (epList.isEmpty()) {
                map.remove(element);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#unregisterEditPart(org.eclipse.gef.EditPart)
     */
    public void unregisterEditPart(final EditPart ep) {
        Collection<EObject> keysToRemove = new ArrayList<>();
        for (Entry<EObject, List<EditPart>> entry : map.entrySet()) {
            List<EditPart> epList = entry.getValue();
            if (epList != null) {
                boolean remove = epList.remove(ep);
                if (remove && epList.isEmpty()) {
                    // Dangling key.
                    keysToRemove.add(entry.getKey());
                }
            }
        }

        // Clean the map, remove dangling keys.
        if (!keysToRemove.isEmpty()) {
            for (EObject danglingKey : keysToRemove) {
                map.remove(danglingKey);
            }
        }

    }

}
