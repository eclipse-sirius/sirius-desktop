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
package org.eclipse.sirius.tests.unit.diagram.node.style;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;

/**
 * Test modification node style in VSM. Test VP-1718
 * 
 * @author jdupont
 * 
 */
public class ModifyNodeStyleTest extends SiriusDiagramTestCase {

    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticketvp1718/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + "tc1718.ecore";

    private static final String SESSION_NAME = "tc1718.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String MODELER_PATH = TEST_DIR + "tc1718.odesign";

    private static final String DIAGRAM_DESCRIPTION_ID = "diagramDescription";

    private DDiagramEditor diagramEditor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        diagramEditor = null;
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Return the searched diagramDescription.
     * 
     * @param group
     *            A group
     * @param name
     *            The name of the searched diagram description
     * @return The searched DiagramDescription of null
     */
    protected DiagramDescription find(final Group group, final String name) {
        final Iterator<RepresentationDescription> it = (group.getOwnedViewpoints().get(0)).getOwnedRepresentations().iterator();
        while (it.hasNext()) {
            final RepresentationDescription next = it.next();
            if (next instanceof DiagramDescription) {
                if (name.equals(((DiagramDescription) next).getName()))
                    return (DiagramDescription) next;
            }
        }
        return null;
    }

    /**
     * Test change style node to workspaceImageDescription. This test allow test
     * the classCastException is repaired.
     * 
     * @throws InterruptedException
     */
    public void testChangeStyleNode() throws InterruptedException {

        URI modelerResourceURI = URI.createPlatformPluginURI(MODELER_PATH, true);
        Group group = null;
        try {
            group = (Group) ModelUtils.load(modelerResourceURI, session.getTransactionalEditingDomain().getResourceSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final DiagramDescription diagramDescription = find(group, DIAGRAM_DESCRIPTION_ID);
        assertNotNull("The unit test data seems incorrect", diagramDescription);

        ContainerMapping container = diagramDescription.getContainerMappings().get(0);
        assertEquals(ContainerLayout.LIST, container.getChildrenPresentation());
        assertEquals(true, new ContainerMappingQuery(container).isListContainer());

        // Search the node attribute on VSM
        EList<NodeMapping> nodes = diagramDescription.getContainerMappings().get(0).getSubNodeMappings();
        assertEquals("The number of nodes mapping is not correct", 1, nodes.size());

        final NodeMapping nodeToModifyStyle = nodes.get(0);
        assertTrue("The style is not correct", nodeToModifyStyle.getStyle() instanceof SquareDescription);

        DDiagram diagram = (DDiagram) getRepresentations(DIAGRAM_DESCRIPTION_ID).toArray()[0];

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DDiagramEditor) openedEditor;

        // Modify node style.
        final WorkspaceImageDescription workspaceImageDescription = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            protected void doExecute() {
                nodeToModifyStyle.setStyle(workspaceImageDescription);
            };
        });

        assertTrue("The style is not correct", nodeToModifyStyle.getStyle() instanceof WorkspaceImageDescription);

        openedEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DDiagramEditor) openedEditor;

    }

}
