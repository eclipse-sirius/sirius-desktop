/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute.DistributeAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotSplitEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.CommandContributionItem;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * 
 * @author smonnier
 */
public class TabBarTest extends AbstractSiriusSwtBotGefTestCase {

    // We don't need a specific diagram so we reuse an existing one.
    private static final String REPRESENTATION_INSTANCE_NAME = "new tc2216";

    private static final String REPRESENTATION_NAME = "tc2216";

    private static final String VIEWPOINT_NAME = "tc2216";

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String[] DIAGRAM_TOOLBARDROPDOWNBUTTONS_TOOLTIPS = { "Arrange All", "Select &All", "Layers", "Filters", Messages.EditModeAction_Label,
            Messages.PasteFormatAction_toolTipText };

    private static final String[] DIAGRAM_TOOLBARBUTTONS_TOOLTIPS = { Messages.SiriusDiagramActionBarContributor_refreshDiagram, Messages.SelectHiddenElementsAction_tooltip,
            Messages.SelectPinnedElementsAction_tooltip, "Zoom In (Ctrl+=)", "Zoom Out (Ctrl+-)", Messages.SaveAsImageFileAction_label };

    private static final String[] CONTAINER_TOOLBARDROPDOWNBUTTONS_TOOLTIPS = { "Arrange Selection", "Align Left", DistributeAction.getTooltip(DistributeAction.GAPS_HORIZONTALLY), "Straighten to top",
            Messages.EditModeAction_Label, "Font Color", "Fill &Color", "Li&ne Color", "Line Style" };

    private static final String[] CONTAINER_TOOLBARBUTTONS_TOOLTIPS = { Messages.PinElementsEclipseAction_text, Messages.UnpinElementsEclipseAction_text, Messages.CopyFormatAction_toolTipText,
            Messages.SiriusDiagramActionBarContributor_showElement, Messages.SiriusDiagramActionBarContributor_hideElement, Messages.RevealOutlineLabelsAction_label,
            Messages.SiriusDiagramActionBarContributor_hideLabel, Messages.SiriusDiagramActionBarContributor_deleteFromDiagram, Messages.SiriusDiagramActionBarContributor_deleteFromModel, "Font",
            Messages.SetStyleToWorkspaceImageAction_text, Messages.ResetStylePropertiesToDefaultValuesAction_text,
            "Apply the applicable appearance properties of the last selected shape to the other selected shapes.", "Make height and width same size", "Auto Size" };

    private static final String[] CONTAINER_TOOLBARTOGGLEBUTTONS_TOOLTIPS = { "Bold Font Style", "Italic Font Style" };

    private static final String TABBAR_EXTENSION_ON_DIAGRAM_ELEMENT = "Action on DDiagramElement (F5)";

