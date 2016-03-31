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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Mapping Import</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl#isHideSubMappings
 * <em>Hide Sub Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl#isInheritsAncestorFilters
 * <em>Inherits Ancestor Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl#getImportedMapping
 * <em>Imported Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeMappingImportImpl extends NodeMappingImpl implements NodeMappingImport {
    /**
     * The default value of the '{@link #isHideSubMappings()
     * <em>Hide Sub Mappings</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isHideSubMappings()
     * @generated
     * @ordered
     */
    protected static final boolean HIDE_SUB_MAPPINGS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isHideSubMappings()
     * <em>Hide Sub Mappings</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isHideSubMappings()
     * @generated
     * @ordered
     */
    protected boolean hideSubMappings = NodeMappingImportImpl.HIDE_SUB_MAPPINGS_EDEFAULT;

    /**
     * The default value of the '{@link #isInheritsAncestorFilters()
     * <em>Inherits Ancestor Filters</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isInheritsAncestorFilters()
     * @generated
     * @ordered
     */
    protected static final boolean INHERITS_ANCESTOR_FILTERS_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isInheritsAncestorFilters()
     * <em>Inherits Ancestor Filters</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isInheritsAncestorFilters()
     * @generated
     * @ordered
     */
    protected boolean inheritsAncestorFilters = NodeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT;

    /**
     * The cached value of the '{@link #getImportedMapping()
     * <em>Imported Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getImportedMapping()
     * @generated
     * @ordered
     */
    protected NodeMapping importedMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeMappingImportImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.NODE_MAPPING_IMPORT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isHideSubMappings() {
        return hideSubMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHideSubMappings(boolean newHideSubMappings) {
        boolean oldHideSubMappings = hideSubMappings;
        hideSubMappings = newHideSubMappings;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS, oldHideSubMappings, hideSubMappings));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isInheritsAncestorFilters() {
        return inheritsAncestorFilters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInheritsAncestorFilters(boolean newInheritsAncestorFilters) {
        boolean oldInheritsAncestorFilters = inheritsAncestorFilters;
        inheritsAncestorFilters = newInheritsAncestorFilters;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS, oldInheritsAncestorFilters, inheritsAncestorFilters));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeMapping getImportedMapping() {
        if (importedMapping != null && importedMapping.eIsProxy()) {
            InternalEObject oldImportedMapping = (InternalEObject) importedMapping;
            importedMapping = (NodeMapping) eResolveProxy(oldImportedMapping);
            if (importedMapping != oldImportedMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING, oldImportedMapping, importedMapping));
                }
            }
        }
        return importedMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NodeMapping basicGetImportedMapping() {
        return importedMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImportedMapping(NodeMapping newImportedMapping) {
        NodeMapping oldImportedMapping = importedMapping;
        importedMapping = newImportedMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING, oldImportedMapping, importedMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            return isHideSubMappings();
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            return isInheritsAncestorFilters();
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            if (resolve) {
                return getImportedMapping();
            }
            return basicGetImportedMapping();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            setHideSubMappings((Boolean) newValue);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters((Boolean) newValue);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((NodeMapping) newValue);
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
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            setHideSubMappings(NodeMappingImportImpl.HIDE_SUB_MAPPINGS_EDEFAULT);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters(NodeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((NodeMapping) null);
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
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            return hideSubMappings != NodeMappingImportImpl.HIDE_SUB_MAPPINGS_EDEFAULT;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            return inheritsAncestorFilters != NodeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            return importedMapping != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractMappingImport.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS;
            case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractMappingImport.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
                return DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
                return DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (hideSubMappings: "); //$NON-NLS-1$
        result.append(hideSubMappings);
        result.append(", inheritsAncestorFilters: "); //$NON-NLS-1$
        result.append(inheritsAncestorFilters);
        result.append(')');
        return result.toString();
    }

} // NodeMappingImportImpl
