/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FigureQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckBoundsCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Assert;

/**
 * Tests on different EditPart that label containing '\n' display text on multilines.
 * 
 * @author edugueperoux
 */
public class MultiLineLabelDiagramTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/multiLines/";

    private static final String SEMANTIC_RESOURCE_NAME = "multiLines.ecore";

    private static final String SESSION_RESOURCE_NAME = "multiLines.aird";

    private static final String MODELER_RESOURCE_NAME = "multiLines.odesign";

    private static final String VP_1810_REPRESENTATION_NAME = "Ticket VP-1810 EPackage";

    private static final String VP_1810_REPRESENTATION_INSTANCE_NAME = "new Ticket VP-1810 EPackage";

    private static final String VP_2211_REPRESENTATION_NAME = "VP-2211";

    private static final String VP_2211_REPRESENTATION_INSTANCE_NAME = "new VP-2211";

    private static final String VP_3382_REPRESENTATION_NAME = "VP-3382";

    private static final String VP_3382_REPRESENTATION_INSTANCE_NAME = "new VP-3382";

    private static final String TRUNCATED_LABEL_PREFIX = "Truncated ";

    private static final String AT_OPENING_LABEL_SUFFIX = " At opening";

    private static final Point TRANSLATION_MINUS_70_X = new Point(-70, 0);

    private SWTBotGefEditPart dDiagramEditPartBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Open the expected diagram for this use case.
     */
    protected void openAndInitialiseForVP1810() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), VP_1810_REPRESENTATION_NAME, VP_1810_REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        SWTBotGefEditPart rootEditPartBot = editor.rootEditPart();
        dDiagramEditPartBot = rootEditPartBot.children().get(0);
    }

    /**
     * Test that Label on Node with text containing '\n' are correctly displayed on representation opening.
     * 
     * DNodeEditPart with label position : node
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNode() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNodeEditPart.class, false);
    }

    /**
     * Test that Label on Node with text containing '\n' are correctly displayed on representation opening.
     * 
     * DNodeEditPart with label position : label
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeBorder() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNodeEditPart.class, true);
    }

    /**
     * Test that Label of a node border of an other node with text containing '\n' are correctly displayed on
     * representation opening.
     * 
     * DNode2EditPart with label position : node
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeInContainer2() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode2EditPart.class, false);
    }

    /**
     * Test that Label of a node border of an other node with text containing '\n' are correctly displayed on
     * representation opening.
     * 
     * DNode2EditPart with label position : label
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeBorderInContainer2() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode2EditPart.class, true);
    }

    /**
     * Test that Label of a node in container with text containing '\n' are correctly displayed on representation
     * opening.
     * 
     * DNode3EditPart with label position : node
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeInContainer3() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode3EditPart.class, false);
    }

    /**
     * Test that Label of a node in container with text containing '\n' are correctly displayed on representation
     * opening.
     * 
     * DNode3EditPart with label position : label
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeBorderInContainer3() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode3EditPart.class, true);
    }

    /**
     * Test that Label of a border node of a container with text containing '\n' are correctly displayed on
     * representation opening.
     * 
     * DNode4EditPart with label position : node
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeInContainer4() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode4EditPart.class, false);
    }

    /**
     * Test that Label of a border node of a container with text containing '\n' are correctly displayed on
     * representation opening.
     * 
     * DNode4EditPart with label position : label
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeBorderInContainer4() {
        doTest_Label_Display_Multiple_Lines_OnNode(DNode4EditPart.class, true);
    }

    private void doTest_Label_Display_Multiple_Lines_OnNode(Class<? extends IAbstractDiagramNodeEditPart> dNodeType, boolean labelOnBorder) {
        openAndInitialiseForVP1810();

        assertTrue("The method is intended to tests nodes and border nodes only.",
                IDiagramBorderNodeEditPart.class.isAssignableFrom(dNodeType) || IDiagramNodeEditPart.class.isAssignableFrom(dNodeType));

        IFigure nameFigure = null;
        for (SWTBotGefEditPart sfep : dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(dNodeType))) {
            List<SWTBotGefEditPart> nameParts = sfep.descendants(IsInstanceOf.instanceOf(DNodeNameEditPart.class));
            if (labelOnBorder && !nameParts.isEmpty()) {
                DNodeNameEditPart dNodeNameEditPart = ((DNodeNameEditPart) nameParts.get(0).part());
                nameFigure = dNodeNameEditPart.getFigure();
            } else if (!labelOnBorder && nameParts.isEmpty()) {
                IAbstractDiagramNodeEditPart dNodeEditPart = (IAbstractDiagramNodeEditPart) sfep.part();
                if (dNodeEditPart instanceof IDiagramNodeEditPart) {
                    nameFigure = ((IDiagramNodeEditPart) dNodeEditPart).getNodeLabel();
                } else if (dNodeEditPart instanceof IDiagramBorderNodeEditPart) {
                    nameFigure = ((IDiagramBorderNodeEditPart) dNodeEditPart).getNodeLabel();
                }
            }

            if (nameFigure != null) {
                editor.select(sfep);
                break;
            }
        }

        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(nameFigure);
    }

    private void do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(IFigure nameFigure) {
        assertLabelMultiLines(nameFigure, 3);

        editor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Test that Label on ListContainer with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnListContainer() {
        openAndInitialiseForVP1810();

        SWTBotGefEditPart dNodeListEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListEditPart.class)).get(0);
        SWTBotGefEditPart dNodeListNameEditPartBot = dNodeListEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListNameEditPart.class)).get(0);

        DNodeListNameEditPart dNodeListNameEditPart = (DNodeListNameEditPart) dNodeListNameEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dNodeListNameEditPart.getFigure());
    }

    /**
     * Test that Label on ListElement with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnNodeListElement() {
        openAndInitialiseForVP1810();

        SWTBotGefEditPart dNodeListElementEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListElementEditPart.class)).get(0);

        DNodeListElementEditPart dNodeListElementEditPart = (DNodeListElementEditPart) dNodeListElementEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dNodeListElementEditPart.getFigure());
    }

    /**
     * Test that Label on Container with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnContainer() {
        openAndInitialiseForVP1810();

        SWTBotGefEditPart dNodeContainerEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerEditPart.class)).get(0);
        SWTBotGefEditPart dNodeContainerNameEditPartBot = dNodeContainerEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerNameEditPart.class)).get(0);

        DNodeContainerNameEditPart dNodeContainerNameEditPart = (DNodeContainerNameEditPart) dNodeContainerNameEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dNodeContainerNameEditPart.getFigure());
    }

    /**
     * Test that Label on Container in another container with text containing '\n' are correctly displayed on
     * representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnContainerInContainer() {
        openAndInitialiseForVP1810();

        SWTBotGefEditPart dNodeContainer2EditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainer2EditPart.class)).get(0);
        SWTBotGefEditPart dNodeContainerName2EditPartBot = dNodeContainer2EditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerName2EditPart.class)).get(0);

        DNodeContainerName2EditPart dNodeContainerName2EditPart = (DNodeContainerName2EditPart) dNodeContainerName2EditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dNodeContainerName2EditPart.getFigure());
    }

    /**
     * Test that Label on List in another container with text containing '\n' are correctly displayed on representation
     * opening.
     */
    public void test_Label_Display_Multiple_Lines_With_ExistingLabel_OnListInContainer() {
        openAndInitialiseForVP1810();

        SWTBotGefEditPart dNodeList2EditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeList2EditPart.class)).get(0);
        SWTBotGefEditPart dNodeListName2EditPartBot = dNodeList2EditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListName2EditPart.class)).get(0);

        DNodeListName2EditPart dNodeListName2EditPart = (DNodeListName2EditPart) dNodeListName2EditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dNodeListName2EditPart.getFigure());
    }

    /**
     * Test that begin Label on edge with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_Existing_Begin_Label_OnEdge() {
        openAndInitialiseForVP1810();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeBeginNameEditPart.class)).get(0);

        DEdgeBeginNameEditPart dEdgeNameEditPart = (DEdgeBeginNameEditPart) dEdgeNameEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dEdgeNameEditPart.getFigure());
    }

    /**
     * Test that center Label on edge with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_Existing_Center_Label_OnEdge() {
        openAndInitialiseForVP1810();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeNameEditPart.class)).get(0);

        DEdgeNameEditPart dEdgeNameEditPart = (DEdgeNameEditPart) dEdgeNameEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dEdgeNameEditPart.getFigure());
    }

    /**
     * Test that end Label on edge with text containing '\n' are correctly displayed on representation opening.
     */
    public void test_Label_Display_Multiple_Lines_With_Existing_End_Label_OnEdge() {
        openAndInitialiseForVP1810();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeEndNameEditPart.class)).get(0);

        DEdgeEndNameEditPart dEdgeNameEditPart = (DEdgeEndNameEditPart) dEdgeNameEditPartBot.part();
        do_basic_Label_Display_Multiple_Lines_With_ExistingLabel(dEdgeNameEditPart.getFigure());
    }

    /**
     * Open the expected diagram for this use case.
     */
    protected void openAndInitialiseForVP3382() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), VP_3382_REPRESENTATION_NAME, VP_3382_REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        SWTBotGefEditPart rootEditPartBot = editor.rootEditPart();
        dDiagramEditPartBot = rootEditPartBot.children().get(0);
    }

    /**
     * Test that Label on Node with text containing '\n' are correctly displayed after direct edit.
     * 
     * DNodeEditPart with label position : node
     */
    public void test_DirectEditSimpleToMultiline_OnNode() {
        do_test_DirectEditSimpleToMultiline_Node(DNodeEditPart.class, false);
    }

    /**
     * Test that Label on Node with text containing '\n' are correctly displayed after direct edit.
     * 
     * DNodeEditPart with label position : label
     */
    public void test_DirectEditSimpleToMultiline_OnNodeBorder() {
        do_test_DirectEditSimpleToMultiline_Node(DNodeEditPart.class, true);
    }

    /**
     * Test that Label of a node border of an other node with text containing '\n' are correctly displayed after direct
     * edit.
     * 
     * DNode2EditPart with label position : node
     */
    public void test_DirectEditSimpleToMultiline_OnNodeInContainer2() {
        do_test_DirectEditSimpleToMultiline_Node(DNode2EditPart.class, false);
    }

    /**
     * Test that Label of a node border of an other node with text containing '\n' are correctly displayed after direct
     * edit.
     * 
     * DNode2EditPart with label position : label
     */
    public void test_DirectEditSimpleToMultiline_OnNodeBorderInContainer2() {
        do_test_DirectEditSimpleToMultiline_Node(DNode2EditPart.class, true);
    }

    /**
     * Test that Label of a node in container with text containing '\n' are correctly displayed after direct edit.
     * 
     * DNode3EditPart with label position : node
     */
    public void test_DirectEditSimpleToMultiline_OnNodeInContainer3() {
        do_test_DirectEditSimpleToMultiline_Node(DNode3EditPart.class, false);
    }

    /**
     * Test that Label of a node in container with text containing '\n' are correctly displayed after direct edit.
     * 
     * DNode3EditPart with label position : label
     */
    public void test_DirectEditSimpleToMultiline_OnNodeBorderInContainer3() {
        do_test_DirectEditSimpleToMultiline_Node(DNode3EditPart.class, true);
    }

    /**
     * Test that Label of a border node of a container with text containing '\n' are correctly displayed after direct
     * edit.
     * 
     * DNode4EditPart with label position : node
     */
    public void test_DirectEditSimpleToMultiline_OnNodeInContainer4() {
        do_test_DirectEditSimpleToMultiline_Node(DNode4EditPart.class, false);
    }

    /**
     * Test that Label of a border node of a container with text containing '\n' are correctly displayed after direct
     * edit.
     * 
     * DNode4EditPart with label position : label
     */
    public void test_DirectEditSimpleToMultiline_OnNodeBorderInContainer4() {
        do_test_DirectEditSimpleToMultiline_Node(DNode4EditPart.class, true);
    }

    private void do_test_DirectEditSimpleToMultiline_Node(Class<? extends IAbstractDiagramNodeEditPart> dNodeType, boolean labelOnBorder) {
        openAndInitialiseForVP3382();

        assertTrue("The method is intended to tests nodes and border nodes only.",
                IDiagramBorderNodeEditPart.class.isAssignableFrom(dNodeType) || IDiagramNodeEditPart.class.isAssignableFrom(dNodeType));

        SWTBotGefEditPart partToEdit = null;
        IFigure nameFigure = null;
        for (SWTBotGefEditPart sfep : dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(dNodeType))) {
            List<SWTBotGefEditPart> nameParts = sfep.descendants(IsInstanceOf.instanceOf(DNodeNameEditPart.class));
            if (labelOnBorder && !nameParts.isEmpty()) {
                DNodeNameEditPart dNodeNameEditPart = ((DNodeNameEditPart) nameParts.get(0).part());
                nameFigure = dNodeNameEditPart.getFigure();
                partToEdit = nameParts.get(0);
            } else if (!labelOnBorder && nameParts.isEmpty()) {
                IAbstractDiagramNodeEditPart dNodeEditPart = (IAbstractDiagramNodeEditPart) sfep.part();
                if (dNodeEditPart instanceof IDiagramNodeEditPart) {
                    nameFigure = ((IDiagramNodeEditPart) dNodeEditPart).getNodeLabel();
                    partToEdit = sfep;
                } else if (dNodeEditPart instanceof IDiagramBorderNodeEditPart) {
                    nameFigure = ((IDiagramBorderNodeEditPart) dNodeEditPart).getNodeLabel();
                    partToEdit = sfep;
                }
            }

            if (nameFigure != null) {
                editor.select(sfep);
                break;
            }
        }

        do_basic_test_direct_edit_to_multiline(partToEdit, nameFigure);
    }

    private void do_basic_test_direct_edit_to_multiline(SWTBotGefEditPart partToEditBot, IFigure nameFigure) {
        assertLabelMultiLines(nameFigure, 1);
        editor.directEditType("O\n1\n2", partToEditBot);
        SWTBotUtils.waitAllUiEvents();
        assertLabelMultiLines(nameFigure, 3);

        editor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Test that Label on ListContainer with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_OnListContainer() {
        openAndInitialiseForVP3382();

        SWTBotGefEditPart dNodeListEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListEditPart.class)).get(0);
        SWTBotGefEditPart dNodeListNameEditPartBot = dNodeListEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListNameEditPart.class)).get(0);

        DNodeListNameEditPart dNodeListNameEditPart = (DNodeListNameEditPart) dNodeListNameEditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dNodeListNameEditPartBot, dNodeListNameEditPart.getFigure());
    }

    /**
     * Test that Label on ListElement with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_OnNodeListElement() {
        openAndInitialiseForVP3382();

        SWTBotGefEditPart dNodeListElementEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListElementEditPart.class)).get(0);

        DNodeListElementEditPart dNodeListElementEditPart = (DNodeListElementEditPart) dNodeListElementEditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dNodeListElementEditPartBot, dNodeListElementEditPart.getFigure());
    }

    /**
     * Test that Label on Container with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_OnContainer() {
        openAndInitialiseForVP3382();

        SWTBotGefEditPart dNodeContainerEditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerEditPart.class)).get(0);
        SWTBotGefEditPart dNodeContainerNameEditPartBot = dNodeContainerEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerNameEditPart.class)).get(0);

        DNodeContainerNameEditPart dNodeContainerNameEditPart = (DNodeContainerNameEditPart) dNodeContainerNameEditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dNodeContainerEditPartBot, dNodeContainerNameEditPart.getFigure());
    }

    /**
     * Test that Label on Container in another container with text containing '\n' are correctly displayed after direct
     * edit.
     */
    public void test_DirectEditSimpleToMultiline_OnContainerInContainer() {
        openAndInitialiseForVP3382();

        SWTBotGefEditPart dNodeContainer2EditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainer2EditPart.class)).get(0);
        SWTBotGefEditPart dNodeContainerName2EditPartBot = dNodeContainer2EditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerName2EditPart.class)).get(0);

        DNodeContainerName2EditPart dNodeContainerName2EditPart = (DNodeContainerName2EditPart) dNodeContainerName2EditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dNodeContainer2EditPartBot, dNodeContainerName2EditPart.getFigure());
    }

    /**
     * Test that Label on List in another container with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_OnListInContainer() {
        openAndInitialiseForVP3382();

        SWTBotGefEditPart dNodeList2EditPartBot = dDiagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeList2EditPart.class)).get(0);
        SWTBotGefEditPart dNodeListName2EditPartBot = dNodeList2EditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListName2EditPart.class)).get(0);

        DNodeListName2EditPart dNodeListName2EditPart = (DNodeListName2EditPart) dNodeListName2EditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dNodeListName2EditPartBot, dNodeListName2EditPart.getFigure());
    }

    /**
     * Test that end Label on edge with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_Edge_Begin() {
        openAndInitialiseForVP3382();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeEndNameEditPart.class)).get(0);

        DEdgeEndNameEditPart dEdgeNameEditPart = (DEdgeEndNameEditPart) dEdgeNameEditPartBot.part();
        checkDeactivatedDirectEdit(dEdgeNameEditPartBot, dEdgeNameEditPart.getFigure());
    }

    private void checkDeactivatedDirectEdit(SWTBotGefEditPart dEdgeNameEditPartBot, IFigure nameFigure) {
        Option<IFigure> wrapLabelFigureOption = new FigureQuery(nameFigure).getLabelFigure();
        assertTrue("This figure should have a label figure.", wrapLabelFigureOption.some());
        assertTrue("The figure of this label should be a SiriusWrapLabel.", wrapLabelFigureOption.get() instanceof SiriusWrapLabel);
        SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) wrapLabelFigureOption.get();
        String wrapText = viewpointWrapLabel.getText();

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        boolean oldErrorCatchActive = platformProblemsListener.isErrorCatchActive();
        platformProblemsListener.setErrorCatchActive(false);
        SWTBotPreferences.TIMEOUT = 1000;
        try {
            assertLabelMultiLines(nameFigure, 1);
            boolean noException = editor.directEditType("O\n1\n2", dEdgeNameEditPartBot);
            if (noException) {
                fail("DirectEdit should not be enabled on begin and end edge labels.");
            }
            SWTBotUtils.waitAllUiEvents();
            assertEquals("DirectEdit should not be enabled on begin and end edge labels.", wrapText, viewpointWrapLabel.getText());
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            platformProblemsListener.setErrorCatchActive(oldErrorCatchActive);
            editor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Test that end Label on edge with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_Edge_Center() {
        openAndInitialiseForVP3382();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeEndNameEditPart.class)).get(0);

        DEdgeEndNameEditPart dEdgeNameEditPart = (DEdgeEndNameEditPart) dEdgeNameEditPartBot.part();
        do_basic_test_direct_edit_to_multiline(dEdgeEditPartBot, dEdgeNameEditPart.getFigure());
    }

    /**
     * Test that end Label on edge with text containing '\n' are correctly displayed after direct edit.
     */
    public void test_DirectEditSimpleToMultiline_Edge_End() {
        openAndInitialiseForVP3382();

        SWTBotGefConnectionEditPart dEdgeEditPartBot = editor.getConnectionsEditPart().get(0);
        SWTBotGefEditPart dEdgeNameEditPartBot = dEdgeEditPartBot.descendants(IsInstanceOf.instanceOf(DEdgeEndNameEditPart.class)).get(0);

        DEdgeEndNameEditPart dEdgeNameEditPart = (DEdgeEndNameEditPart) dEdgeNameEditPartBot.part();
        checkDeactivatedDirectEdit(dEdgeNameEditPartBot, dEdgeNameEditPart.getFigure());
    }

    /**
     * Open the diagram on the correct use case and disabled the snap to grid.
     */
    protected void openDiagramForVP2211() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), VP_2211_REPRESENTATION_NAME, VP_2211_REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    /**
     * Test that the label of a node in the diagram already wrap in saved model will be wrap at the opening of the
     * diagram.
     */
    public void testWrapNodeLabelAtOpenning() {
        testWrapLabelAtOpenning("Node", DNodeEditPart.class, 2);
    }

    /**
     * Test that the label of a node in a container already wrap in saved model will be wrap at the opening of the
     * diagram.
     */
    public void testWrapNodeInContainerLabelAtOpenning() {
        testWrapLabelAtOpenning("Node in container", DNode3EditPart.class, 2);
    }

    /**
     * Test that the label of a container already wrap in saved model will be wrap at the opening of the diagram.
     */
    public void testWrapContainerLabelAtOpenning() {
        testWrapLabelAtOpenning("Container", DNodeContainerEditPart.class, 2);
    }

    /**
     * Test that the label of a container in a container already wrap in saved model will be wrap at the opening of the
     * diagram.
     */
    public void testWrapContainerInContainerLabelAtOpenning() {
        testWrapLabelAtOpenning("Container in container", DNodeContainer2EditPart.class, 4);
    }

    /**
     * Test that the label of a listContainer in the diagram already wrap in saved model will be wrap at the opening of
     * the diagram.
     */
    public void testWrapListContainerLabelAtOpenning() {
        testWrapLabelAtOpenning("ListContainer", DNodeListEditPart.class, 3);
    }

    /**
     * Test that the label of a listContainer in a container already wrap in saved model will be wrap at the opening of
     * the diagram.
     */
    public void testWrapListContainerInContainerLabelAtOpenning() {
        testWrapLabelAtOpenning("ListContainer in container", DNodeList2EditPart.class, 4);
    }

    /**
     * Test that the label of a list element already wrap in saved model will be wrap at the opening of the diagram.
     */
    public void testWrapElementListLabelAtOpenning() {
        testWrapLabelAtOpenning("Element List", DNodeListElementEditPart.class, 3);
    }

    /**
     * Test that the label of a Node is correctly wrapped during a resize of a figure.
     */
    public void testWrapNodeLabelDuringResize() {
        testWrapLabelDuringResize("Node package with long name", DNodeEditPart.class, 2);
    }

    /**
     * Test that the label of a Node in a container is correctly wrapped during a resize of a figure.
     */
    public void testWrapNodeInContainerLabelDuringResize() {
        testWrapLabelDuringResize("Class With Very Very Long Name2", DNode3EditPart.class, 2);
    }

    /**
     * Test that the label of a Container is correctly wrapped during a resize of a figure.
     */
    public void testWrapContainerLabelDuringResize() {
        testWrapLabelDuringResize("Container package with long name", DNodeContainerEditPart.class, 2);
    }

    /**
     * Test that the label of a Container in a container is correctly wrapped during a resize of a figure.
     */
    public void testWrapContainerInContainerLabelDuringResize() {
        testWrapLabelDuringResize("Container package with long name2", DNodeContainer2EditPart.class, 2);
    }

    /**
     * Test that the label of a ListContainer is correctly wrapped during a resize of a figure.
     */
    public void testWrapListContainerLabelDuringResize() {
        testWrapLabelDuringResize("ListContainer package with long name", DNodeListEditPart.class, 2);
    }

    /**
     * Test that the label of a ListContainer in a container is correctly wrapped during a resize of a figure.
     */
    public void testWrapListContainerInContainerLabelDuringResize() {
        testWrapLabelDuringResize("ListContainer package with long name2", DNodeList2EditPart.class, 2);
    }

    /**
     * Test that the label of a list element is correctly wrapped during a resize of a figure.
     */
    public void testWrapElementListLabelDuringResize() {
        testWrapLabelDuringResize("ListContainer package with long name", DNodeListEditPart.class, "NE Class With Very Very Long Name2", DNodeListElementEditPart.class, 2);
    }

    /**
     * Test that the label of an element is correctly wrapped during a resize of a figure.
     * 
     * @param label
     *            The label that is displayed.
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @param nbLinesExpected
     *            The number of lines that is expected
     */
    protected void testWrapLabelDuringResize(String label, Class<? extends AbstractGraphicalEditPart> expectedEditPartType, int nbLinesExpected) {
        testWrapLabelDuringResize(label, expectedEditPartType, label, expectedEditPartType, nbLinesExpected);
    }

    /**
     * Test that the label of an element is correctly wrapped during a resize of a figure.
     * 
     * @param label
     *            The label that is displayed.
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @param nbLinesExpected
     *            The number of lines that is expected
     */
    protected void testWrapLabelDuringResize(String labelToResize, Class<? extends AbstractGraphicalEditPart> expectedEditPartTypeToResize, String labelToCheck,
            Class<? extends AbstractGraphicalEditPart> expectedEditPartTypeToCheck, int nbLinesExpected) {
        openDiagramForVP2211();

        SWTBotGefEditPart partToResize = editor.getEditPart(labelToResize, expectedEditPartTypeToResize);
        assertTrue("The part should be a IGraphicalEditPart (but is a " + partToResize.part().getClass().getName() + ")", partToResize.part() instanceof IGraphicalEditPart);
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, partToResize.part());
        Rectangle bounds = editor.clickNorth(partToResize);
        // Wait the the edit part is selected
        bot.waitUntil(cS);
        Rectangle newBounds = new Rectangle(bounds.x, bounds.y, bounds.getRight().getTranslated(TRANSLATION_MINUS_70_X).x - bounds.x, bounds.height);
        editor.drag(bounds.getRight(), newBounds.getRight());
        // Wait the the edit part is correctly resized
        CheckBoundsCondition cB = new CheckBoundsCondition((IGraphicalEditPart) partToResize.part(), newBounds, true, false);
        bot.waitUntil(cB);

        // Check the number of lines of the label
        if (StringUtil.equals(labelToResize, labelToCheck) && expectedEditPartTypeToCheck == expectedEditPartTypeToResize) {
            assertLabelMultiLines(((IGraphicalEditPart) partToResize.part()).getFigure(), nbLinesExpected);
        } else {
            SWTBotGefEditPart partToCheck = editor.getEditPart(labelToCheck, expectedEditPartTypeToCheck);
            assertLabelMultiLines(((IGraphicalEditPart) partToCheck.part()).getFigure(), nbLinesExpected);
        }

        editor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Test that the label of an element already wrap in saved model will be wrap at the opening of the diagram.
     * 
     * @param middleLabel
     *            The middle of the label that is displayed (with "Truncared " as prefix and " At opening" as suffix).
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @param nbLinesExpected
     *            The number of lines that is expected
     */
    protected void testWrapLabelAtOpenning(String middleLabel, Class<? extends AbstractGraphicalEditPart> expectedEditPartType, int nbLinesExpected) {
        openDiagramForVP2211();

        SWTBotGefEditPart swtBotGefEditPart = editor.getEditPart(TRUNCATED_LABEL_PREFIX + middleLabel + AT_OPENING_LABEL_SUFFIX, expectedEditPartType);
        assertLabelMultiLines(((AbstractGraphicalEditPart) swtBotGefEditPart.part()).getFigure(), nbLinesExpected);

        editor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Assert that labelFigure parameter has its text splitted on i lines.
     * 
     * @param labelFigure
     *            the Label {@link IFigure} on which do the assertion
     * 
     * @param i
     *            the number of lines that the labelFigure should splits the text
     */
    private void assertLabelMultiLines(IFigure labelFigureOrParent, int nbLinesExpected) {

        Option<IFigure> wrapLabelFigureOption = new FigureQuery(labelFigureOrParent).getLabelFigure();
        assertTrue("This figure should have a label figure.", wrapLabelFigureOption.some());
        assertTrue("The figure of this label should be a SiriusWrapLabel.", wrapLabelFigureOption.get() instanceof SiriusWrapLabel);
        SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) wrapLabelFigureOption.get();
        String wrapText = viewpointWrapLabel.getSubStringText();
        String[] lines = wrapText.split("\n");
        Assert.assertEquals("The label is not wrap as expected (bad number of line)", nbLinesExpected, lines.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        dDiagramEditPartBot = null;

        super.tearDown();
    }

}
