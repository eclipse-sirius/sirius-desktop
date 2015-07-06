/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout.margin;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FigureQuery;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Maps;

/**
 * Test border margin computation for Container and Lists edit parts. The margin
 * was fixed to 5 pixels, but the result was not clean with large borders 10
 * pixels for example): scroll bars were displayed on the border, label, list
 * items and children figure bounds (placed by layout/arrange all) sometimes
 * intersected the borders. The current behavior produce a 4+border size margin.
 * 
 * @author mporhel
 * 
 */
public class BorderMarginTest extends SiriusDiagramTestCase {

    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/margin/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + "My.ecore";

    private static final String SESSION_NAME = "My.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String MODELER_PATH = TEST_DIR + "margin.odesign";

    private static final String DIAGRAM_AND_DESCRIPTION_ID = "Margin Diagram";

    private static final String AUTO_SIZE = "Margin Diagram";

    private static final String NO_VISIBLE_SCROLL_BAR = "No scroll bar on fixed size from auto size";

    private static final String NO_VISIBLE_SCROLL_BAR_WIN = "No scroll bar on fixed size from auto size for Windows";

    private static final Map<Integer, Dimension> listAutoSizes = Maps.newHashMap();

    private static final Map<Integer, Dimension> containerAutoSizes = Maps.newHashMap();

    private static final Map<Integer, Dimension> listAutoSizesWin = Maps.newHashMap();

    private static final Map<Integer, Dimension> containerAutoSizesWin = Maps.newHashMap();

    {
        // Comic Sans MS has been used to create the test data, but there are
        // differences between linux and windows.
        listAutoSizes.put(Integer.valueOf(0), new Dimension(183, 49));
        listAutoSizes.put(Integer.valueOf(1), new Dimension(181, 49));
        listAutoSizes.put(Integer.valueOf(10), new Dimension(206, 67));

        listAutoSizesWin.put(Integer.valueOf(0), new Dimension(182, 47));
        listAutoSizesWin.put(Integer.valueOf(1), new Dimension(180, 47));
        listAutoSizesWin.put(Integer.valueOf(10), new Dimension(205, 65));

        containerAutoSizes.put(Integer.valueOf(0), new Dimension(183, 74));
        containerAutoSizes.put(Integer.valueOf(1), new Dimension(181, 74));
        containerAutoSizes.put(Integer.valueOf(10), new Dimension(206, 92));

        containerAutoSizesWin.put(Integer.valueOf(0), new Dimension(182, 74));
        containerAutoSizesWin.put(Integer.valueOf(1), new Dimension(180, 74));
        containerAutoSizesWin.put(Integer.valueOf(10), new Dimension(205, 92));
    }

