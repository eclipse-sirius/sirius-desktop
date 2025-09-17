/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.ocl.business.internal.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.ocl.EnvironmentFactory;
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
import org.eclipse.sirius.common.ocl.tools.Messages;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IConverter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.VariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * The OCL interpreter.
 * 
 * @author ymortier
 */
public class OclInterpreter implements IInterpreter, IInterpreterProvider, IProposalProvider {

    /**
     * Coercion rules used to convert raw values obtained from OCL into the values returned to Sirius.
     * 
     * @author pcdavid
     */
    private static final class OclConverter implements IConverter {

        @Override
        public OptionalInt toInt(Object rawValue) {
            Integer result = null;
            if (rawValue instanceof Integer) {
                result = (Integer) rawValue;
            } else if (rawValue instanceof String) {
                try {
                    result = new Integer((String) rawValue);
                } catch (final NumberFormatException nfe) {
                    DslOclPlugin.getPlugin().error(Messages.OclInterpreter_OclNotANumber, nfe);
                }
            }
            if (result != null) {
                return OptionalInt.of(result);
            } else {
                return OptionalInt.empty();
            }
        }

        @Override
        public Optional<Boolean> toBoolean(Object rawValue) {
            boolean result = false;
            if (rawValue instanceof Boolean) {
                result = ((Boolean) rawValue).booleanValue();
            } else if (rawValue instanceof String) {
                result = Boolean.parseBoolean((String) rawValue);
            }
            return Optional.of(result);
        }

        @Override
        public Optional<String> toString(Object rawValue) {
            if (rawValue != null) {
                return Optional.of(rawValue.toString());
            }
            return Optional.empty();
        }

        @Override
        public Optional<EObject> toEObject(Object rawValue) {
            if (rawValue instanceof EObject) {
                return Optional.of((EObject) rawValue);
            } else {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Collection<EObject>> toEObjectCollection(Object rawValue) {
            Collection<EObject> result = Collections.emptyList();
            if (rawValue instanceof EObject) {
                result = new ArrayList<>(1);
                result.add((EObject) rawValue);
            } else if (rawValue instanceof Collection) {
                result = new ArrayList<>(((Collection<?>) rawValue).size());
                for (final Object object : (Collection<?>) rawValue) {
                    if (object instanceof EObject) {
                        result.add((EObject) object);
                    }
                }
            } else if (rawValue != null && rawValue.getClass().isArray()) {
                result = new ArrayList<>(((Object[]) rawValue).length);
                for (final Object object : (Object[]) rawValue) {
                    if (object instanceof EObject) {
                        result.add((EObject) object);
                    }
                }
            }
            return Optional.of(result);
        }

    }

    /** The OCL expression discrimant. */
    public static final String OCL_DISCRIMINANT = "ocl:"; //$NON-NLS-1$

    /** The OCL object. */
    private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;

    /** The OCL helper. */
    private OCLHelper<EClassifier, ?, ?, Constraint> helper;

    /** The variables. */
    private final VariableManager variables = new VariableManager();

    private final IConverter converter = new OclConverter();
    
    @Override
    public IConverter getConverter() {
        return converter;
    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        // Nothing to do
    }

    @Override
    public void addImport(final String dependency) {
        // ignore
    }

    @Override
    public void clearImports() {
        // ignore
    }

    @Override
    public void setVariable(final String name, final Object value) {
        this.variables.setVariable(name, value);
    }

    @Override
    public void unSetVariable(final String name) {
        this.variables.unSetVariable(name);
    }

    @Override
    public void clearVariables() {
        this.variables.clearVariables();
    }

    @Override
    public Object getVariable(final String name) {
        return this.variables.getVariable(name);
    }

    @Override
    public Map<String, Object> getVariables() {
        return this.variables.getVariables();
    }

    @Override
    public boolean provides(final String expression) {
        return expression != null && expression.startsWith(OclInterpreter.OCL_DISCRIMINANT);
    }

    @Override
    public void setProperty(final Object key, final Object value) {
        // ignore.
    }

    @Override
    public Object evaluate(final EObject target, final String expression) throws EvaluationException {
        return internalEvaluate(target, expression);
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
            if ("".equals(exp)) { //$NON-NLS-1$
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
            ocl = safeCreateOCL(EcoreEnvironmentFactory.INSTANCE);
        }
        return ocl;
    }
    
    @SuppressWarnings("unchecked")
    private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> safeCreateOCL(EnvironmentFactory<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> environmentFactory) {
        Method newInstanceMethod = null;
        try {
            newInstanceMethod = OCL.class.getDeclaredMethod("newInstance", new Class<?>[] { EnvironmentFactory.class }); //$NON-NLS-1$
        } catch (NoSuchMethodException nsme) {
            try {
                newInstanceMethod = OCL.class.getDeclaredMethod("newInstanceAbstract", new Class<?>[] { EnvironmentFactory.class }); //$NON-NLS-1$
            } catch (NoSuchMethodException nsme2) {
                // No more alternatives
            }
        } catch (SecurityException | IllegalArgumentException e) {
            throw new RuntimeException("No valid static factory method found for OCL", e); //$NON-NLS-1$
        }

        if (newInstanceMethod != null && Modifier.isStatic(newInstanceMethod.getModifiers())) {
            try {
                var oclInstance = newInstanceMethod.invoke(null, new Object[] { environmentFactory });
                if (oclInstance instanceof OCL) {
                    return (OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>) oclInstance;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

            }
        }
        throw new RuntimeException("No valid static factory method found for OCL"); //$NON-NLS-1$
    }

    @Override
    public IInterpreter createInterpreter() {
        return new OclInterpreter();
    }

    @Override
    public void dispose() {
        this.variables.clearVariables();
    }

    @Override
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        // ignore
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        return OclCompletionEntry.computeCompletionEntry(context);
    }

    @Override
    public String getPrefix() {
        return OclInterpreter.OCL_DISCRIMINANT;
    }

    @Override
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(OclInterpreter.OCL_DISCRIMINANT, OclInterpreter.OCL_DISCRIMINANT, Messages.OclInterpreter_NewOclExpression);
    }

    @Override
    public String getVariablePrefix() {
        return null; // no prefix for variables
    }

    @Override
    public void setCrossReferencer(final ECrossReferenceAdapter crossReferencer) {
        // no handling (AFAIK) of cross references for OCL
    }

    @Override
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
                if (choices != null && !choices.isEmpty()) {
                    final List<ContentProposal> contentProposals = new ArrayList<>();
                    for (Choice choice : choices) {
                        contentProposals.add(new ContentProposal(choice.getName(), choice.getName() + " (" + choice.getKind().name() + ")", choice.getDescription())); //$NON-NLS-1$//$NON-NLS-2$
                    }
                    Collections.sort(contentProposals);
                    return contentProposals;
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<String> getImports() {
        return Collections.emptyList();
    }

    @Override
    public void removeImport(String dependency) {
        // empty
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return new LinkedHashSet<>();
    }

    @Override
    public boolean supportsValidation() {
        return false;
    }
}
