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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract Tool Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Base class of all tools. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getPrecondition
 * <em>Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isForceRefresh
 * <em>Force Refresh</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getElementsToSelect
 * <em>Elements To Select</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isInverseSelectionOrder
 * <em>Inverse Selection Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractToolDescription extends ToolEntry {
    /**
     * Returns the value of the '<em><b>Precondition</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The precondition of the tool.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition</em>' attribute.
     * @see #setPrecondition(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription_Precondition()
     * @model default="" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation
     *        ="http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables container='ecore.EObject | the container.'"
     * @generated
     */
    String getPrecondition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getPrecondition
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Precondition</em>' attribute.
     * @see #getPrecondition()
     * @generated
     */
    void setPrecondition(String value);

    /**
     * Returns the value of the '<em><b>Force Refresh</b></em>' attribute. The
     * default value is <code>"false"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> If true then a refresh for the
     * whole representation is executed after every execution of the tool. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Force Refresh</em>' attribute.
     * @see #setForceRefresh(boolean)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription_ForceRefresh()
     * @model default="false"
     * @generated
     */
    boolean isForceRefresh();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isForceRefresh
     * <em>Force Refresh</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Force Refresh</em>' attribute.
     * @see #isForceRefresh()
     * @generated
     */
    void setForceRefresh(boolean value);

    /**
     * Returns the value of the '<em><b>Filters</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filters</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Filters</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription_Filters()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolFilterDescription> getFilters();

    /**
     * Returns the value of the '<em><b>Elements To Select</b></em>' attribute.
     * The default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The precondition of the tool.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Elements To Select</em>' attribute.
     * @see #setElementsToSelect(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription_ElementsToSelect()
     * @model default="" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation
     *        ="http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables container='ecore.EObject | the container.'"
     * @generated
     */
    String getElementsToSelect();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getElementsToSelect
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Elements To Select</em>' attribute.
     * @see #getElementsToSelect()
     * @generated
     */
    void setElementsToSelect(String value);

    /**
     * Returns the value of the '<em><b>Inverse Selection Order</b></em>'
     * attribute. The default value is <code>"false"</code>. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Inverse Selection Order</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> By default the elements to
     * select are listed in the creation order. If true, the order is inverted.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Inverse Selection Order</em>' attribute.
     * @see #setInverseSelectionOrder(boolean)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAbstractToolDescription_InverseSelectionOrder()
     * @model default="false" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    boolean isInverseSelectionOrder();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isInverseSelectionOrder
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Inverse Selection Order</em>'
     *            attribute.
     * @see #isInverseSelectionOrder()
     * @generated
     */
    void setInverseSelectionOrder(boolean value);

} // AbstractToolDescription