    private DDiagramEditor diagramEditor;

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    private void openDiagram(String name) {
        assertFalse("Diagram name to find and open cannot be emtpy.", StringUtil.isEmpty(name));

        Collection<DRepresentation> representations = getRepresentations(DIAGRAM_AND_DESCRIPTION_ID);
        for (DRepresentation rep : representations) {
            if (rep instanceof DDiagram && name.equals(rep.getName())) {
                diagram = (DDiagram) rep;
            }
        }

        assertNotNull(diagram);
        diagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagramEditor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        diagramEditor = null;
        diagram = null;
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Test margin and insets for lists and containers.
     * 
     * @throws Exception
     */
    public void testMarginBorderInsets() throws Exception {
        openDiagram(AUTO_SIZE);

        int[] expectedVSMBorderSizes = new int[] { 0, 1, 10, 0, 1, 10 };
        int[] expectedFigureBorderSizes = new int[] { 0, 1, 10, 0, 1, 10 };

        List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
        for (int i = 0; i < ownedDiagramElements.size(); i++) {
            DDiagramElement dde = ownedDiagramElements.get(i);
            ContainerStyle style = (ContainerStyle) ((DDiagramElementContainer) dde).getStyle();
            assertEquals("Check test data.", expectedVSMBorderSizes[i], style.getBorderSize().intValue());

            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);
            int expectedFigureBorderSize = expectedFigureBorderSizes[i];

            // Main margin used to place the label and the compartment.
            Insets expectedMainMargin = new Insets(expectedFigureBorderSize + IContainerLabelOffsets.LABEL_OFFSET - 1, expectedFigureBorderSize, expectedFigureBorderSize, expectedFigureBorderSize);

            // Check that the border size is reported into the border margin and
            // is not only used to draw the border.
            if (part instanceof IDiagramListEditPart) {
                IFigure figure = ((IDiagramListEditPart) part).getPrimaryShape();
                Insets insets = figure.getBorder().getInsets(figure);
                assertEquals("Pb in list figure for a border size=" + expectedFigureBorderSize, expectedMainMargin, insets);

                // Check that scroll bars are not displayed on the border.
                DNodeListElement listChild = ((DNodeList) dde).getOwnedElements().iterator().next();
                IGraphicalEditPart listChildEditPart = getEditPart(listChild);

                ResizableCompartmentFigure compartmentFigure = (ResizableCompartmentFigure) ((IGraphicalEditPart) listChildEditPart.getParent()).getFigure();
                IFigure contentPane = compartmentFigure.getContentPane();
                Insets contentPaneInsets = contentPane.getBorder().getInsets(contentPane);
                assertEquals("Scrollbar pb in list figure for a border size=" + expectedFigureBorderSize, new Insets(1, 4, 0, 4), contentPaneInsets);
            } else if (part instanceof IDiagramContainerEditPart) {
                IFigure figure = ((IDiagramContainerEditPart) part).getPrimaryShape();
                Insets insets = figure.getBorder().getInsets(figure);
                assertEquals("Pb in container figure for a border size=" + expectedFigureBorderSize, expectedMainMargin, insets);

                // Check that scroll bars are not displayed on the border.
                DDiagramElement containerChild = ((DNodeContainer) dde).getOwnedDiagramElements().iterator().next();
                IGraphicalEditPart containerChildEditPart = getEditPart(containerChild);

                IFigure compartmentFigure = ((IGraphicalEditPart) containerChildEditPart.getParent()).getFigure();
                IFigure scrollPane = (IFigure) compartmentFigure.getChildren().get(1);
                Insets scrollPaneInsets = scrollPane.getBorder().getInsets(scrollPane);
                assertEquals("Scrollbar pb in container figure for a border size=" + expectedFigureBorderSize, new Insets(expectedFigureBorderSize + 4), scrollPaneInsets);
            }
        }
    }

