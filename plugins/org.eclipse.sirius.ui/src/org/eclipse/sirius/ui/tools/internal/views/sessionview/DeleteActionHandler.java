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
package org.eclipse.sirius.ui.tools.internal.views.sessionview;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.sirius.ui.tools.internal.views.common.action.DeleteRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;

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

            public void selectionChanged(SelectionChangedEvent event) {

                setEnabled(!getRepresentations().isEmpty());
            }

        });
    }

    private Collection<DRepresentation> getRepresentations() {
        ISelection selection = selectionProvider.getSelection();
        if (selection instanceof IStructuredSelection) {
            Collection<?> selections = ((IStructuredSelection) selection).toList();
            if (selections != null && !selections.isEmpty()) {
                Collection<DRepresentation> selectedRepresentations = Sets.newLinkedHashSet();
                Iterables.addAll(selectedRepresentations, Iterables.filter(selections, DRepresentation.class));
                Iterables
                        .addAll(selectedRepresentations, Iterables.transform(Iterables.filter(selections, RepresentationItemImpl.class), RepresentationItemImpl.REPRESENTATION_ITEM_TO_REPRESENTATION));
                return selectedRepresentations;
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
        Action deleteAction = new DeleteRepresentationAction(getRepresentations());
        deleteAction.run();
    }

}
