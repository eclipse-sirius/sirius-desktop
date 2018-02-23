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
package org.eclipse.sirius.workflow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.workflow.WorkflowFactory
 * @model kind="package"
 * @generated
 */
public interface WorkflowPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "workflow"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/workflow/1.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "workflow"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    WorkflowPackage eINSTANCE = org.eclipse.sirius.workflow.impl.WorkflowPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl
     * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getWorkflowDescription()
     * @generated
     */
    int WORKFLOW_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKFLOW_DESCRIPTION__NAME = DescriptionPackage.EXTENSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKFLOW_DESCRIPTION__LABEL = DescriptionPackage.EXTENSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKFLOW_DESCRIPTION__DOCUMENTATION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKFLOW_DESCRIPTION__PAGES = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WORKFLOW_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.workflow.impl.PageDescriptionImpl <em>Page
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.workflow.impl.PageDescriptionImpl
     * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getPageDescription()
     * @generated
     */
    int PAGE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Image Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__IMAGE_PATH = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Description Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DESCRIPTION_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__SECTIONS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Page Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl <em>Section
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.workflow.impl.SectionDescriptionImpl
     * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getSectionDescription()
     * @generated
     */
    int SECTION_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Image Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__IMAGE_PATH = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Description Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Activities</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION__ACTIVITIES = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Section Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECTION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.workflow.impl.ActivityDescriptionImpl <em>Activity
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.workflow.impl.ActivityDescriptionImpl
     * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getActivityDescription()
     * @generated
     */
    int ACTIVITY_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Image Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__IMAGE_PATH = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION__OPERATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Activity Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACTIVITY_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.workflow.WorkflowDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.workflow.WorkflowDescription
     * @generated
     */
    EClass getWorkflowDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.workflow.WorkflowDescription#getPages <em>Pages</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Pages</em>'.
     * @see org.eclipse.sirius.workflow.WorkflowDescription#getPages()
     * @see #getWorkflowDescription()
     * @generated
     */
    EReference getWorkflowDescription_Pages();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.workflow.PageDescription <em>Page
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Page Description</em>'.
     * @see org.eclipse.sirius.workflow.PageDescription
     * @generated
     */
    EClass getPageDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.workflow.PageDescription#getTitleExpression
     * <em>Title Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.workflow.PageDescription#getTitleExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.workflow.PageDescription#getImagePath
     * <em>Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Path</em>'.
     * @see org.eclipse.sirius.workflow.PageDescription#getImagePath()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_ImagePath();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.workflow.PageDescription#getDescriptionExpression <em>Description Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description Expression</em>'.
     * @see org.eclipse.sirius.workflow.PageDescription#getDescriptionExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_DescriptionExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.workflow.PageDescription#getSections <em>Sections</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sections</em>'.
     * @see org.eclipse.sirius.workflow.PageDescription#getSections()
     * @see #getPageDescription()
     * @generated
     */
    EReference getPageDescription_Sections();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.workflow.SectionDescription <em>Section
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Section Description</em>'.
     * @see org.eclipse.sirius.workflow.SectionDescription
     * @generated
     */
    EClass getSectionDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.workflow.SectionDescription#getTitleExpression <em>Title Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.workflow.SectionDescription#getTitleExpression()
     * @see #getSectionDescription()
     * @generated
     */
    EAttribute getSectionDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.workflow.SectionDescription#getImagePath
     * <em>Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Path</em>'.
     * @see org.eclipse.sirius.workflow.SectionDescription#getImagePath()
     * @see #getSectionDescription()
     * @generated
     */
    EAttribute getSectionDescription_ImagePath();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.workflow.SectionDescription#getDescriptionExpression <em>Description
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description Expression</em>'.
     * @see org.eclipse.sirius.workflow.SectionDescription#getDescriptionExpression()
     * @see #getSectionDescription()
     * @generated
     */
    EAttribute getSectionDescription_DescriptionExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.workflow.SectionDescription#getActivities <em>Activities</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Activities</em>'.
     * @see org.eclipse.sirius.workflow.SectionDescription#getActivities()
     * @see #getSectionDescription()
     * @generated
     */
    EReference getSectionDescription_Activities();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.workflow.ActivityDescription <em>Activity
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Activity Description</em>'.
     * @see org.eclipse.sirius.workflow.ActivityDescription
     * @generated
     */
    EClass getActivityDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.workflow.ActivityDescription#getLabelExpression <em>Label Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.workflow.ActivityDescription#getLabelExpression()
     * @see #getActivityDescription()
     * @generated
     */
    EAttribute getActivityDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.workflow.ActivityDescription#getImagePath
     * <em>Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Path</em>'.
     * @see org.eclipse.sirius.workflow.ActivityDescription#getImagePath()
     * @see #getActivityDescription()
     * @generated
     */
    EAttribute getActivityDescription_ImagePath();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.workflow.ActivityDescription#getOperation <em>Operation</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Operation</em>'.
     * @see org.eclipse.sirius.workflow.ActivityDescription#getOperation()
     * @see #getActivityDescription()
     * @generated
     */
    EReference getActivityDescription_Operation();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    WorkflowFactory getWorkflowFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
         * The meta object literal for the '{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl
         * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl
         * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getWorkflowDescription()
         * @generated
         */
        EClass WORKFLOW_DESCRIPTION = WorkflowPackage.eINSTANCE.getWorkflowDescription();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WORKFLOW_DESCRIPTION__PAGES = WorkflowPackage.eINSTANCE.getWorkflowDescription_Pages();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.workflow.impl.PageDescriptionImpl <em>Page
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.workflow.impl.PageDescriptionImpl
         * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getPageDescription()
         * @generated
         */
        EClass PAGE_DESCRIPTION = WorkflowPackage.eINSTANCE.getPageDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__TITLE_EXPRESSION = WorkflowPackage.eINSTANCE.getPageDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__IMAGE_PATH = WorkflowPackage.eINSTANCE.getPageDescription_ImagePath();

        /**
         * The meta object literal for the '<em><b>Description Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__DESCRIPTION_EXPRESSION = WorkflowPackage.eINSTANCE.getPageDescription_DescriptionExpression();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_DESCRIPTION__SECTIONS = WorkflowPackage.eINSTANCE.getPageDescription_Sections();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.workflow.impl.SectionDescriptionImpl <em>Section
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.workflow.impl.SectionDescriptionImpl
         * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getSectionDescription()
         * @generated
         */
        EClass SECTION_DESCRIPTION = WorkflowPackage.eINSTANCE.getSectionDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SECTION_DESCRIPTION__TITLE_EXPRESSION = WorkflowPackage.eINSTANCE.getSectionDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute SECTION_DESCRIPTION__IMAGE_PATH = WorkflowPackage.eINSTANCE.getSectionDescription_ImagePath();

        /**
         * The meta object literal for the '<em><b>Description Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SECTION_DESCRIPTION__DESCRIPTION_EXPRESSION = WorkflowPackage.eINSTANCE.getSectionDescription_DescriptionExpression();

        /**
         * The meta object literal for the '<em><b>Activities</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SECTION_DESCRIPTION__ACTIVITIES = WorkflowPackage.eINSTANCE.getSectionDescription_Activities();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.workflow.impl.ActivityDescriptionImpl <em>Activity
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.workflow.impl.ActivityDescriptionImpl
         * @see org.eclipse.sirius.workflow.impl.WorkflowPackageImpl#getActivityDescription()
         * @generated
         */
        EClass ACTIVITY_DESCRIPTION = WorkflowPackage.eINSTANCE.getActivityDescription();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ACTIVITY_DESCRIPTION__LABEL_EXPRESSION = WorkflowPackage.eINSTANCE.getActivityDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ACTIVITY_DESCRIPTION__IMAGE_PATH = WorkflowPackage.eINSTANCE.getActivityDescription_ImagePath();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ACTIVITY_DESCRIPTION__OPERATION = WorkflowPackage.eINSTANCE.getActivityDescription_Operation();

    }

} // WorkflowPackage
