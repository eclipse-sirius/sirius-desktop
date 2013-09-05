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
package org.eclipse.sirius.description.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.description.AbstractMappingImport;
import org.eclipse.sirius.description.AbstractNodeMapping;
import org.eclipse.sirius.description.AnnotationEntry;
import org.eclipse.sirius.description.ColorDescription;
import org.eclipse.sirius.description.ColorStep;
import org.eclipse.sirius.description.Component;
import org.eclipse.sirius.description.CompositeLayout;
import org.eclipse.sirius.description.ComputedColor;
import org.eclipse.sirius.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.description.ConditionalStyleDescription;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.ContainerMappingImport;
import org.eclipse.sirius.description.Customization;
import org.eclipse.sirius.description.DAnnotation;
import org.eclipse.sirius.description.DAnnotationEntry;
import org.eclipse.sirius.description.DModelElement;
import org.eclipse.sirius.description.DecorationDescription;
import org.eclipse.sirius.description.DecorationDescriptionsSet;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.DiagramExtensionDescription;
import org.eclipse.sirius.description.DiagramImportDescription;
import org.eclipse.sirius.description.DocumentedElement;
import org.eclipse.sirius.description.DragAndDropTargetDescription;
import org.eclipse.sirius.description.EAttributeCustomization;
import org.eclipse.sirius.description.EReferenceCustomization;
import org.eclipse.sirius.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.EdgeMappingImport;
import org.eclipse.sirius.description.EndUserDocumentedElement;
import org.eclipse.sirius.description.Environment;
import org.eclipse.sirius.description.FeatureExtensionDescription;
import org.eclipse.sirius.description.FixedColor;
import org.eclipse.sirius.description.Group;
import org.eclipse.sirius.description.IEdgeMapping;
import org.eclipse.sirius.description.IVSMElementCustomization;
import org.eclipse.sirius.description.IdentifiedElement;
import org.eclipse.sirius.description.InterpolatedColor;
import org.eclipse.sirius.description.JavaExtension;
import org.eclipse.sirius.description.Layer;
import org.eclipse.sirius.description.Layout;
import org.eclipse.sirius.description.MappingBasedDecoration;
import org.eclipse.sirius.description.MetamodelExtensionSetting;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.description.NodeMappingImport;
import org.eclipse.sirius.description.OptionalLayer;
import org.eclipse.sirius.description.OrderedTreeLayout;
import org.eclipse.sirius.description.PasteTargetDescription;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.RepresentationElementMapping;
import org.eclipse.sirius.description.RepresentationExtensionDescription;
import org.eclipse.sirius.description.RepresentationImportDescription;
import org.eclipse.sirius.description.RepresentationTemplate;
import org.eclipse.sirius.description.SelectionDescription;
import org.eclipse.sirius.description.SemanticBasedDecoration;
import org.eclipse.sirius.description.SystemColor;
import org.eclipse.sirius.description.SytemColorsPalette;
import org.eclipse.sirius.description.UserColor;
import org.eclipse.sirius.description.UserColorsPalette;
import org.eclipse.sirius.description.UserFixedColor;
import org.eclipse.sirius.description.VSMElementCustomization;
import org.eclipse.sirius.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.description.Sirius;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.description.DescriptionPackage
 * @generated
 */
