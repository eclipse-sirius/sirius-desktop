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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DRepresentation Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl#getModels
 * <em>Models</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DRepresentationContainerImpl extends DViewImpl implements DRepresentationContainer {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DRepresentationContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DREPRESENTATION_CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getModels() {
        // TODO: implement this method to return the 'Models' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
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
        case ViewpointPackage.DREPRESENTATION_CONTAINER__MODELS:
            return getModels();
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
        case ViewpointPackage.DREPRESENTATION_CONTAINER__MODELS:
            return !getModels().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DRepresentationContainerImpl
