/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.sirius.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.SiriusFactory
 * @model kind="package" annotation=
 *        "Tags deprecated='Anything tagged as deprecated will disappear quite soon.\n\n' to\040be\040renamed='Anything tagged \"to be renamed\" will be renamed, at least in the UI\n'"
 * @generated
 */
public interface SiriusPackage extends EPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
    SiriusPackage eINSTANCE = org.eclipse.sirius.impl.SiriusPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DAnalysisImpl <em>DAnalysis</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DAnalysisImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysis()
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
     * {@link org.eclipse.sirius.impl.DFeatureExtensionImpl
     * <em>DFeature Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DFeatureExtensionImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFeatureExtension()
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
     * The meta object id for the '{@link org.eclipse.sirius.DValidable
     * <em>DValidable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.DValidable
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDValidable()
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
     * {@link org.eclipse.sirius.impl.DNavigableImpl <em>DNavigable</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNavigableImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNavigable()
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
     * The meta object id for the '{@link org.eclipse.sirius.DStylizable
     * <em>DStylizable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.DStylizable
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDStylizable()
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
     * The meta object id for the '{@link org.eclipse.sirius.DRefreshable
     * <em>DRefreshable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.DRefreshable
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRefreshable()
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
     * The meta object id for the '{@link org.eclipse.sirius.DLabelled
     * <em>DLabelled</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.DLabelled
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDLabelled()
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
     * The meta object id for the '{@link org.eclipse.sirius.DMappingBased
     * <em>DMapping Based</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.DMappingBased
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDMappingBased()
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
     * The meta object id for the '{@link org.eclipse.sirius.DContainer
     * <em>DContainer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.DContainer
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDContainer()
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
     * {@link org.eclipse.sirius.impl.DSemanticDecoratorImpl
     * <em>DSemantic Decorator</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DSemanticDecoratorImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSemanticDecorator()
     * @generated
     */
    int DSEMANTIC_DECORATOR = 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DRepresentationImpl
     * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DRepresentationImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentation()
     * @generated
     */
    int DREPRESENTATION = 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DRepresentationElementImpl
     * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DRepresentationElementImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentationElement()
     * @generated
     */
    int DREPRESENTATION_ELEMENT = 13;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.DViewImpl
     * <em>DView</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DViewImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDView()
     * @generated
     */
    int DVIEW = 14;

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
     * The feature id for the '<em><b>Sirius</b></em>' reference. <!--
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
     * {@link org.eclipse.sirius.impl.DRepresentationContainerImpl
     * <em>DRepresentation Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DRepresentationContainerImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentationContainer()
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
     * The feature id for the '<em><b>Sirius</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__VIEWPOINT = DVIEW__VIEWPOINT;

    /**
     * The feature id for the '<em><b>Diagram Set</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__DIAGRAM_SET = DVIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER__MODELS = DVIEW_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>DRepresentation Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DREPRESENTATION_CONTAINER_FEATURE_COUNT = DVIEW_FEATURE_COUNT + 2;

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
     * {@link org.eclipse.sirius.impl.ExtensibleRepresentationImpl
     * <em>Extensible Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ExtensibleRepresentationImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getExtensibleRepresentation()
     * @generated
     */
    int EXTENSIBLE_REPRESENTATION = 12;

    /**
     * The feature id for the '<em><b>Effective Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Contribution Points</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS = 1;

    /**
     * The number of structural features of the '
     * <em>Extensible Representation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTENSIBLE_REPRESENTATION_FEATURE_COUNT = 2;

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
     * {@link org.eclipse.sirius.impl.MetaModelExtensionImpl
     * <em>Meta Model Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.MetaModelExtensionImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getMetaModelExtension()
     * @generated
     */
    int META_MODEL_EXTENSION = 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.LabelStyleImpl <em>Label Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.LabelStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelStyle()
     * @generated
     */
    int LABEL_STYLE = 55;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.NodeStyleImpl <em>Node Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.NodeStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 39;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.DotImpl
     * <em>Dot</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DotImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDot()
     * @generated
     */
    int DOT = 40;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ContainerStyleImpl
     * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ContainerStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerStyle()
     * @generated
     */
    int CONTAINER_STYLE = 42;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.SquareImpl
     * <em>Square</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.SquareImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSquare()
     * @generated
     */
    int SQUARE = 45;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.BundledImageImpl
     * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.impl.BundledImageImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBundledImage()
     * @generated
     */
    int BUNDLED_IMAGE = 48;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.WorkspaceImageImpl
     * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.WorkspaceImageImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getWorkspaceImage()
     * @generated
     */
    int WORKSPACE_IMAGE = 49;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.GaugeSectionImpl
     * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.impl.GaugeSectionImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGaugeSection()
     * @generated
     */
    int GAUGE_SECTION = 41;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.EdgeTargetImpl <em>Edge Target</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.EdgeTargetImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeTarget()
     * @generated
     */
    int EDGE_TARGET = 51;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.EdgeStyleImpl <em>Edge Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 52;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.GaugeCompositeStyleImpl
     * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.GaugeCompositeStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGaugeCompositeStyle()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE = 54;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.StyleImpl
     * <em>Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.StyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 56;

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
     * {@link org.eclipse.sirius.impl.DDiagramImpl <em>DDiagram</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagram()
     * @generated
     */
    int DDIAGRAM = 16;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DOCUMENTATION = DREPRESENTATION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__EANNOTATIONS = DREPRESENTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS = DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__REPRESENTATION_ELEMENTS = DREPRESENTATION__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NAME = DREPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_ANNOTATION_ENTRIES = DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = DREPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DIAGRAM_ELEMENTS = DREPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DESCRIPTION = DREPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Info</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__INFO = DREPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sub Diagrams</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__SUB_DIAGRAMS = DREPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__EDGES = DREPRESENTATION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODES = DREPRESENTATION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODE_LIST_ELEMENTS = DREPRESENTATION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__CONTAINERS = DREPRESENTATION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__CURRENT_CONCERN = DREPRESENTATION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_FILTERS = DREPRESENTATION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ALL_FILTERS = DREPRESENTATION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_RULES = DREPRESENTATION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATE_BEHAVIORS = DREPRESENTATION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__FILTER_VARIABLE_HISTORY = DREPRESENTATION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_LAYERS = DREPRESENTATION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__SYNCHRONIZED = DREPRESENTATION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__HIDDEN_ELEMENTS = DREPRESENTATION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__IS_IN_LAYOUTING_MODE = DREPRESENTATION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__HEADER_HEIGHT = DREPRESENTATION_FEATURE_COUNT + 19;

    /**
     * The number of structural features of the '<em>DDiagram</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_FEATURE_COUNT = DREPRESENTATION_FEATURE_COUNT + 20;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DSemanticDiagramImpl
     * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DSemanticDiagramImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSemanticDiagram()
     * @generated
     */
    int DSEMANTIC_DIAGRAM = 17;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DOCUMENTATION = DDIAGRAM__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EANNOTATIONS = DDIAGRAM__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_REPRESENTATION_ELEMENTS = DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__REPRESENTATION_ELEMENTS = DDIAGRAM__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NAME = DDIAGRAM__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_ANNOTATION_ENTRIES = DDIAGRAM__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_DIAGRAM_ELEMENTS = DDIAGRAM__OWNED_DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DIAGRAM_ELEMENTS = DDIAGRAM__DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DESCRIPTION = DDIAGRAM__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Info</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__INFO = DDIAGRAM__INFO;

    /**
     * The feature id for the '<em><b>Sub Diagrams</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__SUB_DIAGRAMS = DDIAGRAM__SUB_DIAGRAMS;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EDGES = DDIAGRAM__EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODES = DDIAGRAM__NODES;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODE_LIST_ELEMENTS = DDIAGRAM__NODE_LIST_ELEMENTS;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CONTAINERS = DDIAGRAM__CONTAINERS;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CURRENT_CONCERN = DDIAGRAM__CURRENT_CONCERN;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_FILTERS = DDIAGRAM__ACTIVATED_FILTERS;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ALL_FILTERS = DDIAGRAM__ALL_FILTERS;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_RULES = DDIAGRAM__ACTIVATED_RULES;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATE_BEHAVIORS = DDIAGRAM__ACTIVATE_BEHAVIORS;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__FILTER_VARIABLE_HISTORY = DDIAGRAM__FILTER_VARIABLE_HISTORY;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_LAYERS = DDIAGRAM__ACTIVATED_LAYERS;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__SYNCHRONIZED = DDIAGRAM__SYNCHRONIZED;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HIDDEN_ELEMENTS = DDIAGRAM__HIDDEN_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__IS_IN_LAYOUTING_MODE = DDIAGRAM__IS_IN_LAYOUTING_MODE;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HEADER_HEIGHT = DDIAGRAM__HEADER_HEIGHT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__TARGET = DDIAGRAM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DSemantic Diagram</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM_FEATURE_COUNT = DDIAGRAM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DDiagramElementImpl
     * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DDiagramElementImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramElement()
     * @generated
     */
    int DDIAGRAM_ELEMENT = 18;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TARGET = DREPRESENTATION_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__NAME = DREPRESENTATION_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS = DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__VISIBLE = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TOOLTIP_TEXT = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__PARENT_LAYERS = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DECORATIONS = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>DDiagram Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_FEATURE_COUNT = DREPRESENTATION_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.GraphicalFilter
     * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.GraphicalFilter
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGraphicalFilter()
     * @generated
     */
    int GRAPHICAL_FILTER = 19;

    /**
     * The number of structural features of the '<em>Graphical Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_FILTER_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.HideFilterImpl <em>Hide Filter</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.HideFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getHideFilter()
     * @generated
     */
    int HIDE_FILTER = 20;

    /**
     * The number of structural features of the '<em>Hide Filter</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HIDE_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.HideLabelFilterImpl
     * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.HideLabelFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getHideLabelFilter()
     * @generated
     */
    int HIDE_LABEL_FILTER = 21;

    /**
     * The number of structural features of the '<em>Hide Label Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HIDE_LABEL_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.FoldingPointFilterImpl
     * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.FoldingPointFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFoldingPointFilter()
     * @generated
     */
    int FOLDING_POINT_FILTER = 22;

    /**
     * The number of structural features of the '<em>Folding Point Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOLDING_POINT_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.FoldingFilterImpl
     * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.FoldingFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFoldingFilter()
     * @generated
     */
    int FOLDING_FILTER = 23;

    /**
     * The number of structural features of the '<em>Folding Filter</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOLDING_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.AppliedCompositeFiltersImpl
     * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.AppliedCompositeFiltersImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAppliedCompositeFilters()
     * @generated
     */
    int APPLIED_COMPOSITE_FILTERS = 24;

    /**
     * The feature id for the '<em><b>Composite Filter Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Applied Composite Filters</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.AbsoluteBoundsFilterImpl
     * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.AbsoluteBoundsFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAbsoluteBoundsFilter()
     * @generated
     */
    int ABSOLUTE_BOUNDS_FILTER = 25;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__X = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__Y = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__HEIGHT = GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__WIDTH = GRAPHICAL_FILTER_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Absolute Bounds Filter</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DecorationImpl <em>Decoration</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DecorationImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDecoration()
     * @generated
     */
    int DECORATION = 26;

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
     * {@link org.eclipse.sirius.impl.DNavigationLinkImpl
     * <em>DNavigation Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNavigationLinkImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNavigationLink()
     * @generated
     */
    int DNAVIGATION_LINK = 27;

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
     * {@link org.eclipse.sirius.impl.DEObjectLinkImpl
     * <em>DE Object Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DEObjectLinkImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDEObjectLink()
     * @generated
     */
    int DE_OBJECT_LINK = 28;

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
     * {@link org.eclipse.sirius.impl.DDiagramLinkImpl
     * <em>DDiagram Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.impl.DDiagramLinkImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramLink()
     * @generated
     */
    int DDIAGRAM_LINK = 29;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_LINK__TARGET_TYPE = DNAVIGATION_LINK__TARGET_TYPE;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_LINK__LABEL = DNAVIGATION_LINK__LABEL;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_LINK__TARGET = DNAVIGATION_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Node</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_LINK__NODE = DNAVIGATION_LINK_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DDiagram Link</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_LINK_FEATURE_COUNT = DNAVIGATION_LINK_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DSourceFileLinkImpl
     * <em>DSource File Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DSourceFileLinkImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSourceFileLink()
     * @generated
     */
    int DSOURCE_FILE_LINK = 30;

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
     * The meta object id for the '{@link org.eclipse.sirius.AbstractDNode
     * <em>Abstract DNode</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.AbstractDNode
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAbstractDNode()
     * @generated
     */
    int ABSTRACT_DNODE = 31;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TARGET = DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__NAME = DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__VISIBLE = DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TOOLTIP_TEXT = DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__PARENT_LAYERS = DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DECORATIONS = DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Abstract DNode</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE_FEATURE_COUNT = DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.DNodeImpl
     * <em>DNode</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNodeImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNode()
     * @generated
     */
    int DNODE = 32;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DNODE__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OUTGOING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__INCOMING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__WIDTH = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__HEIGHT = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__LABEL_POSITION = ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Owned Details</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_DETAILS = ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__RESIZE_KIND = ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>DNode</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DDiagramElementContainerImpl
     * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DDiagramElementContainerImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramElementContainer()
     * @generated
     */
    int DDIAGRAM_ELEMENT_CONTAINER = 33;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NODES = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Owned Details</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS = ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '
     * <em>DDiagram Element Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DNodeContainerImpl
     * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNodeContainerImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeContainer()
     * @generated
     */
    int DNODE_CONTAINER = 34;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TARGET = DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NAME = DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__VISIBLE = DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TOOLTIP_TEXT = DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__PARENT_LAYERS = DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DECORATIONS = DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OUTGOING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__INCOMING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NODES = DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CONTAINERS = DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_STYLE = DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Owned Details</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_DETAILS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CANDIDATES_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CHILDREN_PRESENTATION = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DNode Container</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER_FEATURE_COUNT = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DNodeListImpl <em>DNode List</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNodeListImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeList()
     * @generated
     */
    int DNODE_LIST = 35;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__TARGET = DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__NAME = DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__VISIBLE = DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DNODE_LIST__TOOLTIP_TEXT = DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__PARENT_LAYERS = DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__DECORATIONS = DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OUTGOING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__INCOMING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__NODES = DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__CONTAINERS = DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_STYLE = DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Owned Details</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_DETAILS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__CANDIDATES_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Owned Elements</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Line Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__LINE_WIDTH = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DNode List</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_FEATURE_COUNT = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DNodeListElementImpl
     * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DNodeListElementImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeListElement()
     * @generated
     */
    int DNODE_LIST_ELEMENT = 36;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>DNode List Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.DEdgeImpl
     * <em>DEdge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DEdgeImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDEdge()
     * @generated
     */
    int DEDGE = 37;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__TARGET = DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__NAME = DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__VISIBLE = DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEDGE__TOOLTIP_TEXT = DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__PARENT_LAYERS = DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__DECORATIONS = DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OUTGOING_EDGES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__INCOMING_EDGES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OWNED_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SIZE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Node</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SOURCE_NODE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Node</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__TARGET_NODE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ROUTING_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Is Fold</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__IS_FOLD = DDIAGRAM_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Is Mock Edge</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__IS_MOCK_EDGE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__PATH = DDIAGRAM_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Begin Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__BEGIN_LABEL = DDIAGRAM_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>End Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__END_LABEL = DDIAGRAM_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>DEdge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE_FEATURE_COUNT = DDIAGRAM_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DDiagramSetImpl <em>DDiagram Set</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DDiagramSetImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramSet()
     * @generated
     */
    int DDIAGRAM_SET = 38;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_SET__DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Diagrams</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_SET__DIAGRAMS = 1;

    /**
     * The feature id for the '<em><b>View</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_SET__VIEW = 2;

    /**
     * The number of structural features of the '<em>DDiagram Set</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_SET_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.CustomizableImpl <em>Customizable</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.CustomizableImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCustomizable()
     * @generated
     */
    int CUSTOMIZABLE = 78;

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
     * {@link org.eclipse.sirius.impl.BasicLabelStyleImpl
     * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.BasicLabelStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBasicLabelStyle()
     * @generated
     */
    int BASIC_LABEL_STYLE = 73;

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
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
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
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__CUSTOM_FEATURES = LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_SIZE = LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_FORMAT = LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__SHOW_ICON = LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_COLOR = LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__ICON_PATH = LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_ALIGNMENT = LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__DESCRIPTION = LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE = LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_COLOR = LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_POSITION = LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__HIDE_LABEL_BY_DEFAULT = LABEL_STYLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Node Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_FEATURE_COUNT = LABEL_STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOT__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BACKGROUND_COLOR = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Stroke Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Dot</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__CUSTOM_FEATURES = CUSTOMIZABLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MIN = CUSTOMIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MAX = CUSTOMIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__VALUE = CUSTOMIZABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__LABEL = CUSTOMIZABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__BACKGROUND_COLOR = CUSTOMIZABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__FOREGROUND_COLOR = CUSTOMIZABLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Gauge Section</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_FEATURE_COUNT = CUSTOMIZABLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__CUSTOM_FEATURES = LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_SIZE = LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_FORMAT = LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__SHOW_ICON = LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_COLOR = LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__ICON_PATH = LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_ALIGNMENT = LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__DESCRIPTION = LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE = LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_COLOR = LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_FEATURE_COUNT = LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.FlatContainerStyleImpl
     * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.FlatContainerStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFlatContainerStyle()
     * @generated
     */
    int FLAT_CONTAINER_STYLE = 43;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__CUSTOM_FEATURES = CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_SIZE = CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_FORMAT = CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__SHOW_ICON = CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_COLOR = CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__ICON_PATH = CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_ALIGNMENT = CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__DESCRIPTION = CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE = CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_COLOR = CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Background Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Flat Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_FEATURE_COUNT = CONTAINER_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ShapeContainerStyleImpl
     * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ShapeContainerStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getShapeContainerStyle()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE = 44;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__CUSTOM_FEATURES = CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_SIZE = CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_FORMAT = CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHOW_ICON = CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_COLOR = CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__ICON_PATH = CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_ALIGNMENT = CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__DESCRIPTION = CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE = CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_COLOR = CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHAPE = CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Shape Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_FEATURE_COUNT = CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__WIDTH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__HEIGHT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Square</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.CustomStyleImpl <em>Custom Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.CustomStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCustomStyle()
     * @generated
     */
    int CUSTOM_STYLE = 50;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.BorderedStyleImpl
     * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.BorderedStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBorderedStyle()
     * @generated
     */
    int BORDERED_STYLE = 57;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.impl.NoteImpl
     * <em>Note</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.NoteImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getNote()
     * @generated
     */
    int NOTE = 58;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DragAndDropTargetImpl
     * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DragAndDropTargetImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDragAndDropTarget()
     * @generated
     */
    int DRAG_AND_DROP_TARGET = 59;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.FilterVariableHistoryImpl
     * <em>Filter Variable History</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.FilterVariableHistoryImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFilterVariableHistory()
     * @generated
     */
    int FILTER_VARIABLE_HISTORY = 60;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.FilterVariableValueImpl
     * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.FilterVariableValueImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFilterVariableValue()
     * @generated
     */
    int FILTER_VARIABLE_VALUE = 61;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.EllipseImpl <em>Ellipse</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.EllipseImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEllipse()
     * @generated
     */
    int ELLIPSE = 46;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Horizontal Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__HORIZONTAL_DIAMETER = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Vertical Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__VERTICAL_DIAMETER = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Ellipse</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.LozengeImpl <em>Lozenge</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.LozengeImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLozenge()
     * @generated
     */
    int LOZENGE = 47;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__WIDTH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__HEIGHT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Lozenge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHAPE = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__COLOR = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Bundled Image</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Workspace Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__WORKSPACE_PATH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Workspace Image</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ID = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Custom Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET__OUTGOING_EDGES = 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET__INCOMING_EDGES = 1;

    /**
     * The number of structural features of the '<em>Edge Target</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET_FEATURE_COUNT = 2;

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
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CUSTOM_FEATURES = STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__DESCRIPTION = STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__STROKE_COLOR = STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__LINE_STYLE = STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SOURCE_ARROW = STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__TARGET_ARROW = STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__FOLDING_STYLE = STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SIZE = STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__ROUTING_STYLE = STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__BEGIN_LABEL_STYLE = STYLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTER_LABEL_STYLE = STYLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__END_LABEL_STYLE = STYLE_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Edge Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.BracketEdgeStyleImpl
     * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.BracketEdgeStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBracketEdgeStyle()
     * @generated
     */
    int BRACKET_EDGE_STYLE = 77;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DAnalysisCustomDataImpl
     * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc --> ======= The meta object id for the '
     * {@link org.eclipse.sirius.impl.DAnalysisCustomDataImpl
     * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DAnalysisCustomDataImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysisCustomData()
     * @generated
     */
    int DANALYSIS_CUSTOM_DATA = 53;

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
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ALIGNMENT = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SECTIONS = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Gauge Composite Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__CUSTOM_FEATURES = STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__DESCRIPTION = STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE = STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_COLOR = STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Bordered Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NOTE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__COLOR = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Note</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Drag And Drop Target</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Owned Values</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY__OWNED_VALUES = 0;

    /**
     * The number of structural features of the '
     * <em>Filter Variable History</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Variable Definition</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION = 0;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE__MODEL_ELEMENT = 1;

    /**
     * The number of structural features of the '<em>Filter Variable Value</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.RGBValuesImpl <em>RGB Values</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.RGBValuesImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getRGBValues()
     * @generated
     */
    int RGB_VALUES = 62;

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
     * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl
     * <em>DAnalysis Session EObject</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysisSessionEObject()
     * @generated
     */
    int DANALYSIS_SESSION_EOBJECT = 63;

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
     * The feature id for the '<em><b>Activated Siriuss</b></em>' reference
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
     * {@link org.eclipse.sirius.impl.CollapseFilterImpl
     * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.CollapseFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCollapseFilter()
     * @generated
     */
    int COLLAPSE_FILTER = 64;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__WIDTH = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__HEIGHT = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Collapse Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.IndirectlyCollapseFilterImpl
     * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.IndirectlyCollapseFilterImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getIndirectlyCollapseFilter()
     * @generated
     */
    int INDIRECTLY_COLLAPSE_FILTER = 65;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__WIDTH = COLLAPSE_FILTER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__HEIGHT = COLLAPSE_FILTER__HEIGHT;

    /**
     * The number of structural features of the '
     * <em>Indirectly Collapse Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER_FEATURE_COUNT = COLLAPSE_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.SessionManagerEObjectImpl
     * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.SessionManagerEObjectImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSessionManagerEObject()
     * @generated
     */
    int SESSION_MANAGER_EOBJECT = 66;

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
     * The meta object id for the '{@link org.eclipse.sirius.DResource
     * <em>DResource</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.DResource
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDResource()
     * @generated
     */
    int DRESOURCE = 67;

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
     * The meta object id for the '{@link org.eclipse.sirius.impl.DFileImpl
     * <em>DFile</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DFileImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFile()
     * @generated
     */
    int DFILE = 68;

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
     * {@link org.eclipse.sirius.impl.DResourceContainerImpl
     * <em>DResource Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DResourceContainerImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDResourceContainer()
     * @generated
     */
    int DRESOURCE_CONTAINER = 69;

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
     * {@link org.eclipse.sirius.impl.DProjectImpl <em>DProject</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DProjectImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDProject()
     * @generated
     */
    int DPROJECT = 70;

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
     * {@link org.eclipse.sirius.impl.DFolderImpl <em>DFolder</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DFolderImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFolder()
     * @generated
     */
    int DFOLDER = 71;

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
     * The meta object id for the '{@link org.eclipse.sirius.impl.DModelImpl
     * <em>DModel</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DModelImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDModel()
     * @generated
     */
    int DMODEL = 72;

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
     * {@link org.eclipse.sirius.impl.BeginLabelStyleImpl
     * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.BeginLabelStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBeginLabelStyle()
     * @generated
     */
    int BEGIN_LABEL_STYLE = 74;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__CUSTOM_FEATURES = BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_SIZE = BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_FORMAT = BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__SHOW_ICON = BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_COLOR = BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__ICON_PATH = BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The number of structural features of the '<em>Begin Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_FEATURE_COUNT = BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.CenterLabelStyleImpl
     * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.CenterLabelStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCenterLabelStyle()
     * @generated
     */
    int CENTER_LABEL_STYLE = 75;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__CUSTOM_FEATURES = BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_SIZE = BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_FORMAT = BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__SHOW_ICON = BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_COLOR = BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__ICON_PATH = BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The number of structural features of the '<em>Center Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_FEATURE_COUNT = BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.EndLabelStyleImpl
     * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.EndLabelStyleImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEndLabelStyle()
     * @generated
     */
    int END_LABEL_STYLE = 76;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__CUSTOM_FEATURES = BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_SIZE = BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_FORMAT = BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__SHOW_ICON = BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_COLOR = BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__ICON_PATH = BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The number of structural features of the '<em>End Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_FEATURE_COUNT = BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CUSTOM_FEATURES = EDGE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__DESCRIPTION = EDGE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__STROKE_COLOR = EDGE_STYLE__STROKE_COLOR;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__LINE_STYLE = EDGE_STYLE__LINE_STYLE;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SOURCE_ARROW = EDGE_STYLE__SOURCE_ARROW;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__TARGET_ARROW = EDGE_STYLE__TARGET_ARROW;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__FOLDING_STYLE = EDGE_STYLE__FOLDING_STYLE;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SIZE = EDGE_STYLE__SIZE;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__ROUTING_STYLE = EDGE_STYLE__ROUTING_STYLE;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__BEGIN_LABEL_STYLE = EDGE_STYLE__BEGIN_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CENTER_LABEL_STYLE = EDGE_STYLE__CENTER_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__END_LABEL_STYLE = EDGE_STYLE__END_LABEL_STYLE;

    /**
     * The number of structural features of the '<em>Bracket Edge Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_FEATURE_COUNT = EDGE_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ComputedStyleDescriptionRegistryImpl
     * <em>Computed Style Description Registry</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ComputedStyleDescriptionRegistryImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getComputedStyleDescriptionRegistry()
     * @generated
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY = 79;

    /**
     * The feature id for the '<em><b>Computed Style Descriptions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = 0;

    /**
     * The feature id for the '<em><b>Cache</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE = 1;

    /**
     * The number of structural features of the '
     * <em>Computed Style Description Registry</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.DiagramElementMapping2ModelElementImpl
     * <em>Diagram Element Mapping2 Model Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.DiagramElementMapping2ModelElementImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDiagramElementMapping2ModelElement()
     * @generated
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT = 80;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Diagram Element Mapping2 Model Element</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ModelElement2ViewVariableImpl
     * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ModelElement2ViewVariableImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getModelElement2ViewVariable()
     * @generated
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE = 81;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Model Element2 View Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ViewVariable2ContainerVariableImpl
     * <em>View Variable2 Container Variable</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ViewVariable2ContainerVariableImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getViewVariable2ContainerVariable()
     * @generated
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE = 82;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>View Variable2 Container Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.impl.ContainerVariable2StyleDescriptionImpl
     * <em>Container Variable2 Style Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.impl.ContainerVariable2StyleDescriptionImpl
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerVariable2StyleDescription()
     * @generated
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION = 83;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Container Variable2 Style Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.ContainerLayout
     * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.ContainerLayout
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerLayout()
     * @generated
     */
    int CONTAINER_LAYOUT = 84;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.LabelPosition
     * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.LabelPosition
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelPosition()
     * @generated
     */
    int LABEL_POSITION = 85;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.ContainerShape
     * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.ContainerShape
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerShape()
     * @generated
     */
    int CONTAINER_SHAPE = 86;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.BackgroundStyle
     * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.BackgroundStyle
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBackgroundStyle()
     * @generated
     */
    int BACKGROUND_STYLE = 87;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.BundledImageShape
     * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.BundledImageShape
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBundledImageShape()
     * @generated
     */
    int BUNDLED_IMAGE_SHAPE = 88;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.LineStyle
     * <em>Line Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.LineStyle
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 89;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.EdgeArrows
     * <em>Edge Arrows</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.EdgeArrows
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeArrows()
     * @generated
     */
    int EDGE_ARROWS = 90;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.EdgeRouting
     * <em>Edge Routing</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.EdgeRouting
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeRouting()
     * @generated
     */
    int EDGE_ROUTING = 91;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.AlignmentKind
     * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.AlignmentKind
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAlignmentKind()
     * @generated
     */
    int ALIGNMENT_KIND = 92;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.FontFormat
     * <em>Font Format</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.FontFormat
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFontFormat()
     * @generated
     */
    int FONT_FORMAT = 93;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.ResizeKind
     * <em>Resize Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.ResizeKind
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getResizeKind()
     * @generated
     */
    int RESIZE_KIND = 94;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.LabelAlignment
     * <em>Label Alignment</em>}' enum. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @see org.eclipse.sirius.LabelAlignment
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelAlignment()
     * @generated
     */
    int LABEL_ALIGNMENT = 95;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.ArrangeConstraint
     * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.ArrangeConstraint
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getArrangeConstraint()
     * @generated
     */
    int ARRANGE_CONSTRAINT = 96;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.SyncStatus
     * <em>Sync Status</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.SyncStatus
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSyncStatus()
     * @generated
     */
    int SYNC_STATUS = 97;

    /**
     * The meta object id for the '<em>Extended Package</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
     * @see org.eclipse.sirius.impl.SiriusPackageImpl#getExtendedPackage()
     * @generated
     */
    int EXTENDED_PACKAGE = 98;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis</em>'.
     * @see org.eclipse.sirius.DAnalysis
     * @generated
     */
    EClass getDAnalysis();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DAnalysis#getOwnedViews
     * <em>Owned Views</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Views</em>'.
     * @see org.eclipse.sirius.DAnalysis#getOwnedViews()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedViews();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DAnalysis#getSelectedViews
     * <em>Selected Views</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Selected Views</em>'.
     * @see org.eclipse.sirius.DAnalysis#getSelectedViews()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_SelectedViews();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DAnalysis#getOwnedFeatureExtensions
     * <em>Owned Feature Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Feature Extensions</em>'.
     * @see org.eclipse.sirius.DAnalysis#getOwnedFeatureExtensions()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedFeatureExtensions();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DAnalysis#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.DAnalysis#getVersion()
     * @see #getDAnalysis()
     * @generated
     */
    EAttribute getDAnalysis_Version();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DAnalysis#getReferencedAnalysis
     * <em>Referenced Analysis</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Referenced Analysis</em>'.
     * @see org.eclipse.sirius.DAnalysis#getReferencedAnalysis()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_ReferencedAnalysis();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DAnalysis#getModels <em>Models</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.DAnalysis#getModels()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_Models();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DAnalysis#getEAnnotations
     * <em>EAnnotations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>EAnnotations</em>'.
     * @see org.eclipse.sirius.DAnalysis#getEAnnotations()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_EAnnotations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DFeatureExtension
     * <em>DFeature Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DFeature Extension</em>'.
     * @see org.eclipse.sirius.DFeatureExtension
     * @generated
     */
    EClass getDFeatureExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DFeatureExtension#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.DFeatureExtension#getDescription()
     * @see #getDFeatureExtension()
     * @generated
     */
    EReference getDFeatureExtension_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DValidable <em>DValidable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DValidable</em>'.
     * @see org.eclipse.sirius.DValidable
     * @generated
     */
    EClass getDValidable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DNavigable <em>DNavigable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNavigable</em>'.
     * @see org.eclipse.sirius.DNavigable
     * @generated
     */
    EClass getDNavigable();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DNavigable#getOwnedNavigationLinks
     * <em>Owned Navigation Links</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Navigation Links</em>'.
     * @see org.eclipse.sirius.DNavigable#getOwnedNavigationLinks()
     * @see #getDNavigable()
     * @generated
     */
    EReference getDNavigable_OwnedNavigationLinks();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DStylizable <em>DStylizable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DStylizable</em>'.
     * @see org.eclipse.sirius.DStylizable
     * @generated
     */
    EClass getDStylizable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DRefreshable <em>DRefreshable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DRefreshable</em>'.
     * @see org.eclipse.sirius.DRefreshable
     * @generated
     */
    EClass getDRefreshable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DLabelled <em>DLabelled</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DLabelled</em>'.
     * @see org.eclipse.sirius.DLabelled
     * @generated
     */
    EClass getDLabelled();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DMappingBased <em>DMapping Based</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DMapping Based</em>'.
     * @see org.eclipse.sirius.DMappingBased
     * @generated
     */
    EClass getDMappingBased();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DContainer <em>DContainer</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DContainer</em>'.
     * @see org.eclipse.sirius.DContainer
     * @generated
     */
    EClass getDContainer();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DRepresentationContainer
     * <em>DRepresentation Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation Container</em>'.
     * @see org.eclipse.sirius.DRepresentationContainer
     * @generated
     */
    EClass getDRepresentationContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DRepresentationContainer#getDiagramSet
     * <em>Diagram Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Diagram Set</em>'.
     * @see org.eclipse.sirius.DRepresentationContainer#getDiagramSet()
     * @see #getDRepresentationContainer()
     * @generated
     */
    EReference getDRepresentationContainer_DiagramSet();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DRepresentationContainer#getModels
     * <em>Models</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.DRepresentationContainer#getModels()
     * @see #getDRepresentationContainer()
     * @generated
     */
    EReference getDRepresentationContainer_Models();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DSemantic Decorator</em>'.
     * @see org.eclipse.sirius.DSemanticDecorator
     * @generated
     */
    EClass getDSemanticDecorator();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DSemanticDecorator#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.DSemanticDecorator#getTarget()
     * @see #getDSemanticDecorator()
     * @generated
     */
    EReference getDSemanticDecorator_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DRepresentation <em>DRepresentation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation</em>'.
     * @see org.eclipse.sirius.DRepresentation
     * @generated
     */
    EClass getDRepresentation();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DRepresentation#getOwnedRepresentationElements
     * <em>Owned Representation Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Owned Representation Elements</em>'.
     * @see org.eclipse.sirius.DRepresentation#getOwnedRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedRepresentationElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DRepresentation#getRepresentationElements
     * <em>Representation Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Representation Elements</em>'.
     * @see org.eclipse.sirius.DRepresentation#getRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_RepresentationElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DRepresentation#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.DRepresentation#getName()
     * @see #getDRepresentation()
     * @generated
     */
    EAttribute getDRepresentation_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DRepresentation#getOwnedAnnotationEntries
     * <em>Owned Annotation Entries</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Annotation Entries</em>'.
     * @see org.eclipse.sirius.DRepresentation#getOwnedAnnotationEntries()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedAnnotationEntries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.ExtensibleRepresentation
     * <em>Extensible Representation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Extensible Representation</em>'.
     * @see org.eclipse.sirius.ExtensibleRepresentation
     * @generated
     */
    EClass getExtensibleRepresentation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.ExtensibleRepresentation#getEffectiveDescription
     * <em>Effective Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Effective Description</em>'.
     * @see org.eclipse.sirius.ExtensibleRepresentation#getEffectiveDescription()
     * @see #getExtensibleRepresentation()
     * @generated
     */
    EReference getExtensibleRepresentation_EffectiveDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.ExtensibleRepresentation#getContributionPoints
     * <em>Contribution Points</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Contribution Points</em>'.
     * @see org.eclipse.sirius.ExtensibleRepresentation#getContributionPoints()
     * @see #getExtensibleRepresentation()
     * @generated
     */
    EReference getExtensibleRepresentation_ContributionPoints();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DRepresentation Element</em>'.
     * @see org.eclipse.sirius.DRepresentationElement
     * @generated
     */
    EClass getDRepresentationElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DRepresentationElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.DRepresentationElement#getName()
     * @see #getDRepresentationElement()
     * @generated
     */
    EAttribute getDRepresentationElement_Name();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DRepresentationElement#getSemanticElements
     * <em>Semantic Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.DRepresentationElement#getSemanticElements()
     * @see #getDRepresentationElement()
     * @generated
     */
    EReference getDRepresentationElement_SemanticElements();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DView
     * <em>DView</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DView</em>'.
     * @see org.eclipse.sirius.DView
     * @generated
     */
    EClass getDView();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DView#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.DView#getOwnedRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedRepresentations();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DView#getOwnedExtensions
     * <em>Owned Extensions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Extensions</em>'.
     * @see org.eclipse.sirius.DView#getOwnedExtensions()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedExtensions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DView#getAllRepresentations
     * <em>All Representations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Representations</em>'.
     * @see org.eclipse.sirius.DView#getAllRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_AllRepresentations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DView#getHiddenRepresentations
     * <em>Hidden Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Hidden Representations</em>'.
     * @see org.eclipse.sirius.DView#getHiddenRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_HiddenRepresentations();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DView#getReferencedRepresentations
     * <em>Referenced Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Referenced Representations</em>'.
     * @see org.eclipse.sirius.DView#getReferencedRepresentations()
     * @see #getDView()
     * @generated
     */
    EReference getDView_ReferencedRepresentations();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DView#isInitialized <em>Initialized</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Initialized</em>'.
     * @see org.eclipse.sirius.DView#isInitialized()
     * @see #getDView()
     * @generated
     */
    EAttribute getDView_Initialized();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DView#getSirius <em>Sirius</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Sirius</em>'.
     * @see org.eclipse.sirius.DView#getSirius()
     * @see #getDView()
     * @generated
     */
    EReference getDView_Sirius();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.MetaModelExtension
     * <em>Meta Model Extension</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Meta Model Extension</em>'.
     * @see org.eclipse.sirius.MetaModelExtension
     * @generated
     */
    EClass getMetaModelExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.MetaModelExtension#getExtensionGroup
     * <em>Extension Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Extension Group</em>'.
     * @see org.eclipse.sirius.MetaModelExtension#getExtensionGroup()
     * @see #getMetaModelExtension()
     * @generated
     */
    EReference getMetaModelExtension_ExtensionGroup();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DDiagram
     * <em>DDiagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram</em>'.
     * @see org.eclipse.sirius.DDiagram
     * @generated
     */
    EClass getDDiagram();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DDiagram#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.DDiagram#getOwnedDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_OwnedDiagramElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getDiagramElements
     * <em>Diagram Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Diagram Elements</em>
     *         '.
     * @see org.eclipse.sirius.DDiagram#getDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_DiagramElements();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagram#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.DDiagram#getDescription()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Description();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagram#getInfo <em>Info</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Info</em>'.
     * @see org.eclipse.sirius.DDiagram#getInfo()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Info();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DDiagram#getSubDiagrams
     * <em>Sub Diagrams</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Diagrams</em>'.
     * @see org.eclipse.sirius.DDiagram#getSubDiagrams()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_SubDiagrams();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getEdges <em>Edges</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see org.eclipse.sirius.DDiagram#getEdges()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Edges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getNodes <em>Nodes</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.DDiagram#getNodes()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getNodeListElements
     * <em>Node List Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Node List Elements</em>'.
     * @see org.eclipse.sirius.DDiagram#getNodeListElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_NodeListElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getContainers <em>Containers</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.DDiagram#getContainers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Containers();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagram#getCurrentConcern
     * <em>Current Concern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Current Concern</em>'.
     * @see org.eclipse.sirius.DDiagram#getCurrentConcern()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_CurrentConcern();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getActivatedFilters
     * <em>Activated Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activated Filters</em>'.
     * @see org.eclipse.sirius.DDiagram#getActivatedFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getAllFilters <em>All Filters</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Filters</em>'.
     * @see org.eclipse.sirius.DDiagram#getAllFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_AllFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getActivatedRules
     * <em>Activated Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Activated Rules</em>
     *         '.
     * @see org.eclipse.sirius.DDiagram#getActivatedRules()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedRules();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getActivateBehaviors
     * <em>Activate Behaviors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activate Behaviors</em>'.
     * @see org.eclipse.sirius.DDiagram#getActivateBehaviors()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivateBehaviors();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DDiagram#getFilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.DDiagram#getFilterVariableHistory()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_FilterVariableHistory();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getActivatedLayers
     * <em>Activated Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Activated Layers</em>
     *         '.
     * @see org.eclipse.sirius.DDiagram#getActivatedLayers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedLayers();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagram#isSynchronized
     * <em>Synchronized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Synchronized</em>'.
     * @see org.eclipse.sirius.DDiagram#isSynchronized()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Synchronized();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagram#getHiddenElements
     * <em>Hidden Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Hidden Elements</em>
     *         '.
     * @see org.eclipse.sirius.DDiagram#getHiddenElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_HiddenElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagram#isIsInLayoutingMode
     * <em>Is In Layouting Mode</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is In Layouting Mode</em>
     *         '.
     * @see org.eclipse.sirius.DDiagram#isIsInLayoutingMode()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_IsInLayoutingMode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagram#getHeaderHeight
     * <em>Header Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Header Height</em>'.
     * @see org.eclipse.sirius.DDiagram#getHeaderHeight()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_HeaderHeight();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DSemanticDiagram <em>DSemantic Diagram</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DSemantic Diagram</em>'.
     * @see org.eclipse.sirius.DSemanticDiagram
     * @generated
     */
    EClass getDSemanticDiagram();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DDiagramElement <em>DDiagram Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Element</em>'.
     * @see org.eclipse.sirius.DDiagramElement
     * @generated
     */
    EClass getDDiagramElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagramElement#isVisible <em>Visible</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.DDiagramElement#isVisible()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_Visible();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}'. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tooltip Text</em>'.
     * @see org.eclipse.sirius.DDiagramElement#getTooltipText()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_TooltipText();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramElement#getParentLayers
     * <em>Parent Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Parent Layers</em>'.
     * @see org.eclipse.sirius.DDiagramElement#getParentLayers()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_ParentLayers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DDiagramElement#getDecorations
     * <em>Decorations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Decorations</em>'.
     * @see org.eclipse.sirius.DDiagramElement#getDecorations()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_Decorations();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramElement#getDiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.DDiagramElement#getDiagramElementMapping()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_DiagramElementMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DDiagramElement#getGraphicalFilters
     * <em>Graphical Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Graphical Filters</em>'.
     * @see org.eclipse.sirius.DDiagramElement#getGraphicalFilters()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_GraphicalFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.GraphicalFilter <em>Graphical Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Graphical Filter</em>'.
     * @see org.eclipse.sirius.GraphicalFilter
     * @generated
     */
    EClass getGraphicalFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.HideFilter <em>Hide Filter</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hide Filter</em>'.
     * @see org.eclipse.sirius.HideFilter
     * @generated
     */
    EClass getHideFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.HideLabelFilter <em>Hide Label Filter</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hide Label Filter</em>'.
     * @see org.eclipse.sirius.HideLabelFilter
     * @generated
     */
    EClass getHideLabelFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.FoldingPointFilter
     * <em>Folding Point Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Point Filter</em>'.
     * @see org.eclipse.sirius.FoldingPointFilter
     * @generated
     */
    EClass getFoldingPointFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.FoldingFilter <em>Folding Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Filter</em>'.
     * @see org.eclipse.sirius.FoldingFilter
     * @generated
     */
    EClass getFoldingFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Applied Composite Filters</em>'.
     * @see org.eclipse.sirius.AppliedCompositeFilters
     * @generated
     */
    EClass getAppliedCompositeFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.AppliedCompositeFilters#getCompositeFilterDescriptions
     * <em>Composite Filter Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Composite Filter Descriptions</em>'.
     * @see org.eclipse.sirius.AppliedCompositeFilters#getCompositeFilterDescriptions()
     * @see #getAppliedCompositeFilters()
     * @generated
     */
    EReference getAppliedCompositeFilters_CompositeFilterDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter
     * <em>Absolute Bounds Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Absolute Bounds Filter</em>'.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter
     * @generated
     */
    EClass getAbsoluteBoundsFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter#getX <em>X</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter#getX()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter#getY <em>Y</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter#getY()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Y();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter#getHeight()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Height();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter#getWidth()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Width();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.Decoration <em>Decoration</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration</em>'.
     * @see org.eclipse.sirius.Decoration
     * @generated
     */
    EClass getDecoration();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.Decoration#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.Decoration#getDescription()
     * @see #getDecoration()
     * @generated
     */
    EReference getDecoration_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DNavigationLink <em>DNavigation Link</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNavigation Link</em>'.
     * @see org.eclipse.sirius.DNavigationLink
     * @generated
     */
    EClass getDNavigationLink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNavigationLink#getTargetType
     * <em>Target Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Type</em>'.
     * @see org.eclipse.sirius.DNavigationLink#getTargetType()
     * @see #getDNavigationLink()
     * @generated
     */
    EAttribute getDNavigationLink_TargetType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNavigationLink#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.DNavigationLink#getLabel()
     * @see #getDNavigationLink()
     * @generated
     */
    EAttribute getDNavigationLink_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DEObjectLink <em>DE Object Link</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DE Object Link</em>'.
     * @see org.eclipse.sirius.DEObjectLink
     * @generated
     */
    EClass getDEObjectLink();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DEObjectLink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.DEObjectLink#getTarget()
     * @see #getDEObjectLink()
     * @generated
     */
    EReference getDEObjectLink_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DDiagramLink <em>DDiagram Link</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Link</em>'.
     * @see org.eclipse.sirius.DDiagramLink
     * @generated
     */
    EClass getDDiagramLink();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramLink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.DDiagramLink#getTarget()
     * @see #getDDiagramLink()
     * @generated
     */
    EReference getDDiagramLink_Target();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramLink#getNode <em>Node</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Node</em>'.
     * @see org.eclipse.sirius.DDiagramLink#getNode()
     * @see #getDDiagramLink()
     * @generated
     */
    EReference getDDiagramLink_Node();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DSourceFileLink <em>DSource File Link</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DSource File Link</em>'.
     * @see org.eclipse.sirius.DSourceFileLink
     * @generated
     */
    EClass getDSourceFileLink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DSourceFileLink#getFilePath
     * <em>File Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>File Path</em>'.
     * @see org.eclipse.sirius.DSourceFileLink#getFilePath()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_FilePath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DSourceFileLink#getStartPosition
     * <em>Start Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Start Position</em>'.
     * @see org.eclipse.sirius.DSourceFileLink#getStartPosition()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_StartPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DSourceFileLink#getEndPosition
     * <em>End Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>End Position</em>'.
     * @see org.eclipse.sirius.DSourceFileLink#getEndPosition()
     * @see #getDSourceFileLink()
     * @generated
     */
    EAttribute getDSourceFileLink_EndPosition();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.AbstractDNode <em>Abstract DNode</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract DNode</em>'.
     * @see org.eclipse.sirius.AbstractDNode
     * @generated
     */
    EClass getAbstractDNode();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.AbstractDNode#getOwnedBorderedNodes
     * <em>Owned Bordered Nodes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Bordered Nodes</em>'.
     * @see org.eclipse.sirius.AbstractDNode#getOwnedBorderedNodes()
     * @see #getAbstractDNode()
     * @generated
     */
    EReference getAbstractDNode_OwnedBorderedNodes();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.AbstractDNode#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.AbstractDNode#getArrangeConstraints()
     * @see #getAbstractDNode()
     * @generated
     */
    EAttribute getAbstractDNode_ArrangeConstraints();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DNode
     * <em>DNode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode</em>'.
     * @see org.eclipse.sirius.DNode
     * @generated
     */
    EClass getDNode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNode#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.DNode#getWidth()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNode#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.DNode#getHeight()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DNode#getOwnedStyle <em>Owned Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.DNode#getOwnedStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNode#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.DNode#getLabelPosition()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_LabelPosition();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DNode#getOwnedDetails
     * <em>Owned Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Details</em>'.
     * @see org.eclipse.sirius.DNode#getOwnedDetails()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedDetails();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNode#getResizeKind <em>Resize Kind</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.DNode#getResizeKind()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_ResizeKind();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DNode#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.DNode#getOriginalStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DNode#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.DNode#getActualMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DNode#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.DNode#getCandidatesMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Element Container</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer
     * @generated
     */
    EClass getDDiagramElementContainer();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getNodes()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getContainers()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Containers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getElements()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Elements();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getOwnedStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedStyle();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getOwnedDetails
     * <em>Owned Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Details</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getOwnedDetails()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedDetails();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getOriginalStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getActualMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramElementContainer#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.DDiagramElementContainer#getCandidatesMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DNodeContainer <em>DNode Container</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode Container</em>'.
     * @see org.eclipse.sirius.DNodeContainer
     * @generated
     */
    EClass getDNodeContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DNodeContainer#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.DNodeContainer#getOwnedDiagramElements()
     * @see #getDNodeContainer()
     * @generated
     */
    EReference getDNodeContainer_OwnedDiagramElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNodeContainer#getChildrenPresentation
     * <em>Children Presentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Children Presentation</em>
     *         '.
     * @see org.eclipse.sirius.DNodeContainer#getChildrenPresentation()
     * @see #getDNodeContainer()
     * @generated
     */
    EAttribute getDNodeContainer_ChildrenPresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode List</em>'.
     * @see org.eclipse.sirius.DNodeList
     * @generated
     */
    EClass getDNodeList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DNodeList#getOwnedElements
     * <em>Owned Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Elements</em>'.
     * @see org.eclipse.sirius.DNodeList#getOwnedElements()
     * @see #getDNodeList()
     * @generated
     */
    EReference getDNodeList_OwnedElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DNodeList#getLineWidth <em>Line Width</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Width</em>'.
     * @see org.eclipse.sirius.DNodeList#getLineWidth()
     * @see #getDNodeList()
     * @generated
     */
    EAttribute getDNodeList_LineWidth();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DNodeListElement
     * <em>DNode List Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DNode List Element</em>'.
     * @see org.eclipse.sirius.DNodeListElement
     * @generated
     */
    EClass getDNodeListElement();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DNodeListElement#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.DNodeListElement#getOwnedStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DNodeListElement#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.DNodeListElement#getOriginalStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DNodeListElement#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.DNodeListElement#getActualMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DNodeListElement#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.DNodeListElement#getCandidatesMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_CandidatesMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DEdge
     * <em>DEdge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DEdge</em>'.
     * @see org.eclipse.sirius.DEdge
     * @generated
     */
    EClass getDEdge();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DEdge#getOwnedStyle <em>Owned Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.DEdge#getOwnedStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.DEdge#getSize()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_Size();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DEdge#getSourceNode <em>Source Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Source Node</em>'.
     * @see org.eclipse.sirius.DEdge#getSourceNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_SourceNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DEdge#getTargetNode <em>Target Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target Node</em>'.
     * @see org.eclipse.sirius.DEdge#getTargetNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_TargetNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DEdge#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.DEdge#getActualMapping()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_ActualMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.DEdge#getRoutingStyle()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_RoutingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#isIsFold <em>Is Fold</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Fold</em>'.
     * @see org.eclipse.sirius.DEdge#isIsFold()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsFold();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#isIsMockEdge <em>Is Mock Edge</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Mock Edge</em>'.
     * @see org.eclipse.sirius.DEdge#isIsMockEdge()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsMockEdge();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DEdge#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.DEdge#getOriginalStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OriginalStyle();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DEdge#getPath <em>Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Path</em>'.
     * @see org.eclipse.sirius.DEdge#getPath()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_Path();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.DEdge#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.DEdge#getArrangeConstraints()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_ArrangeConstraints();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#getBeginLabel <em>Begin Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Begin Label</em>'.
     * @see org.eclipse.sirius.DEdge#getBeginLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_BeginLabel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DEdge#getEndLabel <em>End Label</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>End Label</em>'.
     * @see org.eclipse.sirius.DEdge#getEndLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_EndLabel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DDiagramSet <em>DDiagram Set</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Set</em>'.
     * @see org.eclipse.sirius.DDiagramSet
     * @generated
     */
    EClass getDDiagramSet();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramSet#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.DDiagramSet#getDescription()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_Description();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DDiagramSet#getDiagrams <em>Diagrams</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Diagrams</em>'.
     * @see org.eclipse.sirius.DDiagramSet#getDiagrams()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_Diagrams();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.DDiagramSet#getView <em>View</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>View</em>'.
     * @see org.eclipse.sirius.DDiagramSet#getView()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_View();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.NodeStyle#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.NodeStyle#getLabelPosition()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.NodeStyle#isHideLabelByDefault
     * <em>Hide Label By Default</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Hide Label By Default</em>
     *         '.
     * @see org.eclipse.sirius.NodeStyle#isHideLabelByDefault()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_HideLabelByDefault();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Dot
     * <em>Dot</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dot</em>'.
     * @see org.eclipse.sirius.Dot
     * @generated
     */
    EClass getDot();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.Dot#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.Dot#getBackgroundColor()
     * @see #getDot()
     * @generated
     */
    EReference getDot_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Dot#getStrokeSizeComputationExpression
     * <em>Stroke Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Stroke Size Computation Expression</em>'.
     * @see org.eclipse.sirius.Dot#getStrokeSizeComputationExpression()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_StrokeSizeComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.ContainerStyle <em>Container Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Style</em>'.
     * @see org.eclipse.sirius.ContainerStyle
     * @generated
     */
    EClass getContainerStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Flat Container Style</em>'.
     * @see org.eclipse.sirius.FlatContainerStyle
     * @generated
     */
    EClass getFlatContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Color</em>'.
     * @see org.eclipse.sirius.FlatContainerStyle#getBackgroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.FlatContainerStyle#getBackgroundStyle()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.FlatContainerStyle#getForegroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Shape Container Style</em>'.
     * @see org.eclipse.sirius.ShapeContainerStyle
     * @generated
     */
    EClass getShapeContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.ShapeContainerStyle#getShape <em>Shape</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.ShapeContainerStyle#getShape()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_Shape();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Color</em>'.
     * @see org.eclipse.sirius.ShapeContainerStyle#getBackgroundColor()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EReference getShapeContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Square
     * <em>Square</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Square</em>'.
     * @see org.eclipse.sirius.Square
     * @generated
     */
    EClass getSquare();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Square#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.Square#getWidth()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Square#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.Square#getHeight()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.Square#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.Square#getColor()
     * @see #getSquare()
     * @generated
     */
    EReference getSquare_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.BundledImage <em>Bundled Image</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bundled Image</em>'.
     * @see org.eclipse.sirius.BundledImage
     * @generated
     */
    EClass getBundledImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BundledImage#getShape <em>Shape</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.BundledImage#getShape()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.BundledImage#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.BundledImage#getColor()
     * @see #getBundledImage()
     * @generated
     */
    EReference getBundledImage_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.WorkspaceImage <em>Workspace Image</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Workspace Image</em>'.
     * @see org.eclipse.sirius.WorkspaceImage
     * @generated
     */
    EClass getWorkspaceImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.WorkspaceImage#getWorkspacePath()
     * @see #getWorkspaceImage()
     * @generated
     */
    EAttribute getWorkspaceImage_WorkspacePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.CustomStyle <em>Custom Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Custom Style</em>'.
     * @see org.eclipse.sirius.CustomStyle
     * @generated
     */
    EClass getCustomStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.CustomStyle#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.CustomStyle#getId()
     * @see #getCustomStyle()
     * @generated
     */
    EAttribute getCustomStyle_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.GaugeSection <em>Gauge Section</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Section</em>'.
     * @see org.eclipse.sirius.GaugeSection
     * @generated
     */
    EClass getGaugeSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.GaugeSection#getMin <em>Min</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.eclipse.sirius.GaugeSection#getMin()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Min();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.GaugeSection#getMax <em>Max</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.eclipse.sirius.GaugeSection#getMax()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Max();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.GaugeSection#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.GaugeSection#getValue()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Value();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.GaugeSection#getLabel <em>Label</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.GaugeSection#getLabel()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Label();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.GaugeSection#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.GaugeSection#getBackgroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.GaugeSection#getForegroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.EdgeTarget <em>Edge Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Target</em>'.
     * @see org.eclipse.sirius.EdgeTarget
     * @generated
     */
    EClass getEdgeTarget();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.EdgeTarget#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.EdgeTarget#getOutgoingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_OutgoingEdges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.EdgeTarget#getIncomingEdges
     * <em>Incoming Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see org.eclipse.sirius.EdgeTarget#getIncomingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_IncomingEdges();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.EdgeStyle#getStrokeColor
     * <em>Stroke Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Stroke Color</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getStrokeColor()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_StrokeColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getLineStyle <em>Line Style</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getLineStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getSourceArrow
     * <em>Source Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getSourceArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_SourceArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getTargetArrow
     * <em>Target Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getTargetArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_TargetArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getFoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getFoldingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_FoldingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getSize()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Size();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getRoutingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_RoutingStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getBeginLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_BeginLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Center Label Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getCenterLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_CenterLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>End Label Style</em>'.
     * @see org.eclipse.sirius.EdgeStyle#getEndLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_EndLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.BracketEdgeStyle
     * <em>Bracket Edge Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Bracket Edge Style</em>'.
     * @see org.eclipse.sirius.BracketEdgeStyle
     * @generated
     */
    EClass getBracketEdgeStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.Customizable <em>Customizable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Customizable</em>'.
     * @see org.eclipse.sirius.Customizable
     * @generated
     */
    EClass getCustomizable();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.Customizable#getCustomFeatures
     * <em>Custom Features</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Custom Features</em>
     *         '.
     * @see org.eclipse.sirius.Customizable#getCustomFeatures()
     * @see #getCustomizable()
     * @generated
     */
    EAttribute getCustomizable_CustomFeatures();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Computed Style Description Registry</em>'.
     * @see org.eclipse.sirius.ComputedStyleDescriptionRegistry
     * @generated
     */
    EClass getComputedStyleDescriptionRegistry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions
     * <em>Computed Style Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Computed Style Descriptions</em>'.
     * @see org.eclipse.sirius.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.ComputedStyleDescriptionRegistry#getCache
     * <em>Cache</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Cache</em>'.
     * @see org.eclipse.sirius.ComputedStyleDescriptionRegistry#getCache()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_Cache();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Diagram Element Mapping2 Model Element</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Diagram Element Mapping2 Model Element</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.sirius.description.DiagramElementMapping"
     *        keyRequired="true" valueMapType=
     *        "org.eclipse.sirius.ModelElement2ViewVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.ViewVariable2ContainerVariable>"
     * @generated
     */
    EClass getDiagramElementMapping2ModelElement();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getDiagramElementMapping2ModelElement()
     * @generated
     */
    EReference getDiagramElementMapping2ModelElement_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getDiagramElementMapping2ModelElement()
     * @generated
     */
    EReference getDiagramElementMapping2ModelElement_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Model Element2 View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Element2 View Variable</em>
     *         '.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType=
     *        "org.eclipse.sirius.ViewVariable2ContainerVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.ContainerVariable2StyleDescription>"
     * @generated
     */
    EClass getModelElement2ViewVariable();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getModelElement2ViewVariable()
     * @generated
     */
    EReference getModelElement2ViewVariable_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getModelElement2ViewVariable()
     * @generated
     */
    EReference getModelElement2ViewVariable_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>View Variable2 Container Variable</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>View Variable2 Container Variable</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType=
     *        "org.eclipse.sirius.ContainerVariable2StyleDescription<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.description.style.StyleDescription>"
     * @generated
     */
    EClass getViewVariable2ContainerVariable();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getViewVariable2ContainerVariable()
     * @generated
     */
    EReference getViewVariable2ContainerVariable_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getViewVariable2ContainerVariable()
     * @generated
     */
    EReference getViewVariable2ContainerVariable_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Container Variable2 Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Container Variable2 Style Description</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueType
     *        ="org.eclipse.sirius.description.style.StyleDescription"
     * @generated
     */
    EClass getContainerVariable2StyleDescription();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerVariable2StyleDescription()
     * @generated
     */
    EReference getContainerVariable2StyleDescription_Key();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerVariable2StyleDescription()
     * @generated
     */
    EReference getContainerVariable2StyleDescription_Value();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.ContainerLayout <em>Container Layout</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Container Layout</em>'.
     * @see org.eclipse.sirius.ContainerLayout
     * @generated
     */
    EEnum getContainerLayout();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DAnalysisCustomData
     * <em>DAnalysis Custom Data</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis Custom Data</em>'.
     * @see org.eclipse.sirius.DAnalysisCustomData
     * @generated
     */
    EClass getDAnalysisCustomData();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DAnalysisCustomData#getKey <em>Key</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.eclipse.sirius.DAnalysisCustomData#getKey()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EAttribute getDAnalysisCustomData_Key();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.DAnalysisCustomData#getData <em>Data</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.DAnalysisCustomData#getData()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EReference getDAnalysisCustomData_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Composite Style</em>'.
     * @see org.eclipse.sirius.GaugeCompositeStyle
     * @generated
     */
    EClass getGaugeCompositeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.GaugeCompositeStyle#getAlignment()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EAttribute getGaugeCompositeStyle_Alignment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.GaugeCompositeStyle#getSections
     * <em>Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sections</em>'.
     * @see org.eclipse.sirius.GaugeCompositeStyle#getSections()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EReference getGaugeCompositeStyle_Sections();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.LabelStyle <em>Label Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Label Style</em>'.
     * @see org.eclipse.sirius.LabelStyle
     * @generated
     */
    EClass getLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.LabelStyle#getLabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.LabelStyle#getLabelAlignment()
     * @see #getLabelStyle()
     * @generated
     */
    EAttribute getLabelStyle_LabelAlignment();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Style
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Style</em>'.
     * @see org.eclipse.sirius.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.Style#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.Style#getDescription()
     * @see #getStyle()
     * @generated
     */
    EReference getStyle_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.BorderedStyle <em>Bordered Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bordered Style</em>'.
     * @see org.eclipse.sirius.BorderedStyle
     * @generated
     */
    EClass getBorderedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BorderedStyle#getBorderSize
     * <em>Border Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.BorderedStyle#getBorderSize()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BorderedStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.BorderedStyle#getBorderSizeComputationExpression()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.BorderedStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Border Color</em>'.
     * @see org.eclipse.sirius.BorderedStyle#getBorderColor()
     * @see #getBorderedStyle()
     * @generated
     */
    EReference getBorderedStyle_BorderColor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Note
     * <em>Note</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Note</em>'.
     * @see org.eclipse.sirius.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Note#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.Note#getColor()
     * @see #getNote()
     * @generated
     */
    EReference getNote_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DragAndDropTarget
     * <em>Drag And Drop Target</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Drag And Drop Target</em>'.
     * @see org.eclipse.sirius.DragAndDropTarget
     * @generated
     */
    EClass getDragAndDropTarget();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.FilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.FilterVariableHistory
     * @generated
     */
    EClass getFilterVariableHistory();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.FilterVariableHistory#getOwnedValues
     * <em>Owned Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Values</em>'.
     * @see org.eclipse.sirius.FilterVariableHistory#getOwnedValues()
     * @see #getFilterVariableHistory()
     * @generated
     */
    EReference getFilterVariableHistory_OwnedValues();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.FilterVariableValue
     * <em>Filter Variable Value</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable Value</em>'.
     * @see org.eclipse.sirius.FilterVariableValue
     * @generated
     */
    EClass getFilterVariableValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.FilterVariableValue#getVariableDefinition
     * <em>Variable Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Variable Definition</em>'.
     * @see org.eclipse.sirius.FilterVariableValue#getVariableDefinition()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_VariableDefinition();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.FilterVariableValue#getModelElement
     * <em>Model Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.eclipse.sirius.FilterVariableValue#getModelElement()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_ModelElement();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.RGBValues <em>RGB Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>RGB Values</em>'.
     * @see org.eclipse.sirius.RGBValues
     * @generated
     */
    EClass getRGBValues();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.RGBValues#getRed <em>Red</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.RGBValues#getRed()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.RGBValues#getGreen <em>Green</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.RGBValues#getGreen()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.RGBValues#getBlue <em>Blue</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.RGBValues#getBlue()
     * @see #getRGBValues()
     * @generated
     */
    EAttribute getRGBValues_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject
     * <em>DAnalysis Session EObject</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnalysis Session EObject</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject
     * @generated
     */
    EClass getDAnalysisSessionEObject();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#isOpen
     * <em>Open</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Open</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#isOpen()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Open();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#isBlocked
     * <em>Blocked</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blocked</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#isBlocked()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Blocked();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getResources
     * <em>Resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Resources</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#getResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Resources();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getControlledResources
     * <em>Controlled Resources</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Controlled Resources</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#getControlledResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_ControlledResources();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getActivatedSiriuss
     * <em>Activated Siriuss</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activated Siriuss</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#getActivatedSiriuss()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_ActivatedSiriuss();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getAnalyses
     * <em>Analyses</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Analyses</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#getAnalyses()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_Analyses();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject#getSynchronizationStatus
     * <em>Synchronization Status</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Synchronization Status</em>'.
     * @see org.eclipse.sirius.DAnalysisSessionEObject#getSynchronizationStatus()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_SynchronizationStatus();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.CollapseFilter <em>Collapse Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Collapse Filter</em>'.
     * @see org.eclipse.sirius.CollapseFilter
     * @generated
     */
    EClass getCollapseFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.CollapseFilter#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.CollapseFilter#getWidth()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.CollapseFilter#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.CollapseFilter#getHeight()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Height();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Indirectly Collapse Filter</em>'.
     * @see org.eclipse.sirius.IndirectlyCollapseFilter
     * @generated
     */
    EClass getIndirectlyCollapseFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.SessionManagerEObject
     * <em>Session Manager EObject</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Session Manager EObject</em>'.
     * @see org.eclipse.sirius.SessionManagerEObject
     * @generated
     */
    EClass getSessionManagerEObject();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.SessionManagerEObject#getOwnedSessions
     * <em>Owned Sessions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Sessions</em>'.
     * @see org.eclipse.sirius.SessionManagerEObject#getOwnedSessions()
     * @see #getSessionManagerEObject()
     * @generated
     */
    EReference getSessionManagerEObject_OwnedSessions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DResource</em>'.
     * @see org.eclipse.sirius.DResource
     * @generated
     */
    EClass getDResource();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DResource#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.DResource#getName()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.DResource#getPath <em>Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see org.eclipse.sirius.DResource#getPath()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Path();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DFile
     * <em>DFile</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DFile</em>'.
     * @see org.eclipse.sirius.DFile
     * @generated
     */
    EClass getDFile();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.DResourceContainer
     * <em>DResource Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DResource Container</em>'.
     * @see org.eclipse.sirius.DResourceContainer
     * @generated
     */
    EClass getDResourceContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.DResourceContainer#getMembers
     * <em>Members</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Members</em>'.
     * @see org.eclipse.sirius.DResourceContainer#getMembers()
     * @see #getDResourceContainer()
     * @generated
     */
    EReference getDResourceContainer_Members();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DProject
     * <em>DProject</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DProject</em>'.
     * @see org.eclipse.sirius.DProject
     * @generated
     */
    EClass getDProject();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DFolder
     * <em>DFolder</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DFolder</em>'.
     * @see org.eclipse.sirius.DFolder
     * @generated
     */
    EClass getDFolder();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.DModel
     * <em>DModel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DModel</em>'.
     * @see org.eclipse.sirius.DModel
     * @generated
     */
    EClass getDModel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.BasicLabelStyle <em>Basic Label Style</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Basic Label Style</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle
     * @generated
     */
    EClass getBasicLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BasicLabelStyle#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle#getLabelSize()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BasicLabelStyle#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Format</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle#getLabelFormat()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelFormat();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BasicLabelStyle#isShowIcon
     * <em>Show Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Show Icon</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle#isShowIcon()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_ShowIcon();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.BasicLabelStyle#getLabelColor
     * <em>Label Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Label Color</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle#getLabelColor()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EReference getBasicLabelStyle_LabelColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.BasicLabelStyle#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.BasicLabelStyle#getIconPath()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_IconPath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.BeginLabelStyle <em>Begin Label Style</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.BeginLabelStyle
     * @generated
     */
    EClass getBeginLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.CenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Center Label Style</em>'.
     * @see org.eclipse.sirius.CenterLabelStyle
     * @generated
     */
    EClass getCenterLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.EndLabelStyle <em>End Label Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>End Label Style</em>'.
     * @see org.eclipse.sirius.EndLabelStyle
     * @generated
     */
    EClass getEndLabelStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Ellipse
     * <em>Ellipse</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Ellipse</em>'.
     * @see org.eclipse.sirius.Ellipse
     * @generated
     */
    EClass getEllipse();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Horizontal Diameter</em>'.
     * @see org.eclipse.sirius.Ellipse#getHorizontalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_HorizontalDiameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Vertical Diameter</em>'.
     * @see org.eclipse.sirius.Ellipse#getVerticalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_VerticalDiameter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.Ellipse#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.Ellipse#getColor()
     * @see #getEllipse()
     * @generated
     */
    EReference getEllipse_Color();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.Lozenge
     * <em>Lozenge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Lozenge</em>'.
     * @see org.eclipse.sirius.Lozenge
     * @generated
     */
    EClass getLozenge();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Lozenge#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.Lozenge#getWidth()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.Lozenge#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.Lozenge#getHeight()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.Lozenge#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.Lozenge#getColor()
     * @see #getLozenge()
     * @generated
     */
    EReference getLozenge_Color();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.LabelPosition <em>Label Position</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.eclipse.sirius.LabelPosition
     * @generated
     */
    EEnum getLabelPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.ContainerShape <em>Container Shape</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Container Shape</em>'.
     * @see org.eclipse.sirius.ContainerShape
     * @generated
     */
    EEnum getContainerShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.BackgroundStyle <em>Background Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Background Style</em>'.
     * @see org.eclipse.sirius.BackgroundStyle
     * @generated
     */
    EEnum getBackgroundStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.BundledImageShape
     * <em>Bundled Image Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Bundled Image Shape</em>'.
     * @see org.eclipse.sirius.BundledImageShape
     * @generated
     */
    EEnum getBundledImageShape();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.LineStyle
     * <em>Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see org.eclipse.sirius.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.EdgeArrows <em>Edge Arrows</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Arrows</em>'.
     * @see org.eclipse.sirius.EdgeArrows
     * @generated
     */
    EEnum getEdgeArrows();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.EdgeRouting <em>Edge Routing</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Routing</em>'.
     * @see org.eclipse.sirius.EdgeRouting
     * @generated
     */
    EEnum getEdgeRouting();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.AlignmentKind <em>Alignment Kind</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Alignment Kind</em>'.
     * @see org.eclipse.sirius.AlignmentKind
     * @generated
     */
    EEnum getAlignmentKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.FontFormat <em>Font Format</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Font Format</em>'.
     * @see org.eclipse.sirius.FontFormat
     * @generated
     */
    EEnum getFontFormat();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.ResizeKind <em>Resize Kind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.ResizeKind
     * @generated
     */
    EEnum getResizeKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.LabelAlignment <em>Label Alignment</em>}'.
     * <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the meta object for enum '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.LabelAlignment
     * @generated
     */
    EEnum getLabelAlignment();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.ArrangeConstraint
     * <em>Arrange Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Arrange Constraint</em>'.
     * @see org.eclipse.sirius.ArrangeConstraint
     * @generated
     */
    EEnum getArrangeConstraint();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.SyncStatus <em>Sync Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Sync Status</em>'.
     * @see org.eclipse.sirius.SyncStatus
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
    SiriusFactory getSiriusFactory();

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
         * {@link org.eclipse.sirius.impl.DAnalysisImpl <em>DAnalysis</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DAnalysisImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysis()
         * @generated
         */
        EClass DANALYSIS = eINSTANCE.getDAnalysis();

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
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DFeatureExtensionImpl
         * <em>DFeature Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DFeatureExtensionImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFeatureExtension()
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
         * {@link org.eclipse.sirius.DValidable <em>DValidable</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DValidable
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDValidable()
         * @generated
         */
        EClass DVALIDABLE = eINSTANCE.getDValidable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DNavigableImpl <em>DNavigable</em>}
         * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNavigableImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNavigable()
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
         * {@link org.eclipse.sirius.DStylizable <em>DStylizable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DStylizable
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDStylizable()
         * @generated
         */
        EClass DSTYLIZABLE = eINSTANCE.getDStylizable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.DRefreshable <em>DRefreshable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DRefreshable
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRefreshable()
         * @generated
         */
        EClass DREFRESHABLE = eINSTANCE.getDRefreshable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.DLabelled <em>DLabelled</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DLabelled
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDLabelled()
         * @generated
         */
        EClass DLABELLED = eINSTANCE.getDLabelled();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.DMappingBased <em>DMapping Based</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DMappingBased
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDMappingBased()
         * @generated
         */
        EClass DMAPPING_BASED = eINSTANCE.getDMappingBased();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.DContainer <em>DContainer</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DContainer
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDContainer()
         * @generated
         */
        EClass DCONTAINER = eINSTANCE.getDContainer();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DRepresentationContainerImpl
         * <em>DRepresentation Container</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DRepresentationContainerImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentationContainer()
         * @generated
         */
        EClass DREPRESENTATION_CONTAINER = eINSTANCE.getDRepresentationContainer();

        /**
         * The meta object literal for the '<em><b>Diagram Set</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION_CONTAINER__DIAGRAM_SET = eINSTANCE.getDRepresentationContainer_DiagramSet();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DREPRESENTATION_CONTAINER__MODELS = eINSTANCE.getDRepresentationContainer_Models();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DSemanticDecoratorImpl
         * <em>DSemantic Decorator</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DSemanticDecoratorImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSemanticDecorator()
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
         * {@link org.eclipse.sirius.impl.DRepresentationImpl
         * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DRepresentationImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentation()
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
         * {@link org.eclipse.sirius.impl.ExtensibleRepresentationImpl
         * <em>Extensible Representation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ExtensibleRepresentationImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getExtensibleRepresentation()
         * @generated
         */
        EClass EXTENSIBLE_REPRESENTATION = eINSTANCE.getExtensibleRepresentation();

        /**
         * The meta object literal for the '
         * <em><b>Effective Description</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION = eINSTANCE.getExtensibleRepresentation_EffectiveDescription();

        /**
         * The meta object literal for the '<em><b>Contribution Points</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS = eINSTANCE.getExtensibleRepresentation_ContributionPoints();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DRepresentationElementImpl
         * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DRepresentationElementImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDRepresentationElement()
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
         * {@link org.eclipse.sirius.impl.DViewImpl <em>DView</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DViewImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDView()
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
         * The meta object literal for the '<em><b>Sirius</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DVIEW__VIEWPOINT = eINSTANCE.getDView_Sirius();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.MetaModelExtensionImpl
         * <em>Meta Model Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.MetaModelExtensionImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getMetaModelExtension()
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
         * {@link org.eclipse.sirius.impl.DDiagramImpl <em>DDiagram</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DDiagramImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagram()
         * @generated
         */
        EClass DDIAGRAM = eINSTANCE.getDDiagram();

        /**
         * The meta object literal for the '
         * <em><b>Owned Diagram Elements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = eINSTANCE.getDDiagram_OwnedDiagramElements();

        /**
         * The meta object literal for the '<em><b>Diagram Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__DIAGRAM_ELEMENTS = eINSTANCE.getDDiagram_DiagramElements();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__DESCRIPTION = eINSTANCE.getDDiagram_Description();

        /**
         * The meta object literal for the '<em><b>Info</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__INFO = eINSTANCE.getDDiagram_Info();

        /**
         * The meta object literal for the '<em><b>Sub Diagrams</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__SUB_DIAGRAMS = eINSTANCE.getDDiagram_SubDiagrams();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__EDGES = eINSTANCE.getDDiagram_Edges();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__NODES = eINSTANCE.getDDiagram_Nodes();

        /**
         * The meta object literal for the '<em><b>Node List Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__NODE_LIST_ELEMENTS = eINSTANCE.getDDiagram_NodeListElements();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__CONTAINERS = eINSTANCE.getDDiagram_Containers();

        /**
         * The meta object literal for the '<em><b>Current Concern</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__CURRENT_CONCERN = eINSTANCE.getDDiagram_CurrentConcern();

        /**
         * The meta object literal for the '<em><b>Activated Filters</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_FILTERS = eINSTANCE.getDDiagram_ActivatedFilters();

        /**
         * The meta object literal for the '<em><b>All Filters</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ALL_FILTERS = eINSTANCE.getDDiagram_AllFilters();

        /**
         * The meta object literal for the '<em><b>Activated Rules</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_RULES = eINSTANCE.getDDiagram_ActivatedRules();

        /**
         * The meta object literal for the '<em><b>Activate Behaviors</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATE_BEHAVIORS = eINSTANCE.getDDiagram_ActivateBehaviors();

        /**
         * The meta object literal for the '
         * <em><b>Filter Variable History</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__FILTER_VARIABLE_HISTORY = eINSTANCE.getDDiagram_FilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Activated Layers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_LAYERS = eINSTANCE.getDDiagram_ActivatedLayers();

        /**
         * The meta object literal for the '<em><b>Synchronized</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__SYNCHRONIZED = eINSTANCE.getDDiagram_Synchronized();

        /**
         * The meta object literal for the '<em><b>Hidden Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__HIDDEN_ELEMENTS = eINSTANCE.getDDiagram_HiddenElements();

        /**
         * The meta object literal for the '<em><b>Is In Layouting Mode</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__IS_IN_LAYOUTING_MODE = eINSTANCE.getDDiagram_IsInLayoutingMode();

        /**
         * The meta object literal for the '<em><b>Header Height</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__HEADER_HEIGHT = eINSTANCE.getDDiagram_HeaderHeight();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DSemanticDiagramImpl
         * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DSemanticDiagramImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSemanticDiagram()
         * @generated
         */
        EClass DSEMANTIC_DIAGRAM = eINSTANCE.getDSemanticDiagram();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DDiagramElementImpl
         * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DDiagramElementImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramElement()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT = eINSTANCE.getDDiagramElement();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__VISIBLE = eINSTANCE.getDDiagramElement_Visible();

        /**
         * The meta object literal for the '<em><b>Tooltip Text</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         * 
         * @since 2.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__TOOLTIP_TEXT = eINSTANCE.getDDiagramElement_TooltipText();

        /**
         * The meta object literal for the '<em><b>Parent Layers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__PARENT_LAYERS = eINSTANCE.getDDiagramElement_ParentLayers();

        /**
         * The meta object literal for the '<em><b>Decorations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DECORATIONS = eINSTANCE.getDDiagramElement_Decorations();

        /**
         * The meta object literal for the '
         * <em><b>Diagram Element Mapping</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = eINSTANCE.getDDiagramElement_DiagramElementMapping();

        /**
         * The meta object literal for the '<em><b>Graphical Filters</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = eINSTANCE.getDDiagramElement_GraphicalFilters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.GraphicalFilter
         * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.GraphicalFilter
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGraphicalFilter()
         * @generated
         */
        EClass GRAPHICAL_FILTER = eINSTANCE.getGraphicalFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.HideFilterImpl
         * <em>Hide Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.HideFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getHideFilter()
         * @generated
         */
        EClass HIDE_FILTER = eINSTANCE.getHideFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.HideLabelFilterImpl
         * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.HideLabelFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getHideLabelFilter()
         * @generated
         */
        EClass HIDE_LABEL_FILTER = eINSTANCE.getHideLabelFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.FoldingPointFilterImpl
         * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.FoldingPointFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFoldingPointFilter()
         * @generated
         */
        EClass FOLDING_POINT_FILTER = eINSTANCE.getFoldingPointFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.FoldingFilterImpl
         * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.FoldingFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFoldingFilter()
         * @generated
         */
        EClass FOLDING_FILTER = eINSTANCE.getFoldingFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.AppliedCompositeFiltersImpl
         * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.AppliedCompositeFiltersImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAppliedCompositeFilters()
         * @generated
         */
        EClass APPLIED_COMPOSITE_FILTERS = eINSTANCE.getAppliedCompositeFilters();

        /**
         * The meta object literal for the '
         * <em><b>Composite Filter Descriptions</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = eINSTANCE.getAppliedCompositeFilters_CompositeFilterDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.AbsoluteBoundsFilterImpl
         * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.AbsoluteBoundsFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAbsoluteBoundsFilter()
         * @generated
         */
        EClass ABSOLUTE_BOUNDS_FILTER = eINSTANCE.getAbsoluteBoundsFilter();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__X = eINSTANCE.getAbsoluteBoundsFilter_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__Y = eINSTANCE.getAbsoluteBoundsFilter_Y();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__HEIGHT = eINSTANCE.getAbsoluteBoundsFilter_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__WIDTH = eINSTANCE.getAbsoluteBoundsFilter_Width();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DecorationImpl <em>Decoration</em>}
         * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DecorationImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDecoration()
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
         * {@link org.eclipse.sirius.impl.DNavigationLinkImpl
         * <em>DNavigation Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNavigationLinkImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNavigationLink()
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
         * {@link org.eclipse.sirius.impl.DEObjectLinkImpl
         * <em>DE Object Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DEObjectLinkImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDEObjectLink()
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
         * {@link org.eclipse.sirius.impl.DDiagramLinkImpl
         * <em>DDiagram Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DDiagramLinkImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramLink()
         * @generated
         */
        EClass DDIAGRAM_LINK = eINSTANCE.getDDiagramLink();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_LINK__TARGET = eINSTANCE.getDDiagramLink_Target();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_LINK__NODE = eINSTANCE.getDDiagramLink_Node();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DSourceFileLinkImpl
         * <em>DSource File Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DSourceFileLinkImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDSourceFileLink()
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
         * {@link org.eclipse.sirius.AbstractDNode <em>Abstract DNode</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.AbstractDNode
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAbstractDNode()
         * @generated
         */
        EClass ABSTRACT_DNODE = eINSTANCE.getAbstractDNode();

        /**
         * The meta object literal for the '<em><b>Owned Bordered Nodes</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_DNODE__OWNED_BORDERED_NODES = eINSTANCE.getAbstractDNode_OwnedBorderedNodes();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = eINSTANCE.getAbstractDNode_ArrangeConstraints();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DNodeImpl <em>DNode</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNodeImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNode()
         * @generated
         */
        EClass DNODE = eINSTANCE.getDNode();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__WIDTH = eINSTANCE.getDNode_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__HEIGHT = eINSTANCE.getDNode_Height();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__OWNED_STYLE = eINSTANCE.getDNode_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__LABEL_POSITION = eINSTANCE.getDNode_LabelPosition();

        /**
         * The meta object literal for the '<em><b>Owned Details</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__OWNED_DETAILS = eINSTANCE.getDNode_OwnedDetails();

        /**
         * The meta object literal for the '<em><b>Resize Kind</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__RESIZE_KIND = eINSTANCE.getDNode_ResizeKind();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__ORIGINAL_STYLE = eINSTANCE.getDNode_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__ACTUAL_MAPPING = eINSTANCE.getDNode_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__CANDIDATES_MAPPING = eINSTANCE.getDNode_CandidatesMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DDiagramElementContainerImpl
         * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DDiagramElementContainerImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramElementContainer()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT_CONTAINER = eINSTANCE.getDDiagramElementContainer();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__NODES = eINSTANCE.getDDiagramElementContainer_Nodes();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = eINSTANCE.getDDiagramElementContainer_Containers();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = eINSTANCE.getDDiagramElementContainer_Elements();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = eINSTANCE.getDDiagramElementContainer_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Owned Details</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS = eINSTANCE.getDDiagramElementContainer_OwnedDetails();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = eINSTANCE.getDDiagramElementContainer_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = eINSTANCE.getDDiagramElementContainer_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = eINSTANCE.getDDiagramElementContainer_CandidatesMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DNodeContainerImpl
         * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNodeContainerImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeContainer()
         * @generated
         */
        EClass DNODE_CONTAINER = eINSTANCE.getDNodeContainer();

        /**
         * The meta object literal for the '
         * <em><b>Owned Diagram Elements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = eINSTANCE.getDNodeContainer_OwnedDiagramElements();

        /**
         * The meta object literal for the '
         * <em><b>Children Presentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE_CONTAINER__CHILDREN_PRESENTATION = eINSTANCE.getDNodeContainer_ChildrenPresentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DNodeListImpl <em>DNode List</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNodeListImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeList()
         * @generated
         */
        EClass DNODE_LIST = eINSTANCE.getDNodeList();

        /**
         * The meta object literal for the '<em><b>Owned Elements</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST__OWNED_ELEMENTS = eINSTANCE.getDNodeList_OwnedElements();

        /**
         * The meta object literal for the '<em><b>Line Width</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE_LIST__LINE_WIDTH = eINSTANCE.getDNodeList_LineWidth();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DNodeListElementImpl
         * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DNodeListElementImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDNodeListElement()
         * @generated
         */
        EClass DNODE_LIST_ELEMENT = eINSTANCE.getDNodeListElement();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__OWNED_STYLE = eINSTANCE.getDNodeListElement_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ORIGINAL_STYLE = eINSTANCE.getDNodeListElement_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ACTUAL_MAPPING = eINSTANCE.getDNodeListElement_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = eINSTANCE.getDNodeListElement_CandidatesMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DEdgeImpl <em>DEdge</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DEdgeImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDEdge()
         * @generated
         */
        EClass DEDGE = eINSTANCE.getDEdge();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__OWNED_STYLE = eINSTANCE.getDEdge_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__SIZE = eINSTANCE.getDEdge_Size();

        /**
         * The meta object literal for the '<em><b>Source Node</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__SOURCE_NODE = eINSTANCE.getDEdge_SourceNode();

        /**
         * The meta object literal for the '<em><b>Target Node</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__TARGET_NODE = eINSTANCE.getDEdge_TargetNode();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__ACTUAL_MAPPING = eINSTANCE.getDEdge_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__ROUTING_STYLE = eINSTANCE.getDEdge_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Is Fold</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__IS_FOLD = eINSTANCE.getDEdge_IsFold();

        /**
         * The meta object literal for the '<em><b>Is Mock Edge</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__IS_MOCK_EDGE = eINSTANCE.getDEdge_IsMockEdge();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__ORIGINAL_STYLE = eINSTANCE.getDEdge_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__PATH = eINSTANCE.getDEdge_Path();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__ARRANGE_CONSTRAINTS = eINSTANCE.getDEdge_ArrangeConstraints();

        /**
         * The meta object literal for the '<em><b>Begin Label</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__BEGIN_LABEL = eINSTANCE.getDEdge_BeginLabel();

        /**
         * The meta object literal for the '<em><b>End Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__END_LABEL = eINSTANCE.getDEdge_EndLabel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DDiagramSetImpl
         * <em>DDiagram Set</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DDiagramSetImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDDiagramSet()
         * @generated
         */
        EClass DDIAGRAM_SET = eINSTANCE.getDDiagramSet();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_SET__DESCRIPTION = eINSTANCE.getDDiagramSet_Description();

        /**
         * The meta object literal for the '<em><b>Diagrams</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_SET__DIAGRAMS = eINSTANCE.getDDiagramSet_Diagrams();

        /**
         * The meta object literal for the '<em><b>View</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_SET__VIEW = eINSTANCE.getDDiagramSet_View();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.NodeStyleImpl <em>Node Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.NodeStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getNodeStyle()
         * @generated
         */
        EClass NODE_STYLE = eINSTANCE.getNodeStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE__LABEL_POSITION = eINSTANCE.getNodeStyle_LabelPosition();

        /**
         * The meta object literal for the '
         * <em><b>Hide Label By Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE__HIDE_LABEL_BY_DEFAULT = eINSTANCE.getNodeStyle_HideLabelByDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DotImpl <em>Dot</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DotImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDot()
         * @generated
         */
        EClass DOT = eINSTANCE.getDot();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DOT__BACKGROUND_COLOR = eINSTANCE.getDot_BackgroundColor();

        /**
         * The meta object literal for the '
         * <em><b>Stroke Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getDot_StrokeSizeComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.ContainerStyleImpl
         * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ContainerStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerStyle()
         * @generated
         */
        EClass CONTAINER_STYLE = eINSTANCE.getContainerStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.FlatContainerStyleImpl
         * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.FlatContainerStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFlatContainerStyle()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE = eINSTANCE.getFlatContainerStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = eINSTANCE.getFlatContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = eINSTANCE.getFlatContainerStyle_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = eINSTANCE.getFlatContainerStyle_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.ShapeContainerStyleImpl
         * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ShapeContainerStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getShapeContainerStyle()
         * @generated
         */
        EClass SHAPE_CONTAINER_STYLE = eINSTANCE.getShapeContainerStyle();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__SHAPE = eINSTANCE.getShapeContainerStyle_Shape();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = eINSTANCE.getShapeContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.SquareImpl <em>Square</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.SquareImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSquare()
         * @generated
         */
        EClass SQUARE = eINSTANCE.getSquare();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE__WIDTH = eINSTANCE.getSquare_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE__HEIGHT = eINSTANCE.getSquare_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SQUARE__COLOR = eINSTANCE.getSquare_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.BundledImageImpl
         * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.BundledImageImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBundledImage()
         * @generated
         */
        EClass BUNDLED_IMAGE = eINSTANCE.getBundledImage();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BUNDLED_IMAGE__SHAPE = eINSTANCE.getBundledImage_Shape();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BUNDLED_IMAGE__COLOR = eINSTANCE.getBundledImage_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.WorkspaceImageImpl
         * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.WorkspaceImageImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getWorkspaceImage()
         * @generated
         */
        EClass WORKSPACE_IMAGE = eINSTANCE.getWorkspaceImage();

        /**
         * The meta object literal for the '<em><b>Workspace Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORKSPACE_IMAGE__WORKSPACE_PATH = eINSTANCE.getWorkspaceImage_WorkspacePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.CustomStyleImpl
         * <em>Custom Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.CustomStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCustomStyle()
         * @generated
         */
        EClass CUSTOM_STYLE = eINSTANCE.getCustomStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CUSTOM_STYLE__ID = eINSTANCE.getCustomStyle_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.GaugeSectionImpl
         * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.GaugeSectionImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGaugeSection()
         * @generated
         */
        EClass GAUGE_SECTION = eINSTANCE.getGaugeSection();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__MIN = eINSTANCE.getGaugeSection_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__MAX = eINSTANCE.getGaugeSection_Max();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__VALUE = eINSTANCE.getGaugeSection_Value();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__LABEL = eINSTANCE.getGaugeSection_Label();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_SECTION__BACKGROUND_COLOR = eINSTANCE.getGaugeSection_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_SECTION__FOREGROUND_COLOR = eINSTANCE.getGaugeSection_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.EdgeTargetImpl
         * <em>Edge Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.EdgeTargetImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeTarget()
         * @generated
         */
        EClass EDGE_TARGET = eINSTANCE.getEdgeTarget();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_TARGET__OUTGOING_EDGES = eINSTANCE.getEdgeTarget_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Incoming Edges</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_TARGET__INCOMING_EDGES = eINSTANCE.getEdgeTarget_IncomingEdges();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.EdgeStyleImpl <em>Edge Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeStyle()
         * @generated
         */
        EClass EDGE_STYLE = eINSTANCE.getEdgeStyle();

        /**
         * The meta object literal for the '<em><b>Stroke Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__STROKE_COLOR = eINSTANCE.getEdgeStyle_StrokeColor();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__LINE_STYLE = eINSTANCE.getEdgeStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__SOURCE_ARROW = eINSTANCE.getEdgeStyle_SourceArrow();

        /**
         * The meta object literal for the '<em><b>Target Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__TARGET_ARROW = eINSTANCE.getEdgeStyle_TargetArrow();

        /**
         * The meta object literal for the '<em><b>Folding Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__FOLDING_STYLE = eINSTANCE.getEdgeStyle_FoldingStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__SIZE = eINSTANCE.getEdgeStyle_Size();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__ROUTING_STYLE = eINSTANCE.getEdgeStyle_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Begin Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__BEGIN_LABEL_STYLE = eINSTANCE.getEdgeStyle_BeginLabelStyle();

        /**
         * The meta object literal for the '<em><b>Center Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__CENTER_LABEL_STYLE = eINSTANCE.getEdgeStyle_CenterLabelStyle();

        /**
         * The meta object literal for the '<em><b>End Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__END_LABEL_STYLE = eINSTANCE.getEdgeStyle_EndLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.BracketEdgeStyleImpl
         * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.BracketEdgeStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBracketEdgeStyle()
         * @generated
         */
        EClass BRACKET_EDGE_STYLE = eINSTANCE.getBracketEdgeStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.CustomizableImpl
         * <em>Customizable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.CustomizableImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCustomizable()
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
         * {@link org.eclipse.sirius.impl.ComputedStyleDescriptionRegistryImpl
         * <em>Computed Style Description Registry</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ComputedStyleDescriptionRegistryImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getComputedStyleDescriptionRegistry()
         * @generated
         */
        EClass COMPUTED_STYLE_DESCRIPTION_REGISTRY = eINSTANCE.getComputedStyleDescriptionRegistry();

        /**
         * The meta object literal for the '
         * <em><b>Computed Style Descriptions</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = eINSTANCE.getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

        /**
         * The meta object literal for the '<em><b>Cache</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE = eINSTANCE.getComputedStyleDescriptionRegistry_Cache();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DiagramElementMapping2ModelElementImpl
         * <em>Diagram Element Mapping2 Model Element</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DiagramElementMapping2ModelElementImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDiagramElementMapping2ModelElement()
         * @generated
         */
        EClass DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT = eINSTANCE.getDiagramElementMapping2ModelElement();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY = eINSTANCE.getDiagramElementMapping2ModelElement_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE = eINSTANCE.getDiagramElementMapping2ModelElement_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.ModelElement2ViewVariableImpl
         * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ModelElement2ViewVariableImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getModelElement2ViewVariable()
         * @generated
         */
        EClass MODEL_ELEMENT2_VIEW_VARIABLE = eINSTANCE.getModelElement2ViewVariable();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_ELEMENT2_VIEW_VARIABLE__KEY = eINSTANCE.getModelElement2ViewVariable_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_ELEMENT2_VIEW_VARIABLE__VALUE = eINSTANCE.getModelElement2ViewVariable_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.ViewVariable2ContainerVariableImpl
         * <em>View Variable2 Container Variable</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ViewVariable2ContainerVariableImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getViewVariable2ContainerVariable()
         * @generated
         */
        EClass VIEW_VARIABLE2_CONTAINER_VARIABLE = eINSTANCE.getViewVariable2ContainerVariable();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_VARIABLE2_CONTAINER_VARIABLE__KEY = eINSTANCE.getViewVariable2ContainerVariable_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_VARIABLE2_CONTAINER_VARIABLE__VALUE = eINSTANCE.getViewVariable2ContainerVariable_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.ContainerVariable2StyleDescriptionImpl
         * <em>Container Variable2 Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.ContainerVariable2StyleDescriptionImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerVariable2StyleDescription()
         * @generated
         */
        EClass CONTAINER_VARIABLE2_STYLE_DESCRIPTION = eINSTANCE.getContainerVariable2StyleDescription();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_VARIABLE2_STYLE_DESCRIPTION__KEY = eINSTANCE.getContainerVariable2StyleDescription_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_VARIABLE2_STYLE_DESCRIPTION__VALUE = eINSTANCE.getContainerVariable2StyleDescription_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.ContainerLayout
         * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.ContainerLayout
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerLayout()
         * @generated
         */
        EEnum CONTAINER_LAYOUT = eINSTANCE.getContainerLayout();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DAnalysisCustomDataImpl
         * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DAnalysisCustomDataImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysisCustomData()
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
         * {@link org.eclipse.sirius.impl.GaugeCompositeStyleImpl
         * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.GaugeCompositeStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getGaugeCompositeStyle()
         * @generated
         */
        EClass GAUGE_COMPOSITE_STYLE = eINSTANCE.getGaugeCompositeStyle();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_COMPOSITE_STYLE__ALIGNMENT = eINSTANCE.getGaugeCompositeStyle_Alignment();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_COMPOSITE_STYLE__SECTIONS = eINSTANCE.getGaugeCompositeStyle_Sections();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.LabelStyleImpl
         * <em>Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.LabelStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelStyle()
         * @generated
         */
        EClass LABEL_STYLE = eINSTANCE.getLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         * 
         * @since 2.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute LABEL_STYLE__LABEL_ALIGNMENT = eINSTANCE.getLabelStyle_LabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.StyleImpl <em>Style</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.StyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getStyle()
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
         * {@link org.eclipse.sirius.impl.BorderedStyleImpl
         * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.BorderedStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBorderedStyle()
         * @generated
         */
        EClass BORDERED_STYLE = eINSTANCE.getBorderedStyle();

        /**
         * The meta object literal for the '<em><b>Border Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE = eINSTANCE.getBorderedStyle_BorderSize();

        /**
         * The meta object literal for the '
         * <em><b>Border Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getBorderedStyle_BorderSizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BORDERED_STYLE__BORDER_COLOR = eINSTANCE.getBorderedStyle_BorderColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.NoteImpl <em>Note</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.NoteImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getNote()
         * @generated
         */
        EClass NOTE = eINSTANCE.getNote();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NOTE__COLOR = eINSTANCE.getNote_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DragAndDropTargetImpl
         * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DragAndDropTargetImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDragAndDropTarget()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET = eINSTANCE.getDragAndDropTarget();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.FilterVariableHistoryImpl
         * <em>Filter Variable History</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.FilterVariableHistoryImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFilterVariableHistory()
         * @generated
         */
        EClass FILTER_VARIABLE_HISTORY = eINSTANCE.getFilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Owned Values</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_HISTORY__OWNED_VALUES = eINSTANCE.getFilterVariableHistory_OwnedValues();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.FilterVariableValueImpl
         * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.FilterVariableValueImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFilterVariableValue()
         * @generated
         */
        EClass FILTER_VARIABLE_VALUE = eINSTANCE.getFilterVariableValue();

        /**
         * The meta object literal for the '<em><b>Variable Definition</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION = eINSTANCE.getFilterVariableValue_VariableDefinition();

        /**
         * The meta object literal for the '<em><b>Model Element</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_VALUE__MODEL_ELEMENT = eINSTANCE.getFilterVariableValue_ModelElement();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.RGBValuesImpl <em>RGB Values</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.RGBValuesImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getRGBValues()
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
         * {@link org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl
         * <em>DAnalysis Session EObject</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDAnalysisSessionEObject()
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
         * The meta object literal for the '<em><b>Activated Siriuss</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @generated
         */
        EReference DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS = eINSTANCE.getDAnalysisSessionEObject_ActivatedSiriuss();

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
         * {@link org.eclipse.sirius.impl.CollapseFilterImpl
         * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.CollapseFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCollapseFilter()
         * @generated
         */
        EClass COLLAPSE_FILTER = eINSTANCE.getCollapseFilter();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLLAPSE_FILTER__WIDTH = eINSTANCE.getCollapseFilter_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLLAPSE_FILTER__HEIGHT = eINSTANCE.getCollapseFilter_Height();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.IndirectlyCollapseFilterImpl
         * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.IndirectlyCollapseFilterImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getIndirectlyCollapseFilter()
         * @generated
         */
        EClass INDIRECTLY_COLLAPSE_FILTER = eINSTANCE.getIndirectlyCollapseFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.SessionManagerEObjectImpl
         * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.SessionManagerEObjectImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSessionManagerEObject()
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
         * {@link org.eclipse.sirius.DResource <em>DResource</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.DResource
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDResource()
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
         * {@link org.eclipse.sirius.impl.DFileImpl <em>DFile</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DFileImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFile()
         * @generated
         */
        EClass DFILE = eINSTANCE.getDFile();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DResourceContainerImpl
         * <em>DResource Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DResourceContainerImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDResourceContainer()
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
         * {@link org.eclipse.sirius.impl.DProjectImpl <em>DProject</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DProjectImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDProject()
         * @generated
         */
        EClass DPROJECT = eINSTANCE.getDProject();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DFolderImpl <em>DFolder</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DFolderImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDFolder()
         * @generated
         */
        EClass DFOLDER = eINSTANCE.getDFolder();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.DModelImpl <em>DModel</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.DModelImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getDModel()
         * @generated
         */
        EClass DMODEL = eINSTANCE.getDModel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.BasicLabelStyleImpl
         * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.BasicLabelStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBasicLabelStyle()
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
         * {@link org.eclipse.sirius.impl.BeginLabelStyleImpl
         * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.BeginLabelStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBeginLabelStyle()
         * @generated
         */
        EClass BEGIN_LABEL_STYLE = eINSTANCE.getBeginLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.CenterLabelStyleImpl
         * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.CenterLabelStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getCenterLabelStyle()
         * @generated
         */
        EClass CENTER_LABEL_STYLE = eINSTANCE.getCenterLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.EndLabelStyleImpl
         * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.EndLabelStyleImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEndLabelStyle()
         * @generated
         */
        EClass END_LABEL_STYLE = eINSTANCE.getEndLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.EllipseImpl <em>Ellipse</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.EllipseImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEllipse()
         * @generated
         */
        EClass ELLIPSE = eINSTANCE.getEllipse();

        /**
         * The meta object literal for the '<em><b>Horizontal Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE__HORIZONTAL_DIAMETER = eINSTANCE.getEllipse_HorizontalDiameter();

        /**
         * The meta object literal for the '<em><b>Vertical Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE__VERTICAL_DIAMETER = eINSTANCE.getEllipse_VerticalDiameter();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELLIPSE__COLOR = eINSTANCE.getEllipse_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.impl.LozengeImpl <em>Lozenge</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.impl.LozengeImpl
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLozenge()
         * @generated
         */
        EClass LOZENGE = eINSTANCE.getLozenge();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE__WIDTH = eINSTANCE.getLozenge_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE__HEIGHT = eINSTANCE.getLozenge_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOZENGE__COLOR = eINSTANCE.getLozenge_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.LabelPosition <em>Label Position</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.LabelPosition
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelPosition()
         * @generated
         */
        EEnum LABEL_POSITION = eINSTANCE.getLabelPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.ContainerShape <em>Container Shape</em>}
         * ' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.ContainerShape
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getContainerShape()
         * @generated
         */
        EEnum CONTAINER_SHAPE = eINSTANCE.getContainerShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.BackgroundStyle
         * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.BackgroundStyle
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBackgroundStyle()
         * @generated
         */
        EEnum BACKGROUND_STYLE = eINSTANCE.getBackgroundStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.BundledImageShape
         * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.BundledImageShape
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getBundledImageShape()
         * @generated
         */
        EEnum BUNDLED_IMAGE_SHAPE = eINSTANCE.getBundledImageShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.LineStyle <em>Line Style</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.LineStyle
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.EdgeArrows <em>Edge Arrows</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.EdgeArrows
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeArrows()
         * @generated
         */
        EEnum EDGE_ARROWS = eINSTANCE.getEdgeArrows();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.EdgeRouting <em>Edge Routing</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.EdgeRouting
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getEdgeRouting()
         * @generated
         */
        EEnum EDGE_ROUTING = eINSTANCE.getEdgeRouting();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.AlignmentKind <em>Alignment Kind</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.AlignmentKind
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getAlignmentKind()
         * @generated
         */
        EEnum ALIGNMENT_KIND = eINSTANCE.getAlignmentKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.FontFormat <em>Font Format</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.FontFormat
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getFontFormat()
         * @generated
         */
        EEnum FONT_FORMAT = eINSTANCE.getFontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.ResizeKind <em>Resize Kind</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.ResizeKind
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getResizeKind()
         * @generated
         */
        EEnum RESIZE_KIND = eINSTANCE.getResizeKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.LabelAlignment <em>Label Alignment</em>}
         * ' enum. <!-- begin-user-doc -->
         * 
         * @since 2.0 <!-- end-user-doc -->
         * @see org.eclipse.sirius.LabelAlignment
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getLabelAlignment()
         * @generated
         */
        EEnum LABEL_ALIGNMENT = eINSTANCE.getLabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.ArrangeConstraint
         * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.ArrangeConstraint
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getArrangeConstraint()
         * @generated
         */
        EEnum ARRANGE_CONSTRAINT = eINSTANCE.getArrangeConstraint();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.SyncStatus <em>Sync Status</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.SyncStatus
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getSyncStatus()
         * @generated
         */
        EEnum SYNC_STATUS = eINSTANCE.getSyncStatus();

        /**
         * The meta object literal for the '<em>Extended Package</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
         * @see org.eclipse.sirius.impl.SiriusPackageImpl#getExtendedPackage()
         * @generated
         */
        EDataType EXTENDED_PACKAGE = eINSTANCE.getExtendedPackage();

    }

} // SiriusPackage
