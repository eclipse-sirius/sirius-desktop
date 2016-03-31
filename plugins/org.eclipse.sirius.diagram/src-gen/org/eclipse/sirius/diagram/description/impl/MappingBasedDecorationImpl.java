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
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mapping Based Decoration</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.MappingBasedDecorationImpl#getMappings
 * <em>Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MappingBasedDecorationImpl extends DecorationDescriptionImpl implements MappingBasedDecoration {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> mappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MappingBasedDecorationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.MAPPING_BASED_DECORATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS:
            return getMappings();
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
        case DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
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
        case DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS:
            getMappings().clear();
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
        case DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // MappingBasedDecorationImpl
