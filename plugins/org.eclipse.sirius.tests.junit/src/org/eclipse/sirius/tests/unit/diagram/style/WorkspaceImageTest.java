/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.style;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 
 * @author mporhel
 */
public class WorkspaceImageTest extends SiriusDiagramTestCase {

    private static final String DATA_RELATIVE_PATH = "/data/unit/style/ticket2262/";

    private static final String SEMANTIC_FILE_NAME = "tc2262.ecore";

    private static final String SESSION_FILE_NAME = "tc2262.aird";

    private static final String MODELER_FILE_NAME = "tc2262.odesign";

    private static final String IMG_RELATIVE_PATH = "/images/";

    private static final String IMG1_FILE_NAME = "es.png";

    private static final String IMG2_FILE_NAME = "logo_o.png";

    private static final String IMG3_FILE_NAME = "UseCase.png";

    private static final String IMG4_FILE_NAME = "Actor.png";

    private static final String REPRESENTATION_DESC_NAME = "tc2262";

    private DDiagram diagram;

    private DDiagramInternalQuery query;

    private DiagramEditor editor;

    private Dimension realImageSize = new Dimension(20, 70);

    private double imageRatio = (double) realImageSize.width / realImageSize.height;

    private Dimension realContainerImageSize = new Dimension(160, 48);

    private double imageContainerRatio = (double) realContainerImageSize.width / realContainerImageSize.height;

