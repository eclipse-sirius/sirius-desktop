/**
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES
 *  All rights reserved.
 * 
 *  Contributors:
 *      Obeo - Initial API and implementation
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

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
     * {@link org.eclipse.sirius.viewpoint.impl.DDiagramImpl <em>DDiagram</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagram()
     * @generated
     */
    int DDIAGRAM = 15;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DSemanticDiagramImpl
     * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DSemanticDiagramImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDiagram()
     * @generated
     */
    int DSEMANTIC_DIAGRAM = 16;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl
     * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramElement()
     * @generated
     */
    int DDIAGRAM_ELEMENT = 17;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.GraphicalFilter
     * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.GraphicalFilter
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGraphicalFilter()
     * @generated
     */
    int GRAPHICAL_FILTER = 18;

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
     * {@link org.eclipse.sirius.viewpoint.impl.HideFilterImpl
     * <em>Hide Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.HideFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getHideFilter()
     * @generated
     */
    int HIDE_FILTER = 19;

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
     * {@link org.eclipse.sirius.viewpoint.impl.HideLabelFilterImpl
     * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.HideLabelFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getHideLabelFilter()
     * @generated
     */
    int HIDE_LABEL_FILTER = 20;

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
     * {@link org.eclipse.sirius.viewpoint.impl.FoldingPointFilterImpl
     * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.FoldingPointFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFoldingPointFilter()
     * @generated
     */
    int FOLDING_POINT_FILTER = 21;

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
     * {@link org.eclipse.sirius.viewpoint.impl.FoldingFilterImpl
     * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.FoldingFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFoldingFilter()
     * @generated
     */
    int FOLDING_FILTER = 22;

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
     * {@link org.eclipse.sirius.viewpoint.impl.AppliedCompositeFiltersImpl
     * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.AppliedCompositeFiltersImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAppliedCompositeFilters()
     * @generated
     */
    int APPLIED_COMPOSITE_FILTERS = 23;

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
     * {@link org.eclipse.sirius.viewpoint.impl.AbsoluteBoundsFilterImpl
     * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.AbsoluteBoundsFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAbsoluteBoundsFilter()
     * @generated
     */
    int ABSOLUTE_BOUNDS_FILTER = 24;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DecorationImpl
     * <em>Decoration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DecorationImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDecoration()
     * @generated
     */
    int DECORATION = 25;

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
    int DNAVIGATION_LINK = 26;

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
    int DE_OBJECT_LINK = 27;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl
     * <em>DDiagram Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramLink()
     * @generated
     */
    int DDIAGRAM_LINK = 28;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
     * <em>DSource File Link</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSourceFileLink()
     * @generated
     */
    int DSOURCE_FILE_LINK = 29;

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
     * {@link org.eclipse.sirius.viewpoint.AbstractDNode
     * <em>Abstract DNode</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.AbstractDNode
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAbstractDNode()
     * @generated
     */
    int ABSTRACT_DNODE = 30;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DNodeImpl <em>DNode</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNode()
     * @generated
     */
    int DNODE = 31;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl
     * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramElementContainer()
     * @generated
     */
    int DDIAGRAM_ELEMENT_CONTAINER = 32;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__WIDTH = ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = ABSTRACT_DNODE_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '
     * <em>DDiagram Element Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DNodeContainerImpl
     * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeContainerImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeContainer()
     * @generated
     */
    int DNODE_CONTAINER = 33;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__WIDTH = DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__HEIGHT = DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DNodeListImpl
     * <em>DNode List</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeList()
     * @generated
     */
    int DNODE_LIST = 34;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__WIDTH = DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__HEIGHT = DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl
     * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeListElement()
     * @generated
     */
    int DNODE_LIST_ELEMENT = 35;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DEdgeImpl <em>DEdge</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDEdge()
     * @generated
     */
    int DEDGE = 36;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl
     * <em>DDiagram Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramSet()
     * @generated
     */
    int DDIAGRAM_SET = 37;

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
     * {@link org.eclipse.sirius.viewpoint.impl.CustomizableImpl
     * <em>Customizable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.CustomizableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomizable()
     * @generated
     */
    int CUSTOMIZABLE = 77;

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
    int BASIC_LABEL_STYLE = 72;

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
    int LABEL_STYLE = 54;

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
     * {@link org.eclipse.sirius.viewpoint.impl.NodeStyleImpl
     * <em>Node Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.NodeStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 38;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DotImpl <em>Dot</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DotImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDot()
     * @generated
     */
    int DOT = 39;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.GaugeSectionImpl
     * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.GaugeSectionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGaugeSection()
     * @generated
     */
    int GAUGE_SECTION = 40;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.ContainerStyleImpl
     * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ContainerStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerStyle()
     * @generated
     */
    int CONTAINER_STYLE = 41;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl
     * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFlatContainerStyle()
     * @generated
     */
    int FLAT_CONTAINER_STYLE = 42;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl
     * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getShapeContainerStyle()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE = 43;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.SquareImpl <em>Square</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.SquareImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSquare()
     * @generated
     */
    int SQUARE = 44;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.EllipseImpl <em>Ellipse</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.EllipseImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEllipse()
     * @generated
     */
    int ELLIPSE = 45;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * {@link org.eclipse.sirius.viewpoint.impl.LozengeImpl <em>Lozenge</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.LozengeImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLozenge()
     * @generated
     */
    int LOZENGE = 46;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.BundledImageImpl
     * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.BundledImageImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBundledImage()
     * @generated
     */
    int BUNDLED_IMAGE = 47;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.WorkspaceImageImpl
     * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.WorkspaceImageImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getWorkspaceImage()
     * @generated
     */
    int WORKSPACE_IMAGE = 48;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.CustomStyleImpl
     * <em>Custom Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.CustomStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomStyle()
     * @generated
     */
    int CUSTOM_STYLE = 49;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.EdgeTargetImpl
     * <em>Edge Target</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.EdgeTargetImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeTarget()
     * @generated
     */
    int EDGE_TARGET = 50;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.StyleImpl <em>Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.StyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 55;

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
     * {@link org.eclipse.sirius.viewpoint.impl.EdgeStyleImpl
     * <em>Edge Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 51;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
     * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisCustomData()
     * @generated
     */
    int DANALYSIS_CUSTOM_DATA = 52;

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
     * {@link org.eclipse.sirius.viewpoint.impl.GaugeCompositeStyleImpl
     * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.GaugeCompositeStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGaugeCompositeStyle()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE = 53;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl
     * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBorderedStyle()
     * @generated
     */
    int BORDERED_STYLE = 56;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.NoteImpl <em>Note</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.NoteImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getNote()
     * @generated
     */
    int NOTE = 57;

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
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.DragAndDropTargetImpl
     * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DragAndDropTargetImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDragAndDropTarget()
     * @generated
     */
    int DRAG_AND_DROP_TARGET = 58;

    /**
     * The number of structural features of the '<em>Drag And Drop Target</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.FilterVariableHistoryImpl
     * <em>Filter Variable History</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.FilterVariableHistoryImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFilterVariableHistory()
     * @generated
     */
    int FILTER_VARIABLE_HISTORY = 59;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.FilterVariableValueImpl
     * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.FilterVariableValueImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFilterVariableValue()
     * @generated
     */
    int FILTER_VARIABLE_VALUE = 60;

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
     * {@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
     * <em>RGB Values</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.RGBValuesImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getRGBValues()
     * @generated
     */
    int RGB_VALUES = 61;

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
    int DANALYSIS_SESSION_EOBJECT = 62;

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
     * {@link org.eclipse.sirius.viewpoint.impl.CollapseFilterImpl
     * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.CollapseFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCollapseFilter()
     * @generated
     */
    int COLLAPSE_FILTER = 63;

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
     * {@link org.eclipse.sirius.viewpoint.impl.IndirectlyCollapseFilterImpl
     * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.IndirectlyCollapseFilterImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getIndirectlyCollapseFilter()
     * @generated
     */
    int INDIRECTLY_COLLAPSE_FILTER = 64;

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
     * {@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
     * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSessionManagerEObject()
     * @generated
     */
    int SESSION_MANAGER_EOBJECT = 65;

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
    int DRESOURCE = 66;

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
    int DFILE = 67;

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
    int DRESOURCE_CONTAINER = 68;

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
    int DPROJECT = 69;

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
    int DFOLDER = 70;

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
    int DMODEL = 71;

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
     * {@link org.eclipse.sirius.viewpoint.impl.BeginLabelStyleImpl
     * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.BeginLabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBeginLabelStyle()
     * @generated
     */
    int BEGIN_LABEL_STYLE = 73;

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
     * {@link org.eclipse.sirius.viewpoint.impl.CenterLabelStyleImpl
     * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.CenterLabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCenterLabelStyle()
     * @generated
     */
    int CENTER_LABEL_STYLE = 74;

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
     * {@link org.eclipse.sirius.viewpoint.impl.EndLabelStyleImpl
     * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.EndLabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEndLabelStyle()
     * @generated
     */
    int END_LABEL_STYLE = 75;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.impl.BracketEdgeStyleImpl
     * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.BracketEdgeStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBracketEdgeStyle()
     * @generated
     */
    int BRACKET_EDGE_STYLE = 76;

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
     * {@link org.eclipse.sirius.viewpoint.impl.ComputedStyleDescriptionRegistryImpl
     * <em>Computed Style Description Registry</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ComputedStyleDescriptionRegistryImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getComputedStyleDescriptionRegistry()
     * @generated
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY = 78;

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
     * {@link org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl
     * <em>Diagram Element Mapping2 Model Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDiagramElementMapping2ModelElement()
     * @generated
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT = 79;

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
     * {@link org.eclipse.sirius.viewpoint.impl.ModelElement2ViewVariableImpl
     * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ModelElement2ViewVariableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getModelElement2ViewVariable()
     * @generated
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE = 80;

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
     * {@link org.eclipse.sirius.viewpoint.impl.ViewVariable2ContainerVariableImpl
     * <em>View Variable2 Container Variable</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ViewVariable2ContainerVariableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getViewVariable2ContainerVariable()
     * @generated
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE = 81;

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
     * {@link org.eclipse.sirius.viewpoint.impl.ContainerVariable2StyleDescriptionImpl
     * <em>Container Variable2 Style Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.impl.ContainerVariable2StyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerVariable2StyleDescription()
     * @generated
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION = 82;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.ContainerLayout
     * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.ContainerLayout
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerLayout()
     * @generated
     */
    int CONTAINER_LAYOUT = 83;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.LabelPosition
     * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.LabelPosition
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelPosition()
     * @generated
     */
    int LABEL_POSITION = 84;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.ContainerShape
     * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.ContainerShape
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerShape()
     * @generated
     */
    int CONTAINER_SHAPE = 85;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.BackgroundStyle
     * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.BackgroundStyle
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBackgroundStyle()
     * @generated
     */
    int BACKGROUND_STYLE = 86;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.BundledImageShape
     * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.BundledImageShape
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBundledImageShape()
     * @generated
     */
    int BUNDLED_IMAGE_SHAPE = 87;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.LineStyle <em>Line Style</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.LineStyle
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 88;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.EdgeArrows <em>Edge Arrows</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeArrows()
     * @generated
     */
    int EDGE_ARROWS = 89;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.EdgeRouting <em>Edge Routing</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeRouting()
     * @generated
     */
    int EDGE_ROUTING = 90;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.AlignmentKind
     * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.AlignmentKind
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAlignmentKind()
     * @generated
     */
    int ALIGNMENT_KIND = 91;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFontFormat()
     * @generated
     */
    int FONT_FORMAT = 92;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.ResizeKind <em>Resize Kind</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.ResizeKind
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getResizeKind()
     * @generated
     */
    int RESIZE_KIND = 93;

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
    int LABEL_ALIGNMENT = 94;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.ArrangeConstraint
     * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.ArrangeConstraint
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getArrangeConstraint()
     * @generated
     */
    int ARRANGE_CONSTRAINT = 95;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSyncStatus()
     * @generated
     */
    int SYNC_STATUS = 96;

    /**
     * The meta object id for the '<em>Extended Package</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getExtendedPackage()
     * @generated
     */
    int EXTENDED_PACKAGE = 97;

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
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getDiagramSet
     * <em>Diagram Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Diagram Set</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationContainer#getDiagramSet()
     * @see #getDRepresentationContainer()
     * @generated
     */
    EReference getDRepresentationContainer_DiagramSet();

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
     * {@link org.eclipse.sirius.viewpoint.DDiagram <em>DDiagram</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram
     * @generated
     */
    EClass getDDiagram();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getOwnedDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_OwnedDiagramElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getDiagramElements
     * <em>Diagram Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Diagram Elements</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_DiagramElements();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getDescription()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Description();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getInfo <em>Info</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Info</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getInfo()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Info();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getSubDiagrams
     * <em>Sub Diagrams</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Diagrams</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getSubDiagrams()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_SubDiagrams();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getEdges()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Edges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getNodes()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getNodeListElements
     * <em>Node List Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Node List Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getNodeListElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_NodeListElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getContainers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Containers();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getCurrentConcern
     * <em>Current Concern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Current Concern</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getCurrentConcern()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_CurrentConcern();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedFilters
     * <em>Activated Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activated Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getActivatedFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getAllFilters
     * <em>All Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getAllFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_AllFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedRules
     * <em>Activated Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Activated Rules</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getActivatedRules()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedRules();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getActivateBehaviors
     * <em>Activate Behaviors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activate Behaviors</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getActivateBehaviors()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivateBehaviors();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getFilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getFilterVariableHistory()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_FilterVariableHistory();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedLayers
     * <em>Activated Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Activated Layers</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getActivatedLayers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedLayers();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#isSynchronized
     * <em>Synchronized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Synchronized</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#isSynchronized()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Synchronized();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getHiddenElements
     * <em>Hidden Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Hidden Elements</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getHiddenElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_HiddenElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#isIsInLayoutingMode
     * <em>Is In Layouting Mode</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is In Layouting Mode</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DDiagram#isIsInLayoutingMode()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_IsInLayoutingMode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getHeaderHeight
     * <em>Header Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Header Height</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagram#getHeaderHeight()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_HeaderHeight();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDiagram
     * <em>DSemantic Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DSemantic Diagram</em>'.
     * @see org.eclipse.sirius.viewpoint.DSemanticDiagram
     * @generated
     */
    EClass getDSemanticDiagram();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement
     * <em>DDiagram Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DDiagram Element</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement
     * @generated
     */
    EClass getDDiagramElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#isVisible()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_Visible();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tooltip Text</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#getTooltipText()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_TooltipText();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getParentLayers
     * <em>Parent Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Parent Layers</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#getParentLayers()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_ParentLayers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getDecorations
     * <em>Decorations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Decorations</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#getDecorations()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_Decorations();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getDiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#getDiagramElementMapping()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_DiagramElementMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getGraphicalFilters
     * <em>Graphical Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Graphical Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement#getGraphicalFilters()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_GraphicalFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.GraphicalFilter
     * <em>Graphical Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Graphical Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.GraphicalFilter
     * @generated
     */
    EClass getGraphicalFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.HideFilter <em>Hide Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hide Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.HideFilter
     * @generated
     */
    EClass getHideFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.HideLabelFilter
     * <em>Hide Label Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Hide Label Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.HideLabelFilter
     * @generated
     */
    EClass getHideLabelFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.FoldingPointFilter
     * <em>Folding Point Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Point Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.FoldingPointFilter
     * @generated
     */
    EClass getFoldingPointFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.FoldingFilter
     * <em>Folding Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.FoldingFilter
     * @generated
     */
    EClass getFoldingFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Applied Composite Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.AppliedCompositeFilters
     * @generated
     */
    EClass getAppliedCompositeFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.AppliedCompositeFilters#getCompositeFilterDescriptions
     * <em>Composite Filter Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Composite Filter Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.AppliedCompositeFilters#getCompositeFilterDescriptions()
     * @see #getAppliedCompositeFilters()
     * @generated
     */
    EReference getAppliedCompositeFilters_CompositeFilterDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter
     * <em>Absolute Bounds Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Absolute Bounds Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter
     * @generated
     */
    EClass getAbsoluteBoundsFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getX <em>X</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getX()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getY <em>Y</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getY()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Y();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getHeight()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Height();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter#getWidth()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Width();

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
     * {@link org.eclipse.sirius.viewpoint.DDiagramLink <em>DDiagram Link</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Link</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramLink
     * @generated
     */
    EClass getDDiagramLink();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramLink#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramLink#getTarget()
     * @see #getDDiagramLink()
     * @generated
     */
    EReference getDDiagramLink_Target();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramLink#getNode <em>Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Node</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramLink#getNode()
     * @see #getDDiagramLink()
     * @generated
     */
    EReference getDDiagramLink_Node();

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
     * {@link org.eclipse.sirius.viewpoint.AbstractDNode
     * <em>Abstract DNode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract DNode</em>'.
     * @see org.eclipse.sirius.viewpoint.AbstractDNode
     * @generated
     */
    EClass getAbstractDNode();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.AbstractDNode#getOwnedBorderedNodes
     * <em>Owned Bordered Nodes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Bordered Nodes</em>'.
     * @see org.eclipse.sirius.viewpoint.AbstractDNode#getOwnedBorderedNodes()
     * @see #getAbstractDNode()
     * @generated
     */
    EReference getAbstractDNode_OwnedBorderedNodes();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.AbstractDNode#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.viewpoint.AbstractDNode#getArrangeConstraints()
     * @see #getAbstractDNode()
     * @generated
     */
    EAttribute getAbstractDNode_ArrangeConstraints();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNode <em>DNode</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode
     * @generated
     */
    EClass getDNode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNode#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getWidth()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNode#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getHeight()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DNode#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getOwnedStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNode#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getLabelPosition()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_LabelPosition();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DNode#getOwnedDetails
     * <em>Owned Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Details</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getOwnedDetails()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedDetails();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNode#getResizeKind
     * <em>Resize Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getResizeKind()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_ResizeKind();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DNode#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getOriginalStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DNode#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getActualMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DNode#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DNode#getCandidatesMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Element Container</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer
     * @generated
     */
    EClass getDDiagramElementContainer();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getNodes()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getContainers()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Containers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getElements()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Elements();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedStyle();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedDetails
     * <em>Owned Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Details</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedDetails()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedDetails();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOriginalStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getActualMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getCandidatesMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_CandidatesMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getWidth()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer#getHeight()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Height();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNodeContainer
     * <em>DNode Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode Container</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeContainer
     * @generated
     */
    EClass getDNodeContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DNodeContainer#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeContainer#getOwnedDiagramElements()
     * @see #getDNodeContainer()
     * @generated
     */
    EReference getDNodeContainer_OwnedDiagramElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNodeContainer#getChildrenPresentation
     * <em>Children Presentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Children Presentation</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.DNodeContainer#getChildrenPresentation()
     * @see #getDNodeContainer()
     * @generated
     */
    EAttribute getDNodeContainer_ChildrenPresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode List</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeList
     * @generated
     */
    EClass getDNodeList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.DNodeList#getOwnedElements
     * <em>Owned Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeList#getOwnedElements()
     * @see #getDNodeList()
     * @generated
     */
    EReference getDNodeList_OwnedElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DNodeList#getLineWidth
     * <em>Line Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Width</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeList#getLineWidth()
     * @see #getDNodeList()
     * @generated
     */
    EAttribute getDNodeList_LineWidth();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement
     * <em>DNode List Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DNode List Element</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement
     * @generated
     */
    EClass getDNodeListElement();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement#getOwnedStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement#getOriginalStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement#getActualMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement#getCandidatesMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DEdge <em>DEdge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DEdge</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge
     * @generated
     */
    EClass getDEdge();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getOwnedStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getSize()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_Size();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getSourceNode
     * <em>Source Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Source Node</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getSourceNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_SourceNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getTargetNode
     * <em>Target Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target Node</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getTargetNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_TargetNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getActualMapping()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_ActualMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getRoutingStyle()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_RoutingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#isIsFold <em>Is Fold</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Fold</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#isIsFold()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsFold();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#isIsMockEdge
     * <em>Is Mock Edge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Mock Edge</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#isIsMockEdge()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsMockEdge();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getOriginalStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OriginalStyle();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getPath <em>Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Path</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getPath()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_Path();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getArrangeConstraints()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_ArrangeConstraints();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getBeginLabel
     * <em>Begin Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Begin Label</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getBeginLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_BeginLabel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getEndLabel <em>End Label</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>End Label</em>'.
     * @see org.eclipse.sirius.viewpoint.DEdge#getEndLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_EndLabel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet <em>DDiagram Set</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Set</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramSet
     * @generated
     */
    EClass getDDiagramSet();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramSet#getDescription()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_Description();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getDiagrams
     * <em>Diagrams</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Diagrams</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramSet#getDiagrams()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_Diagrams();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getView <em>View</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>View</em>'.
     * @see org.eclipse.sirius.viewpoint.DDiagramSet#getView()
     * @see #getDDiagramSet()
     * @generated
     */
    EReference getDDiagramSet_View();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.viewpoint.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.NodeStyle#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.viewpoint.NodeStyle#getLabelPosition()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.NodeStyle#isHideLabelByDefault
     * <em>Hide Label By Default</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Hide Label By Default</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.NodeStyle#isHideLabelByDefault()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_HideLabelByDefault();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Dot <em>Dot</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dot</em>'.
     * @see org.eclipse.sirius.viewpoint.Dot
     * @generated
     */
    EClass getDot();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.Dot#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.Dot#getBackgroundColor()
     * @see #getDot()
     * @generated
     */
    EReference getDot_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Dot#getStrokeSizeComputationExpression
     * <em>Stroke Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Stroke Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.Dot#getStrokeSizeComputationExpression()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_StrokeSizeComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection <em>Gauge Section</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Section</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection
     * @generated
     */
    EClass getGaugeSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getMin <em>Min</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getMin()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Min();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getMax <em>Max</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getMax()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Max();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getValue <em>Value</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getValue()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Value();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getLabel <em>Label</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getLabel()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Label();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getBackgroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeSection#getForegroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.ContainerStyle
     * <em>Container Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Style</em>'.
     * @see org.eclipse.sirius.viewpoint.ContainerStyle
     * @generated
     */
    EClass getContainerStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Flat Container Style</em>'.
     * @see org.eclipse.sirius.viewpoint.FlatContainerStyle
     * @generated
     */
    EClass getFlatContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundStyle()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.viewpoint.FlatContainerStyle#getForegroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Shape Container Style</em>'.
     * @see org.eclipse.sirius.viewpoint.ShapeContainerStyle
     * @generated
     */
    EClass getShapeContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.ShapeContainerStyle#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.ShapeContainerStyle#getShape()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.ShapeContainerStyle#getBackgroundColor()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EReference getShapeContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Square <em>Square</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Square</em>'.
     * @see org.eclipse.sirius.viewpoint.Square
     * @generated
     */
    EClass getSquare();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Square#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.Square#getWidth()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Square#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.Square#getHeight()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.Square#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.Square#getColor()
     * @see #getSquare()
     * @generated
     */
    EReference getSquare_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Ellipse</em>'.
     * @see org.eclipse.sirius.viewpoint.Ellipse
     * @generated
     */
    EClass getEllipse();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Horizontal Diameter</em>'.
     * @see org.eclipse.sirius.viewpoint.Ellipse#getHorizontalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_HorizontalDiameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Vertical Diameter</em>'.
     * @see org.eclipse.sirius.viewpoint.Ellipse#getVerticalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_VerticalDiameter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.Ellipse#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.Ellipse#getColor()
     * @see #getEllipse()
     * @generated
     */
    EReference getEllipse_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Lozenge</em>'.
     * @see org.eclipse.sirius.viewpoint.Lozenge
     * @generated
     */
    EClass getLozenge();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Lozenge#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.Lozenge#getWidth()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.Lozenge#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.Lozenge#getHeight()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.Lozenge#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.Lozenge#getColor()
     * @see #getLozenge()
     * @generated
     */
    EReference getLozenge_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.BundledImage <em>Bundled Image</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bundled Image</em>'.
     * @see org.eclipse.sirius.viewpoint.BundledImage
     * @generated
     */
    EClass getBundledImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BundledImage#getShape <em>Shape</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.BundledImage#getShape()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.BundledImage#getColor <em>Color</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.BundledImage#getColor()
     * @see #getBundledImage()
     * @generated
     */
    EReference getBundledImage_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.WorkspaceImage
     * <em>Workspace Image</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Workspace Image</em>'.
     * @see org.eclipse.sirius.viewpoint.WorkspaceImage
     * @generated
     */
    EClass getWorkspaceImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.viewpoint.WorkspaceImage#getWorkspacePath()
     * @see #getWorkspaceImage()
     * @generated
     */
    EAttribute getWorkspaceImage_WorkspacePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.CustomStyle <em>Custom Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Custom Style</em>'.
     * @see org.eclipse.sirius.viewpoint.CustomStyle
     * @generated
     */
    EClass getCustomStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.CustomStyle#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.viewpoint.CustomStyle#getId()
     * @see #getCustomStyle()
     * @generated
     */
    EAttribute getCustomStyle_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget <em>Edge Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Target</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeTarget
     * @generated
     */
    EClass getEdgeTarget();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeTarget#getOutgoingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_OutgoingEdges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget#getIncomingEdges
     * <em>Incoming Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeTarget#getIncomingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_IncomingEdges();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getStrokeColor
     * <em>Stroke Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Stroke Color</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getStrokeColor()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_StrokeColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getLineStyle
     * <em>Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getLineStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getSourceArrow
     * <em>Source Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getSourceArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_SourceArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getTargetArrow
     * <em>Target Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getTargetArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_TargetArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getFoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getFoldingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_FoldingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getSize <em>Size</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getSize()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Size();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getRoutingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_RoutingStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getBeginLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_BeginLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Center Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getCenterLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_CenterLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>End Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle#getEndLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_EndLabelStyle();

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
     * {@link org.eclipse.sirius.viewpoint.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Composite Style</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeCompositeStyle
     * @generated
     */
    EClass getGaugeCompositeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeCompositeStyle#getAlignment()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EAttribute getGaugeCompositeStyle_Alignment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.GaugeCompositeStyle#getSections
     * <em>Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sections</em>'.
     * @see org.eclipse.sirius.viewpoint.GaugeCompositeStyle#getSections()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EReference getGaugeCompositeStyle_Sections();

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
     * {@link org.eclipse.sirius.viewpoint.BorderedStyle
     * <em>Bordered Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bordered Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BorderedStyle
     * @generated
     */
    EClass getBorderedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BorderedStyle#getBorderSize
     * <em>Border Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.viewpoint.BorderedStyle#getBorderSize()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.BorderedStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.BorderedStyle#getBorderSizeComputationExpression()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.BorderedStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Border Color</em>'.
     * @see org.eclipse.sirius.viewpoint.BorderedStyle#getBorderColor()
     * @see #getBorderedStyle()
     * @generated
     */
    EReference getBorderedStyle_BorderColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.Note <em>Note</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Note</em>'.
     * @see org.eclipse.sirius.viewpoint.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.Note#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.Note#getColor()
     * @see #getNote()
     * @generated
     */
    EReference getNote_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.DragAndDropTarget
     * <em>Drag And Drop Target</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Drag And Drop Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DragAndDropTarget
     * @generated
     */
    EClass getDragAndDropTarget();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.viewpoint.FilterVariableHistory
     * @generated
     */
    EClass getFilterVariableHistory();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableHistory#getOwnedValues
     * <em>Owned Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Values</em>'.
     * @see org.eclipse.sirius.viewpoint.FilterVariableHistory#getOwnedValues()
     * @see #getFilterVariableHistory()
     * @generated
     */
    EReference getFilterVariableHistory_OwnedValues();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableValue
     * <em>Filter Variable Value</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable Value</em>'.
     * @see org.eclipse.sirius.viewpoint.FilterVariableValue
     * @generated
     */
    EClass getFilterVariableValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableValue#getVariableDefinition
     * <em>Variable Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Variable Definition</em>'.
     * @see org.eclipse.sirius.viewpoint.FilterVariableValue#getVariableDefinition()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_VariableDefinition();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableValue#getModelElement
     * <em>Model Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.eclipse.sirius.viewpoint.FilterVariableValue#getModelElement()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_ModelElement();

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
     * {@link org.eclipse.sirius.viewpoint.CollapseFilter
     * <em>Collapse Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Collapse Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.CollapseFilter
     * @generated
     */
    EClass getCollapseFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.CollapseFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.CollapseFilter#getWidth()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.CollapseFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.CollapseFilter#getHeight()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Height();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Indirectly Collapse Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter
     * @generated
     */
    EClass getIndirectlyCollapseFilter();

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
     * {@link org.eclipse.sirius.viewpoint.BeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BeginLabelStyle
     * @generated
     */
    EClass getBeginLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.CenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Center Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.CenterLabelStyle
     * @generated
     */
    EClass getCenterLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.EndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>End Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.EndLabelStyle
     * @generated
     */
    EClass getEndLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.BracketEdgeStyle
     * <em>Bracket Edge Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Bracket Edge Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BracketEdgeStyle
     * @generated
     */
    EClass getBracketEdgeStyle();

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
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Computed Style Description Registry</em>'.
     * @see org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry
     * @generated
     */
    EClass getComputedStyleDescriptionRegistry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions
     * <em>Computed Style Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Computed Style Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry#getCache
     * <em>Cache</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Cache</em>'.
     * @see org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry#getCache()
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
     * @model 
     *        keyType="org.eclipse.sirius.viewpoint.description.DiagramElementMapping"
     *        keyRequired="true" valueMapType=
     *        "org.eclipse.sirius.viewpoint.ModelElement2ViewVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.viewpoint.ViewVariable2ContainerVariable>"
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
     *        "org.eclipse.sirius.viewpoint.ViewVariable2ContainerVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.viewpoint.ContainerVariable2StyleDescription>"
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
     *        "org.eclipse.sirius.viewpoint.ContainerVariable2StyleDescription<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.viewpoint.description.style.StyleDescription>"
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
     *        valueType=
     *        "org.eclipse.sirius.viewpoint.description.style.StyleDescription"
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
     * {@link org.eclipse.sirius.viewpoint.ContainerLayout
     * <em>Container Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Container Layout</em>'.
     * @see org.eclipse.sirius.viewpoint.ContainerLayout
     * @generated
     */
    EEnum getContainerLayout();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.LabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelPosition
     * @generated
     */
    EEnum getLabelPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.ContainerShape
     * <em>Container Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Container Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.ContainerShape
     * @generated
     */
    EEnum getContainerShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.BackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Background Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BackgroundStyle
     * @generated
     */
    EEnum getBackgroundStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.BundledImageShape
     * <em>Bundled Image Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Bundled Image Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.BundledImageShape
     * @generated
     */
    EEnum getBundledImageShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.LineStyle <em>Line Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see org.eclipse.sirius.viewpoint.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.EdgeArrows <em>Edge Arrows</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Arrows</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @generated
     */
    EEnum getEdgeArrows();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.EdgeRouting <em>Edge Routing</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Routing</em>'.
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @generated
     */
    EEnum getEdgeRouting();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.AlignmentKind
     * <em>Alignment Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Alignment Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.AlignmentKind
     * @generated
     */
    EEnum getAlignmentKind();

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
     * {@link org.eclipse.sirius.viewpoint.ResizeKind <em>Resize Kind</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.ResizeKind
     * @generated
     */
    EEnum getResizeKind();

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
     * {@link org.eclipse.sirius.viewpoint.ArrangeConstraint
     * <em>Arrange Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Arrange Constraint</em>'.
     * @see org.eclipse.sirius.viewpoint.ArrangeConstraint
     * @generated
     */
    EEnum getArrangeConstraint();

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
         * {@link org.eclipse.sirius.viewpoint.impl.DDiagramImpl
         * <em>DDiagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagram()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DSemanticDiagramImpl
         * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DSemanticDiagramImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDiagram()
         * @generated
         */
        EClass DSEMANTIC_DIAGRAM = eINSTANCE.getDSemanticDiagram();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl
         * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramElement()
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
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
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
         * {@link org.eclipse.sirius.viewpoint.GraphicalFilter
         * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.GraphicalFilter
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGraphicalFilter()
         * @generated
         */
        EClass GRAPHICAL_FILTER = eINSTANCE.getGraphicalFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.HideFilterImpl
         * <em>Hide Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.HideFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getHideFilter()
         * @generated
         */
        EClass HIDE_FILTER = eINSTANCE.getHideFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.HideLabelFilterImpl
         * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.HideLabelFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getHideLabelFilter()
         * @generated
         */
        EClass HIDE_LABEL_FILTER = eINSTANCE.getHideLabelFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.FoldingPointFilterImpl
         * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.FoldingPointFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFoldingPointFilter()
         * @generated
         */
        EClass FOLDING_POINT_FILTER = eINSTANCE.getFoldingPointFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.FoldingFilterImpl
         * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.FoldingFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFoldingFilter()
         * @generated
         */
        EClass FOLDING_FILTER = eINSTANCE.getFoldingFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.AppliedCompositeFiltersImpl
         * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.AppliedCompositeFiltersImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAppliedCompositeFilters()
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
         * {@link org.eclipse.sirius.viewpoint.impl.AbsoluteBoundsFilterImpl
         * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.AbsoluteBoundsFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAbsoluteBoundsFilter()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl
         * <em>DDiagram Link</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramLink()
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
         * {@link org.eclipse.sirius.viewpoint.AbstractDNode
         * <em>Abstract DNode</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.AbstractDNode
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAbstractDNode()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DNodeImpl <em>DNode</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNode()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl
         * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramElementContainer()
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
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__WIDTH = eINSTANCE.getDDiagramElementContainer_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = eINSTANCE.getDDiagramElementContainer_Height();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.DNodeContainerImpl
         * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNodeContainerImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeContainer()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DNodeListImpl
         * <em>DNode List</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNodeListImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeList()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl
         * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDNodeListElement()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DEdgeImpl <em>DEdge</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDEdge()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl
         * <em>DDiagram Set</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DDiagramSetImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDDiagramSet()
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
         * {@link org.eclipse.sirius.viewpoint.impl.NodeStyleImpl
         * <em>Node Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.NodeStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getNodeStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DotImpl <em>Dot</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DotImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDot()
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
         * {@link org.eclipse.sirius.viewpoint.impl.GaugeSectionImpl
         * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.GaugeSectionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGaugeSection()
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
         * {@link org.eclipse.sirius.viewpoint.impl.ContainerStyleImpl
         * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ContainerStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerStyle()
         * @generated
         */
        EClass CONTAINER_STYLE = eINSTANCE.getContainerStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl
         * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFlatContainerStyle()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE = eINSTANCE.getFlatContainerStyle();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = eINSTANCE.getFlatContainerStyle_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = eINSTANCE.getFlatContainerStyle_BackgroundColor();

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
         * {@link org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl
         * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getShapeContainerStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.SquareImpl <em>Square</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.SquareImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSquare()
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
         * {@link org.eclipse.sirius.viewpoint.impl.EllipseImpl
         * <em>Ellipse</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.EllipseImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEllipse()
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
         * {@link org.eclipse.sirius.viewpoint.impl.LozengeImpl
         * <em>Lozenge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.LozengeImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLozenge()
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
         * {@link org.eclipse.sirius.viewpoint.impl.BundledImageImpl
         * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.BundledImageImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBundledImage()
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
         * {@link org.eclipse.sirius.viewpoint.impl.WorkspaceImageImpl
         * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.WorkspaceImageImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getWorkspaceImage()
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
         * {@link org.eclipse.sirius.viewpoint.impl.CustomStyleImpl
         * <em>Custom Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.CustomStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.EdgeTargetImpl
         * <em>Edge Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.EdgeTargetImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeTarget()
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
         * {@link org.eclipse.sirius.viewpoint.impl.EdgeStyleImpl
         * <em>Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.GaugeCompositeStyleImpl
         * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.GaugeCompositeStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getGaugeCompositeStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl
         * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBorderedStyle()
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
         * {@link org.eclipse.sirius.viewpoint.impl.NoteImpl <em>Note</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.NoteImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getNote()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DragAndDropTargetImpl
         * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DragAndDropTargetImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDragAndDropTarget()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET = eINSTANCE.getDragAndDropTarget();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.FilterVariableHistoryImpl
         * <em>Filter Variable History</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.FilterVariableHistoryImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFilterVariableHistory()
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
         * {@link org.eclipse.sirius.viewpoint.impl.FilterVariableValueImpl
         * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.FilterVariableValueImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFilterVariableValue()
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
         * {@link org.eclipse.sirius.viewpoint.impl.CollapseFilterImpl
         * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.CollapseFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCollapseFilter()
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
         * {@link org.eclipse.sirius.viewpoint.impl.IndirectlyCollapseFilterImpl
         * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.IndirectlyCollapseFilterImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getIndirectlyCollapseFilter()
         * @generated
         */
        EClass INDIRECTLY_COLLAPSE_FILTER = eINSTANCE.getIndirectlyCollapseFilter();

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
         * {@link org.eclipse.sirius.viewpoint.impl.BeginLabelStyleImpl
         * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.BeginLabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBeginLabelStyle()
         * @generated
         */
        EClass BEGIN_LABEL_STYLE = eINSTANCE.getBeginLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.CenterLabelStyleImpl
         * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.CenterLabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCenterLabelStyle()
         * @generated
         */
        EClass CENTER_LABEL_STYLE = eINSTANCE.getCenterLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.EndLabelStyleImpl
         * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.EndLabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEndLabelStyle()
         * @generated
         */
        EClass END_LABEL_STYLE = eINSTANCE.getEndLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.impl.BracketEdgeStyleImpl
         * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.BracketEdgeStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBracketEdgeStyle()
         * @generated
         */
        EClass BRACKET_EDGE_STYLE = eINSTANCE.getBracketEdgeStyle();

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
         * {@link org.eclipse.sirius.viewpoint.impl.ComputedStyleDescriptionRegistryImpl
         * <em>Computed Style Description Registry</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ComputedStyleDescriptionRegistryImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getComputedStyleDescriptionRegistry()
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
         * {@link org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl
         * <em>Diagram Element Mapping2 Model Element</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDiagramElementMapping2ModelElement()
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
         * {@link org.eclipse.sirius.viewpoint.impl.ModelElement2ViewVariableImpl
         * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ModelElement2ViewVariableImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getModelElement2ViewVariable()
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
         * {@link org.eclipse.sirius.viewpoint.impl.ViewVariable2ContainerVariableImpl
         * <em>View Variable2 Container Variable</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ViewVariable2ContainerVariableImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getViewVariable2ContainerVariable()
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
         * {@link org.eclipse.sirius.viewpoint.impl.ContainerVariable2StyleDescriptionImpl
         * <em>Container Variable2 Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.impl.ContainerVariable2StyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerVariable2StyleDescription()
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
         * {@link org.eclipse.sirius.viewpoint.ContainerLayout
         * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.ContainerLayout
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerLayout()
         * @generated
         */
        EEnum CONTAINER_LAYOUT = eINSTANCE.getContainerLayout();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.LabelPosition
         * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.LabelPosition
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelPosition()
         * @generated
         */
        EEnum LABEL_POSITION = eINSTANCE.getLabelPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.ContainerShape
         * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.ContainerShape
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getContainerShape()
         * @generated
         */
        EEnum CONTAINER_SHAPE = eINSTANCE.getContainerShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.BackgroundStyle
         * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.BackgroundStyle
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBackgroundStyle()
         * @generated
         */
        EEnum BACKGROUND_STYLE = eINSTANCE.getBackgroundStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.BundledImageShape
         * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.BundledImageShape
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBundledImageShape()
         * @generated
         */
        EEnum BUNDLED_IMAGE_SHAPE = eINSTANCE.getBundledImageShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.LineStyle <em>Line Style</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.LineStyle
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.EdgeArrows <em>Edge Arrows</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.EdgeArrows
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeArrows()
         * @generated
         */
        EEnum EDGE_ARROWS = eINSTANCE.getEdgeArrows();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.EdgeRouting
         * <em>Edge Routing</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.EdgeRouting
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getEdgeRouting()
         * @generated
         */
        EEnum EDGE_ROUTING = eINSTANCE.getEdgeRouting();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.AlignmentKind
         * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.AlignmentKind
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getAlignmentKind()
         * @generated
         */
        EEnum ALIGNMENT_KIND = eINSTANCE.getAlignmentKind();

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
         * {@link org.eclipse.sirius.viewpoint.ResizeKind <em>Resize Kind</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.ResizeKind
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getResizeKind()
         * @generated
         */
        EEnum RESIZE_KIND = eINSTANCE.getResizeKind();

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
         * {@link org.eclipse.sirius.viewpoint.ArrangeConstraint
         * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.ArrangeConstraint
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getArrangeConstraint()
         * @generated
         */
        EEnum ARRANGE_CONSTRAINT = eINSTANCE.getArrangeConstraint();

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
