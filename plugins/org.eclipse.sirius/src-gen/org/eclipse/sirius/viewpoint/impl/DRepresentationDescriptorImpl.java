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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DRepresentation Descriptor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getDescription
 * <em>Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getRepresentation
 * <em>Representation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DRepresentationDescriptorImpl extends MinimalEObjectImpl.Container implements DRepresentationDescriptor {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DRepresentationDescriptorImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected RepresentationDescription description;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target;

    /**
     * The cached value of the '{@link #getRepresentation()
     * <em>Representation</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRepresentation()
     * @generated
     * @ordered
     */
    protected DRepresentation representation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DRepresentationDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RepresentationDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (RepresentationDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION, oldDescription, description));
                }
            }
        }
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RepresentationDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(RepresentationDescription newDescription) {
        RepresentationDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(EObject newTarget) {
        EObject oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DRepresentation getRepresentation() {
        if (representation != null && representation.eIsProxy()) {
            InternalEObject oldRepresentation = (InternalEObject) representation;
            representation = (DRepresentation) eResolveProxy(oldRepresentation);
            if (representation != oldRepresentation) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION, oldRepresentation, representation));
                }
            }
        }
        return representation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DRepresentation basicGetRepresentation() {
        return representation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepresentation(DRepresentation newRepresentation) {
        DRepresentation oldRepresentation = representation;
        representation = newRepresentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION, oldRepresentation, representation));
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            return getName();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            if (resolve) {
                return getDescription();
            }
            return basicGetDescription();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            if (resolve) {
                return getRepresentation();
            }
            return basicGetRepresentation();
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            setName((String) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            setDescription((RepresentationDescription) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            setTarget((EObject) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            setRepresentation((DRepresentation) newValue);
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            setName(DRepresentationDescriptorImpl.NAME_EDEFAULT);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            setDescription((RepresentationDescription) null);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            setTarget((EObject) null);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            setRepresentation((DRepresentation) null);
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            return DRepresentationDescriptorImpl.NAME_EDEFAULT == null ? name != null : !DRepresentationDescriptorImpl.NAME_EDEFAULT.equals(name);
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            return description != null;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            return target != null;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            return representation != null;
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

} // DRepresentationDescriptorImpl
