/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Obeo - Initial API and implementation
 *      Felix Dorner <felix.dorner@gmail.com> - Bug 533002
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestCaseCleaner;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.internal.helper.CrossReferenceAdapterDetector;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationRedoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationUndoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.api.perspective.DesignerPerspectives;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.SWTBotTestCase;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.ClassUtils;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ErrorEditorPart;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.osgi.framework.Version;

import junit.framework.TestCase;

/**
 * Wrapper for several UI* classes to handle ui management in tests. If needed, new setup operations can be done in
 * {@link #onSetUpBeforeClosingWelcomePage()} overridden method.
 *
 * {@link #onSetUpAfterOpeningDesignerPerspective()} allows developer to add extra behavior after opening designer
 * perspective.
 *
 * @author dlecan
 */
@SuppressWarnings({ "restriction", "nls" })
public abstract class AbstractSiriusSwtBotGefTestCase extends SWTBotGefTestCase {
    static {
        SWTBotPreferences.TIMEOUT = 10000;
    }

    /** Models dir. */
    protected static final String MODELS_DIR = "Models";

    /** Test project name. */
    protected static final String TEMP_PROJECT_NAME = "DesignerTestProject";

    private static final String EN_US = "EN_US";

    private static final String SET_STYLE_TO_WORKSPACE_IMAGE = "Set style to workspace image";

    private static final String POINT = ".";

    private static final String EDIT_MENU_NAME = "Edit";

    private static final List<String> SHELL_TO_CLOSE_AT_TEAR_DOWN_TEXTS = new ArrayList<String>(Arrays.asList(OpenRepresentationsFileJob.JOB_LABEL));

    private static boolean fFullScreen = true;

    /** Counts the screenshots to determine if maximum number is reached. */
    private static int screenshotCounter;

    /** The logger. */
    private static Logger log = Logger.getLogger(SWTBotTestCase.class);

    private static final Version PLATFORM_VERSION = Platform.getBundle("org.eclipse.core.runtime").getVersion();

    /**
     * Designer perspective.
     */
    protected UIPerspective designerPerspective = new UIPerspective(bot);

    /**
     * Designer perspective.
     */
    protected DesignerPerspectives designerPerspectives = new DesignerPerspectives(bot);

    /** . */
    protected DesignerViews designerViews = new DesignerViews(bot);

    /** Designer project. */
    protected UIProject designerProject;

    /** The Session Resource wrapper. */
    protected UIResource sessionAirdResource;

    /** The Session Tree item wrapper. */
    protected UILocalSession localSession;

    /** The DialectEditor (opened on representation creation) wrapper. */
    protected SWTBotSiriusDiagramEditor editor;

    /** The reported errors. */
    protected Map<String, List<IStatus>> errors = new LinkedHashMap<>();

    /** The reported warnings. */
    protected Map<String, List<IStatus>> warnings = new LinkedHashMap<>();

    private boolean defaultEnableAnimatedZoom;

    private boolean defaultEnableAnimatedLayout;

    private boolean defaultPromptPasteMode;

    /**
     * HashMaps to store the initial values of preferences before changes.
     */
    private final HashMap<String, Object> oldValueDiagramPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueDiagramUIPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueCommonPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusUIPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldPlatformUIPreferences = new HashMap<String, Object>();

    /** The unchaught exceptions handler. */
    private UncaughtExceptionHandler exceptionHandler;

    /** The platform log listener. */
    private ILogListener logListener;

    /** Boolean to activate error catch. */
    private boolean errorCatchActive;

    /** Boolean to activate warning catch. */
    private boolean warningCatchActive;

