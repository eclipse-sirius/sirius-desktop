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
package org.eclipse.sirius.diagram.sequence.template.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl;
import org.eclipse.sirius.diagram.sequence.template.TAbstractMapping;
import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageExtremity;
import org.eclipse.sirius.diagram.sequence.template.TMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TTransformer;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TemplatePackageImpl extends EPackageImpl implements TemplatePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tSequenceDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tMessageExtremityEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tLifelineMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tLifelineStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tConditionalLifelineStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tTransformerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tExecutionMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tExecutionStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tConditionalExecutionStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tBasicMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tSourceTargetMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tReturnMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tCreationMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tDestructionMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tAbstractMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tMessageStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tConditionalMessageStyleEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TemplatePackageImpl() {
        super(eNS_URI, TemplateFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link TemplatePackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TemplatePackage init() {
        if (isInited)
            return (TemplatePackage) EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);

        // Obtain or create and register package
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new TemplatePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        SequencePackageImpl theSequencePackage = (SequencePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SequencePackage.eNS_URI) : SequencePackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(OrderingPackage.eNS_URI) : OrderingPackage.eINSTANCE);

        // Create package meta-data objects
        theTemplatePackage.createPackageContents();
        theSequencePackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theOrderingPackage.createPackageContents();

        // Initialize created meta-data
        theTemplatePackage.initializePackageContents();
        theSequencePackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theOrderingPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTemplatePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TemplatePackage.eNS_URI, theTemplatePackage);
        return theTemplatePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTSequenceDiagram() {
        return tSequenceDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTSequenceDiagram_EndsOrdering() {
        return (EAttribute) tSequenceDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTSequenceDiagram_LifelineMappings() {
        return (EReference) tSequenceDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTSequenceDiagram_MessageMappings() {
        return (EReference) tSequenceDiagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTMessageExtremity() {
        return tMessageExtremityEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTLifelineMapping() {
        return tLifelineMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTLifelineMapping_EolVisibleExpression() {
        return (EAttribute) tLifelineMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineMapping_ExecutionMappings() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineMapping_InstanceRoleStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineMapping_LifelineStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineMapping_EndOfLifeStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineMapping_ConditionalLifeLineStyles() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTLifelineStyle() {
        return tLifelineStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTLifelineStyle_LifelineWidthComputationExpression() {
        return (EAttribute) tLifelineStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTLifelineStyle_LifelineColor() {
        return (EReference) tLifelineStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTConditionalLifelineStyle() {
        return tConditionalLifelineStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTConditionalLifelineStyle_PredicateExpression() {
        return (EAttribute) tConditionalLifelineStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTConditionalLifelineStyle_Style() {
        return (EReference) tConditionalLifelineStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTSequenceDiagram_DomainClass() {
        return (EAttribute) tSequenceDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTTransformer() {
        return tTransformerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTTransformer_Outputs() {
        return (EReference) tTransformerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTExecutionMapping() {
        return tExecutionMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTExecutionMapping_StartingEndFinderExpression() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTExecutionMapping_FinishingEndFinderExpression() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTExecutionMapping_Recursive() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTExecutionMapping_ExecutionMappings() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTExecutionMapping_Style() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTExecutionMapping_ConditionalStyles() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTExecutionStyle() {
        return tExecutionStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTExecutionStyle_BorderSizeComputationExpression() {
        return (EAttribute) tExecutionStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTExecutionStyle_BorderColor() {
        return (EReference) tExecutionStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTExecutionStyle_BackgroundColor() {
        return (EReference) tExecutionStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTConditionalExecutionStyle() {
        return tConditionalExecutionStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTConditionalExecutionStyle_PredicateExpression() {
        return (EAttribute) tConditionalExecutionStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTConditionalExecutionStyle_Style() {
        return (EReference) tConditionalExecutionStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTBasicMessageMapping() {
        return tBasicMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTBasicMessageMapping_Target() {
        return (EReference) tBasicMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTSourceTargetMessageMapping() {
        return tSourceTargetMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTSourceTargetMessageMapping_Source() {
        return (EReference) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTSourceTargetMessageMapping_SourceFinderExpression() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTSourceTargetMessageMapping_TargetFinderExpression() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTSourceTargetMessageMapping_UseDomainElement() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTReturnMessageMapping() {
        return tReturnMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTReturnMessageMapping_InvocationMapping() {
        return (EReference) tReturnMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTReturnMessageMapping_InvocationMessageFinderExpression() {
        return (EAttribute) tReturnMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTCreationMessageMapping() {
        return tCreationMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTCreationMessageMapping_Target() {
        return (EReference) tCreationMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTDestructionMessageMapping() {
        return tDestructionMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTDestructionMessageMapping_Target() {
        return (EReference) tDestructionMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTAbstractMapping() {
        return tAbstractMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTAbstractMapping_Name() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTAbstractMapping_DomainClass() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTAbstractMapping_SemanticCandidatesExpression() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTMessageMapping() {
        return tMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageMapping_SendingEndFinderExpression() {
        return (EAttribute) tMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageMapping_ReceivingEndFinderExpression() {
        return (EAttribute) tMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTMessageMapping_Style() {
        return (EReference) tMessageMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTMessageMapping_ConditionalStyle() {
        return (EReference) tMessageMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTMessageStyle() {
        return tMessageStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTMessageStyle_StrokeColor() {
        return (EReference) tMessageStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageStyle_LineStyle() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageStyle_SourceArrow() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageStyle_TargetArrow() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTMessageStyle_LabelExpression() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTConditionalMessageStyle() {
        return tConditionalMessageStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTConditionalMessageStyle_PredicateExpression() {
        return (EAttribute) tConditionalMessageStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTConditionalMessageStyle_Style() {
        return (EReference) tConditionalMessageStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateFactory getTemplateFactory() {
        return (TemplateFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        tTransformerEClass = createEClass(TTRANSFORMER);
        createEReference(tTransformerEClass, TTRANSFORMER__OUTPUTS);

        tAbstractMappingEClass = createEClass(TABSTRACT_MAPPING);
        createEAttribute(tAbstractMappingEClass, TABSTRACT_MAPPING__NAME);
        createEAttribute(tAbstractMappingEClass, TABSTRACT_MAPPING__DOMAIN_CLASS);
        createEAttribute(tAbstractMappingEClass, TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);

        tSequenceDiagramEClass = createEClass(TSEQUENCE_DIAGRAM);
        createEAttribute(tSequenceDiagramEClass, TSEQUENCE_DIAGRAM__DOMAIN_CLASS);
        createEAttribute(tSequenceDiagramEClass, TSEQUENCE_DIAGRAM__ENDS_ORDERING);
        createEReference(tSequenceDiagramEClass, TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS);
        createEReference(tSequenceDiagramEClass, TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS);

        tMessageExtremityEClass = createEClass(TMESSAGE_EXTREMITY);

        tLifelineMappingEClass = createEClass(TLIFELINE_MAPPING);
        createEAttribute(tLifelineMappingEClass, TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION);
        createEReference(tLifelineMappingEClass, TLIFELINE_MAPPING__EXECUTION_MAPPINGS);
        createEReference(tLifelineMappingEClass, TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE);
        createEReference(tLifelineMappingEClass, TLIFELINE_MAPPING__LIFELINE_STYLE);
        createEReference(tLifelineMappingEClass, TLIFELINE_MAPPING__END_OF_LIFE_STYLE);
        createEReference(tLifelineMappingEClass, TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES);

        tLifelineStyleEClass = createEClass(TLIFELINE_STYLE);
        createEAttribute(tLifelineStyleEClass, TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION);
        createEReference(tLifelineStyleEClass, TLIFELINE_STYLE__LIFELINE_COLOR);

        tConditionalLifelineStyleEClass = createEClass(TCONDITIONAL_LIFELINE_STYLE);
        createEAttribute(tConditionalLifelineStyleEClass, TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalLifelineStyleEClass, TCONDITIONAL_LIFELINE_STYLE__STYLE);

        tExecutionMappingEClass = createEClass(TEXECUTION_MAPPING);
        createEAttribute(tExecutionMappingEClass, TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION);
        createEAttribute(tExecutionMappingEClass, TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION);
        createEAttribute(tExecutionMappingEClass, TEXECUTION_MAPPING__RECURSIVE);
        createEReference(tExecutionMappingEClass, TEXECUTION_MAPPING__EXECUTION_MAPPINGS);
        createEReference(tExecutionMappingEClass, TEXECUTION_MAPPING__STYLE);
        createEReference(tExecutionMappingEClass, TEXECUTION_MAPPING__CONDITIONAL_STYLES);

        tExecutionStyleEClass = createEClass(TEXECUTION_STYLE);
        createEAttribute(tExecutionStyleEClass, TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(tExecutionStyleEClass, TEXECUTION_STYLE__BORDER_COLOR);
        createEReference(tExecutionStyleEClass, TEXECUTION_STYLE__BACKGROUND_COLOR);

        tConditionalExecutionStyleEClass = createEClass(TCONDITIONAL_EXECUTION_STYLE);
        createEAttribute(tConditionalExecutionStyleEClass, TCONDITIONAL_EXECUTION_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalExecutionStyleEClass, TCONDITIONAL_EXECUTION_STYLE__STYLE);

        tMessageMappingEClass = createEClass(TMESSAGE_MAPPING);
        createEAttribute(tMessageMappingEClass, TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION);
        createEAttribute(tMessageMappingEClass, TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION);
        createEReference(tMessageMappingEClass, TMESSAGE_MAPPING__STYLE);
        createEReference(tMessageMappingEClass, TMESSAGE_MAPPING__CONDITIONAL_STYLE);

        tMessageStyleEClass = createEClass(TMESSAGE_STYLE);
        createEReference(tMessageStyleEClass, TMESSAGE_STYLE__STROKE_COLOR);
        createEAttribute(tMessageStyleEClass, TMESSAGE_STYLE__LINE_STYLE);
        createEAttribute(tMessageStyleEClass, TMESSAGE_STYLE__SOURCE_ARROW);
        createEAttribute(tMessageStyleEClass, TMESSAGE_STYLE__TARGET_ARROW);
        createEAttribute(tMessageStyleEClass, TMESSAGE_STYLE__LABEL_EXPRESSION);

        tConditionalMessageStyleEClass = createEClass(TCONDITIONAL_MESSAGE_STYLE);
        createEAttribute(tConditionalMessageStyleEClass, TCONDITIONAL_MESSAGE_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalMessageStyleEClass, TCONDITIONAL_MESSAGE_STYLE__STYLE);

        tBasicMessageMappingEClass = createEClass(TBASIC_MESSAGE_MAPPING);
        createEReference(tBasicMessageMappingEClass, TBASIC_MESSAGE_MAPPING__TARGET);

        tSourceTargetMessageMappingEClass = createEClass(TSOURCE_TARGET_MESSAGE_MAPPING);
        createEReference(tSourceTargetMessageMappingEClass, TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE);
        createEAttribute(tSourceTargetMessageMappingEClass, TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION);
        createEAttribute(tSourceTargetMessageMappingEClass, TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION);
        createEAttribute(tSourceTargetMessageMappingEClass, TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT);

        tReturnMessageMappingEClass = createEClass(TRETURN_MESSAGE_MAPPING);
        createEReference(tReturnMessageMappingEClass, TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING);
        createEAttribute(tReturnMessageMappingEClass, TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION);

        tCreationMessageMappingEClass = createEClass(TCREATION_MESSAGE_MAPPING);
        createEReference(tCreationMessageMappingEClass, TCREATION_MESSAGE_MAPPING__TARGET);

        tDestructionMessageMappingEClass = createEClass(TDESTRUCTION_MESSAGE_MAPPING);
        createEReference(tDestructionMessageMappingEClass, TDESTRUCTION_MESSAGE_MAPPING__TARGET);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        tAbstractMappingEClass.getESuperTypes().add(this.getTTransformer());
        tSequenceDiagramEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationTemplate());
        tSequenceDiagramEClass.getESuperTypes().add(this.getTTransformer());
        tLifelineMappingEClass.getESuperTypes().add(this.getTAbstractMapping());
        tLifelineMappingEClass.getESuperTypes().add(this.getTMessageExtremity());
        tLifelineStyleEClass.getESuperTypes().add(this.getTTransformer());
        tConditionalLifelineStyleEClass.getESuperTypes().add(this.getTTransformer());
        tExecutionMappingEClass.getESuperTypes().add(this.getTAbstractMapping());
        tExecutionMappingEClass.getESuperTypes().add(this.getTMessageExtremity());
        tExecutionStyleEClass.getESuperTypes().add(this.getTTransformer());
        tConditionalExecutionStyleEClass.getESuperTypes().add(this.getTTransformer());
        tMessageMappingEClass.getESuperTypes().add(this.getTAbstractMapping());
        tMessageStyleEClass.getESuperTypes().add(this.getTTransformer());
        tConditionalMessageStyleEClass.getESuperTypes().add(this.getTTransformer());
        tBasicMessageMappingEClass.getESuperTypes().add(this.getTSourceTargetMessageMapping());
        tSourceTargetMessageMappingEClass.getESuperTypes().add(this.getTMessageMapping());
        tReturnMessageMappingEClass.getESuperTypes().add(this.getTMessageMapping());
        tCreationMessageMappingEClass.getESuperTypes().add(this.getTSourceTargetMessageMapping());
        tDestructionMessageMappingEClass.getESuperTypes().add(this.getTSourceTargetMessageMapping());

        // Initialize classes and features; add operations and parameters
        initEClass(tTransformerEClass, TTransformer.class, "TTransformer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTTransformer_Outputs(), theEcorePackage.getEObject(), null, "outputs", null, 0, -1, TTransformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tAbstractMappingEClass, TAbstractMapping.class, "TAbstractMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTAbstractMapping_Name(), theEcorePackage.getEString(), "name", null, 1, 1, TAbstractMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTAbstractMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, TAbstractMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTAbstractMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1, TAbstractMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tSequenceDiagramEClass, TSequenceDiagram.class, "TSequenceDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTSequenceDiagram_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, TSequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTSequenceDiagram_EndsOrdering(), theDescriptionPackage_1.getInterpretedExpression(), "endsOrdering", null, 1, 1, TSequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTSequenceDiagram_LifelineMappings(), this.getTLifelineMapping(), null, "lifelineMappings", null, 0, -1, TSequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTSequenceDiagram_MessageMappings(), this.getTMessageMapping(), null, "messageMappings", null, 0, -1, TSequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tMessageExtremityEClass, TMessageExtremity.class, "TMessageExtremity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(tLifelineMappingEClass, TLifelineMapping.class, "TLifelineMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTLifelineMapping_EolVisibleExpression(), theDescriptionPackage_1.getInterpretedExpression(), "eolVisibleExpression", null, 1, 1, TLifelineMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineMapping_ExecutionMappings(), this.getTExecutionMapping(), null, "executionMappings", null, 0, -1, TLifelineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineMapping_InstanceRoleStyle(), theStylePackage.getNodeStyleDescription(), null, "instanceRoleStyle", null, 1, 1, TLifelineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineMapping_LifelineStyle(), this.getTLifelineStyle(), null, "lifelineStyle", null, 1, 1, TLifelineMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineMapping_EndOfLifeStyle(), theStylePackage.getNodeStyleDescription(), null, "endOfLifeStyle", null, 1, 1, TLifelineMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineMapping_ConditionalLifeLineStyles(), this.getTConditionalLifelineStyle(), null, "conditionalLifeLineStyles", null, 0, -1, TLifelineMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tLifelineStyleEClass, TLifelineStyle.class, "TLifelineStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTLifelineStyle_LifelineWidthComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "lifelineWidthComputationExpression", "0", 0, 1,
                TLifelineStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTLifelineStyle_LifelineColor(), theDescriptionPackage_1.getColorDescription(), null, "lifelineColor", null, 1, 1, TLifelineStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tConditionalLifelineStyleEClass, TConditionalLifelineStyle.class, "TConditionalLifelineStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTConditionalLifelineStyle_PredicateExpression(), theDescriptionPackage_1.getInterpretedExpression(), "predicateExpression", null, 1, 1, TConditionalLifelineStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTConditionalLifelineStyle_Style(), this.getTLifelineStyle(), null, "style", null, 1, 1, TConditionalLifelineStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tExecutionMappingEClass, TExecutionMapping.class, "TExecutionMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTExecutionMapping_StartingEndFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "startingEndFinderExpression", null, 1, 1, TExecutionMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTExecutionMapping_FinishingEndFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "finishingEndFinderExpression", null, 1, 1, TExecutionMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTExecutionMapping_Recursive(), ecorePackage.getEBoolean(), "recursive", null, 1, 1, TExecutionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTExecutionMapping_ExecutionMappings(), this.getTExecutionMapping(), null, "executionMappings", null, 0, -1, TExecutionMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTExecutionMapping_Style(), this.getTExecutionStyle(), null, "style", null, 1, 1, TExecutionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTExecutionMapping_ConditionalStyles(), this.getTConditionalExecutionStyle(), null, "conditionalStyles", null, 0, -1, TExecutionMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tExecutionStyleEClass, TExecutionStyle.class, "TExecutionStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTExecutionStyle_BorderSizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "borderSizeComputationExpression", "1", 0, 1, TExecutionStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTExecutionStyle_BorderColor(), theDescriptionPackage_1.getColorDescription(), null, "borderColor", null, 1, 1, TExecutionStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTExecutionStyle_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, TExecutionStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tConditionalExecutionStyleEClass, TConditionalExecutionStyle.class, "TConditionalExecutionStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTConditionalExecutionStyle_PredicateExpression(), theDescriptionPackage_1.getInterpretedExpression(), "predicateExpression", null, 1, 1, TConditionalExecutionStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTConditionalExecutionStyle_Style(), this.getTExecutionStyle(), null, "style", null, 1, 1, TConditionalExecutionStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tMessageMappingEClass, TMessageMapping.class, "TMessageMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTMessageMapping_SendingEndFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sendingEndFinderExpression", null, 1, 1, TMessageMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTMessageMapping_ReceivingEndFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "receivingEndFinderExpression", null, 1, 1, TMessageMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTMessageMapping_Style(), this.getTMessageStyle(), null, "style", null, 1, 1, TMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTMessageMapping_ConditionalStyle(), this.getTConditionalMessageStyle(), null, "conditionalStyle", null, 0, -1, TMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tMessageStyleEClass, TMessageStyle.class, "TMessageStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTMessageStyle_StrokeColor(), theDescriptionPackage_1.getColorDescription(), null, "strokeColor", null, 1, 1, TMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTMessageStyle_LineStyle(), theDiagramPackage.getLineStyle(), "lineStyle", null, 0, 1, TMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTMessageStyle_SourceArrow(), theDiagramPackage.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, TMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTMessageStyle_TargetArrow(), theDiagramPackage.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, TMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTMessageStyle_LabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "labelExpression", "<%name%>", 0, 1, TMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tConditionalMessageStyleEClass, TConditionalMessageStyle.class, "TConditionalMessageStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTConditionalMessageStyle_PredicateExpression(), theDescriptionPackage_1.getInterpretedExpression(), "predicateExpression", null, 1, 1, TConditionalMessageStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTConditionalMessageStyle_Style(), this.getTMessageStyle(), null, "style", null, 1, 1, TConditionalMessageStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tBasicMessageMappingEClass, TBasicMessageMapping.class, "TBasicMessageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTBasicMessageMapping_Target(), this.getTMessageExtremity(), null, "target", null, 1, -1, TBasicMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tSourceTargetMessageMappingEClass, TSourceTargetMessageMapping.class, "TSourceTargetMessageMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTSourceTargetMessageMapping_Source(), this.getTMessageExtremity(), null, "source", null, 1, -1, TSourceTargetMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTSourceTargetMessageMapping_SourceFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sourceFinderExpression", null, 0, 1,
                TSourceTargetMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTSourceTargetMessageMapping_TargetFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "targetFinderExpression", null, 1, 1,
                TSourceTargetMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTSourceTargetMessageMapping_UseDomainElement(), theEcorePackage.getEBoolean(), "useDomainElement", "false", 0, 1, TSourceTargetMessageMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tReturnMessageMappingEClass, TReturnMessageMapping.class, "TReturnMessageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTReturnMessageMapping_InvocationMapping(), this.getTBasicMessageMapping(), null, "invocationMapping", null, 1, 1, TReturnMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTReturnMessageMapping_InvocationMessageFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "invocationMessageFinderExpression", null, 1, 1,
                TReturnMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tCreationMessageMappingEClass, TCreationMessageMapping.class, "TCreationMessageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTCreationMessageMapping_Target(), this.getTLifelineMapping(), null, "target", null, 1, -1, TCreationMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tDestructionMessageMappingEClass, TDestructionMessageMapping.class, "TDestructionMessageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTDestructionMessageMapping_Target(), this.getTLifelineMapping(), null, "target", null, 1, -1, TDestructionMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getTAbstractMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTSequenceDiagram_EndsOrdering(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTLifelineMapping_EolVisibleExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getTLifelineStyle_LifelineWidthComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getTConditionalLifelineStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getTExecutionMapping_StartingEndFinderExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getTExecutionMapping_FinishingEndFinderExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getTExecutionStyle_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getTConditionalExecutionStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getTMessageMapping_SendingEndFinderExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getTMessageMapping_ReceivingEndFinderExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getTMessageStyle_LabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getTConditionalMessageStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getTSourceTargetMessageMapping_SourceFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTSourceTargetMessageMapping_TargetFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] { "returnType", "an EObject." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
        addAnnotation(getTAbstractMapping_SemanticCandidatesExpression(), source, new String[] { "containerView", "the parent view of potential candidates.", "diagram", "the current DDiagram.",
                "viewpoint", "(deprecated) the current DDiagram.", "viewPoint", "(deprecated) the current DDiagram." });
        addAnnotation(getTSequenceDiagram_EndsOrdering(), source, new String[] { "eventEnds", "a List<EObject> containing the semantic event ends." });
        addAnnotation(getTLifelineMapping_EolVisibleExpression(), source, new String[] { "containerView", "the view that sould contain the potential views of the checked elements.", "container",
                "the semantic element of $containerView.", "viewpoint", "(deprecated) the current DSemanticDiagram.", "diagram", "the current DSemanticDiagram." });
        addAnnotation(getTLifelineStyle_LifelineWidthComputationExpression(), source, new String[] {});
        addAnnotation(getTConditionalLifelineStyle_PredicateExpression(), source, new String[] { "view", "the current view.", "container", "the semantic container." });
        addAnnotation(getTExecutionMapping_StartingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTExecutionMapping_FinishingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTExecutionStyle_BorderSizeComputationExpression(), source, new String[] {});
        addAnnotation(getTConditionalExecutionStyle_PredicateExpression(), source, new String[] { "view", "the current view.", "container", "the semantic container." });
        addAnnotation(getTMessageMapping_SendingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTMessageMapping_ReceivingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTMessageStyle_LabelExpression(), source, new String[] { "diagram", "the current DSemanticDiagram.", "view", "the current view for which the label is calculated." });
        addAnnotation(getTConditionalMessageStyle_PredicateExpression(), source, new String[] { "view", "the current view.", "container", "the semantic container." });
        addAnnotation(getTSourceTargetMessageMapping_SourceFinderExpression(), source, new String[] { "diagram", "the current DDiagram.", "viewpoint", "(deprecated) the current DDiagram.",
                "viewPoint", "(deprecated) the current DDiagram." });
        addAnnotation(getTSourceTargetMessageMapping_TargetFinderExpression(), source, new String[] { "diagram", "the current DDiagram.", "viewpoint", "(deprecated) the current DDiagram.",
                "viewPoint", "(deprecated) the current DDiagram." });
        addAnnotation(getTReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] {});
    }

} // TemplatePackageImpl
