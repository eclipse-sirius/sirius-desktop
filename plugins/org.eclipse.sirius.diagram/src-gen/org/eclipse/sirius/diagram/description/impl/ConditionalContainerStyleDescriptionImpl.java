/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Conditional Container Style Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ConditionalContainerStyleDescriptionImpl#getStyle
 * <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalContainerStyleDescriptionImpl extends ConditionalStyleDescriptionImpl implements ConditionalContainerStyleDescription {
    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ContainerStyleDescription style;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ConditionalContainerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerStyleDescription getStyle() {
        if (style != null && style.eIsProxy()) {
            InternalEObject oldStyle = (InternalEObject) style;
            style = (ContainerStyleDescription) eResolveProxy(oldStyle);
            if (style != oldStyle) {
                InternalEObject newStyle = (InternalEObject) style;
                NotificationChain msgs = oldStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, null, null);
                if (newStyle.eInternalContainer() == null) {
                    msgs = newStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, oldStyle, style));
                }
            }
        }
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerStyleDescription basicGetStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ContainerStyleDescription newStyle, NotificationChain msgs) {
        ContainerStyleDescription oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(ContainerStyleDescription newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, newStyle, newStyle));
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
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
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
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
            if (resolve) {
                return getStyle();
            }
            return basicGetStyle();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
            setStyle((ContainerStyleDescription) newValue);
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
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
            setStyle((ContainerStyleDescription) null);
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
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
            return style != null;
        }
        return super.eIsSet(featureID);
    }

} // ConditionalContainerStyleDescriptionImpl
