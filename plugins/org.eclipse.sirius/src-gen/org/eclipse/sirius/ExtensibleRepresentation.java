/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.contribution.ContributionPoint;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Extensible Representation</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.ExtensibleRepresentation#getEffectiveDescription
 * <em>Effective Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.ExtensibleRepresentation#getContributionPoints
 * <em>Contribution Points</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getExtensibleRepresentation()
 * @model abstract="true"
 * @generated
 */
public interface ExtensibleRepresentation extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Effective Description</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Effective Description</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Effective Description</em>' containment
     *         reference.
     * @see #setEffectiveDescription(RepresentationDescription)
     * @see org.eclipse.sirius.SiriusPackage#getExtensibleRepresentation_EffectiveDescription()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RepresentationDescription getEffectiveDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.ExtensibleRepresentation#getEffectiveDescription
     * <em>Effective Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Effective Description</em>'
     *            containment reference.
     * @see #getEffectiveDescription()
     * @generated
     */
    void setEffectiveDescription(RepresentationDescription value);

    /**
     * Returns the value of the '<em><b>Contribution Points</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contribution Points</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Contribution Points</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.SiriusPackage#getExtensibleRepresentation_ContributionPoints()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ContributionPoint> getContributionPoints();

} // ExtensibleRepresentation
