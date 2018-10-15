/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;

/**
 * Contains methods to test the layouting and show/hide edit modes.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public abstract class AbstractModeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/editModes/";

    private static final String SEMANTIC_MODEL_NAME = "vp2120.ecore";

    private static final String MODELER_NAME = "vp2120.odesign";

    private static final String SESSION_FILE_NAME = "vp2120.aird";

    /**
     * Representation description name.
     */
    protected static final String REPRESENTATION_DESCRIPTION_NAME = "LayoutingMode Diagram";

    /**
     * Representation instance name.
     */
    protected static final String REPRESENTATION_INSTANCE_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_MODEL_NAME, SESSION_FILE_NAME, MODELER_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Ensures that a tool has been applied or not (according to the given shouldHaveBeenApplied value), by checking the
     * Session's status.
     * 
     * @param shouldHaveBeenApplied
     *            indicates whether a tool should have been applied or not
     */
    protected void assertToolHasBeenApplied(boolean shouldHaveBeenApplied) {
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return shouldHaveBeenApplied == localSession.getOpenedSession().getStatus().equals(SessionStatus.DIRTY);
            }

            @Override
            public String getFailureMessage() {
                String message = null;
                if (shouldHaveBeenApplied) {
                    message = "Tool should have been applied as Layouting mode is disabled";
                } else {
                    message = "Tool should not have been applied as Layouting mode is activated";
                }
                return message;
            }
        });

    }

    /**
     * Ensures that a direct edit tool has been applied or not (according to the given shouldHaveBeenApplied value).
     * 
     * @param originalEditPartName
     *            the part name before direct edit.
     * @param shouldHaveBeenApplied
     *            indicates whether the direct edit should have been applied or not
     */
    protected void assertDirectEditToolHasBeenApplied(String originalEditPartName, boolean shouldHaveBeenApplied) {
        if (shouldHaveBeenApplied) {
            try {
                editor.getEditPart(originalEditPartName).part();
                fail("Direct edit should have been applied");
            } catch (WidgetNotFoundException e) {
            }
        } else {
            try {
                editor.getEditPart(originalEditPartName).part();
            } catch (WidgetNotFoundException e) {
                fail("Direct edit should have not been applied");
            }
        }
    }

    /**
     * Ensures that a drag and drop tool has been applied or not (according to the given shouldHaveBeenApplied value),
     * by checking the Session's status.
     * 
     * @param draggedObject
     *            the dragged object.
     * @param parentObject
     *            the parent of the dragged object.
     * @param shouldHaveBeenApplied
     *            indicates whether a tool should have been applied or not
     */
    protected void assertDragAndDropToolHasBeenApplied(String draggedObject, String parentObject, boolean shouldHaveBeenApplied) {
        SWTBotGefEditPart draggedEditPart = editor.getEditPart(draggedObject);
        SWTBotGefEditPart parentEditPart = editor.getEditPart(parentObject);
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                if (shouldHaveBeenApplied) {
                    return !draggedEditPart.part().getParent().equals(parentEditPart.part());
                }
                return shouldHaveBeenApplied == draggedEditPart.part().getParent().equals(parentEditPart.part());
            }

            @Override
            public String getFailureMessage() {
                String message = null;
                if (shouldHaveBeenApplied) {
                    message = "Drag and drop was not applied";
                } else {
                    message = "Drag and drop should not have been applied";
                }
                return message;
            }
        });

    }

    /**
     * Returns the SWT bot edge part of the part with the given name.
     * 
     * @param edgeName
     *            the name of the parent to look for.
     * @return the SWT bot part of the part with the given name.
     */
    protected SWTBotGefEditPart getEdgePart(String edgeName) {
        List<SWTBotGefConnectionEditPart> connectionsEditPart = editor.getConnectionsEditPart();
        for (SWTBotGefConnectionEditPart swtBotGefConnectionEditPart : connectionsEditPart) {
            DEdgeEditPart edgePart = ((DEdgeEditPart) swtBotGefConnectionEditPart.part());
            Edge edge = (Edge) edgePart.getModel();
            EObject target = ((DEdge) edge.getElement()).getTarget();
            String name = target instanceof ENamedElement ? ((ENamedElement) target).getName() : "";
            if (edgeName.equals(name)) {
                return swtBotGefConnectionEditPart;
            }
        }
        return null;
    }

    /**
     * Ensures that an edge reconnection has been applied or not (according to the given shouldHaveBeenApplied value).
     * 
     * @param targetPartName
     *            the original target before reconnection
     * @param edgeName
     *            the name of the reconnected edge
     * @param shouldHaveBeenApplied
     *            indicates whether a tool should have been applied or not
     */
    protected void assertEdgeReconnectionToolHasBeenApplied(String targetPartName, String edgeName, boolean shouldHaveBeenApplied) {
        SWTBotGefEditPart swtEdgePart = getEdgePart(edgeName);
        DEdgeEditPart edgePart = (DEdgeEditPart) swtEdgePart.part();
        EditPart target = edgePart.getTarget();
        EditPart expectedTargetPart = editor.getEditPart(targetPartName).part().getParent();
        if (shouldHaveBeenApplied) {
            assertNotEquals("Reconnection should have been done but failed.", expectedTargetPart, target);
        } else {
            assertEquals("Reconnection should have not been done but was executed.", expectedTargetPart, target);
        }
    }

    /**
     * Activates the Show/Hide mode using the tabbar button.
     */
    protected void activateShowHideModeUsingTabbar() {
        SWTBotGefEditPart editPart = editor.getSWTBotGefViewer().mainEditPart();
        editPart.click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButton(4);
        SWTBotUtils.waitAllUiEvents();
        toolbarDropDownButton.menuItem(Messages.ShowingModeSwitchingAction_label).click();
    }

    /**
     * Switch layer activation status.
     * 
     * @param layerName
     *            The name of the layer to switch.
     */
    protected void switchLayer(String layerName) {
        SWTBotGefEditPart editPart = editor.getSWTBotGefViewer().mainEditPart();
        editPart.click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButton(2);
        SWTBotUtils.waitAllUiEvents();
        toolbarDropDownButton.menuItem(layerName).click();
    }

    /**
     * Activate the standard mode.
     */
    protected void activateStandardModeUsingTabbar() {
        SWTBotGefEditPart editPart = editor.getSWTBotGefViewer().mainEditPart();
        editPart.click();
        SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButton(4);
        toolbarDropDownButton.menuItem(Messages.DefaultModeAction_Label).click();
    }

    /**
     * Activates the Layouting mode using the tabbar button.
     */
    protected void activateLayoutingModeUsingTabbar() {
        SWTBotGefEditPart editPart = editor.getSWTBotGefViewer().mainEditPart();
        editPart.click();
        SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButton(4);
        toolbarDropDownButton.menuItem(Messages.LayoutingModeSwitchingAction_label).click();
    }

}
