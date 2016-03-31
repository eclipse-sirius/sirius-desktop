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
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DNode Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl#getOwnedDiagramElements
 * <em>Owned Diagram Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl#getChildrenPresentation
 * <em>Children Presentation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DNodeContainerImpl extends DDiagramElementContainerImpl implements DNodeContainer {
    /**
     * The cached value of the '{@link #getOwnedDiagramElements()
     * <em>Owned Diagram Elements</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedDiagramElements()
     * @generated
     * @ordered
     */
    protected EList<DDiagramElement> ownedDiagramElements;

    /**
     * The default value of the '{@link #getChildrenPresentation()
     * <em>Children Presentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChildrenPresentation()
     * @generated
     * @ordered
     */
    protected static final ContainerLayout CHILDREN_PRESENTATION_EDEFAULT = ContainerLayout.FREE_FORM;

    /**
     * The cached value of the '{@link #getChildrenPresentation()
     * <em>Children Presentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChildrenPresentation()
     * @generated
     * @ordered
     */
    protected ContainerLayout childrenPresentation = DNodeContainerImpl.CHILDREN_PRESENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DNodeContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DNODE_CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElement> getOwnedDiagramElements() {
        if (ownedDiagramElements == null) {
            ownedDiagramElements = new EObjectContainmentEList.Resolving<DDiagramElement>(DDiagramElement.class, this, DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS);
        }
        return ownedDiagramElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerLayout getChildrenPresentation() {
        return childrenPresentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setChildrenPresentation(ContainerLayout newChildrenPresentation) {
        ContainerLayout oldChildrenPresentation = childrenPresentation;
        childrenPresentation = newChildrenPresentation == null ? DNodeContainerImpl.CHILDREN_PRESENTATION_EDEFAULT : newChildrenPresentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION, oldChildrenPresentation, childrenPresentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS:
            return ((InternalEList<?>) getOwnedDiagramElements()).basicRemove(otherEnd, msgs);
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
        case DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS:
            return getOwnedDiagramElements();
        case DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION:
            return getChildrenPresentation();
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
        case DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            getOwnedDiagramElements().addAll((Collection<? extends DDiagramElement>) newValue);
            return;
        case DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION:
            setChildrenPresentation((ContainerLayout) newValue);
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
        case DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            return;
        case DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION:
            setChildrenPresentation(DNodeContainerImpl.CHILDREN_PRESENTATION_EDEFAULT);
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
        case DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS:
            return ownedDiagramElements != null && !ownedDiagramElements.isEmpty();
        case DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION:
            return childrenPresentation != DNodeContainerImpl.CHILDREN_PRESENTATION_EDEFAULT;
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
        result.append(" (childrenPresentation: "); //$NON-NLS-1$
        result.append(childrenPresentation);
        result.append(')');
        return result.toString();
    }

} // DNodeContainerImpl
