/*******************************************************************************
 * Copyright (c) 2023 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EMFUtil;

/**
 * Layout provider to layout all direct children of specified elements.
 * 
 * @author scosta
 *
 */
public class LayoutChildrenProvider extends AbstractLayoutProvider {

    /**
     * The provider which will be used to perform the basic layout (and to which the parameter enabling the layout to be
     * filtered on the elements desired by LayoutChildrenProvider will be added).
     */
    private Optional<AbstractLayoutEditPartProvider> baseLayoutProviderOpt;

    /**
     * Create layout provider to layout all direct children of specified elements. Uses the default algorithm (the one
     * specified in the first EditPart to layout). If you want to use a specific algorithm, use the other constructor.
     */
    public LayoutChildrenProvider() {
        this.baseLayoutProviderOpt = Optional.empty();
    }

    /**
     * Create layout provider to layout all direct children of specified elements.
     * 
     * @param baseLayoutProvider
     *            The provider which will be used to perform the basic layout (and to which the parameter enabling the
     *            layout to be filtered on the elements desired by LayoutChildrenProvider will be added).
     */
    public LayoutChildrenProvider(AbstractLayoutEditPartProvider baseLayoutProvider) {
        this.baseLayoutProviderOpt = Optional.of(baseLayoutProvider);
    }

    @Override
    public Command layoutEditParts(List selectionRaw, IAdaptable layoutHint) {
        List<EditPart> selection = selectionRaw;

        List<GraphicalEditPart> selectedEditParts = selection.stream() //
                .filter(GraphicalEditPart.class::isInstance) //
                .map(GraphicalEditPart.class::cast) //
                .toList();
        List<? extends GraphicalEditPart> directChildren = selectedEditParts.stream() //
                .flatMap(selectedEditPart -> getContent(selectedEditPart).stream()) //
                .toList();
        List<? extends GraphicalEditPart> directChildrenEdges = directChildren.stream() //
                .flatMap(this::getConnectionsStream) //
                .distinct() //
                .toList();
        List<? extends GraphicalEditPart> excludeEditPart = directChildren.stream() //
                .flatMap(this::getRecursivelyChildrenStream) //
                .distinct() //
                .filter(editPart -> !directChildren.contains(editPart)) //
                .filter(editPart -> !directChildrenEdges.contains(editPart)) //
                .toList();

        final IAdaptable newLayoutHint = new ExclusionLayoutHint(excludeEditPart, layoutHint);
        return baseLayoutProviderOpt //
                .or(getDefaultProvider(directChildren)) //
                .map(baseLayoutProvider -> baseLayoutProvider.layoutEditParts(directChildren, newLayoutHint)) //
                .orElse(UnexecutableCommand.INSTANCE);
    }

    /**
     * This method checks whether this layout can be applied to the given list of EditPart.
     * 
     * @param selection
     *            the target list on which the layout should be applied
     * @return if the layout is available for selection
     */
    public boolean isAvailableFor(List<? extends EditPart> selection) {
        List<GraphicalEditPart> selectedEditParts = selection.stream() //
                .filter(GraphicalEditPart.class::isInstance) //
                .map(GraphicalEditPart.class::cast) //
                .toList();
        List<? extends GraphicalEditPart> directChildren = selectedEditParts.stream() //
                .flatMap(selectedEditPart -> getContent(selectedEditPart).stream()) //
                .toList();

        return !directChildren.isEmpty() && baseLayoutProviderOpt.or(getDefaultProvider(directChildren)).isPresent();
    }

    private Supplier<? extends Optional<? extends AbstractLayoutEditPartProvider>> //
            getDefaultProvider(List<? extends GraphicalEditPart> editParts) {
        return () -> editParts.stream() //
                .filter(IGraphicalEditPart.class::isInstance) //
                .map(IGraphicalEditPart.class::cast) //
                .findFirst() //
                .map(first -> LayoutService.getProvider(first).getLayoutNodeProvider(first));
    }

    /**
     * Return a stream of all incoming and outgoing connection of the given edit part.
     * 
     * @param editPart
     *            the given editPart the edit part (of type GraphicalEditPart)
     * @return a stream of all incoming and outgoing connection of the given edit part
     */
    private Stream<? extends GraphicalEditPart> getConnectionsStream(GraphicalEditPart editPart) {
        List<? extends GraphicalEditPart> source = editPart.getSourceConnections();
        List<? extends GraphicalEditPart> target = editPart.getTargetConnections();
        return Stream.concat(source.stream(), target.stream());
    }

    /**
     * Return a stream of all children recursively and all attached edges of the given edit part (of type
     * GraphicalEditPart).
     * 
     * @param editPart
     *            The given edit part
     * @return All children recursively and all attached edges of the given edit part
     */
    private Stream<? extends GraphicalEditPart> getRecursivelyChildrenStream(GraphicalEditPart editPart) {
        return EMFUtil.getTreeStream(editPart, parent -> {
            Stream<? extends GraphicalEditPart> children = parent.getChildren().stream();
            Stream<? extends GraphicalEditPart> connections = getConnectionsStream(parent);
            return Stream.concat(children, connections).toList();
        });
    }
    
    /**
     * Return the content of a free form container or the content of the diagram.
     * 
     * This method returns the children of the edit part as a Sirius model, i.e. ignoring the layer of a container that
     * contains only the label and the compartment. This method returns the edit part of the compartment directly.
     * 
     * @return The list of children of the container/diagram or an empty list if not applicable
     */
    // CHECKSTYLE:OFF
    private List<? extends GraphicalEditPart> getContent(GraphicalEditPart editPart) {
        if (editPart instanceof DDiagramEditPart diagram) {
            // no compartment for the diagram
            return diagram.getChildren();

        } else if (editPart instanceof DNodeContainerEditPart container) {
            Optional<DNodeContainerViewNodeContainerCompartmentEditPart> compartmentOpt;
            compartmentOpt = container.getChildren().stream() //
                    .filter(DNodeContainerViewNodeContainerCompartmentEditPart.class::isInstance) //
                    .map(DNodeContainerViewNodeContainerCompartmentEditPart.class::cast) //
                    .findAny();

            return compartmentOpt.map(compartment -> compartment.getChildren()) //
                    .orElse(Collections.emptyList());

        } else if (editPart instanceof DNodeContainer2EditPart container2) {
            Optional<DNodeContainerViewNodeContainerCompartment2EditPart> compartmentOpt;
            compartmentOpt = container2.getChildren().stream() //
                    .filter(DNodeContainerViewNodeContainerCompartment2EditPart.class::isInstance) //
                    .map(DNodeContainerViewNodeContainerCompartment2EditPart.class::cast) //
                    .findAny();

            return compartmentOpt.map(compartment -> compartment.getChildren()) //
                    .orElse(Collections.emptyList());

        } else {
            return Collections.emptyList();
        }
    }
    // CHECKSTYLE:ON
}
