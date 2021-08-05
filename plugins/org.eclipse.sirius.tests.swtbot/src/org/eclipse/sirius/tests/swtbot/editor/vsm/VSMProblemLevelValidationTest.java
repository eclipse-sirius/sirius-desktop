/*******************************************************************************
 * Copyright (c) 2016, 2021 Obeo.
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
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemWithImageCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the error severity is correctly displayed in the editor (with decorator).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class VSMProblemLevelValidationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String ERROR_NODE = "Errors (1 item)";

    private static final String VSM = "validateVSMProblemSeverity.odesign";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/vsm/";

    private static final String errorMessage = MessageFormat.format(Messages.VSMElementNameValidConstraint_invalidNameErrorMsg, "\"Group > emptyName > diagTest > Default > \"");

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, VSM);
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
        SWTBotUtils.waitAllUiEvents();
        SWTBotTreeItem defaultNode = tree.getTreeItem(ODESIGN).getNode("Group").getNode("2475").getNode("Diagram").getNode("Default");
        final SWTBotTreeItem node1treeItem = defaultNode.getNode("node1").select();
        bot.waitUntil(new TreeItemWithImageCondition(node1treeItem, getNodeMappingWithInfoImage(), "An info must appear on overlay of the node mapping image."));
        final SWTBotTreeItem node2TreeItem = defaultNode.getNode("node2").select();
        bot.waitUntil(new TreeItemWithImageCondition(node2TreeItem, getNodeMappingWithWarningImage(), "A warning must appear on overlay of the node mapping image."));
        final SWTBotTreeItem node3TreeItem = defaultNode.getNode("node3").select();
        bot.waitUntil(new TreeItemWithImageCondition(node3TreeItem, getNodeMappingWithErrorImage(), "A red cross must appear on overlay of the node mapping image."));
    }

    /**
     * Return the image of the NodeMapping with an info decorator.
     * 
     * @return The image of the NodeMapping with an info decorator.
     */
    private Image getNodeMappingWithInfoImage() {
        List<Object> images = new ArrayList<Object>(2);
        images.add(ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.INSTANCE.getImage("full/obj16/NodeMapping")));
        images.add(SiriusEditPlugin.getPlugin().getImage(SiriusInterpreterErrorDecorator.INFO_OVERLAY_DESC));
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

    /**
     * Return the image of the NodeMapping with an warning decorator.
     * 
     * @return The image of the NodeMapping with an warning decorator.
     */
    private Image getNodeMappingWithWarningImage() {
        List<Object> images = new ArrayList<Object>(2);
        images.add(ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.INSTANCE.getImage("full/obj16/NodeMapping")));
        images.add(SiriusEditPlugin.getPlugin().getImage(SiriusInterpreterErrorDecorator.WARNING_OVERLAY_DESC));
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

    /**
     * Return the image of the NodeMapping with an error decorator.
     * 
     * @return The image of the NodeMapping with an error decorator.
     */
    private Image getNodeMappingWithErrorImage() {
        List<Object> images = new ArrayList<Object>(2);
        images.add(ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.INSTANCE.getImage("full/obj16/NodeMapping")));
        // The error is on the children (not on NodeMapping itself), so the icon
        // is not ERROR_OVERLAY_DESC.
        images.add(SiriusEditPlugin.getPlugin().getImage(SiriusInterpreterErrorDecorator.ERROR_OVERLAY_DESC_CHILDREN_ONLY));
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
