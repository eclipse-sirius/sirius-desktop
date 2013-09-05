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

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * A runnable that update the treeViewer of the DesignerSessionView.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DesignerSessionViewUpdateRunnable implements Runnable {
    private TreeViewer modelViewer;

    /**
     * The default constructor.
     * 
     * @param modelViewer
     *            The treeViewer of the DesignerSessionView
     */
    public DesignerSessionViewUpdateRunnable(TreeViewer modelViewer) {
        this.modelViewer = modelViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        /*
         * we should check that the widget is not disposed before trying to
         * update it
         */
        modelViewer.cancelEditing();
        if (!modelViewer.getTree().isDisposed()) {
            Collection<Session> newInput = SessionManager.INSTANCE.getSessions();
            // Check if the inputs are differents
            boolean sameInput = areEquals(newInput, modelViewer.getInput());
            if ((sameInput && !newInput.isEmpty()) || !(sameInput && newInput.isEmpty())) {
                modelViewer.getTree().setRedraw(false);
                if (newInput.isEmpty()) {
                    // Avoid memory leak. Indeed, we know that there is
                    // no input (probably caused by a session closed)
                    // so there is no reason to keep the actual
                    // selection (used later in refresh and setInput methods
                    // above).
                    modelViewer.setSelection(StructuredSelection.EMPTY);
                }
                final int scrollbarVSelect = modelViewer.getTree().getVerticalBar().getSelection();
                final int scrollbarHSelect = modelViewer.getTree().getHorizontalBar().getSelection();

                final Object[] visibleExpandedElements = modelViewer.getVisibleExpandedElements();
                if (sameInput) {
                    // Just refresh the tree to improve performance
                    modelViewer.refresh(true);
                } else {
                    // Use a copy of sessions list to avoid the indirect
                    // input modification.
                    modelViewer.setInput(Lists.newArrayList(newInput));
                }
                if (!newInput.isEmpty()) {
                    // Avoid memory leak. Indeed, we know that there is
                    // no input (probably caused by a session closed)
                    // so there is no reason to try to expand the
                    // previous visible element.
                    modelViewer.setExpandedElements(visibleExpandedElements);

                    modelViewer.getTree().getVerticalBar().setSelection(scrollbarVSelect);
                    modelViewer.getTree().getHorizontalBar().setSelection(scrollbarHSelect);
                }

                modelViewer.getTree().setRedraw(true);
                modelViewer.getTree().redraw();
            }
        }
    }

    /**
     * Check if the newInput has the same number of Sessions that the oldInput
     * and that is the same Session.
     * 
     * @param newInput
     *            A list of Session
     * @param odlInput
     *            The input to compare with
     * @return true if the inputs are equals
     */
    private boolean areEquals(Collection<Session> newInput, Object oldInput) {
        boolean result = false;
        if (oldInput instanceof Collection<?>) {
            Collection<?> oldInputCollection = (Collection<?>) oldInput;
            if (newInput.isEmpty() && oldInputCollection.isEmpty()) {
                result = true;
            } else if (newInput.size() == oldInputCollection.size()) {
                result = containSameSessions(newInput, oldInputCollection);
            }
        }
        return result;
    }

    /**
     * Check that the elements are the same that the sessions.
     * 
     * @param sessions
     *            A list of sessions
     * @param elements
     *            A list of elements
     * @return true if all the elements are Session and is contains in sessions.
     */
    private boolean containSameSessions(Collection<Session> sessions, Collection<?> elements) {
        for (Object object : elements) {
            if (!(object instanceof Session && sessions.contains(object))) {
                return false;
            }
        }
        return true;
    }

}
