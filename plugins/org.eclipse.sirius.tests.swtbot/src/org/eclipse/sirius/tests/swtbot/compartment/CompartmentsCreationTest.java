/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests defined to ensure that elements are created in compartments and regions
 * at expected locations.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class CompartmentsCreationTest extends AbstractCompartmentTest {

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
        
        ContainerMapping rcMapping = getActualMapping(REGION_CONTAINER_NAME);
        checkChildrenPresentation(REGION_CONTAINER_NAME, rcMapping, initialContainerLayout);
    
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
        checkChildrenPresentation(REGION_CONTAINER_NAME, rcMapping, newRCPresentation);
        checkChildrenPresentation(LEFT_CLASS_NAME, listRegionMapping, ContainerLayout.LIST);
        checkChildrenPresentation(LEFT_PKG_NAME, freeFormRegionMapping, ContainerLayout.FREE_FORM);
    
        undo(localSession.getOpenedSession());
        checkChildrenPresentation(REGION_CONTAINER_NAME, rcMapping, initialContainerLayout);
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
        createElement(CLASS_LIST_CREATION_TOOL_NAME, REGION_CONTAINER_NAME);
        checkElement(NEW_CLASS_LIST_4_NAME, REGION_CONTAINER_NAME, CLASS_LIST_CREATION_TOOL_NAME);
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
        compartmentCreationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, new Point(400, 200));
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

}
