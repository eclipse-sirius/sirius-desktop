/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Menu Item</b></em>'. <!-- end-user-doc
 * -->
 *
 * @generated
 */
public abstract class GroupMenuItemImpl extends AbstractToolDescriptionImpl implements GroupMenuItem {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GroupMenuItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.GROUP_MENU_ITEM;
    }

} // GroupMenuItemImpl
