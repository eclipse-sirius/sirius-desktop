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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnnotation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl#getDetails
 * <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DAnnotationImpl extends MinimalEObjectImpl.Container implements DAnnotation {
    /**
     * The default value of the '{@link #getSource() <em>Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected String source = DAnnotationImpl.SOURCE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDetails() <em>Details</em>}' map.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDetails()
     * @generated
     * @ordered
     */
    protected EMap<String, String> details;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DAnnotationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DANNOTATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSource(String newSource) {
        String oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DANNOTATION__SOURCE, oldSource, source));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EMap<String, String> getDetails() {
        if (details == null) {
            details = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, DescriptionPackage.DANNOTATION__DETAILS);
        }
        return details;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.DANNOTATION__DETAILS:
            return ((InternalEList<?>) getDetails()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.DANNOTATION__SOURCE:
            return getSource();
        case DescriptionPackage.DANNOTATION__DETAILS:
            if (coreType) {
                return getDetails();
            } else {
                return getDetails().map();
            }
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
        case DescriptionPackage.DANNOTATION__SOURCE:
            setSource((String) newValue);
            return;
        case DescriptionPackage.DANNOTATION__DETAILS:
            ((EStructuralFeature.Setting) getDetails()).set(newValue);
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
        case DescriptionPackage.DANNOTATION__SOURCE:
            setSource(DAnnotationImpl.SOURCE_EDEFAULT);
            return;
        case DescriptionPackage.DANNOTATION__DETAILS:
            getDetails().clear();
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
        case DescriptionPackage.DANNOTATION__SOURCE:
            return DAnnotationImpl.SOURCE_EDEFAULT == null ? source != null : !DAnnotationImpl.SOURCE_EDEFAULT.equals(source);
        case DescriptionPackage.DANNOTATION__DETAILS:
            return details != null && !details.isEmpty();
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
        result.append(" (source: "); //$NON-NLS-1$
        result.append(source);
        result.append(')');
        return result.toString();
    }

} // DAnnotationImpl
