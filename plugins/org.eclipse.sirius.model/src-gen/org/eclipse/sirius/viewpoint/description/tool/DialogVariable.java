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

import org.eclipse.sirius.viewpoint.description.AbstractVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Dialog Variable</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.DialogVariable#getDialogPrompt <em>Dialog Prompt</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDialogVariable()
 * @model abstract="true"
 * @generated
 */
public interface DialogVariable extends AbstractVariable {
    /**
     * Returns the value of the '<em><b>Dialog Prompt</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dialog Prompt</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Dialog Prompt</em>' attribute.
     * @see #setDialogPrompt(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getDialogVariable_DialogPrompt()
     * @model
     * @generated
     */
    String getDialogPrompt();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.DialogVariable#getDialogPrompt
     * <em>Dialog Prompt</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Dialog Prompt</em>' attribute.
     * @see #getDialogPrompt()
     * @generated
     */
    void setDialogPrompt(String value);

} // DialogVariable
