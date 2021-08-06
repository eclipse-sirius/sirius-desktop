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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Representation Navigation Description</b></em>
 * '. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getNavigationNameExpression
 * <em>Navigation Name Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationDescription
 * <em>Representation Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerViewVariable
 * <em>Container View Variable</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerVariable
 * <em>Container Variable</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable
 * <em>Representation Name Variable</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription()
 * @model abstract="true"
 * @generated
 */
public interface RepresentationNavigationDescription extends AbstractToolDescription {
    /**
     * Returns the value of the '<em><b>Browse Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> Expression returning the navigation target. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Browse Expression</em>' attribute.
     * @see #setBrowseExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_BrowseExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getBrowseExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getBrowseExpression
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Browse Expression</em>' attribute.
     * @see #getBrowseExpression()
     * @generated
     */
    void setBrowseExpression(String value);

    /**
     * Returns the value of the '<em><b>Navigation Name Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Navigation Name Expression</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Navigation Name Expression</em>' attribute.
     * @see #setNavigationNameExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_NavigationNameExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables name='name of the targeted
     *        Representation.'"
     * @generated
     */
    String getNavigationNameExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getNavigationNameExpression
     * <em>Navigation Name Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Navigation Name Expression</em>' attribute.
     * @see #getNavigationNameExpression()
     * @generated
     */
    void setNavigationNameExpression(String value);

    /**
     * Returns the value of the '<em><b>Representation Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Representation Description</em>' reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Representation Description</em>' reference.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_RepresentationDescription()
     * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    RepresentationDescription getRepresentationDescription();

    /**
     * Returns the value of the '<em><b>Container View Variable</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The variable containerView that represents the clickedView
     * (instance of ViewPoint or ViewPointElement). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Container View Variable</em>' containment reference.
     * @see #setContainerViewVariable(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_ContainerViewVariable()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='containerView'"
     * @generated
     */
    ContainerViewVariable getContainerViewVariable();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container View Variable</em>' containment reference.
     * @see #getContainerViewVariable()
     * @generated
     */
    void setContainerViewVariable(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Container Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The variable container that represents the semantic element of the
     * clicked view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Container Variable</em>' containment reference.
     * @see #setContainerVariable(ElementSelectVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_ContainerVariable()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='container'"
     * @generated
     */
    ElementSelectVariable getContainerVariable();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerVariable
     * <em>Container Variable</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container Variable</em>' containment reference.
     * @see #getContainerVariable()
     * @generated
     */
    void setContainerVariable(ElementSelectVariable value);

    /**
     * Returns the value of the '<em><b>Representation Name Variable</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The variable representationName that represents
     * the name of the representation to open. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Representation Name Variable</em>' containment reference.
     * @see #setRepresentationNameVariable(NameVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getRepresentationNavigationDescription_RepresentationNameVariable()
     * @model containment="true" resolveProxies="true" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='representationName'"
     * @generated
     */
    NameVariable getRepresentationNameVariable();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable
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

} // RepresentationNavigationDescription