public class DescriptionAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */

    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch the delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DescriptionSwitch<Adapter> modelSwitch = new DescriptionSwitch<Adapter>() {
        @Override
        public Adapter caseGroup(Group object) {
            return createGroupAdapter();
        }

        @Override
        public Adapter caseComponent(Component object) {
            return createComponentAdapter();
        }

        @Override
        public Adapter caseSirius(Sirius object) {
            return createSiriusAdapter();
        }

        @Override
        public Adapter caseFeatureExtensionDescription(FeatureExtensionDescription object) {
            return createFeatureExtensionDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationDescription(RepresentationDescription object) {
            return createRepresentationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationTemplate(RepresentationTemplate object) {
            return createRepresentationTemplateAdapter();
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
        public Adapter caseMetamodelExtensionSetting(MetamodelExtensionSetting object) {
            return createMetamodelExtensionSettingAdapter();
        }

        @Override
        public Adapter caseJavaExtension(JavaExtension object) {
            return createJavaExtensionAdapter();
        }

        @Override
        public Adapter caseRepresentationElementMapping(RepresentationElementMapping object) {
            return createRepresentationElementMappingAdapter();
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
        public Adapter caseAbstractMappingImport(AbstractMappingImport object) {
            return createAbstractMappingImportAdapter();
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
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseDModelElement(DModelElement object) {
            return createDModelElementAdapter();
        }

        @Override
        public Adapter caseDAnnotation(DAnnotation object) {
            return createDAnnotationAdapter();
        }

        @Override
        public Adapter caseConditionalStyleDescription(ConditionalStyleDescription object) {
            return createConditionalStyleDescriptionAdapter();
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
        public Adapter caseDragAndDropTargetDescription(DragAndDropTargetDescription object) {
            return createDragAndDropTargetDescriptionAdapter();
        }

        @Override
        public Adapter casePasteTargetDescription(PasteTargetDescription object) {
            return createPasteTargetDescriptionAdapter();
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
        public Adapter caseDecorationDescriptionsSet(DecorationDescriptionsSet object) {
            return createDecorationDescriptionsSetAdapter();
        }

        @Override
        public Adapter caseDecorationDescription(DecorationDescription object) {
            return createDecorationDescriptionAdapter();
        }

        @Override
        public Adapter caseMappingBasedDecoration(MappingBasedDecoration object) {
            return createMappingBasedDecorationAdapter();
        }

        @Override
        public Adapter caseSemanticBasedDecoration(SemanticBasedDecoration object) {
            return createSemanticBasedDecorationAdapter();
        }

        @Override
        public Adapter caseLayer(Layer object) {
            return createLayerAdapter();
        }

        @Override
        public Adapter caseOptionalLayer(OptionalLayer object) {
            return createOptionalLayerAdapter();
        }

        @Override
        public Adapter caseCustomization(Customization object) {
            return createCustomizationAdapter();
        }

        @Override
        public Adapter caseIVSMElementCustomization(IVSMElementCustomization object) {
            return createIVSMElementCustomizationAdapter();
        }

        @Override
        public Adapter caseVSMElementCustomization(VSMElementCustomization object) {
            return createVSMElementCustomizationAdapter();
        }

        @Override
        public Adapter caseVSMElementCustomizationReuse(VSMElementCustomizationReuse object) {
            return createVSMElementCustomizationReuseAdapter();
        }

        @Override
        public Adapter caseEStructuralFeatureCustomization(EStructuralFeatureCustomization object) {
            return createEStructuralFeatureCustomizationAdapter();
        }

        @Override
        public Adapter caseEAttributeCustomization(EAttributeCustomization object) {
            return createEAttributeCustomizationAdapter();
        }

        @Override
        public Adapter caseEReferenceCustomization(EReferenceCustomization object) {
            return createEReferenceCustomizationAdapter();
        }

        @Override
        public Adapter caseSelectionDescription(SelectionDescription object) {
            return createSelectionDescriptionAdapter();
        }

        @Override
        public Adapter caseColorDescription(ColorDescription object) {
            return createColorDescriptionAdapter();
        }

        @Override
        public Adapter caseSystemColor(SystemColor object) {
            return createSystemColorAdapter();
        }

        @Override
        public Adapter caseInterpolatedColor(InterpolatedColor object) {
            return createInterpolatedColorAdapter();
        }

        @Override
        public Adapter caseColorStep(ColorStep object) {
            return createColorStepAdapter();
        }

        @Override
        public Adapter caseFixedColor(FixedColor object) {
            return createFixedColorAdapter();
        }

        @Override
        public Adapter caseUserFixedColor(UserFixedColor object) {
            return createUserFixedColorAdapter();
        }

        @Override
        public Adapter caseUserColor(UserColor object) {
            return createUserColorAdapter();
        }

        @Override
        public Adapter caseEnvironment(Environment object) {
            return createEnvironmentAdapter();
        }

        @Override
        public Adapter caseSytemColorsPalette(SytemColorsPalette object) {
            return createSytemColorsPaletteAdapter();
        }

        @Override
        public Adapter caseUserColorsPalette(UserColorsPalette object) {
            return createUserColorsPaletteAdapter();
        }

        @Override
        public Adapter caseAnnotationEntry(AnnotationEntry object) {
            return createAnnotationEntryAdapter();
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
        public Adapter caseComputedColor(ComputedColor object) {
            return createComputedColorAdapter();
        }

        @Override
        public Adapter caseDAnnotationEntry(DAnnotationEntry object) {
            return createDAnnotationEntryAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Component <em>Component</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Component
     * @generated
     */
    public Adapter createComponentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.RepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.RepresentationDescription
     * @generated
     */
    public Adapter createRepresentationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.RepresentationTemplate
     * <em>Representation Template</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.RepresentationTemplate
     * @generated
     */
    public Adapter createRepresentationTemplateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.RepresentationImportDescription
     * <em>Representation Import Description</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.RepresentationImportDescription
     * @generated
     */
    public Adapter createRepresentationImportDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.RepresentationExtensionDescription
     * <em>Representation Extension Description</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.RepresentationExtensionDescription
     * @generated
     */
    public Adapter createRepresentationExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DiagramDescription
     * @generated
     */
    public Adapter createDiagramDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DiagramImportDescription
     * <em>Diagram Import Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DiagramImportDescription
     * @generated
     */
    public Adapter createDiagramImportDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DiagramExtensionDescription
     * <em>Diagram Extension Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DiagramExtensionDescription
     * @generated
     */
    public Adapter createDiagramExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Sirius <em>Sirius</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Sirius
     * @generated
     */
    public Adapter createSiriusAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.FeatureExtensionDescription
     * <em>Feature Extension Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.FeatureExtensionDescription
     * @generated
     */
    public Adapter createFeatureExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.MetamodelExtensionSetting
     * <em>Metamodel Extension Setting</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.MetamodelExtensionSetting
     * @generated
     */
    public Adapter createMetamodelExtensionSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.JavaExtension
     * <em>Java Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.JavaExtension
     * @generated
     */
    public Adapter createJavaExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.RepresentationElementMapping
     * <em>Representation Element Mapping</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.RepresentationElementMapping
     * @generated
     */
    public Adapter createRepresentationElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DiagramElementMapping
     * @generated
     */
    public Adapter createDiagramElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.AbstractNodeMapping
     * <em>Abstract Node Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.AbstractNodeMapping
     * @generated
     */
    public Adapter createAbstractNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.NodeMapping
     * <em>Node Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.NodeMapping
     * @generated
     */
    public Adapter createNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.NodeMappingImport
     * <em>Node Mapping Import</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.NodeMappingImport
     * @generated
     */
    public Adapter createNodeMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ContainerMappingImport
     * <em>Container Mapping Import</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ContainerMappingImport
     * @generated
     */
    public Adapter createContainerMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ContainerMapping
     * <em>Container Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ContainerMapping
     * @generated
     */
    public Adapter createContainerMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.AbstractMappingImport
     * <em>Abstract Mapping Import</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.AbstractMappingImport
     * @generated
     */
    public Adapter createAbstractMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EdgeMapping
     * <em>Edge Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EdgeMapping
     * @generated
     */
    public Adapter createEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.IEdgeMapping
     * <em>IEdge Mapping</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.IEdgeMapping
     * @generated
     */
    public Adapter createIEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EdgeMappingImport
     * <em>Edge Mapping Import</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EdgeMappingImport
     * @generated
     */
    public Adapter createEdgeMappingImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Group <em>Group</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Group
     * @generated
     */
    public Adapter createGroupAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DModelElement
     * @generated
     */
    public Adapter createDModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DAnnotation
     * <em>DAnnotation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DAnnotation
     * @generated
     */
    public Adapter createDAnnotationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ConditionalStyleDescription
     * <em>Conditional Style Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ConditionalStyleDescription
     * @generated
     */
    public Adapter createConditionalStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ConditionalNodeStyleDescription
     * <em>Conditional Node Style Description</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ConditionalNodeStyleDescription
     * @generated
     */
    public Adapter createConditionalNodeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ConditionalEdgeStyleDescription
     * <em>Conditional Edge Style Description</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ConditionalEdgeStyleDescription
     * @generated
     */
    public Adapter createConditionalEdgeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ConditionalContainerStyleDescription
     * <em>Conditional Container Style Description</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ConditionalContainerStyleDescription
     * @generated
     */
    public Adapter createConditionalContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DragAndDropTargetDescription
     * <em>Drag And Drop Target Description</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DragAndDropTargetDescription
     * @generated
     */
    public Adapter createDragAndDropTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.PasteTargetDescription
     * <em>Paste Target Description</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.PasteTargetDescription
     * @generated
     */
    public Adapter createPasteTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Layout <em>Layout</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Layout
     * @generated
     */
    public Adapter createLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.OrderedTreeLayout
     * <em>Ordered Tree Layout</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.OrderedTreeLayout
     * @generated
     */
    public Adapter createOrderedTreeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.CompositeLayout
     * <em>Composite Layout</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.CompositeLayout
     * @generated
     */
    public Adapter createCompositeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DecorationDescriptionsSet
     * <em>Decoration Descriptions Set</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DecorationDescriptionsSet
     * @generated
     */
    public Adapter createDecorationDescriptionsSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DecorationDescription
     * <em>Decoration Description</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DecorationDescription
     * @generated
     */
    public Adapter createDecorationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.MappingBasedDecoration
     * <em>Mapping Based Decoration</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.MappingBasedDecoration
     * @generated
     */
    public Adapter createMappingBasedDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.SemanticBasedDecoration
     * <em>Semantic Based Decoration</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.SemanticBasedDecoration
     * @generated
     */
    public Adapter createSemanticBasedDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Layer <em>Layer</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Layer
     * @generated
     */
    public Adapter createLayerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Customization
     * <em>Customization</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Customization
     * @generated
     */
    public Adapter createCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.IVSMElementCustomization
     * <em>IVSM Element Customization</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.IVSMElementCustomization
     * @generated
     */
    public Adapter createIVSMElementCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.VSMElementCustomization
     * <em>VSM Element Customization</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.VSMElementCustomization
     * @generated
     */
    public Adapter createVSMElementCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.VSMElementCustomizationReuse
     * <em>VSM Element Customization Reuse</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.VSMElementCustomizationReuse
     * @generated
     */
    public Adapter createVSMElementCustomizationReuseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EStructuralFeatureCustomization
     * <em>EStructural Feature Customization</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EStructuralFeatureCustomization
     * @generated
     */
    public Adapter createEStructuralFeatureCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EAttributeCustomization
     * <em>EAttribute Customization</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EAttributeCustomization
     * @generated
     */
    public Adapter createEAttributeCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EReferenceCustomization
     * <em>EReference Customization</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EReferenceCustomization
     * @generated
     */
    public Adapter createEReferenceCustomizationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.OptionalLayer
     * <em>Optional Layer</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.description.OptionalLayer
     * @generated
     */
    public Adapter createOptionalLayerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.SelectionDescription
     * <em>Selection Description</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.SelectionDescription
     * @generated
     */
    public Adapter createSelectionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ColorDescription
     * <em>Color Description</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ColorDescription
     * @generated
     */
    public Adapter createColorDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.SystemColor
     * <em>System Color</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.SystemColor
     * @generated
     */
    public Adapter createSystemColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.InterpolatedColor
     * <em>Interpolated Color</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.InterpolatedColor
     * @generated
     */
    public Adapter createInterpolatedColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ColorStep <em>Color Step</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ColorStep
     * @generated
     */
    public Adapter createColorStepAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.FixedColor <em>Fixed Color</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.FixedColor
     * @generated
     */
    public Adapter createFixedColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.UserFixedColor
     * <em>User Fixed Color</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.UserFixedColor
     * @generated
     */
    public Adapter createUserFixedColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.UserColor <em>User Color</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.UserColor
     * @generated
     */
    public Adapter createUserColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.Environment
     * <em>Environment</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.Environment
     * @generated
     */
    public Adapter createEnvironmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.SytemColorsPalette
     * <em>Sytem Colors Palette</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.SytemColorsPalette
     * @generated
     */
    public Adapter createSytemColorsPaletteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.UserColorsPalette
     * <em>User Colors Palette</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.UserColorsPalette
     * @generated
     */
    public Adapter createUserColorsPaletteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.AnnotationEntry
     * <em>Annotation Entry</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.AnnotationEntry
     * @generated
     */
    public Adapter createAnnotationEntryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.EndUserDocumentedElement
     * <em>End User Documented Element</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.EndUserDocumentedElement
     * @generated
     */
    public Adapter createEndUserDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.ComputedColor
     * <em>Computed Color</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.ComputedColor
     * @generated
     */
    public Adapter createComputedColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DAnnotationEntry
     * <em>DAnnotation Entry</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DAnnotationEntry
     * @generated
     */
    public Adapter createDAnnotationEntryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DescriptionAdapterFactory
