/*******************************************************************************
 * Copyright (c) 2007-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DCell</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getTableElementMapping
 * <em>Table Element Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getLabel
 * <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getLine
 * <em>Line</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getColumn
 * <em>Column</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getCurrentStyle
 * <em>Current Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getUpdater
 * <em>Updater</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl#getIntersectionMapping
 * <em>Intersection Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DCellImpl extends DSemanticDecoratorImpl implements DCell {
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
    protected String name = DCellImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSemanticElements()
     * <em>Semantic Elements</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSemanticElements()
     * @generated
     * @ordered
     */
    protected EList<EObject> semanticElements;

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
    protected String label = DCellImpl.LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getColumn() <em>Column</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getColumn()
     * @generated
     * @ordered
     */
    protected DColumn column;

    /**
     * The cached value of the '{@link #getCurrentStyle()
     * <em>Current Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCurrentStyle()
     * @generated
     * @ordered
     */
    protected DCellStyle currentStyle;

    /**
     * The cached value of the '{@link #getIntersectionMapping()
     * <em>Intersection Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getIntersectionMapping()
     * @generated
     * @ordered
     */
    protected IntersectionMapping intersectionMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DCellImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DCELL;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getSemanticElements() {
        if (semanticElements == null) {
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, TablePackage.DCELL__SEMANTIC_ELEMENTS);
        }
        return semanticElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TableMapping getTableElementMapping() {
        TableMapping tableElementMapping = basicGetTableElementMapping();
        return tableElementMapping != null && tableElementMapping.eIsProxy() ? (TableMapping) eResolveProxy((InternalEObject) tableElementMapping) : tableElementMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TableMapping basicGetTableElementMapping() {
        // TODO: implement this method to return the 'Table Element Mapping'
        // reference
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
    public DLine getLine() {
        if (eContainerFeatureID() != TablePackage.DCELL__LINE) {
            return null;
        }
        return (DLine) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetLine(DLine newLine, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newLine, TablePackage.DCELL__LINE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLine(DLine newLine) {
        if (newLine != eInternalContainer() || (eContainerFeatureID() != TablePackage.DCELL__LINE && newLine != null)) {
            if (EcoreUtil.isAncestor(this, newLine)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newLine != null) {
                msgs = ((InternalEObject) newLine).eInverseAdd(this, TablePackage.DLINE__CELLS, DLine.class, msgs);
            }
            msgs = basicSetLine(newLine, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__LINE, newLine, newLine));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DColumn getColumn() {
        if (column != null && column.eIsProxy()) {
            InternalEObject oldColumn = (InternalEObject) column;
            column = (DColumn) eResolveProxy(oldColumn);
            if (column != oldColumn) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DCELL__COLUMN, oldColumn, column));
                }
            }
        }
        return column;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DColumn basicGetColumn() {
        return column;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetColumn(DColumn newColumn, NotificationChain msgs) {
        DColumn oldColumn = column;
        column = newColumn;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__COLUMN, oldColumn, newColumn);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setColumn(DColumn newColumn) {
        if (newColumn != column) {
            NotificationChain msgs = null;
            if (column != null) {
                msgs = ((InternalEObject) column).eInverseRemove(this, TablePackage.DCOLUMN__CELLS, DColumn.class, msgs);
            }
            if (newColumn != null) {
                msgs = ((InternalEObject) newColumn).eInverseAdd(this, TablePackage.DCOLUMN__CELLS, DColumn.class, msgs);
            }
            msgs = basicSetColumn(newColumn, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__COLUMN, newColumn, newColumn));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DCellStyle getCurrentStyle() {
        return currentStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCurrentStyle(DCellStyle newCurrentStyle, NotificationChain msgs) {
        DCellStyle oldCurrentStyle = currentStyle;
        currentStyle = newCurrentStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__CURRENT_STYLE, oldCurrentStyle, newCurrentStyle);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCurrentStyle(DCellStyle newCurrentStyle) {
        if (newCurrentStyle != currentStyle) {
            NotificationChain msgs = null;
            if (currentStyle != null) {
                msgs = ((InternalEObject) currentStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DCELL__CURRENT_STYLE, null, msgs);
            }
            if (newCurrentStyle != null) {
                msgs = ((InternalEObject) newCurrentStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DCELL__CURRENT_STYLE, null, msgs);
            }
            msgs = basicSetCurrentStyle(newCurrentStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__CURRENT_STYLE, newCurrentStyle, newCurrentStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CellUpdater getUpdater() {
        CellUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (CellUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CellUpdater basicGetUpdater() {
        // TODO: implement this method to return the 'Updater' reference
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
    public void setUpdater(CellUpdater newUpdater) {
        // TODO: implement this method to set the 'Updater' reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntersectionMapping getIntersectionMapping() {
        if (intersectionMapping != null && intersectionMapping.eIsProxy()) {
            InternalEObject oldIntersectionMapping = (InternalEObject) intersectionMapping;
            intersectionMapping = (IntersectionMapping) eResolveProxy(oldIntersectionMapping);
            if (intersectionMapping != oldIntersectionMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DCELL__INTERSECTION_MAPPING, oldIntersectionMapping, intersectionMapping));
                }
            }
        }
        return intersectionMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public IntersectionMapping basicGetIntersectionMapping() {
        return intersectionMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIntersectionMapping(IntersectionMapping newIntersectionMapping) {
        IntersectionMapping oldIntersectionMapping = intersectionMapping;
        intersectionMapping = newIntersectionMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL__INTERSECTION_MAPPING, oldIntersectionMapping, intersectionMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void refresh() {
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
    public Style getStyle() {
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
    public RepresentationElementMapping getMapping() {
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TablePackage.DCELL__LINE:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetLine((DLine) otherEnd, msgs);
        case TablePackage.DCELL__COLUMN:
            if (column != null) {
                msgs = ((InternalEObject) column).eInverseRemove(this, TablePackage.DCOLUMN__CELLS, DColumn.class, msgs);
            }
            return basicSetColumn((DColumn) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TablePackage.DCELL__LINE:
            return basicSetLine(null, msgs);
        case TablePackage.DCELL__COLUMN:
            return basicSetColumn(null, msgs);
        case TablePackage.DCELL__CURRENT_STYLE:
            return basicSetCurrentStyle(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case TablePackage.DCELL__LINE:
            return eInternalContainer().eInverseRemove(this, TablePackage.DLINE__CELLS, DLine.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TablePackage.DCELL__NAME:
            return getName();
        case TablePackage.DCELL__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case TablePackage.DCELL__TABLE_ELEMENT_MAPPING:
            if (resolve) {
                return getTableElementMapping();
            }
            return basicGetTableElementMapping();
        case TablePackage.DCELL__LABEL:
            return getLabel();
        case TablePackage.DCELL__LINE:
            return getLine();
        case TablePackage.DCELL__COLUMN:
            if (resolve) {
                return getColumn();
            }
            return basicGetColumn();
        case TablePackage.DCELL__CURRENT_STYLE:
            return getCurrentStyle();
        case TablePackage.DCELL__UPDATER:
            if (resolve) {
                return getUpdater();
            }
            return basicGetUpdater();
        case TablePackage.DCELL__INTERSECTION_MAPPING:
            if (resolve) {
                return getIntersectionMapping();
            }
            return basicGetIntersectionMapping();
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
        case TablePackage.DCELL__NAME:
            setName((String) newValue);
            return;
        case TablePackage.DCELL__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
            return;
        case TablePackage.DCELL__LABEL:
            setLabel((String) newValue);
            return;
        case TablePackage.DCELL__LINE:
            setLine((DLine) newValue);
            return;
        case TablePackage.DCELL__COLUMN:
            setColumn((DColumn) newValue);
            return;
        case TablePackage.DCELL__CURRENT_STYLE:
            setCurrentStyle((DCellStyle) newValue);
            return;
        case TablePackage.DCELL__UPDATER:
            setUpdater((CellUpdater) newValue);
            return;
        case TablePackage.DCELL__INTERSECTION_MAPPING:
            setIntersectionMapping((IntersectionMapping) newValue);
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
        case TablePackage.DCELL__NAME:
            setName(DCellImpl.NAME_EDEFAULT);
            return;
        case TablePackage.DCELL__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            return;
        case TablePackage.DCELL__LABEL:
            setLabel(DCellImpl.LABEL_EDEFAULT);
            return;
        case TablePackage.DCELL__LINE:
            setLine((DLine) null);
            return;
        case TablePackage.DCELL__COLUMN:
            setColumn((DColumn) null);
            return;
        case TablePackage.DCELL__CURRENT_STYLE:
            setCurrentStyle((DCellStyle) null);
            return;
        case TablePackage.DCELL__UPDATER:
            setUpdater((CellUpdater) null);
            return;
        case TablePackage.DCELL__INTERSECTION_MAPPING:
            setIntersectionMapping((IntersectionMapping) null);
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
        case TablePackage.DCELL__NAME:
            return DCellImpl.NAME_EDEFAULT == null ? name != null : !DCellImpl.NAME_EDEFAULT.equals(name);
        case TablePackage.DCELL__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        case TablePackage.DCELL__TABLE_ELEMENT_MAPPING:
            return basicGetTableElementMapping() != null;
        case TablePackage.DCELL__LABEL:
            return DCellImpl.LABEL_EDEFAULT == null ? label != null : !DCellImpl.LABEL_EDEFAULT.equals(label);
        case TablePackage.DCELL__LINE:
            return getLine() != null;
        case TablePackage.DCELL__COLUMN:
            return column != null;
        case TablePackage.DCELL__CURRENT_STYLE:
            return currentStyle != null;
        case TablePackage.DCELL__UPDATER:
            return basicGetUpdater() != null;
        case TablePackage.DCELL__INTERSECTION_MAPPING:
            return intersectionMapping != null;
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
        if (baseClass == DMappingBased.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DStylizable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRepresentationElement.class) {
            switch (derivedFeatureID) {
            case TablePackage.DCELL__NAME:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;
            case TablePackage.DCELL__SEMANTIC_ELEMENTS:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (derivedFeatureID) {
            case TablePackage.DCELL__TABLE_ELEMENT_MAPPING:
                return TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING;
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
        if (baseClass == DMappingBased.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DStylizable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRepresentationElement.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.DREPRESENTATION_ELEMENT__NAME:
                return TablePackage.DCELL__NAME;
            case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
                return TablePackage.DCELL__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (baseFeatureID) {
            case TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING:
                return TablePackage.DCELL__TABLE_ELEMENT_MAPPING;
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
        result.append(')');
        return result.toString();
    }

} // DCellImpl
