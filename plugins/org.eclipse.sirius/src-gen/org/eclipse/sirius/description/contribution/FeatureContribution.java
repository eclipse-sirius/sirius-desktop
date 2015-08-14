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
package org.eclipse.sirius.description.contribution;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Feature Contribution</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getSourceFeature
 * <em>Source Feature</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getTargetFeature
 * <em>Target Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage#getFeatureContribution()
 * @model abstract="true"
 * @generated
 */
public interface FeatureContribution extends EObject {
    /**
     * Returns the value of the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Feature</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source Feature</em>' reference.
     * @see #setSourceFeature(EStructuralFeature)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getFeatureContribution_SourceFeature()
     * @model required="true"
     * @generated
     */
    EStructuralFeature getSourceFeature();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getSourceFeature
     * <em>Source Feature</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Feature</em>' reference.
     * @see #getSourceFeature()
     * @generated
     */
    void setSourceFeature(EStructuralFeature value);

    /**
     * Returns the value of the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Feature</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target Feature</em>' reference.
     * @see #setTargetFeature(EStructuralFeature)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getFeatureContribution_TargetFeature()
     * @model required="true"
     * @generated
     */
    EStructuralFeature getTargetFeature();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getTargetFeature
     * <em>Target Feature</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Feature</em>' reference.
     * @see #getTargetFeature()
     * @generated
     */
    void setTargetFeature(EStructuralFeature value);

} // FeatureContribution
