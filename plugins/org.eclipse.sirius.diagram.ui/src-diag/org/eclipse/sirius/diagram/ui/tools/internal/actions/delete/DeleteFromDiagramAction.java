/*******************************************************************************
 * Copyright (c) 2007, 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.delete;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.internal.navigation.MappingDefinitionFinder;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Delete a {@link org.eclipse.sirius.diagram.DDiagramElement}.
 * 
 * @author cbrun
 * 
 */
public class DeleteFromDiagramAction extends RetargetAction implements IObjectActionDelegate {

    /** The selection. */
    private ISelection selection;

    /**
     * Constructor.
     */
    public DeleteFromDiagramAction() {
        super("", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param toolTipText
     *            the tool tip text, or <code>null</code> if none
     * @param id
     *            the action id
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     */
    public DeleteFromDiagramAction(final String text, final String toolTipText, final String id, final ImageDescriptor image) {
        super(id, text);
        setImageDescriptor(image);
        setToolTipText(toolTipText);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // empty.
    }

    /**
     * Execute the action. Delete all selected view point element. {@inheritDoc}
     * All the commands for each editPart is group in one to can Undo with one
     * Ctrl+Z (and not one Ctrl+Z by editPart).
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(final IAction action) {
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            final Collection<?> minimizedSelection = Lists.newLinkedList(Arrays.asList(structuredSelection.toArray()));
            final Iterator<?> iterSelection = minimizedSelection.iterator();

            DDiagramEditor diagramEditor = null;
            CompositeTransactionalCommand deleteCC = null;

            while (iterSelection.hasNext()) {
                /* Get the next part */
                final Object nextSelected = iterSelection.next();
                if (nextSelected instanceof EditPart) {
                    final EditPart editPart = (EditPart) nextSelected;
                    // Get the editor from the first editPart
                    if (diagramEditor == null) {
                        diagramEditor = (DDiagramEditor) ((EditPart) nextSelected).getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                        deleteCC = new CompositeTransactionalCommand(getEditingDomain(diagramEditor), DiagramUIActionsMessages.DeleteFromDiagram_ActionLabelText);
                    }
                    /* Send the request to the edit part */
                    Request deleteRequest = null;
                    if (editPart instanceof NoteEditPart || editPart instanceof TextEditPart || editPart instanceof NoteAttachmentEditPart) {
                        deleteRequest = new GroupRequest(org.eclipse.gef.RequestConstants.REQ_DELETE);
                    } else {
                        deleteRequest = new GroupRequest(RequestConstants.REQ_DELETE_FROM_DIAGRAM);
                    }

                    final Command curCommand = editPart.getCommand(deleteRequest);
                    if (curCommand != null) {
                        deleteCC.compose(new CommandProxy(curCommand));
                    }
                }
            }

            if (!deleteCC.isEmpty()) {
                ((IDiagramWorkbenchPart) diagramEditor).getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(deleteCC));
            }
        }
    }

    private TransactionalEditingDomain getEditingDomain(final DDiagramEditor editor) {
        return (TransactionalEditingDomain) editor.getAdapter(EditingDomain.class);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#runWithEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void runWithEvent(final Event event) {
        super.runWithEvent(event);
        run();
    }

    /**
     * Empty. {@inheritDoc} Used from button.
     * 
     * @see org.eclipse.jface.action#run(org.eclipse.jface.action)
     */
    @Override
    public void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        run(this);
    }

    /**
     * Set the selection. {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IAction action, final ISelection s) {
        this.selection = s;
        if (action != null) {
            action.setEnabled(shouldBeEnabled(s));
        }
    }

    /**
     * Check if the action should be enabled for this selection.
     * 
     * @param s
     *            the current selection
     * @return true if the action should be enabled, false otherwise.
     */
    public boolean shouldBeEnabled(ISelection s) {
        final Iterable<? extends IGraphicalEditPart> parts = filterSelection(s, IGraphicalEditPart.class);
        return shouldBeEnabledForEditParts(parts);
    }

    /**
     * Check if the action should be enabled for this edit parts :
     * <UL>
     * <LI>First check that all edit parts has his edit mode enabled</LI>
     * <LI>And then do some checks on target of edit parts.</LI>
     * </UL>
     * 
     * @param selectedParts
     *            the selected objects
     * @return true if the action should be enabled, false otherwise.
     */
    public static boolean shouldBeEnabledForEditParts(Iterable<? extends IGraphicalEditPart> selectedParts) {
        Predicate<IGraphicalEditPart> isEditModeEnabledPredicate = new Predicate<IGraphicalEditPart>() {
            public boolean apply(IGraphicalEditPart input) {
                return input.isEditModeEnabled();
            }
        };

        if (Iterables.all(selectedParts, isEditModeEnabledPredicate) && !Iterables.all(selectedParts, Predicates.instanceOf(IDiagramNameEditPart.class))) {
            return shouldBeEnabled(getSelectedEObject(selectedParts));
        }
        return false;
    }

    /**
     * Get the selected objects from the corresponding selected edit parts.
     * 
     * @param selectedParts
     *            the current selected edit parts, or <code>null</code> if there
     *            is no selection.
     * @return the selected objects
     */
    public static Iterable<EObject> getSelectedEObject(Iterable<? extends IGraphicalEditPart> selectedParts) {
        return Iterables.transform(selectedParts, new com.google.common.base.Function<IGraphicalEditPart, EObject>() {
            public EObject apply(final IGraphicalEditPart from) {
                return from.resolveSemanticElement();
            }
        });
    }

    /**
     * Check if the action should be enabled.
     * 
     * @param selectedEObject
     *            the selected objects
     * @return true if the action should be enabled, false otherwise.
     */
    public static boolean shouldBeEnabled(final Iterable<EObject> selectedEObject) {
        boolean result = true;
        final MappingDefinitionFinder defFinder = new MappingDefinitionFinder();
        for (final EObject eObject : selectedEObject) {
            if (eObject != null) {
                final EObject definition = defFinder.getDefinition(eObject);
                if (definition instanceof DiagramDescription) {
                    result = false;
                    break;
                }
                if (definition instanceof DiagramElementMapping && eObject instanceof DDiagramElement) {
                    if (new DiagramElementMappingQuery((DiagramElementMapping) definition).isSynchronizedAndCreateElement((DDiagramElement) eObject)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private Iterable<? extends IGraphicalEditPart> filterSelection(final ISelection s, final Class<? extends IGraphicalEditPart> class1) {
        if (s instanceof StructuredSelection) {
            return Iterables.filter(((StructuredSelection) s).toList(), class1);
        }
        return Lists.newArrayList();
    }

    @Override
    public void dispose() {
        selection = null;
        super.dispose();
    }

}
