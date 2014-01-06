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
package org.eclipse.sirius.diagram.edit.internal.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.query.NotifierQuery;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.part.DDiagramHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.listener.IChangeListener;
import org.eclipse.sirius.tools.api.command.listener.TriggerOperation;
import org.eclipse.ui.IEditorPart;

/**
 * A command factory that creates selection commands.
 * 
 * @author mporhel
 */
public final class SelectionCommandAppender {
    static TriggerOperation selectionOfCreatedElementsTriggerOperation;

    /**
     * Avoid instanciation.
     */
    private SelectionCommandAppender() {
    }

    /**
     * Append a selection command corresponding to the parameters. Used to
     * select the newly created elements.
     * 
     * @param command
     *            the creation command.
     * @param editPart
     *            the current edit part
     * @return a command on which a selection command has been added.
     */
    public static Command addSelectionCommand(final Command command, GraphicalEditPart editPart) {
        DDiagram currentDDiagram = DDiagramHelper.findParentDDiagram(editPart);
        if (currentDDiagram != null) {
            return decorateCommandWithSelectionOfCreatedElements(command, currentDDiagram);
        } else {
            return decorateCommandWithSelectionOfCreatedElements(command, editPart.getEditingDomain().getResourceSet());
        }
    }

    /**
     * This method use a specific listener to select the elements created during
     * the execution of the command :
     * <UL>
     * <LI>Add the activation of the specific listener before the command,</LI>
     * <LI>Add the deactivation of the specific listener and the execution of
     * trigger operation (that select the elements) after the command.</LI>
     * </UL>
     * 
     * @param command
     *            The command to decorate
     * @return The decorated command
     */
    private static Command decorateCommandWithSelectionOfCreatedElements(Command command, final Notifier notifier) {
        final Option<IChangeListener> optionalListener = (Option<IChangeListener>) new NotifierQuery(notifier).getAdapter(IChangeListener.class);
        CompoundCommand cc = new CompoundCommand(command.getLabel());
        if (optionalListener.some()) {
            optionalListener.get().setTriggerOperation(getSelectionOfCreatedElementsTriggerOperation());

            cc.add(new Command() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.gef.commands.Command#execute()
                 */
                @Override
                public void execute() {
                    optionalListener.get().activate();
                }
            });
        }
        cc.add(command);
        if (optionalListener.some()) {
            cc.add(new Command() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.gef.commands.Command#execute()
                 */
                @Override
                public void execute() {
                    optionalListener.get().deactivate();
                    // If the optionalListener is not longer activated
                    if (!optionalListener.get().isActivated()) {
                        optionalListener.get().launchTriggerOperation();
                    }
                }
            });
        }
        return cc;
    }

    /**
     * Return a trigger operation that select the created elements in the
     * current diagram.
     * 
     * @return a trigger operation that select the created elements in the
     *         current diagram.
     */
    private static TriggerOperation getSelectionOfCreatedElementsTriggerOperation() {
        if (selectionOfCreatedElementsTriggerOperation == null) {
            selectionOfCreatedElementsTriggerOperation = new TriggerOperation() {
                public void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
                    if (createdElements == null || createdElements.isEmpty()) {
                        return;
                    }
                    // Try to select the created elements
                    List<EditPart> editPartsToSelect = new ArrayList<EditPart>();
                    for (Iterator<Object> iterator = createdElements.iterator(); iterator.hasNext(); /* */) {
                        Object object = iterator.next();
                        if (object instanceof DDiagramElement) {
                            Option<EditPart> targetEditPart = getEditPart((DDiagramElement) object);
                            if (targetEditPart.some()) {
                                editPartsToSelect.add(targetEditPart.get());
                            }
                        }
                    }

                    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
                    if (!editPartsToSelect.isEmpty() && editor instanceof DiagramEditor) {
                        SiriusDiagramEditorUtil.selectWithoutRevealElementsInDiagram((DiagramEditor) editor, editPartsToSelect);
                    }
                }
            };
        }
        return selectionOfCreatedElementsTriggerOperation;
    }

    /**
     * Return an option with the editPart corresponding to the
     * <code>dDiagramElement</code> in the current diagram or an empty Option if
     * there is no corresponding editPart.
     * 
     * @param dDiagramElement
     *            The diagram element that is searched
     * @return The corresponding edit part.
     */
    protected static Option<EditPart> getEditPart(DDiagramElement dDiagramElement) {
        Option<EditPart> result = Options.newNone();
        View view = null;
        Collection<EObject> inverseReferences = new EObjectQuery(dDiagramElement).getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
        for (final EObject object : inverseReferences) {
            if (object instanceof View) {
                view = (View) object;
                break;
            }
        }
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        if (view != null && editor instanceof DiagramEditor) {
            final Map editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
            final EditPart targetEditPart = (EditPart) editPartRegistry.get(view);
            if (targetEditPart != null) {
                result = Options.newSome(targetEditPart);
            }
        }
        return result;
    }
}
