/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.initialization;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tests the initialization.
 * 
 * @author ymortier
 */
public class InitializationTest extends SiriusDiagramTestCase {

    /** The name of the simplest viewpoint. */
    private static final String SIMPLEST_VP_NAME = "InitializationTest";

    /** One node, one container and one list. */
    private static final String THREE_MAPPINGS = "ThreeMappings";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/initialization/init.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/initialization/test.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
    }

    /**
     * Tests the initialization of one diagram and zero mapping.
     */
    public void testSimpleInitialization() {
        initialize(SIMPLEST_VP_NAME);
    }

    private void initialize(String vpName) {
        initViewpoint(vpName);
        Viewpoint viewpoint = session.getSelectedViewpoints(false).iterator().next();
        // The viewpoint activation will create a new representation
        final int nbReps = DialectManager.INSTANCE.getRepresentations(semanticModel, session).size();
        try {
            // In the following initRepresentations call that will create a
            // DRepresentation in the aird resource, the Cross referencer is not
            // set on the DRepresentation. It seems linked to when the
            // SaveSessionJob is executed on how much time it takes. Sleeping
            // 500ms we ensure that SaveSessionJob have finished before
            // initRepresentations can start.
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DialectManager.INSTANCE.initRepresentations(viewpoint, semanticModel, new NullProgressMonitor());

        assertEquals("There is no or more than one diagram(s) in the returned view", nbReps + 1, DialectManager.INSTANCE.getRepresentations(semanticModel, session).size());
    }

    /**
     * Tests the initialization of the diagram. There are three mappings, one
     * node, one container and one list. Each mapping references an instance of
     * EClass. As there is only one EClass in the input model there are only
     * three elements in the diagram.
     * 
     */
    public void testInitializationWith3Mappings() {
        initialize(THREE_MAPPINGS);

        final DDiagram diagram = (DDiagram) DialectManager.INSTANCE.getRepresentations(semanticModel, session).toArray()[0];

        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, diagram));

        assertEquals("There is not exactly one node.", 1, diagram.getNodes().size());
        assertEquals("There are not exactly two containers.", 2, diagram.getContainers().size());

        int simpleContainer = 0;
        int listContainer = 0;
        for (Object object : diagram.getContainers()) {
            if (object instanceof DNodeContainer)
                simpleContainer++;
            if (object instanceof DNodeList)
                listContainer++;
        }

        assertEquals("There is not exactly one simple container", 1, simpleContainer);
        assertEquals("There is not exactly one list container", 1, listContainer);
    }

}
