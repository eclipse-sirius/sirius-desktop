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
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DDiagram Element Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getOwnedBorderedNodes
 * <em>Owned Bordered Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getArrangeConstraints
 * <em>Arrange Constraints</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getOutgoingEdges
 * <em>Outgoing Edges</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getIncomingEdges
 * <em>Incoming Edges</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getNodes
 * <em>Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getContainers
 * <em>Containers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getElements
 * <em>Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl#getHeight
 * <em>Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DDiagramElementContainerImpl extends DDiagramElementImpl implements DDiagramElementContainer {
    /**
     * The cached value of the '{@link #getOwnedBorderedNodes()
     * <em>Owned Bordered Nodes</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedBorderedNodes()
     * @generated
     * @ordered
     */
    protected EList<DNode> ownedBorderedNodes;

    /**
     * The cached value of the '{@link #getArrangeConstraints()
     * <em>Arrange Constraints</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getArrangeConstraints()
     * @generated
     * @ordered
     */
    protected EList<ArrangeConstraint> arrangeConstraints;

    /**
     * The cached value of the '{@link #getOutgoingEdges()
     * <em>Outgoing Edges</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOutgoingEdges()
     * @generated
     * @ordered
     */
    protected EList<DEdge> outgoingEdges;

    /**
     * The cached value of the '{@link #getIncomingEdges()
     * <em>Incoming Edges</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIncomingEdges()
     * @generated
     * @ordered
     */
    protected EList<DEdge> incomingEdges;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected ContainerStyle ownedStyle;

    /**
     * The cached value of the '{@link #getOriginalStyle()
     * <em>Original Style</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOriginalStyle()
     * @generated
     * @ordered
     */
    protected Style originalStyle;

    /**
     * The cached value of the '{@link #getActualMapping()
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getActualMapping()
     * @generated
     * @ordered
     */
    protected ContainerMapping actualMapping;

    /**
     * The cached value of the '{@link #getCandidatesMapping()
     * <em>Candidates Mapping</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getCandidatesMapping()
     * @generated
     * @ordered
     */
    protected EList<ContainerMapping> candidatesMapping;

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final Integer WIDTH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected Integer width = DDiagramElementContainerImpl.WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final Integer HEIGHT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected Integer height = DDiagramElementContainerImpl.HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DDiagramElementContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNode> getOwnedBorderedNodes() {
        if (ownedBorderedNodes == null) {
            ownedBorderedNodes = new EObjectContainmentEList.Resolving<DNode>(DNode.class, this, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES);
        }
        return ownedBorderedNodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ArrangeConstraint> getArrangeConstraints() {
        if (arrangeConstraints == null) {
            arrangeConstraints = new EDataTypeUniqueEList<ArrangeConstraint>(ArrangeConstraint.class, this, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS);
        }
        return arrangeConstraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES, DiagramPackage.DEDGE__SOURCE_NODE);
        }
        return outgoingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getIncomingEdges() {
        if (incomingEdges == null) {
            incomingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES, DiagramPackage.DEDGE__TARGET_NODE);
        }
        return incomingEdges;
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
    public EList<DDiagramElement> getElements() {
        // TODO: implement this method to return the 'Elements' reference list
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
    public ContainerStyle getOwnedStyle() {
        if (ownedStyle != null && ownedStyle.eIsProxy()) {
            InternalEObject oldOwnedStyle = (InternalEObject) ownedStyle;
            ownedStyle = (ContainerStyle) eResolveProxy(oldOwnedStyle);
            if (ownedStyle != oldOwnedStyle) {
                InternalEObject newOwnedStyle = (InternalEObject) ownedStyle;
                NotificationChain msgs = oldOwnedStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, null, null);
                if (newOwnedStyle.eInternalContainer() == null) {
                    msgs = newOwnedStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, oldOwnedStyle, ownedStyle));
                }
            }
        }
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerStyle basicGetOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(ContainerStyle newOwnedStyle, NotificationChain msgs) {
        ContainerStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(ContainerStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Style getOriginalStyle() {
        if (originalStyle != null && originalStyle.eIsProxy()) {
            InternalEObject oldOriginalStyle = (InternalEObject) originalStyle;
            originalStyle = (Style) eResolveProxy(oldOriginalStyle);
            if (originalStyle != oldOriginalStyle) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
                }
            }
        }
        return originalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Style basicGetOriginalStyle() {
        return originalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOriginalStyle(Style newOriginalStyle) {
        Style oldOriginalStyle = originalStyle;
        originalStyle = newOriginalStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerMapping getActualMapping() {
        if (actualMapping != null && actualMapping.eIsProxy()) {
            InternalEObject oldActualMapping = (InternalEObject) actualMapping;
            actualMapping = (ContainerMapping) eResolveProxy(oldActualMapping);
            if (actualMapping != oldActualMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING, oldActualMapping, actualMapping));
                }
            }
        }
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerMapping basicGetActualMapping() {
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setActualMapping(ContainerMapping newActualMapping) {
        ContainerMapping oldActualMapping = actualMapping;
        actualMapping = newActualMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING, oldActualMapping, actualMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerMapping> getCandidatesMapping() {
        if (candidatesMapping == null) {
            candidatesMapping = new EObjectResolvingEList<ContainerMapping>(ContainerMapping.class, this, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING);
        }
        return candidatesMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWidth(Integer newWidth) {
        Integer oldWidth = width;
        width = newWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH, oldWidth, width));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeight(Integer newHeight) {
        Integer oldHeight = height;
        height = newHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT, oldHeight, height));
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
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingEdges()).basicAdd(otherEnd, msgs);
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingEdges()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
            return ((InternalEList<?>) getOwnedBorderedNodes()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            return ((InternalEList<?>) getIncomingEdges()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
            return basicSetOwnedStyle(null, msgs);
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
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
            return getOwnedBorderedNodes();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS:
            return getArrangeConstraints();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            return getOutgoingEdges();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            return getIncomingEdges();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NODES:
            return getNodes();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS:
            return getContainers();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS:
            return getElements();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
            if (resolve) {
                return getOwnedStyle();
            }
            return basicGetOwnedStyle();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE:
            if (resolve) {
                return getOriginalStyle();
            }
            return basicGetOriginalStyle();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING:
            if (resolve) {
                return getActualMapping();
            }
            return basicGetActualMapping();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING:
            return getCandidatesMapping();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH:
            return getWidth();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT:
            return getHeight();
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
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            getOwnedBorderedNodes().addAll((Collection<? extends DNode>) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            getArrangeConstraints().addAll((Collection<? extends ArrangeConstraint>) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            getIncomingEdges().clear();
            getIncomingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
            setOwnedStyle((ContainerStyle) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE:
            setOriginalStyle((Style) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING:
            setActualMapping((ContainerMapping) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING:
            getCandidatesMapping().clear();
            getCandidatesMapping().addAll((Collection<? extends ContainerMapping>) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH:
            setWidth((Integer) newValue);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT:
            setHeight((Integer) newValue);
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
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            getIncomingEdges().clear();
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
            setOwnedStyle((ContainerStyle) null);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE:
            setOriginalStyle((Style) null);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING:
            setActualMapping((ContainerMapping) null);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING:
            getCandidatesMapping().clear();
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH:
            setWidth(DDiagramElementContainerImpl.WIDTH_EDEFAULT);
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT:
            setHeight(DDiagramElementContainerImpl.HEIGHT_EDEFAULT);
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
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
            return ownedBorderedNodes != null && !ownedBorderedNodes.isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS:
            return arrangeConstraints != null && !arrangeConstraints.isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
            return incomingEdges != null && !incomingEdges.isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NODES:
            return !getNodes().isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS:
            return !getContainers().isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS:
            return !getElements().isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
            return ownedStyle != null;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE:
            return originalStyle != null;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING:
            return actualMapping != null;
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING:
            return candidatesMapping != null && !candidatesMapping.isEmpty();
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH:
            return DDiagramElementContainerImpl.WIDTH_EDEFAULT == null ? width != null : !DDiagramElementContainerImpl.WIDTH_EDEFAULT.equals(width);
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT:
            return DDiagramElementContainerImpl.HEIGHT_EDEFAULT == null ? height != null : !DDiagramElementContainerImpl.HEIGHT_EDEFAULT.equals(height);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == EdgeTarget.class) {
            switch (derivedFeatureID) {
            case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES:
                return DiagramPackage.EDGE_TARGET__OUTGOING_EDGES;
            case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES:
                return DiagramPackage.EDGE_TARGET__INCOMING_EDGES;
            default:
                return -1;
            }
        }
        if (baseClass == DragAndDropTarget.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == EdgeTarget.class) {
            switch (baseFeatureID) {
            case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
                return DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;
            case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
                return DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;
            default:
                return -1;
            }
        }
        if (baseClass == DragAndDropTarget.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (arrangeConstraints: "); //$NON-NLS-1$
        result.append(arrangeConstraints);
        result.append(", width: "); //$NON-NLS-1$
        result.append(width);
        result.append(", height: "); //$NON-NLS-1$
        result.append(height);
        result.append(')');
        return result.toString();
    }

} // DDiagramElementContainerImpl
