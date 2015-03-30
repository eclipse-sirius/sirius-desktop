/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layoutingmode;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

import com.google.common.collect.Iterables;

/**
 * <p>
 * Ensures that Layouting mode forbids the execution of excepted tools. Also
 * checks Layouting mode lifecycle.
 * </p>
 * <p>
 * Tested tools :
 * <ul>
 * <li>Reconnection</li>
 * <li>Direct Edit</li>
 * <li>Drag'n'Drop</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class LayoutingModeOnECoreModelerTest extends AbstractLayoutingModeTest implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/data/unit/layoutingMode/";

    private static final String SEMANTIC_MODEL_NAME = "vp2120.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_PATH + SEMANTIC_MODEL_NAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Ensures that Layouting mode forbids edge reconnection tools applying.
     */
    public void testLayoutingModeOnEdgeReconnection() {
        // Step 1 : getting tool and diagram elements
        ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(diagram, "ReconnectEReference");
        DEdge edge = Iterables.filter(getDiagramElementsFromLabel(diagram, ""), DEdge.class).iterator().next();
        EdgeTarget eClass = (EdgeTarget) getDiagramElementsFromLabel(diagram, "new EClass").iterator().next();
        EdgeTarget eClass3 = (EdgeTarget) getDiagramElementsFromLabel(diagram, "new EClass 3").iterator().next();
        // Step 2 : applying tool
        Command command = getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, eClass, eClass3);
        executeCommand(command);
        // -> tool should have been applied
        assertToolHasBeenApplied(true);

        // Step 3 : save session and activating layoutingMode
        setLayoutingMode(true);
        session.save(new NullProgressMonitor());

        // Step 4 : applying tool
        command = getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, eClass3, eClass);
        executeCommand(command);

        // -> tool should not have been applied
        assertToolHasBeenApplied(false);

        reopenEditor();
        edge = Iterables.filter(getDiagramElementsFromLabel(diagram, ""), DEdge.class).iterator().next();
        eClass = (EdgeTarget) getDiagramElementsFromLabel(diagram, "new EClass").iterator().next();
        eClass3 = (EdgeTarget) getDiagramElementsFromLabel(diagram, "new EClass 3").iterator().next();
        command = getCommandFactory().buildReconnectEdgeCommandFromTool(reconnectTool, edge, eClass, eClass3);
        executeCommand(command);

        // -> tool should have been applied
        assertToolHasBeenApplied(true);
    }

    /**
     * Ensures that Layouting mode forbids edge direct edit tools applying.
     */
    public void testLayoutingModeOnDirectEdit() {
        // Step 1 : getting tool and diagram elements
        DirectEditLabel editTool = (DirectEditLabel) getTool(diagram, "Edit Name");
        DRepresentationElement eClass = getDiagramElementsFromLabel(diagram, "new EClass").iterator().next();

        // Step 2 : applying tool
        Command command = getCommandFactory().buildDirectEditLabelFromTool(eClass, editTool, "newValue");
        executeCommand(command);
        // -> tool should have been applied
        assertToolHasBeenApplied(true);

        // Step 3 : save session and activating layoutingMode
        session.save(new NullProgressMonitor());
        setLayoutingMode(true);

        // Step 4 : applying tool
        command = getCommandFactory().buildDirectEditLabelFromTool(eClass, editTool, "newValue2");
        executeCommand(command);

        // -> tool should not have been applied
        assertToolHasBeenApplied(false);

        // Step 5 : reopening diagram and applying tool
        reopenEditor();
        command = getCommandFactory().buildDirectEditLabelFromTool(eClass, editTool, "newValue2");
        executeCommand(command);

        // -> tool should have been applied
        assertToolHasBeenApplied(true);
    }

    /**
     * Ensures that Layouting mode forbids edge DnD tools applying.
     */
    public void testLayoutingModeOnDragAndDrop() {
        // Step 1 : getting tool and diagram elements
        ContainerDropDescription dndTool = (ContainerDropDescription) getTool(diagram, "Drop attribute");
        DDiagramElement eAttribute = getDiagramElementsFromLabel(diagram, "new Attribute").get(0);
        DragAndDropTarget eClass3 = (DragAndDropTarget) getDiagramElementsFromLabel(diagram, "new EClass 3").get(0);
        // Step 2 : applying tool
        Command command = getCommandFactory().buildDropInContainerCommandFromTool(eClass3, eAttribute, dndTool);
        executeCommand(command);
        // -> tool should have been applied
        assertToolHasBeenApplied(true);

        // Step 3 : save session and activating layoutingMode
        setLayoutingMode(true);
        session.save(new NullProgressMonitor());

        // Step 4 : applying tool
        command = getCommandFactory().buildDropInContainerCommandFromTool(eClass3, eAttribute, dndTool);
        executeCommand(command);

        // -> tool should not have been applied
        assertToolHasBeenApplied(false);

        // Step 5 : reopening diagram and applying tool
        reopenEditor();
        command = getCommandFactory().buildDropInContainerCommandFromTool(eClass3, eAttribute, dndTool);
        executeCommand(command);

        // -> tool should have been applied
        assertToolHasBeenApplied(true);
    }
}
