/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.ModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the "link with editor" feature works correctly in model explorer
 * view.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class LinkWithEditorFeatureWithModelExplorerViewTest extends AbstractSiriusSwtBotGefTestCase {

	private static final String NEW_PACKAGE1 = "newPackage1";

	private static final String NEW_PACKAGE2 = "newPackage2";

	private static final String SEMANTIC_RESOURCE_NAME = "VP-3832.ecore";

	private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3832.aird";

	private static final String VSM = "tree.odesign";

	private static final String PATH = "data/unit/VP-3832/";

	private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

	private static final String TABLE_DESCRIPTION_NAME = "Classes";

	private static final String REPRESENTATION_NAME = " package entities";

	private static final String TABLE_NAME = "Classes in  package";

	private static final String TREE_NAME = "Tree";

	private UIResource sessionAirdResource;

	private SWTBot modelExplorerViewBot;

	@Override
	protected void onSetUpBeforeClosingWelcomePage() throws Exception {
		copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, VSM);
	}

	@Override
	protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
		sessionAirdResource = new UIResource(designerProject, REPRESENTATIONS_RESOURCE_NAME);
		localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

		SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		SWTBotUtils.waitAllUiEvents();
		modelExplorerView.setFocus();
		modelExplorerViewBot = modelExplorerView.bot();
	}

	/**
	 * Ensure that the "link with editor" feature works correctly in model
	 * explorer view by checking if the opened element is the selected one in
	 * model explorer view.
	 */
	public void testLinkWithEditorFeatureWithModelExplorerView() {
		SWTBotTreeItem projectTreeItemBot = modelExplorerViewBot.tree().expandNode(getProjectName(), true);
		SWTBotTreeItem representationsResourceTreeItemBot = projectTreeItemBot.getNode(REPRESENTATIONS_RESOURCE_NAME);
		SWTBotTreeItem viewpointTreeItemBot = representationsResourceTreeItemBot.getNode(0)
				.getNode(EcoreModeler.DESIGN_VIEWPOINT_NAME);
		SWTBotTreeItem representationDescriptionTreeItemBot = viewpointTreeItemBot.getNode(0);
		SWTBotTreeItem representationTreeItemBot = representationDescriptionTreeItemBot.getNode(0);
		SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		boolean linkWithEditorInitialStatus = modelExplorerView.toolbarToggleButton("Link with Editor").isChecked();
		try {
			if (!linkWithEditorInitialStatus) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}
			openDiagram(REPRESENTATION_NAME);
			assertEquals("The opened representation should be selected in model explorer view",
					representationTreeItemBot.isSelected(), true);
		} finally {
			// Reset to previous environment
			if (linkWithEditorInitialStatus != modelExplorerView.toolbarToggleButton("Link with Editor").isChecked()) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}
		}
	}

	/**
	 * Tests that the link with editor (editor toward model explorer) works
	 * properly when selecting a diagram element.
	 */
	public void testLinkWithEditorWithDiagramElementSelection() {
		final SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		boolean linkWithEditorInitialStatus = modelExplorerView.toolbarToggleButton("Link with Editor").isChecked();
		try {
			if (!linkWithEditorInitialStatus) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}

			// We open the representation...
			openDiagram(REPRESENTATION_NAME);
			SWTBotUtils.waitAllUiEvents();

			// We next select the "newPackage1" package.
			editor.click(NEW_PACKAGE1);
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE1));

			// We next select the "newPackage2" package.
			editor.click(NEW_PACKAGE2);
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

			// we deactivate the link with editor
			modelExplorerView.toolbarToggleButton("Link with Editor").click();

			// We next select the "newPackage1" package.
			editor.click(NEW_PACKAGE1);
			SWTBotUtils.waitAllUiEvents();

			// The NewPackage2 should still be selected.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

		} finally {
			// Reset to previous environment
			if (linkWithEditorInitialStatus != modelExplorerView.toolbarToggleButton("Link with Editor").isChecked()) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}
		}
	}

	/**
	 * Tests that the link with editor (editor toward model explorer) is not
	 * activated when the action is not checked.
	 */
	public void testLinkWithEditorDeactivate() {
		final SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		boolean linkWithEditorChecked = modelExplorerView.toolbarToggleButton("Link with Editor").isChecked();
		assertFalse("The Link with Editor should be deactivated", linkWithEditorChecked);
		// We open the representation...
		openDiagram(REPRESENTATION_NAME);
		SWTBotUtils.waitAllUiEvents();

		// We next select the "newPackage1" package.
		editor.click(NEW_PACKAGE1);
		SWTBotUtils.waitAllUiEvents();

		assertTrue("The model explorer view selection should not be modified.", isSelectionEmplty(modelExplorerView));

		// We next select the "newPackage2" package.
		editor.click(NEW_PACKAGE2);
		SWTBotUtils.waitAllUiEvents();

		assertTrue("The model explorer view selection should not be modified.", isSelectionEmplty(modelExplorerView));
	}

	/**
	 * Tests that the link with editor (editor toward model explorer) works
	 * properly when selecting a table element.
	 */
	public void testLinkWithEditorWithTableElementSelection() {
		final SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		boolean linkWithEditorInitialStatus = modelExplorerView.toolbarToggleButton("Link with Editor").isChecked();
		try {
			if (!linkWithEditorInitialStatus) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}

			// We open the representation...
			SWTBotTree representation = openTable(TABLE_NAME);
			SWTBotUtils.waitAllUiEvents();

			// We next select the "newPackage1" package.
			representation.getTreeItem(NEW_PACKAGE1).select();
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE1));

			// We next select the "newPackage2" package.
			representation.getTreeItem(NEW_PACKAGE2).select();
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

			// we deactivate the link with editor
			modelExplorerView.toolbarToggleButton("Link with Editor").click();

			// We next select the "newPackage1" package.
			representation.getTreeItem(NEW_PACKAGE1).select();
			SWTBotUtils.waitAllUiEvents();

			// The NewPackage2 should still be selected.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

		} finally {
			if (linkWithEditorInitialStatus != modelExplorerView.toolbarToggleButton("Link with Editor").isChecked()) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}
		}
	}

	/**
	 * Tests that the link with editor (editor toward model explorer) works
	 * properly when selecting a tree element.
	 */
	public void testLinkWithEditorWithTreeElementSelection() {
		final SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
		boolean linkWithEditorInitialStatus = modelExplorerView.toolbarToggleButton("Link with Editor").isChecked();
		try {
			if (!linkWithEditorInitialStatus) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}

			// We open the representation...
			SWTBotTree representation = openTree(TREE_NAME);
			SWTBotUtils.waitAllUiEvents();

			// We next select the "newPackage1" package.
			representation.getTreeItem(NEW_PACKAGE1).select();
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE1));

			// We next select the "newPackage2" package.
			representation.getTreeItem(NEW_PACKAGE2).select();
			SWTBotUtils.waitAllUiEvents();

			// We wait until it's actually selected in the model explorer view.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

			// we deactivate the link with editor
			modelExplorerView.toolbarToggleButton("Link with Editor").click();

			// We next select the "newPackage1" package.
			representation.getTreeItem(NEW_PACKAGE1).select();
			SWTBotUtils.waitAllUiEvents();

			// The NewPackage2 should still be selected.
			bot.waitUntil(new ModelExplorerSelectionCondition(modelExplorerView, NEW_PACKAGE2));

		} finally {
			if (linkWithEditorInitialStatus != modelExplorerView.toolbarToggleButton("Link with Editor").isChecked()) {
				modelExplorerView.toolbarToggleButton("Link with Editor").click();
			}
		}
	}

	private void openDiagram(String representationName) {
		editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(),
				REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
	}

	private SWTBotTree openTable(String representationName) {
		SWTBotEditor localEditor = openRepresentation(localSession.getOpenedSession(), TABLE_DESCRIPTION_NAME,
				representationName, DTable.class);
		return localEditor.bot().tree();

	}

	private SWTBotTree openTree(String representationName) {
		SWTBotEditor localEditor = openRepresentation(localSession.getOpenedSession(), TREE_NAME, representationName,
				DTree.class);
		return localEditor.bot().tree();

	}

	private boolean isSelectionEmplty(SWTBotView modelExplorerView) {
		ModelExplorerView explorerView = (ModelExplorerView) modelExplorerView.getViewReference().getView(false);
		IStructuredSelection selection = (IStructuredSelection) explorerView.getCommonViewer().getSelection();
		return selection.isEmpty();

	}

	private class ModelExplorerSelectionCondition extends DefaultCondition {

		private SWTBotView modelExplorerView;
		private String name;

		public ModelExplorerSelectionCondition(SWTBotView modelExplorerView, String name) {
			this.modelExplorerView = modelExplorerView;
			this.name = name;
		}

		@Override
		public boolean test() throws Exception {
			ModelExplorerView explorerView = (ModelExplorerView) modelExplorerView.getViewReference().getView(false);
			IStructuredSelection selection = (IStructuredSelection) explorerView.getCommonViewer().getSelection();
			Object selectedElement = selection.getFirstElement();
			return (selectedElement instanceof ENamedElement)
					&& ((ENamedElement) selectedElement).getName().equals(this.name);
		}

		@Override
		public String getFailureMessage() {
			return "The " + this.name + " should be selected in the Model Explorer View";
		}
	}

}
