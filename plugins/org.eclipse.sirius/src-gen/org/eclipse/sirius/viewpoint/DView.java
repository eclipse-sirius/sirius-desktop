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
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DView</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> An view is the root element <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DView#getViewpoint <em>Viewpoint</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DView#getOwnedRepresentationDescriptors <em>Owned Representation
 * Descriptors</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DView#getOwnedExtensions <em>Owned Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DView#getModels <em>Models</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDView()
 * @model
 * @generated
 */
public interface DView extends IdentifiedElement, DRefreshable {
    /**
     * Returns the value of the '<em><b>Viewpoint</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The viewpoint that is used for this view <!-- end-model-doc -->
     *
     * @return the value of the '<em>Viewpoint</em>' reference.
     * @see #setViewpoint(Viewpoint)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDView_Viewpoint()
     * @model required="true"
     * @generated
     */
    Viewpoint getViewpoint();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DView#getViewpoint <em>Viewpoint</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Viewpoint</em>' reference.
     * @see #getViewpoint()
     * @generated
     */
    void setViewpoint(Viewpoint value);

    /**
     * Returns the value of the ' <em><b>Owned Representation Descriptors</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor}. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The representation descriptors that are owned by this DView . <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Owned Representation Descriptors</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDView_OwnedRepresentationDescriptors()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DRepresentationDescriptor> getOwnedRepresentationDescriptors();

    /**
     * Returns the value of the '<em><b>Owned Extensions</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The Meta Model extension for this analysis. It may be null. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Owned Extensions</em>' containment reference.
     * @see #setOwnedExtensions(MetaModelExtension)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDView_OwnedExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    MetaModelExtension getOwnedExtensions();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DView#getOwnedExtensions <em>Owned Extensions</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Extensions</em>' containment reference.
     * @see #getOwnedExtensions()
     * @generated
     */
    void setOwnedExtensions(MetaModelExtension value);

    /**
     * Returns the value of the '<em><b>Models</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Models</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Models</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDView_Models()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<EObject> getModels();

} // DView
