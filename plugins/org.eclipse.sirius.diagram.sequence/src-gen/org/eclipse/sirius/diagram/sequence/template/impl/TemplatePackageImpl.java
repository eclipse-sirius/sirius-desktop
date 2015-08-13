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
        super(TemplatePackage.eNS_URI, TemplateFactory.eINSTANCE);
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
        if (TemplatePackageImpl.isInited) {
            return (TemplatePackage) EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);
        }

        // Obtain or create and register package
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.get(TemplatePackage.eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE
                .get(TemplatePackage.eNS_URI) : new TemplatePackageImpl());

        TemplatePackageImpl.isInited = true;

        // Initialize simple dependencies
        DiagramPackage.eINSTANCE.eClass();

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
    @Override
    public EClass getTSequenceDiagram() {
        return tSequenceDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTSequenceDiagram_EndsOrdering() {
        return (EAttribute) tSequenceDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTSequenceDiagram_LifelineMappings() {
        return (EReference) tSequenceDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTSequenceDiagram_MessageMappings() {
        return (EReference) tSequenceDiagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTMessageExtremity() {
        return tMessageExtremityEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTLifelineMapping() {
        return tLifelineMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTLifelineMapping_EolVisibleExpression() {
        return (EAttribute) tLifelineMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineMapping_ExecutionMappings() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineMapping_InstanceRoleStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineMapping_LifelineStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineMapping_EndOfLifeStyle() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineMapping_ConditionalLifeLineStyles() {
        return (EReference) tLifelineMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTLifelineStyle() {
        return tLifelineStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTLifelineStyle_LifelineWidthComputationExpression() {
        return (EAttribute) tLifelineStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTLifelineStyle_LifelineColor() {
        return (EReference) tLifelineStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTConditionalLifelineStyle() {
        return tConditionalLifelineStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTConditionalLifelineStyle_PredicateExpression() {
        return (EAttribute) tConditionalLifelineStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTConditionalLifelineStyle_Style() {
        return (EReference) tConditionalLifelineStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTSequenceDiagram_DomainClass() {
        return (EAttribute) tSequenceDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTTransformer() {
        return tTransformerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTTransformer_Outputs() {
        return (EReference) tTransformerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTExecutionMapping() {
        return tExecutionMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTExecutionMapping_StartingEndFinderExpression() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTExecutionMapping_FinishingEndFinderExpression() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTExecutionMapping_Recursive() {
        return (EAttribute) tExecutionMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTExecutionMapping_ExecutionMappings() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTExecutionMapping_Style() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTExecutionMapping_ConditionalStyles() {
        return (EReference) tExecutionMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTExecutionStyle() {
        return tExecutionStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTExecutionStyle_BorderSizeComputationExpression() {
        return (EAttribute) tExecutionStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTExecutionStyle_BorderColor() {
        return (EReference) tExecutionStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTExecutionStyle_BackgroundColor() {
        return (EReference) tExecutionStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTConditionalExecutionStyle() {
        return tConditionalExecutionStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTConditionalExecutionStyle_PredicateExpression() {
        return (EAttribute) tConditionalExecutionStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTConditionalExecutionStyle_Style() {
        return (EReference) tConditionalExecutionStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTBasicMessageMapping() {
        return tBasicMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTBasicMessageMapping_Target() {
        return (EReference) tBasicMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTSourceTargetMessageMapping() {
        return tSourceTargetMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTSourceTargetMessageMapping_Source() {
        return (EReference) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTSourceTargetMessageMapping_SourceFinderExpression() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTSourceTargetMessageMapping_TargetFinderExpression() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTSourceTargetMessageMapping_UseDomainElement() {
        return (EAttribute) tSourceTargetMessageMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTReturnMessageMapping() {
        return tReturnMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTReturnMessageMapping_InvocationMapping() {
        return (EReference) tReturnMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTReturnMessageMapping_InvocationMessageFinderExpression() {
        return (EAttribute) tReturnMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTCreationMessageMapping() {
        return tCreationMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTCreationMessageMapping_Target() {
        return (EReference) tCreationMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTDestructionMessageMapping() {
        return tDestructionMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTDestructionMessageMapping_Target() {
        return (EReference) tDestructionMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTAbstractMapping() {
        return tAbstractMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTAbstractMapping_Name() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTAbstractMapping_DomainClass() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTAbstractMapping_SemanticCandidatesExpression() {
        return (EAttribute) tAbstractMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTMessageMapping() {
        return tMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageMapping_SendingEndFinderExpression() {
        return (EAttribute) tMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageMapping_ReceivingEndFinderExpression() {
        return (EAttribute) tMessageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTMessageMapping_Style() {
        return (EReference) tMessageMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTMessageMapping_ConditionalStyle() {
        return (EReference) tMessageMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTMessageStyle() {
        return tMessageStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTMessageStyle_StrokeColor() {
        return (EReference) tMessageStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageStyle_LineStyle() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageStyle_SourceArrow() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageStyle_TargetArrow() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTMessageStyle_LabelExpression() {
        return (EAttribute) tMessageStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTConditionalMessageStyle() {
        return tConditionalMessageStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTConditionalMessageStyle_PredicateExpression() {
        return (EAttribute) tConditionalMessageStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTConditionalMessageStyle_Style() {
        return (EReference) tConditionalMessageStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        tTransformerEClass = createEClass(TemplatePackage.TTRANSFORMER);
        createEReference(tTransformerEClass, TemplatePackage.TTRANSFORMER__OUTPUTS);

        tAbstractMappingEClass = createEClass(TemplatePackage.TABSTRACT_MAPPING);
        createEAttribute(tAbstractMappingEClass, TemplatePackage.TABSTRACT_MAPPING__NAME);
        createEAttribute(tAbstractMappingEClass, TemplatePackage.TABSTRACT_MAPPING__DOMAIN_CLASS);
        createEAttribute(tAbstractMappingEClass, TemplatePackage.TABSTRACT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);

        tSequenceDiagramEClass = createEClass(TemplatePackage.TSEQUENCE_DIAGRAM);
        createEAttribute(tSequenceDiagramEClass, TemplatePackage.TSEQUENCE_DIAGRAM__DOMAIN_CLASS);
        createEAttribute(tSequenceDiagramEClass, TemplatePackage.TSEQUENCE_DIAGRAM__ENDS_ORDERING);
        createEReference(tSequenceDiagramEClass, TemplatePackage.TSEQUENCE_DIAGRAM__LIFELINE_MAPPINGS);
        createEReference(tSequenceDiagramEClass, TemplatePackage.TSEQUENCE_DIAGRAM__MESSAGE_MAPPINGS);

        tMessageExtremityEClass = createEClass(TemplatePackage.TMESSAGE_EXTREMITY);

        tLifelineMappingEClass = createEClass(TemplatePackage.TLIFELINE_MAPPING);
        createEAttribute(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION);
        createEReference(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS);
        createEReference(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE);
        createEReference(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE);
        createEReference(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE);
        createEReference(tLifelineMappingEClass, TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES);

        tLifelineStyleEClass = createEClass(TemplatePackage.TLIFELINE_STYLE);
        createEAttribute(tLifelineStyleEClass, TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION);
        createEReference(tLifelineStyleEClass, TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR);

        tConditionalLifelineStyleEClass = createEClass(TemplatePackage.TCONDITIONAL_LIFELINE_STYLE);
        createEAttribute(tConditionalLifelineStyleEClass, TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalLifelineStyleEClass, TemplatePackage.TCONDITIONAL_LIFELINE_STYLE__STYLE);

        tExecutionMappingEClass = createEClass(TemplatePackage.TEXECUTION_MAPPING);
        createEAttribute(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION);
        createEAttribute(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION);
        createEAttribute(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__RECURSIVE);
        createEReference(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS);
        createEReference(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__STYLE);
        createEReference(tExecutionMappingEClass, TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES);

        tExecutionStyleEClass = createEClass(TemplatePackage.TEXECUTION_STYLE);
        createEAttribute(tExecutionStyleEClass, TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(tExecutionStyleEClass, TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR);
        createEReference(tExecutionStyleEClass, TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR);

        tConditionalExecutionStyleEClass = createEClass(TemplatePackage.TCONDITIONAL_EXECUTION_STYLE);
        createEAttribute(tConditionalExecutionStyleEClass, TemplatePackage.TCONDITIONAL_EXECUTION_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalExecutionStyleEClass, TemplatePackage.TCONDITIONAL_EXECUTION_STYLE__STYLE);

        tMessageMappingEClass = createEClass(TemplatePackage.TMESSAGE_MAPPING);
        createEAttribute(tMessageMappingEClass, TemplatePackage.TMESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION);
        createEAttribute(tMessageMappingEClass, TemplatePackage.TMESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION);
        createEReference(tMessageMappingEClass, TemplatePackage.TMESSAGE_MAPPING__STYLE);
        createEReference(tMessageMappingEClass, TemplatePackage.TMESSAGE_MAPPING__CONDITIONAL_STYLE);

        tMessageStyleEClass = createEClass(TemplatePackage.TMESSAGE_STYLE);
        createEReference(tMessageStyleEClass, TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR);
        createEAttribute(tMessageStyleEClass, TemplatePackage.TMESSAGE_STYLE__LINE_STYLE);
        createEAttribute(tMessageStyleEClass, TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW);
        createEAttribute(tMessageStyleEClass, TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW);
        createEAttribute(tMessageStyleEClass, TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION);

        tConditionalMessageStyleEClass = createEClass(TemplatePackage.TCONDITIONAL_MESSAGE_STYLE);
        createEAttribute(tConditionalMessageStyleEClass, TemplatePackage.TCONDITIONAL_MESSAGE_STYLE__PREDICATE_EXPRESSION);
        createEReference(tConditionalMessageStyleEClass, TemplatePackage.TCONDITIONAL_MESSAGE_STYLE__STYLE);

        tBasicMessageMappingEClass = createEClass(TemplatePackage.TBASIC_MESSAGE_MAPPING);
        createEReference(tBasicMessageMappingEClass, TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET);

        tSourceTargetMessageMappingEClass = createEClass(TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING);
        createEReference(tSourceTargetMessageMappingEClass, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE);
        createEAttribute(tSourceTargetMessageMappingEClass, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION);
        createEAttribute(tSourceTargetMessageMappingEClass, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION);
        createEAttribute(tSourceTargetMessageMappingEClass, TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT);

        tReturnMessageMappingEClass = createEClass(TemplatePackage.TRETURN_MESSAGE_MAPPING);
        createEReference(tReturnMessageMappingEClass, TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MAPPING);
        createEAttribute(tReturnMessageMappingEClass, TemplatePackage.TRETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION);

        tCreationMessageMappingEClass = createEClass(TemplatePackage.TCREATION_MESSAGE_MAPPING);
        createEReference(tCreationMessageMappingEClass, TemplatePackage.TCREATION_MESSAGE_MAPPING__TARGET);

        tDestructionMessageMappingEClass = createEClass(TemplatePackage.TDESTRUCTION_MESSAGE_MAPPING);
        createEReference(tDestructionMessageMappingEClass, TemplatePackage.TDESTRUCTION_MESSAGE_MAPPING__TARGET);
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
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(TemplatePackage.eNAME);
        setNsPrefix(TemplatePackage.eNS_PREFIX);
        setNsURI(TemplatePackage.eNS_URI);

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
        initEClass(tTransformerEClass, TTransformer.class, "TTransformer", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTTransformer_Outputs(),
                theEcorePackage.getEObject(),
                null,
                "outputs", null, 0, -1, TTransformer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tAbstractMappingEClass, TAbstractMapping.class, "TAbstractMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTAbstractMapping_Name(),
                theEcorePackage.getEString(),
                "name", null, 1, 1, TAbstractMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTAbstractMapping_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, TAbstractMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTAbstractMapping_SemanticCandidatesExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticCandidatesExpression", null, 0, 1, TAbstractMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tSequenceDiagramEClass, TSequenceDiagram.class, "TSequenceDiagram", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTSequenceDiagram_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, TSequenceDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTSequenceDiagram_EndsOrdering(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "endsOrdering", null, 1, 1, TSequenceDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTSequenceDiagram_LifelineMappings(),
                this.getTLifelineMapping(),
                null,
                "lifelineMappings", null, 0, -1, TSequenceDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTSequenceDiagram_MessageMappings(),
                this.getTMessageMapping(),
                null,
                "messageMappings", null, 0, -1, TSequenceDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tMessageExtremityEClass, TMessageExtremity.class, "TMessageExtremity", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(tLifelineMappingEClass, TLifelineMapping.class, "TLifelineMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTLifelineMapping_EolVisibleExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "eolVisibleExpression", null, 1, 1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTLifelineMapping_ExecutionMappings(),
                this.getTExecutionMapping(),
                null,
                "executionMappings", null, 0, -1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTLifelineMapping_InstanceRoleStyle(),
                theStylePackage.getNodeStyleDescription(),
                null,
                "instanceRoleStyle", null, 1, 1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTLifelineMapping_LifelineStyle(),
                this.getTLifelineStyle(),
                null,
                "lifelineStyle", null, 1, 1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTLifelineMapping_EndOfLifeStyle(),
                theStylePackage.getNodeStyleDescription(),
                null,
                "endOfLifeStyle", null, 1, 1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTLifelineMapping_ConditionalLifeLineStyles(),
                this.getTConditionalLifelineStyle(),
                null,
                "conditionalLifeLineStyles", null, 0, -1, TLifelineMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tLifelineStyleEClass, TLifelineStyle.class, "TLifelineStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTLifelineStyle_LifelineWidthComputationExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "lifelineWidthComputationExpression", "0", 0, 1, TLifelineStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getTLifelineStyle_LifelineColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "lifelineColor", null, 1, 1, TLifelineStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tConditionalLifelineStyleEClass, TConditionalLifelineStyle.class,
                "TConditionalLifelineStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTConditionalLifelineStyle_PredicateExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "predicateExpression", null, 1, 1, TConditionalLifelineStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTConditionalLifelineStyle_Style(),
                this.getTLifelineStyle(),
                null,
                "style", null, 1, 1, TConditionalLifelineStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tExecutionMappingEClass, TExecutionMapping.class, "TExecutionMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTExecutionMapping_StartingEndFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "startingEndFinderExpression", null, 1, 1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTExecutionMapping_FinishingEndFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "finishingEndFinderExpression", null, 1, 1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTExecutionMapping_Recursive(),
                ecorePackage.getEBoolean(),
                "recursive", null, 1, 1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTExecutionMapping_ExecutionMappings(),
                this.getTExecutionMapping(),
                null,
                "executionMappings", null, 0, -1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTExecutionMapping_Style(),
                this.getTExecutionStyle(),
                null,
                "style", null, 1, 1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTExecutionMapping_ConditionalStyles(),
                this.getTConditionalExecutionStyle(),
                null,
                "conditionalStyles", null, 0, -1, TExecutionMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tExecutionStyleEClass, TExecutionStyle.class, "TExecutionStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTExecutionStyle_BorderSizeComputationExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "borderSizeComputationExpression", "1", 0, 1, TExecutionStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getTExecutionStyle_BorderColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "borderColor", null, 1, 1, TExecutionStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTExecutionStyle_BackgroundColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "backgroundColor", null, 1, 1, TExecutionStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tConditionalExecutionStyleEClass, TConditionalExecutionStyle.class,
                "TConditionalExecutionStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTConditionalExecutionStyle_PredicateExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "predicateExpression", null, 1, 1, TConditionalExecutionStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTConditionalExecutionStyle_Style(),
                this.getTExecutionStyle(),
                null,
                "style", null, 1, 1, TConditionalExecutionStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tMessageMappingEClass, TMessageMapping.class, "TMessageMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTMessageMapping_SendingEndFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "sendingEndFinderExpression", null, 1, 1, TMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTMessageMapping_ReceivingEndFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "receivingEndFinderExpression", null, 1, 1, TMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTMessageMapping_Style(),
                this.getTMessageStyle(),
                null,
                "style", null, 1, 1, TMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTMessageMapping_ConditionalStyle(),
                this.getTConditionalMessageStyle(),
                null,
                "conditionalStyle", null, 0, -1, TMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tMessageStyleEClass, TMessageStyle.class, "TMessageStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTMessageStyle_StrokeColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "strokeColor", null, 1, 1, TMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTMessageStyle_LineStyle(),
                theDiagramPackage.getLineStyle(),
                "lineStyle", null, 0, 1, TMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTMessageStyle_SourceArrow(),
                theDiagramPackage.getEdgeArrows(),
                "sourceArrow", "NoDecoration", 1, 1, TMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getTMessageStyle_TargetArrow(),
                theDiagramPackage.getEdgeArrows(),
                "targetArrow", "InputArrow", 1, 1, TMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getTMessageStyle_LabelExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "labelExpression", "feature:name", 0, 1, TMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(tConditionalMessageStyleEClass, TConditionalMessageStyle.class,
                "TConditionalMessageStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTConditionalMessageStyle_PredicateExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "predicateExpression", null, 1, 1, TConditionalMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTConditionalMessageStyle_Style(),
                this.getTMessageStyle(),
                null,
                "style", null, 1, 1, TConditionalMessageStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tBasicMessageMappingEClass, TBasicMessageMapping.class, "TBasicMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTBasicMessageMapping_Target(),
                this.getTMessageExtremity(),
                null,
                "target", null, 1, -1, TBasicMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tSourceTargetMessageMappingEClass, TSourceTargetMessageMapping.class,
                "TSourceTargetMessageMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTSourceTargetMessageMapping_Source(),
                this.getTMessageExtremity(),
                null,
                "source", null, 1, -1, TSourceTargetMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTSourceTargetMessageMapping_SourceFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "sourceFinderExpression", null, 0, 1, TSourceTargetMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTSourceTargetMessageMapping_TargetFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "targetFinderExpression", null, 1, 1, TSourceTargetMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTSourceTargetMessageMapping_UseDomainElement(),
                theEcorePackage.getEBoolean(),
                "useDomainElement", "false", 0, 1, TSourceTargetMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(tReturnMessageMappingEClass, TReturnMessageMapping.class, "TReturnMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTReturnMessageMapping_InvocationMapping(),
                this.getTBasicMessageMapping(),
                null,
                "invocationMapping", null, 1, 1, TReturnMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTReturnMessageMapping_InvocationMessageFinderExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "invocationMessageFinderExpression", null, 1, 1, TReturnMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tCreationMessageMappingEClass, TCreationMessageMapping.class,
                "TCreationMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTCreationMessageMapping_Target(),
                this.getTLifelineMapping(),
                null,
                "target", null, 1, -1, TCreationMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(tDestructionMessageMappingEClass, TDestructionMessageMapping.class,
                "TDestructionMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTDestructionMessageMapping_Target(),
                this.getTLifelineMapping(),
                null,
                "target", null, 1, -1, TDestructionMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

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
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getTAbstractMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSequenceDiagram_EndsOrdering(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTLifelineMapping_EolVisibleExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTLifelineStyle_LifelineWidthComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTConditionalLifelineStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTExecutionMapping_StartingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTExecutionMapping_FinishingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTExecutionStyle_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTConditionalExecutionStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTMessageMapping_SendingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTMessageMapping_ReceivingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTMessageStyle_LabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTConditionalMessageStyle_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSourceTargetMessageMapping_SourceFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSourceTargetMessageMapping_TargetFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getTAbstractMapping_SemanticCandidatesExpression(), source, new String[] { "containerView", "the parent view of potential candidates.", //$NON-NLS-1$ //$NON-NLS-2$
            "diagram", "the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewpoint", "(deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewPoint", "(deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSequenceDiagram_EndsOrdering(), source, new String[] { "eventEnds", "a List<EObject> containing the semantic event ends." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTLifelineMapping_EolVisibleExpression(), source, new String[] { "containerView", "the view that sould contain the potential views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "the semantic element of $containerView.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewpoint", "(deprecated) the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "diagram", "the current DSemanticDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTLifelineStyle_LifelineWidthComputationExpression(), source, new String[] {});
        addAnnotation(getTConditionalLifelineStyle_PredicateExpression(), source, new String[] { "view", "the current view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTExecutionMapping_StartingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTExecutionMapping_FinishingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTExecutionStyle_BorderSizeComputationExpression(), source, new String[] {});
        addAnnotation(getTConditionalExecutionStyle_PredicateExpression(), source, new String[] { "view", "the current view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTMessageMapping_SendingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTMessageMapping_ReceivingEndFinderExpression(), source, new String[] {});
        addAnnotation(getTMessageStyle_LabelExpression(), source, new String[] { "diagram", "the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "view", "the current view for which the label is calculated." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTConditionalMessageStyle_PredicateExpression(), source, new String[] { "view", "the current view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSourceTargetMessageMapping_SourceFinderExpression(), source, new String[] { "diagram", "the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewpoint", "(deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewPoint", "(deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTSourceTargetMessageMapping_TargetFinderExpression(), source, new String[] { "diagram", "the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewpoint", "(deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "viewPoint", "(deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] {});
    }

} // TemplatePackageImpl