    @Override
    protected void setUp() throws Exception {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            // Just call getActiveWorkbenchWindow() to avoid potential
            // empty list in getWorkbenchWindows() (see bug 441507).
            PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            Shell shell = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell();
            String osName = System.getProperty("os.name");
            if (osName.contains("Mac") || osName.contains("Linux")) {
                shell.setMaximized(AbstractSiriusSwtBotGefTestCase.fFullScreen);
            } else {
                shell.setFullScreen(AbstractSiriusSwtBotGefTestCase.fFullScreen);
            }
            if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
            }
        });

        initLoggers();

        closeAllSessions(true);

        // CHECKSTYLE:OFF
        System.out.println("Setup of " + this.getClass().getName() + AbstractSiriusSwtBotGefTestCase.POINT + getName() + "()");
        // CHECKSTYLE:ON
        try {
            super.setUp();

            // Disable the animated zoom and the animated arrange to save time
            UIThreadRunnable.syncExec(() -> {
                IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                defaultEnableAnimatedZoom = preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM);
                preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, false);
                defaultEnableAnimatedLayout = preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT);
                preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, false);
                defaultPromptPasteMode = preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name());
                preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), false);

                // Set the auto-refresh to false because it's historically
                // the default value
                DefaultScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
                InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), getAutoRefreshMode());
            });

            // If you need another name, override getProjectName()
            designerProject = designerPerspective.createProject(getProjectName());

            onSetUpBeforeClosingWelcomePage();

            closeWelcomePage();

            // Open Design perspective
            designerPerspectives.openModelingPerspective();

            onSetUpAfterOpeningDesignerPerspective();
            // CHECKSTYLE:OFF
        } catch (Throwable e) {
            takeScreenshot("-after-setup");
            // Call the tear down to clean the environment (not done by JUnit if
            // setup failed)
            try {
                failureTearDown();
            } catch (Exception secondException) {
                // CHECKSTYLE:ON
                // Ignore this secondException, because the first is the more
                // important
            }
            if (e instanceof Exception) {
                throw (Exception) e;
            } else {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Define the auto refresh mode to use during this tests.
     *
     * @return the auto refresh mode.
     */
    protected boolean getAutoRefreshMode() {
        return false;
    }

    /**
     * Get the project name to create.
     *
     * @return Project name.
     */
    protected String getProjectName() {
        return AbstractSiriusSwtBotGefTestCase.TEMP_PROJECT_NAME;
    }

    /**
     * Do something before closing welcome page during setup.
     *
     * @throws Exception
     *             Error.
     */
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Default, nothing
    }

    /**
     * Do something after opening designer perspective, just before executing test.
     *
     * @throws Exception
     *             Error.
     */
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // Default, nothing
    }

    /**
     * Close all opened editors without saving. A waitAllUiEvents is called to wait the closing of editors that is run
     * by Eclipse in asyncExec.
     */
    protected void closeAllEditors() {
        bot.getDisplay().asyncExec(() -> {
            for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                for (IWorkbenchPage page : window.getPages()) {
                    page.closeAllEditors(false);
                }
            }
        });
        // wait ui events according to editor
        SWTBotUtils.waitAllUiEvents();
    }

    private Set<String> closeAllSessions(final boolean warn) {
        final Set<String> sessionIDs = new HashSet<String>();
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            Collection<Session> sessionsToClose = new LinkedHashSet<>();
            for (final Session sess1 : new LinkedHashSet<>(SessionManager.INSTANCE.getSessions())) {
                if (sess1.isOpen()) {
                    sessionsToClose.add(sess1);
                }
            }
            if (warn && !sessionsToClose.isEmpty()) {
                System.out.println("WARNING : the followings sessions were not closed on tearDown of previous tests: ");
                for (Session s1 : sessionsToClose) {
                    System.out.println("\t" + s1.getID());
                }
            }
            for (final Session sess2 : sessionsToClose) {
                if (sess2.isOpen()) {
                    sessionIDs.add(sess2.getID());
                    sess2.close(new NullProgressMonitor());
                }
            }
            if (warn && !sessionsToClose.isEmpty()) {
                if (sessionsToClose.size() == sessionIDs.size()) {
                    System.out.println("They have been closed now.");
                } else {
                    System.out.println("WARNING: some session are still open after close attempt: ");
                    for (Session s2 : sessionsToClose) {
                        if (!sessionIDs.contains(s2.getID())) {
                            System.out.println("\t" + s2.getID());
                        }
                    }
                }
            }
        });
        return sessionIDs;
    }

    /**
     * Close the Welcome Page.
     */
    protected void closeWelcomePage() {
        Matcher<IWorkbenchPartReference> matcher = WidgetMatcherFactory.withPartName("Welcome");
        List<SWTBotView> views = bot.views(matcher);
        for (SWTBotView swtBotView : views) {
            swtBotView.close();
        }
    }

    /**
     * Request an explicit refresh of the current diagram.
     */
    protected void manualRefresh() {
        SWTBotUtils.toolbarButtonWithTooltip(DiagramDialectUIServices.REFRESH_DIAGRAM).click();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * {@inheritDoc}
     *
     * runtTest() method is overridden to allow to take a screenshot just after test execution and before tearDown
     * operation. As tearDown closes diagram and seTssion, after it, screenshots don't show any interesting things.
     */
    @Override
    // CHECKSTYLE:OFF
    // Need Throwable type by contract
    protected void runTest() throws Throwable {
        try {
            super.runTest();
        } catch (Throwable running) {
            // CHECKSTYLE:ON
            takeScreenshot("-before-tearDown");
            throw running;
        }
    }

    /**
     * Helper used by {@link #runTest()}.
     *
     * @param suffix
     *            The suffix of the screenshot to create
     * @see #runTest()
     */
    public void takeScreenshot(CharSequence suffix) {
        String fileName = SWTBotPreferences.SCREENSHOTS_DIR + "/screenshot-" + ClassUtils.simpleClassName(getClass()) + AbstractSiriusSwtBotGefTestCase.POINT + getName() + suffix
                + AbstractSiriusSwtBotGefTestCase.POINT + SWTBotPreferences.SCREENSHOT_FORMAT.toLowerCase();
        new File(SWTBotPreferences.SCREENSHOTS_DIR).mkdirs(); // $NON-NLS-1$
        SWTUtils.captureScreenshot(fileName);
    }

    /**
     * Open error log.
     */
    protected void openErrorLogViewByAPI() {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            try {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.pde.runtime.LogView");
            } catch (PartInitException e) {
                TestCase.fail("Unable to open errorLog view : " + e.getMessage());
            }
        });
    }

    /**
     * Assert the editor is not an error one, close it before failing if it is to avoid breaking further tests.
     *
     * @param message
     *            : message on failure.
     * @param activeEditor
     *            the editor to check.
     */
    protected void assertEditorIsNotError(String message, SWTBotEditor activeEditor) {
        IEditorPart editorPart = activeEditor.getReference().getEditor(false);
        boolean isError = editorPart instanceof ErrorEditorPart;
        if (isError) {
            activeEditor.close();
        }
        TestCase.assertFalse(message, isError);
    }

    /**
     * Undo with shortcut CTRL+z, at the end of this method execution, the operation undo is finished.
     */
    protected void undo() {
        Assert.assertTrue(editor != null);
        ICondition condition = new OperationUndoneCondition();
        bot.activeEditor();

        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = AbstractSiriusSwtBotGefTestCase.EN_US;
        editor.getCanvas().pressShortcut(SWT.CTRL, 'z');
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;

        bot.waitUntil(condition);
    }

    /**
     * Undo using the command stack of the editing domain of the current session.
     *
     * @param session
     *            current session to undo last action.
     */
    protected void undo(Session session) {
        session.getTransactionalEditingDomain().getCommandStack().undo();
    }

    /**
     * Undo the command named cmdName with the menu Edit.
     *
     * @param cmdName
     *            the command to undo
     */
    protected void undo(String cmdName) {
        String menuLabel = "Undo " + cmdName;
        if (PLATFORM_VERSION.getMajor() == 3 && PLATFORM_VERSION.getMinor() >= 30) {
            menuLabel = "Undo";
        }
        SWTBotSiriusHelper.menu(bot, AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu(menuLabel).click();
    }

    /**
     * Redo with shortcut CTRL+y, at the end of this method execution, the operation redo is finished.
     */
    protected void redo() {
        Assert.assertTrue(editor != null);
        ICondition condition = new OperationRedoneCondition();
        bot.activeEditor();

        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = AbstractSiriusSwtBotGefTestCase.EN_US;
        if (System.getProperty("os.name").equals("Linux") && (TestsUtil.isJuno3Platform() || TestsUtil.isEclipse4xPlatform())) {
            editor.getCanvas().pressShortcut(SWT.SHIFT | SWT.CTRL, 'z');
        } else {
            editor.getCanvas().pressShortcut(SWT.CTRL, 'y');
        }
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;

        bot.waitUntil(condition);
    }

    /**
     * Redo the command named cmdName with the menu Edit.
     *
     * @param cmdName
     *            the command to redo
     */
    protected void redo(String cmdName) {
        String menuLabel = "Redo " + cmdName;
        if (PLATFORM_VERSION.getMajor() == 3 && PLATFORM_VERSION.getMinor() >= 30) {
            menuLabel = "Redo";
        }
        SWTBotSiriusHelper.menu(bot, AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu(menuLabel).click();
    }

    /**
     * Redo using the command stack of the editing domain of the current session.
     *
     * @param session
     *            current session to redo last action.
     */
    protected void redo(Session session) {
        session.getTransactionalEditingDomain().getCommandStack().redo();
    }

    /**
     * Cancel the custom style.
     */
    protected void launchCancelCustomStyle() {
        bot.buttonWithTooltip("Cancel custom style").click();
    }

    /**
     * Change a preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Integer newValue) {
        assertNoDiagramUIPreferenceChangedinDiagramCoreStore(preferenceKey);

        int oldValue = Platform.getPreferencesService().getInt(DiagramPlugin.ID, preferenceKey, 0, null);
        oldValueDiagramPreferences.put(preferenceKey, oldValue);

        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        diagramCorePreferences.putInt(preferenceKey, newValue);

        int valueToCheck = Platform.getPreferencesService().getInt(DiagramPlugin.ID, preferenceKey, 0, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, DiagramPlugin.ID), newValue.intValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Boolean newValue) {
        assertNoDiagramUIPreferenceChangedinDiagramCoreStore(preferenceKey);

        boolean oldValue = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, preferenceKey, false, null);
        oldValueDiagramPreferences.put(preferenceKey, oldValue);

        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        diagramCorePreferences.putBoolean(preferenceKey, newValue);

        boolean valueToCheck = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, DiagramPlugin.ID), newValue.booleanValue(), valueToCheck);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramPreference(String, Boolean)} to have effect.
     *
     * @param preferenceKey
     *            The key of the preference.
     */
    protected void resetDiagramPreference(String preferenceKey) {
        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        resetDiagramPreference(preferenceKey, diagramCorePreferences);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramPreference(String, Boolean)} to have effect.
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param diagramCorePreferences
     *            The {@link IEclipsePreferences} to use.
     */
    private void resetDiagramPreference(String preferenceKey, IEclipsePreferences diagramCorePreferences) {
        Object initialValue = oldValueDiagramPreferences.get(preferenceKey);
        if (initialValue instanceof Boolean) {
            diagramCorePreferences.putBoolean(preferenceKey, (Boolean) initialValue);
        } else if (initialValue instanceof Integer) {
            diagramCorePreferences.putInt(preferenceKey, (Integer) initialValue);
        }
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramUIPreference(final String preferenceKey, final Boolean newValue) {
        assertNoDiagramCorePreferenceChangedinDiagramUIStore(preferenceKey);

        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValueDiagramUIPreferences.put(preferenceKey, prefs.getBoolean(preferenceKey));
        UIThreadRunnable.syncExec(() -> prefs.setValue(preferenceKey, newValue));
    }

    /**
     * Change an int preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramUIPreference(final String preferenceKey, final int newValue) {
        assertNoDiagramCorePreferenceChangedinDiagramUIStore(preferenceKey);

        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValueDiagramUIPreferences.put(preferenceKey, prefs.getInt(preferenceKey));
        UIThreadRunnable.syncExec(() -> prefs.setValue(preferenceKey, newValue));
    }

    /**
     * Change a double preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramUIPreference(final String preferenceKey, final double newValue) {
        assertNoDiagramCorePreferenceChangedinDiagramUIStore(preferenceKey);

        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValueDiagramUIPreferences.put(preferenceKey, prefs.getDouble(preferenceKey));
        UIThreadRunnable.syncExec(() -> prefs.setValue(preferenceKey, newValue));
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramUIPreference(String, Boolean)} to have effect.
     *
     * @param preferenceKey
     *            The key of the preference.
     */
    protected void resetDiagramUIPreference(String preferenceKey) {
        IPreferenceStore diagramUIPreferences = DiagramUIPlugin.getPlugin().getPreferenceStore();
        resetDiagramUIPreference(preferenceKey, diagramUIPreferences);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramUIPreference(String, Boolean)} to have effect.
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param diagramUIPreferences
     *            The {@link IPreferenceStore} to use.
     */
    private void resetDiagramUIPreference(String preferenceKey, IPreferenceStore diagramUIPreferences) {
        Object initialValue = oldValueDiagramUIPreferences.get(preferenceKey);
        if (initialValue instanceof Boolean) {
            diagramUIPreferences.setValue(preferenceKey, (Boolean) initialValue);
        } else if (initialValue instanceof Integer) {
            diagramUIPreferences.setValue(preferenceKey, (Integer) initialValue);
        }
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusPreference(String preferenceKey, Boolean newValue) {
        boolean oldValue = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        oldValueSiriusPreferences.put(preferenceKey, oldValue);

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        corePreferences.putBoolean(preferenceKey, newValue);

        boolean valueToCheck = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, SiriusPlugin.ID), newValue.booleanValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusCommonPreference(String preferenceKey, Boolean newValue) {
        boolean oldValue = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        oldValueCommonPreferences.put(preferenceKey, oldValue);

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusTransPlugin.PLUGIN_ID);
        corePreferences.putBoolean(preferenceKey, newValue);

        boolean valueToCheck = Platform.getPreferencesService().getBoolean(SiriusTransPlugin.PLUGIN_ID, preferenceKey, false, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, SiriusTransPlugin.PLUGIN_ID), newValue.booleanValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     *
     * TO CALL ONLY ONCE PER TEST (set up + test)
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusUIPreference(String preferenceKey, Boolean newValue) {
        assertNoSiriusCorePreferenceChangedinSiriusUIStore(preferenceKey);

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        oldValueSiriusUIPreferences.put(preferenceKey, viewpointUIPrefs.getBoolean(preferenceKey));
        viewpointUIPrefs.setValue(preferenceKey, newValue);
    }

    /**
     * Change a boolean preference and store the old value to reset it after the test.
     *
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changePlatformUIPreference(String preferenceKey, Boolean newValue) {
        IPreferenceStore viewpointUIPrefs = PlatformUI.getPreferenceStore();
        oldPlatformUIPreferences.put(preferenceKey, viewpointUIPrefs.getBoolean(preferenceKey));
        viewpointUIPrefs.setValue(preferenceKey, newValue);
    }

    private void assertNoSiriusCorePreferenceChangedinSiriusUIStore(String preferenceKey) {
        Collection<SiriusPreferencesKeys> coreKeys = Arrays.asList(SiriusPreferencesKeys.values());
        TestCase.assertFalse("The DesignerPreferenceKey named " + preferenceKey + " should not be modified in the UI store.",
                coreKeys.stream().anyMatch(key -> java.util.Objects.equals(preferenceKey, key.name())));
    }

    private void assertNoDiagramCorePreferenceChangedinDiagramUIStore(String preferenceKey) {
        Collection<String> coreKeys = new ArrayList<>();
        for (SiriusDiagramInternalPreferencesKeys key : SiriusDiagramInternalPreferencesKeys.values()) {
            coreKeys.add(key.name());
        }
        for (SiriusDiagramPreferencesKeys key : SiriusDiagramPreferencesKeys.values()) {
            coreKeys.add(key.name());
        }
        coreKeys.add(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_LINE_STYLE);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE);
        coreKeys.add(SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK);
        assertFalse("The Diagram core preference named " + preferenceKey + " should not be modified in the Diagram UI store.", coreKeys.contains(preferenceKey));
    }

    private void assertNoDiagramUIPreferenceChangedinDiagramCoreStore(String preferenceKey) {
        Collection<String> uiKeys = new ArrayList<>();
        for (SiriusDiagramUiInternalPreferencesKeys key : SiriusDiagramUiInternalPreferencesKeys.values()) {
            uiKeys.add(key.name());
        }
        for (SiriusDiagramUiPreferencesKeys key : SiriusDiagramUiPreferencesKeys.values()) {
            uiKeys.add(key.name());
        }

        assertFalse("The Diagram UI preference named " + preferenceKey + " should not be modified in the Diagram core store.", uiKeys.contains(preferenceKey));
    }

    private String getErrorMessage(String preferenceKey, String pluginId) {
        return "The " + preferenceKey + " preference value was not changed for plugin " + pluginId;
    }

    /**
     * Delete the selected element if possible.
     */
    protected void deleteSelectedElement() {
        SWTBotMenu deleteMenu = checkDeleteMenuEnablement(true);
        deleteMenu.click();
    }

    /**
     * Delete the selected element from diagram if possible.
     */
    protected void deleteFromDiagram() {
        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = AbstractSiriusSwtBotGefTestCase.EN_US;
        editor.getCanvas().pressShortcut(SWT.CTRL | SWT.SHIFT, 'd');
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Delete the selected element if possible.
     *
     * @param expected
     *            expected value of the delete menu.
     * @return the delete bot menu.
     */
    protected SWTBotMenu checkDeleteMenuEnablement(boolean expected) {
        SWTBotMenu deleteMenu = SWTBotSiriusHelper.menu(bot, AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu("Delete");

        String errorMessage = expected ? "Delete menu should be enabled" : "Delete menu was not enabled";
        TestCase.assertEquals(errorMessage, expected, deleteMenu.isEnabled());

        return deleteMenu;
    }

    /**
     * Toggle Active view/editor maximize. If possible call directly {@link #maximizeEditor(SWTBotSiriusDiagramEditor)}
     * and {@link #restoreEditor(SWTBotSiriusDiagramEditor)}, they are faster because they use directly Eclipse API.
     */
    protected void maximizeEditor() {
        SWTBotSiriusHelper.menu(bot, "Window").menu("Navigation").menu("Maximize Active View or Editor").click();
    }

    /**
     * Maximize view/editor maximize using the eclipse API.
     *
     * @param swtBotDesignerEditor
     *            The editor to maximize
     */
    protected void maximizeEditor(SWTBotSiriusDiagramEditor swtBotDesignerEditor) {
        swtBotDesignerEditor.maximize();
    }

    /**
     * Press zoom in button.
     *
     * @param swtBotDesignerEditor
     *            The current editor
     */
    protected void pressZoomInButton(SWTBotSiriusDiagramEditor swtBotDesignerEditor) {
        pressZoomInButton(swtBotDesignerEditor, 1);
    }

    /**
     * Press "pressCount" times on zoom in button.
     *
     * @param swtBotDesignerEditor
     *            The current editor
     * @param pressCount
     *            the number of times to press zoom in
     */
    protected void pressZoomInButton(SWTBotSiriusDiagramEditor swtBotDesignerEditor, int pressCount) {
        for (int i = 1; i <= pressCount; i++) {
            // 2 possible values for this tooltip according to the target
            // platform
            // No common constant was found, so we try with both possible
            // values
            try {
                swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom In (Ctrl++)").click();
            } catch (WidgetNotFoundException e) {
                swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom In (Ctrl+=)").click();
            }
        }
    }

    /**
     * Press zoom out button.
     *
     * @param swtBotDesignerEditor
     *            The current editor
     */
    protected void pressZoomOutButton(SWTBotSiriusDiagramEditor swtBotDesignerEditor) {
        pressZoomOutButton(swtBotDesignerEditor, 1);
    }

    /**
     * Press "pressCount" times on zoom out button.
     *
     * @param swtBotDesignerEditor
     *            The current editor
     * @param pressCount
     *            the number of times to press zoom in
     */
    protected void pressZoomOutButton(SWTBotSiriusDiagramEditor swtBotDesignerEditor, int pressCount) {
        for (int i = 1; i <= pressCount; i++) {
            swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom Out (Ctrl+-)").click();
        }
    }

    /**
     * Restore the size of the view/editor using the eclipse API.
     *
     * @param swtBotDesignerEditor
     *            The editor to restore
     */
    protected void restoreEditor(SWTBotSiriusDiagramEditor swtBotDesignerEditor) {
        swtBotDesignerEditor.restore();
    }

    /***
     * Do a arrange all of the current diagram using the Diagram menu.
     *
     * @return the menu of the ArrangeAll action
     */
    protected SWTBotMenu arrangeAll() {
        if (editor != null) {
            // Give the focus to the editor
            editor.setFocus();
            // Select the diagram itself
            editor.select(editor.mainEditPart());
        }
        // Launch the arrange via the menu bar
        SWTBotMenu arrangeAllMenutBot = SWTBotSiriusHelper.menu(bot, DiagramUIMessages.DiagramMainMenu_DiagramMainMenuText) //
                .menu(Messages.ArrangeMenuManager_Arrange_ActionLabelText) //
                .menu(Messages.ArrangeAction_ArrangeAll_ActionLabelText) //
                .click();
        SWTBotUtils.waitAllUiEvents();
        return arrangeAllMenutBot;
    }

    /***
     * Do a arrange all of the current diagram using the contextual menu.
     *
     * @return the menu of the ArrangeAll action
     */
    protected SWTBotMenu arrangeAllContextMenu() {
        assertNotNull("Check your test: the field editor can't be null to perform arrangeAllContextMenu", editor);
        // Launch the arrange via the contextual menu of the editor
        SWTBotMenu arrangeAllMenutBot = editor.getCanvas() //
                .contextMenu() //
                .menu(Messages.ArrangeMenuManager_Arrange_ActionLabelText) //
                .menu(Messages.ArrangeAction_ArrangeAll_ActionLabelText) //
                .click();
        SWTBotUtils.waitAllUiEvents();
        return arrangeAllMenutBot;
    }

    /***
     * Do a layout children on the selected elements.
     */
    protected void layoutChildren() {
        // Launch the layout via the menu bar
        SWTBotSiriusHelper.menu(bot, "Diagram").menu("Layout").menu("Children").click();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Toggle the Move Pinned Element action.
     */
    protected void movePinnedElements() {
        // Give the focus to the editor
        editor.setFocus();
        // Launch the arrange via the menu bar
        SWTBotSiriusHelper.menu(bot, DiagramUIMessages.DiagramMainMenu_DiagramMainMenuText) //
                .menu(Messages.ArrangeMenuManager_Arrange_ActionLabelText) //
                .menu(Messages.MovePinnedElementsAction_text) //
                .click();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Returns the bot for the DropDownButton, which contains all paste actions.
     */
    protected SWTBotToolbarDropDownButton getPasteMenu() {
        String[] tooltips = { Messages.PasteStylePureGraphicalAction_toolTipText, Messages.PasteFormatAction_toolTipText_diagram, Messages.PasteFormatAction_toolTipText_diagramElements,
                Messages.PasteLayoutAction_toolTipText_diagram, Messages.PasteLayoutAction_toolTipText_diagramElements, Messages.PasteStyleAction_toolTipText_diagram,
                Messages.PasteStyleAction_toolTipText_diagramElements, Messages.PasteImageAction_toolTipText, };
        List<Matcher<? extends ToolItem>> tooltipMatchers = new ArrayList<>();
        Arrays.stream(tooltips).forEach(tooltip -> tooltipMatchers.add(WidgetMatcherFactory.withTooltip(tooltip)));
        Matcher<ToolItem> tooltipMatcher = WidgetMatcherFactory.anyOf(tooltipMatchers);
        Matcher<ToolItem> typeMatcher = WidgetMatcherFactory.widgetOfType(ToolItem.class);
        Matcher<ToolItem> styleMatcher = WidgetMatcherFactory.withStyle(SWT.DROP_DOWN, "SWT.DROP_DOWN");
        Matcher<ToolItem> matcher = WidgetMatcherFactory.allOf(List.of(typeMatcher, tooltipMatcher, styleMatcher));
        return new SWTBotToolbarDropDownButton(editor.bot().widget(matcher), matcher);
    }

    /**
     * This method returns whether an item of a DropDownButton is enabled or not.
     */
    protected boolean dropDownButtonItemIsEnable(SWTBotToolbarDropDownButton dropDownButton, String itemText) {
        if (dropDownButton.isEnabled()) {
            SWTBotMenu item = dropDownButton.menuItem(itemText);
            boolean isEnable = item.isEnabled();
            dropDownButton.pressShortcut(Keystrokes.ESC);
            return isEnable;
        } else {
            return false;
        }
    }

    /**
     * This method clicks on an item of a DropDownButton (or on this DropDownButton if it's the active item).
     */
    protected void dropDownButtonItemClick(SWTBotToolbarDropDownButton dropDownButton, String itemText, String itemTooltip) {
        if (itemTooltip.equals(dropDownButton.getToolTipText())) {
            dropDownButton.click();
        } else {
            dropDownButton.menuItem(itemText).click();
        }
    }

    /**
     * Get all the representation with the given representation description name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @return a {@link Collection} with all representations retrieved.
     */
    protected Collection<DRepresentation> getRepresentations(final Session session, final String representationDescriptionName) {
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);

        final Collection<DRepresentation> representations = new HashSet<DRepresentation>();

        for (final DRepresentation representation : allRepresentations) {
            final RepresentationDescription desc = DialectManager.INSTANCE.getDescription(representation);
            if (desc != null && representationDescriptionName.equals(desc.getName())) {
                representations.add(representation);
            }
        }
        return representations;
    }

    /**
     * Returns the first representation descriptor with a given name, optionally filtering by a representation
     * description name.
     *
     * @param session
     *            The session to search
     * @param representationDescriptionName
     *            The name of the representation description. May be null.
     * @param representationName
     *            The name of the representation descriptor to search.
     * @return a {@link DRepresentationDescriptor} or null if no matching descriptor exists in the session
     */
    protected DRepresentationDescriptor getRepresentationDescriptorWithName(Session session, String representationDescriptionName, String representationName) {
        for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
            if (representationName.equals(descriptor.getName())) {
                if (representationDescriptionName == null || representationDescriptionName.equals(descriptor.getDescription().getName())) {
                    return descriptor;
                }
            }
        }
        return null;
    }

    /**
     * Return the first representation with the given representation description name and representation name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @param representationName
     *            the name of the representation
     * @return the first corresponding representation
     */
    protected final DRepresentation getRepresentationWithName(Session session, String representationDescriptionName, final String representationName) {
        DRepresentationDescriptor representationDescriptorWithName = getRepresentationDescriptorWithName(session, representationDescriptionName, representationName);
        if (representationDescriptorWithName != null) {
            return representationDescriptorWithName.getRepresentation();
        } else {
            throw new NoSuchElementException("No representation found in session with \"" + representationName + "\" as representation name and with \"" + representationDescriptionName
                    + "\" as representation description name.");
        }
    }

    /**
     * Open the first diagram with the given diagram description name and diagram name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param diagramDescriptionName
     *            the name of the diagram description. <code>null</code> is not excepted.
     * @param diagramName
     *            the name of the diagram
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @return the editor of the first corresponding diagram
     * @deprecated To replace by {@link #openRepresentation(Session, String, String, Class)}
     */
    @Deprecated
    protected SWTBotSiriusDiagramEditor openDiagram(Session session, String diagramDescriptionName, final String diagramName, Class<? extends DRepresentation> expectedRepresentationClass) {
        return openDiagram(session, diagramDescriptionName, diagramName, expectedRepresentationClass, false);
    }

    /**
     * Open the first representation with the given representation description name and representation name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param diagramDescriptionName
     *            the name of the diagram description. <code>null</code> is not excepted.
     * @param diagramName
     *            the name of the diagram
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @param disableSnapToGridOnThisEditor
     *            true if the snapToGrid must be disable for this editor, false otherwise
     * @return the editor of the first corresponding diagram
     * @deprecated To replace by {@link #openRepresentation(Session, String, String, Class, boolean)}
     */
    @Deprecated
    protected SWTBotSiriusDiagramEditor openDiagram(Session session, String diagramDescriptionName, final String diagramName, Class<? extends DRepresentation> expectedRepresentationClass,
            boolean disableSnapToGridOnThisEditor) {
        assertTrue("This method is only able to deal with diagrams.", DiagramPackage.Literals.DDIAGRAM.getInstanceClass().isAssignableFrom(expectedRepresentationClass));
        SWTBotEditor diagramEditor = openRepresentation(session, diagramDescriptionName, diagramName, expectedRepresentationClass, disableSnapToGridOnThisEditor);
        assertTrue(diagramEditor instanceof SWTBotSiriusDiagramEditor);
        return (SWTBotSiriusDiagramEditor) diagramEditor;
    }

    /**
     * Open the first representation with the given representation description name and representation name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @param representationName
     *            the name of the representation
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @return the editor of the first corresponding representation
     */
    protected SWTBotEditor openRepresentation(Session session, String representationDescriptionName, final String representationName, Class<? extends DRepresentation> expectedRepresentationClass) {
        return openRepresentation(session, representationDescriptionName, representationName, expectedRepresentationClass, false, true);
    }

    /**
     * Open the first representation with the given representation description name and representation name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @param representationName
     *            the name of the representation
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @param disableSnapToGridOnThisEditor
     *            true if the snapToGrid must be disable for this editor, false otherwise
     * @return the editor of the first corresponding representation
     */
    protected SWTBotEditor openRepresentation(Session session, String representationDescriptionName, final String representationName, Class<? extends DRepresentation> expectedRepresentationClass,
            boolean disableSnapToGridOnThisEditor) {
        return openRepresentation(session, representationDescriptionName, representationName, expectedRepresentationClass, disableSnapToGridOnThisEditor, true);
    }

    /**
     * Open the first representation with the given representation description name and representation name.
     *
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @param representationName
     *            the name of the representation
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @param disableSnapToGridOnThisEditor
     *            true if the snapToGrid must be disable for this editor, false otherwise
     * @param disableSnapToShapeOnThisEditor
     *            true if the snapToShape must be disable for this editor, false otherwise
     * @return the editor of the first corresponding representation
     */
    protected SWTBotEditor openRepresentation(Session session, String representationDescriptionName, final String representationName, Class<? extends DRepresentation> expectedRepresentationClass,
            boolean disableSnapToGridOnThisEditor, boolean disableSnapToShapeOnThisEditor) {
        // Get the diagram with this name
        DRepresentation representation = getRepresentationWithName(session, representationDescriptionName, representationName);
        TestCase.assertTrue("This representation should be a " + expectedRepresentationClass.getSimpleName(), expectedRepresentationClass.isInstance(representation));
        // Open the corresponding editor
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        SWTBotUtils.waitAllUiEvents();
        // Get the corresponding SWtBotEditor
        SWTBotEditor swtBotEditor = null;
        // Since Luna, e4 removes the leading and trailing spaces from the
        // editors' name, so we must adjust the title of the editor we are
        // looking for.
        String expectedTitle = editorPart.getTitle();
        if (TestsUtil.isLunaPlatform()) {
            expectedTitle = Optional.ofNullable(expectedTitle).orElse("").trim();
        }
        if (DDiagram.class.isAssignableFrom(expectedRepresentationClass)) {
            swtBotEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(expectedTitle);
            if (disableSnapToGridOnThisEditor) {
                ((SWTBotSiriusDiagramEditor) swtBotEditor).setSnapToGrid(false);
            }
            if (disableSnapToShapeOnThisEditor) {
                ((SWTBotSiriusDiagramEditor) swtBotEditor).setSnapToShape(false);
            }
        } else {
            swtBotEditor = SWTBotSiriusHelper.getSiriusEditor(expectedTitle);
        }

        return swtBotEditor;
    }

    /**
     * Get the widget bot corresponding to the button to set a workspace image on a selected node. If tabbar parameter
     * at true, get the button of the tabbar, else get the button of the Appearance tab on the properties view.
     *
     * @param tabbar
     *            if tabbar parameter at true, get the button of the tabbar, else get the button of the Appearance tab
     *            on the properties view
     * @param enabled
     *            true to check if the button must be enable, false to check if it shouldn't be enabled
     * @return the widget bot corresponding to the button to set a workspace image on a selected node
     */
    protected AbstractSWTBot<? extends Widget> getSetStyleToWorkspaceImageButton(boolean tabbar, boolean enabled) {
        AbstractSWTBot<? extends Widget> wkpImageButton = null;
        if (tabbar) {
            wkpImageButton = getSetStyleToWorkspaceImageButtonFromTabbar();
        } else {
            wkpImageButton = getSetStyleToWorkspaceImageButtonFromAppearanceTab();
        }
        TestCase.assertNotNull("Can't find the SetStyleToWorkspaceImage button in the " + (tabbar ? "tabbar" : "Appearance tab"), wkpImageButton);
        TestCase.assertEquals("The SetStyleToWorkspaceImage button should be " + (enabled ? "enabled" : "disabled"), enabled, wkpImageButton.isEnabled());
        return wkpImageButton;
    }

    /**
     * Get the widget bot corresponding to the button to "Reset style properties to default" on a selected node/edge. If
     * tabbar parameter at true, get the button of the tabbar, else get the button of the Appearance tab on the
     * properties view.
     *
     * @param tabbar
     *            if tabbar parameter at true, get the button of the tabbar, else get the button of the Appearance tab
     *            on the properties view
     * @param enabled
     *            true to check if the button must be enable, false to check if it shouldn't be enabled
     * @return the widget bot corresponding to the button to "Reset style properties to default" on a selected node/edge
     */
    protected AbstractSWTBot<? extends Widget> getResetStylePropertiesToDefaultValuesButton(boolean tabbar, boolean enabled) {
        AbstractSWTBot<? extends Widget> resetStylePropertiesToDefaultValuesButton = null;
        if (!tabbar) {
            resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        } else {
            resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        }
        TestCase.assertNotNull("Can't find the \"" + ResetStylePropertiesToDefaultValuesAction.ACTION_NAME + "\" button in the " + (tabbar ? "tabbar" : "Appearance tab"),
                resetStylePropertiesToDefaultValuesButton);
        TestCase.assertEquals("The \"" + ResetStylePropertiesToDefaultValuesAction.ACTION_NAME + "\" button should be " + (enabled ? "enabled" : "disabled"), enabled,
                resetStylePropertiesToDefaultValuesButton.isEnabled());
        return resetStylePropertiesToDefaultValuesButton;
    }

    private SWTBotToolbarButton getSetStyleToWorkspaceImageButtonFromTabbar() {
        return getTabbarButton(AbstractSiriusSwtBotGefTestCase.SET_STYLE_TO_WORKSPACE_IMAGE);
    }

    /**
     * Returns the button allowing to reset the style to default from the tabbar.
     *
     * @return the button allowing to reset the style to default from the tabbar
     */
    protected SWTBotToolbarButton getResetStylePropertiesToDefaultValuesButtonFromTabbar() {
        return getTabbarButton(ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
    }

    private SWTBotButton getSetStyleToWorkspaceImageButtonFromAppearanceTab() {
        return getSectionButton(AbstractSiriusSwtBotGefTestCase.SET_STYLE_TO_WORKSPACE_IMAGE);
    }

    /**
     * Click on the specified button.
     *
     * @param button
     *            the specified button
     */
    protected void click(AbstractSWTBot<? extends Widget> button) {
        if (button instanceof SWTBotToolbarButton) {
            ((SWTBotToolbarButton) button).click();
        } else if (button instanceof SWTBotButton) {
            ((SWTBotButton) button).click();
        }
    }

    /**
     * Returns the button allowing to reset the style to default from the appearance section.
     *
     * @return the button allowing to reset the style to default from the appearance section
     */
    protected SWTBotButton getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab() {
        SWTBotButton resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = null;
        editor.show();
        editor.setFocus();
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getSectionButton(ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
        return resetStylePropertiesToDefaultValuesButtonFromAppearanceTab;
    }

    /**
     * Returns the button from the 'Appearance' section at the given index, that should have the given tooltip.
     *
     * @param tooltip
     *            the expected tooltip for this button
     * @return the button from the 'Appearance' section at the given index, that should have the given tooltip
     */
    protected SWTBotButton getSectionButton(String tooltip) {
        SWTBot propertiesBot = bot.viewByTitle("Properties").bot();
        bot.viewByTitle("Properties").setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesBot);
        SWTBotButton button = propertiesBot.buttonWithTooltipInGroup(tooltip, "Fonts and Colors:");

        TestCase.assertNotNull(button);
        // get button from index and check requested tool-tip allows to check
        // position
        TestCase.assertEquals(tooltip, button.getToolTipText());

        return button;
    }

    private SWTBotToolbarButton getTabbarButton(String tooltip) {
        editor.show();
        SWTBot tabbarBot = editor.bot();
        SWTBotToolbarButton button = tabbarBot.toolbarButtonWithTooltip(tooltip);

        TestCase.assertNotNull("No button found with tooltip \"" + tooltip + "\"", button);

        return button;
    }

    /**
     * Initialize the log listener
     */
    private void initLoggers() {
        logListener = (status, plugin) -> {
            switch (status.getSeverity()) {
            case IStatus.ERROR:
                if (!"org.eclipse.ui.views.properties.tabbed".equals(status.getPlugin()) && status.getMessage() != null && !status.getMessage().startsWith(
                        "Contributor org.eclipse.ui.navigator.ProjectExplorer cannot be created., exception: org.eclipse.core.runtime.CoreException: Plug-in \"org.eclipse.ui.navigator.resources\" was unable to instantiate class \"org.eclipse.ui.internal.navigator.resources.workbench.TabbedPropertySheetTitleProvider\".")) {
                    errorOccurs(status, plugin);
                }
                break;
            case IStatus.WARNING:
                warningOccurs(status, plugin);
                break;
            default:
                // nothing to do
            }
        };
        Platform.addLogListener(logListener);

        final String sourcePlugin = "Uncaught exception";
        exceptionHandler = (Thread t, Throwable e) -> {
            IStatus status = new Status(IStatus.ERROR, sourcePlugin, sourcePlugin, e);
            errorOccurs(status, sourcePlugin);
        };

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        setErrorCatchActive(true);
        setWarningCatchActive(false);
    }

    /**
     * Dispose the log listener.
     */
    private void disposeLoggers() {
        if (logListener != null) {
            Platform.removeLogListener(logListener);
        }
    }

    /**
     * check if an error occurs.
     *
     * @return true if an error occurs.
     */
    protected synchronized boolean doesAnErrorOccurs() {
        return errorsCount() > 0;
    }

    /**
     * Returns the total number of errors recorded.
     * 
     * @return the total number of errors recorded.
     */
    protected int errorsCount() {
        return errors.values().stream().mapToInt(List::size).sum();
    }

    /**
     * check if a warning occurs.
     *
     * @return true if a warning occurs.
     */
    protected synchronized boolean doesAWarningOccurs() {
        return warningsCount() > 0;
    }

    /**
     * Returns the total number of warnings recorded.
     * 
     * @return the total number of warnings recorded.
     */
    protected int warningsCount() {
        return warnings.values().stream().mapToInt(List::size).sum();
    }

    /**
     * check if an error catch is active.
     *
     * @return true if an error catch is active.
     */
    protected synchronized boolean isErrorCatchActive() {
        return errorCatchActive;
    }

    /**
     * check if a warning catch is active.
     *
     * @return true if a warning catch is active.
     */
    protected synchronized boolean isWarningCatchActive() {
        return warningCatchActive;
    }

    /**
     * Records the error.
     *
     * @param status
     *            error status to record
     * @param sourcePlugin
     *            source plugin in which the error occurred
     */
    private synchronized void errorOccurs(IStatus status, String sourcePlugin) {
        if (errorCatchActive) {
            boolean ignoreMessage = false;
            if ("org.eclipse.core.runtime".equals(sourcePlugin) && status != null) {
                if ("Could not acquire INavigatorContentService: Project Explorer not found.".equals(status.getMessage())) {
                    // Ignore error caused by bugzilla 489335 when tests are
                    // launched with product "org.eclipse.platform.ide".
                    ignoreMessage = true;
                } else if (status.getMessage() != null && status.getMessage().startsWith("Resource '/.org.eclipse.jdt.core.external.folders/.link")
                        && status.getMessage().endsWith("' already exists.")) {
                    // Ignore errors that sometimes appears only with runtime
                    // environment during VSP creation.
                    ignoreMessage = true;
                }
            }
            if (!ignoreMessage) {
                errors.putIfAbsent(sourcePlugin, new ArrayList<>());
                errors.get(sourcePlugin).add(status);
            }
        }
    }

    /**
     * Records the warning.
     *
     * @param status
     *            warning status to record
     * @param sourcePlugin
     *            source plugin in which the warning occurred
     */
    private synchronized void warningOccurs(IStatus status, String sourcePlugin) {
        if (warningCatchActive) {
            warnings.putIfAbsent(sourcePlugin, new ArrayList<>());
            warnings.get(sourcePlugin).add(status);
        }
    }

    /**
     * Activate or deactivate the external error detection: the test will fail in an error is logged or uncaught.
     *
     * @param errorCatchActive
     *            boolean to indicate if we activate or deactivate the external error detection
     */
    protected synchronized void setErrorCatchActive(boolean errorCatchActive) {
        this.errorCatchActive = errorCatchActive;
    }

    /**
     * Activate or deactivate the external warning detection: the test will fail in a warning is logged or uncaught.
     *
     * @param warningCatchActive
     *            boolean to indicate if we activate or deactivate the external warning detection
     */
    protected synchronized void setWarningCatchActive(boolean warningCatchActive) {
        this.warningCatchActive = warningCatchActive;
    }

    /**
     * Enable warning and/or error catch and reset existing recorded warnings and/or errors.
     *
     * @param activateWarningCatch
     *            True to activate warning catch and reset existing recorded warnings, false to not activate it.
     * @param activateErrorCatch
     *            True to activate error catch and reset existing recorded errors, false to not activate it.
     */
    protected void startToListenErrorLog(boolean activateWarningCatch, boolean activateErrorCatch) {
        setWarningCatchActive(activateWarningCatch);
        if (activateWarningCatch) {
            warnings.clear();
        }
        setErrorCatchActive(activateErrorCatch);
        if (activateErrorCatch) {
            errors.clear();
        }
    }

    /**
     * Check that there is no existing error or warning.
     */
    private void checkLogs() {
        /* an exception occurs in another thread */

        /*
         * TODO : skip checkErrors when we are in a shouldSkipUnreliableTests mode. We have some unwanted resource
         * notifications during the teardown on jenkins.
         */
        if (!TestsUtil.shouldSkipUnreliableTests()) {
            if (doesAnErrorOccurs()) {
                Assert.fail(getErrorLoggersMessage());
            }

            if (doesAWarningOccurs()) {
                Assert.fail(getWarningLoggersMessage());
            }
        }
    }

    /**
     * Compute an error message from the detected errors.
     *
     * @return the error message.
     */
    protected synchronized String getErrorLoggersMessage() {

        StringBuilder log1 = new StringBuilder();
        String br = "\n";

        String testName = getClass().getName();

        log1.append("Error(s) raised during test : " + testName).append(br);
        for (Entry<String, List<IStatus>> entry : errors.entrySet()) {
            String reporter = entry.getKey();
            log1.append(". Log Plugin : " + reporter).append(br);

            for (IStatus status : entry.getValue()) {
                log1.append("  . " + getSeverity(status) + " from plugin:" + status.getPlugin() + ", message: " + status.getMessage() + ", exception: " + status.getException()).append(br);
                appendStackTrace(log1, br, status);
            }
            log1.append(br);
        }
        return log1.toString();
    }

    /**
     * Compute an error message from the detected warnings.
     *
     * @return the error message.
     */
    protected synchronized String getWarningLoggersMessage() {

        StringBuilder log1 = new StringBuilder();
        String br = "\n";

        String testName = getClass().getName();

        log1.append("Warning(s) raised during test : " + testName).append(br);
        for (Entry<String, List<IStatus>> entry : warnings.entrySet()) {
            String reporter = entry.getKey();
            log1.append(". Log Plugin : " + reporter).append(br);

            for (IStatus status : entry.getValue()) {
                log1.append("  . " + getSeverity(status) + " from plugin:" + status.getPlugin() + ", message: " + status.getMessage() + ", exception: " + status.getException()).append(br);
                appendStackTrace(log1, br, status);
            }
            log1.append(br);
        }
        return log1.toString();
    }

    private void appendStackTrace(StringBuilder log1, String br, IStatus status) {
        PrintWriter pw = null;
        String stacktrace = null;
        if (status.getException() != null) {
            try {
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                // CHECKSTYLE:OFF
                status.getException().printStackTrace(pw);
                // CHECKSTYLE:ON
                stacktrace = sw.toString();
            } finally {
                if (pw != null) {
                    pw.close();
                }
                if (stacktrace == null) {
                    stacktrace = status.getException().toString();
                }
                log1.append("   . Stack trace: " + stacktrace).append(br);
            }
        }
    }

    private String getSeverity(IStatus status) {
        String severity;
        switch (status.getSeverity()) {
        case IStatus.OK:
            severity = "Ok";
            break;
        case IStatus.INFO:
            severity = "Info";
            break;
        case IStatus.WARNING:
            severity = "Warning";
            break;
        case IStatus.CANCEL:
            severity = "Cancel";
            break;
        case IStatus.ERROR:
            severity = "Error";
            break;
        default:
            severity = "Unspecified";
        }
        return severity;
    }

    // Cannot be overriden, we are trying to preserve and cleanup workspace for
    // next tests
    private void failureTearDown() throws Exception {
        CrossReferenceAdapterDetector crossRefDetector = new CrossReferenceAdapterDetector();
        try {
            SWTBotUtils.waitAllUiEvents();
            // Close an eventual popup if the test failed and a popup remain
            // opened
            if (bot != null) {
                // replace shells() by this code to avoid a
                // WidgetNotFoundException: The widget {null} was disposed.
                Shell[] shells = bot.getFinder().getShells();
                ArrayList<SWTBotShell> result = new ArrayList<SWTBotShell>();
                for (Shell shell : shells) {
                    if (!shell.isDisposed()) {
                        result.add(new SWTBotShell(shell));
                    }
                }
                final Set<Shell> workbenchWindowsWidgets = new HashSet<>();
                for (IWorkbenchWindow w : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                    workbenchWindowsWidgets.add(w.getShell());
                }

                final SWTBotShell[] foundShells = result.toArray(new SWTBotShell[] {});
                for (final SWTBotShell swtBotShell : foundShells) {
                    // Close all opened windows except Eclipse
                    if (!workbenchWindowsWidgets.contains(swtBotShell.widget)) {
                        if (swtBotShell.isOpen()) {
                            swtBotShell.close();
                        } else {
                            String shellText = swtBotShell.getText();
                            if (!StringUtil.isEmpty(shellText) && SHELL_TO_CLOSE_AT_TEAR_DOWN_TEXTS.contains(shellText)) {
                                System.err.println("The shell \"" + shellText
                                        + "\" is closed but not disposed. Something is potentially not correctly clean in this test. A dispose of this shell is called on tearDown.");
                                UIThreadRunnable.syncExec(() -> swtBotShell.widget.dispose());
                            }
                        }
                    }
                }
            }
            SWTBotUtils.waitAllUiEvents();

            // Close all opened editors without saving before session closing to
            // avoid ui events managed to late provoking exceptions for example
            // because the session is already closed on ui event execution
            closeAllEditors();

            SWTBotUtils.waitAllUiEvents();

            crossRefDetector.checkNoCrossReferenceAdapter();
            closeAllSessions(false);

            SWTBotUtils.waitAllUiEvents();
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            bot.closeAllEditors();

            // Let the following loop delete the project.
            designerProject = null;

            SWTBotUtils.waitAllUiEvents();
            // Delete projects created by the tests
            for (final IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
                EclipseTestsSupportHelper.INSTANCE.deleteProject(project.getName());
            }

            disposeLoggers();
        } finally {
            // Reset the preferences changed during the test with the method
            // changePreference. This is done in the finally block in case of
            // ConcurrentModificationException during closeAllSession.
            resetPreferences();

            // Wait all ui events. Indeed, if we don't wait, the access to
            // preferenceStore cause some NPE because the editor is not dispose
            // : java.lang.NullPointerException at
            // DiagramDocumentEditor$PropertyChangeListener.propertyChange(DiagramDocumentEditor.java:1661)
            SWTBotUtils.waitAllUiEvents();

            UIThreadRunnable.syncExec(() -> {
                IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, defaultEnableAnimatedZoom);
                preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, defaultEnableAnimatedLayout);
                preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), defaultPromptPasteMode);
            });
            setErrorCatchActive(false);
            setWarningCatchActive(false);

            crossRefDetector.assertNoCrossReferenceAdapterFound();
            checkLogs();

            // All tearDown tasks have been done, now all fields can be safely clear.
            new TestCaseCleaner(this).clearAllFields();
        }

    }

    private void resetPreferences() {
        // Reset the preferences changed during the test with the method
        // changePreference. This is done in the finally block in case of
        // ConcurrentModificationException during closeAllSession.

        IEclipsePreferences diagamCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        for (String key : oldValueDiagramPreferences.keySet()) {
            resetDiagramPreference(key, diagamCorePreferences);
        }

        IPreferenceStore diagramUIPreferences = DiagramUIPlugin.getPlugin().getPreferenceStore();
        for (String key : oldValueDiagramUIPreferences.keySet()) {
            resetDiagramUIPreference(key, diagramUIPreferences);
        }
        boolean currentUiPreference = diagramUIPreferences.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name());
        if (currentUiPreference) {
            System.out.println("This test has not reset the oldUiPreference : " + this.getClass().getName() + " (it is currently true).");
        }

        IPreferenceStore platformUIPrefs = PlatformUI.getPreferenceStore();
        for (Entry<String, Object> pref : oldPlatformUIPreferences.entrySet()) {
            platformUIPrefs.setValue(pref.getKey(), (Boolean) pref.getValue());
        }

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        for (Entry<String, Object> pref : oldValueSiriusUIPreferences.entrySet()) {
            viewpointUIPrefs.setValue(pref.getKey(), (Boolean) pref.getValue());
        }

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        for (Entry<String, Object> pref : oldValueSiriusPreferences.entrySet()) {
            corePreferences.putBoolean(pref.getKey(), (Boolean) pref.getValue());
        }

        IEclipsePreferences commonPrefs = InstanceScope.INSTANCE.getNode(SiriusTransPlugin.PLUGIN_ID);
        for (Entry<String, Object> pref : oldValueCommonPreferences.entrySet()) {
            commonPrefs.putBoolean(pref.getKey(), (Boolean) pref.getValue());
        }

    }

    /**
     * Open viewpoint specification model.
     *
     * @param viewpointSpecificationModel
     *            the name of viewpoint specification model (.odesing)
     * @return odesignEditor
     */
    public SWTBotVSMEditor openViewpointSpecificationModel(String viewpointSpecificationModel) {
        SWTBotCommonHelper.openEditor(getProjectName(), viewpointSpecificationModel);
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(viewpointSpecificationModel);
        odesignEditor.setFocus();
        return odesignEditor;
    }

    /***
     * Copy file to test run project.
     *
     * @param pluginId
     *            corresponding to project name containing data for test
     * @param dataUnitDir
     *            the path of the directory containing data
     * @param fileNames
     *            the files to copy
     */
    protected void copyFileToTestProject(String pluginId, String dataUnitDir, final String... fileNames) {
        copyFileToProject(pluginId, dataUnitDir, getProjectName(), fileNames);
    }

    /**
     * Copy file to specified test project.
     *
     * @param pluginId
     *            corresponding to project name containing data for test
     * @param dataUnitDir
     *            the path of the directory containing data
     * @param projectName
     *            the name of the target project
     * @param fileNames
     *            the files to copy
     */
    protected void copyFileToProject(String pluginId, String dataUnitDir, String projectName, final String... fileNames) {
        for (final String fileName : fileNames) {
            String from = dataUnitDir + fileName;
            String to = projectName + "/" + fileName;
            EclipseTestsSupportHelper.INSTANCE.copyFile(pluginId, from, to);
        }
    }

    /**
     * Explore the current {@link SWTBotSiriusDiagramEditor} edit part children recursively to find out a
     * {@link TextEditPart} with the expected label.
     *
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null otherwise
     */
    public SWTBotGefEditPart findTextEditPart(String label) {
        return findTextEditPart(editor.rootEditPart(), label);
    }

    /**
     * Explore the {@link SWTBotSiriusDiagramEditor} edit part children recursively to find out a {@link TextEditPart}
     * with the expected label.
     *
     * @param designerEditor
     *            the current {@link SWTBotSiriusDiagramEditor} to look for a {@link TextEditPart}
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null otherwise
     */
    public SWTBotGefEditPart findTextEditPart(SWTBotSiriusDiagramEditor designerEditor, String label) {
        return findTextEditPart(designerEditor.rootEditPart(), label);
    }

    /**
     * Explore the {@link SWTBotGefEditPart} children recursively to find out a {@link TextEditPart} with the expected
     * label.
     *
     * @param parent
     *            the {@link SWTBotGefEditPart} parent to start exploration
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null otherwise
     */
    public SWTBotGefEditPart findTextEditPart(SWTBotGefEditPart parent, String label) {
        SWTBotGefEditPart result = null;
        if (parent.part() instanceof TextEditPart textEditPart) {
            var descriptionCompartmentEditPart = textEditPart.getChildren().stream().filter(DescriptionCompartmentEditPart.class::isInstance).map(DescriptionCompartmentEditPart.class::cast)
                    .findFirst().get();
            if (descriptionCompartmentEditPart.getFigure() instanceof WrappingLabel wrappingLabel && label.equals(wrappingLabel.getText())) {
                result = parent;
            }
        } else {
            for (SWTBotGefEditPart child : parent.children()) {
                SWTBotGefEditPart childrenResult = findTextEditPart(child, label);
                if (childrenResult != null) {
                    return childrenResult;
                }
            }
        }
        return result;
    }

    /**
     * Change the default font.
     *
     * @param fontName
     *            the font name to set as default.
     * @return the previous default font name.
     */
    protected String changeDefaultFontName(String fontName) {
        IPreferenceStore preferenceStore = (IPreferenceStore) DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT.getPreferenceStore();
        FontData fontData = PreferenceConverter.getFontData(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT);

        // Get the actual font.
        String oldName = fontData.getName();

        // Change the font.
        fontData.setName(fontName);
        PreferenceConverter.setDefault(preferenceStore, IPreferenceConstants.PREF_DEFAULT_FONT, fontData);
        return oldName;
    }

    /**
     * Scan contained figures to find the background color of a figure typed as figureClass.
     *
     * @param editPart
     *            EditPart containing the figures that need investigation
     * @param figureClass
     *            expected figure type
     * @return the background color of the figure if found, null otherwise
     */
    protected Color getFigureBackgroundColor(AbstractBorderedShapeEditPart editPart, Class<? extends Figure> figureClass) {
        IFigure figure = editPart.getFigure();
        if (figureClass.isInstance(figure)) {
            return figure.getBackgroundColor();
        } else {
            return getFigureBackgroundColor(figure, figureClass);
        }
    }

    /**
     * Scan contained figures to find the background color of a figure typed as figureClass.
     *
     * @param parentFigure
     *            {@link Figure} containing the figures that need investigation
     * @param figureClass
     *            expected figure type
     * @return the background color of the figure if found, null otherwise
     */
    protected Color getFigureBackgroundColor(IFigure parentFigure, Class<? extends Figure> figureClass) {
        List<? extends IFigure> filteredChildren = parentFigure.getChildren().stream().filter(figureClass::isInstance).toList();
        if (filteredChildren.size() == 1) {
            return filteredChildren.get(0).getBackgroundColor();
        }
        Color backgroundColor = null;
        for (IFigure childFigure : parentFigure.getChildren()) {
            backgroundColor = getFigureBackgroundColor(childFigure, figureClass);
            if (backgroundColor != null) {
                break;
            }
        }
        return backgroundColor;
    }

    @Override
    protected void tearDown() throws Exception {
        failureTearDown();

        super.tearDown();
        setErrorCatchActive(false);
        setWarningCatchActive(false);
        // Avoid NPE in
        // org.eclipse.ui.internal.statushandlers.StatusHandlerRegistry.<init>
        // on close.
        ResourcesPlugin.getWorkspace().save(true, null);
    }

    // CHECKSTYLE:OFF
    /**
     * Method override to use a specific captureScreenshot() that uses the constant SWTBotPreferences.SCREENSHOTS_DIR
     * instead of a hard coded folder name.
     */
    @Override
    public void runBare() throws Throwable {
        Throwable exception = null;
        try {
            super.runBare();
        } catch (Throwable running) {
            exception = running;
            captureScreenshot();
        }
        if (exception != null) {
            throw exception;
        }
    }

    /**
     * Helper used by {@link #runBare()}. Duplicate from {@link SWTBotTestCase#captureScreenshot()} to use the constant
     * SWTBotPreferences.SCREENSHOTS_DIR instead of a hard coded folder.
     *
     * @see #runBare()
     */
    private void captureScreenshot() {
        try {
            int maximumScreenshots = SWTBotPreferences.MAX_ERROR_SCREENSHOT_COUNT;
            String fileName = SWTBotPreferences.SCREENSHOTS_DIR + "/screenshot-" + ClassUtils.simpleClassName(getClass()) + POINT + getName() + POINT //$NON-NLS-1$
                    + SWTBotPreferences.SCREENSHOT_FORMAT.toLowerCase();
            if (++screenshotCounter <= maximumScreenshots) {
                new File(SWTBotPreferences.SCREENSHOTS_DIR).mkdirs();
                SWTUtils.captureScreenshot(fileName);
            } else {
                if (log != null) {
                    log.info("No screenshot captured for '" + ClassUtils.simpleClassName(getClass()) + POINT + getName() + "' because maximum number of screenshots reached: " + maximumScreenshots);
                } else {
                    System.err.println("ERR: No screenshot captured for '" + ClassUtils.simpleClassName(getClass()) + POINT + getName() + "' because maximum number of screenshots reached: "
                            + maximumScreenshots);
                }
            }
        } catch (Exception e) {
            if (log != null) {
                log.warn("Could not capture screenshot", e);
            } else {
                System.err.println("ERR: Could not capture screenshot:");
                e.printStackTrace();
            }
        }
    }
    // CHECKSTYLE:ON

    /**
     * Close the "Outline" view if it is visible (usually to avoid the performance overhead of having it update & redraw
     * diagrams when it is not needed). This method is safe to use even if the Outline view is not actually visible at
     * the time, and must be used instead of the more obvious
     * <code>bot.viewById("org.eclipse.ui.views.ContentOutline").close()</code> which can timeout and fail in this case.
     */
    protected void closeOutline() {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IViewReference[] views = page.getViewReferences();
            for (IViewReference view : views) {
                if (Objects.equals("org.eclipse.ui.views.ContentOutline", view.getId())) {
                    view.getPage().hideView(view);
                }
            }
        });
    }
}
