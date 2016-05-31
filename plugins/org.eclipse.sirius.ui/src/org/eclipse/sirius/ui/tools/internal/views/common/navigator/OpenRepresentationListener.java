/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationsAction;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A double click listener which opens an editor if the clicked element is a
 * representation.
 * 
 * @author mchauvin
 */
public class OpenRepresentationListener implements IDoubleClickListener {

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        if (event != null && event.getSelection() instanceof IStructuredSelection) {
            List<?> selection = ((IStructuredSelection) event.getSelection()).toList();
            Iterable<DRepresentationDescriptor> repDescriptorToOpen = getRepresentationDescriptorsToOpen(selection);
            if (!Iterables.isEmpty(repDescriptorToOpen)) {
                new OpenRepresentationsAction(Sets.newLinkedHashSet(repDescriptorToOpen)).run();
            }
        }
    }

    private Iterable<DRepresentationDescriptor> getRepresentationDescriptorsToOpen(List<?> selection) {

        final Set<DRepresentationDescriptor> repDescriptors = Sets.newLinkedHashSet();
        for (final Object obj : selection) {
            if (obj instanceof DRepresentationDescriptor)
                repDescriptors.add((DRepresentationDescriptor) obj);
            else {
                DRepresentationDescriptor adapted = adaptToDRepresentationDescriptor(obj);
                if (adapted != null)
                    repDescriptors.add(adapted);
            }
        }
        return repDescriptors;
    }

    private DRepresentationDescriptor adaptToDRepresentationDescriptor(Object input) {
        if (input instanceof IAdaptable) {
            Object adapter = ((IAdaptable) input).getAdapter(EObject.class);
            if (adapter instanceof DRepresentationDescriptor)
                return (DRepresentationDescriptor) adapter;
        }
        return null;
    }
}
