/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.tests.swtbot.compartment;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
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
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.handles.CompartmentCollapseHandle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
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
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.PlatformUI;

/**
 * Tests to verify compartments behavior: ensure that elements are created in
 * compartments and regions at expected locations, check collapse behavior,
 * check resize behavior ...
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class CompartmentsTest extends AbstractCompartmentTest {

    /** Location where the end-user click to create a container. */
    private static final Point CONTAINER_CREATION_LOCATION = new Point(400, 300);

    /**
     * Location used with the first one, {@link #CONTAINER_CREATION_LOCATION},
     * to draw a rectangle for the creation of the container.
     */
    private static final Point CONTAINER_SECOND_CREATION_LOCATION = new Point(600, 500);

    /** Location of the first region. */
    private static final Point ORIGIN_LOCATION = new Point(0, 0);

    /** Auto sized dimension */
    private static final Dimension DIM_AUTO_SIZED = new Dimension(-1, -1);

    // CONTAINER WITH {-1, -1} as computation expressions

    /** Bounds of a container with computation expression equal to -1. */
    private static final Rectangle CONTAINER_BOUNDS_AUTO_SIZED = new Rectangle(CONTAINER_CREATION_LOCATION, DIM_AUTO_SIZED);

    /**
     * Bounds of a HStack container with computation expression equal to -1, but
     * created directly with one region: the size is increased to allow the
     * auto-sized region.
     */
    private static final Rectangle CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_VSTACK = new Rectangle(CONTAINER_CREATION_LOCATION, new Dimension(74, 78));

    /**
     * Bounds of a container with computation expression equal to -1, but
     * created directly with two regions. Same size as one region but plus the
     * NEW_DEFAULT_CONTAINER_DIMENSION in height.
     */
    private static final Rectangle CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_VSTACK = CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_VSTACK.getCopy()
            .setHeight(CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_VSTACK.height + LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height);

    /**
     * Bounds of a VStack container with computation expression equal to -1, but
     * created directly with one region: the size is increased to allow the
     * auto-sized region.
     */
    private static final Rectangle CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_HSTACK = new Rectangle(CONTAINER_CREATION_LOCATION, new Dimension(72, 76));

    /**
     * Bounds of a container with computation expression equal to -1 and without
     * label.
     */
    private static final Rectangle CONTAINER_BOUNDS_DEFAULT_SIZE = new Rectangle(CONTAINER_CREATION_LOCATION, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION);

    /**
     * GMF bounds of the first region in a VStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED = new Rectangle(ORIGIN_LOCATION, DIM_AUTO_SIZED);

    /**
     * GMF bounds of the second region in a VStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_SECOND_AUTO_SIZED_VSTACK = REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED.getTranslated(0, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height);

    /**
     * GMF bounds of the second region in a VStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_SECOND_AUTO_SIZED_HSTACK = REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED.getTranslated(LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.width, 0);

    /**
     * Draw2D bounds of the first region in a VStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_FIRST_DRAW2D = new Rectangle(ORIGIN_LOCATION, new Dimension(62, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height));

    /**
     * Draw2D bounds of the second region in a VStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_SECOND_DRAW2D_VSTACK = REGION_BOUNDS_FIRST_DRAW2D.getTranslated(0, LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION.height);

    /**
     * Draw2D bounds of the second region in a HStack container with computation
     * expression equal to -1.
     */
    private static final Rectangle REGION_BOUNDS_SECOND_DRAW2D_HSTACK = REGION_BOUNDS_FIRST_DRAW2D.getTranslated(REGION_BOUNDS_FIRST_DRAW2D.width, 0);

    /**
     * Bounds of a container with computation expression equal to -1, but
     * created directly with two regions. Same size as one region but plus the
     * size of the second region in width.
     */
    private static final Rectangle CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_HSTACK = CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_HSTACK.getCopy()
            .setWidth(CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_HSTACK.width + REGION_BOUNDS_SECOND_DRAW2D_HSTACK.width);

    // CONTAINER DRAWN AT CREATION (with a size of 200x200}

    /**
     * Dimension corresponding to the rectangle drawn during creation. The
     * dimension is expanded by {1x1} because of a bug that be fixed later.
     */
    private static final Dimension DIM_DRAWN_SIZE = CONTAINER_SECOND_CREATION_LOCATION.getDifference(CONTAINER_CREATION_LOCATION).expand(1, 1);

    /**
     * Bounds of a container initialized by the end-user at the creation with a
     * rectangle of {200x200}.
     */
    private static final Rectangle CONTAINER_BOUNDS_DRAWN = new Rectangle(CONTAINER_CREATION_LOCATION, DIM_DRAWN_SIZE);

    /** Bounds of the first region in a drawn VStack container. */
    private static final Rectangle REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER = new Rectangle(ORIGIN_LOCATION, new Dimension(189, 163));

    /** Bounds of the first region in a drawn HStack container. */
    private static final Rectangle REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER = new Rectangle(ORIGIN_LOCATION, new Dimension(191, 165));

    /** Bounds of the first region in a drawn HStack container. */
    private static final Rectangle REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER = REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER.getCopy().setWidth(REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER.width / 2);

    /**
     * Bounds of the second region in a drawn VStack container. The same as the
     * first but shift of the width of the first and plus one pixel for the
     * separator.
     */
    private static final Rectangle REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER = REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER.getTranslated(REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER.width, 0)
            .expand(new Insets(0, 0, 0, 1));

    // CONTAINER WITH COMPUTATION EXPRESSIONS EQUAL TO 8

    /** Dimension corresponding to computation expressions equal to 8. */
    private static final Dimension DIM_80_DEFINED_SIZE = new Dimension(8 * LayoutUtils.SCALE, 8 * LayoutUtils.SCALE);

    /**
     * Dimension corresponding to computation expressions equal to 8 and 2
     * regions. The container is increased in height because the 2 regions are
     * higher than the free container space.
     */
    private static final Dimension DIM_80_DEFINED_SIZE_WITH_2_REGIONS = new Dimension(8 * LayoutUtils.SCALE, 93);

    /** Bounds of a container with computation expression equal to 8. */
    private static final Rectangle CONTAINER_BOUNDS_80_FIXED_SIZE = new Rectangle(CONTAINER_CREATION_LOCATION, DIM_80_DEFINED_SIZE);

    /**
     * Bounds of a container with computation expressions equal to 8 and 2
     * regions.
     */
    private static final Rectangle CONTAINER_BOUNDS_80_FIXED_SIZE_WITH_2_REGIONS = new Rectangle(CONTAINER_CREATION_LOCATION, DIM_80_DEFINED_SIZE_WITH_2_REGIONS);

    /**
     * Bounds of the first region in a VStack container computation expressions
     * equal to 8.
     */
    private static final Rectangle REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE = new Rectangle(ORIGIN_LOCATION, new Dimension(68, 42));

    /**
     * Bounds of the first region in a VStack container computation expressions
     * equal to 8.
     */
    private static final Rectangle REGION_BOUNDS_IN_HSTACK_CONTAINER_80_FIXED_SIZE = new Rectangle(ORIGIN_LOCATION, new Dimension(70, 44));

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
        editor.maximize();

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
        editor.maximize();
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
        compartmentCreationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, CONTAINER_CREATION_LOCATION);
    }

    private void compartmentCreationTest(String representationName, String representationInstanceName, Point regionContainerLocation) {
        openRepresentation(representationName, representationInstanceName);
        editor.maximize();

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
        editor.maximize();
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
        editor.maximize();
        createElement(ATTRIBUTE_CREATION_TOOL_NAME, RIGHT_CLASS_NAME);
        checkElement(NEW_ATTRIBUTE_NAME, RIGHT_CLASS_NAME, ATTRIBUTE_CREATION_TOOL_NAME);
    }

    /**
     * Create a new node using the defined Node Creation tool, at the given
     * position.
     * 
     * @param creationToolName
     *            Name of the tool to use to create the new element
     * @param targetEditPartName
     *            The name of the target edit part (container of the new
     *            element)
     */
    private void createElement(String creationToolName, String targetEditPartName) {
        // Select the tool
        editor.activateTool(creationToolName);
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

        if (new DRepresentationQuery(editor.getDRepresentation()).getRepresentationDescriptor().getName().equals(HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            assertEquals("The created compartment '" + createdCompartmentName + "'should be layouted with 'Horizontal Stack' style", ContainerLayout.HORIZONTAL_STACK,
                    ((DNodeContainer) createdCompartmentElement).getChildrenPresentation());
        } else if (new DRepresentationQuery(editor.getDRepresentation()).getRepresentationDescriptor().getName().equals(VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
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
        editor.maximize();

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
        editor.maximize();

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

    /**
     * Test creation of regions container with vertical stack and with no
     * region. Check auto-size gmf size for container (because of computation
     * expressions equal to -1).
     */
    public void testCreationOfNewVerticalRegionContainerWithoutRegion() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, new Rectangle(CONTAINER_CREATION_LOCATION, new Dimension(44, 49)), 0, 1);
    }

    /**
     * Test creation of regions container with vertical stack and which contains
     * one region. Check auto-size gmf size for container and region (because of
     * computation expressions equal to -1).
     */
    public void testCreationOfNewVerticalRegionContainerWithOneRegion() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_VSTACK, 0, 1);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D);
    }

    /**
     * Test creation of regions container with vertical stack which contains two
     * regions. Check auto-size gmf size for container and regions (because of
     * computation expressions equal to -1).
     */
    public void testCreationOfNewVerticalRegionContainerWithTwoRegions() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_TWO_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_VSTACK, 0, 1);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D);
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_AUTO_SIZED_VSTACK, REGION_BOUNDS_SECOND_DRAW2D_VSTACK);
    }

    /**
     * Test creation of regions container with vertical stack and defined size
     * for the container. Check specific size (gmf and Draw2D) for container
     * because of specific computation expressions.
     */
    public void testCreationOfNewVerticalRegionContainerWithDefinedSize() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_80_FIXED_SIZE, CONTAINER_BOUNDS_80_FIXED_SIZE);
    }

    /**
     * Test creation of regions container with vertical stack. The container has
     * a defined size and contains one region. Check specific size for region,
     * auto-size gmf size and specific draw2D size for container.
     */
    public void testCreationOfNewVerticalRegionContainerWithDefinedSizeAndOneRegion() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_ONE_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_80_FIXED_SIZE);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE, REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE, 0, 1);
    }

    /**
     * Test creation of regions container with vertical stack. The container has
     * a defined size and contains two regions. Check specific size for regions,
     * auto-size gmf size for container. Draw2D height size for container is
     * changed because the two regions need a higher container (the size
     * increase in direction of the stack).
     */
    public void testCreationOfNewVerticalRegionContainerWithDefinedSizeAndTwoRegions() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_TWO_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        // The GMF bounds are not consistent with draw2d bounds as the container
        // is too small for two regions. The draw2d is increased. Add 3 as
        // height delta (1 pixel per text line).
        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_80_FIXED_SIZE_WITH_2_REGIONS, 0, 3);
        Rectangle firstRegionGMFBounds = REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE.getCopy().setHeight(REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE.height / 2);
        // TODO: Inconsistency between GMF size and Draw2D size. The GMF height
        // is computed from the initial container height (half of space for
        // regions == 21). But the minimal draw2d needed space is more. This can
        // be considered as a bug, but it can occurs only is the specifier
        // defines a container too small for containing its regions.
        Rectangle firstRegionD2DBounds = REGION_BOUNDS_IN_VSTACK_CONTAINER_80_FIXED_SIZE.getCopy().setHeight(27);
        Rectangle realFirstRegionD2DBounds = checkBounds(LEFT_CLASS_C0_NAME, firstRegionGMFBounds, firstRegionD2DBounds, 0, 1);
        // The second is the same but shift after the first and with one more
        // pixel for the separator (the location is not checked as it depends on
        // the first region size that depends on Font).
        checkBounds(LEFT_CLASS_C1_NAME, firstRegionGMFBounds.getTranslated(0, firstRegionGMFBounds.height),
                realFirstRegionD2DBounds.getTranslated(0, realFirstRegionD2DBounds.height).expand(new Insets(0, 0, 1, 0)), true, 0, 1);
    }

    /**
     * Test creation of regions container with vertical stack and end-user
     * defined size. Check specific size (gmf and Draw2D) of the container
     * (match with rectangle draw).
     */
    public void testCreationOfNewVerticalRegionContainerWithoutRegionAndRectangleDraw() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_DRAWN, CONTAINER_BOUNDS_DRAWN);
    }

    /**
     * Test creation of regions container with vertical stack which contains one
     * region and end-user defined size. Check specific size for region,
     * auto-size gmf size and specific Draw2D size for container.
     */
    public void testCreationOfNewVerticalRegionContainerWithOneRegionAndRectangleDraw() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER, REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER, 0, 1);
    }

    /**
     * Test creation of regions container with vertical stack which contains two
     * regions and end-user defined size. Check specific size for regions,
     * auto-size gmf size for container.
     */
    public void testCreationOfNewVerticalRegionContainerWithTwoRegionsAndRectangleDraw() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_TWO_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        Rectangle firstRegionBounds = REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER.getCopy().setHeight(REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER.height / 2);
        checkBounds(LEFT_CLASS_C0_NAME, firstRegionBounds, firstRegionBounds);
        // The second is the same but shift after the first (plus one pixel for
        // the separator).
        Rectangle secondRegionBounds = firstRegionBounds.getTranslated(0, firstRegionBounds.height).setHeight(firstRegionBounds.height + 1);
        checkBounds(LEFT_CLASS_C1_NAME, secondRegionBounds, secondRegionBounds, 0, 1);
    }

    /**
     * Test use of regions collapsed in container with vertical stack which
     * contains two regions. Check auto-size gmf size for container and regions
     * when collapsing. Height container must change because of collapsing.
     */
    public void testCollapseOfNewVerticalRegionContainerWithTwoRegions() {
        testCreationOfNewVerticalRegionContainerWithTwoRegions();

        SWTBotGefEditPart editPartToResize = editor.getEditPart(NEW_REGION_CONTAINER_NAME, AbstractDiagramElementContainerEditPart.class);
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);

        // selection of the second region
        editor.click(new Point(420, 400));
        SWTBotUtils.waitAllUiEvents(); // needed to have the collapse widget
                                       // displayed

        // collapse of the second region
        editor.click(new Point(460, 380));

        bot.waitUntil(editPartResizedCondition);

        // check collapse
        int heightCollapseSize = 12;
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED,
                CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_VSTACK.getCopy().setHeight(CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_VSTACK.height - heightCollapseSize), 0, 3);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D, 0, 1);
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_AUTO_SIZED_VSTACK, REGION_BOUNDS_SECOND_DRAW2D_VSTACK.getCopy().setHeight(REGION_BOUNDS_SECOND_DRAW2D_VSTACK.height - heightCollapseSize),
                0, 1);
    }

    /**
     * Test creation of regions container with vertical stack and hide Label.
     * Check auto-size gmf size for container (because of computation
     * expressions equal to -1).
     */
    public void testCreationOfNewVerticalRegionContainerWithoutRegionAndHideLabel() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_HIDE_LABEL_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(CONTAINER_CREATION_LOCATION, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DEFAULT_SIZE);
    }

    /**
     * Test resizing of new regions container with vertical stack which contains
     * one region. Check specific size for region, auto-size gmf size and
     * specific Draw2D size for container.
     */
    public void testResizeOfNewVerticalRegionContainerWithOneRegionAndRectangleDraw() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        Rectangle realContainerBounds = checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        Rectangle realRegionBounds = checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER, REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER, 0, 1);
        Dimension regionDelta = realRegionBounds.getSize().getShrinked(REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER.getSize());
        // Resize container
        SWTBotGefEditPart editPartToResize = editor.getEditPart(NEW_REGION_CONTAINER_NAME, AbstractDiagramElementContainerEditPart.class);
        editPartToResize.select();
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);
        editor.drag(realContainerBounds.getBottom(), realContainerBounds.getBottom().getTranslated(0, -60));
        bot.waitUntil(editPartResizedCondition);

        // Height bounds must decrease of 60 px because of resizing of 60 px.
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, realContainerBounds.getResized(0, -60));
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_VSTACK_CONTAINER.getResized(0, -60).getResized(regionDelta), realRegionBounds.getResized(0, -60), 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stack. Check auto-size
     * gmf size for container (because of computation expressions equal to -1).
     */
    public void testCreationOfNewHorizontalRegionContainerWithoutRegion() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        // The D2D dimension is computed according to label size.
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, new Rectangle(CONTAINER_CREATION_LOCATION, new Dimension(42, 47)), 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stack which contains
     * one region. Check auto-size gmf size for container and region (because of
     * computation expressions equal to -1).
     */
    public void testCreationOfNewHorizontalRegionContainerWithOneRegion() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        // The D2D dimension is computed according to label size (of container
        // and of region), the container is increased according to the first
        // region size.
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_AUTO_SIZED_WITH_ONE_REGION_HSTACK, 0, 1);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D);
    }

    /**
     * Test creation of regions container with horizontal stack which contains
     * two regions. Check auto-size gmf size for container and regions (because
     * of computation expressions equal to -1).
     */
    public void testCreationOfNewHorizontalRegionContainerWithTwoRegions() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_TWO_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_HSTACK, 0, 1);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D);
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_AUTO_SIZED_HSTACK, REGION_BOUNDS_SECOND_DRAW2D_HSTACK);
    }

    /**
     * Test creation of regions container with horizontal stack and defined size
     * for the container. Check specific size (gmf and Draw2D) for container
     * because of specific computation expressions.
     */
    public void testCreationOfNewHorizontalRegionContainerWithDefinedSize() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_80_FIXED_SIZE, CONTAINER_BOUNDS_80_FIXED_SIZE);
    }

    /**
     * Test creation of regions container with horizontal stack. The container
     * has a defined size and contains one region. Check specific size for
     * region, auto-size gmf size and specific draw2D size for container.
     */
    public void testCreationOfNewHorizontalRegionContainerWithDefinedSizeAndOneRegion() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_ONE_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_80_FIXED_SIZE);
        // All the free space.
        Rectangle firstRegionBounds = REGION_BOUNDS_IN_HSTACK_CONTAINER_80_FIXED_SIZE;
        checkBounds(LEFT_CLASS_C0_NAME, firstRegionBounds, firstRegionBounds, 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stackThe container has
     * a defined size and contains two regions. Check specific size for regions,
     * auto-size gmf size for container. Draw2D height size for container must
     * change because of addition of two regions size.
     */
    public void testCreationOfNewHorizontalRegionContainerWithDefinedSizeAndTwoRegions() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagram(PACKAGE_TWO_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_80_FIXED_SIZE);
        Rectangle firstRegionBounds = REGION_BOUNDS_IN_HSTACK_CONTAINER_80_FIXED_SIZE.getCopy().setWidth(REGION_BOUNDS_IN_HSTACK_CONTAINER_80_FIXED_SIZE.width / 2);
        checkBounds(LEFT_CLASS_C0_NAME, firstRegionBounds, firstRegionBounds, 0, 1);
        // The second is the same but shift after the first.
        checkBounds(LEFT_CLASS_C1_NAME, firstRegionBounds.getTranslated(firstRegionBounds.width, 0), firstRegionBounds.getTranslated(firstRegionBounds.width, 0), 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stack. Check defined
     * size of the container (match with rectangle draw).
     */
    public void testCreationOfNewHorizontalRegionContainerWithoutRegionAndRectangleDraw() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_DRAWN, CONTAINER_BOUNDS_DRAWN);
    }

    /**
     * Test creation of regions container with horizontal stack which contains
     * one region. Check specific size for region and auto-size gmf size and
     * specific Draw2D size for container match with rectangle draw).
     */
    public void testCreationOfNewHorizontalRegionContainerWithOneRegionAndRectangleDraw() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER, REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER, 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stack which contains
     * two regions. Check specific size for regions, auto-size gmf size for
     * container.
     */
    public void testCreationOfNewHorizontalRegionContainerWithTwoRegionsAndRectangleDraw() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_TWO_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER, REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER, 0, 1);
        // The second is the same but shift after the first.
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER, REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER, 0, 1);
    }

    /**
     * Test use of regions collapsed in container with horizontal stack which
     * contains two regions. Check specific size for regions, auto-size gmf size
     * for container when collapsing. Container size (gmf or Draw2D) must not
     * change because nothing has been collapse (regions are already
     * auto-sized).
     */
    public void testCollapseOfNewHorizontalRegionContainerWithTowRegions() {
        testCreationOfNewHorizontalRegionContainerWithTwoRegions();

        // selection of the second region
        editor.click(new Point(470, 360));
        SWTBotUtils.waitAllUiEvents(); // needed to have the collapse widget
                                       // displayed

        // collapse of the second region
        editor.click(new Point(520, 338));

        // check collapse
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_AUTO_SIZED_WITH_TWO_REGIONS_HSTACK, 0, 1);
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_DRAW2D_AUTO_SIZED, REGION_BOUNDS_FIRST_DRAW2D);
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_AUTO_SIZED_HSTACK, REGION_BOUNDS_SECOND_DRAW2D_HSTACK);
    }

    /**
     * Check that edge is not visible in case of collapse, even when source and
     * target container are moved.
     */
    public void testEdgeVisibilityInCaseOfCompartmentCollapsedAndContainerMoved() {
        openRepresentation(REGION_WITH_EDGES_REPRESENTATION_NAME, REGION_WITH_EDGES_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        SWTBotGefEditPart edgeSourceEditPart = editor.getEditPart("Left_p3", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart edgeTargetContainerEditPart = editor.getEditPart("Center_p4", AbstractDiagramElementContainerEditPart.class);
        DEdgeEditPart edgeEditPart = (DEdgeEditPart) ((AbstractDiagramElementContainerEditPart) edgeSourceEditPart.part()).getSourceConnections().get(0);
        assertTrue("The edge should be visible after diagram opening.", edgeEditPart.getFigure().isVisible());

        SWTBotGefEditPart editPartToResize = editor.getEditPart("[region-Center_p4]", AbstractDiagramElementContainerEditPart.class);
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);

        // Select the region contained in "Center_p4"
        AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) editPartToResize.part();
        GraphicalHelper.getAbsoluteBoundsIn100Percent(part);
        Point top = GraphicalHelper.getAbsoluteBoundsIn100Percent(part).getTop();
        editor.click(top.getTranslated(0, 10));
        // Collapse the region
        // Add a wait condition to have the collapse button displayed and click
        // on it
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                IFigure handleLayer = LayerManager.Helper.find(part).getLayer(LayerConstants.HANDLE_LAYER);
                Point toggleFigureLocation;
                if (handleLayer != null) {
                    for (Object figure : handleLayer.getChildren()) {
                        if (figure instanceof CompartmentCollapseHandle) {
                            toggleFigureLocation = ((CompartmentCollapseHandle) figure).getLocation();
                            if (toggleFigureLocation.x != 0 && toggleFigureLocation.y != 0) {
                                // Use the center of the figure and click on it
                                Dimension size = ((CompartmentCollapseHandle) figure).getSize();
                                toggleFigureLocation.translate(size.width / 2, size.height / 2);
                                editor.click(toggleFigureLocation);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The collapse button has not been found after region selection.";
            }
        });

        bot.waitUntil(editPartResizedCondition);

        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();

        // Check that edge is no longer visible
        assertFalse("The edge should be hidden after collapsing the container of the target of the edge.", edgeEditPart.getFigure().isVisible());
        // Move source of edge and check that edge is not displayed
        Rectangle nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeSourceEditPart.part());
        Point initialLocation = nodeBounds.getTop().getTranslated(0, 10);
        Point targetLocation = new Point(initialLocation.x, initialLocation.y + 20);
        ICondition editPartMovedCondition = new CheckEditPartMoved(edgeSourceEditPart);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
        assertFalse("The edge should be hidden after moving the source of the edge.", edgeEditPart.getFigure().isVisible());
        // Move target of edge and check that edge is not displayed
        nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeTargetContainerEditPart.part());
        initialLocation = nodeBounds.getTop().getTranslated(0, 10);
        targetLocation = new Point(initialLocation.x, initialLocation.y + 20);
        editPartMovedCondition = new CheckEditPartMoved(edgeTargetContainerEditPart);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
        assertFalse("The edge should be hidden after moving the container of the target of the edge.", edgeEditPart.getFigure().isVisible());
    }

    /**
     * Check that edge is not visible in case of collapse, even when source or
     * target node is moved.
     */
    public void testEdgeVisibilityInCaseOfCompartmentCollapsedAndNodeMoved() {
        openRepresentation(REGION_WITH_EDGES_AND_NODES_REPRESENTATION_NAME, REGION_WITH_EDGES_AND_NODES_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        SWTBotGefEditPart edgeSourceEditPart = editor.getEditPart("nodeLeft_p3", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart edgeTargetContainerEditPart = editor.getEditPart("Center_p4", AbstractDiagramElementContainerEditPart.class);
        DEdgeEditPart edgeEditPart = (DEdgeEditPart) ((AbstractDiagramNodeEditPart) edgeSourceEditPart.part()).getSourceConnections().get(0);
        assertTrue("The edge should be visible after diagram opening.", edgeEditPart.getFigure().isVisible());

        SWTBotGefEditPart editPartToResize = editor.getEditPart("[region-Center_p4]", AbstractDiagramElementContainerEditPart.class);
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);

        // Select the region contained in "Center_p4"
        AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) editPartToResize.part();
        GraphicalHelper.getAbsoluteBoundsIn100Percent(part);
        Point top = GraphicalHelper.getAbsoluteBoundsIn100Percent(part).getTop();
        editor.click(top.getTranslated(0, 10));
        // Collapse the region
        // Add a wait condition to have the collapse button displayed and click
        // on it
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                IFigure handleLayer = LayerManager.Helper.find(part).getLayer(LayerConstants.HANDLE_LAYER);
                Point toggleFigureLocation;
                if (handleLayer != null) {
                    for (Object figure : handleLayer.getChildren()) {
                        if (figure instanceof CompartmentCollapseHandle) {
                            toggleFigureLocation = ((CompartmentCollapseHandle) figure).getLocation();
                            if (toggleFigureLocation.x != 0 && toggleFigureLocation.y != 0) {
                                // Use the center of the figure and click on it
                                Dimension size = ((CompartmentCollapseHandle) figure).getSize();
                                toggleFigureLocation.translate(size.width / 2, size.height / 2);
                                editor.click(toggleFigureLocation);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The collapse button has not been found after region selection.";
            }
        });

        bot.waitUntil(editPartResizedCondition);

        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();

        // Check that edge is no longer visible
        assertFalse("The edge should be hidden after collapsing the container of the target of the edge.", edgeEditPart.getFigure().isVisible());
        // Move source of edge and check that edge is not displayed
        Rectangle nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeSourceEditPart.part());
        Point initialLocation = nodeBounds.getTop().getTranslated(0, 10);
        Point targetLocation = new Point(initialLocation.x, initialLocation.y + 20);
        ICondition editPartMovedCondition = new CheckEditPartMoved(edgeSourceEditPart);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
        assertFalse("The edge should be hidden after moving the source of the edge.", edgeEditPart.getFigure().isVisible());
        // Move target of edge and check that edge is not displayed
        nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeTargetContainerEditPart.part());
        initialLocation = nodeBounds.getTop().getTranslated(0, 10);
        targetLocation = new Point(initialLocation.x, initialLocation.y + 20);
        editPartMovedCondition = new CheckEditPartMoved(edgeTargetContainerEditPart);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
        assertFalse("The edge should be hidden after moving the container of the target of the edge.", edgeEditPart.getFigure().isVisible());
    }

    /**
     * Check that edgeToEdge is not visible in case of collapse, source and
     * target of this edge are edge from/to a child of the collapsed
     * compartment.
     */
    public void testEdgeToEdgeVisibilityInCaseOfCollapseCompartment() {
        openRepresentation(REGION_WITH_EDGE2EDGE_REPRESENTATION_NAME, REGION_WITH_EDGE2EDGE_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        SWTBotGefEditPart edge2edgeSourceEditPart = editor.getEditPart("myAnnotation", AbstractDiagramElementContainerEditPart.class);
        DEdgeEditPart edge2edgeEditPart = (DEdgeEditPart) ((AbstractDiagramElementContainerEditPart) edge2edgeSourceEditPart.part()).getSourceConnections().get(0);
        assertTrue("The edgeToEdge should be visible after diagram opening.", edge2edgeEditPart.getFigure().isVisible());

        SWTBotGefEditPart editPartToResize = editor.getEditPart("[region-Center_p4]", AbstractDiagramElementContainerEditPart.class);
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);

        // Select the region contained in "Center_p4"
        AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) editPartToResize.part();
        GraphicalHelper.getAbsoluteBoundsIn100Percent(part);
        Point top = GraphicalHelper.getAbsoluteBoundsIn100Percent(part).getTop();
        editor.click(top.getTranslated(0, 10));
        // Collapse the region
        // Add a wait condition to have the collapse button displayed and click
        // on it
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                IFigure handleLayer = LayerManager.Helper.find(part).getLayer(LayerConstants.HANDLE_LAYER);
                Point toggleFigureLocation;
                if (handleLayer != null) {
                    for (Object figure : handleLayer.getChildren()) {
                        if (figure instanceof CompartmentCollapseHandle) {
                            toggleFigureLocation = ((CompartmentCollapseHandle) figure).getLocation();
                            if (toggleFigureLocation.x != 0 && toggleFigureLocation.y != 0) {
                                // Use the center of the figure and click on it
                                Dimension size = ((CompartmentCollapseHandle) figure).getSize();
                                toggleFigureLocation.translate(size.width / 2, size.height / 2);
                                editor.click(toggleFigureLocation);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "The collapse button has not been found after region selection.";
            }
        });

        bot.waitUntil(editPartResizedCondition);

        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();

        // Check that edge is no longer visible
        assertFalse("The edgeToEdge should be hidden after collapsing the container of the target of the edge.", edge2edgeEditPart.getFigure().isVisible());
        // Move source of edge and check that edge is not displayed
        Rectangle nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edge2edgeSourceEditPart.part());
        Point initialLocation = nodeBounds.getTop().getTranslated(0, 10);
        Point targetLocation = new Point(initialLocation.x, initialLocation.y + 20);
        ICondition editPartMovedCondition = new CheckEditPartMoved(edge2edgeSourceEditPart);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        // Wait all UI events to ensure that the connections are refresh in
        // asyncExec (see
        // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.refreshConnections())
        SWTBotUtils.waitAllUiEvents();
        assertFalse("The edgeToEdge should be still hidden after moving the source of the edge.", edge2edgeEditPart.getFigure().isVisible());
    }

    /**
     * Test use of regions collapsed in container with horizontal stack which
     * contains two regions. Check specific size for regions, auto-size gmf size
     * for container when collapsing. Container size (gmf or Draw2D) must not
     * change. Second region is collapsed so container width change.
     */
    public void testCollapseOfNewHorizontalRegionContainerWithTowRegionsAndRectangleDraw() {
        testCreationOfNewHorizontalRegionContainerWithTwoRegionsAndRectangleDraw();

        SWTBotGefEditPart editPartToResize = editor.getEditPart(NEW_REGION_CONTAINER_NAME, AbstractDiagramElementContainerEditPart.class);
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);

        // selection of the second region
        editor.click(new Point(540, 420));
        SWTBotUtils.waitAllUiEvents(); // needed to have the collapse widget
                                       // displayed

        // collapse of the second region
        // Retrieve the toggle figure location (to click on it)
        SWTBotGefEditPart swtBotEditPart = editor.getEditPart(LEFT_CLASS_C1_NAME, AbstractDiagramElementContainerEditPart.class);
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) swtBotEditPart.part();
        IFigure handleLayer = LayerManager.Helper.find(editPart).getLayer(LayerConstants.HANDLE_LAYER);
        Point toggleFigureLocation = new Point(585, 340);
        if (handleLayer != null) {
            for (Object figure : handleLayer.getChildren()) {
                if (figure instanceof CompartmentCollapseHandle) {
                    toggleFigureLocation = ((CompartmentCollapseHandle) figure).getLocation();
                    // Use the center of the figure
                    Dimension size = ((CompartmentCollapseHandle) figure).getSize();
                    toggleFigureLocation.translate(size.width / 2, size.height / 2);
                }
            }
        }
        editor.click(toggleFigureLocation);

        bot.waitUntil(editPartResizedCondition);

        // check collapse
        int widthCollapseSize = 34;
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN.getCopy().setWidth(CONTAINER_BOUNDS_DRAWN.width - widthCollapseSize));
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER, REGION_BOUNDS_FIRST_IN_DRAWN_HSTACK_CONTAINER, 0, 1);
        // TODO: For HStack, when collapsing the last region, its width is set
        // to -1. But for VStack, in same scenario, its height is not set to -1.
        // Why?
        checkBounds(LEFT_CLASS_C1_NAME, REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER.getCopy().setWidth(-1),
                REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER.getCopy().setWidth(REGION_BOUNDS_SECOND_IN_DRAWN_HSTACK_CONTAINER.width - widthCollapseSize), 0, 1);
    }

    /**
     * Test creation of regions container with horizontal stack and Hide label.
     * Check auto-size gmf size for container (because of computation
     * expressions equal to -1).
     */
    public void testCreationOfNewHorizontalRegionContainerWithoutRegionAndHideLabel() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container without region
        createRegionContainerDiagram(PACKAGE_CREATION_HIDE_LABEL_TOOL_NAME, CONTAINER_CREATION_LOCATION);

        checkBounds(CONTAINER_CREATION_LOCATION, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DEFAULT_SIZE);
    }

    /**
     * Test creation of regions container with horizontal stack which contains
     * one region and then resizing the container. Check specific size for
     * region and auto-size gmf size and specific Draw2D size for container
     * match with rectangle draw).
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the
     *             editor.
     */
    public void testResizeOfNewHorizontalRegionContainerWithOneRegionAndRectangleDraw() throws Exception {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        editor.maximize();

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Create a new ePackage container with one region
        createRegionContainerDiagramWithRectangleDraw(PACKAGE_ONE_CLASS_CREATION_TOOL_NAME, CONTAINER_CREATION_LOCATION, CONTAINER_SECOND_CREATION_LOCATION);

        Rectangle realContainerBounds = checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, CONTAINER_BOUNDS_DRAWN);
        Rectangle realRegionBounds = checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER, REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER, 0, 1);
        // Resize the container
        SWTBotGefEditPart editPartToResize = editor.getEditPart(NEW_REGION_CONTAINER_NAME, AbstractDiagramElementContainerEditPart.class);
        editPartToResize.select();
        ICondition editPartResizedCondition = new CheckEditPartResized(editPartToResize);
        editor.drag(realContainerBounds.getRight(), realContainerBounds.getRight().getTranslated(-60, 0));
        bot.waitUntil(editPartResizedCondition);

        // Width bounds must decrease of 60px because of resizing of 60 px.
        checkBounds(NEW_REGION_CONTAINER_NAME, CONTAINER_BOUNDS_AUTO_SIZED, realContainerBounds.getResized(-60, 0));
        checkBounds(LEFT_CLASS_C0_NAME, REGION_BOUNDS_IN_DRAWN_HSTACK_CONTAINER.getResized(-60, 0), realRegionBounds.getResized(-60, 0), 0, 1);
    }

    /**
     * Create a new node using the given tool directly in the diagram
     * representation.
     * 
     * @param CREATION_TOOL_NAME
     *            Tool name to select
     */
    private void createRegionContainerDiagramWithRectangleDraw(String creationToolName, Point location, Point target) {
        // Select the tool
        editor.activateTool(creationToolName);
        editor.drag(location, target);
    }
}
