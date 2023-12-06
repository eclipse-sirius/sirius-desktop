/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.action.MatchByAutoSizeStatus;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests for the automatic arrangement features.
 * 
 * @author cbrun
 */
public class ArrangeAllTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "ArrangeAll";

    private static final String DATA_UNIT_DIR = "data/unit/layout/";

    private static final String FILE_DIR = "model";

    private static final String MODEL = "model/simple.ecore";

    private static final String VSM_FILE = "layouts.odesign";

    private static final int DEFAULT_PADDING = 30;

    private static final int SMALL_PADDING = 1;

    private static final int BIG_PADDING = 300;

    /**
     * The local session.
     */
    protected UILocalSession localSession;

    /**
     * The first package of the model.
     */
    protected SWTBotTreeItem mainEPackage;

    private UIResource semanticModel;

    /**
     * True if snapToGrid should be activated on editor, false otherwise.
     */
    protected boolean snapToGrid;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (!snapToGrid) {
            changeDiagramUIPreference(IPreferenceConstants.PREF_SNAP_TO_GRID, false);
        }
    }

    private void assertCenterAboveOf(final String message, final IGraphicalEditPart a, final IGraphicalEditPart b) {

        Point aAbsoluteCenter = a.getFigure().getBounds().getCenter().getCopy();
        Point bAbsoluteCenter = b.getFigure().getBounds().getCenter().getCopy();
        a.getFigure().translateToAbsolute(aAbsoluteCenter);
        b.getFigure().translateToAbsolute(bAbsoluteCenter);
        if (aAbsoluteCenter.y >= bAbsoluteCenter.y) {
            fail(message + " first figure has y=" + aAbsoluteCenter.y + " and second one has y=" + bAbsoluteCenter.y);
        }

    }

    private void assertCenterLeftOf(final String message, final IGraphicalEditPart a, final IGraphicalEditPart b) {
        Point aAbsoluteCenter = a.getFigure().getBounds().getCenter().getCopy();
        Point bAbsoluteCenter = b.getFigure().getBounds().getCenter().getCopy();
        a.getFigure().translateToAbsolute(aAbsoluteCenter);
        b.getFigure().translateToAbsolute(bAbsoluteCenter);
        if (aAbsoluteCenter.x >= bAbsoluteCenter.x) {
            fail(message + " first figure has x=" + aAbsoluteCenter + " and second one has x=" + bAbsoluteCenter);
        }

    }

    private void doTestAutosizeOnArrangeAll(final SWTBotSiriusDiagramEditor editor) throws InterruptedException {
        final List<IDiagramContainerEditPart> partsToResize = getAllContainerEditParts(editor);

        final int originalContainersNumber = partsToResize.size();

        for (final IDiagramContainerEditPart iDiagramContainerEditPart : partsToResize) {
            final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            req.setSizeDelta(new Dimension(300, 300));
            iDiagramContainerEditPart.performRequest(req);
        }

        arrangeAll();

        final List<IDiagramContainerEditPart> afterArrangeAll = Lists
                .newArrayList(Iterables.filter(getAllContainerEditParts(editor), Predicates
                        .and(Predicates.instanceOf(IDiagramContainerEditPart.class), new MatchByAutoSizeStatus(true))));

        assertEquals("We should have the same number of auto-sized containers before and after the arrange all",
                originalContainersNumber, afterArrangeAll.size());
    }

    private List<IDiagramContainerEditPart> getAllContainerEditParts(final SWTBotSiriusDiagramEditor editor) {
        final List<IDiagramContainerEditPart> partsToResize = Lists
                .newArrayList(Iterables.filter(editor.editParts(new BaseMatcher<EditPart>() {

                    @Override
                    public void describeTo(final Description description) {

                    }

                    @Override
                    public boolean matches(final Object item) {
                        return true;
                    }

                }), IDiagramContainerEditPart.class));
        return partsToResize;
    }

    private Rectangle getDiagramBounds(final SWTBotSiriusDiagramEditor defaultPaddingEditor) {
        arrangeAll();

        return defaultPaddingEditor.getDiagramEditPart().getFigure().getBounds();
    }

    private SWTBotSiriusDiagramEditor getEditor(final String defaultPaddingDiagramDescription) {
        final UIDiagramRepresentation defaultPadding = localSession
                .newDiagramRepresentation("new " + defaultPaddingDiagramDescription, defaultPaddingDiagramDescription)
                .on(mainEPackage).withDefaultName().ok();
        SWTBotSiriusDiagramEditor editor = defaultPadding.open().getEditor();
        if (!snapToGrid) {
            editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);
            editor.setSnapToGrid(false);
        }
        return editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        semanticModel = new UIResource(designerProject, FILE_DIR, "simple.ecore");
        localSession = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticModel)
                .fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                .selectViewpoints(VIEWPOINT_NAME);
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        mainEPackage = semanticResourceNode.expandNode("Root").expandNode("Main").click();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM_FILE);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAutosizeApplicationOnArrangeAllLeftRight() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Left/Right Container Ports And Edges",
                        "Left/Right Container Ports And Edges")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        doTestAutosizeOnArrangeAll(editor);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAutosizeApplicationOnArrangeAllTopBottom() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Top/Bottom Container Ports And Edges",
                        "Top/Bottom Container Ports And Edges")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        doTestAutosizeOnArrangeAll(editor);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAutosizeApplicationOnArrangeAllTreeOrdering() throws Exception {

        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new TreeOrdering", "TreeOrdering").on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        doTestAutosizeOnArrangeAll(editor);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testLeftRightPaddingSetting() throws Exception {
        final String defaultPaddingDiagramDescription = "Left/Right MainLikeDiagram";
        final String bigPaddingDiagramDescription = "Left/Right MainLikeDiagram with Big Padding";
        final String smallPaddingDiagramDescription = "Left/Right MainLikeDiagram with Small Padding";

        SWTBotSiriusDiagramEditor defaultEditor = getEditor(defaultPaddingDiagramDescription);
        final int defaultPaddingWidth = getDiagramBounds(defaultEditor).width;

        SWTBotSiriusDiagramEditor bigEditor = getEditor(bigPaddingDiagramDescription);
        final int bigPaddingWidth = getDiagramBounds(bigEditor).width;

        SWTBotSiriusDiagramEditor smallEditor = getEditor(smallPaddingDiagramDescription);
        final int smallPaddingWidth = getDiagramBounds(smallEditor).width;

        assertTrue(
                "Diagram with default padding, " + defaultPaddingWidth
                    + ", should have lower width than diagram with big padding, " + bigPaddingWidth,
                bigPaddingWidth > defaultPaddingWidth);

        assertTrue(
                "Diagram with default padding, " + defaultPaddingWidth
                    + ",  should have higher width than diagram with small padding, " + smallPaddingWidth,
                smallPaddingWidth < defaultPaddingWidth);

        checkLeftRightPadding(defaultEditor, DEFAULT_PADDING);
        checkLeftRightPadding(bigEditor, BIG_PADDING);
        checkLeftRightPadding(smallEditor, SMALL_PADDING);
    }

    /**
     * @param editor
     * @param expectedPadding
     */
    private void checkLeftRightPadding(SWTBotSiriusDiagramEditor editor, int expectedPadding) {
        IGraphicalEditPart LF8 = (IGraphicalEditPart) editor.getEditPart("LF8", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF72 = (IGraphicalEditPart) editor.getEditPart("LF7_2", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF71 = (IGraphicalEditPart) editor.getEditPart("LF7_1", DNodeContainerEditPart.class).part();

        assertNotNull(LF8);
        assertNotNull(LF72);
        assertNotNull(LF71);

        if (!snapToGrid) {
            // Nothing to check when snapToGrid is activated because padding can not be respected.
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF72.getFigure().getBounds().getTopLeft().x - LF8.getFigure().getBounds().getTopRight().x);
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF71.getFigure().getBounds().getTopLeft().x - LF72.getFigure().getBounds().getTopRight().x);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testLeftRightSpecifiedLayout() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Left/Right MainLikeDiagram", "Left/Right MainLikeDiagram")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();
        final IGraphicalEditPart LF8 = (IGraphicalEditPart) editor.getEditPart("LF8").part();

        assertNotNull(LF1);
        assertNotNull(LF5);
        assertNotNull(LF8);

        assertCenterLeftOf("Containers should be left-right and LF1 at the right", LF5, LF1);
        assertCenterLeftOf("Containers should be left-right and LF1 at the right", LF8, LF1);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testLeftRightSpecifiedLayoutWithContainers() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Left/Right Container Ports And Edges",
                        "Left/Right Container Ports And Edges")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF3 = (IGraphicalEditPart) editor.getEditPart("LF2").part();
        final IGraphicalEditPart LF2 = (IGraphicalEditPart) editor.getEditPart("LF3").part();
        final IGraphicalEditPart LF4 = (IGraphicalEditPart) editor.getEditPart("LF4").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();

        assertNotNull(LF1);
        assertNotNull(LF5);
        assertNotNull(LF3);
        assertNotNull(LF2);
        assertNotNull(LF4);

        assertCenterLeftOf("Containers should be left-right and LF5 at the left of LF1", LF5, LF1);
        assertCenterLeftOf("Containers should be left-right and LF5 at the left of LF4", LF5, LF4);
        assertCenterLeftOf("Containers should be left-right and LF5 at the left of LF2", LF5, LF2);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTopBottomPaddingSetting() throws Exception {
        final String defaultPaddingDiagramDescription = "Top/Bottom MainLikeDiagram";
        final String bigPaddingDiagramDescription = "Top/Bottom MainLikeDiagram with Big Padding";
        final String smallPaddingDiagramDescription = "Top/Bottom MainLikeDiagram with Small Padding";

        SWTBotSiriusDiagramEditor defaultEditor = getEditor(defaultPaddingDiagramDescription);
        final int defaultPaddingHeight = getDiagramBounds(defaultEditor).height;

        SWTBotSiriusDiagramEditor bigEditor = getEditor(bigPaddingDiagramDescription);
        final int bigPaddingHeight = getDiagramBounds(bigEditor).height;

        assertTrue("Diagram with default padding should have lower height than diagram with big padding",
                bigPaddingHeight > defaultPaddingHeight);

        SWTBotSiriusDiagramEditor smallEditor = getEditor(smallPaddingDiagramDescription);
        final int smallPaddingHeight = getDiagramBounds(smallEditor).height;

        assertTrue("Diagram with default padding should have higher height than diagram with big padding",
                smallPaddingHeight < defaultPaddingHeight);

        checkTopBottomPadding(defaultEditor, DEFAULT_PADDING);
        checkTopBottomPadding(bigEditor, BIG_PADDING);
        checkTopBottomPadding(smallEditor, SMALL_PADDING);
    }

    /**
     * @param editor
     * @param expectedPadding
     */
    private void checkTopBottomPadding(SWTBotSiriusDiagramEditor editor, int expectedPadding) {
        IGraphicalEditPart LF8 = (IGraphicalEditPart) editor.getEditPart("LF8", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF72 = (IGraphicalEditPart) editor.getEditPart("LF7_2", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF71 = (IGraphicalEditPart) editor.getEditPart("LF7_1", DNodeContainerEditPart.class).part();

        assertNotNull(LF8);
        assertNotNull(LF72);
        assertNotNull(LF71);

        if (!snapToGrid) {
            // Nothing to check when snapToGrid is activated because padding can not be respected.
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF72.getFigure().getBounds().getTopLeft().y - LF8.getFigure().getBounds().getBottomLeft().y);
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF71.getFigure().getBounds().getTopLeft().y - LF72.getFigure().getBounds().getBottomLeft().y);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTopBottomSpecifiedLayout() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Top/Bottom MainLikeDiagram", "Top/Bottom MainLikeDiagram")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();
        final IGraphicalEditPart LF8 = (IGraphicalEditPart) editor.getEditPart("LF8").part();

        assertNotNull(LF1);
        assertNotNull(LF5);
        assertNotNull(LF8);

        assertCenterAboveOf("Containers should be bottom-top and LF1 at the top", LF5, LF1);
        assertCenterAboveOf("Containers should be bottom-top and LF1 at the top", LF8, LF1);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTopBottomSpecifiedLayoutWithContainers() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Top/Bottom Container Ports And Edges",
                        "Top/Bottom Container Ports And Edges")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF2 = (IGraphicalEditPart) editor.getEditPart("LF2").part();
        final IGraphicalEditPart LF3 = (IGraphicalEditPart) editor.getEditPart("LF3").part();
        final IGraphicalEditPart LF4 = (IGraphicalEditPart) editor.getEditPart("LF4").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();

        assertNotNull(LF1);
        assertNotNull(LF3);
        assertNotNull(LF2);
        assertNotNull(LF4);
        assertNotNull(LF5);

        assertCenterAboveOf("Containers should be top-bottom and LF1 above LF5", LF5, LF1);

        assertCenterAboveOf("Containers should be top-bottom and LF5 under everybody", LF5, LF2);
        assertCenterAboveOf("Containers should be top-bottom and LF5 under everybody", LF5, LF3);
        assertCenterAboveOf("Containers should be top-bottom and LF5 under everybody", LF5, LF4);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBottomTopPaddingSetting() throws Exception {
        final String defaultPaddingDiagramDescription = "Bottom/Top MainLikeDiagram";
        final String bigPaddingDiagramDescription = "Bottom/Top MainLikeDiagram with Big Padding";
        final String smallPaddingDiagramDescription = "Bottom/Top MainLikeDiagram with Small Padding";

        SWTBotSiriusDiagramEditor defaultEditor = getEditor(defaultPaddingDiagramDescription);
        final int defaultPaddingHeight = getDiagramBounds(defaultEditor).height;

        SWTBotSiriusDiagramEditor bigEditor = getEditor(bigPaddingDiagramDescription);
        final int bigPaddingHeight = getDiagramBounds(bigEditor).height;

        assertTrue(
                "Diagram with default padding, " + defaultPaddingHeight
                    + ", should have lower height than diagram with big padding, " + bigPaddingHeight,
                bigPaddingHeight > defaultPaddingHeight);

        SWTBotSiriusDiagramEditor smallEditor = getEditor(smallPaddingDiagramDescription);
        final int smallPaddingHeight = getDiagramBounds(smallEditor).height;

        assertTrue(
                "Diagram with default padding, " + defaultPaddingHeight
                    + ", should have higher height than diagram with big padding, " + smallPaddingHeight,
                smallPaddingHeight < defaultPaddingHeight);

        checkBottomTopPadding(defaultEditor, DEFAULT_PADDING);
        checkBottomTopPadding(bigEditor, BIG_PADDING);
        checkBottomTopPadding(smallEditor, SMALL_PADDING);
    }

    /**
     * @param editor
     * @param expectedPadding
     */
    private void checkBottomTopPadding(SWTBotSiriusDiagramEditor editor, int expectedPadding) {
        IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5", DNodeContainerEditPart.class).part();
        IGraphicalEditPart LF3 = (IGraphicalEditPart) editor.getEditPart("LF3", DNodeContainerEditPart.class).part();

        assertNotNull(LF1);
        assertNotNull(LF5);
        assertNotNull(LF3);

        if (!snapToGrid) {
            // Nothing to check when snapToGrid is activated because padding can not be respected.
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF5.getFigure().getBounds().getTopLeft().y - LF1.getFigure().getBounds().getBottomLeft().y);
            assertEquals("Bad space between two nodes", expectedPadding * 2,
                    LF5.getFigure().getBounds().getTopLeft().y - LF3.getFigure().getBounds().getBottomLeft().y);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBottomTopSpecifiedLayout() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Bottom/Top MainLikeDiagram", "Bottom/Top MainLikeDiagram")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();
        final IGraphicalEditPart LF8 = (IGraphicalEditPart) editor.getEditPart("LF8").part();

        assertNotNull(LF1);
        assertNotNull(LF5);
        assertNotNull(LF8);

        assertCenterAboveOf("Containers should be top-bottom and LF1 at the bottom", LF1, LF5);
        assertCenterAboveOf("Containers should be top-bottom and LF1 at the bottom", LF5, LF8);
        assertCenterAboveOf("Containers should be top-bottom and LF1 at the bottom", LF1, LF8);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBottomTopSpecifiedLayoutWithContainers() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new Bottom/Top Container Ports And Edges",
                        "Bottom/Top Container Ports And Edges")
                .on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        final IGraphicalEditPart LF2 = (IGraphicalEditPart) editor.getEditPart("LF2").part();
        final IGraphicalEditPart LF3 = (IGraphicalEditPart) editor.getEditPart("LF3").part();
        final IGraphicalEditPart LF4 = (IGraphicalEditPart) editor.getEditPart("LF4").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();

        assertNotNull(LF1);
        assertNotNull(LF3);
        assertNotNull(LF2);
        assertNotNull(LF4);
        assertNotNull(LF5);

        assertCenterAboveOf("Containers should be bottom-top and LF1 above LF5", LF1, LF5);

        assertCenterAboveOf("Containers should be bottom-top and LF5 under everybody", LF1, LF5);
        assertCenterAboveOf("Containers should be bottom-top and LF5 under everybody", LF2, LF5);
        assertCenterAboveOf("Containers should be bottom-top and LF5 under everybody", LF3, LF5);
        assertCenterAboveOf("Containers should be bottom-top and LF5 under everybody", LF4, LF5);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTreeOrdering() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession
                .newDiagramRepresentation("new TreeOrdering", "TreeOrdering").on(mainEPackage).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        arrangeAll();

        final IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1").part();
        IGraphicalEditPart LF3 = (IGraphicalEditPart) editor.getEditPart("LF2").part();
        IGraphicalEditPart LF2 = (IGraphicalEditPart) editor.getEditPart("LF3").part();
        IGraphicalEditPart LF4 = (IGraphicalEditPart) editor.getEditPart("LF4").part();
        final IGraphicalEditPart LF5 = (IGraphicalEditPart) editor.getEditPart("LF5").part();

        assertNotNull(LF1);
        assertNotNull(LF3);
        assertNotNull(LF2);
        assertNotNull(LF4);
        assertNotNull(LF5);

        assertCenterAboveOf("LF1 is root and should be at the top of his childs", LF1, LF2);
        assertCenterAboveOf("LF1 is root and should be at the top of his childs", LF1, LF2);
        assertCenterAboveOf("LF1 is root and should be at the top of his childs", LF1, LF4);

        /*
         * Checking the original order of the children : LF3 - LF2 - LF4
         */
        assertCenterLeftOf("LF3 should be left of LF2", LF3, LF2);
        assertCenterLeftOf("LF2 should be left of LF3", LF2, LF4);
        assertCenterLeftOf("LF3 should be left of LF4", LF3, LF4);

        /*
         * Now moving LF4 at the left most corner.
         */

        editor.drag("LF4", 1, 1);
        editor.mainEditPart().click();
        editor.mainEditPart().select();
        editor.mainEditPart().focus();

        /*
         * Now LF4 should be at the left of everybody and the order should be
         * kept during the arrange all
         */
        arrangeAll();

        LF3 = (IGraphicalEditPart) editor.getEditPart("LF2").part();
        LF2 = (IGraphicalEditPart) editor.getEditPart("LF3").part();
        LF4 = (IGraphicalEditPart) editor.getEditPart("LF4").part();

        assertCenterLeftOf("LF4 should be left of LF3", LF4, LF3);
        assertCenterLeftOf("LF4 should be left of LF2", LF4, LF2);

    }
}
