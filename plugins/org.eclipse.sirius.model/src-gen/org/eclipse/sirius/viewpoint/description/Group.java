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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Group</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getOwnedViewpoints <em>Owned Viewpoints</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getSystemColorsPalette <em>System Colors Palette</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getUserColorsPalettes <em>User Colors Palettes</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Group#getExtensions <em>Extensions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends DModelElement, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_Name()
     * @model default=""
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Group#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Owned Viewpoints</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.viewpoint.description.Viewpoint}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Viewpoints</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Viewpoints</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_OwnedViewpoints()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<Viewpoint> getOwnedViewpoints();

    /**
     * Returns the value of the '<em><b>System Colors Palette</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>System Colors Palette</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>System Colors Palette</em>' reference.
     * @see #setSystemColorsPalette(SytemColorsPalette)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_SystemColorsPalette()
     * @model required="true" transient="true"
     * @generated
     */
    SytemColorsPalette getSystemColorsPalette();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Group#getSystemColorsPalette <em>System
     * Colors Palette</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>System Colors Palette</em>' reference.
     * @see #getSystemColorsPalette()
     * @generated
     */
    void setSystemColorsPalette(SytemColorsPalette value);

    /**
     * Returns the value of the '<em><b>User Colors Palettes</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.viewpoint.description.UserColorsPalette}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>User Colors Palettes</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>User Colors Palettes</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_UserColorsPalettes()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<UserColorsPalette> getUserColorsPalettes();

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Group#getVersion <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Extensions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.Extension}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getGroup_Extensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Extension> getExtensions();

} // Group
