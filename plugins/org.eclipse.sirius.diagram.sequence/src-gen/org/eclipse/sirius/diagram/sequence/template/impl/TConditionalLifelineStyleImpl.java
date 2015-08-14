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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TConditional Lifeline Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl#getPredicateExpression
 * <em>Predicate Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl#getStyle
 * <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TConditionalLifelineStyleImpl extends TTransformerImpl implements TConditionalLifelineStyle {
    /**
     * The default value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected static final String PREDICATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected String predicateExpression = TConditionalLifelineStyleImpl.PREDICATE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected TLifelineStyle style;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TConditionalLifelineStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TCONDITIONAL_LIFELINE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPredicateExpression() {
        return predicateExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPredicateExpression(String newPredicateExpression) {
        String oldPredicateExpression = predicateExpression;
        predicateExpression = newPredicateExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION, oldPredicateExpression, predicateExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TLifelineStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStyle(TLifelineStyle newStyle, NotificationChain msgs) {
        TLifelineStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE, oldStyle, newStyle);
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
    public void setStyle(TLifelineStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE:
            return basicSetStyle(null, msgs);
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
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION:
            return getPredicateExpression();
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE:
            return getStyle();
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
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION:
            setPredicateExpression((String) newValue);
            return;
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE:
            setStyle((TLifelineStyle) newValue);
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
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION:
            setPredicateExpression(TConditionalLifelineStyleImpl.PREDICATE_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE:
            setStyle((TLifelineStyle) null);
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
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION:
            return TConditionalLifelineStyleImpl.PREDICATE_EXPRESSION_EDEFAULT == null ? predicateExpression != null : !TConditionalLifelineStyleImpl.PREDICATE_EXPRESSION_EDEFAULT
                    .equals(predicateExpression);
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE:
            return style != null;
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
        result.append(" (predicateExpression: "); //$NON-NLS-1$
        result.append(predicateExpression);
        result.append(')');
        return result.toString();
    }

} // TConditionalLifelineStyleImpl
