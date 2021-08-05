/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumOption;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.description.StringLayoutOption;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage
 * @generated
 */
public class DescriptionAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionAdapterFactory() {
        if (DescriptionAdapterFactory.modelPackage == null) {
            DescriptionAdapterFactory.modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == DescriptionAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DescriptionAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DescriptionSwitch<Adapter> modelSwitch = new DescriptionSwitch<Adapter>() {
        @Override
        public Adapter caseDiagramDescription(DiagramDescription object) {
            return createDiagramDescriptionAdapter();
        }

        @Override
        public Adapter caseDiagramImportDescription(DiagramImportDescription object) {
            return createDiagramImportDescriptionAdapter();
        }

        @Override
        public Adapter caseDiagramExtensionDescription(DiagramExtensionDescription object) {
            return createDiagramExtensionDescriptionAdapter();
        }

        @Override
        public Adapter caseDiagramElementMapping(DiagramElementMapping object) {
            return createDiagramElementMappingAdapter();
        }

        @Override
        public Adapter caseAbstractNodeMapping(AbstractNodeMapping object) {
            return createAbstractNodeMappingAdapter();
        }

        @Override
        public Adapter caseNodeMapping(NodeMapping object) {
            return createNodeMappingAdapter();
        }

        @Override
        public Adapter caseContainerMapping(ContainerMapping object) {
            return createContainerMappingAdapter();
        }

        @Override
        public Adapter caseNodeMappingImport(NodeMappingImport object) {
            return createNodeMappingImportAdapter();
        }

        @Override
        public Adapter caseContainerMappingImport(ContainerMappingImport object) {
            return createContainerMappingImportAdapter();
        }

        @Override
        public Adapter caseEdgeMapping(EdgeMapping object) {
            return createEdgeMappingAdapter();
        }

        @Override
        public Adapter caseIEdgeMapping(IEdgeMapping object) {
            return createIEdgeMappingAdapter();
        }

        @Override
        public Adapter caseEdgeMappingImport(EdgeMappingImport object) {
            return createEdgeMappingImportAdapter();
        }

        @Override
        public Adapter caseConditionalNodeStyleDescription(ConditionalNodeStyleDescription object) {
            return createConditionalNodeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseConditionalEdgeStyleDescription(ConditionalEdgeStyleDescription object) {
            return createConditionalEdgeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseConditionalContainerStyleDescription(ConditionalContainerStyleDescription object) {
            return createConditionalContainerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseLayout(Layout object) {
            return createLayoutAdapter();
        }

        @Override
        public Adapter caseOrderedTreeLayout(OrderedTreeLayout object) {
            return createOrderedTreeLayoutAdapter();
        }

        @Override
        public Adapter caseCompositeLayout(CompositeLayout object) {
            return createCompositeLayoutAdapter();
        }

        @Override
        public Adapter caseCustomLayoutConfiguration(CustomLayoutConfiguration object) {
            return createCustomLayoutConfigurationAdapter();
        }

        @Override
        public Adapter caseLayoutOption(LayoutOption object) {
            return createLayoutOptionAdapter();
        }

        @Override
        public Adapter caseBooleanLayoutOption(BooleanLayoutOption object) {
            return createBooleanLayoutOptionAdapter();
        }

        @Override
        public Adapter caseStringLayoutOption(StringLayoutOption object) {
            return createStringLayoutOptionAdapter();
        }

        @Override
        public Adapter caseIntegerLayoutOption(IntegerLayoutOption object) {
            return createIntegerLayoutOptionAdapter();
        }

        @Override
        public Adapter caseDoubleLayoutOption(DoubleLayoutOption object) {
            return createDoubleLayoutOptionAdapter();
        }

        @Override
        public Adapter caseEnumLayoutOption(EnumLayoutOption object) {
            return createEnumLayoutOptionAdapter();
        }

        @Override
        public Adapter caseEnumSetLayoutOption(EnumSetLayoutOption object) {
            return createEnumSetLayoutOptionAdapter();
        }

        @Override
        public Adapter caseEnumOption(EnumOption object) {
            return createEnumOptionAdapter();
        }

        @Override
        public Adapter caseEnumLayoutValue(EnumLayoutValue object) {
            return createEnumLayoutValueAdapter();
        }

        @Override
        public Adapter caseMappingBasedDecoration(MappingBasedDecoration object) {
            return createMappingBasedDecorationAdapter();
        }

        @Override
        public Adapter caseLayer(Layer object) {
            return createLayerAdapter();
        }

        @Override
        public Adapter caseAdditionalLayer(AdditionalLayer object) {
            return createAdditionalLayerAdapter();
        }

        @Override
        public Adapter caseDragAndDropTargetDescription(DragAndDropTargetDescription object) {
            return createDragAndDropTargetDescriptionAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseEndUserDocumentedElement(EndUserDocumentedElement object) {
            return createEndUserDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseRepresentationDescription(RepresentationDescription object) {
            return createRepresentationDescriptionAdapter();
        }

        @Override
        public Adapter casePasteTargetDescription(PasteTargetDescription object) {
            return createPasteTargetDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationImportDescription(RepresentationImportDescription object) {
            return createRepresentationImportDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationExtensionDescription(RepresentationExtensionDescription object) {
            return createRepresentationExtensionDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationElementMapping(RepresentationElementMapping object) {
            return createRepresentationElementMappingAdapter();
        }

        @Override
        public Adapter caseAbstractMappingImport(AbstractMappingImport object) {
            return createAbstractMappingImportAdapter();
        }

        @Override
        public Adapter caseConditionalStyleDescription(ConditionalStyleDescription object) {
            return createConditionalStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseDecorationDescription(DecorationDescription object) {
            return createDecorationDescriptionAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.DiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription
     * @generated
     */
    public Adapter createDiagramDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.DiagramImportDescription <em>Diagram Import Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramImportDescription
     * @generated
     */
    public Adapter createDiagramImportDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription <em>Diagram Extension
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramExtensionDescription
     * @generated
     */
    public Adapter createDiagramExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping
     * @generated
     */
    public Adapter createDiagramElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.AbstractNodeMapping
     * <em>Abstract Node Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping
     * @generated
     */
    public Adapter createAbstractNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.NodeMapping <em>Node
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.NodeMapping
     * @generated
     */
    public Adapter createNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.ContainerMapping
     * <em>Container Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping
     * @generated
     */
    public Adapter createContainerMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.NodeMappingImport
     * <em>Node Mapping Import</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.NodeMappingImport
     * @generated
     */
    public Adapter createNodeMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.ContainerMappingImport <em>Container Mapping Import</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ContainerMappingImport
     * @generated
     */
    public Adapter createContainerMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EdgeMapping <em>Edge
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping
     * @generated
     */
    public Adapter createEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.IEdgeMapping
     * <em>IEdge Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.IEdgeMapping
     * @generated
     */
    public Adapter createIEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EdgeMappingImport
     * <em>Edge Mapping Import</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EdgeMappingImport
     * @generated
     */
    public Adapter createEdgeMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription <em>Conditional Node Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription
     * @generated
     */
    public Adapter createConditionalNodeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription <em>Conditional Edge Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription
     * @generated
     */
    public Adapter createConditionalEdgeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription <em>Conditional Container
     * Style Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription
     * @generated
     */
    public Adapter createConditionalContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.Layout
     * <em>Layout</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.Layout
     * @generated
     */
    public Adapter createLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.OrderedTreeLayout
     * <em>Ordered Tree Layout</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.OrderedTreeLayout
     * @generated
     */
    public Adapter createOrderedTreeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.CompositeLayout
     * <em>Composite Layout</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.CompositeLayout
     * @generated
     */
    public Adapter createCompositeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration <em>Custom Layout Configuration</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration
     * @generated
     */
    public Adapter createCustomLayoutConfigurationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.LayoutOption
     * <em>Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.LayoutOption
     * @generated
     */
    public Adapter createLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.BooleanLayoutOption
     * <em>Boolean Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.BooleanLayoutOption
     * @generated
     */
    public Adapter createBooleanLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.StringLayoutOption
     * <em>String Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.StringLayoutOption
     * @generated
     */
    public Adapter createStringLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.IntegerLayoutOption
     * <em>Integer Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.IntegerLayoutOption
     * @generated
     */
    public Adapter createIntegerLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.DoubleLayoutOption
     * <em>Double Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DoubleLayoutOption
     * @generated
     */
    public Adapter createDoubleLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EnumLayoutOption
     * <em>Enum Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutOption
     * @generated
     */
    public Adapter createEnumLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EnumSetLayoutOption
     * <em>Enum Set Layout Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EnumSetLayoutOption
     * @generated
     */
    public Adapter createEnumSetLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EnumOption <em>Enum
     * Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EnumOption
     * @generated
     */
    public Adapter createEnumOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EnumLayoutValue
     * <em>Enum Layout Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutValue
     * @generated
     */
    public Adapter createEnumLayoutValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.MappingBasedDecoration <em>Mapping Based Decoration</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.MappingBasedDecoration
     * @generated
     */
    public Adapter createMappingBasedDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.Layer
     * <em>Layer</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.Layer
     * @generated
     */
    public Adapter createLayerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.AdditionalLayer
     * <em>Additional Layer</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.AdditionalLayer
     * @generated
     */
    public Adapter createAdditionalLayerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.DragAndDropTargetDescription <em>Drag And Drop Target
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DragAndDropTargetDescription
     * @generated
     */
    public Adapter createDragAndDropTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement <em>End User Documented Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * @generated
     */
    public Adapter createEndUserDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription <em>Representation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @generated
     */
    public Adapter createRepresentationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.PasteTargetDescription <em>Paste Target Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.PasteTargetDescription
     * @generated
     */
    public Adapter createPasteTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationImportDescription <em>Representation Import
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationImportDescription
     * @generated
     */
    public Adapter createRepresentationImportDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription <em>Representation Extension
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription
     * @generated
     */
    public Adapter createRepresentationExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping <em>Representation Element
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * @generated
     */
    public Adapter createRepresentationElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport <em>Abstract Mapping Import</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.AbstractMappingImport
     * @generated
     */
    public Adapter createAbstractMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription <em>Conditional Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription
     * @generated
     */
    public Adapter createConditionalStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription <em>Decoration Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDescription
     * @generated
     */
    public Adapter createDecorationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DescriptionAdapterFactory
