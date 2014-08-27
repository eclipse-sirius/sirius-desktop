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
package org.eclipse.sirius.tests.unit.diagram.style;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * 
 * @author mporhel
 */
public class WorkspaceImageTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2262/tc2262.ecore";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2262/tc2262.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2262/tc2262.odesign";

    private static final String REPRESENTATION_DESC_NAME = "tc2262";

    private DDiagram diagram;

    private DDiagramInternalQuery query;

    private DiagramEditor editor;

    private Dimension realImageSize = new Dimension(20, 70);

    private double imageRatio = (double) realImageSize.width / realImageSize.height;

    private int defaultWidth = 30;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);

    }

    /**
     * Check nodes created with "-1" as size computation expression.
     * 
     * @throws Exception
     */
    public void testWorkspaceImageRealSize() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        validateSize(EcorePackage.eINSTANCE.getEAttribute(), realImageSize);
    }

    /**
     * Check nodes created with empty size computation expression.
     * 
     * @throws Exception
     */
    public void testWorkspaceImageDefaultSize() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        validateSize(EcorePackage.eINSTANCE.getEOperation(), new Dimension(defaultWidth, new Double(defaultWidth / imageRatio).intValue()));
    }

    /**
     * Check nodes created with "<%-1%>" as size computation expression.
     * 
     * @throws Exception
     */
    public void testWorkspaceImageComputedRealSize() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        validateSize(EcorePackage.eINSTANCE.getEReference(), realImageSize);
    }

    /**
     * Create a diagram and replay previous tests.
     * 
     * @throws Exception
     */
    public void testWorkspaceImageOnDiagramCreation() throws Exception {
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        validateSize(EcorePackage.eINSTANCE.getEAttribute(), realImageSize);
        validateSize(EcorePackage.eINSTANCE.getEReference(), realImageSize);
        validateSize(EcorePackage.eINSTANCE.getEOperation(), new Dimension(defaultWidth, new Double(defaultWidth / imageRatio).intValue()));
    }

    /**
     * Makes sure that the node P1 has a workspace image. The workspace image
     * description has an empty image path. see VP-3313.
     */
    public void testEmptyWorkspaceImagePath() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        List<DDiagramElement> diagramElements = getDiagramElementsFromLabel(diagram, "P1");
        assertEquals("We should have one package named \"P1\"", 1, diagramElements.size());
        IGraphicalEditPart editPart = getEditPart(diagramElements.get(0));
        WorkspaceImageFigure figure = getWorkspaceImageFigure(editPart.getFigure());
        assertNotNull("The node 'P1' should have a WorkspaceImageFigure 'not found' event without workspace image path specified.", figure);
    }

    private void validateSize(final EClass type, Dimension expectedSize) {

        Predicate<DNode> expectedType = new Predicate<DNode>() {
            public boolean apply(DNode input) {
                return type.isInstance(input.getTarget());
            }
        };

        List<DNode> nodesFromType = Lists.newArrayList(Iterables.filter(query.getNodes(), expectedType));

        // one nodes per node edit part type.
        assertEquals(4, nodesFromType.size());

        for (DNode node : nodesFromType) {

            Integer expectedDWidth;
            Integer expectedDHeight;
            if (expectedSize == realImageSize) {
                expectedDWidth = -1;
                expectedDHeight = -1;
            } else {
                expectedDWidth = expectedSize.width / LayoutUtils.SCALE;
                expectedDHeight = expectedDWidth;
            }

            assertEquals(expectedDWidth, node.getWidth());
            assertEquals(expectedDHeight, node.getHeight());

            IGraphicalEditPart editPart = getEditPart(node);
            Node gmfNode = (Node) editPart.getNotationView();
            assertNotNull(gmfNode);
            Size layoutConstraint = (Size) gmfNode.getLayoutConstraint();

            if (layoutConstraint.getWidth() != -1) {
                assertEquals(expectedSize.width, layoutConstraint.getWidth());
            }

            if (layoutConstraint.getHeight() != -1) {
                assertEquals(expectedSize.height, layoutConstraint.getHeight());
            }

            WorkspaceImageFigure figure = getWorkspaceImageFigure(editPart.getFigure());
            assertNotNull(figure);

            assertEquals("Figure bounds: " + figure.getBounds() + ", GMF bounds: " + gmfNode.getLayoutConstraint(), imageRatio, figure.getImageAspectRatio());
            assertEquals(expectedSize, figure.getBounds().getSize());
        }
    }

    /**
     * Finds the SquareFigure children that contains the Color informations.
     * 
     * @param figure
     * @return
     */
    private WorkspaceImageFigure getWorkspaceImageFigure(IFigure figure) {
        if (!(figure instanceof WorkspaceImageFigure) && figure.getChildren() != null && !figure.getChildren().isEmpty()) {
            if (!(Iterables.isEmpty(Iterables.filter(figure.getChildren(), WorkspaceImageFigure.class)))) {
                return Iterables.get((Iterables.filter(figure.getChildren(), WorkspaceImageFigure.class)), 0);
            }
            for (Object object : figure.getChildren()) {
                if (object instanceof IFigure) {
                    IFigure childFigure = (IFigure) object;
                    WorkspaceImageFigure wkpImgFig = getWorkspaceImageFigure(childFigure);
                    if (wkpImgFig != null) {
                        return wkpImgFig;
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }
}
