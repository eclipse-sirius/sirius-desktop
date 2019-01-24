/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * The contract of an interpreter for EMF Model.
 * 
 * @author ymortier
 */
public interface IInterpreter {

    /**
     * Key for all workspace/plug-in representation description files.
     * 
     * @since 0.9.0
     */
    String FILES = "files"; //$NON-NLS-1$

    // ===================================================================
    // General information about the language supported by the interpreter
    // ===================================================================
    
    /**
     * Returns <code>true</code> if this interpreter is able to evaluate the given expression.
     * 
     * @param expression
     *            the expression to evaluate.
     * @return <code>true</code> if this interpreter is able to evaluate the given expression.
     */
    boolean provides(String expression);

    /**
     * Get the prefix.
     * 
     * @return the prefix if there is one or <code>null</code> if none.
     */
    String getPrefix();

    /**
     * Get the variable prefix for this interpreter.
     * 
     * @return the prefix if there is one or <code>null</code> if none
     */
    String getVariablePrefix();

    // ===================================================================
    // Optional static validation support
    // ===================================================================

    /**
     * Indicates if this {@link IInterpreter} supports static validation of expressions (which means that the
     * {@link IInterpreter#validateExpression(IInterpreterContext, String)} method is able to return meaningful
     * statuses).
     * 
     * @since 0.9.0
     * @return true if this {@link IInterpreter} supports static validation of expressions, false otherwise
     */
    boolean supportsValidation();

