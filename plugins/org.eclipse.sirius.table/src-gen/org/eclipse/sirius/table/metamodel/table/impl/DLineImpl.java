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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DLine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getTableElementMapping
 * <em>Table Element Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getOriginMapping
 * <em>Origin Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#isVisible
 * <em>Visible</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#isCollapsed
 * <em>Collapsed</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getCells
 * <em>Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getContainer
 * <em>Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getOrderedCells
 * <em>Ordered Cells</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl#getCurrentStyle
 * <em>Current Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DLineImpl extends LineContainerImpl implements DLine {
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
    protected String name = DLineImpl.NAME_EDEFAULT;

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
    protected String label = DLineImpl.LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getOriginMapping()
     * <em>Origin Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getOriginMapping()
     * @generated
     * @ordered
     */
    protected LineMapping originMapping;

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
    protected boolean visible = DLineImpl.VISIBLE_EDEFAULT;

    /**
     * The default value of the '{@link #isCollapsed() <em>Collapsed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isCollapsed()
     * @generated
     * @ordered
     */
    protected static final boolean COLLAPSED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCollapsed() <em>Collapsed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isCollapsed()
     * @generated
     * @ordered
     */
    protected boolean collapsed = DLineImpl.COLLAPSED_EDEFAULT;

    /**
     * The cached value of the '{@link #getCells() <em>Cells</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCells()
     * @generated
     * @ordered
     */
    protected EList<DCell> cells;

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
    protected DLineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DLINE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__NAME, oldName, name));
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
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, TablePackage.DLINE__SEMANTIC_ELEMENTS);
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LineMapping getOriginMapping() {
        if (originMapping != null && originMapping.eIsProxy()) {
            InternalEObject oldOriginMapping = (InternalEObject) originMapping;
            originMapping = (LineMapping) eResolveProxy(oldOriginMapping);
            if (originMapping != oldOriginMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DLINE__ORIGIN_MAPPING, oldOriginMapping, originMapping));
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
    public LineMapping basicGetOriginMapping() {
        return originMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setOriginMapping(LineMapping newOriginMapping) {
        LineMapping oldOriginMapping = originMapping;
        originMapping = newOriginMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__ORIGIN_MAPPING, oldOriginMapping, originMapping));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__VISIBLE, oldVisible, visible));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isCollapsed() {
        return collapsed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCollapsed(boolean newCollapsed) {
        boolean oldCollapsed = collapsed;
        collapsed = newCollapsed;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__COLLAPSED, oldCollapsed, collapsed));
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
            cells = new EObjectContainmentWithInverseEList<DCell>(DCell.class, this, TablePackage.DLINE__CELLS, TablePackage.DCELL__LINE);
        }
        return cells;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LineContainer getContainer() {
        if (eContainerFeatureID() != TablePackage.DLINE__CONTAINER) {
            return null;
        }
        return (LineContainer) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainer(LineContainer newContainer, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newContainer, TablePackage.DLINE__CONTAINER, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setContainer(LineContainer newContainer) {
        if (newContainer != eInternalContainer() || (eContainerFeatureID() != TablePackage.DLINE__CONTAINER && newContainer != null)) {
            if (EcoreUtil.isAncestor(this, newContainer)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseAdd(this, TablePackage.LINE_CONTAINER__LINES, LineContainer.class, msgs);
            }
            msgs = basicSetContainer(newContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__CONTAINER, newContainer, newContainer));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__CURRENT_STYLE, oldCurrentStyle, newCurrentStyle);
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
                msgs = ((InternalEObject) currentStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DLINE__CURRENT_STYLE, null, msgs);
            }
            if (newCurrentStyle != null) {
                msgs = ((InternalEObject) newCurrentStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TablePackage.DLINE__CURRENT_STYLE, null, msgs);
            }
            msgs = basicSetCurrentStyle(newCurrentStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DLINE__CURRENT_STYLE, newCurrentStyle, newCurrentStyle));
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
        case TablePackage.DLINE__CELLS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getCells()).basicAdd(otherEnd, msgs);
        case TablePackage.DLINE__CONTAINER:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetContainer((LineContainer) otherEnd, msgs);
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
        case TablePackage.DLINE__CELLS:
            return ((InternalEList<?>) getCells()).basicRemove(otherEnd, msgs);
        case TablePackage.DLINE__CONTAINER:
            return basicSetContainer(null, msgs);
        case TablePackage.DLINE__CURRENT_STYLE:
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
        case TablePackage.DLINE__CONTAINER:
            return eInternalContainer().eInverseRemove(this, TablePackage.LINE_CONTAINER__LINES, LineContainer.class, msgs);
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
        case TablePackage.DLINE__NAME:
            return getName();
        case TablePackage.DLINE__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case TablePackage.DLINE__TABLE_ELEMENT_MAPPING:
            if (resolve) {
                return getTableElementMapping();
            }
            return basicGetTableElementMapping();
        case TablePackage.DLINE__LABEL:
            return getLabel();
        case TablePackage.DLINE__ORIGIN_MAPPING:
            if (resolve) {
                return getOriginMapping();
            }
            return basicGetOriginMapping();
        case TablePackage.DLINE__VISIBLE:
            return isVisible();
        case TablePackage.DLINE__COLLAPSED:
            return isCollapsed();
        case TablePackage.DLINE__CELLS:
            return getCells();
        case TablePackage.DLINE__CONTAINER:
            return getContainer();
        case TablePackage.DLINE__ORDERED_CELLS:
            return getOrderedCells();
        case TablePackage.DLINE__CURRENT_STYLE:
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
        case TablePackage.DLINE__NAME:
            setName((String) newValue);
            return;
        case TablePackage.DLINE__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
            return;
        case TablePackage.DLINE__LABEL:
            setLabel((String) newValue);
            return;
        case TablePackage.DLINE__ORIGIN_MAPPING:
            setOriginMapping((LineMapping) newValue);
            return;
        case TablePackage.DLINE__VISIBLE:
            setVisible((Boolean) newValue);
            return;
        case TablePackage.DLINE__COLLAPSED:
            setCollapsed((Boolean) newValue);
            return;
        case TablePackage.DLINE__CELLS:
            getCells().clear();
            getCells().addAll((Collection<? extends DCell>) newValue);
            return;
        case TablePackage.DLINE__CONTAINER:
            setContainer((LineContainer) newValue);
            return;
        case TablePackage.DLINE__CURRENT_STYLE:
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
        case TablePackage.DLINE__NAME:
            setName(DLineImpl.NAME_EDEFAULT);
            return;
        case TablePackage.DLINE__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            return;
        case TablePackage.DLINE__LABEL:
            setLabel(DLineImpl.LABEL_EDEFAULT);
            return;
        case TablePackage.DLINE__ORIGIN_MAPPING:
            setOriginMapping((LineMapping) null);
            return;
        case TablePackage.DLINE__VISIBLE:
            setVisible(DLineImpl.VISIBLE_EDEFAULT);
            return;
        case TablePackage.DLINE__COLLAPSED:
            setCollapsed(DLineImpl.COLLAPSED_EDEFAULT);
            return;
        case TablePackage.DLINE__CELLS:
            getCells().clear();
            return;
        case TablePackage.DLINE__CONTAINER:
            setContainer((LineContainer) null);
            return;
        case TablePackage.DLINE__CURRENT_STYLE:
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
        case TablePackage.DLINE__NAME:
            return DLineImpl.NAME_EDEFAULT == null ? name != null : !DLineImpl.NAME_EDEFAULT.equals(name);
        case TablePackage.DLINE__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        case TablePackage.DLINE__TABLE_ELEMENT_MAPPING:
            return basicGetTableElementMapping() != null;
        case TablePackage.DLINE__LABEL:
            return DLineImpl.LABEL_EDEFAULT == null ? label != null : !DLineImpl.LABEL_EDEFAULT.equals(label);
        case TablePackage.DLINE__ORIGIN_MAPPING:
            return originMapping != null;
        case TablePackage.DLINE__VISIBLE:
            return visible != DLineImpl.VISIBLE_EDEFAULT;
        case TablePackage.DLINE__COLLAPSED:
            return collapsed != DLineImpl.COLLAPSED_EDEFAULT;
        case TablePackage.DLINE__CELLS:
            return cells != null && !cells.isEmpty();
        case TablePackage.DLINE__CONTAINER:
            return getContainer() != null;
        case TablePackage.DLINE__ORDERED_CELLS:
            return !getOrderedCells().isEmpty();
        case TablePackage.DLINE__CURRENT_STYLE:
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
            case TablePackage.DLINE__NAME:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;
            case TablePackage.DLINE__SEMANTIC_ELEMENTS:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (derivedFeatureID) {
            case TablePackage.DLINE__TABLE_ELEMENT_MAPPING:
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
                return TablePackage.DLINE__NAME;
            case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
                return TablePackage.DLINE__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTableElement.class) {
            switch (baseFeatureID) {
            case TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING:
                return TablePackage.DLINE__TABLE_ELEMENT_MAPPING;
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
        result.append(", collapsed: "); //$NON-NLS-1$
        result.append(collapsed);
        result.append(')');
        return result.toString();
    }

} // DLineImpl
