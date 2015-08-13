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
package org.eclipse.sirius.viewpoint.description;

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
 * @see org.eclipse.sirius.viewpoint.description.DescriptionFactory
 * @model kind="package"
 * @generated
 */
public interface DescriptionPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "description"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "description"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DescriptionPackage eINSTANCE = org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.DModelElementImpl
     * <em>DModel Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DModelElementImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDModelElement()
     * @generated
     */
    int DMODEL_ELEMENT = 13;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl
     * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.GroupImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getGroup()
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
    int GROUP__EANNOTATIONS = DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP__DOCUMENTATION = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP__NAME = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Viewpoints</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__OWNED_VIEWPOINTS = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>System Colors Palette</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__SYSTEM_COLORS_PALETTE = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>User Colors Palettes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP__USER_COLORS_PALETTES = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP__VERSION = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Group</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_FEATURE_COUNT = DescriptionPackage.DMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.Component
     * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.Component
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 1;

    /**
     * The number of structural features of the '<em>Component</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl
     * <em>Documented Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDocumentedElement()
     * @generated
     */
    int DOCUMENTED_ELEMENT = 12;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl
     * <em>Viewpoint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getViewpoint()
     * @generated
     */
    int VIEWPOINT = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__END_USER_DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Model File Extension</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__MODEL_FILE_EXTENSION = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__VALIDATION_SET = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_REPRESENTATIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Owned Representation Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Owned Java Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_JAVA_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Owned MM Extensions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_MM_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Owned Feature Extensions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_FEATURE_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__ICON = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Owned Templates</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEWPOINT__OWNED_TEMPLATES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Conflicts</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__CONFLICTS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Reuses</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__REUSES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Customizes</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT__CUSTOMIZES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>Viewpoint</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEWPOINT_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
     * <em>Feature Extension Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFeatureExtensionDescription()
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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * <em>Representation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationDescription()
     * @generated
     */
    int REPRESENTATION_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__INITIALISATION = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__METAMODEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '
     * <em>Representation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl
     * <em>Representation Template</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationTemplate()
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
     * <em>Representation Template</em>' class. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION_TEMPLATE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
     * <em>Representation Import Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationImportDescription()
     * @generated
     */
    int REPRESENTATION_IMPORT_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__DOCUMENTATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__NAME = DescriptionPackage.REPRESENTATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__LABEL = DescriptionPackage.REPRESENTATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__INITIALISATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__METAMODEL = DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The number of structural features of the '
     * <em>Representation Import Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
     * <em>Representation Extension Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationExtensionDescription()
     * @generated
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_EXTENSION_DESCRIPTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Viewpoint URI</b></em>' attribute. <!--
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
     * {@link org.eclipse.sirius.viewpoint.description.impl.MetamodelExtensionSettingImpl
     * <em>Metamodel Extension Setting</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.MetamodelExtensionSettingImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getMetamodelExtensionSetting()
     * @generated
     */
    int METAMODEL_EXTENSION_SETTING = 8;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.JavaExtensionImpl
     * <em>Java Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.JavaExtensionImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getJavaExtension()
     * @generated
     */
    int JAVA_EXTENSION = 9;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl
     * <em>Identified Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getIdentifiedElement()
     * @generated
     */
    int IDENTIFIED_ELEMENT = 40;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl
     * <em>Representation Element Mapping</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationElementMapping()
     * @generated
     */
    int REPRESENTATION_ELEMENT_MAPPING = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Representation Element Mapping</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.AbstractMappingImportImpl
     * <em>Abstract Mapping Import</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.AbstractMappingImportImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getAbstractMappingImport()
     * @generated
     */
    int ABSTRACT_MAPPING_IMPORT = 11;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl
     * <em>DAnnotation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDAnnotation()
     * @generated
     */
    int DANNOTATION = 14;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl
     * <em>Conditional Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getConditionalStyleDescription()
     * @generated
     */
    int CONDITIONAL_STYLE_DESCRIPTION = 15;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.PasteTargetDescriptionImpl
     * <em>Paste Target Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.PasteTargetDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getPasteTargetDescription()
     * @generated
     */
    int PASTE_TARGET_DESCRIPTION = 16;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionsSetImpl
     * <em>Decoration Descriptions Set</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionsSetImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDecorationDescriptionsSet()
     * @generated
     */
    int DECORATION_DESCRIPTIONS_SET = 17;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl
     * <em>Decoration Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDecorationDescription()
     * @generated
     */
    int DECORATION_DESCRIPTION = 18;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.SemanticBasedDecorationImpl
     * <em>Semantic Based Decoration</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.SemanticBasedDecorationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSemanticBasedDecoration()
     * @generated
     */
    int SEMANTIC_BASED_DECORATION = 19;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__NAME = DescriptionPackage.DECORATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__POSITION = DescriptionPackage.DECORATION_DESCRIPTION__POSITION;

    /**
     * The feature id for the '<em><b>Decorator Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__DECORATOR_PATH = DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__PRECONDITION_EXPRESSION = DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION__DOMAIN_CLASS = DescriptionPackage.DECORATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Semantic Based Decoration</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_BASED_DECORATION_FEATURE_COUNT = DescriptionPackage.DECORATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.CustomizationImpl
     * <em>Customization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.CustomizationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getCustomization()
     * @generated
     */
    int CUSTOMIZATION = 20;

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
     * {@link org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
     * <em>IVSM Element Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getIVSMElementCustomization()
     * @generated
     */
    int IVSM_ELEMENT_CUSTOMIZATION = 21;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl
     * <em>VSM Element Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getVSMElementCustomization()
     * @generated
     */
    int VSM_ELEMENT_CUSTOMIZATION = 22;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Feature Customizations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>VSM Element Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl
     * <em>VSM Element Customization Reuse</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getVSMElementCustomizationReuse()
     * @generated
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE = 23;

    /**
     * The feature id for the '<em><b>Reuse</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>VSM Element Customization Reuse</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VSM_ELEMENT_CUSTOMIZATION_REUSE_FEATURE_COUNT = DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl
     * <em>EStructural Feature Customization</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEStructuralFeatureCustomization()
     * @generated
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION = 24;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON = 0;

    /**
     * The feature id for the '<em><b>Apply On All</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL = 1;

    /**
     * The number of structural features of the '
     * <em>EStructural Feature Customization</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl
     * <em>EAttribute Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEAttributeCustomization()
     * @generated
     */
    int EATTRIBUTE_CUSTOMIZATION = 25;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__APPLIED_ON = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON;

    /**
     * The feature id for the '<em><b>Apply On All</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__APPLY_ON_ALL = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL;

    /**
     * The feature id for the '<em><b>Attribute Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION__VALUE = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>EAttribute Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EATTRIBUTE_CUSTOMIZATION_FEATURE_COUNT = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl
     * <em>EReference Customization</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEReferenceCustomization()
     * @generated
     */
    int EREFERENCE_CUSTOMIZATION = 26;

    /**
     * The feature id for the '<em><b>Applied On</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__APPLIED_ON = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON;

    /**
     * The feature id for the '<em><b>Apply On All</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__APPLY_ON_ALL = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL;

    /**
     * The feature id for the '<em><b>Reference Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__REFERENCE_NAME = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION__VALUE = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>EReference Customization</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EREFERENCE_CUSTOMIZATION_FEATURE_COUNT = DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription
     * <em>Selection Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSelectionDescription()
     * @generated
     */
    int SELECTION_DESCRIPTION = 27;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.ColorDescriptionImpl
     * <em>Color Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ColorDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getColorDescription()
     * @generated
     */
    int COLOR_DESCRIPTION = 28;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl
     * <em>Fixed Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFixedColor()
     * @generated
     */
    int FIXED_COLOR = 32;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FIXED_COLOR__RED = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FIXED_COLOR__GREEN = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FIXED_COLOR__BLUE = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Fixed Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FIXED_COLOR_FEATURE_COUNT = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.SystemColorImpl
     * <em>System Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.SystemColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSystemColor()
     * @generated
     */
    int SYSTEM_COLOR = 29;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__RED = DescriptionPackage.FIXED_COLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__GREEN = DescriptionPackage.FIXED_COLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__BLUE = DescriptionPackage.FIXED_COLOR__BLUE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR__NAME = DescriptionPackage.FIXED_COLOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>System Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SYSTEM_COLOR_FEATURE_COUNT = DescriptionPackage.FIXED_COLOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl
     * <em>Interpolated Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getInterpolatedColor()
     * @generated
     */
    int INTERPOLATED_COLOR = 30;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__NAME = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Color Value Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Min Value Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Max Value Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Color Steps</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR__COLOR_STEPS = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Interpolated Color</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERPOLATED_COLOR_FEATURE_COUNT = DescriptionPackage.COLOR_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl
     * <em>Color Step</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getColorStep()
     * @generated
     */
    int COLOR_STEP = 31;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.UserFixedColorImpl
     * <em>User Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.UserFixedColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserFixedColor()
     * @generated
     */
    int USER_FIXED_COLOR = 33;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__RED = DescriptionPackage.FIXED_COLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__GREEN = DescriptionPackage.FIXED_COLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__BLUE = DescriptionPackage.FIXED_COLOR__BLUE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR__NAME = DescriptionPackage.FIXED_COLOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>User Fixed Color</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_FIXED_COLOR_FEATURE_COUNT = DescriptionPackage.FIXED_COLOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.UserColorImpl
     * <em>User Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.UserColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserColor()
     * @generated
     */
    int USER_COLOR = 34;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl
     * <em>Environment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEnvironment()
     * @generated
     */
    int ENVIRONMENT = 35;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.SytemColorsPaletteImpl
     * <em>Sytem Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.SytemColorsPaletteImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSytemColorsPalette()
     * @generated
     */
    int SYTEM_COLORS_PALETTE = 36;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.UserColorsPaletteImpl
     * <em>User Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.UserColorsPaletteImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserColorsPalette()
     * @generated
     */
    int USER_COLORS_PALETTE = 37;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.AnnotationEntryImpl
     * <em>Annotation Entry</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.AnnotationEntryImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getAnnotationEntry()
     * @generated
     */
    int ANNOTATION_ENTRY = 38;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.EndUserDocumentedElementImpl
     * <em>End User Documented Element</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EndUserDocumentedElementImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEndUserDocumentedElement()
     * @generated
     */
    int END_USER_DOCUMENTED_ELEMENT = 39;

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
     * {@link org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl
     * <em>Computed Color</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getComputedColor()
     * @generated
     */
    int COMPUTED_COLOR = 41;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__NAME = DescriptionPackage.USER_COLOR__NAME;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__RED = DescriptionPackage.USER_COLOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__GREEN = DescriptionPackage.USER_COLOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR__BLUE = DescriptionPackage.USER_COLOR_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Computed Color</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_COLOR_FEATURE_COUNT = DescriptionPackage.USER_COLOR_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl
     * <em>DAnnotation Entry</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDAnnotationEntry()
     * @generated
     */
    int DANNOTATION_ENTRY = 42;

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
     * {@link org.eclipse.sirius.viewpoint.description.Position
     * <em>Position</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.Position
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getPosition()
     * @generated
     */
    int POSITION = 43;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.SystemColors
     * <em>System Colors</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.SystemColors
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSystemColors()
     * @generated
     */
    int SYSTEM_COLORS = 44;

    /**
     * The meta object id for the '<em>Type Name</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getTypeName()
     * @generated
     */
    int TYPE_NAME = 45;

    /**
     * The meta object id for the '<em>Interpreted Expression</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see java.lang.String
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getInterpretedExpression()
     * @generated
     */
    int INTERPRETED_EXPRESSION = 46;

    /**
     * The meta object id for the '<em>Feature Name</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFeatureName()
     * @generated
     */
    int FEATURE_NAME = 47;

    /**
     * The meta object id for the '<em>Image Path</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getImagePath()
     * @generated
     */
    int IMAGE_PATH = 48;

    /**
     * The meta object id for the '<em>URI</em>' data type. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.common.util.URI
     * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getURI()
     * @generated
     */
    int URI = 49;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.Group <em>Group</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Group</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Group
     * @generated
     */
    EClass getGroup();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.Group#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Group#getName()
     * @see #getGroup()
     * @generated
     */
    EAttribute getGroup_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Group#getOwnedViewpoints
     * <em>Owned Viewpoints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Viewpoints</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Group#getOwnedViewpoints()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_OwnedViewpoints();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.Group#getSystemColorsPalette
     * <em>System Colors Palette</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>System Colors Palette</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.Group#getSystemColorsPalette()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_SystemColorsPalette();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Group#getUserColorsPalettes
     * <em>User Colors Palettes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>User Colors Palettes</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Group#getUserColorsPalettes()
     * @see #getGroup()
     * @generated
     */
    EReference getGroup_UserColorsPalettes();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.Group#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Group#getVersion()
     * @see #getGroup()
     * @generated
     */
    EAttribute getGroup_Version();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.Component
     * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Component</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Component
     * @generated
     */
    EClass getComponent();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint
     * <em>Viewpoint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Viewpoint</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint
     * @generated
     */
    EClass getViewpoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getModelFileExtension
     * <em>Model File Extension</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Model File Extension</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getModelFileExtension()
     * @see #getViewpoint()
     * @generated
     */
    EAttribute getViewpoint_ModelFileExtension();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getValidationSet()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_ValidationSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentations()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedRepresentations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentationExtensions
     * <em>Owned Representation Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representation Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedRepresentationExtensions()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedRepresentationExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedJavaExtensions
     * <em>Owned Java Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Java Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedJavaExtensions()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedJavaExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedMMExtensions
     * <em>Owned MM Extensions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned MM Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedMMExtensions()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedMMExtensions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedFeatureExtensions
     * <em>Owned Feature Extensions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Feature Extensions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedFeatureExtensions()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedFeatureExtensions();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getIcon()
     * @see #getViewpoint()
     * @generated
     */
    EAttribute getViewpoint_Icon();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedTemplates
     * <em>Owned Templates</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Templates</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getOwnedTemplates()
     * @see #getViewpoint()
     * @generated
     */
    EReference getViewpoint_OwnedTemplates();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getConflicts
     * <em>Conflicts</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Conflicts</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getConflicts()
     * @see #getViewpoint()
     * @generated
     */
    EAttribute getViewpoint_Conflicts();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getReuses
     * <em>Reuses</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Reuses</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getReuses()
     * @see #getViewpoint()
     * @generated
     */
    EAttribute getViewpoint_Reuses();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.description.Viewpoint#getCustomizes
     * <em>Customizes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Customizes</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Viewpoint#getCustomizes()
     * @see #getViewpoint()
     * @generated
     */
    EAttribute getViewpoint_Customizes();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
     * <em>Feature Extension Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Feature Extension Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
     * @generated
     */
    EClass getFeatureExtensionDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Representation Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @generated
     */
    EClass getRepresentationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#getTitleExpression
     * <em>Title Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription#getTitleExpression()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isInitialisation
     * <em>Initialisation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Initialisation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription#isInitialisation()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_Initialisation();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#getMetamodel
     * <em>Metamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Metamodel</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription#getMetamodel()
     * @see #getRepresentationDescription()
     * @generated
     */
    EReference getRepresentationDescription_Metamodel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription#isShowOnStartup
     * <em>Show On Startup</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Show On Startup</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription#isShowOnStartup()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_ShowOnStartup();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate
     * <em>Representation Template</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Representation Template</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationTemplate
     * @generated
     */
    EClass getRepresentationTemplate();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getName()
     * @see #getRepresentationTemplate()
     * @generated
     */
    EAttribute getRepresentationTemplate_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getOwnedRepresentations
     * <em>Owned Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Representations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getOwnedRepresentations()
     * @see #getRepresentationTemplate()
     * @generated
     */
    EReference getRepresentationTemplate_OwnedRepresentations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
     * <em>Representation Import Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Import Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
     * @generated
     */
    EClass getRepresentationImportDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
     * <em>Representation Extension Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Extension Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
     * @generated
     */
    EClass getRepresentationExtensionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getName()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getViewpointURI
     * <em>Viewpoint URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Viewpoint URI</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getViewpointURI()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_ViewpointURI();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getRepresentationName
     * <em>Representation Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Representation Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getRepresentationName()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EAttribute getRepresentationExtensionDescription_RepresentationName();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getMetamodel
     * <em>Metamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Metamodel</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription#getMetamodel()
     * @see #getRepresentationExtensionDescription()
     * @generated
     */
    EReference getRepresentationExtensionDescription_Metamodel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting
     * <em>Metamodel Extension Setting</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Metamodel Extension Setting</em>'.
     * @see org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting
     * @generated
     */
    EClass getMetamodelExtensionSetting();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting#getExtensionGroup
     * <em>Extension Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Extension Group</em>'.
     * @see org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting#getExtensionGroup()
     * @see #getMetamodelExtensionSetting()
     * @generated
     */
    EReference getMetamodelExtensionSetting_ExtensionGroup();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.JavaExtension
     * <em>Java Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Java Extension</em>'.
     * @see org.eclipse.sirius.viewpoint.description.JavaExtension
     * @generated
     */
    EClass getJavaExtension();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.JavaExtension#getQualifiedClassName
     * <em>Qualified Class Name</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Qualified Class Name</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.JavaExtension#getQualifiedClassName()
     * @see #getJavaExtension()
     * @generated
     */
    EAttribute getJavaExtension_QualifiedClassName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * <em>Representation Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Element Mapping</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * @generated
     */
    EClass getRepresentationElementMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getDetailDescriptions
     * <em>Detail Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>Detail Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getDetailDescriptions()
     * @see #getRepresentationElementMapping()
     * @generated
     */
    EReference getRepresentationElementMapping_DetailDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getNavigationDescriptions
     * <em>Navigation Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Navigation Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getNavigationDescriptions()
     * @see #getRepresentationElementMapping()
     * @generated
     */
    EReference getRepresentationElementMapping_NavigationDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport
     * <em>Abstract Mapping Import</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Mapping Import</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport
     * @generated
     */
    EClass getAbstractMappingImport();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isHideSubMappings
     * <em>Hide Sub Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Hide Sub Mappings</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isHideSubMappings()
     * @see #getAbstractMappingImport()
     * @generated
     */
    EAttribute getAbstractMappingImport_HideSubMappings();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isInheritsAncestorFilters
     * <em>Inherits Ancestor Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Inherits Ancestor Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isInheritsAncestorFilters()
     * @see #getAbstractMappingImport()
     * @generated
     */
    EAttribute getAbstractMappingImport_InheritsAncestorFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Documented Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    EClass getDocumentedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DocumentedElement#getDocumentation
     * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement#getDocumentation()
     * @see #getDocumentedElement()
     * @generated
     */
    EAttribute getDocumentedElement_Documentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DModel Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DModelElement
     * @generated
     */
    EClass getDModelElement();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.DModelElement#getEAnnotations
     * <em>EAnnotations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>EAnnotations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DModelElement#getEAnnotations()
     * @see #getDModelElement()
     * @generated
     */
    EReference getDModelElement_EAnnotations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotation
     * <em>DAnnotation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DAnnotation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotation
     * @generated
     */
    EClass getDAnnotation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotation#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotation#getSource()
     * @see #getDAnnotation()
     * @generated
     */
    EAttribute getDAnnotation_Source();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotation#getDetails
     * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Details</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotation#getDetails()
     * @see #getDAnnotation()
     * @generated
     */
    EReference getDAnnotation_Details();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription
     * <em>Conditional Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Conditional Style Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription
     * @generated
     */
    EClass getConditionalStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription#getPredicateExpression()
     * @see #getConditionalStyleDescription()
     * @generated
     */
    EAttribute getConditionalStyleDescription_PredicateExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.PasteTargetDescription
     * <em>Paste Target Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Paste Target Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.PasteTargetDescription
     * @generated
     */
    EClass getPasteTargetDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.PasteTargetDescription#getPasteDescriptions
     * <em>Paste Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>Paste Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.PasteTargetDescription#getPasteDescriptions()
     * @see #getPasteTargetDescription()
     * @generated
     */
    EReference getPasteTargetDescription_PasteDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet
     * <em>Decoration Descriptions Set</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration Descriptions Set</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet
     * @generated
     */
    EClass getDecorationDescriptionsSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet#getDecorationDescriptions
     * <em>Decoration Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Decoration Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet#getDecorationDescriptions()
     * @see #getDecorationDescriptionsSet()
     * @generated
     */
    EReference getDecorationDescriptionsSet_DecorationDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription
     * <em>Decoration Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Decoration Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription
     * @generated
     */
    EClass getDecorationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription#getName()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition
     * <em>Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Position</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_Position();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getDecoratorPath
     * <em>Decorator Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Decorator Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription#getDecoratorPath()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_DecoratorPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression()
     * @see #getDecorationDescription()
     * @generated
     */
    EAttribute getDecorationDescription_PreconditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration
     * <em>Semantic Based Decoration</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Semantic Based Decoration</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration
     * @generated
     */
    EClass getSemanticBasedDecoration();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration#getDomainClass()
     * @see #getSemanticBasedDecoration()
     * @generated
     */
    EAttribute getSemanticBasedDecoration_DomainClass();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.Customization
     * <em>Customization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Customization
     * @generated
     */
    EClass getCustomization();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Customization#getVsmElementCustomizations
     * <em>Vsm Element Customizations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Vsm Element Customizations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Customization#getVsmElementCustomizations()
     * @see #getCustomization()
     * @generated
     */
    EReference getCustomization_VsmElementCustomizations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
     * <em>IVSM Element Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>IVSM Element Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
     * @generated
     */
    EClass getIVSMElementCustomization();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization
     * <em>VSM Element Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>VSM Element Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomization
     * @generated
     */
    EClass getVSMElementCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getPredicateExpression()
     * @see #getVSMElementCustomization()
     * @generated
     */
    EAttribute getVSMElementCustomization_PredicateExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getFeatureCustomizations
     * <em>Feature Customizations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Feature Customizations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getFeatureCustomizations()
     * @see #getVSMElementCustomization()
     * @generated
     */
    EReference getVSMElementCustomization_FeatureCustomizations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse
     * <em>VSM Element Customization Reuse</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>VSM Element Customization Reuse</em>'.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse
     * @generated
     */
    EClass getVSMElementCustomizationReuse();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getReuse
     * <em>Reuse</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reuse</em>'.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getReuse()
     * @see #getVSMElementCustomizationReuse()
     * @generated
     */
    EReference getVSMElementCustomizationReuse_Reuse();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getAppliedOn
     * <em>Applied On</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Applied On</em>'.
     * @see org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getAppliedOn()
     * @see #getVSMElementCustomizationReuse()
     * @generated
     */
    EReference getVSMElementCustomizationReuse_AppliedOn();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization
     * <em>EStructural Feature Customization</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>EStructural Feature Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization
     * @generated
     */
    EClass getEStructuralFeatureCustomization();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#getAppliedOn
     * <em>Applied On</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Applied On</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#getAppliedOn()
     * @see #getEStructuralFeatureCustomization()
     * @generated
     */
    EReference getEStructuralFeatureCustomization_AppliedOn();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#isApplyOnAll
     * <em>Apply On All</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Apply On All</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#isApplyOnAll()
     * @see #getEStructuralFeatureCustomization()
     * @generated
     */
    EAttribute getEStructuralFeatureCustomization_ApplyOnAll();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization
     * <em>EAttribute Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>EAttribute Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EAttributeCustomization
     * @generated
     */
    EClass getEAttributeCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getAttributeName
     * <em>Attribute Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Attribute Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getAttributeName()
     * @see #getEAttributeCustomization()
     * @generated
     */
    EAttribute getEAttributeCustomization_AttributeName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getValue()
     * @see #getEAttributeCustomization()
     * @generated
     */
    EAttribute getEAttributeCustomization_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization
     * <em>EReference Customization</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>EReference Customization</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EReferenceCustomization
     * @generated
     */
    EClass getEReferenceCustomization();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getReferenceName
     * <em>Reference Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Reference Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getReferenceName()
     * @see #getEReferenceCustomization()
     * @generated
     */
    EAttribute getEReferenceCustomization_ReferenceName();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getValue()
     * @see #getEReferenceCustomization()
     * @generated
     */
    EReference getEReferenceCustomization_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription
     * <em>Selection Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Selection Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription
     * @generated
     */
    EClass getSelectionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#getCandidatesExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isMultiple
     * <em>Multiple</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#isMultiple()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Multiple();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#isTree
     * <em>Tree</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tree</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#isTree()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Tree();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getRootExpression
     * <em>Root Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#getRootExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_RootExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getChildrenExpression
     * <em>Children Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#getChildrenExpression()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_ChildrenExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription#getMessage()
     * @see #getSelectionDescription()
     * @generated
     */
    EAttribute getSelectionDescription_Message();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.ColorDescription
     * <em>Color Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Color Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ColorDescription
     * @generated
     */
    EClass getColorDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.SystemColor
     * <em>System Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>System Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SystemColor
     * @generated
     */
    EClass getSystemColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.SystemColor#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SystemColor#getName()
     * @see #getSystemColor()
     * @generated
     */
    EAttribute getSystemColor_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.InterpolatedColor
     * <em>Interpolated Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Interpolated Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.InterpolatedColor
     * @generated
     */
    EClass getInterpolatedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorValueComputationExpression
     * <em>Color Value Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Color Value Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_ColorValueComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMinValueComputationExpression
     * <em>Min Value Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Min Value Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMinValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_MinValueComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMaxValueComputationExpression
     * <em>Max Value Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Max Value Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMaxValueComputationExpression()
     * @see #getInterpolatedColor()
     * @generated
     */
    EAttribute getInterpolatedColor_MaxValueComputationExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorSteps
     * <em>Color Steps</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Color Steps</em>'.
     * @see org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorSteps()
     * @see #getInterpolatedColor()
     * @generated
     */
    EReference getInterpolatedColor_ColorSteps();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.ColorStep
     * <em>Color Step</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Color Step</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ColorStep
     * @generated
     */
    EClass getColorStep();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedValue
     * <em>Associated Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Associated Value</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedValue()
     * @see #getColorStep()
     * @generated
     */
    EAttribute getColorStep_AssociatedValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedColor
     * <em>Associated Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Associated Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedColor()
     * @see #getColorStep()
     * @generated
     */
    EReference getColorStep_AssociatedColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.FixedColor
     * <em>Fixed Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Fixed Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.FixedColor
     * @generated
     */
    EClass getFixedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.FixedColor#getRed
     * <em>Red</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.viewpoint.description.FixedColor#getRed()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.FixedColor#getGreen
     * <em>Green</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.viewpoint.description.FixedColor#getGreen()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.FixedColor#getBlue
     * <em>Blue</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.viewpoint.description.FixedColor#getBlue()
     * @see #getFixedColor()
     * @generated
     */
    EAttribute getFixedColor_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.UserFixedColor
     * <em>User Fixed Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>User Fixed Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserFixedColor
     * @generated
     */
    EClass getUserFixedColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.UserColor
     * <em>User Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserColor
     * @generated
     */
    EClass getUserColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.UserColor#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserColor#getName()
     * @see #getUserColor()
     * @generated
     */
    EAttribute getUserColor_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.Environment
     * <em>Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Environment</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Environment
     * @generated
     */
    EClass getEnvironment();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.Environment#getSystemColors
     * <em>System Colors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>System Colors</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Environment#getSystemColors()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_SystemColors();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.Environment#getDefaultTools
     * <em>Default Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Default Tools</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Environment#getDefaultTools()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_DefaultTools();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.Environment#getLabelBorderStyles
     * <em>Label Border Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Label Border Styles</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Environment#getLabelBorderStyles()
     * @see #getEnvironment()
     * @generated
     */
    EReference getEnvironment_LabelBorderStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.SytemColorsPalette
     * <em>Sytem Colors Palette</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Sytem Colors Palette</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SytemColorsPalette
     * @generated
     */
    EClass getSytemColorsPalette();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.SytemColorsPalette#getEntries
     * <em>Entries</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Entries</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SytemColorsPalette#getEntries()
     * @see #getSytemColorsPalette()
     * @generated
     */
    EReference getSytemColorsPalette_Entries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.UserColorsPalette
     * <em>User Colors Palette</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>User Colors Palette</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserColorsPalette
     * @generated
     */
    EClass getUserColorsPalette();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.UserColorsPalette#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserColorsPalette#getName()
     * @see #getUserColorsPalette()
     * @generated
     */
    EAttribute getUserColorsPalette_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.UserColorsPalette#getEntries
     * <em>Entries</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Entries</em>'.
     * @see org.eclipse.sirius.viewpoint.description.UserColorsPalette#getEntries()
     * @see #getUserColorsPalette()
     * @generated
     */
    EReference getUserColorsPalette_Entries();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.AnnotationEntry
     * <em>Annotation Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Annotation Entry</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AnnotationEntry
     * @generated
     */
    EClass getAnnotationEntry();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AnnotationEntry#getSource()
     * @see #getAnnotationEntry()
     * @generated
     */
    EAttribute getAnnotationEntry_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.viewpoint.description.AnnotationEntry#getData()
     * @see #getAnnotationEntry()
     * @generated
     */
    EReference getAnnotationEntry_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * <em>End User Documented Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>End User Documented Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * @generated
     */
    EClass getEndUserDocumentedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement#getEndUserDocumentation
     * <em>End User Documentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>End User Documentation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement#getEndUserDocumentation()
     * @see #getEndUserDocumentedElement()
     * @generated
     */
    EAttribute getEndUserDocumentedElement_EndUserDocumentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Identified Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    EClass getIdentifiedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement#getName()
     * @see #getIdentifiedElement()
     * @generated
     */
    EAttribute getIdentifiedElement_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement#getLabel()
     * @see #getIdentifiedElement()
     * @generated
     */
    EAttribute getIdentifiedElement_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.ComputedColor
     * <em>Computed Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Computed Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ComputedColor
     * @generated
     */
    EClass getComputedColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.ComputedColor#getRed
     * <em>Red</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ComputedColor#getRed()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.ComputedColor#getGreen
     * <em>Green</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ComputedColor#getGreen()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.ComputedColor#getBlue
     * <em>Blue</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.viewpoint.description.ComputedColor#getBlue()
     * @see #getComputedColor()
     * @generated
     */
    EAttribute getComputedColor_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotationEntry
     * <em>DAnnotation Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>DAnnotation Entry</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotationEntry
     * @generated
     */
    EClass getDAnnotationEntry();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotationEntry#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotationEntry#getSource()
     * @see #getDAnnotationEntry()
     * @generated
     */
    EAttribute getDAnnotationEntry_Source();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotationEntry#getDetails
     * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Details</em>'.
     * @see org.eclipse.sirius.viewpoint.description.DAnnotationEntry#getDetails()
     * @see #getDAnnotationEntry()
     * @generated
     */
    EAttribute getDAnnotationEntry_Details();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.description.Position
     * <em>Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Position</em>'.
     * @see org.eclipse.sirius.viewpoint.description.Position
     * @generated
     */
    EEnum getPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.description.SystemColors
     * <em>System Colors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>System Colors</em>'.
     * @see org.eclipse.sirius.viewpoint.description.SystemColors
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
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Image Path</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     * @generated
     */
    EDataType getImagePath();

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
         * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl
         * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.GroupImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getGroup()
         * @generated
         */
        EClass GROUP = DescriptionPackage.eINSTANCE.getGroup();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP__NAME = DescriptionPackage.eINSTANCE.getGroup_Name();

        /**
         * The meta object literal for the '<em><b>Owned Viewpoints</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP__OWNED_VIEWPOINTS = DescriptionPackage.eINSTANCE.getGroup_OwnedViewpoints();

        /**
         * The meta object literal for the '
         * <em><b>System Colors Palette</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP__SYSTEM_COLORS_PALETTE = DescriptionPackage.eINSTANCE.getGroup_SystemColorsPalette();

        /**
         * The meta object literal for the '<em><b>User Colors Palettes</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP__USER_COLORS_PALETTES = DescriptionPackage.eINSTANCE.getGroup_UserColorsPalettes();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP__VERSION = DescriptionPackage.eINSTANCE.getGroup_Version();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.Component
         * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.Component
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getComponent()
         * @generated
         */
        EClass COMPONENT = DescriptionPackage.eINSTANCE.getComponent();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl
         * <em>Viewpoint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getViewpoint()
         * @generated
         */
        EClass VIEWPOINT = DescriptionPackage.eINSTANCE.getViewpoint();

        /**
         * The meta object literal for the '<em><b>Model File Extension</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__MODEL_FILE_EXTENSION = DescriptionPackage.eINSTANCE.getViewpoint_ModelFileExtension();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__VALIDATION_SET = DescriptionPackage.eINSTANCE.getViewpoint_ValidationSet();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_REPRESENTATIONS = DescriptionPackage.eINSTANCE.getViewpoint_OwnedRepresentations();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representation Extensions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS = DescriptionPackage.eINSTANCE.getViewpoint_OwnedRepresentationExtensions();

        /**
         * The meta object literal for the '
         * <em><b>Owned Java Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_JAVA_EXTENSIONS = DescriptionPackage.eINSTANCE.getViewpoint_OwnedJavaExtensions();

        /**
         * The meta object literal for the '<em><b>Owned MM Extensions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_MM_EXTENSIONS = DescriptionPackage.eINSTANCE.getViewpoint_OwnedMMExtensions();

        /**
         * The meta object literal for the '
         * <em><b>Owned Feature Extensions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_FEATURE_EXTENSIONS = DescriptionPackage.eINSTANCE.getViewpoint_OwnedFeatureExtensions();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__ICON = DescriptionPackage.eINSTANCE.getViewpoint_Icon();

        /**
         * The meta object literal for the '<em><b>Owned Templates</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VIEWPOINT__OWNED_TEMPLATES = DescriptionPackage.eINSTANCE.getViewpoint_OwnedTemplates();

        /**
         * The meta object literal for the '<em><b>Conflicts</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__CONFLICTS = DescriptionPackage.eINSTANCE.getViewpoint_Conflicts();

        /**
         * The meta object literal for the '<em><b>Reuses</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__REUSES = DescriptionPackage.eINSTANCE.getViewpoint_Reuses();

        /**
         * The meta object literal for the '<em><b>Customizes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEWPOINT__CUSTOMIZES = DescriptionPackage.eINSTANCE.getViewpoint_Customizes();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
         * <em>Feature Extension Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFeatureExtensionDescription()
         * @generated
         */
        EClass FEATURE_EXTENSION_DESCRIPTION = DescriptionPackage.eINSTANCE.getFeatureExtensionDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription
         * <em>Representation Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationDescription()
         * @generated
         */
        EClass REPRESENTATION_DESCRIPTION = DescriptionPackage.eINSTANCE.getRepresentationDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Initialisation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__INITIALISATION = DescriptionPackage.eINSTANCE.getRepresentationDescription_Initialisation();

        /**
         * The meta object literal for the '<em><b>Metamodel</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_DESCRIPTION__METAMODEL = DescriptionPackage.eINSTANCE.getRepresentationDescription_Metamodel();

        /**
         * The meta object literal for the '<em><b>Show On Startup</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.eINSTANCE.getRepresentationDescription_ShowOnStartup();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl
         * <em>Representation Template</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.RepresentationTemplateImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationTemplate()
         * @generated
         */
        EClass REPRESENTATION_TEMPLATE = DescriptionPackage.eINSTANCE.getRepresentationTemplate();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_TEMPLATE__NAME = DescriptionPackage.eINSTANCE.getRepresentationTemplate_Name();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS = DescriptionPackage.eINSTANCE.getRepresentationTemplate_OwnedRepresentations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
         * <em>Representation Import Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationImportDescription()
         * @generated
         */
        EClass REPRESENTATION_IMPORT_DESCRIPTION = DescriptionPackage.eINSTANCE.getRepresentationImportDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
         * <em>Representation Extension Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationExtensionDescription()
         * @generated
         */
        EClass REPRESENTATION_EXTENSION_DESCRIPTION = DescriptionPackage.eINSTANCE.getRepresentationExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__NAME = DescriptionPackage.eINSTANCE.getRepresentationExtensionDescription_Name();

        /**
         * The meta object literal for the '<em><b>Viewpoint URI</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI = DescriptionPackage.eINSTANCE.getRepresentationExtensionDescription_ViewpointURI();

        /**
         * The meta object literal for the '<em><b>Representation Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME = DescriptionPackage.eINSTANCE.getRepresentationExtensionDescription_RepresentationName();

        /**
         * The meta object literal for the '<em><b>Metamodel</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL = DescriptionPackage.eINSTANCE.getRepresentationExtensionDescription_Metamodel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.MetamodelExtensionSettingImpl
         * <em>Metamodel Extension Setting</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.MetamodelExtensionSettingImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getMetamodelExtensionSetting()
         * @generated
         */
        EClass METAMODEL_EXTENSION_SETTING = DescriptionPackage.eINSTANCE.getMetamodelExtensionSetting();

        /**
         * The meta object literal for the '<em><b>Extension Group</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference METAMODEL_EXTENSION_SETTING__EXTENSION_GROUP = DescriptionPackage.eINSTANCE.getMetamodelExtensionSetting_ExtensionGroup();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.JavaExtensionImpl
         * <em>Java Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.JavaExtensionImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getJavaExtension()
         * @generated
         */
        EClass JAVA_EXTENSION = DescriptionPackage.eINSTANCE.getJavaExtension();

        /**
         * The meta object literal for the '<em><b>Qualified Class Name</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute JAVA_EXTENSION__QUALIFIED_CLASS_NAME = DescriptionPackage.eINSTANCE.getJavaExtension_QualifiedClassName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl
         * <em>Representation Element Mapping</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getRepresentationElementMapping()
         * @generated
         */
        EClass REPRESENTATION_ELEMENT_MAPPING = DescriptionPackage.eINSTANCE.getRepresentationElementMapping();

        /**
         * The meta object literal for the '<em><b>Detail Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getRepresentationElementMapping_DetailDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>Navigation Descriptions</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getRepresentationElementMapping_NavigationDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.AbstractMappingImportImpl
         * <em>Abstract Mapping Import</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.AbstractMappingImportImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getAbstractMappingImport()
         * @generated
         */
        EClass ABSTRACT_MAPPING_IMPORT = DescriptionPackage.eINSTANCE.getAbstractMappingImport();

        /**
         * The meta object literal for the '<em><b>Hide Sub Mappings</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = DescriptionPackage.eINSTANCE.getAbstractMappingImport_HideSubMappings();

        /**
         * The meta object literal for the '
         * <em><b>Inherits Ancestor Filters</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = DescriptionPackage.eINSTANCE.getAbstractMappingImport_InheritsAncestorFilters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl
         * <em>Documented Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDocumentedElement()
         * @generated
         */
        EClass DOCUMENTED_ELEMENT = DescriptionPackage.eINSTANCE.getDocumentedElement();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOCUMENTED_ELEMENT__DOCUMENTATION = DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DModelElementImpl
         * <em>DModel Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DModelElementImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDModelElement()
         * @generated
         */
        EClass DMODEL_ELEMENT = DescriptionPackage.eINSTANCE.getDModelElement();

        /**
         * The meta object literal for the '<em><b>EAnnotations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DMODEL_ELEMENT__EANNOTATIONS = DescriptionPackage.eINSTANCE.getDModelElement_EAnnotations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl
         * <em>DAnnotation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDAnnotation()
         * @generated
         */
        EClass DANNOTATION = DescriptionPackage.eINSTANCE.getDAnnotation();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION__SOURCE = DescriptionPackage.eINSTANCE.getDAnnotation_Source();

        /**
         * The meta object literal for the '<em><b>Details</b></em>' map
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DANNOTATION__DETAILS = DescriptionPackage.eINSTANCE.getDAnnotation_Details();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl
         * <em>Conditional Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getConditionalStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getConditionalStyleDescription();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = DescriptionPackage.eINSTANCE.getConditionalStyleDescription_PredicateExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.PasteTargetDescriptionImpl
         * <em>Paste Target Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.PasteTargetDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getPasteTargetDescription()
         * @generated
         */
        EClass PASTE_TARGET_DESCRIPTION = DescriptionPackage.eINSTANCE.getPasteTargetDescription();

        /**
         * The meta object literal for the '<em><b>Paste Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getPasteTargetDescription_PasteDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionsSetImpl
         * <em>Decoration Descriptions Set</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionsSetImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDecorationDescriptionsSet()
         * @generated
         */
        EClass DECORATION_DESCRIPTIONS_SET = DescriptionPackage.eINSTANCE.getDecorationDescriptionsSet();

        /**
         * The meta object literal for the '
         * <em><b>Decoration Descriptions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getDecorationDescriptionsSet_DecorationDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl
         * <em>Decoration Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDecorationDescription()
         * @generated
         */
        EClass DECORATION_DESCRIPTION = DescriptionPackage.eINSTANCE.getDecorationDescription();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__NAME = DescriptionPackage.eINSTANCE.getDecorationDescription_Name();

        /**
         * The meta object literal for the '<em><b>Position</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__POSITION = DescriptionPackage.eINSTANCE.getDecorationDescription_Position();

        /**
         * The meta object literal for the '<em><b>Decorator Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__DECORATOR_PATH = DescriptionPackage.eINSTANCE.getDecorationDescription_DecoratorPath();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.eINSTANCE.getDecorationDescription_PreconditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.SemanticBasedDecorationImpl
         * <em>Semantic Based Decoration</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.SemanticBasedDecorationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSemanticBasedDecoration()
         * @generated
         */
        EClass SEMANTIC_BASED_DECORATION = DescriptionPackage.eINSTANCE.getSemanticBasedDecoration();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SEMANTIC_BASED_DECORATION__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getSemanticBasedDecoration_DomainClass();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.CustomizationImpl
         * <em>Customization</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.CustomizationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getCustomization()
         * @generated
         */
        EClass CUSTOMIZATION = DescriptionPackage.eINSTANCE.getCustomization();

        /**
         * The meta object literal for the '
         * <em><b>Vsm Element Customizations</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS = DescriptionPackage.eINSTANCE.getCustomization_VsmElementCustomizations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
         * <em>IVSM Element Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.IVSMElementCustomization
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getIVSMElementCustomization()
         * @generated
         */
        EClass IVSM_ELEMENT_CUSTOMIZATION = DescriptionPackage.eINSTANCE.getIVSMElementCustomization();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl
         * <em>VSM Element Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getVSMElementCustomization()
         * @generated
         */
        EClass VSM_ELEMENT_CUSTOMIZATION = DescriptionPackage.eINSTANCE.getVSMElementCustomization();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION = DescriptionPackage.eINSTANCE.getVSMElementCustomization_PredicateExpression();

        /**
         * The meta object literal for the '
         * <em><b>Feature Customizations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS = DescriptionPackage.eINSTANCE.getVSMElementCustomization_FeatureCustomizations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl
         * <em>VSM Element Customization Reuse</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getVSMElementCustomizationReuse()
         * @generated
         */
        EClass VSM_ELEMENT_CUSTOMIZATION_REUSE = DescriptionPackage.eINSTANCE.getVSMElementCustomizationReuse();

        /**
         * The meta object literal for the '<em><b>Reuse</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE = DescriptionPackage.eINSTANCE.getVSMElementCustomizationReuse_Reuse();

        /**
         * The meta object literal for the '<em><b>Applied On</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON = DescriptionPackage.eINSTANCE.getVSMElementCustomizationReuse_AppliedOn();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl
         * <em>EStructural Feature Customization</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEStructuralFeatureCustomization()
         * @generated
         */
        EClass ESTRUCTURAL_FEATURE_CUSTOMIZATION = DescriptionPackage.eINSTANCE.getEStructuralFeatureCustomization();

        /**
         * The meta object literal for the '<em><b>Applied On</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON = DescriptionPackage.eINSTANCE.getEStructuralFeatureCustomization_AppliedOn();

        /**
         * The meta object literal for the '<em><b>Apply On All</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL = DescriptionPackage.eINSTANCE.getEStructuralFeatureCustomization_ApplyOnAll();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl
         * <em>EAttribute Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEAttributeCustomization()
         * @generated
         */
        EClass EATTRIBUTE_CUSTOMIZATION = DescriptionPackage.eINSTANCE.getEAttributeCustomization();

        /**
         * The meta object literal for the '<em><b>Attribute Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME = DescriptionPackage.eINSTANCE.getEAttributeCustomization_AttributeName();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EATTRIBUTE_CUSTOMIZATION__VALUE = DescriptionPackage.eINSTANCE.getEAttributeCustomization_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl
         * <em>EReference Customization</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEReferenceCustomization()
         * @generated
         */
        EClass EREFERENCE_CUSTOMIZATION = DescriptionPackage.eINSTANCE.getEReferenceCustomization();

        /**
         * The meta object literal for the '<em><b>Reference Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EREFERENCE_CUSTOMIZATION__REFERENCE_NAME = DescriptionPackage.eINSTANCE.getEReferenceCustomization_ReferenceName();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EREFERENCE_CUSTOMIZATION__VALUE = DescriptionPackage.eINSTANCE.getEReferenceCustomization_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription
         * <em>Selection Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.SelectionDescription
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSelectionDescription()
         * @generated
         */
        EClass SELECTION_DESCRIPTION = DescriptionPackage.eINSTANCE.getSelectionDescription();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION = DescriptionPackage.eINSTANCE.getSelectionDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__MULTIPLE = DescriptionPackage.eINSTANCE.getSelectionDescription_Multiple();

        /**
         * The meta object literal for the '<em><b>Tree</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__TREE = DescriptionPackage.eINSTANCE.getSelectionDescription_Tree();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__ROOT_EXPRESSION = DescriptionPackage.eINSTANCE.getSelectionDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__CHILDREN_EXPRESSION = DescriptionPackage.eINSTANCE.getSelectionDescription_ChildrenExpression();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_DESCRIPTION__MESSAGE = DescriptionPackage.eINSTANCE.getSelectionDescription_Message();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.ColorDescriptionImpl
         * <em>Color Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.ColorDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getColorDescription()
         * @generated
         */
        EClass COLOR_DESCRIPTION = DescriptionPackage.eINSTANCE.getColorDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.SystemColorImpl
         * <em>System Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.SystemColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSystemColor()
         * @generated
         */
        EClass SYSTEM_COLOR = DescriptionPackage.eINSTANCE.getSystemColor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SYSTEM_COLOR__NAME = DescriptionPackage.eINSTANCE.getSystemColor_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl
         * <em>Interpolated Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getInterpolatedColor()
         * @generated
         */
        EClass INTERPOLATED_COLOR = DescriptionPackage.eINSTANCE.getInterpolatedColor();

        /**
         * The meta object literal for the '
         * <em><b>Color Value Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.eINSTANCE.getInterpolatedColor_ColorValueComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Min Value Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.eINSTANCE.getInterpolatedColor_MinValueComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Max Value Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION = DescriptionPackage.eINSTANCE.getInterpolatedColor_MaxValueComputationExpression();

        /**
         * The meta object literal for the '<em><b>Color Steps</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INTERPOLATED_COLOR__COLOR_STEPS = DescriptionPackage.eINSTANCE.getInterpolatedColor_ColorSteps();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl
         * <em>Color Step</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getColorStep()
         * @generated
         */
        EClass COLOR_STEP = DescriptionPackage.eINSTANCE.getColorStep();

        /**
         * The meta object literal for the '<em><b>Associated Value</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLOR_STEP__ASSOCIATED_VALUE = DescriptionPackage.eINSTANCE.getColorStep_AssociatedValue();

        /**
         * The meta object literal for the '<em><b>Associated Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COLOR_STEP__ASSOCIATED_COLOR = DescriptionPackage.eINSTANCE.getColorStep_AssociatedColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl
         * <em>Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFixedColor()
         * @generated
         */
        EClass FIXED_COLOR = DescriptionPackage.eINSTANCE.getFixedColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__RED = DescriptionPackage.eINSTANCE.getFixedColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__GREEN = DescriptionPackage.eINSTANCE.getFixedColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FIXED_COLOR__BLUE = DescriptionPackage.eINSTANCE.getFixedColor_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.UserFixedColorImpl
         * <em>User Fixed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.UserFixedColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserFixedColor()
         * @generated
         */
        EClass USER_FIXED_COLOR = DescriptionPackage.eINSTANCE.getUserFixedColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.UserColorImpl
         * <em>User Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.UserColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserColor()
         * @generated
         */
        EClass USER_COLOR = DescriptionPackage.eINSTANCE.getUserColor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_COLOR__NAME = DescriptionPackage.eINSTANCE.getUserColor_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl
         * <em>Environment</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEnvironment()
         * @generated
         */
        EClass ENVIRONMENT = DescriptionPackage.eINSTANCE.getEnvironment();

        /**
         * The meta object literal for the '<em><b>System Colors</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__SYSTEM_COLORS = DescriptionPackage.eINSTANCE.getEnvironment_SystemColors();

        /**
         * The meta object literal for the '<em><b>Default Tools</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__DEFAULT_TOOLS = DescriptionPackage.eINSTANCE.getEnvironment_DefaultTools();

        /**
         * The meta object literal for the '<em><b>Label Border Styles</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ENVIRONMENT__LABEL_BORDER_STYLES = DescriptionPackage.eINSTANCE.getEnvironment_LabelBorderStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.SytemColorsPaletteImpl
         * <em>Sytem Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.SytemColorsPaletteImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSytemColorsPalette()
         * @generated
         */
        EClass SYTEM_COLORS_PALETTE = DescriptionPackage.eINSTANCE.getSytemColorsPalette();

        /**
         * The meta object literal for the '<em><b>Entries</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SYTEM_COLORS_PALETTE__ENTRIES = DescriptionPackage.eINSTANCE.getSytemColorsPalette_Entries();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.UserColorsPaletteImpl
         * <em>User Colors Palette</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.UserColorsPaletteImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getUserColorsPalette()
         * @generated
         */
        EClass USER_COLORS_PALETTE = DescriptionPackage.eINSTANCE.getUserColorsPalette();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_COLORS_PALETTE__NAME = DescriptionPackage.eINSTANCE.getUserColorsPalette_Name();

        /**
         * The meta object literal for the '<em><b>Entries</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER_COLORS_PALETTE__ENTRIES = DescriptionPackage.eINSTANCE.getUserColorsPalette_Entries();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.AnnotationEntryImpl
         * <em>Annotation Entry</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.AnnotationEntryImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getAnnotationEntry()
         * @generated
         */
        EClass ANNOTATION_ENTRY = DescriptionPackage.eINSTANCE.getAnnotationEntry();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ANNOTATION_ENTRY__SOURCE = DescriptionPackage.eINSTANCE.getAnnotationEntry_Source();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ANNOTATION_ENTRY__DATA = DescriptionPackage.eINSTANCE.getAnnotationEntry_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.EndUserDocumentedElementImpl
         * <em>End User Documented Element</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.EndUserDocumentedElementImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getEndUserDocumentedElement()
         * @generated
         */
        EClass END_USER_DOCUMENTED_ELEMENT = DescriptionPackage.eINSTANCE.getEndUserDocumentedElement();

        /**
         * The meta object literal for the '
         * <em><b>End User Documentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION = DescriptionPackage.eINSTANCE.getEndUserDocumentedElement_EndUserDocumentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl
         * <em>Identified Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getIdentifiedElement()
         * @generated
         */
        EClass IDENTIFIED_ELEMENT = DescriptionPackage.eINSTANCE.getIdentifiedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IDENTIFIED_ELEMENT__NAME = DescriptionPackage.eINSTANCE.getIdentifiedElement_Name();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IDENTIFIED_ELEMENT__LABEL = DescriptionPackage.eINSTANCE.getIdentifiedElement_Label();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl
         * <em>Computed Color</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getComputedColor()
         * @generated
         */
        EClass COMPUTED_COLOR = DescriptionPackage.eINSTANCE.getComputedColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__RED = DescriptionPackage.eINSTANCE.getComputedColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__GREEN = DescriptionPackage.eINSTANCE.getComputedColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPUTED_COLOR__BLUE = DescriptionPackage.eINSTANCE.getComputedColor_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl
         * <em>DAnnotation Entry</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.impl.DAnnotationEntryImpl
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getDAnnotationEntry()
         * @generated
         */
        EClass DANNOTATION_ENTRY = DescriptionPackage.eINSTANCE.getDAnnotationEntry();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION_ENTRY__SOURCE = DescriptionPackage.eINSTANCE.getDAnnotationEntry_Source();

        /**
         * The meta object literal for the '<em><b>Details</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DANNOTATION_ENTRY__DETAILS = DescriptionPackage.eINSTANCE.getDAnnotationEntry_Details();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.Position
         * <em>Position</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.Position
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getPosition()
         * @generated
         */
        EEnum POSITION = DescriptionPackage.eINSTANCE.getPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.SystemColors
         * <em>System Colors</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.SystemColors
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getSystemColors()
         * @generated
         */
        EEnum SYSTEM_COLORS = DescriptionPackage.eINSTANCE.getSystemColors();

        /**
         * The meta object literal for the '<em>Type Name</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getTypeName()
         * @generated
         */
        EDataType TYPE_NAME = DescriptionPackage.eINSTANCE.getTypeName();

        /**
         * The meta object literal for the '<em>Interpreted Expression</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.lang.String
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getInterpretedExpression()
         * @generated
         */
        EDataType INTERPRETED_EXPRESSION = DescriptionPackage.eINSTANCE.getInterpretedExpression();

        /**
         * The meta object literal for the '<em>Feature Name</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.lang.String
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getFeatureName()
         * @generated
         */
        EDataType FEATURE_NAME = DescriptionPackage.eINSTANCE.getFeatureName();

        /**
         * The meta object literal for the '<em>Image Path</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getImagePath()
         * @generated
         */
        EDataType IMAGE_PATH = DescriptionPackage.eINSTANCE.getImagePath();

        /**
         * The meta object literal for the '<em>URI</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.emf.common.util.URI
         * @see org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl#getURI()
         * @generated
         */
        EDataType URI = DescriptionPackage.eINSTANCE.getURI();

    }

} // DescriptionPackage
