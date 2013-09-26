/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.NavigationTargetType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Navigation Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl#getTargetType
 * <em>Target Type</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl#getLabel
 * <em>Label</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class DNavigationLinkImpl extends EObjectImpl implements DNavigationLink {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The default value of the '{@link #getTargetType() <em>Target Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetType()
     * @generated
     * @ordered
     */
    protected static final NavigationTargetType TARGET_TYPE_EDEFAULT = NavigationTargetType.MODEL_LITERAL;

    /**
     * The cached value of the '{@link #getTargetType() <em>Target Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetType()
     * @generated
     * @ordered
     */
    protected NavigationTargetType targetType = TARGET_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = "link to...";

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DNavigationLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DNAVIGATION_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NavigationTargetType getTargetType() {
        return targetType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetType(NavigationTargetType newTargetType) {
        NavigationTargetType oldTargetType = targetType;
        targetType = newTargetType == null ? TARGET_TYPE_EDEFAULT : newTargetType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNAVIGATION_LINK__TARGET_TYPE, oldTargetType, targetType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNAVIGATION_LINK__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isAvailable() {
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
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.DNAVIGATION_LINK__TARGET_TYPE:
            return getTargetType();
        case ViewpointPackage.DNAVIGATION_LINK__LABEL:
            return getLabel();
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
        case ViewpointPackage.DNAVIGATION_LINK__TARGET_TYPE:
            setTargetType((NavigationTargetType) newValue);
            return;
        case ViewpointPackage.DNAVIGATION_LINK__LABEL:
            setLabel((String) newValue);
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
        case ViewpointPackage.DNAVIGATION_LINK__TARGET_TYPE:
            setTargetType(TARGET_TYPE_EDEFAULT);
            return;
        case ViewpointPackage.DNAVIGATION_LINK__LABEL:
            setLabel(LABEL_EDEFAULT);
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
        case ViewpointPackage.DNAVIGATION_LINK__TARGET_TYPE:
            return targetType != TARGET_TYPE_EDEFAULT;
        case ViewpointPackage.DNAVIGATION_LINK__LABEL:
            return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (targetType: ");
        result.append(targetType);
        result.append(", label: ");
        result.append(label);
        result.append(')');
        return result.toString();
    }

} // DNavigationLinkImpl