    /**
     * Test that auto-size did not change. It could allow to detect unwanted
     * figure/margin/... changes. Test that scrollbars are not displayed in list
     * and containers in auto-size.<BR>
     * This test is based on the width of the label. Indeed, a font has not the
     * same size according to the OS used to launch the test. So we can not use
     * a stored value. Only the width is checked because the height is more
     * complex.
     * 
     * @throws Exception
     */
    public void testAutoSize() throws Exception {
        if (!TestsUtil.isJuno3Platform()) {
            openDiagram(AUTO_SIZE);

            // Prepare a global failure message with all expected and observed
            // sizes
            StringBuilder sb = new StringBuilder();
            sb.append("Jenkins wrong expected sizes, some figure size from auto-sized GMF Node have changed:").append("\n");
            boolean wrongSizes = false;

            List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
            for (int i = 0; i < ownedDiagramElements.size(); i++) {
                DDiagramElement dde = ownedDiagramElements.get(i);

                AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);
                Node gmfNode = (Node) part.getModel();

                assertEquals("GMF Node should be in auto-size.", -1, ((Size) gmfNode.getLayoutConstraint()).getHeight());
                assertEquals("GMF Node should be in auto-size.", -1, ((Size) gmfNode.getLayoutConstraint()).getWidth());

                String borderSizeString = dde.getName().substring(29, dde.getName().length() - 1);
                sb.append(" ." + dde.eClass().getName() + " " + dde.getName());
                Option<IFigure> labelFigure = new FigureQuery(part.getFigure()).getLabelFigure();
                if (labelFigure.some()) {
                    Dimension labelFigureSize = labelFigure.get().getBounds().getSize();
                    int borderSize = Integer.valueOf(borderSizeString);
                    int expectedWidth = labelFigureSize.width + 2 + (borderSize * 2);
                    Dimension figureSize = part.getFigure().getBounds().getSize();
                    if (expectedWidth != figureSize.width) {
                        wrongSizes = true;
                        sb.append(", expected: " + expectedWidth);
                        sb.append(" but was: " + figureSize.width).append("\n");
                    } else {
                        sb.append(" expected and observed: " + expectedWidth).append("\n");
                    }
                }

                assertNoVisibleScrollBar(dde, part);
            }
            // At least one figure does not present the expected auto-size.
            assertFalse(sb.toString(), wrongSizes);
        }
    }

    /**
     * Test that scrollbars are not displayed in list and containers when the
     * size has been set by the user to the same value than auto-size (through a
     * copy / paste layout for example).
     * 
     * @throws Exception
     */
    public void testFixedSizeFromAutoSizeDoNotDisplayScrollBars() throws Exception {
        if (!TestsUtil.isJuno3Platform()) {
            if (Platform.getOS().startsWith(Platform.OS_WIN32)) {
                openDiagram(NO_VISIBLE_SCROLL_BAR_WIN);
            } else {
                openDiagram(NO_VISIBLE_SCROLL_BAR);
            }

            List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
            for (int i = 0; i < ownedDiagramElements.size(); i++) {
                DDiagramElement dde = ownedDiagramElements.get(i);

                AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);
                Node gmfNode = (Node) part.getModel();

                assertNotSame("GMF Node should not be in auto-size.", -1, ((Size) gmfNode.getLayoutConstraint()).getHeight());
                assertNotSame("GMF Node should not be in auto-size.", -1, ((Size) gmfNode.getLayoutConstraint()).getWidth());

                Dimension expected = getExpectedAutoSize(dde);
                assertEquals("Figure size was set to be the expected auto-sized. Auto-size has changed." + dde.getTarget(), expected, part.getFigure().getBounds().getSize());

                assertNoVisibleScrollBar(dde, part);
            }
        }
    }

    private void assertNoVisibleScrollBar(DDiagramElement dde, AbstractDiagramElementContainerEditPart part) {
        IFigure hScrollBar = null;
        IFigure vScrollBar = null;
        if (part instanceof IDiagramListEditPart) {
            DNodeListElement listChild = ((DNodeList) dde).getOwnedElements().iterator().next();
            IGraphicalEditPart listChildEditPart = getEditPart(listChild);

            ResizableCompartmentFigure compartmentFigure = (ResizableCompartmentFigure) ((IGraphicalEditPart) listChildEditPart.getParent()).getFigure();
            hScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetHorizontalScrollBar();
            vScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetVerticalScrollBar();
        } else if (part instanceof IDiagramContainerEditPart) {
            DDiagramElement containerChild = ((DNodeContainer) dde).getOwnedDiagramElements().iterator().next();
            IGraphicalEditPart containerChildEditPart = getEditPart(containerChild);

            ResizableCompartmentFigure compartmentFigure = (ResizableCompartmentFigure) ((IGraphicalEditPart) containerChildEditPart.getParent()).getFigure();
            hScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetHorizontalScrollBar();
            vScrollBar = ((AnimatableScrollPane) compartmentFigure.getScrollPane()).basicGetVerticalScrollBar();
        }
        boolean hScrollBarVisible = hScrollBar != null && hScrollBar.isVisible();
        boolean vScrollBarVisible = vScrollBar != null && vScrollBar.isVisible();
        assertFalse("No scrollbar should be visible for " + dde.eClass().getName() + " " + dde.getName() + " (hScrollBar:" + hScrollBarVisible + ", vScrollBar:" + vScrollBarVisible + ").",
                hScrollBarVisible || vScrollBarVisible);
    }

    private Dimension getExpectedAutoSize(DDiagramElement dde) {
        Map<Integer, Dimension> expectedSizeMap = null;
        ContainerStyle style = (ContainerStyle) ((DDiagramElementContainer) dde).getStyle();

        if (dde instanceof DNodeList) {
            if (Platform.getOS().startsWith(Platform.OS_WIN32)) {
                expectedSizeMap = listAutoSizesWin;
            } else {
                expectedSizeMap = listAutoSizes;
            }
        } else if (dde instanceof DNodeContainer) {
            if (Platform.getOS().startsWith(Platform.OS_WIN32)) {
                expectedSizeMap = containerAutoSizesWin;
            } else {
                expectedSizeMap = containerAutoSizes;
            }
        }
        return expectedSizeMap.get(style.getBorderSize());
    }
}
