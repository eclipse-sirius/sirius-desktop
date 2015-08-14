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
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>End User Documented Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EndUserDocumentedElementImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EndUserDocumentedElementImpl extends MinimalEObjectImpl.Container implements EndUserDocumentedElement {
    /**
     * The default value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected static final String END_USER_DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected String endUserDocumentation = EndUserDocumentedElementImpl.END_USER_DOCUMENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EndUserDocumentedElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndUserDocumentation() {
        return endUserDocumentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndUserDocumentation(String newEndUserDocumentation) {
        String oldEndUserDocumentation = endUserDocumentation;
        endUserDocumentation = newEndUserDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
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
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
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
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
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
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
            setEndUserDocumentation(EndUserDocumentedElementImpl.END_USER_DOCUMENTATION_EDEFAULT);
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
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
            return EndUserDocumentedElementImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !EndUserDocumentedElementImpl.END_USER_DOCUMENTATION_EDEFAULT
                    .equals(endUserDocumentation);
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
        result.append(" (endUserDocumentation: "); //$NON-NLS-1$
        result.append(endUserDocumentation);
        result.append(')');
        return result.toString();
    }

} // EndUserDocumentedElementImpl
