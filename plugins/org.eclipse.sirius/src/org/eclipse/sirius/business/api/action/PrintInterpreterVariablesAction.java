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
package org.eclipse.sirius.business.api.action;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * An action designed to help debugging VSMs by dumping on <code>stdout</code>
 * all the variables accessible to model operations at the invocation point.
 * <p>
 * Action parameters:
 * <ul>
 * <li><code>title</code>: an optional header <code>String</code> to print
 * before the list of variables, for example to distinguish different uses of
 * this action in the same VSM.</li>
 * <li><code>enabled</code>: an optional <code>boolean</code> (defaults to
 * <code>true</code>) to indicate if the action should be executed or not. This
 * is useful to quickly enable/disable an action with minimal editing of the
 * VSM.</li>
 * </ul>
 * 
 * @author pcdavid
 */
public class PrintInterpreterVariablesAction extends AbstractExternalJavaAction {
    private static final String TITLE_PARAM = "title"; //$NON-NLS-1$

    private static final String ENABLED_PARAM = "enabled"; //$NON-NLS-1$

    private static final String DEFAULT_TITLE = "<no title>";

    /**
     * {@inheritDoc}
     */
    public boolean canExecute(Collection<? extends EObject> selections) {
        return !selections.isEmpty();
    }

    /**
     * Expects at least one {@link EObject} in the selection, to obtain the
     * corresponding interpreter.
     * <p>
     * {@inheritDoc}
     */
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
        SortedMap<String, Object> allVariables = Maps.newTreeMap(Ordering.natural());
        allVariables.putAll(interpreter.getVariables());
        allVariables.put("self", context); //$NON-NLS-1$
        return allVariables;
    }

    private void printVariables(String title, SortedMap<String, Object> allVariables) {
        if (allVariables.isEmpty()) {
            // CHECKSTYLE:OFF
            System.out.println("[" + title + "] no variables available.");
            // CHECKSTYLE:ON
        } else {
            int maxLength = Ordering.natural().onResultOf(new Function<String, Integer>() {
                public Integer apply(String from) {
                    return from.length();
                }
            }).max(allVariables.keySet()).length();
            // CHECKSTYLE:OFF
            System.out.println("[" + title + "] variables available:");
            int i = 1;
            for (Map.Entry<String, Object> variable : allVariables.entrySet()) {
                System.out.print("  " + i++ + ". " + variable.getKey()); //$NON-NLS-1$ //$NON-NLS-2$
                for (int j = 0; j < (maxLength - variable.getKey().length()); j++) {
                    System.out.print(" "); //$NON-NLS-1$
                }
                System.out.println(": " + variable.getValue()); //$NON-NLS-1$
            }
            System.out.println();
            // CHECKSTYLE:ON
        }
    }
}
