/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.action;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import com.google.common.collect.Ordering;

/**
 * An action designed to help debugging VSMs by dumping on <code>stdout</code> all the variables accessible to model
 * operations at the invocation point.
 * <p>
 * Action parameters:
 * <ul>
 * <li><code>title</code>: an optional header <code>String</code> to print before the list of variables, for example to
 * distinguish different uses of this action in the same VSM.</li>
 * <li><code>enabled</code>: an optional <code>boolean</code> (defaults to <code>true</code>) to indicate if the action
 * should be executed or not. This is useful to quickly enable/disable an action with minimal editing of the VSM.</li>
 * </ul>
 * 
 * @author pcdavid
 */
public class PrintInterpreterVariablesAction extends AbstractExternalJavaAction {
    private static final String TITLE_PARAM = "title"; //$NON-NLS-1$

    private static final String ENABLED_PARAM = "enabled"; //$NON-NLS-1$

    private static final String DEFAULT_TITLE = "<no title>"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return !selections.isEmpty();
    }

    /**
     * Expects at least one {@link EObject} in the selection, to obtain the corresponding interpreter.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Boolean enabled = getOptionalParameter(parameters, ENABLED_PARAM, Boolean.class);
        if (enabled != null && !enabled.booleanValue()) {
            return;
        }
        String title = getOptionalParameter(parameters, TITLE_PARAM, String.class);

        SortedMap<String, Object> allVariables = getSortedVariables(selections);
        printVariables(title != null ? title : DEFAULT_TITLE, allVariables);
    }

    private SortedMap<String, Object> getSortedVariables(Collection<? extends EObject> selections) {
        EObject context = selections.iterator().next();
        IInterpreter interpreter = InterpreterUtil.getInterpreter(context);
        SortedMap<String, Object> allVariables = new TreeMap<>(Ordering.natural());
        allVariables.putAll(interpreter.getVariables());
        allVariables.put("self", context); //$NON-NLS-1$
        return allVariables;
    }

    private void printVariables(String title, SortedMap<String, Object> allVariables) {
        StringBuilder sb = new StringBuilder();
        if (allVariables.isEmpty()) {
            sb.append("[").append(title).append("] no variables available.\n"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            int maxLength = Ordering.natural().onResultOf(String::length).max(allVariables.keySet()).length();
            sb.append("[").append(title).append("] variables available:\n"); //$NON-NLS-1$ //$NON-NLS-2$
            int i = 1;
            for (Map.Entry<String, Object> variable : allVariables.entrySet()) {
                sb.append("  ").append(i++).append(". ").append(variable.getKey()); //$NON-NLS-1$ //$NON-NLS-2$
                for (int j = 0; j < (maxLength - variable.getKey().length()); j++) {
                    sb.append(" "); //$NON-NLS-1$
                }
                sb.append(": ").append(variable.getValue()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            sb.append("\n"); //$NON-NLS-1$
            // CHECKSTYLE:OFF
            System.out.print(sb.toString());
            // CHECKSTYLE:ON
        }
    }
}
