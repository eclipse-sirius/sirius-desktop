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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DTable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl#getTarget
 * <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl#getLines
 * <em>Lines</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl#getColumns
 * <em>Columns</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl#getHeaderColumnWidth
 * <em>Header Column Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl#getDescription
 * <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DTableImpl extends DRepresentationImpl implements DTable {
    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target;

    /**
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLines()
     * @generated
     * @ordered
     */
    protected EList<DLine> lines;

    /**
     * The cached value of the '{@link #getColumns() <em>Columns</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getColumns()
     * @generated
     * @ordered
     */
    protected EList<DColumn> columns;

    /**
     * The default value of the '{@link #getHeaderColumnWidth()
     * <em>Header Column Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getHeaderColumnWidth()
     * @generated
     * @ordered
     */
    protected static final int HEADER_COLUMN_WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeaderColumnWidth()
     * <em>Header Column Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getHeaderColumnWidth()
     * @generated
     * @ordered
     */
    protected int headerColumnWidth = DTableImpl.HEADER_COLUMN_WIDTH_EDEFAULT;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected TableDescription description;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DTableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DTABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DTABLE__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTarget(EObject newTarget) {
        EObject oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DLine> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentWithInverseEList<DLine>(DLine.class, this, TablePackage.DTABLE__LINES, TablePackage.DLINE__CONTAINER);
        }
        return lines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DColumn> getColumns() {
        if (columns == null) {
            columns = new EObjectContainmentWithInverseEList<DColumn>(DColumn.class, this, TablePackage.DTABLE__COLUMNS, TablePackage.DCOLUMN__TABLE);
        }
        return columns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TableDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (TableDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DTABLE__DESCRIPTION, oldDescription, description));
                }
            }
        }
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TableDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDescription(TableDescription newDescription) {
        TableDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getHeaderColumnWidth() {
        return headerColumnWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setHeaderColumnWidth(int newHeaderColumnWidth) {
        int oldHeaderColumnWidth = headerColumnWidth;
        headerColumnWidth = newHeaderColumnWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE__HEADER_COLUMN_WIDTH, oldHeaderColumnWidth, headerColumnWidth));
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
        case TablePackage.DTABLE__LINES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getLines()).basicAdd(otherEnd, msgs);
        case TablePackage.DTABLE__COLUMNS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getColumns()).basicAdd(otherEnd, msgs);
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
        case TablePackage.DTABLE__LINES:
            return ((InternalEList<?>) getLines()).basicRemove(otherEnd, msgs);
        case TablePackage.DTABLE__COLUMNS:
            return ((InternalEList<?>) getColumns()).basicRemove(otherEnd, msgs);
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
        case TablePackage.DTABLE__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case TablePackage.DTABLE__LINES:
            return getLines();
        case TablePackage.DTABLE__COLUMNS:
            return getColumns();
        case TablePackage.DTABLE__HEADER_COLUMN_WIDTH:
            return getHeaderColumnWidth();
        case TablePackage.DTABLE__DESCRIPTION:
            if (resolve) {
                return getDescription();
            }
            return basicGetDescription();
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
        case TablePackage.DTABLE__TARGET:
            setTarget((EObject) newValue);
            return;
        case TablePackage.DTABLE__LINES:
            getLines().clear();
            getLines().addAll((Collection<? extends DLine>) newValue);
            return;
        case TablePackage.DTABLE__COLUMNS:
            getColumns().clear();
            getColumns().addAll((Collection<? extends DColumn>) newValue);
            return;
        case TablePackage.DTABLE__HEADER_COLUMN_WIDTH:
            setHeaderColumnWidth((Integer) newValue);
            return;
        case TablePackage.DTABLE__DESCRIPTION:
            setDescription((TableDescription) newValue);
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
        case TablePackage.DTABLE__TARGET:
            setTarget((EObject) null);
            return;
        case TablePackage.DTABLE__LINES:
            getLines().clear();
            return;
        case TablePackage.DTABLE__COLUMNS:
            getColumns().clear();
            return;
        case TablePackage.DTABLE__HEADER_COLUMN_WIDTH:
            setHeaderColumnWidth(DTableImpl.HEADER_COLUMN_WIDTH_EDEFAULT);
            return;
        case TablePackage.DTABLE__DESCRIPTION:
            setDescription((TableDescription) null);
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
        case TablePackage.DTABLE__TARGET:
            return target != null;
        case TablePackage.DTABLE__LINES:
            return lines != null && !lines.isEmpty();
        case TablePackage.DTABLE__COLUMNS:
            return columns != null && !columns.isEmpty();
        case TablePackage.DTABLE__HEADER_COLUMN_WIDTH:
            return headerColumnWidth != DTableImpl.HEADER_COLUMN_WIDTH_EDEFAULT;
        case TablePackage.DTABLE__DESCRIPTION:
            return description != null;
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
        if (baseClass == DSemanticDecorator.class) {
            switch (derivedFeatureID) {
            case TablePackage.DTABLE__TARGET:
                return ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;
            default:
                return -1;
            }
        }
        if (baseClass == LineContainer.class) {
            switch (derivedFeatureID) {
            case TablePackage.DTABLE__LINES:
                return TablePackage.LINE_CONTAINER__LINES;
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
        if (baseClass == DSemanticDecorator.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.DSEMANTIC_DECORATOR__TARGET:
                return TablePackage.DTABLE__TARGET;
            default:
                return -1;
            }
        }
        if (baseClass == LineContainer.class) {
            switch (baseFeatureID) {
            case TablePackage.LINE_CONTAINER__LINES:
                return TablePackage.DTABLE__LINES;
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
        result.append(" (headerColumnWidth: "); //$NON-NLS-1$
        result.append(headerColumnWidth);
        result.append(')');
        return result.toString();
    }

} // DTableImpl
