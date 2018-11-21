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

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Abstract base class for {@link IExternalJavaAction} implementations, with
 * helper methods to deal with parameters.
 * 
 * @author pcdavid
 */
public abstract class AbstractExternalJavaAction implements IExternalJavaAction {

    /**
     * Gets a required parameter by its name an expected type.
     * 
     * @param <T>
     *            the expected type of the parameter.
     * @param parameters
     *            the map of all the parameters passed to the action.
     * @param name
     *            the name of the parameter.
     * @param type
     *            the expected type of the parameter.
     * @return the value of the named parameter, if it is present and of an
     *         appropriate (compatible) type.
     * @throws IllegalArgumentException
     *             if the parameter is missing or is not type-compatible with
     *             the expected type.
     */
    protected <T> T getParameter(Map<String, Object> parameters, String name, Class<T> type) {
        return getParameter(parameters, name, type, true);
    }

    /**
     * Gets an optional parameter by its name an expected type.
     * 
     * @param <T>
     *            the expected type of the parameter.
     * @param parameters
     *            the map of all the parameters passed to the action.
     * @param name
     *            the name of the parameter.
     * @param type
     *            the expected type of the parameter.
     * @return the value of the named parameter, if it is present and of an
     *         appropriate (compatible) type, or <code>null</code> if no
     *         parameter with the specified name is available.
     * @throws IllegalArgumentException
     *             if the parameter is present but is not type-compatible with
     *             the expected type.
     */
    protected <T> T getOptionalParameter(Map<String, Object> parameters, String name, Class<T> type) {
        return getParameter(parameters, name, type, false);
    }

    private <T> T getParameter(Map<String, Object> parameters, String name, Class<T> type, boolean required) {
        if (required && !parameters.containsKey(name)) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.AbstractExternalJavaAction_parameterErrorMsg, name, this.getClass().getName()));
        }
        T result;
        Object value = parameters.get(name);
        if (type.isInstance(value)) {
            result = type.cast(value);
        } else if (value == null && !required) {
            result = null;
        } else {
            String typeName = (value == null) ? Messages.AbstractExternalJavaAction_nullParameter
                    : (MessageFormat.format(Messages.AbstractExternalJavaAction_parameterType, value.getClass().getSimpleName()));
            throw new IllegalArgumentException(MessageFormat.format(Messages.AbstractExternalJavaAction_parameterTypeErrorMsg, name, type.getSimpleName(), typeName));
        }
        return result;
    }

}
