/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.diagram.ui.handles.CompartmentCollapseHandle;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Tests to verify specific compartments behaviors on the Component modeler.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class CompartmentsWithComponentTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String DATA_UNIT_DIR = "/data/unit/compartments/";

    private static final String MODEL = "My.component";

    private static final String SESSION_FILE = "representations.aird";

    private static final String REGION_WITH_EDGES_REPRESENTATION_NAME = "DiagramWithRegionAndEdges";

    private static final String REGION_WITH_EDGES_REPRESENTATION_INSTANCE_NAME = "new DiagramWithRegionAndEdges";



    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

        // Disable auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Check that edges are correctly refreshed in case of a collapse, ie check that the collapse/expand status change
     * results in a refresh. This test has 2 phases:
     * <UL>
     * <LI>Without collapsing behavior, the edge disappears when the container is collapsed</LI>
     * <LI>With collapsing behavior, another edge, on the container, appears when the container is collapsed (on the
     * original edge disappears as in first phase)</LI>
     * </UL>
     */
    public void testEdgeRefreshInCaseOfCollapseCompartment() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REGION_WITH_EDGES_REPRESENTATION_NAME, REGION_WITH_EDGES_REPRESENTATION_INSTANCE_NAME, DDiagram.class,
                true, true);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        SWTBotGefEditPart edgeSourceEditPart = editor.getEditPart("DC.3.1.1", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart edgeTargetEditPart = editor.getEditPart("DC.2.1.1", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart parentEdgeSourceEditPart = editor.getEditPart("DC.3.1", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart parentEdgeTargetEditPart = editor.getEditPart("DC.2.1", AbstractDiagramElementContainerEditPart.class);

        DEdgeEditPart edgeEditPart = (DEdgeEditPart) ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().get(0);
        assertTrue("The edge should be visible after diagram opening.", edgeEditPart.getFigure().isVisible());

        collapseOrExpandContainer(parentEdgeSourceEditPart);

        // Check that the original edge is no longer visible but is always here
        assertFalse("The edge should be hidden after collapsing the container of the target of the edge.", edgeEditPart.getFigure().isVisible());
        assertEquals("The edge already exists, even if it is not visible.", 1, ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().size());
        // Check that no other edge appears (because the collapse notification has not yet been registered)
        assertEquals("No edge from the collapsed container should appear because the collapse notification has not yet been registered.", 0,
                ((AbstractDiagramElementContainerEditPart) parentEdgeSourceEditPart.part()).getSourceConnections().size());
        // Expand the container to retrieve the initial state
        collapseOrExpandContainer(parentEdgeSourceEditPart);

        // Register the collapse notification as impacted one (by applying a dedicated tool).
        editor.activateTool("Register collapse for Refresh");
        editor.click(10, 10);

        try {
            collapseOrExpandContainer(parentEdgeSourceEditPart);
            // Check that the original edge no longer exists, as a refresh has been launch on the collapse
            assertEquals("The edge should be hidden after collapsing the container of the target of the edge.", 0,
                    ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().size());
            // Check that another edge appears (because the collapse notification has been registered)
            assertEquals("One edge from the collapsed container should appear because the collapse notification has been registered.", 1,
                    ((AbstractDiagramElementContainerEditPart) parentEdgeSourceEditPart.part()).getSourceConnections().size());
            assertTrue("The edge from the parent should be visible after collapsing the container of the target of the previous edge.",
                    ((DEdgeEditPart) ((AbstractDiagramElementContainerEditPart) parentEdgeSourceEditPart.part()).getSourceConnections().get(0)).getFigure().isVisible());

            // Expand the compartment
            collapseOrExpandContainer(parentEdgeSourceEditPart);
            // Check that the original edge is visible again, it is not the same edge as the original state but with the
            // same
            // source and target
            assertEquals("The edge should exist, as initially.", 1,
                    ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().size());
            assertTrue("The edge should be visible after expanding the container of the target of the edge.",
                    ((DEdgeEditPart) ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().get(0)).getFigure().isVisible());
            // Check that other edge disappears
            assertEquals("No edge from the expanded container must be there.", 0, ((AbstractDiagramElementContainerEditPart) parentEdgeSourceEditPart.part()).getSourceConnections().size());
        } finally {
            // Unregister the collapse notification as impacted one (by applying a dedicated tool).
            editor.activateTool("Unregister collapse for Refresh");
            editor.click(10, 10);
        }
    }

    /**
     * Collapse or expand a container by clicking on the collapse/expand button of its region (change its collapse
     * state).
     * 
     * @param container
     *            The container to collapse
     */
    private void collapseOrExpandContainer(SWTBotGefEditPart container) {
        ICondition editPartResizedCondition = new CheckEditPartResized(container);
        // Select the region contained in the container
        AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) container.part();
        GraphicalHelper.getAbsoluteBoundsIn100Percent(part);
        Point top = GraphicalHelper.getAbsoluteBoundsIn100Percent(part).getTop();
        editor.click(top.getTranslated(0, 40));
        // Collapse the region
        // Add a wait condition to have the collapse button displayed and click on it
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                IFigure handleLayer = LayerManager.Helper.find(part).getLayer(LayerConstants.HANDLE_LAYER);
                Point toggleFigureLocation;
                if (handleLayer != null) {
                    for (Object figure : handleLayer.getChildren()) {
                        if (figure instanceof CompartmentCollapseHandle) {
                            toggleFigureLocation = ((CompartmentCollapseHandle) figure).getLocation();
                            if (toggleFigureLocation.x != 0 && toggleFigureLocation.y != 0) {
                                // Use the center of the figure and click on it
                                Dimension size = ((CompartmentCollapseHandle) figure).getSize();
                                toggleFigureLocation.translate(size.width / 2, size.height / 2);
                                editor.click(toggleFigureLocation);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The collapse button has not been found after region selection.";
            }
        });
        // And then wait the collapse result
        bot.waitUntil(editPartResizedCondition);

        // Wait all UI events to ensure that the connections are refresh in asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
    }
}
