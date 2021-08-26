/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Default implementation for {@link org.eclipse.sirius.tools.internal.command.builders.CommandBuilder}.
 * 
 * @author mchauvin
 */
public abstract class AbstractDiagramCommandBuilder extends org.eclipse.sirius.tools.internal.command.builders.AbstractCommandBuilder {

    /**
     * Check the precondition of the specified tool with the specified container and diagram.
     * 
     * @param diagram
     *            the diagram container
     * @param abstractToolDescription
     *            the {@link AbstractToolDescription}.
     * @return <code>true</code> if the predicate is <code>true</code>.
     */
    protected boolean checkPrecondition(final DDiagram diagram, final AbstractToolDescription abstractToolDescription) {
        return genericCheckPrecondition(diagram, abstractToolDescription);
    }

    /**
     * Check the precondition of the specified tool with the specified container.
     * 
     * @param diagramElement
     *            the diagram element container
     * @param abstractToolDescription
     *            the {@link AbstractToolDescription}.
     * @return <code>true</code> if the predicate is <code>true</code>.
     */
    protected boolean checkPrecondition(final DDiagramElement diagramElement, final AbstractToolDescription abstractToolDescription) {
        return genericCheckPrecondition(diagramElement, abstractToolDescription);
    }

    /**
     * Add a refresh task.
     * 
     * @param diagram
     *            the diagram
     * @param result
     *            the command result
     * @param abstractToolDescription
     *            the tool
     */
    protected void addRefreshTask(final DDiagram diagram, final DCommand result, final AbstractToolDescription abstractToolDescription) {
        if (abstractToolDescription != null && diagram instanceof DSemanticDiagram) {
            final EObject semanticElement = ((DSemanticDecorator) diagram).getTarget();
            final Session session = SessionManager.INSTANCE.getSession(semanticElement);
            if (semanticElement != null && session != null) {
                result.getTasks().add(new AbstractCommandTask() {
                    @Override
                    public String getLabel() {
                        return Messages.AbstractDiagramCommandBuilder_refreshTaskLabel;
                    }

                    @Override
                    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
                        if (abstractToolDescription.isForceRefresh()) {
                            // Set the RefreshEditorsListener in forceRefresh
                            // mode
                            session.getRefreshEditorsListener().setForceRefresh(true);
                        }
                        // Add the current representation to be refreshed :
                        // - It could be possible that no editor is opened on it
                        // - The tool should probably made a modification only
                        // in the
                        // aird model (and this not launches a refresh)
                        session.getRefreshEditorsListener().addRepresentationToForceRefresh(diagram);
                    }
                });
            }
        }
    }

    /**
     * Add a refresh task to the diagram which contains the diagram element given as parameter.
     * 
     * @param diagramElement
     *            the diagram element.
     * @param result
     *            the command.
     * @param abstractToolDescription
     *            the tool.
     */
    protected void addRefreshTask(final DDiagramElement diagramElement, final DCommand result, final AbstractToolDescription abstractToolDescription) {
        addRefreshTask(diagramElement.getParentDiagram(), result, abstractToolDescription);
    }

    /**
     * Add $diagram tothe interpreter. Use it after
     * {@link org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask}
     * 
     * @param command
     *            .
     * @param containerView
     *            .
     * @param interpreter
     *            .
     */
    protected void addDiagramVariable(final DCommand command, final EObject containerView, final IInterpreter interpreter) {
        final Option<DDiagram> diag = getDDiagram();
        if (diag.some()) {
            command.getTasks().add(new AbstractCommandTask() {

                @Override
                public String getLabel() {
                    return Messages.AbstractDiagramCommandBuilder_diagramVariableTaskLabel;
                }

                @Override
                public void execute() {
                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diag.get());
                }
            });
        }
    }

    /**
     * Indicates if the given element is contained in a {@link DDiagram} that is in Layouting mode.
     * 
     * @param element
     *            the {@link DDiagram} or any {@link DDiagramElement}
     * @return true if the given element is contained in a {@link DDiagram} that is in Layouting mode
     */
    public boolean isInLayoutingModeDiagram(EObject element) {
        // Step 1 : getting the DDiagram
        DDiagram ddiagram = null;

        if (element instanceof DDiagram) {
            ddiagram = (DDiagram) element;
        } else {
            if (element instanceof DDiagramElement) {
                ddiagram = ((DDiagramElement) element).getParentDiagram();
            }
        }

        // Step 2 : returning the isInLayoutingMode value
        if (ddiagram != null) {
            return ddiagram.isIsInLayoutingMode();
        }
        return false;
    }

    /**
     * Indicates if the given element is contained in a {@link DDiagram} that is in showing mode.
     * 
     * @param element
     *            the {@link DDiagram} or any {@link DDiagramElement}
     * @return true if the given element is contained in a {@link DDiagram} that is in showing mode
     */
    public boolean isInShowingModeDiagram(DRepresentationElement element) {
        DDiagram ddiagram = null;

        if (element instanceof DDiagram) {
            ddiagram = (DDiagram) element;
        } else {
            if (element instanceof DDiagramElement) {
                ddiagram = ((DDiagramElement) element).getParentDiagram();
            }
        }
        if (ddiagram != null) {
            return ddiagram.isIsInShowingMode();
        }
        return false;
    }

    /**
     * Check the precondition of the specified tool with the specified container.
     * 
     * @param container
     *            the container variable.
     * @param abstractToolDescription
     *            the {@link AbstractToolDescription}.
     * @return <code>true</code> if the predicate is <code>true</code>.
     */
    private boolean genericCheckPrecondition(final EObject container, final AbstractToolDescription abstractToolDescription) {
        boolean result = true;

        EObject semanticContainer = null;
        if (container instanceof DSemanticDecorator) {
            semanticContainer = ((DSemanticDecorator) container).getTarget();
        }

        if (abstractToolDescription.getPrecondition() != null && !StringUtil.isEmpty(abstractToolDescription.getPrecondition().trim())) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(container);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, container);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, semanticContainer);

            result = evaluatePrecondition(interpreter, semanticContainer, abstractToolDescription.getPrecondition());

            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        }
        return result;
    }

    /**
     * Return the current diagram.
     * 
     * @return the current DDiagram.
     */
    protected abstract Option<DDiagram> getDDiagram();
}
