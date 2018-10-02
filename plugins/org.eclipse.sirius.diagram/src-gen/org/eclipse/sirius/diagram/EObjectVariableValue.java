/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>EObject Variable Value</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.EObjectVariableValue#getVariableDefinition <em>Variable Definition</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EObjectVariableValue#getModelElement <em>Model Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEObjectVariableValue()
 * @model
 * @generated
 */
public interface EObjectVariableValue extends VariableValue {
    /**
     * Returns the value of the '<em><b>Variable Definition</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable Definition</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Variable Definition</em>' reference.
     * @see #setVariableDefinition(SelectModelElementVariable)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEObjectVariableValue_VariableDefinition()
     * @model required="true"
     * @generated
     */
    SelectModelElementVariable getVariableDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.EObjectVariableValue#getVariableDefinition <em>Variable
     * Definition</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Variable Definition</em>' reference.
     * @see #getVariableDefinition()
     * @generated
     */
    void setVariableDefinition(SelectModelElementVariable value);

    /**
     * Returns the value of the '<em><b>Model Element</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Element</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Model Element</em>' reference.
     * @see #setModelElement(EObject)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEObjectVariableValue_ModelElement()
     * @model required="true"
     * @generated
     */
    EObject getModelElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.EObjectVariableValue#getModelElement <em>Model
     * Element</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Model Element</em>' reference.
     * @see #getModelElement()
     * @generated
     */
    void setModelElement(EObject value);

} // EObjectVariableValue
