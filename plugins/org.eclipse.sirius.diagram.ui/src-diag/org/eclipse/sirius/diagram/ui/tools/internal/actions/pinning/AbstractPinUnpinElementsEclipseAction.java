/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Abstract class to handle pin/unpin actions.
 * 
 * @author dlecan
 */
public abstract class AbstractPinUnpinElementsEclipseAction extends Action implements IObjectActionDelegate, Disposable {

    private static final Class<?>[] EXCEPTIONS = new Class<?>[] { IDiagramEdgeEditPart.class };

    private ISelection sel;

    /**
     * Constructor.
     * 
     * @param text
     *            Text.
     * @param actionId
     *            Action id (from
     *            {@link org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds}
     *            .
     * @param toolTipText
     *            Tooltip text.
     * @param iconPath
     *            Icon path (from
     *            {@link org.eclipse.sirius.diagram.DiagramImagesPath}.
     */
    public AbstractPinUnpinElementsEclipseAction(final String text, final String actionId, final String toolTipText, final String iconPath) {
        super(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(iconPath));
        setId(actionId);
        setToolTipText(toolTipText);
    }

    /**
     * Get current selected diagram elements.
     * 
     * @param selection
     *            Selection
     * @return Selected elements.
     */
    private Collection<DDiagramElement> getSelectedDiagramElements(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            final UnmodifiableIterator<IGraphicalEditPart> iter = Iterators.filter(((IStructuredSelection) selection).iterator(), IGraphicalEditPart.class);
            final Collection<DDiagramElement> elements = new ArrayList<>();
            while (iter.hasNext()) {
                final IGraphicalEditPart part = iter.next();

                if (keepElement(part)) {
                    final EObject semanticElement = part.resolveSemanticElement();
                    if (semanticElement instanceof DDiagramElement) {
                        elements.add((DDiagramElement) semanticElement);
                    }
                }
            }
            return elements;
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // Nothing.
    }

    /**
     * {@inheritDoc}
     */
    public void run(final IAction action) {
        final Collection<DDiagramElement> elements = getSelectedDiagramElements(sel);
        if (!elements.isEmpty()) {
            final Command cmd = createCommand(elements);
            TransactionUtil.getEditingDomain(elements.iterator().next()).getCommandStack().execute(cmd);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IAction action, final ISelection selection) {
        sel = selection;
    }

    /**
     * Create the command.
     * 
     * @param elements
     *            Elements to send to the command.
     * @return Built command.
     */
    protected abstract Command createCommand(Collection<DDiagramElement> elements);

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.sel = getCurrentSelection();
        run(this);
    }

    /**
     * Get the current selection.
     * 
     * @return The current selection.
     */
    private ISelection getCurrentSelection() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page != null) {
            return page.getSelection();
        }
        return new StructuredSelection();
    }

    /**
     * Internal isEnabled.
     * 
     * @param elements
     *            Elements to use to check is this action has to be enabled.
     * @return <code>true</code> if this action is enabled. <code>false</code>
     *         otherwise.
     */
    protected abstract boolean doIsEnabled(Collection<DDiagramElement> elements);

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEnabled() {
        Collection<DDiagramElement> selectedDiagramElements = getSelectedDiagramElements(getCurrentSelection());
        if (!selectedDiagramElements.isEmpty()) {
            Predicate<DDiagramElement> pred = new Predicate<DDiagramElement>() {
                @Override
                public boolean apply(DDiagramElement input) {
                    return PinHelper.allowsPinUnpin(input);
                }
            };
            if (Iterables.all(selectedDiagramElements, pred)) {
                return doIsEnabled(selectedDiagramElements);
            }
        }
        return false;
    }

    private boolean keepElement(final IGraphicalEditPart editPart) {
        boolean result = true;

        for (int i = 0; i < EXCEPTIONS.length && result; i++) {
            result = !EXCEPTIONS[i].isAssignableFrom(editPart.getClass());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        sel = null;
    }
}
