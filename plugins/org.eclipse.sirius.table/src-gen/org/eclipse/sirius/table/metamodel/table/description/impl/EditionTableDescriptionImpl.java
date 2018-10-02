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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Edition Table Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl#getReusedColumnMappings
 * <em>Reused Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl#getAllColumnMappings
 * <em>All Column Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EditionTableDescriptionImpl extends TableDescriptionImpl implements EditionTableDescription {
    /**
     * The cached value of the '{@link #getOwnedColumnMappings() <em>Owned Column Mappings</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedColumnMappings()
     * @generated
     * @ordered
     */
    protected EList<FeatureColumnMapping> ownedColumnMappings;

    /**
     * The cached value of the '{@link #getReusedColumnMappings() <em>Reused Column Mappings</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReusedColumnMappings()
     * @generated
     * @ordered
     */
    protected EList<FeatureColumnMapping> reusedColumnMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EditionTableDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EDITION_TABLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FeatureColumnMapping> getOwnedColumnMappings() {
        if (ownedColumnMappings == null) {
            ownedColumnMappings = new EObjectContainmentEList<FeatureColumnMapping>(FeatureColumnMapping.class, this, DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        }
        return ownedColumnMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FeatureColumnMapping> getReusedColumnMappings() {
        if (reusedColumnMappings == null) {
            reusedColumnMappings = new EObjectResolvingEList<FeatureColumnMapping>(FeatureColumnMapping.class, this, DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS);
        }
        return reusedColumnMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FeatureColumnMapping> getAllColumnMappings() {
        // TODO: implement this method to return the 'All Column Mappings' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ((InternalEList<?>) getOwnedColumnMappings()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return getOwnedColumnMappings();
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS:
            return getReusedColumnMappings();
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS:
            return getAllColumnMappings();
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
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            getOwnedColumnMappings().addAll((Collection<? extends FeatureColumnMapping>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS:
            getReusedColumnMappings().clear();
            getReusedColumnMappings().addAll((Collection<? extends FeatureColumnMapping>) newValue);
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
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS:
            getReusedColumnMappings().clear();
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
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ownedColumnMappings != null && !ownedColumnMappings.isEmpty();
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS:
            return reusedColumnMappings != null && !reusedColumnMappings.isEmpty();
        case DescriptionPackage.EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS:
            return !getAllColumnMappings().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // EditionTableDescriptionImpl
