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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Note</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.NoteImpl#getColor <em>Color</em>}
 * </li>
 * </ul>
 *
 * @generated
 */
public class NoteImpl extends NodeStyleImpl implements Note {
    /**
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "252,233,79"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color = NoteImpl.COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NoteImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.NOTE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(RGBValues newColor) {
        RGBValues oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.NOTE__COLOR, oldColor, color));
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
        case DiagramPackage.NOTE__COLOR:
            return getColor();
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
        case DiagramPackage.NOTE__COLOR:
            setColor((RGBValues) newValue);
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
        case DiagramPackage.NOTE__COLOR:
            setColor(NoteImpl.COLOR_EDEFAULT);
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
        case DiagramPackage.NOTE__COLOR:
            return NoteImpl.COLOR_EDEFAULT == null ? color != null : !NoteImpl.COLOR_EDEFAULT.equals(color);
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
        result.append(" (color: "); //$NON-NLS-1$
        result.append(color);
        result.append(')');
        return result.toString();
    }

} // NoteImpl
