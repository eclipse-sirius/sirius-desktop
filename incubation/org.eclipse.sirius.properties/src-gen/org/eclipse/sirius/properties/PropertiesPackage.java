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
package org.eclipse.sirius.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.properties.PropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "properties";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "properties";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    PropertiesPackage eINSTANCE = org.eclipse.sirius.properties.impl.PropertiesPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl <em>View Extension Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
     * @generated
     */
    int VIEW_EXTENSION_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = DescriptionPackage.EXTENSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Metamodels</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__METAMODELS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__PAGES = DescriptionPackage.EXTENSION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Default Page</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__GROUPS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>View Extension Description</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
     * @generated
     */
    int PAGE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__GROUPS = 4;

    /**
     * The number of structural features of the '<em>Page Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
     * @generated
     */
    int GROUP_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONTAINER = 4;

    /**
     * The number of structural features of the '<em>Group Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl <em>Container Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
     * @generated
     */
    int CONTAINER_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__WIDGETS = 1;

    /**
     * The number of structural features of the '<em>Container Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
     * @generated
     */
    int WIDGET_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION = 2;

    /**
     * The number of structural features of the '<em>Widget Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
     * @generated
     */
    int TEXT_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__IDENTIFIER = WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__LABEL_EXPRESSION = WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION = WIDGET_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__VALUE_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__INITIAL_OPERATION = WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Text Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION_FEATURE_COUNT = WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl <em>Checkbox Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
     * @generated
     */
    int CHECKBOX_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__IDENTIFIER = WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__LABEL_EXPRESSION = WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION = WIDGET_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__INITIAL_OPERATION = WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Checkbox Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION_FEATURE_COUNT = WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl <em>Select Description</em>}' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
     * @generated
     */
    int SELECT_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__IDENTIFIER = WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__LABEL_EXPRESSION = WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION = WIDGET_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__VALUE_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__INITIAL_OPERATION = WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__MULTIPLE = WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Select Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION_FEATURE_COUNT = WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ViewExtensionDescription <em>View Extension Description</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>View Extension Description</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    EClass getViewExtensionDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_Identifier();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels <em>Metamodels</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Metamodels</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Metamodels();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getPages <em>Pages</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pages</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getPages()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Pages();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage <em>Default Page</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Page</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_DefaultPage();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getPreconditionExpression <em>Precondition Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getPreconditionExpression()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_PreconditionExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_LabelExpression();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getGroups()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Groups();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.PageDescription
     * <em>Page Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Page Description</em>'.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    EClass getPageDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getIdentifier()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getLabelExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getDomainClass <em>Domain Class</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getDomainClass()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression <em>Semantic Candidate Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.properties.PageDescription#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getGroups()
     * @see #getPageDescription()
     * @generated
     */
    EReference getPageDescription_Groups();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GroupDescription
     * <em>Group Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Group Description</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    EClass getGroupDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getIdentifier()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getLabelExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getDomainClass <em>Domain Class</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getDomainClass()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression <em>Semantic Candidate Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.properties.GroupDescription#getContainer <em>Container</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Container</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getContainer()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_Container();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ContainerDescription <em>Container Description</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Container Description</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    EClass getContainerDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ContainerDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getIdentifier()
     * @see #getContainerDescription()
     * @generated
     */
    EAttribute getContainerDescription_Identifier();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ContainerDescription#getWidgets <em>Widgets</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Widgets</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getWidgets()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_Widgets();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.WidgetDescription
     * <em>Widget Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Widget Description</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    EClass getWidgetDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getIdentifier()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getLabelExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetDescription#getDomainCandidatesExpression <em>Domain Candidates Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Domain Candidates Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getDomainCandidatesExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_DomainCandidatesExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.TextDescription
     * <em>Text Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Text Description</em>'.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    EClass getTextDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getValueExpression()
     * @see #getTextDescription()
     * @generated
     */
    EAttribute getTextDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.TextDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getInitialOperation()
     * @see #getTextDescription()
     * @generated
     */
    EReference getTextDescription_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CheckboxDescription <em>Checkbox Description</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Checkbox Description</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription
     * @generated
     */
    EClass getCheckboxDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getValueExpression()
     * @see #getCheckboxDescription()
     * @generated
     */
    EAttribute getCheckboxDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getInitialOperation()
     * @see #getCheckboxDescription()
     * @generated
     */
    EReference getCheckboxDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.SelectDescription
     * <em>Select Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Select Description</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription
     * @generated
     */
    EClass getSelectDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SelectDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getValueExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SelectDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getInitialOperation()
     * @see #getSelectDescription()
     * @generated
     */
    EReference getSelectDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.SelectDescription#isMultiple <em>Multiple</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#isMultiple()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_Multiple();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression <em>Candidates Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Candidates Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression <em>Candidate Display Expression</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the attribute '<em>Candidate Display Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_CandidateDisplayExpression();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesFactory getPropertiesFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl <em>View Extension Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
         * @generated
         */
        EClass VIEW_EXTENSION_DESCRIPTION = eINSTANCE.getViewExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = eINSTANCE.getViewExtensionDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Metamodels</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__METAMODELS = eINSTANCE.getViewExtensionDescription_Metamodels();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__PAGES = eINSTANCE.getViewExtensionDescription_Pages();

        /**
         * The meta object literal for the '<em><b>Default Page</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = eINSTANCE.getViewExtensionDescription_DefaultPage();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION = eINSTANCE.getViewExtensionDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getViewExtensionDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__GROUPS = eINSTANCE.getViewExtensionDescription_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
         * @generated
         */
        EClass PAGE_DESCRIPTION = eINSTANCE.getPageDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__IDENTIFIER = eINSTANCE.getPageDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getPageDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__DOMAIN_CLASS = eINSTANCE.getPageDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = eINSTANCE.getPageDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference PAGE_DESCRIPTION__GROUPS = eINSTANCE.getPageDescription_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
         * @generated
         */
        EClass GROUP_DESCRIPTION = eINSTANCE.getGroupDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__IDENTIFIER = eINSTANCE.getGroupDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getGroupDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__DOMAIN_CLASS = eINSTANCE.getGroupDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = eINSTANCE.getGroupDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference GROUP_DESCRIPTION__CONTAINER = eINSTANCE.getGroupDescription_Container();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl <em>Container Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
         * @generated
         */
        EClass CONTAINER_DESCRIPTION = eINSTANCE.getContainerDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTAINER_DESCRIPTION__IDENTIFIER = eINSTANCE.getContainerDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Widgets</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__WIDGETS = eINSTANCE.getContainerDescription_Widgets();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
         * @generated
         */
        EClass WIDGET_DESCRIPTION = eINSTANCE.getWidgetDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__IDENTIFIER = eINSTANCE.getWidgetDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getWidgetDescription_LabelExpression();

        /**
         * The meta object literal for the '
         * <em><b>Domain Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__DOMAIN_CANDIDATES_EXPRESSION = eINSTANCE.getWidgetDescription_DomainCandidatesExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
         * @generated
         */
        EClass TEXT_DESCRIPTION = eINSTANCE.getTextDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_DESCRIPTION__VALUE_EXPRESSION = eINSTANCE.getTextDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference TEXT_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getTextDescription_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl <em>Checkbox Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
         * @generated
         */
        EClass CHECKBOX_DESCRIPTION = eINSTANCE.getCheckboxDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = eINSTANCE.getCheckboxDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference CHECKBOX_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getCheckboxDescription_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl <em>Select Description</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
         * @generated
         */
        EClass SELECT_DESCRIPTION = eINSTANCE.getSelectDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__VALUE_EXPRESSION = eINSTANCE.getSelectDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference SELECT_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getSelectDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__MULTIPLE = eINSTANCE.getSelectDescription_Multiple();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = eINSTANCE.getSelectDescription_CandidatesExpression();

        /**
         * The meta object literal for the '
         * <em><b>Candidate Display Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = eINSTANCE.getSelectDescription_CandidateDisplayExpression();

    }

} // PropertiesPackage
