/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=458977 .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeRefreshWithF5ShortcutTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "ecore.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private DTree dTree;

    private DTreeItem firstDTreeItem;

    private DTreeItem secondDTreeItem;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        treeRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Tree", "new Tree");
        treeEditorBot = treeRepresentation.getEditor();
        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        dTree = (DTree) dTreeEditor.getRepresentation();
        firstDTreeItem = dTree.getOwnedTreeItems().get(0);
        secondDTreeItem = dTree.getOwnedTreeItems().get(1);
    }

    /**
     * Test tree global refresh with F5 shortcut.
     */
    public void testTreeGlobalRefreshWithF5Shortcut() {
        int nbSubTreeitemsOfFirstDTreeItem = firstDTreeItem.getOwnedTreeItems().size();
        int nbSubTreeitemsOfSecondDTreeItem = secondDTreeItem.getOwnedTreeItems().size();

        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        Command cmd = AddCommand.create(domain, firstDTreeItem.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);
        cmd = AddCommand.create(domain, secondDTreeItem.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);

        assertEquals(nbSubTreeitemsOfFirstDTreeItem, firstDTreeItem.getOwnedTreeItems().size());
        assertEquals(nbSubTreeitemsOfSecondDTreeItem, secondDTreeItem.getOwnedTreeItems().size());

        launchRefreshWithF5Shortcut(treeRepresentation.getTree());

        assertEquals(nbSubTreeitemsOfFirstDTreeItem + 1, firstDTreeItem.getOwnedTreeItems().size());
        assertEquals(nbSubTreeitemsOfSecondDTreeItem + 1, secondDTreeItem.getOwnedTreeItems().size());
    }

    /**
     * Test tree local refresh with F5 shortcut.
     */
    public void testTreeLocalRefreshWithF5Shortcut() {
        int nbSubTreeitemsOfFirstDTreeItem = firstDTreeItem.getOwnedTreeItems().size();
        int nbSubTreeitemsOfSecondDTreeItem = secondDTreeItem.getOwnedTreeItems().size();

        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        Command cmd = AddCommand.create(domain, firstDTreeItem.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);
        cmd = AddCommand.create(domain, secondDTreeItem.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);

        assertEquals(nbSubTreeitemsOfFirstDTreeItem, firstDTreeItem.getOwnedTreeItems().size());
        assertEquals(nbSubTreeitemsOfSecondDTreeItem, secondDTreeItem.getOwnedTreeItems().size());

        treeEditorBot.bot().tree().getAllItems()[0].select();
        launchRefreshWithF5Shortcut(treeRepresentation.getTree());

        assertEquals(nbSubTreeitemsOfFirstDTreeItem + 1, firstDTreeItem.getOwnedTreeItems().size());
        assertEquals(nbSubTreeitemsOfSecondDTreeItem, secondDTreeItem.getOwnedTreeItems().size());
    }

    private void launchRefreshWithF5Shortcut(final SWTBotTree swtBotTree) {
        ICondition condition = new OperationDoneCondition();
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.F5);
        bot.waitUntil(condition);
    }

    @Override
    protected void tearDown() throws Exception {

        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        dTree = null;
        firstDTreeItem = null;
        secondDTreeItem = null;

        super.tearDown();
    }

}
