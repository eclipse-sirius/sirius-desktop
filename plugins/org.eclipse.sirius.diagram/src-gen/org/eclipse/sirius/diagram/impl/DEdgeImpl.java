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
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DEdge</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getOutgoingEdges
 * <em>Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getIncomingEdges
 * <em>Incoming Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getSize <em>Size</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getSourceNode
 * <em>Source Node</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getTargetNode
 * <em>Target Node</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#isIsFold
 * <em>Is Fold</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#isIsMockEdge
 * <em>Is Mock Edge</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getPath <em>Path</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getArrangeConstraints
 * <em>Arrange Constraints</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getBeginLabel
 * <em>Begin Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DEdgeImpl#getEndLabel
 * <em>End Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DEdgeImpl extends DDiagramElementImpl implements DEdge {
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
    protected EdgeStyle ownedStyle;

    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final Integer SIZE_EDEFAULT = new Integer(1);

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSize()
     * @generated
     * @ordered
     */
    @Deprecated
    protected Integer size = DEdgeImpl.SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getSourceNode() <em>Source Node</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSourceNode()
     * @generated
     * @ordered
     */
    protected EdgeTarget sourceNode;

    /**
     * The cached value of the '{@link #getTargetNode() <em>Target Node</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetNode()
     * @generated
     * @ordered
     */
    protected EdgeTarget targetNode;

    /**
     * The cached value of the '{@link #getActualMapping()
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getActualMapping()
     * @generated
     * @ordered
     */
    protected IEdgeMapping actualMapping;

    /**
     * The default value of the '{@link #getRoutingStyle()
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected static final EdgeRouting ROUTING_STYLE_EDEFAULT = EdgeRouting.STRAIGHT_LITERAL;

    /**
     * The cached value of the '{@link #getRoutingStyle() <em>Routing Style</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    @Deprecated
    protected EdgeRouting routingStyle = DEdgeImpl.ROUTING_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #isIsFold() <em>Is Fold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsFold()
     * @generated
     * @ordered
     */
    protected static final boolean IS_FOLD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsFold() <em>Is Fold</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsFold()
     * @generated
     * @ordered
     */
    protected boolean isFold = DEdgeImpl.IS_FOLD_EDEFAULT;

    /**
     * The default value of the '{@link #isIsMockEdge() <em>Is Mock Edge</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsMockEdge()
     * @generated
     * @ordered
     */
    protected static final boolean IS_MOCK_EDGE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsMockEdge() <em>Is Mock Edge</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsMockEdge()
     * @generated
     * @ordered
     */
    protected boolean isMockEdge = DEdgeImpl.IS_MOCK_EDGE_EDEFAULT;

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
     * The cached value of the '{@link #getPath() <em>Path</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected EList<EdgeTarget> path;

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
     * The default value of the '{@link #getBeginLabel() <em>Begin Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBeginLabel()
     * @generated
     * @ordered
     */
    protected static final String BEGIN_LABEL_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBeginLabel() <em>Begin Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBeginLabel()
     * @generated
     * @ordered
     */
    protected String beginLabel = DEdgeImpl.BEGIN_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getEndLabel() <em>End Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEndLabel()
     * @generated
     * @ordered
     */
    protected static final String END_LABEL_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getEndLabel() <em>End Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEndLabel()
     * @generated
     * @ordered
     */
    protected String endLabel = DEdgeImpl.END_LABEL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DEdgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DEDGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.DEDGE__OUTGOING_EDGES, DiagramPackage.DEDGE__SOURCE_NODE);
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
            incomingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.DEDGE__INCOMING_EDGES, DiagramPackage.DEDGE__TARGET_NODE);
        }
        return incomingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyle getOwnedStyle() {
        if (ownedStyle != null && ownedStyle.eIsProxy()) {
            InternalEObject oldOwnedStyle = (InternalEObject) ownedStyle;
            ownedStyle = (EdgeStyle) eResolveProxy(oldOwnedStyle);
            if (ownedStyle != oldOwnedStyle) {
                InternalEObject newOwnedStyle = (InternalEObject) ownedStyle;
                NotificationChain msgs = oldOwnedStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DEDGE__OWNED_STYLE, null, null);
                if (newOwnedStyle.eInternalContainer() == null) {
                    msgs = newOwnedStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DEDGE__OWNED_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DEDGE__OWNED_STYLE, oldOwnedStyle, ownedStyle));
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
    public EdgeStyle basicGetOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(EdgeStyle newOwnedStyle, NotificationChain msgs) {
        EdgeStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(EdgeStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DEDGE__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DEDGE__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSize(Integer newSize) {
        Integer oldSize = size;
        size = newSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__SIZE, oldSize, size));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeTarget getSourceNode() {
        if (sourceNode != null && sourceNode.eIsProxy()) {
            InternalEObject oldSourceNode = (InternalEObject) sourceNode;
            sourceNode = (EdgeTarget) eResolveProxy(oldSourceNode);
            if (sourceNode != oldSourceNode) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DEDGE__SOURCE_NODE, oldSourceNode, sourceNode));
                }
            }
        }
        return sourceNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EdgeTarget basicGetSourceNode() {
        return sourceNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSourceNode(EdgeTarget newSourceNode, NotificationChain msgs) {
        EdgeTarget oldSourceNode = sourceNode;
        sourceNode = newSourceNode;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__SOURCE_NODE, oldSourceNode, newSourceNode);
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
    public void setSourceNode(EdgeTarget newSourceNode) {
        if (newSourceNode != sourceNode) {
            NotificationChain msgs = null;
            if (sourceNode != null) {
                msgs = ((InternalEObject) sourceNode).eInverseRemove(this, DiagramPackage.EDGE_TARGET__OUTGOING_EDGES, EdgeTarget.class, msgs);
            }
            if (newSourceNode != null) {
                msgs = ((InternalEObject) newSourceNode).eInverseAdd(this, DiagramPackage.EDGE_TARGET__OUTGOING_EDGES, EdgeTarget.class, msgs);
            }
            msgs = basicSetSourceNode(newSourceNode, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__SOURCE_NODE, newSourceNode, newSourceNode));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeTarget getTargetNode() {
        if (targetNode != null && targetNode.eIsProxy()) {
            InternalEObject oldTargetNode = (InternalEObject) targetNode;
            targetNode = (EdgeTarget) eResolveProxy(oldTargetNode);
            if (targetNode != oldTargetNode) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DEDGE__TARGET_NODE, oldTargetNode, targetNode));
                }
            }
        }
        return targetNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EdgeTarget basicGetTargetNode() {
        return targetNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTargetNode(EdgeTarget newTargetNode, NotificationChain msgs) {
        EdgeTarget oldTargetNode = targetNode;
        targetNode = newTargetNode;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__TARGET_NODE, oldTargetNode, newTargetNode);
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
    public void setTargetNode(EdgeTarget newTargetNode) {
        if (newTargetNode != targetNode) {
            NotificationChain msgs = null;
            if (targetNode != null) {
                msgs = ((InternalEObject) targetNode).eInverseRemove(this, DiagramPackage.EDGE_TARGET__INCOMING_EDGES, EdgeTarget.class, msgs);
            }
            if (newTargetNode != null) {
                msgs = ((InternalEObject) newTargetNode).eInverseAdd(this, DiagramPackage.EDGE_TARGET__INCOMING_EDGES, EdgeTarget.class, msgs);
            }
            msgs = basicSetTargetNode(newTargetNode, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__TARGET_NODE, newTargetNode, newTargetNode));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public IEdgeMapping getActualMapping() {
        if (actualMapping != null && actualMapping.eIsProxy()) {
            InternalEObject oldActualMapping = (InternalEObject) actualMapping;
            actualMapping = (IEdgeMapping) eResolveProxy(oldActualMapping);
            if (actualMapping != oldActualMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DEDGE__ACTUAL_MAPPING, oldActualMapping, actualMapping));
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
    public IEdgeMapping basicGetActualMapping() {
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setActualMapping(IEdgeMapping newActualMapping) {
        IEdgeMapping oldActualMapping = actualMapping;
        actualMapping = newActualMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__ACTUAL_MAPPING, oldActualMapping, actualMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeRouting getRoutingStyle() {
        return routingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRoutingStyle(EdgeRouting newRoutingStyle) {
        EdgeRouting oldRoutingStyle = routingStyle;
        routingStyle = newRoutingStyle == null ? DEdgeImpl.ROUTING_STYLE_EDEFAULT : newRoutingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__ROUTING_STYLE, oldRoutingStyle, routingStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    @Deprecated
    public boolean isIsFold() {
        return isFold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    @Deprecated
    public void setIsFold(boolean newIsFold) {
        boolean oldIsFold = isFold;
        isFold = newIsFold;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__IS_FOLD, oldIsFold, isFold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    @Deprecated
    public boolean isIsMockEdge() {
        return isMockEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    @Deprecated
    public void setIsMockEdge(boolean newIsMockEdge) {
        boolean oldIsMockEdge = isMockEdge;
        isMockEdge = newIsMockEdge;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__IS_MOCK_EDGE, oldIsMockEdge, isMockEdge));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DEDGE__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeTarget> getPath() {
        if (path == null) {
            path = new EObjectResolvingEList<EdgeTarget>(EdgeTarget.class, this, DiagramPackage.DEDGE__PATH);
        }
        return path;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ArrangeConstraint> getArrangeConstraints() {
        if (arrangeConstraints == null) {
            arrangeConstraints = new EDataTypeUniqueEList<ArrangeConstraint>(ArrangeConstraint.class, this, DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS);
        }
        return arrangeConstraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getBeginLabel() {
        return beginLabel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBeginLabel(String newBeginLabel) {
        String oldBeginLabel = beginLabel;
        beginLabel = newBeginLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__BEGIN_LABEL, oldBeginLabel, beginLabel));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getEndLabel() {
        return endLabel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEndLabel(String newEndLabel) {
        String oldEndLabel = endLabel;
        endLabel = newEndLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DEDGE__END_LABEL, oldEndLabel, endLabel));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isRootFolding() {
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingEdges()).basicAdd(otherEnd, msgs);
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingEdges()).basicAdd(otherEnd, msgs);
        case DiagramPackage.DEDGE__SOURCE_NODE:
            if (sourceNode != null) {
                msgs = ((InternalEObject) sourceNode).eInverseRemove(this, DiagramPackage.EDGE_TARGET__OUTGOING_EDGES, EdgeTarget.class, msgs);
            }
            return basicSetSourceNode((EdgeTarget) otherEnd, msgs);
        case DiagramPackage.DEDGE__TARGET_NODE:
            if (targetNode != null) {
                msgs = ((InternalEObject) targetNode).eInverseRemove(this, DiagramPackage.EDGE_TARGET__INCOMING_EDGES, EdgeTarget.class, msgs);
            }
            return basicSetTargetNode((EdgeTarget) otherEnd, msgs);
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            return ((InternalEList<?>) getIncomingEdges()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DEDGE__OWNED_STYLE:
            return basicSetOwnedStyle(null, msgs);
        case DiagramPackage.DEDGE__SOURCE_NODE:
            return basicSetSourceNode(null, msgs);
        case DiagramPackage.DEDGE__TARGET_NODE:
            return basicSetTargetNode(null, msgs);
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            return getOutgoingEdges();
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            return getIncomingEdges();
        case DiagramPackage.DEDGE__OWNED_STYLE:
            if (resolve) {
                return getOwnedStyle();
            }
            return basicGetOwnedStyle();
        case DiagramPackage.DEDGE__SIZE:
            return getSize();
        case DiagramPackage.DEDGE__SOURCE_NODE:
            if (resolve) {
                return getSourceNode();
            }
            return basicGetSourceNode();
        case DiagramPackage.DEDGE__TARGET_NODE:
            if (resolve) {
                return getTargetNode();
            }
            return basicGetTargetNode();
        case DiagramPackage.DEDGE__ACTUAL_MAPPING:
            if (resolve) {
                return getActualMapping();
            }
            return basicGetActualMapping();
        case DiagramPackage.DEDGE__ROUTING_STYLE:
            return getRoutingStyle();
        case DiagramPackage.DEDGE__IS_FOLD:
            return isIsFold();
        case DiagramPackage.DEDGE__IS_MOCK_EDGE:
            return isIsMockEdge();
        case DiagramPackage.DEDGE__ORIGINAL_STYLE:
            if (resolve) {
                return getOriginalStyle();
            }
            return basicGetOriginalStyle();
        case DiagramPackage.DEDGE__PATH:
            return getPath();
        case DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS:
            return getArrangeConstraints();
        case DiagramPackage.DEDGE__BEGIN_LABEL:
            return getBeginLabel();
        case DiagramPackage.DEDGE__END_LABEL:
            return getEndLabel();
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            getIncomingEdges().clear();
            getIncomingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case DiagramPackage.DEDGE__OWNED_STYLE:
            setOwnedStyle((EdgeStyle) newValue);
            return;
        case DiagramPackage.DEDGE__SIZE:
            setSize((Integer) newValue);
            return;
        case DiagramPackage.DEDGE__SOURCE_NODE:
            setSourceNode((EdgeTarget) newValue);
            return;
        case DiagramPackage.DEDGE__TARGET_NODE:
            setTargetNode((EdgeTarget) newValue);
            return;
        case DiagramPackage.DEDGE__ACTUAL_MAPPING:
            setActualMapping((IEdgeMapping) newValue);
            return;
        case DiagramPackage.DEDGE__ROUTING_STYLE:
            setRoutingStyle((EdgeRouting) newValue);
            return;
        case DiagramPackage.DEDGE__IS_FOLD:
            setIsFold((Boolean) newValue);
            return;
        case DiagramPackage.DEDGE__IS_MOCK_EDGE:
            setIsMockEdge((Boolean) newValue);
            return;
        case DiagramPackage.DEDGE__ORIGINAL_STYLE:
            setOriginalStyle((Style) newValue);
            return;
        case DiagramPackage.DEDGE__PATH:
            getPath().clear();
            getPath().addAll((Collection<? extends EdgeTarget>) newValue);
            return;
        case DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            getArrangeConstraints().addAll((Collection<? extends ArrangeConstraint>) newValue);
            return;
        case DiagramPackage.DEDGE__BEGIN_LABEL:
            setBeginLabel((String) newValue);
            return;
        case DiagramPackage.DEDGE__END_LABEL:
            setEndLabel((String) newValue);
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            getIncomingEdges().clear();
            return;
        case DiagramPackage.DEDGE__OWNED_STYLE:
            setOwnedStyle((EdgeStyle) null);
            return;
        case DiagramPackage.DEDGE__SIZE:
            setSize(DEdgeImpl.SIZE_EDEFAULT);
            return;
        case DiagramPackage.DEDGE__SOURCE_NODE:
            setSourceNode((EdgeTarget) null);
            return;
        case DiagramPackage.DEDGE__TARGET_NODE:
            setTargetNode((EdgeTarget) null);
            return;
        case DiagramPackage.DEDGE__ACTUAL_MAPPING:
            setActualMapping((IEdgeMapping) null);
            return;
        case DiagramPackage.DEDGE__ROUTING_STYLE:
            setRoutingStyle(DEdgeImpl.ROUTING_STYLE_EDEFAULT);
            return;
        case DiagramPackage.DEDGE__IS_FOLD:
            setIsFold(DEdgeImpl.IS_FOLD_EDEFAULT);
            return;
        case DiagramPackage.DEDGE__IS_MOCK_EDGE:
            setIsMockEdge(DEdgeImpl.IS_MOCK_EDGE_EDEFAULT);
            return;
        case DiagramPackage.DEDGE__ORIGINAL_STYLE:
            setOriginalStyle((Style) null);
            return;
        case DiagramPackage.DEDGE__PATH:
            getPath().clear();
            return;
        case DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            return;
        case DiagramPackage.DEDGE__BEGIN_LABEL:
            setBeginLabel(DEdgeImpl.BEGIN_LABEL_EDEFAULT);
            return;
        case DiagramPackage.DEDGE__END_LABEL:
            setEndLabel(DEdgeImpl.END_LABEL_EDEFAULT);
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
        case DiagramPackage.DEDGE__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case DiagramPackage.DEDGE__INCOMING_EDGES:
            return incomingEdges != null && !incomingEdges.isEmpty();
        case DiagramPackage.DEDGE__OWNED_STYLE:
            return ownedStyle != null;
        case DiagramPackage.DEDGE__SIZE:
            return DEdgeImpl.SIZE_EDEFAULT == null ? size != null : !DEdgeImpl.SIZE_EDEFAULT.equals(size);
        case DiagramPackage.DEDGE__SOURCE_NODE:
            return sourceNode != null;
        case DiagramPackage.DEDGE__TARGET_NODE:
            return targetNode != null;
        case DiagramPackage.DEDGE__ACTUAL_MAPPING:
            return actualMapping != null;
        case DiagramPackage.DEDGE__ROUTING_STYLE:
            return routingStyle != DEdgeImpl.ROUTING_STYLE_EDEFAULT;
        case DiagramPackage.DEDGE__IS_FOLD:
            return isFold != DEdgeImpl.IS_FOLD_EDEFAULT;
        case DiagramPackage.DEDGE__IS_MOCK_EDGE:
            return isMockEdge != DEdgeImpl.IS_MOCK_EDGE_EDEFAULT;
        case DiagramPackage.DEDGE__ORIGINAL_STYLE:
            return originalStyle != null;
        case DiagramPackage.DEDGE__PATH:
            return path != null && !path.isEmpty();
        case DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS:
            return arrangeConstraints != null && !arrangeConstraints.isEmpty();
        case DiagramPackage.DEDGE__BEGIN_LABEL:
            return DEdgeImpl.BEGIN_LABEL_EDEFAULT == null ? beginLabel != null : !DEdgeImpl.BEGIN_LABEL_EDEFAULT.equals(beginLabel);
        case DiagramPackage.DEDGE__END_LABEL:
            return DEdgeImpl.END_LABEL_EDEFAULT == null ? endLabel != null : !DEdgeImpl.END_LABEL_EDEFAULT.equals(endLabel);
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
            case DiagramPackage.DEDGE__OUTGOING_EDGES:
                return DiagramPackage.EDGE_TARGET__OUTGOING_EDGES;
            case DiagramPackage.DEDGE__INCOMING_EDGES:
                return DiagramPackage.EDGE_TARGET__INCOMING_EDGES;
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
                return DiagramPackage.DEDGE__OUTGOING_EDGES;
            case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
                return DiagramPackage.DEDGE__INCOMING_EDGES;
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
        result.append(" (size: "); //$NON-NLS-1$
        result.append(size);
        result.append(", routingStyle: "); //$NON-NLS-1$
        result.append(routingStyle);
        result.append(", isFold: "); //$NON-NLS-1$
        result.append(isFold);
        result.append(", isMockEdge: "); //$NON-NLS-1$
        result.append(isMockEdge);
        result.append(", arrangeConstraints: "); //$NON-NLS-1$
        result.append(arrangeConstraints);
        result.append(", beginLabel: "); //$NON-NLS-1$
        result.append(beginLabel);
        result.append(", endLabel: "); //$NON-NLS-1$
        result.append(endLabel);
        result.append(')');
        return result.toString();
    }

} // DEdgeImpl
