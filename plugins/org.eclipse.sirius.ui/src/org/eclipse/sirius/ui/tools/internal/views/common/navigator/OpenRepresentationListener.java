/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationsAction;

/**
 * A double click listener which opens an editor if the clicked element is a representation.
 * @author mchauvin
 */
public class OpenRepresentationListener implements IDoubleClickListener {
    
    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(final DoubleClickEvent event) {
        if (event != null && event.getSelection() instanceof IStructuredSelection) {
            List<?> selection = ((IStructuredSelection) event.getSelection()).toList();
            Iterable<DRepresentation> representationToOpen = getRepresentationsToOpen(selection);
            if (!Iterables.isEmpty(representationToOpen)) {
                new OpenRepresentationsAction(Sets.newLinkedHashSet(representationToOpen)).run();
            }
        }
    }
   
    private Iterable<DRepresentation> getRepresentationsToOpen(List<?> selection) {
       
        final Set<DRepresentation> representations = Sets.newLinkedHashSet();
        for (final Object obj : selection) {
            if (obj instanceof DRepresentation)
                representations.add((DRepresentation) obj);
            else {
                DRepresentation adapted = adaptToDRepresentation(obj);
                if (adapted != null)
                    representations.add((DRepresentation) adapted);
            }    
        }
        return representations;
    }

    private DRepresentation  adaptToDRepresentation(Object input) {
        if (input instanceof IAdaptable) {
            Object adapter = ((IAdaptable) input).getAdapter(EObject.class);
            if (adapter instanceof DRepresentation)
                return (DRepresentation) adapter;
        }
        return null;
    }    
}
