/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.LayerSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.tool.spec.ToolSectionSpec;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.Matchers;

/**
 * Test class for double click tool and navigation operation.
 * 
 * @author smonnier
 */
public class DoubleClickToolNavigationOperationTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Inner class to check if number of editor increases or not.
     * 
     * @author dlecan
     */
    private class CheckIncreaseOfNumberOfEditors extends DefaultCondition {

        private int initialNumberOfEditors;

        private final SWTWorkbenchBot bot;

        /**
         * Constructor.
         * 
         * @param bot
         *            Bot.
         */
        public CheckIncreaseOfNumberOfEditors(SWTWorkbenchBot bot) {
            this.bot = bot;
            initialNumberOfEditors = bot.editors().size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test() throws Exception {
            int newNumberOfEditors = bot.editors().size();
            return initialNumberOfEditors < newNumberOfEditors;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getFailureMessage() {
            return null;
        }
    }

    /**
     * Condition testing that the label of the class edit part has been upodated after the double click.
     * 
     * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
     *
     */
    private final class DoubleClickChangeCondition implements ICondition {

        private SWTBotSiriusDiagramEditor diagramEditor;

        public DoubleClickChangeCondition(SWTBotSiriusDiagramEditor diagramEditor) {
            this.diagramEditor = diagramEditor;
        }

        @Override
        public boolean test() throws Exception {
            SWTBotGefEditPart editPart = diagramEditor.getEditPart("test");
            return editPart != null;
        }

        @Override
        public String getFailureMessage() {
            return "Double click tool has not been taken in consideration. No change has been applied after double click.";
        }
    }

    private static final String REPRESENTATION_INSTANCE_NAME_R1_Root = "TC1054 representation 1 root";

    private static final String REPRESENTATION_INSTANCE_NAME_R1_SP2 = "TC1054 representation 1 sp2";

    private static final String REPRESENTATION_INSTANCE_NAME_R2_SYS1 = "TC1054 representation 2 on System1";

    private static final String REPRESENTATION_NAME = "TC1054 representation 1";

    private static final String MODEL = "tc1054.ecore";

    private static final String SESSION_FILE = "tc1054.aird";

    private static final String VSM_FILE = "tc1054.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/tools/DoubleClick__NavigationOperation/Viewpoint1054/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    private AdapterFactoryEditingDomain editingDomain;

    private LayerSpec layer;

    private ToolSectionSpec toolDescription;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_R1_Root, DDiagram.class);
    }

    /**
     * Test navigation tool by double click with refresh at opening at true.
     */
    public void testDoubleClickToolNavigationOperationWithRefreshAtOpeningAtTrue() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        // On the first diagram, double click can create a diagram if there is
        // no existing one
        editor.getEditPart("System1");
        editor.doubleClick("System1");

        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("New TC1054 representation 2");
        wizardBot.text("TC1054 representation 2").setText(REPRESENTATION_INSTANCE_NAME_R2_SYS1);
        wizardBot.button("OK").click();
        SWTBotSiriusDiagramEditor currentDesignerEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());

        assertThat("The active editor is not the expected one", currentDesignerEditor.getTitle(), Matchers.is(REPRESENTATION_INSTANCE_NAME_R2_SYS1));

        // On the second diagram, double click can not create a diagram if there
        // is no existing one

        // Double click on Sous-package3
        currentDesignerEditor.getEditPart("Sous-package3", AbstractBorderedShapeEditPart.class).select();
        currentDesignerEditor.doubleClick("Sous-package3", AbstractBorderedShapeEditPart.class);
        try {
            bot.waitUntil(new CheckIncreaseOfNumberOfEditors(bot), 4000);
        } catch (TimeoutException e) {
            // Nothing, expected, because number of editors must stay unchanged
        }

        SWTBotSiriusDiagramEditor newEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());
        assertThat("The active editor is not the expected one. The previous double-click should not have changed the active editor.", newEditor.getTitle(),
                Matchers.is(currentDesignerEditor.getTitle()));

        // Current editor didn't change

        // Double click on Sous-package2
        CheckIncreaseOfNumberOfEditors condition = new CheckIncreaseOfNumberOfEditors(bot);
        currentDesignerEditor.getEditPart("Sous-package 2", AbstractBorderedShapeEditPart.class).select();
        currentDesignerEditor.doubleClickCentered("Sous-package 2", AbstractBorderedShapeEditPart.class);
        bot.waitUntil(condition);
        assertEditorIsNotError("Double click navigation editor did not opened correctly", bot.activeEditor());

        newEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());
        assertThat("The active editor is not the expected one", newEditor.getTitle(), Matchers.is(REPRESENTATION_INSTANCE_NAME_R1_SP2));
    }

    /**
     * Test navigation tool by double click with refresh at opening at false.
     */
    public void testDoubleClickToolNavigationOperationWithRefreshAtOpeningAtFalse() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        // On the first diagram, double click can create a diagram if there is
        // no existing one
        editor.getEditPart("System1");
        editor.doubleClick("System1");

        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("New TC1054 representation 2");
        wizardBot.text("TC1054 representation 2").setText(REPRESENTATION_INSTANCE_NAME_R2_SYS1);
        wizardBot.button("OK").click();
        SWTBotSiriusDiagramEditor currentDesignerEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());

        assertThat("The active editor is not the expected one", currentDesignerEditor.getTitle(), Matchers.is(REPRESENTATION_INSTANCE_NAME_R2_SYS1));

        // On the second diagram, double click can not create a diagram if there
        // is no existing one

        currentDesignerEditor.getEditPart("Sous-package3", AbstractBorderedShapeEditPart.class).select();
        currentDesignerEditor.doubleClick("Sous-package3", AbstractBorderedShapeEditPart.class);
        try {
            bot.waitUntil(new CheckIncreaseOfNumberOfEditors(bot), 4000);
        } catch (TimeoutException e) {
            // Nothing, expected, because number of editors must stay unchanged
        }

        SWTBotSiriusDiagramEditor newEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());
        assertThat("The active editor is not the expected one. The previous double-click should not have changed the active editor.", newEditor.getTitle(),
                Matchers.is(currentDesignerEditor.getTitle()));

        // Current editor didn't change

        // Double click on Sous-package2
        CheckIncreaseOfNumberOfEditors condition = new CheckIncreaseOfNumberOfEditors(bot);
        currentDesignerEditor.getEditPart("Sous-package 2", AbstractBorderedShapeEditPart.class).select();
        currentDesignerEditor.doubleClickCentered("Sous-package 2", AbstractBorderedShapeEditPart.class);
        bot.waitUntil(condition);
        assertEditorIsNotError("Double click navigation editor did not opened correctly", bot.activeEditor());

        newEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(bot.activeEditor().getTitle());
        assertThat("The active editor is not the expected one", newEditor.getTitle(), Matchers.is(REPRESENTATION_INSTANCE_NAME_R1_SP2));

    }

    /**
     * Tests that a double click tool created in a workspace VSM is immediately available in a representation using this
     * VSM in the same workspace.
     */
    public void testDoubleClickToolApplicationInWorkspaceContext() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
        CustomSiriusEditor customSiriusEditor = (CustomSiriusEditor) PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        editingDomain = (AdapterFactoryEditingDomain) customSiriusEditor.getEditingDomain();
        layer = (LayerSpec) editingDomain.getResourceSet().getResources().get(0).getContents().get(0).eContents().get(0).eContents().get(0).eContents().get(0);
        toolDescription = (ToolSectionSpec) layer.eContents().get(1);

        editingDomain.getCommandStack().execute(new AbstractOverrideableCommand(editingDomain) {

            @Override
            public void doUndo() {
            }

            @Override
            public void doRedo() {
            }

            @Override
            public boolean doCanExecute() {
                return true;
            }

            @Override
            public void doExecute() {
                DoubleClickDescription newDoubleClickDescription = ToolFactory.eINSTANCE.createDoubleClickDescription();
                newDoubleClickDescription.setName("test");
                ElementDoubleClickVariable newElementDoubleClickVariable = ToolFactory.eINSTANCE.createElementDoubleClickVariable();
                newElementDoubleClickVariable.setName("element");
                newDoubleClickDescription.setElement(newElementDoubleClickVariable);
                newElementDoubleClickVariable = ToolFactory.eINSTANCE.createElementDoubleClickVariable();
                newElementDoubleClickVariable.setName("elementView");
                newDoubleClickDescription.setElementView(newElementDoubleClickVariable);

                InitialOperation initialOperation = newDoubleClickDescription.getInitialOperation();
                ChangeContext newChangeContext = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createChangeContext();
                newChangeContext.setBrowseExpression("var:element");
                initialOperation.setFirstModelOperations(newChangeContext);

                SetValue newSetValue = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createSetValue();
                newSetValue.setFeatureName("name");
                newSetValue.setValueExpression("aql:'test'");
                newChangeContext.getSubModelOperations().add(newSetValue);

                DiagramElementMapping mapping = (DiagramElementMapping) layer.eContents().get(0).eContents().get(0);
                newDoubleClickDescription.getMappings().add(mapping);

                toolDescription.getOwnedTools().add(newDoubleClickDescription);

            }
        });

        SWTBotEditor customSiriusEditorBot = bot.editorByTitle(VSM_FILE);
        customSiriusEditorBot.save();

        editor.show();
        editor.doubleClick("Sous-package1");

        ICondition doubleClickChangeCondition = new DoubleClickChangeCondition(editor);
        TestsUtil.waitUntil(doubleClickChangeCondition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession.save();
        localSession = null;
        sessionAirdResource = null;
        editingDomain = null;
        toolDescription = null;
        layer = null;
        super.tearDown();
    }
}
