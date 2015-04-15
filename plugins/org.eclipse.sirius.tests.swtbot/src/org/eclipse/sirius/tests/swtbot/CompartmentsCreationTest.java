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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
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
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        childrenPresentationTest(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that containers displays correctly its children in a vertical
     * stack.
     */
    public void testChildrenPresentationsInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        childrenPresentationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that a NodeList element is correctly created in a horizontal
     * stack compartment and has the expected location.
     */
    public void testContainerListCreationInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(CLASS_LIST_CREATION_TOOL_NAME, COMPARTMENT_NAME);
        checkElement(NEW_CLASS_LIST_4_NAME, COMPARTMENT_NAME, CLASS_LIST_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a NodeList element is correctly created in a vertical stack
     * compartment and has the expected location.
     */
    public void testContainerListCreationInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(CLASS_LIST_CREATION_TOOL_NAME, COMPARTMENT_NAME);
        checkElement(NEW_CLASS_LIST_4_NAME, COMPARTMENT_NAME, CLASS_LIST_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that horizontal stack Compartment and its children are correctly
     * created.
     */
    public void testCompartmentCreationInHorizontalStack() {
        compartmentCreationTest(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);

    }

    /**
     * Ensures that vertical stack Compartment and its children are correctly
     * created.
     */
    public void testCompartmentCreationInVerticalStack() {
        compartmentCreationTest(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Ensures that a Node element is correctly created in container layouted
     * with "FreeForm" style in a horizontal stack compartment and has the
     * expected location.
     */
    public void testNodeCreationInFreeFormContainerInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(CLASS_NODE_CREATION_TOOL_NAME, PACKAGE_3_NAME);
        checkElement(NEW_CLASS_NODE_NAME, PACKAGE_3_NAME, CLASS_NODE_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a Node element is correctly created in container layouted
     * with "FreeForm" style in a vertical stack compartment and has the
     * expected location.
     */
    public void testNodeCreationInFreeFormContainerInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(CLASS_NODE_CREATION_TOOL_NAME, PACKAGE_3_NAME);
        checkElement(NEW_CLASS_NODE_NAME, PACKAGE_3_NAME, CLASS_NODE_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a list item element is correctly created in container
     * layouted with "list" style in a horizontal stack compartment and has the
     * expected location.
     */
    public void testItemCreationInListContainerInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(ATTRIBUTE_CREATION_TOOL_NAME, RIGHT_CLASS_NAME);
        checkElement(NEW_ATTRIBUTE_NODE_NAME, RIGHT_CLASS_NAME, ATTRIBUTE_CREATION_TOOL_NAME);
    }

    /**
     * Ensures that a list item element is correctly created in container
     * layouted with "list" style created in a vertical stack compartment and
     * has the expected location.
     */
    public void testItemCreationInListContainerInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        createElement(ATTRIBUTE_CREATION_TOOL_NAME, RIGHT_CLASS_NAME);
        checkElement(NEW_ATTRIBUTE_NODE_NAME, RIGHT_CLASS_NAME, ATTRIBUTE_CREATION_TOOL_NAME);
    }

    private void compartmentCreationTest(String representationName, String representationInstanceName) {
        openRepresentation(representationName, representationInstanceName);
        createCompartmentInDiagram(PACKAGE_CREATION_TOOL_NAME);
        checkCompartmentInDiagram(NEW_COMPARTMENT_NAME);

        createElement(CLASS_LIST_CREATION_TOOL_NAME, NEW_COMPARTMENT_NAME);
        checkElement(NEW_CLASS_LIST_1_NAME, NEW_COMPARTMENT_NAME, CLASS_LIST_CREATION_TOOL_NAME);

        createElement(PACKAGE_CREATION_TOOL_NAME, NEW_COMPARTMENT_NAME);
        checkElement(NEW_PACKAGE_1_NAME, NEW_COMPARTMENT_NAME, PACKAGE_CREATION_TOOL_NAME);

        createElement(CLASS_NODE_CREATION_TOOL_NAME, NEW_PACKAGE_1_NAME);
        checkElement(NEW_CLASS_NODE_NAME, NEW_PACKAGE_1_NAME, CLASS_NODE_CREATION_TOOL_NAME);
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
        diagramEditor.activateTool(CREATION_TOOL_NAME);
        // Click in the target edit part to create element on it
        diagramEditor.click(targetEditPartName);
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
        SWTBotGefEditPart containerEditPart = diagramEditor.getEditPart(containerName, AbstractDiagramElementContainerEditPart.class);
        EObject containerElement = ((IGraphicalEditPart) containerEditPart.part()).resolveSemanticElement();

        SWTBotGefEditPart createdEditPart = null;
        EObject createdElement = null;
        if (creationToolName.equals(ATTRIBUTE_CREATION_TOOL_NAME)) {
            createdEditPart = diagramEditor.getEditPart(createdElementName, DNodeListElementEditPart.class);
            createdElement = ((DNodeListElementEditPart) createdEditPart.part()).resolveSemanticElement();
        } else if (creationToolName.equals(CLASS_NODE_CREATION_TOOL_NAME)) {
            createdEditPart = diagramEditor.getEditPart(createdElementName, AbstractDiagramNodeEditPart.class);
            createdElement = ((AbstractDiagramNodeEditPart) createdEditPart.part()).resolveSemanticElement();
        } else {
            createdEditPart = diagramEditor.getEditPart(createdElementName, AbstractDiagramElementContainerEditPart.class);
            createdElement = ((AbstractDiagramElementContainerEditPart) createdEditPart.part()).resolveSemanticElement();
        }

        boolean isContained = ((DDiagramElementContainer) containerElement).getElements().contains(createdElement);
        assertTrue("The '" + createdElementName + "' element should be correctly created in '" + containerName + "' element", isContained);
    }

    /**
     * Create a new node using the defined Node Creation tool directly in the
     * diagram representation.
     * 
     * @param CREATION_TOOL_NAME
     *            Tool name to select
     */
    private void createCompartmentInDiagram(String CREATION_TOOL_NAME) {
        // Select the tool
        diagramEditor.activateTool(CREATION_TOOL_NAME);
        // Click in the diagram representation to create element on it
        if (diagramEditor.getDRepresentation().getName().equals(HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            diagramEditor.click(200, 200);
        } else if (diagramEditor.getDRepresentation().getName().equals(VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            diagramEditor.click(400, 200);
        }
    }

    /**
     * Check if the new element has been created in the diagram with the correct
     * style.
     * 
     * @param createdCompartmentName
     *            the new created element
     */
    private void checkCompartmentInDiagram(String createdCompartmentName) {
        SWTBotGefEditPart createdCompartmentEditPart = diagramEditor.getEditPart(createdCompartmentName, AbstractDiagramElementContainerEditPart.class);
        EObject createdCompartmentElement = ((IGraphicalEditPart) createdCompartmentEditPart.part()).resolveSemanticElement();
        boolean isContained = diagramEditor.getDRepresentation().getOwnedRepresentationElements().contains(createdCompartmentElement);
        assertTrue("The '" + createdCompartmentName + "' element should be correctly created in the diagram", isContained);
        assertTrue("The created compartment '" + createdCompartmentName + "'should be a DNodeContainer type", createdCompartmentElement instanceof DNodeContainer);

        if (diagramEditor.getDRepresentation().getName().equals(HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            assertEquals("The created compartment '" + createdCompartmentName + "'should be layouted with 'Horizontal Stack' style", ContainerLayout.HORIZONTAL_STACK,
                    ((DNodeContainer) createdCompartmentElement).getChildrenPresentation());
        } else if (diagramEditor.getDRepresentation().getName().equals(VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME)) {
            assertEquals("The created compartment '" + createdCompartmentName + "'should be layouted with 'Vertical Stack' style", ContainerLayout.VERTICAL_STACK,
                    ((DNodeContainer) createdCompartmentElement).getChildrenPresentation());
        }
    }

    private void childrenPresentationTest(String representationName, String representationInstanceName) {
        if (representationName.equals(HORIZONTAL_STACK_REPRESENTATION_NAME)) {
            checkChildrenPresentation(COMPARTMENT_NAME, ContainerLayout.HORIZONTAL_STACK);
        } else if (representationName.equals(VERTICAL_STACK_REPRESENTATION_NAME)) {
            checkChildrenPresentation(COMPARTMENT_NAME, ContainerLayout.VERTICAL_STACK);
        }
        checkChildrenPresentation(LEFT_CLASS_NAME, ContainerLayout.LIST);
        checkChildrenPresentation(PACKAGE_3_NAME, ContainerLayout.FREE_FORM);
    }

    /**
     * Edit and test container children presentation mappings.
     * 
     * @param editPartName
     *            the edit part name
     * @param containerLayout
     *            the expected container layout
     */
    private void checkChildrenPresentation(String editPartName, ContainerLayout containerLayout) {
        // Check mapping
        ContainerMapping actualMapping = getActualMapping(editPartName);
        assertEquals("Wrong children prensetantion name for '" + editPartName + "' container", containerLayout, actualMapping.getChildrenPresentation());

        // Ensure that the actual mapping is correctly edited from the VSM
        if (actualMapping.getChildrenPresentation().equals(ContainerLayout.FREE_FORM)) {
            // Change children presentation from FREE_FORM to LIST
            changeChildrenPresentation(actualMapping, ContainerLayout.LIST);
            assertEquals("Wrong children prensetantion name for '" + editPartName + "' container", ContainerLayout.LIST, getActualMapping(editPartName).getChildrenPresentation());
        } else if (actualMapping.getChildrenPresentation().equals(ContainerLayout.LIST)) {
            // Change children presentation from LIST to FREE_FORM
            changeChildrenPresentation(actualMapping, ContainerLayout.FREE_FORM);
            assertEquals("Wrong children prensetantion name for '" + editPartName + "' container", ContainerLayout.FREE_FORM, getActualMapping(editPartName).getChildrenPresentation());
        } else if (actualMapping.getChildrenPresentation().equals(ContainerLayout.HORIZONTAL_STACK)) {
            // Change check children presentation from horizontal to vertical
            // stack
            changeChildrenPresentation(actualMapping, ContainerLayout.VERTICAL_STACK);
            assertEquals("Wrong children prensetantion name for '" + editPartName + "' container", ContainerLayout.VERTICAL_STACK, getActualMapping(editPartName).getChildrenPresentation());
        } else if (actualMapping.getChildrenPresentation().equals(ContainerLayout.VERTICAL_STACK)) {
            // Change check children presentation from vertical to horizontal
            // stack
            changeChildrenPresentation(actualMapping, ContainerLayout.HORIZONTAL_STACK);
            assertEquals("Wrong children prensetantion name for '" + editPartName + "' container", ContainerLayout.HORIZONTAL_STACK, getActualMapping(editPartName).getChildrenPresentation());
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
        DDiagramElementContainer semanticElement = (DDiagramElementContainer) ((IGraphicalEditPart) diagramEditor.getEditPart(editPartName).part()).resolveSemanticElement();
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
