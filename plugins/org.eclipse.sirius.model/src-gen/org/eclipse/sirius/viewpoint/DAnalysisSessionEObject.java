/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DAnalysis Session EObject</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen <em>Open</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getControlledResources <em>Controlled
 * Resources</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getActivatedViewpoints <em>Activated
 * Viewpoints</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getAnalyses <em>Analyses</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus <em>Synchronization
 * Status</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject()
 * @model
 * @generated
 */
public interface DAnalysisSessionEObject extends EObject {
    /**
     * Returns the value of the '<em><b>Open</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Open</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Open</em>' attribute.
     * @see #setOpen(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_Open()
     * @model required="true"
     * @generated
     */
    boolean isOpen();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen <em>Open</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Open</em>' attribute.
     * @see #isOpen()
     * @generated
     */
    void setOpen(boolean value);

    /**
     * Returns the value of the '<em><b>Resources</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.emf.ecore.resource.Resource}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' attribute list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Resources</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_Resources()
     * @model transient="true"
     * @generated
     */
    EList<Resource> getResources();

    /**
     * Returns the value of the '<em><b>Controlled Resources</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.emf.ecore.resource.Resource}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controlled Resources</em>' attribute list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controlled Resources</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_ControlledResources()
     * @model transient="true"
     * @generated
     */
    EList<Resource> getControlledResources();

    /**
     * Returns the value of the '<em><b>Activated Viewpoints</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activated Viewpoints</em>' reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Activated Viewpoints</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_ActivatedViewpoints()
     * @model
     * @generated
     */
    EList<Viewpoint> getActivatedViewpoints();

    /**
     * Returns the value of the '<em><b>Analyses</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DAnalysis}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Analyses</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Analyses</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_Analyses()
     * @model
     * @generated
     */
    EList<DAnalysis> getAnalyses();

    /**
     * Returns the value of the '<em><b>Synchronization Status</b></em>' attribute. The default value is
     * <code>"dirty"</code>. The literals are from the enumeration {@link org.eclipse.sirius.viewpoint.SyncStatus}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Synchronization Status</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Synchronization Status</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @see #setSynchronizationStatus(SyncStatus)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDAnalysisSessionEObject_SynchronizationStatus()
     * @model default="dirty" required="true"
     * @generated
     */
    SyncStatus getSynchronizationStatus();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus
     * <em>Synchronization Status</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Synchronization Status</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @see #getSynchronizationStatus()
     * @generated
     */
    void setSynchronizationStatus(SyncStatus value);

} // DAnalysisSessionEObject
