/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A task which call an external Java Action.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExternalJavaActionTask extends AbstractOperationTask {

    private final ExternalJavaAction externalJavaAction;

    private final IExternalJavaAction javaAction;

    private final Collection<EObject> selections = new ArrayList<EObject>();

    private final ModelAccessor accessor;

    private final TaskHelper taskHelper;

    /**
     * Default constructor.
     * 
     * @param context
     *            the command context
     * @param extPackage
     *            the extended package
     * @param op
     *            the operation
     * @param interpreter
     *            the {@link IInterpreter} to be used to this task
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public ExternalJavaActionTask(final CommandContext context, final ModelAccessor extPackage, final ExternalJavaAction op, final IInterpreter interpreter, final UICallBack uiCallback) {
        super(context, extPackage, interpreter);
        externalJavaAction = op;
        this.accessor = extPackage;
        if (context.getCurrentTarget() != null) {
            selections.add(context.getCurrentTarget());
        }
        javaAction = ExternalJavaActionProvider.INSTANCE.getJavaActionById(externalJavaAction.getId());
        this.taskHelper = new TaskHelper(accessor, uiCallback);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        final Map<String, Object> parameters = new HashMap<String, Object>();

        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

        if (taskHelper.checkPrecondition(context.getCurrentTarget(), externalJavaAction)) {
            // Evaluate the parameters
            for (final ExternalJavaActionParameter parameter : externalJavaAction.getParameters()) {
                final Object value = safeInterpreter.evaluate(context.getCurrentTarget(), parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
                parameters.put(parameter.getName(), value);
            }
            javaAction.execute(selections, parameters);
        }
    }

    @Override
    public String getLabel() {
        return MessageFormat.format(Messages.ExternalJavaActionTask_label, externalJavaAction.getName());
    }

    @Override
    public boolean canExecute() {
        return javaAction != null && javaAction.canExecute(selections);

    }

}
