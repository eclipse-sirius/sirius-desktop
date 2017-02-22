/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterWithDiagnostic;
import org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener;
import org.eclipse.sirius.common.tools.api.interpreter.VariableManager;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;

import com.google.common.collect.Sets;

/**
 * A generic interpreter.
 * 
 * @author ymortier
 */
public class SessionInterpreter implements IInterpreter, IProposalProvider, IInterpreterWithDiagnostic {

    /** Maps provider with interpreters. */
    private final Map<IInterpreterProvider, IInterpreter> loadedInterpreters = new HashMap<IInterpreterProvider, IInterpreter>();

    /** The dependencies. */
    private Collection<String> dependencies = Sets.newLinkedHashSet();

    /**
     * If Sirius knows of any additional metamodel that may be necessary for the
     * interpreter, they'll be registered here.
     */
    private Collection<MetamodelDescriptor> additionalMetamodels = Sets.newLinkedHashSet();

    /** The variables. */
    private final VariableManager variables = new VariableManager();

    /** The properties. */
    private final Map<Object, Object> properties = new HashMap<Object, Object>();

    /** The optional model accessor. */
    private ModelAccessor modelAccessor;

    /** The variable status listener. */
    private final Collection<IVariableStatusListener> listeners = new HashSet<IVariableStatusListener>();

    /** The cross referencer. */
    private ECrossReferenceAdapter crossReferencer;

