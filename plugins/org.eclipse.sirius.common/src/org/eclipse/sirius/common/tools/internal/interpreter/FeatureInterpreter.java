/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.InterpreterStatusFactory;

/**
 * A specialized interpreter which only supports direct access to a named
 * feature (or pseudo-feature) of the context element.
 * 
 * @author pcdavid
 */
public class FeatureInterpreter extends AbstractInterpreter implements org.eclipse.sirius.common.tools.api.interpreter.IInterpreter, IInterpreterProvider {

    /** The Feature interpreter prefix. */
    public static final String PREFIX = "feature:";

    /** The eContainer feature name. */
    public static final String E_CONTAINER_FEATURE_NAME = "eContainer";

    /** The eContents feature name. */
    public static final String E_CONTENTS_FEATURE_NAME = "eContents";

    /** The eAllContents feature name. */
    public static final String E_ALL_CONTENTS_FEATURE_NAME = "eAllContents";

    /** The eClass feature name. */
    public static final String E_CLASS_FEATURE_NAME = "eClass";

    /** The eReferences feature name. */
    public static final String E_REFERENCES_FEATURE_NAME = "eReferences";

    /** The default feature names. */
    public static final String[] DEFAULT_FEATURE_NAMES = { E_CONTAINER_FEATURE_NAME, E_CONTENTS_FEATURE_NAME, E_ALL_CONTENTS_FEATURE_NAME, E_CLASS_FEATURE_NAME, E_REFERENCES_FEATURE_NAME };

    /**
     * {@inheritDoc}
     */
    public IInterpreter createInterpreter() {
        return new FeatureInterpreter();
    }

    /**
     * {@inheritDoc}
     */
    public String getPrefix() {
        return PREFIX;
    }

    /**
     * {@inheritDoc}
     */
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        Object result = null;
        if (target != null && expression != null && expression.startsWith(PREFIX)) {
            String featureName = expression.trim().substring(PREFIX.length());
            if (E_CONTAINER_FEATURE_NAME.equals(featureName)) {
                result = target.eContainer();
            } else if (E_CONTENTS_FEATURE_NAME.equals(featureName)) {
                result = target.eContents();
            } else if (E_ALL_CONTENTS_FEATURE_NAME.equals(featureName)) {
                result = target.eAllContents();
            } else if (E_CLASS_FEATURE_NAME.equals(featureName)) {
                result = target.eClass();
            } else if (E_REFERENCES_FEATURE_NAME.equals(featureName)) {
                result = target.eCrossReferences();
            } else {
                EStructuralFeature feature = target.eClass().getEStructuralFeature(featureName);
                if (feature != null) {
                    result = target.eGet(feature);
                } else {
                    throw new EvaluationException("Unknown feature name " + featureName + ".");
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsValidation() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        Collection<IInterpreterStatus> interpreterStatus = new ArrayList<IInterpreterStatus>();
        if (expression != null && context != null && expression.startsWith(PREFIX)) {
            String featureName = expression.substring(PREFIX.length());
            EClass currentElementType = getCurrentElementType(context);
            if (currentElementType != null && !(hasFeatureName(currentElementType, featureName) || isDefaultFeatureName(featureName))) {
                interpreterStatus.add(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, "The current element " + currentElementType.getEPackage().getName() + "::"
                        + currentElementType.getName() + " does not have the feature named : " + featureName));
            }
        }
        return interpreterStatus;
    }

    private boolean hasFeatureName(EClass currentElementType, String featureName) {
        boolean hasFeatureName = currentElementType.getEStructuralFeature(featureName) != null;
        return hasFeatureName;
    }

    private boolean isDefaultFeatureName(String featureName) {
        boolean isDefaultFeatureName = false;
        for (String defaultFeatureName : DEFAULT_FEATURE_NAMES) {
            if (defaultFeatureName.equals(featureName)) {
                isDefaultFeatureName = true;
                break;
            }
        }
        return isDefaultFeatureName;
    }

    /**
     * Get the type of the current element from the specified
     * {@link IInterpreterContext}.
     * 
     * @param interpreterContext
     *            the specified {@link IInterpreterContext}
     * @return the type of the current element
     */
    public EClass getCurrentElementType(IInterpreterContext interpreterContext) {
        EClass currentElementType = null;
        Collection<String> targetTypes = interpreterContext.getTargetTypes();
        if (!targetTypes.isEmpty()) {
            String targetType = targetTypes.iterator().next();
            Collection<EPackage> availableEPackages = interpreterContext.getAvailableEPackages();
            if (targetType.contains(SEPARATOR)) {
                // If the current targetType has a EPackage prefix then look for
                // the corresponding EPackage
                for (EPackage availableEPackage : availableEPackages) {
                    if (availableEPackage.getName() != null && targetType.startsWith(availableEPackage.getName() + SEPARATOR)) {
                        String eClassName = targetType.substring(targetType.indexOf(SEPARATOR) + 1);
                        EClassifier eClassifier = availableEPackage.getEClassifier(eClassName);
                        if (eClassifier instanceof EClass) {
                            currentElementType = (EClass) eClassifier;
                            break;
                        }
                    }
                }
            } else {
                // Else look for in all EPackages
                for (EPackage availableEPackage : availableEPackages) {
                    EClassifier eClassifier = availableEPackage.getEClassifier(targetType);
                    if (eClassifier instanceof EClass) {
                        currentElementType = (EClass) eClassifier;
                        break;
                    }
                }
            }
        }
        return currentElementType;
    }
}
