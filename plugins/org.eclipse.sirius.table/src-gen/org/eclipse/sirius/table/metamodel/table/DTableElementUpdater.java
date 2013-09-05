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
 * <em><b>DTable Element Updater</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementUpdater()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DTableElementUpdater extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation\n";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model syncRequired="true"
     * @generated
     */
    void activate(DTableElementSynchronizer sync);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void deactivate();

} // DTableElementUpdater
