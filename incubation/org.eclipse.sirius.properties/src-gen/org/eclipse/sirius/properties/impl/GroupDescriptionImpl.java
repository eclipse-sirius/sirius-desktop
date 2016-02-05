/**
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Group Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl#getSemanticCandidateExpression
 * <em>Semantic Candidate Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl#getContainer
 * <em>Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupDescriptionImpl extends MinimalEObjectImpl.Container implements GroupDescription {
    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String identifier = IDENTIFIER_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = LABEL_EXPRESSION_EDEFAULT;

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
    protected String domainClass = DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getSemanticCandidateExpression()
     * <em>Semantic Candidate Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidateExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticCandidateExpression()
     * <em>Semantic Candidate Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidateExpression()
     * @generated
     * @ordered
     */
    protected String semanticCandidateExpression = SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getContainer() <em>Container</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getContainer()
     * @generated
     * @ordered
     */
    protected ContainerDescription container;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GroupDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.GROUP_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = identifier;
        identifier = newIdentifier;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER, oldIdentifier, identifier));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSemanticCandidateExpression() {
        return semanticCandidateExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSemanticCandidateExpression(String newSemanticCandidateExpression) {
        String oldSemanticCandidateExpression = semanticCandidateExpression;
        semanticCandidateExpression = newSemanticCandidateExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION, oldSemanticCandidateExpression, semanticCandidateExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerDescription getContainer() {
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainer(ContainerDescription newContainer, NotificationChain msgs) {
        ContainerDescription oldContainer = container;
        container = newContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__CONTAINER, oldContainer, newContainer);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setContainer(ContainerDescription newContainer) {
        if (newContainer != container) {
            NotificationChain msgs = null;
            if (container != null)
                msgs = ((InternalEObject) container).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.GROUP_DESCRIPTION__CONTAINER, null, msgs);
            if (newContainer != null)
                msgs = ((InternalEObject) newContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.GROUP_DESCRIPTION__CONTAINER, null, msgs);
            msgs = basicSetContainer(newContainer, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_DESCRIPTION__CONTAINER, newContainer, newContainer));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.GROUP_DESCRIPTION__CONTAINER:
            return basicSetContainer(null, msgs);
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
        case PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER:
            return getIdentifier();
        case PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return getSemanticCandidateExpression();
        case PropertiesPackage.GROUP_DESCRIPTION__CONTAINER:
            return getContainer();
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
        case PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER:
            setIdentifier((String) newValue);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__CONTAINER:
            setContainer((ContainerDescription) newValue);
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
        case PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER:
            setIdentifier(IDENTIFIER_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(DOMAIN_CLASS_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            setSemanticCandidateExpression(SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_DESCRIPTION__CONTAINER:
            setContainer((ContainerDescription) null);
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
        case PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER:
            return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
        case PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION:
            return LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS:
            return DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
            return SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT == null ? semanticCandidateExpression != null : !SEMANTIC_CANDIDATE_EXPRESSION_EDEFAULT.equals(semanticCandidateExpression);
        case PropertiesPackage.GROUP_DESCRIPTION__CONTAINER:
            return container != null;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (identifier: ");
        result.append(identifier);
        result.append(", labelExpression: ");
        result.append(labelExpression);
        result.append(", domainClass: ");
        result.append(domainClass);
        result.append(", semanticCandidateExpression: ");
        result.append(semanticCandidateExpression);
        result.append(')');
        return result.toString();
    }

} // GroupDescriptionImpl
