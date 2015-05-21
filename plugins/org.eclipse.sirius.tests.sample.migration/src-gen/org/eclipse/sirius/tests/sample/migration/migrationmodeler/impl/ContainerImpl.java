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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl#getContainerRepresentations
 * <em>Container Representations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl#getElements
 * <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerImpl extends GraphicalElementImpl implements Container {
    /**
     * The cached value of the '{@link #getContainerRepresentations()
     * <em>Container Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainerRepresentations()
     * @generated
     * @ordered
     */
    protected EList<ContainerRepresentation> containerRepresentations;

    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElements()
     * @generated
     * @ordered
     */
    protected EList<GraphicalElement> elements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerRepresentation> getContainerRepresentations() {
        if (containerRepresentations == null) {
            containerRepresentations = new EObjectContainmentEList<ContainerRepresentation>(ContainerRepresentation.class, this, MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS);
        }
        return containerRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GraphicalElement> getElements() {
        if (elements == null) {
            elements = new EObjectContainmentEList<GraphicalElement>(GraphicalElement.class, this, MigrationmodelerPackage.CONTAINER__ELEMENTS);
        }
        return elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS:
            return ((InternalEList<?>) getContainerRepresentations()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.CONTAINER__ELEMENTS:
            return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS:
            return getContainerRepresentations();
        case MigrationmodelerPackage.CONTAINER__ELEMENTS:
            return getElements();
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
        case MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS:
            getContainerRepresentations().clear();
            getContainerRepresentations().addAll((Collection<? extends ContainerRepresentation>) newValue);
            return;
        case MigrationmodelerPackage.CONTAINER__ELEMENTS:
            getElements().clear();
            getElements().addAll((Collection<? extends GraphicalElement>) newValue);
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
        case MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS:
            getContainerRepresentations().clear();
            return;
        case MigrationmodelerPackage.CONTAINER__ELEMENTS:
            getElements().clear();
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
        case MigrationmodelerPackage.CONTAINER__CONTAINER_REPRESENTATIONS:
            return containerRepresentations != null && !containerRepresentations.isEmpty();
        case MigrationmodelerPackage.CONTAINER__ELEMENTS:
            return elements != null && !elements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ContainerImpl
