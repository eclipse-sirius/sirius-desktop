/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Compartment;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.internal.refresh.SynchronizeGMFModelCommand;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartmentEditPart;
import org.junit.Assert;

import junit.framework.TestCase;

/**
 * Test the DDiagramCanonicalSynchronizer.
 * 
 * Test cases :
 * <ol>
 * <li>Session model & notation model are already synchronized then the DDiagramCanonicalSynchronizer has nothing to
 * do</li>
 * <li>Session model has new {@link DDiagramElement} according to the notation model then the
 * DDiagramCanonicalSynchronizer must create new {@link View} elements in the notation model</li>
 * <li>Notation model has View without corresponding View.element (because some {@link DDiagramElement} have been
 * deleted from the Session model) then the DDiagramCanonicalSynchronizer must delete these {@link View} elements in the
 * notation model</li>
 * </ol>
 * 
 * These 3 test cases must be tested on a combinaison of followings {@link DDiagramElement} :
 * <ol>
 * <li>{@link DNode}</li>
 * <li>{@link DNodeContainer}</li>
 * <li>{@link DNodeList}</li>
 * <li>{@link DNodeListElement}</li>
 * <li>{@link DEdge}</li>
 * </ol>
 * 
 * and followings combinaison of {@link View} :
 * 
 * <ol>
 * <li>Diagram</li>
 * <li>StandardDiagram</li>
 * <li>Node</li>
 * <li>DecorationNode</li>
 * <li>BasicCompartment</li>
 * <li>Compartment</li>
 * <li>ListCompartment</li>
 * <li>Shape</li>
 * <li>BasicDecorationNode</li>
 * <li>BasicSemanticCompartment</li>
 * <li>SemanticListCompartment</li>
 * <li>Connector</li>
 * <li>Edge</li>
 * </ol>
 * 
 * //TODO : DDiagram.subDiagrams DDiagram.getHiddenElements {@link Compartment}? DDiagramElement.getDecorations Refresh
 * on ViewpointPackage.eINSTANCE.getDDiagram_ActivatedFilters() || input.getFeature() ==
 * ViewpointPackage.eINSTANCE.getDDiagram_ActivatedLayers() AFTER_CHILD_CANNONICALS
 * 
 * Mappings VISUAL_ID(EditPart) <-> notation semantic (see SiriusVisualIDRegistry)
 * <ol>
 * <li>DDiagramEditPart(1000) : has DNodeEditPart, DNodeContainerEditPart and DNodeListEditPart as childs</li>
 * 
 * <li>DNodeEditPart() : has DNode2EditPart as child</li>
 * <li>DNode2EditPart(3001) : has itself as child</li>
 * <li>DNode3EditPart(3007) : has DNode2EditPart as child</li>
 * <li>DNode4EditPart(3012) : has itself as child</li>
 * <li>DNodeNameEditPart(NOTHING) :</li>
 * 
 * <li>DNodeContainerEditPart(2002) : has DNode4EditPart and DNodeContainerViewNodeContainerCompartmentEditPart as
 * child</li>
 * <li>DNodeContainer2EditPart(3008) : has DNode4EditPart and DNodeContainerViewNodeContainerCompartment2EditPart as
 * child</li>
 * <li>DNodeContainerNameEditPart(5006) :</li>
 * <li>DNodeContainerName2EditPart(5005) :</li>
 * 
 * <li>{@link DNodeContainerViewNodeContainerCompartmentEditPart} : has DNode3EditPart, DNodeContainer2EditPart and
 * DNodeList2EditPart as child</li>
 * <li>{@link DNodeContainerViewNodeContainerCompartment2EditPart} : same as previous</li>
 * 
 * <li>DNodeListEditPart() : has DNode4EditPart as child</li>
 * <li>DNodeList2EditPart() : has DNode4EditPart as child</li>
 * <li>DNodeListNameEditPart() :</li>
 * <li>DNodeListName2EditPart() :</li>
 * <li>DNodeListElementEditPart() :</li>
 * 
 * <li>{@link DNodeListViewNodeListCompartmentEditPart}() : has DNodeListElementEditPart as child</li>
 * <li>{@link DNodeListViewNodeListCompartment2EditPart}() : same as previous</li>
 * 
 * <li>DEdgeEditPart(4001) :</li>
 * <li>DEdgeNameEditPart(6001) :</li>
 * </ol>
 * 
 * @author edugueperoux
 */
