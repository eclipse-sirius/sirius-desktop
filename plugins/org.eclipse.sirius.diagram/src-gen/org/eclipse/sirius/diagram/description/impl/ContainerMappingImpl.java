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
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getDropDescriptions
 * <em>Drop Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getSubNodeMappings
 * <em>Sub Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getAllNodeMappings
 * <em>All Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getReusedNodeMappings
 * <em>Reused Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getSubContainerMappings
 * <em>Sub Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getReusedContainerMappings
 * <em>Reused Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getAllContainerMappings
 * <em>All Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl#getChildrenPresentation
 * <em>Children Presentation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerMappingImpl extends AbstractNodeMappingImpl implements ContainerMapping {
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
     * The cached value of the '{@link #getSubNodeMappings()
     * <em>Sub Node Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> subNodeMappings;

    /**
     * The cached value of the '{@link #getReusedNodeMappings()
     * <em>Reused Node Mappings</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getReusedNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> reusedNodeMappings;

    /**
     * The cached value of the '{@link #getSubContainerMappings()
     * <em>Sub Container Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubContainerMappings()
     * @generated
     * @ordered
     */
    protected EList<ContainerMapping> subContainerMappings;

    /**
     * The cached value of the '{@link #getReusedContainerMappings()
     * <em>Reused Container Mappings</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getReusedContainerMappings()
     * @generated
     * @ordered
     */
    protected EList<ContainerMapping> reusedContainerMappings;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ContainerStyleDescription style;

    /**
     * The cached value of the '{@link #getConditionnalStyles()
     * <em>Conditionnal Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionnalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalContainerStyleDescription> conditionnalStyles;

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
    protected ContainerLayout childrenPresentation = ContainerMappingImpl.CHILDREN_PRESENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContainerMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CONTAINER_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerDropDescription> getDropDescriptions() {
        if (dropDescriptions == null) {
            dropDescriptions = new EObjectResolvingEList<ContainerDropDescription>(ContainerDropDescription.class, this, DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS);
        }
        return dropDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getSubNodeMappings() {
        if (subNodeMappings == null) {
            subNodeMappings = new EObjectContainmentEList.Resolving<NodeMapping>(NodeMapping.class, this, DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS);
        }
        return subNodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getAllNodeMappings() {
        // TODO: implement this method to return the 'All Node Mappings'
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
    public EList<NodeMapping> getReusedNodeMappings() {
        if (reusedNodeMappings == null) {
            reusedNodeMappings = new EObjectResolvingEList<NodeMapping>(NodeMapping.class, this, DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS);
        }
        return reusedNodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerMapping> getSubContainerMappings() {
        if (subContainerMappings == null) {
            subContainerMappings = new EObjectContainmentEList.Resolving<ContainerMapping>(ContainerMapping.class, this, DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS);
        }
        return subContainerMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerMapping> getReusedContainerMappings() {
        if (reusedContainerMappings == null) {
            reusedContainerMappings = new EObjectResolvingEList<ContainerMapping>(ContainerMapping.class, this, DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS);
        }
        return reusedContainerMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerMapping> getAllContainerMappings() {
        // TODO: implement this method to return the 'All Container Mappings'
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
    public ContainerStyleDescription getStyle() {
        if (style != null && style.eIsProxy()) {
            InternalEObject oldStyle = (InternalEObject) style;
            style = (ContainerStyleDescription) eResolveProxy(oldStyle);
            if (style != oldStyle) {
                InternalEObject newStyle = (InternalEObject) style;
                NotificationChain msgs = oldStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONTAINER_MAPPING__STYLE, null, null);
                if (newStyle.eInternalContainer() == null) {
                    msgs = newStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONTAINER_MAPPING__STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.CONTAINER_MAPPING__STYLE, oldStyle, style));
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
    public ContainerStyleDescription basicGetStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ContainerStyleDescription newStyle, NotificationChain msgs) {
        ContainerStyleDescription oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.CONTAINER_MAPPING__STYLE, oldStyle, newStyle);
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
    public void setStyle(ContainerStyleDescription newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONTAINER_MAPPING__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CONTAINER_MAPPING__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CONTAINER_MAPPING__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConditionalContainerStyleDescription> getConditionnalStyles() {
        if (conditionnalStyles == null) {
            conditionnalStyles = new EObjectContainmentEList.Resolving<ConditionalContainerStyleDescription>(ConditionalContainerStyleDescription.class, this,
                    DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES);
        }
        return conditionnalStyles;
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
        childrenPresentation = newChildrenPresentation == null ? ContainerMappingImpl.CHILDREN_PRESENTATION_EDEFAULT : newChildrenPresentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION, oldChildrenPresentation, childrenPresentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable) {
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
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
            return ((InternalEList<?>) getSubNodeMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
            return ((InternalEList<?>) getSubContainerMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
            return basicSetStyle(null, msgs);
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
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
        case DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS:
            return getDropDescriptions();
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
            return getSubNodeMappings();
        case DescriptionPackage.CONTAINER_MAPPING__ALL_NODE_MAPPINGS:
            return getAllNodeMappings();
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS:
            return getReusedNodeMappings();
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
            return getSubContainerMappings();
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS:
            return getReusedContainerMappings();
        case DescriptionPackage.CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS:
            return getAllContainerMappings();
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
            if (resolve) {
                return getStyle();
            }
            return basicGetStyle();
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
            return getConditionnalStyles();
        case DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION:
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
        case DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS:
            getDropDescriptions().clear();
            getDropDescriptions().addAll((Collection<? extends ContainerDropDescription>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
            getSubNodeMappings().clear();
            getSubNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS:
            getReusedNodeMappings().clear();
            getReusedNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
            getSubContainerMappings().clear();
            getSubContainerMappings().addAll((Collection<? extends ContainerMapping>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS:
            getReusedContainerMappings().clear();
            getReusedContainerMappings().addAll((Collection<? extends ContainerMapping>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
            setStyle((ContainerStyleDescription) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            getConditionnalStyles().addAll((Collection<? extends ConditionalContainerStyleDescription>) newValue);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION:
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
        case DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS:
            getDropDescriptions().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
            getSubNodeMappings().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS:
            getReusedNodeMappings().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
            getSubContainerMappings().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS:
            getReusedContainerMappings().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
            setStyle((ContainerStyleDescription) null);
            return;
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            return;
        case DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION:
            setChildrenPresentation(ContainerMappingImpl.CHILDREN_PRESENTATION_EDEFAULT);
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
        case DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS:
            return dropDescriptions != null && !dropDescriptions.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
            return subNodeMappings != null && !subNodeMappings.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__ALL_NODE_MAPPINGS:
            return !getAllNodeMappings().isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS:
            return reusedNodeMappings != null && !reusedNodeMappings.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
            return subContainerMappings != null && !subContainerMappings.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS:
            return reusedContainerMappings != null && !reusedContainerMappings.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS:
            return !getAllContainerMappings().isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
            return style != null;
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
            return conditionnalStyles != null && !conditionnalStyles.isEmpty();
        case DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION:
            return childrenPresentation != ContainerMappingImpl.CHILDREN_PRESENTATION_EDEFAULT;
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
            case DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS:
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
                return DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS;
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
        result.append(" (childrenPresentation: "); //$NON-NLS-1$
        result.append(childrenPresentation);
        result.append(')');
        return result.toString();
    }

} // ContainerMappingImpl
