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
package org.eclipse.sirius.tests.unit.diagram.sequence.vsm.interpreted.expression.variables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Model;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.sample.interactions.State;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IEditorPart;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterators;

/**
 * Test Sequence specific AbstractVariable access/renaming.
 * <ol>
 * <li>startingEndPredecessor</li>
 * <li>finishingEndPredecessor</li>
 * <li>coverage</li>
 * </ol>
 * 
 * @author edugueperoux
 */
public class AbstractToolDescription_Precondition_AbstractVariable_Access_Tests extends SiriusDiagramTestCase {

    private static final String UNIT_DATA_ROOT = "/data/sequence/unit/";

    private static final String PATH = UNIT_DATA_ROOT + "variablesAccess/";

    private static final String modelFileName = "variablesAccess.interactions";

    private static final String metaModelFileName = "variablesAccess.ecore";

    private static final String modelerModelFileName = "variablesAccess.odesign";

    private static final String syntaxModelFileName = "variablesAccess.aird";

    private Interaction interaction;

    private Group group;

    private Set<AbstractToolDescription> tools;

    private Collection<DRepresentation> representations;

    private IEditorPart editorPart;

    private SequenceDDiagram sequenceDDiagram;

    private DiagramEditor diagramEditor;

    private SequenceDiagramEditPart sequenceDiagramEditPart;

    private Participant participantA;

    private Participant participantB;

    private DDiagramElement instanceRoleADDiagramElement;

    private DDiagramElement instanceRoleBDDiagramElement;

    private GraphicalEditPart instanceRoleAEditPart;

    private GraphicalEditPart instanceRoleBEditPart;

    private GraphicalEditPart instanceRoleCEditPart;

    private GraphicalEditPart lifelineAEditPart;

    private GraphicalEditPart lifelineBEditPart;

    private GraphicalEditPart lifelineCEditPart;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        // Disable catching of error log
        // TODO: This test must be fixed to avoid message in error log (VP-2365)
        platformProblemsListener.setErrorCatchActive(false);

