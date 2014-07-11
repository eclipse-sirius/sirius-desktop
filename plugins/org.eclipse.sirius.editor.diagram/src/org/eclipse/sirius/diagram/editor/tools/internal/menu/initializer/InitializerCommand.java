/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com>  - initial API and implementation
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.initializer;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This is a command to create a skeleton of a Class/Entity diagram.
 * 
 * @author Joao Martins
 * 
 */
public class InitializerCommand extends AbstractUndoRecordingCommand {

    /**
     * Command Label.
     */
    public static final String TEXT = "Class diagram skeleton creation command";

    /**
     * Default ID.
     */
    private static final String BASE_ID = "pattern.class.diagram";

    private final Viewpoint viewpoint;

    /**
     * Constructor.
     * 
     * @param set
     *            set.
     * @param selection
     *            selection.
     */
    public InitializerCommand(ResourceSet set, final Collection<EObject> selection) {
        super(set);
        this.viewpoint = (Viewpoint) selection.iterator().next();
        this.setLabel(TEXT);
    }

    @Override
    protected void doExecute() {
        // Create the Pattern Elements
        DiagramDescription diagramdesc = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramdesc.setName(BASE_ID + ".default.layer");
        viewpoint.getOwnedRepresentations().add(diagramdesc);

        Layer layer = DescriptionFactory.eINSTANCE.createLayer();
        diagramdesc.setDefaultLayer(layer);
        ToolSection toolSection = createToolSection(layer);

        ContainerMapping packageMapping = createPackageMapping(layer, toolSection);
        ContainerMapping classMapping = createClassMapping(layer, toolSection);

        NodeMapping attribute = createAttribute(classMapping, toolSection);

        EdgeMapping edgeMappingInheritance = createEdgeMappingInheritance(layer, toolSection, classMapping);
        EdgeMapping edgeMappingReference = createEdgeMappingReference(layer, toolSection, classMapping);

        createDeleteTool(toolSection, classMapping, packageMapping, attribute, edgeMappingInheritance, edgeMappingReference);
        createEditTool(toolSection, classMapping, packageMapping, attribute, edgeMappingInheritance, edgeMappingReference);
    }

    private ContainerMapping createPackageMapping(Layer layer, ToolSection toolSection) {
        ContainerMapping packageMapping = DescriptionFactory.eINSTANCE.createContainerMapping();
        packageMapping.setLabel("packageMapping");
        packageMapping.setName(BASE_ID + ".package.container");
        packageMapping.setStyle(StyleFactory.eINSTANCE.createFlatContainerStyleDescription());
        layer.getContainerMappings().add(packageMapping);
        packageMapping.setSemanticCandidatesExpression("feature:eContents");

        ContainerCreationDescription containerPackageMapping = ToolFactory.eINSTANCE.createContainerCreationDescription();
        containerPackageMapping.setName(BASE_ID + ".package.container.creation");
        containerPackageMapping.setLabel("Package");
        toolSection.getOwnedTools().add(containerPackageMapping);
        containerPackageMapping.getContainerMappings().add(packageMapping);

        return packageMapping;
    }

    private NodeMapping createAttribute(ContainerMapping classMapping, ToolSection toolSection) {
        NodeMapping attribute = DescriptionFactory.eINSTANCE.createNodeMapping();
        attribute.setLabel("Attribute");
        attribute.setName(BASE_ID + "class.list.attribute");

        NodeCreationDescription nodeCreation = ToolFactory.eINSTANCE.createNodeCreationDescription();
        nodeCreation.setName(BASE_ID + ".class.list.attribute.creation");
        nodeCreation.setLabel("Attribute");
        toolSection.getOwnedTools().add(nodeCreation);
        nodeCreation.getNodeMappings().add(attribute);

        classMapping.getSubNodeMappings().add(attribute);
        return attribute;
    }

