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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.JavaExtension;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Java Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.JavaExtensionImpl#getQualifiedClassName
 * <em>Qualified Class Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaExtensionImpl extends MinimalEObjectImpl.Container implements JavaExtension {
    /**
     * The default value of the '{@link #getQualifiedClassName()
     * <em>Qualified Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getQualifiedClassName()
     * @generated
     * @ordered
     */
    protected static final String QUALIFIED_CLASS_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQualifiedClassName()
     * <em>Qualified Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getQualifiedClassName()
     * @generated
     * @ordered
     */
    protected String qualifiedClassName = JavaExtensionImpl.QUALIFIED_CLASS_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected JavaExtensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.JAVA_EXTENSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setQualifiedClassName(String newQualifiedClassName) {
        String oldQualifiedClassName = qualifiedClassName;
        qualifiedClassName = newQualifiedClassName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME, oldQualifiedClassName, qualifiedClassName));
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
        case DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME:
            return getQualifiedClassName();
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
        case DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME:
            setQualifiedClassName((String) newValue);
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
        case DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME:
            setQualifiedClassName(JavaExtensionImpl.QUALIFIED_CLASS_NAME_EDEFAULT);
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
        case DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME:
            return JavaExtensionImpl.QUALIFIED_CLASS_NAME_EDEFAULT == null ? qualifiedClassName != null : !JavaExtensionImpl.QUALIFIED_CLASS_NAME_EDEFAULT.equals(qualifiedClassName);
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
        result.append(" (qualifiedClassName: "); //$NON-NLS-1$
        result.append(qualifiedClassName);
        result.append(')');
        return result.toString();
    }

} // JavaExtensionImpl
