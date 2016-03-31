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
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Navigation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl#isCreateIfNotExistent
 * <em>Create If Not Existent</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl#getDiagramDescription
 * <em>Diagram Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationImpl extends ContainerModelOperationImpl implements Navigation {
    /**
     * The default value of the '{@link #isCreateIfNotExistent()
     * <em>Create If Not Existent</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isCreateIfNotExistent()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_IF_NOT_EXISTENT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCreateIfNotExistent()
     * <em>Create If Not Existent</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isCreateIfNotExistent()
     * @generated
     * @ordered
     */
    protected boolean createIfNotExistent = NavigationImpl.CREATE_IF_NOT_EXISTENT_EDEFAULT;

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
    protected NavigationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.NAVIGATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isCreateIfNotExistent() {
        return createIfNotExistent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCreateIfNotExistent(boolean newCreateIfNotExistent) {
        boolean oldCreateIfNotExistent = createIfNotExistent;
        createIfNotExistent = newCreateIfNotExistent;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT, oldCreateIfNotExistent, createIfNotExistent));
        }
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION, oldDiagramDescription, diagramDescription));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION, oldDiagramDescription, diagramDescription));
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
        case ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT:
            return isCreateIfNotExistent();
        case ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION:
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
        case ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT:
            setCreateIfNotExistent((Boolean) newValue);
            return;
        case ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION:
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
        case ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT:
            setCreateIfNotExistent(NavigationImpl.CREATE_IF_NOT_EXISTENT_EDEFAULT);
            return;
        case ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION:
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
        case ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT:
            return createIfNotExistent != NavigationImpl.CREATE_IF_NOT_EXISTENT_EDEFAULT;
        case ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION:
            return diagramDescription != null;
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
        result.append(" (createIfNotExistent: "); //$NON-NLS-1$
        result.append(createIfNotExistent);
        result.append(')');
        return result.toString();
    }

} // NavigationImpl
