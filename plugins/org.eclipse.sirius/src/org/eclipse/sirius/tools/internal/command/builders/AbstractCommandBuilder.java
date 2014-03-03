/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.builders;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Default implementation for {@link CommandBuilder}.
 * 
 * @author mchauvin
 */
public abstract class AbstractCommandBuilder implements CommandBuilder {

    /** the permission authority. */
    protected IPermissionAuthority permissionAuthority;

    /** The model accessor. */
    protected ModelAccessor modelAccessor;

    /** The editing domain. */
    protected TransactionalEditingDomain editingDomain;

    /** the user interface callback. */
    protected UICallBack uiCallback;

    /** A command helper. */
    protected TaskHelper taskHelper;

    /** auto refresh property. */
    protected boolean autoRefresh;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#init(org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority,
     *      org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor,
     *      org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void init(final ModelAccessor accessor, final TransactionalEditingDomain domain, final UICallBack ui) {
        this.modelAccessor = accessor;
        this.editingDomain = domain;
        this.uiCallback = ui;
        this.permissionAuthority = accessor.getPermissionAuthority();
        this.taskHelper = new TaskHelper(modelAccessor, uiCallback);
        this.autoRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
    }

    /**
     * Check the precondition of the specified tool with the specified container
     * and diagram.
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
     * Check the precondition of the specified tool with the specified
     * container.
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
     * Check the precondition of the specified tool with the specified
     * container.
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
     * Evaluates the precondition on the current tool. Must only be overridden
     * to add specific variables.
     * 
     * @param interpreter
     *            the interpreter capable to interpret the precondition
     *            expression
     * @param semanticContainer
     *            the semantic element on which evaluates the expression
     * @param precondition
     *            the expression to evaluate
     * 
     * @return the result of the precondition evaluation
     */
    protected boolean evaluatePrecondition(IInterpreter interpreter, EObject semanticContainer, String precondition) {
        boolean evaluation;
        try {
            evaluation = interpreter.evaluateBoolean(semanticContainer, precondition);
        } catch (EvaluationException e) {
            evaluation = false;
        }
        return evaluation;
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
                    public String getLabel() {
                        return "Set RefreshEditorsPrecommitListener options";
                    }

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
     * Add a refresh task to the diagram which contains the diagram element
     * given as parameter.
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

                public String getLabel() {
                    return "Add diagram variable";
                }

                public void execute() {
                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diag.get());
                }
            });
        }
    }

    /**
     * Indicates if the given element is contained in a {@link DDiagram} that is
     * in Layouting mode.
     * 
     * @param element
     *            the {@link DDiagram} or any {@link DDiagramElement}
     * @return true if the given element is contained in a {@link DDiagram} that
     *         is in Layouting mode
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
     * Return the enclosing command.
     * 
     * @return the enclosing command to put all tasks created by this builder.
     */
    protected DCommand createEnclosingCommand() {
        return new SiriusCommand(editingDomain, getEnclosingCommandLabel());
    }

    /**
     * Return the label of the enclosing command.
     * 
     * @return the label of the enclosing command.
     */
    protected abstract String getEnclosingCommandLabel();

    /**
     * Return the current diagram.
     * 
     * @return the current DDiagram.
     */
    protected abstract Option<DDiagram> getDDiagram();
}
