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
package org.eclipse.sirius.description;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.sirius.description.DescriptionFactory
 * @model kind="package"
 * @generated
 */
public interface DescriptionPackage extends EPackage {
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
    String eNAME = "description";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "description";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DescriptionPackage eINSTANCE = org.eclipse.sirius.description.impl.DescriptionPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DModelElementImpl
     * <em>DModel Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DModelElementImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDModelElement()
     * @generated
     */
    int DMODEL_ELEMENT = 25;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMODEL_ELEMENT__EANNOTATIONS = 0;

    /**
     * The number of structural features of the '<em>DModel Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DMODEL_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.Component <em>Component</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.Component
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.RepresentationDescription
     * <em>Representation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.RepresentationDescription
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationDescription()
     * @generated
     */
    int REPRESENTATION_DESCRIPTION = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.RepresentationImportDescription
     * <em>Representation Import Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.RepresentationImportDescription
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationImportDescription()
     * @generated
     */
    int REPRESENTATION_IMPORT_DESCRIPTION = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription
     * <em>Representation Extension Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationExtensionDescription()
     * @generated
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION = 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DocumentedElementImpl
     * <em>Documented Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DocumentedElementImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDocumentedElement()
     * @generated
     */
    int DOCUMENTED_ELEMENT = 24;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DiagramDescriptionImpl
     * <em>Diagram Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramDescription()
     * @generated
     */
    int DIAGRAM_DESCRIPTION = 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DiagramImportDescriptionImpl
     * <em>Diagram Import Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DiagramImportDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramImportDescription()
     * @generated
     */
    int DIAGRAM_IMPORT_DESCRIPTION = 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DiagramExtensionDescriptionImpl
     * <em>Diagram Extension Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DiagramExtensionDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramExtensionDescription()
     * @generated
     */
    int DIAGRAM_EXTENSION_DESCRIPTION = 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.SiriusImpl
     * <em>Sirius</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.SiriusImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSirius()
     * @generated
     */
    int VIEWPOINT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.MetamodelExtensionSettingImpl
     * <em>Metamodel Extension Setting</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.MetamodelExtensionSettingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getMetamodelExtensionSetting()
     * @generated
     */
    int METAMODEL_EXTENSION_SETTING = 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.JavaExtensionImpl
     * <em>Java Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.JavaExtensionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getJavaExtension()
     * @generated
     */
    int JAVA_EXTENSION = 12;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DiagramElementMappingImpl
     * <em>Diagram Element Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DiagramElementMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramElementMapping()
     * @generated
     */
    int DIAGRAM_ELEMENT_MAPPING = 14;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.AbstractNodeMappingImpl
     * <em>Abstract Node Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.AbstractNodeMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAbstractNodeMapping()
     * @generated
     */
    int ABSTRACT_NODE_MAPPING = 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.NodeMappingImpl
     * <em>Node Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.NodeMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNodeMapping()
     * @generated
     */
    int NODE_MAPPING = 16;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.NodeMappingImportImpl
     * <em>Node Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.NodeMappingImportImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNodeMappingImport()
     * @generated
     */
    int NODE_MAPPING_IMPORT = 19;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ContainerMappingImportImpl
     * <em>Container Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ContainerMappingImportImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getContainerMappingImport()
     * @generated
     */
    int CONTAINER_MAPPING_IMPORT = 20;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ContainerMappingImpl
     * <em>Container Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ContainerMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getContainerMapping()
     * @generated
     */
    int CONTAINER_MAPPING = 17;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.AbstractMappingImportImpl
     * <em>Abstract Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.AbstractMappingImportImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAbstractMappingImport()
     * @generated
     */
    int ABSTRACT_MAPPING_IMPORT = 18;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EdgeMappingImpl
     * <em>Edge Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.EdgeMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEdgeMapping()
     * @generated
     */
    int EDGE_MAPPING = 21;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.GroupImpl <em>Group</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.GroupImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getGroup()
     * @generated
     */
    int GROUP = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__EANNOTATIONS = DMODEL_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__DOCUMENTATION = DMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__NAME = DMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Siriuss</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__OWNED_VIEWPOINTS = DMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>System Colors Palette</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__SYSTEM_COLORS_PALETTE = DMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>User Colors Palettes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__USER_COLORS_PALETTES = DMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__VERSION = DMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Group</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_FEATURE_COUNT = DMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Component</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENTED_ELEMENT__DOCUMENTATION = 0;

    /**
     * The number of structural features of the '<em>Documented Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENTED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__DOCUMENTATION = DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__END_USER_DOCUMENTATION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__NAME = DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__LABEL = DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Model File Extension</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__MODEL_FILE_EXTENSION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__VALIDATION_SET = DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_REPRESENTATIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Owned Representation Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Owned Java Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_JAVA_EXTENSIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Owned MM Extensions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_MM_EXTENSIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Owned Feature Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_FEATURE_EXTENSIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__ICON = DOCUMENTED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Owned Templates</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_TEMPLATES = DOCUMENTED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Conflicts</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__CONFLICTS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Reuses</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__REUSES = DOCUMENTED_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Customizes</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__CUSTOMIZES = DOCUMENTED_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>Sirius</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.FeatureExtensionDescription
     * <em>Feature Extension Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.FeatureExtensionDescription
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFeatureExtensionDescription()
     * @generated
     */
    int FEATURE_EXTENSION_DESCRIPTION = 3;

    /**
     * The number of structural features of the '
     * <em>Feature Extension Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_EXTENSION_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__DOCUMENTATION = DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__NAME = DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__LABEL = DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__INITIALISATION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__METAMODEL = DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP = DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '
     * <em>Representation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.RepresentationTemplateImpl
     * <em>Representation Template</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.RepresentationTemplateImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationTemplate()
     * @generated
     */
    int REPRESENTATION_TEMPLATE = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_TEMPLATE__NAME = 0;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS = 1;

    /**
     * The number of structural features of the '
     * <em>Representation Template</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_TEMPLATE_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__DOCUMENTATION = REPRESENTATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION = REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__NAME = REPRESENTATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__LABEL = REPRESENTATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__TITLE_EXPRESSION = REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__INITIALISATION = REPRESENTATION_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__METAMODEL = REPRESENTATION_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__SHOW_ON_STARTUP = REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The number of structural features of the '
     * <em>Representation Import Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Sirius URI</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI = 1;

    /**
     * The feature id for the '<em><b>Representation Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME = 2;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL = 3;

    /**
     * The number of structural features of the '
     * <em>Representation Extension Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.RepresentationElementMappingImpl
     * <em>Representation Element Mapping</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.RepresentationElementMappingImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationElementMapping()
     * @generated
     */
    int REPRESENTATION_ELEMENT_MAPPING = 13;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.IEdgeMapping
     * <em>IEdge Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.IEdgeMapping
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIEdgeMapping()
     * @generated
     */
    int IEDGE_MAPPING = 22;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EdgeMappingImportImpl
     * <em>Edge Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.EdgeMappingImportImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEdgeMappingImport()
     * @generated
     */
    int EDGE_MAPPING_IMPORT = 23;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DAnnotationImpl
     * <em>DAnnotation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.DAnnotationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDAnnotation()
     * @generated
     */
    int DANNOTATION = 26;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ConditionalStyleDescriptionImpl
     * <em>Conditional Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ConditionalStyleDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalStyleDescription()
     * @generated
     */
    int CONDITIONAL_STYLE_DESCRIPTION = 27;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ConditionalNodeStyleDescriptionImpl
     * <em>Conditional Node Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ConditionalNodeStyleDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalNodeStyleDescription()
     * @generated
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION = 28;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ConditionalEdgeStyleDescriptionImpl
     * <em>Conditional Edge Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ConditionalEdgeStyleDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalEdgeStyleDescription()
     * @generated
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION = 29;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ConditionalContainerStyleDescriptionImpl
     * <em>Conditional Container Style Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ConditionalContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalContainerStyleDescription()
     * @generated
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION = 30;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DragAndDropTargetDescriptionImpl
     * <em>Drag And Drop Target Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DragAndDropTargetDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDragAndDropTargetDescription()
     * @generated
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION = 31;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Drag And Drop Target Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DROP_DESCRIPTIONS = DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DOCUMENTATION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NAME = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__LABEL = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__TITLE_EXPRESSION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__INITIALISATION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__METAMODEL = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__FILTERS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>All Activated Edge Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_ACTIVATED_EDGE_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__VALIDATION_SET = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__CONCERNS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Information Sections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__INFORMATION_SECTIONS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_TOOLS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DOMAIN_CLASS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 19;

    /**
     * The feature id for the '<em><b>Default Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DEFAULT_CONCERN = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 20;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ROOT_EXPRESSION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 21;

    /**
     * The feature id for the '<em><b>Init</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__INIT = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 22;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__LAYOUT = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 23;

    /**
     * The feature id for the '<em><b>Diagram Initialisation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 24;

    /**
     * The feature id for the '<em><b>Default Layer</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DEFAULT_LAYER = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 25;

    /**
     * The feature id for the '<em><b>Additional Layers</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 26;

    /**
     * The feature id for the '<em><b>All Layers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_LAYERS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 27;

    /**
     * The feature id for the '<em><b>All Activated Tools</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 28;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NODE_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 29;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__EDGE_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 30;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 31;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 32;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__REUSED_MAPPINGS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 33;

    /**
     * The feature id for the '<em><b>Tool Section</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__TOOL_SECTION = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 34;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__REUSED_TOOLS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 35;

    /**
     * The feature id for the '<em><b>Enable Popup Bars</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 36;

    /**
     * The number of structural features of the '<em>Diagram Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION_FEATURE_COUNT = DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 37;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DOCUMENTATION = REPRESENTATION_IMPORT_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION = REPRESENTATION_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__NAME = REPRESENTATION_IMPORT_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__LABEL = REPRESENTATION_IMPORT_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__TITLE_EXPRESSION = REPRESENTATION_IMPORT_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__INITIALISATION = REPRESENTATION_IMPORT_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__METAMODEL = REPRESENTATION_IMPORT_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__SHOW_ON_STARTUP = REPRESENTATION_IMPORT_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DROP_DESCRIPTIONS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__PASTE_DESCRIPTIONS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__FILTERS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_EDGE_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>All Activated Edge Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_ACTIVATED_EDGE_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_NODE_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_CONTAINER_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__VALIDATION_SET = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__CONCERNS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Information Sections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__INFORMATION_SECTIONS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_TOOLS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DOMAIN_CLASS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__PRECONDITION_EXPRESSION = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Default Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DEFAULT_CONCERN = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ROOT_EXPRESSION = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Init</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__INIT = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__LAYOUT = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Diagram Initialisation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DIAGRAM_INITIALISATION = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Default Layer</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DEFAULT_LAYER = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Additional Layers</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ADDITIONAL_LAYERS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 19;

    /**
     * The feature id for the '<em><b>All Layers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_LAYERS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 20;

    /**
     * The feature id for the '<em><b>All Activated Tools</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_ACTIVATED_TOOLS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 21;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__NODE_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 22;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 23;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPING_IMPORTS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 24;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__CONTAINER_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 25;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__REUSED_MAPPINGS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 26;

    /**
     * The feature id for the '<em><b>Tool Section</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__TOOL_SECTION = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 27;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__REUSED_TOOLS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 28;

    /**
     * The feature id for the '<em><b>Enable Popup Bars</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ENABLE_POPUP_BARS = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 29;

    /**
     * The feature id for the '<em><b>Imported Diagram</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 30;

    /**
     * The number of structural features of the '
     * <em>Diagram Import Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 31;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__NAME = REPRESENTATION_EXTENSION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Sirius URI</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI = REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI;

    /**
     * The feature id for the '<em><b>Representation Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME = REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL = REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Layers</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__LAYERS = REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET = REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS = REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Diagram Extension Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extension Group</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METAMODEL_EXTENSION_SETTING__EXTENSION_GROUP = 0;

    /**
     * The number of structural features of the '
     * <em>Metamodel Extension Setting</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int METAMODEL_EXTENSION_SETTING_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Qualified Class Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int JAVA_EXTENSION__QUALIFIED_CLASS_NAME = 0;

    /**
     * The number of structural features of the '<em>Java Extension</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int JAVA_EXTENSION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.IdentifiedElementImpl
     * <em>Identified Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.IdentifiedElementImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIdentifiedElement()
     * @generated
     */
    int IDENTIFIED_ELEMENT = 62;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IDENTIFIED_ELEMENT__NAME = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IDENTIFIED_ELEMENT__LABEL = 1;

