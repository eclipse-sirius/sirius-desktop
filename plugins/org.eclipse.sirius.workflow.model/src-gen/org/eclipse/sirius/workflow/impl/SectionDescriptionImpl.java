/**
 *  Copyright (c) 2018 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  Contributors:
 *     Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.workflow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;
import org.eclipse.sirius.workflow.ActivityDescription;
import org.eclipse.sirius.workflow.SectionDescription;
import org.eclipse.sirius.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Section Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl#getTitleExpression <em>Title Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl#getImagePath <em>Image Path</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl#getDescriptionExpression <em>Description
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SectionDescriptionImpl extends IdentifiedElementImpl implements SectionDescription {
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
    protected String documentation = SectionDescriptionImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected String titleExpression = SectionDescriptionImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getImagePath() <em>Image Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getImagePath()
     * @generated
     * @ordered
     */
    protected static final String IMAGE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImagePath() <em>Image Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getImagePath()
     * @generated
     * @ordered
     */
    protected String imagePath = SectionDescriptionImpl.IMAGE_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getDescriptionExpression() <em>Description Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescriptionExpression()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescriptionExpression() <em>Description Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescriptionExpression()
     * @generated
     * @ordered
     */
    protected String descriptionExpression = SectionDescriptionImpl.DESCRIPTION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getActivities() <em>Activities</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getActivities()
     * @generated
     * @ordered
     */
    protected EList<ActivityDescription> activities;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SectionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return WorkflowPackage.Literals.SECTION_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTitleExpression() {
        return titleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTitleExpression(String newTitleExpression) {
        String oldTitleExpression = titleExpression;
        titleExpression = newTitleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SECTION_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getImagePath() {
        return imagePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImagePath(String newImagePath) {
        String oldImagePath = imagePath;
        imagePath = newImagePath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SECTION_DESCRIPTION__IMAGE_PATH, oldImagePath, imagePath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDescriptionExpression() {
        return descriptionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescriptionExpression(String newDescriptionExpression) {
        String oldDescriptionExpression = descriptionExpression;
        descriptionExpression = newDescriptionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION, oldDescriptionExpression, descriptionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ActivityDescription> getActivities() {
        if (activities == null) {
            activities = new EObjectContainmentEList.Resolving<ActivityDescription>(ActivityDescription.class, this, WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES);
        }
        return activities;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES:
            return ((InternalEList<?>) getActivities()).basicRemove(otherEnd, msgs);
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
        case WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case WorkflowPackage.SECTION_DESCRIPTION__TITLE_EXPRESSION:
            return getTitleExpression();
        case WorkflowPackage.SECTION_DESCRIPTION__IMAGE_PATH:
            return getImagePath();
        case WorkflowPackage.SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION:
            return getDescriptionExpression();
        case WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES:
            return getActivities();
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
        case WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__IMAGE_PATH:
            setImagePath((String) newValue);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION:
            setDescriptionExpression((String) newValue);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES:
            getActivities().clear();
            getActivities().addAll((Collection<? extends ActivityDescription>) newValue);
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
        case WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION:
            setDocumentation(SectionDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression(SectionDescriptionImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__IMAGE_PATH:
            setImagePath(SectionDescriptionImpl.IMAGE_PATH_EDEFAULT);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION:
            setDescriptionExpression(SectionDescriptionImpl.DESCRIPTION_EXPRESSION_EDEFAULT);
            return;
        case WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES:
            getActivities().clear();
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
        case WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION:
            return SectionDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !SectionDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case WorkflowPackage.SECTION_DESCRIPTION__TITLE_EXPRESSION:
            return SectionDescriptionImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !SectionDescriptionImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case WorkflowPackage.SECTION_DESCRIPTION__IMAGE_PATH:
            return SectionDescriptionImpl.IMAGE_PATH_EDEFAULT == null ? imagePath != null : !SectionDescriptionImpl.IMAGE_PATH_EDEFAULT.equals(imagePath);
        case WorkflowPackage.SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION:
            return SectionDescriptionImpl.DESCRIPTION_EXPRESSION_EDEFAULT == null ? descriptionExpression != null
                    : !SectionDescriptionImpl.DESCRIPTION_EXPRESSION_EDEFAULT.equals(descriptionExpression);
        case WorkflowPackage.SECTION_DESCRIPTION__ACTIVITIES:
            return activities != null && !activities.isEmpty();
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
            case WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION:
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
                return WorkflowPackage.SECTION_DESCRIPTION__DOCUMENTATION;
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
        result.append(", titleExpression: "); //$NON-NLS-1$
        result.append(titleExpression);
        result.append(", imagePath: "); //$NON-NLS-1$
        result.append(imagePath);
        result.append(", descriptionExpression: "); //$NON-NLS-1$
        result.append(descriptionExpression);
        result.append(')');
        return result.toString();
    }

} // SectionDescriptionImpl
