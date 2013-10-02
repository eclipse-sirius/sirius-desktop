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
import org.eclipse.sirius.viewpoint.ArrangeConstraint;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.LabelPosition;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.ResizeKind;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DragAndDropTargetDescription;
import org.eclipse.sirius.viewpoint.description.NodeMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>View Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getOwnedBorderedNodes
 * <em>Owned Bordered Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getArrangeConstraints
 * <em>Arrange Constraints</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getOutgoingEdges <em>
 * Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getIncomingEdges <em>
 * Incoming Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getWidth <em>Width
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getHeight <em>Height
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getOwnedStyle <em>
 * Owned Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getLabelPosition <em>
 * Label Position</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getOwnedDetails <em>
 * Owned Details</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getResizeKind <em>
 * Resize Kind</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getOriginalStyle <em>
 * Original Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getActualMapping <em>
 * Actual Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeImpl#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DNodeImpl extends DDiagramElementImpl implements DNode {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

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
    protected Integer width = WIDTH_EDEFAULT;

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
    protected Integer height = HEIGHT_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected NodeStyle ownedStyle;

    /**
     * The default value of the '{@link #getLabelPosition()
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    protected static final LabelPosition LABEL_POSITION_EDEFAULT = LabelPosition.BORDER_LITERAL;

    /**
     * The cached value of the '{@link #getLabelPosition()
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    @Deprecated
    protected LabelPosition labelPosition = LABEL_POSITION_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedDetails()
     * <em>Owned Details</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOwnedDetails()
     * @generated
     * @ordered
     */
    protected EList<DDiagram> ownedDetails;

    /**
     * The default value of the '{@link #getResizeKind() <em>Resize Kind</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResizeKind()
     * @generated
     * @ordered
     */
    protected static final ResizeKind RESIZE_KIND_EDEFAULT = ResizeKind.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getResizeKind() <em>Resize Kind</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResizeKind()
     * @generated
     * @ordered
     */
    protected ResizeKind resizeKind = RESIZE_KIND_EDEFAULT;

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
    protected NodeMapping actualMapping;

    /**
     * The cached value of the '{@link #getCandidatesMapping()
     * <em>Candidates Mapping</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCandidatesMapping()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> candidatesMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DNODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DNode> getOwnedBorderedNodes() {
        if (ownedBorderedNodes == null) {
            ownedBorderedNodes = new EObjectContainmentEList.Resolving<DNode>(DNode.class, this, ViewpointPackage.DNODE__OWNED_BORDERED_NODES);
        }
        return ownedBorderedNodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ArrangeConstraint> getArrangeConstraints() {
        if (arrangeConstraints == null) {
            arrangeConstraints = new EDataTypeUniqueEList<ArrangeConstraint>(ArrangeConstraint.class, this, ViewpointPackage.DNODE__ARRANGE_CONSTRAINTS);
        }
        return arrangeConstraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DEdge> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, ViewpointPackage.DNODE__OUTGOING_EDGES, ViewpointPackage.DEDGE__SOURCE_NODE);
        }
        return outgoingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DEdge> getIncomingEdges() {
        if (incomingEdges == null) {
            incomingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, ViewpointPackage.DNODE__INCOMING_EDGES, ViewpointPackage.DEDGE__TARGET_NODE);
        }
        return incomingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setWidth(Integer newWidth) {
        Integer oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setHeight(Integer newHeight) {
        Integer oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeStyle getOwnedStyle() {
        if (ownedStyle != null && ownedStyle.eIsProxy()) {
            InternalEObject oldOwnedStyle = (InternalEObject) ownedStyle;
            ownedStyle = (NodeStyle) eResolveProxy(oldOwnedStyle);
            if (ownedStyle != oldOwnedStyle) {
                InternalEObject newOwnedStyle = (InternalEObject) ownedStyle;
                NotificationChain msgs = oldOwnedStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DNODE__OWNED_STYLE, null, null);
                if (newOwnedStyle.eInternalContainer() == null) {
                    msgs = newOwnedStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DNODE__OWNED_STYLE, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DNODE__OWNED_STYLE, oldOwnedStyle, ownedStyle));
            }
        }
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeStyle basicGetOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(NodeStyle newOwnedStyle, NotificationChain msgs) {
        NodeStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(NodeStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null)
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DNODE__OWNED_STYLE, null, msgs);
            if (newOwnedStyle != null)
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.DNODE__OWNED_STYLE, null, msgs);
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeMapping getActualMapping() {
        if (actualMapping != null && actualMapping.eIsProxy()) {
            InternalEObject oldActualMapping = (InternalEObject) actualMapping;
            actualMapping = (NodeMapping) eResolveProxy(oldActualMapping);
            if (actualMapping != oldActualMapping) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DNODE__ACTUAL_MAPPING, oldActualMapping, actualMapping));
            }
        }
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeMapping basicGetActualMapping() {
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setActualMapping(NodeMapping newActualMapping) {
        NodeMapping oldActualMapping = actualMapping;
        actualMapping = newActualMapping;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__ACTUAL_MAPPING, oldActualMapping, actualMapping));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<NodeMapping> getCandidatesMapping() {
        if (candidatesMapping == null) {
            candidatesMapping = new EObjectResolvingEList<NodeMapping>(NodeMapping.class, this, ViewpointPackage.DNODE__CANDIDATES_MAPPING);
        }
        return candidatesMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelPosition getLabelPosition() {
        return labelPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelPosition(LabelPosition newLabelPosition) {
        LabelPosition oldLabelPosition = labelPosition;
        labelPosition = newLabelPosition == null ? LABEL_POSITION_EDEFAULT : newLabelPosition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__LABEL_POSITION, oldLabelPosition, labelPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DDiagram> getOwnedDetails() {
        if (ownedDetails == null) {
            ownedDetails = new EObjectContainmentEList.Resolving<DDiagram>(DDiagram.class, this, ViewpointPackage.DNODE__OWNED_DETAILS);
        }
        return ownedDetails;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ResizeKind getResizeKind() {
        return resizeKind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setResizeKind(ResizeKind newResizeKind) {
        ResizeKind oldResizeKind = resizeKind;
        resizeKind = newResizeKind == null ? RESIZE_KIND_EDEFAULT : newResizeKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__RESIZE_KIND, oldResizeKind, resizeKind));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Style getOriginalStyle() {
        if (originalStyle != null && originalStyle.eIsProxy()) {
            InternalEObject oldOriginalStyle = (InternalEObject) originalStyle;
            originalStyle = (Style) eResolveProxy(oldOriginalStyle);
            if (originalStyle != oldOriginalStyle) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DNODE__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
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
    public void setOriginalStyle(Style newOriginalStyle) {
        Style oldOriginalStyle = originalStyle;
        originalStyle = newOriginalStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
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
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingEdges()).basicAdd(otherEnd, msgs);
        case ViewpointPackage.DNODE__INCOMING_EDGES:
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
        case ViewpointPackage.DNODE__OWNED_BORDERED_NODES:
            return ((InternalEList<?>) getOwnedBorderedNodes()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DNODE__INCOMING_EDGES:
            return ((InternalEList<?>) getIncomingEdges()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DNODE__OWNED_STYLE:
            return basicSetOwnedStyle(null, msgs);
        case ViewpointPackage.DNODE__OWNED_DETAILS:
            return ((InternalEList<?>) getOwnedDetails()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DNODE__OWNED_BORDERED_NODES:
            return getOwnedBorderedNodes();
        case ViewpointPackage.DNODE__ARRANGE_CONSTRAINTS:
            return getArrangeConstraints();
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            return getOutgoingEdges();
        case ViewpointPackage.DNODE__INCOMING_EDGES:
            return getIncomingEdges();
        case ViewpointPackage.DNODE__WIDTH:
            return getWidth();
        case ViewpointPackage.DNODE__HEIGHT:
            return getHeight();
        case ViewpointPackage.DNODE__OWNED_STYLE:
            if (resolve)
                return getOwnedStyle();
            return basicGetOwnedStyle();
        case ViewpointPackage.DNODE__LABEL_POSITION:
            return getLabelPosition();
        case ViewpointPackage.DNODE__OWNED_DETAILS:
            return getOwnedDetails();
        case ViewpointPackage.DNODE__RESIZE_KIND:
            return getResizeKind();
        case ViewpointPackage.DNODE__ORIGINAL_STYLE:
            if (resolve)
                return getOriginalStyle();
            return basicGetOriginalStyle();
        case ViewpointPackage.DNODE__ACTUAL_MAPPING:
            if (resolve)
                return getActualMapping();
            return basicGetActualMapping();
        case ViewpointPackage.DNODE__CANDIDATES_MAPPING:
            return getCandidatesMapping();
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
        case ViewpointPackage.DNODE__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            getOwnedBorderedNodes().addAll((Collection<? extends DNode>) newValue);
            return;
        case ViewpointPackage.DNODE__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            getArrangeConstraints().addAll((Collection<? extends ArrangeConstraint>) newValue);
            return;
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case ViewpointPackage.DNODE__INCOMING_EDGES:
            getIncomingEdges().clear();
            getIncomingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case ViewpointPackage.DNODE__WIDTH:
            setWidth((Integer) newValue);
            return;
        case ViewpointPackage.DNODE__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case ViewpointPackage.DNODE__OWNED_STYLE:
            setOwnedStyle((NodeStyle) newValue);
            return;
        case ViewpointPackage.DNODE__LABEL_POSITION:
            setLabelPosition((LabelPosition) newValue);
            return;
        case ViewpointPackage.DNODE__OWNED_DETAILS:
            getOwnedDetails().clear();
            getOwnedDetails().addAll((Collection<? extends DDiagram>) newValue);
            return;
        case ViewpointPackage.DNODE__RESIZE_KIND:
            setResizeKind((ResizeKind) newValue);
            return;
        case ViewpointPackage.DNODE__ORIGINAL_STYLE:
            setOriginalStyle((Style) newValue);
            return;
        case ViewpointPackage.DNODE__ACTUAL_MAPPING:
            setActualMapping((NodeMapping) newValue);
            return;
        case ViewpointPackage.DNODE__CANDIDATES_MAPPING:
            getCandidatesMapping().clear();
            getCandidatesMapping().addAll((Collection<? extends NodeMapping>) newValue);
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
        case ViewpointPackage.DNODE__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            return;
        case ViewpointPackage.DNODE__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            return;
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case ViewpointPackage.DNODE__INCOMING_EDGES:
            getIncomingEdges().clear();
            return;
        case ViewpointPackage.DNODE__WIDTH:
            setWidth(WIDTH_EDEFAULT);
            return;
        case ViewpointPackage.DNODE__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case ViewpointPackage.DNODE__OWNED_STYLE:
            setOwnedStyle((NodeStyle) null);
            return;
        case ViewpointPackage.DNODE__LABEL_POSITION:
            setLabelPosition(LABEL_POSITION_EDEFAULT);
            return;
        case ViewpointPackage.DNODE__OWNED_DETAILS:
            getOwnedDetails().clear();
            return;
        case ViewpointPackage.DNODE__RESIZE_KIND:
            setResizeKind(RESIZE_KIND_EDEFAULT);
            return;
        case ViewpointPackage.DNODE__ORIGINAL_STYLE:
            setOriginalStyle((Style) null);
            return;
        case ViewpointPackage.DNODE__ACTUAL_MAPPING:
            setActualMapping((NodeMapping) null);
            return;
        case ViewpointPackage.DNODE__CANDIDATES_MAPPING:
            getCandidatesMapping().clear();
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
        case ViewpointPackage.DNODE__OWNED_BORDERED_NODES:
            return ownedBorderedNodes != null && !ownedBorderedNodes.isEmpty();
        case ViewpointPackage.DNODE__ARRANGE_CONSTRAINTS:
            return arrangeConstraints != null && !arrangeConstraints.isEmpty();
        case ViewpointPackage.DNODE__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case ViewpointPackage.DNODE__INCOMING_EDGES:
            return incomingEdges != null && !incomingEdges.isEmpty();
        case ViewpointPackage.DNODE__WIDTH:
            return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
        case ViewpointPackage.DNODE__HEIGHT:
            return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
        case ViewpointPackage.DNODE__OWNED_STYLE:
            return ownedStyle != null;
        case ViewpointPackage.DNODE__LABEL_POSITION:
            return labelPosition != LABEL_POSITION_EDEFAULT;
        case ViewpointPackage.DNODE__OWNED_DETAILS:
            return ownedDetails != null && !ownedDetails.isEmpty();
        case ViewpointPackage.DNODE__RESIZE_KIND:
            return resizeKind != RESIZE_KIND_EDEFAULT;
        case ViewpointPackage.DNODE__ORIGINAL_STYLE:
            return originalStyle != null;
        case ViewpointPackage.DNODE__ACTUAL_MAPPING:
            return actualMapping != null;
        case ViewpointPackage.DNODE__CANDIDATES_MAPPING:
            return candidatesMapping != null && !candidatesMapping.isEmpty();
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
            case ViewpointPackage.DNODE__OUTGOING_EDGES:
                return ViewpointPackage.EDGE_TARGET__OUTGOING_EDGES;
            case ViewpointPackage.DNODE__INCOMING_EDGES:
                return ViewpointPackage.EDGE_TARGET__INCOMING_EDGES;
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
            case ViewpointPackage.EDGE_TARGET__OUTGOING_EDGES:
                return ViewpointPackage.DNODE__OUTGOING_EDGES;
            case ViewpointPackage.EDGE_TARGET__INCOMING_EDGES:
                return ViewpointPackage.DNODE__INCOMING_EDGES;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (arrangeConstraints: ");
        result.append(arrangeConstraints);
        result.append(", width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(", labelPosition: ");
        result.append(labelPosition);
        result.append(", resizeKind: ");
        result.append(resizeKind);
        result.append(')');
        return result.toString();
    }

} // DNodeImpl
