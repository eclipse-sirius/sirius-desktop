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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeImpl#getNodeRepresentations
 * <em>Node Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends AbstractNodeImpl implements Node {
    /**
     * The cached value of the '{@link #getNodeRepresentations()
     * <em>Node Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNodeRepresentations()
     * @generated
     * @ordered
     */
    protected EList<NodeRepresentation> nodeRepresentations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeRepresentation> getNodeRepresentations() {
        if (nodeRepresentations == null) {
            nodeRepresentations = new EObjectContainmentEList<NodeRepresentation>(NodeRepresentation.class, this, MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS);
        }
        return nodeRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS:
            return ((InternalEList<?>) getNodeRepresentations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS:
            return getNodeRepresentations();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS:
            getNodeRepresentations().clear();
            getNodeRepresentations().addAll((Collection<? extends NodeRepresentation>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS:
            getNodeRepresentations().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case MigrationmodelerPackage.NODE__NODE_REPRESENTATIONS:
            return nodeRepresentations != null && !nodeRepresentations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // NodeImpl
