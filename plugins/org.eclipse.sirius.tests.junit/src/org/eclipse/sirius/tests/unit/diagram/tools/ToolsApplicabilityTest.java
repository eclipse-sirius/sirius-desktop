/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.query.ContainerCreationDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EdgeCreationDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.NodeCreationDescriptionQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.unit.diagram.tools.data.GroupToolsApplicability;
import org.eclipse.sirius.viewpoint.description.Group;

import junit.framework.TestCase;

public class ToolsApplicabilityTest extends TestCase {

    ToolsApplicabilityData oDesign;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        oDesign = new ToolsApplicabilityData();
        oDesign.load();
    }

    public void testNodeSuperTypeIteration() throws Exception {
        Iterator<DiagramElementMapping> it = new DiagramElementMappingQuery(oDesign.group().design().entities().documentation().otherrootnodespecialization().object()).superTypes();
        assertSame(oDesign.group().design().entities().defaultlayer().othernode().object(), it.next());
        assertFalse(it.hasNext());
    }

    public void testContainerSuperTypeIteration() throws Exception {
        Iterator<DiagramElementMapping> it = new DiagramElementMappingQuery(oDesign.group().design().entities().documentation().classcontainerspecialization().object()).superTypes();
        assertSame(oDesign.group().design().entities().defaultlayer().classcontainer().object(), it.next());
        assertFalse(it.hasNext());
    }

    public void testEdgeMappingSuperTypeIteration() throws Exception {
        Iterator<DiagramElementMapping> it = new DiagramElementMappingQuery(oDesign.group().design().entities().documentation().class2otherspecialization().object()).superTypes();
        assertSame(oDesign.group().design().entities().defaultlayer().class2other().object(), it.next());
        assertFalse(it.hasNext());
    }

    public void testAreInSameHiearchy() throws Exception {
        assertTrue(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classnode().object()).areInSameHiearchy(oDesign.group().design().entities().documentation()
                .classnodespecialization().object()));
        assertFalse(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classnode().object()).areInSameHiearchy(oDesign.group().design().entities().documentation()
                .classcontainerspecialization().object()));
        assertFalse(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().othernode().object()).areInSameHiearchy(oDesign.group().design().entities().documentation()
                .classcontainerspecialization().othernodespecialization().object()));

    }

    public void testNoSuperTypeIteration() throws Exception {
        Iterator<DiagramElementMapping> it = new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().class2other().object()).superTypes();
        assertFalse(it.hasNext());
        it = new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classcontainer().object()).superTypes();
        assertFalse(it.hasNext());
        it = new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classnode().object()).superTypes();
        assertFalse(it.hasNext());
        it = new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().class2other().object()).superTypes();
        assertFalse(it.hasNext());
    }

    public void testNodeTypeOfWhenMappingsHaveTheSameNames() throws Exception {
        DNode myNode = DiagramFactory.eINSTANCE.createDNode();

        NodeMapping theRightNode = oDesign.group().design().entities().defaultlayer().othernode().object();
        myNode.setActualMapping(theRightNode);

        assertTrue("A node should be of type of its original mapping.", new DiagramElementMappingQuery(myNode.getActualMapping()).isTypeOf(myNode));

        NodeMapping otherMappingWithSameName = oDesign.group().design().entities().defaultlayer().classcontainer().othernode().object();

        assertFalse("A node should not be of type of a mapping just because it's the right name", new DiagramElementMappingQuery(otherMappingWithSameName).isTypeOf(myNode));
    }

    public void testNodeInstanceOfWhenMappingsHaveTheSameNames() throws Exception {
        DNode myNode = DiagramFactory.eINSTANCE.createDNode();

        NodeMapping theRightNode = oDesign.group().design().entities().defaultlayer().othernode().object();
        myNode.setActualMapping(theRightNode);

        assertTrue("A node should be of type of its original mapping.", new DiagramElementMappingQuery(myNode.getActualMapping()).isInstanceOf(myNode));

        NodeMapping otherMappingWithSameName = oDesign.group().design().entities().defaultlayer().classcontainer().othernode().object();

        assertFalse("A node should not be of type of a mapping just because it's the right name", new DiagramElementMappingQuery(otherMappingWithSameName).isInstanceOf(myNode));
    }

    public void testNodeMappingImportTypeOf() throws Exception {
        DNode myNode = DiagramFactory.eINSTANCE.createDNode();

        myNode.setActualMapping(oDesign.group().design().entities().documentation().classnodespecialization().object());

        assertTrue(new DiagramElementMappingQuery(myNode.getActualMapping()).isTypeOf(myNode));

        assertFalse(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classnode().object()).isTypeOf(myNode));
        assertFalse(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classcontainer().object()).isTypeOf(myNode));
        assertFalse(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().class2other().object()).isTypeOf(myNode));

    }

    public void testNodeMappingImportInstanceOf() throws Exception {
        DNode myNode = DiagramFactory.eINSTANCE.createDNode();

        myNode.setActualMapping(oDesign.group().design().entities().documentation().classnodespecialization().object());

        assertTrue(new DiagramElementMappingQuery(myNode.getActualMapping()).isInstanceOf(myNode));

        assertTrue(new DiagramElementMappingQuery(oDesign.group().design().entities().defaultlayer().classnode().object()).isInstanceOf(myNode));

    }

    public void testEdgeCreationOnNodes() throws Exception {

        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclass2other().object());

        assertTrue("we should be able to create from a to b", toolQuery.canCreate(a, b));

        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));

    }

    public void testEdgeCreationWithExtraSourceMapping() throws Exception {

        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        DNodeContainer c = DiagramFactory.eINSTANCE.createDNodeContainer();
        c.setActualMapping(oDesign.group().design().entities().defaultlayer().classcontainer().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclass2otherextrasrconcontainer().object());

        assertTrue("we should be able to create from a to b", toolQuery.canCreate(a, b));

        assertTrue("we should be able to apply the tool from c to b", toolQuery.canBeAppliedOn(c, b));
        assertFalse("we should be not able to create from c to b", toolQuery.canCreate(c, b));

        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
        assertFalse("we should not be able to create from b to c", toolQuery.canCreate(b, c));

    }

    public void testEdgeCreationWithExtraTargetMapping() throws Exception {

        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        DNodeContainer c = DiagramFactory.eINSTANCE.createDNodeContainer();
        c.setActualMapping(oDesign.group().design().entities().defaultlayer().classcontainer().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclass2otherextratgtoncontainer().object());

        assertTrue("we should be able to apply the tool from a to b", toolQuery.canBeAppliedOn(a, b));

        assertTrue("we should be able to apply the tool from a to c", toolQuery.canBeAppliedOn(a, c));
        assertFalse("we should not be able to create from a to b", toolQuery.canCreate(a, c));

        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
        assertFalse("we should not be able to create from b to c", toolQuery.canCreate(c, a));
    }

    public void testEdgeCreationWithNodeMappingImportsSource() throws Exception {
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().documentation().classnodespecialization().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclass2other().object());

        assertTrue("we should be able to create from a to b", toolQuery.canCreate(a, b));

        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testEdgeCreationWithNodeMappingImportsTarget() throws Exception {
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().documentation().otherrootnodespecialization().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclass2other().object());

        assertTrue("we should be able to create from a to b", toolQuery.canCreate(a, b));

        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testEdgeMappingInOptionalLayerRefSpecializedWithNonSpecializedNodes() throws Exception {
        /*
         * Edge mapping referencing specialized nodes and applied on non
         * specialized ones.
         */
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().documentation().tools().createedgefromspecializednodes().object());

        assertFalse("we should not be able to create from a to b", toolQuery.canCreate(a, b));
        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testEdgeMappingInOptionalLayerRefSpecializedWithSpecializedNodes() throws Exception {
        /*
         * Edge mapping referencing specialized nodes and applied on specialized
         * ones.
         */
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().documentation().classnodespecialization().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().documentation().otherrootnodespecialization().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().documentation().tools().createedgefromspecializednodes().object());

        assertTrue("we should be able to create from a to b", toolQuery.canCreate(a, b));
        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testEdgeMappingInOptionalLayerRefNonSpecializedWithSpecializedNodes() throws Exception {
        /*
         * Edge mapping referencing non specialized nodes and applied on
         * specialized ones.
         */
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().documentation().classnodespecialization().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().documentation().otherrootnodespecialization().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().documentation().tools().createedgefromnonspecializednodes().object());

        assertTrue("we should not be able to create from a to b", toolQuery.canCreate(a, b));
        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testEdgeMappingInOptionalLayerRefNonSpecializedWithNonSpecializedNodes() throws Exception {
        /*
         * Edge mapping referencing non specialized nodes and applied on non
         * specialized nodes.
         */
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        EdgeCreationDescriptionQuery toolQuery = new EdgeCreationDescriptionQuery(oDesign.group().design().entities().documentation().tools().createedgefromnonspecializednodes().object());

        assertTrue("we should not be able to create from a to b", toolQuery.canCreate(a, b));
        assertFalse("we should not be able to create from b to a", toolQuery.canCreate(b, a));
    }

    public void testNodeCreationInDiagram() throws Exception {
        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().entities().object());

        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclassnode().object());

        assertTrue("we should be able to create the given node in the diagram", toolQuery.canCreateIn(diag));

        DNode childNode = DiagramFactory.eINSTANCE.createDNode();
        childNode.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        assertFalse("we should not be able to create the given node in an unrelated node", toolQuery.canCreateIn(childNode));

    }

    public void testNodeCreationInDiagramSpecialization() throws Exception {
        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().diagramspecialization().object());

        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclassnode().object());

        assertTrue("we should be able to create the given node in the specialized diagram", toolQuery.canCreateIn(diag));

        DNode childNode = DiagramFactory.eINSTANCE.createDNode();
        childNode.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        assertFalse("we should not be able to create the given node in an unrelated node", toolQuery.canCreateIn(childNode));

    }

    public void testNodeCreationInContainer() throws Exception {
        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        container.setActualMapping(oDesign.group().design().entities().defaultlayer().classcontainer().object());

        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclasscontainerothernode().object());

        assertTrue("we should be able to create the given node in the container", toolQuery.canCreateIn(container));

        DNodeContainer containerSpecialized = DiagramFactory.eINSTANCE.createDNodeContainer();

        containerSpecialized.setActualMapping(oDesign.group().design().entities().documentation().classcontainerspecialization().object());

        assertTrue("we should be able to create the given node in a specialized container", toolQuery.canCreateIn(containerSpecialized));

        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().entities().object());

        assertFalse("we should not be able to create the given node in the diagram", toolQuery.canCreateIn(diag));
    }

    public void testBorderNodeCreation() throws Exception {
        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createbordernode().object());

        assertTrue("We should be able to create a border node in its parent specification", toolQuery.canCreateIn(a));

        a.setActualMapping(oDesign.group().design().entities().documentation().otherrootnodespecialization().object());

        assertTrue("We should be able to create a border node in its parent specialization", toolQuery.canCreateIn(a));

        DNode b = DiagramFactory.eINSTANCE.createDNode();
        b.setActualMapping(oDesign.group().design().entities().defaultlayer().classnode().object());

        assertFalse("We should not be able to create a border node on another node.", toolQuery.canCreateIn(b));

        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().entities().object());

        assertFalse("We should not be able to create a border node on the diagram.", toolQuery.canCreateIn(diag));

    }

    public void testReusedBorderNodeCreation() throws Exception {
        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        container.setActualMapping(oDesign.group().design().entities().defaultlayer().classcontainer().object());
        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createbordernode().object());

        assertTrue("We should be able to create a border node in the container reusing it", toolQuery.canCreateIn(container));

    }

    public void testSpecializedNodeCreation() throws Exception {

        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().documentation().tools().createotherrootspecialization().object());

        DNodeContainer containerSpecialized = DiagramFactory.eINSTANCE.createDNodeContainer();
        containerSpecialized.setActualMapping(oDesign.group().design().entities().documentation().classcontainerspecialization().object());

        assertTrue("we should be able to create the given node in a specialized container", toolQuery.canCreateIn(containerSpecialized));

        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().entities().object());

        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().othernode().object());

        assertFalse("we should not be able to create the given container as a border of the node", toolQuery.canCreateIn(a));
    }

    public void testContainerCreation() throws Exception {

        ContainerCreationDescriptionQuery toolQuery = new ContainerCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclasscontainer().object());

        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().entities().object());

        assertTrue("we should be able to create the given container in the simple diagram", toolQuery.canCreateIn(diag));

        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        container.setActualMapping(oDesign.group().design().entities().defaultlayer().classcontainer().object());

        assertTrue("we should be able to create the given container in the container as it's recursively importing him", toolQuery.canCreateIn(container));

    }

    public void testContainerSpecializationCreation() throws Exception {
        ContainerCreationDescriptionQuery toolQuery = new ContainerCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclasscontainer().object());

        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        diag.setDescription(oDesign.group().design().diagramspecialization().object());

        assertTrue("we should be able to create the given container in the diagram specialization", toolQuery.canCreateIn(diag));
    }

    public void testExtraMappingOnContainerCreationTool() throws Exception {
        ContainerCreationDescriptionQuery toolQuery = new ContainerCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclasscontainer().object());

        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        container.setActualMapping(oDesign.group().design().entities().defaultlayer().extracontainermapping().object());

        assertTrue("we should be able to create the given container in the container as it's one of the extra mappings", toolQuery.canCreateIn(container));

    }

    public void testExtraMappingOnNodeCreationTool() throws Exception {
        NodeCreationDescriptionQuery toolQuery = new NodeCreationDescriptionQuery(oDesign.group().design().entities().defaultlayer().tools().createclassnode().object());

        DNode a = DiagramFactory.eINSTANCE.createDNode();
        a.setActualMapping(oDesign.group().design().entities().defaultlayer().extranodemapping().object());

        assertTrue("we should be able to create the given node in the node as it's one of the extra mappings", toolQuery.canCreateIn(a));

        DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
        container.setActualMapping(oDesign.group().design().entities().defaultlayer().extracontainermapping().object());

        assertTrue("we should be able to create the node in the container as it's one of the extra mappings", toolQuery.canCreateIn(container));

    }

}

class ToolsApplicabilityData {
    private Group oDesign;

    public void load() {
        ResourceSet set = new ResourceSetImpl();
        oDesign = (Group) set.getResource(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/unit/tools/applicability/applicability.odesign", true), true).getContents().get(0);
    }

    public GroupToolsApplicability group() {
        return new GroupToolsApplicability(oDesign);
    }

}
