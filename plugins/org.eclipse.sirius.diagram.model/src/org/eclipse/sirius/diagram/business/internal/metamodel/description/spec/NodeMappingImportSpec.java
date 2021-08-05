/*******************************************************************************
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
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.spec;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.model.tools.internal.util.StringUtil;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * Implementation of the NodeMappingImport interface. This class is more or less a wrapper for another NodeMapping, it
 * helps in reusing mappings from other elements.
 * 
 * @author cbrun
 * 
 */
public class NodeMappingImportSpec extends NodeMappingSpec implements NodeMappingImport {

    /**
     * The default value of the '{@link #isHideSubMappings() <em>Hide Sub Mappings</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #isHideSubMappings()
     * @generated
     * @ordered
     */
    protected static final boolean HIDE_SUB_MAPPINGS_EDEFAULT = false;

    /**
     * The default value of the '{@link #isInheritsAncestorFilters() <em>Inherits Ancestor Filters</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isInheritsAncestorFilters()
     * @generated
     * @ordered
     */
    protected static final boolean INHERITS_ANCESTOR_FILTERS_EDEFAULT = true;

    /**
     * The cached value of the '{@link #getImportedMapping() <em>Imported Mapping</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getImportedMapping()
     * @generated
     * @ordered
     */
    protected NodeMapping importedMapping;

    /**
     * The cached value of the '{@link #isHideSubMappings() <em>Hide Sub Mappings</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #isHideSubMappings()
     * @generated
     * @ordered
     */
    protected boolean hideSubMappings = HIDE_SUB_MAPPINGS_EDEFAULT;

