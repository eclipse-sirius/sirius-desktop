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
package org.eclipse.sirius.diagram.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Navigation Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DiagramNavigationDescriptionImpl#getDiagramDescription
 * <em>Diagram Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramNavigationDescriptionImpl extends RepresentationNavigationDescriptionImpl implements DiagramNavigationDescription {
    /**
     * The cached value of the '{@link #getDiagramDescription()
     * <em>Diagram Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDiagramDescription()
     * @generated
     * @ordered
     */
    protected DiagramDescription diagramDescription;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramNavigationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DIAGRAM_NAVIGATION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramDescription getDiagramDescription() {
        if (diagramDescription != null && diagramDescription.eIsProxy()) {
            InternalEObject oldDiagramDescription = (InternalEObject) diagramDescription;
            diagramDescription = (DiagramDescription) eResolveProxy(oldDiagramDescription);
            if (diagramDescription != oldDiagramDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION, oldDiagramDescription, diagramDescription));
                }
            }
        }
        return diagramDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DiagramDescription basicGetDiagramDescription() {
        return diagramDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDiagramDescription(DiagramDescription newDiagramDescription) {
        DiagramDescription oldDiagramDescription = diagramDescription;
        diagramDescription = newDiagramDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION, oldDiagramDescription, diagramDescription));
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
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION:
            if (resolve) {
                return getDiagramDescription();
            }
            return basicGetDiagramDescription();
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
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION:
            setDiagramDescription((DiagramDescription) newValue);
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
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION:
            setDiagramDescription((DiagramDescription) null);
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
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION:
            return diagramDescription != null;
        }
        return super.eIsSet(featureID);
    }

} // DiagramNavigationDescriptionImpl
