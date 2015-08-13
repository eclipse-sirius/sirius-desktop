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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
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
 * <em><b>DTarget Column</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getTableElementMapping
 * <em>Table Element Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getCells
 * <em>Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getOriginMapping
 * <em>Origin Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getTable
 * <em>Table</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getOrderedCells
 * <em>Ordered Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#isVisible
 * <em>Visible</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl#getCurrentStyle
 * <em>Current Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DTargetColumnImpl extends DSemanticDecoratorImpl implements DTargetColumn {
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
    protected String name = DTargetColumnImpl.NAME_EDEFAULT;

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
    protected String label = DTargetColumnImpl.LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getCells() <em>Cells</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCells()
     * @generated
     * @ordered
     */
    protected EList<DCell> cells;

    /**
     * The cached value of the '{@link #getOriginMapping()
     * <em>Origin Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getOriginMapping()
     * @generated
     * @ordered
     */
    protected ColumnMapping originMapping;

    /**
     * The default value of the '{@link #isVisible() <em>Visible</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isVisible()
     * @generated
     * @ordered
     */
    protected static final boolean VISIBLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isVisible() <em>Visible</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isVisible()
     * @generated
     * @ordered
     */
    protected boolean visible = DTargetColumnImpl.VISIBLE_EDEFAULT;

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = DTargetColumnImpl.WIDTH_EDEFAULT;

    /**
     * The cached value of the '{@link #getCurrentStyle()
     * <em>Current Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCurrentStyle()
     * @generated
     * @ordered
     */
    protected DTableElementStyle currentStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DTargetColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DTARGET_COLUMN;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__NAME, oldName, name));
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
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS);
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
     * @not-generated
     */
    public TableMapping basicGetTableElementMapping() {
        return (TableMapping) getMapping();
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DCell> getCells() {
        if (cells == null) {
            cells = new EObjectWithInverseResolvingEList<DCell>(DCell.class, this, TablePackage.DTARGET_COLUMN__CELLS, TablePackage.DCELL__COLUMN);
        }
        return cells;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColumnMapping getOriginMapping() {
        if (originMapping != null && originMapping.eIsProxy()) {
            InternalEObject oldOriginMapping = (InternalEObject) originMapping;
            originMapping = (ColumnMapping) eResolveProxy(oldOriginMapping);
            if (originMapping != oldOriginMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING, oldOriginMapping, originMapping));
                }
            }
        }
        return originMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColumnMapping basicGetOriginMapping() {
        return originMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setOriginMapping(ColumnMapping newOriginMapping) {
        ColumnMapping oldOriginMapping = originMapping;
        originMapping = newOriginMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING, oldOriginMapping, originMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTable getTable() {
        if (eContainerFeatureID() != TablePackage.DTARGET_COLUMN__TABLE) {
            return null;
        }
        return (DTable) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTable(DTable newTable, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newTable, TablePackage.DTARGET_COLUMN__TABLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTable(DTable newTable) {
        if (newTable != eInternalContainer() || (eContainerFeatureID() != TablePackage.DTARGET_COLUMN__TABLE && newTable != null)) {
            if (EcoreUtil.isAncestor(this, newTable)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newTable != null) {
                msgs = ((InternalEObject) newTable).eInverseAdd(this, TablePackage.DTABLE__COLUMNS, DTable.class, msgs);
            }
            msgs = basicSetTable(newTable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__TABLE, newTable, newTable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DCell> getOrderedCells() {
        // TODO: implement this method to return the 'Ordered Cells' reference
        // list
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
    public boolean isVisible() {
        return visible;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVisible(boolean newVisible) {
        boolean oldVisible = visible;
        visible = newVisible;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__VISIBLE, oldVisible, visible));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__WIDTH, oldWidth, width));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTableElementStyle getCurrentStyle() {
        return currentStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCurrentStyle(DTableElementStyle newCurrentStyle, NotificationChain msgs) {
        DTableElementStyle oldCurrentStyle = currentStyle;
        currentStyle = newCurrentStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__CURRENT_STYLE, oldCurrentStyle, newCurrentStyle);
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
    public void setCurrentStyle(DTableElementStyle newCurrentStyle) {
        if (newCurrentStyle != currentStyle) {
            NotificationChain msgs = null;
            if (currentStyle != null) {
                msgs = ((InternalEObject) currentStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DTARGET_COLUMN__CURRENT_STYLE, null, msgs);
            }
            if (newCurrentStyle != null) {
                msgs = ((InternalEObject) newCurrentStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DTARGET_COLUMN__CURRENT_STYLE, null, msgs);
            }
            msgs = basicSetCurrentStyle(newCurrentStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTARGET_COLUMN__CURRENT_STYLE, newCurrentStyle, newCurrentStyle));
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
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TablePackage.DTARGET_COLUMN__CELLS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getCells()).basicAdd(otherEnd, msgs);
        case TablePackage.DTARGET_COLUMN__TABLE:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetTable((DTable) otherEnd, msgs);
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
        case TablePackage.DTARGET_COLUMN__CELLS:
            return ((InternalEList<?>) getCells()).basicRemove(otherEnd, msgs);
        case TablePackage.DTARGET_COLUMN__TABLE:
            return basicSetTable(null, msgs);
        case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
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
        case TablePackage.DTARGET_COLUMN__TABLE:
            return eInternalContainer().eInverseRemove(this, TablePackage.DTABLE__COLUMNS, DTable.class, msgs);
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
        case TablePackage.DTARGET_COLUMN__NAME:
            return getName();
        case TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case TablePackage.DTARGET_COLUMN__TABLE_ELEMENT_MAPPING:
            if (resolve) {
                return getTableElementMapping();
            }
            return basicGetTableElementMapping();
        case TablePackage.DTARGET_COLUMN__LABEL:
            return getLabel();
        case TablePackage.DTARGET_COLUMN__CELLS:
            return getCells();
        case TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING:
            if (resolve) {
                return getOriginMapping();
            }
            return basicGetOriginMapping();
        case TablePackage.DTARGET_COLUMN__TABLE:
            return getTable();
        case TablePackage.DTARGET_COLUMN__ORDERED_CELLS:
            return getOrderedCells();
        case TablePackage.DTARGET_COLUMN__VISIBLE:
            return isVisible();
        case TablePackage.DTARGET_COLUMN__WIDTH:
            return getWidth();
        case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
            return getCurrentStyle();
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
        case TablePackage.DTARGET_COLUMN__NAME:
            setName((String) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__LABEL:
            setLabel((String) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__CELLS:
            getCells().clear();
            getCells().addAll((Collection<? extends DCell>) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING:
            setOriginMapping((ColumnMapping) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__TABLE:
            setTable((DTable) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__VISIBLE:
            setVisible((Boolean) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__WIDTH:
            setWidth((Integer) newValue);
            return;
        case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
            setCurrentStyle((DTableElementStyle) newValue);
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
        case TablePackage.DTARGET_COLUMN__NAME:
            setName(DTargetColumnImpl.NAME_EDEFAULT);
            return;
        case TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            return;
        case TablePackage.DTARGET_COLUMN__LABEL:
            setLabel(DTargetColumnImpl.LABEL_EDEFAULT);
            return;
        case TablePackage.DTARGET_COLUMN__CELLS:
            getCells().clear();
            return;
        case TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING:
            setOriginMapping((ColumnMapping) null);
            return;
        case TablePackage.DTARGET_COLUMN__TABLE:
            setTable((DTable) null);
            return;
        case TablePackage.DTARGET_COLUMN__VISIBLE:
            setVisible(DTargetColumnImpl.VISIBLE_EDEFAULT);
            return;
        case TablePackage.DTARGET_COLUMN__WIDTH:
            setWidth(DTargetColumnImpl.WIDTH_EDEFAULT);
            return;
        case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
            setCurrentStyle((DTableElementStyle) null);
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
        case TablePackage.DTARGET_COLUMN__NAME:
            return DTargetColumnImpl.NAME_EDEFAULT == null ? name != null : !DTargetColumnImpl.NAME_EDEFAULT.equals(name);
        case TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        case TablePackage.DTARGET_COLUMN__TABLE_ELEMENT_MAPPING:
            return basicGetTableElementMapping() != null;
        case TablePackage.DTARGET_COLUMN__LABEL:
            return DTargetColumnImpl.LABEL_EDEFAULT == null ? label != null : !DTargetColumnImpl.LABEL_EDEFAULT.equals(label);
        case TablePackage.DTARGET_COLUMN__CELLS:
            return cells != null && !cells.isEmpty();
        case TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING:
            return originMapping != null;
        case TablePackage.DTARGET_COLUMN__TABLE:
            return getTable() != null;
        case TablePackage.DTARGET_COLUMN__ORDERED_CELLS:
            return !getOrderedCells().isEmpty();
        case TablePackage.DTARGET_COLUMN__VISIBLE:
            return visible != DTargetColumnImpl.VISIBLE_EDEFAULT;
        case TablePackage.DTARGET_COLUMN__WIDTH:
            return width != DTargetColumnImpl.WIDTH_EDEFAULT;
        case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
            return currentStyle != null;
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
            case TablePackage.DTARGET_COLUMN__NAME:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;
            case TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (derivedFeatureID) {
            case TablePackage.DTARGET_COLUMN__TABLE_ELEMENT_MAPPING:
                return TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING;
            default:
                return -1;
            }
        }
        if (baseClass == DColumn.class) {
            switch (derivedFeatureID) {
            case TablePackage.DTARGET_COLUMN__LABEL:
                return TablePackage.DCOLUMN__LABEL;
            case TablePackage.DTARGET_COLUMN__CELLS:
                return TablePackage.DCOLUMN__CELLS;
            case TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING:
                return TablePackage.DCOLUMN__ORIGIN_MAPPING;
            case TablePackage.DTARGET_COLUMN__TABLE:
                return TablePackage.DCOLUMN__TABLE;
            case TablePackage.DTARGET_COLUMN__ORDERED_CELLS:
                return TablePackage.DCOLUMN__ORDERED_CELLS;
            case TablePackage.DTARGET_COLUMN__VISIBLE:
                return TablePackage.DCOLUMN__VISIBLE;
            case TablePackage.DTARGET_COLUMN__WIDTH:
                return TablePackage.DCOLUMN__WIDTH;
            case TablePackage.DTARGET_COLUMN__CURRENT_STYLE:
                return TablePackage.DCOLUMN__CURRENT_STYLE;
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
                return TablePackage.DTARGET_COLUMN__NAME;
            case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
                return TablePackage.DTARGET_COLUMN__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (baseFeatureID) {
            case TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING:
                return TablePackage.DTARGET_COLUMN__TABLE_ELEMENT_MAPPING;
            default:
                return -1;
            }
        }
        if (baseClass == DColumn.class) {
            switch (baseFeatureID) {
            case TablePackage.DCOLUMN__LABEL:
                return TablePackage.DTARGET_COLUMN__LABEL;
            case TablePackage.DCOLUMN__CELLS:
                return TablePackage.DTARGET_COLUMN__CELLS;
            case TablePackage.DCOLUMN__ORIGIN_MAPPING:
                return TablePackage.DTARGET_COLUMN__ORIGIN_MAPPING;
            case TablePackage.DCOLUMN__TABLE:
                return TablePackage.DTARGET_COLUMN__TABLE;
            case TablePackage.DCOLUMN__ORDERED_CELLS:
                return TablePackage.DTARGET_COLUMN__ORDERED_CELLS;
            case TablePackage.DCOLUMN__VISIBLE:
                return TablePackage.DTARGET_COLUMN__VISIBLE;
            case TablePackage.DCOLUMN__WIDTH:
                return TablePackage.DTARGET_COLUMN__WIDTH;
            case TablePackage.DCOLUMN__CURRENT_STYLE:
                return TablePackage.DTARGET_COLUMN__CURRENT_STYLE;
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
        result.append(", visible: "); //$NON-NLS-1$
        result.append(visible);
        result.append(", width: "); //$NON-NLS-1$
        result.append(width);
        result.append(')');
        return result.toString();
    }

} // DTargetColumnImpl
