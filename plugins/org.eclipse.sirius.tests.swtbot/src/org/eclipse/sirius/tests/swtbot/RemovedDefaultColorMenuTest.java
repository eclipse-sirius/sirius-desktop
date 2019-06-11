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
package org.eclipse.sirius.tests.swtbot;

import java.util.Collections;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;

/**
 * Test the default color menu was removed from Contextual menu, property view appearance and toolbar. Test for ticket
 * vp-1701.
 * 
 * @author jdupont
 */
public class RemovedDefaultColorMenuTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc1701.ecore";

    private static final String SESSION_FILE = "tc1701.aird";

    private static final String VSM_FILE = "ecore.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/defaultColorMenuRemoved/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "1701 package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String REF = "[0..1] newEReference1";

    private static final String NODE_CONTAINER = "myEClass2";

    private static final String NODE_CONTAINER2 = "MyEclassPackage";

    private static final String NODE = "new AttributeNode";

    private static final String NODE2 = "new AttributeNode2";

    private static final String NODE3 = "new Attribute1";

    private static final String NODE4 = "new AttributeBorderedNode";

    private static final String SUPER = "";

    private static final String FILL_COLOR = "Fill &Color";

    private static final String FONT_COLOR = "Font Color";

    private static final String LINE_COLOR = "Li&ne Color";

    private static final String DEFAULT_COLOR = "Default Color";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private final long oldTimeout = SWTBotPreferences.TIMEOUT;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

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
        /*
         * Force the addition of a dependency to the sample ecore editor otherwise the interpreter has no way to
         * retrieve the service class hence any call to "render()" will fail.
         */
        localSession.getOpenedSession().getInterpreter().setProperty(IInterpreter.FILES, Collections.singleton("/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign"));

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test no default color menu in contextual menu from a container mapping. Test no default color menu from fill
     * color, font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromContainerMapping() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container 2 mapping. Test no default color menu from fill
     * color, font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromContainer2Mapping() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 3 mapping. Test no default color menu from fill color,
     * font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromNode3Mapping() {
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 2 mapping. Test no default color menu from fill color,
     * font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromNode2Mapping() {
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a node mapping. Test no default color menu from fill color,
     * font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromNodeMapping() {
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a relation base edge mapping. Test no default color menu from
     * fill color, font color and line color.
     */
    public void testNoDefaulColortMenuInContextualMenuRelationBaseEdgeMapping() {
        selectAndCheckEditPart(SUPER, DEdgeEditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a element base edge mapping. Test no default color menu from
     * fill color, font color et line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromElementBaseEdgeMapping() {
        selectAndCheckEditPart(REF, DEdgeEditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a bordered node mapping. Test no default color menu from fill
     * color, font color et line color.
     */
    public void testNoDefaulColortMenuInContextualMenuFromBorderedNodeMapping() {
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        try {
            editor.clickContextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainerMappingFontColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotToolbarDropDownButton fontColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fontColor.click();
        try {
            fontColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainerMappingLineColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotToolbarDropDownButton lineColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        lineColor.click();
        try {
            lineColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainerMappingFillColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container 2 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainer2MappingFillColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container 2 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainer2MappingFontColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a container 2 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInToolBarFromContainer2MappingLineColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 3 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode3MappingFontColor() {
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotToolbarDropDownButton fontColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fontColor.click();
        try {
            fontColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 3 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode3MappingLineColor() {
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotToolbarDropDownButton lineColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        lineColor.click();
        try {
            lineColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 3 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode3MappingFillColor() {
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromNodeMappingFillColor() {
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromNodeMappingFontColor() {
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInToolBarFromNodeMappingLineColor() {
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 2 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode2MappingFontColor() {
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 2 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode2MappingFillColor() {
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 2 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromNode2MappingLineColor() {
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a relation base edge mapping. Test no default color menu from line
     * color.
     */
    public void testNoDefaulColortMenuInToolBarFromRelationBaseEdgeMappingFontColor() {
        selectAndCheckEditPart(REF, DEdgeEditPart.class);
        SWTBotToolbarDropDownButton fontColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        fontColor.click();
        try {
            fontColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a relation base edge mapping. Test no default color menu from font
     * color.
     */
    public void testNoDefaulColortMenuInToolBarFromRelationBaseEdgeMappingLineColor() {
        selectAndCheckEditPart(REF, DEdgeEditPart.class);
        editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR).click();
        try {
            ((SWTBotToolbarDropDownButton) editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR).click()).menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 4 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInToolBarFromBorderedNodeMappingFontColor() {
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FONT_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 4 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInToolBarFromBorderedNodeMappingFillColor() {
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(FILL_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in tool bar from a node 4 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInToolBarFromBorderedNodeMappingLineColor() {
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotToolbarDropDownButton fillColor = editor.bot().toolbarDropDownButtonWithTooltip(LINE_COLOR);
        fillColor.click();
        try {
            fillColor.menuItem(DEFAULT_COLOR).click();
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container mapping. Test no default color menu from font
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainerMappingFontColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            propertiesView.bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container mapping. Test no default color menu from line
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainerMappingLineColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container mapping. Test no default color menu from fill
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainerMappingFillColor() {
        selectAndCheckEditPart(NODE_CONTAINER, DNodeContainerEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container 2 mapping. Test no default color menu from font
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainer2MappingFontColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container 2 mapping. Test no default color menu from line
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainer2MappingLineColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        }
    }

    /**
     * Test no default color menu in contextual menu from a container 2 mapping. Test no default color menu from fill
     * color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromContainer2MappingFillColor() {
        selectAndCheckEditPart(NODE_CONTAINER2, DNodeContainer2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            propertiesView.bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNodeMappingFontColor() {
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNodeMappingLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNodeMappingFillColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE, DNodeEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 2 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode2MappingFontColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 2 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode2MappingLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 2 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode2MappingFillColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE2, DNode2EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 3 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode3MappingFontColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 3 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode3MappingLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 3 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode3MappingFillColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE3, DNode3EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 4 mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode4MappingFontColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 4 mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode4MappingLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a node 4 mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromNode4MappingFillColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(NODE4, DNode4EditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a Relation based edge mapping. Test no default color menu from
     * font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewRelationBaseEdgeMappingFontColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(REF, DEdgeEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a Relation based edge mapping. Test no default color menu from
     * line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewRelationBaseEdgeMappingLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        selectAndCheckEditPart(REF, DEdgeEditPart.class);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a Diagram mapping. Test no default color menu from font color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromDiagramFontColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        editor.setFocus();
        editor.click(1, 1);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 0).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a Diagram mapping. Test no default color menu from line color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromDiagramLineColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        editor.setFocus();
        editor.click(1, 1);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 1).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test no default color menu in contextual menu from a Diagram mapping. Test no default color menu from fill color.
     */
    public void testNoDefaulColortMenuInAppearancePropertyViewFromDiagramFillColor() {
        SWTBotPreferences.TIMEOUT = 1000;
        editor.setFocus();
        editor.click(1, 1);
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());
        try {
            bot.viewByTitle("Properties").bot().buttonInGroup("Fonts and Colors:", 2).click().contextMenu(DEFAULT_COLOR);
            fail();
        } catch (WidgetNotFoundException e) {
            // Expected exception, do nothing.
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();

        bot.waitUntil(new CheckSelectedCondition(editor, name));

        return botPart;
    }

}