    /** The profiler tasks. */
    private final Map<String, ProfilerTask> profilerTasks = new HashMap<String, ProfilerTask>();

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        this.additionalMetamodels.addAll(metamodels);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            if (interpreter != null) {
                interpreter.activateMetamodels(metamodels);
            }
        }
    }

    @Override
    public void addImport(final String dependency) {
        this.dependencies.add(dependency);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            if (interpreter != null) {
                interpreter.addImport(dependency);
            }
        }
    }

    @Override
    public void clearImports() {
        this.dependencies.clear();
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.clearImports();
        }
    }

    @Override
    public boolean supportsValidation() {
        return true;
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return getInterpreter(expression).validateExpression(context, expression);
    }

    @Override
    public IEvaluationResult evaluateExpression(final EObject target, final String expression) throws EvaluationException {
        final IInterpreter interpreter = getInterpreter(expression);
        if (interpreter instanceof IInterpreterWithDiagnostic) {
            return ((IInterpreterWithDiagnostic) interpreter).evaluateExpression(target, expression);
        }

        // Fall back on the default behavior otherwise with an OK diagnostic
        final Object result = interpreter.evaluate(target, expression);

        IEvaluationResult evaluationResult = new IEvaluationResult() {
            @Override
            public Object getValue() {
                return result;
            }

            @Override
            public Diagnostic getDiagnostic() {
                return Diagnostic.OK_INSTANCE;
            }
        };

        return evaluationResult;
    }

    @Override
    public Object evaluate(final EObject target, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final Object evaluate = getInterpreter(expression).evaluate(target, expression);
        postEvaluation(expression);
        return evaluate;
    }

    @Override
    public boolean evaluateBoolean(final EObject context, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final boolean evaluateBoolean = getInterpreter(expression).evaluateBoolean(context, expression);
        postEvaluation(expression);
        return evaluateBoolean;
    }

    @Override
    public Collection<EObject> evaluateCollection(final EObject context, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final Collection<EObject> evaluateCollection = getInterpreter(expression).evaluateCollection(context, expression);
        postEvaluation(expression);
        return evaluateCollection;
    }

    @Override
    public EObject evaluateEObject(final EObject context, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final EObject evaluateEObject = getInterpreter(expression).evaluateEObject(context, expression);
        postEvaluation(expression);
        return evaluateEObject;
    }

    @Override
    public Integer evaluateInteger(final EObject context, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final Integer evaluateInteger = getInterpreter(expression).evaluateInteger(context, expression);
        postEvaluation(expression);
        return evaluateInteger;
    }

    @Override
    public String evaluateString(final EObject context, final String expression) throws EvaluationException {
        preEvaluation(expression);
        final String evaluateString = getInterpreter(expression).evaluateString(context, expression);
        postEvaluation(expression);
        return evaluateString;
    }

    @Override
    public boolean provides(final String expression) {
        return getInterpreter(expression) != null;
    }

    /**
     * Returns the interpreter to use for the given expression.
     * 
     * @param expression
     *            the expression to evaluate.
     * @return the interpreter to use for the given expression.
     */
    private IInterpreter getInterpreter(final String expression) {
        final IInterpreterProvider provider = CompoundInterpreter.INSTANCE.getProviderForExpression(expression);
        IInterpreter result = this.loadedInterpreters.get(provider);
        if (result == null) {
            result = provider.createInterpreter();
            this.loadedInterpreters.put(provider, result);
            for (final Entry<Object, Object> property : this.properties.entrySet()) {
                result.setProperty(property.getKey(), property.getValue());
                result.clearImports();
            }
            for (final String dependency : this.dependencies) {
                result.addImport(dependency);
            }
            result.activateMetamodels(additionalMetamodels);
            this.variables.setVariables(result);
            result.setModelAccessor(this.modelAccessor);
            for (final IVariableStatusListener variableStatusListener : this.listeners) {
                result.addVariableStatusListener(variableStatusListener);
            }
        }
        result.setCrossReferencer(crossReferencer);
        return result;
    }

    @Override
    public void setProperty(final Object key, final Object value) {
        this.properties.put(key, value);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.setProperty(key, value);
        }
    }

    /**
     * Returns the current value of a specific property.
     * 
     * @param key
     *            the property's key.
     * @return the property's value.
     */
    public Object getProperty(Object key) {
        return this.properties.get(key);
    }

    @Override
    public void clearVariables() {
        this.variables.clearVariables();
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            if (interpreter != null) {
                interpreter.clearVariables();
            }
        }
    }

    @Override
    public void dispose() {
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.dispose();
        }
    }

    @Override
    public Object getVariable(final String name) {
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            if (interpreter.getVariable(name) != null) {
                return interpreter.getVariable(name);
            }
        }
        return null;
    }

    @Override
    public void setVariable(final String name, final Object value) {
        this.variables.setVariable(name, value);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.setVariable(name, value);
        }
    }

    @Override
    public void unSetVariable(final String name) {
        this.variables.unSetVariable(name);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.unSetVariable(name);
        }
    }

    @Override
    public void addVariableStatusListener(final IVariableStatusListener newListener) {
        this.listeners.add(newListener);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.addVariableStatusListener(newListener);
        }
    }

    @Override
    public Map<String, Object> getVariables() {
        final Map<String, Object> result = new HashMap<String, Object>();
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            for (final Map.Entry<String, ?> entry : interpreter.getVariables().entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Override
    public void removeVariableStatusListener(final IVariableStatusListener listener) {
        this.listeners.remove(listener);
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.removeVariableStatusListener(listener);
        }
    }

    @Override
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        this.modelAccessor = modelAccessor;
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.setModelAccessor(modelAccessor);
        }
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        return CompoundInterpreter.INSTANCE.getProposals(interpreter, context);
    }

    @Override
    public String getPrefix() {
        return CompoundInterpreter.INSTANCE.getPrefix();
    }

    @Override
    public ContentProposal getNewEmtpyExpression() {
        return CompoundInterpreter.INSTANCE.getNewEmtpyExpression();
    }

    @Override
    public String getVariablePrefix() {
        return CompoundInterpreter.INSTANCE.getVariablePrefix();
    }

    @Override
    public void setCrossReferencer(final ECrossReferenceAdapter crossReferencer) {
        this.crossReferencer = crossReferencer;
        for (final IInterpreter interpreter : this.loadedInterpreters.values()) {
            interpreter.setCrossReferencer(crossReferencer);
        }
    }

    private void preEvaluation(final String toEval) {
        if (toEval != null && DslCommonPlugin.PROFILER.isActive()) {
            final ProfilerTask profilerTask = buildInterpretationTask(toEval);
            DslCommonPlugin.PROFILER.startWork(profilerTask);
        }
    }

    private void postEvaluation(final String toEval) {
        if (toEval != null && DslCommonPlugin.PROFILER.isActive()) {
            final ProfilerTask profilerTask = buildInterpretationTask(toEval);
            DslCommonPlugin.PROFILER.stopWork(profilerTask);
        }
    }

    private ProfilerTask buildInterpretationTask(final String evaluation) {
        ProfilerTask result = this.profilerTasks.get(evaluation);
        if (result == null) {
            result = new ProfilerTask(SiriusTasksKey.ACCELEO_CAT, evaluation);
            this.profilerTasks.put(evaluation, result);
        }
        return result;
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        return CompoundInterpreter.INSTANCE.getProposals(interpreter, context);
    }

    @Override
    public Collection<String> getImports() {
        return Collections.<String> unmodifiableCollection(this.dependencies);
    }

    @Override
    public void removeImport(String dependency) {
        this.dependencies.remove(dependency);
        for (IInterpreter interpreter : this.loadedInterpreters.values()) {
            if (interpreter != null) {
                interpreter.removeImport(dependency);
            }
        }
    }

}
