/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;

/**
 * Default implementation for of {@link IInterpreterContext}.
 * 
 * @author alagarde
 */
public class InterpretedContextImpl implements IInterpreterContext {

    private EStructuralFeature field;

    private Map<String, VariableType> variables;

    private Collection<EPackage> availablePackages;

    private VariableType targetTypes;

    private final EObject element;

    private final Collection<String> dependencies;

    /**
     * Indicates if the expression need all possibles type that can be held by
     * "current" element to be validated. It can not be true, for example when
     * considering a PopupMenuContribution's precondition, that is only
     * evaluated with variables.
     */
    private boolean requiresTargetType;

    /**
     * Default constructor.
     * 
     * @param element
     *            the concerned element
     * @param requiresTargetType
     *            indicates whether this expression requires a targetType for
     *            the "current" element
     * @param field
     *            the concerned field
     * @param targetTypes
     *            the possible types for the element
     * @param avalaiblePackages
     *            the list of available EPackages
     * @param variables
     *            the defined variables
     * @param dependencies
     *            the list of available dependencies.
     */
    public InterpretedContextImpl(EObject element, boolean requiresTargetType, EStructuralFeature field, VariableType targetTypes, Collection<EPackage> avalaiblePackages,
            Map<String, VariableType> variables, Collection<String> dependencies) {
        this.element = element;
        this.requiresTargetType = requiresTargetType;
        this.targetTypes = targetTypes;
        this.availablePackages = avalaiblePackages;
        this.variables = variables;
        this.field = field;
        this.dependencies = dependencies;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getElement()
     */
    public EObject getElement() {
        return element;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getTargetType()
     */
    public VariableType getTargetType() {
        return targetTypes;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getAvailableEPackages()
     */
    public Collection<EPackage> getAvailableEPackages() {
        return availablePackages;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getVariables()
     */
    public Map<String, VariableType> getVariables() {
        return variables;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getField()
     */
    public EStructuralFeature getField() {
        return field;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#getDependencies()
     */
    public Collection<String> getDependencies() {
        return dependencies;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext#requiresTargetType()
     */
    public boolean requiresTargetType() {
        return requiresTargetType;
    }

    @Override
    public Collection<String> getTargetTypes() {
        Collection<String> typeNames = new LinkedHashSet<>();
        for (TypeName type : getTargetType().getPossibleTypes()) {
            typeNames.add(type.getCompleteName());
        }
        return typeNames;

    }

}
