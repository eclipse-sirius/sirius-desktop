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
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.sirius.ui.tools.internal.views.common.action.DeleteRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * An handler which redirect to the appropriate delete action depending on the
 * selection.
 * 
 * @author mchauvin
 */
public class DeleteActionHandler extends Action {

    private ISelectionProvider selectionProvider;

    /**
     * Create a new instance.
     * 
     * @param selectionProvider
     *            the selection provider
     */
    public DeleteActionHandler(ISelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
        this.selectionProvider.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {

                setEnabled(!getRepresentationDescriptors().isEmpty());
            }

        });
    }

    private Collection<DRepresentationDescriptor> getRepresentationDescriptors() {
        ISelection selection = selectionProvider.getSelection();
        if (selection instanceof IStructuredSelection) {
            Collection<?> selections = ((IStructuredSelection) selection).toList();
            if (selections != null && !selections.isEmpty()) {
                Collection<DRepresentationDescriptor> selectedRepDescriptors = Sets.newLinkedHashSet();
                Iterables.addAll(selectedRepDescriptors, Iterables.filter(selections, DRepresentationDescriptor.class));
                Iterables.addAll(selectedRepDescriptors, Iterables.transform(Iterables.filter(selections, RepresentationItemImpl.class), RepresentationItemImpl.REPRESENTATION_ITEM_TO_REPRESENTATION));
                return selectedRepDescriptors;
            }
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Action deleteAction = new DeleteRepresentationAction(getRepresentationDescriptors());
        deleteAction.run();
    }

}
