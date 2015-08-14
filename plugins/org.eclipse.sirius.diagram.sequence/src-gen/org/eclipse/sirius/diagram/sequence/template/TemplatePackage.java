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
package org.eclipse.sirius.diagram.sequence.template;

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
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.diagram.sequence.template.TemplateFactory
 * @model kind="package"
 * @generated
 */
public interface TemplatePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "template"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/sequence/template/2.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "template"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TemplatePackage eINSTANCE = org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl
     * <em>TSequence Diagram</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTSequenceDiagram()
     * @generated
     */
    int TSEQUENCE_DIAGRAM = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageExtremityImpl
     * <em>TMessage Extremity</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageExtremityImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageExtremity()
     * @generated
     */
    int TMESSAGE_EXTREMITY = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl
     * <em>TLifeline Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTLifelineMapping()
     * @generated
     */
    int TLIFELINE_MAPPING = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TTransformerImpl
     * <em>TTransformer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TTransformerImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTTransformer()
     * @generated
     */
    int TTRANSFORMER = 0;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TTRANSFORMER__OUTPUTS = 0;

    /**
     * The number of structural features of the '<em>TTransformer</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TTRANSFORMER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TAbstractMappingImpl
     * <em>TAbstract Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TAbstractMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTAbstractMapping()
     * @generated
     */
    int TABSTRACT_MAPPING = 1;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABSTRACT_MAPPING__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABSTRACT_MAPPING__NAME = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABSTRACT_MAPPING__DOMAIN_CLASS = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>TAbstract Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABSTRACT_MAPPING_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__NAME = DescriptionPackage.REPRESENTATION_TEMPLATE__NAME;

    /**
     * The feature id for the '<em><b>Owned Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__OWNED_REPRESENTATIONS = DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__OUTPUTS = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__DOMAIN_CLASS = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Ends Ordering</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__ENDS_ORDERING = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Lifeline Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Message Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>TSequence Diagram</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSEQUENCE_DIAGRAM_FEATURE_COUNT = DescriptionPackage.REPRESENTATION_TEMPLATE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>TMessage Extremity</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_EXTREMITY_FEATURE_COUNT = 0;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__OUTPUTS = TemplatePackage.TABSTRACT_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__NAME = TemplatePackage.TABSTRACT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__DOMAIN_CLASS = TemplatePackage.TABSTRACT_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Eol Visible Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Execution Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__EXECUTION_MAPPINGS = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Instance Role Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Lifeline Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__LIFELINE_STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>End Of Life Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__END_OF_LIFE_STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Life Line Styles</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>TLifeline Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_MAPPING_FEATURE_COUNT = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl
     * <em>TLifeline Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTLifelineStyle()
     * @generated
     */
    int TLIFELINE_STYLE = 5;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '
     * <em><b>Lifeline Width Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Lifeline Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TLIFELINE_STYLE__LIFELINE_COLOR = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>TLifeline Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TLIFELINE_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl
     * <em>TConditional Lifeline Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalLifelineStyle()
     * @generated
     */
    int TCONDITIONAL_LIFELINE_STYLE = 6;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCONDITIONAL_LIFELINE_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_LIFELINE_STYLE__STYLE = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>TConditional Lifeline Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_LIFELINE_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl
     * <em>TExecution Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTExecutionMapping()
     * @generated
     */
    int TEXECUTION_MAPPING = 7;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__OUTPUTS = TemplatePackage.TABSTRACT_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__NAME = TemplatePackage.TABSTRACT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__DOMAIN_CLASS = TemplatePackage.TABSTRACT_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Recursive</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__RECURSIVE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Execution Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__EXECUTION_MAPPINGS = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING__CONDITIONAL_STYLES = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>TExecution Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_MAPPING_FEATURE_COUNT = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl
     * <em>TExecution Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTExecutionStyle()
     * @generated
     */
    int TEXECUTION_STYLE = 8;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_STYLE__BORDER_COLOR = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXECUTION_STYLE__BACKGROUND_COLOR = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>TExecution Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXECUTION_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalExecutionStyleImpl
     * <em>TConditional Execution Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalExecutionStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalExecutionStyle()
     * @generated
     */
    int TCONDITIONAL_EXECUTION_STYLE = 9;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCONDITIONAL_EXECUTION_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_EXECUTION_STYLE__PREDICATE_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_EXECUTION_STYLE__STYLE = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>TConditional Execution Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_EXECUTION_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl
     * <em>TMessage Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageMapping()
     * @generated
     */
    int TMESSAGE_MAPPING = 10;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__OUTPUTS = TemplatePackage.TABSTRACT_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__NAME = TemplatePackage.TABSTRACT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TABSTRACT_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>TMessage Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TABSTRACT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl
     * <em>TMessage Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageStyle()
     * @generated
     */
    int TMESSAGE_STYLE = 11;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__STROKE_COLOR = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__LINE_STYLE = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__SOURCE_ARROW = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__TARGET_ARROW = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE__LABEL_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>TMessage Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TMESSAGE_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalMessageStyleImpl
     * <em>TConditional Message Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalMessageStyleImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalMessageStyle()
     * @generated
     */
    int TCONDITIONAL_MESSAGE_STYLE = 12;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCONDITIONAL_MESSAGE_STYLE__OUTPUTS = TemplatePackage.TTRANSFORMER__OUTPUTS;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_MESSAGE_STYLE__PREDICATE_EXPRESSION = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_MESSAGE_STYLE__STYLE = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>TConditional Message Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCONDITIONAL_MESSAGE_STYLE_FEATURE_COUNT = TemplatePackage.TTRANSFORMER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl
     * <em>TSource Target Message Mapping</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTSourceTargetMessageMapping()
     * @generated
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING = 14;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__OUTPUTS = TemplatePackage.TMESSAGE_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__NAME = TemplatePackage.TMESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TMESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__STYLE = TemplatePackage.TMESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>TSource Target Message Mapping</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl
     * <em>TBasic Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTBasicMessageMapping()
     * @generated
     */
    int TBASIC_MESSAGE_MAPPING = 13;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__OUTPUTS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__NAME = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__CONDITIONAL_STYLE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__SOURCE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING__TARGET = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>TBasic Message Mapping</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TBASIC_MESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl
     * <em>TReturn Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTReturnMessageMapping()
     * @generated
     */
    int TRETURN_MESSAGE_MAPPING = 15;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__OUTPUTS = TemplatePackage.TMESSAGE_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__NAME = TemplatePackage.TMESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TMESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__STYLE = TemplatePackage.TMESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE;

    /**
     * The feature id for the '<em><b>Invocation Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Invocation Message Finder Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>TReturn Message Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TRETURN_MESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TMESSAGE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TCreationMessageMappingImpl
     * <em>TCreation Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TCreationMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTCreationMessageMapping()
     * @generated
     */
    int TCREATION_MESSAGE_MAPPING = 16;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__OUTPUTS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__NAME = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__CONDITIONAL_STYLE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__SOURCE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING__TARGET = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>TCreation Message Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TCREATION_MESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.template.impl.TDestructionMessageMappingImpl
     * <em>TDestruction Message Mapping</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TDestructionMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTDestructionMessageMapping()
     * @generated
     */
    int TDESTRUCTION_MESSAGE_MAPPING = 17;

    /**
     * The feature id for the '<em><b>Outputs</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__OUTPUTS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__OUTPUTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__NAME = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__DOMAIN_CLASS = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Style</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__CONDITIONAL_STYLE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__SOURCE = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING__TARGET = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>TDestruction Message Mapping</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TDESTRUCTION_MESSAGE_MAPPING_FEATURE_COUNT = TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram
     * <em>TSequence Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TSequence Diagram</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram
     * @generated
     */
    EClass getTSequenceDiagram();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getEndsOrdering
     * <em>Ends Ordering</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Ends Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getEndsOrdering()
     * @see #getTSequenceDiagram()
     * @generated
     */
    EAttribute getTSequenceDiagram_EndsOrdering();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getLifelineMappings
     * <em>Lifeline Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Lifeline Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getLifelineMappings()
     * @see #getTSequenceDiagram()
     * @generated
     */
    EReference getTSequenceDiagram_LifelineMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getMessageMappings
     * <em>Message Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Message Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getMessageMappings()
     * @see #getTSequenceDiagram()
     * @generated
     */
    EReference getTSequenceDiagram_MessageMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageExtremity
     * <em>TMessage Extremity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TMessage Extremity</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageExtremity
     * @generated
     */
    EClass getTMessageExtremity();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping
     * <em>TLifeline Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TLifeline Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping
     * @generated
     */
    EClass getTLifelineMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEolVisibleExpression
     * <em>Eol Visible Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Eol Visible Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEolVisibleExpression()
     * @see #getTLifelineMapping()
     * @generated
     */
    EAttribute getTLifelineMapping_EolVisibleExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getExecutionMappings
     * <em>Execution Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Execution Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getExecutionMappings()
     * @see #getTLifelineMapping()
     * @generated
     */
    EReference getTLifelineMapping_ExecutionMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getInstanceRoleStyle
     * <em>Instance Role Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Instance Role Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getInstanceRoleStyle()
     * @see #getTLifelineMapping()
     * @generated
     */
    EReference getTLifelineMapping_InstanceRoleStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getLifelineStyle
     * <em>Lifeline Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Lifeline Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getLifelineStyle()
     * @see #getTLifelineMapping()
     * @generated
     */
    EReference getTLifelineMapping_LifelineStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEndOfLifeStyle
     * <em>End Of Life Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>End Of Life Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getEndOfLifeStyle()
     * @see #getTLifelineMapping()
     * @generated
     */
    EReference getTLifelineMapping_EndOfLifeStyle();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getConditionalLifeLineStyles
     * <em>Conditional Life Line Styles</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Conditional Life Line Styles</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping#getConditionalLifeLineStyles()
     * @see #getTLifelineMapping()
     * @generated
     */
    EReference getTLifelineMapping_ConditionalLifeLineStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle
     * <em>TLifeline Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>TLifeline Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineStyle
     * @generated
     */
    EClass getTLifelineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineWidthComputationExpression
     * <em>Lifeline Width Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Lifeline Width Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineWidthComputationExpression()
     * @see #getTLifelineStyle()
     * @generated
     */
    EAttribute getTLifelineStyle_LifelineWidthComputationExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineColor
     * <em>Lifeline Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Lifeline Color</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineColor()
     * @see #getTLifelineStyle()
     * @generated
     */
    EReference getTLifelineStyle_LifelineColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle
     * <em>TConditional Lifeline Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TConditional Lifeline Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle
     * @generated
     */
    EClass getTConditionalLifelineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle#getPredicateExpression()
     * @see #getTConditionalLifelineStyle()
     * @generated
     */
    EAttribute getTConditionalLifelineStyle_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle#getStyle()
     * @see #getTConditionalLifelineStyle()
     * @generated
     */
    EReference getTConditionalLifelineStyle_Style();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram#getDomainClass()
     * @see #getTSequenceDiagram()
     * @generated
     */
    EAttribute getTSequenceDiagram_DomainClass();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TTransformer
     * <em>TTransformer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>TTransformer</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TTransformer
     * @generated
     */
    EClass getTTransformer();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TTransformer#getOutputs
     * <em>Outputs</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Outputs</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TTransformer#getOutputs()
     * @see #getTTransformer()
     * @generated
     */
    EReference getTTransformer_Outputs();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping
     * <em>TExecution Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TExecution Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping
     * @generated
     */
    EClass getTExecutionMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStartingEndFinderExpression
     * <em>Starting End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Starting End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStartingEndFinderExpression()
     * @see #getTExecutionMapping()
     * @generated
     */
    EAttribute getTExecutionMapping_StartingEndFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getFinishingEndFinderExpression
     * <em>Finishing End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Finishing End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getFinishingEndFinderExpression()
     * @see #getTExecutionMapping()
     * @generated
     */
    EAttribute getTExecutionMapping_FinishingEndFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#isRecursive
     * <em>Recursive</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Recursive</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#isRecursive()
     * @see #getTExecutionMapping()
     * @generated
     */
    EAttribute getTExecutionMapping_Recursive();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getExecutionMappings
     * <em>Execution Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Execution Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getExecutionMappings()
     * @see #getTExecutionMapping()
     * @generated
     */
    EReference getTExecutionMapping_ExecutionMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getStyle()
     * @see #getTExecutionMapping()
     * @generated
     */
    EReference getTExecutionMapping_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping#getConditionalStyles()
     * @see #getTExecutionMapping()
     * @generated
     */
    EReference getTExecutionMapping_ConditionalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle
     * <em>TExecution Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TExecution Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionStyle
     * @generated
     */
    EClass getTExecutionStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderSizeComputationExpression()
     * @see #getTExecutionStyle()
     * @generated
     */
    EAttribute getTExecutionStyle_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Border Color</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderColor()
     * @see #getTExecutionStyle()
     * @generated
     */
    EReference getTExecutionStyle_BorderColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBackgroundColor()
     * @see #getTExecutionStyle()
     * @generated
     */
    EReference getTExecutionStyle_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle
     * <em>TConditional Execution Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TConditional Execution Style</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle
     * @generated
     */
    EClass getTConditionalExecutionStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle#getPredicateExpression()
     * @see #getTConditionalExecutionStyle()
     * @generated
     */
    EAttribute getTConditionalExecutionStyle_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle#getStyle()
     * @see #getTConditionalExecutionStyle()
     * @generated
     */
    EReference getTConditionalExecutionStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping
     * <em>TBasic Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TBasic Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping
     * @generated
     */
    EClass getTBasicMessageMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Target</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping#getTarget()
     * @see #getTBasicMessageMapping()
     * @generated
     */
    EReference getTBasicMessageMapping_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping
     * <em>TSource Target Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>TSource Target Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping
     * @generated
     */
    EClass getTSourceTargetMessageMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Source</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSource()
     * @see #getTSourceTargetMessageMapping()
     * @generated
     */
    EReference getTSourceTargetMessageMapping_Source();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSourceFinderExpression
     * <em>Source Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Source Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getSourceFinderExpression()
     * @see #getTSourceTargetMessageMapping()
     * @generated
     */
    EAttribute getTSourceTargetMessageMapping_SourceFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getTargetFinderExpression
     * <em>Target Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Target Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#getTargetFinderExpression()
     * @see #getTSourceTargetMessageMapping()
     * @generated
     */
    EAttribute getTSourceTargetMessageMapping_TargetFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#isUseDomainElement
     * <em>Use Domain Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Use Domain Element</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping#isUseDomainElement()
     * @see #getTSourceTargetMessageMapping()
     * @generated
     */
    EAttribute getTSourceTargetMessageMapping_UseDomainElement();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping
     * <em>TReturn Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TReturn Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping
     * @generated
     */
    EClass getTReturnMessageMapping();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMapping
     * <em>Invocation Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Invocation Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMapping()
     * @see #getTReturnMessageMapping()
     * @generated
     */
    EReference getTReturnMessageMapping_InvocationMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMessageFinderExpression
     * <em>Invocation Message Finder Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Invocation Message Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMessageFinderExpression()
     * @see #getTReturnMessageMapping()
     * @generated
     */
    EAttribute getTReturnMessageMapping_InvocationMessageFinderExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping
     * <em>TCreation Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TCreation Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping
     * @generated
     */
    EClass getTCreationMessageMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Target</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping#getTarget()
     * @see #getTCreationMessageMapping()
     * @generated
     */
    EReference getTCreationMessageMapping_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping
     * <em>TDestruction Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TDestruction Message Mapping</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping
     * @generated
     */
    EClass getTDestructionMessageMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Target</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping#getTarget()
     * @see #getTDestructionMessageMapping()
     * @generated
     */
    EReference getTDestructionMessageMapping_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TAbstractMapping
     * <em>TAbstract Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TAbstract Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TAbstractMapping
     * @generated
     */
    EClass getTAbstractMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getName()
     * @see #getTAbstractMapping()
     * @generated
     */
    EAttribute getTAbstractMapping_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getDomainClass()
     * @see #getTAbstractMapping()
     * @generated
     */
    EAttribute getTAbstractMapping_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TAbstractMapping#getSemanticCandidatesExpression()
     * @see #getTAbstractMapping()
     * @generated
     */
    EAttribute getTAbstractMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping
     * <em>TMessage Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>TMessage Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping
     * @generated
     */
    EClass getTMessageMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getSendingEndFinderExpression
     * <em>Sending End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Sending End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getSendingEndFinderExpression()
     * @see #getTMessageMapping()
     * @generated
     */
    EAttribute getTMessageMapping_SendingEndFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getReceivingEndFinderExpression
     * <em>Receiving End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Receiving End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getReceivingEndFinderExpression()
     * @see #getTMessageMapping()
     * @generated
     */
    EAttribute getTMessageMapping_ReceivingEndFinderExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getStyle()
     * @see #getTMessageMapping()
     * @generated
     */
    EReference getTMessageMapping_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getConditionalStyle
     * <em>Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getConditionalStyle()
     * @see #getTMessageMapping()
     * @generated
     */
    EReference getTMessageMapping_ConditionalStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle
     * <em>TMessage Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>TMessage Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle
     * @generated
     */
    EClass getTMessageStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getStrokeColor
     * <em>Stroke Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Stroke Color</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getStrokeColor()
     * @see #getTMessageStyle()
     * @generated
     */
    EReference getTMessageStyle_StrokeColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLineStyle
     * <em>Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLineStyle()
     * @see #getTMessageStyle()
     * @generated
     */
    EAttribute getTMessageStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getSourceArrow
     * <em>Source Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getSourceArrow()
     * @see #getTMessageStyle()
     * @generated
     */
    EAttribute getTMessageStyle_SourceArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getTargetArrow
     * <em>Target Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getTargetArrow()
     * @see #getTMessageStyle()
     * @generated
     */
    EAttribute getTMessageStyle_TargetArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLabelExpression()
     * @see #getTMessageStyle()
     * @generated
     */
    EAttribute getTMessageStyle_LabelExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle
     * <em>TConditional Message Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>TConditional Message Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle
     * @generated
     */
    EClass getTConditionalMessageStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle#getPredicateExpression()
     * @see #getTConditionalMessageStyle()
     * @generated
     */
    EAttribute getTConditionalMessageStyle_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle#getStyle()
     * @see #getTConditionalMessageStyle()
     * @generated
     */
    EReference getTConditionalMessageStyle_Style();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TemplateFactory getTemplateFactory();

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
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl
         * <em>TSequence Diagram</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TSequenceDiagramImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTSequenceDiagram()
         * @generated
         */
        EClass TSEQUENCE_DIAGRAM = TemplatePackage.eINSTANCE.getTSequenceDiagram();

        /**
         * The meta object literal for the '<em><b>Ends Ordering</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TSEQUENCE_DIAGRAM__ENDS_ORDERING = TemplatePackage.eINSTANCE.getTSequenceDiagram_EndsOrdering();

        /**
         * The meta object literal for the '<em><b>Lifeline Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS = TemplatePackage.eINSTANCE.getTSequenceDiagram_LifelineMappings();

        /**
         * The meta object literal for the '<em><b>Message Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS = TemplatePackage.eINSTANCE.getTSequenceDiagram_MessageMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageExtremityImpl
         * <em>TMessage Extremity</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageExtremityImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageExtremity()
         * @generated
         */
        EClass TMESSAGE_EXTREMITY = TemplatePackage.eINSTANCE.getTMessageExtremity();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl
         * <em>TLifeline Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TLifelineMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTLifelineMapping()
         * @generated
         */
        EClass TLIFELINE_MAPPING = TemplatePackage.eINSTANCE.getTLifelineMapping();

        /**
         * The meta object literal for the '
         * <em><b>Eol Visible Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION = TemplatePackage.eINSTANCE.getTLifelineMapping_EolVisibleExpression();

        /**
         * The meta object literal for the '<em><b>Execution Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_MAPPING__EXECUTION_MAPPINGS = TemplatePackage.eINSTANCE.getTLifelineMapping_ExecutionMappings();

        /**
         * The meta object literal for the '<em><b>Instance Role Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE = TemplatePackage.eINSTANCE.getTLifelineMapping_InstanceRoleStyle();

        /**
         * The meta object literal for the '<em><b>Lifeline Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_MAPPING__LIFELINE_STYLE = TemplatePackage.eINSTANCE.getTLifelineMapping_LifelineStyle();

        /**
         * The meta object literal for the '<em><b>End Of Life Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_MAPPING__END_OF_LIFE_STYLE = TemplatePackage.eINSTANCE.getTLifelineMapping_EndOfLifeStyle();

        /**
         * The meta object literal for the '
         * <em><b>Conditional Life Line Styles</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES = TemplatePackage.eINSTANCE.getTLifelineMapping_ConditionalLifeLineStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl
         * <em>TLifeline Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTLifelineStyle()
         * @generated
         */
        EClass TLIFELINE_STYLE = TemplatePackage.eINSTANCE.getTLifelineStyle();

        /**
         * The meta object literal for the '
         * <em><b>Lifeline Width Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION = TemplatePackage.eINSTANCE.getTLifelineStyle_LifelineWidthComputationExpression();

        /**
         * The meta object literal for the '<em><b>Lifeline Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TLIFELINE_STYLE__LIFELINE_COLOR = TemplatePackage.eINSTANCE.getTLifelineStyle_LifelineColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl
         * <em>TConditional Lifeline Style</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalLifelineStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalLifelineStyle()
         * @generated
         */
        EClass TCONDITIONAL_LIFELINE_STYLE = TemplatePackage.eINSTANCE.getTConditionalLifelineStyle();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION = TemplatePackage.eINSTANCE.getTConditionalLifelineStyle_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TCONDITIONAL_LIFELINE_STYLE__STYLE = TemplatePackage.eINSTANCE.getTConditionalLifelineStyle_Style();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TSEQUENCE_DIAGRAM__DOMAIN_CLASS = TemplatePackage.eINSTANCE.getTSequenceDiagram_DomainClass();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TTransformerImpl
         * <em>TTransformer</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TTransformerImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTTransformer()
         * @generated
         */
        EClass TTRANSFORMER = TemplatePackage.eINSTANCE.getTTransformer();

        /**
         * The meta object literal for the '<em><b>Outputs</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TTRANSFORMER__OUTPUTS = TemplatePackage.eINSTANCE.getTTransformer_Outputs();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl
         * <em>TExecution Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TExecutionMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTExecutionMapping()
         * @generated
         */
        EClass TEXECUTION_MAPPING = TemplatePackage.eINSTANCE.getTExecutionMapping();

        /**
         * The meta object literal for the '
         * <em><b>Starting End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTExecutionMapping_StartingEndFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Finishing End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTExecutionMapping_FinishingEndFinderExpression();

        /**
         * The meta object literal for the '<em><b>Recursive</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXECUTION_MAPPING__RECURSIVE = TemplatePackage.eINSTANCE.getTExecutionMapping_Recursive();

        /**
         * The meta object literal for the '<em><b>Execution Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TEXECUTION_MAPPING__EXECUTION_MAPPINGS = TemplatePackage.eINSTANCE.getTExecutionMapping_ExecutionMappings();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEXECUTION_MAPPING__STYLE = TemplatePackage.eINSTANCE.getTExecutionMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TEXECUTION_MAPPING__CONDITIONAL_STYLES = TemplatePackage.eINSTANCE.getTExecutionMapping_ConditionalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl
         * <em>TExecution Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTExecutionStyle()
         * @generated
         */
        EClass TEXECUTION_STYLE = TemplatePackage.eINSTANCE.getTExecutionStyle();

        /**
         * The meta object literal for the '
         * <em><b>Border Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = TemplatePackage.eINSTANCE.getTExecutionStyle_BorderSizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEXECUTION_STYLE__BORDER_COLOR = TemplatePackage.eINSTANCE.getTExecutionStyle_BorderColor();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEXECUTION_STYLE__BACKGROUND_COLOR = TemplatePackage.eINSTANCE.getTExecutionStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalExecutionStyleImpl
         * <em>TConditional Execution Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalExecutionStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalExecutionStyle()
         * @generated
         */
        EClass TCONDITIONAL_EXECUTION_STYLE = TemplatePackage.eINSTANCE.getTConditionalExecutionStyle();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TCONDITIONAL_EXECUTION_STYLE__PREDICATE_EXPRESSION = TemplatePackage.eINSTANCE.getTConditionalExecutionStyle_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TCONDITIONAL_EXECUTION_STYLE__STYLE = TemplatePackage.eINSTANCE.getTConditionalExecutionStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl
         * <em>TBasic Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTBasicMessageMapping()
         * @generated
         */
        EClass TBASIC_MESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTBasicMessageMapping();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TBASIC_MESSAGE_MAPPING__TARGET = TemplatePackage.eINSTANCE.getTBasicMessageMapping_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl
         * <em>TSource Target Message Mapping</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TSourceTargetMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTSourceTargetMessageMapping()
         * @generated
         */
        EClass TSOURCE_TARGET_MESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTSourceTargetMessageMapping();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE = TemplatePackage.eINSTANCE.getTSourceTargetMessageMapping_Source();

        /**
         * The meta object literal for the '
         * <em><b>Source Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTSourceTargetMessageMapping_SourceFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Target Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTSourceTargetMessageMapping_TargetFinderExpression();

        /**
         * The meta object literal for the '<em><b>Use Domain Element</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = TemplatePackage.eINSTANCE.getTSourceTargetMessageMapping_UseDomainElement();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl
         * <em>TReturn Message Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TReturnMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTReturnMessageMapping()
         * @generated
         */
        EClass TRETURN_MESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTReturnMessageMapping();

        /**
         * The meta object literal for the '<em><b>Invocation Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING = TemplatePackage.eINSTANCE.getTReturnMessageMapping_InvocationMapping();

        /**
         * The meta object literal for the '
         * <em><b>Invocation Message Finder Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTReturnMessageMapping_InvocationMessageFinderExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TCreationMessageMappingImpl
         * <em>TCreation Message Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TCreationMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTCreationMessageMapping()
         * @generated
         */
        EClass TCREATION_MESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTCreationMessageMapping();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TCREATION_MESSAGE_MAPPING__TARGET = TemplatePackage.eINSTANCE.getTCreationMessageMapping_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TDestructionMessageMappingImpl
         * <em>TDestruction Message Mapping</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TDestructionMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTDestructionMessageMapping()
         * @generated
         */
        EClass TDESTRUCTION_MESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTDestructionMessageMapping();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TDESTRUCTION_MESSAGE_MAPPING__TARGET = TemplatePackage.eINSTANCE.getTDestructionMessageMapping_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TAbstractMappingImpl
         * <em>TAbstract Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TAbstractMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTAbstractMapping()
         * @generated
         */
        EClass TABSTRACT_MAPPING = TemplatePackage.eINSTANCE.getTAbstractMapping();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABSTRACT_MAPPING__NAME = TemplatePackage.eINSTANCE.getTAbstractMapping_Name();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABSTRACT_MAPPING__DOMAIN_CLASS = TemplatePackage.eINSTANCE.getTAbstractMapping_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidates Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = TemplatePackage.eINSTANCE.getTAbstractMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl
         * <em>TMessage Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageMapping()
         * @generated
         */
        EClass TMESSAGE_MAPPING = TemplatePackage.eINSTANCE.getTMessageMapping();

        /**
         * The meta object literal for the '
         * <em><b>Sending End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTMessageMapping_SendingEndFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Receiving End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = TemplatePackage.eINSTANCE.getTMessageMapping_ReceivingEndFinderExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TMESSAGE_MAPPING__STYLE = TemplatePackage.eINSTANCE.getTMessageMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Style</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TMESSAGE_MAPPING__CONDITIONAL_STYLE = TemplatePackage.eINSTANCE.getTMessageMapping_ConditionalStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl
         * <em>TMessage Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTMessageStyle()
         * @generated
         */
        EClass TMESSAGE_STYLE = TemplatePackage.eINSTANCE.getTMessageStyle();

        /**
         * The meta object literal for the '<em><b>Stroke Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TMESSAGE_STYLE__STROKE_COLOR = TemplatePackage.eINSTANCE.getTMessageStyle_StrokeColor();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_STYLE__LINE_STYLE = TemplatePackage.eINSTANCE.getTMessageStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_STYLE__SOURCE_ARROW = TemplatePackage.eINSTANCE.getTMessageStyle_SourceArrow();

        /**
         * The meta object literal for the '<em><b>Target Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_STYLE__TARGET_ARROW = TemplatePackage.eINSTANCE.getTMessageStyle_TargetArrow();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TMESSAGE_STYLE__LABEL_EXPRESSION = TemplatePackage.eINSTANCE.getTMessageStyle_LabelExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.template.impl.TConditionalMessageStyleImpl
         * <em>TConditional Message Style</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TConditionalMessageStyleImpl
         * @see org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl#getTConditionalMessageStyle()
         * @generated
         */
        EClass TCONDITIONAL_MESSAGE_STYLE = TemplatePackage.eINSTANCE.getTConditionalMessageStyle();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TCONDITIONAL_MESSAGE_STYLE__PREDICATE_EXPRESSION = TemplatePackage.eINSTANCE.getTConditionalMessageStyle_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TCONDITIONAL_MESSAGE_STYLE__STYLE = TemplatePackage.eINSTANCE.getTConditionalMessageStyle_Style();

    }

} // TemplatePackage
