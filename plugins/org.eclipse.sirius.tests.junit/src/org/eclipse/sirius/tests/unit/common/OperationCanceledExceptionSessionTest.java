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
package org.eclipse.sirius.tests.unit.common;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Tests that the Operation Canceled Exception is correctly managed at the
 * session opening time.
 * 
 * @author fbarbin
 */
public class OperationCanceledExceptionSessionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/session/VP-3829";

    private static final String AIRD = "test.aird";

    private static final String MODEL = "test.ecore";

    protected static final URI REPRESENTATIONS_FILE_URI = URI.createPlatformResourceURI(File.separator + TEMPORARY_PROJECT_NAME + File.separator + AIRD, true);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Setup

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODEL, "/" + TEMPORARY_PROJECT_NAME + "/" + MODEL);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + AIRD, "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD);

    }

    /**
     * This test checks that <code>session.open</code> throws an
     * {@link OperationCanceledException} and close the session.
     * <ul>
     * <li>Add a session listener to throw a new
     * {@link OperationCanceledException}</li>
     * <li>Make sure that the <code>session.open</code> catch and throw this
     * exception.</li>
     * <li>Make sure that the session is correctly closed at the end.</li>
     * </ul>
     */
    public void testOperationCanceledExceptionAtOpeningTime() {
        createSession(REPRESENTATIONS_FILE_URI);
        boolean exceptionThrown = false;
        session.addListener(new SessionListener() {
            public void notify(int changeKind) {
                if (changeKind == SessionListener.OPENING) {
                    throw new OperationCanceledException();
                }
            }
        });
        try {
            session.open(new NullProgressMonitor());
        } catch (OperationCanceledException e) {
            exceptionThrown = true;
        }
        assertTrue("The session should have thrown an OperationCanceledException", exceptionThrown);
        assertFalse("The session should be closed", session.isOpen());

    }
}
