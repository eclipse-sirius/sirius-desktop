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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Layout provider that add a list of diagram elements to keep fixed in the LayoutHint, during arrange selection action.
 * This information will be used later in the PinnedElementHandler and PinnedElementLayoutProvider. It is executed
 * before the generic arrange operation.
 * 
 * @author smonnier
 * 
 */
public class ArrangeSelectionLayoutProvider extends AbstractLayoutProvider {

    /**
     * The initial layout provider that arrange the nodes (launch before the arrange of bordered nodes and before
     * updating the list of diagram element to keep fixed on arrange).
     */
    private AbstractLayoutProvider initialLayoutProvider;

    /**
     * List of IDiagramElementEditPart not selected and unpinned on the current diagram.
     */
    private ArrayList<IDiagramElementEditPart> notSelectedShapeNodeEditPartAndUnpinned;

    /**
     * Predicate to check if an EditPart is not selected
     */
    private Predicate<EditPart> editPartIsNotSelected = new Predicate<EditPart>() {

        @Override
        public boolean apply(EditPart input) {
            return input.getSelected() == EditPart.SELECTED_NONE;
        }
    };

    /**
     * Predicate to check if a IDiagramElementEditPart is unpinned
     */
    private Predicate<IDiagramElementEditPart> diagramElementEditPartIsUnpinned = new Predicate<IDiagramElementEditPart>() {

        @Override
        public boolean apply(IDiagramElementEditPart input) {
            EObject diagramElement = input.resolveDiagramElement();
            if (diagramElement instanceof DDiagramElement) {
                return !new PinHelper().isPinned((DDiagramElement) diagramElement);
            }
            return false;
        }
    };

    /**
     * The default constructor.
     * 
     * @param clp
     *            The layout provider to call after finding diagram element to keep fixed on arrange all.
     */
    public ArrangeSelectionLayoutProvider(AbstractLayoutProvider clp) {
        initialLayoutProvider = clp;
    }

    /**
     * {@inheritDoc}
     * 
     * This method is overridden to have the arrange selection acting as an arrange all where non selected elements are
     * pinned.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider#layoutEditParts(java.util.List,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint) {
        if (selectedObjects.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        }

        IAdaptable updatedLayoutHint = layoutHint;

        CompoundCommand result = new CompoundCommand();

        boolean arrangeIsArrangeSelection = false;
        if (selectedObjects instanceof LinkedList<?>) {
            LinkedList<?> selectedObjectsLinkedList = (LinkedList<?>) selectedObjects;
            if (selectedObjectsLinkedList.get(0) instanceof IGraphicalEditPart) {
                IGraphicalEditPart igep = (IGraphicalEditPart) selectedObjectsLinkedList.get(0);
                List topLevelEditParts = Lists.newArrayList(Iterables.filter(igep.getParent().getChildren(), ShapeNodeEditPart.class));
                arrangeIsArrangeSelection = validateArrangeIsArrangeSelection(selectedObjectsLinkedList, topLevelEditParts);
                if (arrangeIsArrangeSelection) {
                    // Find out unselected diagram element on container (same
                    // parent) that are unpinned
                    List<ShapeNodeEditPart> notSelectedShapeNodeEditPart = Lists.newArrayList(Iterables.filter(topLevelEditParts, editPartIsNotSelected));
                    notSelectedShapeNodeEditPart.removeAll(selectedObjectsLinkedList);
                    notSelectedShapeNodeEditPartAndUnpinned = Lists
                            .newArrayList(Iterables.filter(Iterables.filter(notSelectedShapeNodeEditPart, IDiagramElementEditPart.class), diagramElementEditPartIsUnpinned));
                    // Add all children of not selected editPart in the unpinned
                    // list (indeed this avoid a move of children
                    // and
                    // LayoutStabilityOnManualRefreshTest.testLayoutStabilityOnOneViewCreatedDuringManualRefreshWithoutPinnedElement
                    // and
                    // LayoutStabilityOnManualRefreshTest.testLayoutStabilityOnOneViewCreatedDuringManualRefreshWithPinnedElement.
                    addChildrenToNotSelectedUnpinnedList(notSelectedShapeNodeEditPart);
                    // Add all edges that connect two unpinned elements in the
                    // unpinned list
                    RootEditPart root = igep.getRoot();
                    if (root.getChildren().size() == 1 && root.getChildren().get(0) instanceof DiagramEditPart) {
                        addEdgesToNotSelectedUnpinnedList((DiagramEditPart) root.getChildren().get(0));
                    }

                    // Update Layout Hint to find later (in
                    // org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.AbstractCompositeLayoutProvider.layoutEditParts(List,
                    // IAdaptable) or
                    // org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.BorderItemAwareLayoutProvider.layoutEditParts(List,
                    // IAdaptable, boolean) for example) the list of unselected
                    // diagram element on diagram that are unpinned as elements
                    // to keep fixed in PinnedElementsHandler
                    final IAdaptable originalHint = layoutHint;
                    updatedLayoutHint = new IAdaptable() {
                        @Override
                        public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
                            if (Collection.class.equals(adapter)) {
                                return notSelectedShapeNodeEditPartAndUnpinned;
                            } else {
                                return originalHint.getAdapter(adapter);
                            }
                        }
                    };
                    // add all top level edit parts to have the arrange
                    // selection acting as an arrange all where non selected
                    // elements are pinned
                    selectedObjectsLinkedList.clear();
                    selectedObjectsLinkedList.addAll(topLevelEditParts);
                }
            }
        }

        result.add(lauchPrimaryArrangeAll(selectedObjects, updatedLayoutHint));

        if (notSelectedShapeNodeEditPartAndUnpinned != null) {
            notSelectedShapeNodeEditPartAndUnpinned.clear();
        }
        return result;
    }

    /**
     * Add, if needed, children that are also unpinned.
     * 
     * @param notSelectedParent
     *            list of parents which are not selected for which you want to browse the children to possibly add to
     *            the unpinned list
     */
    private void addChildrenToNotSelectedUnpinnedList(Collection<? extends EditPart> notSelectedParent) {
        for (EditPart editPart : notSelectedParent) {
            ArrayList<EditPart> notSelectedChildrenShapeNodeEditPart = Lists.newArrayList(Iterables.filter(editPart.getChildren(), editPartIsNotSelected));
            if (!notSelectedChildrenShapeNodeEditPart.isEmpty()) {
                notSelectedShapeNodeEditPartAndUnpinned.addAll(Lists.newArrayList(Iterables
                        .filter(Iterables.filter(Iterables.filter(notSelectedChildrenShapeNodeEditPart, ShapeNodeEditPart.class), IDiagramElementEditPart.class), diagramElementEditPartIsUnpinned)));
                addChildrenToNotSelectedUnpinnedList(notSelectedChildrenShapeNodeEditPart);
            }
        }
    }

