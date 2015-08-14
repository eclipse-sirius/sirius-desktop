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
package org.eclipse.sirius.table.metamodel.table.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DColumn</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getCells
 * <em>Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getOriginMapping
 * <em>Origin Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getTable
 * <em>Table</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getOrderedCells
 * <em>Ordered Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#isVisible
 * <em>Visible</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl#getCurrentStyle
 * <em>Current Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DColumnImpl extends DTableElementImpl implements DColumn {
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
    protected String label = DColumnImpl.LABEL_EDEFAULT;

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
    protected boolean visible = DColumnImpl.VISIBLE_EDEFAULT;

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
    protected int width = DColumnImpl.WIDTH_EDEFAULT;

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
    protected DColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DCOLUMN;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__LABEL, oldLabel, label));
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
            cells = new EObjectWithInverseResolvingEList<DCell>(DCell.class, this, TablePackage.DCOLUMN__CELLS, TablePackage.DCELL__COLUMN);
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DCOLUMN__ORIGIN_MAPPING, oldOriginMapping, originMapping));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__ORIGIN_MAPPING, oldOriginMapping, originMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTable getTable() {
        if (eContainerFeatureID() != TablePackage.DCOLUMN__TABLE) {
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
        msgs = eBasicSetContainer((InternalEObject) newTable, TablePackage.DCOLUMN__TABLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTable(DTable newTable) {
        if (newTable != eInternalContainer() || (eContainerFeatureID() != TablePackage.DCOLUMN__TABLE && newTable != null)) {
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__TABLE, newTable, newTable));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__VISIBLE, oldVisible, visible));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__WIDTH, oldWidth, width));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__CURRENT_STYLE, oldCurrentStyle, newCurrentStyle);
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
                msgs = ((InternalEObject) currentStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DCOLUMN__CURRENT_STYLE, null, msgs);
            }
            if (newCurrentStyle != null) {
                msgs = ((InternalEObject) newCurrentStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DCOLUMN__CURRENT_STYLE, null, msgs);
            }
            msgs = basicSetCurrentStyle(newCurrentStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCOLUMN__CURRENT_STYLE, newCurrentStyle, newCurrentStyle));
        }
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
        case TablePackage.DCOLUMN__CELLS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getCells()).basicAdd(otherEnd, msgs);
        case TablePackage.DCOLUMN__TABLE:
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
        case TablePackage.DCOLUMN__CELLS:
            return ((InternalEList<?>) getCells()).basicRemove(otherEnd, msgs);
        case TablePackage.DCOLUMN__TABLE:
            return basicSetTable(null, msgs);
        case TablePackage.DCOLUMN__CURRENT_STYLE:
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
        case TablePackage.DCOLUMN__TABLE:
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
        case TablePackage.DCOLUMN__LABEL:
            return getLabel();
        case TablePackage.DCOLUMN__CELLS:
            return getCells();
        case TablePackage.DCOLUMN__ORIGIN_MAPPING:
            if (resolve) {
                return getOriginMapping();
            }
            return basicGetOriginMapping();
        case TablePackage.DCOLUMN__TABLE:
            return getTable();
        case TablePackage.DCOLUMN__ORDERED_CELLS:
            return getOrderedCells();
        case TablePackage.DCOLUMN__VISIBLE:
            return isVisible();
        case TablePackage.DCOLUMN__WIDTH:
            return getWidth();
        case TablePackage.DCOLUMN__CURRENT_STYLE:
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
        case TablePackage.DCOLUMN__LABEL:
            setLabel((String) newValue);
            return;
        case TablePackage.DCOLUMN__CELLS:
            getCells().clear();
            getCells().addAll((Collection<? extends DCell>) newValue);
            return;
        case TablePackage.DCOLUMN__ORIGIN_MAPPING:
            setOriginMapping((ColumnMapping) newValue);
            return;
        case TablePackage.DCOLUMN__TABLE:
            setTable((DTable) newValue);
            return;
        case TablePackage.DCOLUMN__VISIBLE:
            setVisible((Boolean) newValue);
            return;
        case TablePackage.DCOLUMN__WIDTH:
            setWidth((Integer) newValue);
            return;
        case TablePackage.DCOLUMN__CURRENT_STYLE:
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
        case TablePackage.DCOLUMN__LABEL:
            setLabel(DColumnImpl.LABEL_EDEFAULT);
            return;
        case TablePackage.DCOLUMN__CELLS:
            getCells().clear();
            return;
        case TablePackage.DCOLUMN__ORIGIN_MAPPING:
            setOriginMapping((ColumnMapping) null);
            return;
        case TablePackage.DCOLUMN__TABLE:
            setTable((DTable) null);
            return;
        case TablePackage.DCOLUMN__VISIBLE:
            setVisible(DColumnImpl.VISIBLE_EDEFAULT);
            return;
        case TablePackage.DCOLUMN__WIDTH:
            setWidth(DColumnImpl.WIDTH_EDEFAULT);
            return;
        case TablePackage.DCOLUMN__CURRENT_STYLE:
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
        case TablePackage.DCOLUMN__LABEL:
            return DColumnImpl.LABEL_EDEFAULT == null ? label != null : !DColumnImpl.LABEL_EDEFAULT.equals(label);
        case TablePackage.DCOLUMN__CELLS:
            return cells != null && !cells.isEmpty();
        case TablePackage.DCOLUMN__ORIGIN_MAPPING:
            return originMapping != null;
        case TablePackage.DCOLUMN__TABLE:
            return getTable() != null;
        case TablePackage.DCOLUMN__ORDERED_CELLS:
            return !getOrderedCells().isEmpty();
        case TablePackage.DCOLUMN__VISIBLE:
            return visible != DColumnImpl.VISIBLE_EDEFAULT;
        case TablePackage.DCOLUMN__WIDTH:
            return width != DColumnImpl.WIDTH_EDEFAULT;
        case TablePackage.DCOLUMN__CURRENT_STYLE:
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
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", visible: "); //$NON-NLS-1$
        result.append(visible);
        result.append(", width: "); //$NON-NLS-1$
        result.append(width);
        result.append(')');
        return result.toString();
    }

} // DColumnImpl