    /**
     * The number of structural features of the '<em>Identified Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IDENTIFIED_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__NAME = IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__LABEL = IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Representation Element Mapping</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__NAME = REPRESENTATION_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__LABEL = REPRESENTATION_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '
     * <em>Diagram Element Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT = REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__NAME = DIAGRAM_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__LABEL = DIAGRAM_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION = DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION = DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT = DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS = DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS = DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK = DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOCUMENTATION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOMAIN_CLASS = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Abstract Node Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING_FEATURE_COUNT = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__NAME = ABSTRACT_NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__LABEL = ABSTRACT_NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DETAIL_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__NAVIGATION_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__PASTE_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__PRECONDITION_EXPRESSION = ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DELETION_DESCRIPTION = ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__LABEL_DIRECT_EDIT = ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__CREATE_ELEMENTS = ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SEMANTIC_ELEMENTS = ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION = ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SYNCHRONIZATION_LOCK = ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOCUMENTATION = ABSTRACT_NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOMAIN_CLASS = ABSTRACT_NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__BORDERED_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DROP_DESCRIPTIONS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__STYLE = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING__CONDITIONNAL_STYLES = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Node Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_FEATURE_COUNT = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__NAME = ABSTRACT_NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__LABEL = ABSTRACT_NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DETAIL_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__PASTE_DESCRIPTIONS = ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__PRECONDITION_EXPRESSION = ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DELETION_DESCRIPTION = ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__LABEL_DIRECT_EDIT = ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CREATE_ELEMENTS = ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SEMANTIC_ELEMENTS = ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION = ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SYNCHRONIZATION_LOCK = ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOCUMENTATION = ABSTRACT_NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOMAIN_CLASS = ABSTRACT_NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DROP_DESCRIPTIONS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SUB_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__ALL_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_NODE_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__STYLE = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CONDITIONNAL_STYLES = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CHILDREN_PRESENTATION = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Container Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_FEATURE_COUNT = ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Hide Sub Mappings</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = 0;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = 1;

    /**
     * The number of structural features of the '
     * <em>Abstract Mapping Import</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_MAPPING_IMPORT_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__NAME = NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__LABEL = NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DETAIL_DESCRIPTIONS = NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__NAVIGATION_DESCRIPTIONS = NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__PASTE_DESCRIPTIONS = NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__PRECONDITION_EXPRESSION = NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DELETION_DESCRIPTION = NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__LABEL_DIRECT_EDIT = NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SEMANTIC_CANDIDATES_EXPRESSION = NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__CREATE_ELEMENTS = NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SEMANTIC_ELEMENTS = NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOUBLE_CLICK_DESCRIPTION = NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SYNCHRONIZATION_LOCK = NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOCUMENTATION = NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOMAIN_CLASS = NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__BORDERED_NODE_MAPPINGS = NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__REUSED_BORDERED_NODE_MAPPINGS = NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DROP_DESCRIPTIONS = NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__STYLE = NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__CONDITIONNAL_STYLES = NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Hide Sub Mappings</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__IMPORTED_MAPPING = NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Node Mapping Import</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT_FEATURE_COUNT = NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__NAME = CONTAINER_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__LABEL = CONTAINER_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DETAIL_DESCRIPTIONS = CONTAINER_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__NAVIGATION_DESCRIPTIONS = CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__PASTE_DESCRIPTIONS = CONTAINER_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__PRECONDITION_EXPRESSION = CONTAINER_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DELETION_DESCRIPTION = CONTAINER_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__LABEL_DIRECT_EDIT = CONTAINER_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SEMANTIC_CANDIDATES_EXPRESSION = CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CREATE_ELEMENTS = CONTAINER_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SEMANTIC_ELEMENTS = CONTAINER_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOUBLE_CLICK_DESCRIPTION = CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SYNCHRONIZATION_LOCK = CONTAINER_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOCUMENTATION = CONTAINER_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOMAIN_CLASS = CONTAINER_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__BORDERED_NODE_MAPPINGS = CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_BORDERED_NODE_MAPPINGS = CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DROP_DESCRIPTIONS = CONTAINER_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SUB_NODE_MAPPINGS = CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__ALL_NODE_MAPPINGS = CONTAINER_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_NODE_MAPPINGS = CONTAINER_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SUB_CONTAINER_MAPPINGS = CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_CONTAINER_MAPPINGS = CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__ALL_CONTAINER_MAPPINGS = CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__STYLE = CONTAINER_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CONDITIONNAL_STYLES = CONTAINER_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CHILDREN_PRESENTATION = CONTAINER_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Hide Sub Mappings</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = CONTAINER_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = CONTAINER_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING = CONTAINER_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Container Mapping Import</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT_FEATURE_COUNT = CONTAINER_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__NAME = DIAGRAM_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__LABEL = DIAGRAM_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DETAIL_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__NAVIGATION_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PASTE_DESCRIPTIONS = DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PRECONDITION_EXPRESSION = DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DELETION_DESCRIPTION = DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__LABEL_DIRECT_EDIT = DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__CREATE_ELEMENTS = DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SEMANTIC_ELEMENTS = DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SYNCHRONIZATION_LOCK = DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOCUMENTATION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SOURCE_MAPPING = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_MAPPING = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_FINDER_EXPRESSION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SOURCE_FINDER_EXPRESSION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__STYLE = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__CONDITIONNAL_STYLES = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_EXPRESSION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOMAIN_CLASS = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__USE_DOMAIN_ELEMENT = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__RECONNECTIONS = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PATH_EXPRESSION = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PATH_NODE_MAPPING = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '<em>Edge Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_FEATURE_COUNT = DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 13;

    /**
     * The number of structural features of the '<em>IEdge Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IEDGE_MAPPING_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__DOCUMENTATION = DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__NAME = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__LABEL = DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__IMPORTED_MAPPING = DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES = DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Edge Mapping Import</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Details</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION__DETAILS = 1;

    /**
     * The number of structural features of the '<em>DAnnotation</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = 0;

    /**
     * The number of structural features of the '
     * <em>Conditional Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Conditional Node Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION_FEATURE_COUNT = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Conditional Edge Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION_FEATURE_COUNT = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Conditional Container Style Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.PasteTargetDescriptionImpl
     * <em>Paste Target Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.PasteTargetDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getPasteTargetDescription()
     * @generated
     */
    int PASTE_TARGET_DESCRIPTION = 32;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Paste Target Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_TARGET_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.Layout <em>Layout</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.Layout
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayout()
     * @generated
     */
    int LAYOUT = 33;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYOUT__DOCUMENTATION = DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Layout</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYOUT_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.OrderedTreeLayoutImpl
     * <em>Ordered Tree Layout</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.OrderedTreeLayoutImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getOrderedTreeLayout()
     * @generated
     */
    int ORDERED_TREE_LAYOUT = 34;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__DOCUMENTATION = LAYOUT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION = LAYOUT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Node Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__NODE_MAPPING = LAYOUT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Ordered Tree Layout</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT_FEATURE_COUNT = LAYOUT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.CompositeLayoutImpl
     * <em>Composite Layout</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.CompositeLayoutImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getCompositeLayout()
     * @generated
     */
    int COMPOSITE_LAYOUT = 35;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__DOCUMENTATION = LAYOUT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Padding</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__PADDING = LAYOUT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__DIRECTION = LAYOUT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Composite Layout</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT_FEATURE_COUNT = LAYOUT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DecorationDescriptionsSetImpl
     * <em>Decoration Descriptions Set</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DecorationDescriptionsSetImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDecorationDescriptionsSet()
     * @generated
     */
    int DECORATION_DESCRIPTIONS_SET = 36;

    /**
     * The feature id for the '<em><b>Decoration Descriptions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Decoration Descriptions Set</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTIONS_SET_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DecorationDescriptionImpl
     * <em>Decoration Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DecorationDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDecorationDescription()
     * @generated
     */
    int DECORATION_DESCRIPTION = 37;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTION__POSITION = 1;

    /**
     * The feature id for the '<em><b>Decorator Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTION__DECORATOR_PATH = 2;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION = 3;

    /**
     * The number of structural features of the '<em>Decoration Description</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DECORATION_DESCRIPTION_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.MappingBasedDecorationImpl
     * <em>Mapping Based Decoration</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.MappingBasedDecorationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getMappingBasedDecoration()
     * @generated
     */
    int MAPPING_BASED_DECORATION = 38;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__NAME = DECORATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__POSITION = DECORATION_DESCRIPTION__POSITION;

    /**
     * The feature id for the '<em><b>Decorator Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__DECORATOR_PATH = DECORATION_DESCRIPTION__DECORATOR_PATH;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__PRECONDITION_EXPRESSION = DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__MAPPINGS = DECORATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Mapping Based Decoration</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION_FEATURE_COUNT = DECORATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.SemanticBasedDecorationImpl
     * <em>Semantic Based Decoration</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.SemanticBasedDecorationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSemanticBasedDecoration()
     * @generated
     */
    int SEMANTIC_BASED_DECORATION = 39;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__NAME = DECORATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__POSITION = DECORATION_DESCRIPTION__POSITION;

    /**
     * The feature id for the '<em><b>Decorator Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__DECORATOR_PATH = DECORATION_DESCRIPTION__DECORATOR_PATH;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__PRECONDITION_EXPRESSION = DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__DOMAIN_CLASS = DECORATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Semantic Based Decoration</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION_FEATURE_COUNT = DECORATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.LayerImpl <em>Layer</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.LayerImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayer()
     * @generated
     */
    int LAYER = 40;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__DOCUMENTATION = DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__END_USER_DOCUMENTATION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__NAME = DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__LABEL = DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__NODE_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__EDGE_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__EDGE_MAPPING_IMPORTS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__CONTAINER_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__REUSED_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__ALL_TOOLS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Tool Sections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__TOOL_SECTIONS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__REUSED_TOOLS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Decoration Descriptions Set</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__DECORATION_DESCRIPTIONS_SET = DOCUMENTED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__ICON = DOCUMENTED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__ALL_EDGE_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>All Activated Edge Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__ALL_ACTIVATED_EDGE_MAPPINGS = DOCUMENTED_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Customization</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER__CUSTOMIZATION = DOCUMENTED_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The number of structural features of the '<em>Layer</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LAYER_FEATURE_COUNT = DOCUMENTED_ELEMENT_FEATURE_COUNT + 16;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.AdditionalLayerImpl
     * <em>Additional Layer</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.AdditionalLayerImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAdditionalLayer()
     * @generated
     */
    int ADDITIONAL_LAYER = 41;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__DOCUMENTATION = LAYER__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__END_USER_DOCUMENTATION = LAYER__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__NAME = LAYER__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__LABEL = LAYER__LABEL;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__NODE_MAPPINGS = LAYER__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__EDGE_MAPPINGS = LAYER__EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__EDGE_MAPPING_IMPORTS = LAYER__EDGE_MAPPING_IMPORTS;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__CONTAINER_MAPPINGS = LAYER__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__REUSED_MAPPINGS = LAYER__REUSED_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ALL_TOOLS = LAYER__ALL_TOOLS;

    /**
     * The feature id for the '<em><b>Tool Sections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__TOOL_SECTIONS = LAYER__TOOL_SECTIONS;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__REUSED_TOOLS = LAYER__REUSED_TOOLS;

    /**
     * The feature id for the '<em><b>Decoration Descriptions Set</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__DECORATION_DESCRIPTIONS_SET = LAYER__DECORATION_DESCRIPTIONS_SET;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ICON = LAYER__ICON;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ALL_EDGE_MAPPINGS = LAYER__ALL_EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Activated Edge Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ALL_ACTIVATED_EDGE_MAPPINGS = LAYER__ALL_ACTIVATED_EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Customization</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__CUSTOMIZATION = LAYER__CUSTOMIZATION;

    /**
     * The feature id for the '<em><b>Active By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT = LAYER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__OPTIONAL = LAYER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Additional Layer</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER_FEATURE_COUNT = LAYER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.CustomizationImpl
     * <em>Customization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.CustomizationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getCustomization()
     * @generated
     */
    int CUSTOMIZATION = 42;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.VSMElementCustomizationImpl
     * <em>VSM Element Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.VSMElementCustomizationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getVSMElementCustomization()
     * @generated
     */
    int VSM_ELEMENT_CUSTOMIZATION = 44;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.VSMElementCustomizationReuseImpl
     * <em>VSM Element Customization Reuse</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.VSMElementCustomizationReuseImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getVSMElementCustomizationReuse()
     * @generated
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE = 45;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EStructuralFeatureCustomizationImpl
     * <em>EStructural Feature Customization</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.EStructuralFeatureCustomizationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEStructuralFeatureCustomization()
     * @generated
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION = 46;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EAttributeCustomizationImpl
     * <em>EAttribute Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.EAttributeCustomizationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEAttributeCustomization()
     * @generated
     */
    int EATTRIBUTE_CUSTOMIZATION = 47;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EReferenceCustomizationImpl
     * <em>EReference Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.EReferenceCustomizationImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEReferenceCustomization()
     * @generated
     */
    int EREFERENCE_CUSTOMIZATION = 48;

    /**
     * The feature id for the '<em><b>Vsm Element Customizations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS = 0;

    /**
     * The number of structural features of the '<em>Customization</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOMIZATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.IVSMElementCustomization
     * <em>IVSM Element Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.IVSMElementCustomization
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIVSMElementCustomization()
     * @generated
     */
    int IVSM_ELEMENT_CUSTOMIZATION = 43;

    /**
     * The number of structural features of the '
     * <em>IVSM Element Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Feature Customizations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>VSM Element Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Reuse</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>VSM Element Customization Reuse</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE_FEATURE_COUNT = IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON = 0;

    /**
     * The number of structural features of the '
     * <em>EStructural Feature Customization</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__APPLIED_ON = ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON;

    /**
     * The feature id for the '<em><b>Attribute Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__VALUE = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>EAttribute Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION_FEATURE_COUNT = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__APPLIED_ON = ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON;

    /**
     * The feature id for the '<em><b>Reference Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__REFERENCE_NAME = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__VALUE = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>EReference Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION_FEATURE_COUNT = ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.SelectionDescription
     * <em>Selection Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.SelectionDescription
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSelectionDescription()
     * @generated
     */
    int SELECTION_DESCRIPTION = 49;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__MULTIPLE = 1;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__TREE = 2;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__ROOT_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__CHILDREN_EXPRESSION = 4;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION__MESSAGE = 5;

    /**
     * The number of structural features of the '<em>Selection Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_DESCRIPTION_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ColorDescriptionImpl
     * <em>Color Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ColorDescriptionImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getColorDescription()
     * @generated
     */
    int COLOR_DESCRIPTION = 50;

    /**
     * The number of structural features of the '<em>Color Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLOR_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.FixedColorImpl
     * <em>Fixed Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.FixedColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFixedColor()
     * @generated
     */
    int FIXED_COLOR = 54;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FIXED_COLOR__RED = COLOR_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FIXED_COLOR__GREEN = COLOR_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FIXED_COLOR__BLUE = COLOR_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Fixed Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FIXED_COLOR_FEATURE_COUNT = COLOR_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.SystemColorImpl
     * <em>System Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.SystemColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSystemColor()
     * @generated
     */
    int SYSTEM_COLOR = 51;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__RED = FIXED_COLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__GREEN = FIXED_COLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__BLUE = FIXED_COLOR__BLUE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__NAME = FIXED_COLOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>System Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR_FEATURE_COUNT = FIXED_COLOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.InterpolatedColorImpl
     * <em>Interpolated Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.InterpolatedColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getInterpolatedColor()
     * @generated
     */
    int INTERPOLATED_COLOR = 52;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__NAME = COLOR_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Color Value Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION = COLOR_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Min Value Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION = COLOR_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Max Value Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION = COLOR_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Color Steps</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__COLOR_STEPS = COLOR_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Interpolated Color</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR_FEATURE_COUNT = COLOR_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ColorStepImpl
     * <em>Color Step</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.ColorStepImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getColorStep()
     * @generated
     */
    int COLOR_STEP = 53;

    /**
     * The feature id for the '<em><b>Associated Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLOR_STEP__ASSOCIATED_VALUE = 0;

    /**
     * The feature id for the '<em><b>Associated Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLOR_STEP__ASSOCIATED_COLOR = 1;

    /**
     * The number of structural features of the '<em>Color Step</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLOR_STEP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.UserFixedColorImpl
     * <em>User Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.UserFixedColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserFixedColor()
     * @generated
     */
    int USER_FIXED_COLOR = 55;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__RED = FIXED_COLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__GREEN = FIXED_COLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__BLUE = FIXED_COLOR__BLUE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__NAME = FIXED_COLOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>User Fixed Color</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR_FEATURE_COUNT = FIXED_COLOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.UserColorImpl
     * <em>User Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.UserColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserColor()
     * @generated
     */
    int USER_COLOR = 56;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_COLOR__NAME = 0;

