/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.migration.WorkspaceImageGMFBoundsMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.osgi.framework.Version;

/**
 * Tests for {@link WorkspaceImageGMFBoundsMigrationParticipant}
 * 
 * @author Glenn Plouhinec
 *
 */
public class WorkspaceImageGMFBoundsMigrationParticipantTest extends SiriusDiagramTestCase {

    private static final String VSM_RESOURCE_NAME = "workspaceImageGMFBounds.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "workspaceImageGMFBounds.ecore";

    private static final String SESSION_RESOURCE_NAME = "workspaceImageGMFBounds.aird";

    private static final String PATH = "/data/unit/migration/do_not_migrate/workspaceImageGMFBounds/";

    private static final String SESSION_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME;

    private static final String SEMANTIC_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME;

    private static final String MODELER_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + VSM_RESOURCE_NAME;

    private DDiagram diagram;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, VSM_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(SEMANTIC_RESOURCE_PATH), Collections.singletonList(MODELER_RESOURCE_PATH), SESSION_RESOURCE_PATH);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new WorkspaceImageGMFBoundsMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SESSION_RESOURCE_PATH, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    public void testGMFBounds() {
        diagram = (DDiagram) getRepresentationsByName("workspaceImageGMFBounds").toArray()[0];
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateNodeSize("enum", 50, 50);
        validateNodeSize("P1", 100, 70);
        validateNodeSize("container_image", 160, 48);
        validateNodeSize("list_image", 160, 48);
        validateNodeSize("container_image_not_found", 100, 70);
        validateNodeSize("list_image_not_found", 100, 70);
    }

    private void validateNodeSize(String nodeName, int expectedWidth, int expectedHeight) {
        DDiagramElement dNode = null;
        switch (nodeName) {
        case "enum":
        case "P1":
            dNode = getDiagramElementsFromLabel(diagram, nodeName, DNode.class).iterator().next();
            break;
        case "container_image":
        case "container_image_not_found":
            dNode = getDiagramElementsFromLabel(diagram, nodeName, DNodeContainer.class).iterator().next();
            break;
        case "list_image":
        case "list_image_not_found":
            dNode = getDiagramElementsFromLabel(diagram, nodeName, DNodeList.class).iterator().next();
            break;
        }
        Node node = SiriusGMFHelper.getGmfNode(dNode, session);
        Size size = (Size) node.getLayoutConstraint();
        IGraphicalEditPart editPart = getEditPart(dNode, editor);
        Rectangle bounds = editPart.getFigure().getBounds();

        assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint height.", expectedHeight, size.getHeight());
        assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node " + dNode.getName() + " has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node " + dNode.getName() + " has a wrong figure bounds width.", expectedWidth, bounds.width);
    }
}
