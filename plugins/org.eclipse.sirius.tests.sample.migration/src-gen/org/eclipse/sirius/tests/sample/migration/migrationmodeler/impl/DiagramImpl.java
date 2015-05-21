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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl#getContainers
 * <em>Containers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl#getNodes
 * <em>Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl#getEdges
 * <em>Edges</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl#getLayers
 * <em>Layers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramImpl extends RepresentationImpl implements Diagram {
    /**
     * The cached value of the '{@link #getContainers() <em>Containers</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainers()
     * @generated
     * @ordered
     */
    protected EList<Container> containers;

    /**
     * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNodes()
     * @generated
     * @ordered
     */
    protected EList<Node> nodes;

    /**
     * The cached value of the '{@link #getEdges() <em>Edges</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEdges()
     * @generated
     * @ordered
     */
    protected EList<Edge> edges;

    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<Filter> filters;

    /**
     * The cached value of the '{@link #getLayers() <em>Layers</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLayers()
     * @generated
     * @ordered
     */
    protected EList<Layer> layers;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.DIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Container> getContainers() {
        if (containers == null) {
            containers = new EObjectContainmentEList<Container>(Container.class, this, MigrationmodelerPackage.DIAGRAM__CONTAINERS);
        }
        return containers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Node> getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentEList<Node>(Node.class, this, MigrationmodelerPackage.DIAGRAM__NODES);
        }
        return nodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Edge> getEdges() {
        if (edges == null) {
            edges = new EObjectContainmentEList<Edge>(Edge.class, this, MigrationmodelerPackage.DIAGRAM__EDGES);
        }
        return edges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Filter> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList<Filter>(Filter.class, this, MigrationmodelerPackage.DIAGRAM__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Layer> getLayers() {
        if (layers == null) {
            layers = new EObjectContainmentEList<Layer>(Layer.class, this, MigrationmodelerPackage.DIAGRAM__LAYERS);
        }
        return layers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.DIAGRAM__CONTAINERS:
            return ((InternalEList<?>) getContainers()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.DIAGRAM__NODES:
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.DIAGRAM__EDGES:
            return ((InternalEList<?>) getEdges()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.DIAGRAM__FILTERS:
            return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.DIAGRAM__LAYERS:
            return ((InternalEList<?>) getLayers()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.DIAGRAM__CONTAINERS:
            return getContainers();
        case MigrationmodelerPackage.DIAGRAM__NODES:
            return getNodes();
        case MigrationmodelerPackage.DIAGRAM__EDGES:
            return getEdges();
        case MigrationmodelerPackage.DIAGRAM__FILTERS:
            return getFilters();
        case MigrationmodelerPackage.DIAGRAM__LAYERS:
            return getLayers();
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
        case MigrationmodelerPackage.DIAGRAM__CONTAINERS:
            getContainers().clear();
            getContainers().addAll((Collection<? extends Container>) newValue);
            return;
        case MigrationmodelerPackage.DIAGRAM__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends Node>) newValue);
            return;
        case MigrationmodelerPackage.DIAGRAM__EDGES:
            getEdges().clear();
            getEdges().addAll((Collection<? extends Edge>) newValue);
            return;
        case MigrationmodelerPackage.DIAGRAM__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends Filter>) newValue);
            return;
        case MigrationmodelerPackage.DIAGRAM__LAYERS:
            getLayers().clear();
            getLayers().addAll((Collection<? extends Layer>) newValue);
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
        case MigrationmodelerPackage.DIAGRAM__CONTAINERS:
            getContainers().clear();
            return;
        case MigrationmodelerPackage.DIAGRAM__NODES:
            getNodes().clear();
            return;
        case MigrationmodelerPackage.DIAGRAM__EDGES:
            getEdges().clear();
            return;
        case MigrationmodelerPackage.DIAGRAM__FILTERS:
            getFilters().clear();
            return;
        case MigrationmodelerPackage.DIAGRAM__LAYERS:
            getLayers().clear();
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
        case MigrationmodelerPackage.DIAGRAM__CONTAINERS:
            return containers != null && !containers.isEmpty();
        case MigrationmodelerPackage.DIAGRAM__NODES:
            return nodes != null && !nodes.isEmpty();
        case MigrationmodelerPackage.DIAGRAM__EDGES:
            return edges != null && !edges.isEmpty();
        case MigrationmodelerPackage.DIAGRAM__FILTERS:
            return filters != null && !filters.isEmpty();
        case MigrationmodelerPackage.DIAGRAM__LAYERS:
            return layers != null && !layers.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DiagramImpl
