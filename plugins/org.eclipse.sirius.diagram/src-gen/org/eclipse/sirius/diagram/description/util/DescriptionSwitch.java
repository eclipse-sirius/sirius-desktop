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
package org.eclipse.sirius.diagram.description.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage
 * @generated
 */
public class DescriptionSwitch<T> {
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
        if (DescriptionSwitch.modelPackage == null) {
            DescriptionSwitch.modelPackage = DescriptionPackage.eINSTANCE;
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
        if (theEClass.eContainer() == DescriptionSwitch.modelPackage) {
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION: {
            DiagramDescription diagramDescription = (DiagramDescription) theEObject;
            T result = caseDiagramDescription(diagramDescription);
            if (result == null) {
                result = caseDragAndDropTargetDescription(diagramDescription);
            }
            if (result == null) {
                result = caseRepresentationDescription(diagramDescription);
            }
            if (result == null) {
                result = casePasteTargetDescription(diagramDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(diagramDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(diagramDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(diagramDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION: {
            DiagramImportDescription diagramImportDescription = (DiagramImportDescription) theEObject;
            T result = caseDiagramImportDescription(diagramImportDescription);
            if (result == null) {
                result = caseRepresentationImportDescription(diagramImportDescription);
            }
            if (result == null) {
                result = caseDiagramDescription(diagramImportDescription);
            }
            if (result == null) {
                result = caseRepresentationDescription(diagramImportDescription);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(diagramImportDescription);
            }
            if (result == null) {
                result = casePasteTargetDescription(diagramImportDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(diagramImportDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(diagramImportDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(diagramImportDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION: {
            DiagramExtensionDescription diagramExtensionDescription = (DiagramExtensionDescription) theEObject;
            T result = caseDiagramExtensionDescription(diagramExtensionDescription);
            if (result == null) {
                result = caseRepresentationExtensionDescription(diagramExtensionDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING: {
            DiagramElementMapping diagramElementMapping = (DiagramElementMapping) theEObject;
            T result = caseDiagramElementMapping(diagramElementMapping);
            if (result == null) {
                result = caseRepresentationElementMapping(diagramElementMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(diagramElementMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(diagramElementMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ABSTRACT_NODE_MAPPING: {
            AbstractNodeMapping abstractNodeMapping = (AbstractNodeMapping) theEObject;
            T result = caseAbstractNodeMapping(abstractNodeMapping);
            if (result == null) {
                result = caseDiagramElementMapping(abstractNodeMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractNodeMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(abstractNodeMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(abstractNodeMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractNodeMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.NODE_MAPPING: {
            NodeMapping nodeMapping = (NodeMapping) theEObject;
            T result = caseNodeMapping(nodeMapping);
            if (result == null) {
                result = caseAbstractNodeMapping(nodeMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(nodeMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(nodeMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(nodeMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(nodeMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(nodeMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(nodeMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONTAINER_MAPPING: {
            ContainerMapping containerMapping = (ContainerMapping) theEObject;
            T result = caseContainerMapping(containerMapping);
            if (result == null) {
                result = caseAbstractNodeMapping(containerMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(containerMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(containerMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(containerMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(containerMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(containerMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.NODE_MAPPING_IMPORT: {
            NodeMappingImport nodeMappingImport = (NodeMappingImport) theEObject;
            T result = caseNodeMappingImport(nodeMappingImport);
            if (result == null) {
                result = caseNodeMapping(nodeMappingImport);
            }
            if (result == null) {
                result = caseAbstractMappingImport(nodeMappingImport);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(nodeMappingImport);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(nodeMappingImport);
            }
            if (result == null) {
                result = caseDiagramElementMapping(nodeMappingImport);
            }
            if (result == null) {
                result = caseDocumentedElement(nodeMappingImport);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(nodeMappingImport);
            }
            if (result == null) {
                result = casePasteTargetDescription(nodeMappingImport);
            }
            if (result == null) {
                result = caseIdentifiedElement(nodeMappingImport);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONTAINER_MAPPING_IMPORT: {
            ContainerMappingImport containerMappingImport = (ContainerMappingImport) theEObject;
            T result = caseContainerMappingImport(containerMappingImport);
            if (result == null) {
                result = caseContainerMapping(containerMappingImport);
            }
            if (result == null) {
                result = caseAbstractMappingImport(containerMappingImport);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(containerMappingImport);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(containerMappingImport);
            }
            if (result == null) {
                result = caseDiagramElementMapping(containerMappingImport);
            }
            if (result == null) {
                result = caseDocumentedElement(containerMappingImport);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(containerMappingImport);
            }
            if (result == null) {
                result = casePasteTargetDescription(containerMappingImport);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerMappingImport);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EDGE_MAPPING: {
            EdgeMapping edgeMapping = (EdgeMapping) theEObject;
            T result = caseEdgeMapping(edgeMapping);
            if (result == null) {
                result = caseDiagramElementMapping(edgeMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(edgeMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(edgeMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(edgeMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(edgeMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(edgeMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.IEDGE_MAPPING: {
            IEdgeMapping iEdgeMapping = (IEdgeMapping) theEObject;
            T result = caseIEdgeMapping(iEdgeMapping);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EDGE_MAPPING_IMPORT: {
            EdgeMappingImport edgeMappingImport = (EdgeMappingImport) theEObject;
            T result = caseEdgeMappingImport(edgeMappingImport);
            if (result == null) {
                result = caseDocumentedElement(edgeMappingImport);
            }
            if (result == null) {
                result = caseIEdgeMapping(edgeMappingImport);
            }
            if (result == null) {
                result = caseIdentifiedElement(edgeMappingImport);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION: {
            ConditionalNodeStyleDescription conditionalNodeStyleDescription = (ConditionalNodeStyleDescription) theEObject;
            T result = caseConditionalNodeStyleDescription(conditionalNodeStyleDescription);
            if (result == null) {
                result = caseConditionalStyleDescription(conditionalNodeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION: {
            ConditionalEdgeStyleDescription conditionalEdgeStyleDescription = (ConditionalEdgeStyleDescription) theEObject;
            T result = caseConditionalEdgeStyleDescription(conditionalEdgeStyleDescription);
            if (result == null) {
                result = caseConditionalStyleDescription(conditionalEdgeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION: {
            ConditionalContainerStyleDescription conditionalContainerStyleDescription = (ConditionalContainerStyleDescription) theEObject;
            T result = caseConditionalContainerStyleDescription(conditionalContainerStyleDescription);
            if (result == null) {
                result = caseConditionalStyleDescription(conditionalContainerStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.LAYOUT: {
            Layout layout = (Layout) theEObject;
            T result = caseLayout(layout);
            if (result == null) {
                result = caseDocumentedElement(layout);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ORDERED_TREE_LAYOUT: {
            OrderedTreeLayout orderedTreeLayout = (OrderedTreeLayout) theEObject;
            T result = caseOrderedTreeLayout(orderedTreeLayout);
            if (result == null) {
                result = caseLayout(orderedTreeLayout);
            }
            if (result == null) {
                result = caseDocumentedElement(orderedTreeLayout);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COMPOSITE_LAYOUT: {
            CompositeLayout compositeLayout = (CompositeLayout) theEObject;
            T result = caseCompositeLayout(compositeLayout);
            if (result == null) {
                result = caseLayout(compositeLayout);
            }
            if (result == null) {
                result = caseDocumentedElement(compositeLayout);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.MAPPING_BASED_DECORATION: {
            MappingBasedDecoration mappingBasedDecoration = (MappingBasedDecoration) theEObject;
            T result = caseMappingBasedDecoration(mappingBasedDecoration);
            if (result == null) {
                result = caseDecorationDescription(mappingBasedDecoration);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.LAYER: {
            Layer layer = (Layer) theEObject;
            T result = caseLayer(layer);
            if (result == null) {
                result = caseDocumentedElement(layer);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(layer);
            }
            if (result == null) {
                result = caseIdentifiedElement(layer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ADDITIONAL_LAYER: {
            AdditionalLayer additionalLayer = (AdditionalLayer) theEObject;
            T result = caseAdditionalLayer(additionalLayer);
            if (result == null) {
                result = caseLayer(additionalLayer);
            }
            if (result == null) {
                result = caseDocumentedElement(additionalLayer);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(additionalLayer);
            }
            if (result == null) {
                result = caseIdentifiedElement(additionalLayer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION: {
            DragAndDropTargetDescription dragAndDropTargetDescription = (DragAndDropTargetDescription) theEObject;
            T result = caseDragAndDropTargetDescription(dragAndDropTargetDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
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
