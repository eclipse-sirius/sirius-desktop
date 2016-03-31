/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FilterVariableHistory;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DDiagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getOwnedDiagramElements
 * <em>Owned Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getDiagramElements
 * <em>Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getEdges
 * <em>Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getNodes
 * <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getNodeListElements
 * <em>Node List Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getContainers
 * <em>Containers</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getCurrentConcern
 * <em>Current Concern</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getActivatedFilters
 * <em>Activated Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getAllFilters
 * <em>All Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getActivatedRules
 * <em>Activated Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getActivateBehaviors
 * <em>Activate Behaviors</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getFilterVariableHistory
 * <em>Filter Variable History</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getActivatedLayers
 * <em>Activated Layers</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#isSynchronized
 * <em>Synchronized</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getHiddenElements
 * <em>Hidden Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#isIsInLayoutingMode
 * <em>Is In Layouting Mode</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DDiagramImpl#getHeaderHeight
 * <em>Header Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DDiagramImpl extends DRepresentationImpl implements DDiagram {
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
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected DiagramDescription description;

    /**
     * The cached value of the '{@link #getCurrentConcern()
     * <em>Current Concern</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getCurrentConcern()
     * @generated
     * @ordered
     */
    protected ConcernDescription currentConcern;

    /**
     * The cached value of the '{@link #getActivatedFilters()
     * <em>Activated Filters</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getActivatedFilters()
     * @generated
     * @ordered
     */
    protected EList<FilterDescription> activatedFilters;

    /**
     * The cached value of the '{@link #getActivatedRules()
     * <em>Activated Rules</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getActivatedRules()
     * @generated
     * @ordered
     */
    protected EList<ValidationRule> activatedRules;

    /**
     * The cached value of the '{@link #getActivateBehaviors()
     * <em>Activate Behaviors</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getActivateBehaviors()
     * @generated
     * @ordered
     */
    protected EList<BehaviorTool> activateBehaviors;

    /**
     * The cached value of the '{@link #getFilterVariableHistory()
     * <em>Filter Variable History</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterVariableHistory()
     * @generated
     * @ordered
     */
    protected FilterVariableHistory filterVariableHistory;

    /**
     * The cached value of the '{@link #getActivatedLayers()
     * <em>Activated Layers</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getActivatedLayers()
     * @generated
     * @ordered
     */
    protected EList<Layer> activatedLayers;

    /**
     * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSynchronized()
     * @generated
     * @ordered
     */
    protected static final boolean SYNCHRONIZED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSynchronized()
     * @generated
     * @ordered
     */
    protected boolean synchronized_ = DDiagramImpl.SYNCHRONIZED_EDEFAULT;

    /**
     * The cached value of the '{@link #getHiddenElements()
     * <em>Hidden Elements</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHiddenElements()
     * @generated
     * @ordered
     */
    protected EList<DDiagramElement> hiddenElements;

    /**
     * The default value of the '{@link #isIsInLayoutingMode()
     * <em>Is In Layouting Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isIsInLayoutingMode()
     * @generated
     * @ordered
     */
    protected static final boolean IS_IN_LAYOUTING_MODE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsInLayoutingMode()
     * <em>Is In Layouting Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isIsInLayoutingMode()
     * @generated
     * @ordered
     */
    protected boolean isInLayoutingMode = DDiagramImpl.IS_IN_LAYOUTING_MODE_EDEFAULT;

    /**
     * The default value of the '{@link #getHeaderHeight()
     * <em>Header Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHeaderHeight()
     * @generated
     * @ordered
     */
    protected static final int HEADER_HEIGHT_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getHeaderHeight() <em>Header Height</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHeaderHeight()
     * @generated
     * @ordered
     */
    protected int headerHeight = DDiagramImpl.HEADER_HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DDIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElement> getOwnedDiagramElements() {
        if (ownedDiagramElements == null) {
            ownedDiagramElements = new EObjectContainmentEList.Resolving<DDiagramElement>(DDiagramElement.class, this, DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS);
        }
        return ownedDiagramElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElement> getDiagramElements() {
        // TODO: implement this method to return the 'Diagram Elements'
        // reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (DiagramDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM__DESCRIPTION, oldDescription, description));
                }
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
    @Override
    public void setDescription(DiagramDescription newDescription) {
        DiagramDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getEdges() {
        // TODO: implement this method to return the 'Edges' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNode> getNodes() {
        // TODO: implement this method to return the 'Nodes' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNodeListElement> getNodeListElements() {
        // TODO: implement this method to return the 'Node List Elements'
        // reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElementContainer> getContainers() {
        // TODO: implement this method to return the 'Containers' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ConcernDescription getCurrentConcern() {
        if (currentConcern != null && currentConcern.eIsProxy()) {
            InternalEObject oldCurrentConcern = (InternalEObject) currentConcern;
            currentConcern = (ConcernDescription) eResolveProxy(oldCurrentConcern);
            if (currentConcern != oldCurrentConcern) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM__CURRENT_CONCERN, oldCurrentConcern, currentConcern));
                }
            }
        }
        return currentConcern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConcernDescription basicGetCurrentConcern() {
        return currentConcern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCurrentConcern(ConcernDescription newCurrentConcern) {
        ConcernDescription oldCurrentConcern = currentConcern;
        currentConcern = newCurrentConcern;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__CURRENT_CONCERN, oldCurrentConcern, currentConcern));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FilterDescription> getActivatedFilters() {
        if (activatedFilters == null) {
            activatedFilters = new EObjectResolvingEList<FilterDescription>(FilterDescription.class, this, DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS);
        }
        return activatedFilters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FilterDescription> getAllFilters() {
        // TODO: implement this method to return the 'All Filters' reference
        // list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ValidationRule> getActivatedRules() {
        if (activatedRules == null) {
            activatedRules = new EObjectResolvingEList<ValidationRule>(ValidationRule.class, this, DiagramPackage.DDIAGRAM__ACTIVATED_RULES);
        }
        return activatedRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<BehaviorTool> getActivateBehaviors() {
        if (activateBehaviors == null) {
            activateBehaviors = new EObjectResolvingEList<BehaviorTool>(BehaviorTool.class, this, DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS);
        }
        return activateBehaviors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FilterVariableHistory getFilterVariableHistory() {
        if (filterVariableHistory != null && filterVariableHistory.eIsProxy()) {
            InternalEObject oldFilterVariableHistory = (InternalEObject) filterVariableHistory;
            filterVariableHistory = (FilterVariableHistory) eResolveProxy(oldFilterVariableHistory);
            if (filterVariableHistory != oldFilterVariableHistory) {
                InternalEObject newFilterVariableHistory = (InternalEObject) filterVariableHistory;
                NotificationChain msgs = oldFilterVariableHistory.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, null);
                if (newFilterVariableHistory.eInternalContainer() == null) {
                    msgs = newFilterVariableHistory.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, oldFilterVariableHistory, filterVariableHistory));
                }
            }
        }
        return filterVariableHistory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public FilterVariableHistory basicGetFilterVariableHistory() {
        return filterVariableHistory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFilterVariableHistory(FilterVariableHistory newFilterVariableHistory, NotificationChain msgs) {
        FilterVariableHistory oldFilterVariableHistory = filterVariableHistory;
        filterVariableHistory = newFilterVariableHistory;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, oldFilterVariableHistory, newFilterVariableHistory);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterVariableHistory(FilterVariableHistory newFilterVariableHistory) {
        if (newFilterVariableHistory != filterVariableHistory) {
            NotificationChain msgs = null;
            if (filterVariableHistory != null) {
                msgs = ((InternalEObject) filterVariableHistory).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
            }
            if (newFilterVariableHistory != null) {
                msgs = ((InternalEObject) newFilterVariableHistory).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
            }
            msgs = basicSetFilterVariableHistory(newFilterVariableHistory, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, newFilterVariableHistory, newFilterVariableHistory));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Layer> getActivatedLayers() {
        if (activatedLayers == null) {
            activatedLayers = new EObjectResolvingEList<Layer>(Layer.class, this, DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS);
        }
        return activatedLayers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSynchronized() {
        return synchronized_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSynchronized(boolean newSynchronized) {
        boolean oldSynchronized = synchronized_;
        synchronized_ = newSynchronized;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__SYNCHRONIZED, oldSynchronized, synchronized_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElement> getHiddenElements() {
        if (hiddenElements == null) {
            hiddenElements = new EObjectResolvingEList<DDiagramElement>(DDiagramElement.class, this, DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS);
        }
        return hiddenElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isIsInLayoutingMode() {
        return isInLayoutingMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsInLayoutingMode(boolean newIsInLayoutingMode) {
        boolean oldIsInLayoutingMode = isInLayoutingMode;
        isInLayoutingMode = newIsInLayoutingMode;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE, oldIsInLayoutingMode, isInLayoutingMode));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getHeaderHeight() {
        return headerHeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeaderHeight(int newHeaderHeight) {
        int oldHeaderHeight = headerHeight;
        headerHeight = newHeaderHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM__HEADER_HEIGHT, oldHeaderHeight, headerHeight));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNode> getNodesFromMapping(NodeMapping mapping) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getEdgesFromMapping(EdgeMapping mapping) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElementContainer> getContainersFromMapping(ContainerMapping mapping) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return ((InternalEList<?>) getOwnedDiagramElements()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            return basicSetFilterVariableHistory(null, msgs);
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
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return getOwnedDiagramElements();
        case DiagramPackage.DDIAGRAM__DIAGRAM_ELEMENTS:
            return getDiagramElements();
        case DiagramPackage.DDIAGRAM__DESCRIPTION:
            if (resolve) {
                return getDescription();
            }
            return basicGetDescription();
        case DiagramPackage.DDIAGRAM__EDGES:
            return getEdges();
        case DiagramPackage.DDIAGRAM__NODES:
            return getNodes();
        case DiagramPackage.DDIAGRAM__NODE_LIST_ELEMENTS:
            return getNodeListElements();
        case DiagramPackage.DDIAGRAM__CONTAINERS:
            return getContainers();
        case DiagramPackage.DDIAGRAM__CURRENT_CONCERN:
            if (resolve) {
                return getCurrentConcern();
            }
            return basicGetCurrentConcern();
        case DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS:
            return getActivatedFilters();
        case DiagramPackage.DDIAGRAM__ALL_FILTERS:
            return getAllFilters();
        case DiagramPackage.DDIAGRAM__ACTIVATED_RULES:
            return getActivatedRules();
        case DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            return getActivateBehaviors();
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            if (resolve) {
                return getFilterVariableHistory();
            }
            return basicGetFilterVariableHistory();
        case DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS:
            return getActivatedLayers();
        case DiagramPackage.DDIAGRAM__SYNCHRONIZED:
            return isSynchronized();
        case DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            return getHiddenElements();
        case DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            return isIsInLayoutingMode();
        case DiagramPackage.DDIAGRAM__HEADER_HEIGHT:
            return getHeaderHeight();
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
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            getOwnedDiagramElements().addAll((Collection<? extends DDiagramElement>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__DESCRIPTION:
            setDescription((DiagramDescription) newValue);
            return;
        case DiagramPackage.DDIAGRAM__CURRENT_CONCERN:
            setCurrentConcern((ConcernDescription) newValue);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS:
            getActivatedFilters().clear();
            getActivatedFilters().addAll((Collection<? extends FilterDescription>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_RULES:
            getActivatedRules().clear();
            getActivatedRules().addAll((Collection<? extends ValidationRule>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            getActivateBehaviors().clear();
            getActivateBehaviors().addAll((Collection<? extends BehaviorTool>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            setFilterVariableHistory((FilterVariableHistory) newValue);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS:
            getActivatedLayers().clear();
            getActivatedLayers().addAll((Collection<? extends Layer>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__SYNCHRONIZED:
            setSynchronized((Boolean) newValue);
            return;
        case DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            getHiddenElements().clear();
            getHiddenElements().addAll((Collection<? extends DDiagramElement>) newValue);
            return;
        case DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            setIsInLayoutingMode((Boolean) newValue);
            return;
        case DiagramPackage.DDIAGRAM__HEADER_HEIGHT:
            setHeaderHeight((Integer) newValue);
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
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            return;
        case DiagramPackage.DDIAGRAM__DESCRIPTION:
            setDescription((DiagramDescription) null);
            return;
        case DiagramPackage.DDIAGRAM__CURRENT_CONCERN:
            setCurrentConcern((ConcernDescription) null);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS:
            getActivatedFilters().clear();
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_RULES:
            getActivatedRules().clear();
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            getActivateBehaviors().clear();
            return;
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            setFilterVariableHistory((FilterVariableHistory) null);
            return;
        case DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS:
            getActivatedLayers().clear();
            return;
        case DiagramPackage.DDIAGRAM__SYNCHRONIZED:
            setSynchronized(DDiagramImpl.SYNCHRONIZED_EDEFAULT);
            return;
        case DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            getHiddenElements().clear();
            return;
        case DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            setIsInLayoutingMode(DDiagramImpl.IS_IN_LAYOUTING_MODE_EDEFAULT);
            return;
        case DiagramPackage.DDIAGRAM__HEADER_HEIGHT:
            setHeaderHeight(DDiagramImpl.HEADER_HEIGHT_EDEFAULT);
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
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return ownedDiagramElements != null && !ownedDiagramElements.isEmpty();
        case DiagramPackage.DDIAGRAM__DIAGRAM_ELEMENTS:
            return !getDiagramElements().isEmpty();
        case DiagramPackage.DDIAGRAM__DESCRIPTION:
            return description != null;
        case DiagramPackage.DDIAGRAM__EDGES:
            return !getEdges().isEmpty();
        case DiagramPackage.DDIAGRAM__NODES:
            return !getNodes().isEmpty();
        case DiagramPackage.DDIAGRAM__NODE_LIST_ELEMENTS:
            return !getNodeListElements().isEmpty();
        case DiagramPackage.DDIAGRAM__CONTAINERS:
            return !getContainers().isEmpty();
        case DiagramPackage.DDIAGRAM__CURRENT_CONCERN:
            return currentConcern != null;
        case DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS:
            return activatedFilters != null && !activatedFilters.isEmpty();
        case DiagramPackage.DDIAGRAM__ALL_FILTERS:
            return !getAllFilters().isEmpty();
        case DiagramPackage.DDIAGRAM__ACTIVATED_RULES:
            return activatedRules != null && !activatedRules.isEmpty();
        case DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            return activateBehaviors != null && !activateBehaviors.isEmpty();
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            return filterVariableHistory != null;
        case DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS:
            return activatedLayers != null && !activatedLayers.isEmpty();
        case DiagramPackage.DDIAGRAM__SYNCHRONIZED:
            return synchronized_ != DDiagramImpl.SYNCHRONIZED_EDEFAULT;
        case DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            return hiddenElements != null && !hiddenElements.isEmpty();
        case DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            return isInLayoutingMode != DDiagramImpl.IS_IN_LAYOUTING_MODE_EDEFAULT;
        case DiagramPackage.DDIAGRAM__HEADER_HEIGHT:
            return headerHeight != DDiagramImpl.HEADER_HEIGHT_EDEFAULT;
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
        result.append(" (synchronized: "); //$NON-NLS-1$
        result.append(synchronized_);
        result.append(", isInLayoutingMode: "); //$NON-NLS-1$
        result.append(isInLayoutingMode);
        result.append(", headerHeight: "); //$NON-NLS-1$
        result.append(headerHeight);
        result.append(')');
        return result.toString();
    }

} // DDiagramImpl
