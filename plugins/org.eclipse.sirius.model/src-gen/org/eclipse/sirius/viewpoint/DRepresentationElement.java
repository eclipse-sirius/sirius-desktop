/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DRepresentation Element</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationElement#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationElement#getSemanticElements <em>Semantic Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationElement()
 * @model abstract="true"
 * @generated
 */
public interface DRepresentationElement extends IdentifiedElement, DMappingBased, DStylizable, DRefreshable, DSemanticDecorator {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The name of the element. It is the name that is
     * displayed on the diagram. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationElement_Name()
     * @model default=""
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationElement#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Semantic Elements</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject} . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic elements to show that represents this view point element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Semantic Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationElement_SemanticElements()
     * @model
     * @generated
     */
    EList<EObject> getSemanticElements();

} // DRepresentationElement