    /**
     * The number of structural features of the '<em>User Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_COLOR_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EnvironmentImpl
     * <em>Environment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.impl.EnvironmentImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEnvironment()
     * @generated
     */
    int ENVIRONMENT = 57;

    /**
     * The feature id for the '<em><b>System Colors</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__SYSTEM_COLORS = 0;

    /**
     * The feature id for the '<em><b>Default Tools</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__DEFAULT_TOOLS = 1;

    /**
     * The feature id for the '<em><b>Label Border Styles</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT__LABEL_BORDER_STYLES = 2;

    /**
     * The number of structural features of the '<em>Environment</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENVIRONMENT_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.SytemColorsPaletteImpl
     * <em>Sytem Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.SytemColorsPaletteImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSytemColorsPalette()
     * @generated
     */
    int SYTEM_COLORS_PALETTE = 58;

    /**
     * The feature id for the '<em><b>Entries</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYTEM_COLORS_PALETTE__ENTRIES = 0;

    /**
     * The number of structural features of the '<em>Sytem Colors Palette</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYTEM_COLORS_PALETTE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.UserColorsPaletteImpl
     * <em>User Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.UserColorsPaletteImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserColorsPalette()
     * @generated
     */
    int USER_COLORS_PALETTE = 59;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_COLORS_PALETTE__NAME = 0;

    /**
     * The feature id for the '<em><b>Entries</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_COLORS_PALETTE__ENTRIES = 1;

    /**
     * The number of structural features of the '<em>User Colors Palette</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_COLORS_PALETTE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.AnnotationEntryImpl
     * <em>Annotation Entry</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.AnnotationEntryImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAnnotationEntry()
     * @generated
     */
    int ANNOTATION_ENTRY = 60;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ANNOTATION_ENTRY__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ANNOTATION_ENTRY__DATA = 1;

    /**
     * The number of structural features of the '<em>Annotation Entry</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ANNOTATION_ENTRY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.EndUserDocumentedElementImpl
     * <em>End User Documented Element</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.EndUserDocumentedElementImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEndUserDocumentedElement()
     * @generated
     */
    int END_USER_DOCUMENTED_ELEMENT = 61;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION = 0;

    /**
     * The number of structural features of the '
     * <em>End User Documented Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_USER_DOCUMENTED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.ComputedColorImpl
     * <em>Computed Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.ComputedColorImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getComputedColor()
     * @generated
     */
    int COMPUTED_COLOR = 63;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__NAME = USER_COLOR__NAME;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__RED = USER_COLOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__GREEN = USER_COLOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__BLUE = USER_COLOR_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Computed Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR_FEATURE_COUNT = USER_COLOR_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.impl.DAnnotationEntryImpl
     * <em>DAnnotation Entry</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.impl.DAnnotationEntryImpl
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDAnnotationEntry()
     * @generated
     */
    int DANNOTATION_ENTRY = 64;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION_ENTRY__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Details</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION_ENTRY__DETAILS = 1;

    /**
     * The number of structural features of the '<em>DAnnotation Entry</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DANNOTATION_ENTRY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.NavigationTargetType
     * <em>Navigation Target Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.NavigationTargetType
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNavigationTargetType()
     * @generated
     */
    int NAVIGATION_TARGET_TYPE = 65;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.FoldingStyle
     * <em>Folding Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.FoldingStyle
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFoldingStyle()
     * @generated
     */
    int FOLDING_STYLE = 66;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.LayoutDirection
     * <em>Layout Direction</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.LayoutDirection
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayoutDirection()
     * @generated
     */
    int LAYOUT_DIRECTION = 67;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.Position <em>Position</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.Position
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getPosition()
     * @generated
     */
    int POSITION = 68;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.SystemColors
     * <em>System Colors</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.SystemColors
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSystemColors()
     * @generated
     */
    int SYSTEM_COLORS = 69;

    /**
     * The meta object id for the '<em>Type Name</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see java.lang.String
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getTypeName()
     * @generated
     */
    int TYPE_NAME = 70;

    /**
     * The meta object id for the '<em>Interpreted Expression</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see java.lang.String
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getInterpretedExpression()
     * @generated
     */
    int INTERPRETED_EXPRESSION = 71;

    /**
     * The meta object id for the '<em>Feature Name</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see java.lang.String
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFeatureName()
     * @generated
     */
    int FEATURE_NAME = 72;

