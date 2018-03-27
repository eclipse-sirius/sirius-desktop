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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.AbstractPageDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Page Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getSemanticCandidateExpression <em>Semantic
 * Candidate Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getValidationSet <em>Validation
 * Set</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getFilterGroupsFromExtendedPageExpression
 * <em>Filter Groups From Extended Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#getFilterValidationRulesFromExtendedPageExpression
 * <em>Filter Validation Rules From Extended Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl#isIndented <em>Indented</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractPageDescriptionImpl extends IdentifiedElementImpl implements AbstractPageDescription {
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
    protected String documentation = AbstractPageDescriptionImpl.DOCUMENTATION_EDEFAULT;

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
    protected String labelExpression = AbstractPageDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

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
    protected String domainClass = AbstractPageDescriptionImpl.DOMAIN_CLASS_EDEFAULT;

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
    protected String semanticCandidateExpression = AbstractPageDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT;

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
    protected String preconditionExpression = AbstractPageDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList<GroupDescription> groups;

    /**
     * The cached value of the '{@link #getValidationSet() <em>Validation Set</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getValidationSet()
     * @generated
     * @ordered
     */
    protected PageValidationSetDescription validationSet;

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
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected PageDescription extends_;

    /**
     * The default value of the '{@link #getFilterGroupsFromExtendedPageExpression() <em>Filter Groups From Extended
     * Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterGroupsFromExtendedPageExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterGroupsFromExtendedPageExpression() <em>Filter Groups From Extended Page
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterGroupsFromExtendedPageExpression()
     * @generated
     * @ordered
     */
    protected String filterGroupsFromExtendedPageExpression = AbstractPageDescriptionImpl.FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterValidationRulesFromExtendedPageExpression() <em>Filter Validation
     * Rules From Extended Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromExtendedPageExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterValidationRulesFromExtendedPageExpression() <em>Filter Validation Rules
     * From Extended Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromExtendedPageExpression()
     * @generated
     * @ordered
     */
    protected String filterValidationRulesFromExtendedPageExpression = AbstractPageDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isIndented() <em>Indented</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isIndented()
     * @generated
     * @ordered
     */
    protected static final boolean INDENTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIndented() <em>Indented</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isIndented()
     * @generated
     * @ordered
     */
    protected boolean indented = AbstractPageDescriptionImpl.INDENTED_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractPageDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION, oldSemanticCandidateExpression,
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupDescription> getGroups() {
        if (groups == null) {
            groups = new EObjectResolvingEList<GroupDescription>(GroupDescription.class, this, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageValidationSetDescription getValidationSet() {
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetValidationSet(PageValidationSetDescription newValidationSet, NotificationChain msgs) {
        PageValidationSetDescription oldValidationSet = validationSet;
        validationSet = newValidationSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET, oldValidationSet, newValidationSet);
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
    public void setValidationSet(PageValidationSetDescription newValidationSet) {
        if (newValidationSet != validationSet) {
            NotificationChain msgs = null;
            if (validationSet != null) {
                msgs = ((InternalEObject) validationSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            if (newValidationSet != null) {
                msgs = ((InternalEObject) newValidationSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            msgs = basicSetValidationSet(newValidationSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET, newValidationSet, newValidationSet));
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
            actions = new EObjectContainmentEList<ToolbarAction>(ToolbarAction.class, this, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (PageDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public PageDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(PageDescription newExtends) {
        PageDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterGroupsFromExtendedPageExpression() {
        return filterGroupsFromExtendedPageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterGroupsFromExtendedPageExpression(String newFilterGroupsFromExtendedPageExpression) {
        String oldFilterGroupsFromExtendedPageExpression = filterGroupsFromExtendedPageExpression;
        filterGroupsFromExtendedPageExpression = newFilterGroupsFromExtendedPageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION, oldFilterGroupsFromExtendedPageExpression,
                    filterGroupsFromExtendedPageExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterValidationRulesFromExtendedPageExpression() {
        return filterValidationRulesFromExtendedPageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterValidationRulesFromExtendedPageExpression(String newFilterValidationRulesFromExtendedPageExpression) {
        String oldFilterValidationRulesFromExtendedPageExpression = filterValidationRulesFromExtendedPageExpression;
        filterValidationRulesFromExtendedPageExpression = newFilterValidationRulesFromExtendedPageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION,
                    oldFilterValidationRulesFromExtendedPageExpression, filterValidationRulesFromExtendedPageExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isIndented() {
        return indented;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIndented(boolean newIndented) {
        boolean oldIndented = indented;
        indented = newIndented;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED, oldIndented, indented));
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
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET:
            return basicSetValidationSet(null, msgs);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS:
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
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return getSemanticCandidateExpression();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS:
            return getGroups();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET:
            return getValidationSet();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION:
            return getFilterGroupsFromExtendedPageExpression();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION:
            return getFilterValidationRulesFromExtendedPageExpression();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED:
            return isIndented();
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
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS:
            getGroups().clear();
            getGroups().addAll((Collection<? extends GroupDescription>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET:
            setValidationSet((PageValidationSetDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends ToolbarAction>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS:
            setExtends((PageDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION:
            setFilterGroupsFromExtendedPageExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION:
            setFilterValidationRulesFromExtendedPageExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED:
            setIndented((Boolean) newValue);
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
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION:
            setDocumentation(AbstractPageDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(AbstractPageDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(AbstractPageDescriptionImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression(AbstractPageDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(AbstractPageDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS:
            getGroups().clear();
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET:
            setValidationSet((PageValidationSetDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS:
            setExtends((PageDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION:
            setFilterGroupsFromExtendedPageExpression(AbstractPageDescriptionImpl.FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION:
            setFilterValidationRulesFromExtendedPageExpression(AbstractPageDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED:
            setIndented(AbstractPageDescriptionImpl.INDENTED_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION:
            return AbstractPageDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !AbstractPageDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION:
            return AbstractPageDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !AbstractPageDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS:
            return AbstractPageDescriptionImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !AbstractPageDescriptionImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return AbstractPageDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT == null ? semanticCandidateExpression != null
                    : !AbstractPageDescriptionImpl.SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT.equals(semanticCandidateExpression);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return AbstractPageDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null
                    : !AbstractPageDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS:
            return groups != null && !groups.isEmpty();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET:
            return validationSet != null;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION:
            return AbstractPageDescriptionImpl.FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT == null ? filterGroupsFromExtendedPageExpression != null
                    : !AbstractPageDescriptionImpl.FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT.equals(filterGroupsFromExtendedPageExpression);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION:
            return AbstractPageDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT == null ? filterValidationRulesFromExtendedPageExpression != null
                    : !AbstractPageDescriptionImpl.FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION_EDEFAULT.equals(filterValidationRulesFromExtendedPageExpression);
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED:
            return indented != AbstractPageDescriptionImpl.INDENTED_EDEFAULT;
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
            case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION:
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
                return PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION;
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
        result.append(", filterGroupsFromExtendedPageExpression: "); //$NON-NLS-1$
        result.append(filterGroupsFromExtendedPageExpression);
        result.append(", filterValidationRulesFromExtendedPageExpression: "); //$NON-NLS-1$
        result.append(filterValidationRulesFromExtendedPageExpression);
        result.append(", indented: "); //$NON-NLS-1$
        result.append(indented);
        result.append(')');
        return result.toString();
    }

} // AbstractPageDescriptionImpl
