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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Note Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.NoteDescriptionImpl#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NoteDescriptionImpl extends NodeStyleDescriptionImpl implements NoteDescription {
    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected ColorDescription color;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NoteDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.NOTE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getColor() {
        if (color != null && color.eIsProxy()) {
            InternalEObject oldColor = (InternalEObject) color;
            color = (ColorDescription) eResolveProxy(oldColor);
            if (color != oldColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.NOTE_DESCRIPTION__COLOR, oldColor, color));
                }
            }
        }
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(ColorDescription newColor) {
        ColorDescription oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NOTE_DESCRIPTION__COLOR, oldColor, color));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case StylePackage.NOTE_DESCRIPTION__COLOR:
            if (resolve) {
                return getColor();
            }
            return basicGetColor();
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
        case StylePackage.NOTE_DESCRIPTION__COLOR:
            setColor((ColorDescription) newValue);
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
        case StylePackage.NOTE_DESCRIPTION__COLOR:
            setColor((ColorDescription) null);
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
        case StylePackage.NOTE_DESCRIPTION__COLOR:
            return color != null;
        }
        return super.eIsSet(featureID);
    }

} // NoteDescriptionImpl
