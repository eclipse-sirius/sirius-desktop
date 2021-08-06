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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Selection Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isMultiple <em>Multiple</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isTree <em>Tree</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getRootExpression <em>Root
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getChildrenExpression <em>Children
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SelectionDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Candidates Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Candidates Expression</em>' attribute.
     * @see #setCandidatesExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_CandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the view of the container.' container='ecore.EObject | the semantic element of the container.'"
     * @generated
     */
    String getCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getCandidatesExpression <em>Candidates
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Candidates Expression</em>' attribute.
     * @see #getCandidatesExpression()
     * @generated
     */
    void setCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Multiple</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Multiple</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Multiple</em>' attribute.
     * @see #setMultiple(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_Multiple()
     * @model required="true"
     * @generated
     */
    boolean isMultiple();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isMultiple
     * <em>Multiple</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Multiple</em>' attribute.
     * @see #isMultiple()
     * @generated
     */
    void setMultiple(boolean value);

    /**
     * Returns the value of the '<em><b>Tree</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Set to true if you want a tree representation of the selection candidates. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Tree</em>' attribute.
     * @see #setTree(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_Tree()
     * @model required="true"
     * @generated
     */
    boolean isTree();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isTree
     * <em>Tree</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tree</em>' attribute.
     * @see #isTree()
     * @generated
     */
    void setTree(boolean value);

    /**
     * Returns the value of the '<em><b>Root Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Root Expression</em>' attribute.
     * @see #setRootExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_RootExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the view of the container.' container='ecore.EObject | the semantic element of the container.'"
     * @generated
     */
    String getRootExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getRootExpression
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Root Expression</em>' attribute.
     * @see #getRootExpression()
     * @generated
     */
    void setRootExpression(String value);

    /**
     * Returns the value of the '<em><b>Children Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Children Expression</em>' attribute.
     * @see #setChildrenExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_ChildrenExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        diagram='diagram.DSemanticDiagram | the current DDiagram.' containerView='viewpoint.DSemanticDecorator |
     *        the view of the container.' container='ecore.EObject | the semantic element of the container.'"
     * @generated
     */
    String getChildrenExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getChildrenExpression
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Children Expression</em>' attribute.
     * @see #getChildrenExpression()
     * @generated
     */
    void setChildrenExpression(String value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSelectionDescription_Message()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TranslatableMessage"
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getMessage
     * <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);

} // SelectionDescription
