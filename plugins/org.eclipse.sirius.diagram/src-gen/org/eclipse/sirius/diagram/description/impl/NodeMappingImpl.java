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
package org.eclipse.sirius.diagram.description.impl;

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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImpl#getDropDescriptions
 * <em>Drop Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.NodeMappingImpl#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeMappingImpl extends AbstractNodeMappingImpl implements NodeMapping {
    /**
     * The cached value of the '{@link #getDropDescriptions()
     * <em>Drop Descriptions</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDropDescriptions()
     * @generated
     * @ordered
     */
    protected EList<ContainerDropDescription> dropDescriptions;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected NodeStyleDescription style;

    /**
     * The cached value of the '{@link #getConditionnalStyles()
     * <em>Conditionnal Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionnalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalNodeStyleDescription> conditionnalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.NODE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerDropDescription> getDropDescriptions() {
        if (dropDescriptions == null) {
            dropDescriptions = new EObjectResolvingEList<ContainerDropDescription>(ContainerDropDescription.class, this, DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS);
        }
        return dropDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeStyleDescription getStyle() {
        if (style != null && style.eIsProxy()) {
            InternalEObject oldStyle = (InternalEObject) style;
            style = (NodeStyleDescription) eResolveProxy(oldStyle);
            if (style != oldStyle) {
                InternalEObject newStyle = (InternalEObject) style;
                NotificationChain msgs = oldStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.NODE_MAPPING__STYLE, null, null);
                if (newStyle.eInternalContainer() == null) {
                    msgs = newStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.NODE_MAPPING__STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.NODE_MAPPING__STYLE, oldStyle, style));
                }
            }
        }
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NodeStyleDescription basicGetStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(NodeStyleDescription newStyle, NotificationChain msgs) {
        NodeStyleDescription oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING__STYLE, oldStyle, newStyle);
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
    public void setStyle(NodeStyleDescription newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.NODE_MAPPING__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.NODE_MAPPING__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.NODE_MAPPING__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConditionalNodeStyleDescription> getConditionnalStyles() {
        if (conditionnalStyles == null) {
            conditionnalStyles = new EObjectContainmentEList.Resolving<ConditionalNodeStyleDescription>(ConditionalNodeStyleDescription.class, this,
                    DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES);
        }
        return conditionnalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DNode createNode(EObject modelElement, EObject container, DDiagram viewPoint) {
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
    public void updateNode(DNode node) {
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
    public void updateListElement(DNodeListElement listElement) {
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
    public EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container) {
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
    public EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container, EObject containerView) {
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
        case DescriptionPackage.NODE_MAPPING__STYLE:
            return basicSetStyle(null, msgs);
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
            return ((InternalEList<?>) getConditionnalStyles()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS:
            return getDropDescriptions();
        case DescriptionPackage.NODE_MAPPING__STYLE:
            if (resolve) {
                return getStyle();
            }
            return basicGetStyle();
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
            return getConditionnalStyles();
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
        case DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS:
            getDropDescriptions().clear();
            getDropDescriptions().addAll((Collection<? extends ContainerDropDescription>) newValue);
            return;
        case DescriptionPackage.NODE_MAPPING__STYLE:
            setStyle((NodeStyleDescription) newValue);
            return;
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            getConditionnalStyles().addAll((Collection<? extends ConditionalNodeStyleDescription>) newValue);
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
        case DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS:
            getDropDescriptions().clear();
            return;
        case DescriptionPackage.NODE_MAPPING__STYLE:
            setStyle((NodeStyleDescription) null);
            return;
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
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
        case DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS:
            return dropDescriptions != null && !dropDescriptions.isEmpty();
        case DescriptionPackage.NODE_MAPPING__STYLE:
            return style != null;
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
            return conditionnalStyles != null && !conditionnalStyles.isEmpty();
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
        if (baseClass == DragAndDropTargetDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS:
                return DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS;
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
        if (baseClass == DragAndDropTargetDescription.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS:
                return DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // NodeMappingImpl
