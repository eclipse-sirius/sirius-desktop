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
import org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TExecution Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#getStartingEndFinderExpression
 * <em>Starting End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#getFinishingEndFinderExpression
 * <em>Finishing End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#isRecursive
 * <em>Recursive</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#getExecutionMappings
 * <em>Execution Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TExecutionMappingImpl extends TAbstractMappingImpl implements TExecutionMapping {
    /**
     * The default value of the '{@link #getStartingEndFinderExpression()
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStartingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String STARTING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStartingEndFinderExpression()
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStartingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String startingEndFinderExpression = TExecutionMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFinishingEndFinderExpression()
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getFinishingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String FINISHING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFinishingEndFinderExpression()
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getFinishingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String finishingEndFinderExpression = TExecutionMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isRecursive() <em>Recursive</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isRecursive()
     * @generated
     * @ordered
     */
    protected static final boolean RECURSIVE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRecursive() <em>Recursive</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isRecursive()
     * @generated
     * @ordered
     */
    protected boolean recursive = TExecutionMappingImpl.RECURSIVE_EDEFAULT;

    /**
     * The cached value of the '{@link #getExecutionMappings()
     * <em>Execution Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExecutionMappings()
     * @generated
     * @ordered
     */
    protected EList<TExecutionMapping> executionMappings;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected TExecutionStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles()
     * <em>Conditional Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<TConditionalExecutionStyle> conditionalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TExecutionMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TEXECUTION_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getStartingEndFinderExpression() {
        return startingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStartingEndFinderExpression(String newStartingEndFinderExpression) {
        String oldStartingEndFinderExpression = startingEndFinderExpression;
        startingEndFinderExpression = newStartingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION, oldStartingEndFinderExpression, startingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFinishingEndFinderExpression() {
        return finishingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFinishingEndFinderExpression(String newFinishingEndFinderExpression) {
        String oldFinishingEndFinderExpression = finishingEndFinderExpression;
        finishingEndFinderExpression = newFinishingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION, oldFinishingEndFinderExpression, finishingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isRecursive() {
        return recursive;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRecursive(boolean newRecursive) {
        boolean oldRecursive = recursive;
        recursive = newRecursive;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_MAPPING__RECURSIVE, oldRecursive, recursive));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TExecutionMapping> getExecutionMappings() {
        if (executionMappings == null) {
            executionMappings = new EObjectContainmentEList<TExecutionMapping>(TExecutionMapping.class, this, TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS);
        }
        return executionMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TExecutionStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStyle(TExecutionStyle newStyle, NotificationChain msgs) {
        TExecutionStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_MAPPING__STYLE, oldStyle, newStyle);
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
    public void setStyle(TExecutionStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TEXECUTION_MAPPING__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TEXECUTION_MAPPING__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_MAPPING__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TConditionalExecutionStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<TConditionalExecutionStyle>(TConditionalExecutionStyle.class, this, TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
            return ((InternalEList<?>) getExecutionMappings()).basicRemove(otherEnd, msgs);
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
            return basicSetStyle(null, msgs);
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
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
        case TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return getStartingEndFinderExpression();
        case TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return getFinishingEndFinderExpression();
        case TemplatePackage.TEXECUTION_MAPPING__RECURSIVE:
            return isRecursive();
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
            return getExecutionMappings();
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
            return getStyle();
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
            return getConditionalStyles();
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
        case TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression((String) newValue);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression((String) newValue);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__RECURSIVE:
            setRecursive((Boolean) newValue);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
            getExecutionMappings().clear();
            getExecutionMappings().addAll((Collection<? extends TExecutionMapping>) newValue);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
            setStyle((TExecutionStyle) newValue);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends TConditionalExecutionStyle>) newValue);
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
        case TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression(TExecutionMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression(TExecutionMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__RECURSIVE:
            setRecursive(TExecutionMappingImpl.RECURSIVE_EDEFAULT);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
            getExecutionMappings().clear();
            return;
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
            setStyle((TExecutionStyle) null);
            return;
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
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
        case TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return TExecutionMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT == null ? startingEndFinderExpression != null : !TExecutionMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(startingEndFinderExpression);
        case TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return TExecutionMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT == null ? finishingEndFinderExpression != null : !TExecutionMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(finishingEndFinderExpression);
        case TemplatePackage.TEXECUTION_MAPPING__RECURSIVE:
            return recursive != TExecutionMappingImpl.RECURSIVE_EDEFAULT;
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
            return executionMappings != null && !executionMappings.isEmpty();
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
            return style != null;
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
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
        result.append(" (startingEndFinderExpression: "); //$NON-NLS-1$
        result.append(startingEndFinderExpression);
        result.append(", finishingEndFinderExpression: "); //$NON-NLS-1$
        result.append(finishingEndFinderExpression);
        result.append(", recursive: "); //$NON-NLS-1$
        result.append(recursive);
        result.append(')');
        return result.toString();
    }

} // TExecutionMappingImpl
