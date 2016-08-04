/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A context providing all informations required by {@link IInterpreter} to
 * validate statically an expression and compute completion proposals.
 * 
 * @since 0.9.0
 * @author alagarde
 */
public interface IInterpreterContext {

    /**
     * Returns the concerned element.
     * 
     * @return the concerned element.
     */
    EObject getElement();

    /**
     * Indicates if the expression need all possibles type that can be held by
     * "current" element to be validated. It can not be true, for example when
     * considering a PopupMenuContribution's precondition, that is only
     * evaluated with variables and the clicked element whose type can not be
     * computed from Viewpoint Specification Model. The receiver will be typed
     * as EObject.
     * 
     * @return true if the expression need all possibles type that can be held
     *         by "current" element to be validated, false otherwise
     */
    boolean requiresTargetType();

    /**
     * Returns the EPackages that are currently available to validate an
     * expression.
     * 
     * @return the EPackages that are currently available to validate an
     *         expression
     */
    Collection<EPackage> getAvailableEPackages();

    /**
     * Returns the available variables. Key is the variable name, value is the
     * variable Type.
     * 
     * @return the available variables (Key is the variable name, value is the
     *         variable Type)
     */
    Map<String, VariableType> getVariables();

    /**
     * Returns the feature containing the expression to evaluate.
     * 
     * @return the feature containing the expression to evaluate
     */
    EStructuralFeature getField();

    /**
     * Returns the dependencies that are currently available to validate an
     * expression.
     * 
     * @return the dependencies that are currently available to validate an
     *         expression
     */
    Collection<String> getDependencies();

    /**
     * Returns a representation of the current receiver type. This type might be
     * the union of several types.
     * 
     * @return a representation of the current receiver type. This type might be
     *         the union of several types.
     * @since 3.0
     * 
     */
    VariableType getTargetType();

}
