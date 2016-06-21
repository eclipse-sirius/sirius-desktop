/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.compartment;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.PlatformUI;

/**
 * Tests defined to ensure that elements are created in compartments and regions
 * at expected locations.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class CompartmentsCreationTest extends AbstractCompartmentTest {

    private String oldFont;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        oldFont = changeDefaultFontName("Comic Sans MS");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * Ensures that containers displays correctly its children in a horizontal
     * stack.
     */
    public void testChildrenPresentationsInHorizontalStack() {
        childrenPresentationTest(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, ContainerLayout.HORIZONTAL_STACK);
    }

    /**
     * Ensures that containers displays correctly its children in a vertical
     * stack.
     */
    public void testChildrenPresentationsInVerticalStack() {
        childrenPresentationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, ContainerLayout.VERTICAL_STACK);
    }

    private void childrenPresentationTest(String representationName, String representationInstanceName, ContainerLayout initialContainerLayout) {
        openRepresentation(representationName, representationInstanceName);

        ContainerMapping rcMapping = getActualMapping(FIRST_REGION_CONTAINER_NAME);
        checkChildrenPresentation(FIRST_REGION_CONTAINER_NAME, rcMapping, initialContainerLayout);

        ContainerMapping listRegionMapping = getActualMapping(LEFT_CLASS_NAME);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);

        ContainerMapping freeFormRegionMapping = getActualMapping(LEFT_PKG_NAME);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);

        // Change list region presentation to free form
        changeChildrenPresentation(listRegionMapping, ContainerLayout.FREE_FORM);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.FREE_FORM);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);

        undo(localSession.getOpenedSession());
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);

        // Change free form region presentation to list
        changeChildrenPresentation(freeFormRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.LIST);

        undo(localSession.getOpenedSession());
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);

        // Change region container children presentation from VStack to HStack
        // or from HStack to VStack
        ContainerLayout newRCPresentation = initialContainerLayout == ContainerLayout.VERTICAL_STACK ? ContainerLayout.HORIZONTAL_STACK : ContainerLayout.VERTICAL_STACK;
        changeChildrenPresentation(rcMapping, newRCPresentation);
        checkChildrenPresentation(FIRST_REGION_CONTAINER_NAME, rcMapping, newRCPresentation);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);

        undo(localSession.getOpenedSession());
        checkChildrenPresentation(FIRST_REGION_CONTAINER_NAME, rcMapping, initialContainerLayout);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);
    }

    /**
     * Ensures that a NodeList element is correctly created in a horizontal
     * stack compartment and has the expected location.
     */
    public void testContainerListCreationInHorizontalStack() {
        doTestContainerListCreation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that a NodeList element is correctly created in a vertical stack
     * compartment and has the expected location.
     */
    public void testContainerListCreationInVerticalStack() {
        doTestContainerListCreation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    private void doTestContainerListCreation(String representationName, String representationInstanceName) {
        openRepresentation(representationName, representationInstanceName);
        createElement(CLASS_LIST_CREATION_TOOL_NAME, FIRST_REGION_CONTAINER_NAME);
        checkElement(NEW_CLASS_LIST_4_NAME, FIRST_REGION_CONTAINER_NAME, CLASS_LIST_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that horizontal stack Compartment and its children are correctly
     * created.
     */
    public void testCompartmentCreationInHorizontalStack() {
        compartmentCreationTest(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, new Point(400, 350));
    }

    /**
     * Ensures that vertical stack Compartment and its children are correctly
     * created.
     */
    public void testCompartmentCreationInVerticalStack() {
        compartmentCreationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, new Point(400, 300));
    }

    private void compartmentCreationTest(String representationName, String representationInstanceName, Point regionContainerLocation) {
        openRepresentation(representationName, representationInstanceName);
        createRegionContainerDiagram(PACKAGE_CREATION_TOOL_NAME, regionContainerLocation);
        checkRegionContainerInDiagram(NEW_REGION_CONTAINER_NAME);

        createElement(CLASS_LIST_CREATION_TOOL_NAME, NEW_REGION_CONTAINER_NAME);
        checkElement(NEW_CLASS_1_NAME, NEW_REGION_CONTAINER_NAME, CLASS_LIST_CREATION_TOOL_NAME);

        createElement(PACKAGE_CREATION_TOOL_NAME, NEW_REGION_CONTAINER_NAME);
        checkElement(NEW_PACKAGE_1_NAME, NEW_REGION_CONTAINER_NAME, PACKAGE_CREATION_TOOL_NAME);

        createElement(CLASS_NODE_CREATION_TOOL_NAME, NEW_PACKAGE_1_NAME);
        checkElement(NEW_CLASS_1_NAME, NEW_PACKAGE_1_NAME, CLASS_NODE_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a Node element is correctly created in container layouted
     * with "FreeForm" style in a horizontal stack compartment and has the
     * expected location.
     */
    public void testNodeCreationInFreeFormContainerInHorizontalStack() {
        doTestNodeCreationInFreeFormContainer(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that a Node element is correctly created in container layouted
     * with "FreeForm" style in a vertical stack compartment and has the
     * expected location.
     */
    public void testNodeCreationInFreeFormContainerInVerticalStack() {
        doTestNodeCreationInFreeFormContainer(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    private void doTestNodeCreationInFreeFormContainer(String representationName, String representationInstanceName) {
        openRepresentation(representationName, representationInstanceName);
        createElement(CLASS_NODE_CREATION_TOOL_NAME, LEFT_PKG_NAME);
        checkElement(NEW_CLASS_1_NAME, LEFT_PKG_NAME, CLASS_NODE_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a list item element is correctly created in container
     * layouted with "list" style in a horizontal stack compartment and has the
     * expected location.
     */
    public void testItemCreationInListContainerInHorizontalStack() {
        doTestItemCreationInListContainer(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that a list item element is correctly created in container
     * layouted with "list" style created in a vertical stack compartment and
     * has the expected location.
     */
    public void testItemCreationInListContainerInVerticalStack() {
        doTestItemCreationInListContainer(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    private void doTestItemCreationInListContainer(String representationName, String representationInstanceName) {
        openRepresentation(representationName, representationInstanceName);
        createElement(ATTRIBUTE_CREATION_TOOL_NAME, RIGHT_CLASS_NAME);
        checkElement(NEW_ATTRIBUTE_NAME, RIGHT_CLASS_NAME, ATTRIBUTE_CREATION_TOOL_NAME);
    }

    /**
     * Create a new node using the defined Node Creation tool, at the given
     * position.
     * 
     * @param CREATION_TOOL_NAME
     *            Tool name to select
     * @param targetEditPartName
     *            The target edit part name
     */
    private void createElement(String CREATION_TOOL_NAME, String targetEditPartName) {
        // Select the tool
        editor.activateTool(CREATION_TOOL_NAME);
        // Click in the target edit part to create element on it
        editor.click(targetEditPartName);
    }

    /**
     * Check if the new element has been correctly created.
     * 
     * @param createdElementName
     *            the new created element
     * @param containerName
     *            the container element
     * @param creationToolName
     *            the creation tool name
     */
    private void checkElement(String createdElementName, String containerName, String creationToolName) {
        SWTBotGefEditPart containerEditPart = editor.getEditPart(containerName, AbstractDiagramElementContainerEditPart.class);
        EObject containerElement = ((IGraphicalEditPart) containerEditPart.part()).resolveSemanticElement();

        SWTBotGefEditPart createdEditPart = editor.getSWTBotGefViewer().selectedEditParts().iterator().next();
        if (creationToolName.equals(ATTRIBUTE_CREATION_TOOL_NAME)) {
            assertTrue("The '" + ATTRIBUTE_CREATION_TOOL_NAME + "' tool should create a DNodeListElement.", createdEditPart.part() instanceof DNodeListElementEditPart);
        } else if (creationToolName.equals(CLASS_NODE_CREATION_TOOL_NAME)) {
            assertTrue("The '" + CLASS_NODE_CREATION_TOOL_NAME + "' tool should create a DNode.", createdEditPart.part() instanceof AbstractDiagramNodeEditPart);
        } else {
            assertTrue("The '" + CLASS_LIST_CREATION_TOOL_NAME + "' tool should create a DNodeList/DNodeContainer.", createdEditPart.part() instanceof AbstractDiagramElementContainerEditPart);
        }
        EObject createdElement = ((IDiagramElementEditPart) createdEditPart.part()).resolveSemanticElement();

        boolean isContained = ((DDiagramElementContainer) containerElement).getElements().contains(createdElement);
        assertTrue("The '" + createdElementName + "' element should be correctly created in '" + containerName + "' element", isContained);
    }

    /**
     * Create a new node using the given tool directly in the diagram
     * representation.
     * 
     * @param CREATION_TOOL_NAME
     *            Tool name to select
     */
    private void createRegionContainerDiagram(String creationToolName, Point location) {
        // Select the tool
        editor.activateTool(creationToolName);
        editor.click(location);
    }

    /**
     * Check if the new element has been created in the diagram with the correct
     * style.
     * 
     * @param createdCompartmentName
     *            the new created element
     */
    private void checkRegionContainerInDiagram(String createdCompartmentName) {
        SWTBotGefEditPart createdCompartmentEditPart = editor.getEditPart(createdCompartmentName, AbstractDiagramElementContainerEditPart.class);
        EObject createdCompartmentElement = ((IGraphicalEditPart) createdCompartmentEditPart.part()).resolveSemanticElement();
        boolean isContained = editor.getDRepresentation().getOwnedRepresentationElements().contains(createdCompartmentElement);
        assertTrue("The '" + createdCompartmentName + "' element should be correctly created in the diagram", isContained);
        assertTrue("The created compartment '" + createdCompartmentName + "'should be a DNodeContainer type", createdCompartmentElement instanceof DNodeContainer);

        if (editor.getDRepresentation().getName().equals(HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            assertEquals("The created compartment '" + createdCompartmentName + "'should be layouted with 'Horizontal Stack' style", ContainerLayout.HORIZONTAL_STACK,
                    ((DNodeContainer) createdCompartmentElement).getChildrenPresentation());
        } else if (editor.getDRepresentation().getName().equals(VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            assertEquals("The created compartment '" + createdCompartmentName + "'should be layouted with 'Vertical Stack' style", ContainerLayout.VERTICAL_STACK,
                    ((DNodeContainer) createdCompartmentElement).getChildrenPresentation());
        }
    }

    /**
     * Edit and test container children presentation mappings.
     * 
     * @param editPartName
     *            the edit part name
     * @param containerLayout
     *            the expected container layout
     */
    private void checkChildrenPresentation(String editPartName, ContainerMapping actualMapping, ContainerLayout expectedContainerLayout) {
        assertEquals("Wrong children presentation for '" + editPartName + "' mapping", expectedContainerLayout, actualMapping.getChildrenPresentation());

        DDiagramElementContainer ddec = getDiagramElementContainer(editPartName);
        if (ContainerLayout.LIST == expectedContainerLayout) {
            assertTrue("Wrong children presentation for '" + editPartName + "' list", ddec instanceof DNodeList);
        } else {
            DNodeContainer diagramElement = (DNodeContainer) ddec;
            assertEquals("Wrong children presentation for '" + editPartName + "' container", expectedContainerLayout, diagramElement.getChildrenPresentation());
        }
    }

    /**
     * Get the mapping of the given edit part name.
     * 
     * @param editPartName
     *            the edit part name
     * @return the actual mapping
     */
    private ContainerMapping getActualMapping(String editPartName) {
        DDiagramElementContainer semanticElement = (DDiagramElementContainer) ((IGraphicalEditPart) editor.getEditPart(editPartName).part()).resolveSemanticElement();
        ContainerMapping actualMapping = semanticElement.getActualMapping();
        return actualMapping;
    }

    /**
     * Change container children presentation mapping.
     */
    private void changeChildrenPresentation(final ContainerMapping mapping, final ContainerLayout containerLayout) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                mapping.setChildrenPresentation(containerLayout);
            }
        });
    }

    /**
     * Test that the refresh of a diagram caused by an external change (and that
     * creates a new regions container) does not impact existing regions
     * container (no layout of them).
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the
     *             editor.
     */
    public void testCreationOfNewHorizontalRegionContainerOutsideEditor() throws Exception {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check that the existing container is as expected (a delta of 1 is
        // tolerate for height because of font problem in such OS)
        Rectangle currentDraw2DBounds = checkBounds(SECOND_REGION_CONTAINER_NAME, new Rectangle(940, 125, -1, -1), new Rectangle(940, 125, 233, 258), false, 0, 1);

        // Create a new ePackage (that causes creation of new region container
        // region at refresh) outside of the current session (as from an
        // external editor).
        modifySemanticModelOutsideDiagram();

        // Check that the existing container has not changed
        checkBounds(SECOND_REGION_CONTAINER_NAME, new Rectangle(940, 125, -1, -1), currentDraw2DBounds);
    }

    /**
     * Test that the refresh of a diagram caused by an external change (and that
     * creates a new regions container) does not impact existing regions
     * container (no layout of them).
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the
     *             editor.
     */
    public void testCreationOfNewVerticalRegionContainerOutsideEditor() throws Exception {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check that the existing region is as expected
        Rectangle currentDraw2DBounds = checkBounds("aaa", new Rectangle(0, 80, 154, 40), new Rectangle(0, 80, 154, 40));

        // Create a new ePackage (that causes creation of new region container
        // region at refresh) outside of the current session (as from an
        // external editor).
        modifySemanticModelOutsideDiagram();

        // Check that the existing container has not changed (or at least one of
        // its region in current case)
        checkBounds("aaa", new Rectangle(0, 80, 154, 40), currentDraw2DBounds);
    }

    private void checkSize(String label, Dimension expectedGmfSize, Dimension expectedFigureSize) {
        checkBounds(label, new Rectangle(0, 0, expectedGmfSize.width(), expectedGmfSize.height()), new Rectangle(0, 0, expectedFigureSize.width(), expectedFigureSize.height()), true);
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part with given
     * <code>label</code>.
     * 
     * @param label
     *            The label of the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals
     *            to -1, we ignore it.
     * 
     * @return The current Draw2d bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, false);
    }

    /**
     * Check the GMF and Draw2d bounds (or size) of the edit part with given
     * <code>label</code>.
     * 
     * @param label
     *            The label of the edit part to check
     * @param expectedGmfBounds
     *            The expected GMF bounds
     * @param expectedFigureBounds
     *            The expected draw2d bounds, if the width or height is equals
     *            to -1, we ignore it.
     * @param onlyCheckSize
     *            true if only the size must be check (and not the location),
     *            false otherwise.
     * 
     * @return The current Draw2d bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, boolean onlyCheckSize) {
        return checkBounds(label, expectedGmfBounds, expectedFigureBounds, onlyCheckSize, 0, 0);
    }

    /**
     * Check that the bounds (GMF and Draw2D) are as expected.
     *
     * @param label
     *            Label of the container to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in
     *            this bounds is equal to -1, we don't check it. This is useful
     *            in case of size that depends on Font (with different result
     *            according to OS).
     * @param onlyCheckSize
     *            true if only the size must be check (and not the location),
     *            false otherwise. * @param widthDelta The width delta to
     *            consider the width as equal (because of font size that can be
     *            slightly different on each OS).
     * @param heightDelta
     *            The height delta to consider the height as equal (because of
     *            font size that can be slightly different on each OS).
     * @return the current DrawD2 bounds
     */
    private Rectangle checkBounds(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, boolean onlyCheckSize, int widthDelta, int heightDelta) {
        SWTBotGefEditPart swtBotEditPart = editor.getEditPart(label, AbstractDiagramElementContainerEditPart.class);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) swtBotEditPart.part();

        IFigure mainFigure = editPart.getMainFigure();

        if (onlyCheckSize) {
            assertEquals("Wrong GMF size for " + label, expectedGmfBounds.getSize(), getBounds((Node) editPart.getNotationView()).getSize());
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                if (widthDelta == 0 && heightDelta == 0) {
                    assertEquals("Wrong Draw2D size for " + label, expectedFigureBounds.getSize(), mainFigure.getBounds().getSize());
                } else {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            } else {
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            }
        } else {
            assertEquals("Wrong GMF bounds for " + label, expectedGmfBounds, getBounds((Node) editPart.getNotationView()));
            if (expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
                if (widthDelta == 0 && heightDelta == 0) {
                    assertEquals("Wrong Draw2D bounds for " + label, expectedFigureBounds, mainFigure.getBounds());
                } else {
                    assertEquals("Wrong Draw2D location for " + label, expectedFigureBounds.getLocation(), mainFigure.getBounds().getLocation());
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            } else {
                assertEquals("Wrong Draw2D x for " + label, expectedFigureBounds.x(), mainFigure.getBounds().x());
                assertEquals("Wrong Draw2D y for " + label, expectedFigureBounds.y(), mainFigure.getBounds().y());
                if (expectedFigureBounds.width() != -1) {
                    assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), mainFigure.getBounds().width(), widthDelta);
                }
                if (expectedFigureBounds.height() != -1) {
                    assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), mainFigure.getBounds().height(), heightDelta);
                }
            }
        }
        return (Rectangle) mainFigure.getBounds();
    }

    private Rectangle getBounds(Node notationView) {
        Rectangle bounds = new Rectangle();
        LayoutConstraint layoutConstraint = notationView.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            Location location = (Location) layoutConstraint;
            bounds.setLocation(location.getX(), location.getY());
        }
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            bounds.setSize(size.getWidth(), size.getHeight());
        }
        return bounds;
    }

    /**
     * Add a new package p3 in the root package. The modification is not made in
     * the same resourceSet, as if this modification is made in another editor
     * not in Sirius.
     */
    private void modifySemanticModelOutsideDiagram() throws Exception {
        // Load the semantic resource in another resource set, delete the
        // elements and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(localSession.getOpenedSession().getSemanticResources().iterator().next().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(localSession.getOpenedSession().getTransactionalEditingDomain()));

            domain.getCommandStack().execute(new RecordingCommand(domain, "Add new package") {

                @Override
                protected void doExecute() {
                    // Remove the package subRoot
                    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
                    ePackage.setName(NEW_REGION_CONTAINER_NAME);
                    ePackageInAnotherResourceSet.getESubpackages().add(ePackage);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        // Wait job ResourceSyncClientNotifier to ensure the session has been
        // reloaded.
        if (Display.getCurrent() != null) {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InterruptedException {
                    Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, monitor);
                }
            });
        } else {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        }
    }
}
