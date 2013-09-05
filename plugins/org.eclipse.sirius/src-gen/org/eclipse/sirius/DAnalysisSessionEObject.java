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
package org.eclipse.sirius;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.sirius.description.Sirius;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DAnalysis Session EObject</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.DAnalysisSessionEObject#isOpen <em>Open
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.DAnalysisSessionEObject#isBlocked <em>
 * Blocked</em>}</li>
 * <li>{@link org.eclipse.sirius.DAnalysisSessionEObject#getResources <em>
 * Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.DAnalysisSessionEObject#getControlledResources
 * <em>Controlled Resources</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.DAnalysisSessionEObject#getActivatedSiriuss
 * <em>Activated Siriuss</em>}</li>
 * <li>{@link org.eclipse.sirius.DAnalysisSessionEObject#getAnalyses <em>
 * Analyses</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.DAnalysisSessionEObject#getSynchronizationStatus
 * <em>Synchronization Status</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject()
 * @model
 * @generated
 */
public interface DAnalysisSessionEObject extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Open</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Open</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Open</em>' attribute.
     * @see #setOpen(boolean)
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_Open()
     * @model required="true"
     * @generated
     */
    boolean isOpen();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#isOpen
     * <em>Open</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Open</em>' attribute.
     * @see #isOpen()
     * @generated
     */
    void setOpen(boolean value);

    /**
     * Returns the value of the '<em><b>Blocked</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blocked</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Blocked</em>' attribute.
     * @see #setBlocked(boolean)
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_Blocked()
     * @model required="true"
     * @generated
     */
    boolean isBlocked();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#isBlocked
     * <em>Blocked</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Blocked</em>' attribute.
     * @see #isBlocked()
     * @generated
     */
    void setBlocked(boolean value);

    /**
     * Returns the value of the '<em><b>Resources</b></em>' attribute list. The
     * list contents are of type {@link org.eclipse.emf.ecore.resource.Resource}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resources</em>' attribute list.
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_Resources()
     * @model transient="true"
     * @generated
     */
    EList<Resource> getResources();

    /**
     * Returns the value of the '<em><b>Controlled Resources</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.resource.Resource}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controlled Resources</em>' attribute list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Controlled Resources</em>' attribute list.
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_ControlledResources()
     * @model transient="true"
     * @generated
     */
    EList<Resource> getControlledResources();

    /**
     * Returns the value of the '<em><b>Activated Siriuss</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.description.Sirius}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Activated Siriuss</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Activated Siriuss</em>' reference list.
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_ActivatedSiriuss()
     * @model
     * @generated
     */
    EList<Sirius> getActivatedSiriuss();

    /**
     * Returns the value of the '<em><b>Analyses</b></em>' reference list. The
     * list contents are of type {@link org.eclipse.sirius.DAnalysis}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Analyses</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Analyses</em>' reference list.
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_Analyses()
     * @model
     * @generated
     */
    EList<DAnalysis> getAnalyses();

    /**
     * Returns the value of the '<em><b>Synchronization Status</b></em>'
     * attribute. The default value is <code>"0"</code>. The literals are from
     * the enumeration {@link org.eclipse.sirius.SyncStatus}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Synchronization Status</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Synchronization Status</em>' attribute.
     * @see org.eclipse.sirius.SyncStatus
     * @see #setSynchronizationStatus(SyncStatus)
     * @see org.eclipse.sirius.SiriusPackage#getDAnalysisSessionEObject_SynchronizationStatus()
     * @model default="0" required="true"
     * @generated
     */
    SyncStatus getSynchronizationStatus();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getSynchronizationStatus
     * <em>Synchronization Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Synchronization Status</em>'
     *            attribute.
     * @see org.eclipse.sirius.SyncStatus
     * @see #getSynchronizationStatus()
     * @generated
     */
    void setSynchronizationStatus(SyncStatus value);

} // DAnalysisSessionEObject
