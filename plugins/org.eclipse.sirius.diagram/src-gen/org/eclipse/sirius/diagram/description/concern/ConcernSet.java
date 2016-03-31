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
package org.eclipse.sirius.diagram.description.concern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Set</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A set of many concerns. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.concern.ConcernSet#getOwnedConcernDescriptions
 * <em>Owned Concern Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernSet()
 * @model
 * @generated
 */
public interface ConcernSet extends DocumentedElement {
    /**
     * Returns the value of the '<em><b>Owned Concern Descriptions</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All concerns <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Concern Descriptions</em>'
     *         containment reference list.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage#getConcernSet_OwnedConcernDescriptions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConcernDescription> getOwnedConcernDescriptions();

} // ConcernSet
