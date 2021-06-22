/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree;

import org.eclipse.sirius.viewpoint.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTree Element Synchronizer</b></em>'. <!--
 * end-user-doc -->
 *
 *
 * @see org.eclipse.sirius.tree.TreePackage#getDTreeElementSynchronizer()
 * @model
 * @generated
 */
public interface DTreeElementSynchronizer extends IdentifiedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model DTreeItemRequired="true"
     * @generated
     */
    void refresh(DTreeItem DTreeItem);

} // DTreeElementSynchronizer
