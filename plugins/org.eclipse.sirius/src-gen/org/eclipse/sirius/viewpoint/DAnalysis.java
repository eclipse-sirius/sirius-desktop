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
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DAnalysis</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getReferencedAnalysis <em>Referenced Analysis</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getSemanticResources <em>Semantic Resources</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getModels <em>Models</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getEAnnotations <em>EAnnotations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedViews <em>Owned Views</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getSelectedViews <em>Selected Views</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedFeatureExtensions <em>Owned Feature Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysis#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis()
 * @model
 * @generated
 */
public interface DAnalysis extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Referenced Analysis</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DAnalysis}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Referenced Analysis</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Referenced Analysis</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_ReferencedAnalysis()
     * @model
     * @generated
     */
    EList<DAnalysis> getReferencedAnalysis();

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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_Models()
     * @model transient="true" volatile="true" derived="true"
     * @generated
     */
    EList<EObject> getModels();

    /**
     * Returns the value of the '<em><b>EAnnotations</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotationEntry}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EAnnotations</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>EAnnotations</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_EAnnotations()
     * @model containment="true"
     * @generated
     */
    EList<DAnnotationEntry> getEAnnotations();

    /**
     * Returns the value of the '<em><b>Owned Views</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DView}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Views</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Views</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_OwnedViews()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DView> getOwnedViews();

    /**
     * Returns the value of the '<em><b>Selected Views</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DView}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selected Views</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Selected Views</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_SelectedViews()
     * @model
     * @generated
     */
    EList<DView> getSelectedViews();

    /**
     * Returns the value of the '<em><b>Owned Feature Extensions</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.DFeatureExtension}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Feature Extensions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Feature Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_OwnedFeatureExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DFeatureExtension> getOwnedFeatureExtensions();

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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DAnalysis#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Semantic Resources</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.sirius.business.api.resource.ResourceDescriptor}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Resources</em>' attribute list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Resources</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysis_SemanticResources()
     * @model dataType="org.eclipse.sirius.viewpoint.ResourceDescriptor"
     * @generated
     */
    EList<ResourceDescriptor> getSemanticResources();

} // DAnalysis
