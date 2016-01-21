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
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.properties.PropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "properties";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "properties";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    PropertiesPackage eINSTANCE = org.eclipse.sirius.properties.impl.PropertiesPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl <em>View Extension Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
     * @generated
     */
    int VIEW_EXTENSION_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = DescriptionPackage.EXTENSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Metamodels</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__METAMODELS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__PAGES = DescriptionPackage.EXTENSION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Default Page</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__GROUPS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>View Extension Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
     * @generated
     */
    int PAGE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__GROUPS = 4;

    /**
     * The number of structural features of the '<em>Page Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
     * @generated
     */
    int GROUP_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONTAINER = 4;

    /**
     * The number of structural features of the '<em>Group Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl <em>Container Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
     * @generated
     */
    int CONTAINER_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__WIDGETS = 1;

    /**
     * The number of structural features of the '<em>Container Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
     * @generated
     */
    int WIDGET_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The number of structural features of the '<em>Widget Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
     * @generated
     */
    int TEXT_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__IDENTIFIER = WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__LABEL_EXPRESSION = WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__VALUE_EXPRESSION = WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__INITIAL_OPERATION = WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Text Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION_FEATURE_COUNT = WIDGET_DESCRIPTION_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl <em>Label Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
     * @generated
     */
    int LABEL_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__IDENTIFIER = WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__LABEL_EXPRESSION = WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The number of structural features of the '<em>Label Description</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION_FEATURE_COUNT = WIDGET_DESCRIPTION_FEATURE_COUNT + 0;


    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ViewExtensionDescription <em>View Extension Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>View Extension Description</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    EClass getViewExtensionDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_Identifier();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels <em>Metamodels</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Metamodels</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Metamodels();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getPages <em>Pages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pages</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getPages()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Pages();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage <em>Default Page</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Page</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_DefaultPage();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_LabelExpression();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getGroups()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Groups();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.PageDescription <em>Page Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Page Description</em>'.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    EClass getPageDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getIdentifier()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_Identifier();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getLabelExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getDomainClass <em>Domain Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getDomainClass()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression <em>Semantic Candidate Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.properties.PageDescription#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getGroups()
     * @see #getPageDescription()
     * @generated
     */
    EReference getPageDescription_Groups();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupDescription <em>Group Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group Description</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    EClass getGroupDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getIdentifier()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_Identifier();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getLabelExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getDomainClass <em>Domain Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getDomainClass()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression <em>Semantic Candidate Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.properties.GroupDescription#getContainer <em>Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Container</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getContainer()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_Container();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ContainerDescription <em>Container Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Container Description</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    EClass getContainerDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ContainerDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getIdentifier()
     * @see #getContainerDescription()
     * @generated
     */
    EAttribute getContainerDescription_Identifier();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.properties.ContainerDescription#getWidgets <em>Widgets</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Widgets</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getWidgets()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_Widgets();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WidgetDescription <em>Widget Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Widget Description</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    EClass getWidgetDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetDescription#getIdentifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getIdentifier()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_Identifier();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getLabelExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_LabelExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextDescription <em>Text Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Description</em>'.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    EClass getTextDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.TextDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getValueExpression()
     * @see #getTextDescription()
     * @generated
     */
    EAttribute getTextDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.properties.TextDescription#getInitialOperation <em>Initial Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getInitialOperation()
     * @see #getTextDescription()
     * @generated
     */
    EReference getTextDescription_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LabelDescription <em>Label Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label Description</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription
     * @generated
     */
    EClass getLabelDescription();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesFactory getPropertiesFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
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
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = eINSTANCE.getViewExtensionDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Metamodels</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__METAMODELS = eINSTANCE.getViewExtensionDescription_Metamodels();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__PAGES = eINSTANCE.getViewExtensionDescription_Pages();

        /**
         * The meta object literal for the '<em><b>Default Page</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = eINSTANCE.getViewExtensionDescription_DefaultPage();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getViewExtensionDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__GROUPS = eINSTANCE.getViewExtensionDescription_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
         * @generated
         */
        EClass PAGE_DESCRIPTION = eINSTANCE.getPageDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__IDENTIFIER = eINSTANCE.getPageDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getPageDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__DOMAIN_CLASS = eINSTANCE.getPageDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = eINSTANCE.getPageDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PAGE_DESCRIPTION__GROUPS = eINSTANCE.getPageDescription_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
         * @generated
         */
        EClass GROUP_DESCRIPTION = eINSTANCE.getGroupDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__IDENTIFIER = eINSTANCE.getGroupDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getGroupDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__DOMAIN_CLASS = eINSTANCE.getGroupDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = eINSTANCE.getGroupDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference GROUP_DESCRIPTION__CONTAINER = eINSTANCE.getGroupDescription_Container();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl <em>Container Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
         * @generated
         */
        EClass CONTAINER_DESCRIPTION = eINSTANCE.getContainerDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTAINER_DESCRIPTION__IDENTIFIER = eINSTANCE.getContainerDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Widgets</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__WIDGETS = eINSTANCE.getContainerDescription_Widgets();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
         * @generated
         */
        EClass WIDGET_DESCRIPTION = eINSTANCE.getWidgetDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__IDENTIFIER = eINSTANCE.getWidgetDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getWidgetDescription_LabelExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
         * @generated
         */
        EClass TEXT_DESCRIPTION = eINSTANCE.getTextDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEXT_DESCRIPTION__VALUE_EXPRESSION = eINSTANCE.getTextDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TEXT_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getTextDescription_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl <em>Label Description</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
         * @generated
         */
        EClass LABEL_DESCRIPTION = eINSTANCE.getLabelDescription();

    }

} //PropertiesPackage
