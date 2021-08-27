/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Representation Element Mapping</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl#getDetailDescriptions
 * <em>Detail Descriptions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl#getNavigationDescriptions
 * <em>Navigation Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class RepresentationElementMappingImpl extends IdentifiedElementImpl implements RepresentationElementMapping {
    /**
     * The cached value of the '{@link #getDetailDescriptions() <em>Detail Descriptions</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDetailDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationCreationDescription> detailDescriptions;

    /**
     * The cached value of the '{@link #getNavigationDescriptions() <em>Navigation Descriptions</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNavigationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationNavigationDescription> navigationDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RepresentationElementMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.REPRESENTATION_ELEMENT_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RepresentationCreationDescription> getDetailDescriptions() {
        if (detailDescriptions == null) {
            detailDescriptions = new EObjectResolvingEList<>(RepresentationCreationDescription.class, this, DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS);
        }
        return detailDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RepresentationNavigationDescription> getNavigationDescriptions() {
        if (navigationDescriptions == null) {
            navigationDescriptions = new EObjectResolvingEList<>(RepresentationNavigationDescription.class, this, DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS);
        }
        return navigationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS:
            return getDetailDescriptions();
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS:
            return getNavigationDescriptions();
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
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS:
            getDetailDescriptions().clear();
            getDetailDescriptions().addAll((Collection<? extends RepresentationCreationDescription>) newValue);
            return;
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS:
            getNavigationDescriptions().clear();
            getNavigationDescriptions().addAll((Collection<? extends RepresentationNavigationDescription>) newValue);
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
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS:
            getDetailDescriptions().clear();
            return;
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS:
            getNavigationDescriptions().clear();
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
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS:
            return detailDescriptions != null && !detailDescriptions.isEmpty();
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS:
            return navigationDescriptions != null && !navigationDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // RepresentationElementMappingImpl
