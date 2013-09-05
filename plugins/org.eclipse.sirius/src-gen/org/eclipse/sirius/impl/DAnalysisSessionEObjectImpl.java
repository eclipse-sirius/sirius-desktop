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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DAnalysisSessionEObject;
import org.eclipse.sirius.SyncStatus;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.Sirius;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnalysis Session EObject</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#isOpen <em>
 * Open</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#isBlocked
 * <em>Blocked</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#getResources
 * <em>Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#getControlledResources
 * <em>Controlled Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#getActivatedSiriuss
 * <em>Activated Siriuss</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#getAnalyses
 * <em>Analyses</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl#getSynchronizationStatus
 * <em>Synchronization Status</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DAnalysisSessionEObjectImpl extends EObjectImpl implements DAnalysisSessionEObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The default value of the '{@link #isOpen() <em>Open</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isOpen()
     * @generated
     * @ordered
     */
    protected static final boolean OPEN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOpen() <em>Open</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isOpen()
     * @generated
     * @ordered
     */
    protected boolean open = OPEN_EDEFAULT;

    /**
     * The default value of the '{@link #isBlocked() <em>Blocked</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isBlocked()
     * @generated
     * @ordered
     */
    protected static final boolean BLOCKED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBlocked() <em>Blocked</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isBlocked()
     * @generated
     * @ordered
     */
    protected boolean blocked = BLOCKED_EDEFAULT;

    /**
     * The cached value of the '{@link #getResources() <em>Resources</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected EList<Resource> resources;

    /**
     * The cached value of the '{@link #getControlledResources()
     * <em>Controlled Resources</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getControlledResources()
     * @generated
     * @ordered
     */
    protected EList<Resource> controlledResources;

    /**
     * The cached value of the '{@link #getActivatedSiriuss()
     * <em>Activated Siriuss</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getActivatedSiriuss()
     * @generated
     * @ordered
     */
    protected EList<Sirius> activatedSiriuss;

    /**
     * The cached value of the '{@link #getAnalyses() <em>Analyses</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAnalyses()
     * @generated
     * @ordered
     */
    protected EList<DAnalysis> analyses;

    /**
     * The default value of the '{@link #getSynchronizationStatus()
     * <em>Synchronization Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSynchronizationStatus()
     * @generated
     * @ordered
     */
    protected static final SyncStatus SYNCHRONIZATION_STATUS_EDEFAULT = SyncStatus.DIRTY;

    /**
     * The cached value of the '{@link #getSynchronizationStatus()
     * <em>Synchronization Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSynchronizationStatus()
     * @generated
     * @ordered
     */
    protected SyncStatus synchronizationStatus = SYNCHRONIZATION_STATUS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DAnalysisSessionEObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SiriusPackage.Literals.DANALYSIS_SESSION_EOBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOpen(boolean newOpen) {
        boolean oldOpen = open;
        open = newOpen;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DANALYSIS_SESSION_EOBJECT__OPEN, oldOpen, open));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBlocked(boolean newBlocked) {
        boolean oldBlocked = blocked;
        blocked = newBlocked;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DANALYSIS_SESSION_EOBJECT__BLOCKED, oldBlocked, blocked));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Resource> getResources() {
        if (resources == null) {
            resources = new EDataTypeUniqueEList<Resource>(Resource.class, this, SiriusPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Resource> getControlledResources() {
        if (controlledResources == null) {
            controlledResources = new EDataTypeUniqueEList<Resource>(Resource.class, this, SiriusPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES);
        }
        return controlledResources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Sirius> getActivatedSiriuss() {
        if (activatedSiriuss == null) {
            activatedSiriuss = new EObjectResolvingEList<Sirius>(Sirius.class, this, SiriusPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS);
        }
        return activatedSiriuss;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DAnalysis> getAnalyses() {
        if (analyses == null) {
            analyses = new EObjectResolvingEList<DAnalysis>(DAnalysis.class, this, SiriusPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES);
        }
        return analyses;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SyncStatus getSynchronizationStatus() {
        return synchronizationStatus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSynchronizationStatus(SyncStatus newSynchronizationStatus) {
        SyncStatus oldSynchronizationStatus = synchronizationStatus;
        synchronizationStatus = newSynchronizationStatus == null ? SYNCHRONIZATION_STATUS_EDEFAULT : newSynchronizationStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS, oldSynchronizationStatus, synchronizationStatus));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            return isOpen();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__BLOCKED:
            return isBlocked();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            return getResources();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            return getControlledResources();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            return getActivatedSiriuss();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            return getAnalyses();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            return getSynchronizationStatus();
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
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            setOpen((Boolean) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__BLOCKED:
            setBlocked((Boolean) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            getResources().clear();
            getResources().addAll((Collection<? extends Resource>) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            getControlledResources().clear();
            getControlledResources().addAll((Collection<? extends Resource>) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            getActivatedSiriuss().clear();
            getActivatedSiriuss().addAll((Collection<? extends Sirius>) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            getAnalyses().clear();
            getAnalyses().addAll((Collection<? extends DAnalysis>) newValue);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            setSynchronizationStatus((SyncStatus) newValue);
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
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            setOpen(OPEN_EDEFAULT);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__BLOCKED:
            setBlocked(BLOCKED_EDEFAULT);
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            getResources().clear();
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            getControlledResources().clear();
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            getActivatedSiriuss().clear();
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            getAnalyses().clear();
            return;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            setSynchronizationStatus(SYNCHRONIZATION_STATUS_EDEFAULT);
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
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            return open != OPEN_EDEFAULT;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__BLOCKED:
            return blocked != BLOCKED_EDEFAULT;
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            return resources != null && !resources.isEmpty();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            return controlledResources != null && !controlledResources.isEmpty();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            return activatedSiriuss != null && !activatedSiriuss.isEmpty();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            return analyses != null && !analyses.isEmpty();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            return synchronizationStatus != SYNCHRONIZATION_STATUS_EDEFAULT;
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
        result.append(" (open: ");
        result.append(open);
        result.append(", blocked: ");
        result.append(blocked);
        result.append(", resources: ");
        result.append(resources);
        result.append(", controlledResources: ");
        result.append(controlledResources);
        result.append(", synchronizationStatus: ");
        result.append(synchronizationStatus);
        result.append(')');
        return result.toString();
    }

} // DAnalysisSessionEObjectImpl
