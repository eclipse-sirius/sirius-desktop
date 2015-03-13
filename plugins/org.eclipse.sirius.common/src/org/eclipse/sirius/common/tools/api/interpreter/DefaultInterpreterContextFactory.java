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
import org.eclipse.sirius.common.tools.internal.interpreter.InterpretedContextImpl;

/**
 * Allows to create {@link IInterpreterContext}s.
 * 
 * @since 0.9.0
 * @author alagarde
 */
public final class DefaultInterpreterContextFactory {

    private DefaultInterpreterContextFactory() {

    }

    /**
     * Creates the {@link IInterpreterContext} allowing to validate the
     * InterpretedExpression stored in the given feature.
     * 
     * @param element
     *            the concerned element
     * @param requiresTargetType
     *            indicates whether this expression requires a targetType for
     *            the "current" element
     * @param feature
     *            the feature containing the expression to evaluate
     * @param targetTypes
     *            targetDomainClasses
     * @param avalaiblePackages
     *            the list of available EPackages
     * @param variables
     *            the defined variables
     * @param dependencies
     *            the list of available dependencies
     * 
     * @return a {@link IInterpreterContext} created from the given informations
     */
    public static IInterpreterContext createInterpreterContext(EObject element, boolean requiresTargetType, EStructuralFeature feature, VariableType targetTypes,
            Collection<EPackage> avalaiblePackages, Map<String, VariableType> variables, Collection<String> dependencies) {
        return new InterpretedContextImpl(element, requiresTargetType, feature, targetTypes, avalaiblePackages, variables, dependencies);
    }

}
