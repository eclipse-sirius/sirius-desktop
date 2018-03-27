/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.AbstractGroupDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Group Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getSemanticCandidateExpression
 * <em>Semantic Candidate Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getControls <em>Controls</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getValidationSet <em>Validation
 * Set</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getFilterControlsFromExtendedGroupExpression
 * <em>Filter Controls From Extended Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getFilterValidationRulesFromExtendedGroupExpression
 * <em>Filter Validation Rules From Extended Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getFilterConditionalStylesFromExtendedGroupExpression
 * <em>Filter Conditional Styles From Extended Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractGroupDescriptionImpl extends IdentifiedElementImpl implements AbstractGroupDescription {
    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = AbstractGroupDescriptionImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = AbstractGroupDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDomainClass() <em>Domain Class</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClass() <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected String domainClass = AbstractGroupDescriptionImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getSemanticCandidateExpression() <em>Semantic Candidate Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticCandidateExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticCandidateExpression() <em>Semantic Candidate Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticCandidateExpression()
     * @generated
     * @ordered
     */
    protected String semanticCandidateExpression = AbstractGroupDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression() <em>Precondition Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionExpression() <em>Precondition Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = AbstractGroupDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getControls() <em>Controls</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getControls()
     * @generated
     * @ordered
     */
    protected EList<ControlDescription> controls;

    /**
     * The cached value of the '{@link #getValidationSet() <em>Validation Set</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getValidationSet()
     * @generated
     * @ordered
     */
    protected GroupValidationSetDescription validationSet;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected GroupStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<GroupConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected GroupDescription extends_;

    /**
     * The default value of the '{@link #getFilterControlsFromExtendedGroupExpression() <em>Filter Controls From
     * Extended Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterControlsFromExtendedGroupExpression() <em>Filter Controls From Extended
     * Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterControlsFromExtendedGroupExpression = AbstractGroupDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterValidationRulesFromExtendedGroupExpression() <em>Filter Validation
     * Rules From Extended Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterValidationRulesFromExtendedGroupExpression() <em>Filter Validation
     * Rules From Extended Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterValidationRulesFromExtendedGroupExpression = AbstractGroupDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedGroupExpression() <em>Filter Conditional
     * Styles From Extended Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedGroupExpression() <em>Filter Conditional
     * Styles From Extended Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedGroupExpression = AbstractGroupDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getActions()
     * @generated
     * @ordered
     */
    protected EList<ToolbarAction> actions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractGroupDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSemanticCandidateExpression() {
        return semanticCandidateExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSemanticCandidateExpression(String newSemanticCandidateExpression) {
        String oldSemanticCandidateExpression = semanticCandidateExpression;
        semanticCandidateExpression = newSemanticCandidateExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION, oldSemanticCandidateExpression,
                    semanticCandidateExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPreconditionExpression(String newPreconditionExpression) {
        String oldPreconditionExpression = preconditionExpression;
        preconditionExpression = newPreconditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ControlDescription> getControls() {
        if (controls == null) {
            controls = new EObjectContainmentEList<ControlDescription>(ControlDescription.class, this, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS);
        }
        return controls;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupValidationSetDescription getValidationSet() {
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetValidationSet(GroupValidationSetDescription newValidationSet, NotificationChain msgs) {
        GroupValidationSetDescription oldValidationSet = validationSet;
        validationSet = newValidationSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET, oldValidationSet, newValidationSet);
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
    public void setValidationSet(GroupValidationSetDescription newValidationSet) {
        if (newValidationSet != validationSet) {
            NotificationChain msgs = null;
            if (validationSet != null) {
                msgs = ((InternalEObject) validationSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            if (newValidationSet != null) {
                msgs = ((InternalEObject) newValidationSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            msgs = basicSetValidationSet(newValidationSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET, newValidationSet, newValidationSet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(GroupStyle newStyle, NotificationChain msgs) {
        GroupStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(GroupStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<GroupConditionalStyle>(GroupConditionalStyle.class, this, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (GroupDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS, oldExtends, extends_));
                }
            }
        }
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public GroupDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(GroupDescription newExtends) {
        GroupDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterControlsFromExtendedGroupExpression() {
        return filterControlsFromExtendedGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterControlsFromExtendedGroupExpression(String newFilterControlsFromExtendedGroupExpression) {
        String oldFilterControlsFromExtendedGroupExpression = filterControlsFromExtendedGroupExpression;
        filterControlsFromExtendedGroupExpression = newFilterControlsFromExtendedGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION,
                    oldFilterControlsFromExtendedGroupExpression, filterControlsFromExtendedGroupExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterValidationRulesFromExtendedGroupExpression() {
        return filterValidationRulesFromExtendedGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterValidationRulesFromExtendedGroupExpression(String newFilterValidationRulesFromExtendedGroupExpression) {
        String oldFilterValidationRulesFromExtendedGroupExpression = filterValidationRulesFromExtendedGroupExpression;
        filterValidationRulesFromExtendedGroupExpression = newFilterValidationRulesFromExtendedGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION,
                    oldFilterValidationRulesFromExtendedGroupExpression, filterValidationRulesFromExtendedGroupExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedGroupExpression() {
        return filterConditionalStylesFromExtendedGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedGroupExpression(String newFilterConditionalStylesFromExtendedGroupExpression) {
        String oldFilterConditionalStylesFromExtendedGroupExpression = filterConditionalStylesFromExtendedGroupExpression;
        filterConditionalStylesFromExtendedGroupExpression = newFilterConditionalStylesFromExtendedGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedGroupExpression, filterConditionalStylesFromExtendedGroupExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolbarAction> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<ToolbarAction>(ToolbarAction.class, this, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
            return ((InternalEList<?>) getControls()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
            return basicSetValidationSet(null, msgs);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
            return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return getSemanticCandidateExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
            return getControls();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
            return getValidationSet();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION:
            return getFilterControlsFromExtendedGroupExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION:
            return getFilterValidationRulesFromExtendedGroupExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION:
            return getFilterConditionalStylesFromExtendedGroupExpression();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
            return getActions();
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
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
            getControls().clear();
            getControls().addAll((Collection<? extends ControlDescription>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
            setValidationSet((GroupValidationSetDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
            setStyle((GroupStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends GroupConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS:
            setExtends((GroupDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterControlsFromExtendedGroupExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterValidationRulesFromExtendedGroupExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterConditionalStylesFromExtendedGroupExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends ToolbarAction>) newValue);
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
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
            setDocumentation(AbstractGroupDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(AbstractGroupDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(AbstractGroupDescriptionImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression(AbstractGroupDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(AbstractGroupDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
            getControls().clear();
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
            setValidationSet((GroupValidationSetDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
            setStyle((GroupStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS:
            setExtends((GroupDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterControlsFromExtendedGroupExpression(AbstractGroupDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterValidationRulesFromExtendedGroupExpression(AbstractGroupDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION:
            setFilterConditionalStylesFromExtendedGroupExpression(AbstractGroupDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
            getActions().clear();
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
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
            return AbstractGroupDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !AbstractGroupDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION:
            return AbstractGroupDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !AbstractGroupDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS:
            return AbstractGroupDescriptionImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !AbstractGroupDescriptionImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return AbstractGroupDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT == null ? semanticCandidateExpression != null
                    : !AbstractGroupDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT.equals(semanticCandidateExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION:
            return AbstractGroupDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null
                    : !AbstractGroupDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
            return controls != null && !controls.isEmpty();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
            return validationSet != null;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION:
            return AbstractGroupDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT == null ? filterControlsFromExtendedGroupExpression != null
                    : !AbstractGroupDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT.equals(filterControlsFromExtendedGroupExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION:
            return AbstractGroupDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT == null ? filterValidationRulesFromExtendedGroupExpression != null
                    : !AbstractGroupDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT.equals(filterValidationRulesFromExtendedGroupExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION:
            return AbstractGroupDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedGroupExpression != null
                    : !AbstractGroupDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedGroupExpression);
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
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
            case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
                return DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
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
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION;
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
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", semanticCandidateExpression: "); //$NON-NLS-1$
        result.append(semanticCandidateExpression);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", filterControlsFromExtendedGroupExpression: "); //$NON-NLS-1$
        result.append(filterControlsFromExtendedGroupExpression);
        result.append(", filterValidationRulesFromExtendedGroupExpression: "); //$NON-NLS-1$
        result.append(filterValidationRulesFromExtendedGroupExpression);
        result.append(", filterConditionalStylesFromExtendedGroupExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedGroupExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractGroupDescriptionImpl
