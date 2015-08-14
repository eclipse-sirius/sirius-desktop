/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.impl;

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
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DTree Item</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getTreeElementMapping
 * <em>Tree Element Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#isExpanded <em>Expanded
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getOwnedStyle <em>Owned
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getActualMapping <em>
 * Actual Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getContainer <em>
 * Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getStyleUpdater <em>
 * Style Updater</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemImpl#getUpdater <em>Updater
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DTreeItemImpl extends DTreeItemContainerImpl implements DTreeItem {
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
    protected String name = DTreeItemImpl.NAME_EDEFAULT;

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
     * The default value of the '{@link #isExpanded() <em>Expanded</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isExpanded()
     * @generated
     * @ordered
     */
    protected static final boolean EXPANDED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isExpanded() <em>Expanded</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isExpanded()
     * @generated
     * @ordered
     */
    protected boolean expanded = DTreeItemImpl.EXPANDED_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected TreeItemStyle ownedStyle;

    /**
     * The cached value of the '{@link #getActualMapping()
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getActualMapping()
     * @generated
     * @ordered
     */
    protected TreeItemMapping actualMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DTreeItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TreePackage.Literals.DTREE_ITEM;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__NAME, oldName, name));
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
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS);
        }
        return semanticElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeMapping getTreeElementMapping() {
        TreeMapping treeElementMapping = basicGetTreeElementMapping();
        return treeElementMapping != null && treeElementMapping.eIsProxy() ? (TreeMapping) eResolveProxy((InternalEObject) treeElementMapping) : treeElementMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeMapping basicGetTreeElementMapping() {
        // TODO: implement this method to return the 'Tree Element Mapping'
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
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setExpanded(boolean newExpanded) {
        boolean oldExpanded = expanded;
        expanded = newExpanded;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__EXPANDED, oldExpanded, expanded));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemStyle getOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(TreeItemStyle newOwnedStyle, NotificationChain msgs) {
        TreeItemStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(TreeItemStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TreePackage.DTREE_ITEM__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TreePackage.DTREE_ITEM__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemMapping getActualMapping() {
        if (actualMapping != null && actualMapping.eIsProxy()) {
            InternalEObject oldActualMapping = (InternalEObject) actualMapping;
            actualMapping = (TreeItemMapping) eResolveProxy(oldActualMapping);
            if (actualMapping != oldActualMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.DTREE_ITEM__ACTUAL_MAPPING, oldActualMapping, actualMapping));
                }
            }
        }
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeItemMapping basicGetActualMapping() {
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setActualMapping(TreeItemMapping newActualMapping) {
        TreeItemMapping oldActualMapping = actualMapping;
        actualMapping = newActualMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__ACTUAL_MAPPING, oldActualMapping, actualMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTreeItemContainer getContainer() {
        if (eContainerFeatureID() != TreePackage.DTREE_ITEM__CONTAINER) {
            return null;
        }
        return (DTreeItemContainer) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainer(DTreeItemContainer newContainer, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newContainer, TreePackage.DTREE_ITEM__CONTAINER, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setContainer(DTreeItemContainer newContainer) {
        if (newContainer != eInternalContainer() || (eContainerFeatureID() != TreePackage.DTREE_ITEM__CONTAINER && newContainer != null)) {
            if (EcoreUtil.isAncestor(this, newContainer)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseAdd(this, TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS, DTreeItemContainer.class, msgs);
            }
            msgs = basicSetContainer(newContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE_ITEM__CONTAINER, newContainer, newContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public StyleUpdater getStyleUpdater() {
        StyleUpdater styleUpdater = basicGetStyleUpdater();
        return styleUpdater != null && styleUpdater.eIsProxy() ? (StyleUpdater) eResolveProxy((InternalEObject) styleUpdater) : styleUpdater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public StyleUpdater basicGetStyleUpdater() {
        // TODO: implement this method to return the 'Style Updater' reference
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
    public TreeItemUpdater getUpdater() {
        TreeItemUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (TreeItemUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeItemUpdater basicGetUpdater() {
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
    public void setUpdater(TreeItemUpdater newUpdater) {
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
        case TreePackage.DTREE_ITEM__CONTAINER:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetContainer((DTreeItemContainer) otherEnd, msgs);
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
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            return basicSetOwnedStyle(null, msgs);
        case TreePackage.DTREE_ITEM__CONTAINER:
            return basicSetContainer(null, msgs);
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
        case TreePackage.DTREE_ITEM__CONTAINER:
            return eInternalContainer().eInverseRemove(this, TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS, DTreeItemContainer.class, msgs);
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
        case TreePackage.DTREE_ITEM__NAME:
            return getName();
        case TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case TreePackage.DTREE_ITEM__TREE_ELEMENT_MAPPING:
            if (resolve) {
                return getTreeElementMapping();
            }
            return basicGetTreeElementMapping();
        case TreePackage.DTREE_ITEM__EXPANDED:
            return isExpanded();
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            return getOwnedStyle();
        case TreePackage.DTREE_ITEM__ACTUAL_MAPPING:
            if (resolve) {
                return getActualMapping();
            }
            return basicGetActualMapping();
        case TreePackage.DTREE_ITEM__CONTAINER:
            return getContainer();
        case TreePackage.DTREE_ITEM__STYLE_UPDATER:
            if (resolve) {
                return getStyleUpdater();
            }
            return basicGetStyleUpdater();
        case TreePackage.DTREE_ITEM__UPDATER:
            if (resolve) {
                return getUpdater();
            }
            return basicGetUpdater();
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
        case TreePackage.DTREE_ITEM__NAME:
            setName((String) newValue);
            return;
        case TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
            return;
        case TreePackage.DTREE_ITEM__EXPANDED:
            setExpanded((Boolean) newValue);
            return;
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            setOwnedStyle((TreeItemStyle) newValue);
            return;
        case TreePackage.DTREE_ITEM__ACTUAL_MAPPING:
            setActualMapping((TreeItemMapping) newValue);
            return;
        case TreePackage.DTREE_ITEM__CONTAINER:
            setContainer((DTreeItemContainer) newValue);
            return;
        case TreePackage.DTREE_ITEM__UPDATER:
            setUpdater((TreeItemUpdater) newValue);
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
        case TreePackage.DTREE_ITEM__NAME:
            setName(DTreeItemImpl.NAME_EDEFAULT);
            return;
        case TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            return;
        case TreePackage.DTREE_ITEM__EXPANDED:
            setExpanded(DTreeItemImpl.EXPANDED_EDEFAULT);
            return;
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            setOwnedStyle((TreeItemStyle) null);
            return;
        case TreePackage.DTREE_ITEM__ACTUAL_MAPPING:
            setActualMapping((TreeItemMapping) null);
            return;
        case TreePackage.DTREE_ITEM__CONTAINER:
            setContainer((DTreeItemContainer) null);
            return;
        case TreePackage.DTREE_ITEM__UPDATER:
            setUpdater((TreeItemUpdater) null);
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
        case TreePackage.DTREE_ITEM__NAME:
            return DTreeItemImpl.NAME_EDEFAULT == null ? name != null : !DTreeItemImpl.NAME_EDEFAULT.equals(name);
        case TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        case TreePackage.DTREE_ITEM__TREE_ELEMENT_MAPPING:
            return basicGetTreeElementMapping() != null;
        case TreePackage.DTREE_ITEM__EXPANDED:
            return expanded != DTreeItemImpl.EXPANDED_EDEFAULT;
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            return ownedStyle != null;
        case TreePackage.DTREE_ITEM__ACTUAL_MAPPING:
            return actualMapping != null;
        case TreePackage.DTREE_ITEM__CONTAINER:
            return getContainer() != null;
        case TreePackage.DTREE_ITEM__STYLE_UPDATER:
            return basicGetStyleUpdater() != null;
        case TreePackage.DTREE_ITEM__UPDATER:
            return basicGetUpdater() != null;
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
            case TreePackage.DTREE_ITEM__NAME:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;
            case TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTreeElement.class) {
            switch (derivedFeatureID) {
            case TreePackage.DTREE_ITEM__TREE_ELEMENT_MAPPING:
                return TreePackage.DTREE_ELEMENT__TREE_ELEMENT_MAPPING;
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
                return TreePackage.DTREE_ITEM__NAME;
            case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
                return TreePackage.DTREE_ITEM__SEMANTIC_ELEMENTS;
            default:
                return -1;
            }
        }
        if (baseClass == DTreeElement.class) {
            switch (baseFeatureID) {
            case TreePackage.DTREE_ELEMENT__TREE_ELEMENT_MAPPING:
                return TreePackage.DTREE_ITEM__TREE_ELEMENT_MAPPING;
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
        result.append(", expanded: "); //$NON-NLS-1$
        result.append(expanded);
        result.append(')');
        return result.toString();
    }

} // DTreeItemImpl
