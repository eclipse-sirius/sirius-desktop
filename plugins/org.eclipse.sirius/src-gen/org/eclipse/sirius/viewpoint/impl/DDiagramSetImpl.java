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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramSet;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>View Point Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl#getDiagrams <em>
 * Diagrams</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl#getView <em>View
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DDiagramSetImpl extends EObjectImpl implements DDiagramSet {
    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected DiagramDescription description;

    /**
     * This is true if the Description reference has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean descriptionESet;

    /**
     * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDiagrams()
     * @generated
     * @ordered
     */
    protected EList<DDiagram> diagrams;

    /**
     * The cached value of the '{@link #getView() <em>View</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getView()
     * @generated
     * @ordered
     */
    protected DRepresentationContainer view;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DDiagramSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DDIAGRAM_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (DiagramDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DDIAGRAM_SET__DESCRIPTION, oldDescription, description));
            }
        }
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDescription(DiagramDescription newDescription) {
        DiagramDescription oldDescription = description;
        description = newDescription;
        boolean oldDescriptionESet = descriptionESet;
        descriptionESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DDIAGRAM_SET__DESCRIPTION, oldDescription, description, !oldDescriptionESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void unsetDescription() {
        DiagramDescription oldDescription = description;
        boolean oldDescriptionESet = descriptionESet;
        description = null;
        descriptionESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ViewpointPackage.DDIAGRAM_SET__DESCRIPTION, oldDescription, null, oldDescriptionESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSetDescription() {
        return descriptionESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagram> getDiagrams() {
        if (diagrams == null) {
            diagrams = new EObjectResolvingEList<DDiagram>(DDiagram.class, this, ViewpointPackage.DDIAGRAM_SET__DIAGRAMS);
        }
        return diagrams;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DRepresentationContainer getView() {
        if (view != null && view.eIsProxy()) {
            InternalEObject oldView = (InternalEObject) view;
            view = (DRepresentationContainer) eResolveProxy(oldView);
            if (view != oldView) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DDIAGRAM_SET__VIEW, oldView, view));
            }
        }
        return view;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DRepresentationContainer basicGetView() {
        return view;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setView(DRepresentationContainer newView) {
        DRepresentationContainer oldView = view;
        view = newView;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DDIAGRAM_SET__VIEW, oldView, view));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.DDIAGRAM_SET__DESCRIPTION:
            if (resolve)
                return getDescription();
            return basicGetDescription();
        case ViewpointPackage.DDIAGRAM_SET__DIAGRAMS:
            return getDiagrams();
        case ViewpointPackage.DDIAGRAM_SET__VIEW:
            if (resolve)
                return getView();
            return basicGetView();
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
        case ViewpointPackage.DDIAGRAM_SET__DESCRIPTION:
            setDescription((DiagramDescription) newValue);
            return;
        case ViewpointPackage.DDIAGRAM_SET__DIAGRAMS:
            getDiagrams().clear();
            getDiagrams().addAll((Collection<? extends DDiagram>) newValue);
            return;
        case ViewpointPackage.DDIAGRAM_SET__VIEW:
            setView((DRepresentationContainer) newValue);
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
        case ViewpointPackage.DDIAGRAM_SET__DESCRIPTION:
            unsetDescription();
            return;
        case ViewpointPackage.DDIAGRAM_SET__DIAGRAMS:
            getDiagrams().clear();
            return;
        case ViewpointPackage.DDIAGRAM_SET__VIEW:
            setView((DRepresentationContainer) null);
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
        case ViewpointPackage.DDIAGRAM_SET__DESCRIPTION:
            return isSetDescription();
        case ViewpointPackage.DDIAGRAM_SET__DIAGRAMS:
            return diagrams != null && !diagrams.isEmpty();
        case ViewpointPackage.DDIAGRAM_SET__VIEW:
            return view != null;
        }
        return super.eIsSet(featureID);
    }

} // DDiagramSetImpl
