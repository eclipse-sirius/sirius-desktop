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
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TLifeline Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getEolVisibleExpression
 * <em>Eol Visible Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getExecutionMappings
 * <em>Execution Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getInstanceRoleStyle
 * <em>Instance Role Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getLifelineStyle
 * <em>Lifeline Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getEndOfLifeStyle
 * <em>End Of Life Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl#getConditionalLifeLineStyles
 * <em>Conditional Life Line Styles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TLifelineMappingImpl extends TAbstractMappingImpl implements TLifelineMapping {
    /**
     * The default value of the '{@link #getEolVisibleExpression()
     * <em>Eol Visible Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEolVisibleExpression()
     * @generated
     * @ordered
     */
    protected static final String EOL_VISIBLE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEolVisibleExpression()
     * <em>Eol Visible Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEolVisibleExpression()
     * @generated
     * @ordered
     */
    protected String eolVisibleExpression = TLifelineMappingImpl.EOL_VISIBLE_EXPRESSION_EDEFAULT;

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
     * The cached value of the '{@link #getInstanceRoleStyle()
     * <em>Instance Role Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getInstanceRoleStyle()
     * @generated
     * @ordered
     */
    protected NodeStyleDescription instanceRoleStyle;

    /**
     * The cached value of the '{@link #getLifelineStyle()
     * <em>Lifeline Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLifelineStyle()
     * @generated
     * @ordered
     */
    protected TLifelineStyle lifelineStyle;

    /**
     * The cached value of the '{@link #getEndOfLifeStyle()
     * <em>End Of Life Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getEndOfLifeStyle()
     * @generated
     * @ordered
     */
    protected NodeStyleDescription endOfLifeStyle;

    /**
     * The cached value of the '{@link #getConditionalLifeLineStyles()
     * <em>Conditional Life Line Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalLifeLineStyles()
     * @generated
     * @ordered
     */
    protected EList<TConditionalLifelineStyle> conditionalLifeLineStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TLifelineMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TLIFELINE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEolVisibleExpression() {
        return eolVisibleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEolVisibleExpression(String newEolVisibleExpression) {
        String oldEolVisibleExpression = eolVisibleExpression;
        eolVisibleExpression = newEolVisibleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION, oldEolVisibleExpression, eolVisibleExpression));
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
            executionMappings = new EObjectContainmentEList<TExecutionMapping>(TExecutionMapping.class, this, TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS);
        }
        return executionMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NodeStyleDescription getInstanceRoleStyle() {
        return instanceRoleStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetInstanceRoleStyle(NodeStyleDescription newInstanceRoleStyle, NotificationChain msgs) {
        NodeStyleDescription oldInstanceRoleStyle = instanceRoleStyle;
        instanceRoleStyle = newInstanceRoleStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, oldInstanceRoleStyle, newInstanceRoleStyle);
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
    public void setInstanceRoleStyle(NodeStyleDescription newInstanceRoleStyle) {
        if (newInstanceRoleStyle != instanceRoleStyle) {
            NotificationChain msgs = null;
            if (instanceRoleStyle != null) {
                msgs = ((InternalEObject) instanceRoleStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, null, msgs);
            }
            if (newInstanceRoleStyle != null) {
                msgs = ((InternalEObject) newInstanceRoleStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, null, msgs);
            }
            msgs = basicSetInstanceRoleStyle(newInstanceRoleStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, newInstanceRoleStyle, newInstanceRoleStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TLifelineStyle getLifelineStyle() {
        return lifelineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetLifelineStyle(TLifelineStyle newLifelineStyle, NotificationChain msgs) {
        TLifelineStyle oldLifelineStyle = lifelineStyle;
        lifelineStyle = newLifelineStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE, oldLifelineStyle, newLifelineStyle);
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
    public void setLifelineStyle(TLifelineStyle newLifelineStyle) {
        if (newLifelineStyle != lifelineStyle) {
            NotificationChain msgs = null;
            if (lifelineStyle != null) {
                msgs = ((InternalEObject) lifelineStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE, null, msgs);
            }
            if (newLifelineStyle != null) {
                msgs = ((InternalEObject) newLifelineStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE, null, msgs);
            }
            msgs = basicSetLifelineStyle(newLifelineStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE, newLifelineStyle, newLifelineStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NodeStyleDescription getEndOfLifeStyle() {
        return endOfLifeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEndOfLifeStyle(NodeStyleDescription newEndOfLifeStyle, NotificationChain msgs) {
        NodeStyleDescription oldEndOfLifeStyle = endOfLifeStyle;
        endOfLifeStyle = newEndOfLifeStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, oldEndOfLifeStyle, newEndOfLifeStyle);
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
    public void setEndOfLifeStyle(NodeStyleDescription newEndOfLifeStyle) {
        if (newEndOfLifeStyle != endOfLifeStyle) {
            NotificationChain msgs = null;
            if (endOfLifeStyle != null) {
                msgs = ((InternalEObject) endOfLifeStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, null, msgs);
            }
            if (newEndOfLifeStyle != null) {
                msgs = ((InternalEObject) newEndOfLifeStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, null, msgs);
            }
            msgs = basicSetEndOfLifeStyle(newEndOfLifeStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, newEndOfLifeStyle, newEndOfLifeStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TConditionalLifelineStyle> getConditionalLifeLineStyles() {
        if (conditionalLifeLineStyles == null) {
            conditionalLifeLineStyles = new EObjectContainmentEList<TConditionalLifelineStyle>(TConditionalLifelineStyle.class, this, TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES);
        }
        return conditionalLifeLineStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
            return ((InternalEList<?>) getExecutionMappings()).basicRemove(otherEnd, msgs);
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
            return basicSetInstanceRoleStyle(null, msgs);
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
            return basicSetLifelineStyle(null, msgs);
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
            return basicSetEndOfLifeStyle(null, msgs);
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
            return ((InternalEList<?>) getConditionalLifeLineStyles()).basicRemove(otherEnd, msgs);
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
        case TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION:
            return getEolVisibleExpression();
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
            return getExecutionMappings();
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
            return getInstanceRoleStyle();
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
            return getLifelineStyle();
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
            return getEndOfLifeStyle();
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
            return getConditionalLifeLineStyles();
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
        case TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION:
            setEolVisibleExpression((String) newValue);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
            getExecutionMappings().clear();
            getExecutionMappings().addAll((Collection<? extends TExecutionMapping>) newValue);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
            setInstanceRoleStyle((NodeStyleDescription) newValue);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
            setLifelineStyle((TLifelineStyle) newValue);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
            setEndOfLifeStyle((NodeStyleDescription) newValue);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
            getConditionalLifeLineStyles().clear();
            getConditionalLifeLineStyles().addAll((Collection<? extends TConditionalLifelineStyle>) newValue);
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
        case TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION:
            setEolVisibleExpression(TLifelineMappingImpl.EOL_VISIBLE_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
            getExecutionMappings().clear();
            return;
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
            setInstanceRoleStyle((NodeStyleDescription) null);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
            setLifelineStyle((TLifelineStyle) null);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
            setEndOfLifeStyle((NodeStyleDescription) null);
            return;
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
            getConditionalLifeLineStyles().clear();
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
        case TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION:
            return TLifelineMappingImpl.EOL_VISIBLE_EXPRESSION_EDEFAULT == null ? eolVisibleExpression != null : !TLifelineMappingImpl.EOL_VISIBLE_EXPRESSION_EDEFAULT.equals(eolVisibleExpression);
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
            return executionMappings != null && !executionMappings.isEmpty();
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
            return instanceRoleStyle != null;
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
            return lifelineStyle != null;
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
            return endOfLifeStyle != null;
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
            return conditionalLifeLineStyles != null && !conditionalLifeLineStyles.isEmpty();
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
        result.append(" (eolVisibleExpression: "); //$NON-NLS-1$
        result.append(eolVisibleExpression);
        result.append(')');
        return result.toString();
    }

} // TLifelineMappingImpl
