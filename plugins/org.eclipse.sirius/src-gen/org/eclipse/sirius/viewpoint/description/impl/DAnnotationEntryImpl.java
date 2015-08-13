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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnnotation Entry</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl#getDetails
 * <em>Details</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DAnnotationEntryImpl extends MinimalEObjectImpl.Container implements DAnnotationEntry {
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
    protected String source = DAnnotationEntryImpl.SOURCE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDetails() <em>Details</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDetails()
     * @generated
     * @ordered
     */
    protected EList<String> details;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DAnnotationEntryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DANNOTATION_ENTRY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DANNOTATION_ENTRY__SOURCE, oldSource, source));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<String> getDetails() {
        if (details == null) {
            details = new EDataTypeUniqueEList<String>(String.class, this, DescriptionPackage.DANNOTATION_ENTRY__DETAILS);
        }
        return details;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.DANNOTATION_ENTRY__SOURCE:
            return getSource();
        case DescriptionPackage.DANNOTATION_ENTRY__DETAILS:
            return getDetails();
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
        case DescriptionPackage.DANNOTATION_ENTRY__SOURCE:
            setSource((String) newValue);
            return;
        case DescriptionPackage.DANNOTATION_ENTRY__DETAILS:
            getDetails().clear();
            getDetails().addAll((Collection<? extends String>) newValue);
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
        case DescriptionPackage.DANNOTATION_ENTRY__SOURCE:
            setSource(DAnnotationEntryImpl.SOURCE_EDEFAULT);
            return;
        case DescriptionPackage.DANNOTATION_ENTRY__DETAILS:
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
        case DescriptionPackage.DANNOTATION_ENTRY__SOURCE:
            return DAnnotationEntryImpl.SOURCE_EDEFAULT == null ? source != null : !DAnnotationEntryImpl.SOURCE_EDEFAULT.equals(source);
        case DescriptionPackage.DANNOTATION_ENTRY__DETAILS:
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
        result.append(", details: "); //$NON-NLS-1$
        result.append(details);
        result.append(')');
        return result.toString();
    }

} // DAnnotationEntryImpl
