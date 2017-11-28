/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The result of an expression validation.
 * 
 * @author cedric
 * @since 3.0
 *
 */
public class ValidationResult {

    private List<IInterpreterStatus> statuses = new ArrayList<>();

    private VariableType returnType = VariableType.ANY_EOBJECT;

    /**
     * Add a status in the result.
     * 
     * @param newStatus
     *            a status.
     */
    public void addStatus(IInterpreterStatus newStatus) {
        this.statuses.add(newStatus);
    }

    /**
     * Add several statuses at once in the validation result.
     * 
     * @param newStatuses
     *            new statuses.
     */
    public void addAllStatus(Collection<IInterpreterStatus> newStatuses) {
        this.statuses.addAll(newStatuses);
    }

    /**
     * Specify the expression return type.
     * 
     * @param returnType
     *            expression return type.
     */
    public void setReturnType(VariableType returnType) {
        this.returnType = returnType;
    }

    /**
     * return the validation statuses.
     * 
     * @return the validation statuses.
     */
    public Collection<IInterpreterStatus> getStatuses() {
        return this.statuses;
    }

    /**
     * return the expression return types.
     * 
     * @return the expression return types.
     */
    public VariableType getReturnTypes() {
        return returnType;
    }

    /**
     * Return a map of type infered types based on the fact that the expression
     * is considered as returning a specific boolean result.
     * 
     * @param value
     *            the predicate result we consider.
     * @return a map of type infered types based on the fact that the expression
     *         is considered as returning true or false.
     */
    public Map<String, VariableType> getInferredVariableTypes(Boolean value) {
        return Collections.<String, VariableType> emptyMap();
    }
}
