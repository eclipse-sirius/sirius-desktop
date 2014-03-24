/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestCaseCleaner;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationRedoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationUndoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotDesignerHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.api.perspective.DesignerPerspectives;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.ClassUtils;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ErrorEditorPart;
import org.hamcrest.Matcher;
import org.junit.Assert;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Wrapper for several UI* classes to handle ui management in tests. If needed,
 * new setup operations can be done in
 * {@link #onSetUpBeforeClosingWelcomePage()} overridden method.
 * 
 * {@link #onSetUpAfterOpeningDesignerPerspective()} allows developer to add
 * extra behavior after opening designer perspective.
 * 
 * @author dlecan
 */
@SuppressWarnings("restriction")
public abstract class AbstractSiriusSwtBotGefTestCase extends SWTBotGefTestCase {

    static {
        SWTBotPreferences.TIMEOUT = 10000;
    }

    /**
     * Models dir.
     */
    protected static final String MODELS_DIR = "Models";

    /** Test project name. */
    protected static final String TEMP_PROJECT_NAME = "DesignerTestProject";

    private static final String EN_US = "EN_US";

    private static final String SET_STYLE_TO_WORKSPACE_IMAGE = "Set style to workspace image";

    private static final String POINT = ".";

    private static final String EDIT_MENU_NAME = "Edit";

    private static boolean fFullScreen = true;

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

    /**
     * Designer project.
     */
    protected UIProject designerProject;

    /**
     * The Session Resource wrapper.
     */
    protected UIResource sessionAirdResource;

    /**
     * The Session Tree item wrapper.
     */
    protected UILocalSession localSession;

    /**
     * The DialectEditor (opened on representation creation) wrapper.
     */
    protected SWTBotDesignerEditor editor;

    /**
     * The reported errors.
     */
    protected LinkedHashMultimap<String, IStatus> errors;

    private boolean defaultEnableAnimatedZoom;

    private boolean defaultEnableAnimatedLayout;

    private final HashMap<String, Object> oldValuePreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusUIPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldPlatformUIPreferences = new HashMap<String, Object>();

    /**
     * The unchaught exceptions handler.
     */
    private UncaughtExceptionHandler exceptionHandler;

    /**
     * The platform error listener.
     */
    private ILogListener logListener;

    private boolean errorCatchActive;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().setFullScreen(AbstractSiriusSwtBotGefTestCase.fFullScreen);
            }
        });

        /* Init error log and uncauht exception handlers */
        errors = LinkedHashMultimap.create();
        initErrorLoggers();

        System.out.println("Setup of " + this.getClass().getName() + AbstractSiriusSwtBotGefTestCase.POINT + getName() + "()");
        try {
            super.setUp();

            // Disable the animated zoom and the animated arrange to save time
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                    defaultEnableAnimatedZoom = preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM);
                    preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, false);
                    defaultEnableAnimatedLayout = preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT);
                    preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, false);

                    // Set the auto-refresh to false because it's historically
                    // the
                    // default value
                    DefaultScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
                    InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), getAutoRefreshMode());
                }
            });

            // If you need another name, override getProjectName()
            designerProject = designerPerspective.createProject(getProjectName());

            onSetUpBeforeClosingWelcomePage();

            closeWelcomePage();

            // Set up a no ui callback for auto migration
            // SiriusEditPlugin.getPlugin().setUiCallback(new
            // UiCallBackWithoutMigrationNotification(SiriusEditPlugin.getPlugin().getUiCallback()));

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
     * Do something after opening designer perspective, just before executing
     * test.
     * 
     * @throws Exception
     *             Error.
     */
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // Default, nothing
    }

    /**
     * Close all opened editors without saving. A waitAllUiEvents is called to
     * wait the closing of editors that is run by Eclipse in asyncExec.
     */
    protected void closeAllEditors() {
        bot.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {
                    for (int j = 0; j < PlatformUI.getWorkbench().getWorkbenchWindows()[i].getPages().length; j++) {
                        PlatformUI.getWorkbench().getWorkbenchWindows()[i].getPages()[j].closeAllEditors(false);
                    }
                }
            }
        });
        // wait ui events according to editor
        SWTBotUtils.waitAllUiEvents();
    }

    private void closeAllSessions() {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                for (final Session sess : Sets.newLinkedHashSet(SessionManager.INSTANCE.getSessions())) {
                    if (sess.isOpen()) {
                        sess.save(new NullProgressMonitor());
                        try {
                            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
                        } catch (OperationCanceledException e) {
                            TestCase.fail(e.getLocalizedMessage());
                        } catch (InterruptedException e) {
                            TestCase.fail(e.getLocalizedMessage());
                        }
                        sess.close(new NullProgressMonitor());
                    }
                }
            }
        });
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
        if (TestsUtil.isDynamicTabbar()) {
            bot.toolbarButtonWithTooltip(DiagramDialectUIServices.REFRESH_DIAGRAM).click();
        } else {
            // Use context menu instead of tabbar
            new SWTBotDesignerEditor(bot.activeEditor().getReference(), bot).clickContextMenu("Refresh");
        }
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
    }

    /**
     * {@inheritDoc}
     * 
     * runtTest() method is overridden to allow to take a screenshot just after
     * test execution and before tearDown operation. As tearDown closes diagram
     * and session, after it, screenshots don't show any interesting things.
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
        String fileName = "screenshots/screenshot-" + ClassUtils.simpleClassName(getClass()) + AbstractSiriusSwtBotGefTestCase.POINT + getName() + suffix + AbstractSiriusSwtBotGefTestCase.POINT
                + SWTBotPreferences.SCREENSHOT_FORMAT.toLowerCase();
        new File("screenshots").mkdirs(); //$NON-NLS-1$
        SWTUtils.captureScreenshot(fileName);
    }

    /**
     * Open error log.
     */
    protected void openErrorLogViewByAPI() {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.pde.runtime.LogView");
                } catch (PartInitException e) {
                    TestCase.fail("Unable to open errorLog view : " + e.getMessage());
                }
            }
        });
    }

    /**
     * Get the number of Status in the error log at the time of the call.
     * 
     * @return the number of Status in the error log at the time of the call
     */
    public int getNbStatusInErrorLog() {
        openErrorLogViewByAPI();

        SWTBotView logViewBot = bot.viewByTitle("Error Log");
        logViewBot.show();
        SWTBotTree tree = logViewBot.bot().tree();
        int nbStatus = tree.getAllItems().length;
        logViewBot.close();

        return nbStatus;
    }

    /**
     * Assert the editor is not an error one, close it before failing if it is
     * to avoid breaking further tests.
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
     * Undo with shortcut CTRL+z, at the end of this method execution, the
     * operation undo is finished.
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
     * Undo using the command stack of the editing domain of the current
     * session.
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
        bot.menu(AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu("Undo " + cmdName).click();
    }

    /**
     * Redo with shortcut CTRL+y, at the end of this method execution, the
     * operation redo is finished.
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
        bot.menu(AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu("Redo " + cmdName).click();
    }

    /**
     * Redo using the command stack of the editing domain of the current
     * session.
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
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(final String preferenceKey, final Boolean newValue) {
        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValuePreferences.put(preferenceKey, prefs.getBoolean(preferenceKey));
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                prefs.setValue(preferenceKey, newValue);
            }
        });
    }

    /**
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
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

        String message = "The " + preferenceKey + " preference value was not changed for plugin " + SiriusPlugin.ID;
        boolean valueToCheck = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(message, newValue.booleanValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusUIPreference(String preferenceKey, Boolean newValue) {
        Collection<SiriusPreferencesKeys> coreValues = Lists.newArrayList(SiriusPreferencesKeys.values());
        Function<SiriusPreferencesKeys, String> prefToName = new Function<SiriusPreferencesKeys, String>() {
            @Override
            public String apply(SiriusPreferencesKeys input) {
                return input.name();
            }
        };
        TestCase.assertFalse("The DesignerPreferenceKey named " + preferenceKey + " should not be modified in the UI store.",
                Lists.newArrayList(Iterables.transform(coreValues, prefToName)).contains(preferenceKey));

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        oldValueSiriusUIPreferences.put(preferenceKey, viewpointUIPrefs.getBoolean(preferenceKey));
        viewpointUIPrefs.setValue(preferenceKey, newValue);
    }

    /**
     * Change a boolean preference and store the old value to reset it after the
     * test.
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
        SWTBotMenu deleteMenu = bot.menu(AbstractSiriusSwtBotGefTestCase.EDIT_MENU_NAME).menu("Delete");

        String errorMessage = expected ? "Delete menu should be enabled" : "Delete menu was not enabled";
        TestCase.assertEquals(errorMessage, expected, deleteMenu.isEnabled());

        return deleteMenu;
    }

    /**
     * Toggle Active view/editor maximize. If possible call directly
     * {@link #maximizeEditor(SWTBotDesignerEditor)} and
     * {@link #restoreEditor(SWTBotDesignerEditor)}, they are faster because
     * they use directly Eclipse API.
     */
    protected void maximizeEditor() {
        bot.menu("Window").menu("Navigation").menu("Maximize Active View or Editor").click();
    }

    /**
     * Maximize view/editor maximize using the eclipse API.
     * 
     * @param swtBotDesignerEditor
     *            The editor to maximize
     */
    protected void maximizeEditor(SWTBotDesignerEditor swtBotDesignerEditor) {
        swtBotDesignerEditor.maximize();
    }

    /**
     * Press zoom in button.
     * 
     * @param swtBotDesignerEditor
     *            The current editor
     */
    protected void pressZoomInButton(SWTBotDesignerEditor swtBotDesignerEditor) {
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
    protected void pressZoomInButton(SWTBotDesignerEditor swtBotDesignerEditor, int pressCount) {
        for (int i = 1; i <= pressCount; i++) {
            if (TestsUtil.isDynamicTabbar()) {
                // 2 possible values for this tooltip according to the target
                // platform
                // No common constant was found, so we try with both possible
                // values
                try {
                    swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom In (Ctrl++)").click();
                } catch (WidgetNotFoundException e) {
                    swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom In (Ctrl+=)").click();
                }
            } else {
                double currentZoom = GraphicalHelper.getZoom((IGraphicalEditPart) ((DiagramRootEditPart) swtBotDesignerEditor.rootEditPart().part()).getContents());
                swtBotDesignerEditor.zoom(ZoomLevel.createNextZoomInLevel(currentZoom));
            }
        }
    }

    /**
     * Press zoom out button.
     * 
     * @param swtBotDesignerEditor
     *            The current editor
     */
    protected void pressZoomOutButton(SWTBotDesignerEditor swtBotDesignerEditor) {
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
    protected void pressZoomOutButton(SWTBotDesignerEditor swtBotDesignerEditor, int pressCount) {
        for (int i = 1; i <= pressCount; i++) {
            if (TestsUtil.isDynamicTabbar()) {
                swtBotDesignerEditor.bot().toolbarButtonWithTooltip("Zoom Out (Ctrl+-)").click();
            } else {
                double currentZoom = GraphicalHelper.getZoom((IGraphicalEditPart) ((DiagramRootEditPart) swtBotDesignerEditor.rootEditPart().part()).getContents());
                swtBotDesignerEditor.zoom(ZoomLevel.createNextZoomOutLevel(currentZoom));
            }
        }
    }

    /**
     * Restore the size of the view/editor using the eclipse API.
     * 
     * @param swtBotDesignerEditor
     *            The editor to restore
     */
    protected void restoreEditor(SWTBotDesignerEditor swtBotDesignerEditor) {
        swtBotDesignerEditor.restore();
    }

    /***
     * Do a arrange all of the current diagram.
     * 
     * @return the menu of the ArrangeAll action
     */
    protected SWTBotMenu arrangeAll() {
        // Give the focus to the editor
        editor.setFocus();
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Launch the arrange via the menu bar
        SWTBotMenu arrangeAllMenutBot = bot.menu("Diagram").menu("Arrange").menu("All").click();
        SWTBotUtils.waitAllUiEvents();
        return arrangeAllMenutBot;
    }

    /**
     * Get all the representation with the given representation description
     * name.
     * 
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code>
     *            is not excepted.
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
     * Return the first representation with the given representation description
     * name and representation name.
     * 
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code>
     *            is not excepted.
     * @param representationName
     *            the name of the representation
     * @return the first corresponding representation
     */
    protected final DRepresentation getRepresentationWithName(Session session, String representationDescriptionName, final String representationName) {
        try {
            final DRepresentation existingRepresentation = Iterables.find(getRepresentations(session, representationDescriptionName), new Predicate<DRepresentation>() {
                @Override
                public boolean apply(DRepresentation input) {
                    return input.getName().equals(representationName);
                }
            });
            return existingRepresentation;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No representation found in session with \"" + representationName + "\" as representation name and with \"" + representationDescriptionName
                    + "\" as representation description name.");
        }

    }

    /**
     * Open the first representation with the given representation description
     * name and representation name.
     * 
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code>
     *            is not excepted.
     * @param representationName
     *            the name of the representation
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @return the editor of the first corresponding representation
     */
    protected SWTBotDesignerEditor openRepresentation(Session session, String representationDescriptionName, final String representationName,
            Class<? extends DRepresentation> expectedRepresentationClass) {
        return openRepresentation(session, representationDescriptionName, representationName, expectedRepresentationClass, false);
    }

    /**
     * Open the first representation with the given representation description
     * name and representation name.
     * 
     * @param session
     *            The session containing the searched representations.
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code>
     *            is not excepted.
     * @param representationName
     *            the name of the representation
     * @param expectedRepresentationClass
     *            the expected type of representation to found
     * @param disableSnapToGridOnThisEditor
     *            true if the snapToGrid must be disable for this editor, false
     *            otherwise
     * @return the editor of the first corresponding representation
     */
    protected SWTBotDesignerEditor openRepresentation(Session session, String representationDescriptionName, final String representationName,
            Class<? extends DRepresentation> expectedRepresentationClass, boolean disableSnapToGridOnThisEditor) {
        // Get the diagram with this name
        DRepresentation representation = getRepresentationWithName(session, representationDescriptionName, representationName);
        TestCase.assertTrue("This representation should be a " + expectedRepresentationClass.getSimpleName(), expectedRepresentationClass.isInstance(representation));
        // Open the corresponding editor
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        SWTBotUtils.waitAllUiEvents();
        // Get the corresponding SWtBotEditor
        SWTBotDesignerEditor swtBotEditor = SWTBotDesignerHelper.getDesignerEditor(editorPart.getTitle());
        if (disableSnapToGridOnThisEditor) {
            swtBotEditor.disableSnapToGrid();
        }
        return swtBotEditor;
    }

    /**
     * Get the widget bot corresponding to the button to set a workspace image
     * on a selected node. If tabbar parameter at true, get the button of the
     * tabbar, else get the button of the Appearance tab on the properties view.
     * 
     * @param tabbar
     *            if tabbar parameter at true, get the button of the tabbar,
     *            else get the button of the Appearance tab on the properties
     *            view
     * @param enabled
     *            true to check if the button must be enable, false to check if
     *            it shouldn't be enabled
     * @return the widget bot corresponding to the button to set a workspace
     *         image on a selected node
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
     * Get the widget bot corresponding to the button to
     * "Reset style properties to default" on a selected node/edge. If tabbar
     * parameter at true, get the button of the tabbar, else get the button of
     * the Appearance tab on the properties view.
     * 
     * @param tabbar
     *            if tabbar parameter at true, get the button of the tabbar,
     *            else get the button of the Appearance tab on the properties
     *            view
     * @param enabled
     *            true to check if the button must be enable, false to check if
     *            it shouldn't be enabled
     * @return the widget bot corresponding to the button to
     *         "Reset style properties to default" on a selected node/edge
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
     * Returns the button allowing to reset the style to default from the
     * tabbar.
     * 
     * @return the button allowing to reset the style to default from the tabbar
     */
    protected SWTBotToolbarButton getResetStylePropertiesToDefaultValuesButtonFromTabbar() {
        return getTabbarButton(ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
    }

    private SWTBotButton getSetStyleToWorkspaceImageButtonFromAppearanceTab() {
        return getSectionButton(3, AbstractSiriusSwtBotGefTestCase.SET_STYLE_TO_WORKSPACE_IMAGE);
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
     * Returns the button allowing to reset the style to default from the
     * appearance section.
     * 
     * @return the button allowing to reset the style to default from the
     *         appearance section
     */
    protected SWTBotButton getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab() {
        SWTBotButton resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = null;
        int buttonIndex = 4;
        editor.show();
        editor.setFocus();
        ISelection selection = editor.getSelection();
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof AbstractDiagramEdgeEditPart) {
                buttonIndex = 3;
            }
        }
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getSectionButton(buttonIndex, ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
        return resetStylePropertiesToDefaultValuesButtonFromAppearanceTab;
    }

    /**
     * Returns the button from the 'Appearance' section at the given index, that
     * should have the given tooltip.
     * 
     * @param index
     *            the index of the button to get from the 'Appearance' section
     * @param tooltip
     *            the expected tooltip for this button
     * @return the button from the 'Appearance' section at the given index, that
     *         should have the given tooltip
     */
    protected SWTBotButton getSectionButton(int index, String tooltip) {
        SWTBot propertiesBot = bot.viewByTitle("Properties").bot();
        bot.viewByTitle("Properties").setFocus();
        SWTBotDesignerHelper.selectPropertyTabItem("Appearance");
        SWTBotButton button = propertiesBot.buttonInGroup("Fonts and Colors:", index);

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

    private void initErrorLoggers() {

        logListener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.ERROR) {

                    if (!"org.eclipse.ui.views.properties.tabbed".equals(status.getPlugin())
                            && status.getMessage() != null
                            && !status
                            .getMessage()
                            .startsWith(
                                    "Contributor org.eclipse.ui.navigator.ProjectExplorer cannot be created., exception: org.eclipse.core.runtime.CoreException: Plug-in \"org.eclipse.ui.navigator.resources\" was unable to instantiate class \"org.eclipse.ui.internal.navigator.resources.workbench.TabbedPropertySheetTitleProvider\".")) {
                        errorOccurs(status, plugin);
                    }
                }
            }

        };
        Platform.addLogListener(logListener);

        exceptionHandler = new UncaughtExceptionHandler() {
            private final String sourcePlugin = "Uncaught exception";

            @Override
            public void uncaughtException(Thread t, Throwable e) {

                IStatus status = new Status(IStatus.ERROR, sourcePlugin, sourcePlugin, e);
                errorOccurs(status, sourcePlugin);
            }
        };

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        setErrorCatchActive(true);
    }

    private void disposeErrorLoggers() {
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
        if (errors != null) {
            return errors.values().size() != 0;
        }
        return false;
    }

    /**
     * check if an error catch is active.
     * 
     * @return true if an error catch is active.
     */
    protected synchronized boolean isErrorCatchActive() {
        return errorCatchActive;
    }

    private synchronized void errorOccurs(IStatus status, String sourcePlugin) {
        if (errorCatchActive) {
            errors.put(sourcePlugin, status);
        }
    }

    /**
     * Activate or deactivate the external error detection: the test will fail
     * in an error is logged or uncaught.
     * 
     * @param errorCatchActive
     *            boolean to indicate if we activate or deactivate the external
     *            error detection
     */
    protected synchronized void setErrorCatchActive(boolean errorCatchActive) {
        this.errorCatchActive = errorCatchActive;
    }

    private void checkErrors() {
        /* an exception occurs in another thread */

        /*
         * TODO : skip checkErrors when we are in a shouldSkipUnreliableTests
         * mode. We have some unwanted resource notifications during the
         * teardown on jenkins.
         */
        if (!TestsUtil.shouldSkipUnreliableTests() && doesAnErrorOccurs()) {
            Assert.fail(getErrorLoggersMessage());
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
        for (Entry<String, Collection<IStatus>> entry : errors.asMap().entrySet()) {
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
        try {
            SWTBotUtils.waitAllUiEvents();

            // Close an eventual popup if the test failed and a popup remain
            // opened
            if (bot != null) {
                /*
                 * replace shells() by this code to avoid a
                 * WidgetNotFoundException: The widget {null} was disposed.
                 */
                Shell[] shells = bot.getFinder().getShells();
                ArrayList<SWTBotShell> result = new ArrayList<SWTBotShell>();
                for (Shell shell : shells) {
                    if (!shell.isDisposed()) {
                        result.add(new SWTBotShell(shell));
                    }
                }
                final SWTBotShell[] foundShells = result.toArray(new SWTBotShell[] {});

                for (final SWTBotShell swtBotShell : foundShells) {
                    // Close all opened windows except Eclipse
                    if (swtBotShell.isOpen()) {
                        for (IWorkbenchWindow w : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                            if (swtBotShell.widget != w.getShell()) {
                                swtBotShell.close();
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

            closeAllSessions();

            SWTBotUtils.waitAllUiEvents();
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            bot.closeAllEditors();

            // Let the following loop delete the project.
            designerProject = null;

            SWTBotUtils.waitAllUiEvents();
            // Delete projects created by the tests
            for (final IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
                designerPerspective.deleteProject(project.getName());
            }

            disposeErrorLoggers();
        } finally {
            // Reset the preferences changed during the test with the method
            // changePreference. This is done in the finally block in case of
            // ConcurrentModificationException during closeAllSession.
            resetPreferences();

            // Wait all ui events. Indeed, if we don't wait, the access to
            // preferenceStore cause some NPE because the editor is not dispose
            // :
            // java.lang.NullPointerException
            // at
            // org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor$PropertyChangeListener.propertyChange(DiagramDocumentEditor.java:1661)
            SWTBotUtils.waitAllUiEvents();

            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                    preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, defaultEnableAnimatedZoom);
                    preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, defaultEnableAnimatedLayout);
                }
            });
            setErrorCatchActive(false);
            checkErrors();
        }

    }

    private void resetPreferences() {
        // Reset the preferences changed during the test with the method
        // changePreference. This is done in the finally block in case of
        // ConcurrentModificationException during closeAllSession.
        IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        for (String key : oldValuePreferences.keySet()) {
            prefs.setValue(key, (Boolean) oldValuePreferences.get(key));
        }

        boolean currentUiPreference = prefs.getBoolean(SiriusDiagramPreferencesKeys.PREF_OLD_UI.name());
        if (currentUiPreference) {
            System.out.println("This test has not reset the oldUiPreference : " + this.getClass().getName() + " (it is currently true).");
        }

        // Reset the preferences changed during the test with the method
        // changePreference. This is done in the finally block in case of
        // ConcurrentModificationException during closeAllSession.
        IPreferenceStore platformPrefs = PlatformUI.getPreferenceStore();
        for (String key : oldPlatformUIPreferences.keySet()) {
            platformPrefs.setValue(key, (Boolean) oldPlatformUIPreferences.get(key));
        }

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        for (String key : oldValueSiriusUIPreferences.keySet()) {
            viewpointUIPrefs.setValue(key, (Boolean) oldValueSiriusUIPreferences.get(key));
        }

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        for (String key : oldValueSiriusPreferences.keySet()) {
            corePreferences.putBoolean(key, (Boolean) oldValueSiriusPreferences.get(key));
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
     *            the path of the directory containing datas
     * @param fileNames
     *            the files to copy
     */
    protected void copyFileToTestProject(String pluginId, String dataUnitDir, final String... fileNames) {
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(pluginId, dataUnitDir + fileName, getProjectName() + "/" + fileName);
        }
    }

    /**
     * Explore the current {@link SWTBotDesignerEditor} edit part children
     * recursively to find out a {@link TextEditPart} with the expected label.
     * 
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null
     *         otherwise
     */
    public SWTBotGefEditPart findTextEditPart(String label) {
        return findTextEditPart(editor.rootEditPart(), label);
    }

    /**
     * Explore the {@link SWTBotDesignerEditor} edit part children recursively
     * to find out a {@link TextEditPart} with the expected label.
     * 
     * @param designerEditor
     *            the current {@link SWTBotDesignerEditor} to look for a
     *            {@link TextEditPart}
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null
     *         otherwise
     */
    public SWTBotGefEditPart findTextEditPart(SWTBotDesignerEditor designerEditor, String label) {
        return findTextEditPart(designerEditor.rootEditPart(), label);
    }

    /**
     * Explore the {@link SWTBotGefEditPart} children recursively to find out a
     * {@link TextEditPart} with the expected label.
     * 
     * @param parent
     *            the {@link SWTBotGefEditPart} parent to start exploration
     * @param label
     *            the label of the expected {@link TextEditPart}
     * @return a {@link TextEditPart} with the expected label if existing, null
     *         otherwise
     */
    public SWTBotGefEditPart findTextEditPart(SWTBotGefEditPart parent, String label) {
        SWTBotGefEditPart result = null;
        if (parent.part() instanceof TextEditPart) {
            TextEditPart textEditPart = (TextEditPart) parent.part();
            DescriptionCompartmentEditPart descriptionCompartmentEditPart = Iterables.getOnlyElement(Iterables.filter(textEditPart.getChildren(), DescriptionCompartmentEditPart.class));
            if (descriptionCompartmentEditPart.getFigure() instanceof WrappingLabel && label.equals(((WrappingLabel) descriptionCompartmentEditPart.getFigure()).getText())) {
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
     * Scan contained figures to find the background color of a figure typed as
     * figureClass.
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
     * Scan contained figures to find the background color of a figure typed as
     * figureClass.
     * 
     * @param parentFigure
     *            {@link Figure} containing the figures that need investigation
     * @param figureClass
     *            expected figure type
     * @return the background color of the figure if found, null otherwise
     */
    protected Color getFigureBackgroundColor(IFigure parentFigure, Class<? extends Figure> figureClass) {
        Iterable<? extends Figure> filter = Iterables.filter(parentFigure.getChildren(), figureClass);
        if (Iterables.size(filter) == 1) {
            return Iterables.getOnlyElement(filter).getBackgroundColor();
        }
        Color backgroundColor = null;
        for (IFigure childFigure : Iterables.filter(parentFigure.getChildren(), IFigure.class)) {
            backgroundColor = getFigureBackgroundColor(childFigure, figureClass);
            if (backgroundColor != null) {
                break;
            }
        }
        return backgroundColor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void tearDown() throws Exception {
        failureTearDown();

        new TestCaseCleaner(this).clearAllFields();

        super.tearDown();
        setErrorCatchActive(false);
    }

}
