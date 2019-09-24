/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeChildrenNumberCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemAvailableCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Iterators;

/**
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=428545 about tree .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CreatedDTreeItemsSelectionTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/selectionOnCreation/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "428545.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "428545.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "428545.ecore";

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private DTree dTree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        String sessionResourceFolder = "/";
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, sessionResourceFolder + SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        treeRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Tree", "new Tree");
        treeEditorBot = treeRepresentation.getEditor();
        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        dTree = (DTree) dTreeEditor.getRepresentation();
    }

    /**
     * Test selection of created {@link DTreeItem} from a semantic element addition.
     */
    public void testCreatedDTreeItemSelection() {
        SWTBotTree swtBotTree = treeEditorBot.bot().tree();
        int selectionCount = swtBotTree.selectionCount();
        assertEquals("Selection should be empty at the beginning", 0, selectionCount);
        assertEquals(2, swtBotTree.visibleRowCount());

        SWTBotTreeItem expandedBotTreeItem = swtBotTree.expandNode("new EClass 1");
        bot.waitUntil(new TreeItemExpanded(expandedBotTreeItem, expandedBotTreeItem.getText()));
        SWTBotUtils.waitAllUiEvents();

        selectionCount = swtBotTree.selectionCount();
        assertEquals("Selection should be empty at the beginning", 0, selectionCount);
        assertEquals(4, swtBotTree.visibleRowCount());

        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        EPackage ePackage = (EPackage) dTree.getTarget();
        EPackage subEPackage1 = EcoreFactory.eINSTANCE.createEPackage();
        subEPackage1.setName("subEPackage1");
        Command addEPackageCmd = AddCommand.create(domain, ePackage, EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, subEPackage1);
        domain.getCommandStack().execute(addEPackageCmd);
        SWTBotUtils.waitAllUiEvents();

        TestsUtil.waitUntil(new ICondition() {
            @Override
            public boolean test() throws Exception {
                return swtBotTree.selectionCount() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "After a EPackage creation we should have one DTreeItem selected";
            }
        });
        assertEquals(subEPackage1.getName(), swtBotTree.selection().get(0, 0));
        assertEquals(5, swtBotTree.visibleRowCount());

        EPackage subEPackage2 = EcoreFactory.eINSTANCE.createEPackage();
        subEPackage2.setName("subEPackage2");
        EPackage subEPackage21 = EcoreFactory.eINSTANCE.createEPackage();
        subEPackage21.setName("subEPackage21");
        EPackage subEPackage22 = EcoreFactory.eINSTANCE.createEPackage();
        subEPackage22.setName("subEPackage22");
        EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
        eClass1.setName("eClass1");
        EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
        eClass2.setName("eClass2");
        EClass eClass3 = EcoreFactory.eINSTANCE.createEClass();
        eClass3.setName("eClass3");
        subEPackage2.getESubpackages().add(subEPackage21);
        subEPackage2.getESubpackages().add(subEPackage22);
        subEPackage2.getEClassifiers().add(eClass1);
        subEPackage21.getEClassifiers().add(eClass2);
        subEPackage22.getEClassifiers().add(eClass3);
        addEPackageCmd = AddCommand.create(domain, ePackage, EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, subEPackage2);
        domain.getCommandStack().execute(addEPackageCmd);
        bot.waitUntil(new TreeItemAvailableCondition(swtBotTree, subEPackage2.getName(), true));
        SWTBotUtils.waitAllUiEvents();

        selectionCount = swtBotTree.selectionCount();
        assertEquals("After a EPackage creation containing contents we should have 1 DTreeItem selected", 1, selectionCount);
        assertEquals(subEPackage2.getName(), swtBotTree.selection().get(0, 0));
        assertEquals(Iterators.size(ePackage.eAllContents()), swtBotTree.visibleRowCount());

        // Test that undo of deletion do not selection
        Command removeRootCmd = RemoveCommand.create(domain, ePackage.eContents());
        domain.getCommandStack().execute(removeRootCmd);
        bot.waitUntil(new TreeChildrenNumberCondition(swtBotTree, 0));
        SWTBotUtils.waitAllUiEvents();

        selectionCount = swtBotTree.selectionCount();
        assertEquals("As tree is empty the selection count should be 0", 0, selectionCount);
        assertEquals(0, swtBotTree.visibleRowCount());

        undo(localSession.getOpenedSession());
        SWTBotUtils.waitAllUiEvents();

        selectionCount = swtBotTree.selectionCount();
        assertEquals("An deletion undo should trigger selection", 0, selectionCount);
        assertEquals(Iterators.size(ePackage.eAllContents()), swtBotTree.visibleRowCount());
    }

    @Override
    protected void tearDown() throws Exception {

        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        dTree = null;

        super.tearDown();
    }

}
