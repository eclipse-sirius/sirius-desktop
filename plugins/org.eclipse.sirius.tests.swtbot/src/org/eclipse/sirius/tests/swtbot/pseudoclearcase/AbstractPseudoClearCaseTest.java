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
package org.eclipse.sirius.tests.swtbot.pseudoclearcase;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotWorkbenchPart;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;

/**
 * Common behavior for all pseudo clear-case tests.
 * 
 * @author dlecan
 * @param <E>
 *            Type of editor.
 */
public abstract class AbstractPseudoClearCaseTest<E extends SWTBotWorkbenchPart<?>> extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Cannot use mocks here because {@link #logging(IStatus, String)} is called
     * asynchonously. Thrown exception is lost and doesn't stop
     * 
     * @author dlecan
     */
    private static class InnerLogListener implements ILogListener {

        private boolean hasFailed = false;

        private String message;

        /**
         * {@inheritDoc}
         */
        public void logging(final IStatus status, final String plugin) {

            hasFailed = status.matches(IStatus.WARNING) || status.matches(IStatus.ERROR);

            if (hasFailed) {
                // Remove this listener because we don't want to catch next
                // errors or warnings...
                Platform.removeLogListener(this);
                message = status.toString();

                if (status.getException() != null) {
                    final StringWriter sw = new StringWriter();
                    final PrintWriter pw = new PrintWriter(sw);
                    status.getException().printStackTrace(pw);
                    pw.close();
                    message += "\n" + sw.toString();
                }
            }
        }
    }

    protected static final String VIEWPOINT_NAME = "Design";

    private static final String MODEL = "1936.ecore";

    private static final String SESSION_FILE = "1936.aird";

    private static final String DATA_UNIT_DIR = "data/unit/pseudoClearCase/tc1936/noncontrolled/";

    private static final String FILE_DIR = "/";

    private static final String SHELL_REGEX = "Read-only File[s]{0,1} Encountered";

    /** Local session. */
    protected UILocalSession localSession;

    /**
     * Current editor.
     */
    protected E editor;

    private byte[] md5sumSessionFile;

    private byte[] md5sumModelFile;

    private UIResource sessionAirdResource;

    private InnerLogListener logListener;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(true, MODEL, SESSION_FILE);

        md5sumSessionFile = getMD5SumFromFile(SESSION_FILE);
        md5sumModelFile = getMD5SumFromFile(MODEL);
    }

    private void copyFileToTestProject(final boolean readOnly, final String... fileNames) {
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
            EclipseTestsSupportHelper.INSTANCE.changeFileReadOnlyAttribute(getProjectName() + "/" + fileName, readOnly);
        }
    }

    private byte[] getMD5SumFromFile(final String fileName) {
        InputStream inputStream = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");

            final IFile destinationFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getProjectName() + "/" + fileName));

            inputStream = destinationFile.getContents();

            final byte[] buffer = new byte[10000];

            while (inputStream.read(buffer) != -1) {
                md.update(buffer, 0, 10000);
            }

            return md.digest();
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (final CoreException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    // Nothing
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = openAndGetEditor(localSession.getOpenedSession(), getRepresentationName(), getRepresentationInstanceName());

        logListener = new InnerLogListener();

        Platform.addLogListener(logListener);
    }

    /**
     * Get the representation name.
     * 
     * @return the representation name.
     */
    protected abstract String getRepresentationName();

    /**
     * Get the representation instance name.
     * 
     * @return the representation instance name.
     */
    protected abstract String getRepresentationInstanceName();

    /**
     * Open and get editor.
     * 
     * @param session
     *            The current session
     * @param representationDescriptionName
     *            the representation description name.
     * @param representationName
     *            the representation name.
     * @return Editor.
     */
    protected abstract E openAndGetEditor(final Session session, final String representationDescriptionName, final String representationName);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Empty task queue
        UIThreadRunnable.syncExec(new VoidResult() {
            public void run() {
                // Nothing
            }
        });
        waitUntilReadOnlyPopupDialogOpens();

        if (editor != null) {
            editor.close();
        }

        localSession.closeNoDirty();

        assertFalse("An error status has been detected by log listener: " + logListener.message, logListener.hasFailed);

        Platform.removeLogListener(logListener);

        checkMD5Sums();

        super.tearDown();
    }

    private void checkMD5Sums() {
        assertTrue("Wrong model md5 sum (has been modified)", Arrays.equals(md5sumModelFile, getMD5SumFromFile(MODEL)));
        assertTrue("Wrong session md5 sum (has been modified)", Arrays.equals(md5sumSessionFile, getMD5SumFromFile(SESSION_FILE)));
    }

    /**
     * See method name.
     */
    protected void waitUntilReadOnlyPopupDialogOpens() {
        final WaitForObjectCondition<Shell> waitForShell = Conditions.waitForShell(regexShellMatcher());
        bot.waitUntilWidgetAppears(waitForShell);
        final SWTBotShell shell = new SWTBotShell(waitForShell.get(0));

        shell.setFocus();

        final SWTBotButton button = bot.button("No");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }

    @SuppressWarnings("unchecked")
    private static Matcher<Shell> regexShellMatcher() {
        return WidgetMatcherFactory.<Shell> allOf(IsInstanceOf.<Shell> instanceOf(Shell.class), WidgetMatcherFactory.<Shell> withRegex(SHELL_REGEX));
    }
}