    private ContainerMapping createClassMapping(Layer layer, ToolSection toolSection) {
        ContainerMapping classMapping = DescriptionFactory.eINSTANCE.createContainerMapping();
        classMapping.setChildrenPresentation(ContainerLayout.LIST);

        classMapping.setLabel("classMapping");
        classMapping.setName(BASE_ID + ".class.list");
        layer.getContainerMappings().add(classMapping);
        classMapping.setSemanticCandidatesExpression("feature:eContents");

        ContainerCreationDescription containerClassMapping = ToolFactory.eINSTANCE.createContainerCreationDescription();
        containerClassMapping.setName(BASE_ID + ".class.list.creation");
        containerClassMapping.setLabel("Class");
        toolSection.getOwnedTools().add(containerClassMapping);
        containerClassMapping.getContainerMappings().add(classMapping);
        return classMapping;
    }

    private EdgeMapping createEdgeMappingInheritance(Layer layer, ToolSection toolSection, ContainerMapping classMapping) {
        EdgeMapping edgeMappingInheritance = DescriptionFactory.eINSTANCE.createEdgeMapping();
        EdgeStyleDescription edgeStyleI = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeMappingInheritance.setName(BASE_ID + ".edge.inheritance");
        edgeMappingInheritance.setLabel("Inheritance");

        edgeStyleI.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());

        edgeMappingInheritance.setStyle(edgeStyleI);

        edgeMappingInheritance.getSourceMapping().add(classMapping);
        edgeMappingInheritance.getTargetMapping().add(classMapping);

        layer.getEdgeMappings().add(edgeMappingInheritance);

        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        edgeCreationDescription.setName(BASE_ID + ".edge.inheritance.creation");
        edgeCreationDescription.setLabel("Inheritance");
        
        edgeCreationDescription.getEdgeMappings().add(edgeMappingInheritance);
        toolSection.getOwnedTools().add(edgeCreationDescription);

        return edgeMappingInheritance;
    }

    private EdgeMapping createEdgeMappingReference(Layer layer, ToolSection toolSection, ContainerMapping classMapping) {
        EdgeMapping edgeMappingReference = DescriptionFactory.eINSTANCE.createEdgeMapping();
        EdgeStyleDescription edgeStyleR = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeMappingReference.setName(BASE_ID + ".edge.reference");
        edgeMappingReference.setLabel("Reference");

        edgeMappingReference.setUseDomainElement(true);

        edgeStyleR.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());

        edgeMappingReference.setStyle(edgeStyleR);

        edgeMappingReference.getSourceMapping().add(classMapping);
        edgeMappingReference.getTargetMapping().add(classMapping);

        layer.getEdgeMappings().add(edgeMappingReference);

        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        edgeCreationDescription.setName(BASE_ID + ".edge.reference.creation");
        edgeCreationDescription.setLabel("Reference");
        
        edgeCreationDescription.getEdgeMappings().add(edgeMappingReference);
        toolSection.getOwnedTools().add(edgeCreationDescription);

        return edgeMappingReference;
    }

    private ToolSection createToolSection(Layer layer) {
        ToolSection toolSection = ToolFactory.eINSTANCE.createToolSection();
        toolSection.setLabel("Tool Section");
        toolSection.setName(BASE_ID + ".package.toolsection");
        layer.getToolSections().add(toolSection);
        return toolSection;
    }

    private void createDeleteTool(ToolSection toolSection, DiagramElementMapping... mappings) {
        DeleteElementDescription deleteElement = ToolFactory.eINSTANCE.createDeleteElementDescription();
        deleteElement.setName(BASE_ID + "global.delete");
        deleteElement.setLabel("Delete Tool");
        toolSection.getOwnedTools().add(deleteElement);

        for (DiagramElementMapping dem : mappings) {
            deleteElement.getMappings().add(dem);
        }
    }

    private void createEditTool(ToolSection toolSection, DiagramElementMapping... mappings) {
        DirectEditLabel directEditElement = ToolFactory.eINSTANCE.createDirectEditLabel();
        directEditElement.setName(BASE_ID + ".global.directedit");
        directEditElement.setLabel("Edit Tool");
        toolSection.getOwnedTools().add(directEditElement);

        for (DiagramElementMapping dem : mappings) {
            directEditElement.getMapping().add(dem);
        }
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    protected String getText() {
        return TEXT;
    }

}
