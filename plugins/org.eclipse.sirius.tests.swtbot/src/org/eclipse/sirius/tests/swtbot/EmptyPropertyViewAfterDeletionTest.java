/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.stream.Stream;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Assert;

/**
 * Test class that after semantic element deletion in REFRESH_AUTO mode to false, the Property view should not display
 * properties on the remaining orphan View.
 * 
 * @author edugueperoux
 */
public class EmptyPropertyViewAfterDeletionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/refresh/VP-1950/";

    private static final String REPRESENTATION_DEFINITION_NAME = "VP_1950_Diagram";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DEFINITION_NAME;

    private static final String SEMANTIC_RESOURCE_NAME = "VP-1950.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-1950.aird";

    private static final String MODELER_RESOURCE_NAME = "VP-1950.odesign";

    private SWTBotGefEditPart node1Bot;

    private SWTBotGefEditPart node2Bot;

    private SWTBotGefEditPart edgeBot;

    /**
     * This test must be done in AUTO_REFRESH mode to false.
     * 
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), true);

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DEFINITION_NAME, REPRESENTATION_NAME, DDiagram.class);
        initEditor();

        node1Bot = editor.getEditPart("Node1").parent();
        node2Bot = editor.getEditPart("Node2").parent();
        edgeBot = editor.getEditPart("node2").parent();
    }

    private void initEditor() {
        if (editor != null) {
            editor.setSnapToGrid(false);

            editor.setFocus();
        }
    }

    /**
     * Test class that after semantic node deletion in REFRESH_AUTO mode to false, the Property view should not display
     * properties on the remaining orphan View.
     */
    public void testEmptyPropertyViewAfterNodeDeletionTest() {
        // Eclipse 4.x setFocus
        editor.click(0, 0);

        ICondition checkSelected = new CheckSelectedCondition(editor, node1Bot.part());
        node1Bot.select();
        bot.waitUntil(checkSelected);
        deleteFromOutside((IDiagramElementEditPart) node1Bot.part());

        Assert.assertEquals(2, getRedCrossDecoratorNumbers());

        // DSemanticDecorator.target is not unsetted by the
        // DanglingRevRemovalTrigger, so the property section is no more
        // notified of a semantic change.
        // It is consistent with the other tab.
        node2Bot.select();
        node1Bot.select();

        // Checks that property view tabs content is empty, nothing is
        // displaying about the deleted element
        checkEmptyPropertyTabs();

        // Checks that change selection to another view with existing associated
        // semantic element show its properties in the property view
        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        edgeBot.select();
        checkEmptyPropertyTabs();

        // Undo
        undo();

        Assert.assertEquals(0, getRedCrossDecoratorNumbers());

        // Rechecks properties display on selection
        editor.show();
        node1Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        edgeBot.select();
        checkNotEmptyPropertyTabs();

        // Delete when VP-2014 will be resolved
        platformProblemsListener.setErrorCatchActive(false);
        // Redo
        redo();

        // Decomment when VP-2014 will be resolved
        // Assert.assertEquals(2, getRedCrossDecoratorNumbers());

        // Rechecks properties display on selection
        editor.show();
        node1Bot.select();
        checkEmptyPropertyTabs();

        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        edgeBot.select();
        checkEmptyPropertyTabs();

        // Decomment when VP-2014 will be resolved
        // Checks that not new Status has appeared in error log
        // Assert.assertEquals(nbStatusInErrorLogBefore,
        // getNbStatusInErrorLog());
    }

    /**
     * Test class that after semantic edge deletion in REFRESH_AUTO mode to false, the Property view should not display
     * properties on the remaining orphan View.
     */
    public void testEmptyPropertyViewAfterEdgeDeletionTest() {
        // Eclipse 4.x setFocus
        editor.click(0, 0);

        ICondition checkSelected = new CheckSelectedCondition(editor, edgeBot.part());
        edgeBot.select();
        bot.waitUntil(checkSelected);
        deleteFromOutside((IDiagramElementEditPart) edgeBot.part());

        Assert.assertEquals(1, getRedCrossDecoratorNumbers());

        // DSemanticDecorator.target is not unsetted by the
        // DanglingRevRemovalTrigger, so the property section is no more
        // notified of a semantic change.
        // It is consistent with the other tab.
        node1Bot.select();
        edgeBot.select();

        // Checks that property view tabs content is empty, nothing is
        // displaying about the deleted element
        checkEmptyPropertyTabs();

        // Checks that change selection to another view with existing associated
        // semantic element show its properties in the property view
        editor.show();
        node1Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        // Undo
        undo();

        Assert.assertEquals(0, getRedCrossDecoratorNumbers());

        // Rechecks properties display on selection
        editor.show();
        edgeBot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        node1Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        // Delete when VP-2014 will be resolved
        platformProblemsListener.setErrorCatchActive(false);

        // Redo
        redo();

        // Decomment when VP-2014 will be resolved
        // Assert.assertEquals(1, getRedCrossDecoratorNumbers());

        // Rechecks properties display on selection
        editor.show();
        edgeBot.select();
        checkEmptyPropertyTabs();

        editor.show();
        node1Bot.select();
        checkNotEmptyPropertyTabs();

        editor.show();
        node2Bot.select();
        checkNotEmptyPropertyTabs();

        // Decomment when VP-2014 will be resolved
        // Checks that not new Status has appeared in error log
        // Assert.assertEquals(nbStatusInErrorLogBefore,
        // getNbStatusInErrorLog());
    }

    /**
     * Specific delete, made outside of the editor to get the semantic decorators.
     */
    private void deleteFromOutside(IDiagramElementEditPart part) {
        EObject semElt = part.resolveTargetSemanticElement();
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(RemoveCommand.create(domain, semElt));

        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Get the numbers of Red cross decorators (used to indicate a delete semantic element of a figure in REFRESH_AUTO
     * mode to false).
     * 
     * @return the numbers of Red cross decorators
     */
    private int getRedCrossDecoratorNumbers() {
        int redCrossDecoratorNumbers = 0;
        EditPart rootEditPart = editor.rootEditPart().part();
        LayerManager layerManager = LayerManager.Helper.find(rootEditPart);
        IFigure decorationLayer = layerManager.getLayer(DiagramRootEditPart.DECORATION_PRINTABLE_LAYER);
        Stream<IDecoration> decorations = decorationLayer.getChildren().stream().filter(IDecoration.class::isInstance).map(IDecoration.class::cast);
        redCrossDecoratorNumbers += decorations.count();
        return redCrossDecoratorNumbers;
    }

    /**
     * Checks that on current diagram selection the Property view tabs are noy empty.
     */
    private void checkNotEmptyPropertyTabs() {
        SWTBotView propertyView = bot.viewByTitle("Properties");
        propertyView.setFocus();
        SWTBot propertyViewBot = propertyView.bot();

        SWTBotSiriusHelper.selectPropertyTabItem("Semantic", propertyViewBot);
        SWTBotTree swtBotTree = propertyViewBot.tree();
        Assert.assertNotSame(0, swtBotTree.getAllItems().length);

        SWTBotSiriusHelper.selectPropertyTabItem("Style", propertyViewBot);
        swtBotTree = propertyViewBot.tree();
        Assert.assertNotSame(0, swtBotTree.getAllItems().length);

        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertyViewBot);
        // Checks if the Appearance tab is empty, if not the focused widget is a
        // text widget otherwise it is a ScrolledComposite
        Assert.assertTrue(propertyViewBot.getFocusedWidget() instanceof Text);

    }

    /**
     * Checks that on current diagram selection the Property view tabs are empty.
     */
    private void checkEmptyPropertyTabs() {
        SWTBotView propertyView = bot.viewByTitle("Properties");
        propertyView.setFocus();
        SWTBot propertyViewBot = propertyView.bot();

        SWTBotSiriusHelper.selectPropertyTabItem("Semantic", propertyViewBot);
        SWTBotTree swtBotTree = propertyViewBot.tree();
        Assert.assertEquals(0, swtBotTree.getAllItems().length);

        SWTBotSiriusHelper.selectPropertyTabItem("Style", propertyViewBot);
        swtBotTree = propertyViewBot.tree();
        Assert.assertEquals(0, swtBotTree.getAllItems().length);

        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertyViewBot);
        // Checks if the Appearance tab is empty, i.e. if not the focused widget
        // is a
        // text widget otherwise it is a ScrolledComposite
        Assert.assertTrue(propertyViewBot.getFocusedWidget() instanceof ScrolledComposite);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        node1Bot = null;
        node2Bot = null;
        edgeBot = null;

        super.tearDown();
    }

}
