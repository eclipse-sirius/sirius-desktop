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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Cross Table Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl#getIntersection
 * <em>Intersection</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl#getCreateColumn
 * <em>Create Column</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CrossTableDescriptionImpl extends TableDescriptionImpl implements CrossTableDescription {
    /**
     * The cached value of the '{@link #getOwnedColumnMappings() <em>Owned Column Mappings</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedColumnMappings()
     * @generated
     * @ordered
     */
    protected EList<ElementColumnMapping> ownedColumnMappings;

    /**
     * The cached value of the '{@link #getIntersection() <em>Intersection</em>} ' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIntersection()
     * @generated
     * @ordered
     */
    protected EList<IntersectionMapping> intersection;

    /**
     * The cached value of the '{@link #getCreateColumn() <em>Create Column</em> }' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCreateColumn()
     * @generated
     * @ordered
     */
    protected EList<CreateCrossColumnTool> createColumn;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CrossTableDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CROSS_TABLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ElementColumnMapping> getOwnedColumnMappings() {
        if (ownedColumnMappings == null) {
            ownedColumnMappings = new EObjectContainmentEList<>(ElementColumnMapping.class, this, DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        }
        return ownedColumnMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<IntersectionMapping> getIntersection() {
        if (intersection == null) {
            intersection = new EObjectContainmentEList<>(IntersectionMapping.class, this, DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION);
        }
        return intersection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CreateCrossColumnTool> getCreateColumn() {
        if (createColumn == null) {
            createColumn = new EObjectContainmentEList<>(CreateCrossColumnTool.class, this, DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN);
        }
        return createColumn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ((InternalEList<?>) getOwnedColumnMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION:
            return ((InternalEList<?>) getIntersection()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN:
            return ((InternalEList<?>) getCreateColumn()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return getOwnedColumnMappings();
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION:
            return getIntersection();
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN:
            return getCreateColumn();
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
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            getOwnedColumnMappings().addAll((Collection<? extends ElementColumnMapping>) newValue);
            return;
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION:
            getIntersection().clear();
            getIntersection().addAll((Collection<? extends IntersectionMapping>) newValue);
            return;
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN:
            getCreateColumn().clear();
            getCreateColumn().addAll((Collection<? extends CreateCrossColumnTool>) newValue);
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
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            return;
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION:
            getIntersection().clear();
            return;
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN:
            getCreateColumn().clear();
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
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ownedColumnMappings != null && !ownedColumnMappings.isEmpty();
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__INTERSECTION:
            return intersection != null && !intersection.isEmpty();
        case DescriptionPackage.CROSS_TABLE_DESCRIPTION__CREATE_COLUMN:
            return createColumn != null && !createColumn.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // CrossTableDescriptionImpl
