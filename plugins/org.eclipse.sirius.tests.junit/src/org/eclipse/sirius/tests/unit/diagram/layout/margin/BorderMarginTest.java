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
package org.eclipse.sirius.tests.unit.diagram.layout.margin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FigureQuery;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

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

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private static final String SESSION_FILENAME = "My.aird";

    private static final String MODELER_FILENAME = "margin.odesign";

    private static final String DIAGRAM_AND_DESCRIPTION_ID = "Margin Diagram";

    private static final String AUTO_SIZE = "Margin Diagram";

    private static final String NO_VISIBLE_SCROLL_BAR = "No scroll bar on fixed size from auto size";

    private DDiagramEditor diagramEditor;

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(TEST_DIR + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(TEST_DIR + SESSION_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(TEST_DIR + MODELER_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_FILENAME);
    }

    private void openDiagram(String name) {
        assertFalse("Diagram name to find and open cannot be emtpy.", StringUtil.isEmpty(name));

        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(DIAGRAM_AND_DESCRIPTION_ID);
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getRepresentation() instanceof DDiagram && name.equals(representationDescriptor.getName())) {
                diagram = (DDiagram) representationDescriptor.getRepresentation();
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
            int defaultExpectedWidth = part instanceof IDiagramListEditPart ? LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width : LayoutUtils.DEFAULT_CONTAINER_DIMENSION.width;
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
                expectedWidth = Math.max(expectedWidth, defaultExpectedWidth);
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

    /**
     * Test that scrollbars are not displayed in list and containers when the
     * size has been set by the user to the same value than auto-size (through a
     * copy / paste layout for example). In this test, we set the same size as
     * the figure in auto-size mode.
     * 
     * @throws Exception
     */
    public void testFixedSizeFromAutoSizeDoNotDisplayScrollBars() throws Exception {
        // Open "auto-size" diagram to retrieve the real figure size
        // according to each OS (fonts have not the same size on each OS)
        openDiagram(AUTO_SIZE);
        Map<EObject, Map<DiagramElementMapping, Dimension>> autoSizedDimensions = new HashMap<>();

        List<DDiagramElement> autoSizedOwnedDiagramElements = diagram.getOwnedDiagramElements();
        for (int i = 0; i < autoSizedOwnedDiagramElements.size(); i++) {
            DDiagramElement dde = autoSizedOwnedDiagramElements.get(i);

            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);
            Dimension figureSize = part.getFigure().getBounds().getSize();
            Map<DiagramElementMapping, Dimension> currentSemanticDimensions = autoSizedDimensions.get(dde.getTarget());
            if (autoSizedDimensions.get(dde.getTarget()) == null) {
                currentSemanticDimensions = new HashMap<>();
                autoSizedDimensions.put(dde.getTarget(), currentSemanticDimensions);
            }
            currentSemanticDimensions.put(dde.getDiagramElementMapping(), figureSize);
        }
        // Open the diagram and set the GMF size according to the figure
        // size.
        openDiagram(NO_VISIBLE_SCROLL_BAR);

        List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CompoundCommand resizeAllDiagramElementsAccordingToAutoSizeDimentsion = new CompoundCommand("Resize elements");
        for (int i = 0; i < ownedDiagramElements.size(); i++) {
            DDiagramElement dde = ownedDiagramElements.get(i);

            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);
            Node gmfNode = (Node) part.getModel();
            Dimension autoSizeDimension = autoSizedDimensions.get(dde.getTarget()).get(dde.getDiagramElementMapping());
            resizeAllDiagramElementsAccordingToAutoSizeDimentsion.append(SetCommand.create(domain, gmfNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, autoSizeDimension.height()));
            resizeAllDiagramElementsAccordingToAutoSizeDimentsion.append(SetCommand.create(domain, gmfNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__WIDTH, autoSizeDimension.width()));
        }
        domain.getCommandStack().execute(resizeAllDiagramElementsAccordingToAutoSizeDimentsion);
        // Check that with these sizes, there is no scroll bar.
        for (int i = 0; i < ownedDiagramElements.size(); i++) {
            DDiagramElement dde = ownedDiagramElements.get(i);
            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) getEditPart(dde);

            assertNoVisibleScrollBar(dde, part);
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
}
