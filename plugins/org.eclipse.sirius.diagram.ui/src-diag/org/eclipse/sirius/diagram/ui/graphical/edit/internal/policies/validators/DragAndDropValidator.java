/*******************************************************************************
 * Copyright (c) 2011, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.internal.policies.validators;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.resource.WorkspaceDragAndDropSupport;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerWithInterpreterOperations;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.dnd.DragAndDropWrapper;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;

/**
 * Validator for drag'n drop request.
 * 
 * @author edugueperoux
 */
public class DragAndDropValidator {

    private Set<DragAndDropWrapper> elementsFromEclipseViewToDrop = new HashSet<DragAndDropWrapper>();

    private Set<DDiagramElement> elementsFromDiagramToDrop = new HashSet<DDiagramElement>();

    private Set<IGraphicalEditPart> editPartsFromDiagramToDrop = new HashSet<IGraphicalEditPart>();

    private DragAndDropTarget targetDragAndDropTarget;

    private Session session;

    private EObject targetAbstractDNodeSemanticTarget;

    private DragAndDropTargetDescription dragAndDropDescription;

    /**
     * True if all elements drop from a view will be "transformed" in border nodes after the drop, false otherwise. This
     * variable is set after a call to method {@link #isValid(ChangeBoundsRequest, GraphicalEditPart)}.
     */
    private boolean isConcerningOnlyBorderNodeFromView;

