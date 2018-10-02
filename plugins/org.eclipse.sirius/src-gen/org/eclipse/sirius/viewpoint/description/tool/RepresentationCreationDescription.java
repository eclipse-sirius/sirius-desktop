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
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Representation Creation Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getTitleExpression
 * <em>Title Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationDescription
 * <em>Representation Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getContainerViewVariable
 * <em>Container View Variable</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationNameVariable
 * <em>Representation Name Variable</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription()
 * @model abstract="true"
 * @generated
 */
public interface RepresentationCreationDescription extends AbstractToolDescription {
    /**
     * Returns the value of the '<em><b>Title Expression</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The default title of the representation to
     * create. (new + name if empty) <!-- end-model-doc -->
     *
     * @return the value of the '<em>Title Expression</em>' attribute.
     * @see #setTitleExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_TitleExpression()
     * @model default="" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     * @generated
     */
    String getTitleExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getTitleExpression
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title Expression</em>' attribute.
     * @see #getTitleExpression()
     * @generated
     */
    void setTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Browse Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> You might put here an expression to browse the semantic model to get to a new place
     * before creating the representation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Browse Expression</em>' attribute.
     * @see #setBrowseExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_BrowseExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getBrowseExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getBrowseExpression
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Browse Expression</em>' attribute.
     * @see #getBrowseExpression()
     * @generated
     */
    void setBrowseExpression(String value);

    /**
     * Returns the value of the '<em><b>Representation Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Representation Description</em>' reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Representation Description</em>' reference.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_RepresentationDescription()
     * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    RepresentationDescription getRepresentationDescription();

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_InitialOperation()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Container View Variable</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The variable containerView that represents the clickedView
     * (instance of ViewPoint or ViewPointElement). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Container View Variable</em>' containment reference.
     * @see #setContainerViewVariable(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_ContainerViewVariable()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='containerView'"
     * @generated
     */
    ContainerViewVariable getContainerViewVariable();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container View Variable</em>' containment reference.
     * @see #getContainerViewVariable()
     * @generated
     */
    void setContainerViewVariable(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Representation Name Variable</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The variable representationName that represents
     * the name of the created representation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Representation Name Variable</em>' containment reference.
     * @see #setRepresentationNameVariable(NameVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationCreationDescription_RepresentationNameVariable()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='representationName'"
     * @generated
     */
    NameVariable getRepresentationNameVariable();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationNameVariable
     * <em>Representation Name Variable</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Representation Name Variable</em>' containment reference.
     * @see #getRepresentationNameVariable()
     * @generated
     */
    void setRepresentationNameVariable(NameVariable value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation" required="true"
     * @generated
     */
    EList<RepresentationElementMapping> getMappings();

} // RepresentationCreationDescription
