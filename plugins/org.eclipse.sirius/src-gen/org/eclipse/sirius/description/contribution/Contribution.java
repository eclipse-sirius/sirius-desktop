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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.Contribution#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.Contribution#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.Contribution#getFeatureMask
 * <em>Feature Mask</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.Contribution#getSubContributions
 * <em>Sub Contributions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.Contribution#getDescription
 * <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source</em>' containment reference.
     * @see #setSource(EObjectReference)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution_Source()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EObjectReference getSource();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getSource
     * <em>Source</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' containment reference.
     * @see #getSource()
     * @generated
     */
    void setSource(EObjectReference value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(EObjectReference)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution_Target()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EObjectReference getTarget();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getTarget
     * <em>Target</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(EObjectReference value);

    /**
     * Returns the value of the '<em><b>Feature Mask</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Mask</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Feature Mask</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution_FeatureMask()
     * @model containment="true" resolveProxies="true" required="true"
     *        ordered="false"
     * @generated
     */
    EList<FeatureContribution> getFeatureMask();

    /**
     * Returns the value of the '<em><b>Sub Contributions</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.description.contribution.Contribution}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Contributions</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sub Contributions</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution_SubContributions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Contribution> getSubContributions();

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContribution_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

} // Contribution
