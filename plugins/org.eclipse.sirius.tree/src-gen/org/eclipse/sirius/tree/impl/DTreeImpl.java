/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.impl;

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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DTree</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeImpl#getOwnedTreeItems <em>Owned Tree Items</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeImpl#getSemanticElements <em>Semantic Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DTreeImpl extends DRepresentationImpl implements DTree {
    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target;

    /**
     * The cached value of the '{@link #getOwnedTreeItems() <em>Owned Tree Items</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedTreeItems()
     * @generated
     * @ordered
     */
    protected EList<DTreeItem> ownedTreeItems;

    /**
     * The cached value of the '{@link #getSemanticElements() <em>Semantic Elements</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticElements()
     * @generated
     * @ordered
     */
    protected EList<EObject> semanticElements;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected TreeDescription description;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DTreeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TreePackage.Literals.DTREE;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.DTREE__TARGET, oldTarget, target));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DTreeItem> getOwnedTreeItems() {
        if (ownedTreeItems == null) {
            ownedTreeItems = new EObjectContainmentWithInverseEList<DTreeItem>(DTreeItem.class, this, TreePackage.DTREE__OWNED_TREE_ITEMS, TreePackage.DTREE_ITEM__CONTAINER);
        }
        return ownedTreeItems;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getSemanticElements() {
        if (semanticElements == null) {
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, TreePackage.DTREE__SEMANTIC_ELEMENTS);
        }
        return semanticElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TreeDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (TreeDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.DTREE__DESCRIPTION, oldDescription, description));
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
    public TreeDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(TreeDescription newDescription) {
        TreeDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.DTREE__DESCRIPTION, oldDescription, description));
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
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedTreeItems()).basicAdd(otherEnd, msgs);
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
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            return ((InternalEList<?>) getOwnedTreeItems()).basicRemove(otherEnd, msgs);
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
        case TreePackage.DTREE__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            return getOwnedTreeItems();
        case TreePackage.DTREE__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case TreePackage.DTREE__DESCRIPTION:
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
        case TreePackage.DTREE__TARGET:
            setTarget((EObject) newValue);
            return;
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            getOwnedTreeItems().clear();
            getOwnedTreeItems().addAll((Collection<? extends DTreeItem>) newValue);
            return;
        case TreePackage.DTREE__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
            return;
        case TreePackage.DTREE__DESCRIPTION:
            setDescription((TreeDescription) newValue);
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
        case TreePackage.DTREE__TARGET:
            setTarget((EObject) null);
            return;
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            getOwnedTreeItems().clear();
            return;
        case TreePackage.DTREE__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            return;
        case TreePackage.DTREE__DESCRIPTION:
            setDescription((TreeDescription) null);
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
        case TreePackage.DTREE__TARGET:
            return target != null;
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
            return ownedTreeItems != null && !ownedTreeItems.isEmpty();
        case TreePackage.DTREE__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        case TreePackage.DTREE__DESCRIPTION:
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
            case TreePackage.DTREE__TARGET:
                return ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;
            default:
                return -1;
            }
        }
        if (baseClass == DTreeItemContainer.class) {
            switch (derivedFeatureID) {
            case TreePackage.DTREE__OWNED_TREE_ITEMS:
                return TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS;
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
                return TreePackage.DTREE__TARGET;
            default:
                return -1;
            }
        }
        if (baseClass == DTreeItemContainer.class) {
            switch (baseFeatureID) {
            case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
                return TreePackage.DTREE__OWNED_TREE_ITEMS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // DTreeImpl
