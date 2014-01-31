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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.viewpoint.ViewpointFactory
 * @model kind="package" annotation=
 *        "Tags deprecated='Anything tagged as deprecated will disappear quite soon.\n\n' to\040be\040renamed='Anything tagged \"to be renamed\" will be renamed, at least in the UI\n'"
 * @generated
 */
public interface ViewpointPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "viewpoint";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "viewpoint";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ViewpointPackage eINSTANCE = org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
     * <em>DAnalysis</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysis()
     * @generated
     */
    int DANALYSIS = 0;

    /**
     * The feature id for the '<em><b>Referenced Analysis</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__REFERENCED_ANALYSIS = 0;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__MODELS = 1;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__EANNOTATIONS = 2;

    /**
     * The feature id for the '<em><b>Owned Views</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__OWNED_VIEWS = 3;

    /**
     * The feature id for the '<em><b>Selected Views</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__SELECTED_VIEWS = 4;

    /**
     * The feature id for the '<em><b>Owned Feature Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__OWNED_FEATURE_EXTENSIONS = 5;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS__VERSION = 6;

    /**
     * The number of structural features of the '<em>DAnalysis</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
     * <em>DFeature Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFeatureExtension()
     * @generated
     */
    int DFEATURE_EXTENSION = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_EXTENSION__DESCRIPTION = 0;

    /**
     * The number of structural features of the '<em>DFeature Extension</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_EXTENSION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DValidable <em>DValidable</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DValidable
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDValidable()
     * @generated
     */
    int DVALIDABLE = 2;

    /**
     * The number of structural features of the '<em>DValidable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVALIDABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DNavigableImpl
     * <em>DNavigable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNavigableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNavigable()
     * @generated
     */
    int DNAVIGABLE = 3;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNAVIGABLE__OWNED_NAVIGATION_LINKS = 0;

    /**
     * The number of structural features of the '<em>DNavigable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNAVIGABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDStylizable()
     * @generated
     */
    int DSTYLIZABLE = 4;

    /**
     * The number of structural features of the '<em>DStylizable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSTYLIZABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRefreshable()
     * @generated
     */
    int DREFRESHABLE = 5;

    /**
     * The number of structural features of the '<em>DRefreshable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREFRESHABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DLabelled <em>DLabelled</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DLabelled
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDLabelled()
     * @generated
     */
    int DLABELLED = 6;

    /**
     * The number of structural features of the '<em>DLabelled</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLABELLED_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DMappingBased
     * <em>DMapping Based</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDMappingBased()
     * @generated
     */
    int DMAPPING_BASED = 7;

    /**
     * The number of structural features of the '<em>DMapping Based</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMAPPING_BASED_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DContainer <em>DContainer</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DContainer
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDContainer()
     * @generated
     */
    int DCONTAINER = 8;

    /**
     * The number of structural features of the '<em>DContainer</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCONTAINER_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DViewImpl <em>DView</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DViewImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDView()
     * @generated
     */
    int DVIEW = 13;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__OWNED_REPRESENTATIONS = DREFRESHABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Owned Extensions</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__OWNED_EXTENSIONS = DREFRESHABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>All Representations</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__ALL_REPRESENTATIONS = DREFRESHABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Hidden Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__HIDDEN_REPRESENTATIONS = DREFRESHABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Referenced Representations</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__REFERENCED_REPRESENTATIONS = DREFRESHABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initialized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__INITIALIZED = DREFRESHABLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Viewpoint</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW__VIEWPOINT = DREFRESHABLE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>DView</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DVIEW_FEATURE_COUNT = DREFRESHABLE_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl
     * <em>DRepresentation Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationContainer()
     * @generated
     */
    int DREPRESENTATION_CONTAINER = 9;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__OWNED_REPRESENTATIONS = DVIEW__OWNED_REPRESENTATIONS;

    /**
     * The feature id for the '<em><b>Owned Extensions</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__OWNED_EXTENSIONS = DVIEW__OWNED_EXTENSIONS;

    /**
     * The feature id for the '<em><b>All Representations</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__ALL_REPRESENTATIONS = DVIEW__ALL_REPRESENTATIONS;

    /**
     * The feature id for the '<em><b>Hidden Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__HIDDEN_REPRESENTATIONS = DVIEW__HIDDEN_REPRESENTATIONS;

    /**
     * The feature id for the '<em><b>Referenced Representations</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__REFERENCED_REPRESENTATIONS = DVIEW__REFERENCED_REPRESENTATIONS;

    /**
     * The feature id for the '<em><b>Initialized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__INITIALIZED = DVIEW__INITIALIZED;

    /**
     * The feature id for the '<em><b>Viewpoint</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__VIEWPOINT = DVIEW__VIEWPOINT;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__MODELS = DVIEW_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>DRepresentation Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER_FEATURE_COUNT = DVIEW_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
     * <em>DSemantic Decorator</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDecorator()
     * @generated
     */
    int DSEMANTIC_DECORATOR = 10;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DECORATOR__TARGET = 0;

    /**
     * The number of structural features of the '<em>DSemantic Decorator</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DECORATOR_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
     * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentation()
     * @generated
     */
    int DREPRESENTATION = 11;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__EANNOTATIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__REPRESENTATION_ELEMENTS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION__OWNED_ANNOTATION_ENTRIES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>DRepresentation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
     * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationElement()
     * @generated
     */
    int DREPRESENTATION_ELEMENT = 12;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__TARGET = DLABELLED_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__NAME = DLABELLED_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS = DLABELLED_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>DRepresentation Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT_FEATURE_COUNT = DLABELLED_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
     * <em>Meta Model Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getMetaModelExtension()
     * @generated
     */
    int META_MODEL_EXTENSION = 14;

    /**
     * The feature id for the '<em><b>Extension Group</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int META_MODEL_EXTENSION__EXTENSION_GROUP = 0;

    /**
     * The number of structural features of the '<em>Meta Model Extension</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int META_MODEL_EXTENSION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DecorationImpl
     * <em>Decoration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DecorationImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDecoration()
     * @generated
     */
    int DECORATION = 15;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION__DESCRIPTION = 0;

    /**
     * The number of structural features of the '<em>Decoration</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl
     * <em>DNavigation Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNavigationLink()
     * @generated
     */
    int DNAVIGATION_LINK = 16;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNAVIGATION_LINK__TARGET_TYPE = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNAVIGATION_LINK__LABEL = 1;

    /**
     * The number of structural features of the '<em>DNavigation Link</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNAVIGATION_LINK_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DEObjectLinkImpl
     * <em>DE Object Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEObjectLinkImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDEObjectLink()
     * @generated
     */
    int DE_OBJECT_LINK = 17;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DE_OBJECT_LINK__TARGET_TYPE = DNAVIGATION_LINK__TARGET_TYPE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DE_OBJECT_LINK__LABEL = DNAVIGATION_LINK__LABEL;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DE_OBJECT_LINK__TARGET = DNAVIGATION_LINK_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DE Object Link</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DE_OBJECT_LINK_FEATURE_COUNT = DNAVIGATION_LINK_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
     * <em>DSource File Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSourceFileLink()
     * @generated
     */
    int DSOURCE_FILE_LINK = 18;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK__TARGET_TYPE = DNAVIGATION_LINK__TARGET_TYPE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK__LABEL = DNAVIGATION_LINK__LABEL;

    /**
     * The feature id for the '<em><b>File Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK__FILE_PATH = DNAVIGATION_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Start Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK__START_POSITION = DNAVIGATION_LINK_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>End Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK__END_POSITION = DNAVIGATION_LINK_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>DSource File Link</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSOURCE_FILE_LINK_FEATURE_COUNT = DNAVIGATION_LINK_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
     * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisCustomData()
     * @generated
     */
    int DANALYSIS_CUSTOM_DATA = 19;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA__KEY = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA__DATA = 1;

    /**
     * The number of structural features of the '<em>DAnalysis Custom Data</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.CustomizableImpl
     * <em>Customizable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.CustomizableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomizable()
     * @generated
     */
    int CUSTOMIZABLE = 32;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOMIZABLE__CUSTOM_FEATURES = 0;

    /**
     * The number of structural features of the '<em>Customizable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOMIZABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
     * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBasicLabelStyle()
     * @generated
     */
    int BASIC_LABEL_STYLE = 31;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__CUSTOM_FEATURES = CUSTOMIZABLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_SIZE = CUSTOMIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_FORMAT = CUSTOMIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__SHOW_ICON = CUSTOMIZABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_COLOR = CUSTOMIZABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__ICON_PATH = CUSTOMIZABLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Basic Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_FEATURE_COUNT = CUSTOMIZABLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
     * <em>Label Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelStyle()
     * @generated
     */
    int LABEL_STYLE = 20;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__CUSTOM_FEATURES = BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_SIZE = BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_FORMAT = BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__SHOW_ICON = BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_COLOR = BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__ICON_PATH = BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_ALIGNMENT = BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_FEATURE_COUNT = BASIC_LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.StyleImpl <em>Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.StyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 21;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE__CUSTOM_FEATURES = DREFRESHABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE__DESCRIPTION = DREFRESHABLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Style</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_FEATURE_COUNT = DREFRESHABLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
     * <em>RGB Values</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getRGBValues()
     * @generated
     */
    int RGB_VALUES = 22;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RGB_VALUES__RED = 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RGB_VALUES__GREEN = 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RGB_VALUES__BLUE = 2;

    /**
     * The number of structural features of the '<em>RGB Values</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RGB_VALUES_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
     * <em>DAnalysis Session EObject</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisSessionEObject()
     * @generated
     */
    int DANALYSIS_SESSION_EOBJECT = 23;

    /**
     * The feature id for the '<em><b>Open</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__OPEN = 0;

    /**
     * The feature id for the '<em><b>Blocked</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__BLOCKED = 1;

    /**
     * The feature id for the '<em><b>Resources</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__RESOURCES = 2;

    /**
     * The feature id for the '<em><b>Controlled Resources</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES = 3;

    /**
     * The feature id for the '<em><b>Activated Viewpoints</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS = 4;

    /**
     * The feature id for the '<em><b>Analyses</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__ANALYSES = 5;

    /**
     * The feature id for the '<em><b>Synchronization Status</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS = 6;

    /**
     * The number of structural features of the '
     * <em>DAnalysis Session EObject</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
     * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSessionManagerEObject()
     * @generated
     */
    int SESSION_MANAGER_EOBJECT = 24;

    /**
     * The feature id for the '<em><b>Owned Sessions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SESSION_MANAGER_EOBJECT__OWNED_SESSIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Session Manager EObject</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SESSION_MANAGER_EOBJECT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.DResource
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResource()
     * @generated
     */
    int DRESOURCE = 25;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE__NAME = 0;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE__PATH = 1;

    /**
     * The number of structural features of the '<em>DResource</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DFileImpl <em>DFile</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DFileImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFile()
     * @generated
     */
    int DFILE = 26;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFILE__NAME = DRESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFILE__PATH = DRESOURCE__PATH;

    /**
     * The number of structural features of the '<em>DFile</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFILE_FEATURE_COUNT = DRESOURCE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
     * <em>DResource Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResourceContainer()
     * @generated
     */
    int DRESOURCE_CONTAINER = 27;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__NAME = DRESOURCE__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__PATH = DRESOURCE__PATH;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__MEMBERS = DRESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DResource Container</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER_FEATURE_COUNT = DRESOURCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DProjectImpl <em>DProject</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DProjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDProject()
     * @generated
     */
    int DPROJECT = 28;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DPROJECT__NAME = DRESOURCE_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DPROJECT__PATH = DRESOURCE_CONTAINER__PATH;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DPROJECT__MEMBERS = DRESOURCE_CONTAINER__MEMBERS;

    /**
     * The number of structural features of the '<em>DProject</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DPROJECT_FEATURE_COUNT = DRESOURCE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DFolderImpl <em>DFolder</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DFolderImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFolder()
     * @generated
     */
    int DFOLDER = 29;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFOLDER__NAME = DRESOURCE_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFOLDER__PATH = DRESOURCE_CONTAINER__PATH;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFOLDER__MEMBERS = DRESOURCE_CONTAINER__MEMBERS;

    /**
     * The number of structural features of the '<em>DFolder</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFOLDER_FEATURE_COUNT = DRESOURCE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DModelImpl <em>DModel</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DModelImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDModel()
     * @generated
     */
    int DMODEL = 30;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMODEL__NAME = DFILE__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMODEL__PATH = DFILE__PATH;

    /**
     * The number of structural features of the '<em>DModel</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMODEL_FEATURE_COUNT = DFILE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFontFormat()
     * @generated
     */
    int FONT_FORMAT = 33;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.LabelAlignment
     * <em>Label Alignment</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelAlignment()
     * @generated
     */
    int LABEL_ALIGNMENT = 34;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSyncStatus()
     * @generated
     */
    int SYNC_STATUS = 35;

    /**
     * The meta object id for the '<em>Extended Package</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getExtendedPackage()
     * @generated
     */
    int EXTENDED_PACKAGE = 36;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis
     * @generated
     */
    EClass getDAnalysis();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getReferencedAnalysis
     * <em>Referenced Analysis</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Referenced Analysis</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getReferencedAnalysis()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_ReferencedAnalysis();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getModels <em>Models</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getModels()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_Models();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getEAnnotations
     * <em>EAnnotations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>EAnnotations</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getEAnnotations()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_EAnnotations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedViews
     * <em>Owned Views</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Views</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getOwnedViews()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedViews();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getSelectedViews
     * <em>Selected Views</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Selected Views</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getSelectedViews()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_SelectedViews();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedFeatureExtensions
     * <em>Owned Feature Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Feature Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getOwnedFeatureExtensions()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedFeatureExtensions();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getVersion()
     * @see #getDAnalysis()
     * @generated
     */
    EAttribute getDAnalysis_Version();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DFeatureExtension
     * <em>DFeature Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DFeature Extension</em>'.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension
     * @generated
     */
    EClass getDFeatureExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription()
     * @see #getDFeatureExtension()
     * @generated
     */
    EReference getDFeatureExtension_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DValidable <em>DValidable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DValidable</em>'.
     * @see org.eclipse.sirius.viewpoint.DValidable
     * @generated
     */
    EClass getDValidable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNavigable <em>DNavigable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNavigable</em>'.
     * @see org.eclipse.sirius.viewpoint.DNavigable
     * @generated
     */
    EClass getDNavigable();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DNavigable#getOwnedNavigationLinks
     * <em>Owned Navigation Links</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Navigation Links</em>'.
     * @see org.eclipse.sirius.viewpoint.DNavigable#getOwnedNavigationLinks()
     * @see #getDNavigable()
     * @generated
     */
    EReference getDNavigable_OwnedNavigationLinks();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DStylizable</em>'.
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @generated
     */
    EClass getDStylizable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DRefreshable</em>'.
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @generated
     */
    EClass getDRefreshable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DLabelled <em>DLabelled</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DLabelled</em>'.
     * @see org.eclipse.sirius.viewpoint.DLabelled
     * @generated
     */
    EClass getDLabelled();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DMappingBased
     * <em>DMapping Based</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DMapping Based</em>'.
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @generated
     */
    EClass getDMappingBased();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DContainer <em>DContainer</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DContainer</em>'.
     * @see org.eclipse.sirius.viewpoint.DContainer
     * @generated
     */
    EClass getDContainer();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer
     * <em>DRepresentation Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation Container</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationContainer
     * @generated
     */
    EClass getDRepresentationContainer();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getModels
     * <em>Models</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationContainer#getModels()
     * @see #getDRepresentationContainer()
     * @generated
     */
    EReference getDRepresentationContainer_Models();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DSemantic Decorator</em>'.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator
     * @generated
     */
    EClass getDSemanticDecorator();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget()
     * @see #getDSemanticDecorator()
     * @generated
     */
    EReference getDSemanticDecorator_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation
     * <em>DRepresentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation
     * @generated
     */
    EClass getDRepresentation();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedRepresentationElements
     * <em>Owned Representation Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Owned Representation Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getOwnedRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedRepresentationElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation#getRepresentationElements
     * <em>Representation Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Representation Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_RepresentationElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getName()
     * @see #getDRepresentation()
     * @generated
     */
    EAttribute getDRepresentation_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedAnnotationEntries
     * <em>Owned Annotation Entries</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Annotation Entries</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getOwnedAnnotationEntries()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedAnnotationEntries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation Element</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement
     * @generated
     */
    EClass getDRepresentationElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement#getName()
     * @see #getDRepresentationElement()
     * @generated
     */
    EAttribute getDRepresentationElement_Name();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement#getSemanticElements
     * <em>Semantic Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement#getSemanticElements()
     * @see #getDRepresentationElement()
     * @generated
     */
    EReference getDRepresentationElement_SemanticElements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DView <em>DView</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DView</em>'.
     * @see org.eclipse.sirius.viewpoint.DView
     * @generated
     */
    EClass getDView();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DView#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getOwnedRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedRepresentations();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DView#getOwnedExtensions
     * <em>Owned Extensions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getOwnedExtensions()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedExtensions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DView#getAllRepresentations
     * <em>All Representations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getAllRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_AllRepresentations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DView#getHiddenRepresentations
     * <em>Hidden Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Hidden Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getHiddenRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_HiddenRepresentations();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DView#getReferencedRepresentations
     * <em>Referenced Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Referenced Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getReferencedRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_ReferencedRepresentations();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DView#isInitialized
     * <em>Initialized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Initialized</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#isInitialized()
     * @see #getDView()
     * @generated
     */
    EAttribute getDView_Initialized();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DView#getViewpoint
     * <em>Viewpoint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Viewpoint</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getViewpoint()
     * @see #getDView()
     * @generated
     */
    EReference getDView_Viewpoint();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.MetaModelExtension
     * <em>Meta Model Extension</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Meta Model Extension</em>'.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension
     * @generated
     */
    EClass getMetaModelExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup
     * <em>Extension Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Extension Group</em>'.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup()
     * @see #getMetaModelExtension()
     * @generated
     */
    EReference getMetaModelExtension_ExtensionGroup();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Decoration <em>Decoration</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration</em>'.
     * @see org.eclipse.sirius.viewpoint.Decoration
     * @generated
     */
    EClass getDecoration();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.Decoration#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.Decoration#getDescription()
     * @see #getDecoration()
     * @generated
     */
    EReference getDecoration_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNavigationLink
     * <em>DNavigation Link</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DNavigation Link</em>'.
     * @see org.eclipse.sirius.viewpoint.DNavigationLink
     * @generated
     */
    EClass getDNavigationLink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNavigationLink#getTargetType
     * <em>Target Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Type</em>'.
     * @see org.eclipse.sirius.viewpoint.DNavigationLink#getTargetType()
     * @see #getDNavigationLink()
     * @generated
     */
    EAttribute getDNavigationLink_TargetType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNavigationLink#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.viewpoint.DNavigationLink#getLabel()
     * @see #getDNavigationLink()
     * @generated
     */
    EAttribute getDNavigationLink_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DEObjectLink <em>DE Object Link</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DE Object Link</em>'.
     * @see org.eclipse.sirius.viewpoint.DEObjectLink
     * @generated
     */
    EClass getDEObjectLink();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DEObjectLink#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DEObjectLink#getTarget()
     * @see #getDEObjectLink()
     * @generated
     */
    EReference getDEObjectLink_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DSourceFileLink
     * <em>DSource File Link</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DSource File Link</em>'.
     * @see org.eclipse.sirius.viewpoint.DSourceFileLink
     * @generated
     */
    EClass getDSourceFileLink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DSourceFileLink#getFilePath
     * <em>File Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>File Path</em>'.
     * @see org.eclipse.sirius.viewpoint.DSourceFileLink#getFilePath()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_FilePath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DSourceFileLink#getStartPosition
     * <em>Start Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Start Position</em>'.
     * @see org.eclipse.sirius.viewpoint.DSourceFileLink#getStartPosition()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_StartPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DSourceFileLink#getEndPosition
     * <em>End Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>End Position</em>'.
     * @see org.eclipse.sirius.viewpoint.DSourceFileLink#getEndPosition()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_EndPosition();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * <em>DAnalysis Custom Data</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis Custom Data</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * @generated
     */
    EClass getDAnalysisCustomData();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisCustomData#getKey
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData#getKey()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EAttribute getDAnalysisCustomData_Key();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisCustomData#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData#getData()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EReference getDAnalysisCustomData_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.LabelStyle <em>Label Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelStyle
     * @generated
     */
    EClass getLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.LabelStyle#getLabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelStyle#getLabelAlignment()
     * @see #getLabelStyle()
     * @generated
     */
    EAttribute getLabelStyle_LabelAlignment();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Style <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Style</em>'.
     * @see org.eclipse.sirius.viewpoint.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.Style#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.Style#getDescription()
     * @see #getStyle()
     * @generated
     */
    EReference getStyle_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.RGBValues <em>RGB Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>RGB Values</em>'.
     * @see org.eclipse.sirius.viewpoint.RGBValues
     * @generated
     */
    EClass getRGBValues();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getRed <em>Red</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.viewpoint.RGBValues#getRed()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getGreen <em>Green</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.viewpoint.RGBValues#getGreen()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getBlue <em>Blue</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.viewpoint.RGBValues#getBlue()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * <em>DAnalysis Session EObject</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis Session EObject</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * @generated
     */
    EClass getDAnalysisSessionEObject();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen
     * <em>Open</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Open</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Open();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isBlocked
     * <em>Blocked</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blocked</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isBlocked()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Blocked();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Resources</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Resources();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getControlledResources
     * <em>Controlled Resources</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Controlled Resources</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getControlledResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_ControlledResources();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getActivatedViewpoints
     * <em>Activated Viewpoints</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activated Viewpoints</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getActivatedViewpoints()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_ActivatedViewpoints();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getAnalyses
     * <em>Analyses</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Analyses</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getAnalyses()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_Analyses();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus
     * <em>Synchronization Status</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Synchronization Status</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_SynchronizationStatus();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.SessionManagerEObject
     * <em>Session Manager EObject</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Session Manager EObject</em>'.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject
     * @generated
     */
    EClass getSessionManagerEObject();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.SessionManagerEObject#getOwnedSessions
     * <em>Owned Sessions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Sessions</em>'.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject#getOwnedSessions()
     * @see #getSessionManagerEObject()
     * @generated
     */
    EReference getSessionManagerEObject_OwnedSessions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DResource</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource
     * @generated
     */
    EClass getDResource();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DResource#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource#getName()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DResource#getPath <em>Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource#getPath()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Path();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DFile <em>DFile</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DFile</em>'.
     * @see org.eclipse.sirius.viewpoint.DFile
     * @generated
     */
    EClass getDFile();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DResourceContainer
     * <em>DResource Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DResource Container</em>'.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer
     * @generated
     */
    EClass getDResourceContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DResourceContainer#getMembers
     * <em>Members</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Members</em>'.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer#getMembers()
     * @see #getDResourceContainer()
     * @generated
     */
    EReference getDResourceContainer_Members();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DProject <em>DProject</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DProject</em>'.
     * @see org.eclipse.sirius.viewpoint.DProject
     * @generated
     */
    EClass getDProject();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DFolder <em>DFolder</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DFolder</em>'.
     * @see org.eclipse.sirius.viewpoint.DFolder
     * @generated
     */
    EClass getDFolder();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DModel <em>DModel</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DModel</em>'.
     * @see org.eclipse.sirius.viewpoint.DModel
     * @generated
     */
    EClass getDModel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle
     * <em>Basic Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Basic Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle
     * @generated
     */
    EClass getBasicLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelSize()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Format</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelFormat()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelFormat();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle#isShowIcon
     * <em>Show Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Show Icon</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#isShowIcon()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_ShowIcon();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelColor
     * <em>Label Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Label Color</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelColor()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EReference getBasicLabelStyle_LabelColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getIconPath()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_IconPath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Customizable <em>Customizable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Customizable</em>'.
     * @see org.eclipse.sirius.viewpoint.Customizable
     * @generated
     */
    EClass getCustomizable();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.Customizable#getCustomFeatures
     * <em>Custom Features</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Custom Features</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.Customizable#getCustomFeatures()
     * @see #getCustomizable()
     * @generated
     */
    EAttribute getCustomizable_CustomFeatures();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Font Format</em>'.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @generated
     */
    EEnum getFontFormat();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.LabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @generated
     */
    EEnum getLabelAlignment();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Sync Status</em>'.
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @generated
     */
    EEnum getSyncStatus();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
     * <em>Extended Package</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for data type '<em>Extended Package</em>'.
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
     * @model instanceClass=
     *        "org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor"
     * @generated
     */
    EDataType getExtendedPackage();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ViewpointFactory getViewpointFactory();

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
         * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
         * <em>DAnalysis</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysis()
         * @generated
         */
        EClass DANALYSIS = eINSTANCE.getDAnalysis();

        /**
         * The meta object literal for the '<em><b>Referenced Analysis</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__REFERENCED_ANALYSIS = eINSTANCE.getDAnalysis_ReferencedAnalysis();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__MODELS = eINSTANCE.getDAnalysis_Models();

        /**
         * The meta object literal for the '<em><b>EAnnotations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__EANNOTATIONS = eINSTANCE.getDAnalysis_EAnnotations();

        /**
         * The meta object literal for the '<em><b>Owned Views</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__OWNED_VIEWS = eINSTANCE.getDAnalysis_OwnedViews();

        /**
         * The meta object literal for the '<em><b>Selected Views</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__SELECTED_VIEWS = eINSTANCE.getDAnalysis_SelectedViews();

        /**
         * The meta object literal for the '
         * <em><b>Owned Feature Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS__OWNED_FEATURE_EXTENSIONS = eINSTANCE.getDAnalysis_OwnedFeatureExtensions();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS__VERSION = eINSTANCE.getDAnalysis_Version();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
         * <em>DFeature Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFeatureExtension()
         * @generated
         */
        EClass DFEATURE_EXTENSION = eINSTANCE.getDFeatureExtension();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DFEATURE_EXTENSION__DESCRIPTION = eINSTANCE.getDFeatureExtension_Description();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DValidable <em>DValidable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DValidable
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDValidable()
         * @generated
         */
        EClass DVALIDABLE = eINSTANCE.getDValidable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DNavigableImpl
         * <em>DNavigable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNavigableImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNavigable()
         * @generated
         */
        EClass DNAVIGABLE = eINSTANCE.getDNavigable();

        /**
         * The meta object literal for the '
         * <em><b>Owned Navigation Links</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNAVIGABLE__OWNED_NAVIGATION_LINKS = eINSTANCE.getDNavigable_OwnedNavigationLinks();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}
         * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DStylizable
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDStylizable()
         * @generated
         */
        EClass DSTYLIZABLE = eINSTANCE.getDStylizable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DRefreshable
         * <em>DRefreshable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DRefreshable
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRefreshable()
         * @generated
         */
        EClass DREFRESHABLE = eINSTANCE.getDRefreshable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DLabelled <em>DLabelled</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DLabelled
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDLabelled()
         * @generated
         */
        EClass DLABELLED = eINSTANCE.getDLabelled();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DMappingBased
         * <em>DMapping Based</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DMappingBased
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDMappingBased()
         * @generated
         */
        EClass DMAPPING_BASED = eINSTANCE.getDMappingBased();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DContainer <em>DContainer</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DContainer
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDContainer()
         * @generated
         */
        EClass DCONTAINER = eINSTANCE.getDContainer();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl
         * <em>DRepresentation Container</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationContainer()
         * @generated
         */
        EClass DREPRESENTATION_CONTAINER = eINSTANCE.getDRepresentationContainer();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION_CONTAINER__MODELS = eINSTANCE.getDRepresentationContainer_Models();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
         * <em>DSemantic Decorator</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDecorator()
         * @generated
         */
        EClass DSEMANTIC_DECORATOR = eINSTANCE.getDSemanticDecorator();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DSEMANTIC_DECORATOR__TARGET = eINSTANCE.getDSemanticDecorator_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
         * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentation()
         * @generated
         */
        EClass DREPRESENTATION = eINSTANCE.getDRepresentation();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representation Elements</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS = eINSTANCE.getDRepresentation_OwnedRepresentationElements();

        /**
         * The meta object literal for the '
         * <em><b>Representation Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION__REPRESENTATION_ELEMENTS = eINSTANCE.getDRepresentation_RepresentationElements();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DREPRESENTATION__NAME = eINSTANCE.getDRepresentation_Name();

        /**
         * The meta object literal for the '
         * <em><b>Owned Annotation Entries</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION__OWNED_ANNOTATION_ENTRIES = eINSTANCE.getDRepresentation_OwnedAnnotationEntries();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
         * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationElement()
         * @generated
         */
        EClass DREPRESENTATION_ELEMENT = eINSTANCE.getDRepresentationElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DREPRESENTATION_ELEMENT__NAME = eINSTANCE.getDRepresentationElement_Name();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS = eINSTANCE.getDRepresentationElement_SemanticElements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DViewImpl <em>DView</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DViewImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDView()
         * @generated
         */
        EClass DVIEW = eINSTANCE.getDView();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__OWNED_REPRESENTATIONS = eINSTANCE.getDView_OwnedRepresentations();

        /**
         * The meta object literal for the '<em><b>Owned Extensions</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__OWNED_EXTENSIONS = eINSTANCE.getDView_OwnedExtensions();

        /**
         * The meta object literal for the '<em><b>All Representations</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__ALL_REPRESENTATIONS = eINSTANCE.getDView_AllRepresentations();

        /**
         * The meta object literal for the '
         * <em><b>Hidden Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__HIDDEN_REPRESENTATIONS = eINSTANCE.getDView_HiddenRepresentations();

        /**
         * The meta object literal for the '
         * <em><b>Referenced Representations</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__REFERENCED_REPRESENTATIONS = eINSTANCE.getDView_ReferencedRepresentations();

        /**
         * The meta object literal for the '<em><b>Initialized</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DVIEW__INITIALIZED = eINSTANCE.getDView_Initialized();

        /**
         * The meta object literal for the '<em><b>Viewpoint</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__VIEWPOINT = eINSTANCE.getDView_Viewpoint();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
         * <em>Meta Model Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getMetaModelExtension()
         * @generated
         */
        EClass META_MODEL_EXTENSION = eINSTANCE.getMetaModelExtension();

        /**
         * The meta object literal for the '<em><b>Extension Group</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference META_MODEL_EXTENSION__EXTENSION_GROUP = eINSTANCE.getMetaModelExtension_ExtensionGroup();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DecorationImpl
         * <em>Decoration</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DecorationImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDecoration()
         * @generated
         */
        EClass DECORATION = eINSTANCE.getDecoration();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DECORATION__DESCRIPTION = eINSTANCE.getDecoration_Description();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl
         * <em>DNavigation Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNavigationLink()
         * @generated
         */
        EClass DNAVIGATION_LINK = eINSTANCE.getDNavigationLink();

        /**
         * The meta object literal for the '<em><b>Target Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNAVIGATION_LINK__TARGET_TYPE = eINSTANCE.getDNavigationLink_TargetType();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNAVIGATION_LINK__LABEL = eINSTANCE.getDNavigationLink_Label();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DEObjectLinkImpl
         * <em>DE Object Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DEObjectLinkImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDEObjectLink()
         * @generated
         */
        EClass DE_OBJECT_LINK = eINSTANCE.getDEObjectLink();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DE_OBJECT_LINK__TARGET = eINSTANCE.getDEObjectLink_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
         * <em>DSource File Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSourceFileLink()
         * @generated
         */
        EClass DSOURCE_FILE_LINK = eINSTANCE.getDSourceFileLink();

        /**
         * The meta object literal for the '<em><b>File Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DSOURCE_FILE_LINK__FILE_PATH = eINSTANCE.getDSourceFileLink_FilePath();

        /**
         * The meta object literal for the '<em><b>Start Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DSOURCE_FILE_LINK__START_POSITION = eINSTANCE.getDSourceFileLink_StartPosition();

        /**
         * The meta object literal for the '<em><b>End Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DSOURCE_FILE_LINK__END_POSITION = eINSTANCE.getDSourceFileLink_EndPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
         * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisCustomData()
         * @generated
         */
        EClass DANALYSIS_CUSTOM_DATA = eINSTANCE.getDAnalysisCustomData();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_CUSTOM_DATA__KEY = eINSTANCE.getDAnalysisCustomData_Key();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS_CUSTOM_DATA__DATA = eINSTANCE.getDAnalysisCustomData_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
         * <em>Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelStyle()
         * @generated
         */
        EClass LABEL_STYLE = eINSTANCE.getLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_STYLE__LABEL_ALIGNMENT = eINSTANCE.getLabelStyle_LabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.StyleImpl <em>Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.StyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getStyle()
         * @generated
         */
        EClass STYLE = eINSTANCE.getStyle();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STYLE__DESCRIPTION = eINSTANCE.getStyle_Description();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
         * <em>RGB Values</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getRGBValues()
         * @generated
         */
        EClass RGB_VALUES = eINSTANCE.getRGBValues();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RGB_VALUES__RED = eINSTANCE.getRGBValues_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RGB_VALUES__GREEN = eINSTANCE.getRGBValues_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RGB_VALUES__BLUE = eINSTANCE.getRGBValues_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
         * <em>DAnalysis Session EObject</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisSessionEObject()
         * @generated
         */
        EClass DANALYSIS_SESSION_EOBJECT = eINSTANCE.getDAnalysisSessionEObject();

        /**
         * The meta object literal for the '<em><b>Open</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__OPEN = eINSTANCE.getDAnalysisSessionEObject_Open();

        /**
         * The meta object literal for the '<em><b>Blocked</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__BLOCKED = eINSTANCE.getDAnalysisSessionEObject_Blocked();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__RESOURCES = eINSTANCE.getDAnalysisSessionEObject_Resources();

        /**
         * The meta object literal for the '<em><b>Controlled Resources</b></em>
         * ' attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES = eINSTANCE.getDAnalysisSessionEObject_ControlledResources();

        /**
         * The meta object literal for the '<em><b>Activated Viewpoints</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @generated
         */
        EReference DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS = eINSTANCE.getDAnalysisSessionEObject_ActivatedViewpoints();

        /**
         * The meta object literal for the '<em><b>Analyses</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANALYSIS_SESSION_EOBJECT__ANALYSES = eINSTANCE.getDAnalysisSessionEObject_Analyses();

        /**
         * The meta object literal for the '
         * <em><b>Synchronization Status</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS = eINSTANCE.getDAnalysisSessionEObject_SynchronizationStatus();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
         * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSessionManagerEObject()
         * @generated
         */
        EClass SESSION_MANAGER_EOBJECT = eINSTANCE.getSessionManagerEObject();

        /**
         * The meta object literal for the '<em><b>Owned Sessions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SESSION_MANAGER_EOBJECT__OWNED_SESSIONS = eINSTANCE.getSessionManagerEObject_OwnedSessions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.DResource
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResource()
         * @generated
         */
        EClass DRESOURCE = eINSTANCE.getDResource();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DRESOURCE__NAME = eINSTANCE.getDResource_Name();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DRESOURCE__PATH = eINSTANCE.getDResource_Path();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DFileImpl <em>DFile</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DFileImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFile()
         * @generated
         */
        EClass DFILE = eINSTANCE.getDFile();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
         * <em>DResource Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResourceContainer()
         * @generated
         */
        EClass DRESOURCE_CONTAINER = eINSTANCE.getDResourceContainer();

        /**
         * The meta object literal for the '<em><b>Members</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DRESOURCE_CONTAINER__MEMBERS = eINSTANCE.getDResourceContainer_Members();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DProjectImpl
         * <em>DProject</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DProjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDProject()
         * @generated
         */
        EClass DPROJECT = eINSTANCE.getDProject();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DFolderImpl
         * <em>DFolder</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DFolderImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFolder()
         * @generated
         */
        EClass DFOLDER = eINSTANCE.getDFolder();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DModelImpl <em>DModel</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DModelImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDModel()
         * @generated
         */
        EClass DMODEL = eINSTANCE.getDModel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
         * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBasicLabelStyle()
         * @generated
         */
        EClass BASIC_LABEL_STYLE = eINSTANCE.getBasicLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_SIZE = eINSTANCE.getBasicLabelStyle_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_FORMAT = eINSTANCE.getBasicLabelStyle_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Show Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__SHOW_ICON = eINSTANCE.getBasicLabelStyle_ShowIcon();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BASIC_LABEL_STYLE__LABEL_COLOR = eINSTANCE.getBasicLabelStyle_LabelColor();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__ICON_PATH = eINSTANCE.getBasicLabelStyle_IconPath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.CustomizableImpl
         * <em>Customizable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.CustomizableImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomizable()
         * @generated
         */
        EClass CUSTOMIZABLE = eINSTANCE.getCustomizable();

        /**
         * The meta object literal for the '<em><b>Custom Features</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CUSTOMIZABLE__CUSTOM_FEATURES = eINSTANCE.getCustomizable_CustomFeatures();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.FontFormat
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFontFormat()
         * @generated
         */
        EEnum FONT_FORMAT = eINSTANCE.getFontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.LabelAlignment
         * <em>Label Alignment</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.LabelAlignment
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelAlignment()
         * @generated
         */
        EEnum LABEL_ALIGNMENT = eINSTANCE.getLabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.SyncStatus
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSyncStatus()
         * @generated
         */
        EEnum SYNC_STATUS = eINSTANCE.getSyncStatus();

        /**
         * The meta object literal for the '<em>Extended Package</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getExtendedPackage()
         * @generated
         */
        EDataType EXTENDED_PACKAGE = eINSTANCE.getExtendedPackage();

    }

} // ViewpointPackage
