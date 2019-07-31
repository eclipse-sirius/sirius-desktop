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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DModelElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DRepresentation</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedRepresentationElements <em>Owned Representation
 * Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentation#getRepresentationElements <em>Representation Elements</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentation#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedAnnotationEntries <em>Owned Annotation Entries</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentation#getUiState <em>Ui State</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation()
 * @model abstract="true"
 * @generated
 */
public interface DRepresentation extends IdentifiedElement, DModelElement, DRefreshable {
    /**
     * Returns the value of the '<em><b>Owned Representation Elements</b></em>' reference list. The list contents are of
     * type {@link org.eclipse.sirius.viewpoint.DRepresentationElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The directly contained representation elements <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Representation Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_OwnedRepresentationElements()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DRepresentationElement> getOwnedRepresentationElements();

    /**
     * Returns the value of the '<em><b>Representation Elements</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement}. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The directly and indirectly contained representation elements <!-- end-model-doc -->
     *
     * @return the value of the '<em>Representation Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_RepresentationElements()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DRepresentationElement> getRepresentationElements();

    /**
     * Returns the value of the '<em><b>Owned Annotation Entries</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.AnnotationEntry}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Annotation Entries</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Annotation Entries</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_OwnedAnnotationEntries()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<AnnotationEntry> getOwnedAnnotationEntries();

    /**
     * Returns the value of the '<em><b>Ui State</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ui State</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ui State</em>' containment reference.
     * @see #setUiState(UIState)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_UiState()
     * @model containment="true" transient="true"
     * @generated
     */
    UIState getUiState();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentation#getUiState <em>Ui State</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Ui State</em>' containment reference.
     * @see #getUiState()
     * @generated
     */
    void setUiState(UIState value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_Name()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getName();

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Documentation</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentation_Documentation()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getDocumentation();

} // DRepresentation
