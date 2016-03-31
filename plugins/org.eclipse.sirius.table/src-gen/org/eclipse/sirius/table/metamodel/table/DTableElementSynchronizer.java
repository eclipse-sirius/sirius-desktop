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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DTable Element Synchronizer</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementSynchronizer()
 * @model abstract="true"
 * @generated
 */
public interface DTableElementSynchronizer extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model cellRequired="true"
     * @generated
     */
    void refresh(DCell cell);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model columnRequired="true"
     * @generated
     */
    void refresh(DColumn column);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model lineRequired="true"
     * @generated
     */
    void refresh(DLine line);

} // DTableElementSynchronizer