        String modelPath = TEMPORARY_PROJECT_NAME + "/" + modelFileName;
        String metaModelPath = TEMPORARY_PROJECT_NAME + "/" + metaModelFileName;
        String modelerModelPath = TEMPORARY_PROJECT_NAME + "/" + modelerModelFileName;
        String syntaxModelPath = TEMPORARY_PROJECT_NAME + "/" + syntaxModelFileName;

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + modelFileName, modelPath);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + metaModelFileName, metaModelPath);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + modelerModelFileName, modelerModelPath);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + syntaxModelFileName, syntaxModelPath);

        genericSetUp(modelPath, modelerModelPath, syntaxModelPath);

        interaction = ((Model) session.getSemanticResources().iterator().next().getContents().get(0)).getOwnedInteractions().get(0);

        group = (Group) session.getTransactionalEditingDomain().getResourceSet().getResource(URI.createPlatformResourceURI("/" + modelerModelPath, true), true).getContents().get(0);

        accessor = session.getModelAccessor();

        representations = DialectManager.INSTANCE.getAllRepresentations(session);
        sequenceDDiagram = (SequenceDDiagram) representations.iterator().next();

        editorPart = DialectUIManager.INSTANCE.openEditor(session, sequenceDDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        diagramEditor = (DiagramEditor) editorPart;
        sequenceDiagramEditPart = (SequenceDiagramEditPart) diagramEditor.getDiagramEditPart();

        participantA = interaction.getParticipants().get(0);
        participantB = interaction.getParticipants().get(1);

        instanceRoleADDiagramElement = (DDiagramElement) getFirstRepresentationElement(sequenceDDiagram, participantA);
        instanceRoleBDDiagramElement = (DDiagramElement) getFirstRepresentationElement(sequenceDDiagram, participantB);

        View mainView = sequenceDiagramEditPart.getNotationView();
        View instanceRoleAView = (View) mainView.getChildren().get(0);
        View instanceRoleBView = (View) mainView.getChildren().get(1);
        View instanceRoleCView = (View) mainView.getChildren().get(2);

        Map<?, ?> editPartRegistry = sequenceDiagramEditPart.getViewer().getEditPartRegistry();
        instanceRoleAEditPart = (GraphicalEditPart) editPartRegistry.get(instanceRoleAView);
        instanceRoleBEditPart = (GraphicalEditPart) editPartRegistry.get(instanceRoleBView);
        instanceRoleCEditPart = (GraphicalEditPart) editPartRegistry.get(instanceRoleCView);

        lifelineAEditPart = (GraphicalEditPart) instanceRoleAEditPart.getChildren().get(0);
        lifelineBEditPart = (GraphicalEditPart) instanceRoleBEditPart.getChildren().get(0);
        lifelineCEditPart = (GraphicalEditPart) instanceRoleCEditPart.getChildren().get(0);

        tools = getTools();

    }

    /**
     * Test that sequence specific variables from {@link AbstractToolDescription} are accessibles (without renaming)
     */
    @Test
    public void testSequenceSpecificsVariablesAccess() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();

        Point messageFirstClickLocation = lifelineAEditPart.getFigure().getBounds().getTop().translate(0, 15);

        Point messageSecondClickLocation = lifelineBEditPart.getFigure().getBounds().getTop().translate(0, 15);

        Point nodeCreationLocation = lifelineCEditPart.getFigure().getBounds().getTop().translate(0, 20);

        for (AbstractToolDescription abstractToolDescription : tools) {
            List<AbstractVariable> variables = getVariables(abstractToolDescription);
            variables = keepOnlySequenceSpecificVariables(variables);
            if (!variables.isEmpty()) {
                String precondition = getAcceleoPreconditionWithAllVariables(variables);
                if (precondition != null) {
                    Command changePreconditionCmd = SetCommand.create(domain, abstractToolDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION, precondition);
                    commandStack.execute(changePreconditionCmd);
                }

                // Test creation possible precondition's variables recognized
                if (abstractToolDescription instanceof MessageCreationTool) {
                    EdgeCreationDescription edgeCreationDescription = (EdgeCreationDescription) abstractToolDescription;

                    checkMessageEndVariablesRecognizedForMessageCreation(edgeCreationDescription, messageFirstClickLocation, messageSecondClickLocation);

                    messageFirstClickLocation.translate(0, 20);
                    messageSecondClickLocation.translate(0, 20);

                } else if (abstractToolDescription instanceof NodeCreationDescription && abstractToolDescription instanceof OrderedElementCreationTool) {
                    NodeCreationDescription nodeCreationDescription = (NodeCreationDescription) abstractToolDescription;

                    checkMessageEndVariablesRecognizedForNodeCreation(nodeCreationDescription, nodeCreationLocation);

                    nodeCreationLocation.translate(0, 50);

                } else if (abstractToolDescription instanceof ContainerCreationDescription && abstractToolDescription instanceof CoveringElementCreationTool) {
                    ContainerCreationDescription containerCreationDescription = (ContainerCreationDescription) abstractToolDescription;

                    checkMessageEndVariablesRecognizedForFrameCreation(containerCreationDescription, nodeCreationLocation);

                    nodeCreationLocation.translate(0, 90);
                }
            }
        }
    }

    /**
     * Test that sequence specific variables from {@link AbstractToolDescription} are accessible (after renaming)
     */
    // FIXME : EDU
    @Test
    public void _testSequenceSpecificsVariablesAccessAfterRenaming() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();
        // rename variables
        for (AbstractToolDescription abstractToolDescription : tools) {
            List<AbstractVariable> variables = getVariables(abstractToolDescription);
            variables = keepOnlySequenceSpecificVariables(variables);
            for (AbstractVariable variable : variables) {
                String name = variable.getName();
                String newName = name + "Renamed";
                Command changeVariableNameCmd = SetCommand.create(domain, variable, org.eclipse.sirius.viewpoint.description.DescriptionPackage.Literals.ABSTRACT_VARIABLE__NAME, newName);
                commandStack.execute(changeVariableNameCmd);
            }
        }
        testSequenceSpecificsVariablesAccess();
    }

    /**
     * Specific Assert for message creation tool
     * 
     * @param messageSecondClickLocation
     * @param messageFirstClickLocation
     */
    private void checkMessageEndVariablesRecognizedForMessageCreation(final EdgeCreationDescription edgeCreationDescription, Point messageFirstClickLocation, Point messageSecondClickLocation) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();

        EdgeMapping edgeMapping = edgeCreationDescription.getEdgeMappings().get(0);

        EdgeTarget edgeSourceA = null;
        if (instanceRoleADDiagramElement instanceof AbstractDNode) {
            AbstractDNode instanceRoleADNode = (AbstractDNode) instanceRoleADDiagramElement;
            if (!instanceRoleADNode.getOwnedBorderedNodes().isEmpty()) {
                DNode lifelineADNode = instanceRoleADNode.getOwnedBorderedNodes().get(0);
                if (lifelineADNode instanceof EdgeTarget) {
                    edgeSourceA = lifelineADNode;
                }
            }
        }
        EdgeTarget edgeTargetB = null;
        if (instanceRoleBDDiagramElement instanceof AbstractDNode) {
            AbstractDNode instanceRoleBDNode = (AbstractDNode) instanceRoleBDDiagramElement;
            if (!instanceRoleBDNode.getOwnedBorderedNodes().isEmpty()) {
                DNode lifelineBDNode = instanceRoleBDNode.getOwnedBorderedNodes().get(0);
                if (lifelineBDNode instanceof EdgeTarget) {
                    edgeTargetB = lifelineBDNode;
                }
            }
        }

        CreateConnectionRequest createConnectionRequest = new CreateConnectionRequest();
        createConnectionRequest.setSourceEditPart(lifelineAEditPart);
        createConnectionRequest.setTargetEditPart(lifelineBEditPart);
        createConnectionRequest.setType(RequestConstants.REQ_CONNECTION_START);
        createConnectionRequest.setLocation(messageFirstClickLocation);
        createConnectionRequest.setFactory(new CustomCreationFactory(edgeCreationDescription));

        lifelineAEditPart.showSourceFeedback(createConnectionRequest);
        lifelineBEditPart.showTargetFeedback(createConnectionRequest);
        org.eclipse.gef.commands.Command firstClickCmd = lifelineAEditPart.getCommand(createConnectionRequest);

        // Assert.assertTrue(firstClickCmd.canExecute());

        createConnectionRequest.setType(RequestConstants.REQ_CONNECTION_END);
        createConnectionRequest.setLocation(messageSecondClickLocation);

        lifelineAEditPart.showSourceFeedback(createConnectionRequest);
        lifelineBEditPart.showTargetFeedback(createConnectionRequest);
        final org.eclipse.gef.commands.Command secondClickCmd = lifelineBEditPart.getCommand(createConnectionRequest);

        // Assert.assertNotNull(secondClickCmd);
        // Assert.assertTrue(secondClickCmd.canExecute());
        Command cmd = new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                secondClickCmd.execute();
            }
        };
        // commandStack.execute(cmd);
        TestsUtil.synchronizationWithUIThread();

        commandStack.undo();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Specific Assert for execution/state creation tool
     */
    private void checkMessageEndVariablesRecognizedForNodeCreation(final NodeCreationDescription nodeCreationDescription, Point nodeCreationLocation) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();

        NodeMapping nodeMapping = nodeCreationDescription.getNodeMappings().get(0);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setLocation(nodeCreationLocation);
        createRequest.setFactory(new CustomCreationFactory(nodeCreationDescription));

        final org.eclipse.gef.commands.Command createExecution = lifelineAEditPart.getCommand(createRequest);

        Assert.assertTrue(createExecution.canExecute());
        Command cmd = new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                createExecution.execute();
            }
        };
        commandStack.execute(cmd);
        TestsUtil.synchronizationWithUIThread();

        if (nodeCreationDescription instanceof ExecutionCreationTool) {
            Assert.assertEquals(interaction.getExecutions().size(), 1);
            Execution execution = interaction.getExecutions().get(0);
            Assert.assertTrue(accessor.eInstanceOf(execution, nodeMapping.getDomainClass()));
        } else if (nodeCreationDescription instanceof StateCreationTool) {
            Assert.assertEquals(interaction.getStates().size(), 1);
            State state = interaction.getStates().get(0);
            Assert.assertTrue(accessor.eInstanceOf(state, nodeMapping.getDomainClass()));
        }
        commandStack.undo();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Specific Assert for frame creation tool
     * 
     * @param nodeCreationLocation
     */
    private void checkMessageEndVariablesRecognizedForFrameCreation(final ContainerCreationDescription containerCreationDescription, Point nodeCreationLocation) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();

        ContainerMapping containerMapping = containerCreationDescription.getContainerMappings().get(0);

        CreateRequest createRequest = new CreateRequest();
        createRequest.setLocation(nodeCreationLocation);
        createRequest.setFactory(new CustomCreationFactory(containerCreationDescription));

        final org.eclipse.gef.commands.Command createExecution = lifelineAEditPart.getCommand(createRequest);

        Assert.assertTrue(createExecution.canExecute());
        Command cmd = new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                createExecution.execute();
            }
        };
        commandStack.execute(cmd);
        TestsUtil.synchronizationWithUIThread();

        if (containerCreationDescription instanceof InteractionUseCreationTool) {
            Assert.assertEquals(interaction.getInteractionUses().size(), 1);
            InteractionUse interactionUse = interaction.getInteractionUses().get(0);
            Assert.assertTrue(accessor.eInstanceOf(interactionUse, containerMapping.getDomainClass()));
        } else if (containerCreationDescription instanceof CombinedFragmentCreationTool) {
            Assert.assertEquals(interaction.getCombinedFragments().size(), 1);
            CombinedFragment combinedFragment = interaction.getCombinedFragments().get(0);
            Assert.assertTrue(accessor.eInstanceOf(combinedFragment, containerMapping.getDomainClass()));
        }
        commandStack.undo();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Filter to checks only sequence specific variables.
     */
    private List<AbstractVariable> keepOnlySequenceSpecificVariables(List<AbstractVariable> variables) {
        List<AbstractVariable> filteredVariablesList = new ArrayList<AbstractVariable>();
        for (AbstractVariable variable : variables) {
            if (variable.getClass().getName().startsWith(DescriptionPackage.class.getPackage().getName())) {
                filteredVariablesList.add(variable);
            }
        }
        return filteredVariablesList;
    }

    /**
     * Compute a acceleo expression (always evaluated to true) with all sequence specific variables.
     */
    private String getAcceleoPreconditionWithAllVariables(List<AbstractVariable> variables) {
        String precondition = "aql:";
        Iterator<AbstractVariable> iterator = variables.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        while (iterator.hasNext()) {
            AbstractVariable variable = iterator.next();
            String variableName = variable.getName();
            String affirmation = " " + variableName + "<> null";
            String negation = " not (" + affirmation + ")";
            precondition += affirmation + " or " + negation;
            if (variables.indexOf(variable) < variables.size() - 1) {
                precondition += " and ";
            }
        }
        return precondition;
    }

    /**
     * Get all variables of the odesign model.
     */
    private Set<AbstractToolDescription> getTools() {
        Set<AbstractToolDescription> tools = new HashSet<AbstractToolDescription>();
        Viewpoint viewpoint = group.getOwnedViewpoints().get(0);
        for (RepresentationDescription representationDescription : viewpoint.getOwnedRepresentations()) {
            Iterator<AbstractToolDescription> iterator = Iterators.filter(representationDescription.eAllContents(), AbstractToolDescription.class);
            while (iterator.hasNext()) {
                AbstractToolDescription abstractToolDescription = iterator.next();
                if (!(abstractToolDescription instanceof ExternalJavaAction) && !(abstractToolDescription instanceof ExternalJavaActionCall)) {
                    tools.add(abstractToolDescription);
                }
            }
        }
        return tools;
    }

    /**
     * Get all variables of a tool.
     */
    private List<AbstractVariable> getVariables(AbstractToolDescription abstractToolDescription) {
        List<AbstractVariable> variableNames = new ArrayList<AbstractVariable>();
        for (EStructuralFeature structuralFeature : abstractToolDescription.eClass().getEAllStructuralFeatures()) {
            if (structuralFeature.getEType() instanceof EClass) {
                EClass eClass = (EClass) structuralFeature.getEType();
                if (eClass.getEAllSuperTypes().contains(org.eclipse.sirius.viewpoint.description.DescriptionPackage.Literals.ABSTRACT_VARIABLE)) {
                    AbstractVariable variable = (AbstractVariable) abstractToolDescription.eGet(structuralFeature);
                    if (variable != null) {
                        variableNames.add(variable);
                    }
                }
            }
        }
        return variableNames;
    }

    @Override
    @After
    public void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagramEditor = null;
        accessor = null;
        interaction = null;
        group = null;
        tools = null;
        representations = null;
        sequenceDDiagram = null;
        sequenceDiagramEditPart = null;
        participantA = null;
        participantB = null;
        instanceRoleADDiagramElement = null;
        instanceRoleBDDiagramElement = null;
        instanceRoleAEditPart = null;
        instanceRoleBEditPart = null;
        instanceRoleCEditPart = null;
        lifelineAEditPart = null;
        lifelineBEditPart = null;
        lifelineCEditPart = null;

        super.tearDown();
    }

    class CustomCreationFactory implements CreationFactory {

        private AbstractToolDescription abstractToolDescription;

        public CustomCreationFactory(AbstractToolDescription abstractToolDescription) {
            this.abstractToolDescription = abstractToolDescription;
        }

        @Override
        public Object getNewObject() {
            return abstractToolDescription;
        }

        @Override
        public Object getObjectType() {
            return abstractToolDescription.getClass();
        }

    }
}
