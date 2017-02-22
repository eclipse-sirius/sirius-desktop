/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.logger;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A decorator to {@link org.eclipse.sirius.common.tools.api.interpreter.IInterpreter}
 * to log evaluation exceptions.
 * 
 * @author mchauvin
 */
public interface RuntimeLoggerInterpreter {

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as an {@Object}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the {@link Object} value if the evaluation succeed,
     *         <code>null</code> otherwise
     */
    Object evaluate(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as an {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the boolean value if the evaluation succeed, false otherwise
     */
    boolean evaluateBoolean(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as an {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @param flagCondition
     *            this flag is used to know if evaluateBoolean call come from
     *            IfTask or SwitchTask
     * @return the boolean value if the evaluation succeed, false otherwise
     */
    boolean evaluateBoolean(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature, boolean flagCondition);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as an {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the integer reference if the evaluation succeed,
     *         <code>null</code> otherwise
     */
    Integer evaluateInteger(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as a {@link String}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the string object if the evaluation succeed, <code>null</code>
     *         otherwise
     */
    String evaluateString(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as an {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the {@link EObject} value if the evaluation succeed,
     *         <code>null</code> otherwise
     */
    EObject evaluateEObject(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

    /**
     * Evaluates the value of object feature given as parameter as an expression
     * on the given context and returns the result as a collection of
     * {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param descriptionObject
     *            the current object which owns the feature.
     * @param descriptionFeature
     *            the feature which the value will be evaluated as an expression
     * @return the collection if the evaluation succeed, an empty collection
     *         otherwise
     */
    Collection<EObject> evaluateCollection(EObject context, EObject descriptionObject, EStructuralFeature descriptionFeature);

}
