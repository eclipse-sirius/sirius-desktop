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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Representation Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#getTitleExpression <em>Title
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isInitialisation
 * <em>Initialisation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#getMetamodel <em>Metamodel</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isShowOnStartup <em>Show On
 * Startup</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationDescription()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface RepresentationDescription extends DocumentedElement, EndUserDocumentedElement, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Title Expression</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The default title of the representation. (new +
     * name if empty) <!-- end-model-doc -->
     *
     * @return the value of the '<em>Title Expression</em>' attribute.
     * @see #setTitleExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationDescription_TitleExpression()
     * @model default="" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     * @generated
     */
    String getTitleExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#getTitleExpression <em>Title
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title Expression</em>' attribute.
     * @see #getTitleExpression()
     * @generated
     */
    void setTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Initialisation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> Set to true if your want your representation to be automatically created when
     * initializing a new session. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initialisation</em>' attribute.
     * @see #setInitialisation(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationDescription_Initialisation()
     * @model required="true"
     * @generated
     */
    boolean isInitialisation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isInitialisation
     * <em>Initialisation</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initialisation</em>' attribute.
     * @see #isInitialisation()
     * @generated
     */
    void setInitialisation(boolean value);

    /**
     * Returns the value of the '<em><b>Metamodel</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EPackage}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * You might use this reference to statically bind your representation with a set of Ecore packages. Keep in mind
     * that this is not mandatory. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Metamodel</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationDescription_Metamodel()
     * @model
     * @generated
     */
    EList<EPackage> getMetamodel();

    /**
     * Returns the value of the '<em><b>Show On Startup</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Show On Startup</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     *
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return the value of the '<em>Show On Startup</em>' attribute.
     * @see #setShowOnStartup(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationDescription_ShowOnStartup()
     * @model
     * @generated
     */
    boolean isShowOnStartup();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isShowOnStartup
     * <em>Show On Startup</em>}' attribute. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @param value
     *            the new value of the '<em>Show On Startup</em>' attribute.
     * @see #isShowOnStartup()
     * @generated
     */
    void setShowOnStartup(boolean value);

} // RepresentationDescription
