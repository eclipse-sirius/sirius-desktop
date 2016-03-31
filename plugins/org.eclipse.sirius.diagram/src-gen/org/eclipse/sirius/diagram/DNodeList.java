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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DNode List</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A container that shows its contents as a list. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.DNodeList#getOwnedElements
 * <em>Owned Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getDNodeList()
 * @model
 * @generated
 */
public interface DNodeList extends DDiagramElementContainer {
    /**
     * Returns the value of the '<em><b>Owned Elements</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.DNodeListElement}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> elements owned by this
     * list. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Elements</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDNodeList_OwnedElements()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DNodeListElement> getOwnedElements();

} // DNodeList
