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
package org.eclipse.sirius.tree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DTree Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeElementImpl#getTreeElementMapping <em>Tree Element Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DTreeElementImpl extends DRepresentationElementImpl implements DTreeElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DTreeElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TreePackage.Literals.DTREE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TreeMapping getTreeElementMapping() {
        TreeMapping treeElementMapping = basicGetTreeElementMapping();
        return treeElementMapping != null && treeElementMapping.eIsProxy() ? (TreeMapping) eResolveProxy((InternalEObject) treeElementMapping) : treeElementMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeMapping basicGetTreeElementMapping() {
        // TODO: implement this method to return the 'Tree Element Mapping' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TreePackage.DTREE_ELEMENT__TREE_ELEMENT_MAPPING:
            if (resolve) {
                return getTreeElementMapping();
            }
            return basicGetTreeElementMapping();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case TreePackage.DTREE_ELEMENT__TREE_ELEMENT_MAPPING:
            return basicGetTreeElementMapping() != null;
        }
        return super.eIsSet(featureID);
    }

} // DTreeElementImpl
