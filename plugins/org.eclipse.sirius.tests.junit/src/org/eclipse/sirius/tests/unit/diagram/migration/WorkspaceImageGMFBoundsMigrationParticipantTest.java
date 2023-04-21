/*******************************************************************************
 * Copyright (c) 2022, 2023 THALES GLOBAL SERVICES.
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
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.CollapseFilter;
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

    private static final String VSM_RESOURCE_NAME = "workspaceImageGMFBounds.odesign"; //$NON-NLS-1$

    private static final String SEMANTIC_RESOURCE_NAME = "workspaceImageGMFBounds.ecore"; //$NON-NLS-1$

    private static final String SESSION_RESOURCE_NAME = "workspaceImageGMFBounds.aird"; //$NON-NLS-1$

    private static final String PATH = "/data/unit/migration/do_not_migrate/workspaceImageGMFBounds/"; //$NON-NLS-1$

    private static final String SESSION_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME; //$NON-NLS-1$

    private static final String SEMANTIC_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME; //$NON-NLS-1$

    private static final String MODELER_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + VSM_RESOURCE_NAME; //$NON-NLS-1$

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
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SESSION_RESOURCE_PATH, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0); //$NON-NLS-1$
    }

    public void testGMFBounds() {
        diagram = (DDiagram) getRepresentationsByName("workspaceImageGMFBounds").toArray()[0]; //$NON-NLS-1$
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateNodeSize("enum", DNode.class, 50, 50, false); //$NON-NLS-1$
        validateNodeSize("att", DNode.class, 1, 1, false); //$NON-NLS-1$
        validateCollapseSize("att", DNode.class, 10, 10); //$NON-NLS-1$
        validateNodeSize("P1", DNode.class, 100, 70, false); //$NON-NLS-1$
        validateNodeSize("container_image", DNodeContainer.class, 160, 48, true); //$NON-NLS-1$
        validateNodeSize("list_image", DNodeList.class, 160, 48, true); //$NON-NLS-1$
        validateNodeSize("cont_not_found", DNodeContainer.class, 100, 70, true); // Container default size //$NON-NLS-1$
        validateNodeSize("list_not_found", DNodeList.class, 100, 70, true); // Container default size //$NON-NLS-1$
    }

    private void validateCollapseSize(String nodeName, Class<? extends DDiagramElement> clazz, int width, int height) {
        DDiagramElement dNode = getDiagramElementsFromLabel(diagram, nodeName, clazz).iterator().next();
        Optional<CollapseFilter> optionalCollapseFilter = dNode.getGraphicalFilters().stream().filter(CollapseFilter.class::isInstance).map(CollapseFilter.class::cast).findFirst();
        if (optionalCollapseFilter.isPresent()) {
            CollapseFilter collapseFilter = optionalCollapseFilter.get();
            assertEquals("Wrong expended width in the collapse filter", width, collapseFilter.getWidth()); //$NON-NLS-1$
            assertEquals("Wrong expended height in the collapse filter", height, collapseFilter.getHeight()); //$NON-NLS-1$
        } else {
            fail("Missing collapse filter."); //$NON-NLS-1$
        }
    }

    private void validateNodeSize(String nodeName, Class<? extends DDiagramElement> clazz, int expectedWidth, int expectedHeight, boolean autoSize) {
        DDiagramElement dNode = getDiagramElementsFromLabel(diagram, nodeName, clazz).iterator().next();
        Node node = SiriusGMFHelper.getGmfNode(dNode, session);
        Size size = (Size) node.getLayoutConstraint();
        IGraphicalEditPart editPart = getEditPart(dNode, editor);
        Rectangle bounds = editPart.getFigure().getBounds();

        if (autoSize) {
            assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint height.", -1, size.getHeight()); //$NON-NLS-1$ //$NON-NLS-2$
            assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint width.", -1, size.getWidth()); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint height.", expectedHeight, size.getHeight()); //$NON-NLS-1$ //$NON-NLS-2$
            assertEquals("Node " + dNode.getName() + " has a wrong layoutConstraint width.", expectedWidth, size.getWidth()); //$NON-NLS-1$ //$NON-NLS-2$

        }
        assertEquals("Node " + dNode.getName() + " has a wrong figure bounds height.", expectedHeight, bounds.height); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Node " + dNode.getName() + " has a wrong figure bounds width.", expectedWidth, bounds.width); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
