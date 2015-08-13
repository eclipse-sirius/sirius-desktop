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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TMessage Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl#getSendingEndFinderExpression
 * <em>Sending End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl#getReceivingEndFinderExpression
 * <em>Receiving End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl#getConditionalStyle
 * <em>Conditional Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TMessageMappingImpl extends TAbstractMappingImpl implements TMessageMapping {
    /**
     * The default value of the '{@link #getSendingEndFinderExpression()
     * <em>Sending End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSendingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String SENDING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSendingEndFinderExpression()
     * <em>Sending End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSendingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String sendingEndFinderExpression = TMessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getReceivingEndFinderExpression()
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReceivingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String RECEIVING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReceivingEndFinderExpression()
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReceivingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String receivingEndFinderExpression = TMessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected TMessageStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyle()
     * <em>Conditional Style</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyle()
     * @generated
     * @ordered
     */
    protected EList<TConditionalMessageStyle> conditionalStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TMESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSendingEndFinderExpression() {
        return sendingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSendingEndFinderExpression(String newSendingEndFinderExpression) {
        String oldSendingEndFinderExpression = sendingEndFinderExpression;
        sendingEndFinderExpression = newSendingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION, oldSendingEndFinderExpression, sendingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getReceivingEndFinderExpression() {
        return receivingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setReceivingEndFinderExpression(String newReceivingEndFinderExpression) {
        String oldReceivingEndFinderExpression = receivingEndFinderExpression;
        receivingEndFinderExpression = newReceivingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION, oldReceivingEndFinderExpression, receivingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TMessageStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStyle(TMessageStyle newStyle, NotificationChain msgs) {
        TMessageStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_MAPPING__STYLE, oldStyle, newStyle);
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
    public void setStyle(TMessageStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TMESSAGE_MAPPING__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TMESSAGE_MAPPING__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_MAPPING__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TConditionalMessageStyle> getConditionalStyle() {
        if (conditionalStyle == null) {
            conditionalStyle = new EObjectContainmentEList<TConditionalMessageStyle>(TConditionalMessageStyle.class, this, TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE);
        }
        return conditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TemplatePackage.TMESSAGE_MAPPING__STYLE:
            return basicSetStyle(null, msgs);
        case TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE:
            return ((InternalEList<?>) getConditionalStyle()).basicRemove(otherEnd, msgs);
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
        case TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            return getSendingEndFinderExpression();
        case TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            return getReceivingEndFinderExpression();
        case TemplatePackage.TMESSAGE_MAPPING__STYLE:
            return getStyle();
        case TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE:
            return getConditionalStyle();
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
        case TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            setSendingEndFinderExpression((String) newValue);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            setReceivingEndFinderExpression((String) newValue);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__STYLE:
            setStyle((TMessageStyle) newValue);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE:
            getConditionalStyle().clear();
            getConditionalStyle().addAll((Collection<? extends TConditionalMessageStyle>) newValue);
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
        case TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            setSendingEndFinderExpression(TMessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            setReceivingEndFinderExpression(TMessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__STYLE:
            setStyle((TMessageStyle) null);
            return;
        case TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE:
            getConditionalStyle().clear();
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
        case TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            return TMessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT == null ? sendingEndFinderExpression != null : !TMessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(sendingEndFinderExpression);
        case TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            return TMessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT == null ? receivingEndFinderExpression != null : !TMessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(receivingEndFinderExpression);
        case TemplatePackage.TMESSAGE_MAPPING__STYLE:
            return style != null;
        case TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE:
            return conditionalStyle != null && !conditionalStyle.isEmpty();
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
        result.append(" (sendingEndFinderExpression: "); //$NON-NLS-1$
        result.append(sendingEndFinderExpression);
        result.append(", receivingEndFinderExpression: "); //$NON-NLS-1$
        result.append(receivingEndFinderExpression);
        result.append(')');
        return result.toString();
    }

} // TMessageMappingImpl
