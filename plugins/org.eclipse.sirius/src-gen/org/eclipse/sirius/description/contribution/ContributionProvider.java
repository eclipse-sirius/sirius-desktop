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
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.ContributionProvider#getContributions
 * <em>Contributions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContributionProvider()
 * @model abstract="true"
 * @generated
 */
public interface ContributionProvider extends EObject {
    /**
     * Returns the value of the '<em><b>Contributions</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.description.contribution.Contribution}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contributions</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Contributions</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContributionProvider_Contributions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Contribution> getContributions();

} // ContributionProvider