    private int defaultWidth = 30;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Copy the files in the workspace (to allow a serialization in some tests)
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_RELATIVE_PATH + SEMANTIC_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_RELATIVE_PATH + MODELER_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_RELATIVE_PATH + SESSION_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, IMG_RELATIVE_PATH + IMG1_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMG1_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, IMG_RELATIVE_PATH + IMG2_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMG2_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, IMG_RELATIVE_PATH + IMG3_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMG3_FILE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, IMG_RELATIVE_PATH + IMG4_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMG4_FILE_NAME);
        TestsUtil.emptyEventsFromUIThread();

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_FILE_NAME);
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

        validateNodeSize(EcorePackage.eINSTANCE.getEAttribute(), realImageSize);

        // Test here the size of container/list as there is no possibility to
        // compute/define the size of containers in the VSM (See
        // ContainerStyleDescription): the initial GMF bounds will (-1,-1),
        // equivalent to -1 case for nodes. The sizeComputationExpression
        // attribute on WorkspaceImageDescription comes from the inheritance to
        // NodeStyleDescription and is not used in the context of a container
        // mapping.
        List<DDiagramElement> containers = getDiagramElementsFromLabel(diagram, "container_image");
        assertEquals(1, containers.size());
        List<DDiagramElement> lists = getDiagramElementsFromLabel(diagram, "list_image");
        assertEquals(1, lists.size());

        containers.addAll(lists);
        validateSize(realContainerImageSize, imageContainerRatio, lists);
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

        validateNodeSize(EcorePackage.eINSTANCE.getEOperation(), new Dimension(defaultWidth, new Double(defaultWidth / imageRatio).intValue()));
    }

    /**
     * Check nodes created with "-1" as size computation expression.
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

        validateNodeSize(EcorePackage.eINSTANCE.getEReference(), realImageSize);
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

        validateNodeSize(EcorePackage.eINSTANCE.getEAttribute(), realImageSize);
        validateNodeSize(EcorePackage.eINSTANCE.getEReference(), realImageSize);
        validateNodeSize(EcorePackage.eINSTANCE.getEOperation(), new Dimension(defaultWidth, new Double(defaultWidth / imageRatio).intValue()));

        List<DDiagramElement> containers = getDiagramElementsFromLabel(diagram, "container_image");
        assertEquals(1, containers.size());
        List<DDiagramElement> lists = getDiagramElementsFromLabel(diagram, "list_image");
        assertEquals(1, lists.size());

        containers.addAll(lists);
        validateSize(realContainerImageSize, imageContainerRatio, lists);
    }

    /**
     * Makes sure that the node P1 has a workspace image. The workspace image description has an empty image path. see
     * VP-3313.
     */
    public void testEmptyWorkspaceImagePath() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        List<DDiagramElement> diagramElements = getDiagramElementsFromLabel(diagram, "P1");
        assertEquals("We should have one package named \"P1\"", 1, diagramElements.size());
        IGraphicalEditPart editPart = getEditPart(diagramElements.get(0));
        WorkspaceImageFigure figure = getWorkspaceImageFigure(editPart.getFigure());
        assertNotNull("The node 'P1' should have a WorkspaceImageFigure 'not found' event without workspace image path specified.", figure);
    }

    /**
     * Makes sure that the container and list has a workspace image when the description references a non existing
     * image.
     */
    public void testNotFoundImage() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElements = getDiagramElementsFromLabel(diagram, "container_image_not_found");
        assertEquals("We should have one package named \"container_image_not_found\"", 1, diagramElements.size());
        IGraphicalEditPart editPart = getEditPart(diagramElements.get(0));
        WorkspaceImageFigure figure = getWorkspaceImageFigure(editPart.getFigure());
        assertNotNull("The container 'container_image_not_found' should have a WorkspaceImageFigure 'not found' even with.", figure);

        diagramElements = getDiagramElementsFromLabel(diagram, "list_image_not_found");
        assertEquals("We should have one package named \"list_image_not_found\"", 1, diagramElements.size());
        editPart = getEditPart(diagramElements.get(0));
        figure = getWorkspaceImageFigure(editPart.getFigure());
        assertNotNull("The container 'list_image_not_found' should have a WorkspaceImageFigure 'not found' even with.", figure);

    }

    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.<BR/>
     * In the diagram used as input, the size of the DNode is "wrong" (5 instead of 2) to force a refresh at opening and
     * reveals the problem.
     */
    public void testMoveWorkspaceImageWithIntegerSizeComputationExpression() {
        Optional<DRepresentation> optionalRepresentation = getRepresentations(REPRESENTATION_DESC_NAME).stream().filter(d -> "tc2262".equals(d.getName())).findFirst();
        assertTrue("A diagram with representation description \"" + REPRESENTATION_DESC_NAME + "\" and with name \"tc2262\" must exist.", optionalRepresentation.isPresent());
        testMoveWorkspaceImageWithIntegerSizeComputationExpression((DDiagram) optionalRepresentation.get());
    }

    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.<BR/>
     * The diagram used as input is the same as above but the node to test has been serialized with a wrong GMF size
     * (the real size of the image, ie the consequence of the fixed problem).
     */
    public void testMoveWorkspaceImageWithIntegerSizeComputationExpressionInCorruptedSerializedDiagram() {
        Optional<DRepresentation> optionalRepresentation = getRepresentations(REPRESENTATION_DESC_NAME).stream().filter(d -> "tc2262CorruptedSerialization".equals(d.getName())).findFirst();
        assertTrue("A diagram with representation description \"" + REPRESENTATION_DESC_NAME + "\" and with name \"tc2262CorruptedSerialization\" must exist.", optionalRepresentation.isPresent());
        testMoveWorkspaceImageWithIntegerSizeComputationExpression((DDiagram) optionalRepresentation.get());
    }

    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.
     */
    public void testMoveWorkspaceImageWithIntegerSizeComputationExpression(DDiagram diagram) {
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        List<DDiagramElement> enumElement = getDiagramElementsFromLabel(diagram, "enum");
        DDiagramElement dde = enumElement.get(0);

        IGraphicalEditPart editPart = getEditPart(dde);
        Node gmfNode = (Node) editPart.getNotationView();
        assertNotNull(gmfNode);
        Size size = (Size) gmfNode.getLayoutConstraint();
        Rectangle bounds = editPart.getFigure().getBounds();

        int expectedWidth = 20;
        // "* 2" because the ratio width/height of the image is 1/2
        int expectedHeight = expectedWidth * 2;

        assertEquals("Node \"enum\" has a wrong figure bounds width.", expectedWidth, bounds.width);
        assertEquals("Node \"enum\" has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node \"enum\" has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node \"enum\" has a wrong layoutConstraint height.", expectedHeight, size.getHeight());

        moveEditPart(dde, new Point(100, 100));

        size = (Size) gmfNode.getLayoutConstraint();
        bounds = editPart.getFigure().getBounds();

        assertEquals("Node \"enum\" has a wrong figure bounds width.", expectedWidth, bounds.width);
        assertEquals("Node \"enum\" has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node \"enum\" has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node \"enum\" has a wrong layoutConstraint height.", expectedHeight, size.getHeight());
    }

    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.<BR/>
     * In the diagram used as input, the size of the DNode is computed according to the number of semantic elements.
     */
    public void testMoveWorkspaceImageWithAqlSizeComputationExpression() {
        Optional<DRepresentation> optionalRepresentation = getRepresentations(REPRESENTATION_DESC_NAME).stream().filter(d -> "tc2262".equals(d.getName())).findFirst();
        assertTrue("A diagram with representation description \"" + REPRESENTATION_DESC_NAME + "\" and with name \"tc2262\" must exist.", optionalRepresentation.isPresent());
        testMoveWorkspaceImageWithAqlSizeComputationExpression((DDiagram) optionalRepresentation.get());
    }


    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.<BR/>
     * The diagram used as input is the same as above but the node to test has been serialized with a wrong GMF size
     * (the real size of the image, ie the consequence of the fixed problem).
     */
    public void testMoveWorkspaceImageWithAqlSizeComputationExpressionInCorruptedSerializedDiagram() {
        Optional<DRepresentation> optionalRepresentation = getRepresentations(REPRESENTATION_DESC_NAME).stream().filter(d -> "tc2262CorruptedSerialization".equals(d.getName())).findFirst();
        assertTrue("A diagram with representation description \"" + REPRESENTATION_DESC_NAME + "\" and with name \"tc2262CorruptedSerialization\" must exist.", optionalRepresentation.isPresent());
        testMoveWorkspaceImageWithAqlSizeComputationExpression((DDiagram) optionalRepresentation.get());
    }

    /**
     * Makes sure that moving a node doesn't change its size. See bug 576423.
     */
    public void testMoveWorkspaceImageWithAqlSizeComputationExpression(DDiagram diagram) {
        // changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        assertNotNull(diagram);
        query = new DDiagramInternalQuery(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(diagram);

        List<DDiagramElement> enumElement = getDiagramElementsFromLabel(diagram, "enumWithExpr");
        DDiagramElement dde = enumElement.get(0);

        IGraphicalEditPart editPart = getEditPart(dde);
        Node gmfNode = (Node) editPart.getNotationView();
        assertNotNull(gmfNode);
        Size size = (Size) gmfNode.getLayoutConstraint();
        Rectangle bounds = editPart.getFigure().getBounds();

        assertTrue("The semantic element should be an EEnum.", dde.getTarget() instanceof EEnum);
        EEnum currentSemanticElement = (EEnum) dde.getTarget();
        int nbParentChildren = currentSemanticElement.eContainer().eContents().size();
        int expectedWidth = nbParentChildren * LayoutUtils.SCALE;
        // "* 2" because the ratio width/height of the image is 1/2
        int expectedHeight = expectedWidth * 2;
        assertEquals("Node \"enum\" has a wrong figure bounds width.", expectedWidth, bounds.width);
        assertEquals("Node \"enum\" has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node \"enum\" has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node \"enum\" has a wrong layoutConstraint height.", expectedHeight, size.getHeight());

        // Modify the number of children outside of the session: Load the semantic resource in another resource set, add
        // a new sub package and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(currentSemanticElement.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(TransactionUtil.getEditingDomain(currentSemanticElement)));

            domain.getCommandStack().execute(new RecordingCommand(domain, "Add 2 new sub packages") {

                @Override
                protected void doExecute() {
                    EPackage newPackage2 = EcoreFactory.eINSTANCE.createEPackage();
                    newPackage2.setName("P2");
                    ePackageInAnotherResourceSet.getESubpackages().add(newPackage2);
                    EPackage newPackage3 = EcoreFactory.eINSTANCE.createEPackage();
                    newPackage3.setName("P3");
                    ePackageInAnotherResourceSet.getESubpackages().add(newPackage3);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        // Force a manual refresh to adapt size according to new child
        Command refreshCmd = new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), new NullProgressMonitor(), diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(refreshCmd);

        // Assert that the node has been resized according to new elements
        editPart = getEditPart(dde);
        gmfNode = (Node) editPart.getNotationView();
        size = (Size) gmfNode.getLayoutConstraint();
        bounds = editPart.getFigure().getBounds();
        assertTrue("The semantic element should be an EEnum.", dde.getTarget() instanceof EEnum);
        currentSemanticElement = (EEnum) dde.getTarget();
        nbParentChildren = currentSemanticElement.eContainer().eContents().size();
        expectedWidth = nbParentChildren * LayoutUtils.SCALE;
        expectedHeight = expectedWidth * 2;

        assertEquals("Node \"enum\" has a wrong figure bounds width.", expectedWidth, bounds.width);
        assertEquals("Node \"enum\" has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node \"enum\" has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node \"enum\" has a wrong layoutConstraint height.", expectedHeight, size.getHeight());

        moveEditPart(dde, new Point(100, 100));

        // Assert that the node is the same after a move

        size = (Size) gmfNode.getLayoutConstraint();
        bounds = editPart.getFigure().getBounds();

        assertEquals("Node \"enum\" has a wrong figure bounds width.", expectedWidth, bounds.width);
        assertEquals("Node \"enum\" has a wrong figure bounds height.", expectedHeight, bounds.height);
        assertEquals("Node \"enum\" has a wrong layoutConstraint width.", expectedWidth, size.getWidth());
        assertEquals("Node \"enum\" has a wrong layoutConstraint height.", expectedHeight, size.getHeight());
    }

    private void validateNodeSize(final EClass type, Dimension expectedSize) {

        Predicate<DNode> expectedType = new Predicate<DNode>() {
            @Override
            public boolean apply(DNode input) {
                return type.isInstance(input.getTarget());
            }
        };

        List<DNode> nodesFromType = Lists.newArrayList(Iterables.filter(query.getNodes(), expectedType));

        // one nodes per node edit part type.
        assertEquals(4, nodesFromType.size());

        validateSize(expectedSize, imageRatio, nodesFromType);
    }

    private void validateSize(Dimension expectedSize, double expectedRatio, List<? extends DDiagramElement> nodesFromType) {
        for (DDiagramElement dde : nodesFromType) {

            Integer expectedDWidth;
            Integer expectedDHeight;
            if (expectedSize == realImageSize) {
                expectedDWidth = -1;
                expectedDHeight = -1;
            } else {
                expectedDWidth = expectedSize.width / LayoutUtils.SCALE;
                expectedDHeight = expectedDWidth;
            }

            if (dde instanceof DNode) {
                DNode node = (DNode) dde;
                assertEquals(expectedDWidth, node.getWidth());
                assertEquals(expectedDHeight, node.getHeight());
            }

            IGraphicalEditPart editPart = getEditPart(dde);
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

            assertEquals("Figure bounds: " + figure.getBounds() + ", GMF bounds: " + gmfNode.getLayoutConstraint(), expectedRatio, figure.getImageAspectRatio());
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

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }
}
