/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.tests.swtbot.celleditor;

import java.io.ByteArrayInputStream;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.ui.tools.internal.properties.SiriusCellEditorProviderCollector;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test case to check properties CellEditor extensibility. See https://bugs.eclipse.org/bugs/show_bug.cgi?id=451364
 * 
 * @author Florian Barbin
 */
public class CellEditorExtensionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/cellEditor/";

    private static final String SEMANTIC_MODEL = "cellEditor.ecore";

    private static final String REPRESENTATION_MODEL = "cellEditor.aird";

    private static final String MODELER = "cellEditor.odesign";

    private static final String REPRESENTATION_DESCRIPTION_DIAGRAM = "Diagram";

    private static final String REPRESENTATION_DESCRIPTION_TREE = "Tree";

    private static final String REPRESENTATION_DESCRIPTION_TABLE = "Table";

    private static final String PROPERTIES = "Properties";

    private SWTBotEditor table;

    private SWTBotEditor tree;

    private static final String PLUGIN_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><?eclipse version=\"3.4\"?><plugin><extension  id=\"customCellEditor\" point=\""
            + SiriusCellEditorProviderCollector.EXTENSION_POINT_ID + "\"> <provider class=\"org.eclipse.sirius.tests.swtbot.celleditor.CustomCellEditor\"> </provider>  </extension></plugin>";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL, REPRESENTATION_MODEL, MODELER);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATION_MODEL);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        registerExtension();
    }

    /**
     * Tests the semantic properties with diagram editor.
     */
    public void testCellEditorOnDiagram() {
        if (!TestsUtil.isGTK3supported()) {
            return;
        }

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_DIAGRAM, "new " + REPRESENTATION_DESCRIPTION_DIAGRAM, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
        SWTBotGefEditPart botGefEditPart = editor.getEditPart("NewEClass1", AbstractDiagramContainerEditPart.class);
        botGefEditPart.click();
        SWTBotUtils.waitAllUiEvents();
        checkCellEditorValue();
    }

    /**
     * Tests the semantic properties with tree editor.
     */
    public void testCellEditorOnTree() {
        if (!TestsUtil.isGTK3supported()) {
            return;
        }

        tree = openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_TREE, "new " + REPRESENTATION_DESCRIPTION_TREE, DTree.class);
        SWTBotUtils.waitAllUiEvents();
        SWTBot swtBot = tree.bot();
        swtBot.tree().getTreeItem("NewEClass1").select();
        SWTBotUtils.waitAllUiEvents();
        checkCellEditorValue();
    }

    /**
     * Tests the semantic properties with table editor.
     */
    public void testCellEditorOnTable() {
        if (!TestsUtil.isGTK3supported()) {
            return;
        }

        table = openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_TABLE, "new " + REPRESENTATION_DESCRIPTION_TABLE, DTable.class);
        SWTBotUtils.waitAllUiEvents();
        SWTBot swtBot = table.bot();
        swtBot.tree().getTreeItem("NewEClass1").select();
        SWTBotUtils.waitAllUiEvents();
        checkCellEditorValue();
    }

    private void checkCellEditorValue() {
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Semantic");
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode("NewEClass1").select().getNode("Name").click();
        SWTBotText text = propertiesBot.bot().text();
        String result = text.getText();
        assertEquals("The class name field should be customized", "NewEClass1custom", result);
    }

    /**
     * Installs dynamically an extension that adds "custom" at the end of the value of the "name" field CellEditor.
     */
    private void registerExtension() {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        IContributor contributor = ContributorFactoryOSGi.createContributor(Activator.getDefault().getBundle());
        extensionRegistry.addContribution(new ByteArrayInputStream(PLUGIN_XML.getBytes()), contributor, false, null, null, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
    }

    /**
     * Remove the installed extension.
     */
    private void removeExtension() {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        IExtension extension = extensionRegistry.getExtension(SiriusCellEditorProviderCollector.EXTENSION_POINT_ID, Activator.PLUGIN_ID + ".customCellEditor");
        extensionRegistry.removeExtension(extension, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
        // Also force a clean of the internal cache of
        // SiriusCellEditorProviderCollector
        ReflectionHelper.setFieldValueWithoutException(SiriusCellEditorProviderCollector.getInstance(), "cache", null);
    }

    @Override
    protected void tearDown() throws Exception {
        tree = null;
        table = null;
        removeExtension();
        super.tearDown();
    }

}