    /**
     * Adds all the edges that connect two pinned nodes (or considered as such) in the list of
     * <code>notSelectedShapeNodeEditPartAndUnpinned</code>. This list of edges is used in
     * {@link AbstractCompositeLayoutProvider} to avoid the call of method routeThrough.
     */
    private void addEdgesToNotSelectedUnpinnedList(DiagramEditPart diagramEditPart) {
        for (Object connection : diagramEditPart.getConnections()) {
            if (connection instanceof DEdgeEditPart) {
                final DEdgeEditPart dEdgeEditPart = (DEdgeEditPart) connection;
                if (isPinnedOrConsiderAs(dEdgeEditPart.getSource(), notSelectedShapeNodeEditPartAndUnpinned)
                        && isPinnedOrConsiderAs(dEdgeEditPart.getTarget(), notSelectedShapeNodeEditPartAndUnpinned)) {
                    notSelectedShapeNodeEditPartAndUnpinned.add(dEdgeEditPart);
                }
            }
        }
    }

    /**
     * Tests whether an edit part is pinned or should be considered as pinned (fixed size and location) during the
     * layout.
     * 
     * @param editPart
     *            the edit part.
     * @param editPartsConsiderAsPinned
     *            list of editParts that have to be consider as pinned
     * @return <code>true</code> if the edit part is pinned or should be considered as pinned.
     */
    protected boolean isPinnedOrConsiderAs(EditPart editPart, List<IDiagramElementEditPart> editPartsConsiderAsPinned) {
        return editPart instanceof IGraphicalEditPart && (isPinned((IGraphicalEditPart) editPart) || editPartsConsiderAsPinned.contains(editPart));
    }

    /**
     * Validate if the Arrange action is an Arrange Selection action.
     * 
     * @param selectedObjects
     *            List of selected element
     * @param diagramTopElements
     * @return
     */
    private boolean validateArrangeIsArrangeSelection(LinkedList<?> selectedObjects, List diagramTopElements) {
        // We validate that we do not have the same element as the top diagram
        // elements.
        boolean result = selectedObjects.size() != diagramTopElements.size() || !selectedObjects.containsAll(diagramTopElements);
        return result;
    }

    /**
     * Launches the primary arrange all that arrange all nodes.
     * 
     * @param selectedObjects
     *            the objects to arrange.
     * @param layoutHint
     *            the layout hint.
     * @return the arrange command.
     */
    protected Command lauchPrimaryArrangeAll(final List selectedObjects, final IAdaptable layoutHint) {
        return initialLayoutProvider.layoutEditParts(selectedObjects, layoutHint);
    }

}
