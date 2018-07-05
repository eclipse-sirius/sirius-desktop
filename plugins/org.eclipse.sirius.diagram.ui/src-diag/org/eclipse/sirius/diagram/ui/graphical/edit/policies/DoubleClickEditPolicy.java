/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractGeneratedDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;

/**
 * An edit policy which launch an action after double-clicking an edit part.
 * 
 * @author smonnier
 * 
 */
public class DoubleClickEditPolicy extends OpenEditPolicy {

    @Override
    protected Command getOpenCommand(Request request) {
        Command result = null;
        EditPart editPart = this.getHost();
        Object model = editPart.getModel();
        if (model instanceof View) {
            EObject element = ((View) model).getElement();
            if (element instanceof DDiagramElement) {
                final DDiagramElement ddiagramElement = (DDiagramElement) element;
                DiagramElementMapping diagramElementMapping = ddiagramElement.getDiagramElementMapping();
                DDiagram parentDiagram = ddiagramElement.getParentDiagram();
                if (parentDiagram.isIsInShowingMode()) {
                    org.eclipse.emf.common.command.Command cmd = null;
                    final IDiagramCommandFactory emfCommandFactory = getCommandFactory(editPart);
                    EditPart targetEditPart = this.getTargetEditPart(request);
                    if (targetEditPart instanceof AbstractGeneratedDiagramNameEditPart && !(targetEditPart instanceof DNodeListElementEditPart)) {
                        // label case
                        DDiagramElementQuery query = new DDiagramElementQuery(ddiagramElement);
                        // CHECKSTYLE:OFF
                        if (query.canHideLabel()) {
                            if (query.isLabelHidden()) {
                                cmd = revealElement(ddiagramElement, emfCommandFactory, true);
                            } else {
                                cmd = hideElement(ddiagramElement, emfCommandFactory, true);
                            }
                        }
                        // CHECKSTYLE:ON
                    } else {
                        if (!ddiagramElement.isVisible()) {
                            cmd = revealElement(ddiagramElement, emfCommandFactory, false);
                        } else {
                            cmd = hideElement(ddiagramElement, emfCommandFactory, false);
                        }
                    }
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    result = new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
                } else if (diagramElementMapping.getDoubleClickDescription() != null) {
                    final IDiagramCommandFactory emfCommandFactory = getCommandFactory(editPart);
                    final org.eclipse.emf.common.command.Command cmd = emfCommandFactory.buildDoubleClickOnElementCommandFromTool(ddiagramElement, diagramElementMapping.getDoubleClickDescription());
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    result = new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
                }
            }
        }
        return result;
    }

    /**
     * Hides the given {@link DDiagramElement}.
     * 
     * @param ddiagramElement
     *            element to reveal.
     * @param emfCommandFactory
     *            factory for command creation.
     * @param hideLabel
     *            true if we are hiding a label. False for any other element.
     * @return the command doing the hiding.
     */
    private org.eclipse.emf.common.command.Command hideElement(final DDiagramElement ddiagramElement, final IDiagramCommandFactory emfCommandFactory, boolean hideLabel) {
        org.eclipse.emf.common.command.Command cmd;
        Set<EObject> elementSet = new HashSet<>();
        elementSet.add(ddiagramElement);
        if (hideLabel) {
            cmd = emfCommandFactory.buildHideLabelCommand(elementSet);
        } else {
            cmd = emfCommandFactory.buildHideCommand(elementSet);
        }
        return cmd;
    }

    /**
     * Reveals the given {@link DDiagramElement} and all its parents recursively. If the element in an edge, also reveal
     * the source and target and their parent recursively.
     * 
     * @param ddiagramElement
     *            element to reveal.
     * @param emfCommandFactory
     *            factory for command creation.
     * @param hideLabel
     *            true if we are hiding a label. False for any other element.
     * @return the command doing the revelation.
     */
    private org.eclipse.emf.common.command.Command revealElement(final DDiagramElement ddiagramElement, final IDiagramCommandFactory emfCommandFactory, boolean hideLabel) {
        org.eclipse.emf.common.command.Command cmd;
        Set<DDiagramElement> elementSet = new HashSet<>();
        elementSet.add(ddiagramElement);
        if (ddiagramElement instanceof DEdge) {
            // we show source and target node as well as the edge.
            DEdge edge = (DEdge) ddiagramElement;
            EdgeTarget sourceNode = edge.getSourceNode();
            EdgeTarget targetNode = edge.getTargetNode();
            if (sourceNode instanceof DDiagramElement && targetNode instanceof DDiagramElement) {
                elementSet.add((DDiagramElement) sourceNode);
                elementSet.add((DDiagramElement) targetNode);
                addAllParentRecursively(elementSet, (DDiagramElement) sourceNode);
                addAllParentRecursively(elementSet, (DDiagramElement) targetNode);
            }
        } else {
            addAllParentRecursively(elementSet, ddiagramElement);
        }
        if (hideLabel) {
            cmd = emfCommandFactory.buildRevealLabelCommand(ddiagramElement);
        } else {

            cmd = emfCommandFactory.buildRevealElementsCommand(elementSet);
        }
        return cmd;
    }

    /**
     * Add all parents recursively of the given {@link DDiagramElement} in the given set.
     * 
     * @param elementSet
     *            the set where to add parents.
     * @param ddiagramElement
     *            the element from which parents will be added.
     */
    private void addAllParentRecursively(Set<DDiagramElement> elementSet, DDiagramElement ddiagramElement) {
        EObject eContainer = ddiagramElement.eContainer();
        if (eContainer instanceof DDiagramElement) {
            elementSet.add((DDiagramElement) eContainer);
            addAllParentRecursively(elementSet, (DDiagramElement) eContainer);
        }
    }

    private IDiagramCommandFactory getCommandFactory(EditPart editPart) {
        final TransactionalEditingDomain transactionalEditingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        final DDiagramEditor diagramEditor = (DDiagramEditor) editPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        return emfCommandFactory;
    }

}
