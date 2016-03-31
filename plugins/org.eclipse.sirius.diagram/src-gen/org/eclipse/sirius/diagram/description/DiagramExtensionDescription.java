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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Extension Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getLayers
 * <em>Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getValidationSet
 * <em>Validation Set</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getConcerns
 * <em>Concerns</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramExtensionDescription()
 * @model
 * @generated
 */
public interface DiagramExtensionDescription extends RepresentationExtensionDescription {
    /**
     * Returns the value of the '<em><b>Layers</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.AdditionalLayer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layers</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layers</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramExtensionDescription_Layers()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<AdditionalLayer> getLayers();

    /**
     * Returns the value of the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The validations rules to add <!-- end-model-doc -->
     *
     * @return the value of the '<em>Validation Set</em>' containment reference.
     * @see #setValidationSet(ValidationSet)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramExtensionDescription_ValidationSet()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ValidationSet getValidationSet();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getValidationSet
     * <em>Validation Set</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Validation Set</em>' containment
     *            reference.
     * @see #getValidationSet()
     * @generated
     */
    void setValidationSet(ValidationSet value);

    /**
     * Returns the value of the '<em><b>Concerns</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> All concerns of the diagrams to add. A concern is a
     * set of filters, validations and behaviors. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Concerns</em>' containment reference.
     * @see #setConcerns(ConcernSet)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramExtensionDescription_Concerns()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ConcernSet getConcerns();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getConcerns
     * <em>Concerns</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Concerns</em>' containment
     *            reference.
     * @see #getConcerns()
     * @generated
     */
    void setConcerns(ConcernSet value);

} // DiagramExtensionDescription
