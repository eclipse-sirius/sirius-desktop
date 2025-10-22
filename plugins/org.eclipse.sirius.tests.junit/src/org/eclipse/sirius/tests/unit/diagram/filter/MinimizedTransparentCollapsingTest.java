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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.ext.draw2d.figure.ITransparentFigure;
import org.eclipse.sirius.ext.draw2d.figure.StyledFigure;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests to check the behavior of transparent minimization of border node on
 * MappingFilter "collapse" activation.
 * 
 * @author mporhel
 */
public class MinimizedTransparentCollapsingTest extends SiriusDiagramTestCase {

    private static final String NORMAL_DIAGRAM = "tc1412";

    private static final String COLLAPSED_DIAGRAM = "tc1412COLLAPSED";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/collapse/tc1412/tc1412.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/collapse/tc1412/tc1412.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compositefilter/collapse/tc1412/tc1412.odesign";

    private static final String REPRESENTATION_DESC_NAME = "tc1412";

    private DDiagram diagram;

    private DiagramEditor editor;

    private Collection<DRepresentationDescriptor> representationDescriptors;

    private Predicate<DDiagramElement> collapsable = new Predicate<DDiagramElement>() {
        @Override
        public boolean apply(DDiagramElement input) {
            return "EAttribute as border node".equals(new DDiagramElementQuery(input).getMappingName().get());
        }
    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        representationDescriptors = getRepresentationDescriptors(REPRESENTATION_DESC_NAME);
    }

    /**
     * Checks that the points contained in all edges of the diagram are in the
     * expected positions.
     * 
     * @throws Exception
     */
    public void testCollapse() throws Exception {
        openDiagram(0, NORMAL_DIAGRAM, false);

        testCollapse(false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testOpenCollapsedDiagram() throws Exception {
        openDiagram(1, COLLAPSED_DIAGRAM, true);

        testCollapse(true);
    }

    private void openDiagram(int index, String diagramName, boolean collapsed) {
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getRepresentation() instanceof DDiagram && diagramName.equals(representationDescriptor.getName())) {
                diagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        assertNotNull("Diagram named " + diagramName + " cannot be found.", diagram);

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertNotNull(editor);

        hideAll();
        revealAll();

        checkDiagramConsistency(collapsed);
    }

    private void testCollapse(boolean alreadyCollapsed) {
        if (alreadyCollapsed) {
            uncollapsePorts();
        }

        collapsePorts();

        uncollapsePorts();

        if (!alreadyCollapsed) {
            collapsePorts();
        }
    }

    private void checkDiagramConsistency(boolean collapsed) {
        checkDiagramElements(true);

        List<DDiagramElement> borderNodes = Lists.newArrayList(Iterables.filter(diagram.getDiagramElements(), collapsable));
        assertEquals("Wrong number of border nodes to test", 7, borderNodes.size());

        for (DDiagramElement dde : borderNodes) {
            IGraphicalEditPart editPart = getEditPart(dde);

            assertTrue(editPart instanceof IDiagramBorderNodeEditPart);

            StyledFigure styledFigure = DiagramElementEditPartOperation.getStyledFigure(editPart.getFigure());

            assertNotNull(styledFigure);

            assertTrue(styledFigure instanceof ITransparentFigure);

            ITransparentFigure fig = (ITransparentFigure) styledFigure;

            assertEquals(collapsed, fig.isTransparent());

            Dimension expectedDim = new Dimension(30, 30);
            if (fig instanceof WorkspaceImageFigure) {
                expectedDim.height = new Double(expectedDim.width / ((WorkspaceImageFigure) fig).getImageAspectRatio()).intValue();
            }

            if (collapsed) {
                // the ratio is now kept when collapsing a workspace image
                // figure.
                if (fig instanceof WorkspaceImageFigure) {
                    expectedDim.setSize(new NodeQuery((Node) editPart.getModel()).getCollapsedSize());
                    expectedDim.height = new Double(expectedDim.width / ((WorkspaceImageFigure) fig).getImageAspectRatio()).intValue();
                } else {
                    expectedDim.setSize(new NodeQuery((Node) editPart.getModel()).getCollapsedSize());
                }
            }

            // We check the airStyleDefaultSizeNodEfigure because of
            // GaugeComposite Figure whose size is not updated
            Rectangle figureBounds = styledFigure.getParent().getBounds();

            assertEquals(expectedDim.width, figureBounds.width);
            assertEquals(expectedDim.height, figureBounds.height);

        }

    }

    private void checkDiagramElements(boolean consistent) {
        assertEquals(24, diagram.getDiagramElements().size());

        Predicate<DDiagramElement> isVisible = new Predicate<DDiagramElement>() {
            @Override
            public boolean apply(DDiagramElement input) {
                return input.isVisible();
            };
        };

        assertEquals(consistent ? 24 : 0, Iterables.size(Iterables.filter(diagram.getDiagramElements(), isVisible)));
    }

    private void collapsePorts() {
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, "CollapseAttrRef");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();

        checkDiagramConsistency(true);
    }

    private void uncollapsePorts() {
        TestsUtil.synchronizationWithUIThread();

        deactivateFilter(diagram, "CollapseAttrRef");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();

        checkDiagramConsistency(false);
    }

    private void hideAll() {
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, "HideClass");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();

        checkDiagramElements(false);
    }

    private void revealAll() {
        TestsUtil.synchronizationWithUIThread();

        deactivateFilter(diagram, "HideClass");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();

        checkDiagramElements(true);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
