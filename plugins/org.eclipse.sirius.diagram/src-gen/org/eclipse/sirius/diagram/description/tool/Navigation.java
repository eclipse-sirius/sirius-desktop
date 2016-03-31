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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Navigation</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Open or create a representation for the element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.Navigation#isCreateIfNotExistent
 * <em>Create If Not Existent</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.Navigation#getDiagramDescription
 * <em>Diagram Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNavigation()
 * @model
 * @generated
 */
public interface Navigation extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Create If Not Existent</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create If Not Existent</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create If Not Existent</em>' attribute.
     * @see #setCreateIfNotExistent(boolean)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNavigation_CreateIfNotExistent()
     * @model
     * @generated
     */
    boolean isCreateIfNotExistent();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.Navigation#isCreateIfNotExistent
     * <em>Create If Not Existent</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Create If Not Existent</em>'
     *            attribute.
     * @see #isCreateIfNotExistent()
     * @generated
     */
    void setCreateIfNotExistent(boolean value);

    /**
     * Returns the value of the '<em><b>Diagram Description</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagram Description</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Diagram Description</em>' reference.
     * @see #setDiagramDescription(DiagramDescription)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNavigation_DiagramDescription()
     * @model required="true"
     * @generated
     */
    DiagramDescription getDiagramDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.Navigation#getDiagramDescription
     * <em>Diagram Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Diagram Description</em>' reference.
     * @see #getDiagramDescription()
     * @generated
     */
    void setDiagramDescription(DiagramDescription value);

} // Navigation