    /**
     * The meta object id for the '<em>URI</em>' data type. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.common.util.URI
     * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getURI()
     * @generated
     */
    int URI = 73;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Component <em>Component</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Component</em>'.
     * @see org.eclipse.sirius.description.Component
     * @generated
     */
    EClass getComponent();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.RepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Representation Description</em>'.
     * @see org.eclipse.sirius.description.RepresentationDescription
     * @generated
     */
    EClass getRepresentationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationDescription#getTitleExpression
     * <em>Title Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.description.RepresentationDescription#getTitleExpression()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationDescription#isInitialisation
     * <em>Initialisation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Initialisation</em>'.
     * @see org.eclipse.sirius.description.RepresentationDescription#isInitialisation()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_Initialisation();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.RepresentationDescription#getMetamodel
     * <em>Metamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Metamodel</em>'.
     * @see org.eclipse.sirius.description.RepresentationDescription#getMetamodel()
     * @see #getRepresentationDescription()
     * @generated
     */
    EReference getRepresentationDescription_Metamodel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationDescription#isShowOnStartup
     * <em>Show On Startup</em>}'. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Show On Startup</em>'.
     * @see org.eclipse.sirius.description.RepresentationDescription#isShowOnStartup()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_ShowOnStartup();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.RepresentationTemplate
     * <em>Representation Template</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Representation Template</em>'.
     * @see org.eclipse.sirius.description.RepresentationTemplate
     * @generated
     */
    EClass getRepresentationTemplate();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationTemplate#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.RepresentationTemplate#getName()
     * @see #getRepresentationTemplate()
     * @generated
     */
    EAttribute getRepresentationTemplate_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.RepresentationTemplate#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.description.RepresentationTemplate#getOwnedRepresentations()
     * @see #getRepresentationTemplate()
     * @generated
     */
    EReference getRepresentationTemplate_OwnedRepresentations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.RepresentationImportDescription
     * <em>Representation Import Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Import Description</em>'.
     * @see org.eclipse.sirius.description.RepresentationImportDescription
     * @generated
     */
    EClass getRepresentationImportDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription
     * <em>Representation Extension Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Extension Description</em>'.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription
     * @generated
     */
    EClass getRepresentationExtensionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription#getName()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription#getSiriusURI
     * <em>Sirius URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Sirius URI</em>'.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription#getSiriusURI()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_SiriusURI();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription#getRepresentationName
     * <em>Representation Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Representation Name</em>'.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription#getRepresentationName()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_RepresentationName();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription#getMetamodel
     * <em>Metamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Metamodel</em>'.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription#getMetamodel()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EReference getRepresentationExtensionDescription_Metamodel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription
     * @generated
     */
    EClass getDiagramDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getFilters
     * <em>Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Filters</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getFilters()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Filters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllEdgeMappings
     * <em>All Edge Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllEdgeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllEdgeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllActivatedEdgeMappings
     * <em>All Activated Edge Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Activated Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllActivatedEdgeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllActivatedEdgeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllNodeMappings
     * <em>All Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Node Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllNodeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllNodeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllContainerMappings
     * <em>All Container Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Container Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllContainerMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllContainerMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getValidationSet()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getConcerns
     * <em>Concerns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Concerns</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramDescription#getConcerns()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Concerns();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getInformationSections
     * <em>Information Sections</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Information Sections</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getInformationSections()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_InformationSections();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllTools
     * <em>All Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Tools</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllTools();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramDescription#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getDomainClass()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getPreconditionExpression()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_PreconditionExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getDefaultConcern
     * <em>Default Concern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Default Concern</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getDefaultConcern()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DefaultConcern();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramDescription#getRootExpression
     * <em>Root Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getRootExpression()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_RootExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getInit
     * <em>Init</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Init</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getInit()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Init();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getLayout
     * <em>Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getLayout()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Layout();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getDefaultLayer
     * <em>Default Layer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Default Layer</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getDefaultLayer()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DefaultLayer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAdditionalLayers
     * <em>Additional Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Additional Layers</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAdditionalLayers()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AdditionalLayers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllLayers
     * <em>All Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Layers</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllLayers()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllLayers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getAllActivatedTools
     * <em>All Activated Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Activated Tools</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getAllActivatedTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllActivatedTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getNodeMappings
     * <em>Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Node Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getNodeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_NodeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getEdgeMappings
     * <em>Edge Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getEdgeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_EdgeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getEdgeMappingImports
     * <em>Edge Mapping Imports</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Edge Mapping Imports</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getEdgeMappingImports()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_EdgeMappingImports();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getContainerMappings
     * <em>Container Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Container Mappings</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getContainerMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ContainerMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getReusedMappings
     * <em>Reused Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Mappings</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramDescription#getReusedMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ReusedMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getToolSection
     * <em>Tool Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Tool Section</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getToolSection()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ToolSection();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DiagramDescription#getReusedTools
     * <em>Reused Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getReusedTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ReusedTools();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramDescription#isEnablePopupBars
     * <em>Enable Popup Bars</em>}'. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enable Popup Bars</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#isEnablePopupBars()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_EnablePopupBars();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DiagramImportDescription
     * <em>Diagram Import Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram Import Description</em>'.
     * @see org.eclipse.sirius.description.DiagramImportDescription
     * @generated
     */
    EClass getDiagramImportDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramImportDescription#getImportedDiagram
     * <em>Imported Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Imported Diagram</em>'.
     * @see org.eclipse.sirius.description.DiagramImportDescription#getImportedDiagram()
     * @see #getDiagramImportDescription()
     * @generated
     */
    EReference getDiagramImportDescription_ImportedDiagram();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DiagramExtensionDescription
     * <em>Diagram Extension Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram Extension Description</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramExtensionDescription
     * @generated
     */
    EClass getDiagramExtensionDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DiagramExtensionDescription#getLayers
     * <em>Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Layers</em>'.
     * @see org.eclipse.sirius.description.DiagramExtensionDescription#getLayers()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_Layers();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramExtensionDescription#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.description.DiagramExtensionDescription#getValidationSet()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramExtensionDescription#getConcerns
     * <em>Concerns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Concerns</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramExtensionDescription#getConcerns()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_Concerns();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.DiagramDescription#getDiagramInitialisation
     * <em>Diagram Initialisation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Diagram Initialisation</em>'.
     * @see org.eclipse.sirius.description.DiagramDescription#getDiagramInitialisation()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DiagramInitialisation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Sirius <em>Sirius</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Sirius</em>'.
     * @see org.eclipse.sirius.description.Sirius
     * @generated
     */
    EClass getSirius();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.Sirius#getModelFileExtension
     * <em>Model File Extension</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Model File Extension</em>
     *         '.
     * @see org.eclipse.sirius.description.Sirius#getModelFileExtension()
     * @see #getSirius()
     * @generated
     */
    EAttribute getSirius_ModelFileExtension();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.Sirius#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.description.Sirius#getValidationSet()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_ValidationSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedRepresentations()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedRepresentations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedRepresentationExtensions
     * <em>Owned Representation Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representation Extensions</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedRepresentationExtensions()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedRepresentationExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedJavaExtensions
     * <em>Owned Java Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Java Extensions</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedJavaExtensions()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedJavaExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedMMExtensions
     * <em>Owned MM Extensions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned MM Extensions</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedMMExtensions()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedMMExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedFeatureExtensions
     * <em>Owned Feature Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Feature Extensions</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedFeatureExtensions()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedFeatureExtensions();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.Sirius#getIcon <em>Icon</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.description.Sirius#getIcon()
     * @see #getSirius()
     * @generated
     */
    EAttribute getSirius_Icon();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Sirius#getOwnedTemplates
     * <em>Owned Templates</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Templates</em>'.
     * @see org.eclipse.sirius.description.Sirius#getOwnedTemplates()
     * @see #getSirius()
     * @generated
     */
    EReference getSirius_OwnedTemplates();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.description.Sirius#getConflicts
     * <em>Conflicts</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Conflicts</em>'.
     * @see org.eclipse.sirius.description.Sirius#getConflicts()
     * @see #getSirius()
     * @generated
     */
    EAttribute getSirius_Conflicts();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.description.Sirius#getReuses
     * <em>Reuses</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Reuses</em>'.
     * @see org.eclipse.sirius.description.Sirius#getReuses()
     * @see #getSirius()
     * @generated
     */
    EAttribute getSirius_Reuses();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.description.Sirius#getCustomizes
     * <em>Customizes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Customizes</em>'.
     * @see org.eclipse.sirius.description.Sirius#getCustomizes()
     * @see #getSirius()
     * @generated
     */
    EAttribute getSirius_Customizes();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.FeatureExtensionDescription
     * <em>Feature Extension Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Feature Extension Description</em>
     *         '.
     * @see org.eclipse.sirius.description.FeatureExtensionDescription
     * @generated
     */
    EClass getFeatureExtensionDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.MetamodelExtensionSetting
     * <em>Metamodel Extension Setting</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Metamodel Extension Setting</em>'.
     * @see org.eclipse.sirius.description.MetamodelExtensionSetting
     * @generated
     */
    EClass getMetamodelExtensionSetting();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.MetamodelExtensionSetting#getExtensionGroup
     * <em>Extension Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Extension Group</em>'.
     * @see org.eclipse.sirius.description.MetamodelExtensionSetting#getExtensionGroup()
     * @see #getMetamodelExtensionSetting()
     * @generated
     */
    EReference getMetamodelExtensionSetting_ExtensionGroup();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.JavaExtension
     * <em>Java Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Java Extension</em>'.
     * @see org.eclipse.sirius.description.JavaExtension
     * @generated
     */
    EClass getJavaExtension();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.JavaExtension#getQualifiedClassName
     * <em>Qualified Class Name</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Qualified Class Name</em>
     *         '.
     * @see org.eclipse.sirius.description.JavaExtension#getQualifiedClassName()
     * @see #getJavaExtension()
     * @generated
     */
    EAttribute getJavaExtension_QualifiedClassName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.RepresentationElementMapping
     * <em>Representation Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Element Mapping</em>'.
     * @see org.eclipse.sirius.description.RepresentationElementMapping
     * @generated
     */
    EClass getRepresentationElementMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.RepresentationElementMapping#getDetailDescriptions
     * <em>Detail Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Detail Descriptions</em>'.
     * @see org.eclipse.sirius.description.RepresentationElementMapping#getDetailDescriptions()
     * @see #getRepresentationElementMapping()
     * @generated
     */
    EReference getRepresentationElementMapping_DetailDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.RepresentationElementMapping#getNavigationDescriptions
     * <em>Navigation Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Navigation Descriptions</em>'.
     * @see org.eclipse.sirius.description.RepresentationElementMapping#getNavigationDescriptions()
     * @see #getRepresentationElementMapping()
     * @generated
     */
    EReference getRepresentationElementMapping_NavigationDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping
     * @generated
     */
    EClass getDiagramElementMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getPreconditionExpression()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_PreconditionExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getDeletionDescription
     * <em>Deletion Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Deletion Description</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getDeletionDescription()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_DeletionDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getLabelDirectEdit
     * <em>Label Direct Edit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Label Direct Edit</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getLabelDirectEdit()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_LabelDirectEdit();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getSemanticCandidatesExpression()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#isCreateElements
     * <em>Create Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Create Elements</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#isCreateElements()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_CreateElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getSemanticElements
     * <em>Semantic Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getSemanticElements()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SemanticElements();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#getDoubleClickDescription
     * <em>Double Click Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Double Click Description</em>'.
     * @see org.eclipse.sirius.description.DiagramElementMapping#getDoubleClickDescription()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_DoubleClickDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DiagramElementMapping#isSynchronizationLock
     * <em>Synchronization Lock</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Synchronization Lock</em>
     *         '.
     * @see org.eclipse.sirius.description.DiagramElementMapping#isSynchronizationLock()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SynchronizationLock();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.AbstractNodeMapping
     * <em>Abstract Node Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Node Mapping</em>'.
     * @see org.eclipse.sirius.description.AbstractNodeMapping
     * @generated
     */
    EClass getAbstractNodeMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AbstractNodeMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.AbstractNodeMapping#getDomainClass()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EAttribute getAbstractNodeMapping_DomainClass();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.AbstractNodeMapping#getBorderedNodeMappings
     * <em>Bordered Node Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Bordered Node Mappings</em>'.
     * @see org.eclipse.sirius.description.AbstractNodeMapping#getBorderedNodeMappings()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EReference getAbstractNodeMapping_BorderedNodeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.AbstractNodeMapping#getReusedBorderedNodeMappings
     * <em>Reused Bordered Node Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Reused Bordered Node Mappings</em>'.
     * @see org.eclipse.sirius.description.AbstractNodeMapping#getReusedBorderedNodeMappings()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EReference getAbstractNodeMapping_ReusedBorderedNodeMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.NodeMapping
     * <em>Node Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Mapping</em>'.
     * @see org.eclipse.sirius.description.NodeMapping
     * @generated
     */
    EClass getNodeMapping();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.NodeMapping#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.NodeMapping#getStyle()
     * @see #getNodeMapping()
     * @generated
     */
    EReference getNodeMapping_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.NodeMapping#getConditionnalStyles
     * <em>Conditionnal Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.description.NodeMapping#getConditionnalStyles()
     * @see #getNodeMapping()
     * @generated
     */
    EReference getNodeMapping_ConditionnalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.NodeMappingImport
     * <em>Node Mapping Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Node Mapping Import</em>'.
     * @see org.eclipse.sirius.description.NodeMappingImport
     * @generated
     */
    EClass getNodeMappingImport();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.NodeMappingImport#getImportedMapping
     * <em>Imported Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.description.NodeMappingImport#getImportedMapping()
     * @see #getNodeMappingImport()
     * @generated
     */
    EReference getNodeMappingImport_ImportedMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ContainerMappingImport
     * <em>Container Mapping Import</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Mapping Import</em>'.
     * @see org.eclipse.sirius.description.ContainerMappingImport
     * @generated
     */
    EClass getContainerMappingImport();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.ContainerMappingImport#getImportedMapping
     * <em>Imported Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.description.ContainerMappingImport#getImportedMapping()
     * @see #getContainerMappingImport()
     * @generated
     */
    EReference getContainerMappingImport_ImportedMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ContainerMapping
     * <em>Container Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Container Mapping</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping
     * @generated
     */
    EClass getContainerMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getSubNodeMappings
     * <em>Sub Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Node Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getSubNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_SubNodeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getAllNodeMappings
     * <em>All Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Node Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getAllNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_AllNodeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getReusedNodeMappings
     * <em>Reused Node Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Reused Node Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getReusedNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ReusedNodeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getSubContainerMappings
     * <em>Sub Container Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Container Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getSubContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_SubContainerMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getReusedContainerMappings
     * <em>Reused Container Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Reused Container Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getReusedContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ReusedContainerMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getAllContainerMappings
     * <em>All Container Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Container Mappings</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getAllContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_AllContainerMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.ContainerMapping#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getStyle()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.ContainerMapping#getConditionnalStyles
     * <em>Conditionnal Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.description.ContainerMapping#getConditionnalStyles()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ContainerMapping#getChildrenPresentation
     * <em>Children Presentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Children Presentation</em>
     *         '.
     * @see org.eclipse.sirius.description.ContainerMapping#getChildrenPresentation()
     * @see #getContainerMapping()
     * @generated
     */
    EAttribute getContainerMapping_ChildrenPresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.AbstractMappingImport
     * <em>Abstract Mapping Import</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Mapping Import</em>'.
     * @see org.eclipse.sirius.description.AbstractMappingImport
     * @generated
     */
    EClass getAbstractMappingImport();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AbstractMappingImport#isHideSubMappings
     * <em>Hide Sub Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Hide Sub Mappings</em>'.
     * @see org.eclipse.sirius.description.AbstractMappingImport#isHideSubMappings()
     * @see #getAbstractMappingImport()
     * @generated
     */
    EAttribute getAbstractMappingImport_HideSubMappings();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AbstractMappingImport#isInheritsAncestorFilters
     * <em>Inherits Ancestor Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Inherits Ancestor Filters</em>'.
     * @see org.eclipse.sirius.description.AbstractMappingImport#isInheritsAncestorFilters()
     * @see #getAbstractMappingImport()
     * @generated
     */
    EAttribute getAbstractMappingImport_InheritsAncestorFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EdgeMapping
     * <em>Edge Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Mapping</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping
     * @generated
     */
    EClass getEdgeMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.EdgeMapping#getSourceMapping
     * <em>Source Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Source Mapping</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getSourceMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_SourceMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.EdgeMapping#getTargetMapping
     * <em>Target Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Target Mapping</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getTargetMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_TargetMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#getTargetFinderExpression
     * <em>Target Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Target Finder Expression</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getTargetFinderExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_TargetFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#getSourceFinderExpression
     * <em>Source Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Source Finder Expression</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getSourceFinderExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_SourceFinderExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.EdgeMapping#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getStyle()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.EdgeMapping#getConditionnalStyles
     * <em>Conditionnal Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getConditionnalStyles()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#getTargetExpression
     * <em>Target Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Target Expression</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getTargetExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_TargetExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getDomainClass()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#isUseDomainElement
     * <em>Use Domain Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Use Domain Element</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#isUseDomainElement()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_UseDomainElement();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.EdgeMapping#getReconnections
     * <em>Reconnections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reconnections</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getReconnections()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_Reconnections();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMapping#getPathExpression
     * <em>Path Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Path Expression</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getPathExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_PathExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.EdgeMapping#getPathNodeMapping
     * <em>Path Node Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Path Node Mapping</em>'.
     * @see org.eclipse.sirius.description.EdgeMapping#getPathNodeMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_PathNodeMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.IEdgeMapping
     * <em>IEdge Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>IEdge Mapping</em>'.
     * @see org.eclipse.sirius.description.IEdgeMapping
     * @generated
     */
    EClass getIEdgeMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EdgeMappingImport
     * <em>Edge Mapping Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Edge Mapping Import</em>'.
     * @see org.eclipse.sirius.description.EdgeMappingImport
     * @generated
     */
    EClass getEdgeMappingImport();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.EdgeMappingImport#getImportedMapping
     * <em>Imported Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.description.EdgeMappingImport#getImportedMapping()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EReference getEdgeMappingImport_ImportedMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.EdgeMappingImport#getConditionnalStyles
     * <em>Conditionnal Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.description.EdgeMappingImport#getConditionnalStyles()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EReference getEdgeMappingImport_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EdgeMappingImport#isInheritsAncestorFilters
     * <em>Inherits Ancestor Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Inherits Ancestor Filters</em>'.
     * @see org.eclipse.sirius.description.EdgeMappingImport#isInheritsAncestorFilters()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EAttribute getEdgeMappingImport_InheritsAncestorFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Group <em>Group</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Group</em>'.
     * @see org.eclipse.sirius.description.Group
     * @generated
     */
    EClass getGroup();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.Group#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.Group#getName()
     * @see #getGroup()
     * @generated
     */
    EAttribute getGroup_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Group#getOwnedSiriuss
     * <em>Owned Siriuss</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Siriuss</em>'.
     * @see org.eclipse.sirius.description.Group#getOwnedSiriuss()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_OwnedSiriuss();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.Group#getSystemColorsPalette
     * <em>System Colors Palette</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>System Colors Palette</em>
     *         '.
     * @see org.eclipse.sirius.description.Group#getSystemColorsPalette()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_SystemColorsPalette();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Group#getUserColorsPalettes
     * <em>User Colors Palettes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>User Colors Palettes</em>'.
     * @see org.eclipse.sirius.description.Group#getUserColorsPalettes()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_UserColorsPalettes();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.Group#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.description.Group#getVersion()
     * @see #getGroup()
     * @generated
     */
    EAttribute getGroup_Version();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Documented Element</em>'.
     * @see org.eclipse.sirius.description.DocumentedElement
     * @generated
     */
    EClass getDocumentedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DocumentedElement#getDocumentation
     * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.eclipse.sirius.description.DocumentedElement#getDocumentation()
     * @see #getDocumentedElement()
     * @generated
     */
    EAttribute getDocumentedElement_Documentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DModel Element</em>'.
     * @see org.eclipse.sirius.description.DModelElement
     * @generated
     */
    EClass getDModelElement();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DModelElement#getEAnnotations
     * <em>EAnnotations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>EAnnotations</em>'.
     * @see org.eclipse.sirius.description.DModelElement#getEAnnotations()
     * @see #getDModelElement()
     * @generated
     */
    EReference getDModelElement_EAnnotations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DAnnotation
     * <em>DAnnotation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnnotation</em>'.
     * @see org.eclipse.sirius.description.DAnnotation
     * @generated
     */
    EClass getDAnnotation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DAnnotation#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.description.DAnnotation#getSource()
     * @see #getDAnnotation()
     * @generated
     */
    EAttribute getDAnnotation_Source();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.description.DAnnotation#getDetails
     * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Details</em>'.
     * @see org.eclipse.sirius.description.DAnnotation#getDetails()
     * @see #getDAnnotation()
     * @generated
     */
    EReference getDAnnotation_Details();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ConditionalStyleDescription
     * <em>Conditional Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Conditional Style Description</em>
     *         '.
     * @see org.eclipse.sirius.description.ConditionalStyleDescription
     * @generated
     */
    EClass getConditionalStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ConditionalStyleDescription#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.ConditionalStyleDescription#getPredicateExpression()
     * @see #getConditionalStyleDescription()
     * @generated
     */
    EAttribute getConditionalStyleDescription_PredicateExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ConditionalNodeStyleDescription
     * <em>Conditional Node Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Conditional Node Style Description</em>'.
     * @see org.eclipse.sirius.description.ConditionalNodeStyleDescription
     * @generated
     */
    EClass getConditionalNodeStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.ConditionalNodeStyleDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.ConditionalNodeStyleDescription#getStyle()
     * @see #getConditionalNodeStyleDescription()
     * @generated
     */
    EReference getConditionalNodeStyleDescription_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ConditionalEdgeStyleDescription
     * <em>Conditional Edge Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Conditional Edge Style Description</em>'.
     * @see org.eclipse.sirius.description.ConditionalEdgeStyleDescription
     * @generated
     */
    EClass getConditionalEdgeStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.ConditionalEdgeStyleDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.ConditionalEdgeStyleDescription#getStyle()
     * @see #getConditionalEdgeStyleDescription()
     * @generated
     */
    EReference getConditionalEdgeStyleDescription_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ConditionalContainerStyleDescription
     * <em>Conditional Container Style Description</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Conditional Container Style Description</em>'.
     * @see org.eclipse.sirius.description.ConditionalContainerStyleDescription
     * @generated
     */
    EClass getConditionalContainerStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.ConditionalContainerStyleDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.description.ConditionalContainerStyleDescription#getStyle()
     * @see #getConditionalContainerStyleDescription()
     * @generated
     */
    EReference getConditionalContainerStyleDescription_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DragAndDropTargetDescription
     * <em>Drag And Drop Target Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Drag And Drop Target Description</em>'.
     * @see org.eclipse.sirius.description.DragAndDropTargetDescription
     * @generated
     */
    EClass getDragAndDropTargetDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.DragAndDropTargetDescription#getDropDescriptions
     * <em>Drop Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Drop Descriptions</em>'.
     * @see org.eclipse.sirius.description.DragAndDropTargetDescription#getDropDescriptions()
     * @see #getDragAndDropTargetDescription()
     * @generated
     */
    EReference getDragAndDropTargetDescription_DropDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.PasteTargetDescription
     * <em>Paste Target Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Paste Target Description</em>'.
     * @see org.eclipse.sirius.description.PasteTargetDescription
     * @generated
     */
    EClass getPasteTargetDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.PasteTargetDescription#getPasteDescriptions
     * <em>Paste Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Paste Descriptions</em>'.
     * @see org.eclipse.sirius.description.PasteTargetDescription#getPasteDescriptions()
     * @see #getPasteTargetDescription()
     * @generated
     */
    EReference getPasteTargetDescription_PasteDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Layout <em>Layout</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Layout</em>'.
     * @see org.eclipse.sirius.description.Layout
     * @generated
     */
    EClass getLayout();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.OrderedTreeLayout
     * <em>Ordered Tree Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Ordered Tree Layout</em>'.
     * @see org.eclipse.sirius.description.OrderedTreeLayout
     * @generated
     */
    EClass getOrderedTreeLayout();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.OrderedTreeLayout#getChildrenExpression
     * <em>Children Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.description.OrderedTreeLayout#getChildrenExpression()
     * @see #getOrderedTreeLayout()
     * @generated
     */
    EAttribute getOrderedTreeLayout_ChildrenExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.OrderedTreeLayout#getNodeMapping
     * <em>Node Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Node Mapping</em>'.
     * @see org.eclipse.sirius.description.OrderedTreeLayout#getNodeMapping()
     * @see #getOrderedTreeLayout()
     * @generated
     */
    EReference getOrderedTreeLayout_NodeMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.CompositeLayout
     * <em>Composite Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Composite Layout</em>'.
     * @see org.eclipse.sirius.description.CompositeLayout
     * @generated
     */
    EClass getCompositeLayout();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.CompositeLayout#getPadding
     * <em>Padding</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Padding</em>'.
     * @see org.eclipse.sirius.description.CompositeLayout#getPadding()
     * @see #getCompositeLayout()
     * @generated
     */
    EAttribute getCompositeLayout_Padding();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.CompositeLayout#getDirection
     * <em>Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see org.eclipse.sirius.description.CompositeLayout#getDirection()
     * @see #getCompositeLayout()
     * @generated
     */
    EAttribute getCompositeLayout_Direction();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DecorationDescriptionsSet
     * <em>Decoration Descriptions Set</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration Descriptions Set</em>'.
     * @see org.eclipse.sirius.description.DecorationDescriptionsSet
     * @generated
     */
    EClass getDecorationDescriptionsSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.DecorationDescriptionsSet#getDecorationDescriptions
     * <em>Decoration Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Decoration Descriptions</em>'.
     * @see org.eclipse.sirius.description.DecorationDescriptionsSet#getDecorationDescriptions()
     * @see #getDecorationDescriptionsSet()
     * @generated
     */
    EReference getDecorationDescriptionsSet_DecorationDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DecorationDescription
     * <em>Decoration Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration Description</em>'.
     * @see org.eclipse.sirius.description.DecorationDescription
     * @generated
     */
    EClass getDecorationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DecorationDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.DecorationDescription#getName()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DecorationDescription#getPosition
     * <em>Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Position</em>'.
     * @see org.eclipse.sirius.description.DecorationDescription#getPosition()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_Position();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DecorationDescription#getDecoratorPath
     * <em>Decorator Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Decorator Path</em>'.
     * @see org.eclipse.sirius.description.DecorationDescription#getDecoratorPath()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_DecoratorPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DecorationDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.description.DecorationDescription#getPreconditionExpression()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_PreconditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.MappingBasedDecoration
     * <em>Mapping Based Decoration</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Mapping Based Decoration</em>'.
     * @see org.eclipse.sirius.description.MappingBasedDecoration
     * @generated
     */
    EClass getMappingBasedDecoration();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.MappingBasedDecoration#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.description.MappingBasedDecoration#getMappings()
     * @see #getMappingBasedDecoration()
     * @generated
     */
    EReference getMappingBasedDecoration_Mappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.SemanticBasedDecoration
     * <em>Semantic Based Decoration</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Semantic Based Decoration</em>'.
     * @see org.eclipse.sirius.description.SemanticBasedDecoration
     * @generated
     */
    EClass getSemanticBasedDecoration();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SemanticBasedDecoration#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.SemanticBasedDecoration#getDomainClass()
     * @see #getSemanticBasedDecoration()
     * @generated
     */
    EAttribute getSemanticBasedDecoration_DomainClass();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Layer <em>Layer</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Layer</em>'.
     * @see org.eclipse.sirius.description.Layer
     * @generated
     */
    EClass getLayer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Layer#getNodeMappings
     * <em>Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Node Mappings</em>'.
     * @see org.eclipse.sirius.description.Layer#getNodeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_NodeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Layer#getEdgeMappings
     * <em>Edge Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.Layer#getEdgeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_EdgeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Layer#getEdgeMappingImports
     * <em>Edge Mapping Imports</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Edge Mapping Imports</em>'.
     * @see org.eclipse.sirius.description.Layer#getEdgeMappingImports()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_EdgeMappingImports();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Layer#getContainerMappings
     * <em>Container Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Container Mappings</em>'.
     * @see org.eclipse.sirius.description.Layer#getContainerMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ContainerMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.Layer#getReusedMappings
     * <em>Reused Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Mappings</em>
     *         '.
     * @see org.eclipse.sirius.description.Layer#getReusedMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ReusedMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.Layer#getAllTools
     * <em>All Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Tools</em>'.
     * @see org.eclipse.sirius.description.Layer#getAllTools()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_AllTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Layer#getToolSections
     * <em>Tool Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Tool Sections</em>'.
     * @see org.eclipse.sirius.description.Layer#getToolSections()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ToolSections();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.Layer#getReusedTools
     * <em>Reused Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.description.Layer#getReusedTools()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ReusedTools();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.Layer#getDecorationDescriptionsSet
     * <em>Decoration Descriptions Set</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Decoration Descriptions Set</em>'.
     * @see org.eclipse.sirius.description.Layer#getDecorationDescriptionsSet()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_DecorationDescriptionsSet();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.Layer#getIcon <em>Icon</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.description.Layer#getIcon()
     * @see #getLayer()
     * @generated
     */
    EAttribute getLayer_Icon();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.Layer#getAllEdgeMappings
     * <em>All Edge Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.Layer#getAllEdgeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_AllEdgeMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.Layer#getAllActivatedEdgeMappings
     * <em>All Activated Edge Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Activated Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.Layer#getAllActivatedEdgeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_AllActivatedEdgeMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.Layer#getCustomization
     * <em>Customization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Customization</em>'.
     * @see org.eclipse.sirius.description.Layer#getCustomization()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_Customization();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.AdditionalLayer
     * <em>Additional Layer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Additional Layer</em>'.
     * @see org.eclipse.sirius.description.AdditionalLayer
     * @generated
     */
    EClass getAdditionalLayer();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AdditionalLayer#isActiveByDefault
     * <em>Active By Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Active By Default</em>'.
     * @see org.eclipse.sirius.description.AdditionalLayer#isActiveByDefault()
     * @see #getAdditionalLayer()
     * @generated
     */
    EAttribute getAdditionalLayer_ActiveByDefault();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AdditionalLayer#isOptional
     * <em>Optional</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Optional</em>'.
     * @see org.eclipse.sirius.description.AdditionalLayer#isOptional()
     * @see #getAdditionalLayer()
     * @generated
     */
    EAttribute getAdditionalLayer_Optional();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Customization
     * <em>Customization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Customization</em>'.
     * @see org.eclipse.sirius.description.Customization
     * @generated
     */
    EClass getCustomization();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Customization#getVsmElementCustomizations
     * <em>Vsm Element Customizations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Vsm Element Customizations</em>'.
     * @see org.eclipse.sirius.description.Customization#getVsmElementCustomizations()
     * @see #getCustomization()
     * @generated
     */
    EReference getCustomization_VsmElementCustomizations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.IVSMElementCustomization
     * <em>IVSM Element Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>IVSM Element Customization</em>'.
     * @see org.eclipse.sirius.description.IVSMElementCustomization
     * @generated
     */
    EClass getIVSMElementCustomization();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.VSMElementCustomization
     * <em>VSM Element Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>VSM Element Customization</em>'.
     * @see org.eclipse.sirius.description.VSMElementCustomization
     * @generated
     */
    EClass getVSMElementCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.VSMElementCustomization#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.VSMElementCustomization#getPredicateExpression()
     * @see #getVSMElementCustomization()
     * @generated
     */
    EAttribute getVSMElementCustomization_PredicateExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.VSMElementCustomization#getFeatureCustomizations
     * <em>Feature Customizations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Feature Customizations</em>'.
     * @see org.eclipse.sirius.description.VSMElementCustomization#getFeatureCustomizations()
     * @see #getVSMElementCustomization()
     * @generated
     */
    EReference getVSMElementCustomization_FeatureCustomizations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.VSMElementCustomizationReuse
     * <em>VSM Element Customization Reuse</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>VSM Element Customization Reuse</em>'.
     * @see org.eclipse.sirius.description.VSMElementCustomizationReuse
     * @generated
     */
    EClass getVSMElementCustomizationReuse();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.VSMElementCustomizationReuse#getReuse
     * <em>Reuse</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reuse</em>'.
     * @see org.eclipse.sirius.description.VSMElementCustomizationReuse#getReuse()
     * @see #getVSMElementCustomizationReuse()
     * @generated
     */
    EReference getVSMElementCustomizationReuse_Reuse();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.VSMElementCustomizationReuse#getAppliedOn
     * <em>Applied On</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Applied On</em>'.
     * @see org.eclipse.sirius.description.VSMElementCustomizationReuse#getAppliedOn()
     * @see #getVSMElementCustomizationReuse()
     * @generated
     */
    EReference getVSMElementCustomizationReuse_AppliedOn();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EStructuralFeatureCustomization
     * <em>EStructural Feature Customization</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>EStructural Feature Customization</em>'.
     * @see org.eclipse.sirius.description.EStructuralFeatureCustomization
     * @generated
     */
    EClass getEStructuralFeatureCustomization();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.EStructuralFeatureCustomization#getAppliedOn
     * <em>Applied On</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Applied On</em>'.
     * @see org.eclipse.sirius.description.EStructuralFeatureCustomization#getAppliedOn()
     * @see #getEStructuralFeatureCustomization()
     * @generated
     */
    EReference getEStructuralFeatureCustomization_AppliedOn();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EAttributeCustomization
     * <em>EAttribute Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>EAttribute Customization</em>'.
     * @see org.eclipse.sirius.description.EAttributeCustomization
     * @generated
     */
    EClass getEAttributeCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EAttributeCustomization#getAttributeName
     * <em>Attribute Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Attribute Name</em>'.
     * @see org.eclipse.sirius.description.EAttributeCustomization#getAttributeName()
     * @see #getEAttributeCustomization()
     * @generated
     */
    EAttribute getEAttributeCustomization_AttributeName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EAttributeCustomization#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.description.EAttributeCustomization#getValue()
     * @see #getEAttributeCustomization()
     * @generated
     */
    EAttribute getEAttributeCustomization_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EReferenceCustomization
     * <em>EReference Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>EReference Customization</em>'.
     * @see org.eclipse.sirius.description.EReferenceCustomization
     * @generated
     */
    EClass getEReferenceCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EReferenceCustomization#getReferenceName
     * <em>Reference Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Reference Name</em>'.
     * @see org.eclipse.sirius.description.EReferenceCustomization#getReferenceName()
     * @see #getEReferenceCustomization()
     * @generated
     */
    EAttribute getEReferenceCustomization_ReferenceName();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.EReferenceCustomization#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.eclipse.sirius.description.EReferenceCustomization#getValue()
     * @see #getEReferenceCustomization()
     * @generated
     */
    EReference getEReferenceCustomization_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.SelectionDescription
     * <em>Selection Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Selection Description</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription
     * @generated
     */
    EClass getSelectionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.SelectionDescription#getCandidatesExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#isMultiple
     * <em>Multiple</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription#isMultiple()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Multiple();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#isTree
     * <em>Tree</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tree</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription#isTree()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Tree();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#getRootExpression
     * <em>Root Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription#getRootExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_RootExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#getChildrenExpression
     * <em>Children Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription#getChildrenExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_ChildrenExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SelectionDescription#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see org.eclipse.sirius.description.SelectionDescription#getMessage()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Message();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ColorDescription
     * <em>Color Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Color Description</em>'.
     * @see org.eclipse.sirius.description.ColorDescription
     * @generated
     */
    EClass getColorDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.SystemColor
     * <em>System Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>System Color</em>'.
     * @see org.eclipse.sirius.description.SystemColor
     * @generated
     */
    EClass getSystemColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.SystemColor#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.SystemColor#getName()
     * @see #getSystemColor()
     * @generated
     */
    EAttribute getSystemColor_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.InterpolatedColor
     * <em>Interpolated Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Interpolated Color</em>'.
     * @see org.eclipse.sirius.description.InterpolatedColor
     * @generated
     */
    EClass getInterpolatedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.InterpolatedColor#getColorValueComputationExpression
     * <em>Color Value Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Color Value Computation Expression</em>'.
     * @see org.eclipse.sirius.description.InterpolatedColor#getColorValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_ColorValueComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.InterpolatedColor#getMinValueComputationExpression
     * <em>Min Value Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Min Value Computation Expression</em>'.
     * @see org.eclipse.sirius.description.InterpolatedColor#getMinValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_MinValueComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.InterpolatedColor#getMaxValueComputationExpression
     * <em>Max Value Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Max Value Computation Expression</em>'.
     * @see org.eclipse.sirius.description.InterpolatedColor#getMaxValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_MaxValueComputationExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.InterpolatedColor#getColorSteps
     * <em>Color Steps</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Color Steps</em>'.
     * @see org.eclipse.sirius.description.InterpolatedColor#getColorSteps()
     * @see #getInterpolatedColor()
     * @generated
     */
    EReference getInterpolatedColor_ColorSteps();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ColorStep <em>Color Step</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Color Step</em>'.
     * @see org.eclipse.sirius.description.ColorStep
     * @generated
     */
    EClass getColorStep();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ColorStep#getAssociatedValue
     * <em>Associated Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Associated Value</em>'.
     * @see org.eclipse.sirius.description.ColorStep#getAssociatedValue()
     * @see #getColorStep()
     * @generated
     */
    EAttribute getColorStep_AssociatedValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.ColorStep#getAssociatedColor
     * <em>Associated Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Associated Color</em>'.
     * @see org.eclipse.sirius.description.ColorStep#getAssociatedColor()
     * @see #getColorStep()
     * @generated
     */
    EReference getColorStep_AssociatedColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.FixedColor <em>Fixed Color</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Fixed Color</em>'.
     * @see org.eclipse.sirius.description.FixedColor
     * @generated
     */
    EClass getFixedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.FixedColor#getRed <em>Red</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.description.FixedColor#getRed()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.FixedColor#getGreen
     * <em>Green</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.description.FixedColor#getGreen()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.FixedColor#getBlue
     * <em>Blue</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.description.FixedColor#getBlue()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.UserFixedColor
     * <em>User Fixed Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>User Fixed Color</em>'.
     * @see org.eclipse.sirius.description.UserFixedColor
     * @generated
     */
    EClass getUserFixedColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.UserColor <em>User Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Color</em>'.
     * @see org.eclipse.sirius.description.UserColor
     * @generated
     */
    EClass getUserColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.UserColor#getName <em>Name</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.UserColor#getName()
     * @see #getUserColor()
     * @generated
     */
    EAttribute getUserColor_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.Environment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Environment</em>'.
     * @see org.eclipse.sirius.description.Environment
     * @generated
     */
    EClass getEnvironment();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.Environment#getSystemColors
     * <em>System Colors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>System Colors</em>'.
     * @see org.eclipse.sirius.description.Environment#getSystemColors()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_SystemColors();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.Environment#getDefaultTools
     * <em>Default Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Default Tools</em>'.
     * @see org.eclipse.sirius.description.Environment#getDefaultTools()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_DefaultTools();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.Environment#getLabelBorderStyles
     * <em>Label Border Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Label Border Styles</em>'.
     * @see org.eclipse.sirius.description.Environment#getLabelBorderStyles()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_LabelBorderStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.SytemColorsPalette
     * <em>Sytem Colors Palette</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Sytem Colors Palette</em>'.
     * @see org.eclipse.sirius.description.SytemColorsPalette
     * @generated
     */
    EClass getSytemColorsPalette();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.SytemColorsPalette#getEntries
     * <em>Entries</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Entries</em>'.
     * @see org.eclipse.sirius.description.SytemColorsPalette#getEntries()
     * @see #getSytemColorsPalette()
     * @generated
     */
    EReference getSytemColorsPalette_Entries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.UserColorsPalette
     * <em>User Colors Palette</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>User Colors Palette</em>'.
     * @see org.eclipse.sirius.description.UserColorsPalette
     * @generated
     */
    EClass getUserColorsPalette();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.UserColorsPalette#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.UserColorsPalette#getName()
     * @see #getUserColorsPalette()
     * @generated
     */
    EAttribute getUserColorsPalette_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.UserColorsPalette#getEntries
     * <em>Entries</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Entries</em>'.
     * @see org.eclipse.sirius.description.UserColorsPalette#getEntries()
     * @see #getUserColorsPalette()
     * @generated
     */
    EReference getUserColorsPalette_Entries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.AnnotationEntry
     * <em>Annotation Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Annotation Entry</em>'.
     * @see org.eclipse.sirius.description.AnnotationEntry
     * @generated
     */
    EClass getAnnotationEntry();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.AnnotationEntry#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.description.AnnotationEntry#getSource()
     * @see #getAnnotationEntry()
     * @generated
     */
    EAttribute getAnnotationEntry_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.AnnotationEntry#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.description.AnnotationEntry#getData()
     * @see #getAnnotationEntry()
     * @generated
     */
    EReference getAnnotationEntry_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.EndUserDocumentedElement
     * <em>End User Documented Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>End User Documented Element</em>'.
     * @see org.eclipse.sirius.description.EndUserDocumentedElement
     * @generated
     */
    EClass getEndUserDocumentedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.EndUserDocumentedElement#getEndUserDocumentation
     * <em>End User Documentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>End User Documentation</em>'.
     * @see org.eclipse.sirius.description.EndUserDocumentedElement#getEndUserDocumentation()
     * @see #getEndUserDocumentedElement()
     * @generated
     */
    EAttribute getEndUserDocumentedElement_EndUserDocumentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Identified Element</em>'.
     * @see org.eclipse.sirius.description.IdentifiedElement
     * @generated
     */
    EClass getIdentifiedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.IdentifiedElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.IdentifiedElement#getName()
     * @see #getIdentifiedElement()
     * @generated
     */
    EAttribute getIdentifiedElement_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.IdentifiedElement#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.description.IdentifiedElement#getLabel()
     * @see #getIdentifiedElement()
     * @generated
     */
    EAttribute getIdentifiedElement_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.ComputedColor
     * <em>Computed Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Computed Color</em>'.
     * @see org.eclipse.sirius.description.ComputedColor
     * @generated
     */
    EClass getComputedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ComputedColor#getRed
     * <em>Red</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.description.ComputedColor#getRed()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ComputedColor#getGreen
     * <em>Green</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.description.ComputedColor#getGreen()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.ComputedColor#getBlue
     * <em>Blue</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.description.ComputedColor#getBlue()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.DAnnotationEntry
     * <em>DAnnotation Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DAnnotation Entry</em>'.
     * @see org.eclipse.sirius.description.DAnnotationEntry
     * @generated
     */
    EClass getDAnnotationEntry();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.DAnnotationEntry#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.description.DAnnotationEntry#getSource()
     * @see #getDAnnotationEntry()
     * @generated
     */
    EAttribute getDAnnotationEntry_Source();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.description.DAnnotationEntry#getDetails
     * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Details</em>'.
     * @see org.eclipse.sirius.description.DAnnotationEntry#getDetails()
     * @see #getDAnnotationEntry()
     * @generated
     */
    EAttribute getDAnnotationEntry_Details();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.NavigationTargetType
     * <em>Navigation Target Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Navigation Target Type</em>'.
     * @see org.eclipse.sirius.description.NavigationTargetType
     * @generated
     */
    EEnum getNavigationTargetType();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.FoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.description.FoldingStyle
     * @generated
     */
    EEnum getFoldingStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.LayoutDirection
     * <em>Layout Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Layout Direction</em>'.
     * @see org.eclipse.sirius.description.LayoutDirection
     * @generated
     */
    EEnum getLayoutDirection();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.Position <em>Position</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Position</em>'.
     * @see org.eclipse.sirius.description.Position
     * @generated
     */
    EEnum getPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.SystemColors
     * <em>System Colors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>System Colors</em>'.
     * @see org.eclipse.sirius.description.SystemColors
     * @generated
     */
    EEnum getSystemColors();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Type Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Type Name</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     * @generated
     */
    EDataType getTypeName();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Interpreted Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for data type '<em>Interpreted Expression</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     * @generated
     */
    EDataType getInterpretedExpression();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Feature Name</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     * @generated
     */
    EDataType getFeatureName();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.emf.common.util.URI <em>URI</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>URI</em>'.
     * @see org.eclipse.emf.common.util.URI
     * @model instanceClass="org.eclipse.emf.common.util.URI"
     * @generated
     */
    EDataType getURI();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DescriptionFactory getDescriptionFactory();

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
         * {@link org.eclipse.sirius.description.Component
         * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.Component
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getComponent()
         * @generated
         */
        EClass COMPONENT = eINSTANCE.getComponent();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.RepresentationDescription
         * <em>Representation Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.RepresentationDescription
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationDescription()
         * @generated
         */
        EClass REPRESENTATION_DESCRIPTION = eINSTANCE.getRepresentationDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = eINSTANCE.getRepresentationDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Initialisation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__INITIALISATION = eINSTANCE.getRepresentationDescription_Initialisation();

        /**
         * The meta object literal for the '<em><b>Metamodel</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_DESCRIPTION__METAMODEL = eINSTANCE.getRepresentationDescription_Metamodel();

        /**
         * The meta object literal for the '<em><b>Show On Startup</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         * 
         * @since 2.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP = eINSTANCE.getRepresentationDescription_ShowOnStartup();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.RepresentationTemplateImpl
         * <em>Representation Template</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.RepresentationTemplateImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationTemplate()
         * @generated
         */
        EClass REPRESENTATION_TEMPLATE = eINSTANCE.getRepresentationTemplate();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_TEMPLATE__NAME = eINSTANCE.getRepresentationTemplate_Name();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS = eINSTANCE.getRepresentationTemplate_OwnedRepresentations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.RepresentationImportDescription
         * <em>Representation Import Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.RepresentationImportDescription
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationImportDescription()
         * @generated
         */
        EClass REPRESENTATION_IMPORT_DESCRIPTION = eINSTANCE.getRepresentationImportDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.RepresentationExtensionDescription
         * <em>Representation Extension Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.RepresentationExtensionDescription
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationExtensionDescription()
         * @generated
         */
        EClass REPRESENTATION_EXTENSION_DESCRIPTION = eINSTANCE.getRepresentationExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__NAME = eINSTANCE.getRepresentationExtensionDescription_Name();

        /**
         * The meta object literal for the '<em><b>Sirius URI</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI = eINSTANCE.getRepresentationExtensionDescription_SiriusURI();

        /**
         * The meta object literal for the '<em><b>Representation Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME = eINSTANCE.getRepresentationExtensionDescription_RepresentationName();

        /**
         * The meta object literal for the '<em><b>Metamodel</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL = eINSTANCE.getRepresentationExtensionDescription_Metamodel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DiagramDescriptionImpl
         * <em>Diagram Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DiagramDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramDescription()
         * @generated
         */
        EClass DIAGRAM_DESCRIPTION = eINSTANCE.getDiagramDescription();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__FILTERS = eINSTANCE.getDiagramDescription_Filters();

        /**
         * The meta object literal for the '<em><b>All Edge Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS = eINSTANCE.getDiagramDescription_AllEdgeMappings();

        /**
         * The meta object literal for the '
         * <em><b>All Activated Edge Mappings</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_ACTIVATED_EDGE_MAPPINGS = eINSTANCE.getDiagramDescription_AllActivatedEdgeMappings();

        /**
         * The meta object literal for the '<em><b>All Node Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS = eINSTANCE.getDiagramDescription_AllNodeMappings();

        /**
         * The meta object literal for the '
         * <em><b>All Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS = eINSTANCE.getDiagramDescription_AllContainerMappings();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__VALIDATION_SET = eINSTANCE.getDiagramDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Concerns</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__CONCERNS = eINSTANCE.getDiagramDescription_Concerns();

        /**
         * The meta object literal for the '<em><b>Information Sections</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__INFORMATION_SECTIONS = eINSTANCE.getDiagramDescription_InformationSections();

        /**
         * The meta object literal for the '<em><b>All Tools</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_TOOLS = eINSTANCE.getDiagramDescription_AllTools();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__DOMAIN_CLASS = eINSTANCE.getDiagramDescription_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION = eINSTANCE.getDiagramDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Default Concern</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DEFAULT_CONCERN = eINSTANCE.getDiagramDescription_DefaultConcern();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__ROOT_EXPRESSION = eINSTANCE.getDiagramDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Init</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__INIT = eINSTANCE.getDiagramDescription_Init();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__LAYOUT = eINSTANCE.getDiagramDescription_Layout();

        /**
         * The meta object literal for the '<em><b>Default Layer</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DEFAULT_LAYER = eINSTANCE.getDiagramDescription_DefaultLayer();

        /**
         * The meta object literal for the '<em><b>Additional Layers</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS = eINSTANCE.getDiagramDescription_AdditionalLayers();

        /**
         * The meta object literal for the '<em><b>All Layers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_LAYERS = eINSTANCE.getDiagramDescription_AllLayers();

        /**
         * The meta object literal for the '<em><b>All Activated Tools</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS = eINSTANCE.getDiagramDescription_AllActivatedTools();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__NODE_MAPPINGS = eINSTANCE.getDiagramDescription_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__EDGE_MAPPINGS = eINSTANCE.getDiagramDescription_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mapping Imports</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS = eINSTANCE.getDiagramDescription_EdgeMappingImports();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS = eINSTANCE.getDiagramDescription_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Reused Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__REUSED_MAPPINGS = eINSTANCE.getDiagramDescription_ReusedMappings();

        /**
         * The meta object literal for the '<em><b>Tool Section</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__TOOL_SECTION = eINSTANCE.getDiagramDescription_ToolSection();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__REUSED_TOOLS = eINSTANCE.getDiagramDescription_ReusedTools();

        /**
         * The meta object literal for the '<em><b>Enable Popup Bars</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         * 
         * @since 2.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS = eINSTANCE.getDiagramDescription_EnablePopupBars();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DiagramImportDescriptionImpl
         * <em>Diagram Import Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DiagramImportDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramImportDescription()
         * @generated
         */
        EClass DIAGRAM_IMPORT_DESCRIPTION = eINSTANCE.getDiagramImportDescription();

        /**
         * The meta object literal for the '<em><b>Imported Diagram</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM = eINSTANCE.getDiagramImportDescription_ImportedDiagram();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DiagramExtensionDescriptionImpl
         * <em>Diagram Extension Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DiagramExtensionDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramExtensionDescription()
         * @generated
         */
        EClass DIAGRAM_EXTENSION_DESCRIPTION = eINSTANCE.getDiagramExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Layers</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__LAYERS = eINSTANCE.getDiagramExtensionDescription_Layers();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET = eINSTANCE.getDiagramExtensionDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Concerns</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS = eINSTANCE.getDiagramExtensionDescription_Concerns();

        /**
         * The meta object literal for the '
         * <em><b>Diagram Initialisation</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION = eINSTANCE.getDiagramDescription_DiagramInitialisation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.SiriusImpl
         * <em>Sirius</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.impl.SiriusImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSirius()
         * @generated
         */
        EClass VIEWPOINT = eINSTANCE.getSirius();

        /**
         * The meta object literal for the '<em><b>Model File Extension</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__MODEL_FILE_EXTENSION = eINSTANCE.getSirius_ModelFileExtension();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__VALIDATION_SET = eINSTANCE.getSirius_ValidationSet();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_REPRESENTATIONS = eINSTANCE.getSirius_OwnedRepresentations();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representation Extensions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS = eINSTANCE.getSirius_OwnedRepresentationExtensions();

        /**
         * The meta object literal for the '
         * <em><b>Owned Java Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_JAVA_EXTENSIONS = eINSTANCE.getSirius_OwnedJavaExtensions();

        /**
         * The meta object literal for the '<em><b>Owned MM Extensions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_MM_EXTENSIONS = eINSTANCE.getSirius_OwnedMMExtensions();

        /**
         * The meta object literal for the '
         * <em><b>Owned Feature Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_FEATURE_EXTENSIONS = eINSTANCE.getSirius_OwnedFeatureExtensions();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__ICON = eINSTANCE.getSirius_Icon();

        /**
         * The meta object literal for the '<em><b>Owned Templates</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_TEMPLATES = eINSTANCE.getSirius_OwnedTemplates();

        /**
         * The meta object literal for the '<em><b>Conflicts</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__CONFLICTS = eINSTANCE.getSirius_Conflicts();

        /**
         * The meta object literal for the '<em><b>Reuses</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__REUSES = eINSTANCE.getSirius_Reuses();

        /**
         * The meta object literal for the '<em><b>Customizes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__CUSTOMIZES = eINSTANCE.getSirius_Customizes();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.FeatureExtensionDescription
         * <em>Feature Extension Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.FeatureExtensionDescription
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFeatureExtensionDescription()
         * @generated
         */
        EClass FEATURE_EXTENSION_DESCRIPTION = eINSTANCE.getFeatureExtensionDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.MetamodelExtensionSettingImpl
         * <em>Metamodel Extension Setting</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.MetamodelExtensionSettingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getMetamodelExtensionSetting()
         * @generated
         */
        EClass METAMODEL_EXTENSION_SETTING = eINSTANCE.getMetamodelExtensionSetting();

        /**
         * The meta object literal for the '<em><b>Extension Group</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METAMODEL_EXTENSION_SETTING__EXTENSION_GROUP = eINSTANCE.getMetamodelExtensionSetting_ExtensionGroup();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.JavaExtensionImpl
         * <em>Java Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.JavaExtensionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getJavaExtension()
         * @generated
         */
        EClass JAVA_EXTENSION = eINSTANCE.getJavaExtension();

        /**
         * The meta object literal for the '<em><b>Qualified Class Name</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute JAVA_EXTENSION__QUALIFIED_CLASS_NAME = eINSTANCE.getJavaExtension_QualifiedClassName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.RepresentationElementMappingImpl
         * <em>Representation Element Mapping</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.RepresentationElementMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getRepresentationElementMapping()
         * @generated
         */
        EClass REPRESENTATION_ELEMENT_MAPPING = eINSTANCE.getRepresentationElementMapping();

        /**
         * The meta object literal for the '<em><b>Detail Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = eINSTANCE.getRepresentationElementMapping_DetailDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>Navigation Descriptions</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = eINSTANCE.getRepresentationElementMapping_NavigationDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DiagramElementMappingImpl
         * <em>Diagram Element Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DiagramElementMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDiagramElementMapping()
         * @generated
         */
        EClass DIAGRAM_ELEMENT_MAPPING = eINSTANCE.getDiagramElementMapping();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION = eINSTANCE.getDiagramElementMapping_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Deletion Description</b></em>
         * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION = eINSTANCE.getDiagramElementMapping_DeletionDescription();

        /**
         * The meta object literal for the '<em><b>Label Direct Edit</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT = eINSTANCE.getDiagramElementMapping_LabelDirectEdit();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidates Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Create Elements</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS = eINSTANCE.getDiagramElementMapping_CreateElements();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS = eINSTANCE.getDiagramElementMapping_SemanticElements();

        /**
         * The meta object literal for the '
         * <em><b>Double Click Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION = eINSTANCE.getDiagramElementMapping_DoubleClickDescription();

        /**
         * The meta object literal for the '<em><b>Synchronization Lock</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK = eINSTANCE.getDiagramElementMapping_SynchronizationLock();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.AbstractNodeMappingImpl
         * <em>Abstract Node Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.AbstractNodeMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAbstractNodeMapping()
         * @generated
         */
        EClass ABSTRACT_NODE_MAPPING = eINSTANCE.getAbstractNodeMapping();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_NODE_MAPPING__DOMAIN_CLASS = eINSTANCE.getAbstractNodeMapping_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Bordered Node Mappings</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS = eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings();

        /**
         * The meta object literal for the '
         * <em><b>Reused Bordered Node Mappings</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = eINSTANCE.getAbstractNodeMapping_ReusedBorderedNodeMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.NodeMappingImpl
         * <em>Node Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.NodeMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNodeMapping()
         * @generated
         */
        EClass NODE_MAPPING = eINSTANCE.getNodeMapping();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_MAPPING__STYLE = eINSTANCE.getNodeMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_MAPPING__CONDITIONNAL_STYLES = eINSTANCE.getNodeMapping_ConditionnalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.NodeMappingImportImpl
         * <em>Node Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.NodeMappingImportImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNodeMappingImport()
         * @generated
         */
        EClass NODE_MAPPING_IMPORT = eINSTANCE.getNodeMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_MAPPING_IMPORT__IMPORTED_MAPPING = eINSTANCE.getNodeMappingImport_ImportedMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ContainerMappingImportImpl
         * <em>Container Mapping Import</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ContainerMappingImportImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getContainerMappingImport()
         * @generated
         */
        EClass CONTAINER_MAPPING_IMPORT = eINSTANCE.getContainerMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING = eINSTANCE.getContainerMappingImport_ImportedMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ContainerMappingImpl
         * <em>Container Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ContainerMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getContainerMapping()
         * @generated
         */
        EClass CONTAINER_MAPPING = eINSTANCE.getContainerMapping();

        /**
         * The meta object literal for the '<em><b>Sub Node Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__SUB_NODE_MAPPINGS = eINSTANCE.getContainerMapping_SubNodeMappings();

        /**
         * The meta object literal for the '<em><b>All Node Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__ALL_NODE_MAPPINGS = eINSTANCE.getContainerMapping_AllNodeMappings();

        /**
         * The meta object literal for the '<em><b>Reused Node Mappings</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__REUSED_NODE_MAPPINGS = eINSTANCE.getContainerMapping_ReusedNodeMappings();

        /**
         * The meta object literal for the '
         * <em><b>Sub Container Mappings</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS = eINSTANCE.getContainerMapping_SubContainerMappings();

        /**
         * The meta object literal for the '
         * <em><b>Reused Container Mappings</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS = eINSTANCE.getContainerMapping_ReusedContainerMappings();

        /**
         * The meta object literal for the '
         * <em><b>All Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS = eINSTANCE.getContainerMapping_AllContainerMappings();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__STYLE = eINSTANCE.getContainerMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MAPPING__CONDITIONNAL_STYLES = eINSTANCE.getContainerMapping_ConditionnalStyles();

        /**
         * The meta object literal for the '
         * <em><b>Children Presentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_MAPPING__CHILDREN_PRESENTATION = eINSTANCE.getContainerMapping_ChildrenPresentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.AbstractMappingImportImpl
         * <em>Abstract Mapping Import</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.AbstractMappingImportImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAbstractMappingImport()
         * @generated
         */
        EClass ABSTRACT_MAPPING_IMPORT = eINSTANCE.getAbstractMappingImport();

        /**
         * The meta object literal for the '<em><b>Hide Sub Mappings</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = eINSTANCE.getAbstractMappingImport_HideSubMappings();

        /**
         * The meta object literal for the '
         * <em><b>Inherits Ancestor Filters</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = eINSTANCE.getAbstractMappingImport_InheritsAncestorFilters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EdgeMappingImpl
         * <em>Edge Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EdgeMappingImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEdgeMapping()
         * @generated
         */
        EClass EDGE_MAPPING = eINSTANCE.getEdgeMapping();

        /**
         * The meta object literal for the '<em><b>Source Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__SOURCE_MAPPING = eINSTANCE.getEdgeMapping_SourceMapping();

        /**
         * The meta object literal for the '<em><b>Target Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__TARGET_MAPPING = eINSTANCE.getEdgeMapping_TargetMapping();

        /**
         * The meta object literal for the '
         * <em><b>Target Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__TARGET_FINDER_EXPRESSION = eINSTANCE.getEdgeMapping_TargetFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Source Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__SOURCE_FINDER_EXPRESSION = eINSTANCE.getEdgeMapping_SourceFinderExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__STYLE = eINSTANCE.getEdgeMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__CONDITIONNAL_STYLES = eINSTANCE.getEdgeMapping_ConditionnalStyles();

        /**
         * The meta object literal for the '<em><b>Target Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__TARGET_EXPRESSION = eINSTANCE.getEdgeMapping_TargetExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__DOMAIN_CLASS = eINSTANCE.getEdgeMapping_DomainClass();

        /**
         * The meta object literal for the '<em><b>Use Domain Element</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__USE_DOMAIN_ELEMENT = eINSTANCE.getEdgeMapping_UseDomainElement();

        /**
         * The meta object literal for the '<em><b>Reconnections</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__RECONNECTIONS = eINSTANCE.getEdgeMapping_Reconnections();

        /**
         * The meta object literal for the '<em><b>Path Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING__PATH_EXPRESSION = eINSTANCE.getEdgeMapping_PathExpression();

        /**
         * The meta object literal for the '<em><b>Path Node Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING__PATH_NODE_MAPPING = eINSTANCE.getEdgeMapping_PathNodeMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.IEdgeMapping
         * <em>IEdge Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.IEdgeMapping
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIEdgeMapping()
         * @generated
         */
        EClass IEDGE_MAPPING = eINSTANCE.getIEdgeMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EdgeMappingImportImpl
         * <em>Edge Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EdgeMappingImportImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEdgeMappingImport()
         * @generated
         */
        EClass EDGE_MAPPING_IMPORT = eINSTANCE.getEdgeMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING_IMPORT__IMPORTED_MAPPING = eINSTANCE.getEdgeMappingImport_ImportedMapping();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES = eINSTANCE.getEdgeMappingImport_ConditionnalStyles();

        /**
         * The meta object literal for the '
         * <em><b>Inherits Ancestor Filters</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = eINSTANCE.getEdgeMappingImport_InheritsAncestorFilters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.GroupImpl
         * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.GroupImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getGroup()
         * @generated
         */
        EClass GROUP = eINSTANCE.getGroup();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP__NAME = eINSTANCE.getGroup_Name();

        /**
         * The meta object literal for the '<em><b>Owned Siriuss</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP__OWNED_VIEWPOINTS = eINSTANCE.getGroup_OwnedSiriuss();

        /**
         * The meta object literal for the '
         * <em><b>System Colors Palette</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP__SYSTEM_COLORS_PALETTE = eINSTANCE.getGroup_SystemColorsPalette();

        /**
         * The meta object literal for the '<em><b>User Colors Palettes</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP__USER_COLORS_PALETTES = eINSTANCE.getGroup_UserColorsPalettes();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP__VERSION = eINSTANCE.getGroup_Version();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DocumentedElementImpl
         * <em>Documented Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DocumentedElementImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDocumentedElement()
         * @generated
         */
        EClass DOCUMENTED_ELEMENT = eINSTANCE.getDocumentedElement();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOCUMENTED_ELEMENT__DOCUMENTATION = eINSTANCE.getDocumentedElement_Documentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DModelElementImpl
         * <em>DModel Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DModelElementImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDModelElement()
         * @generated
         */
        EClass DMODEL_ELEMENT = eINSTANCE.getDModelElement();

        /**
         * The meta object literal for the '<em><b>EAnnotations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DMODEL_ELEMENT__EANNOTATIONS = eINSTANCE.getDModelElement_EAnnotations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DAnnotationImpl
         * <em>DAnnotation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DAnnotationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDAnnotation()
         * @generated
         */
        EClass DANNOTATION = eINSTANCE.getDAnnotation();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION__SOURCE = eINSTANCE.getDAnnotation_Source();

        /**
         * The meta object literal for the '<em><b>Details</b></em>' map
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANNOTATION__DETAILS = eINSTANCE.getDAnnotation_Details();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ConditionalStyleDescriptionImpl
         * <em>Conditional Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ConditionalStyleDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_STYLE_DESCRIPTION = eINSTANCE.getConditionalStyleDescription();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = eINSTANCE.getConditionalStyleDescription_PredicateExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ConditionalNodeStyleDescriptionImpl
         * <em>Conditional Node Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ConditionalNodeStyleDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalNodeStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_NODE_STYLE_DESCRIPTION = eINSTANCE.getConditionalNodeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE = eINSTANCE.getConditionalNodeStyleDescription_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ConditionalEdgeStyleDescriptionImpl
         * <em>Conditional Edge Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ConditionalEdgeStyleDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalEdgeStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_EDGE_STYLE_DESCRIPTION = eINSTANCE.getConditionalEdgeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE = eINSTANCE.getConditionalEdgeStyleDescription_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ConditionalContainerStyleDescriptionImpl
         * <em>Conditional Container Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ConditionalContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getConditionalContainerStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_CONTAINER_STYLE_DESCRIPTION = eINSTANCE.getConditionalContainerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE = eINSTANCE.getConditionalContainerStyleDescription_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DragAndDropTargetDescriptionImpl
         * <em>Drag And Drop Target Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DragAndDropTargetDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDragAndDropTargetDescription()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET_DESCRIPTION = eINSTANCE.getDragAndDropTargetDescription();

        /**
         * The meta object literal for the '<em><b>Drop Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS = eINSTANCE.getDragAndDropTargetDescription_DropDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.PasteTargetDescriptionImpl
         * <em>Paste Target Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.PasteTargetDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getPasteTargetDescription()
         * @generated
         */
        EClass PASTE_TARGET_DESCRIPTION = eINSTANCE.getPasteTargetDescription();

        /**
         * The meta object literal for the '<em><b>Paste Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS = eINSTANCE.getPasteTargetDescription_PasteDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.Layout <em>Layout</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.Layout
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayout()
         * @generated
         */
        EClass LAYOUT = eINSTANCE.getLayout();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.OrderedTreeLayoutImpl
         * <em>Ordered Tree Layout</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.OrderedTreeLayoutImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getOrderedTreeLayout()
         * @generated
         */
        EClass ORDERED_TREE_LAYOUT = eINSTANCE.getOrderedTreeLayout();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION = eINSTANCE.getOrderedTreeLayout_ChildrenExpression();

        /**
         * The meta object literal for the '<em><b>Node Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ORDERED_TREE_LAYOUT__NODE_MAPPING = eINSTANCE.getOrderedTreeLayout_NodeMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.CompositeLayoutImpl
         * <em>Composite Layout</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.CompositeLayoutImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getCompositeLayout()
         * @generated
         */
        EClass COMPOSITE_LAYOUT = eINSTANCE.getCompositeLayout();

        /**
         * The meta object literal for the '<em><b>Padding</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPOSITE_LAYOUT__PADDING = eINSTANCE.getCompositeLayout_Padding();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPOSITE_LAYOUT__DIRECTION = eINSTANCE.getCompositeLayout_Direction();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DecorationDescriptionsSetImpl
         * <em>Decoration Descriptions Set</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DecorationDescriptionsSetImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDecorationDescriptionsSet()
         * @generated
         */
        EClass DECORATION_DESCRIPTIONS_SET = eINSTANCE.getDecorationDescriptionsSet();

        /**
         * The meta object literal for the '
         * <em><b>Decoration Descriptions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS = eINSTANCE.getDecorationDescriptionsSet_DecorationDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DecorationDescriptionImpl
         * <em>Decoration Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DecorationDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDecorationDescription()
         * @generated
         */
        EClass DECORATION_DESCRIPTION = eINSTANCE.getDecorationDescription();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__NAME = eINSTANCE.getDecorationDescription_Name();

        /**
         * The meta object literal for the '<em><b>Position</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__POSITION = eINSTANCE.getDecorationDescription_Position();

        /**
         * The meta object literal for the '<em><b>Decorator Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__DECORATOR_PATH = eINSTANCE.getDecorationDescription_DecoratorPath();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION = eINSTANCE.getDecorationDescription_PreconditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.MappingBasedDecorationImpl
         * <em>Mapping Based Decoration</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.MappingBasedDecorationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getMappingBasedDecoration()
         * @generated
         */
        EClass MAPPING_BASED_DECORATION = eINSTANCE.getMappingBasedDecoration();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MAPPING_BASED_DECORATION__MAPPINGS = eINSTANCE.getMappingBasedDecoration_Mappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.SemanticBasedDecorationImpl
         * <em>Semantic Based Decoration</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.SemanticBasedDecorationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSemanticBasedDecoration()
         * @generated
         */
        EClass SEMANTIC_BASED_DECORATION = eINSTANCE.getSemanticBasedDecoration();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SEMANTIC_BASED_DECORATION__DOMAIN_CLASS = eINSTANCE.getSemanticBasedDecoration_DomainClass();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.LayerImpl
         * <em>Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.LayerImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayer()
         * @generated
         */
        EClass LAYER = eINSTANCE.getLayer();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__NODE_MAPPINGS = eINSTANCE.getLayer_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__EDGE_MAPPINGS = eINSTANCE.getLayer_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mapping Imports</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__EDGE_MAPPING_IMPORTS = eINSTANCE.getLayer_EdgeMappingImports();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__CONTAINER_MAPPINGS = eINSTANCE.getLayer_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Reused Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__REUSED_MAPPINGS = eINSTANCE.getLayer_ReusedMappings();

        /**
         * The meta object literal for the '<em><b>All Tools</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__ALL_TOOLS = eINSTANCE.getLayer_AllTools();

        /**
         * The meta object literal for the '<em><b>Tool Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__TOOL_SECTIONS = eINSTANCE.getLayer_ToolSections();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__REUSED_TOOLS = eINSTANCE.getLayer_ReusedTools();

        /**
         * The meta object literal for the '
         * <em><b>Decoration Descriptions Set</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__DECORATION_DESCRIPTIONS_SET = eINSTANCE.getLayer_DecorationDescriptionsSet();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LAYER__ICON = eINSTANCE.getLayer_Icon();

        /**
         * The meta object literal for the '<em><b>All Edge Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__ALL_EDGE_MAPPINGS = eINSTANCE.getLayer_AllEdgeMappings();

        /**
         * The meta object literal for the '
         * <em><b>All Activated Edge Mappings</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__ALL_ACTIVATED_EDGE_MAPPINGS = eINSTANCE.getLayer_AllActivatedEdgeMappings();

        /**
         * The meta object literal for the '<em><b>Customization</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LAYER__CUSTOMIZATION = eINSTANCE.getLayer_Customization();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.AdditionalLayerImpl
         * <em>Additional Layer</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.AdditionalLayerImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAdditionalLayer()
         * @generated
         */
        EClass ADDITIONAL_LAYER = eINSTANCE.getAdditionalLayer();

        /**
         * The meta object literal for the '<em><b>Active By Default</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT = eINSTANCE.getAdditionalLayer_ActiveByDefault();

        /**
         * The meta object literal for the '<em><b>Optional</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ADDITIONAL_LAYER__OPTIONAL = eINSTANCE.getAdditionalLayer_Optional();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.CustomizationImpl
         * <em>Customization</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.CustomizationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getCustomization()
         * @generated
         */
        EClass CUSTOMIZATION = eINSTANCE.getCustomization();

        /**
         * The meta object literal for the '
         * <em><b>Vsm Element Customizations</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS = eINSTANCE.getCustomization_VsmElementCustomizations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.IVSMElementCustomization
         * <em>IVSM Element Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.IVSMElementCustomization
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIVSMElementCustomization()
         * @generated
         */
        EClass IVSM_ELEMENT_CUSTOMIZATION = eINSTANCE.getIVSMElementCustomization();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.VSMElementCustomizationImpl
         * <em>VSM Element Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.VSMElementCustomizationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getVSMElementCustomization()
         * @generated
         */
        EClass VSM_ELEMENT_CUSTOMIZATION = eINSTANCE.getVSMElementCustomization();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION = eINSTANCE.getVSMElementCustomization_PredicateExpression();

        /**
         * The meta object literal for the '
         * <em><b>Feature Customizations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS = eINSTANCE.getVSMElementCustomization_FeatureCustomizations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.VSMElementCustomizationReuseImpl
         * <em>VSM Element Customization Reuse</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.VSMElementCustomizationReuseImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getVSMElementCustomizationReuse()
         * @generated
         */
        EClass VSM_ELEMENT_CUSTOMIZATION_REUSE = eINSTANCE.getVSMElementCustomizationReuse();

        /**
         * The meta object literal for the '<em><b>Reuse</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE = eINSTANCE.getVSMElementCustomizationReuse_Reuse();

        /**
         * The meta object literal for the '<em><b>Applied On</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON = eINSTANCE.getVSMElementCustomizationReuse_AppliedOn();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EStructuralFeatureCustomizationImpl
         * <em>EStructural Feature Customization</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EStructuralFeatureCustomizationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEStructuralFeatureCustomization()
         * @generated
         */
        EClass ESTRUCTURAL_FEATURE_CUSTOMIZATION = eINSTANCE.getEStructuralFeatureCustomization();

        /**
         * The meta object literal for the '<em><b>Applied On</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON = eINSTANCE.getEStructuralFeatureCustomization_AppliedOn();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EAttributeCustomizationImpl
         * <em>EAttribute Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EAttributeCustomizationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEAttributeCustomization()
         * @generated
         */
        EClass EATTRIBUTE_CUSTOMIZATION = eINSTANCE.getEAttributeCustomization();

        /**
         * The meta object literal for the '<em><b>Attribute Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME = eINSTANCE.getEAttributeCustomization_AttributeName();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EATTRIBUTE_CUSTOMIZATION__VALUE = eINSTANCE.getEAttributeCustomization_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EReferenceCustomizationImpl
         * <em>EReference Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EReferenceCustomizationImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEReferenceCustomization()
         * @generated
         */
        EClass EREFERENCE_CUSTOMIZATION = eINSTANCE.getEReferenceCustomization();

        /**
         * The meta object literal for the '<em><b>Reference Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EREFERENCE_CUSTOMIZATION__REFERENCE_NAME = eINSTANCE.getEReferenceCustomization_ReferenceName();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EREFERENCE_CUSTOMIZATION__VALUE = eINSTANCE.getEReferenceCustomization_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.SelectionDescription
         * <em>Selection Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.SelectionDescription
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSelectionDescription()
         * @generated
         */
        EClass SELECTION_DESCRIPTION = eINSTANCE.getSelectionDescription();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION = eINSTANCE.getSelectionDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__MULTIPLE = eINSTANCE.getSelectionDescription_Multiple();

        /**
         * The meta object literal for the '<em><b>Tree</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__TREE = eINSTANCE.getSelectionDescription_Tree();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__ROOT_EXPRESSION = eINSTANCE.getSelectionDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__CHILDREN_EXPRESSION = eINSTANCE.getSelectionDescription_ChildrenExpression();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__MESSAGE = eINSTANCE.getSelectionDescription_Message();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ColorDescriptionImpl
         * <em>Color Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ColorDescriptionImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getColorDescription()
         * @generated
         */
        EClass COLOR_DESCRIPTION = eINSTANCE.getColorDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.SystemColorImpl
         * <em>System Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.SystemColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSystemColor()
         * @generated
         */
        EClass SYSTEM_COLOR = eINSTANCE.getSystemColor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SYSTEM_COLOR__NAME = eINSTANCE.getSystemColor_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.InterpolatedColorImpl
         * <em>Interpolated Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.InterpolatedColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getInterpolatedColor()
         * @generated
         */
        EClass INTERPOLATED_COLOR = eINSTANCE.getInterpolatedColor();

        /**
         * The meta object literal for the '
         * <em><b>Color Value Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION = eINSTANCE.getInterpolatedColor_ColorValueComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Min Value Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION = eINSTANCE.getInterpolatedColor_MinValueComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Max Value Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION = eINSTANCE.getInterpolatedColor_MaxValueComputationExpression();

        /**
         * The meta object literal for the '<em><b>Color Steps</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INTERPOLATED_COLOR__COLOR_STEPS = eINSTANCE.getInterpolatedColor_ColorSteps();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ColorStepImpl
         * <em>Color Step</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ColorStepImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getColorStep()
         * @generated
         */
        EClass COLOR_STEP = eINSTANCE.getColorStep();

        /**
         * The meta object literal for the '<em><b>Associated Value</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLOR_STEP__ASSOCIATED_VALUE = eINSTANCE.getColorStep_AssociatedValue();

        /**
         * The meta object literal for the '<em><b>Associated Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COLOR_STEP__ASSOCIATED_COLOR = eINSTANCE.getColorStep_AssociatedColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.FixedColorImpl
         * <em>Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.FixedColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFixedColor()
         * @generated
         */
        EClass FIXED_COLOR = eINSTANCE.getFixedColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__RED = eINSTANCE.getFixedColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__GREEN = eINSTANCE.getFixedColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__BLUE = eINSTANCE.getFixedColor_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.UserFixedColorImpl
         * <em>User Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.UserFixedColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserFixedColor()
         * @generated
         */
        EClass USER_FIXED_COLOR = eINSTANCE.getUserFixedColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.UserColorImpl
         * <em>User Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.UserColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserColor()
         * @generated
         */
        EClass USER_COLOR = eINSTANCE.getUserColor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_COLOR__NAME = eINSTANCE.getUserColor_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EnvironmentImpl
         * <em>Environment</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EnvironmentImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEnvironment()
         * @generated
         */
        EClass ENVIRONMENT = eINSTANCE.getEnvironment();

        /**
         * The meta object literal for the '<em><b>System Colors</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__SYSTEM_COLORS = eINSTANCE.getEnvironment_SystemColors();

        /**
         * The meta object literal for the '<em><b>Default Tools</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__DEFAULT_TOOLS = eINSTANCE.getEnvironment_DefaultTools();

        /**
         * The meta object literal for the '<em><b>Label Border Styles</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__LABEL_BORDER_STYLES = eINSTANCE.getEnvironment_LabelBorderStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.SytemColorsPaletteImpl
         * <em>Sytem Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.SytemColorsPaletteImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSytemColorsPalette()
         * @generated
         */
        EClass SYTEM_COLORS_PALETTE = eINSTANCE.getSytemColorsPalette();

        /**
         * The meta object literal for the '<em><b>Entries</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SYTEM_COLORS_PALETTE__ENTRIES = eINSTANCE.getSytemColorsPalette_Entries();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.UserColorsPaletteImpl
         * <em>User Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.UserColorsPaletteImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getUserColorsPalette()
         * @generated
         */
        EClass USER_COLORS_PALETTE = eINSTANCE.getUserColorsPalette();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_COLORS_PALETTE__NAME = eINSTANCE.getUserColorsPalette_Name();

        /**
         * The meta object literal for the '<em><b>Entries</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER_COLORS_PALETTE__ENTRIES = eINSTANCE.getUserColorsPalette_Entries();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.AnnotationEntryImpl
         * <em>Annotation Entry</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.AnnotationEntryImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getAnnotationEntry()
         * @generated
         */
        EClass ANNOTATION_ENTRY = eINSTANCE.getAnnotationEntry();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ANNOTATION_ENTRY__SOURCE = eINSTANCE.getAnnotationEntry_Source();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ANNOTATION_ENTRY__DATA = eINSTANCE.getAnnotationEntry_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.EndUserDocumentedElementImpl
         * <em>End User Documented Element</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.EndUserDocumentedElementImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getEndUserDocumentedElement()
         * @generated
         */
        EClass END_USER_DOCUMENTED_ELEMENT = eINSTANCE.getEndUserDocumentedElement();

        /**
         * The meta object literal for the '
         * <em><b>End User Documentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION = eINSTANCE.getEndUserDocumentedElement_EndUserDocumentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.IdentifiedElementImpl
         * <em>Identified Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.IdentifiedElementImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getIdentifiedElement()
         * @generated
         */
        EClass IDENTIFIED_ELEMENT = eINSTANCE.getIdentifiedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IDENTIFIED_ELEMENT__NAME = eINSTANCE.getIdentifiedElement_Name();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IDENTIFIED_ELEMENT__LABEL = eINSTANCE.getIdentifiedElement_Label();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.ComputedColorImpl
         * <em>Computed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.ComputedColorImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getComputedColor()
         * @generated
         */
        EClass COMPUTED_COLOR = eINSTANCE.getComputedColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__RED = eINSTANCE.getComputedColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__GREEN = eINSTANCE.getComputedColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__BLUE = eINSTANCE.getComputedColor_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.impl.DAnnotationEntryImpl
         * <em>DAnnotation Entry</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.impl.DAnnotationEntryImpl
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getDAnnotationEntry()
         * @generated
         */
        EClass DANNOTATION_ENTRY = eINSTANCE.getDAnnotationEntry();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION_ENTRY__SOURCE = eINSTANCE.getDAnnotationEntry_Source();

        /**
         * The meta object literal for the '<em><b>Details</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION_ENTRY__DETAILS = eINSTANCE.getDAnnotationEntry_Details();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.NavigationTargetType
         * <em>Navigation Target Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.NavigationTargetType
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getNavigationTargetType()
         * @generated
         */
        EEnum NAVIGATION_TARGET_TYPE = eINSTANCE.getNavigationTargetType();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.FoldingStyle
         * <em>Folding Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.FoldingStyle
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFoldingStyle()
         * @generated
         */
        EEnum FOLDING_STYLE = eINSTANCE.getFoldingStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.LayoutDirection
         * <em>Layout Direction</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.LayoutDirection
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getLayoutDirection()
         * @generated
         */
        EEnum LAYOUT_DIRECTION = eINSTANCE.getLayoutDirection();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.Position <em>Position</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.Position
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getPosition()
         * @generated
         */
        EEnum POSITION = eINSTANCE.getPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.SystemColors
         * <em>System Colors</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.SystemColors
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getSystemColors()
         * @generated
         */
        EEnum SYSTEM_COLORS = eINSTANCE.getSystemColors();

        /**
         * The meta object literal for the '<em>Type Name</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.lang.String
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getTypeName()
         * @generated
         */
        EDataType TYPE_NAME = eINSTANCE.getTypeName();

        /**
         * The meta object literal for the '<em>Interpreted Expression</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.lang.String
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getInterpretedExpression()
         * @generated
         */
        EDataType INTERPRETED_EXPRESSION = eINSTANCE.getInterpretedExpression();

        /**
         * The meta object literal for the '<em>Feature Name</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.lang.String
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getFeatureName()
         * @generated
         */
        EDataType FEATURE_NAME = eINSTANCE.getFeatureName();

        /**
         * The meta object literal for the '<em>URI</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.emf.common.util.URI
         * @see org.eclipse.sirius.description.impl.DescriptionPackageImpl#getURI()
         * @generated
         */
        EDataType URI = eINSTANCE.getURI();

    }

} // DescriptionPackage
