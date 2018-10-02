/**
 *  Copyright (c) 2018 Obeo.
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  Contributors:
 *     Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.workflow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Section Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.workflow.SectionDescription#getTitleExpression <em>Title Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.SectionDescription#getImagePath <em>Image Path</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.SectionDescription#getDescriptionExpression <em>Description
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.SectionDescription#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.workflow.WorkflowPackage#getSectionDescription()
 * @model
 * @generated
 */
public interface SectionDescription extends IdentifiedElement, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Title Expression</em>' attribute.
     * @see #setTitleExpression(String)
     * @see org.eclipse.sirius.workflow.WorkflowPackage#getSectionDescription_TitleExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getTitleExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.workflow.SectionDescription#getTitleExpression <em>Title
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title Expression</em>' attribute.
     * @see #getTitleExpression()
     * @generated
     */
    void setTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Image Path</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Path</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Image Path</em>' attribute.
     * @see #setImagePath(String)
     * @see org.eclipse.sirius.workflow.WorkflowPackage#getSectionDescription_ImagePath()
     * @model dataType="org.eclipse.sirius.viewpoint.description.ImagePath"
     * @generated
     */
    String getImagePath();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.workflow.SectionDescription#getImagePath <em>Image Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Image Path</em>' attribute.
     * @see #getImagePath()
     * @generated
     */
    void setImagePath(String value);

    /**
     * Returns the value of the '<em><b>Description Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description Expression</em>' attribute.
     * @see #setDescriptionExpression(String)
     * @see org.eclipse.sirius.workflow.WorkflowPackage#getSectionDescription_DescriptionExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getDescriptionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.workflow.SectionDescription#getDescriptionExpression
     * <em>Description Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description Expression</em>' attribute.
     * @see #getDescriptionExpression()
     * @generated
     */
    void setDescriptionExpression(String value);

    /**
     * Returns the value of the '<em><b>Activities</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.workflow.ActivityDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activities</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Activities</em>' containment reference list.
     * @see org.eclipse.sirius.workflow.WorkflowPackage#getSectionDescription_Activities()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ActivityDescription> getActivities();

} // SectionDescription
