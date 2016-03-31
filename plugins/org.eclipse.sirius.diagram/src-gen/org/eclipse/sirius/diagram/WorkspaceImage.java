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

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Workspace Image</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A custom image that is present in the user
 * workspace. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath
 * <em>Workspace Path</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getWorkspaceImage()
 * @model
 * @generated
 */
public interface WorkspaceImage extends NodeStyle, ContainerStyle {
    /**
     * Returns the value of the '<em><b>Workspace Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * path of the image to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Workspace Path</em>' attribute.
     * @see #setWorkspacePath(String)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getWorkspaceImage_WorkspacePath()
     * @model required="true"
     * @generated
     */
    String getWorkspacePath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Workspace Path</em>' attribute.
     * @see #getWorkspacePath()
     * @generated
     */
    void setWorkspacePath(String value);

} // WorkspaceImage
