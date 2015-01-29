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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * This test is about issue #1989.
 * <p>
 * Refresh of styles are not constant : even if nothing is modified for user,
 * style refresh generates EMF notifications which leads to set session dirty
 * (and it shouldn't be).
 * </p>
 * 
 * @author dlecan
 */
public class ConstantStyleRefreshTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/constantStyleRefresh/";

    private static final String ROOT = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH;

    private static final String SESSION_FILE_NAME = "tc1989.aird";

    private static final String SESSION_RELATIVE_PATH = PATH + SESSION_FILE_NAME;

    private static final String SESSION_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_FILE_NAME;

    private static final String SEMANTIC_MODEL_PATH = ROOT + "tc1989.ecore";

    private static final String MODELER_PATH = ROOT + "tc1989.odesign";

    private static final String VIEWPOINT_NAME = "tc1989";

    private static final String REPRESENTATION_DESC_NAME = "tc1989";

    private ResourceSyncClient resourceSyncClient;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        resourceSyncClient = createMock(ResourceSyncClient.class);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SESSION_RELATIVE_PATH, SESSION_WORKSPACE_PATH);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_WORKSPACE_PATH);
        initViewpoint(VIEWPOINT_NAME);
        createRepresentation(REPRESENTATION_DESC_NAME);

        mocks = new Object[] { resourceSyncClient };

        // save session to remove dirty state
        session.save(new NullProgressMonitor());

        TestsUtil.emptyEventsFromUIThread();

        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckConstantStyleRefresh() throws Exception {
        // Expect calls on mock
        replay(mocks);

        ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain());

        try {
            ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).registerClient(resourceSyncClient);

            assertEquals(SessionStatus.SYNC, session.getStatus());
        } finally {
            ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).unregisterClient(resourceSyncClient);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        verify(mocks);
        mocks = null;
        resourceSyncClient = null;
        super.tearDown();
    }
}
