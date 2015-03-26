/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.filter.FilterVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Filter Variable Value</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.FilterVariableValue#getVariableDefinition
 * <em>Variable Definition</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.FilterVariableValue#getModelElement
 * <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getFilterVariableValue()
 * @model
 * @generated
 */
public interface FilterVariableValue extends EObject {
    /**
     * Returns the value of the '<em><b>Variable Definition</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable Definition</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Variable Definition</em>' reference.
     * @see #setVariableDefinition(FilterVariable)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFilterVariableValue_VariableDefinition()
     * @model required="true"
     * @generated
     */
    FilterVariable getVariableDefinition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.FilterVariableValue#getVariableDefinition
     * <em>Variable Definition</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Variable Definition</em>' reference.
     * @see #getVariableDefinition()
     * @generated
     */
    void setVariableDefinition(FilterVariable value);

    /**
     * Returns the value of the '<em><b>Model Element</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Model Element</em>' reference.
     * @see #setModelElement(EObject)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFilterVariableValue_ModelElement()
     * @model required="true"
     * @generated
     */
    EObject getModelElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.FilterVariableValue#getModelElement
     * <em>Model Element</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Model Element</em>' reference.
     * @see #getModelElement()
     * @generated
     */
    void setModelElement(EObject value);

} // FilterVariableValue
