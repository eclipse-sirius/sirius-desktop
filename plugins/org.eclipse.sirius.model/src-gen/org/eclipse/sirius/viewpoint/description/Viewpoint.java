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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Viewpoint</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A Viewpoint defines a "way of looking at your model", you could make an analogy with "What
 * is your current concern about your analysis". It defines representations and might also define specific data only
 * relevant for this concern. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getModelFileExtension <em>Model File
 * Extension</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getValidationSet <em>Validation Set</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentations <em>Owned
 * Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentationExtensions <em>Owned
 * Representation Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedJavaExtensions <em>Owned Java
 * Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedMMExtensions <em>Owned MM Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedFeatureExtensions <em>Owned Feature
 * Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getIcon <em>Icon</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedTemplates <em>Owned Templates</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getConflicts <em>Conflicts</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getReuses <em>Reuses</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getCustomizes <em>Customizes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint()
 * @model
 * @generated
 */
public interface Viewpoint extends DocumentedElement, Component, EndUserDocumentedElement, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Model File Extension</b></em>' attribute. The default value is <code>"*"</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Might be used to restrict your viewpoint
     * to a set of file extensions, for instance "ecore" <!-- end-model-doc -->
     *
     * @return the value of the '<em>Model File Extension</em>' attribute.
     * @see #setModelFileExtension(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_ModelFileExtension()
     * @model default="*"
     * @generated
     */
    String getModelFileExtension();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getModelFileExtension <em>Model
     * File Extension</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Model File Extension</em>' attribute.
     * @see #getModelFileExtension()
     * @generated
     */
    void setModelFileExtension(String value);

    /**
     * Returns the value of the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The validations rules <!-- end-model-doc -->
     *
     * @return the value of the '<em>Validation Set</em>' containment reference.
     * @see #setValidationSet(ValidationSet)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_ValidationSet()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    ValidationSet getValidationSet();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getValidationSet <em>Validation
     * Set</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Validation Set</em>' containment reference.
     * @see #getValidationSet()
     * @generated
     */
    void setValidationSet(ValidationSet value);

    /**
     * Returns the value of the '<em><b>Owned Representations</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Representations</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Representations</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_OwnedRepresentations()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<RepresentationDescription> getOwnedRepresentations();

    /**
     * Returns the value of the '<em><b>Owned Representation Extensions</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Representation Extensions</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Representation Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_OwnedRepresentationExtensions()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<RepresentationExtensionDescription> getOwnedRepresentationExtensions();

    /**
     * Returns the value of the '<em><b>Owned Java Extensions</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.JavaExtension}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Java Extensions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Java Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_OwnedJavaExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<JavaExtension> getOwnedJavaExtensions();

    /**
     * Returns the value of the '<em><b>Owned MM Extensions</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned MM Extensions</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned MM Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_OwnedMMExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<MetamodelExtensionSetting> getOwnedMMExtensions();

    /**
     * Returns the value of the '<em><b>Owned Feature Extensions</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Feature Extensions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Feature Extensions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_OwnedFeatureExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<FeatureExtensionDescription> getOwnedFeatureExtensions();

    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> image path to use as an icon for the viewpoint <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_Icon()
     * @model
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Viewpoint#getIcon <em>Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

    /**
     * Returns the value of the '<em><b>Conflicts</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.emf.common.util.URI}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conflicts</em>' attribute list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conflicts</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_Conflicts()
     * @model dataType="org.eclipse.sirius.viewpoint.description.URI"
     * @generated
     */
    EList<URI> getConflicts();

    /**
     * Returns the value of the '<em><b>Reuses</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.emf.common.util.URI}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuses</em>' attribute list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reuses</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_Reuses()
     * @model dataType="org.eclipse.sirius.viewpoint.description.URI"
     * @generated
     */
    EList<URI> getReuses();

    /**
     * Returns the value of the '<em><b>Customizes</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.emf.common.util.URI}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Customizes</em>' attribute list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Customizes</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getViewpoint_Customizes()
     * @model dataType="org.eclipse.sirius.viewpoint.description.URI"
     * @generated
     */
    EList<URI> getCustomizes();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Initialize the analysis.
     *
     * @param model
     *            The root model element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    void initView(EObject model);

} // Viewpoint