    /**
     * Valid a drag'n drop request.
     * 
     * @param request
     *            the request of the drag'n drop
     * @param hostGraphicalEditPart
     *            the EditPart target
     * 
     * @return true if the request is valid (i.e. can be executed)
     */
    public boolean isValid(ChangeBoundsRequest request, GraphicalEditPart hostGraphicalEditPart) {

        boolean isValid = init(request, hostGraphicalEditPart);
        // If there is no element to drop the drag'n'drop request has been
        // consider has invalid.
        if (!elementsFromEclipseViewToDrop.isEmpty() || !elementsFromDiagramToDrop.isEmpty()) {
            isValid = isValid && RequestConstants.REQ_DROP.equals(request.getType());
        } else {
            isValid = false;
        }
        if (isValid) {
            dragAndDropDescription = targetDragAndDropTarget.getDragAndDropDescription();
            isValid = isValid && targetDragAndDropTarget instanceof DSemanticDecorator;
            if (isValid) {
                targetAbstractDNodeSemanticTarget = ((DSemanticDecorator) targetDragAndDropTarget).getTarget();
                session = SessionManager.INSTANCE.getSession(targetAbstractDNodeSemanticTarget);
                if (dragAndDropDescription != null && targetAbstractDNodeSemanticTarget != null)
                    isValid = isValidDragAndDropRequestForElementFromEclipseView() && isValidDragAndDropRequestForElementFromDiagram();
                else
                    isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Initializes the set containing {@link DDiagramElement} associated to dragged elements.
     * 
     * Returns false if an element to drop is a BorderedNode and if the layout mode is activated in the diagram of the
     * host part drag and drop is forbidden in this case (returning false will invalidate the drop and re-enable the
     * move).
     * 
     * @param request
     *            the request from which the initialization is done.
     * @param hostGraphicalEditPart
     *            the part target of the drop.
     * @return false if an element to drop is a bordered node and if the layout mode is activated in the diagram of the
     *         host part. True otherwise.
     */
    private boolean init(ChangeBoundsRequest request, GraphicalEditPart hostGraphicalEditPart) {
        List<?> editParts = request.getEditParts();
        EditPartQuery editPartQuery = new EditPartQuery(hostGraphicalEditPart);
        boolean isInLayoutingMode = editPartQuery.isInLayoutingMode();
        boolean isInShowingMode = editPartQuery.isInShowingMode();
        for (Object editPart : editParts) {
            if (editPart instanceof DragAndDropWrapper) {
                DragAndDropWrapper dragAndDropWrapperToDrop = (DragAndDropWrapper) editPart;
                elementsFromEclipseViewToDrop.add(dragAndDropWrapperToDrop);
            } else if (editPart instanceof IGraphicalEditPart) {
                IGraphicalEditPart graphicalEditPartToDrop = (IGraphicalEditPart) editPart;

                /*
                 * Impossible to move a border node in a subfunction if there is a drag and drop tool associated to the
                 * node drag'n'drop should not be enabled on the same container to avoid to block the move of an element
                 */
                if (!(graphicalEditPartToDrop.getParent() == hostGraphicalEditPart
                        || hostGraphicalEditPart instanceof CompartmentEditPart && graphicalEditPartToDrop.getParent() == hostGraphicalEditPart.getParent())
                        && graphicalEditPartToDrop.resolveSemanticElement() instanceof DSemanticDecorator) {
                    EObject elementFromDiagramToDrop = graphicalEditPartToDrop.resolveSemanticElement();
                    DDiagramElement dDiagramElementTopDrop = (DDiagramElement) elementFromDiagramToDrop;
                    elementsFromDiagramToDrop.add(dDiagramElementTopDrop);
                    editPartsFromDiagramToDrop.add(graphicalEditPartToDrop);

                    if ((isInLayoutingMode || isInShowingMode) && graphicalEditPartToDrop instanceof IAbstractDiagramNodeEditPart) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    private boolean isValidDragAndDropRequestForElementFromEclipseView() {
        boolean isValidDragAndDropRequestForElementFromEclipseView = true;
        Iterator<DragAndDropWrapper> iterator = elementsFromEclipseViewToDrop.iterator();
        if (iterator.hasNext()) {
            isConcerningOnlyBorderNodeFromView = true;
        }
        while (iterator.hasNext() && isValidDragAndDropRequestForElementFromEclipseView) {
            DragAndDropWrapper elementFromEclipseViewToDrop = iterator.next();
            if (elementFromEclipseViewToDrop.getObject() instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) elementFromEclipseViewToDrop.getObject();
                for (final Object object : structuredSelection.toList()) {
                    EObject droppedElementForDropTool = new WorkspaceDragAndDropSupport().convert(object, session);
                    isValidDragAndDropRequestForElementFromEclipseView = isValidDragAndDropRequestForElementFromEclipseView && droppedElementForDropTool != null;
                    if (isValidDragAndDropRequestForElementFromEclipseView) {
                        ContainerDropDescription containerDropDescription = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragAndDropDescription, droppedElementForDropTool, null,
                                targetAbstractDNodeSemanticTarget, targetDragAndDropTarget, DragSource.PROJECT_EXPLORER_LITERAL, null);
                        isValidDragAndDropRequestForElementFromEclipseView = containerDropDescription != null;
                        if (isValidDragAndDropRequestForElementFromEclipseView) {
                            for (DiagramElementMapping diagramElementMapping : containerDropDescription.getMappings()) {
                                if (!DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings().equals(diagramElementMapping.eContainingFeature())) {
                                    isConcerningOnlyBorderNodeFromView = false;
                                }
                            }
                        } else {
                            isConcerningOnlyBorderNodeFromView = false;
                        }
                    }
                }
            }
        }
        return isValidDragAndDropRequestForElementFromEclipseView;
    }

    /**
     * The drop location is different for nodes and for border nodes. So it is useful to know if the current drop
     * concerned only border nodes to adapt the drop location.<BR>
     * WARNING: This method must be called after {@link #isValid(ChangeBoundsRequest, GraphicalEditPart)}, because the
     * calculating of <code>isConcerningOnlyBorderNode</code> is made during this last one.
     * 
     * @return true if all elements drop from a view will be "transformed" in border nodes after the drop, false
     *         otherwise.
     */
    public boolean isConcerningOnlyBorderNodeFromView() {
        return isConcerningOnlyBorderNodeFromView;
    }

    private boolean isValidDragAndDropRequestForElementFromDiagram() {
        boolean isValidDragAndDropRequestForElementFromDiagram = true;
        Iterator<DDiagramElement> iterator = elementsFromDiagramToDrop.iterator();
        while (iterator.hasNext() && isValidDragAndDropRequestForElementFromDiagram) {
            DDiagramElement elementFromDiagramToDrop = iterator.next();
            isValidDragAndDropRequestForElementFromDiagram = isValidDragAndDropRequestForElementFromDiagram && elementFromDiagramToDrop != null;
            if (isValidDragAndDropRequestForElementFromDiagram) {
                ContainerDropDescription containerDropDescription = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragAndDropDescription,
                        ((DSemanticDecorator) elementFromDiagramToDrop).getTarget(), getSemanticContainer(elementFromDiagramToDrop), targetAbstractDNodeSemanticTarget, targetDragAndDropTarget,
                        DragSource.DIAGRAM_LITERAL, elementFromDiagramToDrop);
                isValidDragAndDropRequestForElementFromDiagram = containerDropDescription != null;
            }
        }
        return isValidDragAndDropRequestForElementFromDiagram;
    }

    private EObject getSemanticContainer(final DDiagramElement diagramElement) {
        EObject semanticContainer = null;
        EObject current = diagramElement.eContainer();
        while (current != null && semanticContainer == null) {
            if (current instanceof DSemanticDecorator) {
                semanticContainer = ((DSemanticDecorator) current).getTarget();
            }
            current = current.eContainer();
        }
        return semanticContainer;
    }

    /**
     * Set the target {@link targetDragAndDropTarget} of this drag'n drop request.
     * 
     * @param targetDragAndDropTarget
     *            the target
     */
    public void setTargetDragAndDropTarget(DragAndDropTarget targetDragAndDropTarget) {
        this.targetDragAndDropTarget = targetDragAndDropTarget;
    }

    /**
     * Get the description of the target {@link DDiagramElement} of the drag'n drop request.
     * 
     * @return the description of the target {@link DDiagramElement} of the drag'n drop request
     */
    public DragAndDropTargetDescription getDragDragAndDropDescription() {
        return dragAndDropDescription;
    }

    /**
     * Get the semantic of the target {@link DDiagramElement} of the drag'n drop request.
     * 
     * @return the semantic of the target {@link DDiagramElement} of the drag'n drop request
     */
    public EObject getTargetAbstractDNodeSemanticTarget() {
        return targetAbstractDNodeSemanticTarget;
    }

    /**
     * Get the set of element to drop from a Eclipse view.
     * 
     * @return the set of element to drop from a Eclipse view
     */
    public Set<DragAndDropWrapper> getElementsFromEclipseViewToDrop() {
        return elementsFromEclipseViewToDrop;
    }

    /**
     * Get the set of element to drop from a Diagram.
     * 
     * @return the set of element to drop from a Diagram
     */
    public Set<DDiagramElement> getElementsFromDiagramToDrop() {
        return elementsFromDiagramToDrop;
    }

    /**
     * Get the set of EditPart to drop from a Diagram.
     * 
     * @return the set of EditPart to drop from a Diagram
     */
    public Set<IGraphicalEditPart> getEditPartsFromDiagramToDrop() {
        return editPartsFromDiagramToDrop;
    }
}
