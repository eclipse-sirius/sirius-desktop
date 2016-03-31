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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Mapping Import</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl#getImportedMapping
 * <em>Imported Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl#isInheritsAncestorFilters
 * <em>Inherits Ancestor Filters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeMappingImportImpl extends DocumentedElementImpl implements EdgeMappingImport {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = EdgeMappingImportImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = EdgeMappingImportImpl.LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getImportedMapping()
     * <em>Imported Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getImportedMapping()
     * @generated
     * @ordered
     */
    protected IEdgeMapping importedMapping;

    /**
     * The cached value of the '{@link #getConditionnalStyles()
     * <em>Conditionnal Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionnalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalEdgeStyleDescription> conditionnalStyles;

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
    protected boolean inheritsAncestorFilters = EdgeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeMappingImportImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EDGE_MAPPING_IMPORT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING_IMPORT__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public IEdgeMapping getImportedMapping() {
        if (importedMapping != null && importedMapping.eIsProxy()) {
            InternalEObject oldImportedMapping = (InternalEObject) importedMapping;
            importedMapping = (IEdgeMapping) eResolveProxy(oldImportedMapping);
            if (importedMapping != oldImportedMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING, oldImportedMapping, importedMapping));
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
    public IEdgeMapping basicGetImportedMapping() {
        return importedMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImportedMapping(IEdgeMapping newImportedMapping) {
        IEdgeMapping oldImportedMapping = importedMapping;
        importedMapping = newImportedMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING, oldImportedMapping, importedMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConditionalEdgeStyleDescription> getConditionnalStyles() {
        if (conditionnalStyles == null) {
            conditionnalStyles = new EObjectContainmentEList.Resolving<ConditionalEdgeStyleDescription>(ConditionalEdgeStyleDescription.class, this,
                    DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES);
        }
        return conditionnalStyles;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS, oldInheritsAncestorFilters, inheritsAncestorFilters));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
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
        case DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES:
            return ((InternalEList<?>) getConditionnalStyles()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.EDGE_MAPPING_IMPORT__NAME:
            return getName();
        case DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL:
            return getLabel();
        case DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING:
            if (resolve) {
                return getImportedMapping();
            }
            return basicGetImportedMapping();
        case DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES:
            return getConditionnalStyles();
        case DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            return isInheritsAncestorFilters();
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
        case DescriptionPackage.EDGE_MAPPING_IMPORT__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((IEdgeMapping) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            getConditionnalStyles().addAll((Collection<? extends ConditionalEdgeStyleDescription>) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters((Boolean) newValue);
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
        case DescriptionPackage.EDGE_MAPPING_IMPORT__NAME:
            setName(EdgeMappingImportImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL:
            setLabel(EdgeMappingImportImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((IEdgeMapping) null);
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            return;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters(EdgeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT);
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
        case DescriptionPackage.EDGE_MAPPING_IMPORT__NAME:
            return EdgeMappingImportImpl.NAME_EDEFAULT == null ? name != null : !EdgeMappingImportImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL:
            return EdgeMappingImportImpl.LABEL_EDEFAULT == null ? label != null : !EdgeMappingImportImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING:
            return importedMapping != null;
        case DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES:
            return conditionnalStyles != null && !conditionnalStyles.isEmpty();
        case DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            return inheritsAncestorFilters != EdgeMappingImportImpl.INHERITS_ANCESTOR_FILTERS_EDEFAULT;
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
        if (baseClass == IEdgeMapping.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.EDGE_MAPPING_IMPORT__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
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
        if (baseClass == IEdgeMapping.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.EDGE_MAPPING_IMPORT__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.EDGE_MAPPING_IMPORT__LABEL;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", inheritsAncestorFilters: "); //$NON-NLS-1$
        result.append(inheritsAncestorFilters);
        result.append(')');
        return result.toString();
    }

} // EdgeMappingImportImpl