    /**
     * Indicates if the given expression is valid. Notice that if {@link IInterpreter#supportsValidation()} returns
     * false this method will always return an empty list.
     * 
     * @since 0.9.0
     * @param context
     *            the {@link IInterpreterContext} to use for validating this expression
     * @param expression
     *            the expression to analyze
     * @return a collection containing all warnings and errors found during the validation. An empty list means that the
     *         validation was successful.
     */
    Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression);

    // ===================================================================
    // The actual expression interpretation API
    // ===================================================================

    /**
     * Wrapper method to evaluate an expression.
     * 
     * @param target
     *            the EObject instance to evaluate on.
     * @param expression
     *            the expression to evaluate.
     * @return an object with the evaluation result.
     * @throws EvaluationException
     *             if the evaluation was not successful.
     */
    Object evaluate(EObject target, String expression) throws EvaluationException;

    /**
     * Generic method to evaluate an expression and return the result and its diagnostic. This method should have the
     * same behavior as the method {@link IInterpreter#evaluate(EObject, String)} but with a result containing a
     * diagnostic too.
     * 
     * @param target
     *            the EObject instance to evaluate on.
     * @param expression
     *            the expression to evaluate.
     * @return an object with the evaluation result.
     * @throws EvaluationException
     *             if the evaluation was not successful.
     */
    default IEvaluationResult evaluateExpression(EObject target, String expression) throws EvaluationException {
        Object result = this.evaluate(target, expression);
        return EvaluationResult.ofValue(result, getConverter());
    }

    /**
     * Returns the {@link IConverter} to use to coerce the raw values returned by the implemented into the small set of
     * types specifically supported by Sirius.
     * 
     * @return the converter to use for the raw results of this interpreter.
     */
    IConverter getConverter();

    /**
     * Evaluates the given expression on the given context and returns the result as a collection of {@link EObject}s.
     * 
     * @param context
     *            The context.
     * @param expression
     *            the expression to evaluate.
     * @return the collection of {@link EObject}s.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    default Collection<EObject> evaluateCollection(EObject context, String expression) throws EvaluationException {
        Object rawValue = evaluate(context, expression);
        return getConverter().toEObjectCollection(rawValue).orElse(Collections.emptySet());
    }

    /**
     * Evaluates the given expression on the given context and returns the result as a Boolean.
     * 
     * @param context
     *            The context.
     * @param expression
     *            the expression to evaluate.
     * @return the boolean value, false if adaptation fails.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    default boolean evaluateBoolean(EObject context, String expression) throws EvaluationException {
        Object rawValue = evaluate(context, expression);
        return getConverter().toBoolean(rawValue).orElse(false);
    }

    /**
     * Evaluates the given expression on the given context and returns the result as an {@link EObject}.
     * 
     * @param context
     *            The context.
     * @param expression
     *            the expression to evaluate.
     * @return the {@link EObject} value.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    default EObject evaluateEObject(EObject context, String expression) throws EvaluationException {
        Object rawValue = evaluate(context, expression);
        return getConverter().toEObject(rawValue).orElse(null);
    }

    /**
     * Evaluates the given expression on the given context and returns the result as a {@link String}.
     * 
     * @param context
     *            The context.
     * @param expression
     *            the expression to evaluate.
     * @return the {@link String} value.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    default String evaluateString(EObject context, String expression) throws EvaluationException {
        Object rawValue = evaluate(context, expression);
        return getConverter().toString(rawValue).orElse(null);
    }

    /**
     * Evaluates the given expression on the given context and returns the result as an {@link Integer}.
     * 
     * @param context
     *            The context.
     * @param expression
     *            the expression to evaluate.
     * @return the {@link Integer} value.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    default Integer evaluateInteger(EObject context, String expression) throws EvaluationException {
        Object rawValue = evaluate(context, expression);
        return getConverter().toInt(rawValue).orElse(0);
    }
    
    // ===================================================================
    // Stateful variable/environment management
    // ===================================================================

    /**
     * Sets a variable.
     * 
     * @param name
     *            the name of the variable.
     * @param value
     *            the value of the variable.
     */
    void setVariable(String name, Object value);

    /**
     * Unsets a variable.
     * 
     * @param name
     *            the name of the variable to unset.
     */
    void unSetVariable(String name);

    /**
     * Returns the value of the variable.
     * 
     * @param name
     *            the name of the variable.
     * @return the value of the variable.
     */
    Object getVariable(String name);

    /**
     * Returns all declared variables.
     * 
     * @return all declared variables.
     */
    Map<String, ?> getVariables();

    /**
     * Clears all variables.
     */
    void clearVariables();

    // ===================================================================
    // Stateful configuration of the interpreter
    // - imports
    // - properties, in practice only FILES exists
    // - model accessor
    // - cross-referencer
    // - metamodels to consider
    // ===================================================================

    /**
     * Adds a dependency to this interpreter.
     * 
     * @param dependency
     *            the dependency to add.
     */
    void addImport(String dependency);

    /**
     * Get the imports (qualified names) for this interpreter.
     * 
     * @return a collection of imports.
     * @since 0.9.0
     */
    Collection<String> getImports();

    /**
     * Remove an import.
     * 
     * @param dependency
     *            the import to remove.
     * @since 0.9.0
     */
    void removeImport(String dependency);

    /**
     * Clear all dependencies of this interpreter.
     */
    void clearImports();
    
    /**
     * Sets a property to this interpreter.
     * 
     * @param key
     *            the key of the property.
     * @param value
     *            the value of the property.
     */
    void setProperty(Object key, Object value);

    /**
     * Sets the optional model accessor to use.
     * 
     * @param modelAccessor
     *            the optional model accessor to use.
     */
    void setModelAccessor(ModelAccessor modelAccessor);
    
    /**
     * Set the interpreter cross referencer.
     * 
     * @param crossReferencer
     *            any cross referencer concerning the models.
     */
    void setCrossReferencer(ECrossReferenceAdapter crossReferencer);

    /**
     * Tells the interpreter that the list of available metamodels has changed.
     * 
     * @param metamodels
     *            The new metamodels.
     */
    void activateMetamodels(Collection<MetamodelDescriptor> metamodels);

    // ===================================================================
    // Lifecycle management: only needed because the interpreter holds state
    // ===================================================================

    /**
     * This will be called when the session is closed. Clients should dispose of all data that is no longer needed
     */
    void dispose();
}
