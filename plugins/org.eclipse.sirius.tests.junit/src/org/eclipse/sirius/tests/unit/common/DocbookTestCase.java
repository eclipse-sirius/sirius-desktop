/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookFactory;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Sect2;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;

/**
 * Provides basic setUp and tearDown as well as utility methods for commands
 * testing.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class DocbookTestCase extends SiriusDiagramTestCase implements DocBookModeler {

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_E_CLASS_NAME_PARA_N_LAST = "aql:self.eAllContents(diagram::DEdge)->select(e | e.target.eClass().name ='Para')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_LAST = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Chapter')->last()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_E_CLASS_NAME_D_NODE_CONTAINER_TARGET_NODE_E_CLASS_NAME_D_NODE_CONTAINER_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.eClass().name = 'DNodeContainer' and e.targetNode.eClass().name = 'DNodeContainer')->at(1)";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT3_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST = "aql:self.eAllContents(diagram::DNode)->select(n | n.target.eClass().name = 'Sect3').eAllContents(diagram::DNode)->select(n | n.target.eClass().name = 'Para')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_1 = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Sect2')->at(2)";

    private static final String E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_GET_0 = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name ='Sect1')->first()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT3_N_LAST = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Sect3')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_LAST = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Sect2')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_TITLE_N_GET_0 = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Title')->first()";

    private static final String E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_LAST = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name ='Sect1')->last()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.eClass().name ='Para')->last()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST = "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.target.eClass().name ='Para')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST = "aql:self.eAllContents(diagram::DNode)->select(e | e.target.eClass().name ='Para')->last()";

    private static final String E_ALL_CONTENTS_TARGET_E_CLASS_NAME_INFO_N_LAST = "aql:self.eAllContents()->select(e | e.target.eClass().name ='Info')->last()";

    private static final String E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_LAST = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name ='Chapter')->last()";

    /**
     * Interpreter which can be used by subclasses to avoid instantiating more.
     */
    protected static IInterpreter INTERPRETER;

    /**
     * fetch node exception.
     */
    protected static final String EXCEPTION_FETCH_NODE = "Exception while trying to fetch the created nodes.";

    /**
     * fetch edge exception.
     */
    protected static final String EXCEPTION_FETCH_EDGE = "Exception while trying to fetch the created edges.";

    /**
     * Acceleo interpreter exception.
     */
    protected static final String EXCEPTION_ACCELEO_INTERPRETER = "AcceleoInterpreter failed to retrieve nodes/edges.";

    /**
     * casting exception.
     */
    protected static final String EXCEPTION_CAST = "Exception while trying to cast request result.";

    /**
     * Used as a failure message when too many unmatched element are found in
     * the model.
     */
    protected static final String UNEXPECTED_UNMATCH_COUNT = "Unexpected count of unmatched elements in the model.";

    /**
     * Used as a failure message when too many differences are detected in the
     * model.
     */
    protected static final String UNEXPECTED_DIFFERENCES_COUNT = "Unexpected differences count in the model.";

    /**
     * Used as a failure message when a particular unmatched element isn't an
     * instance of the expected type.
     */
    protected static final String UNEXPECTED_UNMATCH_ELEMENT = "Unexpected type of unmatched element.";

    /** Sirius of the obvious diagram. */
    protected DDiagram obviousDiagram;

    /** Sirius of the obvious diagram. */
    protected DDiagram evoluateDiagram;

    /** Input model loaded as an EObject. */
    protected EObject sessionModel;

    /*
     * The representation built on docbook model.
     */
    protected DRepresentation myRepresentation;

    /*
     * Path of the analysis.
     */
    protected String analysisPath = "platform:/plugin/org.eclipse.sirius.tests.sample.docbook.design/sample/test.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);

        sessionModel = session.getSessionResource().getContents().get(0);

        INTERPRETER = session.getInterpreter();

        obviousDiagram = (DDiagram) getRepresentations("obviousDiagram").iterator().next();
        assertNotNull("Couldn't find the obvious diagram.", obviousDiagram);

        evoluateDiagram = (DDiagram) getRepresentations("evoluate view").iterator().next();
        assertNotNull("Couldn't find the evoluate diagram.", obviousDiagram);

        InterpreterRegistry.prepareImportsFromSession(INTERPRETER, session);

    }

    /**
     * Returns a command that can be used to create a chapter in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @return a create-chapter command
     */
    protected Command createChapterCommand(final DDiagram diagram) {
        final AbstractToolDescription createChapterTool = getTool(diagram, "chapter");
        assertNotNull("Could not find the tool to create a chapter", createChapterTool);
        return getCommandFactory().buildCreateContainerCommandFromTool(diagram, (ContainerCreationDescription) createChapterTool);
    }

    protected Command createChapterCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createChapterTool = getTool(diagram, "chap tool");
        assertNotNull("Could not find the tool to create a chapter", createChapterTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createChapterTool);
    }

    protected Command createInfoCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createInfoTool = getTool(diagram, "info tool");
        assertNotNull("Could not find the tool to create an info", createInfoTool);
        return getCommandFactory().buildCreateContainerCommandFromTool(diagram, (ContainerCreationDescription) createInfoTool);
    }

    protected Command createTitleCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createTitleTool = getTool(diagram, "title tool");
        assertNotNull("Could not find the tool to create a title", createTitleTool);
        return getCommandFactory().buildCreateContainerCommandFromTool(diagram, (ContainerCreationDescription) createTitleTool);
    }

    protected Command createTitleCommandInInfoInEvoluateView(final DDiagram diagram, final DNodeContainer container) {
        final AbstractToolDescription createTitleTool = getTool(diagram, "title tool");
        assertNotNull("Could not find the tool to create a title", createTitleTool);
        return getCommandFactory().buildCreateContainerCommandFromTool(container, (ContainerCreationDescription) createTitleTool);
    }

    protected Command createBigSectCommandInInfoInEvoluateView(final DDiagram diagram, final DNodeContainer container) {
        final AbstractToolDescription createBigSectTool = getTool(diagram, "big sect tool");
        assertNotNull("Could not find the tool to create a title", createBigSectTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(container, (NodeCreationDescription) createBigSectTool);
    }

    /**
     * Returns a command that can be used to create a big section in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @param container
     *            in which the node shall be created
     * @return a create-big-section command
     */
    protected Command createBigSectionCommand(final DDiagram diagram, final DNodeContainer container) {
        final AbstractToolDescription createBigSectionTool = getTool(diagram, "big section");
        assertNotNull("Could not find the tool to create a big section", createBigSectionTool);
        return getCommandFactory().buildCreateContainerCommandFromTool(container, (ContainerCreationDescription) createBigSectionTool);
    }

    protected Command createBigSectionCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createBigSectionTool = getTool(diagram, "big sect tool");
        assertNotNull("Could not find the tool to create a big section", createBigSectionTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createBigSectionTool);
    }

    protected Command createAuthorCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createAuthorTool = getTool(diagram, "author tool");
        assertNotNull("Could not find the tool to create an author", createAuthorTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createAuthorTool);
    }

    /**
     * Returns a command that can be used to create a medium section in the
     * given DDiagram. Will lead to a failure if the required tool cannot be
     * retrieved in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @param container
     *            in which the node shall be created
     * @return a create-medium-section command
     */
    protected Command createMediumSectionCommand(final DDiagram diagram, final DNodeContainer container) {
        final AbstractToolDescription createMediumSectionTool = getTool(diagram, "medium section");
        assertNotNull("Could not find the tool to create a medium section", createMediumSectionTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(container, (NodeCreationDescription) createMediumSectionTool);
    }

    protected Command createMediumSectionCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createMediumSectionTool = getTool(diagram, "med sect tool");
        assertNotNull("Could not find the tool to create a medium section", createMediumSectionTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createMediumSectionTool);
    }

    /**
     * Returns a command that can be used to create a tiny section in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @return a create-tiny section command
     */
    protected Command createTinySectionCommand(final DDiagram diagram) {
        final AbstractToolDescription createTinySectionTool = getTool(diagram, "tiny section");
        assertNotNull("Could not find the tool to create a tiny section", createTinySectionTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createTinySectionTool);
    }

    protected Command createTinySectionCommandInEvoluateView(final DDiagram diagram) {
        final AbstractToolDescription createTinySectionTool = getTool(diagram, "tiny sect tool");
        assertNotNull("Could not find the tool to create a tiny section", createTinySectionTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createTinySectionTool);
    }

    /**
     * Returns a command that can be used to create a tiny note in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @param container
     *            in which the node shall be created
     * @return a create-tiny-note command
     */
    protected Command createTinyNoteCommand(final DDiagram diagram, final DNode container) {
        final AbstractToolDescription createTinyNoteTool = getTool(diagram, "tiny note");
        assertNotNull("Could not find the tool to create a tiny note", createTinyNoteTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(container, (NodeCreationDescription) createTinyNoteTool);
    }

    /**
     * Returns a command that can be used to create a chapter note in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            in which the node shall be created
     * @param container
     *            in which the node shall be created
     * @return a create-chapter-note command
     */
    protected Command createChapterNoteCommand(final DDiagram diagram, final DNodeContainer container) {
        final AbstractToolDescription createChapterNoteTool = getTool(diagram, "chapter note");
        assertNotNull("Could not find the tool to create a chapter note", createChapterNoteTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(container, (NodeCreationDescription) createChapterNoteTool);
    }

    /**
     * Returns a command that can be used to create a note in the given
     * DDiagram. Will lead to a failure if the required tool cannot be retrieved
     * in <tt>diagram</tt>.
     * 
     * @param diagram
     *            diagram in which the node shall be created
     * @return create-note command
     */
    protected Command createNoteCommande(final DDiagram diagram) {
        final AbstractToolDescription createNoteTool = getTool(diagram, "note");
        assertNotNull("Could not find the tool to create a chapter", createNoteTool);
        return getCommandFactory().buildCreateNodeCommandFromTool(diagram, (NodeCreationDescription) createNoteTool);
    }

    /**
     * Returns a command that will create a link between the given
     * <tt>parent</tt> and <tt>child</tt>. This will result in a test failure if
     * the containment tool cannot be retrieved in the given viewpoint.
     * 
     * @param diagram
     *            The viewpoint containing the two function and in which we seek
     *            to retrieve a containment tool.
     * @param source
     *            The child function.
     * @param dest
     *            The parent function.
     * @return A command that will create a containment link between the given
     *         <tt>parent</tt> and <tt>child</tt>
     */
    protected Command createTinySectionEdgeCommand(final DDiagram diagram, final EdgeTarget source, final EdgeTarget dest) {
        final EdgeCreationDescription createTinySectionEdgeTool = (EdgeCreationDescription) getTool(diagram, "tiny section link");
        assertNotNull("Could not find the tool to create a tiny section Edge", createTinySectionEdgeTool);
        return getCommandFactory().buildCreateEdgeCommandFromTool(source, dest, createTinySectionEdgeTool);
    }

    protected Command createNoteEdgeCommand(final DDiagram diagram, final EdgeTarget source, final EdgeTarget dest) {
        final EdgeCreationDescription createEdgeTool = (EdgeCreationDescription) getTool(diagram, "note tool");
        assertNotNull("Could not find the tool to create a note Edge", createEdgeTool);
        return getCommandFactory().buildCreateEdgeCommandFromTool(source, dest, createEdgeTool);
    }

    /**
     * Used to create a navigation link. The navigation link appears in the
     * diagram description only once the command to create the diagram throw the
     * navigation link is executed.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param container
     *            in which the navigation link is defined.
     * @return the command to create the diagram.
     */
    protected Command createDiagramThrowNodeNavigationLinkCommand(final DDiagram diagram, final DNode container) {
        final DiagramCreationDescription createDiagramTool = (DiagramCreationDescription) getTool(diagram, "navigate throw node");
        assertNotNull("Could not find the tool to create a diagram throw a node navigation link", createDiagramTool);
        return getCommandFactory().buildCreateRepresentationFromDescription(createDiagramTool, container, "NewDiagram");
    }

    /**
     * Used to create a navigation link. The navigation link appears in the
     * diagram description only once the command to create the diagram throw the
     * navigation link is executed.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param container
     *            in which the navigation link is defined.
     * @return the command to create the diagram.
     */
    protected Command createDiagramThrowContainerNavigationLinkCommand(final DDiagram diagram, final DNodeContainer container) {
        final DiagramCreationDescription createDiagramTool = (DiagramCreationDescription) getTool(diagram, "navigate throw container");
        assertNotNull("Could not find the tool to create a diagram throw a container navigation link", createDiagramTool);
        return getCommandFactory().buildCreateRepresentationFromDescription(createDiagramTool, container, "NewDiagram");
    }

    /**
     * Used to create a navigation link. The navigation link appears in the
     * diagram description only once the command to create the diagram throw the
     * navigation link is executed.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param container
     *            in which the navigation link is defined.
     * @return the command to create the diagram.
     */
    protected Command createDiagramThrowEdgeNavigationLinkCommand(final DDiagram diagram, final DEdge container) {
        final DiagramCreationDescription createDiagramTool = (DiagramCreationDescription) getTool(diagram, "navigate throw link");
        assertNotNull("Could not find the tool to create a diagram throw an edge navigation link", createDiagramTool);
        return getCommandFactory().buildCreateRepresentationFromDescription(createDiagramTool, container, "NewDiagram");
    }

    /**
     * Used to create an edge target reconnection command from a node or
     * container to a node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeTargetToNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "medium section target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target to a node", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge target reconnection command from a node or
     * container to a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeTargetToContainer(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target to a container", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge source reconnection command from a node or
     * container to a node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeSourceToNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "medium section source reconnection");
        assertNotNull("Could not find the tool to reconnect edge source to a node", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge source reconnection command from a node or
     * container to a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeSourceToContainer(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter source reconnection");
        assertNotNull("Could not find the tool to reconnect edge source to a container", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge source reconnection command from a bordered node
     * to a node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeSourceFromBorderedNodeToDNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note medium section source reconnection");
        assertNotNull("Could not find the tool to reconnect edge source from bordered node to DNode", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge source reconnection command from a bordered node
     * to a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeSourceFromBorderedNodeToContainer(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note big section source reconnection");
        assertNotNull("Could not find the tool to reconnect edge source from bordered node to container", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge target reconnection command from a bordered node
     * to a node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeTargetFromBorderedNodeToDNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note medium section target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target from bordered node to DNode", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge target reconnection command from a bordered node
     * to a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeTargetFromBorderedNodeToContainer(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note big section target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target from bordered node to container", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge source reconnection to a bordered node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeSourceToBorderedNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note source reconnection");
        assertNotNull("Could not find the tool to reconnect edge target to bordered node", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to create an edge target reconnection to a bordered node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param edge
     *            to reconnect.
     * @param source
     *            node where edge target is connected.
     * @param target
     *            node where edge target has to be reconnected.
     * @return the reconnection command.
     */
    protected Command reconnectEdgeTargetToBorderedNode(final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "chapter note target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target to a bordered node", reconnectTool);
        return getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, source, target);
    }

    /**
     * Used to drag & drop a container in another container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param target
     *            node where edge target has to be reconnected.
     * @param element
     *            node to drop.
     * @return the drag & drop command.
     */
    protected Command dragAndDropContainerInsideContainer(final DDiagram diagram, final DragAndDropTarget target, final DDiagramElement element) {
        final ContainerDropDescription dragAndDropTool = (ContainerDropDescription) getTool(diagram, "drop big section in container");
        assertNotNull("Could not find the tool to drop a container in a container", dragAndDropTool);
        return getCommandFactory().buildDropInContainerCommandFromTool(target, element, dragAndDropTool);
    }

    /**
     * Used to drag & drop a node in a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param target
     *            node where edge target has to be reconnected.
     * @param element
     *            node to drop.
     * @return the drag & drop command.
     */
    protected Command dragAndDropNodeInsideContainer(final DDiagram diagram, final DragAndDropTarget target, final DDiagramElement element) {
        final ContainerDropDescription dragAndDropTool = (ContainerDropDescription) getTool(diagram, "drop medium section in big section");
        assertNotNull("Could not find the tool to drop a node in a container", dragAndDropTool);
        return getCommandFactory().buildDropInContainerCommandFromTool(target, element, dragAndDropTool);
    }

    /**
     * Used to drag & drop a bordered node in a container.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param target
     *            node where edge target has to be reconnected.
     * @param element
     *            node to drop.
     * @return the drag & drop command.
     */
    protected Command dragAndDropBorderedNodeInsideContainer(final DDiagram diagram, final DragAndDropTarget target, final DDiagramElement element) {
        final ContainerDropDescription dragAndDropTool = (ContainerDropDescription) getTool(diagram, "drop chapter note in chapter");
        assertNotNull("Could not find the tool to drop a bordered node in a container", dragAndDropTool);
        return getCommandFactory().buildDropInContainerCommandFromTool(target, element, dragAndDropTool);
    }

    /**
     * Used to drag & drop a bordered node in a node.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param target
     *            node where edge target has to be reconnected.
     * @param element
     *            node to drop.
     * @return the drag & drop command.
     */
    protected Command dragAndDropBorderedNodeInsideNode(final DDiagram diagram, final DragAndDropTarget target, final DDiagramElement element) {
        final ContainerDropDescription dragAndDropTool = (ContainerDropDescription) getTool(diagram, "drop tiny note in tiny section");
        assertNotNull("Could not find the tool to drop a bordered node in a node", dragAndDropTool);
        return getCommandFactory().buildDropInContainerCommandFromTool(target, element, dragAndDropTool);
    }

    /**
     * Used to drag & drop a bordered node in a diagram.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param target
     *            node where edge target has to be reconnected.
     * @param element
     *            node to drop.
     * @return the drag & drop command.
     */
    protected Command dragAndDropBorderedNodeInsideDiagram(final DDiagram diagram, final DragAndDropTarget target, final DDiagramElement element) {
        final ContainerDropDescription dragAndDropTool = (ContainerDropDescription) getTool(diagram, "drop chapter note in diagram");
        assertNotNull("Could not find the tool to drop a bordered node in the diagram", dragAndDropTool);
        return getCommandFactory().buildDropInContainerCommandFromTool(target, element, dragAndDropTool);
    }

    /**
     * Used to direct-edit Node or Container Label.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param repElement
     *            the element on which the label should be changed.
     * @param newValue
     *            the new label value.
     * @return the direct edit tool.
     */
    protected Command directEditNodeAndContainerLabel(final DDiagram diagram, final DRepresentationElement repElement, final String newValue) {
        final DirectEditLabel directEditTool = (DirectEditLabel) getTool(diagram, "direct edit node and container");
        assertNotNull("Could not find the direct edit tool for node and container", directEditTool);
        return getCommandFactory().buildDirectEditLabelFromTool(repElement, directEditTool, newValue);
    }

    /**
     * Used to direct-edit Bordererd Node Label.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param repElement
     *            the element on which the label should be changed.
     * @param newValue
     *            the new label value.
     * @return the direct edit tool.
     */
    protected Command directEditBorderedNodeLabel(final DDiagram diagram, final DRepresentationElement repElement, final String newValue) {
        final DirectEditLabel directEditTool = (DirectEditLabel) getTool(diagram, "direct edit bordered node");
        assertNotNull("Could not find the direct edit tool for node and container", directEditTool);
        return getCommandFactory().buildDirectEditLabelFromTool(repElement, directEditTool, newValue);
    }

    /**
     * Used to direct-edit edge Label.
     * 
     * @param diagram
     *            where the tool is defined.
     * @param repElement
     *            the element on which the label should be changed.
     * @param newValue
     *            the new label value.
     * @return the direct edit tool.
     */
    protected Command directEditEdgeLabel(final DDiagram diagram, final DRepresentationElement repElement, final String newValue) {
        final DirectEditLabel directEditTool = (DirectEditLabel) getTool(diagram, "direct edit note");
        assertNotNull("Could not find the direct edit tool for edges", directEditTool);
        return getCommandFactory().buildDirectEditLabelFromTool(repElement, directEditTool, newValue);
    }

    protected Command getSingleSelectionWizardCommand(final DDiagram diagram, final DSemanticDecorator container, final Collection<EObject> selectedElement) {
        final AbstractToolDescription selectionWizardTool = getTool(diagram, "single selection wizard");
        assertNotNull("Could not find the single selection wizard tool", selectionWizardTool);
        return getCommandFactory().buildSelectionWizardCommandFromTool((SelectionWizardDescription) selectionWizardTool, container, selectedElement);
    }

    protected Command getMultipleSelectionWizardCommand(final DDiagram diagram, final DSemanticDecorator container, final Collection<EObject> selectedElement) {
        final AbstractToolDescription selectionWizardTool = getTool(diagram, "multiple selection wizard");
        assertNotNull("Could not find the multiple selection wizard tool", selectionWizardTool);
        return getCommandFactory().buildSelectionWizardCommandFromTool((SelectionWizardDescription) selectionWizardTool, container, selectedElement);
    }

    /**
     * Browses the whole content of the given EObject (via
     * {@link EObject#eAllContents()}) and tries to get the functionnal ID of
     * each encountered object.
     * 
     * @param model
     *            The model we which to generate functional IDs for.
     */
    protected void generateIDs(final EObject model) {
        final TreeIterator<EObject> iterator = model.eAllContents();
        while (iterator.hasNext()) {
            final EObject next = iterator.next();
            final List<EStructuralFeature> features = next.eClass().getEAllStructuralFeatures();
            for (int i = 0; i < features.size(); i++)
                next.eGet(features.get(i));
        }
    }

    /**
     * This will execute the given Command if possible and return
     * <code>True</code> if it is, <code>False</code> otherwise.
     * 
     * @param command
     *            The command that needs be executed.
     * @return <code>True</code> if the command was executable,
     *         <code>False</code> otherwise.
     */
    protected boolean execute(final Command command) {
        boolean result = command.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        return result;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements.
     * 
     * @return chapter container Node
     */
    public DNodeContainer createChapter() {
        final Command command = createChapterCommand(obviousDiagram);
        EObject chapter = null;
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        try {
            chapter = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject chapter.");
            e.printStackTrace();
        }
        if (chapter instanceof DNodeContainer) {
            return (DNodeContainer) chapter;
        } else
            return null;
    }

    public DNodeContainer createInfoInEvoluateView() {
        EObject info = null;
        Command command = createChapterCommandInEvoluateView(evoluateDiagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        command = createInfoCommandInEvoluateView(evoluateDiagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            info = INTERPRETER.evaluateEObject(evoluateDiagram, E_ALL_CONTENTS_TARGET_E_CLASS_NAME_INFO_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject info");
            e.printStackTrace();
        }
        if (info instanceof DNodeContainer) {
            return (DNodeContainer) info;
        } else
            return null;
    }

    public void createTitleInEvoluateView() {
        createNoteInEvoluateView();
        final Command command = createTitleCommandInEvoluateView(evoluateDiagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates finally a note bordered node with two
     * edges toward and from the chapter container.
     * 
     * @return the note bordered node, the edge going from the note to the
     *         chapter and the edges going from the chapter to the note.
     */
    public List<EObject> createChapterNote() {
        final DNodeContainer chapter = createChapter();
        EObject note = null;
        EObject edgeFromNote = null;
        EObject edgeToNote = null;
        List<EObject> createdElts = new ArrayList<EObject>(4);

        final DCommand command = (DCommand) createChapterNoteCommand(obviousDiagram, chapter);
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            note = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject chapter note.");
            e.printStackTrace();
        }

        createdElts.add(note);

        try {
            edgeFromNote = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        createdElts.add(edgeFromNote);

        try {
            edgeToNote = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        createdElts.add(edgeToNote);

        createdElts.add(chapter);

        return createdElts;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a note bordered node with two edges toward
     * and from the chapter container. creates a big section and a medium
     * section in the chapter.
     * 
     * @return the note bordered node
     */
    public DNode createMediumSectionAndChapterNote() {
        EObject bigSection = null;

        final List<EObject> elts = createChapterNote();
        final DNodeContainer chapter = (DNodeContainer) elts.get(3);

        DCommand command = (DCommand) createBigSectionCommand(obviousDiagram, chapter);
        assertTrue("Could not create container.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            bigSection = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject big section.");
            e.printStackTrace();
        }

        if (bigSection instanceof DNodeContainer) {
            command = (DCommand) createMediumSectionCommand(obviousDiagram, (DNodeContainer) bigSection);
            assertTrue("Could not create node", command.canExecute());
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }

        return (DNode) elts.get(0);
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements.
     * 
     * @return title bordered Node
     */
    public DNode createChapterReturnTitle() {
        final DCommand command = (DCommand) createChapterCommand(obviousDiagram);
        EObject title = null;
        assertTrue("Could not create container and bordered node.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            title = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_TITLE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject title.");
            e.printStackTrace();
        }

        if (title instanceof DNode) {
            return (DNode) title;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates finally a big section container inside the
     * chapter container.
     * 
     * @return big section container node
     */
    public DNodeContainer createBigSection() {
        final DNodeContainer chapter = createChapter();
        EObject bigSection = null;
        final DCommand command = (DCommand) createBigSectionCommand(obviousDiagram, chapter);
        assertTrue("Could not create container.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            bigSection = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject big section.");
            e.printStackTrace();
        }

        if (bigSection instanceof DNodeContainer) {
            return (DNodeContainer) bigSection;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a big section container inside the chapter
     * container. creates finally a medium section node inside the big section
     * container.
     * 
     * @return medium section node
     */
    public DNode createMediumSection() {
        final DNodeContainer bigSection = createBigSection();
        final DCommand command = (DCommand) createMediumSectionCommand(obviousDiagram, bigSection);
        EObject mediumSection = null;
        assertTrue("Could not create node", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            mediumSection = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject medium section.");
            e.printStackTrace();
        }

        if (mediumSection instanceof DNode) {
            return (DNode) mediumSection;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a big section container inside the chapter
     * container. creates a medium section node inside the big section
     * container. creates finally a tiny section node in the diagram, an edge
     * between the tiny section node and the chapter container and an edge
     * between the tiny section and the medium section node.
     * 
     * @return tiny section node
     */
    public DNode createTinySection() {
        createMediumSection();
        final DCommand command = (DCommand) createTinySectionCommand(obviousDiagram);
        EObject tinySection = null;
        assertTrue("Could not create node", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            tinySection = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT3_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject tiny section.");
            e.printStackTrace();
        }

        if (tinySection instanceof DNode) {
            return (DNode) tinySection;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a big section container inside the chapter
     * container. creates a medium section node inside the big section
     * container. creates a tiny section node in the diagram, an edge between
     * the tiny section node and the chapter container and an edge between the
     * tiny section and the medium section node. creates finally a second medium
     * section.
     * 
     * @return second medium section node
     */
    public EdgeTarget createTinySectionAndExtraMediumSection() {
        createTinySection();
        EObject bigSect = null;
        EObject medSect = null;
        EdgeTarget res = null;

        try {
            bigSect = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject big section.");
            e.printStackTrace();
        }

        if (bigSect instanceof DNodeContainer) {
            final DCommand command = (DCommand) createMediumSectionCommand(obviousDiagram, (DNodeContainer) bigSect);
            assertTrue("Could not create node", command.canExecute());
            session.getTransactionalEditingDomain().getCommandStack().execute(command);

            try {
                medSect = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_1);
            } catch (final EvaluationException e) {
                fail("Exception while trying to get the EObject medium section.");
                e.printStackTrace();
            }

            if (medSect instanceof EdgeTarget) {
                res = (EdgeTarget) medSect;
            }
        }

        return res;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a big section container inside the chapter
     * container. creates a medium section node inside the big section
     * container. creates a tiny section node in the diagram, an edge between
     * the tiny section node and the chapter container and an edge between the
     * tiny section and the medium section node. creates finally a bordered node
     * on the tiny section node.
     * 
     * @return created tiny note.
     */
    public DNode createTinyNote() {
        final DNode tinySection = createTinySection();
        EObject tinyNote = null;
        final DCommand command = (DCommand) createTinyNoteCommand(obviousDiagram, tinySection);
        assertTrue("Could not create tiny note", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            tinyNote = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT3_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject tiny note.");
            e.printStackTrace();
        }

        if (tinyNote instanceof DNode) {
            return (DNode) tinyNote;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates finally a note (node) in diagram and an
     * edge between the note node and the title bordered node.
     */
    public void createNote() {
        createMediumSection();
        final DCommand command = (DCommand) createNoteCommande(obviousDiagram);
        assertTrue("Could not create note", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }

    /**
     * creates two chapters containers, two titles bordered nodes on them and
     * two edges between titles and related chapters. creates finally an edge
     * between both chapters containers.
     * 
     * @return the edge between both chapters
     */
    public DEdge createTwoChapters() {
        final DCommand command1 = (DCommand) createChapterCommand(obviousDiagram);
        assertTrue("Could not create container, bordered node or edge.", command1.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command1);
        final DCommand command2 = (DCommand) createChapterCommand(obviousDiagram);
        assertTrue("Could not create container, bordered node or edge.", command2.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command2);
        EObject edge = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_E_CLASS_NAME_D_NODE_CONTAINER_TARGET_NODE_E_CLASS_NAME_D_NODE_CONTAINER_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge between two chapters.");
            e.printStackTrace();
        }

        if (edge instanceof DEdge) {
            return (DEdge) edge;
        } else
            return null;
    }

    /**
     * creates a chapter container, a title bordered node on it and an edge
     * between both elements. creates a big section container inside the chapter
     * container. creates a medium section node inside the big section
     * container. creates a tiny section node in the diagram, an edge between
     * the tiny section node and the chapter container and an edge between the
     * tiny section and the medium section node. creates finally two bordered
     * nodes on the tiny section node and an edge between both bordered nodes.
     */
    public void createTwoTinyNotes() {
        final DNode tinySection = createTinySection();
        final DCommand command1 = (DCommand) createTinyNoteCommand(obviousDiagram, tinySection);
        assertTrue("Could not create bordered node", command1.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command1);
        final DCommand command2 = (DCommand) createTinyNoteCommand(obviousDiagram, tinySection);
        assertTrue("Could not create bordered node", command2.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command2);
    }

    public List<EObject> createNoteInEvoluateView() {
        EObject chapter = null;
        EObject tinySect = null;
        EObject note = null;
        List<EObject> createdElts = new ArrayList<EObject>(3);

        Command command = createChapterCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create the first node.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        command = createBigSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create the second node.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        command = createMediumSectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create the third node.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        command = createTinySectionCommandInEvoluateView(evoluateDiagram);
        assertTrue("Could not create the forth node.", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        try {
            chapter = INTERPRETER.evaluateEObject(evoluateDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject chapter.");
            e.printStackTrace();
        }

        try {
            tinySect = INTERPRETER.evaluateEObject(evoluateDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT3_N_LAST);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject tiny section.");
            e.printStackTrace();
        }

        if (chapter instanceof DNode && tinySect instanceof DNode) {
            createdElts.add(tinySect);
            command = createNoteEdgeCommand(evoluateDiagram, (EdgeTarget) chapter, (EdgeTarget) tinySect);
            assertTrue("Could not create the edge.", command.canExecute());
            session.getTransactionalEditingDomain().getCommandStack().execute(command);

            try {
                note = INTERPRETER.evaluateEObject(evoluateDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_E_CLASS_NAME_PARA_N_LAST);
            } catch (final EvaluationException e) {
                fail("Exception while trying to get the EObject note.");
                e.printStackTrace();
            }
            if (note instanceof DEdge) {
                createdElts.add(note);
                createdElts.add(chapter);
                return createdElts;
            }
        }
        return null;
    }

    protected ContainerMapping getContainerMappingFromName(final DDiagram diagram, final String mappingName) {
        final List<ContainerMapping> mappings = DiagramComponentizationTestSupport.getAllContainerMappings(session, diagram.getDescription());
        ContainerMapping theContainerMapping = null;
        for (int i = 0; i < mappings.size(); i++) {

            final ContainerMapping mapping = mappings.get(i);
            if (mapping.getName().equals(mappingName)) {
                theContainerMapping = mapping;
                break;
            }
        }
        return theContainerMapping;
    }

    protected EdgeMapping getEdgeMappingFromName(final DDiagram diagram, final String mappingName) {
        final List<EdgeMapping> mappings = DiagramComponentizationTestSupport.getAllEdgeMappings(session, diagram.getDescription());
        EdgeMapping theEdgeMapping = null;
        for (int i = 0; i < mappings.size(); i++) {

            final EdgeMapping mapping = mappings.get(i);
            if (mapping.getName().equals(mappingName)) {
                theEdgeMapping = mapping;
                break;
            }
        }
        return theEdgeMapping;
    }

    public void refreshRepresentation(final DRepresentation diagram) {
        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
            }
        });
    }

    /*
     * Remove the first chapter under the book.
     */
    public void deleteChapter() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                EcoreUtil.remove(semanticModel.eContents().get(0));
            }
        });
    }

    /*
     * Rename the first chapter under the book.
     */
    public void renameChapter(final String name) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                ((Book) semanticModel).getChapter().get(0).setId(name);
            }
        });
    }

    /*
     * Remove the Para element under the book.
     */
    public void deleteNote() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                EcoreUtil.remove(((Book) semanticModel).getChapter().get(0).getPara().get(0));
            }
        });
    }

    /*
     * Add a second chapter to the semantic model.
     */
    public void addChapter() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Chapter chap = DocbookFactory.eINSTANCE.createChapter();
                ((Book) semanticModel).getChapter().add(chap);
            }
        });
    }

    /*
     * Add a second chapter and a big section (Sect1) to the semantic model.
     */
    public void addChapterAndBigSection() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Sect1 sect1 = DocbookFactory.eINSTANCE.createSect1();
                Chapter chap = DocbookFactory.eINSTANCE.createChapter();
                chap.getSect1().add(sect1);
                ((Book) semanticModel).getChapter().add(chap);
            }
        });
    }

    /*
     * add a big (Sect1), a medium (Sect2) and a tiny section(Sect3) under the
     * chapter.
     */
    public void addTinySection() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Sect1 bigSect = DocbookFactory.eINSTANCE.createSect1();
                Sect2 medSect = DocbookFactory.eINSTANCE.createSect2();
                Sect3 tinySect = DocbookFactory.eINSTANCE.createSect3();
                medSect.getSect3().add(tinySect);
                bigSect.getSect2().add(medSect);
                ((Book) semanticModel).getChapter().get(0).getSect1().add(bigSect);
            }
        });
    }

    /*
     * add a "Para" element under the chapter.
     */
    public void addNote() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Para note = DocbookFactory.eINSTANCE.createPara();
                ((Book) semanticModel).getChapter().get(0).getPara().add(note);
            }
        });
    }

    /*
     * Move the "Para" element from the chapter to the big section (Sect1).
     */
    public void moveNoteUnderBigSection() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Para note = ((Book) semanticModel).getChapter().get(0).getPara().get(0);
                ((Book) semanticModel).getChapter().get(0).getSect1().get(0).getPara().add(note);
            }
        });
    }

    /*
     * Move the "Sect1" element from the first chapter to the second chapter .
     */
    public void moveBigSectionUnderChapter() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Sect1 sect1 = ((Book) semanticModel).getChapter().get(0).getSect1().get(0);
                ((Book) semanticModel).getChapter().get(1).getSect1().add(sect1);
            }
        });
    }

    /*
     * Move the "Sect2" element from the first Sect1 to the second Sect1.
     */
    public void moveMediumSectionUnderBigSection() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Sect2 sect2 = ((Book) semanticModel).getChapter().get(0).getSect1().get(0).getSect2().get(0);
                ((Book) semanticModel).getChapter().get(1).getSect1().get(0).getSect2().add(sect2);
            }
        });
    }
    @Override
    protected void tearDown() throws Exception {
        DiagramEventBroker.stopListening(session.getTransactionalEditingDomain());
        obviousDiagram = null;
        clearErrors();
        super.tearDown();
    }

}
