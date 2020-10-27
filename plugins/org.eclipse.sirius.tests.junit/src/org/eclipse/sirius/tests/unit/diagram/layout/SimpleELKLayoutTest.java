/*******************************************************************************
 * Copyright (c) 2020 Obeo.
 * All rights reserved.
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ResetOriginChangeModelOperation;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests to realize some verification of arrange result with basic ELK layouts.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class SimpleELKLayoutTest extends SiriusDiagramTestCase {
    private static final String PATH = "/data/unit/layout/withELK/";

    private static final String PATH_REPLACE = "/data/unit/layout/withELK/replace/";

    private static final String VSM_RESOURCE_NAME = "My.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "representations.aird";

    private DDiagram diagram;

    private IDiagramWorkbenchPart editorPart;

    private boolean initialSnapToGridValue;

    private double initialGridSpacingValue;

    private int initialRulerUnitValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + VSM_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + REPRESENTATIONS_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        SessionUIManager.INSTANCE.createUISession(session);
    }

    @Override
    protected void tearDown() throws Exception {
        if (editorPart != null) {
            SessionUIManager.INSTANCE.getUISession(session).closeEditors(false, Collections.singleton((DDiagramEditor) editorPart));
        }
        TestsUtil.emptyEventsFromUIThread();
        super.tearDown();
    }

    /**
     * Makes sure that activating the Snap to grid has no effect on the ELK layout.
     */
    public void testArrangeWithSnapToWithELK() {
        // We create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        DRepresentation representation = createRepresentation("SimpleDiagram", root);
        // We open the editor and set the preferences for the test.
        IEditorPart newEditorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        IPreferenceStore workspaceViewerPreferenceStore = ((DiagramGraphicalViewer) ((DiagramEditor) newEditorPart).getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore();
        changeSnapToPreferences(workspaceViewerPreferenceStore);
        try {
            arrangeAll((DiagramEditor) newEditorPart);
            TestsUtil.synchronizationWithUIThread();
            // We keep the figures bounds after the arrange all without the Snap to grid.
            Map<DNode, Rectangle> DNodes2Bounds = computeNodesBounds(representation);
            // We activate the Snap to Grid.
            workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, true);
            // We perform the arrange all again.
            arrangeAll((DiagramEditor) newEditorPart);
            TestsUtil.synchronizationWithUIThread();
            // We check that the layout did not change
            Map<DNode, Rectangle> afterDNodes2Bounds = computeNodesBounds(representation);
            afterDNodes2Bounds.forEach((dNode, rect) -> {
                assertEquals("The layout should not change after having activated the snap to grid with ELK algorithm.", DNodes2Bounds.get(dNode), rect);
            });
        } finally {
            restoreInitilaPreferences(workspaceViewerPreferenceStore);
        }
    }

    /**
     * Makes sure that activating the Snap to grid has effect on diagram without ELK algorithm
     */
    public void testArrangeWithSnapToWithoutELK() {
        // We create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        DRepresentation representation = createRepresentation("SimpleDiagramNoELK", root);
        // We open the editor and set the preferences for the test.
        IEditorPart newEditorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        IPreferenceStore workspaceViewerPreferenceStore = ((DiagramGraphicalViewer) ((DiagramEditor) newEditorPart).getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore();
        changeSnapToPreferences(workspaceViewerPreferenceStore);
        try {
            arrangeAll((DiagramEditor) newEditorPart);
            TestsUtil.synchronizationWithUIThread();
            // We keep the figures bounds after the arrange all without the Snap to grid.
            Map<DNode, Rectangle> dNodes2Bounds = computeNodesBounds(representation);
            // We activate the Snap to Grid.
            workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, true);
            // We perform the arrange all again.
            arrangeAll((DiagramEditor) newEditorPart);
            TestsUtil.synchronizationWithUIThread();
            // We check that the layout has changed
            Map<DNode, Rectangle> afterDNodes2Bounds = computeNodesBounds(representation);
            boolean atLeastOneElementHasChanged = false;
            for (Iterator<Entry<DNode, Rectangle>> iterator = afterDNodes2Bounds.entrySet().iterator(); iterator.hasNext();) {
                Entry<DNode, Rectangle> dNodeToRect = iterator.next();
                if (!dNodeToRect.getValue().equals(dNodes2Bounds.get(dNodeToRect.getKey()))) {
                    atLeastOneElementHasChanged = true;
                    break;
                }
            }
            assertTrue("The activation of the Snap to grid should have changed the layout", atLeastOneElementHasChanged);
        } finally {
            restoreInitilaPreferences(workspaceViewerPreferenceStore);
        }
    }

    /**
     * Check that the size of a Node under the root (under the diagram), is sufficiently large to read the label.
     */
    public void testSizeOfRootNode() {
        openDiagram("simpleDiagram");

        Optional<DDiagramElement> c1Dde = diagram.getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass1")).findFirst();
        assertTrue("The diagram should have a node named \"MyClass1\".", c1Dde.isPresent());
        IGraphicalEditPart c1EditPart = getEditPart(c1Dde.get());
        assertTrue("The node for \"MyClass1\" should be a DNodeEditPart.", c1EditPart instanceof DNodeEditPart);
        Dimension minimumTextSize = ((DNodeEditPart) c1EditPart).getNodeLabel().getPreferredSize();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the new Size is bigger than the minimum size to display the text
        Dimension c1Dimension = c1EditPart.getFigure().getSize();
        assertTrue("The size of \"MyClass1\" should be sufficiently large to read the label (minimul label size is " + minimumTextSize + " and node size is " + c1Dimension + ".",
                c1Dimension.contains(minimumTextSize));
    }

    /**
     * Check that the Note is moved with an arrange using ELK, when the preference "Move unlinked notes during layout"
     * is enabled.
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testNoteLayoutWithPrefTrue() throws Exception {
        testNoteLayoutAccordingToPref(true);
    }

    /**
     * Check that the Note is not moved with an arrange using ELK, when the preference "Move unlinked notes during
     * layout" is disabled.
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testNoteLayoutWithPrefFalse() throws Exception {
        testNoteLayoutAccordingToPref(false);
    }

    /**
     * Check that the size of a Note is the same before and after an arrange.
     */
    @SuppressWarnings("rawtypes")
    public void testNoteHaveFixedSizeAfterLayout() {
        openDiagram("simpleDiagramWithNote");

        // Get the GMF node corresponding to the Note
        Node noteNode = getNote(editorPart.getDiagram());
        assertTrue("One note should exist on the diagram", noteNode != null);

        // Get the corresponding edit part
        Map editPartRegistry = editorPart.getDiagramEditPart().getRoot().getViewer().getEditPartRegistry();
        final IGraphicalEditPart noteEditPart = (IGraphicalEditPart) editPartRegistry.get(noteNode);

        // Get the initial note bounds (to be compare to the new bounds after the layout)
        final Rectangle initialNoteBounds = noteEditPart.getFigure().getBounds().getCopy();

        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), true);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the Note has been moved during the arrange
        Rectangle currentNoteBounds = noteEditPart.getFigure().getBounds().getCopy();
        assertFalse("The Note should be moved during the arrange.", initialNoteBounds.getLocation().equals(currentNoteBounds.getLocation()));

        // Check that the size of the Note is the same after the arrange
        assertEquals("The Note should have the same size before and after the arrange.", initialNoteBounds.getSize(), (currentNoteBounds.getSize()));
    }

    /**
     * Check that the size of a Text is the same before and after an arrange.
     */
    @SuppressWarnings("rawtypes")
    public void testTextHaveFixedSizeAfterLayout() {
        openDiagram("simpleDiagramWithText");

        // Get the GMF node corresponding to the Note
        Node textNode = getText(editorPart.getDiagram());
        assertTrue("One test should exist on the diagram", textNode != null);

        // Get the corresponding edit part
        Map editPartRegistry = editorPart.getDiagramEditPart().getRoot().getViewer().getEditPartRegistry();
        final IGraphicalEditPart textEditPart = (IGraphicalEditPart) editPartRegistry.get(textNode);

        // Get the initial text bounds (to be compare to the new bounds after the layout)
        final Rectangle initialTextBounds = textEditPart.getFigure().getBounds().getCopy();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the Text has been moved during the arrange
        Rectangle currentTextBounds = textEditPart.getFigure().getBounds().getCopy();
        assertFalse("The Text should be moved during the arrange.", initialTextBounds.getLocation().equals(currentTextBounds.getLocation()));

        // Check that the size of the Note is the same after the arrange
        assertEquals("The Text should have the same size before and after the arrange.", initialTextBounds.getSize(), currentTextBounds.getSize());
    }

    /**
     * Check that the location of the label is centered under the bottom side of its border node.
     */
    public void testLocationOfLabelOnBorderOnBorderNode() {
        openDiagram("diagramWithBorderNodesWithLabelOnBorder");

        Optional<DDiagramElement> dde = diagram.getDiagramElements().stream().filter(ode -> ode.getName().equals("att1")).findFirst();
        assertTrue("The diagram should have a node named \"att1\".", dde.isPresent());
        IGraphicalEditPart portEditPart = getEditPart(dde.get());
        assertTrue("The node for \"att1\" should be a AbstractDiagramBorderNodeEditPart but was a " + portEditPart.getClass().getSimpleName(),
                portEditPart instanceof AbstractDiagramBorderNodeEditPart);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        Rectangle borderNodeBounds = portEditPart.getFigure().getBounds().getCopy();

        // Check the label location
        boolean labelFound = false;
        for (Object portChildObj : portEditPart.getChildren()) {
            if (portChildObj instanceof IGraphicalEditPart) {
                IFigure labelFigure = ((IGraphicalEditPart) portChildObj).getFigure();
                String text = null;
                if (labelFigure instanceof WrappingLabel) {
                    text = ((WrappingLabel) labelFigure).getText();
                } else if (labelFigure instanceof Label) {
                    text = ((Label) labelFigure).getText();
                } else if (labelFigure instanceof SiriusWrapLabel) {
                    SiriusWrapLabel label = (SiriusWrapLabel) labelFigure;
                    text = label.getText();
                }

                if (text != null) {
                    labelFound = true;
                    Rectangle labelBounds = labelFigure.getBounds();
                    assertEquals("The label of the border node is visually not horizontally centered on its border node (draw2d x coordinate).", labelBounds.getCenter().x(),
                            borderNodeBounds.getCenter().x());
                    assertEquals("The label of the border node is visually not under the bottom side of its border node (draw2d y coordinate).", labelBounds.getTop().y(),
                            borderNodeBounds.getBottom().y() + 1);
                    assertTrue(((IGraphicalEditPart) portChildObj).getModel() instanceof Node);
                    Node labelNode = (Node) ((IGraphicalEditPart) portChildObj).getModel();
                    Location gmfLabelLocation = (Location) labelNode.getLayoutConstraint();
                    assertEquals("The x GMF coordinate of the label of the border node does not correspond to a centered location.", -(labelBounds.width() - borderNodeBounds.width()) / 2,
                            gmfLabelLocation.getX(), 1);
                    assertEquals("The y GMF coordinate of the label of the border node does not correspond to a location under its border node.", borderNodeBounds.height() + 1,
                            gmfLabelLocation.getY());
                }
            }
            if (!labelFound) {
                fail("The label of the border node has not been found.");
            }
        }

    }

    /**
     * Check that the height of a ListContainer is sufficient to display the list items (when list items are only icons
     * without text).
     */
    public void testListContainerWithIconListItemsLayout() {
        openDiagram("diagramWithListWithIconListItems");

        // Check for a list in the diagram
        checkForListContainerWithIconListItems("L_MyClass1");
        // Check for a list in a container
        checkForListContainerWithIconListItems("MyClass1");
    }

    private void checkForListContainerWithIconListItems(String listName) {
        Optional<DDiagramElement> dde = diagram.getDiagramElements().stream().filter(ode -> ode.getName().equals(listName)).findFirst();
        assertTrue("The diagram should have a node named \"" + listName + "\".", dde.isPresent());
        IGraphicalEditPart editPart = getEditPart(dde.get());
        assertTrue("The node for \"" + listName + "\" should be a AbstractDiagramListEditPart but was a " + editPart.getClass().getSimpleName(), editPart instanceof AbstractDiagramListEditPart);

        assertEquals("Wrong number of list items, the list should contain 3 list items.", 3, ((DNodeList) dde.get()).getOwnedElements().stream().count());

        Dimension listItemSize = getEditPart(((DNodeList) dde.get()).getOwnedElements().get(0)).getFigure().getSize();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        Dimension listSize = editPart.getFigure().getSize();

        assertTrue("The height of the list should be at least bigger that thrice the size of a list item + 20 pixels for the title. Expected more than " + (20 + (3 * listItemSize.height()))
                + " but was " + listSize.height(), listSize.height() > (20 + (3 * listItemSize.height())));
    }

    /**
     * Check that the size of a ListContainer is OK:
     * <UL>
     * <LI>Size is long enough to avoid wrap label of list items</LI>
     * <LI>Incoming edges have no bendpoint</LI>
     * <LI>Size if not too big</LI>
     * </UL>
     */
    public void testListContainerLayout() {
        openDiagram("diagramWithList");

        Optional<DDiagramElement> c2Dde = diagram.getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass2")).findFirst();
        assertTrue("The diagram should have a node named \"MyClass2\".", c2Dde.isPresent());
        IGraphicalEditPart c2EditPart = getEditPart(c2Dde.get());
        assertTrue("The node for \"MyClass2\" should be a DNodeListEditPart.", c2EditPart instanceof DNodeListEditPart);

        Optional<DNodeListElement> listItem = ((DNodeList) c2Dde.get()).getOwnedElements().stream().filter(dde -> dde.getName().equals("listItemWithALongName")).findFirst();
        assertTrue("The container \"MyClass2\" should have a list item named \"listItemWithALongName\".", listItem.isPresent());
        IGraphicalEditPart listItemEditPart = getEditPart(listItem.get());
        assertTrue("The node for \"listItemWithALongName\" should be a DNodeListElementEditPart.", listItemEditPart instanceof DNodeListElementEditPart);
        int expectedOneLineHeight = ((DNodeListElementEditPart) listItemEditPart).getFigure().getSize().height();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the new size of list item is sufficiently large to display the label without wrapping.
        assertEquals("The list item should be on one line (with one line height)", expectedOneLineHeight, ((DNodeListElementEditPart) listItemEditPart).getFigure().getSize().height());

        // Check that the new size of the list if not too big (around 2x the size of one line height : a delta of 20
        // pixels for all margins).
        assertEquals("The list should not be too high (arround 2x the size of one line height)", 2 * expectedOneLineHeight, c2EditPart.getFigure().getSize().height(), 20);

        // Check that incoming edge has only 2 points (ie without intermediate bendpoints)
        assertEquals("The container \"MyClass2\" should have one incoming edge", 1, c2EditPart.getTargetConnections().size());
        assertTrue("The container \"MyClass2\" should have one incoming edge of kind DEdgeEditPart", c2EditPart.getTargetConnections().get(0) instanceof DEdgeEditPart);
        DEdgeEditPart edgeEditPart = (DEdgeEditPart) c2EditPart.getTargetConnections().get(0);
        assertTrue("The edge figure should be a PolylineConnectionEx", edgeEditPart.getFigure() instanceof PolylineConnectionEx);
        assertEquals("The edge should have only 2 points (ie without intermediate bendpoints)", 2, ((PolylineConnectionEx) edgeEditPart.getFigure()).getPoints().size());
    }

    /**
     * Check that the size of an empty ListContainer without title is OK (ie same size as default Sirius Size : 40x40).
     */
    public void testEmptyListContainerMinimalSizeLayout() {
        openDiagram("diagramWithEmptyListWithoutTitle");

        Optional<DDiagramElement> dde = diagram.getDiagramElements().stream().findFirst();
        assertTrue("The diagram should have at least one node.", dde.isPresent());
        IGraphicalEditPart editPart = getEditPart(dde.get());
        assertTrue("The first node should be a DNodeListEditPart.", editPart instanceof DNodeListEditPart);

        assertEquals("Wrong number of list items, the list should be empty.", 0, ((DNodeList) dde.get()).getOwnedElements().stream().count());

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the new size is the default one
        // (org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart.getDefaultDimension(DDiagramElement)
        // ie LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.
        assertEquals("The width of the list should be the default Sirius one (to have a minimal size for empty list)", LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width,
                ((DNodeListEditPart) editPart).getFigure().getSize().width());
        assertEquals("The height of the list should be the default Sirius one (to have a minimal size for empty list)", LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height,
                ((DNodeListEditPart) editPart).getFigure().getSize().height());
    }

    /**
     * Check the layout of a container with VStack layout.
     */
    public void testVStackContainerLayout() {
        openDiagram("diagramWithRegions");

        Optional<DDiagramElement> vStackContainer = diagram.getDiagramElements().stream().filter(dde -> dde.getName().equals("root_V")).findFirst();
        assertTrue("The diagram should have a node named \"root_V\".", vStackContainer.isPresent());
        IGraphicalEditPart vStackContainerEditPart = getEditPart(vStackContainer.get());
        assertTrue("The node for \"root_V\" should be an AbstractDiagramContainerEditPart.", vStackContainerEditPart instanceof AbstractDiagramContainerEditPart);

        Optional<DDiagramElement> c2Dde = ((DNodeContainer) vStackContainer.get()).getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass2")).findFirst();
        assertTrue("The container \"root_V\" should have a region named \"MyClass2\".", c2Dde.isPresent());
        IGraphicalEditPart c2EditPart = getEditPart(c2Dde.get());
        assertTrue("The node for \"MyClass2\" should be a AbstractDiagramContainerEditPart.", c2EditPart instanceof AbstractDiagramContainerEditPart);
        int expectedOneLineHeight = ((AbstractDiagramContainerEditPart) c2EditPart).getNodeLabel().getSize().height();
        int expectedOneLineWidth = ((AbstractDiagramContainerEditPart) c2EditPart).getNodeLabel().getSize().width();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the new size of list item is sufficiently large to display the label without wrapping : one line
        // label height + label offset + 1 pixel for the separator between 2 regions.
        int expectedRegionHeight = Math.max(expectedOneLineHeight + IContainerLabelOffsets.LABEL_OFFSET + 1, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height);
        int currentLineHeight = ((AbstractDiagramContainerEditPart) c2EditPart).getFigure().getSize().height();
        assertTrue("The empty region should be on one line (with at least one line height; more than " + expectedRegionHeight + " but was " + currentLineHeight + ")",
                currentLineHeight >= expectedRegionHeight);

        // The GMF size of VStack container should be "auto-size" ie {-1,-1}
        assertTrue(vStackContainerEditPart.getNotationView() instanceof Node);
        Node vStackNode = (Node) vStackContainerEditPart.getNotationView();
        LayoutConstraint layoutConstraint = vStackNode.getLayoutConstraint();
        assertTrue(layoutConstraint instanceof Bounds);
        Bounds bounds = (Bounds) layoutConstraint;
        assertEquals("The width of the VStack container should be \"auto-sized\" after an arrange all.", -1, bounds.getWidth());
        assertEquals("The height of the VStack container should be \"auto-sized\" after an arrange all.", -1, bounds.getHeight());

        // Check that the new size of the VStack compartment if not too big (around 5x the size of one region height : a
        // delta of 20 pixels for all margins).
        assertEquals("The VStack container should not be too high (arround 5x the size of one line height)", 5 * expectedRegionHeight, vStackContainerEditPart.getFigure().getSize().height(), 20);

        // Check width
        assertEquals("The region size should fit the label size", expectedOneLineWidth, c2EditPart.getFigure().getSize().width(), 10);

        // Check width, height, x and y location of each region (GMF bounds): Same width for each region, same height
        // for each region, x==0 for each region, y==0 for first region and other regions below
        assertEquals(2, ((AbstractDiagramContainerEditPart) vStackContainerEditPart).getChildren().size());
        Object compartmentEditPart = ((AbstractDiagramContainerEditPart) vStackContainerEditPart).getChildren().get(1);
        assertTrue(compartmentEditPart instanceof AbstractDNodeContainerCompartmentEditPart);
        int previousRegionWidth = 0;
        int previousRegionHeight = 0;
        int previousY = 0;
        for (Object child : ((AbstractDNodeContainerCompartmentEditPart) compartmentEditPart).getChildren()) {
            if (child instanceof AbstractDiagramContainerEditPart) {
                AbstractDiagramContainerEditPart regionEditPart = (AbstractDiagramContainerEditPart) child;
                assertTrue(regionEditPart.getNotationView() instanceof Node);
                Node regionNode = (Node) regionEditPart.getNotationView();
                LayoutConstraint regionLayoutConstraint = regionNode.getLayoutConstraint();
                assertTrue(regionLayoutConstraint instanceof Bounds);
                Bounds regionBounds = (Bounds) regionLayoutConstraint;
                assertEquals("x coordinate of each region should be 0", 0, regionBounds.getX());
                assertEquals("Each region should be below the previous, wrong location for " + ((DNodeContainer) regionEditPart.resolveSemanticElement()).getName(), previousY + previousRegionHeight,
                        regionBounds.getY());
                previousY = regionBounds.getY();
                if (previousRegionWidth == 0) {
                    previousRegionWidth = regionBounds.getWidth();
                } else {
                    assertEquals("Each region should have the same width, width of \"" + ((DNodeContainer) regionEditPart.resolveSemanticElement()).getName()
                            + "\" is not the same than the previous region", previousRegionWidth, regionBounds.getWidth());
                }
                if (previousRegionHeight == 0) {
                    previousRegionHeight = regionBounds.getHeight();
                } else {
                    assertEquals("Each region should have the same height, height of \"" + ((DNodeContainer) regionEditPart.resolveSemanticElement()).getName()
                            + "\" is not the same than the previous region", previousRegionHeight, regionBounds.getHeight());
                    previousRegionHeight = regionBounds.getHeight();
                }
            }
        }
    }

    /**
     * Check that the "authorized side" constraint of a border node is considered in ELK layout.
     */
    public void testBorderNodeLayoutWithOneAuthorizedSide() {
        openDiagram("diagramWithBorderNodesWithOneAuthorizedSide");

        // Initialization checks
        Optional<DDiagramElement> p1Dde = diagram.getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("p1")).findFirst();
        assertTrue("The diagram should have an element named \"p1\".", p1Dde.isPresent());
        assertTrue("The diagram should have a node named \"p1\".", p1Dde.get() instanceof DNode);

        Optional<DDiagramElement> p2Dde = diagram.getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("p2")).findFirst();
        assertTrue("The diagram should have an element named \"p2\".", p2Dde.isPresent());
        assertTrue("The diagram should have a node named \"p2\".", p2Dde.get() instanceof DNode);

        List<DNode> borderNodesOfP1 = ((DNode) p1Dde.get()).getOwnedBorderedNodes();
        assertEquals("\"p1\" should have one border node.", 1, borderNodesOfP1.size());

        List<DNode> borderNodesOfP2 = ((DNode) p2Dde.get()).getOwnedBorderedNodes();
        assertEquals("\"p2\" should have one border node.", 1, borderNodesOfP2.size());

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the figure of border node of p1 is above them from 2 pixels.
        IGraphicalEditPart p1EditPart = getEditPart(p1Dde.get());
        IGraphicalEditPart borderNodeOfp1EditPart = getEditPart(borderNodesOfP1.get(0));
        Rectangle borderNodeOfp1Bounds = borderNodeOfp1EditPart.getFigure().getBounds();
        int p1Delta = borderNodeOfp1Bounds.height() - IBorderItemOffsets.DEFAULT_OFFSET.height();
        assertEquals("The y location of p1 should be 2 pixels lower that its border node as border node is constaint on North side.", p1EditPart.getFigure().getBounds().y() - p1Delta,
                borderNodeOfp1Bounds.y());
        // Check that GMF coordinates also reflect that
        assertTrue(borderNodeOfp1EditPart.getModel() instanceof Node);
        Node borderNodeOfp1Node = (Node) borderNodeOfp1EditPart.getModel();
        Location gmfLocation1 = (Location) borderNodeOfp1Node.getLayoutConstraint();
        assertEquals("The y GMF coordinate of the the border node of p1 is wrong.", -p1Delta, gmfLocation1.getY());
        // Check that the figure of border node of p2 is below them from 2 pixels.
        IGraphicalEditPart p2EditPart = getEditPart(p2Dde.get());
        IGraphicalEditPart borderNodeOfp2EditPart = getEditPart(borderNodesOfP2.get(0));
        Rectangle p2Bounds = p2EditPart.getFigure().getBounds();
        Rectangle borderNodeOfp2Bounds = borderNodeOfp2EditPart.getFigure().getBounds();
        int p2Delta = borderNodeOfp2Bounds.height() - IBorderItemOffsets.DEFAULT_OFFSET.height();
        assertEquals("The bottom location of p2 should be 2 pixels upper that its border node as border node is constaint on South side.", p2Bounds.getBottom().y() + p2Delta,
                borderNodeOfp2Bounds.getBottom().y());
        // Check that GMF coordinates also reflect that
        assertTrue(borderNodeOfp2EditPart.getModel() instanceof Node);
        Node borderNodeOfp2Node = (Node) borderNodeOfp2EditPart.getModel();
        Location gmfLocation2 = (Location) borderNodeOfp2Node.getLayoutConstraint();
        assertEquals("The y GMF coordinate of the the border node of p2 is wrong.", p2Bounds.height() - IBorderItemOffsets.DEFAULT_OFFSET.height(), gmfLocation2.getY());
    }

    /**
     * Check that when all sides are "authorized", a classical ELK layout (from left to right) is done.
     */
    public void testBorderNodeLayoutWithAllAuthorizedSides() {
        openDiagram("diagramWithBorderNodesWithAllAuthorizedSides");

        // Initialization checks
        Optional<DDiagramElement> p1Dde = diagram.getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("p1")).findFirst();
        assertTrue("The diagram should have an element named \"p1\".", p1Dde.isPresent());
        assertTrue("The diagram should have a node named \"p1\".", p1Dde.get() instanceof DNode);

        Optional<DDiagramElement> p2Dde = diagram.getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("p2")).findFirst();
        assertTrue("The diagram should have an element named \"p2\".", p2Dde.isPresent());
        assertTrue("The diagram should have a node named \"p2\".", p2Dde.get() instanceof DNode);

        List<DNode> borderNodesOfP1 = ((DNode) p1Dde.get()).getOwnedBorderedNodes();
        assertEquals("\"p1\" should have one border node.", 1, borderNodesOfP1.size());

        List<DNode> borderNodesOfP2 = ((DNode) p2Dde.get()).getOwnedBorderedNodes();
        assertEquals("\"p2\" should have one border node.", 1, borderNodesOfP2.size());

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check that the figure of border node of p1 is above them from 2 pixels.
        IGraphicalEditPart p1EditPart = getEditPart(p1Dde.get());
        IGraphicalEditPart borderNodeOfp1EditPart = getEditPart(borderNodesOfP1.get(0));
        Rectangle p1Bounds = p1EditPart.getFigure().getBounds();
        Rectangle borderNodeOfp1Bounds = borderNodeOfp1EditPart.getFigure().getBounds();
        int p1Delta = borderNodeOfp1Bounds.width() - IBorderItemOffsets.DEFAULT_OFFSET.height();
        assertEquals("The right location of p1 should be 2 pixels lefter that its border node as border node has no constraint (East side is used).", p1Bounds.getRight().x() + p1Delta,
                borderNodeOfp1Bounds.getRight().x());
        // Check that GMF coordinates also reflect that
        assertTrue(borderNodeOfp1EditPart.getModel() instanceof Node);
        Node borderNodeOfp1Node = (Node) borderNodeOfp1EditPart.getModel();
        Location gmfLocation1 = (Location) borderNodeOfp1Node.getLayoutConstraint();
        assertEquals("The x GMF coordinate of the the border node of p1 is wrong.", p1Bounds.width() - IBorderItemOffsets.DEFAULT_OFFSET.height(), gmfLocation1.getX());
        // Check that the figure of border node of p2 is below them from 2 pixels.
        IGraphicalEditPart p2EditPart = getEditPart(p2Dde.get());
        IGraphicalEditPart borderNodeOfp2EditPart = getEditPart(borderNodesOfP2.get(0));
        Rectangle p2Bounds = p2EditPart.getFigure().getBounds();
        Rectangle borderNodeOfp2Bounds = borderNodeOfp2EditPart.getFigure().getBounds();
        int p2Delta = borderNodeOfp2Bounds.height() - IBorderItemOffsets.DEFAULT_OFFSET.height();
        assertEquals("The x location of p2 should be 2 pixels righter that its border node as border node has no constraint (West side is used).", p2Bounds.x() - p2Delta, borderNodeOfp2Bounds.x());
        // Check that GMF coordinates also reflect that
        assertTrue(borderNodeOfp2EditPart.getModel() instanceof Node);
        Node borderNodeOfp2Node = (Node) borderNodeOfp2EditPart.getModel();
        Location gmfLocation2 = (Location) borderNodeOfp2Node.getLayoutConstraint();
        assertEquals("The x GMF coordinate of the the border node of p2 is wrong.", -p2Delta, gmfLocation2.getX());
    }

    /**
     * Makes sure that the result of an arrange all respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>There is no scrollbar on all containers</LI>
     * <LI>All the containers's contents correctly layouted</LI>
     * <UL>
     */
    public void testArrangeAllResult() {
        testArrangeAllResult("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange all respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>There is no scrollbar on all containers</LI>
     * <LI>All the containers's contents correctly layouted</LI>
     * <UL>
     */
    public void testArrangeAllResultOfDiagramWithOneChild() {
        openDiagram("diagramWithContainerWithOnlyOneChild");

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Assert that the bounding box coordinates of all elements are {20, 20}
        // Compute primary edit parts (first level edit parts of the container)
        List<?> primaryEditParts = getPrimaryEditParts(editorPart.getDiagramEditPart());
        List<IGraphicalEditPart> primaryGraphicalEditParts = Lists.newArrayList(Iterables.filter(primaryEditParts, IGraphicalEditPart.class));
        Rectangle boundingbox = DiagramImageUtils.calculateImageRectangle(primaryGraphicalEditParts, 0, new Dimension(0, 0));
        assertEquals("Wrong x coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.x());
        assertEquals("Wrong y coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.y());

        // Assert that there is no scroll bar on all containers
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p1"));

        // Assert that content of all containers is "correctly layouted"
        assertAlignCentered(50, "Class1_1", "Class1_2");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnOneContainerInDiagram() {
        testArrangeSelectionResultOnOneContainerInDiagram("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnOneContainerInAnotherContainer() {
        testArrangeSelectionResultOnOneContainerInAnotherContainer("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnOneContainerWithBorderNode() {
        testArrangeSelectionResultOnOneContainerWithBorderNode("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection on two containers respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The content of the selected container is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnTwoContainers() {
        testArrangeSelectionResultOnTwoContainers("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some of its children respect the following
     * rules:
     * <UL>
     * <LI>Same rules of arrange selection on only the container</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnOneContainerAndSomeOfItsChildren() {
        testArrangeSelectionResultOnOneContainerAndSomeOfItsChildren("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection of some children of a container respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnSomeContainerChildren() {
        testArrangeSelectionResultOnSomeContainerChildren("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection of some children of a container (contained in another
     * container) respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnSomeContainerChildren_ContainedInAContainer() {
        testArrangeSelectionResultOnSomeContainerChildren_ContainedInAContainer("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some children of other container respect
     * the following rules:
     * <UL>
     * <LI>No rules: No layout is perform as this kind of arrange selection is forbidden (see comment in method
     * org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getTargetEditPartForArrangeSelection(List)).</LI>
     * <UL>
     */
    public void testArrangeSelectionResultOnAContainerAndSomeChildrenOfOtherConainer() {
        testArrangeSelectionResultOnAContainerAndSomeChildrenOfOtherConainer("diagramWithContainer");
    }

    /**
     * Makes sure that the result of an arrange all respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>There is no scrollbar on all containers</LI>
     * <LI>All the containers's contents correctly layouted</LI>
     * <UL>
     */
    public void testArrangeAllResultWithScroll() {
        testArrangeAllResult("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnOneContainerInDiagram() {
        testArrangeSelectionResultOnOneContainerInDiagram("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnOneContainerInAnotherContainer() {
        testArrangeSelectionResultOnOneContainerInAnotherContainer("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnOneContainerWithBorderNode() {
        testArrangeSelectionResultOnOneContainerWithBorderNode("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection on two containers respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The content of the selected container is correctly layouted</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnTwoContainers() {
        testArrangeSelectionResultOnTwoContainers("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some of its children respect the following
     * rules:
     * <UL>
     * <LI>Same rules of arrange selection on only the container</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnOneContainerAndSomeOfItsChildren() {
        testArrangeSelectionResultOnOneContainerAndSomeOfItsChildren("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection of some children of a container respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnSomeContainerChildren() {
        testArrangeSelectionResultOnSomeContainerChildren("diagramWithContainerAndScroll");
    }


    /**
     * Makes sure that the result of an arrange selection of some children of a container respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnSomeContainerChildrenn_ContainedInAContainer() {
        testArrangeSelectionResultOnSomeContainerChildren_ContainedInAContainer("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some children of other container respect
     * the following rules:
     * <UL>
     * <LI>No rules: No layout is perform as this kind of arrange selection is forbidden (see comment in method
     * org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getTargetEditPartForArrangeSelection(List)).</LI>
     * <UL>
     */
    public void testArrangeSelectionResultWithScrollOnAContainerAndSomeChildrenOfOtherConainer() {
        testArrangeSelectionResultOnAContainerAndSomeChildrenOfOtherConainer("diagramWithContainerAndScroll");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnOneContainerInDiagram(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart editPart = getEditPart("p1");
        Point locationOfP1BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p1
        assertNoVisibleScrollBar((IDiagramContainerEditPart) editPart);

        // Assert that the location of the container is the same before and after the layout
        assertEquals("The location of the container should be the same before and after the layout.", locationOfP1BeforeLayout, editPart.getFigure().getBounds().getTopLeft());

        // Assert content is layouted
        assertAlignCentered(50, "Class1_1", "Class1_2");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnOneContainerInAnotherContainer(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart editPart = getEditPart("p2_2");
        Point locationOfP22BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p2_2
        assertNoVisibleScrollBar((IDiagramContainerEditPart) editPart);

        // Assert that the location of the container is the same before and after the layout
        assertEquals("The location of the container should be the same before and after the layout.", locationOfP22BeforeLayout, editPart.getFigure().getBounds().getTopLeft());

        // Assert content is layouted
        assertAlignCentered(50, "Class2_2_1", "Class2_2_2");
    }

    /**
     * Makes sure that the result of an arrange selection on one container respect the following rules:
     * <UL>
     * <LI>No scroll bar in the container (container resized)</LI>
     * <LI>Container is not moved</LI>
     * <LI>Container's content is correctly layouted</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnOneContainerWithBorderNode(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart editPart = getEditPart("p4");
        Point locationOfP4BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p1
        assertNoVisibleScrollBar((IDiagramContainerEditPart) editPart);

        // Assert that the location of the container is the same before and after the layout
        assertEquals("The location of the container should be the same before and after the layout.", locationOfP4BeforeLayout, editPart.getFigure().getBounds().getTopLeft());

        // Assert content is layouted
        assertAlignCentered(50, "Class4_1", "Class4_2");
    }

    /**
     * Makes sure that the result of an arrange selection on two containers respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The content of the selected container is correctly layouted</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnTwoContainers(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart p1EditPart = getEditPart("p1");
        IGraphicalEditPart p3EditPart = getEditPart("p3");
        Point topLeftCornerBeforeLayout = getTopLeftCorner(p1EditPart, p3EditPart);

        // Launch an arrange selection
        arrangeSelection(p1EditPart, p3EditPart);

        // Assert that the top-left corner of bounding box remains the same
        assertEquals("The top-left corner of the bounding box of layouted elements should remain the same.", topLeftCornerBeforeLayout, getTopLeftCorner(p1EditPart, p3EditPart));

        // Assert that p1 and p3 is layouted according to each other
        assertAlignCentered(50, "p1", "p3");

        // Assert that content of all containers is "correctly layouted"
        assertAlignCentered(50, "Class1_1", "Class1_2");
        assertAlignCentered(50, "Class3_1", "Class3_2", "Class3_3", "Class3_4");
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some of its children respect the following
     * rules:
     * <UL>
     * <LI>Same rules of arrange selection on only the container</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnOneContainerAndSomeOfItsChildren(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart p2EditPart = getEditPart("p2");
        Point locationOfP2BeforeLayout = p2EditPart.getFigure().getBounds().getTopLeft();
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1");
        IGraphicalEditPart class23EditPart = getEditPart("Class2_3");

        // Launch an arrange selection
        arrangeSelection(p2EditPart, class21EditPart, class23EditPart);

        // Assert that there is no scroll bar on p2
        assertNoVisibleScrollBar((IDiagramContainerEditPart) p2EditPart);

        // Assert that the location of the container is the same before and after the layout
        assertEquals("The location of the container should be the same before and after the layout.", locationOfP2BeforeLayout, p2EditPart.getFigure().getBounds().getTopLeft());

        // Assert content is layouted
        assertAlignCentered(50, "p2_2", "Class2_1", "Class2_2", "Class2_3");
    }

    /**
     * Makes sure that the result of an arrange selection of some children of a container respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnSomeContainerChildren_ContainedInAContainer(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart p22EditPart = getEditPart("p2_2");
        Rectangle boundsOfP22BeforeLayout = p22EditPart.getFigure().getBounds().getCopy();
        IGraphicalEditPart class222EditPart = getEditPart("Class2_2_2");
        IGraphicalEditPart class221EditPart = getEditPart("Class2_2_1");
        Point topLeftCornerBeforeLayout = getTopLeftCorner(class221EditPart, class222EditPart);

        // Launch an arrange selection
        arrangeSelection(class222EditPart, class221EditPart);

        // Assert that the top-left corner of bounding box remains the same
        assertEquals("The top-left corner of the bounding box of layouted elements should remain the same.", topLeftCornerBeforeLayout, getTopLeftCorner(class221EditPart, class222EditPart));

        // Assert content is layouted
        assertAlignCentered(50, "Class2_2_1", "Class2_2_2");

        // Assert that the location and the size of the container is the same before and after the layout
        assertEquals("The location and the size of the container should be the same before and after the layout.", boundsOfP22BeforeLayout, p22EditPart.getFigure().getBounds());
    }

    /**
     * Makes sure that the result of an arrange selection of some children of a container respect the following rules:
     * <UL>
     * <LI>The top-left corner of bounding box of selected elements remains the same</LI>
     * <LI>Selected elements are layouted according to each others (but by ignoring other not selected elements,
     * potential overlap with these elements)</LI>
     * <LI>The container size and location are not changed.</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnSomeContainerChildren(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart p2EditPart = getEditPart("p2");
        Rectangle boundsOfP2BeforeLayout = p2EditPart.getFigure().getBounds().getCopy();
        IGraphicalEditPart class22EditPart = getEditPart("Class2_2");
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1");
        Point topLeftCornerBeforeLayout = getTopLeftCorner(class21EditPart, class22EditPart);

        // Launch an arrange selection
        arrangeSelection(class22EditPart, class21EditPart);

        // Assert that the top-left corner of bounding box remains the same
        assertEquals("The top-left corner of the bounding box of layouted elements should remain the same.", topLeftCornerBeforeLayout, getTopLeftCorner(class21EditPart, class22EditPart));

        // Assert content is layouted
        assertAlignCentered(50, "Class2_1", "Class2_2");

        // Assert that the location and the size of the container is the same before and after the layout
        assertEquals("The location and the size of the container should be the same before and after the layout.", boundsOfP2BeforeLayout, p2EditPart.getFigure().getBounds());
    }

    /**
     * Makes sure that the result of an arrange selection of a container and some children of other container respect
     * the following rules:
     * <UL>
     * <LI>No rules: No layout is perform as this kind of arrange selection is forbidden (see comment in method
     * org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getTargetEditPartForArrangeSelection(List)).</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeSelectionResultOnAContainerAndSomeChildrenOfOtherConainer(String diagramName) {
        openDiagram(diagramName);

        IGraphicalEditPart p1EditPart = getEditPart("p1");
        IGraphicalEditPart class22EditPart = getEditPart("Class2_2");
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1");

        // Keep the figures bounds after the arrange all without pinned elements.
        Map<DNode, Rectangle> DNodes2Bounds = computeNodesBounds(diagram);

        // Launch an arrange selection
        arrangeSelection(p1EditPart, class22EditPart, class21EditPart);

        // Check that the layout is the same (because arrange selection on element not in the same parent has no
        // result).
        Map<DNode, Rectangle> afterDNodes2Bounds = computeNodesBounds(diagram);
        afterDNodes2Bounds.forEach((dNode, rect) -> {
            assertEquals("The layout result should not change after an arrange selection of elements without parent link.", DNodes2Bounds.get(dNode), rect);
        });
    }

    /**
     * Makes sure that the result of an arrange all respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>There is no scrollbar on all containers</LI>
     * <LI>All the containers's contents correctly layouted</LI>
     * <UL>
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeAllResult(String diagramName) {
        openDiagram(diagramName);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Assert that the bounding box coordinates of all elements are {20, 20}
        // Compute primary edit parts (first level edit parts of the container)
        List<?> primaryEditParts = getPrimaryEditParts(editorPart.getDiagramEditPart());
        List<IGraphicalEditPart> primaryGraphicalEditParts = Lists.newArrayList(Iterables.filter(primaryEditParts, IGraphicalEditPart.class));
        Rectangle boundingbox = DiagramImageUtils.calculateImageRectangle(primaryGraphicalEditParts, 0, new Dimension(0, 0));
        assertEquals("Wrong x coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.x());
        assertEquals("Wrong y coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.y());

        // Assert that there is no scroll bar on all containers
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p1"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p2"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p3"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p4"));

        // Assert that content of all containers is "correctly layouted"
        assertAlignCentered(50, "Class1", "Class2", "Class3", "Class4", "p1", "p2", "p3", "p4");
        assertAlignCentered(50, "Class1_1", "Class1_2");
        assertAlignCentered(50, "p2_2", "Class2_1", "Class2_2", "Class2_3");
        assertAlignCentered(50, "Class3_1", "Class3_2", "Class3_3", "Class3_4");
        assertAlignCentered(50, "Class4_1", "Class4_2");
    }


    /**
     * Makes sure that pinned elements do no affect result of layout when using ELK.
     */
    public void testArrangeWithPinnedElements() {
        // Create a new diagram
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        DRepresentation representation = createRepresentation("SimpleDiagram", root);
        // Open the editor
        IEditorPart newEditorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        // Move 3 nodes
        Optional<DDiagramElement> c2Dde = ((DDiagram) representation).getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass2")).findFirst();
        assertTrue("The diagram should have an element named \"MyClass2\".", c2Dde.isPresent());
        Optional<DDiagramElement> c3Dde = ((DDiagram) representation).getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass3")).findFirst();
        assertTrue("The diagram should have an element named \"MyClass3\".", c3Dde.isPresent());
        Optional<DDiagramElement> c4Dde = ((DDiagram) representation).getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass4")).findFirst();
        assertTrue("The diagram should have an element named \"MyClass4\".", c4Dde.isPresent());
        moveEditPart(c2Dde.get(), new Point(100, 50));
        moveEditPart(c3Dde.get(), new Point(100, 50));
        moveEditPart(c4Dde.get(), new Point(100, 50));
        // Arrange the diagram without any pinned elements
        arrangeAll((DiagramEditor) newEditorPart);
        TestsUtil.synchronizationWithUIThread();
        // Keep the figures bounds after the arrange all without pinned elements.
        Map<DNode, Rectangle> DNodes2Bounds = computeNodesBounds(representation);
        // Move the same elements and pin 2 of them.
        moveEditPart(c2Dde.get(), new Point(100, 50));
        moveEditPart(c3Dde.get(), new Point(100, 50));
        moveEditPart(c4Dde.get(), new Point(100, 50));;
        List<DDiagramElement> elementsToPin = new ArrayList<DDiagramElement>();
        elementsToPin.add(c2Dde.get());
        elementsToPin.add(c4Dde.get());
        executeCommand(new PinElementsCommand(elementsToPin));
        // Perform the arrange all again.
        arrangeAll((DiagramEditor) newEditorPart);
        TestsUtil.synchronizationWithUIThread();
        // Check that the layout is the same as without pinned elements
        Map<DNode, Rectangle> afterDNodes2Bounds = computeNodesBounds(representation);
        afterDNodes2Bounds.forEach((dNode, rect) -> {
            assertEquals("The layout result should not change after having pinned some elements.", DNodes2Bounds.get(dNode), rect);
        });
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     */
    public void testArrangeAtCreation1() {
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        assertTrue(root instanceof EPackage);
        EPackage subPackage = ((EPackage) root).getESubpackages().get(1);
        assertEquals("Wrong name for the second subpackage.", "packageForArrangeSelectionTest", subPackage.getName());

        testArrangeAtCreation(subPackage, "DiagramWithContainer");
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     */
    public void testArrangeAtCreation2() {
        testArrangeAtCreation("SimpleDiagram");
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     */
    public void testArrangeAtCreation3() {
        testArrangeAtCreation("DiagramWithBorderNodesWithOneAuthorizedSide");
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     */
    public void testArrangeAtCreation4() {
        EObject root = session.getSemanticResources().stream().findFirst().get().getContents().get(0);
        assertTrue(root instanceof EPackage);
        EPackage subPackage = ((EPackage) root).getESubpackages().get(1);
        assertEquals("Wrong name for the second subpackage.", "packageForArrangeSelectionTest", subPackage.getName());

        testArrangeAtCreation("DiagramWithContainerAndEdges");
    }

    /**
     * Make sure that arrange launched at diagram opening, with new elements created because of the refresh is OK when
     * using ELK.
     */
    public void testArrangeAtOpening1() {
        // Open the diagram, launch an arrange all and close the diagram
        testArrangeAllResult("diagramWithContainer");

        Rectangle boundsOfP1BeforeLayoutAtOpening = getEditPart("p1").getFigure().getBounds().getCopy();
        Rectangle boundsOfP22BeforeLayoutAtOpening = getEditPart("p2_2").getFigure().getBounds().getCopy();
        Rectangle boundsOfP3BeforeLayoutAtOpening = getEditPart("p3").getFigure().getBounds().getCopy();

        SessionUIManager.INSTANCE.getUISession(session).closeEditors(false, Collections.singleton((DDiagramEditor) editorPart));
        TestsUtil.emptyEventsFromUIThread();

        // Modify externally session file (Copy another ecore file with additional semantic elements)
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH_REPLACE + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        try {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException | InterruptedException e) {
            fail(e.getMessage());
        }

        openDiagram("diagramWithContainer");

        // Assert new elements are layouted
        assertAlignCentered(50, "Class5", "Class6");
        assertAlignCentered(50, "Class1_3", "Class1_4", "Class1_5");
        assertAlignCentered(50, "Class3_5", "Class3_6");

        // Assert that top left corner of new layouted elements is OK
        assertEquals(new Point(ResetOriginChangeModelOperation.MARGIN, ResetOriginChangeModelOperation.MARGIN), getTopLeftCorner(getEditPart("Class5"), getEditPart("Class6")));
        // TODO : This top left container corresponds to the insets (it could be computed). But by default, it should be
        // the same location is during an arrange all (further improvement)
        Point expectedContainerTopLeftCorner = new Point(7, 6);
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class1_3"), getEditPart("Class1_4"), getEditPart("Class1_5")));
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class3_5"), getEditPart("Class3_6")));
        // TODO : The top left corner should be the same for container in other container (it is currently not the case)
        // (further improvement)
        expectedContainerTopLeftCorner = new Point(0, 0);
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class2_2_3")));

        // Assert that there is no scroll bar on container (for p1 and p3 the new elements layout is larger than
        // previous)
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p1"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p2_2"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p3"));

        // Assert that the location of the container with new elements are the same before and after the layout
        assertEquals("The location of the container p1 should be the same before and after the layout.", boundsOfP1BeforeLayoutAtOpening.getLocation(),
                getEditPart("p1").getFigure().getBounds().getLocation());
        assertEquals("The location of the container p2_2 should be the same before and after the layout.", boundsOfP22BeforeLayoutAtOpening.getLocation(),
                getEditPart("p2_2").getFigure().getBounds().getLocation());
        assertEquals("The location of the container p3 should be the same before and after the layout.", boundsOfP3BeforeLayoutAtOpening.getLocation(),
                getEditPart("p3").getFigure().getBounds().getLocation());
    }

    /**
     * Make sure that arrange launched at diagram opening, with new elements created because of the refresh is OK when
     * using ELK.
     */
    public void testArrangeAtOpening2() {
        // Open the diagram, launch an arrange all and close the diagram
        testArrangeAllResult("diagramWithContainerAndEdges");

        Rectangle boundsOfP1BeforeLayoutAtOpening = getEditPart("p1").getFigure().getBounds().getCopy();
        Rectangle boundsOfP22BeforeLayoutAtOpening = getEditPart("p2_2").getFigure().getBounds().getCopy();
        Rectangle boundsOfP3BeforeLayoutAtOpening = getEditPart("p3").getFigure().getBounds().getCopy();

        SessionUIManager.INSTANCE.getUISession(session).closeEditors(false, Collections.singleton((DDiagramEditor) editorPart));
        TestsUtil.emptyEventsFromUIThread();

        // Modify externally session file (Copy another ecore file with additional semantic elements)
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH_REPLACE + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        try {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException | InterruptedException e) {
            fail(e.getMessage());
        }

        openDiagram("diagramWithContainerAndEdges");

        // Assert new elements are layouted
        assertAlignCentered(50, "Class5", "Class6");
        assertAlignCentered(50, "Class1_3", "Class1_4");
        assertAlignCentered(50, "Class3_5", "Class3_6");

        // Assert that top left corner of new layouted elements is OK
        assertEquals(new Point(ResetOriginChangeModelOperation.MARGIN, ResetOriginChangeModelOperation.MARGIN), getTopLeftCorner(getEditPart("Class5"), getEditPart("Class6")));
        // TODO : This top left container corresponds to the insets (it could be computed). But by default, it should be
        // the same location is during an arrange all (further improvement)
        Point expectedContainerTopLeftCorner = new Point(7, 6);
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class1_3"), getEditPart("Class1_4"), getEditPart("Class1_5")));
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class3_5"), getEditPart("Class3_6")));
        // TODO : The top left corner should be the same for container in other container (it is currently not the case)
        // (further improvement)
        expectedContainerTopLeftCorner = new Point(0, 0);
        assertEquals(expectedContainerTopLeftCorner, getTopLeftCorner(getEditPart("Class2_2_3")));

        // Assert that there is no scroll bar on container (for p1 and p3 the new elements layout is larger than
        // previous)
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p1"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p2_2"));
        assertNoVisibleScrollBar((IDiagramContainerEditPart) getEditPart("p3"));

        // Assert that the location and the size of the container with new elements are the same before and after the
        // layout
        assertEquals("The location of the container p1 should be the same before and after the layout.", boundsOfP1BeforeLayoutAtOpening.getLocation(),
                getEditPart("p1").getFigure().getBounds().getLocation());
        assertEquals("The location of the container p2_2 should be the same before and after the layout.", boundsOfP22BeforeLayoutAtOpening.getLocation(),
                getEditPart("p2_2").getFigure().getBounds().getLocation());
        assertEquals("The location of the container p3 should be the same before and after the layout.", boundsOfP3BeforeLayoutAtOpening.getLocation(),
                getEditPart("p3").getFigure().getBounds().getLocation());
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeAtCreation(String diagramName) {
        testArrangeAtCreation(session.getSemanticResources().stream().findFirst().get().getContents().get(0), diagramName);
    }

    /**
     * Make sure that arrange all launched at diagram creation is OK when using ELK.
     * 
     * @param semanticRootElement
     *            The semantic root element used to create the diagram
     * @param diagramName
     *            The name of the diagram to use
     */

    public void testArrangeAtCreation(EObject semanticRootElement, String diagramName) {
        // Create a new diagram
        DRepresentation representation = createRepresentation(diagramName, semanticRootElement);
        // Open the editor
        IEditorPart newEditorPart = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());

        // Keep the figures bounds after the arrange all launches automatically at opening.
        Map<DNode, Rectangle> DNodes2Bounds = computeNodesBounds(representation);

        // Launch an arrange all explicitly
        arrangeAll((DiagramEditor) newEditorPart);

        // Check that the layout is the same as after opening
        Map<DNode, Rectangle> afterDNodes2Bounds = computeNodesBounds(representation);
        afterDNodes2Bounds.forEach((dNode, rect) -> {
            assertEquals("The layout result should not change.", DNodes2Bounds.get(dNode), rect);
        });
    }

    protected void openDiagram(String diagramName) {
        diagram = (DDiagram) getRepresentationsByName(diagramName).toArray()[0];
        editorPart = (IDiagramWorkbenchPart) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check that the Note is moved or not moved with an arrange using ELK, according to the value of the preference
     * "Move unlinked notes during layout".
     * 
     * @throws Exception
     *             in case of problem
     */
    @SuppressWarnings("rawtypes")
    protected void testNoteLayoutAccordingToPref(boolean moveNoteDuringLayout) throws Exception {
        openDiagram("simpleDiagramWithNote");

        // Get the GMF node corresponding to the Note
        Node noteNode = getNote(editorPart.getDiagram());
        assertTrue("One note should exist on the diagram", noteNode != null);

        // Get the corresponding edit part
        Map editPartRegistry = editorPart.getDiagramEditPart().getRoot().getViewer().getEditPartRegistry();
        final IGraphicalEditPart noteEditPart = (IGraphicalEditPart) editPartRegistry.get(noteNode);

        // Get the initial note bounds (to be compare to the new bounds after the layout)
        final Rectangle initialNoteBounds = noteEditPart.getFigure().getBounds().getCopy();

        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), moveNoteDuringLayout);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Compare the new location with the expected result
        Rectangle currentNoteBounds = noteEditPart.getFigure().getBounds().getCopy();
        if (moveNoteDuringLayout) {
            assertFalse("The Note should be moved during the arrange.", initialNoteBounds.getLocation().equals(currentNoteBounds.getLocation()));
        } else {
            assertTrue("The Note should not be moved during the arrange.", initialNoteBounds.getLocation().equals(currentNoteBounds.getLocation()));
            Optional<DDiagramElement> c4Dde = diagram.getDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass4")).findFirst();
            if (c4Dde.isPresent()) {
                IGraphicalEditPart c4EditPart = getEditPart(c4Dde.get());
                Rectangle c4Bounds = c4EditPart.getFigure().getBounds().getCopy();
                assertTrue("As the Note is not moved, it is expected to overlap \"MyClass4\" node.", currentNoteBounds.intersects(c4Bounds));
            } else {
                fail("The diagram should have a node named \"MyClass4\".");
            }
        }
    }

    private Node getNote(Diagram gmfDiagram) {
        return getSpecificGmfNode(gmfDiagram, "Note");
    }

    private Node getText(Diagram gmfDiagram) {
        return getSpecificGmfNode(gmfDiagram, "Text");
    }

    private Node getSpecificGmfNode(Diagram gmfDiagram, String id) {
        Node specificNode = null;
        for (Iterator<Object> iterator = gmfDiagram.getChildren().iterator(); iterator.hasNext() && specificNode == null;) {
            Object node = iterator.next();
            if (node instanceof Node) {
                if (((Node) node).getType().equals(id)) {
                    specificNode = (Node) node;
                }
            }
        }
        return specificNode;
    }

    private void restoreInitilaPreferences(IPreferenceStore workspaceViewerPreferenceStore) {
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, initialSnapToGridValue);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.GRIDSPACING, initialGridSpacingValue);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.RULERUNIT, initialRulerUnitValue);
    }

    private void changeSnapToPreferences(IPreferenceStore workspaceViewerPreferenceStore) {
        initialSnapToGridValue = workspaceViewerPreferenceStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID);
        initialGridSpacingValue = workspaceViewerPreferenceStore.getDouble(WorkspaceViewerProperties.GRIDSPACING);
        initialRulerUnitValue = workspaceViewerPreferenceStore.getInt(WorkspaceViewerProperties.RULERUNIT);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, false);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.GRIDSPACING, 100.0);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.RULERUNIT, RulerProvider.UNIT_PIXELS);
    }

    private Map<DNode, Rectangle> computeNodesBounds(DRepresentation representation) {
        Map<DNode, Rectangle> dNodes2Bounds = new HashMap<>();
        ((DDiagram) representation).getNodes().stream().forEach(dNode -> {
            IGraphicalEditPart editPart = getEditPart(dNode);
            dNodes2Bounds.put(dNode, editPart.getFigure().getBounds().getCopy());
        });
        return dNodes2Bounds;
    }

    private void arrangeAll(final DiagramEditor editorPart) {
        ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
        arrangeRequest.setPartsToArrange(Collections.singletonList(editorPart));
        editorPart.getDiagramEditPart().performRequest(arrangeRequest);
        TestsUtil.synchronizationWithUIThread();
    }

    private void arrangeSelection(final IGraphicalEditPart... editPartsToSelect) {
        arrangeSelection(Arrays.asList(editPartsToSelect));
    }

    private void arrangeSelection(List<IGraphicalEditPart> editPartsToSelect) {
        ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_SELECTION);
        // Filter the list as it is done in
        // org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.createOperationSet()
        List<IGraphicalEditPart> realEditPartsToSelect = ToolUtilities.getSelectionWithoutDependants(editPartsToSelect);
        arrangeRequest.setPartsToArrange(realEditPartsToSelect);
        // Validate that there is a common parent (as in
        // org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getTargetEditPartForArrangeSelection(List)).
        boolean validated = true;
        EditPart parentEP = getSelectionParent(realEditPartsToSelect);
        for (int i = 1; i < realEditPartsToSelect.size(); i++) {
            EditPart part = (EditPart) realEditPartsToSelect.get(i);
            if (part instanceof ConnectionEditPart) {
                continue;
            }
            // if there is no common parent, then Arrange Selected isn't
            // supported.
            if (part.getParent() != parentEP) {
                validated = false;
            }
        }
        if (validated) {
            editorPart.getDiagramEditPart().performRequest(arrangeRequest);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    /**
     * Copy of org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getSelectionParent(List).<BR/>
     * getSelectionParent Utility to return the logical parent of the selection list
     * 
     * @param editparts
     *            List to parse for a common parent.
     * @return EditPart that is the parent or null if a common parent doesn't exist.
     */
    @SuppressWarnings("rawtypes")
    private EditPart getSelectionParent(List editparts) {
        ListIterator li = editparts.listIterator();
        while (li.hasNext()) {
            Object obj = li.next();
            if (!(obj instanceof ConnectionEditPart) && obj instanceof EditPart) {
                return ((EditPart) obj).getParent();
            }
        }
        return null;
    }

    private IGraphicalEditPart getEditPart(String editorPartName) {
        Optional<DDiagramElement> dde = diagram.getDiagramElements().stream().filter(ode -> ode.getName().equals(editorPartName)).findFirst();
        assertTrue("The diagram should have a node named \"" + editorPartName + "\".", dde.isPresent());
        return getEditPart(dde.get());
    }

    /**
     * Gets the primary editparts on this container, that is, the top-level shapes and connectors.
     * 
     * @param containerEditPart
     *            the concerned container
     * 
     * @return List of primary edit parts. If there are none then it returns a Collections.EMPTY_LIST, which is
     *         immutable
     */
    private List<?> getPrimaryEditParts(IGraphicalEditPart containerEditPart) {
        List<?> result = null;
        if (containerEditPart instanceof DiagramEditPart) {
            result = ((DiagramEditPart) containerEditPart).getPrimaryEditParts();
        } else {
            for (Object child : containerEditPart.getChildren()) {
                if (child instanceof AbstractDNodeContainerCompartmentEditPart) {
                    result = ((AbstractDNodeContainerCompartmentEditPart) child).getChildren();
                }
            }
        }
        if (result == null) {
            result = Collections.EMPTY_LIST;
        }
        return result;
    }

    private void assertNoVisibleScrollBar(IDiagramContainerEditPart part) {
        IFigure hScrollBar = null;
        IFigure vScrollBar = null;
        Object child = part.getChildren().get(1);
        if (child instanceof AbstractDNodeContainerCompartmentEditPart) {
            ResizableCompartmentFigure compartmentFigure = (ResizableCompartmentFigure) ((IGraphicalEditPart) child).getFigure();
            hScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetHorizontalScrollBar();
            vScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetVerticalScrollBar();
        }
        boolean hScrollBarVisible = hScrollBar != null && hScrollBar.isVisible();
        boolean vScrollBarVisible = vScrollBar != null && vScrollBar.isVisible();
        assertFalse("No scrollbar should be visible for this container (hScrollBar:" + hScrollBarVisible + ", vScrollBar:" + vScrollBarVisible + ").", hScrollBarVisible || vScrollBarVisible);
    }

    private void assertAlignCentered(int verticalSpace, String... names) {
        String previousName = "";
        int previousCenter = 0;
        int previousRight = 0;
        for (String name : names) {
            IGraphicalEditPart editPart = getEditPart(name);
            Rectangle bounds = editPart.getFigure().getBounds();
            if (!previousName.isEmpty()) {
                Point leftPoint = bounds.getLeft();
                int currentCenter = leftPoint.y();
                assertEquals("\"" + previousName + "\" is not centered aligned with \"" + name + "\".", previousCenter, currentCenter);
                previousCenter = currentCenter;
                assertEquals("\"" + name + "\" should be " + verticalSpace + " after \"" + previousName + "\".", previousRight + verticalSpace, leftPoint.x());
                previousRight = bounds.getRight().x();
            }
        }
    }

    private Point getTopLeftCorner(IGraphicalEditPart... editParts) {
        Point topLeftCorner = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (IGraphicalEditPart editPart : editParts) {
            Point location = editPart.getFigure().getBounds().getTopLeft();
            topLeftCorner = new Point(Math.min(location.x(), topLeftCorner.x()), Math.min(location.y(), topLeftCorner.y()));
        }
        return topLeftCorner;
    }


    /**
     * Move the edit part.
     * 
     * @param dde
     *            the DDiagramElement of the edit part to move.
     * @param point
     *            delta to move.
     * 
     */
    private void moveEditPart(DDiagramElement dde, Point point) {
        IGraphicalEditPart editPart = getEditPart(dde);
        ChangeBoundsRequest request = new ChangeBoundsRequest();
        request.setMoveDelta(point);
        request.setLocation(point);
        request.setType(RequestConstants.REQ_MOVE);
        editPart.performRequest(request);
        TestsUtil.synchronizationWithUIThread();
    }
}
