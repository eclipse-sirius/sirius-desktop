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
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test a reconnect edge with change edge routing style. By default the routing style is Rectilinear Style Routing. Test
 * VP-2954 and VP-3047.
 * 
 * @author jdupont
 */
public class ReconnectEdgeWithChangedRoutingStyleTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME = "pkgA package entities";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/routing/change_routing_style/";

    private static final String FILE_DIR = "/";

    private static final String PROPERTIES = "Properties";

    private static final String STYLE = "Style";

    private static final String APPEARANCE = "Appearance";

    private static final String STYLES = "Styles:";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
        /*
         * Force the addition of a dependency to the sample ecore editor otherwise the interpreter has no way to
         * retrieve the service class hence any call to "render()" will fail.
         */
        localSession.getOpenedSession().getInterpreter().setProperty(IInterpreter.FILES, Collections.singleton("/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign"));
    }

    /**
     * Test reconnect edge with changed routing style
     * <ol>
     * <li>Step 1 : select edge [0..1] ref3-1 between EClass 3 and ECLass 2</li>
     * <li>Step 2 : Check that routing style is Tree</li>
     * <li>Step 3 : Change routing style to Straight from tab Style of Properties view</li>
     * <li>Step 4 : Reconnect edge to EClass 4 and check that routing style is always Straight</li>
     * <li>Step 5 : Reconnect edge to EClass 3 and check that routing style is always Straight</li>
     * </ol>
     */
    public void testReconnectEdgeWithChangingRoutingStyleFromStyleView() {
        // Retrieve location for container list named EClass 4
        Point location = editor.getLocation("EClass 4", AbstractDiagramListEditPart.class);
        Dimension dimension = editor.getDimension("EClass 4", AbstractDiagramListEditPart.class);

        // Retrieve edge "[0..1] ref3-1" point location.
        Point target = edgeTarget("EClass 3", "EClass 2");

        // Step 1 : select edge [0..1] ref3-1 between EClass 3 and ECLass 2
        editor.select(getConnectionEditPartList("EClass 3", "EClass 2").get(0));

        DEdge dedge = getDEdge("EClass 3", "EClass 2");

        // Step 2 : Check that routing style is Tree
        checkRoutingStyle(EdgeRouting.TREE_LITERAL, dedge);

        // Step 3 : Change routing style to Straight
        changeRoutingStyleFromTabStyleOfPropertiesView(EdgeRouting.STRAIGHT_LITERAL, dedge);

        // Step 4 : Reconnect edge to EClass 4 and check that routing style is
        // always Straight
        editor.drag(target, location.x + dimension.width / 2, location.y + dimension.height / 2);
        dedge = getDEdge("EClass 3", "EClass 4");
        checkRoutingStyle(EdgeRouting.STRAIGHT_LITERAL, dedge);

        // Step 5 : Reconnect edge to EClass 3 and check that routing style is
        // always Straight
        target = edgeTarget("EClass 3", "EClass 4");
        location = editor.getLocation("EClass 2", AbstractDiagramListEditPart.class);
        select("[0..1] ref3-1");
        editor.drag(target, location.x + dimension.width / 2, location.y + dimension.height / 2);
        dedge = getDEdge("EClass 3", "EClass 2");
        checkRoutingStyle(EdgeRouting.STRAIGHT_LITERAL, dedge);
    }

    /**
     * Test reconnect edge with changed routing style
     * <ol>
     * <li>Step 1 : select edge [0..1] ref3-1 between EClass 3 and ECLass 2</li>
     * <li>Step 2 : Check that routing style is Tree</li>
     * <li>Step 3 : Change routing style to Straight from tab Appearance of Properties view</li>
     * <li>Step 4 : Reconnect edge to EClass 4 and check that routing style is always Straight</li>
     * <li>Step 5 : Reconnect edge to EClass 3 and check that routing style is always Straight</li>
     * </ol>
     */
    public void testReconnectEdgeWithChangingRoutingStyleFromAppearanceView() {
        // Retrieve location for container list named EClass 4
        Point location = editor.getLocation("EClass 4", AbstractDiagramListEditPart.class);
        Dimension dimension = editor.getDimension("EClass 4", AbstractDiagramListEditPart.class);

        // Retrieve edge "[0..1] ref3-1" point location.
        Point target = edgeTarget("EClass 3", "EClass 2");

        // Step 1 : select edge [0..1] ref3-1 between EClass 3 and ECLass 2
        editor.select(getConnectionEditPartList("EClass 3", "EClass 2").get(0));

        DEdge dedge = getDEdge("EClass 3", "EClass 2");

        // Step 2 : Check that routing style is Tree
        checkRoutingStyle(EdgeRouting.TREE_LITERAL, dedge);

        // Step 3 : Change routing style to Straight
        changeRoutingStyleFromTabAppearanceOfPropertiesView(EdgeRouting.STRAIGHT_LITERAL, dedge);

        // Step 4 : Reconnect edge to EClass 4 and check that routing style is
        // always Straight
        editor.drag(target, location.x + dimension.width / 2, location.y + dimension.height / 2);
        dedge = getDEdge("EClass 3", "EClass 4");
        checkRoutingStyle(EdgeRouting.STRAIGHT_LITERAL, dedge);

        // Step 5 : Reconnect edge to EClass 3 and check that routing style is
        // always Straight
        target = edgeTarget("EClass 3", "EClass 4");
        location = editor.getLocation("EClass 2", AbstractDiagramListEditPart.class);
        select("[0..1] ref3-1");
        editor.drag(target, location.x + dimension.width / 2, location.y + dimension.height / 2);
        dedge = getDEdge("EClass 3", "EClass 2");
        checkRoutingStyle(EdgeRouting.STRAIGHT_LITERAL, dedge);
    }

    private void checkRoutingStyle(EdgeRouting routingStyle, DEdge dedge) {
        assertEquals("The rooting style is not : " + routingStyle.getLiteral(), routingStyle, ((EdgeStyle) dedge.getStyle()).getRoutingStyle());
        Edge edgeGMF = ((Edge) getConnectionEditPart(((DNodeList) dedge.getSourceNode()).getName(), ((DNodeList) dedge.getTargetNode()).getName()).getModel());
        // String routingStyleGMF = ((ConnectorStyleImpl)
        // edgeGMF.getStyles().get(0)).getRouting().getLiteral();
        String routingStyleGMF;
        if (((ConnectorStyle) edgeGMF.getStyles().get(0)).getRouting().equals(Routing.MANUAL_LITERAL)) {
            routingStyleGMF = "straight";
        } else {
            routingStyleGMF = ((ConnectorStyle) edgeGMF.getStyles().get(0)).getRouting().getLiteral().toLowerCase();
        }
        assertEquals("The GMF routing style is not the right", routingStyle.getLiteral(), routingStyleGMF);
    }

    private void checkRoutingStyleInAppearance() {
        assertEquals("The radio button oblique should be selected", true, bot.viewByTitle(PROPERTIES).bot().radioInGroup("Oblique", STYLES).isSelected());
    }

    private void changeRoutingStyleFromTabStyleOfPropertiesView(final EdgeRouting routingStyle, final DEdge dedge) {
        // accesses to property view
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotUtils.waitAllUiEvents();
        propertiesBot.setFocus();
        // accesses to tab Style
        SWTBotSiriusHelper.selectPropertyTabItem(STYLE, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        // select routing syle Straight in combo
        tree.expandNode("Misc").select().getNode("Routing Style").doubleClick();
        SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox();
        String routingStyleLitteral = routingStyle.getLiteral().substring(0, 1).toUpperCase().concat(routingStyle.getLiteral().substring(1, routingStyle.getLiteral().length()));
        comboBox.setSelection(routingStyleLitteral);
        // applied change with change focus
        tree.expandNode("Misc").click();
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE, propertiesBot.bot());

        checkRoutingStyleInAppearance();
        checkRoutingStyle(routingStyle, dedge);
    }

    private void changeRoutingStyleFromTabAppearanceOfPropertiesView(final EdgeRouting routingStyle, final DEdge dedge) {
        // accesses to property view
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotUtils.waitAllUiEvents();
        propertiesBot.setFocus();
        // accesses to tab Style
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE, propertiesBot.bot());
        assertEquals("The 'Tree' style should be selected", true, bot.viewByTitle(PROPERTIES).bot().radioInGroup("Tree", STYLES).isSelected());
        new WrappedSWTBotRadio(bot.viewByTitle(PROPERTIES).bot().radioInGroup("Oblique", STYLES)).click();
        // applied change with change focus
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE, propertiesBot.bot());
        checkRoutingStyleInAppearance();
        checkRoutingStyle(routingStyle, dedge);
    }

    private DEdge getDEdge(String sourceEditPartName, String targetEditPartName) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        return (DEdge) element;
    }

    private ConnectionEditPart getConnectionEditPart(String sourceEditPartName, String targetEditPartName) {
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, getConnectionEditPartList(sourceEditPartName, targetEditPartName));
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1,
                getConnectionEditPartList(sourceEditPartName, targetEditPartName).size());
        return getConnectionEditPartList(sourceEditPartName, targetEditPartName).get(0).part();
    }

    private Point edgeTarget(String sourceEditPartName, String targetEditPartName) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

        return pointList.getLastPoint();
    }

    private void select(String element) {
        editor.click(editor.getEditPart(element));
        editor.select(editor.getEditPart(element));
    }

    private List<SWTBotGefConnectionEditPart> getConnectionEditPartList(String sourceEditPart, String targetEditPart) {
        return editor.getConnectionEditPart(editor.getEditPart(sourceEditPart, AbstractDiagramListEditPart.class), editor.getEditPart(targetEditPart, AbstractDiagramListEditPart.class));
    }
}
