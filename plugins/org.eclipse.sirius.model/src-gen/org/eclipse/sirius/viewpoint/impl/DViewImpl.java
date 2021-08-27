/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DView</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getViewpoint <em>Viewpoint</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getOwnedRepresentationDescriptors <em>Owned Representation
 * Descriptors</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getOwnedExtensions <em>Owned Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getModels <em>Models</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DViewImpl extends IdentifiedElementImpl implements DView {
    /**
     * The cached value of the '{@link #getViewpoint() <em>Viewpoint</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getViewpoint()
     * @generated
     * @ordered
     */
    protected Viewpoint viewpoint;

    /**
     * The cached value of the '{@link #getOwnedRepresentationDescriptors() <em>Owned Representation Descriptors</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedRepresentationDescriptors()
     * @generated
     * @ordered
     */
    protected EList<DRepresentationDescriptor> ownedRepresentationDescriptors;

    /**
     * The cached value of the '{@link #getOwnedExtensions() <em>Owned Extensions</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedExtensions()
     * @generated
     * @ordered
     */
    protected MetaModelExtension ownedExtensions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DViewImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DVIEW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetaModelExtension getOwnedExtensions() {
        if (ownedExtensions != null && ownedExtensions.eIsProxy()) {
            InternalEObject oldOwnedExtensions = (InternalEObject) ownedExtensions;
            ownedExtensions = (MetaModelExtension) eResolveProxy(oldOwnedExtensions);
            if (ownedExtensions != oldOwnedExtensions) {
                InternalEObject newOwnedExtensions = (InternalEObject) ownedExtensions;
                NotificationChain msgs = oldOwnedExtensions.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, null);
                if (newOwnedExtensions.eInternalContainer() == null) {
                    msgs = newOwnedExtensions.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DVIEW__OWNED_EXTENSIONS, oldOwnedExtensions, ownedExtensions));
                }
            }
        }
        return ownedExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetaModelExtension basicGetOwnedExtensions() {
        return ownedExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOwnedExtensions(MetaModelExtension newOwnedExtensions, NotificationChain msgs) {
        MetaModelExtension oldOwnedExtensions = ownedExtensions;
        ownedExtensions = newOwnedExtensions;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__OWNED_EXTENSIONS, oldOwnedExtensions, newOwnedExtensions);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOwnedExtensions(MetaModelExtension newOwnedExtensions) {
        if (newOwnedExtensions != ownedExtensions) {
            NotificationChain msgs = null;
            if (ownedExtensions != null) {
                msgs = ((InternalEObject) ownedExtensions).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            }
            if (newOwnedExtensions != null) {
                msgs = ((InternalEObject) newOwnedExtensions).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            }
            msgs = basicSetOwnedExtensions(newOwnedExtensions, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__OWNED_EXTENSIONS, newOwnedExtensions, newOwnedExtensions));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Viewpoint getViewpoint() {
        if (viewpoint != null && viewpoint.eIsProxy()) {
            InternalEObject oldViewpoint = (InternalEObject) viewpoint;
            viewpoint = (Viewpoint) eResolveProxy(oldViewpoint);
            if (viewpoint != oldViewpoint) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DVIEW__VIEWPOINT, oldViewpoint, viewpoint));
                }
            }
        }
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Viewpoint basicGetViewpoint() {
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setViewpoint(Viewpoint newViewpoint) {
        Viewpoint oldViewpoint = viewpoint;
        viewpoint = newViewpoint;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__VIEWPOINT, oldViewpoint, viewpoint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DRepresentationDescriptor> getOwnedRepresentationDescriptors() {
        if (ownedRepresentationDescriptors == null) {
            ownedRepresentationDescriptors = new EObjectContainmentEList.Resolving<>(DRepresentationDescriptor.class, this, ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS);
        }
        return ownedRepresentationDescriptors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getModels() {
        // TODO: implement this method to return the 'Models' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS:
            return ((InternalEList<?>) getOwnedRepresentationDescriptors()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            return basicSetOwnedExtensions(null, msgs);
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
        case ViewpointPackage.DVIEW__VIEWPOINT:
            if (resolve) {
                return getViewpoint();
            }
            return basicGetViewpoint();
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS:
            return getOwnedRepresentationDescriptors();
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            if (resolve) {
                return getOwnedExtensions();
            }
            return basicGetOwnedExtensions();
        case ViewpointPackage.DVIEW__MODELS:
            return getModels();
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
        case ViewpointPackage.DVIEW__VIEWPOINT:
            setViewpoint((Viewpoint) newValue);
            return;
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS:
            getOwnedRepresentationDescriptors().clear();
            getOwnedRepresentationDescriptors().addAll((Collection<? extends DRepresentationDescriptor>) newValue);
            return;
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) newValue);
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
        case ViewpointPackage.DVIEW__VIEWPOINT:
            setViewpoint((Viewpoint) null);
            return;
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS:
            getOwnedRepresentationDescriptors().clear();
            return;
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) null);
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
        case ViewpointPackage.DVIEW__VIEWPOINT:
            return viewpoint != null;
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS:
            return ownedRepresentationDescriptors != null && !ownedRepresentationDescriptors.isEmpty();
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            return ownedExtensions != null;
        case ViewpointPackage.DVIEW__MODELS:
            return !getModels().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DViewImpl
