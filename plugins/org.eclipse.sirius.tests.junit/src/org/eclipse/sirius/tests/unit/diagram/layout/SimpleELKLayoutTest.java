/*******************************************************************************
 * Copyright (c) 2020, 2025 Obeo.
 * All rights reserved.
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout;

import java.io.IOException;
import java.text.MessageFormat;
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
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
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
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.StringValueStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.elk.SiriusElkUtil;
import org.eclipse.sirius.diagram.elk.migration.EmptyJunctionPointsStringValueStyleMigrationParticipant;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ResetOriginChangeModelOperation;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests to realize some verification of arrange result with basic ELK layouts.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class SimpleELKLayoutTest extends SiriusDiagramTestCase {
    /**
     * A customized ViewQuery to ignore FontStyle, because the default Font Style depends on the OS where the test is
     * launched. In reality, it is not ignored but the current font style is returned as default.
     * 
     * @author Laurent Redor
     */
    public class ViewQueryWithoutFontNameCheck extends ViewQuery {

        public ViewQueryWithoutFontNameCheck(View view) {
            super(view);
        }

        @Override
        public Object getDefaultValue(EAttribute eAttribute) {
            Object result = super.getDefaultValue(eAttribute);
            if (NotationPackage.Literals.FONT_STYLE__FONT_NAME.equals(eAttribute)) {
                FontStyle fontStyle = (FontStyle) view.getStyle(NotationPackage.Literals.FONT_STYLE);
                if (fontStyle != null) {
                    result = fontStyle.eGet(NotationPackage.Literals.FONT_STYLE__FONT_NAME);
                }
            }
            return result;
        }
    }

    private static final String WRONG_INITIAL_ROUTING_MESSAGE = "The edge \"{0}\" has a wrong initial routing style.";

    private static final String WRONG_ROUTING_AFTER_LAYOUT_MESSAGE = "The edge \"{0}\" has a wrong routing style after the arrange.";

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

        AbstractDiagramNodeEditPart c1EditPart = getEditPart("MyClass1", AbstractDiagramNodeEditPart.class);
        Dimension minimumTextSize = c1EditPart.getNodeLabel().getPreferredSize();

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

        AbstractDiagramBorderNodeEditPart portEditPart = getEditPart("att1", AbstractDiagramBorderNodeEditPart.class);

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
        DDiagramElement dde = getDDiagramElement(listName);
        AbstractDiagramListEditPart editPart = getEditPart(dde, AbstractDiagramListEditPart.class);

        assertEquals("Wrong number of list items, the list should contain 3 list items.", 3, ((DNodeList) dde).getOwnedElements().stream().count());

        Dimension listItemSize = getEditPart(((DNodeList) dde).getOwnedElements().get(0), IGraphicalEditPart.class).getFigure().getSize();

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

        DDiagramElement c2Dde = getDDiagramElement("MyClass2");
        DNodeListEditPart c2EditPart = getEditPart(c2Dde, DNodeListEditPart.class);

        Optional<DNodeListElement> listItem = ((DNodeList) c2Dde).getOwnedElements().stream().filter(dde -> dde.getName().equals("listItemWithALongName")).findFirst();
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

        DDiagramElement vStackContainer = getDDiagramElement("root_V");
        IGraphicalEditPart vStackContainerEditPart = getEditPart(vStackContainer, AbstractDiagramContainerEditPart.class);

        Optional<DDiagramElement> c2Dde = ((DNodeContainer) vStackContainer).getOwnedDiagramElements().stream().filter(dde -> dde.getName().equals("MyClass2")).findFirst();
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
        testArrangeAllResult_ForPackageArrangeSelection("diagramWithContainer");
    }

    /**
     * Makes sure that the label on border of a node is moved when only its location is changed (nothing else is
     * changed).
     */
    public void testArrangeAllResultWhenOnlyABorderLabelShouldBeMoved() {
        openDiagram("diagramWithJustALabelOnBorderMove");

        // Get locations of MyClass1 and of attribute1 (they should not be moved in this test, already correctly
        // located))
        AbstractDiagramContainerEditPart nodeEditPart = getEditPart("MyClass1", AbstractDiagramContainerEditPart.class);
        Rectangle nodeBounds = nodeEditPart.getFigure().getBounds().getCopy();

        AbstractDiagramBorderNodeEditPart portEditPart = getEditPart("attribute1", AbstractDiagramBorderNodeEditPart.class);
        Rectangle borderNodeBounds = portEditPart.getFigure().getBounds().getCopy();

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        assertEquals("The node for \"MyClass1\" should not move during this test (data corrupted or behavior unexpected).", nodeBounds, nodeEditPart.getFigure().getBounds().getCopy());
        assertEquals("The border node for \"attribute1\" should not move during this test (data corrupted or behavior unexpected).", borderNodeBounds, portEditPart.getFigure().getBounds().getCopy());

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
                    assertTrue(((IGraphicalEditPart) portChildObj).getModel() instanceof Node);
                    Node labelNode = (Node) ((IGraphicalEditPart) portChildObj).getModel();
                    Location gmfLabelLocation = (Location) labelNode.getLayoutConstraint();
                    assertEquals("The x GMF coordinate of the label of the border node does not correspond to a centered location.", -(labelBounds.width() - borderNodeBounds.width()) / 2,
                            gmfLabelLocation.getX(), 1);
                    assertEquals("The y GMF coordinate of the label of the border node does not correspond to a location under its border node.", borderNodeBounds.height() + 1,
                            gmfLabelLocation.getY());
                    assertEquals("Even if GMF coordinates are OK, the label of the border node is visually not horizontally centered on its border node (draw2d x coordinate).",
                            labelBounds.getCenter().x(), borderNodeBounds.getCenter().x());
                    assertEquals("Even if GMF coordinates are OK, the label of the border node is visually not under the bottom side of its border node (draw2d y coordinate).",
                            labelBounds.getTop().y(), borderNodeBounds.getBottom().y() + 1);
                }
            }
            if (!labelFound) {
                fail("The label of the border node has not been found.");
            }
        }
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
        assertNoVisibleScrollBar(getEditPart("p1", IDiagramContainerEditPart.class));

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
        testArrangeAllResult_ForPackageArrangeSelection("diagramWithContainerAndScroll");
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

        IDiagramContainerEditPart editPart = getEditPart("p1", IDiagramContainerEditPart.class);
        Point locationOfP1BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p1
        assertNoVisibleScrollBar(editPart);

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

        IDiagramContainerEditPart editPart = getEditPart("p2_2", IDiagramContainerEditPart.class);
        Point locationOfP22BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p2_2
        assertNoVisibleScrollBar(editPart);

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

        IDiagramContainerEditPart editPart = getEditPart("p4", IDiagramContainerEditPart.class);
        Point locationOfP4BeforeLayout = editPart.getFigure().getBounds().getTopLeft();

        // Launch an arrange selection
        arrangeSelection(editPart);

        // Assert that there is no scroll bar on p1
        assertNoVisibleScrollBar(editPart);

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

        IGraphicalEditPart p1EditPart = getEditPart("p1", IDiagramContainerEditPart.class);
        IGraphicalEditPart p3EditPart = getEditPart("p3", IDiagramContainerEditPart.class);
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

        IDiagramContainerEditPart p2EditPart = getEditPart("p2", IDiagramContainerEditPart.class);
        Point locationOfP2BeforeLayout = p2EditPart.getFigure().getBounds().getTopLeft();
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1", AbstractDiagramNodeEditPart.class);
        IGraphicalEditPart class23EditPart = getEditPart("Class2_3", AbstractDiagramNodeEditPart.class);

        // Launch an arrange selection
        arrangeSelection(p2EditPart, class21EditPart, class23EditPart);

        // Assert that there is no scroll bar on p2
        assertNoVisibleScrollBar(p2EditPart);

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

        IDiagramContainerEditPart p22EditPart = getEditPart("p2_2", IDiagramContainerEditPart.class);
        Rectangle boundsOfP22BeforeLayout = p22EditPart.getFigure().getBounds().getCopy();
        IGraphicalEditPart class222EditPart = getEditPart("Class2_2_2", AbstractDiagramNodeEditPart.class);
        IGraphicalEditPart class221EditPart = getEditPart("Class2_2_1", AbstractDiagramNodeEditPart.class);
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

        IGraphicalEditPart p2EditPart = getEditPart("p2", IDiagramContainerEditPart.class);
        Rectangle boundsOfP2BeforeLayout = p2EditPart.getFigure().getBounds().getCopy();
        IGraphicalEditPart class22EditPart = getEditPart("Class2_2", AbstractDiagramNodeEditPart.class);
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1", AbstractDiagramNodeEditPart.class);
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

        IGraphicalEditPart p1EditPart = getEditPart("p1", IDiagramContainerEditPart.class);
        IGraphicalEditPart class22EditPart = getEditPart("Class2_2", IDiagramNodeEditPart.class);
        IGraphicalEditPart class21EditPart = getEditPart("Class2_1", IDiagramNodeEditPart.class);

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
     * This method should be used for diagram on the package packageForArrangeSelectionTest.
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    protected void testArrangeAllResult_ForPackageArrangeSelection(String diagramName) {
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
        assertNoVisibleScrollBar(getEditPart("p1", IDiagramContainerEditPart.class));
        assertNoVisibleScrollBar(getEditPart("p2", IDiagramContainerEditPart.class));
        assertNoVisibleScrollBar(getEditPart("p3", IDiagramContainerEditPart.class));
        assertNoVisibleScrollBar(getEditPart("p4", IDiagramContainerEditPart.class));

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

    /**
     * Makes sure that the border node is not in the margin area of {20x20}.
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeAllResultWithBorderNode() {
        testArrangeAllResult_ForPackageResetOrigin("resetOrigin1");
    }

    /**
     * Makes sure that the label of border node is not in the margin area of {20x20}.
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeAllResultWithBorderNodeWithLabel() {
        testArrangeAllResult_ForPackageResetOrigin("resetOrigin2");
    }

    /**
     * Makes sure that the edge is not in the margin area of {20x20}.
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    public void testArrangeAllResultWithEdgeOutsideOfBoundingBox() {
        testArrangeAllResult_ForPackageResetOrigin("resetOrigin3", true, false, false);
    }

    public void testArrangeAll_edgeOnEdge_Simple_EdgeAsTarget() {
        testArrangeAll_edgeOnEdge_Simple_EdgeAsTarget(false);
    }

    public void testArrangeAll_edgeOnEdge_Simple_EdgeAsTarget_forceRectilinear() {
        testArrangeAll_edgeOnEdge_Simple_EdgeAsTarget(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The edge on edge has 2 sections, and its end point is the only point on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_Simple_EdgeAsTarget(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_subClasses for simpleEdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op1", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_Simple_EdgeAsSource() {
        testArrangeAll_edgeOnEdge_Simple_EdgeAsSource(false);
    }

    public void testArrangeAll_edgeOnEdge_Simple_EdgeAsSource_forceRectilinear() {
        testArrangeAll_edgeOnEdge_Simple_EdgeAsSource(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The edge on edge, from op1 to other edge, has 2 sections, and its start point is the only point on the main
     * edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_Simple_EdgeAsSource(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_subClassesReverse for simpleEdgeOnEdge", forceRectilinear);
        DEdgeEditPart sourceEdgeEditPart = checkEdge("C1", "C2", true);
        Connection sourceConnection = sourceEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", sourceConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_Complexe_EdgeAsTarget() {
        testArrangeAll_edgeOnEdge_Complexe_EdgeAsTarget(false);
    }

    public void testArrangeAll_edgeOnEdge_Complexe_EdgeAsTarget_forceRectilinear() {
        testArrangeAll_edgeOnEdge_Complexe_EdgeAsTarget(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The main edge from C3 to C4 is a straight line (2 points on the same y axis)</LI>
     * <LI>The edge on edge, from op1 to edge C1-C2, has 2 sections, and its end point is the only point on the main
     * edge</LI>
     * <LI>The edge on edge, from op3 to edge C1-C2, has 2 sections, and its end point is the only point on the main
     * edge</LI>
     * <LI>The edge on edge, from op2 to edge C3-C4, has 2 sections, and its end point is the only point on the main
     * edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_Complexe_EdgeAsTarget(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_subClasses for complexeEdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op1", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op3", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        targetEdgeEditPart = checkEdge("C3", "C4", true);
        targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op2", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_Complexe_EdgeAsSource() {
        testArrangeAll_edgeOnEdge_Complexe_EdgeAsSource(false);
    }

    public void testArrangeAll_edgeOnEdge_Complexe_EdgeAsSource_forceRectilinear() {
        testArrangeAll_edgeOnEdge_Complexe_EdgeAsSource(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The main edge from C3 to C4 is a straight line (2 points on the same y axis)</LI>
     * <LI>The edge on edge, from edge C1-C2 to op1, has 2 sections, and its start point is the only point on the main
     * edge</LI>
     * <LI>The edge on edge, from edge C1-C2 to op3, has 2 sections, and its start point is the only point on the main
     * edge</LI>
     * <LI>The edge on edge, from edge C3-C4 to op2, has 2 sections, and its start point is the only point on the main
     * edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_Complexe_EdgeAsSource(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_subClassesReverse for complexeEdgeOnEdge", forceRectilinear);
        DEdgeEditPart sourceEdgeEditPart = checkEdge("C1", "C2", true);
        Connection sourceConnection = sourceEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", sourceConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsSource("op3", sourceConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        sourceEdgeEditPart = checkEdge("C3", "C4", true);
        sourceConnection = sourceEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op2", sourceConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsTarget() {
        testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsTarget(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsTarget_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsTarget(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, pointing to it, are "rectilinear" with expected number of bendpoints, and their end points
     * are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsTarget(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackage for levels1EdgeOnEdge", true, false, forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op1", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op2", targetConnection, forceRectilinear ? 5 : 6, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op3", targetConnection, forceRectilinear ? 5 : 6, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op4", targetConnection, forceRectilinear ? 5 : 6, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op5", targetConnection, forceRectilinear ? 5 : 6, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsTarget() {
        testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsTarget(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsTarget_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsTarget(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, pointing to it, are "rectilinear" with expected number of bendpoints, and their end points
     * are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsTarget(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackageWithOpAtRoot for levels1EdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op1", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op2", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op3", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op4", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op5", targetConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsTarget() {
        testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsTarget(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsTarget_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsTarget(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, pointing to it, are "rectilinear" with expected number of bendpoints, and their end points
     * are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsTarget(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackageWithOpAtRoot for levels2EdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C2", "C1", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsTarget("op1", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op2", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op3", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op4", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsTarget("op5", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsSource() {
        testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsSource(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsSource_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsSource(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, starting from it, are "rectilinear" with expected number of bendpoints, and their start
     * points are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel1_EdgeAsSource(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackageReverse for levels1EdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op2", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op3", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op4", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op5", targetConnection, 3, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsSource() {
        testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsSource(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsSource_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsSource(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, starting from it, are "rectilinear" with expected number of bendpoints, and their start
     * points are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel2_EdgeAsSource(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackageWithOpAtRootReverse for levels1EdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C1", "C2", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op2", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op3", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op4", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op5", targetConnection, 3, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsSource() {
        testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsSource(false);
    }

    public void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsSource_forceRectilinear() {
        testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsSource(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge and with edges with different containing
     * levels respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The other edges, starting from it, are "rectilinear" with expected number of bendpoints, and their start
     * points are the only points on the main edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_DifferentLevel3_EdgeAsSource(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_withPackageWithOpAtRootReverse for levels2EdgeOnEdge", forceRectilinear);
        DEdgeEditPart targetEdgeEditPart = checkEdge("C2", "C1", true);
        Connection targetConnection = targetEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", targetConnection, 3, forceRectilinear);
        checkEdgeWithEdgeAsSource("op2", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsSource("op3", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsSource("op4", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
        checkEdgeWithEdgeAsSource("op5", targetConnection, forceRectilinear ? 5 : 4, forceRectilinear);
    }

    public void testArrangeAll_edgeOnEdge_nodeMappingOrderReversed() {
        testArrangeAll_edgeOnEdge_nodeMappingOrderReversed(false);
    }

    public void testArrangeAll_edgeOnEdge_nodeMappingOrderReversed_forceRectilinear() {
        testArrangeAll_edgeOnEdge_nodeMappingOrderReversed(true);
    }

    /**
     * Makes sure that the result of an arrange containing an edge on edge respect the following rules:
     * <UL>
     * <LI>The top left corner of the bounding box is {20, 20}</LI>
     * <LI>The main edge from C1 to C2 is a straight line (2 points on the same y axis)</LI>
     * <LI>The edge on edge, from other edge to op1, has 2 sections, and its start point is the only point on the main
     * edge</LI>
     * <UL>
     */
    private void testArrangeAll_edgeOnEdge_nodeMappingOrderReversed(boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin("diagramEdgeOnEdge_subClassesReverse_nodeOrderReverse for simpleEdgeOnEdge", forceRectilinear);
        DEdgeEditPart sourceEdgeEditPart = checkEdge("C1", "C2", true);
        Connection sourceConnection = sourceEdgeEditPart.getConnectionFigure();
        checkEdgeWithEdgeAsSource("op1", sourceConnection, forceRectilinear ? 3 : 2, forceRectilinear);
    }

    /**
     * Makes sure that no diagram element are not in the margin area of {20x20}.<BR/>
     * This method should be used for diagram on the package "resetOriginCases".
     * 
     * @param diagramName
     *            The name of the diagram to use
     */
    protected void testArrangeAllResult_ForPackageResetOrigin(String diagramName) {
        testArrangeAllResult_ForPackageResetOrigin(diagramName, false, false, false);
    }

    /**
     * Makes sure that no diagram element are not in the margin area of {20x20}.<BR/>
     * This method should be used for diagram on the package "resetOriginCases".
     * 
     * @param diagramName
     *            The name of the diagram to use
     * @param forceRectilinear
     *            true to modify the VSM and add the property EdgeRouting.ORTHOGONAL, false to use the current VSM
     *            without modification
     */
    protected void testArrangeAllResult_ForPackageResetOrigin(String diagramName, boolean forceRectilinear) {
        testArrangeAllResult_ForPackageResetOrigin(diagramName, false, false, forceRectilinear);
    }

    /**
     * Makes sure that no diagram element are not in the margin area of {20x20}.<BR/>
     * This method should be used for diagram on the package "resetOriginCases".
     * 
     * @param diagramName
     *            The name of the diagram to use
     * @param withHorizontalEdgeCase
     *            true if the diagram contains at least one edge outside of the bounding box of nodes on the horizontal
     *            axis, false otherwise.
     * @param withVerticalEdgeCase
     *            true if the diagram contains at least one edge outside of the bounding box of nodes on the vertical
     *            axis, false otherwise.
     * @param forceRectilinear
     *            true to modify the VSM and add the property EdgeRouting.ORTHOGONAL, false to use the current VSM
     *            without modification
     */
    protected void testArrangeAllResult_ForPackageResetOrigin(String diagramName, boolean withHorizontalEdgeCase, boolean withVerticalEdgeCase, boolean forceRectilinear) {
        if (forceRectilinear) {
            DDiagram currentDiagram = (DDiagram) getRepresentationsByName(diagramName).toArray()[0];
            setDefaultRoutingStyle(currentDiagram.getDescription().getName(), org.eclipse.elk.core.options.EdgeRouting.ORTHOGONAL);
        }
        openDiagram(diagramName);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Assert that the bounding box coordinates of all elements are {20, 20}
        // Compute primary edit parts (first level edit parts of the container)
        List<?> primaryEditParts = getPrimaryEditParts(editorPart.getDiagramEditPart());
        List<IGraphicalEditPart> primaryGraphicalEditParts = Lists.newArrayList(Iterables.filter(primaryEditParts, IGraphicalEditPart.class));
        Rectangle boundingbox = DiagramImageUtils.calculateImageRectangle(primaryGraphicalEditParts, 0, new Dimension(0, 0));
        // Fix the bounding box according to :
        // * calculatedTolerance, ie 4 pixels used in
        // org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx#getBounds())
        // * jumpLinkSize, ie 10 pixels , used in
        // org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx#getBounds())
        // * expand, ie 1 pixel, used in org.eclipse.draw2d.Polyline.getBounds()
        if (withVerticalEdgeCase) {
            boundingbox.shrink(15, 0);
        }
        if (withHorizontalEdgeCase) {
            boundingbox.shrink(0, 15);
        }
        assertEquals("Wrong x coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.x());
        assertEquals("Wrong y coordinate for the bounding box of all diagram elements.", ResetOriginChangeModelOperation.MARGIN, boundingbox.y());
    }

    /**
     * Makes sure that the oblique routing style is kept after an arrange all with ELK (for a diagram containing only
     * edges with oblique routing style).
     */
    public void testArrangeAllResultForObliqueEdges() {
        testArrangeAllResultForEdgesRoutingStyle("simpleDiagramWithObliqueEdges", "to2", Routing.MANUAL, "toA2", Routing.MANUAL, Routing.MANUAL);
    }

    /**
     * Makes sure that the rectilinear routing style is kept after an arrange all with ELK (for a diagram containing
     * only edges with oblique routing style).
     */
    public void testArrangeAllResultForRectilinearEdges() {
        testArrangeAllResultForEdgesRoutingStyle("simpleDiagramWithRectilinearEdges", "to2", Routing.RECTILINEAR, "toA2", Routing.RECTILINEAR, Routing.RECTILINEAR);
    }

    /**
     * Makes sure that when a diagram contains a mix of rectilinear and oblique routing style, the priority rule is
     * respect after an arrange all with ELK (Oblique > Rectilinear > Tree).
     */
    public void testArrangeAllResultForEdgesWithMixRoutingStyle_AndSameNumberOfBoth() {
        testArrangeAllResultForEdgesRoutingStyle("simpleDiagramWithMixRoutingEdges_SameNumber", "to2", Routing.RECTILINEAR, "toA2", Routing.MANUAL, Routing.MANUAL);
    }

    /**
     * Makes sure that when a diagram contains a mix of rectilinear and oblique routing style, if there are more
     * rectilinear edges, the rectilinear routing style is applied to all edges after the layout with ELK.
     */
    public void testArrangeAllResultForEdgesWithMixRoutingStyle_AndMoreRectilinear() {
        testArrangeAllResultForEdgesRoutingStyle("simpleDiagramWithMixRoutingEdges_MoreRectilinear", "to2", Routing.RECTILINEAR, "toA4", Routing.MANUAL, Routing.RECTILINEAR);
    }

    /**
     * Makes sure that when a diagram contains a mix of rectilinear and oblique routing style, if there are more oblique
     * edges, the oblique routing style is applied to all edges after the layout with ELK.
     */
    public void testArrangeAllResultForEdgesWithMixRoutingStyle_AndMoreOblique() {
        testArrangeAllResultForEdgesRoutingStyle("simpleDiagramWithMixRoutingEdges_MoreOblique", "to4", Routing.RECTILINEAR, "toA2", Routing.MANUAL, Routing.MANUAL);
    }

    private void testArrangeAllResultForEdgesRoutingStyle(String diagramName, String firstEdgeNameToCheck, int initialExpectedFirstEdgeRouting, String secondEdgeNameToCheck,
            int initialExpectedSecondEdgeRouting, int afterLayoutExpectedEdgeRouting) {
        openDiagram(diagramName);

        // Check the initial routing style
        AbstractDiagramEdgeEditPart edgeEditPartWithRightAngle = getEdgeEditPart(firstEdgeNameToCheck, AbstractDiagramEdgeEditPart.class);
        checkRoutingStyle(edgeEditPartWithRightAngle, WRONG_INITIAL_ROUTING_MESSAGE, firstEdgeNameToCheck, initialExpectedFirstEdgeRouting);
        AbstractDiagramEdgeEditPart edgeEditPartWithoutRightAngle = getEdgeEditPart(secondEdgeNameToCheck, AbstractDiagramEdgeEditPart.class);
        checkRoutingStyle(edgeEditPartWithoutRightAngle, WRONG_INITIAL_ROUTING_MESSAGE, secondEdgeNameToCheck, initialExpectedSecondEdgeRouting);

        // Launch an arrange all
        arrangeAll((DiagramEditor) editorPart);

        // Check the routing style after the arrange
        checkRoutingStyle(edgeEditPartWithRightAngle, WRONG_ROUTING_AFTER_LAYOUT_MESSAGE, firstEdgeNameToCheck, afterLayoutExpectedEdgeRouting);
        checkRoutingStyle(edgeEditPartWithoutRightAngle, WRONG_ROUTING_AFTER_LAYOUT_MESSAGE, secondEdgeNameToCheck, afterLayoutExpectedEdgeRouting);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration of
     * EmptyJunctionPointsStringValueStyleMigrationParticipant in other tests.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new EmptyJunctionPointsStringValueStyleMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI("/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0); //$NON-NLS-1$
    }

    /**
     * Check that when floating coordinates are returned by ELK, the rounded conversion is OK and that the delta of
     * absolute location is never higher than 0.5.
     */
    public void testRoundedCoordinates() {
        PrecisionPoint location = new PrecisionPoint(10.5, 10.5);
        ElkNode parent = createNode(null, "parent", location);
        ElkNode child1 = createNode(parent, "child1", location);
        ElkNode child2 = createNode(child1, "child2", location);
        ElkNode child3 = createNode(child2, "child3", location);
        PrecisionPoint parentLocation = SiriusElkUtil.getRoundedCoordinatesAccordingToParents(parent);
        assertEquals("The rounded coordinates are not the expected ones for " + parent.getIdentifier(), new PrecisionPoint(11, 11), parentLocation);
        PrecisionPoint child1Location = SiriusElkUtil.getRoundedCoordinatesAccordingToParents(child1);
        assertEquals("The rounded coordinates are not the expected ones for " + child1.getIdentifier(), new PrecisionPoint(10, 10), child1Location);
        assertEquals("The absolute coordinates are not the expected ones for " + child1.getIdentifier(), new PrecisionPoint(21, 21), (PrecisionPoint) child1Location.translate(parentLocation), 0.5);
        PrecisionPoint child2Location = SiriusElkUtil.getRoundedCoordinatesAccordingToParents(child2);
        assertEquals("The rounded coordinates are not the expected ones for " + child2.getIdentifier(), new PrecisionPoint(11, 11), child2Location);
        assertEquals("The absolute coordinates are not the expected ones for " + child1.getIdentifier(), new PrecisionPoint(31.5, 31.5), (PrecisionPoint) child2Location.translate(child1Location),
                0.5);
        PrecisionPoint child3Location = SiriusElkUtil.getRoundedCoordinatesAccordingToParents(child3);
        assertEquals("The rounded coordinates are not the expected ones for " + child3.getIdentifier(), new PrecisionPoint(10, 10), child3Location);
        assertEquals("The absolute coordinates are not the expected ones for " + child3.getIdentifier(), new PrecisionPoint(42, 42), (PrecisionPoint) child3Location.translate(child2Location), 0.5);
    }

    private ElkNode createNode(ElkNode parent, String identifier, PrecisionPoint location) {
        ElkNode node = ElkGraphUtil.createNode(parent);
        node.setIdentifier(identifier);
        node.setLocation(location.preciseX(), location.preciseY());
        return node;
    }

    /**
     * Asserts that two precision points are equal concerning a delta. If they are not an AssertionFailedError is thrown
     * with the given message. If the expected value, x or y, is infinity then the delta value is ignored.
     */
    private void assertEquals(String message, PrecisionPoint expected, PrecisionPoint actual, double delta) {
        assertEquals(message, expected.preciseX(), actual.preciseX(), delta);
        assertEquals(message, expected.preciseY(), actual.preciseY(), delta);
    }

    private void checkRoutingStyle(AbstractDiagramEdgeEditPart edgeEditPart, String message, String edgeName, int expectedRoutingStyle) {
        String fullMessage = MessageFormat.format(message, edgeName);
        View notationView = edgeEditPart.getNotationView();
        DDiagramElement dde = edgeEditPart.resolveDiagramElement();
        assertTrue("The diagram element of the AbstractDiagramEdgeEditPart must be a DEdge.", dde instanceof DEdge);
        DEdgeQuery dEdgeQuery = new DEdgeQuery((DEdge) dde);
        Routing routing = dEdgeQuery.getRouting();
        assertEquals(fullMessage, expectedRoutingStyle, routing.getValue());
        // Check that custom features list contains the routingStyle (if necessary)
        Option<EdgeMapping> optionalEdgeMapping = new IEdgeMappingQuery(((DEdge) dde).getActualMapping()).getEdgeMapping();
        if (optionalEdgeMapping.some()) {
            EdgeRouting originalEdgeRouting = optionalEdgeMapping.get().getStyle().getRoutingStyle();
            if (EdgeRouting.get(expectedRoutingStyle).equals(originalEdgeRouting)) {
                // The routing is the same, so the edge should not appear as customized.
                assertTrue("This edge \"" + edgeName + "\" must not appear as customized.",
                        (!(new DDiagramElementQuery(dde).isCustomized() || new ViewQueryWithoutFontNameCheck(notationView).isCustomized())));
            } else {
                assertTrue("The custom features list of the style of DEdge \"" + edgeName + "\" must contain the routingStyle.",
                        dde.getStyle().getCustomFeatures().contains(DiagramPackage.Literals.DEDGE__ROUTING_STYLE.getName()));
            }
        }
    }

    protected void openDiagram(String diagramName) {
        diagram = (DDiagram) getRepresentationsByName(diagramName).toArray()[0];
        editorPart = (IDiagramWorkbenchPart) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        checkStringValueStyle();
    }

    protected void checkStringValueStyle() {
        Diagram gmfDiag = getGmfDiagram(diagram);
        if (gmfDiag != null) {
            TreeIterator<EObject> childIterator = gmfDiag.eAllContents();
            while (childIterator.hasNext()) {
                EObject eObject = childIterator.next();
                if (eObject instanceof Edge edge) {
                    StringValueStyle stringValueStyle = (StringValueStyle) edge.getStyle(NotationPackage.Literals.STRING_VALUE_STYLE);
                    if (stringValueStyle != null && GmfLayoutCommand.JUNCTION_POINTS_STYLE_NAME.equals(stringValueStyle.getName()) && "()".equals(stringValueStyle.getStringValue())) {
                        fail("At least one edge contains an empty junctionPoints StringValueStyle.");
                    }
                }
            }
        }
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
            IGraphicalEditPart c4EditPart = getEditPart("MyClass4", IGraphicalEditPart.class);
            Rectangle c4Bounds = c4EditPart.getFigure().getBounds().getCopy();
            assertTrue("As the Note is not moved, it is expected to overlap \"MyClass4\" node.", currentNoteBounds.intersects(c4Bounds));
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
        List<? extends EditPart> realEditPartsToSelect = ToolUtilities.getSelectionWithoutDependants(editPartsToSelect);
        arrangeRequest.setPartsToArrange(realEditPartsToSelect);
        // Validate that there is a common parent (as in
        // org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction.getTargetEditPartForArrangeSelection(List)).
        boolean validated = true;
        EditPart parentEP = getSelectionParent(realEditPartsToSelect);
        for (int i = 1; i < realEditPartsToSelect.size(); i++) {
            EditPart part = realEditPartsToSelect.get(i);
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
            IGraphicalEditPart editPart = getEditPart(name, IGraphicalEditPart.class);
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

    private void checkEdgeWithEdgeAsTarget(String sourceNodeName, Connection targetConnection, int expectedNumberOfPoints, boolean forceRectilinearRouting) {
        DEdgeEditPart edgePointingToAnotherEdge = getEdgeWithNodeAsSource(sourceNodeName);
        Connection connectionFigure = edgePointingToAnotherEdge.getConnectionFigure();
        PointList points = connectionFigure.getPoints();
        assertEquals("Wrong number of points for the edge between \"" + sourceNodeName + "\" and another edge.", expectedNumberOfPoints, points.size());
        List<Object> targetLineSegments = PointListUtilities.getLineSegments(targetConnection.getPoints());
        assertTrue("The last point of the egde between \"" + sourceNodeName + "\" and another edge should be on the target edge.", containsPoint(targetLineSegments, points.getLastPoint()));
        for (int i = 0; i < points.size() - 1; i++) {
            Point pointToTest = points.getPoint(i);
            assertFalse("Only the last point of the egde between \"" + sourceNodeName + "\" and another edge should be on the target edge. The point " + pointToTest + " is also on the target edge.",
                    containsPoint(targetLineSegments, pointToTest));
        }
        if (forceRectilinearRouting) {
            if (expectedNumberOfPoints == 2) {
                assertTrue("The edge between \"" + sourceNodeName + "\" and another edge is not horizontal.",
                        new LineSeg(connectionFigure.getPoints().getFirstPoint(), connectionFigure.getPoints().getLastPoint()).isHorizontal());
            } else if (expectedNumberOfPoints == 3 || expectedNumberOfPoints == 5) {
                for (int i = 0; i < points.size() - 2; i++) {
                    LineSeg aSegment = new LineSeg(points.getPoint(i), points.getPoint(i + 1));
                    LineSeg aFollowingSegment = new LineSeg(points.getPoint(i + 1), points.getPoint(i + 2));
                    assertTrue("A segment of the egde between \"" + sourceNodeName + "\" and another edge should make a right angle with its following segment.",
                            (aSegment.isHorizontal() && aFollowingSegment.isVertical()) || (aSegment.isVertical() && aFollowingSegment.isHorizontal()));
                }
            } else {
                fail(expectedNumberOfPoints + " is not an handled value for the expectedNumberOfPoints parameters.");
            }
        }
    }

    private void checkEdgeWithEdgeAsSource(String targetNodeName, Connection sourceConnection, int expectedNumberOfPoints, boolean forceRectilinearRouting) {
        DEdgeEditPart edgeStartingFormAnotherEdge = getEdgeWithNodeAsTarget(targetNodeName);
        Connection connectionFigure = edgeStartingFormAnotherEdge.getConnectionFigure();
        PointList points = connectionFigure.getPoints();
        assertEquals("Wrong number of points for the edge between another edge and \"" + targetNodeName + "\".", expectedNumberOfPoints, points.size());
        List<Object> sourceLineSegments = PointListUtilities.getLineSegments(sourceConnection.getPoints());
        assertTrue("The first point of the egde between another edge and \"" + targetNodeName + "\" should be on the source edge.", containsPoint(sourceLineSegments, points.getFirstPoint()));
        for (int i = 1; i < points.size(); i++) {
            Point pointToTest = points.getPoint(i);
            assertFalse("Only the first point of the egde between another edge and \"" + targetNodeName + "\" should be on the source edge. The point " + pointToTest + " is also on the source edge.",
                    containsPoint(sourceLineSegments, pointToTest));
        }
        if (forceRectilinearRouting) {
            if (expectedNumberOfPoints == 2) {
                assertTrue("The edge between another edge and \"" + targetNodeName + "\" is not horizontal.",
                        new LineSeg(connectionFigure.getPoints().getFirstPoint(), connectionFigure.getPoints().getLastPoint()).isHorizontal());
            } else if (expectedNumberOfPoints == 3 || expectedNumberOfPoints == 4 || expectedNumberOfPoints == 5) {
                for (int i = 0; i < points.size() - 2; i++) {
                    LineSeg aSegment = new LineSeg(points.getPoint(i), points.getPoint(i + 1));
                    LineSeg aFollowingSegment = new LineSeg(points.getPoint(i + 1), points.getPoint(i + 2));
                    assertTrue("The first segment of the egde between another edge and \"" + targetNodeName + "\" should make a right angle with the second segment.",
                            (aSegment.isHorizontal() && aFollowingSegment.isVertical()) || (aSegment.isVertical() && aFollowingSegment.isHorizontal()));
                }
            } else {
                fail(expectedNumberOfPoints + " is not an handled value for the expectedNumberOfPoints parameters.");
            }
        }
    }

    private boolean containsPoint(List<Object> targetLineSegments, Point pointTotest) {
        LineSeg lineSeg = PointListUtilities.getNearestSegment(targetLineSegments, pointTotest.x, pointTotest.y);
        return lineSeg.containsPoint(pointTotest, 0);
    }

    /**
     * Check that the edge between <code>sourceNodeName</code> and <code>targetNodeName</code> exists and optionally
     * check if it is an horizontal straight line (only two points with same y axis).
     * 
     * @param sourceNodeName
     *            The name of the source node of the edge
     * @param targetNodeName
     *            The name of the target node of the edge
     * @param mustBeHorizontal
     *            true if we must check that the edge is an horizontal edge, false if we want to check that the edge has
     *            3 segments with right angle between each of them.
     */
    private DEdgeEditPart checkEdge(String sourceNodeName, String targetNodeName, boolean mustBeHorizontal) {
        IGraphicalEditPart sourceNodeEditPart = getEditPart(sourceNodeName, AbstractDiagramContainerEditPart.class);
        IGraphicalEditPart targetNodeEditPart = getEditPart(targetNodeName, AbstractDiagramContainerEditPart.class);

        Optional<DEdgeEditPart> edgeEditPart = sourceNodeEditPart.getSourceConnections().stream().filter(DEdgeEditPart.class::isInstance).map(DEdgeEditPart.class::cast)
                .filter(deep -> targetNodeEditPart.equals(((DEdgeEditPart) deep).getTarget())).findFirst();
        assertTrue("The diagram should have an edge between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\".", edgeEditPart.isPresent());
        Connection connectionFigure = edgeEditPart.get().getConnectionFigure();
        if (mustBeHorizontal) {
            assertEquals("Wrong number of points for the edge between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\".", 2, connectionFigure.getPoints().size());
            assertTrue("The edge between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\" is not horizontal.",
                    new LineSeg(connectionFigure.getPoints().getFirstPoint(), connectionFigure.getPoints().getLastPoint()).isHorizontal());
        } else {
            assertEquals("Wrong number of points for the edge between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\".", 4, connectionFigure.getPoints().size());
            PointList points = connectionFigure.getPoints();
            LineSeg firstSegment = new LineSeg(points.getPoint(0), points.getPoint(1));
            LineSeg secondSegment = new LineSeg(points.getPoint(1), points.getPoint(2));
            LineSeg thirdSegment = new LineSeg(points.getPoint(2), points.getPoint(3));
            assertTrue("The first segment of the egde between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\" should make a right angle with the second segment.",
                    (firstSegment.isHorizontal() && secondSegment.isVertical()) || (firstSegment.isVertical() && secondSegment.isHorizontal()));
            assertTrue("The second segment of the egde between \"" + sourceNodeName + "\" and \"" + targetNodeName + "\" should make a right angle with the third segment.",
                    (secondSegment.isHorizontal() && thirdSegment.isVertical()) || (secondSegment.isVertical() && thirdSegment.isHorizontal()));
        }
        return edgeEditPart.get();
    }

    /**
     * Get the diagramElement with the current name.
     * 
     * @param nodeName
     *            The name of the diagram element
     * @return the corresponding diagramElement.
     */
    protected DDiagramElement getDDiagramElement(String nodeName) {
        Optional<DDiagramElement> optionalDDE = diagram.getDiagramElements().stream().filter(dde -> dde.getName().equals(nodeName)).findFirst();
        assertTrue("The diagram should have a node named \"" + nodeName + "\".", optionalDDE.isPresent());
        return optionalDDE.get();
    }

    /**
     * Get the edit part with the current name.
     * 
     * @param nodeName
     *            The name of the diagram element
     * @param expectedClassType
     *            the expected type of representation element
     * @return the corresponding diagramElement.
     */
    protected <T> T getEditPart(String nodeName, Class<T> expectedClassType) {
        DDiagramElement dde = getDDiagramElement(nodeName);
        return getEditPart(dde, expectedClassType);
    }

    /**
     * Get the edit part with the current name.
     * 
     * @param dDiagramElement
     *            The diagram element
     * @param expectedClassType
     *            the expected type of representation element
     * @return the corresponding diagramElement.
     */
    @SuppressWarnings("unchecked")
    protected <T> T getEditPart(DDiagramElement dDiagramElement, Class<T> expectedClassType) {
        IGraphicalEditPart editPart = getEditPart(dDiagramElement);
        assertTrue("The node for \"" + dDiagramElement.getName() + "\" should be a " + expectedClassType.getSimpleName() + " but was a " + editPart.getClass().getSimpleName(),
                expectedClassType.isInstance(editPart));
        return (T) editPart;
    }

    /**
     * Get the DEdge with the current name.
     * 
     * @param edgeName
     *            The name of the edge
     * @return the corresponding edge.
     */
    protected DEdge getDEdge(String edgeName) {
        Optional<DEdge> optionalDEdge = diagram.getEdges().stream().filter(dEdge -> edgeName.equals(dEdge.getName())).findFirst();
        assertTrue("The diagram should have an edge named \"" + edgeName + "\".", optionalDEdge.isPresent());
        return optionalDEdge.get();
    }

    /**
     * Get the edit part with the current name.
     * 
     * @param edgeName
     *            The name of the edge
     * @param expectedClassType
     *            the expected type of the edge
     * @return the corresponding edge EditPart.
     */
    protected <T> T getEdgeEditPart(String edgeName, Class<T> expectedClassType) {
        DEdge dEdge = getDEdge(edgeName);
        return getEditPart(dEdge, expectedClassType);
    }

    /**
     * Get the first edge having <code>sourceNodeName</code> as source node.
     * 
     * @param sourceNodeName
     *            The name of the source node of the edge
     * @return the first edge having <code>sourceNodeName</code> as source node.
     */
    private DEdgeEditPart getEdgeWithNodeAsSource(String sourceNodeName) {
        IGraphicalEditPart sourceNodeEditPart = getEditPart(sourceNodeName, AbstractDiagramContainerEditPart.class);

        Optional<DEdgeEditPart> edgeEditPart = sourceNodeEditPart.getSourceConnections().stream().filter(DEdgeEditPart.class::isInstance).map(DEdgeEditPart.class::cast).findFirst();
        assertTrue("The diagram should have an edge starting from \"" + sourceNodeName + "\".", edgeEditPart.isPresent());
        return edgeEditPart.get();
    }

    /**
     * Get the first edge having <code>targetNodeName</code> as target node.
     * 
     * @param targetNodeName
     *            The name of the target node of the edge
     * @return the first edge having <code>targetNodeName</code> as target node.
     */
    private DEdgeEditPart getEdgeWithNodeAsTarget(String targetNodeName) {
        IGraphicalEditPart targetNodeEditPart = getEditPart(targetNodeName, AbstractDiagramContainerEditPart.class);

        Optional<DEdgeEditPart> edgeEditPart = targetNodeEditPart.getTargetConnections().stream().filter(DEdgeEditPart.class::isInstance).map(DEdgeEditPart.class::cast).findFirst();
        assertTrue("The diagram should have an edge ending to \"" + targetNodeName + "\".", edgeEditPart.isPresent());
        return edgeEditPart.get();
    }

    /**
     * Set a default routing style on the description named "representationDescriptionName".
     * 
     * @param representationDescriptionName
     *            name of the description to modify
     * @param edgeRouting
     *            the routing style to use
     */
    private void setDefaultRoutingStyle(String representationDescriptionName, org.eclipse.elk.core.options.EdgeRouting edgeRouting) {
        try {
            // Get Table description
            ResourceSet set = new ResourceSetImpl();
            Resource vsm = set.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME, true), true);
            Group group = (Group) vsm.getContents().get(0);
            Optional<RepresentationDescription> description = group.getOwnedViewpoints().stream().flatMap(vp -> vp.getOwnedRepresentations().stream())
                    .filter(d -> d.getName().equals(representationDescriptionName)).findFirst();

            if (description.isPresent()) {
                final DiagramDescription desc = (DiagramDescription) description.get();
                CustomLayoutConfiguration layout = (CustomLayoutConfiguration) desc.getLayout();
                // Modify header column width value
                TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
                DescriptionFactory layoutDescriptionFactory = DescriptionFactory.eINSTANCE;
                EnumLayoutOption enumLayoutOption = layoutDescriptionFactory.createEnumLayoutOption();
                enumLayoutOption.setId("org.eclipse.elk.edgeRouting");
                enumLayoutOption.getTargets().add(LayoutOptionTarget.PARENT);
                EnumLayoutValue enumLayoutValue = layoutDescriptionFactory.createEnumLayoutValue();
                enumLayoutValue.setName(edgeRouting.name());
                enumLayoutOption.setValue(enumLayoutValue);
                domain.getCommandStack().execute(new AddCommand(domain, layout, DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions(), enumLayoutOption));

                // Save modification
                vsm.save(Collections.emptyMap());
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
