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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.SyncStatus;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnalysis Session EObject</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#isOpen
 * <em>Open</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#getResources
 * <em>Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#getControlledResources
 * <em>Controlled Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#getActivatedViewpoints
 * <em>Activated Viewpoints</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#getAnalyses
 * <em>Analyses</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl#getSynchronizationStatus
 * <em>Synchronization Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DAnalysisSessionEObjectImpl extends MinimalEObjectImpl.Container implements DAnalysisSessionEObject {
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
    protected boolean open = DAnalysisSessionEObjectImpl.OPEN_EDEFAULT;

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
     * The cached value of the '{@link #getActivatedViewpoints()
     * <em>Activated Viewpoints</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getActivatedViewpoints()
     * @generated
     * @ordered
     */
    protected EList<Viewpoint> activatedViewpoints;

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
    protected SyncStatus synchronizationStatus = DAnalysisSessionEObjectImpl.SYNCHRONIZATION_STATUS_EDEFAULT;

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
        return ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isOpen() {
        return open;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setOpen(boolean newOpen) {
        boolean oldOpen = open;
        open = newOpen;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN, oldOpen, open));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Resource> getResources() {
        if (resources == null) {
            resources = new EDataTypeUniqueEList<Resource>(Resource.class, this, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Resource> getControlledResources() {
        if (controlledResources == null) {
            controlledResources = new EDataTypeUniqueEList<Resource>(Resource.class, this, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES);
        }
        return controlledResources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Viewpoint> getActivatedViewpoints() {
        if (activatedViewpoints == null) {
            activatedViewpoints = new EObjectResolvingEList<Viewpoint>(Viewpoint.class, this, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS);
        }
        return activatedViewpoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DAnalysis> getAnalyses() {
        if (analyses == null) {
            analyses = new EObjectResolvingEList<DAnalysis>(DAnalysis.class, this, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES);
        }
        return analyses;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SyncStatus getSynchronizationStatus() {
        return synchronizationStatus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSynchronizationStatus(SyncStatus newSynchronizationStatus) {
        SyncStatus oldSynchronizationStatus = synchronizationStatus;
        synchronizationStatus = newSynchronizationStatus == null ? DAnalysisSessionEObjectImpl.SYNCHRONIZATION_STATUS_EDEFAULT : newSynchronizationStatus;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS, oldSynchronizationStatus, synchronizationStatus));
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
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            return isOpen();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            return getResources();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            return getControlledResources();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            return getActivatedViewpoints();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            return getAnalyses();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
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
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            setOpen((Boolean) newValue);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            getResources().clear();
            getResources().addAll((Collection<? extends Resource>) newValue);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            getControlledResources().clear();
            getControlledResources().addAll((Collection<? extends Resource>) newValue);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            getActivatedViewpoints().clear();
            getActivatedViewpoints().addAll((Collection<? extends Viewpoint>) newValue);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            getAnalyses().clear();
            getAnalyses().addAll((Collection<? extends DAnalysis>) newValue);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
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
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            setOpen(DAnalysisSessionEObjectImpl.OPEN_EDEFAULT);
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            getResources().clear();
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            getControlledResources().clear();
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            getActivatedViewpoints().clear();
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            getAnalyses().clear();
            return;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            setSynchronizationStatus(DAnalysisSessionEObjectImpl.SYNCHRONIZATION_STATUS_EDEFAULT);
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
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
            return open != DAnalysisSessionEObjectImpl.OPEN_EDEFAULT;
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
            return resources != null && !resources.isEmpty();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
            return controlledResources != null && !controlledResources.isEmpty();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS:
            return activatedViewpoints != null && !activatedViewpoints.isEmpty();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES:
            return analyses != null && !analyses.isEmpty();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
            return synchronizationStatus != DAnalysisSessionEObjectImpl.SYNCHRONIZATION_STATUS_EDEFAULT;
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
        result.append(" (open: "); //$NON-NLS-1$
        result.append(open);
        result.append(", resources: "); //$NON-NLS-1$
        result.append(resources);
        result.append(", controlledResources: "); //$NON-NLS-1$
        result.append(controlledResources);
        result.append(", synchronizationStatus: "); //$NON-NLS-1$
        result.append(synchronizationStatus);
        result.append(')');
        return result.toString();
    }

} // DAnalysisSessionEObjectImpl
