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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A SWTBot test for VSM editor customization property sections.
 * 
 * See VP-3511.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CustomizationPropertySectionsTests extends AbstractContentAssistTest {

    private static final String PATH = "/data/unit/refresh/StyleCustomizations/";

    private static final String MODELER_RESOURCE_NAME = "StyleCustomizations.odesign";

    private static final String GROUP_NAME = "StyleCustomizations";

    private static final String VIEWPOINT_NAME = "StyleCustomizations";

    private static final String DIAGRAM_DESCRIPTION_NAME = "DiagramDescription";

    private static final String DIAGRAM_DESCRIPTION_BIS_NAME = "DiagramDescriptionBis";

    private static final String OPTIONAL_LAYER_NAME = "NodeCustomizationlayer";

    private EAttributeCustomization eAttributeCustomization;

    private EReferenceCustomization eReferenceCustomization;

    private SWTBotEditor odesignEditorBot;

    private SWTBotTreeItem viewpointItemBot;

    private SWTBotView propertiesBot;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, getProjectName() + "/" + MODELER_RESOURCE_NAME);

        URI modelerResourceURI = URI.createPlatformResourceURI(getProjectName() + "/" + MODELER_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource modelerResource = resourceSet.getResource(modelerResourceURI, true);
        Group group = (Group) modelerResource.getContents().get(0);
        Viewpoint viewpoint = group.getOwnedViewpoints().get(0);
        DiagramDescription diagramDescription1 = (DiagramDescription) viewpoint.getOwnedRepresentations().get(0);
        AdditionalLayer optionalLayer = diagramDescription1.getAdditionalLayers().get(0);
        Customization customization = optionalLayer.getCustomization();
        VSMElementCustomization vsmElementCustomization1 = (VSMElementCustomization) customization.getVsmElementCustomizations().get(0);
        VSMElementCustomization vsmElementCustomization2 = (VSMElementCustomization) customization.getVsmElementCustomizations().get(1);
        eAttributeCustomization = (EAttributeCustomization) vsmElementCustomization1.getFeatureCustomizations().get(0);
        eReferenceCustomization = (EReferenceCustomization) vsmElementCustomization2.getFeatureCustomizations().get(0);

        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(MODELER_RESOURCE_NAME).doubleClick();

        odesignEditorBot = bot.activeEditor();
        odesignEditorBot.setFocus();
        viewpointItemBot = odesignEditorBot.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + MODELER_RESOURCE_NAME).expandNode(GROUP_NAME).expandNode(VIEWPOINT_NAME);
    }

    /**
     * Test appliedOn and attributeName sections.
     */
    public void testEAttributeCustomization() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        SWTBotTreeItem diagramDescItem = viewpointItemBot.getNode(DIAGRAM_DESCRIPTION_NAME).select().expand();
        SWTBotTreeItem optionalLayerItem = diagramDescItem.getNode(OPTIONAL_LAYER_NAME).select().expand();
        SWTBotTreeItem customizationItem = optionalLayerItem.getNode("Style Customizations").select().expand();
        SWTBotTreeItem vsmEltCustomizationItem1 = customizationItem.getNode("Style Customization").select().expand();
        vsmEltCustomizationItem1.getNode("Property Customization (by expression) labelSize").select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();

        // Test attributeName completion
        SWTBotText attributeNameText = propertiesBot.bot().text(1);
        attributeNameText.setFocus();
        Collection<String> proposedAttributeNames = getContentAssistProposal(attributeNameText, 0);

        // Check that all proposed attribute names exists on referenced style
        // description elements
        for (EObject styleDescElt : eAttributeCustomization.getAppliedOn()) {
            for (String proposedAttributeName : proposedAttributeNames) {
                EStructuralFeature eStructuralFeature = styleDescElt.eClass().getEStructuralFeature(proposedAttributeName);
                assertTrue("Style description element : " + styleDescElt + " doesn't have a EAttribute named " + proposedAttributeName, eStructuralFeature instanceof EAttribute);
            }
        }

        // Test attributeName partial completion
        Collection<String> proposedLabelAttributeNames = getContentAssistProposal(attributeNameText, 5);
        assertEquals("They should have only 4 common EAttributes common to appliedOn beginning with name : label", 4, proposedLabelAttributeNames.size());
        Iterator<String> iterator = proposedLabelAttributeNames.iterator();
        assertEquals(org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE.getName(), iterator.next());
        assertEquals(org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT.getName(), iterator.next());
        assertEquals(org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION.getName(), iterator.next());
        assertEquals(org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT.getName(), iterator.next());

        // Test that with labelSize attributeName we have only 3 style
        // description elements proposed and 3 selected
        SWTBotButton appliedOnButton = propertiesBot.bot().button(0);
        appliedOnButton.click();
        SWTBotShell appliedOnSelectorShell = bot.activeShell();
        SWTBot appliedOnSelectorShellBot = appliedOnSelectorShell.bot();
        SWTBotTable table1 = appliedOnSelectorShellBot.table(0);
        assertEquals("The left list of available elements should be of 3, for the begin/center/end edge style description", 3, table1.rowCount());
        SWTBotTable table2 = appliedOnSelectorShellBot.table(1);
        assertEquals("The right list of selected elements should be of 3", 3, table2.rowCount());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EPackageMapping > Gradient white to light_gray", table2.getTableItem(0).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > EAttributeMapping > Gauge", table2.getTableItem(1).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > Gradient white to light_gray", table2.getTableItem(2).getText());
        appliedOnSelectorShell.close();

        // Test that without attributeName we have 5 style description elements
        // proposed
        attributeNameText.setText("");
        appliedOnButton.setFocus();
        SWTBotUtils.waitAllUiEvents();
        appliedOnButton.click();
        appliedOnSelectorShell = bot.activeShell();
        appliedOnSelectorShellBot = appliedOnSelectorShell.bot();
        assertEquals("The left list of available elements should be of 5, i.e. all available style description elements", 5, appliedOnSelectorShell.bot().table(0).rowCount());
        table2 = appliedOnSelectorShellBot.table(1);
        assertEquals("The right list of selected elements should be of 3", 3, table2.rowCount());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EPackageMapping > Gradient white to light_gray", table2.getTableItem(0).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > EAttributeMapping > Gauge", table2.getTableItem(1).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > Gradient white to light_gray", table2.getTableItem(2).getText());
        appliedOnSelectorShell.close();
    }

    /**
     * Test appliedOn and referenceName sections.
     */
    public void testEReferenceCustomization() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        SWTBotTreeItem diagramDescItem = viewpointItemBot.getNode(DIAGRAM_DESCRIPTION_NAME).select().expand();
        SWTBotTreeItem optionalLayerItem = diagramDescItem.getNode(OPTIONAL_LAYER_NAME).select().expand();
        SWTBotTreeItem customizationItem = optionalLayerItem.getNode("Style Customizations").select().expand();
        SWTBotTreeItem vsmEltCustomizationItem2 = customizationItem.getNodes("Style Customization").get(1).select().expand();
        vsmEltCustomizationItem2.getNode("Property Customization (by selection) backgroundColor").select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();

        // Test referenceName completion
        SWTBotText referenceNameText = propertiesBot.bot().text(1);
        referenceNameText.setFocus();
        Collection<String> proposedReferenceNames = getContentAssistProposal(referenceNameText, 0);

        // Check that all proposed reference names exists on referenced style
        // description elements
        for (EObject styleDescElt : eReferenceCustomization.getAppliedOn()) {
            for (String proposedReferenceName : proposedReferenceNames) {
                EStructuralFeature eStructuralFeature = styleDescElt.eClass().getEStructuralFeature(proposedReferenceName);
                assertTrue("Style description element : " + styleDescElt + " doesn't have a EReference named " + proposedReferenceName, eStructuralFeature instanceof EReference);
            }
        }

        // Test referenceName partial completion
        Collection<String> proposedLabelReferenceNames = getContentAssistProposal(referenceNameText, 5);
        assertEquals("They should have only a backgroundColor proposal", 1, proposedLabelReferenceNames.size());
        Iterator<String> iterator = proposedLabelReferenceNames.iterator();
        assertEquals(StylePackage.Literals.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR.getName(), iterator.next());

        // Test that with backgroundColor referenceName we have only 3 style
        // description elements proposed and 3 selected
        SWTBotButton appliedOnButton = propertiesBot.bot().button(0);
        appliedOnButton.click();
        SWTBotShell appliedOnSelectorShell = bot.activeShell();
        SWTBot appliedOnSelectorShellBot = appliedOnSelectorShell.bot();
        assertEquals("The left list of available elements should be empty because we have already selected elements selectionnable", 0, appliedOnSelectorShellBot.table(0).rowCount());
        SWTBotTable table2 = appliedOnSelectorShellBot.table(1);
        assertEquals("The right list of selected elements should be of 3", 3, table2.rowCount());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > Gradient white to light_gray", table2.getTableItem(0).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EPackageMapping > Gradient white to light_gray", table2.getTableItem(1).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > EAttributeMapping > Gauge > Gauge Section black to green",
                table2.getTableItem(2).getText());
        appliedOnSelectorShell.close();

        // Test that without attributeName we have 5 style description elements
        // proposed
        referenceNameText.setText("");
        appliedOnButton.setFocus();
        SWTBotUtils.waitAllUiEvents();
        appliedOnButton.click();
        appliedOnSelectorShell = bot.activeShell();
        final SWTBot appliedOnSelectorShellBot2 = appliedOnSelectorShell.bot();
        appliedOnSelectorShellBot2.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return appliedOnSelectorShellBot2.table(0).rowCount() == 5;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The left list of available elements should be of 5, i.e. all available style description elements, but was " + appliedOnSelectorShellBot2.table(0).rowCount();
            }
        });
        table2 = appliedOnSelectorShellBot2.table(1);
        assertEquals("The right list of selected elements should be of 3", 3, table2.rowCount());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > Gradient white to light_gray", table2.getTableItem(0).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EPackageMapping > Gradient white to light_gray", table2.getTableItem(1).getText());
        assertEquals("StyleCustomizations > StyleCustomizations > DiagramDescription > Default > EClassMapping > EAttributeMapping > Gauge > Gauge Section black to green",
                table2.getTableItem(2).getText());
        appliedOnSelectorShell.close();
    }

    /**
     * Test VSMElementCustomizationReuse sections.
     */
    public void testVSMElementCustomizationReuseEReferenceCustomization() {
        SWTBotTreeItem diagramDescBisItem = viewpointItemBot.getNode(DIAGRAM_DESCRIPTION_BIS_NAME).select().expand();
        SWTBotTreeItem layerItem = diagramDescBisItem.getNode("Default").select().expand();
        SWTBotTreeItem customizationItem = layerItem.getNode("Style Customizations").select().expand();
        customizationItem.getNode("Style Customization Reuse").select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();

        // Test referenceName completion
        SWTBotButton reuseButton = propertiesBot.bot().button();
        reuseButton.setFocus();
        SWTBotUtils.waitAllUiEvents();
        reuseButton.click();

        SWTBotShell reuseSelectorShell = bot.activeShell();
        assertEquals("The left list of available elements should have only one style customization available because we have already selected the other one", 1,
                reuseSelectorShell.bot().table(0).rowCount());
        assertEquals("The right list of selected elements should be of 1", 1, reuseSelectorShell.bot().table(1).rowCount());
        reuseSelectorShell.close();

        SWTBotButton appliedOnButton = propertiesBot.bot().button(1);
        appliedOnButton.setFocus();
        SWTBotUtils.waitAllUiEvents();
        appliedOnButton.click();

        SWTBotShell appliedOnSelectorShell = bot.activeShell();
        appliedOnSelectorShell.bot().button("Remove").click();
        appliedOnSelectorShell.bot().button("OK").click();

        reuseButton = propertiesBot.bot().button();
        reuseButton.setFocus();
        SWTBotUtils.waitAllUiEvents();
        reuseButton.click();

        reuseSelectorShell = bot.activeShell();
        assertEquals("The left list of available elements should be of 2, the EReferenceCustomization and EAttributeCustomization should be available as we have not appliedOn selected now", 2,
                reuseSelectorShell.bot().table(0).rowCount());
        assertEquals("The right list of selected elements should be of 1", 1, reuseSelectorShell.bot().table(1).rowCount());
        reuseSelectorShell.close();

    }

    /**
     * Ensure that the order displayed in properties view of VSM file is (Left - Center - Right).
     */
    public void testLabelAlignmentInVSM() {
        SWTBotTreeItem diagramDescItem = viewpointItemBot.getNode(DIAGRAM_DESCRIPTION_NAME).select().expand();
        SWTBotTreeItem layerItem = diagramDescItem.getNode("Default").select().expand();
        SWTBotTreeItem mappingItem = layerItem.getNode("EClassMapping").select().expand();
        mappingItem.getNode("Gradient white to light_gray").select();

        propertiesBot = bot.viewByTitle("Properties");
        SWTBotSiriusHelper.selectPropertyTabItem("Label", propertiesBot.bot());
        SWTBotUtils.waitAllUiEvents();
        SWTBotRadio left = propertiesBot.bot().radio(0);
        SWTBotRadio center = propertiesBot.bot().radio(1);
        SWTBotRadio right = propertiesBot.bot().radio(2);
        assertEquals("The first label alignment should be the left", "Left", left.getText());
        assertEquals("The second label alignment should be the center", "Center", center.getText());
        assertEquals("The third label alignment should be the right", "Right", right.getText());
    }

    @Override
    protected void tearDown() throws Exception {
        odesignEditorBot.close();
        eAttributeCustomization = null;
        eReferenceCustomization = null;
        odesignEditorBot = null;
        propertiesBot = null;
        viewpointItemBot = null;
        super.tearDown();
    }
}
