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
package org.eclipse.sirius;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Source File Link</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A link that references a Source File. <!--
 * end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.DSourceFileLink#getFilePath <em>File Path
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.DSourceFileLink#getStartPosition <em>Start
 * Position</em>}</li>
 * <li>{@link org.eclipse.sirius.DSourceFileLink#getEndPosition <em>End
 * Position</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getDSourceFileLink()
 * @model
 * @generated
 */
public interface DSourceFileLink extends DNavigationLink {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>File Path</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Path</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>File Path</em>' attribute.
     * @see #setFilePath(String)
     * @see org.eclipse.sirius.SiriusPackage#getSourceFileLink_FilePath()
     * @model required="true"
     * @generated
     */
    String getFilePath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DSourceFileLink#getFilePath
     * <em>File Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>File Path</em>' attribute.
     * @see #getFilePath()
     * @generated
     */
    void setFilePath(String value);

    /**
     * Returns the value of the '<em><b>Start Position</b></em>' attribute. The
     * default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Position</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The index of the start
     * portion of code. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Start Position</em>' attribute.
     * @see #setStartPosition(int)
     * @see org.eclipse.sirius.SiriusPackage#getSourceFileLink_StartPosition()
     * @model default="0"
     * @generated
     */
    int getStartPosition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DSourceFileLink#getStartPosition
     * <em>Start Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Start Position</em>' attribute.
     * @see #getStartPosition()
     * @generated
     */
    void setStartPosition(int value);

    /**
     * Returns the value of the '<em><b>End Position</b></em>' attribute. The
     * default value is <code>"1"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Position</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The index of the end
     * portion of code. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>End Position</em>' attribute.
     * @see #setEndPosition(int)
     * @see org.eclipse.sirius.SiriusPackage#getSourceFileLink_EndPosition()
     * @model default="1"
     * @generated
     */
    int getEndPosition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DSourceFileLink#getEndPosition
     * <em>End Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>End Position</em>' attribute.
     * @see #getEndPosition()
     * @generated
     */
    void setEndPosition(int value);

} // DSourceFileLink
