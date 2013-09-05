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
package org.eclipse.sirius.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.MetaModelExtension;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.Sirius;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DView</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getOwnedRepresentations <em>
 * Owned Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getOwnedExtensions <em>Owned
 * Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getAllRepresentations <em>All
 * Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getHiddenRepresentations <em>
 * Hidden Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getReferencedRepresentations
 * <em>Referenced Representations</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#isInitialized <em>Initialized
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DViewImpl#getSirius <em>Sirius
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DViewImpl extends EObjectImpl implements DView {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
     * The cached value of the '{@link #getHiddenRepresentations()
     * <em>Hidden Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHiddenRepresentations()
     * @generated
     * @ordered
     */
    protected EList<DRepresentation> hiddenRepresentations;

    /**
     * The cached value of the '{@link #getReferencedRepresentations()
     * <em>Referenced Representations</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReferencedRepresentations()
     * @generated
     * @ordered
     */
    protected EList<DRepresentation> referencedRepresentations;

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
     * The cached value of the '{@link #getSirius() <em>Sirius</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSirius()
     * @generated
     * @ordered
     */
    protected Sirius viewpoint;

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
        return SiriusPackage.Literals.DVIEW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DRepresentation> getOwnedRepresentations() {
        if (ownedRepresentations == null) {
            ownedRepresentations = new EObjectContainmentEList.Resolving<DRepresentation>(DRepresentation.class, this, SiriusPackage.DVIEW__OWNED_REPRESENTATIONS);
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
                NotificationChain msgs = oldOwnedExtensions.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DVIEW__OWNED_EXTENSIONS, null, null);
                if (newOwnedExtensions.eInternalContainer() == null) {
                    msgs = newOwnedExtensions.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.DVIEW__OWNED_EXTENSIONS, oldOwnedExtensions, ownedExtensions));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SiriusPackage.DVIEW__OWNED_EXTENSIONS, oldOwnedExtensions, newOwnedExtensions);
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
                msgs = ((InternalEObject) ownedExtensions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            if (newOwnedExtensions != null)
                msgs = ((InternalEObject) newOwnedExtensions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DVIEW__OWNED_EXTENSIONS, null, msgs);
            msgs = basicSetOwnedExtensions(newOwnedExtensions, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DVIEW__OWNED_EXTENSIONS, newOwnedExtensions, newOwnedExtensions));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DRepresentation> getAllRepresentations() {
        // TODO: implement this method to return the 'All Representations'
        // reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DRepresentation> getHiddenRepresentations() {
        if (hiddenRepresentations == null) {
            hiddenRepresentations = new EObjectContainmentEList.Resolving<DRepresentation>(DRepresentation.class, this, SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS);
        }
        return hiddenRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DRepresentation> getReferencedRepresentations() {
        if (referencedRepresentations == null) {
            referencedRepresentations = new EObjectResolvingEList<DRepresentation>(DRepresentation.class, this, SiriusPackage.DVIEW__REFERENCED_REPRESENTATIONS);
        }
        return referencedRepresentations;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DVIEW__INITIALIZED, oldInitialized, initialized));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Sirius getSirius() {
        if (viewpoint != null && viewpoint.eIsProxy()) {
            InternalEObject oldSirius = (InternalEObject) viewpoint;
            viewpoint = (Sirius) eResolveProxy(oldSirius);
            if (viewpoint != oldSirius) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.DVIEW__VIEWPOINT, oldSirius, viewpoint));
            }
        }
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Sirius basicGetSirius() {
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSirius(Sirius newSirius) {
        Sirius oldSirius = viewpoint;
        viewpoint = newSirius;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DVIEW__VIEWPOINT, oldSirius, viewpoint));
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
        case SiriusPackage.DVIEW__OWNED_REPRESENTATIONS:
            return ((InternalEList<?>) getOwnedRepresentations()).basicRemove(otherEnd, msgs);
        case SiriusPackage.DVIEW__OWNED_EXTENSIONS:
            return basicSetOwnedExtensions(null, msgs);
        case SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS:
            return ((InternalEList<?>) getHiddenRepresentations()).basicRemove(otherEnd, msgs);
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
        case SiriusPackage.DVIEW__OWNED_REPRESENTATIONS:
            return getOwnedRepresentations();
        case SiriusPackage.DVIEW__OWNED_EXTENSIONS:
            if (resolve)
                return getOwnedExtensions();
            return basicGetOwnedExtensions();
        case SiriusPackage.DVIEW__ALL_REPRESENTATIONS:
            return getAllRepresentations();
        case SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS:
            return getHiddenRepresentations();
        case SiriusPackage.DVIEW__REFERENCED_REPRESENTATIONS:
            return getReferencedRepresentations();
        case SiriusPackage.DVIEW__INITIALIZED:
            return isInitialized();
        case SiriusPackage.DVIEW__VIEWPOINT:
            if (resolve)
                return getSirius();
            return basicGetSirius();
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
        case SiriusPackage.DVIEW__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            getOwnedRepresentations().addAll((Collection<? extends DRepresentation>) newValue);
            return;
        case SiriusPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) newValue);
            return;
        case SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS:
            getHiddenRepresentations().clear();
            getHiddenRepresentations().addAll((Collection<? extends DRepresentation>) newValue);
            return;
        case SiriusPackage.DVIEW__REFERENCED_REPRESENTATIONS:
            getReferencedRepresentations().clear();
            getReferencedRepresentations().addAll((Collection<? extends DRepresentation>) newValue);
            return;
        case SiriusPackage.DVIEW__INITIALIZED:
            setInitialized((Boolean) newValue);
            return;
        case SiriusPackage.DVIEW__VIEWPOINT:
            setSirius((Sirius) newValue);
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
        case SiriusPackage.DVIEW__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            return;
        case SiriusPackage.DVIEW__OWNED_EXTENSIONS:
            setOwnedExtensions((MetaModelExtension) null);
            return;
        case SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS:
            getHiddenRepresentations().clear();
            return;
        case SiriusPackage.DVIEW__REFERENCED_REPRESENTATIONS:
            getReferencedRepresentations().clear();
            return;
        case SiriusPackage.DVIEW__INITIALIZED:
            setInitialized(INITIALIZED_EDEFAULT);
            return;
        case SiriusPackage.DVIEW__VIEWPOINT:
            setSirius((Sirius) null);
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
        case SiriusPackage.DVIEW__OWNED_REPRESENTATIONS:
            return ownedRepresentations != null && !ownedRepresentations.isEmpty();
        case SiriusPackage.DVIEW__OWNED_EXTENSIONS:
            return ownedExtensions != null;
        case SiriusPackage.DVIEW__ALL_REPRESENTATIONS:
            return !getAllRepresentations().isEmpty();
        case SiriusPackage.DVIEW__HIDDEN_REPRESENTATIONS:
            return hiddenRepresentations != null && !hiddenRepresentations.isEmpty();
        case SiriusPackage.DVIEW__REFERENCED_REPRESENTATIONS:
            return referencedRepresentations != null && !referencedRepresentations.isEmpty();
        case SiriusPackage.DVIEW__INITIALIZED:
            return initialized != INITIALIZED_EDEFAULT;
        case SiriusPackage.DVIEW__VIEWPOINT:
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
