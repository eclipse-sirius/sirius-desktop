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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.LayoutDirection;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Composite Layout</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl#getPadding
 * <em>Padding</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl#getDirection
 * <em>Direction</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompositeLayoutImpl extends DocumentedElementImpl implements CompositeLayout {
    /**
     * The default value of the '{@link #getPadding() <em>Padding</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPadding()
     * @generated
     * @ordered
     */
    protected static final int PADDING_EDEFAULT = 30;

    /**
     * The cached value of the '{@link #getPadding() <em>Padding</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPadding()
     * @generated
     * @ordered
     */
    protected int padding = CompositeLayoutImpl.PADDING_EDEFAULT;

    /**
     * The default value of the '{@link #getDirection() <em>Direction</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected static final LayoutDirection DIRECTION_EDEFAULT = LayoutDirection.TOP_TO_BOTTOM;

    /**
     * The cached value of the '{@link #getDirection() <em>Direction</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected LayoutDirection direction = CompositeLayoutImpl.DIRECTION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CompositeLayoutImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.COMPOSITE_LAYOUT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getPadding() {
        return padding;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPadding(int newPadding) {
        int oldPadding = padding;
        padding = newPadding;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COMPOSITE_LAYOUT__PADDING, oldPadding, padding));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LayoutDirection getDirection() {
        return direction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDirection(LayoutDirection newDirection) {
        LayoutDirection oldDirection = direction;
        direction = newDirection == null ? CompositeLayoutImpl.DIRECTION_EDEFAULT : newDirection;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION, oldDirection, direction));
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
        case DescriptionPackage.COMPOSITE_LAYOUT__PADDING:
            return getPadding();
        case DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION:
            return getDirection();
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
        case DescriptionPackage.COMPOSITE_LAYOUT__PADDING:
            setPadding((Integer) newValue);
            return;
        case DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION:
            setDirection((LayoutDirection) newValue);
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
        case DescriptionPackage.COMPOSITE_LAYOUT__PADDING:
            setPadding(CompositeLayoutImpl.PADDING_EDEFAULT);
            return;
        case DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION:
            setDirection(CompositeLayoutImpl.DIRECTION_EDEFAULT);
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
        case DescriptionPackage.COMPOSITE_LAYOUT__PADDING:
            return padding != CompositeLayoutImpl.PADDING_EDEFAULT;
        case DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION:
            return direction != CompositeLayoutImpl.DIRECTION_EDEFAULT;
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
        result.append(" (padding: "); //$NON-NLS-1$
        result.append(padding);
        result.append(", direction: "); //$NON-NLS-1$
        result.append(direction);
        result.append(')');
        return result.toString();
    }

} // CompositeLayoutImpl
