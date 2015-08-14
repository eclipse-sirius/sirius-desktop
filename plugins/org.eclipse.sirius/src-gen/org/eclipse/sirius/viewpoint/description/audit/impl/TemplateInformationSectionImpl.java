/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.audit.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Template Information Section</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.audit.impl.TemplateInformationSectionImpl#getTemplatePath
 * <em>Template Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateInformationSectionImpl extends InformationSectionImpl implements TemplateInformationSection {
    /**
     * The default value of the '{@link #getTemplatePath()
     * <em>Template Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePath()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplatePath()
     * <em>Template Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePath()
     * @generated
     * @ordered
     */
    protected String templatePath = TemplateInformationSectionImpl.TEMPLATE_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TemplateInformationSectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AuditPackage.Literals.TEMPLATE_INFORMATION_SECTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTemplatePath(String newTemplatePath) {
        String oldTemplatePath = templatePath;
        templatePath = newTemplatePath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, AuditPackage.TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH, oldTemplatePath, templatePath));
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
        case AuditPackage.TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH:
            return getTemplatePath();
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
        case AuditPackage.TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH:
            setTemplatePath((String) newValue);
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
        case AuditPackage.TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH:
            setTemplatePath(TemplateInformationSectionImpl.TEMPLATE_PATH_EDEFAULT);
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
        case AuditPackage.TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH:
            return TemplateInformationSectionImpl.TEMPLATE_PATH_EDEFAULT == null ? templatePath != null : !TemplateInformationSectionImpl.TEMPLATE_PATH_EDEFAULT.equals(templatePath);
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
        result.append(" (templatePath: "); //$NON-NLS-1$
        result.append(templatePath);
        result.append(')');
        return result.toString();
    }

} // TemplateInformationSectionImpl
