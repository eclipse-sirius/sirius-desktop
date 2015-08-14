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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TReturn Message Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl#getInvocationMapping
 * <em>Invocation Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl#getInvocationMessageFinderExpression
 * <em>Invocation Message Finder Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TReturnMessageMappingImpl extends TMessageMappingImpl implements TReturnMessageMapping {
    /**
     * The cached value of the '{@link #getInvocationMapping()
     * <em>Invocation Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getInvocationMapping()
     * @generated
     * @ordered
     */
    protected TBasicMessageMapping invocationMapping;

    /**
     * The default value of the '{@link #getInvocationMessageFinderExpression()
     * <em>Invocation Message Finder Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInvocationMessageFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInvocationMessageFinderExpression()
     * <em>Invocation Message Finder Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInvocationMessageFinderExpression()
     * @generated
     * @ordered
     */
    protected String invocationMessageFinderExpression = TReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TReturnMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TRETURN_MESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TBasicMessageMapping getInvocationMapping() {
        if (invocationMapping != null && invocationMapping.eIsProxy()) {
            InternalEObject oldInvocationMapping = (InternalEObject) invocationMapping;
            invocationMapping = (TBasicMessageMapping) eResolveProxy(oldInvocationMapping);
            if (invocationMapping != oldInvocationMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING, oldInvocationMapping, invocationMapping));
                }
            }
        }
        return invocationMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TBasicMessageMapping basicGetInvocationMapping() {
        return invocationMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInvocationMapping(TBasicMessageMapping newInvocationMapping) {
        TBasicMessageMapping oldInvocationMapping = invocationMapping;
        invocationMapping = newInvocationMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING, oldInvocationMapping, invocationMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getInvocationMessageFinderExpression() {
        return invocationMessageFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInvocationMessageFinderExpression(String newInvocationMessageFinderExpression) {
        String oldInvocationMessageFinderExpression = invocationMessageFinderExpression;
        invocationMessageFinderExpression = newInvocationMessageFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION, oldInvocationMessageFinderExpression,
                    invocationMessageFinderExpression));
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
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING:
            if (resolve) {
                return getInvocationMapping();
            }
            return basicGetInvocationMapping();
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            return getInvocationMessageFinderExpression();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING:
            setInvocationMapping((TBasicMessageMapping) newValue);
            return;
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            setInvocationMessageFinderExpression((String) newValue);
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
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING:
            setInvocationMapping((TBasicMessageMapping) null);
            return;
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            setInvocationMessageFinderExpression(TReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT);
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
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING:
            return invocationMapping != null;
        case TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            return TReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT == null ? invocationMessageFinderExpression != null
                    : !TReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT.equals(invocationMessageFinderExpression);
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
        result.append(" (invocationMessageFinderExpression: "); //$NON-NLS-1$
        result.append(invocationMessageFinderExpression);
        result.append(')');
        return result.toString();
    }

} // TReturnMessageMappingImpl
