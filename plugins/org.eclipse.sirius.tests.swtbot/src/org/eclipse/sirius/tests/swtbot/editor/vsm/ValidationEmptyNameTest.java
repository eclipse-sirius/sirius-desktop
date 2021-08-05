/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.properties.validation.SiriusInterpreterErrorDecorator;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckTreeItemEnabled;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemWithImageCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the empty name validation error appears in the "problems" view.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class ValidationEmptyNameTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String ERROR_NODE = "Errors (1 item)";

    private static final String VSM = "emptyName.odesign";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/vsmValidation/emptyNameVariable/";

    private static final String errorMessage = MessageFormat.format(Messages.VSMElementNameValidConstraint_invalidNameErrorMsg, "\"Group > emptyName > diagTest > Default > \"");

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Test that the empty name validation error appears in the "problems" view when the name of a diagram node is not
     * filled.
     */
    public void testValidationErrorValue() {
        // Opened VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        bot.editorByTitle(VSM).setFocus();
        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotUtils.clickContextMenu(tree, "Validate");
        bot.button("OK").click();
        checkIconNodeInvalidate(odesignEditor, tree);
        assertTrue("Empty Name error does not appear in the problems view.", checkProblemValue());
    }

    /**
     * Check that the validation generate empty name error in the problem view.
     * 
     * @return Boolean
     */
    private Boolean checkProblemValue() {
        // accesses to problems view
        final SWTBotView problemViewBot = bot.viewByPartName("Problems");
        problemViewBot.setFocus();
        final SWTBotTree problemTree = problemViewBot.bot().tree();
        bot.waitUntil(new CheckTreeItemEnabled(problemTree.getTreeItem(ERROR_NODE)));
        SWTBotTreeItem item = problemTree.getTreeItem(ERROR_NODE).expand();
        for (String itemMessage : item.getNodes()) {
            if (errorMessage.equals(itemMessage)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check that a red cross appears on the icon Node Mapping to show problem validation.
     * 
     * @param odesignEditor
     *            the odesign editor
     * @param tree
     *            the tree validated
     */
    private void checkIconNodeInvalidate(final SWTBotVSMEditor odesignEditor, SWTBotTree tree) {
        final SWTBotTreeItem treeItem = tree.getTreeItem(ODESIGN).getNode("Group").getNode("emptyName").expandNode("diagTest", "Default", "Node").select();
        bot.waitUntil(new TreeItemWithImageCondition(treeItem, getNodeMappingWithErrorImage(), "A red cross must appear on overlay of the node mapping image."));
    }

    /**
     * Return the image of the NodeMapping with an error decorator.
     * 
     * @return The image of the NodeMapping with an error decorator.
     */
    private Image getNodeMappingWithErrorImage() {
        List<Object> images = new ArrayList<Object>(2);
        images.add(ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.INSTANCE.getImage("full/obj16/NodeMapping")));
        images.add(SiriusEditPlugin.getPlugin().getImage(SiriusInterpreterErrorDecorator.ERROR_OVERLAY_DESC));
        // The composed image is inspired from
        // ValidationDecoration.decorateSeverity(Image, Integer).
        ComposedImage ci = new ComposedImage(images) {
            @Override
            public List<Point> getDrawPoints(Size size) {
                List<Point> results = new ArrayList<Point>();
                results.add(new Point());
                Point overlay = new Point();
                overlay.x = 0;
                overlay.y = 7;
                results.add(overlay);
                return results;
            }
        };
        ImageDescriptor descriptor = new ComposedImageDescriptor(ci);
        return SiriusEditPlugin.getPlugin().getImage(descriptor);
    }
}
