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
package org.eclipse.sirius.table.metamodel.table.description.impl;

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
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Style Updater</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl#getDefaultForeground
 * <em>Default Foreground</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl#getDefaultBackground
 * <em>Default Background</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StyleUpdaterImpl extends MinimalEObjectImpl.Container implements StyleUpdater {
    /**
     * The cached value of the '{@link #getDefaultForeground() <em>Default Foreground</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultForeground()
     * @generated
     * @ordered
     */
    protected ForegroundStyleDescription defaultForeground;

    /**
     * The cached value of the '{@link #getForegroundConditionalStyle() <em>Foreground Conditional Style</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getForegroundConditionalStyle()
     * @generated
     * @ordered
     */
    protected EList<ForegroundConditionalStyle> foregroundConditionalStyle;

    /**
     * The cached value of the '{@link #getDefaultBackground() <em>Default Background</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultBackground()
     * @generated
     * @ordered
     */
    protected BackgroundStyleDescription defaultBackground;

    /**
     * The cached value of the '{@link #getBackgroundConditionalStyle() <em>Background Conditional Style</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBackgroundConditionalStyle()
     * @generated
     * @ordered
     */
    protected EList<BackgroundConditionalStyle> backgroundConditionalStyle;

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
    public ForegroundStyleDescription getDefaultForeground() {
        return defaultForeground;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefaultForeground(ForegroundStyleDescription newDefaultForeground, NotificationChain msgs) {
        ForegroundStyleDescription oldDefaultForeground = defaultForeground;
        defaultForeground = newDefaultForeground;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND, oldDefaultForeground, newDefaultForeground);
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
    public void setDefaultForeground(ForegroundStyleDescription newDefaultForeground) {
        if (newDefaultForeground != defaultForeground) {
            NotificationChain msgs = null;
            if (defaultForeground != null) {
                msgs = ((InternalEObject) defaultForeground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND, null, msgs);
            }
            if (newDefaultForeground != null) {
                msgs = ((InternalEObject) newDefaultForeground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND, null, msgs);
            }
            msgs = basicSetDefaultForeground(newDefaultForeground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND, newDefaultForeground, newDefaultForeground));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ForegroundConditionalStyle> getForegroundConditionalStyle() {
        if (foregroundConditionalStyle == null) {
            foregroundConditionalStyle = new EObjectContainmentEList<>(ForegroundConditionalStyle.class, this,
                    DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
        }
        return foregroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BackgroundStyleDescription getDefaultBackground() {
        return defaultBackground;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefaultBackground(BackgroundStyleDescription newDefaultBackground, NotificationChain msgs) {
        BackgroundStyleDescription oldDefaultBackground = defaultBackground;
        defaultBackground = newDefaultBackground;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND, oldDefaultBackground, newDefaultBackground);
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
    public void setDefaultBackground(BackgroundStyleDescription newDefaultBackground) {
        if (newDefaultBackground != defaultBackground) {
            NotificationChain msgs = null;
            if (defaultBackground != null) {
                msgs = ((InternalEObject) defaultBackground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND, null, msgs);
            }
            if (newDefaultBackground != null) {
                msgs = ((InternalEObject) newDefaultBackground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND, null, msgs);
            }
            msgs = basicSetDefaultBackground(newDefaultBackground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND, newDefaultBackground, newDefaultBackground));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<BackgroundConditionalStyle> getBackgroundConditionalStyle() {
        if (backgroundConditionalStyle == null) {
            backgroundConditionalStyle = new EObjectContainmentEList<>(BackgroundConditionalStyle.class, this,
                    DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);
        }
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
            return basicSetDefaultForeground(null, msgs);
        case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getForegroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
            return basicSetDefaultBackground(null, msgs);
        case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getBackgroundConditionalStyle()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
            return getDefaultForeground();
        case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
            return getForegroundConditionalStyle();
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
            return getDefaultBackground();
        case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
            return getBackgroundConditionalStyle();
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) newValue);
            return;
        case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            getForegroundConditionalStyle().addAll((Collection<? extends ForegroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) newValue);
            return;
        case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            getBackgroundConditionalStyle().addAll((Collection<? extends BackgroundConditionalStyle>) newValue);
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) null);
            return;
        case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            return;
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) null);
            return;
        case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
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
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
            return defaultForeground != null;
        case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
            return foregroundConditionalStyle != null && !foregroundConditionalStyle.isEmpty();
        case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
            return defaultBackground != null;
        case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
            return backgroundConditionalStyle != null && !backgroundConditionalStyle.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // StyleUpdaterImpl
