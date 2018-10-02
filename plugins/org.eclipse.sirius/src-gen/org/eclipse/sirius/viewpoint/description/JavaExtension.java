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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Java Extension</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This class allows to import a Java Class that is used as an Acceleo Service. All acceleo
 * expressions can use the services. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.JavaExtension#getQualifiedClassName <em>Qualified Class
 * Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getJavaExtension()
 * @model
 * @generated
 */
public interface JavaExtension extends EObject {
    /**
     * Returns the value of the '<em><b>Qualified Class Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The name of the class to import. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Qualified Class Name</em>' attribute.
     * @see #setQualifiedClassName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getJavaExtension_QualifiedClassName()
     * @model required="true"
     * @generated
     */
    String getQualifiedClassName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.JavaExtension#getQualifiedClassName
     * <em>Qualified Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Qualified Class Name</em>' attribute.
     * @see #getQualifiedClassName()
     * @generated
     */
    void setQualifiedClassName(String value);

} // JavaExtension
