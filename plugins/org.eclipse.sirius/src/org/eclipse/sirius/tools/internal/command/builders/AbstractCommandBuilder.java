/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.command.builders;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

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
    @Override
    public void init(final ModelAccessor accessor, final TransactionalEditingDomain domain, final UICallBack ui) {
        this.modelAccessor = accessor;
        this.editingDomain = domain;
        this.uiCallback = ui;
        this.permissionAuthority = accessor.getPermissionAuthority();
        this.taskHelper = new TaskHelper(modelAccessor, uiCallback);
        Optional<Session> session = SessionManager.INSTANCE.getSessions().stream().filter(s -> s.getTransactionalEditingDomain().equals(editingDomain)).findAny();
        if (session.isPresent()) {
            this.autoRefresh = session.get().getSiriusPreferences().isAutoRefresh();
        }
    }

    /**
     * Evaluates the precondition on the current tool. Must only be overridden to add specific variables.
     * 
     * @param interpreter
     *            the interpreter capable to interpret the precondition expression
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
     * }
     * 
     * /** Return the enclosing command.
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
}
