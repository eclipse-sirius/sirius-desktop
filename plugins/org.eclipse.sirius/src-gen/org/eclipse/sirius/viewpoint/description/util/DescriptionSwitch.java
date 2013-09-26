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
package org.eclipse.sirius.viewpoint.description.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.AdditionalLayer;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.CompositeLayout;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.DragAndDropTargetDescription;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.Layout;
import org.eclipse.sirius.viewpoint.description.MappingBasedDecoration;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.OrderedTreeLayout;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserColor;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage
 * @generated
 */
public class DescriptionSwitch<T> {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionSwitch() {
        if (modelPackage == null) {
            modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case DescriptionPackage.GROUP: {
            Group group = (Group) theEObject;
            T result = caseGroup(group);
            if (result == null)
                result = caseDModelElement(group);
            if (result == null)
                result = caseDocumentedElement(group);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.COMPONENT: {
            Component component = (Component) theEObject;
            T result = caseComponent(component);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.VIEWPOINT: {
            Viewpoint viewpoint = (Viewpoint) theEObject;
            T result = caseViewpoint(viewpoint);
            if (result == null)
                result = caseDocumentedElement(viewpoint);
            if (result == null)
                result = caseComponent(viewpoint);
            if (result == null)
                result = caseEndUserDocumentedElement(viewpoint);
            if (result == null)
                result = caseIdentifiedElement(viewpoint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.FEATURE_EXTENSION_DESCRIPTION: {
            FeatureExtensionDescription featureExtensionDescription = (FeatureExtensionDescription) theEObject;
            T result = caseFeatureExtensionDescription(featureExtensionDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.REPRESENTATION_DESCRIPTION: {
            RepresentationDescription representationDescription = (RepresentationDescription) theEObject;
            T result = caseRepresentationDescription(representationDescription);
            if (result == null)
                result = caseDocumentedElement(representationDescription);
            if (result == null)
                result = caseEndUserDocumentedElement(representationDescription);
            if (result == null)
                result = caseIdentifiedElement(representationDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.REPRESENTATION_TEMPLATE: {
            RepresentationTemplate representationTemplate = (RepresentationTemplate) theEObject;
            T result = caseRepresentationTemplate(representationTemplate);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION: {
            RepresentationImportDescription representationImportDescription = (RepresentationImportDescription) theEObject;
            T result = caseRepresentationImportDescription(representationImportDescription);
            if (result == null)
                result = caseRepresentationDescription(representationImportDescription);
            if (result == null)
                result = caseDocumentedElement(representationImportDescription);
            if (result == null)
                result = caseEndUserDocumentedElement(representationImportDescription);
            if (result == null)
                result = caseIdentifiedElement(representationImportDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION: {
            RepresentationExtensionDescription representationExtensionDescription = (RepresentationExtensionDescription) theEObject;
            T result = caseRepresentationExtensionDescription(representationExtensionDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DIAGRAM_DESCRIPTION: {
            DiagramDescription diagramDescription = (DiagramDescription) theEObject;
            T result = caseDiagramDescription(diagramDescription);
            if (result == null)
                result = caseDragAndDropTargetDescription(diagramDescription);
            if (result == null)
                result = caseRepresentationDescription(diagramDescription);
            if (result == null)
                result = casePasteTargetDescription(diagramDescription);
            if (result == null)
                result = caseDocumentedElement(diagramDescription);
            if (result == null)
                result = caseEndUserDocumentedElement(diagramDescription);
            if (result == null)
                result = caseIdentifiedElement(diagramDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION: {
            DiagramImportDescription diagramImportDescription = (DiagramImportDescription) theEObject;
            T result = caseDiagramImportDescription(diagramImportDescription);
            if (result == null)
                result = caseRepresentationImportDescription(diagramImportDescription);
            if (result == null)
                result = caseDiagramDescription(diagramImportDescription);
            if (result == null)
                result = caseRepresentationDescription(diagramImportDescription);
            if (result == null)
                result = caseDragAndDropTargetDescription(diagramImportDescription);
            if (result == null)
                result = casePasteTargetDescription(diagramImportDescription);
            if (result == null)
                result = caseDocumentedElement(diagramImportDescription);
            if (result == null)
                result = caseEndUserDocumentedElement(diagramImportDescription);
            if (result == null)
                result = caseIdentifiedElement(diagramImportDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION: {
            DiagramExtensionDescription diagramExtensionDescription = (DiagramExtensionDescription) theEObject;
            T result = caseDiagramExtensionDescription(diagramExtensionDescription);
            if (result == null)
                result = caseRepresentationExtensionDescription(diagramExtensionDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.METAMODEL_EXTENSION_SETTING: {
            MetamodelExtensionSetting metamodelExtensionSetting = (MetamodelExtensionSetting) theEObject;
            T result = caseMetamodelExtensionSetting(metamodelExtensionSetting);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.JAVA_EXTENSION: {
            JavaExtension javaExtension = (JavaExtension) theEObject;
            T result = caseJavaExtension(javaExtension);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING: {
            RepresentationElementMapping representationElementMapping = (RepresentationElementMapping) theEObject;
            T result = caseRepresentationElementMapping(representationElementMapping);
            if (result == null)
                result = caseIdentifiedElement(representationElementMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING: {
            DiagramElementMapping diagramElementMapping = (DiagramElementMapping) theEObject;
            T result = caseDiagramElementMapping(diagramElementMapping);
            if (result == null)
                result = caseRepresentationElementMapping(diagramElementMapping);
            if (result == null)
                result = casePasteTargetDescription(diagramElementMapping);
            if (result == null)
                result = caseIdentifiedElement(diagramElementMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ABSTRACT_NODE_MAPPING: {
            AbstractNodeMapping abstractNodeMapping = (AbstractNodeMapping) theEObject;
            T result = caseAbstractNodeMapping(abstractNodeMapping);
            if (result == null)
                result = caseDiagramElementMapping(abstractNodeMapping);
            if (result == null)
                result = caseDocumentedElement(abstractNodeMapping);
            if (result == null)
                result = caseRepresentationElementMapping(abstractNodeMapping);
            if (result == null)
                result = casePasteTargetDescription(abstractNodeMapping);
            if (result == null)
                result = caseIdentifiedElement(abstractNodeMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.NODE_MAPPING: {
            NodeMapping nodeMapping = (NodeMapping) theEObject;
            T result = caseNodeMapping(nodeMapping);
            if (result == null)
                result = caseAbstractNodeMapping(nodeMapping);
            if (result == null)
                result = caseDragAndDropTargetDescription(nodeMapping);
            if (result == null)
                result = caseDiagramElementMapping(nodeMapping);
            if (result == null)
                result = caseDocumentedElement(nodeMapping);
            if (result == null)
                result = caseRepresentationElementMapping(nodeMapping);
            if (result == null)
                result = casePasteTargetDescription(nodeMapping);
            if (result == null)
                result = caseIdentifiedElement(nodeMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONTAINER_MAPPING: {
            ContainerMapping containerMapping = (ContainerMapping) theEObject;
            T result = caseContainerMapping(containerMapping);
            if (result == null)
                result = caseAbstractNodeMapping(containerMapping);
            if (result == null)
                result = caseDragAndDropTargetDescription(containerMapping);
            if (result == null)
                result = caseDiagramElementMapping(containerMapping);
            if (result == null)
                result = caseDocumentedElement(containerMapping);
            if (result == null)
                result = caseRepresentationElementMapping(containerMapping);
            if (result == null)
                result = casePasteTargetDescription(containerMapping);
            if (result == null)
                result = caseIdentifiedElement(containerMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ABSTRACT_MAPPING_IMPORT: {
            AbstractMappingImport abstractMappingImport = (AbstractMappingImport) theEObject;
            T result = caseAbstractMappingImport(abstractMappingImport);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.NODE_MAPPING_IMPORT: {
            NodeMappingImport nodeMappingImport = (NodeMappingImport) theEObject;
            T result = caseNodeMappingImport(nodeMappingImport);
            if (result == null)
                result = caseNodeMapping(nodeMappingImport);
            if (result == null)
                result = caseAbstractMappingImport(nodeMappingImport);
            if (result == null)
                result = caseAbstractNodeMapping(nodeMappingImport);
            if (result == null)
                result = caseDragAndDropTargetDescription(nodeMappingImport);
            if (result == null)
                result = caseDiagramElementMapping(nodeMappingImport);
            if (result == null)
                result = caseDocumentedElement(nodeMappingImport);
            if (result == null)
                result = caseRepresentationElementMapping(nodeMappingImport);
            if (result == null)
                result = casePasteTargetDescription(nodeMappingImport);
            if (result == null)
                result = caseIdentifiedElement(nodeMappingImport);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONTAINER_MAPPING_IMPORT: {
            ContainerMappingImport containerMappingImport = (ContainerMappingImport) theEObject;
            T result = caseContainerMappingImport(containerMappingImport);
            if (result == null)
                result = caseContainerMapping(containerMappingImport);
            if (result == null)
                result = caseAbstractMappingImport(containerMappingImport);
            if (result == null)
                result = caseAbstractNodeMapping(containerMappingImport);
            if (result == null)
                result = caseDragAndDropTargetDescription(containerMappingImport);
            if (result == null)
                result = caseDiagramElementMapping(containerMappingImport);
            if (result == null)
                result = caseDocumentedElement(containerMappingImport);
            if (result == null)
                result = caseRepresentationElementMapping(containerMappingImport);
            if (result == null)
                result = casePasteTargetDescription(containerMappingImport);
            if (result == null)
                result = caseIdentifiedElement(containerMappingImport);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.EDGE_MAPPING: {
            EdgeMapping edgeMapping = (EdgeMapping) theEObject;
            T result = caseEdgeMapping(edgeMapping);
            if (result == null)
                result = caseDiagramElementMapping(edgeMapping);
            if (result == null)
                result = caseDocumentedElement(edgeMapping);
            if (result == null)
                result = caseIEdgeMapping(edgeMapping);
            if (result == null)
                result = caseRepresentationElementMapping(edgeMapping);
            if (result == null)
                result = casePasteTargetDescription(edgeMapping);
            if (result == null)
                result = caseIdentifiedElement(edgeMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.IEDGE_MAPPING: {
            IEdgeMapping iEdgeMapping = (IEdgeMapping) theEObject;
            T result = caseIEdgeMapping(iEdgeMapping);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.EDGE_MAPPING_IMPORT: {
            EdgeMappingImport edgeMappingImport = (EdgeMappingImport) theEObject;
            T result = caseEdgeMappingImport(edgeMappingImport);
            if (result == null)
                result = caseDocumentedElement(edgeMappingImport);
            if (result == null)
                result = caseIEdgeMapping(edgeMappingImport);
            if (result == null)
                result = caseIdentifiedElement(edgeMappingImport);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DOCUMENTED_ELEMENT: {
            DocumentedElement documentedElement = (DocumentedElement) theEObject;
            T result = caseDocumentedElement(documentedElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DMODEL_ELEMENT: {
            DModelElement dModelElement = (DModelElement) theEObject;
            T result = caseDModelElement(dModelElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DANNOTATION: {
            DAnnotation dAnnotation = (DAnnotation) theEObject;
            T result = caseDAnnotation(dAnnotation);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION: {
            ConditionalStyleDescription conditionalStyleDescription = (ConditionalStyleDescription) theEObject;
            T result = caseConditionalStyleDescription(conditionalStyleDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION: {
            ConditionalNodeStyleDescription conditionalNodeStyleDescription = (ConditionalNodeStyleDescription) theEObject;
            T result = caseConditionalNodeStyleDescription(conditionalNodeStyleDescription);
            if (result == null)
                result = caseConditionalStyleDescription(conditionalNodeStyleDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION: {
            ConditionalEdgeStyleDescription conditionalEdgeStyleDescription = (ConditionalEdgeStyleDescription) theEObject;
            T result = caseConditionalEdgeStyleDescription(conditionalEdgeStyleDescription);
            if (result == null)
                result = caseConditionalStyleDescription(conditionalEdgeStyleDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION: {
            ConditionalContainerStyleDescription conditionalContainerStyleDescription = (ConditionalContainerStyleDescription) theEObject;
            T result = caseConditionalContainerStyleDescription(conditionalContainerStyleDescription);
            if (result == null)
                result = caseConditionalStyleDescription(conditionalContainerStyleDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION: {
            DragAndDropTargetDescription dragAndDropTargetDescription = (DragAndDropTargetDescription) theEObject;
            T result = caseDragAndDropTargetDescription(dragAndDropTargetDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.PASTE_TARGET_DESCRIPTION: {
            PasteTargetDescription pasteTargetDescription = (PasteTargetDescription) theEObject;
            T result = casePasteTargetDescription(pasteTargetDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.LAYOUT: {
            Layout layout = (Layout) theEObject;
            T result = caseLayout(layout);
            if (result == null)
                result = caseDocumentedElement(layout);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ORDERED_TREE_LAYOUT: {
            OrderedTreeLayout orderedTreeLayout = (OrderedTreeLayout) theEObject;
            T result = caseOrderedTreeLayout(orderedTreeLayout);
            if (result == null)
                result = caseLayout(orderedTreeLayout);
            if (result == null)
                result = caseDocumentedElement(orderedTreeLayout);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.COMPOSITE_LAYOUT: {
            CompositeLayout compositeLayout = (CompositeLayout) theEObject;
            T result = caseCompositeLayout(compositeLayout);
            if (result == null)
                result = caseLayout(compositeLayout);
            if (result == null)
                result = caseDocumentedElement(compositeLayout);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET: {
            DecorationDescriptionsSet decorationDescriptionsSet = (DecorationDescriptionsSet) theEObject;
            T result = caseDecorationDescriptionsSet(decorationDescriptionsSet);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DECORATION_DESCRIPTION: {
            DecorationDescription decorationDescription = (DecorationDescription) theEObject;
            T result = caseDecorationDescription(decorationDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.MAPPING_BASED_DECORATION: {
            MappingBasedDecoration mappingBasedDecoration = (MappingBasedDecoration) theEObject;
            T result = caseMappingBasedDecoration(mappingBasedDecoration);
            if (result == null)
                result = caseDecorationDescription(mappingBasedDecoration);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.SEMANTIC_BASED_DECORATION: {
            SemanticBasedDecoration semanticBasedDecoration = (SemanticBasedDecoration) theEObject;
            T result = caseSemanticBasedDecoration(semanticBasedDecoration);
            if (result == null)
                result = caseDecorationDescription(semanticBasedDecoration);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.LAYER: {
            Layer layer = (Layer) theEObject;
            T result = caseLayer(layer);
            if (result == null)
                result = caseDocumentedElement(layer);
            if (result == null)
                result = caseEndUserDocumentedElement(layer);
            if (result == null)
                result = caseIdentifiedElement(layer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ADDITIONAL_LAYER: {
            AdditionalLayer additionalLayer = (AdditionalLayer) theEObject;
            T result = caseAdditionalLayer(additionalLayer);
            if (result == null)
                result = caseLayer(additionalLayer);
            if (result == null)
                result = caseDocumentedElement(additionalLayer);
            if (result == null)
                result = caseEndUserDocumentedElement(additionalLayer);
            if (result == null)
                result = caseIdentifiedElement(additionalLayer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.CUSTOMIZATION: {
            Customization customization = (Customization) theEObject;
            T result = caseCustomization(customization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION: {
            IVSMElementCustomization ivsmElementCustomization = (IVSMElementCustomization) theEObject;
            T result = caseIVSMElementCustomization(ivsmElementCustomization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION: {
            VSMElementCustomization vsmElementCustomization = (VSMElementCustomization) theEObject;
            T result = caseVSMElementCustomization(vsmElementCustomization);
            if (result == null)
                result = caseIVSMElementCustomization(vsmElementCustomization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE: {
            VSMElementCustomizationReuse vsmElementCustomizationReuse = (VSMElementCustomizationReuse) theEObject;
            T result = caseVSMElementCustomizationReuse(vsmElementCustomizationReuse);
            if (result == null)
                result = caseIVSMElementCustomization(vsmElementCustomizationReuse);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION: {
            EStructuralFeatureCustomization eStructuralFeatureCustomization = (EStructuralFeatureCustomization) theEObject;
            T result = caseEStructuralFeatureCustomization(eStructuralFeatureCustomization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION: {
            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) theEObject;
            T result = caseEAttributeCustomization(eAttributeCustomization);
            if (result == null)
                result = caseEStructuralFeatureCustomization(eAttributeCustomization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION: {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) theEObject;
            T result = caseEReferenceCustomization(eReferenceCustomization);
            if (result == null)
                result = caseEStructuralFeatureCustomization(eReferenceCustomization);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.SELECTION_DESCRIPTION: {
            SelectionDescription selectionDescription = (SelectionDescription) theEObject;
            T result = caseSelectionDescription(selectionDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.COLOR_DESCRIPTION: {
            ColorDescription colorDescription = (ColorDescription) theEObject;
            T result = caseColorDescription(colorDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.SYSTEM_COLOR: {
            SystemColor systemColor = (SystemColor) theEObject;
            T result = caseSystemColor(systemColor);
            if (result == null)
                result = caseFixedColor(systemColor);
            if (result == null)
                result = caseColorDescription(systemColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.INTERPOLATED_COLOR: {
            InterpolatedColor interpolatedColor = (InterpolatedColor) theEObject;
            T result = caseInterpolatedColor(interpolatedColor);
            if (result == null)
                result = caseColorDescription(interpolatedColor);
            if (result == null)
                result = caseUserColor(interpolatedColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.COLOR_STEP: {
            ColorStep colorStep = (ColorStep) theEObject;
            T result = caseColorStep(colorStep);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.FIXED_COLOR: {
            FixedColor fixedColor = (FixedColor) theEObject;
            T result = caseFixedColor(fixedColor);
            if (result == null)
                result = caseColorDescription(fixedColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.USER_FIXED_COLOR: {
            UserFixedColor userFixedColor = (UserFixedColor) theEObject;
            T result = caseUserFixedColor(userFixedColor);
            if (result == null)
                result = caseFixedColor(userFixedColor);
            if (result == null)
                result = caseUserColor(userFixedColor);
            if (result == null)
                result = caseColorDescription(userFixedColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.USER_COLOR: {
            UserColor userColor = (UserColor) theEObject;
            T result = caseUserColor(userColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ENVIRONMENT: {
            Environment environment = (Environment) theEObject;
            T result = caseEnvironment(environment);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.SYTEM_COLORS_PALETTE: {
            SytemColorsPalette sytemColorsPalette = (SytemColorsPalette) theEObject;
            T result = caseSytemColorsPalette(sytemColorsPalette);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.USER_COLORS_PALETTE: {
            UserColorsPalette userColorsPalette = (UserColorsPalette) theEObject;
            T result = caseUserColorsPalette(userColorsPalette);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.ANNOTATION_ENTRY: {
            AnnotationEntry annotationEntry = (AnnotationEntry) theEObject;
            T result = caseAnnotationEntry(annotationEntry);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT: {
            EndUserDocumentedElement endUserDocumentedElement = (EndUserDocumentedElement) theEObject;
            T result = caseEndUserDocumentedElement(endUserDocumentedElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.IDENTIFIED_ELEMENT: {
            IdentifiedElement identifiedElement = (IdentifiedElement) theEObject;
            T result = caseIdentifiedElement(identifiedElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.COMPUTED_COLOR: {
            ComputedColor computedColor = (ComputedColor) theEObject;
            T result = caseComputedColor(computedColor);
            if (result == null)
                result = caseUserColor(computedColor);
            if (result == null)
                result = caseColorDescription(computedColor);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case DescriptionPackage.DANNOTATION_ENTRY: {
            DAnnotationEntry dAnnotationEntry = (DAnnotationEntry) theEObject;
            T result = caseDAnnotationEntry(dAnnotationEntry);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Component</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponent(Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Viewpoint</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Viewpoint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewpoint(Viewpoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationDescription(RepresentationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation Template</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationTemplate(RepresentationTemplate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation Import Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation Import Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationImportDescription(RepresentationImportDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation Extension Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationExtensionDescription(RepresentationExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramDescription(DiagramDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram Import Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram Import Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramImportDescription(DiagramImportDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram Extension Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramExtensionDescription(DiagramExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Feature Extension Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Feature Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureExtensionDescription(FeatureExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Metamodel Extension Setting</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Metamodel Extension Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetamodelExtensionSetting(MetamodelExtensionSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Java Extension</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Java Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseJavaExtension(JavaExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Representation Element Mapping</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Representation Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationElementMapping(RepresentationElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram Element Mapping</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramElementMapping(DiagramElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Node Mapping</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Node Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNodeMapping(AbstractNodeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Mapping</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeMapping(NodeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Mapping Import</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Mapping Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeMappingImport(NodeMappingImport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Mapping Import</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Mapping Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerMappingImport(ContainerMappingImport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Mapping</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerMapping(ContainerMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Mapping Import</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Mapping Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractMappingImport(AbstractMappingImport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Mapping</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeMapping(EdgeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>IEdge Mapping</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>IEdge Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIEdgeMapping(IEdgeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Mapping Import</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Mapping Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeMappingImport(EdgeMappingImport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Group</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroup(Group object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Documented Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DModel Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnnotation</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnnotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnnotation(DAnnotation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Conditional Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Conditional Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalStyleDescription(ConditionalStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Conditional Node Style Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Conditional Node Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalNodeStyleDescription(ConditionalNodeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Conditional Edge Style Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Conditional Edge Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalEdgeStyleDescription(ConditionalEdgeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Conditional Container Style Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Conditional Container Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalContainerStyleDescription(ConditionalContainerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Drag And Drop Target Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Drag And Drop Target Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDragAndDropTargetDescription(DragAndDropTargetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Paste Target Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Paste Target Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePasteTargetDescription(PasteTargetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Layout</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayout(Layout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ordered Tree Layout</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ordered Tree Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOrderedTreeLayout(OrderedTreeLayout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Composite Layout</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Composite Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompositeLayout(CompositeLayout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Decoration Descriptions Set</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Decoration Descriptions Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecorationDescriptionsSet(DecorationDescriptionsSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Decoration Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Decoration Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecorationDescription(DecorationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Mapping Based Decoration</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Mapping Based Decoration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMappingBasedDecoration(MappingBasedDecoration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Semantic Based Decoration</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Semantic Based Decoration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSemanticBasedDecoration(SemanticBasedDecoration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Layer</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Layer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayer(Layer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Additional Layer</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Additional Layer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdditionalLayer(AdditionalLayer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Customization</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomization(Customization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>IVSM Element Customization</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>IVSM Element Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIVSMElementCustomization(IVSMElementCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>VSM Element Customization</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>VSM Element Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVSMElementCustomization(VSMElementCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>VSM Element Customization Reuse</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>VSM Element Customization Reuse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVSMElementCustomizationReuse(VSMElementCustomizationReuse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EStructural Feature Customization</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EStructural Feature Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEStructuralFeatureCustomization(EStructuralFeatureCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EAttribute Customization</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EAttribute Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEAttributeCustomization(EAttributeCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EReference Customization</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EReference Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEReferenceCustomization(EReferenceCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Selection Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Selection Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectionDescription(SelectionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Color Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Color Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColorDescription(ColorDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>System Color</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>System Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSystemColor(SystemColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interpolated Color</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interpolated Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterpolatedColor(InterpolatedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Color Step</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Color Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColorStep(ColorStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Fixed Color</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Fixed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFixedColor(FixedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>User Fixed Color</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>User Fixed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserFixedColor(UserFixedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>User Color</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>User Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserColor(UserColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Environment</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Environment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnvironment(Environment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Sytem Colors Palette</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Sytem Colors Palette</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSytemColorsPalette(SytemColorsPalette object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>User Colors Palette</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>User Colors Palette</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserColorsPalette(UserColorsPalette object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Annotation Entry</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Annotation Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAnnotationEntry(AnnotationEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>End User Documented Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>End User Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndUserDocumentedElement(EndUserDocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Identified Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Computed Color</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Computed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComputedColor(ComputedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnnotation Entry</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnnotation Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnnotationEntry(DAnnotationEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // DescriptionSwitch
