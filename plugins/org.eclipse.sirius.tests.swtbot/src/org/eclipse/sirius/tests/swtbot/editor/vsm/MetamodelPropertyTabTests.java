/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A SWTBot test for metamodels selection on a RepresentationDescription (See VP-2651).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class MetamodelPropertyTabTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/odesign/VP-2651/";

    private static final String MODELER_RESOURCE_NAME = "VP-2651.odesign";

    private static final String ECORE_RESOURCE_NAME = "EcoreResourceWithNullEPackageProperties.ecore";

    private static final String GROUP_NAME = "VP-2651";

    private static final String VIEWPOINT_NAME = "VP-2651_Viewpoint";

    private static final String TREE_DESCRIPTION_NAME = "VP-2651_TreeDescription";

    private static final String EDITION_TABLE_DESCRIPTION_NAME = "VP-2651_EditionTableDescription";

    private static final String CROSS_TABLE_DESCRIPTION_NAME = "VP-2651_CrossTableDescription";

    private static final String DIAGRAM_DESCRIPTION_NAME = "VP-2651_DiagramDescription";

    private static final String SEQUENCE_DIAGRAM_DESCRIPTION_NAME = "VP-2651_SequenceDiagramDescription";

    private static final String DIAGRAM_EXTENSION_DESCRIPTION_NAME = "VP-2651_DiagramExtensionDescription";

    private SWTBotEditor odesignEditorBot;

    private SWTBotTreeItem viewpointItemBot;

    private SWTBotView propertiesBot;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, getProjectName() + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + ECORE_RESOURCE_NAME, getProjectName() + "/" + ECORE_RESOURCE_NAME);

        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(MODELER_RESOURCE_NAME).doubleClick();

        odesignEditorBot = bot.activeEditor();
        odesignEditorBot.setFocus();
        viewpointItemBot = odesignEditorBot.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + MODELER_RESOURCE_NAME).expandNode(GROUP_NAME).expandNode(VIEWPOINT_NAME);

    }

    /**
     * Test the metamodel tab of the property view on the tree description.
     */
    public void testMetamodelsAddingFromRegistryOnTreeDescription() {
        testMetamodelsAddingFromRegistry(TREE_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the edition table description.
     */
    public void testMetamodelsAddingFromRegistryOnEditionTableDescription() {
        testMetamodelsAddingFromRegistry(EDITION_TABLE_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the cross table description.
     */
    public void testMetamodelsAddingFromRegistryOnCrossTableDescription() {
        testMetamodelsAddingFromRegistry(CROSS_TABLE_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the diagram description.
     */
    public void testMetamodelsAddingFromRegistryOnDiagramDescription() {
        testMetamodelsAddingFromRegistry(DIAGRAM_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the sequence diagram description.
     */
    public void testMetamodelsAddingFromRegistryOnSequenceDiagramDescription() {
        testMetamodelsAddingFromRegistry(SEQUENCE_DIAGRAM_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the diagram extension description.
     */
    public void testMetamodelsAddingFromRegistryOnDiagramExtensionDescription() {
        testMetamodelsAddingFromRegistry("Diagram Extension " + DIAGRAM_EXTENSION_DESCRIPTION_NAME);
    }

    public void testMetamodelsAddingFromRegistryWithAllRepresentation() {
        testMetamodelsAddingFromRegistry(TREE_DESCRIPTION_NAME);
        testMetamodelsAddingFromRegistry(EDITION_TABLE_DESCRIPTION_NAME);
        testMetamodelsAddingFromRegistry(CROSS_TABLE_DESCRIPTION_NAME);
        testMetamodelsAddingFromRegistry(DIAGRAM_DESCRIPTION_NAME);
        testMetamodelsAddingFromRegistry(SEQUENCE_DIAGRAM_DESCRIPTION_NAME);
        testMetamodelsAddingFromRegistry("Diagram Extension " + DIAGRAM_EXTENSION_DESCRIPTION_NAME);
    }

    /**
     * Test the metamodel tab of the property view on the specified selection.
     */
    private void testMetamodelsAddingFromRegistry(String representationDescriptionName) {
        viewpointItemBot.getNode(representationDescriptionName).select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Metamodels", propertiesBot.bot());

        SWTBotTable metamodelsTableBot = propertiesBot.bot().table();
        SWTBotButton addFromRegistryButtonBot = propertiesBot.bot().button("Add from registry");
        SWTBotButton removeButtonBot = propertiesBot.bot().button("Remove");

        assertEquals("The list of associated metamodels should be empty at the beginning", 0, metamodelsTableBot.rowCount());
        assertTrue("Like the metamodels list is empty the remove button should be disabled", !removeButtonBot.isEnabled());

        addFromRegistryButtonBot.click();

        SWTBot metamodelsSelectionFromRegistryBot = SWTBotSiriusHelper.getShellBot("Metamodel selection");
        metamodelsSelectionFromRegistryBot.text().setText("*sirius*");
        SWTBotTable metamodelsFromRegistryTableBot = metamodelsSelectionFromRegistryBot.table();

        int nbOfMetamodelsFromRegistry = metamodelsFromRegistryTableBot.rowCount();
        int[] selection = new int[nbOfMetamodelsFromRegistry];
        for (int i = 0; i < nbOfMetamodelsFromRegistry; i++) {
            selection[i] = i;
        }
        metamodelsFromRegistryTableBot.select(selection);
        metamodelsSelectionFromRegistryBot.waitUntil(new TableSelectionCondition(metamodelsFromRegistryTableBot, nbOfMetamodelsFromRegistry));

        metamodelsSelectionFromRegistryBot.button("OK").click();
        bot.waitUntil(Conditions.tableHasRows(metamodelsTableBot, nbOfMetamodelsFromRegistry));

        assertEquals("The number of added metamodels should be equals to the number of metamodels available in the registry", nbOfMetamodelsFromRegistry, metamodelsTableBot.rowCount());
        assertTrue("The odesign resource is changed then the editor should be dirty, ", odesignEditorBot.isDirty());
        assertTrue("After adding new metamodels, they should be selected, then the remove button should be enabled", removeButtonBot.isEnabled());

        // Checks that EPackage added from registry are registered as nsURI in
        // the odesign
        assertEPackageReferenceFromRegistry(representationDescriptionName);

        undo("Add");

        assertEquals("After a undo the metamodels list should be empty", 0, metamodelsTableBot.rowCount());
        assertTrue("After a undo, the remove button should be disabled", !removeButtonBot.isEnabled());

        redo("Add");

        assertEquals("The number of added metamodels should be equals to the number of metamodels available in the registry", nbOfMetamodelsFromRegistry, metamodelsTableBot.rowCount());
        assertTrue("After adding new metamodels, they should be selected, then the remove button should be enabled", removeButtonBot.isEnabled());

        removeButtonBot.click();

        assertEquals("After a undo the metamodels list should be empty", 0, metamodelsTableBot.rowCount());
        assertTrue("After a undo, the remove button should be disabled", !removeButtonBot.isEnabled());
    }

    /**
     * Assert that the added EPackages from registry are referenced using their nsURI in the odesign resource.
     */
    private void assertEPackageReferenceFromRegistry(String representationDescriptionName) {
        URI modelerResourceURI = URI.createPlatformResourceURI(getProjectName() + "/" + MODELER_RESOURCE_NAME, true);
        Resource modelerResource = new ResourceSetImpl().getResource(modelerResourceURI, true);
        EObject eObject = modelerResource.getContents().get(0);
        assertTrue(eObject instanceof Group);
        Group group = (Group) eObject;
        Viewpoint viewpoint = group.getOwnedViewpoints().get(0);
        List<EPackage> ePackages = getEPackages(viewpoint, representationDescriptionName);
        for (EPackage ePackage : ePackages) {
            assertFalse(
                    "The EPackage with the nsURI " + ePackage.getNsURI() + " has not been added using the EPackage from the registry (i.e. the nsURI is not used to reference it in the odesign xmi)",
                    ePackage.eResource().getURI().isPlatform());
        }
    }

    /**
     * Get the EPackages referenced by the {@link RepresentationDescription} or {@link DiagramExtensionDescription}.
     */
    private List<EPackage> getEPackages(Viewpoint viewpoint, String representationDescriptionName) {
        List<EPackage> ePackages = null;
        if (DIAGRAM_EXTENSION_DESCRIPTION_NAME.equals(representationDescriptionName)) {
            ePackages = viewpoint.getOwnedRepresentationExtensions().get(0).getMetamodel();
        } else {
            for (RepresentationDescription representationDescription : viewpoint.getOwnedRepresentations()) {
                if (TREE_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                    ePackages = representationDescription.getMetamodel();
                    break;
                } else if (EDITION_TABLE_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                    ePackages = representationDescription.getMetamodel();
                    break;
                } else if (CROSS_TABLE_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                    ePackages = representationDescription.getMetamodel();
                    break;
                } else if (DIAGRAM_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                    ePackages = representationDescription.getMetamodel();
                    break;
                } else if (SEQUENCE_DIAGRAM_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                    ePackages = representationDescription.getMetamodel();
                    break;
                }
            }
        }
        return ePackages;
    }

    /**
     * Test that adding a EPackage with its properties (name, nsURI) to null doesn't throws exceptions.
     */
    public void testMetamodelAddingFromEPackageWithNullProperties() {
        viewpointItemBot.getNode(TREE_DESCRIPTION_NAME).select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Metamodels", propertiesBot.bot());

        SWTBotTable metamodelsTableBot = propertiesBot.bot().table();
        int nbOfSelectedMetamodels = metamodelsTableBot.rowCount();
        SWTBotButton addFromWorkspaceButtonBot = propertiesBot.bot().button("Add from workspace");
        SWTBotButton removeButtonBot = propertiesBot.bot().button("Remove");

        addFromWorkspaceButtonBot.click();

        SWTBot metamodelsSelectionFromWorkspaceBot = SWTBotSiriusHelper.getShellBot("Ecore resource selection");
        SWTBotTree workspaceBrowserBot = metamodelsSelectionFromWorkspaceBot.tree();
        workspaceBrowserBot.setFocus();
        workspaceBrowserBot.expandNode(getProjectName());
        workspaceBrowserBot.getTreeItem(getProjectName()).getNode(ECORE_RESOURCE_NAME).select();
        metamodelsSelectionFromWorkspaceBot.button("OK").click();
        bot.waitUntil(Conditions.tableHasRows(metamodelsTableBot, nbOfSelectedMetamodels + 1));

        assertEquals("The table of selected metamodels should have a new added item", nbOfSelectedMetamodels + 1, metamodelsTableBot.rowCount());
        assertTrue("The odesign resource is changed then the editor should be dirty, ", odesignEditorBot.isDirty());
        assertTrue("After adding new metamodels, they should be selected, then the remove button should be enabled", removeButtonBot.isEnabled());

        undo("Add");

        assertEquals("After a undo the metamodels list should be empty", 0, metamodelsTableBot.rowCount());
        assertTrue("After a undo, the remove button should be disabled", !removeButtonBot.isEnabled());

        redo("Add");

        assertEquals("The table of selected metamodels should have a new added item", nbOfSelectedMetamodels + 1, metamodelsTableBot.rowCount());
        assertTrue("After adding new metamodels, they should be selected, then the remove button should be enabled", removeButtonBot.isEnabled());

        removeButtonBot.click();

        assertEquals("After a undo the metamodels list should be empty", 0, metamodelsTableBot.rowCount());
        assertTrue("After a undo, the remove button should be disabled", !removeButtonBot.isEnabled());
    }

    private class TableSelectionCondition extends DefaultCondition {

        private SWTBotTable swtBotTable;

        private int expectedSelectionCound;

        public TableSelectionCondition(SWTBotTable swtBotTable, int expectedSelectionCound) {
            this.swtBotTable = swtBotTable;
            this.expectedSelectionCound = expectedSelectionCound;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test() throws Exception {
            return expectedSelectionCound == swtBotTable.selectionCount();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getFailureMessage() {
            return "The selection count is not the expected one, expected (" + expectedSelectionCound + "), " + swtBotTable.selectionCount();
        }

    }

    @Override
    protected void tearDown() throws Exception {
        odesignEditorBot.close();
        odesignEditorBot = null;
        propertiesBot = null;
        viewpointItemBot = null;
        super.tearDown();
    }
}
