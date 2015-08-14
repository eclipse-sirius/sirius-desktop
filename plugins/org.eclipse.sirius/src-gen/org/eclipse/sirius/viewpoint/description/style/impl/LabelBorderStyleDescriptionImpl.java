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
package org.eclipse.sirius.viewpoint.description.style.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Label Border Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl#getCornerHeight
 * <em>Corner Height</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl#getCornerWidth
 * <em>Corner Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelBorderStyleDescriptionImpl extends MinimalEObjectImpl.Container implements LabelBorderStyleDescription {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = LabelBorderStyleDescriptionImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = LabelBorderStyleDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getCornerHeight()
     * <em>Corner Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCornerHeight()
     * @generated
     * @ordered
     */
    protected static final int CORNER_HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCornerHeight()
     * <em>Corner Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCornerHeight()
     * @generated
     * @ordered
     */
    protected int cornerHeight = LabelBorderStyleDescriptionImpl.CORNER_HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getCornerWidth() <em>Corner Width</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCornerWidth()
     * @generated
     * @ordered
     */
    protected static final int CORNER_WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCornerWidth() <em>Corner Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCornerWidth()
     * @generated
     * @ordered
     */
    protected int cornerWidth = LabelBorderStyleDescriptionImpl.CORNER_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LabelBorderStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.LABEL_BORDER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__ID, oldId, id));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getCornerHeight() {
        return cornerHeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCornerHeight(int newCornerHeight) {
        int oldCornerHeight = cornerHeight;
        cornerHeight = newCornerHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT, oldCornerHeight, cornerHeight));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getCornerWidth() {
        return cornerWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCornerWidth(int newCornerWidth) {
        int oldCornerWidth = cornerWidth;
        cornerWidth = newCornerWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH, oldCornerWidth, cornerWidth));
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
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__ID:
            return getId();
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__NAME:
            return getName();
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT:
            return getCornerHeight();
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH:
            return getCornerWidth();
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
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__ID:
            setId((String) newValue);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT:
            setCornerHeight((Integer) newValue);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH:
            setCornerWidth((Integer) newValue);
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
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__ID:
            setId(LabelBorderStyleDescriptionImpl.ID_EDEFAULT);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__NAME:
            setName(LabelBorderStyleDescriptionImpl.NAME_EDEFAULT);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT:
            setCornerHeight(LabelBorderStyleDescriptionImpl.CORNER_HEIGHT_EDEFAULT);
            return;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH:
            setCornerWidth(LabelBorderStyleDescriptionImpl.CORNER_WIDTH_EDEFAULT);
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
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__ID:
            return LabelBorderStyleDescriptionImpl.ID_EDEFAULT == null ? id != null : !LabelBorderStyleDescriptionImpl.ID_EDEFAULT.equals(id);
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__NAME:
            return LabelBorderStyleDescriptionImpl.NAME_EDEFAULT == null ? name != null : !LabelBorderStyleDescriptionImpl.NAME_EDEFAULT.equals(name);
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT:
            return cornerHeight != LabelBorderStyleDescriptionImpl.CORNER_HEIGHT_EDEFAULT;
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH:
            return cornerWidth != LabelBorderStyleDescriptionImpl.CORNER_WIDTH_EDEFAULT;
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", cornerHeight: "); //$NON-NLS-1$
        result.append(cornerHeight);
        result.append(", cornerWidth: "); //$NON-NLS-1$
        result.append(cornerWidth);
        result.append(')');
        return result.toString();
    }

} // LabelBorderStyleDescriptionImpl
