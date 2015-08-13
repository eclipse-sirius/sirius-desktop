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
package org.eclipse.sirius.diagram.sequence.template.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.template.TMessageExtremity;
import org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TSource Target Message Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl#getSourceFinderExpression
 * <em>Source Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl#getTargetFinderExpression
 * <em>Target Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl#isUseDomainElement
 * <em>Use Domain Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TSourceTargetMessageMappingImpl extends TMessageMappingImpl implements TSourceTargetMessageMapping {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected EList<TMessageExtremity> source;

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
    protected String sourceFinderExpression = TSourceTargetMessageMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetFinderExpression()
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String TARGET_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetFinderExpression()
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetFinderExpression()
     * @generated
     * @ordered
     */
    protected String targetFinderExpression = TSourceTargetMessageMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT;

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
    protected boolean useDomainElement = TSourceTargetMessageMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TSourceTargetMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TSOURCE_TARGET_MESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TMessageExtremity> getSource() {
        if (source == null) {
            source = new EObjectResolvingEList<TMessageExtremity>(TMessageExtremity.class, this, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE);
        }
        return source;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION, oldSourceFinderExpression, sourceFinderExpression));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION, oldTargetFinderExpression, targetFinderExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT, oldUseDomainElement, useDomainElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE:
            return getSource();
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            return getSourceFinderExpression();
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION:
            return getTargetFinderExpression();
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT:
            return isUseDomainElement();
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
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE:
            getSource().clear();
            getSource().addAll((Collection<? extends TMessageExtremity>) newValue);
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            setSourceFinderExpression((String) newValue);
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION:
            setTargetFinderExpression((String) newValue);
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT:
            setUseDomainElement((Boolean) newValue);
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
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE:
            getSource().clear();
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            setSourceFinderExpression(TSourceTargetMessageMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION:
            setTargetFinderExpression(TSourceTargetMessageMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT:
            setUseDomainElement(TSourceTargetMessageMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT);
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
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE:
            return source != null && !source.isEmpty();
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            return TSourceTargetMessageMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT == null ? sourceFinderExpression != null : !TSourceTargetMessageMappingImpl.SOURCE_FINDER_EXPRESSION_EDEFAULT
                    .equals(sourceFinderExpression);
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION:
            return TSourceTargetMessageMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT == null ? targetFinderExpression != null : !TSourceTargetMessageMappingImpl.TARGET_FINDER_EXPRESSION_EDEFAULT
                    .equals(targetFinderExpression);
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT:
            return useDomainElement != TSourceTargetMessageMappingImpl.USE_DOMAIN_ELEMENT_EDEFAULT;
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
        result.append(" (sourceFinderExpression: "); //$NON-NLS-1$
        result.append(sourceFinderExpression);
        result.append(", targetFinderExpression: "); //$NON-NLS-1$
        result.append(targetFinderExpression);
        result.append(", useDomainElement: "); //$NON-NLS-1$
        result.append(useDomainElement);
        result.append(')');
        return result.toString();
    }

} // TSourceTargetMessageMappingImpl
