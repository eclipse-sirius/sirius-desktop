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
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getSourceMapping
 * <em>Source Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getTargetMapping
 * <em>Target Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getTargetFinderExpression
 * <em>Target Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getSourceFinderExpression
 * <em>Source Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getTargetExpression
 * <em>Target Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#isUseDomainElement
 * <em>Use Domain Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getReconnections
 * <em>Reconnections</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getPathExpression
 * <em>Path Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getPathNodeMapping
 * <em>Path Node Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeMappingImpl extends DiagramElementMappingImpl implements EdgeMapping {
    /**
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = EdgeMappingImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSourceMapping()
     * <em>Source Mapping</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSourceMapping()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> sourceMapping;

    /**
     * The cached value of the '{@link #getTargetMapping()
     * <em>Target Mapping</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetMapping()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> targetMapping;

    /**
     * The default value of the '{@link #getTargetFinderExpression()
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getTargetFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String TARGET_FINDER_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTargetFinderExpression()
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getTargetFinderExpression()
     * @generated
     * @ordered
     */
    protected String targetFinderExpression = EdgeMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceFinderExpression()
     * <em>Source Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getSourceFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceFinderExpression()
     * <em>Source Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getSourceFinderExpression()
     * @generated
     * @ordered
     */
    protected String sourceFinderExpression = EdgeMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected EdgeStyleDescription style;

    /**
     * The cached value of the '{@link #getConditionnalStyles()
     * <em>Conditionnal Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionnalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalEdgeStyleDescription> conditionnalStyles;

    /**
     * The default value of the '{@link #getTargetExpression()
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetExpression()
     * @generated
     * @ordered
     */
    protected static final String TARGET_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetExpression()
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetExpression()
     * @generated
     * @ordered
     */
    protected String targetExpression = EdgeMappingImpl.TARGET_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDomainClass() <em>Domain Class</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClass() <em>Domain Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected String domainClass = EdgeMappingImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #isUseDomainElement()
     * <em>Use Domain Element</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUseDomainElement()
     * @generated
     * @ordered
     */
    protected static final boolean USE_DOMAIN_ELEMENT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseDomainElement()
     * <em>Use Domain Element</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUseDomainElement()
     * @generated
     * @ordered
     */
    protected boolean useDomainElement = EdgeMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getReconnections()
     * <em>Reconnections</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReconnections()
     * @generated
     * @ordered
     */
    protected EList<ReconnectEdgeDescription> reconnections;

    /**
     * The default value of the '{@link #getPathExpression()
     * <em>Path Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPathExpression()
     * @generated
     * @ordered
     */
    protected static final String PATH_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPathExpression()
     * <em>Path Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPathExpression()
     * @generated
     * @ordered
     */
    protected String pathExpression = EdgeMappingImpl.PATH_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getPathNodeMapping()
     * <em>Path Node Mapping</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPathNodeMapping()
     * @generated
     * @ordered
     */
    protected EList<AbstractNodeMapping> pathNodeMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EDGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getSourceMapping() {
        if (sourceMapping == null) {
            sourceMapping = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING);
        }
        return sourceMapping;
    }

    /**
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getTargetMapping() {
        if (targetMapping == null) {
            targetMapping = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING);
        }
        return targetMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTargetFinderExpression() {
        return targetFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetFinderExpression(String newTargetFinderExpression) {
        String oldTargetFinderExpression = targetFinderExpression;
        targetFinderExpression = newTargetFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION, oldTargetFinderExpression, targetFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSourceFinderExpression() {
        return sourceFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSourceFinderExpression(String newSourceFinderExpression) {
        String oldSourceFinderExpression = sourceFinderExpression;
        sourceFinderExpression = newSourceFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION, oldSourceFinderExpression, sourceFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyleDescription getStyle() {
        if (style != null && style.eIsProxy()) {
            InternalEObject oldStyle = (InternalEObject) style;
            style = (EdgeStyleDescription) eResolveProxy(oldStyle);
            if (style != oldStyle) {
                InternalEObject newStyle = (InternalEObject) style;
                NotificationChain msgs = oldStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.EDGE_MAPPING__STYLE, null, null);
                if (newStyle.eInternalContainer() == null) {
                    msgs = newStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.EDGE_MAPPING__STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.EDGE_MAPPING__STYLE, oldStyle, style));
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
    public EdgeStyleDescription basicGetStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(EdgeStyleDescription newStyle, NotificationChain msgs) {
        EdgeStyleDescription oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__STYLE, oldStyle, newStyle);
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
    public void setStyle(EdgeStyleDescription newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.EDGE_MAPPING__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.EDGE_MAPPING__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConditionalEdgeStyleDescription> getConditionnalStyles() {
        if (conditionnalStyles == null) {
            conditionnalStyles = new EObjectContainmentEList.Resolving<ConditionalEdgeStyleDescription>(ConditionalEdgeStyleDescription.class, this,
                    DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES);
        }
        return conditionnalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTargetExpression() {
        return targetExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetExpression(String newTargetExpression) {
        String oldTargetExpression = targetExpression;
        targetExpression = newTargetExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION, oldTargetExpression, targetExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isUseDomainElement() {
        return useDomainElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUseDomainElement(boolean newUseDomainElement) {
        boolean oldUseDomainElement = useDomainElement;
        useDomainElement = newUseDomainElement;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT, oldUseDomainElement, useDomainElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ReconnectEdgeDescription> getReconnections() {
        if (reconnections == null) {
            reconnections = new EObjectResolvingEList<ReconnectEdgeDescription>(ReconnectEdgeDescription.class, this, DescriptionPackage.EDGE_MAPPING__RECONNECTIONS);
        }
        return reconnections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPathExpression() {
        return pathExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPathExpression(String newPathExpression) {
        String oldPathExpression = pathExpression;
        pathExpression = newPathExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION, oldPathExpression, pathExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractNodeMapping> getPathNodeMapping() {
        if (pathNodeMapping == null) {
            pathNodeMapping = new EObjectResolvingEList<AbstractNodeMapping>(AbstractNodeMapping.class, this, DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING);
        }
        return pathNodeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DEdge createEdge(EdgeTarget source, EdgeTarget target, EObject semanticTarget) {
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
    public DEdge createEdge(EdgeTarget source, EdgeTarget target, EObject container, EObject semanticTarget) {
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
    public EdgeStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable) {
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
    public void updateEdge(DEdge viewEdge) {
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
    public EList<EObject> getEdgeTargetCandidates(EObject semanticOrigin, DDiagram viewPoint) {
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
    public EList<EObject> getEdgeSourceCandidates(EObject semanticOrigin, DDiagram viewPoint) {
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
    public EList<EObject> getEdgeTargetCandidates(EObject semanticOrigin, EObject container, EObject containerView) {
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
        case DescriptionPackage.EDGE_MAPPING__STYLE:
            return basicSetStyle(null, msgs);
        case DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
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
        case DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
            return getDocumentation();
        case DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING:
            return getSourceMapping();
        case DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING:
            return getTargetMapping();
        case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            return getTargetFinderExpression();
        case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            return getSourceFinderExpression();
        case DescriptionPackage.EDGE_MAPPING__STYLE:
            if (resolve) {
                return getStyle();
            }
            return basicGetStyle();
        case DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
            return getConditionnalStyles();
        case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            return getTargetExpression();
        case DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT:
            return isUseDomainElement();
        case DescriptionPackage.EDGE_MAPPING__RECONNECTIONS:
            return getReconnections();
        case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            return getPathExpression();
        case DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING:
            return getPathNodeMapping();
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
        case DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING:
            getSourceMapping().clear();
            getSourceMapping().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING:
            getTargetMapping().clear();
            getTargetMapping().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            setTargetFinderExpression((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            setSourceFinderExpression((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__STYLE:
            setStyle((EdgeStyleDescription) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            getConditionnalStyles().addAll((Collection<? extends ConditionalEdgeStyleDescription>) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            setTargetExpression((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT:
            setUseDomainElement((Boolean) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__RECONNECTIONS:
            getReconnections().clear();
            getReconnections().addAll((Collection<? extends ReconnectEdgeDescription>) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            setPathExpression((String) newValue);
            return;
        case DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING:
            getPathNodeMapping().clear();
            getPathNodeMapping().addAll((Collection<? extends AbstractNodeMapping>) newValue);
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
        case DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
            setDocumentation(EdgeMappingImpl.DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING:
            getSourceMapping().clear();
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING:
            getTargetMapping().clear();
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            setTargetFinderExpression(EdgeMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            setSourceFinderExpression(EdgeMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__STYLE:
            setStyle((EdgeStyleDescription) null);
            return;
        case DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
            getConditionnalStyles().clear();
            return;
        case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            setTargetExpression(EdgeMappingImpl.TARGET_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS:
            setDomainClass(EdgeMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT:
            setUseDomainElement(EdgeMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__RECONNECTIONS:
            getReconnections().clear();
            return;
        case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            setPathExpression(EdgeMappingImpl.PATH_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING:
            getPathNodeMapping().clear();
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
        case DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
            return EdgeMappingImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !EdgeMappingImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING:
            return sourceMapping != null && !sourceMapping.isEmpty();
        case DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING:
            return targetMapping != null && !targetMapping.isEmpty();
        case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            return EdgeMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT == null ? targetFinderExpression != null : !EdgeMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT.equals(targetFinderExpression);
        case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            return EdgeMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT == null ? sourceFinderExpression != null : !EdgeMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT.equals(sourceFinderExpression);
        case DescriptionPackage.EDGE_MAPPING__STYLE:
            return style != null;
        case DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
            return conditionnalStyles != null && !conditionnalStyles.isEmpty();
        case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            return EdgeMappingImpl.TARGET_EXPRESSION_EDEFAULT == null ? targetExpression != null : !EdgeMappingImpl.TARGET_EXPRESSION_EDEFAULT.equals(targetExpression);
        case DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS:
            return EdgeMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !EdgeMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT:
            return useDomainElement != EdgeMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT;
        case DescriptionPackage.EDGE_MAPPING__RECONNECTIONS:
            return reconnections != null && !reconnections.isEmpty();
        case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            return EdgeMappingImpl.PATH_EXPRESSION_EDEFAULT == null ? pathExpression != null : !EdgeMappingImpl.PATH_EXPRESSION_EDEFAULT.equals(pathExpression);
        case DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING:
            return pathNodeMapping != null && !pathNodeMapping.isEmpty();
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
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IEdgeMapping.class) {
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
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return DescriptionPackage.EDGE_MAPPING__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IEdgeMapping.class) {
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", targetFinderExpression: "); //$NON-NLS-1$
        result.append(targetFinderExpression);
        result.append(", sourceFinderExpression: "); //$NON-NLS-1$
        result.append(sourceFinderExpression);
        result.append(", targetExpression: "); //$NON-NLS-1$
        result.append(targetExpression);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", useDomainElement: "); //$NON-NLS-1$
        result.append(useDomainElement);
        result.append(", pathExpression: "); //$NON-NLS-1$
        result.append(pathExpression);
        result.append(')');
        return result.toString();
    }

} // EdgeMappingImpl
