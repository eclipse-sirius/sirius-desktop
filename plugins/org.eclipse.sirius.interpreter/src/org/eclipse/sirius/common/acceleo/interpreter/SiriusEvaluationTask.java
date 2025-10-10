/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.acceleo.interpreter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.language.EvaluationContext;
import org.eclipse.acceleo.ui.interpreter.language.EvaluationResult;
import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IEvaluationResult;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * This task delegates to the viewpoint's CompoundInterpreter for expression evaluations.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SiriusEvaluationTask implements Callable<EvaluationResult> {

    /** Context of this evaluation as passed from the interpreter. */
    private EvaluationContext context;

    /**
     * Instantiates our evaluation task given the current evaluation context.
     * 
     * @param context
     *            The current interpreter evaluation context.
     */
    public SiriusEvaluationTask(EvaluationContext context) {
        this.context = context;
    }

    @Override
    public EvaluationResult call() throws Exception {
        checkCancelled();

        final String expression = context.getExpression();
        if (context.getTargetEObjects().isEmpty()) {
            IStatus errorStatus = new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, InterpreterMessages.getString("SiriusEvaluationTask_status_noEvaluationTarget", expression)); //$NON-NLS-1$
            return new EvaluationResult(errorStatus);
        }

        // Only consider the first of the selected EObjects' list
        final EObject target = context.getTargetEObjects().get(0);
        EObject semanticTarget = target;
        if (target instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) target).getTarget();
        }
        Session curSession = SessionManager.INSTANCE.getSession(semanticTarget);

        final IInterpreter vpInterpreter;
        if (curSession != null) {
            vpInterpreter = curSession.getInterpreter();
        } else {
            vpInterpreter = CompoundInterpreter.INSTANCE.getInterpreterForExpression(expression);

            /*
             * we can't rely on the session initializing the interpreter with the accessible EPackages as we are in a
             * case where there is no session. As such we at least make sure the current EPackage of the current
             * selection is known by the interpreter.
             */
            Collection<MetamodelDescriptor> mmDescriptors = new LinkedHashSet<>();

            final Set<EPackage> knownEPackages = collectEPackagesToRegister(target);
            for (EPackage ePackage : knownEPackages) {
                mmDescriptors.add(new EcoreMetamodelDescriptor(ePackage));
            }
            vpInterpreter.activateMetamodels(mmDescriptors);

        }

        assert vpInterpreter != null;
        checkCancelled();

        for (Variable var : context.getVariables()) {
            vpInterpreter.setVariable(var.getName(), var.getValue());
        }

        EvaluationResult evaluationResult = null;
        try {
            IEvaluationResult result = vpInterpreter.evaluateExpression(target, expression);
            final IStatus status = createResultStatus(result);
            evaluationResult = new EvaluationResult(result.getValue(), status);
        } catch (EvaluationException e) {
            final IStatus status = new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, e.getMessage(), e);
            evaluationResult = new EvaluationResult(status);
        }

        assert evaluationResult != null;

        return evaluationResult;
    }

    private Set<EPackage> collectEPackagesToRegister(final EObject target) {
        final Set<EPackage.Registry> localEPackageRegistries = new LinkedHashSet<>();
        final Set<EPackage> knownEPackages = new LinkedHashSet<>();

        context.getTargetNotifiers().stream().filter(EObject.class::isInstance).map(EObject.class::cast).forEachOrdered(targetEObject -> {
            EPackage ePackage = targetEObject.eClass().getEPackage();
            if (ePackage != null && ePackage.getNsURI() != null) {
                knownEPackages.add(ePackage);
            }
            Resource targetEObjectResource = targetEObject.eResource();
            if (targetEObjectResource != null && targetEObjectResource.getResourceSet() != null) {
                localEPackageRegistries.add(targetEObjectResource.getResourceSet().getPackageRegistry());
            }
        });
        Set<String> localyRegisteredNsURIs = new LinkedHashSet<>();
        for (EPackage.Registry registry : localEPackageRegistries) {
            localyRegisteredNsURIs.addAll(registry.keySet());
            for (String nsURI : registry.keySet()) {
                /*
                 * we get the instance by using Map.get() instead of getEPackage() in order to avoid resolving EPackages
                 * which would be registered but not used yet.
                 */
                Object value = registry.get(nsURI);
                if (value instanceof EPackage) {
                    knownEPackages.add((EPackage) value);
                }
            }
        }

        for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
            if (localyRegisteredNsURIs.isEmpty() || !localyRegisteredNsURIs.contains(nsURI)) {
                Object value = EPackage.Registry.INSTANCE.get(nsURI);
                if (value instanceof EPackage) {
                    knownEPackages.add((EPackage) value);
                }
            }
        }
        return knownEPackages;
    }

    /**
     * This will create the status that allows the interpreter to display the type and size of the result object for
     * successful evaluations.
     * 
     * @param result
     *            The result of the evaluation.
     * @return Status that can be displayed by the interpreter for this evaluation.
     */
    private IStatus createResultStatus(Object result) {
        IStatus status = new Status(IStatus.OK, InterpreterPlugin.PLUGIN_ID, ""); //$NON-NLS-1$
        if (result instanceof IEvaluationResult) {
            IEvaluationResult evaluationResult = (IEvaluationResult) result;
            status = this.getStatus(evaluationResult);
        } else if (result != null) {
            // Fallback to the original behavior if we are not using an
            // IEvaluationResult but a regular object
            String message = this.getDefaultMessage(result);
            status = new Status(IStatus.OK, InterpreterPlugin.PLUGIN_ID, message);
        }
        return status;
    }

    /**
     * Computes the status representing the given evaluation result.
     * 
     * @param evaluationResult
     *            The evaluation result
     * @return The status to be displayed to the end user
     */
    private IStatus getStatus(IEvaluationResult evaluationResult) {
        Object result = evaluationResult.getValue();

        String message = this.getDefaultMessage(result);

        Diagnostic diagnostic = evaluationResult.getDiagnostic();
        int statusCode = BasicDiagnostic.toIStatus(diagnostic).getSeverity();

        IStatus status = new Status(statusCode, InterpreterPlugin.PLUGIN_ID, message);
        if (Diagnostic.OK != diagnostic.getSeverity() && !diagnostic.getChildren().isEmpty()) {
            MultiStatus multiStatus = new MultiStatus(InterpreterPlugin.PLUGIN_ID, statusCode, message, null);

            List<Diagnostic> children = diagnostic.getChildren();
            for (Diagnostic childDiagnostic : children) {
                int childStatusCode = BasicDiagnostic.toIStatus(childDiagnostic).getSeverity();
                String childMessage = childDiagnostic.getMessage();
                IStatus childStatus = new Status(childStatusCode, InterpreterPlugin.PLUGIN_ID, childMessage);
                multiStatus.add(childStatus);
            }

            status = multiStatus;
        }

        return status;
    }

    /**
     * Computes the default message to display in the interpreter view.
     * 
     * @param result
     *            The result of the evaluation
     * @return A message to be displayed in the interpreter
     */
    private String getDefaultMessage(Object result) {
        String type = "null"; //$NON-NLS-1$

        if (result != null) {
            type = result.getClass().getSimpleName();
        }

        String size = null;
        if (result instanceof String) {
            size = String.valueOf(((String) result).length());
        } else if (result instanceof Collection<?>) {
            size = String.valueOf(((Collection<?>) result).size());

            // Convert the type of collection displayed (List -> Sequence, Set
            // -> Set)
            if (result instanceof List<?>) {
                type = "Sequence"; //$NON-NLS-1$
            } else if (result instanceof Set<?>) {
                type = "Set"; //$NON-NLS-1$
            }
        } else if (result instanceof EObject) {
            // Improve the type displayed if we have an EObject
            EObject eObject = (EObject) result;
            EClass eClass = eObject.eClass();
            EPackage ePackage = eClass.getEPackage();
            type = ePackage.getName() + "::" + eClass.getName(); //$NON-NLS-1$
        }

        String message;
        if (size == null) {
            message = InterpreterMessages.getString("SiriusEvaluationTask_status_resultMessage", type); //$NON-NLS-1$
        } else {
            message = InterpreterMessages.getString("SiriusEvaluationTask_status_sizedResultMessage", type, size); //$NON-NLS-1$
        }
        return message;
    }

    /**
     * Throws a new {@link CancellationException} if the current thread has been cancelled.
     */
    private void checkCancelled() {
        if (Thread.currentThread().isInterrupted()) {
            throw new CancellationException();
        }
    }
}
