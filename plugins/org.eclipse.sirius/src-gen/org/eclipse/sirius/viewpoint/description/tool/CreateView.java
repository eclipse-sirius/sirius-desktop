/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Create View</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> This operation allows to create a view. <!--
 * end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getMapping
 * <em>Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getContainerViewExpression
 * <em>Container View Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getVariableName
 * <em>Variable Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateView()
 * @model
 * @generated
 */
public interface CreateView extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Mapping</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mapping</em>' reference.
     * @see #setMapping(DiagramElementMapping)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateView_Mapping()
     * @model required="true"
     * @generated
     */
    DiagramElementMapping getMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getMapping
     * <em>Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Mapping</em>' reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(DiagramElementMapping value);

    /**
     * Returns the value of the '<em><b>Container View Expression</b></em>'
     * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container View Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Container View Expression</em>' attribute.
     * @see #setContainerViewExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateView_ContainerViewExpression()
     * @model default="" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a view (DNode, DEdge, DDiagram -> any DSemanticDecorator).'"
     * @generated
     */
    String getContainerViewExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getContainerViewExpression
     * <em>Container View Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Container View Expression</em>'
     *            attribute.
     * @see #getContainerViewExpression()
     * @generated
     */
    void setContainerViewExpression(String value);

    /**
     * Returns the value of the '<em><b>Variable Name</b></em>' attribute. The
     * default value is <code>"instance"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Once the instance is created, a
     * new variable will be binded with the name given here and will be
     * available to any contained operation. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Variable Name</em>' attribute.
     * @see #setVariableName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateView_VariableName()
     * @model default="instance"
     * @generated
     */
    String getVariableName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateView#getVariableName
     * <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Variable Name</em>' attribute.
     * @see #getVariableName()
     * @generated
     */
    void setVariableName(String value);

} // CreateView