    /**
     * The cached value of the '{@link #isInheritsAncestorFilters() <em>Inherits Ancestor Filters</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isInheritsAncestorFilters()
     * @generated
     * @ordered
     */
    protected boolean inheritsAncestorFilters = INHERITS_ANCESTOR_FILTERS_EDEFAULT;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.AbstractNodeMappingImpl#getDomainClass()
     */
    @Override
    public String getDomainClass() {
        /*
         * Each import can have it owns domain class
         */
        if (StringUtil.isEmpty(super.getDomainClass()) && getImportedMapping() != null) {
            return getImportedMapping().getDomainClass();
        }
        return super.getDomainClass();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#getDeletionDescription()
     */
    @Override
    public DeleteElementDescription getDeletionDescription() {
        final DeleteElementDescription intrinsicValue = super.getDeletionDescription();
        /* if redefined take it */
        if (intrinsicValue != null) {
            return intrinsicValue;
        }

        DeleteElementDescription deleteElementDescription = null;

        if (getImportedMapping() != null && getImportedMapping() != this) {
            deleteElementDescription = getImportedMapping().getDeletionDescription();
        }
        return deleteElementDescription;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#getLabelDirectEdit()
     */
    @Override
    public DirectEditLabel getLabelDirectEdit() {
        final DirectEditLabel intrinsicValue = super.getLabelDirectEdit();
        /* if redefined take it */
        if (intrinsicValue != null) {
            return intrinsicValue;
        }

        DirectEditLabel directEditLabel = null;

        if (getImportedMapping() != null && getImportedMapping() != this) {
            directEditLabel = getImportedMapping().getLabelDirectEdit();
        }

        return directEditLabel;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#getPreconditionExpression()
     */
    @Override
    public String getPreconditionExpression() {
        /*
         * each import has it owns precondition expression
         */
        return super.getPreconditionExpression();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#getSemanticCandidatesExpression()
     */
    @Override
    public String getSemanticCandidatesExpression() {
        /*
         * each import has it owns semantic candidates expression
         */
        return super.getSemanticCandidatesExpression();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isCreateElements()
     */
    @Override
    public boolean isCreateElements() {
        if (getImportedMapping() != null && getImportedMapping() != this) {
            return getImportedMapping().isCreateElements();
        }
        /* each import has it own value */
        return super.isCreateElements();
    }

    /*
     * The following code is copy/pasted from NodeMappingImportImpl.
     */

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eStaticClass()
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.NODE_MAPPING_IMPORT;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.NodeMappingImport#getImportedMapping()
     */
    @Override
    public NodeMapping getImportedMapping() {
        if (importedMapping != null && importedMapping.eIsProxy()) {
            final InternalEObject oldImportedMapping = (InternalEObject) importedMapping;
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
     * Return the imported mapping.
     * 
     * @return the imported mapping.
     */
    public NodeMapping basicGetImportedMapping() {
        return importedMapping;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.NodeMappingImport#setImportedMapping(org.eclipse.sirius.viewpoint.description.NodeMapping)
     */
    @Override
    public void setImportedMapping(final NodeMapping newImportedMapping) {
        final NodeMapping oldImportedMapping = importedMapping;
        importedMapping = newImportedMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING, oldImportedMapping, importedMapping));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eGet(int, boolean, boolean)
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        Object result = null;
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            result = isHideSubMappings() ? Boolean.TRUE : Boolean.FALSE;
            break;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            result = isInheritsAncestorFilters() ? Boolean.TRUE : Boolean.FALSE;
            break;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            Object mapping;
            if (resolve) {
                mapping = getImportedMapping();
            } else {
                mapping = basicGetImportedMapping();
            }
            result = mapping;
            break;
        default:
            result = super.eGet(featureID, resolve, coreType);
            break;
        }
        return result;
    }

    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc} CHECKSTYLE:OFF because this code is copied from generated code
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eSet(int, java.lang.Object)
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            setHideSubMappings(((Boolean) newValue).booleanValue());
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters(((Boolean) newValue).booleanValue());
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((NodeMapping) newValue);
            return;
        default:
            break;
        }
        super.eSet(featureID, newValue);
    }

    // CHECKSTYLE:ON

    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc} CHECKSTYLE:OFF because this code is copied from generated code
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eUnset(int)
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            setHideSubMappings(HIDE_SUB_MAPPINGS_EDEFAULT);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            setInheritsAncestorFilters(INHERITS_ANCESTOR_FILTERS_EDEFAULT);
            return;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            setImportedMapping((NodeMapping) null);
            return;
        default:
            break;
        }
        super.eUnset(featureID);
    }

    // CHECKSTYLE:ON

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eIsSet(int)
     */
    @Override
    public boolean eIsSet(final int featureID) {
        boolean result;
        switch (featureID) {
        case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
            result = hideSubMappings != HIDE_SUB_MAPPINGS_EDEFAULT;
            break;
        case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
            result = inheritsAncestorFilters != INHERITS_ANCESTOR_FILTERS_EDEFAULT;
            break;
        case DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            result = importedMapping != null;
            break;
        default:
            result = super.eIsSet(featureID);
            break;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eBaseStructuralFeatureID(int, java.lang.Class)
     */
    @Override
    public int eBaseStructuralFeatureID(final int derivedFeatureID, @SuppressWarnings("rawtypes") final Class baseClass) {
        if (baseClass == AbstractMappingImport.class) {
            int result;
            switch (derivedFeatureID) {
            case DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
                result = org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS;
                break;
            case DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
                result = org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS;
                break;
            default:
                result = -1;
                break;
            }
            return result;
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.NodeMappingImpl#eDerivedStructuralFeatureID(int,
     *      java.lang.Class)
     */
    @Override
    public int eDerivedStructuralFeatureID(final int baseFeatureID, @SuppressWarnings("rawtypes") final Class baseClass) {
        if (baseClass == AbstractMappingImport.class) {
            int result;
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
                result = DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS;
                break;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
                result = DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS;
                break;
            default:
                result = -1;
                break;
            }
            return result;
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isHideSubMappings()
     */
    @Override
    public boolean isHideSubMappings() {
        return hideSubMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#setHideSubMappings(boolean)
     */
    @Override
    public void setHideSubMappings(final boolean newHideSubMappings) {
        final boolean oldHideSubMappings = hideSubMappings;
        hideSubMappings = newHideSubMappings;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS, oldHideSubMappings, hideSubMappings));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isInheritsAncestorFilters()
     */
    @Override
    public boolean isInheritsAncestorFilters() {
        return inheritsAncestorFilters;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#setInheritsAncestorFilters(boolean)
     */
    @Override
    public void setInheritsAncestorFilters(boolean newInheritsAncestorFilters) {
        boolean oldInheritsAncestorFilters = inheritsAncestorFilters;
        inheritsAncestorFilters = newInheritsAncestorFilters;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS, oldInheritsAncestorFilters, inheritsAncestorFilters));
        }
    }

}
