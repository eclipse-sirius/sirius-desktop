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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DNode List Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getOwnedBorderedNodes
 * <em>Owned Bordered Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getArrangeConstraints
 * <em>Arrange Constraints</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DNodeListElementImpl extends DDiagramElementImpl implements DNodeListElement {
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
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected NodeStyle ownedStyle;

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
    protected DNodeListElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DNODE_LIST_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNode> getOwnedBorderedNodes() {
        if (ownedBorderedNodes == null) {
            ownedBorderedNodes = new EObjectContainmentEList.Resolving<DNode>(DNode.class, this, DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES);
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
            arrangeConstraints = new EDataTypeUniqueEList<ArrangeConstraint>(ArrangeConstraint.class, this, DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS);
        }
        return arrangeConstraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeStyle getOwnedStyle() {
        if (ownedStyle != null && ownedStyle.eIsProxy()) {
            InternalEObject oldOwnedStyle = (InternalEObject) ownedStyle;
            ownedStyle = (NodeStyle) eResolveProxy(oldOwnedStyle);
            if (ownedStyle != oldOwnedStyle) {
                InternalEObject newOwnedStyle = (InternalEObject) ownedStyle;
                NotificationChain msgs = oldOwnedStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, null, null);
                if (newOwnedStyle.eInternalContainer() == null) {
                    msgs = newOwnedStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, oldOwnedStyle, ownedStyle));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(NodeStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE, oldOriginalStyle, originalStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeMapping getActualMapping() {
        if (actualMapping != null && actualMapping.eIsProxy()) {
            InternalEObject oldActualMapping = (InternalEObject) actualMapping;
            actualMapping = (NodeMapping) eResolveProxy(oldActualMapping);
            if (actualMapping != oldActualMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING, oldActualMapping, actualMapping));
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
    public NodeMapping basicGetActualMapping() {
        return actualMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setActualMapping(NodeMapping newActualMapping) {
        NodeMapping oldActualMapping = actualMapping;
        actualMapping = newActualMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING, oldActualMapping, actualMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getCandidatesMapping() {
        if (candidatesMapping == null) {
            candidatesMapping = new EObjectResolvingEList<NodeMapping>(NodeMapping.class, this, DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING);
        }
        return candidatesMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
            return ((InternalEList<?>) getOwnedBorderedNodes()).basicRemove(otherEnd, msgs);
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
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
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
            return getOwnedBorderedNodes();
        case DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS:
            return getArrangeConstraints();
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
            if (resolve) {
                return getOwnedStyle();
            }
            return basicGetOwnedStyle();
        case DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE:
            if (resolve) {
                return getOriginalStyle();
            }
            return basicGetOriginalStyle();
        case DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING:
            if (resolve) {
                return getActualMapping();
            }
            return basicGetActualMapping();
        case DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING:
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
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            getOwnedBorderedNodes().addAll((Collection<? extends DNode>) newValue);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            getArrangeConstraints().addAll((Collection<? extends ArrangeConstraint>) newValue);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
            setOwnedStyle((NodeStyle) newValue);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE:
            setOriginalStyle((Style) newValue);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING:
            setActualMapping((NodeMapping) newValue);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING:
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
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
            getOwnedBorderedNodes().clear();
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS:
            getArrangeConstraints().clear();
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
            setOwnedStyle((NodeStyle) null);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE:
            setOriginalStyle((Style) null);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING:
            setActualMapping((NodeMapping) null);
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING:
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
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
            return ownedBorderedNodes != null && !ownedBorderedNodes.isEmpty();
        case DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS:
            return arrangeConstraints != null && !arrangeConstraints.isEmpty();
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
            return ownedStyle != null;
        case DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE:
            return originalStyle != null;
        case DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING:
            return actualMapping != null;
        case DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING:
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
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (arrangeConstraints: "); //$NON-NLS-1$
        result.append(arrangeConstraints);
        result.append(')');
        return result.toString();
    }

} // DNodeListElementImpl
