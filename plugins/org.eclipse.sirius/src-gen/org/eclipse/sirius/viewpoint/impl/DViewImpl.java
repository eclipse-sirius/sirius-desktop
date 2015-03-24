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
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DView</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getOwnedRepresentations
 * <em>Owned Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getOwnedExtensions
 * <em>Owned Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#isInitialized <em>
 * Initialized</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DViewImpl#getViewpoint <em>
 * Viewpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DViewImpl extends MinimalEObjectImpl.Container implements DView {
    /**
     * The cached value of the '{@link #getOwnedRepresentations()
     * <em>Owned Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentations()
     * @generated
     * @ordered
     */
    protected EList<DRepresentation> ownedRepresentations;

    /**
     * The cached value of the '{@link #getOwnedExtensions()
     * <em>Owned Extensions</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOwnedExtensions()
     * @generated
     * @ordered
     */
    protected MetaModelExtension ownedExtensions;

    /**
     * The default value of the '{@link #isInitialized() <em>Initialized</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isInitialized()
     * @generated
     * @ordered
     */
    protected static final boolean INITIALIZED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInitialized() <em>Initialized</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isInitialized()
     * @generated
     * @ordered
     */
    protected boolean initialized = INITIALIZED_EDEFAULT;

    /**
     * The cached value of the '{@link #getViewpoint() <em>Viewpoint</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getViewpoint()
     * @generated
     * @ordered
     */
    protected Viewpoint viewpoint;

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
    public EList<DRepresentation> getOwnedRepresentations() {
        if (ownedRepresentations == null) {
            ownedRepresentations = new EObjectContainmentEList.Resolving<DRepresentation>(DRepresentation.class, this, ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS);
        }
        return ownedRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MetaModelExtension getOwnedExtensions() {
        if (ownedExtensions != null && ownedExtensions.eIsProxy()) {
            InternalEObject oldOwnedExtensions = (InternalEObject) ownedExtensions;
            ownedExtensions = (MetaModelExtension) eResolveProxy(oldOwnedExtensions);
            if (ownedExtensions != oldOwnedExtensions) {
                InternalEObject newOwnedExtensions = (InternalEObject) ownedExtensions;
                NotificationChain msgs = oldOwnedExtensions.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, null);
                if (newOwnedExtensions.eInternalContainer() == null) {
                    msgs = newOwnedExtensions.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DVIEW__OWNED_EXTENSIONS, oldOwnedExtensions, ownedExtensions));
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
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOwnedExtensions(MetaModelExtension newOwnedExtensions) {
        if (newOwnedExtensions != ownedExtensions) {
            NotificationChain msgs = null;
            if (ownedExtensions != null)
                msgs = ((InternalEObject) ownedExtensions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            if (newOwnedExtensions != null)
                msgs = ((InternalEObject) newOwnedExtensions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            msgs = basicSetOwnedExtensions(newOwnedExtensions, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__OWNED_EXTENSIONS, newOwnedExtensions, newOwnedExtensions));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setInitialized(boolean newInitialized) {
        boolean oldInitialized = initialized;
        initialized = newInitialized;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__INITIALIZED, oldInitialized, initialized));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Viewpoint getViewpoint() {
        if (viewpoint != null && viewpoint.eIsProxy()) {
            InternalEObject oldViewpoint = (InternalEObject) viewpoint;
            viewpoint = (Viewpoint) eResolveProxy(oldViewpoint);
            if (viewpoint != oldViewpoint) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DVIEW__VIEWPOINT, oldViewpoint, viewpoint));
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
    public void setViewpoint(Viewpoint newViewpoint) {
        Viewpoint oldViewpoint = viewpoint;
        viewpoint = newViewpoint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DVIEW__VIEWPOINT, oldViewpoint, viewpoint));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void refresh() {
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
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS:
            return ((InternalEList<?>) getOwnedRepresentations()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS:
            return getOwnedRepresentations();
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            if (resolve)
                return getOwnedExtensions();
            return basicGetOwnedExtensions();
        case ViewpointPackage.DVIEW__INITIALIZED:
            return isInitialized();
        case ViewpointPackage.DVIEW__VIEWPOINT:
            if (resolve)
                return getViewpoint();
            return basicGetViewpoint();
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
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            getOwnedRepresentations().addAll((Collection<? extends DRepresentation>) newValue);
            return;
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) newValue);
            return;
        case ViewpointPackage.DVIEW__INITIALIZED:
            setInitialized((Boolean) newValue);
            return;
        case ViewpointPackage.DVIEW__VIEWPOINT:
            setViewpoint((Viewpoint) newValue);
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
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            return;
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) null);
            return;
        case ViewpointPackage.DVIEW__INITIALIZED:
            setInitialized(INITIALIZED_EDEFAULT);
            return;
        case ViewpointPackage.DVIEW__VIEWPOINT:
            setViewpoint((Viewpoint) null);
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
        case ViewpointPackage.DVIEW__OWNED_REPRESENTATIONS:
            return ownedRepresentations != null && !ownedRepresentations.isEmpty();
        case ViewpointPackage.DVIEW__OWNED_EXTENSIONS:
            return ownedExtensions != null;
        case ViewpointPackage.DVIEW__INITIALIZED:
            return initialized != INITIALIZED_EDEFAULT;
        case ViewpointPackage.DVIEW__VIEWPOINT:
            return viewpoint != null;
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
        result.append(" (initialized: ");
        result.append(initialized);
        result.append(')');
        return result.toString();
    }

} // DViewImpl
