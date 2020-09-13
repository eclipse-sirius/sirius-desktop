/*******************************************************************************
 * Copyright (c) 2020 Obeo.
 * All rights reserved.
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

/**
 * Tests to realize some verification of arrange result with basic ELK layouts.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class SimpleELKLayoutTest extends SiriusDiagramTestCase {
    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/withELK/My.ecore";

    private static final String VSM_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/withELK/My.odesign";

    private static final String REPRESENTATIONS_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/withELK/representations.aird";

    private DDiagram diagram;

    private IDiagramWorkbenchPart editorPart;

    private boolean initialSnapToGridValue;

    private double initialGridSpacingValue;

    private int initialRulerUnitValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, VSM_PATH, REPRESENTATIONS_MODEL_PATH);
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
    public void _testNoteLayoutWithPrefFalse() throws Exception {
        testNoteLayoutAccordingToPref(false);
    }

    /**
     * Check that the size of a Note is the same before and after an arrange.
     */
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
        int expectedRegionHeight = expectedOneLineHeight + IContainerLabelOffsets.LABEL_OFFSET + 1;
        assertEquals("The empty region should be on one line (with one line height)", expectedRegionHeight, ((AbstractDiagramContainerEditPart) c2EditPart).getFigure().getSize().height());

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
        boolean isFirstRegion = true;
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
                    if (isFirstRegion) {
                        // The first region is one pixel less than others (no upper border)
                        previousRegionHeight += 1;
                        isFirstRegion = false;
                    }
                    assertEquals("Each region should have the same height, height of \"" + ((DNodeContainer) regionEditPart.resolveSemanticElement()).getName()
                            + "\" is not the same than the previous region", previousRegionHeight, regionBounds.getHeight());
                    previousRegionHeight = regionBounds.getHeight();
                }
            }
        }
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
}
