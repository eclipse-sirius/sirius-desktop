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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Representation Template</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl#getOwnedRepresentations
 * <em>Owned Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RepresentationTemplateImpl extends MinimalEObjectImpl.Container implements RepresentationTemplate {
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
    protected String name = RepresentationTemplateImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedRepresentations()
     * <em>Owned Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedRepresentations()
     * @generated
     * @ordered
     */
    protected EList<RepresentationDescription> ownedRepresentations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RepresentationTemplateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.REPRESENTATION_TEMPLATE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.REPRESENTATION_TEMPLATE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationDescription> getOwnedRepresentations() {
        if (ownedRepresentations == null) {
            ownedRepresentations = new EObjectContainmentEList.Resolving<RepresentationDescription>(RepresentationDescription.class, this,
                    DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS);
        }
        return ownedRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS:
            return ((InternalEList<?>) getOwnedRepresentations()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.REPRESENTATION_TEMPLATE__NAME:
            return getName();
        case DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS:
            return getOwnedRepresentations();
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
        case DescriptionPackage.REPRESENTATION_TEMPLATE__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            getOwnedRepresentations().addAll((Collection<? extends RepresentationDescription>) newValue);
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
        case DescriptionPackage.REPRESENTATION_TEMPLATE__NAME:
            setName(RepresentationTemplateImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
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
        case DescriptionPackage.REPRESENTATION_TEMPLATE__NAME:
            return RepresentationTemplateImpl.NAME_EDEFAULT == null ? name != null : !RepresentationTemplateImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS:
            return ownedRepresentations != null && !ownedRepresentations.isEmpty();
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // RepresentationTemplateImpl
