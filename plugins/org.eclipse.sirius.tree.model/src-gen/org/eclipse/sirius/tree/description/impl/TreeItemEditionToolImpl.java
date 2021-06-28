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
package org.eclipse.sirius.tree.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Tree Item Edition Tool</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreeItemEditionToolImpl#getMask <em>Mask</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreeItemEditionToolImpl#getMapping <em>Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreeItemEditionToolImpl#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreeItemEditionToolImpl#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TreeItemEditionToolImpl extends TreeItemToolImpl implements TreeItemEditionTool {
    /**
     * The cached value of the '{@link #getMask() <em>Mask</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMask()
     * @generated
     * @ordered
     */
    protected EditMaskVariables mask;

    /**
     * The cached value of the '{@link #getMapping() <em>Mapping</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMapping()
     * @generated
     * @ordered
     */
    protected EList<TreeItemMapping> mapping;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementDropVariable element;

    /**
     * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRoot()
     * @generated
     * @ordered
     */
    protected ElementDropVariable root;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TreeItemEditionToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_ITEM_EDITION_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EditMaskVariables getMask() {
        return mask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetMask(EditMaskVariables newMask, NotificationChain msgs) {
        EditMaskVariables oldMask = mask;
        mask = newMask;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK, oldMask, newMask);
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
    public void setMask(EditMaskVariables newMask) {
        if (newMask != mask) {
            NotificationChain msgs = null;
            if (mask != null) {
                msgs = ((InternalEObject) mask).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK, null, msgs);
            }
            if (newMask != null) {
                msgs = ((InternalEObject) newMask).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK, null, msgs);
            }
            msgs = basicSetMask(newMask, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK, newMask, newMask));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TreeItemMapping> getMapping() {
        if (mapping == null) {
            mapping = new EObjectResolvingEList<>(TreeItemMapping.class, this, DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING);
        }
        return mapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDropVariable getElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElement(ElementDropVariable newElement, NotificationChain msgs) {
        ElementDropVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementDropVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDropVariable getRoot() {
        return root;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRoot(ElementDropVariable newRoot, NotificationChain msgs) {
        ElementDropVariable oldRoot = root;
        root = newRoot;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT, oldRoot, newRoot);
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
    public void setRoot(ElementDropVariable newRoot) {
        if (newRoot != root) {
            NotificationChain msgs = null;
            if (root != null) {
                msgs = ((InternalEObject) root).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT, null, msgs);
            }
            if (newRoot != null) {
                msgs = ((InternalEObject) newRoot).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT, null, msgs);
            }
            msgs = basicSetRoot(newRoot, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT, newRoot, newRoot));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK:
            return basicSetMask(null, msgs);
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT:
            return basicSetElement(null, msgs);
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT:
            return basicSetRoot(null, msgs);
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
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK:
            return getMask();
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING:
            return getMapping();
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT:
            return getElement();
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT:
            return getRoot();
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
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK:
            setMask((EditMaskVariables) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING:
            getMapping().clear();
            getMapping().addAll((Collection<? extends TreeItemMapping>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT:
            setElement((ElementDropVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT:
            setRoot((ElementDropVariable) newValue);
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
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK:
            setMask((EditMaskVariables) null);
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING:
            getMapping().clear();
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT:
            setElement((ElementDropVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT:
            setRoot((ElementDropVariable) null);
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
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK:
            return mask != null;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING:
            return mapping != null && !mapping.isEmpty();
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT:
            return element != null;
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT:
            return root != null;
        }
        return super.eIsSet(featureID);
    }

} // TreeItemEditionToolImpl
