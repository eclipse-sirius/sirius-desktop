/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
 * @see org.eclipse.sirius.viewpoint.ViewpointFactory
 * @model kind="package" annotation="Tags deprecated='Anything tagged as deprecated will disappear quite soon.\n\n'
 *        to\040be\040renamed='Anything tagged \"to be renamed\" will be renamed, at least in the UI\n'"
 * @generated
 */
public interface ViewpointPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "viewpoint"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "viewpoint"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ViewpointPackage eINSTANCE = org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.IdentifiedElementImpl <em>Identified
     * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.IdentifiedElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getIdentifiedElement()
     * @generated
     */
    int IDENTIFIED_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IDENTIFIED_ELEMENT__UID = 0;

    /**
     * The number of structural features of the '<em>Identified Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IDENTIFIED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl <em>DAnalysis</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysis()
     * @generated
     */
    int DANALYSIS = 1;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Referenced Analysis</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__REFERENCED_ANALYSIS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Resources</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__SEMANTIC_RESOURCES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__MODELS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__EANNOTATIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Views</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__OWNED_VIEWS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Selected Views</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__SELECTED_VIEWS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Owned Feature Extensions</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__OWNED_FEATURE_EXTENSIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS__VERSION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>DAnalysis</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl <em>DFeature
     * Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFeatureExtension()
     * @generated
     */
    int DFEATURE_EXTENSION = 2;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_EXTENSION__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_EXTENSION__DESCRIPTION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DFeature Extension</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_EXTENSION_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDStylizable()
     * @generated
     */
    int DSTYLIZABLE = 3;

    /**
     * The number of structural features of the '<em>DStylizable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DSTYLIZABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRefreshable()
     * @generated
     */
    int DREFRESHABLE = 4;

    /**
     * The number of structural features of the '<em>DRefreshable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DREFRESHABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.DMappingBased <em>DMapping Based</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDMappingBased()
     * @generated
     */
    int DMAPPING_BASED = 5;

    /**
     * The number of structural features of the '<em>DMapping Based</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DMAPPING_BASED_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DViewImpl <em>DView</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DViewImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDView()
     * @generated
     */
    int DVIEW = 10;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl <em>DSemantic
     * Decorator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDecorator()
     * @generated
     */
    int DSEMANTIC_DECORATOR = 6;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DECORATOR__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DECORATOR__TARGET = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DSemantic Decorator</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DECORATOR_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl
     * <em>DRepresentation Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationDescriptor()
     * @generated
     */
    int DREPRESENTATION_DESCRIPTOR = 7;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__EANNOTATIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__NAME = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__DESCRIPTION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__TARGET = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Representation</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__REPRESENTATION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Rep Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR__REP_PATH = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>DRepresentation Descriptor</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_DESCRIPTOR_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
     * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentation()
     * @generated
     */
    int DREPRESENTATION = 8;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__EANNOTATIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__DOCUMENTATION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__REPRESENTATION_ELEMENTS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__NAME = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION__UI_STATE = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>DRepresentation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
     * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationElement()
     * @generated
     */
    int DREPRESENTATION_ELEMENT = 9;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__TARGET = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__NAME = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>DRepresentation Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DREPRESENTATION_ELEMENT_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Viewpoint</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW__VIEWPOINT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Owned Representation Descriptors</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW__OWNED_REPRESENTATION_DESCRIPTORS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW__OWNED_EXTENSIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW__MODELS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>DView</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DVIEW_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl <em>Meta Model
     * Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getMetaModelExtension()
     * @generated
     */
    int META_MODEL_EXTENSION = 11;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int META_MODEL_EXTENSION__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Extension Group</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int META_MODEL_EXTENSION__EXTENSION_GROUP = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Meta Model Extension</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int META_MODEL_EXTENSION_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DecorationImpl <em>Decoration</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DecorationImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDecoration()
     * @generated
     */
    int DECORATION = 12;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DECORATION__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DECORATION__DESCRIPTION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Decoration</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DECORATION_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl <em>DAnalysis Custom
     * Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisCustomData()
     * @generated
     */
    int DANALYSIS_CUSTOM_DATA = 13;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA__KEY = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA__DATA = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DAnalysis Custom Data</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_CUSTOM_DATA_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.CustomizableImpl <em>Customizable</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.CustomizableImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomizable()
     * @generated
     */
    int CUSTOMIZABLE = 25;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOMIZABLE__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOMIZABLE__CUSTOM_FEATURES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Customizable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOMIZABLE_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl <em>Basic Label
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBasicLabelStyle()
     * @generated
     */
    int BASIC_LABEL_STYLE = 24;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__UID = ViewpointPackage.CUSTOMIZABLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__SHOW_ICON = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__ICON_PATH = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Basic Label Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.LabelStyleImpl <em>Label Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelStyle()
     * @generated
     */
    int LABEL_STYLE = 14;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__UID = ViewpointPackage.BASIC_LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_ALIGNMENT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.StyleImpl <em>Style</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.StyleImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 15;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__CUSTOM_FEATURES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__DESCRIPTION = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '<em>RGB Values</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.RGBValues
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getRGBValues()
     * @generated
     */
    int RGB_VALUES = 33;

    /**
     * The meta object id for the '<em>Resource Descriptor</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.business.api.resource.ResourceDescriptor
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getResourceDescriptor()
     * @generated
     */
    int RESOURCE_DESCRIPTOR = 34;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.IdentifiedElement <em>Identified
     * Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Identified Element</em>'.
     * @see org.eclipse.sirius.viewpoint.IdentifiedElement
     * @generated
     */
    EClass getIdentifiedElement();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.IdentifiedElement#getUid
     * <em>Uid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Uid</em>'.
     * @see org.eclipse.sirius.viewpoint.IdentifiedElement#getUid()
     * @see #getIdentifiedElement()
     * @generated
     */
    EAttribute getIdentifiedElement_Uid();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl <em>DAnalysis
     * Session EObject</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisSessionEObject()
     * @generated
     */
    int DANALYSIS_SESSION_EOBJECT = 16;

    /**
     * The feature id for the '<em><b>Open</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__OPEN = 0;

    /**
     * The feature id for the '<em><b>Resources</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__RESOURCES = 1;

    /**
     * The feature id for the '<em><b>Controlled Resources</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES = 2;

    /**
     * The feature id for the '<em><b>Activated Viewpoints</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS = 3;

    /**
     * The feature id for the '<em><b>Analyses</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__ANALYSES = 4;

    /**
     * The feature id for the '<em><b>Synchronization Status</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS = 5;

    /**
     * The number of structural features of the '<em>DAnalysis Session EObject</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DANALYSIS_SESSION_EOBJECT_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl <em>Session
     * Manager EObject</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSessionManagerEObject()
     * @generated
     */
    int SESSION_MANAGER_EOBJECT = 17;

    /**
     * The feature id for the '<em><b>Owned Sessions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SESSION_MANAGER_EOBJECT__OWNED_SESSIONS = 0;

    /**
     * The number of structural features of the '<em>Session Manager EObject</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SESSION_MANAGER_EOBJECT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.DResource
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResource()
     * @generated
     */
    int DRESOURCE = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE__NAME = 0;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE__PATH = 1;

    /**
     * The number of structural features of the '<em>DResource</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DFileImpl <em>DFile</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DFileImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFile()
     * @generated
     */
    int DFILE = 19;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFILE__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFILE__NAME = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFILE__PATH = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DFile</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFILE_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl <em>DResource
     * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResourceContainer()
     * @generated
     */
    int DRESOURCE_CONTAINER = 20;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__NAME = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__PATH = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER__MEMBERS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>DResource Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRESOURCE_CONTAINER_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DProjectImpl <em>DProject</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DProjectImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDProject()
     * @generated
     */
    int DPROJECT = 21;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DPROJECT__UID = ViewpointPackage.DRESOURCE_CONTAINER__UID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DPROJECT__NAME = ViewpointPackage.DRESOURCE_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DPROJECT__PATH = ViewpointPackage.DRESOURCE_CONTAINER__PATH;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DPROJECT__MEMBERS = ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS;

    /**
     * The number of structural features of the '<em>DProject</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DPROJECT_FEATURE_COUNT = ViewpointPackage.DRESOURCE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DFolderImpl <em>DFolder</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DFolderImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFolder()
     * @generated
     */
    int DFOLDER = 22;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFOLDER__UID = ViewpointPackage.DRESOURCE_CONTAINER__UID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFOLDER__NAME = ViewpointPackage.DRESOURCE_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFOLDER__PATH = ViewpointPackage.DRESOURCE_CONTAINER__PATH;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFOLDER__MEMBERS = ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS;

    /**
     * The number of structural features of the '<em>DFolder</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFOLDER_FEATURE_COUNT = ViewpointPackage.DRESOURCE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.DModelImpl <em>DModel</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.DModelImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDModel()
     * @generated
     */
    int DMODEL = 23;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DMODEL__UID = ViewpointPackage.DFILE__UID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DMODEL__NAME = ViewpointPackage.DFILE__NAME;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DMODEL__PATH = ViewpointPackage.DFILE__PATH;

    /**
     * The number of structural features of the '<em>DModel</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DMODEL_FEATURE_COUNT = ViewpointPackage.DFILE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl <em>UI State</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.UIStateImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getUIState()
     * @generated
     */
    int UI_STATE = 26;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE__INVERSE_SELECTION_ORDER = 0;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE__ELEMENTS_TO_SELECT = 1;

    /**
     * The feature id for the '<em><b>Decoration Image</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE__DECORATION_IMAGE = 2;

    /**
     * The feature id for the '<em><b>Tool Sections</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE__TOOL_SECTIONS = 3;

    /**
     * The feature id for the '<em><b>Sub Diagram Decoration Descriptors</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS = 4;

    /**
     * The number of structural features of the '<em>UI State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UI_STATE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl <em>Tool Instance</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolInstance()
     * @generated
     */
    int TOOL_INSTANCE = 27;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE__ID = 0;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE__ENABLED = 1;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE__VISIBLE = 2;

    /**
     * The feature id for the '<em><b>Tool Entry</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE__TOOL_ENTRY = 3;

    /**
     * The feature id for the '<em><b>Filtered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE__FILTERED = 4;

    /**
     * The number of structural features of the '<em>Tool Instance</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_INSTANCE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl <em>Tool Group
     * Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolGroupInstance()
     * @generated
     */
    int TOOL_GROUP_INSTANCE = 28;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__ID = ViewpointPackage.TOOL_INSTANCE__ID;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__ENABLED = ViewpointPackage.TOOL_INSTANCE__ENABLED;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__VISIBLE = ViewpointPackage.TOOL_INSTANCE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tool Entry</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__TOOL_ENTRY = ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY;

    /**
     * The feature id for the '<em><b>Filtered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__FILTERED = ViewpointPackage.TOOL_INSTANCE__FILTERED;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__TOOLS = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Group</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE__GROUP = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Tool Group Instance</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_INSTANCE_FEATURE_COUNT = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl <em>Tool Section
     * Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolSectionInstance()
     * @generated
     */
    int TOOL_SECTION_INSTANCE = 29;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__ID = ViewpointPackage.TOOL_INSTANCE__ID;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__ENABLED = ViewpointPackage.TOOL_INSTANCE__ENABLED;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__VISIBLE = ViewpointPackage.TOOL_INSTANCE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tool Entry</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__TOOL_ENTRY = ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY;

    /**
     * The feature id for the '<em><b>Filtered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__FILTERED = ViewpointPackage.TOOL_INSTANCE__FILTERED;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__TOOLS = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Section</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__SECTION = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Sub Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE__SUB_SECTIONS = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Tool Section Instance</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_INSTANCE_FEATURE_COUNT = ViewpointPackage.TOOL_INSTANCE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFontFormat()
     * @generated
     */
    int FONT_FORMAT = 30;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.LabelAlignment <em>Label Alignment</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelAlignment()
     * @generated
     */
    int LABEL_ALIGNMENT = 31;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSyncStatus()
     * @generated
     */
    int SYNC_STATUS = 32;

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DAnalysis</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis
     * @generated
     */
    EClass getDAnalysis();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getReferencedAnalysis <em>Referenced Analysis</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list ' <em>Referenced Analysis</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getReferencedAnalysis()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_ReferencedAnalysis();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.viewpoint.DAnalysis#getModels
     * <em>Models</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getModels()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_Models();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.DAnalysis#getEAnnotations <em>EAnnotations</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>EAnnotations</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getEAnnotations()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_EAnnotations();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedViews <em>Owned Views</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Views</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getOwnedViews()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedViews();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.viewpoint.DAnalysis#getSelectedViews
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
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getOwnedFeatureExtensions <em>Owned Feature Extensions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Feature Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getOwnedFeatureExtensions()
     * @see #getDAnalysis()
     * @generated
     */
    EReference getDAnalysis_OwnedFeatureExtensions();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DAnalysis#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getVersion()
     * @see #getDAnalysis()
     * @generated
     */
    EAttribute getDAnalysis_Version();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis#getSemanticResources <em>Semantic Resources</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list ' <em>Semantic Resources</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysis#getSemanticResources()
     * @see #getDAnalysis()
     * @generated
     */
    EAttribute getDAnalysis_SemanticResources();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DFeatureExtension <em>DFeature
     * Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DFeature Extension</em>'.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension
     * @generated
     */
    EClass getDFeatureExtension();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription()
     * @see #getDFeatureExtension()
     * @generated
     */
    EReference getDFeatureExtension_Description();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DStylizable</em>'.
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @generated
     */
    EClass getDStylizable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DRefreshable</em>'.
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @generated
     */
    EClass getDRefreshable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DMappingBased <em>DMapping Based</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DMapping Based</em>'.
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @generated
     */
    EClass getDMappingBased();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DSemanticDecorator <em>DSemantic
     * Decorator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DSemantic Decorator</em>'.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator
     * @generated
     */
    EClass getDSemanticDecorator();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget()
     * @see #getDSemanticDecorator()
     * @generated
     */
    EReference getDSemanticDecorator_Target();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor
     * <em>DRepresentation Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DRepresentation Descriptor</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor
     * @generated
     */
    EClass getDRepresentationDescriptor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getName()
     * @see #getDRepresentationDescriptor()
     * @generated
     */
    EAttribute getDRepresentationDescriptor_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepresentation <em>Representation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Representation</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepresentation()
     * @see #getDRepresentationDescriptor()
     * @generated
     */
    EReference getDRepresentationDescriptor_Representation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepPath <em>Rep Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Rep Path</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepPath()
     * @see #getDRepresentationDescriptor()
     * @generated
     */
    EAttribute getDRepresentationDescriptor_RepPath();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getDescription <em>Description</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getDescription()
     * @see #getDRepresentationDescriptor()
     * @generated
     */
    EReference getDRepresentationDescriptor_Description();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getTarget <em>Target</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getTarget()
     * @see #getDRepresentationDescriptor()
     * @generated
     */
    EReference getDRepresentationDescriptor_Target();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DRepresentation
     * <em>DRepresentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DRepresentation</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation
     * @generated
     */
    EClass getDRepresentation();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedRepresentationElements <em>Owned Representation
     * Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Owned Representation Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getOwnedRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedRepresentationElements();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.viewpoint.DRepresentation#getRepresentationElements <em>Representation
     * Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Representation Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getRepresentationElements()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_RepresentationElements();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DRepresentation#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getName()
     * @see #getDRepresentation()
     * @generated
     */
    EAttribute getDRepresentation_Name();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.DRepresentation#getOwnedAnnotationEntries <em>Owned Annotation
     * Entries</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Annotation Entries</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getOwnedAnnotationEntries()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_OwnedAnnotationEntries();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.viewpoint.DRepresentation#getUiState <em>Ui State</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Ui State</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentation#getUiState()
     * @see #getDRepresentation()
     * @generated
     */
    EReference getDRepresentation_UiState();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DRepresentationElement <em>DRepresentation
     * Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DRepresentation Element</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement
     * @generated
     */
    EClass getDRepresentationElement();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DRepresentationElement#getName
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
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement#getSemanticElements <em>Semantic Elements</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list ' <em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement#getSemanticElements()
     * @see #getDRepresentationElement()
     * @generated
     */
    EReference getDRepresentationElement_SemanticElements();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DView <em>DView</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DView</em>'.
     * @see org.eclipse.sirius.viewpoint.DView
     * @generated
     */
    EClass getDView();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.viewpoint.DView#getOwnedExtensions <em>Owned Extensions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Owned Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getOwnedExtensions()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedExtensions();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.DView#getViewpoint
     * <em>Viewpoint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Viewpoint</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getViewpoint()
     * @see #getDView()
     * @generated
     */
    EReference getDView_Viewpoint();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.DView#getOwnedRepresentationDescriptors <em>Owned Representation
     * Descriptors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Representation Descriptors</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getOwnedRepresentationDescriptors()
     * @see #getDView()
     * @generated
     */
    EReference getDView_OwnedRepresentationDescriptors();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.viewpoint.DView#getModels
     * <em>Models</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see org.eclipse.sirius.viewpoint.DView#getModels()
     * @see #getDView()
     * @generated
     */
    EReference getDView_Models();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.MetaModelExtension <em>Meta Model
     * Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Meta Model Extension</em>'.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension
     * @generated
     */
    EClass getMetaModelExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup <em>Extension Group</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extension Group</em>'.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup()
     * @see #getMetaModelExtension()
     * @generated
     */
    EReference getMetaModelExtension_ExtensionGroup();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.Decoration <em>Decoration</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Decoration</em>'.
     * @see org.eclipse.sirius.viewpoint.Decoration
     * @generated
     */
    EClass getDecoration();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.Decoration#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.Decoration#getDescription()
     * @see #getDecoration()
     * @generated
     */
    EReference getDecoration_Description();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DAnalysisCustomData <em>DAnalysis Custom
     * Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DAnalysis Custom Data</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * @generated
     */
    EClass getDAnalysisCustomData();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DAnalysisCustomData#getKey
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData#getKey()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EAttribute getDAnalysisCustomData_Key();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.viewpoint.DAnalysisCustomData#getData <em>Data</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData#getData()
     * @see #getDAnalysisCustomData()
     * @generated
     */
    EReference getDAnalysisCustomData_Data();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.LabelStyle <em>Label Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelStyle
     * @generated
     */
    EClass getLabelStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.LabelStyle#getLabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelStyle#getLabelAlignment()
     * @see #getLabelStyle()
     * @generated
     */
    EAttribute getLabelStyle_LabelAlignment();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.Style <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Style</em>'.
     * @see org.eclipse.sirius.viewpoint.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.Style#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.Style#getDescription()
     * @see #getStyle()
     * @generated
     */
    EReference getStyle_Description();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.RGBValues <em>RGB Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>RGB Values</em>'.
     * @see org.eclipse.sirius.viewpoint.RGBValues
     * @generated
     */
    EDataType getRGBValues();

    /**
     * Returns the meta object for data type '{@link org.eclipse.sirius.business.api.resource.ResourceDescriptor
     * <em>Resource Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>Resource Descriptor</em>'.
     * @see org.eclipse.sirius.business.api.resource.ResourceDescriptor
     * @model instanceClass="org.eclipse.sirius.business.api.resource.ResourceDescriptor"
     * @generated
     */
    EDataType getResourceDescriptor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject <em>DAnalysis
     * Session EObject</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DAnalysis Session EObject</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * @generated
     */
    EClass getDAnalysisSessionEObject();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen
     * <em>Open</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Open</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#isOpen()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Open();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getResources <em>Resources</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Resources</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_Resources();

    /**
     * Returns the meta object for the attribute list
     * '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getControlledResources <em>Controlled
     * Resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Controlled Resources</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getControlledResources()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_ControlledResources();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getActivatedViewpoints <em>Activated
     * Viewpoints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activated Viewpoints</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getActivatedViewpoints()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_ActivatedViewpoints();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getAnalyses <em>Analyses</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Analyses</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getAnalyses()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EReference getDAnalysisSessionEObject_Analyses();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus <em>Synchronization
     * Status</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Synchronization Status</em>'.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject#getSynchronizationStatus()
     * @see #getDAnalysisSessionEObject()
     * @generated
     */
    EAttribute getDAnalysisSessionEObject_SynchronizationStatus();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.SessionManagerEObject <em>Session Manager
     * EObject</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Session Manager EObject</em>'.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject
     * @generated
     */
    EClass getSessionManagerEObject();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.SessionManagerEObject#getOwnedSessions <em>Owned Sessions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Sessions</em>'.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject#getOwnedSessions()
     * @see #getSessionManagerEObject()
     * @generated
     */
    EReference getSessionManagerEObject_OwnedSessions();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DResource</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource
     * @generated
     */
    EClass getDResource();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DResource#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource#getName()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Name();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.DResource#getPath <em>Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see org.eclipse.sirius.viewpoint.DResource#getPath()
     * @see #getDResource()
     * @generated
     */
    EAttribute getDResource_Path();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DFile <em>DFile</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DFile</em>'.
     * @see org.eclipse.sirius.viewpoint.DFile
     * @generated
     */
    EClass getDFile();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.DResourceContainer <em>DResource
     * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DResource Container</em>'.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer
     * @generated
     */
    EClass getDResourceContainer();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.DResourceContainer#getMembers <em>Members</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Members</em>'.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer#getMembers()
     * @see #getDResourceContainer()
     * @generated
     */
    EReference getDResourceContainer_Members();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DProject <em>DProject</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DProject</em>'.
     * @see org.eclipse.sirius.viewpoint.DProject
     * @generated
     */
    EClass getDProject();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DFolder <em>DFolder</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DFolder</em>'.
     * @see org.eclipse.sirius.viewpoint.DFolder
     * @generated
     */
    EClass getDFolder();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.DModel <em>DModel</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DModel</em>'.
     * @see org.eclipse.sirius.viewpoint.DModel
     * @generated
     */
    EClass getDModel();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle <em>Basic Label
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Basic Label Style</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle
     * @generated
     */
    EClass getBasicLabelStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelSize()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelSize();

    /**
     * Returns the meta object for the attribute list
     * '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelFormat <em>Label Format</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Label Format</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelFormat()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelFormat();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle#isShowIcon
     * <em>Show Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Show Icon</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#isShowIcon()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_ShowIcon();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelColor
     * <em>Label Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Color</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getLabelColor()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle#getIconPath()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_IconPath();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.Customizable <em>Customizable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Customizable</em>'.
     * @see org.eclipse.sirius.viewpoint.Customizable
     * @generated
     */
    EClass getCustomizable();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.Customizable#getCustomFeatures <em>Custom Features</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Custom Features</em>'.
     * @see org.eclipse.sirius.viewpoint.Customizable#getCustomFeatures()
     * @see #getCustomizable()
     * @generated
     */
    EAttribute getCustomizable_CustomFeatures();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.viewpoint.UIState <em>UI State</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>UI State</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState
     * @generated
     */
    EClass getUIState();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder
     * <em>Inverse Selection Order</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Inverse Selection Order</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder()
     * @see #getUIState()
     * @generated
     */
    EAttribute getUIState_InverseSelectionOrder();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect
     * <em>Elements To Select</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Elements To Select</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState#getElementsToSelect()
     * @see #getUIState()
     * @generated
     */
    EReference getUIState_ElementsToSelect();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.UIState#getDecorationImage
     * <em>Decoration Image</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Decoration Image</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState#getDecorationImage()
     * @see #getUIState()
     * @generated
     */
    EAttribute getUIState_DecorationImage();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.viewpoint.UIState#getToolSections
     * <em>Tool Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Tool Sections</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState#getToolSections()
     * @see #getUIState()
     * @generated
     */
    EReference getUIState_ToolSections();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.viewpoint.UIState#getSubDiagramDecorationDescriptors <em>Sub Diagram Decoration
     * Descriptors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Sub Diagram Decoration Descriptors</em>'.
     * @see org.eclipse.sirius.viewpoint.UIState#getSubDiagramDecorationDescriptors()
     * @see #getUIState()
     * @generated
     */
    EAttribute getUIState_SubDiagramDecorationDescriptors();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.ToolInstance <em>Tool Instance</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Tool Instance</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance
     * @generated
     */
    EClass getToolInstance();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.ToolInstance#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance#getId()
     * @see #getToolInstance()
     * @generated
     */
    EAttribute getToolInstance_Id();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.ToolInstance#isEnabled
     * <em>Enabled</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance#isEnabled()
     * @see #getToolInstance()
     * @generated
     */
    EAttribute getToolInstance_Enabled();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.ToolInstance#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance#isVisible()
     * @see #getToolInstance()
     * @generated
     */
    EAttribute getToolInstance_Visible();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.ToolInstance#getToolEntry <em>Tool
     * Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Tool Entry</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance#getToolEntry()
     * @see #getToolInstance()
     * @generated
     */
    EReference getToolInstance_ToolEntry();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.viewpoint.ToolInstance#isFiltered
     * <em>Filtered</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filtered</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolInstance#isFiltered()
     * @see #getToolInstance()
     * @generated
     */
    EAttribute getToolInstance_Filtered();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.ToolGroupInstance <em>Tool Group
     * Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Tool Group Instance</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolGroupInstance
     * @generated
     */
    EClass getToolGroupInstance();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.ToolGroupInstance#getTools <em>Tools</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Tools</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolGroupInstance#getTools()
     * @see #getToolGroupInstance()
     * @generated
     */
    EReference getToolGroupInstance_Tools();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.viewpoint.ToolGroupInstance#getGroup <em>Group</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Group</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolGroupInstance#getGroup()
     * @see #getToolGroupInstance()
     * @generated
     */
    EReference getToolGroupInstance_Group();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.viewpoint.ToolSectionInstance <em>Tool Section
     * Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Tool Section Instance</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolSectionInstance
     * @generated
     */
    EClass getToolSectionInstance();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getTools <em>Tools</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Tools</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolSectionInstance#getTools()
     * @see #getToolSectionInstance()
     * @generated
     */
    EReference getToolSectionInstance_Tools();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getSection
     * <em>Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Section</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolSectionInstance#getSection()
     * @see #getToolSectionInstance()
     * @generated
     */
    EReference getToolSectionInstance_Section();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getSubSections <em>Sub Sections</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sub Sections</em>'.
     * @see org.eclipse.sirius.viewpoint.ToolSectionInstance#getSubSections()
     * @see #getToolSectionInstance()
     * @generated
     */
    EReference getToolSectionInstance_SubSections();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Font Format</em>'.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @generated
     */
    EEnum getFontFormat();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.viewpoint.LabelAlignment <em>Label Alignment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @generated
     */
    EEnum getLabelAlignment();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Sync Status</em>'.
     * @see org.eclipse.sirius.viewpoint.SyncStatus
     * @generated
     */
    EEnum getSyncStatus();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ViewpointFactory getViewpointFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.IdentifiedElementImpl
         * <em>Identified Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.IdentifiedElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getIdentifiedElement()
         * @generated
         */
        EClass IDENTIFIED_ELEMENT = ViewpointPackage.eINSTANCE.getIdentifiedElement();

        /**
         * The meta object literal for the '<em><b>Uid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute IDENTIFIED_ELEMENT__UID = ViewpointPackage.eINSTANCE.getIdentifiedElement_Uid();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl <em>DAnalysis</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysis()
         * @generated
         */
        EClass DANALYSIS = ViewpointPackage.eINSTANCE.getDAnalysis();

        /**
         * The meta object literal for the '<em><b>Referenced Analysis</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__REFERENCED_ANALYSIS = ViewpointPackage.eINSTANCE.getDAnalysis_ReferencedAnalysis();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__MODELS = ViewpointPackage.eINSTANCE.getDAnalysis_Models();

        /**
         * The meta object literal for the '<em><b>EAnnotations</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__EANNOTATIONS = ViewpointPackage.eINSTANCE.getDAnalysis_EAnnotations();

        /**
         * The meta object literal for the '<em><b>Owned Views</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__OWNED_VIEWS = ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews();

        /**
         * The meta object literal for the '<em><b>Selected Views</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__SELECTED_VIEWS = ViewpointPackage.eINSTANCE.getDAnalysis_SelectedViews();

        /**
         * The meta object literal for the '<em><b>Owned Feature Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS__OWNED_FEATURE_EXTENSIONS = ViewpointPackage.eINSTANCE.getDAnalysis_OwnedFeatureExtensions();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS__VERSION = ViewpointPackage.eINSTANCE.getDAnalysis_Version();

        /**
         * The meta object literal for the '<em><b>Semantic Resources</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS__SEMANTIC_RESOURCES = ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl <em>DFeature
         * Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFeatureExtension()
         * @generated
         */
        EClass DFEATURE_EXTENSION = ViewpointPackage.eINSTANCE.getDFeatureExtension();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DFEATURE_EXTENSION__DESCRIPTION = ViewpointPackage.eINSTANCE.getDFeatureExtension_Description();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.DStylizable
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDStylizable()
         * @generated
         */
        EClass DSTYLIZABLE = ViewpointPackage.eINSTANCE.getDStylizable();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.DRefreshable
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRefreshable()
         * @generated
         */
        EClass DREFRESHABLE = ViewpointPackage.eINSTANCE.getDRefreshable();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.DMappingBased <em>DMapping Based</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.DMappingBased
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDMappingBased()
         * @generated
         */
        EClass DMAPPING_BASED = ViewpointPackage.eINSTANCE.getDMappingBased();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
         * <em>DSemantic Decorator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDSemanticDecorator()
         * @generated
         */
        EClass DSEMANTIC_DECORATOR = ViewpointPackage.eINSTANCE.getDSemanticDecorator();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DSEMANTIC_DECORATOR__TARGET = ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl
         * <em>DRepresentation Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationDescriptor()
         * @generated
         */
        EClass DREPRESENTATION_DESCRIPTOR = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DREPRESENTATION_DESCRIPTOR__NAME = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Name();

        /**
         * The meta object literal for the '<em><b>Representation</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION_DESCRIPTOR__REPRESENTATION = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation();

        /**
         * The meta object literal for the '<em><b>Rep Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DREPRESENTATION_DESCRIPTOR__REP_PATH = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_RepPath();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION_DESCRIPTOR__DESCRIPTION = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Description();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION_DESCRIPTOR__TARGET = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Target();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
         * <em>DRepresentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentation()
         * @generated
         */
        EClass DREPRESENTATION = ViewpointPackage.eINSTANCE.getDRepresentation();

        /**
         * The meta object literal for the '<em><b>Owned Representation Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements();

        /**
         * The meta object literal for the ' <em><b>Representation Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION__REPRESENTATION_ELEMENTS = ViewpointPackage.eINSTANCE.getDRepresentation_RepresentationElements();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DREPRESENTATION__NAME = ViewpointPackage.eINSTANCE.getDRepresentation_Name();

        /**
         * The meta object literal for the '<em><b>Owned Annotation Entries</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.eINSTANCE.getDRepresentation_OwnedAnnotationEntries();

        /**
         * The meta object literal for the '<em><b>Ui State</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION__UI_STATE = ViewpointPackage.eINSTANCE.getDRepresentation_UiState();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
         * <em>DRepresentation Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDRepresentationElement()
         * @generated
         */
        EClass DREPRESENTATION_ELEMENT = ViewpointPackage.eINSTANCE.getDRepresentationElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DREPRESENTATION_ELEMENT__NAME = ViewpointPackage.eINSTANCE.getDRepresentationElement_Name();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DViewImpl <em>DView</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DViewImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDView()
         * @generated
         */
        EClass DVIEW = ViewpointPackage.eINSTANCE.getDView();

        /**
         * The meta object literal for the '<em><b>Owned Extensions</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DVIEW__OWNED_EXTENSIONS = ViewpointPackage.eINSTANCE.getDView_OwnedExtensions();

        /**
         * The meta object literal for the '<em><b>Viewpoint</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DVIEW__VIEWPOINT = ViewpointPackage.eINSTANCE.getDView_Viewpoint();

        /**
         * The meta object literal for the '<em><b>Owned Representation Descriptors</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DVIEW__OWNED_REPRESENTATION_DESCRIPTORS = ViewpointPackage.eINSTANCE.getDView_OwnedRepresentationDescriptors();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DVIEW__MODELS = ViewpointPackage.eINSTANCE.getDView_Models();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl <em>Meta
         * Model Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getMetaModelExtension()
         * @generated
         */
        EClass META_MODEL_EXTENSION = ViewpointPackage.eINSTANCE.getMetaModelExtension();

        /**
         * The meta object literal for the '<em><b>Extension Group</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference META_MODEL_EXTENSION__EXTENSION_GROUP = ViewpointPackage.eINSTANCE.getMetaModelExtension_ExtensionGroup();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DecorationImpl
         * <em>Decoration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DecorationImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDecoration()
         * @generated
         */
        EClass DECORATION = ViewpointPackage.eINSTANCE.getDecoration();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DECORATION__DESCRIPTION = ViewpointPackage.eINSTANCE.getDecoration_Description();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
         * <em>DAnalysis Custom Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisCustomData()
         * @generated
         */
        EClass DANALYSIS_CUSTOM_DATA = ViewpointPackage.eINSTANCE.getDAnalysisCustomData();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS_CUSTOM_DATA__KEY = ViewpointPackage.eINSTANCE.getDAnalysisCustomData_Key();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS_CUSTOM_DATA__DATA = ViewpointPackage.eINSTANCE.getDAnalysisCustomData_Data();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.LabelStyleImpl <em>Label
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.LabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelStyle()
         * @generated
         */
        EClass LABEL_STYLE = ViewpointPackage.eINSTANCE.getLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_STYLE__LABEL_ALIGNMENT = ViewpointPackage.eINSTANCE.getLabelStyle_LabelAlignment();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.StyleImpl <em>Style</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.StyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getStyle()
         * @generated
         */
        EClass STYLE = ViewpointPackage.eINSTANCE.getStyle();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference STYLE__DESCRIPTION = ViewpointPackage.eINSTANCE.getStyle_Description();

        /**
         * The meta object literal for the '<em>RGB Values</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.RGBValues
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getRGBValues()
         * @generated
         */
        EDataType RGB_VALUES = ViewpointPackage.eINSTANCE.getRGBValues();

        /**
         * The meta object literal for the '<em>Resource Descriptor</em>' data type. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.business.api.resource.ResourceDescriptor
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getResourceDescriptor()
         * @generated
         */
        EDataType RESOURCE_DESCRIPTOR = ViewpointPackage.eINSTANCE.getResourceDescriptor();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
         * <em>DAnalysis Session EObject</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDAnalysisSessionEObject()
         * @generated
         */
        EClass DANALYSIS_SESSION_EOBJECT = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject();

        /**
         * The meta object literal for the '<em><b>Open</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__OPEN = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_Open();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' attribute list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__RESOURCES = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_Resources();

        /**
         * The meta object literal for the '<em><b>Controlled Resources</b></em> ' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_ControlledResources();

        /**
         * The meta object literal for the '<em><b>Activated Viewpoints</b></em> ' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_ActivatedViewpoints();

        /**
         * The meta object literal for the '<em><b>Analyses</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DANALYSIS_SESSION_EOBJECT__ANALYSES = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_Analyses();

        /**
         * The meta object literal for the ' <em><b>Synchronization Status</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS = ViewpointPackage.eINSTANCE.getDAnalysisSessionEObject_SynchronizationStatus();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
         * <em>Session Manager EObject</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSessionManagerEObject()
         * @generated
         */
        EClass SESSION_MANAGER_EOBJECT = ViewpointPackage.eINSTANCE.getSessionManagerEObject();

        /**
         * The meta object literal for the '<em><b>Owned Sessions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SESSION_MANAGER_EOBJECT__OWNED_SESSIONS = ViewpointPackage.eINSTANCE.getSessionManagerEObject_OwnedSessions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.DResource
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResource()
         * @generated
         */
        EClass DRESOURCE = ViewpointPackage.eINSTANCE.getDResource();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DRESOURCE__NAME = ViewpointPackage.eINSTANCE.getDResource_Name();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DRESOURCE__PATH = ViewpointPackage.eINSTANCE.getDResource_Path();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DFileImpl <em>DFile</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DFileImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFile()
         * @generated
         */
        EClass DFILE = ViewpointPackage.eINSTANCE.getDFile();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
         * <em>DResource Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDResourceContainer()
         * @generated
         */
        EClass DRESOURCE_CONTAINER = ViewpointPackage.eINSTANCE.getDResourceContainer();

        /**
         * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DRESOURCE_CONTAINER__MEMBERS = ViewpointPackage.eINSTANCE.getDResourceContainer_Members();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DProjectImpl <em>DProject</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DProjectImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDProject()
         * @generated
         */
        EClass DPROJECT = ViewpointPackage.eINSTANCE.getDProject();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DFolderImpl <em>DFolder</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DFolderImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDFolder()
         * @generated
         */
        EClass DFOLDER = ViewpointPackage.eINSTANCE.getDFolder();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.DModelImpl <em>DModel</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.DModelImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getDModel()
         * @generated
         */
        EClass DMODEL = ViewpointPackage.eINSTANCE.getDModel();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl <em>Basic Label
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getBasicLabelStyle()
         * @generated
         */
        EClass BASIC_LABEL_STYLE = ViewpointPackage.eINSTANCE.getBasicLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.eINSTANCE.getBasicLabelStyle_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>' attribute list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.eINSTANCE.getBasicLabelStyle_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Show Icon</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__SHOW_ICON = ViewpointPackage.eINSTANCE.getBasicLabelStyle_ShowIcon();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.eINSTANCE.getBasicLabelStyle_LabelColor();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__ICON_PATH = ViewpointPackage.eINSTANCE.getBasicLabelStyle_IconPath();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.CustomizableImpl
         * <em>Customizable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.CustomizableImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getCustomizable()
         * @generated
         */
        EClass CUSTOMIZABLE = ViewpointPackage.eINSTANCE.getCustomizable();

        /**
         * The meta object literal for the '<em><b>Custom Features</b></em>' attribute list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOMIZABLE__CUSTOM_FEATURES = ViewpointPackage.eINSTANCE.getCustomizable_CustomFeatures();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl <em>UI State</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.UIStateImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getUIState()
         * @generated
         */
        EClass UI_STATE = ViewpointPackage.eINSTANCE.getUIState();

        /**
         * The meta object literal for the ' <em><b>Inverse Selection Order</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute UI_STATE__INVERSE_SELECTION_ORDER = ViewpointPackage.eINSTANCE.getUIState_InverseSelectionOrder();

        /**
         * The meta object literal for the '<em><b>Elements To Select</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference UI_STATE__ELEMENTS_TO_SELECT = ViewpointPackage.eINSTANCE.getUIState_ElementsToSelect();

        /**
         * The meta object literal for the '<em><b>Decoration Image</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute UI_STATE__DECORATION_IMAGE = ViewpointPackage.eINSTANCE.getUIState_DecorationImage();

        /**
         * The meta object literal for the '<em><b>Tool Sections</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference UI_STATE__TOOL_SECTIONS = ViewpointPackage.eINSTANCE.getUIState_ToolSections();

        /**
         * The meta object literal for the '<em><b>Sub Diagram Decoration Descriptors</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS = ViewpointPackage.eINSTANCE.getUIState_SubDiagramDecorationDescriptors();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl <em>Tool
         * Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolInstance()
         * @generated
         */
        EClass TOOL_INSTANCE = ViewpointPackage.eINSTANCE.getToolInstance();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL_INSTANCE__ID = ViewpointPackage.eINSTANCE.getToolInstance_Id();

        /**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL_INSTANCE__ENABLED = ViewpointPackage.eINSTANCE.getToolInstance_Enabled();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL_INSTANCE__VISIBLE = ViewpointPackage.eINSTANCE.getToolInstance_Visible();

        /**
         * The meta object literal for the '<em><b>Tool Entry</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_INSTANCE__TOOL_ENTRY = ViewpointPackage.eINSTANCE.getToolInstance_ToolEntry();

        /**
         * The meta object literal for the '<em><b>Filtered</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL_INSTANCE__FILTERED = ViewpointPackage.eINSTANCE.getToolInstance_Filtered();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl <em>Tool
         * Group Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolGroupInstance()
         * @generated
         */
        EClass TOOL_GROUP_INSTANCE = ViewpointPackage.eINSTANCE.getToolGroupInstance();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_GROUP_INSTANCE__TOOLS = ViewpointPackage.eINSTANCE.getToolGroupInstance_Tools();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_GROUP_INSTANCE__GROUP = ViewpointPackage.eINSTANCE.getToolGroupInstance_Group();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl <em>Tool
         * Section Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getToolSectionInstance()
         * @generated
         */
        EClass TOOL_SECTION_INSTANCE = ViewpointPackage.eINSTANCE.getToolSectionInstance();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION_INSTANCE__TOOLS = ViewpointPackage.eINSTANCE.getToolSectionInstance_Tools();

        /**
         * The meta object literal for the '<em><b>Section</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION_INSTANCE__SECTION = ViewpointPackage.eINSTANCE.getToolSectionInstance_Section();

        /**
         * The meta object literal for the '<em><b>Sub Sections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION_INSTANCE__SUB_SECTIONS = ViewpointPackage.eINSTANCE.getToolSectionInstance_SubSections();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.FontFormat <em>Font Format</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.FontFormat
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getFontFormat()
         * @generated
         */
        EEnum FONT_FORMAT = ViewpointPackage.eINSTANCE.getFontFormat();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.LabelAlignment <em>Label
         * Alignment</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.LabelAlignment
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getLabelAlignment()
         * @generated
         */
        EEnum LABEL_ALIGNMENT = ViewpointPackage.eINSTANCE.getLabelAlignment();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.viewpoint.SyncStatus <em>Sync Status</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.SyncStatus
         * @see org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl#getSyncStatus()
         * @generated
         */
        EEnum SYNC_STATUS = ViewpointPackage.eINSTANCE.getSyncStatus();

    }

} // ViewpointPackage