public class DDiagramCanonicalSynchronizerTests extends TestCase {

    private TransactionalEditingDomain domain;

    private Resource sessionResource;

    private DSemanticDiagram dSemanticDiagram;

    private Diagram diagram;

    private CanonicalSynchronizer dDiagramCanonicalSynchronizer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Needs a TED because BasicNodeViewFactory use it to do
        // View.setElement()
        domain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain();
        URI sessionResourceURI = URI.createFileURI(System.getProperty("java.io.tmpdir") + "test.aird");
        sessionResource = domain.getResourceSet().createResource(sessionResourceURI);

        dSemanticDiagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        Command saveResourceCmd = new AbstractCommand("") {

            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                sessionResource.getContents().add(dSemanticDiagram);
            }

            @Override
            public void redo() {
                execute();
            }

        };
        domain.getCommandStack().execute(saveResourceCmd);

        diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.setType(DDiagramEditPart.MODEL_ID);
        diagram.setElement(dSemanticDiagram);

        saveResourceCmd = new AbstractCommand("") {

            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                sessionResource.getContents().add(diagram);
            }

            @Override
            public void redo() {
                execute();
            }

        };
        domain.getCommandStack().execute(saveResourceCmd);

        dDiagramCanonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(diagram);
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a empty {@link DSemanticDiagram} if the {@link Diagram}
     * is already empty.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnEmptyNotationModel() {
        // dDiagramCanonicalSynchronizer.synchronize();
        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));

        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(0, dSemanticDiagram.getDiagramElements().size());
        Assert.assertEquals(0, dSemanticDiagram.getOwnedDiagramElements().size());
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(0, diagram.getChildren().size());
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a Session model with one DNode and the notation model
     * with the corresponding Node.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnSessionModelWithDNode() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        // dSemanticDiagram.getDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNode);
        domain.getCommandStack().execute(addCmd);

        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeEditPart.VISUAL_ID));
        node.setElement(dNode);
        addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);
        // diagram.getPersistedChildren().add(node);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(1, dSemanticDiagram.getDiagramElements().size());
        Assert.assertEquals(dNode, dSemanticDiagram.getOwnedDiagramElements().get(0));
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(1, diagram.getChildren().size());
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
        Assert.assertEquals(node, diagram.getChildren().get(0));
        Assert.assertEquals(dNode, ((View) diagram.getChildren().get(0)).getElement());
        Assert.assertEquals(String.valueOf(DNodeEditPart.VISUAL_ID), ((View) diagram.getChildren().get(0)).getType());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a Session model with one {@link DNodeContainer} and the
     * notation model with the corresponding Node.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnSessionModelWithDNodeContainer() {
        DNodeContainer dNodeContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
        // dSemanticDiagram.getDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNodeContainer);
        domain.getCommandStack().execute(addCmd);

        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeContainerEditPart.VISUAL_ID));
        node.setElement(dNodeContainer);
        addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);
        // diagram.getPersistedChildren().add(node);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(1, dSemanticDiagram.getDiagramElements().size());
        Assert.assertEquals(dNodeContainer, dSemanticDiagram.getOwnedDiagramElements().get(0));
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(1, diagram.getChildren().size());
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
        Assert.assertEquals(node, diagram.getChildren().get(0));
        Assert.assertEquals(dNodeContainer, ((View) diagram.getChildren().get(0)).getElement());
        Assert.assertEquals(String.valueOf(DNodeContainerEditPart.VISUAL_ID), ((View) diagram.getChildren().get(0)).getType());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a Session model with one {@link DNodeList} and the
     * notation model with the corresponding Node.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnSessionModelWithDNodeList() {
        DNodeList dNodeContainer = DiagramFactory.eINSTANCE.createDNodeList();
        // dSemanticDiagram.getDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNodeContainer);
        domain.getCommandStack().execute(addCmd);

        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeListEditPart.VISUAL_ID));
        node.setElement(dNodeContainer);
        addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);
        // diagram.getPersistedChildren().add(node);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(1, dSemanticDiagram.getDiagramElements().size());
        Assert.assertEquals(dNodeContainer, dSemanticDiagram.getOwnedDiagramElements().get(0));
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(1, diagram.getChildren().size());
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
        Assert.assertEquals(node, diagram.getChildren().get(0));
        Assert.assertEquals(dNodeContainer, ((View) diagram.getChildren().get(0)).getElement());
        Assert.assertEquals(String.valueOf(DNodeListEditPart.VISUAL_ID), ((View) diagram.getChildren().get(0)).getType());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a Session model with one {@link DNode} with a
     * borderedNode and the notation model with the corresponding Node and a Node as children.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnSessionModelWithDNodeWithBorderedNode() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        // dSemanticDiagram.getDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNode);
        domain.getCommandStack().execute(addCmd);
        DNode dBorderedNode = DiagramFactory.eINSTANCE.createDNode();
        addCmd = AddCommand.create(domain, dNode, DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES, dBorderedNode);
        domain.getCommandStack().execute(addCmd);

        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeEditPart.VISUAL_ID));
        node.setElement(dNode);
        addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);
        // diagram.getPersistedChildren().add(node);
        Node borderNode = NotationFactory.eINSTANCE.createNode();
        borderNode.setType(String.valueOf(DNode2EditPart.VISUAL_ID));
        borderNode.setElement(dBorderedNode);
        addCmd = AddCommand.create(domain, node, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, borderNode);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        // TODDO
        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(2, dSemanticDiagram.getDiagramElements().size());
        DNode dNodeToAssert = (DNode) dSemanticDiagram.getOwnedDiagramElements().get(0);
        Assert.assertEquals(dNode, dNodeToAssert);
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());
        Assert.assertEquals(1, dNodeToAssert.getOwnedBorderedNodes().size());
        DNode dBorderedNodeToAssert = dNodeToAssert.getOwnedBorderedNodes().get(0);
        Assert.assertEquals(dBorderedNode, dBorderedNodeToAssert);

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(1, diagram.getChildren().size());
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
        Assert.assertEquals(node, diagram.getChildren().get(0));
        Node nodeToAssert = (Node) diagram.getChildren().get(0);
        Assert.assertEquals(dNode, nodeToAssert.getElement());
        Assert.assertEquals(String.valueOf(DNodeEditPart.VISUAL_ID), nodeToAssert.getType());
        Assert.assertEquals(1, nodeToAssert.getChildren().size());

        Node borderNodeToAssert = (Node) nodeToAssert.getChildren().get(0);
        Assert.assertEquals(0, borderNodeToAssert.getSourceEdges().size());
        Assert.assertEquals(0, borderNodeToAssert.getTargetEdges().size());
        Assert.assertEquals(0, borderNodeToAssert.getChildren().size());
        Assert.assertEquals(String.valueOf(DNode2EditPart.VISUAL_ID), borderNodeToAssert.getType());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer do nothing on a Session model with one {@link DNode} with a
     * borderedNode with a borderedNode and the notation model with the corresponding Node and a Node as children.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_DoNothing_OnSessionModelWithDNodeWithBorderedNodeOf2Level() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        // dSemanticDiagram.getDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNode);
        domain.getCommandStack().execute(addCmd);

        DNode dBorderedNode = DiagramFactory.eINSTANCE.createDNode();
        addCmd = AddCommand.create(domain, dNode, DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES, dBorderedNode);
        domain.getCommandStack().execute(addCmd);

        DNode subDBorderedNode = DiagramFactory.eINSTANCE.createDNode();
        addCmd = AddCommand.create(domain, dBorderedNode, DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES, subDBorderedNode);
        domain.getCommandStack().execute(addCmd);

        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeEditPart.VISUAL_ID));
        node.setElement(dNode);
        addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);
        // diagram.getPersistedChildren().add(node);

        Node borderNode = NotationFactory.eINSTANCE.createNode();
        borderNode.setType(String.valueOf(DNode2EditPart.VISUAL_ID));
        borderNode.setElement(dBorderedNode);
        addCmd = AddCommand.create(domain, node, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, borderNode);
        domain.getCommandStack().execute(addCmd);

        Node subBorderNode = NotationFactory.eINSTANCE.createNode();
        subBorderNode.setType(String.valueOf(DNode2EditPart.VISUAL_ID));
        subBorderNode.setElement(subDBorderedNode);
        addCmd = AddCommand.create(domain, borderNode, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, subBorderNode);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        // test if DSemanticDiagram is unchanged
        Assert.assertEquals(3, dSemanticDiagram.getDiagramElements().size());

        DNode dNodeToAssert = (DNode) dSemanticDiagram.getOwnedDiagramElements().get(0);
        Assert.assertEquals(dNode, dNodeToAssert);
        Assert.assertEquals(0, dSemanticDiagram.getEdges().size());
        Assert.assertEquals(1, dNodeToAssert.getOwnedBorderedNodes().size());

        DNode dBorderedNodeToAssert = dNodeToAssert.getOwnedBorderedNodes().get(0);
        Assert.assertEquals(dBorderedNode, dBorderedNodeToAssert);
        Assert.assertEquals(0, dBorderedNodeToAssert.getArrangeConstraints().size());
        Assert.assertEquals(0, dBorderedNodeToAssert.getDecorations().size());
        Assert.assertEquals(0, dBorderedNodeToAssert.getGraphicalFilters().size());
        Assert.assertEquals(0, dBorderedNodeToAssert.getIncomingEdges().size());
        Assert.assertEquals(0, dBorderedNodeToAssert.getOutgoingEdges().size());
        Assert.assertEquals(0, dBorderedNodeToAssert.getParentLayers().size());
        Assert.assertEquals(1, dBorderedNodeToAssert.getOwnedBorderedNodes().size());
        DNode subDBorderedNodeToAssert = dBorderedNodeToAssert.getOwnedBorderedNodes().get(0);

        Assert.assertEquals(subDBorderedNode, subDBorderedNodeToAssert);
        Assert.assertEquals(0, subDBorderedNodeToAssert.getArrangeConstraints().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getDecorations().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getGraphicalFilters().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getIncomingEdges().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getOutgoingEdges().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getParentLayers().size());
        Assert.assertEquals(0, subDBorderedNodeToAssert.getOwnedBorderedNodes().size());

        // test if Diagram is unchanged because not GMF notation update is
        // needed
        Assert.assertEquals(1, diagram.getChildren().size());
        Assert.assertEquals(node, diagram.getChildren().get(0));
        Assert.assertEquals(dSemanticDiagram, diagram.getElement());
        Assert.assertEquals(0, diagram.getEdges().size());
        Node nodeToAssert = (Node) diagram.getChildren().get(0);
        Assert.assertEquals(node, nodeToAssert);
        Assert.assertEquals(dNode, nodeToAssert.getElement());
        Assert.assertEquals(String.valueOf(DNodeEditPart.VISUAL_ID), nodeToAssert.getType());

        Assert.assertEquals(0, nodeToAssert.getSourceEdges().size());
        Assert.assertEquals(0, nodeToAssert.getTargetEdges().size());
        Assert.assertEquals(1, nodeToAssert.getChildren().size());

        Node borderNodeToAssert = (Node) nodeToAssert.getChildren().get(0);
        Assert.assertEquals(0, borderNodeToAssert.getSourceEdges().size());
        Assert.assertEquals(0, borderNodeToAssert.getTargetEdges().size());
        Assert.assertEquals(1, borderNodeToAssert.getChildren().size());
        Assert.assertEquals(String.valueOf(DNode2EditPart.VISUAL_ID), borderNodeToAssert.getType());

        Node subBorderNodeToAssert = (Node) borderNodeToAssert.getChildren().get(0);
        Assert.assertEquals(0, subBorderNodeToAssert.getSourceEdges().size());
        Assert.assertEquals(0, subBorderNodeToAssert.getTargetEdges().size());
        Assert.assertEquals(0, subBorderNodeToAssert.getChildren().size());
        Assert.assertEquals(String.valueOf(DNode2EditPart.VISUAL_ID), subBorderNodeToAssert.getType());

    }

    /**
     * Test that the DDiagramCanonicalSynchronizer remove some GMF Notation elements because there's not corresponding
     * {@link DDiagramElement}.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_RemoveOrphanNode() {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.setType(String.valueOf(DNodeEditPart.VISUAL_ID));
        // diagram.getPersistedChildren().add(node);
        Command addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, node);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertTrue(dSemanticDiagram.getDiagramElements().isEmpty());
        Assert.assertTrue(diagram.getPersistedChildren().isEmpty());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer remove some GMF Notation elements because there's not corresponding
     * {@link DDiagramElement}.
     */
    // TODO
    public void test_DDiagramCanonicalSynchronizer_Synchronize_RemoveOrphanEdge() {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.setType(String.valueOf(DEdgeEditPart.VISUAL_ID));
        // diagram.getPersistedChildren().add(node);
        Command addCmd = AddCommand.create(domain, diagram, NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, edge);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertTrue(dSemanticDiagram.getDiagramElements().isEmpty());
        Assert.assertTrue(diagram.getPersistedChildren().isEmpty());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer add some GMF Notation elements because there's new
     * {@link DDiagramElement}s.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_AddGMFNotationElts_WithNewDNode() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        // dSemanticDiagram.getOwnedDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNode);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertFalse(dSemanticDiagram.getDiagramElements().isEmpty());
        Assert.assertFalse(diagram.getChildren().isEmpty());

        View createdView = (View) diagram.getChildren().get(0);
        Assert.assertEquals(dNode, createdView.getElement());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer add some GMF Notation elements because there's new
     * {@link DDiagramElement}s.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_AddGMFNotationElts_WithNewDNodeContainer() {
        DNodeContainer dNodeContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
        // dSemanticDiagram.getOwnedDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNodeContainer);
        domain.getCommandStack().execute(addCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertFalse(dSemanticDiagram.getDiagramElements().isEmpty());
        Assert.assertFalse(diagram.getChildren().isEmpty());

        View createdView = (View) diagram.getChildren().get(0);
        Assert.assertEquals(dNodeContainer, createdView.getElement());
    }

    /**
     * Test that the DDiagramCanonicalSynchronizer add some GMF Notation elements because there's new
     * {@link DDiagramElement}s.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_AddGMFNotationElts_WithNewDEdge() {
        DNode dNodeSource = DiagramFactory.eINSTANCE.createDNode();
        DNode dNodeTarget = DiagramFactory.eINSTANCE.createDNode();
        DEdge dEdge = DiagramFactory.eINSTANCE.createDEdge();
        EdgeStyle edgeStyle = DiagramFactory.eINSTANCE.createEdgeStyle();
        // dSemanticDiagram.getOwnedDiagramElements().add(dNode);
        Command addNodeSourceCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNodeSource);
        Command addNodeTargetCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNodeTarget);
        Command addEdgeCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dEdge);
        Command setEdgeSourceCmd = SetCommand.create(domain, dEdge, DiagramPackage.Literals.DEDGE__SOURCE_NODE, dNodeSource);
        Command setEdgeTargetCmd = SetCommand.create(domain, dEdge, DiagramPackage.Literals.DEDGE__TARGET_NODE, dNodeTarget);
        Command setEdgeStyleCmd = SetCommand.create(domain, dEdge, DiagramPackage.Literals.DEDGE__OWNED_STYLE, edgeStyle);
        domain.getCommandStack().execute(addNodeSourceCmd);
        domain.getCommandStack().execute(addNodeTargetCmd);
        domain.getCommandStack().execute(addEdgeCmd);
        domain.getCommandStack().execute(setEdgeSourceCmd);
        domain.getCommandStack().execute(setEdgeTargetCmd);
        domain.getCommandStack().execute(setEdgeStyleCmd);

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertEquals(3, dSemanticDiagram.getDiagramElements().size());
        Assert.assertTrue(dSemanticDiagram.getDiagramElements().contains(dNodeSource));
        Assert.assertTrue(dSemanticDiagram.getDiagramElements().contains(dNodeTarget));
        Assert.assertTrue(dSemanticDiagram.getDiagramElements().contains(dEdge));

        Assert.assertEquals(2, diagram.getChildren().size());
        Assert.assertEquals(1, diagram.getEdges().size());

        Edge edge = (Edge) diagram.getEdges().get(0);

        Assert.assertTrue(diagram.getChildren().contains(edge.getSource()));
        Assert.assertTrue(diagram.getChildren().contains(edge.getTarget()));
        Assert.assertNotSame(edge.getSource(), edge.getTarget());

        Assert.assertEquals(dEdge, edge.getElement());
        Assert.assertTrue(dSemanticDiagram.getOwnedDiagramElements().contains(edge.getSource().getElement()));
        Assert.assertTrue(dSemanticDiagram.getOwnedDiagramElements().contains(edge.getTarget().getElement()));

    }

    /**
     * Test that the DDiagramCanonicalSynchronizer add some GMF node with correct bounds using the
     * SiriusLayoutDataManager for node creation.
     */
    public void test_DDiagramCanonicalSynchronizer_Synchronize_UpdateGMF_NodeBoundsCorrectlyForNodeCreation() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        dNode.setResizeKind(ResizeKind.NSEW_LITERAL);
        // dSemanticDiagram.getOwnedDiagramElements().add(dNode);
        Command addCmd = AddCommand.create(domain, dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, dNode);
        domain.getCommandStack().execute(addCmd);

        int x = 10;
        int y = 20;
        int width = 30;
        int height = 40;
        Point location = new Point(x, y);
        Dimension size = new Dimension(width, height);
        SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(dNode, location, size));

        domain.getCommandStack().execute(new SynchronizeGMFModelCommand(domain, dDiagramCanonicalSynchronizer));
        // dDiagramCanonicalSynchronizer.synchronize();

        Assert.assertFalse(dSemanticDiagram.getDiagramElements().isEmpty());
        Assert.assertFalse(diagram.getChildren().isEmpty());

        View createdView = (View) diagram.getChildren().get(0);
        Assert.assertEquals(dNode, createdView.getElement());

        Assert.assertTrue(createdView instanceof Node);
        Node createdNode = (Node) createdView;
        LayoutConstraint layoutConstraint = createdNode.getLayoutConstraint();
        Assert.assertTrue(layoutConstraint instanceof Bounds);
        Bounds bounds = (Bounds) layoutConstraint;
        Assert.assertEquals(x, bounds.getX());
        Assert.assertEquals(y, bounds.getY());
        Assert.assertEquals(width, bounds.getWidth());
        Assert.assertEquals(height, bounds.getHeight());
    }

    @Override
    protected void tearDown() throws Exception {
        dSemanticDiagram = null;
        diagram = null;
        dDiagramCanonicalSynchronizer = null;

        domain.dispose();
        domain = null;
        super.tearDown();
    }

}
