/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.internal.migration.EdgesZOrderMigrationParticipant;
import org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.figures.SiriusPolylineConnectionEx;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Version;

/**
 * Test for {@link EdgesZOrderMigrationParticipant}.
 * 
 * @author lredor
 */
public class EdgesZOrderMigrationParticipantTest extends SiriusDiagramTestCase {
    private static final String PATH = "/data/unit/migration/do_not_migrate/edgesZOrder/";

    private static final String SESSION_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "representations.aird";

    private static final String SEMANTIC_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "My.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Set full screen to be sure to draw all figures.
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            // Just call getActiveWorkbenchWindow() to avoid potential
            // empty list in getWorkbenchWindows() (see bug 441507).
            PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            Shell shell = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell();
            String osName = System.getProperty("os.name");
            if (osName.contains("Mac") || osName.contains("Linux")) {
                shell.setMaximized(true);
            } else {
                shell.setFullScreen(true);
            }
            if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
            }
        });
        genericSetUp(Collections.singletonList(SEMANTIC_RESOURCE_PATH), Collections.<String> emptyList(), SESSION_RESOURCE_PATH);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new EdgesZOrderMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SESSION_RESOURCE_PATH, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the figures of edges are correctly displayed:
     * <UL>
     * <LI>right number of jump links,</LI>
     * <LI>or straight line.</LI>
     * </UL>
     */
    public void testEdgesFigures() {
        DDiagram diagram = (DDiagram) getRepresentationsByName("new Diag").toArray()[0];
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // TestsUtil.synchronizationWithUIThread();
        // Check that edge a5 has a jump link (OK even without migration participant)
        SiriusPolylineConnectionEx connection = getConnection(diagram, editorPart, "a5");
        checkJumpLinksNumber("a5", connection, 1);
        // Check that edge a6 has not a jump link (OK even without migration participant)
        connection = getConnection(diagram, editorPart, "a6");
        checkJumpLinksNumber("a6", connection, 0);
        // Check that edge b5 has not a jump link (KO without migration participant)
        connection = getConnection(diagram, editorPart, "b5");
        checkJumpLinksNumber("b5", connection, 0);
        // Check that edge b6 has a jump link (KO without migration participant)
        connection = getConnection(diagram, editorPart, "b6");
        checkJumpLinksNumber("b6", connection, 1);
        // Check that edge c3 is a straight line, ie with only 2 points (OK even without migration participant)
        connection = getConnection(diagram, editorPart, "c3");
        assertEquals("The edge c3 should have only 2 points.", 2, connection.getPoints().size());
        // Check that edge d4 is a straight line, ie with only 2 points (KO without migration participant)
        connection = getConnection(diagram, editorPart, "d4");
        assertEquals("The edge d4 should have only 2 points.", 2, connection.getPoints().size());
    }

    /**
     * Check the number of jump links. This method uses ReflectionHelper to access private fields to count the jump
     * links on the figure.
     * 
     * @param edgeName
     *            The edge that is currently tested
     * @param connection
     *            The figure to check
     * @param expectedNumberOfJumpLinks
     *            The expected number of jump links on the edge
     */
    @SuppressWarnings("unchecked")
    private void checkJumpLinksNumber(String edgeName, SiriusPolylineConnectionEx connection, int expectedNumberOfJumpLinks) {
        Optional<Object> optionalJumpLinkSet = ReflectionHelper.getFieldValueWithoutException(connection, "jumpLinkSet");
        if (optionalJumpLinkSet.isPresent()) {
            Optional<Object> optionalJumpLinkList = ReflectionHelper.getFieldValueWithoutException(optionalJumpLinkSet.get(), "m_pJumpLinks");
            if (optionalJumpLinkList.isPresent()) {
                if (optionalJumpLinkList.get() instanceof List) {
                    String errorMessage;
                    if (expectedNumberOfJumpLinks == 0) {
                        errorMessage = "The edge " + edgeName + " should not have jump link.";
                    } else {
                        errorMessage = "Wrong number of expected jump links for edge " + edgeName + ".";
                    }
                    assertEquals(errorMessage, expectedNumberOfJumpLinks, ((List<Object>) optionalJumpLinkList.get()).size());
                } else {
                    fail("Impossible to get the private jump links list.");
                }
            } else {
                fail("There was a problem when trying to get the private jump links list field through reflection.");
            }
        } else {
            fail("There was a problem when trying to get the private jump links field through reflection.");
        }
    }

    /**
     * Get the connection figure corresponding to the edge name.
     * 
     * @param diagram
     *            The diagram containing the edge.
     * @param editor
     *            The editor containing the searched figure.
     * @param edgeName
     *            The name of the searched edge.
     * 
     * @return The connection figure corresponding to the edge name.
     */
    protected SiriusPolylineConnectionEx getConnection(DDiagram diagram, IEditorPart editor, String edgeName) {
        DEdge edge = getDiagramElementsFromLabel(diagram, edgeName, DEdge.class).iterator().next();
        IGraphicalEditPart edgeEditPart = getEditPart(edge, editor);
        assertTrue("The figure for edge " + edgeName + " should be a SiriusPolylineConnectionEx", edgeEditPart.getFigure() instanceof SiriusPolylineConnectionEx);
        SiriusPolylineConnectionEx connection = (SiriusPolylineConnectionEx) edgeEditPart.getFigure();
        return connection;
    }
}
