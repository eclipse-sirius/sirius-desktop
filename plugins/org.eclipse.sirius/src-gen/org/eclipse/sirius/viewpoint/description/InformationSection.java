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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Information Section</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getTitle
 * <em> Title</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getTargetClass
 * <em>Target Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getMessage
 * <em>Message</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInformationSection()
 * @model abstract="true"
 * @generated
 */
public interface InformationSection extends EObject {
    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute. The default
     * value is <code>"Section"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInformationSection_Title()
     * @model default="Section"
     * @generated
     */
    String getTitle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getTitle
     * <em>Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
    void setTitle(String value);

    /**
     * Returns the value of the '<em><b>Target Class</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Class</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Class</em>' reference.
     * @see #setTargetClass(EClass)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInformationSection_TargetClass()
     * @model required="true"
     * @generated
     */
    EClass getTargetClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getTargetClass
     * <em>Target Class</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Class</em>' reference.
     * @see #getTargetClass()
     * @generated
     */
    void setTargetClass(EClass value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. The default
     * value is <code>"Information provided about the <%name%> element."</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInformationSection_Message()
     * @model default="Information provided about the <%name%> element."
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.InformationSection#getMessage
     * <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String getTitle(EObject eObj);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String getContent(EObject eObj);

} // InformationSection
