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
package org.eclipse.sirius.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.FilterVariableHistory;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.description.DragAndDropTargetDescription;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.Layer;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.description.concern.ConcernDescription;
import org.eclipse.sirius.description.filter.FilterDescription;
import org.eclipse.sirius.description.tool.BehaviorTool;
import org.eclipse.sirius.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>View Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getOwnedDiagramElements
 * <em>Owned Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getDiagramElements <em>
 * Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getInfo <em>Info</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getSubDiagrams <em>Sub
 * Diagrams</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getEdges <em>Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getNodeListElements <em>
 * Node List Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getContainers <em>
 * Containers</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getCurrentConcern <em>
 * Current Concern</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getActivatedFilters <em>
 * Activated Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getAllFilters <em>All
 * Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getActivatedRules <em>
 * Activated Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getActivateBehaviors <em>
 * Activate Behaviors</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getFilterVariableHistory
 * <em>Filter Variable History</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getActivatedLayers <em>
 * Activated Layers</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#isSynchronized <em>
 * Synchronized</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getHiddenElements <em>
 * Hidden Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#isIsInLayoutingMode <em>Is
 * In Layouting Mode</em>}</li>
 * <li>{@link org.eclipse.sirius.impl.DDiagramImpl#getHeaderHeight <em>Header
 * Height</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DDiagramImpl extends DRepresentationImpl implements DDiagram {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
     * The default value of the '{@link #getInfo() <em>Info</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getInfo()
     * @generated
     * @ordered
     */
    protected static final String INFO_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getInfo() <em>Info</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getInfo()
     * @generated
     * @ordered
     */
    protected String info = INFO_EDEFAULT;

    /**
     * The cached value of the '{@link #getSubDiagrams() <em>Sub Diagrams</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSubDiagrams()
     * @generated
     * @ordered
     */
    protected EList<DDiagram> subDiagrams;

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
    protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

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
    protected boolean isInLayoutingMode = IS_IN_LAYOUTING_MODE_EDEFAULT;

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
     * The cached value of the '{@link #getHeaderHeight()
     * <em>Header Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getHeaderHeight()
     * @generated
     * @ordered
     */
    protected int headerHeight = HEADER_HEIGHT_EDEFAULT;

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
        return SiriusPackage.Literals.DDIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagramElement> getOwnedDiagramElements() {
        if (ownedDiagramElements == null) {
            ownedDiagramElements = new EObjectContainmentEList.Resolving<DDiagramElement>(DDiagramElement.class, this, SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS);
        }
        return ownedDiagramElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
    public DiagramDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (DiagramDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.DDIAGRAM__DESCRIPTION, oldDescription, description));
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getInfo() {
        return info;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setInfo(String newInfo) {
        String oldInfo = info;
        info = newInfo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__INFO, oldInfo, info));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagram> getSubDiagrams() {
        if (subDiagrams == null) {
            subDiagrams = new EObjectContainmentEList.Resolving<DDiagram>(DDiagram.class, this, SiriusPackage.DDIAGRAM__SUB_DIAGRAMS);
        }
        return subDiagrams;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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

    // LGO : This doesn't seem to be generated, yet it is never used. commented
    // private void addViewNodes(DDiagramElementContainer elem, Collection
    // result) {
    // if (elem instanceof DNodeContainer) {
    // DNodeContainer container = (DNodeContainer) elem;
    // result.addAll(container.getOwnedBorderedViewNodes());
    // Iterator it = container.getOwnedSiriusElements().iterator();
    // while (it.hasNext()) {
    // DDiagramElement subElem = (DDiagramElement) it.next();
    // if (subElem instanceof DNode) {
    // result.add(subElem);
    // }
    // if (subElem instanceof DDiagramElementContainer) {
    // addViewNodes((DDiagramElementContainer) subElem, result);
    // }
    //
    // }
    // } else if (elem instanceof DNodeList) {
    // DNodeList container = (DNodeList) elem;
    // Iterator it = container.getOwnedElements().iterator();
    // while (it.hasNext()) {
    // DDiagramElement subElem = (DDiagramElement) it.next();
    // if (subElem instanceof DNode) {
    // result.add(subElem);
    // }
    // if (subElem instanceof DDiagramElementContainer) {
    // addViewNodes((DDiagramElementContainer) subElem, result);
    // }
    // }
    //
    // }
    //
    // }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
    public ConcernDescription getCurrentConcern() {
        if (currentConcern != null && currentConcern.eIsProxy()) {
            InternalEObject oldCurrentConcern = (InternalEObject) currentConcern;
            currentConcern = (ConcernDescription) eResolveProxy(oldCurrentConcern);
            if (currentConcern != oldCurrentConcern) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.DDIAGRAM__CURRENT_CONCERN, oldCurrentConcern, currentConcern));
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
    public void setCurrentConcern(ConcernDescription newCurrentConcern) {
        ConcernDescription oldCurrentConcern = currentConcern;
        currentConcern = newCurrentConcern;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__CURRENT_CONCERN, oldCurrentConcern, currentConcern));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<FilterDescription> getActivatedFilters() {
        if (activatedFilters == null) {
            activatedFilters = new EObjectResolvingEList<FilterDescription>(FilterDescription.class, this, SiriusPackage.DDIAGRAM__ACTIVATED_FILTERS);
        }
        return activatedFilters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
    public EList<ValidationRule> getActivatedRules() {
        if (activatedRules == null) {
            activatedRules = new EObjectResolvingEList<ValidationRule>(ValidationRule.class, this, SiriusPackage.DDIAGRAM__ACTIVATED_RULES);
        }
        return activatedRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<BehaviorTool> getActivateBehaviors() {
        if (activateBehaviors == null) {
            activateBehaviors = new EObjectResolvingEList<BehaviorTool>(BehaviorTool.class, this, SiriusPackage.DDIAGRAM__ACTIVATE_BEHAVIORS);
        }
        return activateBehaviors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterVariableHistory getFilterVariableHistory() {
        if (filterVariableHistory != null && filterVariableHistory.eIsProxy()) {
            InternalEObject oldFilterVariableHistory = (InternalEObject) filterVariableHistory;
            filterVariableHistory = (FilterVariableHistory) eResolveProxy(oldFilterVariableHistory);
            if (filterVariableHistory != oldFilterVariableHistory) {
                InternalEObject newFilterVariableHistory = (InternalEObject) filterVariableHistory;
                NotificationChain msgs = oldFilterVariableHistory.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, null);
                if (newFilterVariableHistory.eInternalContainer() == null) {
                    msgs = newFilterVariableHistory.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, oldFilterVariableHistory, filterVariableHistory));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, oldFilterVariableHistory, newFilterVariableHistory);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFilterVariableHistory(FilterVariableHistory newFilterVariableHistory) {
        if (newFilterVariableHistory != filterVariableHistory) {
            NotificationChain msgs = null;
            if (filterVariableHistory != null)
                msgs = ((InternalEObject) filterVariableHistory).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
            if (newFilterVariableHistory != null)
                msgs = ((InternalEObject) newFilterVariableHistory).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, null, msgs);
            msgs = basicSetFilterVariableHistory(newFilterVariableHistory, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY, newFilterVariableHistory, newFilterVariableHistory));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Layer> getActivatedLayers() {
        if (activatedLayers == null) {
            activatedLayers = new EObjectResolvingEList<Layer>(Layer.class, this, SiriusPackage.DDIAGRAM__ACTIVATED_LAYERS);
        }
        return activatedLayers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSynchronized() {
        return synchronized_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSynchronized(boolean newSynchronized) {
        boolean oldSynchronized = synchronized_;
        synchronized_ = newSynchronized;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__SYNCHRONIZED, oldSynchronized, synchronized_));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagramElement> getHiddenElements() {
        if (hiddenElements == null) {
            hiddenElements = new EObjectResolvingEList<DDiagramElement>(DDiagramElement.class, this, SiriusPackage.DDIAGRAM__HIDDEN_ELEMENTS);
        }
        return hiddenElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isIsInLayoutingMode() {
        return isInLayoutingMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setIsInLayoutingMode(boolean newIsInLayoutingMode) {
        boolean oldIsInLayoutingMode = isInLayoutingMode;
        isInLayoutingMode = newIsInLayoutingMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE, oldIsInLayoutingMode, isInLayoutingMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getHeaderHeight() {
        return headerHeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setHeaderHeight(int newHeaderHeight) {
        int oldHeaderHeight = headerHeight;
        headerHeight = newHeaderHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.DDIAGRAM__HEADER_HEIGHT, oldHeaderHeight, headerHeight));
    }

    // LGO : This doesn't seem to be generated, yet it is never used. commented
    // private void addSiriusElementContainers(DNodeContainer container,
    // Collection result) {
    // Iterator it = container.getOwnedSiriusElements().iterator();
    // while (it.hasNext()) {
    // DDiagramElement elem = (DDiagramElement) it.next();
    // if (elem instanceof DDiagramElementContainer)
    // result.add(elem);
    // if (elem instanceof DNodeContainer) {
    // addSiriusElementContainers((DNodeContainer) elem, result);
    // }
    // }
    // }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void clean() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
    public EList<DDiagramElement> findDiagramElements(EObject semanticElement, EClass type) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean validate() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        case SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return ((InternalEList<?>) getOwnedDiagramElements()).basicRemove(otherEnd, msgs);
        case SiriusPackage.DDIAGRAM__SUB_DIAGRAMS:
            return ((InternalEList<?>) getSubDiagrams()).basicRemove(otherEnd, msgs);
        case SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
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
        case SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return getOwnedDiagramElements();
        case SiriusPackage.DDIAGRAM__DIAGRAM_ELEMENTS:
            return getDiagramElements();
        case SiriusPackage.DDIAGRAM__DESCRIPTION:
            if (resolve)
                return getDescription();
            return basicGetDescription();
        case SiriusPackage.DDIAGRAM__INFO:
            return getInfo();
        case SiriusPackage.DDIAGRAM__SUB_DIAGRAMS:
            return getSubDiagrams();
        case SiriusPackage.DDIAGRAM__EDGES:
            return getEdges();
        case SiriusPackage.DDIAGRAM__NODES:
            return getNodes();
        case SiriusPackage.DDIAGRAM__NODE_LIST_ELEMENTS:
            return getNodeListElements();
        case SiriusPackage.DDIAGRAM__CONTAINERS:
            return getContainers();
        case SiriusPackage.DDIAGRAM__CURRENT_CONCERN:
            if (resolve)
                return getCurrentConcern();
            return basicGetCurrentConcern();
        case SiriusPackage.DDIAGRAM__ACTIVATED_FILTERS:
            return getActivatedFilters();
        case SiriusPackage.DDIAGRAM__ALL_FILTERS:
            return getAllFilters();
        case SiriusPackage.DDIAGRAM__ACTIVATED_RULES:
            return getActivatedRules();
        case SiriusPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            return getActivateBehaviors();
        case SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            if (resolve)
                return getFilterVariableHistory();
            return basicGetFilterVariableHistory();
        case SiriusPackage.DDIAGRAM__ACTIVATED_LAYERS:
            return getActivatedLayers();
        case SiriusPackage.DDIAGRAM__SYNCHRONIZED:
            return isSynchronized();
        case SiriusPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            return getHiddenElements();
        case SiriusPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            return isIsInLayoutingMode();
        case SiriusPackage.DDIAGRAM__HEADER_HEIGHT:
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
        case SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            getOwnedDiagramElements().addAll((Collection<? extends DDiagramElement>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__DESCRIPTION:
            setDescription((DiagramDescription) newValue);
            return;
        case SiriusPackage.DDIAGRAM__INFO:
            setInfo((String) newValue);
            return;
        case SiriusPackage.DDIAGRAM__SUB_DIAGRAMS:
            getSubDiagrams().clear();
            getSubDiagrams().addAll((Collection<? extends DDiagram>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__CURRENT_CONCERN:
            setCurrentConcern((ConcernDescription) newValue);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_FILTERS:
            getActivatedFilters().clear();
            getActivatedFilters().addAll((Collection<? extends FilterDescription>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_RULES:
            getActivatedRules().clear();
            getActivatedRules().addAll((Collection<? extends ValidationRule>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            getActivateBehaviors().clear();
            getActivateBehaviors().addAll((Collection<? extends BehaviorTool>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            setFilterVariableHistory((FilterVariableHistory) newValue);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_LAYERS:
            getActivatedLayers().clear();
            getActivatedLayers().addAll((Collection<? extends Layer>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__SYNCHRONIZED:
            setSynchronized((Boolean) newValue);
            return;
        case SiriusPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            getHiddenElements().clear();
            getHiddenElements().addAll((Collection<? extends DDiagramElement>) newValue);
            return;
        case SiriusPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            setIsInLayoutingMode((Boolean) newValue);
            return;
        case SiriusPackage.DDIAGRAM__HEADER_HEIGHT:
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
        case SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            getOwnedDiagramElements().clear();
            return;
        case SiriusPackage.DDIAGRAM__DESCRIPTION:
            setDescription((DiagramDescription) null);
            return;
        case SiriusPackage.DDIAGRAM__INFO:
            setInfo(INFO_EDEFAULT);
            return;
        case SiriusPackage.DDIAGRAM__SUB_DIAGRAMS:
            getSubDiagrams().clear();
            return;
        case SiriusPackage.DDIAGRAM__CURRENT_CONCERN:
            setCurrentConcern((ConcernDescription) null);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_FILTERS:
            getActivatedFilters().clear();
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_RULES:
            getActivatedRules().clear();
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            getActivateBehaviors().clear();
            return;
        case SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            setFilterVariableHistory((FilterVariableHistory) null);
            return;
        case SiriusPackage.DDIAGRAM__ACTIVATED_LAYERS:
            getActivatedLayers().clear();
            return;
        case SiriusPackage.DDIAGRAM__SYNCHRONIZED:
            setSynchronized(SYNCHRONIZED_EDEFAULT);
            return;
        case SiriusPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            getHiddenElements().clear();
            return;
        case SiriusPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            setIsInLayoutingMode(IS_IN_LAYOUTING_MODE_EDEFAULT);
            return;
        case SiriusPackage.DDIAGRAM__HEADER_HEIGHT:
            setHeaderHeight(HEADER_HEIGHT_EDEFAULT);
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
        case SiriusPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
            return ownedDiagramElements != null && !ownedDiagramElements.isEmpty();
        case SiriusPackage.DDIAGRAM__DIAGRAM_ELEMENTS:
            return !getDiagramElements().isEmpty();
        case SiriusPackage.DDIAGRAM__DESCRIPTION:
            return description != null;
        case SiriusPackage.DDIAGRAM__INFO:
            return INFO_EDEFAULT == null ? info != null : !INFO_EDEFAULT.equals(info);
        case SiriusPackage.DDIAGRAM__SUB_DIAGRAMS:
            return subDiagrams != null && !subDiagrams.isEmpty();
        case SiriusPackage.DDIAGRAM__EDGES:
            return !getEdges().isEmpty();
        case SiriusPackage.DDIAGRAM__NODES:
            return !getNodes().isEmpty();
        case SiriusPackage.DDIAGRAM__NODE_LIST_ELEMENTS:
            return !getNodeListElements().isEmpty();
        case SiriusPackage.DDIAGRAM__CONTAINERS:
            return !getContainers().isEmpty();
        case SiriusPackage.DDIAGRAM__CURRENT_CONCERN:
            return currentConcern != null;
        case SiriusPackage.DDIAGRAM__ACTIVATED_FILTERS:
            return activatedFilters != null && !activatedFilters.isEmpty();
        case SiriusPackage.DDIAGRAM__ALL_FILTERS:
            return !getAllFilters().isEmpty();
        case SiriusPackage.DDIAGRAM__ACTIVATED_RULES:
            return activatedRules != null && !activatedRules.isEmpty();
        case SiriusPackage.DDIAGRAM__ACTIVATE_BEHAVIORS:
            return activateBehaviors != null && !activateBehaviors.isEmpty();
        case SiriusPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
            return filterVariableHistory != null;
        case SiriusPackage.DDIAGRAM__ACTIVATED_LAYERS:
            return activatedLayers != null && !activatedLayers.isEmpty();
        case SiriusPackage.DDIAGRAM__SYNCHRONIZED:
            return synchronized_ != SYNCHRONIZED_EDEFAULT;
        case SiriusPackage.DDIAGRAM__HIDDEN_ELEMENTS:
            return hiddenElements != null && !hiddenElements.isEmpty();
        case SiriusPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
            return isInLayoutingMode != IS_IN_LAYOUTING_MODE_EDEFAULT;
        case SiriusPackage.DDIAGRAM__HEADER_HEIGHT:
            return headerHeight != HEADER_HEIGHT_EDEFAULT;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (info: ");
        result.append(info);
        result.append(", synchronized: ");
        result.append(synchronized_);
        result.append(", isInLayoutingMode: ");
        result.append(isInLayoutingMode);
        result.append(", headerHeight: ");
        result.append(headerHeight);
        result.append(')');
        return result.toString();
    }

} // DDiagramImpl
