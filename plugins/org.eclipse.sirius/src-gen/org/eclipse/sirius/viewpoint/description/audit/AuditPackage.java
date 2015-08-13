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
package org.eclipse.sirius.viewpoint.description.audit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.audit.AuditFactory
 * @model kind="package"
 * @generated
 */
public interface AuditPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "audit"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/audit/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "audit"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    AuditPackage eINSTANCE = org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.audit.impl.InformationSectionImpl
     * <em>Information Section</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.audit.impl.InformationSectionImpl
     * @see org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl#getInformationSection()
     * @generated
     */
    int INFORMATION_SECTION = 0;

    /**
     * The number of structural features of the '<em>Information Section</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INFORMATION_SECTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.audit.impl.TemplateInformationSectionImpl
     * <em>Template Information Section</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.audit.impl.TemplateInformationSectionImpl
     * @see org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl#getTemplateInformationSection()
     * @generated
     */
    int TEMPLATE_INFORMATION_SECTION = 1;

    /**
     * The feature id for the '<em><b>Template Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH = AuditPackage.INFORMATION_SECTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Template Information Section</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_INFORMATION_SECTION_FEATURE_COUNT = AuditPackage.INFORMATION_SECTION_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.audit.InformationSection
     * <em>Information Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Information Section</em>'.
     * @see org.eclipse.sirius.viewpoint.description.audit.InformationSection
     * @generated
     */
    EClass getInformationSection();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection
     * <em>Template Information Section</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Template Information Section</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection
     * @generated
     */
    EClass getTemplateInformationSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection#getTemplatePath
     * <em>Template Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection#getTemplatePath()
     * @see #getTemplateInformationSection()
     * @generated
     */
    EAttribute getTemplateInformationSection_TemplatePath();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    AuditFactory getAuditFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.audit.impl.InformationSectionImpl
         * <em>Information Section</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.audit.impl.InformationSectionImpl
         * @see org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl#getInformationSection()
         * @generated
         */
        EClass INFORMATION_SECTION = AuditPackage.eINSTANCE.getInformationSection();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.audit.impl.TemplateInformationSectionImpl
         * <em>Template Information Section</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.audit.impl.TemplateInformationSectionImpl
         * @see org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl#getTemplateInformationSection()
         * @generated
         */
        EClass TEMPLATE_INFORMATION_SECTION = AuditPackage.eINSTANCE.getTemplateInformationSection();

        /**
         * The meta object literal for the '<em><b>Template Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE_INFORMATION_SECTION__TEMPLATE_PATH = AuditPackage.eINSTANCE.getTemplateInformationSection_TemplatePath();

    }

} // AuditPackage
