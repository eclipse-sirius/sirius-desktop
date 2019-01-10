/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Basic behavior to reveal elements in a diagram.
 * 
 * @author dlecan
 * @param <T>
 *            Type of element to reveal.
 */
public abstract class AbstractRevealElementsAction<T> extends Action implements IObjectActionDelegate {

    /** The selection. */
    protected ISelection selection;

    /**
     * Constructor.
     */
    public AbstractRevealElementsAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public AbstractRevealElementsAction(final String text) {
        this(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REVEAL_ELEMENTS_IMG));
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     * @param image
     *            Image
     */
    public AbstractRevealElementsAction(final String text, final ImageDescriptor image) {
        super(text, image);
        setId(text);
    }

    /**
     * Execute the action. Reveal all selected view point element. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public final void run(final IAction action) {
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            final Collection<?> minimizedSelection = new LinkedList<Object>(Arrays.asList(structuredSelection.toArray()));

            final Iterator<?> iterSelection = minimizedSelection.iterator();
            boolean mustContinue = true;
            while (iterSelection.hasNext() && mustContinue) {
                final Object nextSelected = iterSelection.next();

                if (getElementType().isAssignableFrom(nextSelected.getClass())) {
                    mustContinue = doRun(getElementType().cast(nextSelected));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public final void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // Nothing.
    }

    /**
     * Get the element type.
     * 
     * @return The element type.
     */
    protected abstract Class<T> getElementType();

    /**
     * Real run operation.
     * 
     * @param element
     *            Element on which to run action.
     * @return <code>true</code> if next selected element must be reveal too.
     *         <code>false</code> otherwise.
     */
    protected abstract boolean doRun(T element);

    /**
     * Empty. {@inheritDoc} Used from button.
     * 
     * @see org.eclipse.jface.action#run(org.eclipse.jface.action)
     */
    @Override
    public final void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        run(this);
    }

    /**
     * Set the selection. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(final IAction action, final ISelection s) {
        this.selection = s;
    }

}
