/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper.EdgeData;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests for the popup menu tool that creates element. Validate that the created
 * elements are located where the user right-clicked and not at the origin.
 * 
 * See VP-1859.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ElementCreationWithPopupMenuTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/tools/creation/popupMenu/VP-1859/";

    private static final String SEMANTIC_RESOURCE_NAME = "vp-1859.ecore";

    private static final String SESSION_RESOURCE_NAME = "vp-1859.aird";

    private static final String MODELER_RESOURCE_NAME = "vp-1859.odesign";

    private static final String REPRESENTATION_NAME = "VP-1859";

    private static final String REPRESENTATION_INSTANCE_NAME = "new " + REPRESENTATION_NAME;

    private static final String TOOL_NAME = "1 EClass";

    private static final String TOOL_NAME_IN_TRANSIENT_LAYER = "Transient-1 EClass";

    private static final String TOOL_NAME_ON_EDGE = "EClass On Edge";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /** Bot for the DiagramEditPart */
    protected SWTBotGefEditPart diagramEditPartBot;

    private SWTBotGefEditPart dNodeContainerABot;

    private SWTBotGefEditPart dNodeContainerCBot;

    private SWTBotGefEditPart class3ChildOfDiagramBot;

    private SWTBotGefEditPart class2ChildOfContainerABot;

    private Rectangle dNodeContainerCBotBounds;

    private DSemanticDiagram dSemanticDiagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
        diagramEditPartBot = editor.rootEditPart().children().get(0);
        DDiagramEditPart dDiagramEditPart = (DDiagramEditPart) diagramEditPartBot.part();
        dSemanticDiagram = (DSemanticDiagram) dDiagramEditPart.resolveSemanticElement();
        EPackage rootEPackage = (EPackage) dSemanticDiagram.getTarget();
        EClass eclass3 = (EClass) rootEPackage.eContents().get(2);

        class3ChildOfDiagramBot = diagramEditPartBot.descendants(WithSemantic.withSemantic(eclass3)).get(0);

        dNodeContainerABot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerEditPart.class)).get(0);

        class2ChildOfContainerABot = dNodeContainerABot.descendants(IsInstanceOf.instanceOf(DNode3EditPart.class)).get(1);

        SWTBotGefEditPart dNodeContainerBBot = dNodeContainerABot.descendants(IsInstanceOf.instanceOf(DNodeContainer2EditPart.class)).get(0);
        dNodeContainerCBot = dNodeContainerBBot.descendants(IsInstanceOf.instanceOf(DNodeContainer2EditPart.class)).get(0);

        dNodeContainerCBotBounds = editor.getBounds(dNodeContainerCBot);
        editor.scrollTo(0, 0);
    }

    /**
     * Test that one EClass creation on DiagramEditPart via the tool in the
     * palette creates the View at the clicked position.
     */
    public void testOneEClassCreationOnDiagramEditPartViaPaletteTool() {
        IGraphicalEditPart class3Part = (IGraphicalEditPart) class3ChildOfDiagramBot.part();

        editor.reveal(class3Part);
        // 1. Create a new EClass
        editor.activateTool(TOOL_NAME);
        // The creation location in logical coordinates (not in screen
        // coordinates)
        Point absoluteCreationLocation = editor.getAbsoluteLocation(class3Part).getCopy().translate(-100, 0);
        // The creation location in screen coordinates
        Point screenCreationLocation = absoluteCreationLocation.getCopy();
        GraphicalHelper.logical2screen(screenCreationLocation, (IGraphicalEditPart) diagramEditPartBot.part());

        editor.click(screenCreationLocation);

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class6", DNodeEditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);

        Point absoluteNewClassBotLocation = editor.getAbsoluteLocation((IGraphicalEditPart) newClassBot.part()).getCopy();

        assertEquals("Creation with palette tool is not at the correct location", absoluteCreationLocation, absoluteNewClassBotLocation);
    }

    /**
     * Test that one EClass creation on DiagramEditPart via the tool in the
     * popup menu creates the View at the clicked position.
     */
    public void testOneEClassCreationOnDiagramEditPartViaPopupMenuTool() {
        IGraphicalEditPart class3Part = (IGraphicalEditPart) class3ChildOfDiagramBot.part();

        editor.reveal(class3Part);
        // 1. Create a new EClass
        // The creation location in logical coordinates (not in screen
        // coordinates)
        Point absoluteCreationLocation = editor.getAbsoluteLocation(class3Part).getCopy().translate(-100, 0);
        // The creation location in screen coordinates
        Point screenCreationLocation = absoluteCreationLocation.getCopy();
        GraphicalHelper.logical2screen(screenCreationLocation, (IGraphicalEditPart) diagramEditPartBot.part());

        editor.clickContextMenu(screenCreationLocation, TOOL_NAME);
        SWTBotUtils.waitAllUiEvents();

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class6", DNodeEditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);

        Point absoluteNewClassBotLocation = editor.getAbsoluteLocation((IGraphicalEditPart) newClassBot.part()).getCopy();

        assertEquals("Creation with contextual menu is not at the correct location", absoluteCreationLocation, absoluteNewClassBotLocation);
    }

    /**
     * Test that one EClass creation on DiagramEditPart via the tool from a
     * transient layer in the popup menu creates the View at the clicked
     * position.
     */
    public void testOneEClassCreationOnDiagramEditPartViaPopupMenuToolFromTransientLayer() {
        IGraphicalEditPart class3Part = (IGraphicalEditPart) class3ChildOfDiagramBot.part();

        editor.reveal(class3Part);
        // 1. Create a new EClass
        // The creation location in logical coordinates (not in screen
        // coordinates)
        Point absoluteCreationLocation = editor.getAbsoluteLocation(class3Part).getCopy().translate(-100, 0);
        // The creation location in screen coordinates
        Point screenCreationLocation = absoluteCreationLocation.getCopy();
        GraphicalHelper.logical2screen(screenCreationLocation, (IGraphicalEditPart) diagramEditPartBot.part());

        editor.clickContextMenu(screenCreationLocation, TOOL_NAME_IN_TRANSIENT_LAYER);
        SWTBotUtils.waitAllUiEvents();

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class6", DNodeEditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);

        Point absoluteNewClassBotLocation = editor.getAbsoluteLocation((IGraphicalEditPart) newClassBot.part()).getCopy();

        assertEquals("Creation with contextual menu is not at the correct location", absoluteCreationLocation, absoluteNewClassBotLocation);
    }

    /**
     * Test that one EClass creation on the ContainerEditPart's view named 'A'
     * with scroll via the tool in the palette creates the View at the clicked
     * position.
     */
    public void testOneEClassCreationOnContainerEditPartAWithScrollViaPaletteTool() {
        // Reveal ContainerA (to scroll in the diagram)
        editor.reveal(dNodeContainerABot.part());

        double zoomLevel = GraphicalHelper.getZoom(diagramEditPartBot.part());
        Point screenCreationLocation = editor.getBounds(class2ChildOfContainerABot).getLocation().getCopy().translate(new Point(100, 0).getScaled(zoomLevel));

        // 1. Create a new EClass
        editor.activateTool(TOOL_NAME);
        // editor.scrollTo(creationLocation);
        editor.click(screenCreationLocation);

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class4", DNode3EditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);
        assertEquals("Creation with palette tool is not at the correct location", screenCreationLocation, editor.getBounds(newClassBot).getLocation());
    }

    /**
     * Test that one EClass creation on the ContainerEditPart's view named 'A'
     * with scroll via the tool in the popup menu creates the View at the
     * clicked position.
     */
    public void testOneEClassCreationOnContainerEditPartAWithScrollViaPopupMenuTool() {
        // Reveal ContainerA (to scroll in the diagram)
        editor.reveal(dNodeContainerABot.part());
        editor.select(dNodeContainerABot);
        double zoomLevel = GraphicalHelper.getZoom(diagramEditPartBot.part());
        Point screenCreationLocation = editor.getBounds(class2ChildOfContainerABot).getLocation().getCopy().translate(new Point(100, 0).getScaled(zoomLevel));

        // 1. Create a new EClass
        editor.clickContextMenu(screenCreationLocation, TOOL_NAME);

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class4", DNode3EditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);
        assertEquals("Creation with contextual menu is not at the correct location", screenCreationLocation, editor.getBounds(newClassBot).getLocation());
    }

    /**
     * Tests that a new EClass is created via the tool in the pop-up menu when
     * right-clicking on an edge
     * 
     */
    public void testOneEClassCreationOnDiagramViaPopupMenuToolOnEdge() {
        // Getting the edge middle bend-point location
        final SWTBotGefEditPart class5EditPart = editor.getEditPart("Class5").parent();
        final SWTBotGefEditPart class2EditPart = editor.getEditPart("Class2").parent();
        final List<EdgeData> edgeData = SWTBotCommonHelper.getEdgeData(class5EditPart, class2EditPart, editor);
        final SWTBotGefConnectionEditPart swtBotConnectionEditPart = edgeData.get(0).getSwtBotEditPart();
        final ConnectionEditPart connectionEditPart = swtBotConnectionEditPart.part();
        assertTrue("The figure should be a ViewEdgeFigure.", connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        final ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure) connectionEditPart.getFigure();

        Point middlePoint = viewEdgeFigure.getPoints().getMidpoint().getCopy();

        double zoomLevel = GraphicalHelper.getZoom(diagramEditPartBot.part());
        if (zoomLevel < ZoomLevel.ZOOM_100.getAmount()) {
            middlePoint.scale(zoomLevel);
        }

        // Create a new EClass
        editor.clickContextMenu(middlePoint, TOOL_NAME_ON_EDGE);

        // Asserts that the new EClass was added
        // Throw an exception if edit part is not found
        assertTrue("The new class should be displayed on the diagram", editor.getEditPart("Class6") != null);
    }

    /**
     * Test that one EClass creation on the ContainerEditPart's view named 'C'
     * with scroll via the tool in the palette creates the View at the clicked
     * position.
     * 
     * TODO : This test is disabled because we click outside the C container.
     */
    public void _testOneEClassCreationOnContainerEditPartCWithScrollViaPaletteTool() {
        Point screenCreationLocation = dNodeContainerCBotBounds.getCenter();
        double zoomLevel = GraphicalHelper.getZoom(diagramEditPartBot.part());
        if (zoomLevel < ZoomLevel.ZOOM_100.getAmount()) {
            screenCreationLocation.scale(zoomLevel);
        }

        // 1. Create a new EClass
        editor.activateTool(TOOL_NAME);
        // editor.scrollTo(creationLocation);
        editor.click(screenCreationLocation);

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class4", DNode3EditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);
        assertEquals("Creation with palette tool is not at the correct location", screenCreationLocation, editor.getBounds(newClassBot).getLocation());
    }

    /**
     * Test that one EClass creation on the ContainerEditPart's view named 'C'
     * with scroll via the tool in the popup menu creates the View at the
     * clicked position.
     * 
     * TODO : This test is disabled because we click outside the C container.
     */
    public void _testOneEClassCreationOnContainerEditPartCWithScrollViaPopupMenuTool() {
        Point screenCreationLocation = dNodeContainerCBotBounds.getCenter();
        double zoomLevel = GraphicalHelper.getZoom(diagramEditPartBot.part());
        if (zoomLevel < ZoomLevel.ZOOM_100.getAmount()) {
            screenCreationLocation.scale(zoomLevel);
        }

        // 1. Create a new EClass
        editor.clickContextMenu(screenCreationLocation, TOOL_NAME);

        // 2. Assert the location of the created EClass
        SWTBotGefEditPart newClassBot = editor.getEditPart("Class4", DNode3EditPart.class);
        assertNotNull("No class has been created for this location", newClassBot);
        assertEquals("Creation with contextual menu is not at the correct location", screenCreationLocation, editor.getBounds(newClassBot).getLocation());
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagramEditPartBot = null;
        dNodeContainerABot = null;
        dNodeContainerCBot = null;
        class3ChildOfDiagramBot = null;
        class2ChildOfContainerABot = null;
        dNodeContainerCBotBounds = null;

        super.tearDown();
    }

}
