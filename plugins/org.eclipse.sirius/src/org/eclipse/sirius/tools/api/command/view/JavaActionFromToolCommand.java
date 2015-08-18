/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Specific command to set the actual mapping of an edge.
 * 
 * @author mporhel
 */
public final class JavaActionFromToolCommand extends RecordingCommand {

    private final IExternalJavaAction action;

    private final ExternalJavaAction tool;

    private final Collection<DSemanticDecorator> containerViews;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param javaAction
     *            the java action.
     * @param tool
     *            the tool.
     * @param containerViews
     *            the container views
     */
    public JavaActionFromToolCommand(TransactionalEditingDomain domain, IExternalJavaAction javaAction, ExternalJavaAction tool, Collection<DSemanticDecorator> containerViews) {
        super(domain, "Java action from tool: " + (javaAction != null ? javaAction.getClass().getSimpleName() : "")); //$NON-NLS-2$
        this.action = javaAction;
        this.tool = tool;
        this.containerViews = containerViews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doExecute() {
        if (action == null || tool == null || containerViews == null) {
            return;
        }

        final Map<String, Object> parameters = new HashMap<String, Object>();
        // Evaluate the parameters
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(containerViews.iterator().next().getTarget());
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        for (final ExternalJavaActionParameter parameter : tool.getParameters()) {
            Object value = null;
            if (containerViews.size() == 1) {
                value = safeInterpreter.evaluate(containerViews.iterator().next(), parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
            } else {
                value = new ArrayList<Object>(containerViews.size());
                for (final DSemanticDecorator semanticDecorator : containerViews) {
                    final Object val = safeInterpreter.evaluate(semanticDecorator, parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
                    if (val != null) {
                        ((List) value).add(val);
                    }
                }
            }
            parameters.put(parameter.getName(), value);
        }
        action.execute(containerViews, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute() {
        return action != null && action.canExecute(containerViews);
    }
}
