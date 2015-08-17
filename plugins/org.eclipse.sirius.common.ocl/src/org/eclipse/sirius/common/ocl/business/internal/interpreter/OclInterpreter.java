/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ocl.business.internal.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreFactory;
import org.eclipse.ocl.ecore.SequenceType;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.sirius.common.ocl.DslOclPlugin;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener;
import org.eclipse.sirius.common.tools.api.interpreter.VariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * The OCL interpreter.
 * 
 * @author ymortier
 */
public class OclInterpreter implements IInterpreter, IInterpreterProvider, IProposalProvider {

    /** The OCL expression discrimant. */
    public static final String OCL_DISCRIMINANT = "ocl:";

    /** The OCL object. */
    private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;

    /** The OCL helper. */
    private OCLHelper<EClassifier, ?, ?, Constraint> helper;

    /** The variables. */
    private final VariableManager variables = new VariableManager();

    /** The variables listener. */
    private final List<IVariableStatusListener> variablesListeners = new LinkedList<IVariableStatusListener>();

    /**
     * {@inheritDoc}
     */
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    public void addImport(final String dependency) {
        // ignore
    }

    /**
     * {@inheritDoc}
     */
    public void clearImports() {
        // ignore
    }

    /**
     * {@inheritDoc}
     */
    public void addVariableStatusListener(final IVariableStatusListener newListener) {
        this.variablesListeners.add(newListener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeVariableStatusListener(final IVariableStatusListener listener) {
        this.variablesListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void setVariable(final String name, final Object value) {
        this.variables.setVariable(name, value);
        this.fireVariablesChanged();
    }

    /**
     * {@inheritDoc}
     */
    public void unSetVariable(final String name) {
        this.variables.unSetVariable(name);
        this.fireVariablesChanged();
    }

    /**
     * {@inheritDoc}
     */
    public void clearVariables() {
        this.variables.clearVariables();
        this.fireVariablesChanged();
    }

    /**
     * {@inheritDoc}
     */
    public Object getVariable(final String name) {
        return this.variables.getVariable(name);
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> getVariables() {
        return this.variables.getVariables();
    }

    /**
     * Notifies all variables listeners that variables are modified.
     */
    protected void fireVariablesChanged() {
        for (final IVariableStatusListener listener : this.variablesListeners) {
            listener.notifyChanged(getVariables());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(final String expression) {
        return expression != null && expression.startsWith(OclInterpreter.OCL_DISCRIMINANT);
    }

    /**
     * {@inheritDoc}
     */
    public void setProperty(final Object key, final Object value) {
        // ignore.
    }

    /**
     * {@inheritDoc}
     */
    public Object evaluate(final EObject target, final String expression) throws EvaluationException {
        return internalEvaluate(target, expression);
    }

    /**
     * {@inheritDoc}
     */
    public boolean evaluateBoolean(final EObject context, final String expression) throws EvaluationException {
        final Object value = evaluate(context, expression);
        boolean result = false;
        if (value instanceof Boolean) {
            result = ((Boolean) value).booleanValue();
        } else if (value instanceof String) {
            result = Boolean.parseBoolean((String) value);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<EObject> evaluateCollection(final EObject context, final String expression) throws EvaluationException {
        final Object value = evaluate(context, expression);
        Collection<EObject> result = Collections.emptyList();
        if (value instanceof EObject) {
            result = new ArrayList<EObject>(1);
            result.add((EObject) value);
        } else if (value instanceof Collection) {
            result = new ArrayList<EObject>(((Collection<?>) value).size());
            for (final Object object : (Collection<?>) value) {
                if (object instanceof EObject) {
                    result.add((EObject) object);
                }
            }
        } else if (value != null && value.getClass().isArray()) {
            result = new ArrayList<EObject>(((Object[]) value).length);
            for (final Object object : (Object[]) value) {
                if (object instanceof EObject) {
                    result.add((EObject) object);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public EObject evaluateEObject(final EObject context, final String expression) throws EvaluationException {
        final Object value = evaluate(context, expression);
        if (value instanceof EObject) {
            return (EObject) value;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Integer evaluateInteger(final EObject context, final String expression) throws EvaluationException {
        final Object value = evaluate(context, expression);
        Integer result = null;
        if (value instanceof Integer) {
            result = (Integer) value;
        } else if (value instanceof String) {
            try {
                result = new Integer((String) value);
            } catch (final NumberFormatException nfe) {
                DslOclPlugin.getPlugin().error("The value returned by the OCL evaluation is not a number", nfe);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public String evaluateString(final EObject context, final String expression) throws EvaluationException {
        final Object value = evaluate(context, expression);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    /**
     * Internal evaluation of this ocl expression.
     * 
     * @param context
     *            the context of the expression.
     * @param expression
     *            the expression to evaluate.
     * @return the result of the evaluation.
     * @throws EvaluationException
     *             if the evaluation fails.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object internalEvaluate(final EObject context, final String expression) throws EvaluationException {
        try {
            final String exp = expression.substring(OCL_DISCRIMINANT.length());
            if ("".equals(exp)) {
                return null;
            }

            // Add the variables in the parsing environment
            getOCLHelper().setContext(context.eClass());
            for (final Map.Entry<String, Object> variable : getVariables().entrySet()) {
                final Object value = variable.getValue();
                final org.eclipse.ocl.ecore.Variable oclVar = EcoreFactory.eINSTANCE.createVariable();
                if (value instanceof Collection) {
                    final SequenceType sequence = EcoreFactory.eINSTANCE.createSequenceType();
                    sequence.setElementType(EcorePackage.eINSTANCE.getEObject());
                    oclVar.setType(sequence);
                } else {
                    oclVar.setType(EcorePackage.eINSTANCE.getEObject());
                }
                oclVar.setName(variable.getKey());
                getOCLHelper().getOCL().getEnvironment().addElement(oclVar.getName(), (Variable) oclVar, false);
            }

            final OCLExpression<EClassifier> query = getOCLHelper().createQuery(exp);
            final Query<EClassifier, EClass, EObject> eval = getOCL().createQuery(query);

            for (final Map.Entry<String, Object> variable : getVariables().entrySet()) {
                eval.getEvaluationEnvironment().add(variable.getKey(), variable.getValue());
            }

            return eval.evaluate(context);

        } catch (final ParserException pe) {
            throw new EvaluationException(pe);
        }
    }

    /**
     * This method might initialize a new helper.
     * 
     * @return the interpreter ocl helper
     */
    public OCLHelper<EClassifier, ?, ?, Constraint> getOCLHelper() {
        if (helper == null) {
            helper = getOCL().createOCLHelper();
        }
        return helper;
    }

    private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> getOCL() {
        if (ocl == null) {
            ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
        }
        return ocl;
    }

    /**
     * {@inheritDoc}
     */
    public IInterpreter createInterpreter() {
        return new OclInterpreter();
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        this.variables.clearVariables();
        this.variablesListeners.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        // ignore
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        List<ContentProposal> computeCompletionEntry = OclCompletionEntry.computeCompletionEntry(context);
        return computeCompletionEntry;
    }

    /**
     * {@inheritDoc}
     */
    public String getPrefix() {
        return OclInterpreter.OCL_DISCRIMINANT;
    }

    /**
     * {@inheritDoc}
     */
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(OclInterpreter.OCL_DISCRIMINANT, OclInterpreter.OCL_DISCRIMINANT, "New OCL expression.");
    }

    /**
     * {@inheritDoc}
     */
    public String getVariablePrefix() {
        return null; // no prefix for variables
    }

    /**
     * {@inheritDoc}
     */
    public void setCrossReferencer(final ECrossReferenceAdapter crossReferencer) {
        // no handling (AFAIK) of cross references for OCL
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        // Make sure that context and cursor position are valid
        if (context != null && context.getTextSoFar() != null && context.getCursorPosition() != -1 && context.getTextSoFar().length() >= context.getCursorPosition()) {
            final String textSoFar = context.getTextSoFar();
            String evaluationString = textSoFar.substring(0, context.getCursorPosition());

            if (evaluationString.toLowerCase().startsWith(OclInterpreter.OCL_DISCRIMINANT) && context.getCurrentSelected() != null) {
                getOCLHelper().setContext(context.getCurrentSelected().eClass());
                // Remove the "ocl:" tag
                evaluationString = evaluationString.substring(OclInterpreter.OCL_DISCRIMINANT.length());

                // Ask OCLHelper for proposals and process them
                final List<Choice> choices = getOCLHelper().getSyntaxHelp(ConstraintKind.INVARIANT, evaluationString);
                if (choices != null && choices.size() > 0) {
                    final List<ContentProposal> contentProposals = new ArrayList<ContentProposal>();
                    for (Choice choice : choices) {
                        contentProposals.add(new ContentProposal(choice.getName(), choice.getName() + " (" + choice.getKind().name() + ")", choice.getDescription()));
                    }
                    Collections.sort(contentProposals);
                    return contentProposals;
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<String> getImports() {
        return Collections.<String> emptyList();
    }

    /**
     * {@inheritDoc}
     */
    public void removeImport(String dependency) {
        // empty
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return new LinkedHashSet<IInterpreterStatus>();
    }

    /**
     * {@inheritDoc}
     */
    public boolean supportsValidation() {
        return false;
    }
}
