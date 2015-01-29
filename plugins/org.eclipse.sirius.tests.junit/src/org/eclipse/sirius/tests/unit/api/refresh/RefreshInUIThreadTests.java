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
package org.eclipse.sirius.tests.unit.api.refresh;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reportMatcher;
import static org.easymock.EasyMock.verify;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.easymock.IArgumentMatcher;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * Tests that refresh occurs in UI thread and does not throw SWTException based
 * on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 * @trac bug #1849
 */
public class RefreshInUIThreadTests extends GenericTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/packages.ecore";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    private ILogListener listener;

    private UncaughtExceptionHandler exceptionHandler;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];

        emptyEventsFromUIThread();

        exceptionHandler = createMock(UncaughtExceptionHandler.class);

        listener = createMock(ILogListener.class);
        listener.logging(checkStatusIsNot(IStatus.ERROR), (String) anyObject());
        // Might be not called
        expectLastCall().anyTimes();

        WorkbenchPlugin.getDefault().getLog().addLogListener(listener);

        mocks = new Object[] { exceptionHandler, listener };

        replay(mocks);
    }

    private IStatus checkStatusIsNot(final int severity) {
        reportMatcher(new IArgumentMatcher() {

            public boolean matches(final Object argument) {
                boolean result = true;
                if (argument instanceof IStatus) {
                    result = ((IStatus) argument).getSeverity() == severity;
                }
                return result;
            }

            public void appendTo(final StringBuffer buffer) {
                buffer.append("Expect an IStatus with a severity different from ERROR");
            }
        });
        return null;
    }

    /**
     * Test.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirtyNotification() throws Exception {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(editor instanceof DDiagramEditor);
        final DDiagramEditor diagramEditor = (DDiagramEditor) editor;
        assertTrue(editor instanceof SessionListener);

        /* add an exception handler to the default thread */
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        final CountDownLatch latch = new CountDownLatch(1);

        final Thread t = new Thread() {
            @Override
            public void run() {
                /*
                 * launch a notification in a new thread which is not the UI
                 * thread
                 */
                ((SessionListener) diagramEditor).notify(SessionListener.DIRTY);
                latch.countDown();
            }
        };
        t.start();

        assertTrue("Listener was not notified before timeout", latch.await(5, TimeUnit.SECONDS));

        /* this may launch an exception and the test should failed */
        synchronizationWithUIThread();

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test when the preference 'Do refresh on representation opening' set to
     * false, the diagram is not blank.
     */
    public void testRefreshOnOpeningWithPrefRefreshOnOpeningFalse() {
        checkThatTheRepresentationIsNotBlank(false);
    }

    /**
     * Test when the preference 'Do refresh on representation opening' set to
     * true, the diagram is not blank.
     */
    public void testRefreshOnOpeningWithPrefRefreshOnOpeningTrue() {
        checkThatTheRepresentationIsNotBlank(true);
    }

    private void checkThatTheRepresentationIsNotBlank(boolean prefRefreshOnReprensentationOpening) {
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), prefRefreshOnReprensentationOpening);
        createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);

        /* this may launch an exception and the test should failed */
        synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertEquals("The representation must contain diagram elements.", true, rootdiagram.getRepresentationElements().size() > 0);
    }

    private void emptyEventsFromUIThread() {
        try {
            synchronizationWithUIThread();
        } catch (final Exception e) {
            emptyEventsFromUIThread();
        }
    }

    private void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
            // Nothing
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        verify(mocks);
        WorkbenchPlugin.getDefault().getLog().removeLogListener(listener);
        super.tearDown();
    }

}
