/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener;
import org.eclipse.sirius.common.tools.api.interpreter.TypedValidation;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

import com.google.common.collect.Sets;

/**
 * A minimal implementation of {@link IInterpreter} which only handles litteral integers, booleans and strings. It is
 * used as a fallback/default implementation when interpreting an expression the none of the available interpreters can
 * {@link #provides(String) handle}. In practice this means this is the one used when specifiers use constant
 * expressions inside their VSMs where interpreted expressions are possible, e.g. a fixed label (string), size (integer)
 * or predicate/condition (boolean).
 * <p>
 * Note that the generic {@link #evaluate(EObject, String)} method will try to convert the expression into an integer if
 * possible (but not booleans) and either return that integer or the original expression string.
 *
 * @author ymortier, pcdavid
 */
public class DefaultInterpreterProvider implements IInterpreterProvider, IInterpreter, TypedValidation {

    @Override
    public IInterpreter createInterpreter() {
        return this;
    }

    @Override
    public void dispose() {
        // nothing to dispose
    }

    @Override
    public boolean provides(final String expression) {
        return true;
    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        // empty
    }

    @Override
    public void addImport(final String dependency) {
        // empty.
    }

    @Override
    public void addVariableStatusListener(final IVariableStatusListener newListener) {
        // empty.
    }

    @Override
    public void clearImports() {
        // empty.
    }

    @Override
    public void clearVariables() {
        // empty.
    }

    @Override
    public Object evaluate(final EObject target, final String expression) throws EvaluationException {
        Object result = evaluateInteger(target, expression);
        if (result != null) {
            return result;
        } else {
            return expression;
        }
    }

    @Override
    public boolean evaluateBoolean(final EObject context, final String expression) throws EvaluationException {
        return Boolean.parseBoolean(expression);
    }

    @Override
    public Collection<EObject> evaluateCollection(final EObject context, final String expression) throws EvaluationException {
        return Collections.<EObject> emptyList();
    }

    @Override
    public EObject evaluateEObject(final EObject context, final String expression) throws EvaluationException {
        return context;
    }

    @Override
    public Integer evaluateInteger(final EObject context, final String expression) throws EvaluationException {
        try {
            return new Integer(expression);
        } catch (final NumberFormatException nfe) {
            return null;
        }
    }

    @Override
    public String evaluateString(final EObject context, final String expression) throws EvaluationException {
        return expression;
    }

    @Override
    public Object getVariable(final String name) {
        return null;
    }

    @Override
    public Map<String, Object> getVariables() {
        return Collections.<String, Object> emptyMap();
    }

    @Override
    public void removeVariableStatusListener(final IVariableStatusListener listener) {
        // empty
    }

    @Override
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        // empty
    }

    @Override
    public void setProperty(final Object key, final Object value) {
        // empty
    }

    @Override
    public void setVariable(final String name, final Object value) {
        // empty.
    }

    @Override
    public void unSetVariable(final String name) {
        // empty
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getVariablePrefix() {
        return null;
    }

    @Override
    public void setCrossReferencer(final ECrossReferenceAdapter crossReferencer) {
        // nothing to do.
    }

    @Override
    public Collection<String> getImports() {
        return Collections.<String> emptyList();
    }

    @Override
    public void removeImport(String dependency) {
        // empty.
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return Sets.newLinkedHashSet();
    }

    @Override
    public boolean supportsValidation() {
        return false;
    }

    @Override
    public ValidationResult analyzeExpression(IInterpreterContext context, String expression) {
        return new ValidationResult();
    }

}
