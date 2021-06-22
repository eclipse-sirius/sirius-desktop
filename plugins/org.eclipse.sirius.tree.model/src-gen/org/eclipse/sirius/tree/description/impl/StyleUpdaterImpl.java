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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Style Updater</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.impl.StyleUpdaterImpl#getDefaultStyle <em>Default Style</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.impl.StyleUpdaterImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StyleUpdaterImpl extends MinimalEObjectImpl.Container implements StyleUpdater {
    /**
     * The cached value of the '{@link #getDefaultStyle() <em>Default Style</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultStyle()
     * @generated
     * @ordered
     */
    protected TreeItemStyleDescription defaultStyle;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalTreeItemStyleDescription> conditionalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected StyleUpdaterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.STYLE_UPDATER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TreeItemStyleDescription getDefaultStyle() {
        return defaultStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefaultStyle(TreeItemStyleDescription newDefaultStyle, NotificationChain msgs) {
        TreeItemStyleDescription oldDefaultStyle = defaultStyle;
        defaultStyle = newDefaultStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE, oldDefaultStyle, newDefaultStyle);
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
    public void setDefaultStyle(TreeItemStyleDescription newDefaultStyle) {
        if (newDefaultStyle != defaultStyle) {
            NotificationChain msgs = null;
            if (defaultStyle != null) {
                msgs = ((InternalEObject) defaultStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE, null, msgs);
            }
            if (newDefaultStyle != null) {
                msgs = ((InternalEObject) newDefaultStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE, null, msgs);
            }
            msgs = basicSetDefaultStyle(newDefaultStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE, newDefaultStyle, newDefaultStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConditionalTreeItemStyleDescription> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ConditionalTreeItemStyleDescription>(ConditionalTreeItemStyleDescription.class, this, DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
            return basicSetDefaultStyle(null, msgs);
        case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
            return getDefaultStyle();
        case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
            return getConditionalStyles();
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
            setDefaultStyle((TreeItemStyleDescription) newValue);
            return;
        case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ConditionalTreeItemStyleDescription>) newValue);
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
            setDefaultStyle((TreeItemStyleDescription) null);
            return;
        case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
            return defaultStyle != null;
        case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // StyleUpdaterImpl