    private static final String TABBAR_EXTENSION_ON_DIAGRAM = "Action on DDiagram (F5)";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * .
     * 
     * @throws Exception
     *             Test error.
     */
    public void testToolBarButtonInitialization() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        SWTBotUtils.waitAllUiEvents();
        doTestToolbarButtonInitialization(false);

    }

    /**
     * .
     * 
     * @throws Exception
     *             Test error.
     */
    public void testToolBarButtonInitializationWithExtension() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        ActivateTestTabbarExtensionPropertyTester.enableTestTabbarExtensions(true);
        selectDiagramElement0();
        selectDiagram();
        try {
            doTestToolbarButtonInitialization(true);
        } finally {
            // Reset the property tester state.
            ActivateTestTabbarExtensionPropertyTester.enableTestTabbarExtensions(false);
        }
    }

    /**
     * When an element is selected on a diagram, setting focus on another view, than the editor, should not reload the
     * tabbar like if there was no selection. (VP-3663)
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTabbarButtonStabilityOnEditorFocusOut() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        // Select an element on diagram and validate Tabbar tools
        selectDiagramElement0();
        checkDiagramElementTabbarButtons(false);

        // Select the property view and validate Tabbar tools are still action
        // provided when a diagram element is selected
        bot.viewByTitle("Properties").setFocus();
        checkDiagramElementTabbarButtons(false);
    }

    /**
     * When an element is selected on a diagram, setting focus on another editor (with split editor area to display more
     * than one editor), should not reload the tabbar like if there was no selection. (VP-3663)
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTabbarButtonStabilityOnEditorSwitch() throws Exception {
        // Clone the diagram
        SWTBotTreeItem representationTreeItem = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME, UIDiagramRepresentation.class).getTreeItem().select();
        SWTBotUtils.clickContextMenu(representationTreeItem, "Copy");
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Copy representation");
        wizardBot.text(REPRESENTATION_INSTANCE_NAME).setText(REPRESENTATION_INSTANCE_NAME + "_Copy");
        wizardBot.button("OK").click();

        SWTBotSiriusDiagramEditor editorCopy = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME + "_Copy",
                DDiagram.class);

        // Split editor area to display both editor side by side
        SWTBotSplitEditor.splitEditorArea();

        if (SWTBotSplitEditor.isEditor_split()) {
            // Select an element on the first editor
            editor.setFocus();
            checkCanvasHashFocus(editor);

            selectDiagramElement0();

            // Switch to the second editor
            editorCopy.setFocus();
            checkCanvasHashFocus(editorCopy);

            // Validate that on the first editor, the tabbar still display
            // actions
            // for a selected element
            checkDiagramElementTabbarButtons(false);
        }
    }

    private void checkCanvasHashFocus(SWTBotSiriusDiagramEditor editorToCheck) {
        // The Zoom Combo should not take the focus, the Figure canvas should.
        final SWTBotGefFigureCanvas canvas = editorToCheck.getCanvas();
        Result<Boolean> hasFocus = new Result<Boolean>() {
            @Override
            public Boolean run() {
                return canvas.widget.isFocusControl();
            }
        };
        Boolean focus = UIThreadRunnable.syncExec(SWTUtils.display(), hasFocus);
        assertTrue("The canvas should have the focus.", focus);
    }

    private void doTestToolbarButtonInitialization(boolean activeExtensions) {
        // Validate tools of the tab bar without any diagram element selected
        checkDiagramTabbarButtons(activeExtensions);

        selectDiagramElement0();

        checkDiagramElementTabbarButtons(activeExtensions);
    }

    // Check is done with indexes, it allows to check that there are no visible
    // diagram specific actions and that diagram element actions are visible
    // (can be found in the toolbar) and
    // at the expected place.
    private void checkDiagramElementTabbarButtons(boolean activeExtensions) {
        List<String> elementSelectedTabbarButtons = new ArrayList<String>(Arrays.asList(CONTAINER_TOOLBARBUTTONS_TOOLTIPS));
        if (activeExtensions) {
            elementSelectedTabbarButtons.add(TABBAR_EXTENSION_ON_DIAGRAM_ELEMENT);
        }

        // Validate tools of the tab bar with a diagram element selected
        for (int i = 0; i < CONTAINER_TOOLBARDROPDOWNBUTTONS_TOOLTIPS.length; i++) {
            String expectedTooltip = CONTAINER_TOOLBARDROPDOWNBUTTONS_TOOLTIPS[i];
            SWTBotToolbarDropDownButton button = editor.bot().toolbarDropDownButton(i);
            assertEquals("The toolbarDropDownButton index " + i + " does not have the expected tooltip", expectedTooltip, button.getToolTipText());
        }
        for (int i = 0; i < elementSelectedTabbarButtons.size(); i++) {
            String expectedTooltip = elementSelectedTabbarButtons.get(i);
            SWTBotToolbarButton button = editor.bot().toolbarButton(i);
            assertEquals("The toolbarButton index " + i + " does not have the expected tooltip", expectedTooltip, button.getToolTipText());
        }
        for (int i = 0; i < CONTAINER_TOOLBARTOGGLEBUTTONS_TOOLTIPS.length; i++) {
            String expectedTooltip = CONTAINER_TOOLBARTOGGLEBUTTONS_TOOLTIPS[i];
            SWTBotToolbarToggleButton button = editor.bot().toolbarToggleButton(i);
            assertEquals("The toolbarToggleButton index " + i + " does not have the expected tooltip", expectedTooltip, button.getToolTipText());
        }

        if (!activeExtensions) {
            checkButtonNotPresent(TABBAR_EXTENSION_ON_DIAGRAM_ELEMENT);
        }
        checkButtonNotPresent(TABBAR_EXTENSION_ON_DIAGRAM);
    }

    private void checkButtonNotPresent(String tooltip) {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        boolean oldErrorCatchActive = isErrorCatchActive();
        setErrorCatchActive(false);
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.bot().toolbarButtonWithTooltip(tooltip);
            fail("The tabbar button with tooltip" + tooltip + " should not be in the tabbar.");
        } catch (Exception e) {
            // Ok.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
        setErrorCatchActive(oldErrorCatchActive);
    }

    private void selectDiagramElement0() {
        // Select a diagram element named "0"
        editor.reveal("0");
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, "0", AbstractDiagramContainerEditPart.class);
        editor.click("0");
        bot.waitUntil(cs);
    }

    private void selectDiagram() {
        SWTBotGefEditPart diagPart = editor.rootEditPart().children().iterator().next();
        IDDiagramEditPart part = (IDDiagramEditPart) diagPart.part();
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, part);
        editor.select(diagPart);
        bot.waitUntil(cs);

        // Wait for tabbar refresh
        // Should be removed when tabbar will be rewritten.
        bot.sleep(1000);
    }

    /**
     * Test toggling the old style preference programmatically. Note that this preference does not exist anymore in the
     * UI, but can still be used un-officially in the code to programmatically disable the tabbar. This possibility is
     * used for in some SWTBot tests that rely heavily on hard-coded screen corrdinates which were determined when the
     * tabbar did not exist (in particular for sequence diagrams).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTabbarActivation() throws Exception {
        // changeDiagramUIPreference is already called in set up for the old ui
        // pref, to set it to false.
        // we must not call this method again (it will change the value after
        // the
        // test)
        // The previous value (before this test and setup) will be restored
        // during tear down.
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                DiagramUIPlugin.getPlugin().getPreferenceStore().setValue(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

            }
        });

        editor.close();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        SWTBotUtils.waitAllUiEvents();

        try {
            editor.bot().toolbarButton();
            fail("We shouldn't have editor toolbar with SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name() pref to true");
        } catch (WidgetNotFoundException e) {

        }

    }

    /**
     * Test that tabbar items are well disposed.
     * 
     * @throws Exception
     */
    public void testTabbarDisposal() throws Exception {
        assertFalse("Tabbar should be active for this test.", DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name()));

        // Get the tabbar contributions
        editor.setFocus();
        DDiagramEditorImpl edit = (DDiagramEditorImpl) PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        List<IContributionItem> items = Arrays.asList(edit.getTabBarManager().getItems());

        // Close the current editor
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        // Check that all tabbar contributions are disposed.
        for (final IContributionItem item : items) {
            if (item instanceof ActionContributionItem) {
                IAction action = ((ActionContributionItem) item).getAction();
                if (action instanceof IDisposableAction) {
                    assertTrue("The action with id " + action.getId() + " should be disposed.", ((IDisposableAction) action).isDisposed());
                }

                final Collection<Class<?>> acceptedNonDisposedTypes = Arrays.asList(IAction.class, ImageDescriptor.class, Predicate.class, Function.class);
                Predicate<Field> acceptedNonDisposedField = new Predicate<Field>() {
                    @Override
                    public boolean apply(Field input) {
                        return acceptedNonDisposedTypes.contains(input.getType());
                    }
                };
                assertFieldsAreDisposed(action, acceptedNonDisposedField);
            }
            // Collection<Class<?>> acceptedNonDisposedTypes =
            // Arrays.asList(IAction.class, ImageDescriptor.class,
            // Predicate.class, Function.class, IPropertyChangeListener.class,
            // Listener.class, IPartService.class);

            // HandledContributionITem (e4) or CommandContributionITem (e3) are
            // used for the test contribution. Some fields (model, icons, ..)
            // are not reseted but these type of contribution are managed by
            // Eclipse
            if ("org.eclipse.e4.ui.workbench.renderers.swt.HandledContributionItem".equals(item.getClass().getCanonicalName()) || item instanceof CommandContributionItem) {
                continue;
            }

            Predicate<Field> privateEnclosingClassAccessor = new Predicate<Field>() {

                @Override
                public boolean apply(Field input) {
                    return input.getType() == item.getClass().getEnclosingClass() && "this$0".equals(input.getName());
                }
            };

            final Collection<Class<?>> acceptedNonDisposedTypes = Arrays.asList(IAction.class, Listener.class, IPropertyChangeListener.class);

            Predicate<Field> acceptedNonDisposedField = new Predicate<Field>() {
                @Override
                public boolean apply(Field input) {
                    return acceptedNonDisposedTypes.contains(input.getType());
                }
            };
            assertFieldsAreDisposed(item, Predicates.or(privateEnclosingClassAccessor, acceptedNonDisposedField));
        }
    }

    // Check that non primitive or String fields are disposed, ie have a null
    // value.
    private void assertFieldsAreDisposed(Object obj, Predicate<Field> skippedFieldPredicate) {
        // Reflectively check the disposal
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (!(field.getType().isPrimitive() || String.class == field.getType() || field.isSynthetic())) {
                if (skippedFieldPredicate != null && skippedFieldPredicate.apply(field)) {
                    // Accepted non null field.
                    continue;
                }

                Optional<Object> fieldValue = ReflectionHelper.getFieldValueWithoutException(obj, field.getName());
                assertFalse("The field " + field.getName() + " should be null for " + obj, fieldValue.isPresent());
            }
        }
    }

    /**
     * Test Pin/Unpin and Show/Hide wizard tabbar buttons are enabled when diagram is selected.
     * 
     * See VP-3731 : those buttons were disabled and hidden just after element creation. The diagram selection made them
     * visible but not enabled.
     */
    public void testTabbarDiagramActionsEnablement() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        // close the current editor
        editor.close();

        // Open the entity diagram
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        assertTrue("The diagram should be selected", ((IStructuredSelection) editor.getSelection()).getFirstElement() instanceof IDDiagramEditPart);
        checkDiagramTabbarButtons(false);

        // Create an EClass and select the diagram
        String eClassCreationTool = "Class";
        ICondition done = new CheckToolIsActivated(editor, eClassCreationTool);
        editor.activateTool(eClassCreationTool);
        bot.waitUntil(done);
        done = new OperationDoneCondition();
        editor.click(200, 200);
        bot.waitUntil(done);

        checkButtonNotPresent("Show/Hide");
        checkButtonNotPresent("Pin/Unpin");
        checkDiagramElementTabbarButtons(false);

        editor.click(300, 300);
        SWTBotUtils.waitAllUiEvents();

        // Check enablement
        assertTrue("The diagram should be selected", ((IStructuredSelection) editor.getSelection()).getFirstElement() instanceof IDDiagramEditPart);
        checkDiagramTabbarButtons(false);
    }

    // Check is done with indexes, it allows to check that there are no visible
    // diagram element specific actions and that diagram actions are visible
    // (can be found in the toolbar),
    // enabled and at the expected place.
    private void checkDiagramTabbarButtons(boolean activeExtensions) {
        List<String> diagramSelectedTabbarButtons = new ArrayList<String>(Arrays.asList(DIAGRAM_TOOLBARBUTTONS_TOOLTIPS));
        if (activeExtensions) {
            diagramSelectedTabbarButtons.add(TABBAR_EXTENSION_ON_DIAGRAM);
        }

        for (int i = 0; i < DIAGRAM_TOOLBARDROPDOWNBUTTONS_TOOLTIPS.length; i++) {
            SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButton(i);
            String expectedTooltip = DIAGRAM_TOOLBARDROPDOWNBUTTONS_TOOLTIPS[i];
            assertEquals("The toolbarDropDownButton index " + i + " does not have the expected tooltip", expectedTooltip, toolbarDropDownButton.getToolTipText());
            if (expectedTooltip.equals(Messages.PasteFormatAction_toolTipText)) {
                // Paste layout is not enabled if there is no previous copy
                // layout.
                continue;
            }
            assertTrue("The toolbarDropDownButton with tooltip " + expectedTooltip + " should be enabled", toolbarDropDownButton.isEnabled());
        }
        for (int i = 0; i < diagramSelectedTabbarButtons.size(); i++) {
            String expectedTooltip = diagramSelectedTabbarButtons.get(i);
            SWTBotToolbarButton button = editor.bot().toolbarButton(i);
            if (i == 3) {
                final String zoomIn = "Zoom In (Ctrl+";
                assertTrue("The toolbarButton index " + i + " does not have the expected tooltip, it does not starts with" + zoomIn, editor.bot().toolbarButton(i).getToolTipText().startsWith(zoomIn));
            } else {
                assertEquals("The toolbarButton index " + i + " does not have the expected tooltip", expectedTooltip, editor.bot().toolbarButton(i).getToolTipText());
            }
            assertTrue("The toolbarButton with tooltip " + expectedTooltip + " should be enabled", button.isEnabled());
        }

        if (!activeExtensions) {
            checkButtonNotPresent(TABBAR_EXTENSION_ON_DIAGRAM);
        }
        checkButtonNotPresent(TABBAR_EXTENSION_ON_DIAGRAM_ELEMENT);
    }
}
