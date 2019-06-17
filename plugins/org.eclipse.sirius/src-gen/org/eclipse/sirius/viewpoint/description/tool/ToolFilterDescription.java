/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Filter Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> filter to hide a tool in UI based on preconditon evaluated when specified elements to listen
 * are modified <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getPrecondition <em>Precondition</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getElementsToListen <em>Elements To
 * Listen</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getListeners <em>Listeners</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolFilterDescription()
 * @model
 * @generated
 */
public interface ToolFilterDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The precondition of the filter. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition</em>' attribute.
     * @see #setPrecondition(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolFilterDescription_Precondition()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getPrecondition();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getPrecondition
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition</em>' attribute.
     * @see #getPrecondition()
     * @generated
     */
    void setPrecondition(String value);

    /**
     * Returns the value of the '<em><b>Elements To Listen</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The elements to listen by the filter. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Elements To Listen</em>' attribute.
     * @see #setElementsToListen(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolFilterDescription_ElementsToListen()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a collection.'"
     * @generated
     */
    String getElementsToListen();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getElementsToListen <em>Elements To
     * Listen</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Elements To Listen</em>' attribute.
     * @see #getElementsToListen()
     * @generated
     */
    void setElementsToListen(String value);

    /**
     * Returns the value of the '<em><b>Listeners</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Listeners</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Listeners</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getToolFilterDescription_Listeners()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EList<FeatureChangeListener> getListeners();

} // ToolFilterDescription
