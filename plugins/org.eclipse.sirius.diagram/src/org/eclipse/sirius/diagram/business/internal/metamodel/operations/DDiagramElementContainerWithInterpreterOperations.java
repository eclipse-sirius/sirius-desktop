/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Sets;

/**
 * Implementation of DDiagramElementContainerImpl.java.
 * 
 * @author cbrun, mchauvin
 */
public final class DDiagramElementContainerWithInterpreterOperations {

    /** Avoid instanciations */
    private DDiagramElementContainerWithInterpreterOperations() {
        // empty.
    }

    /**
     * Return all elements that are in the specified container.
     * 
     * @param container
     *            the container.
     * @return all elements that are in the specified container.
     */
    public static EList<DDiagramElement> getElements(final DDiagramElementContainer container) {
        /*
         * subclasses should override it
         */
        return new BasicEList<DDiagramElement>();
    }

    /**
     * Return the best drop description.
     * 
     * @param description
     *            .
     * @param droppedElement
     *            The semantic dropped element
     * @param oldContainer
     *            The old semantic container, can be null (for instance if drop comes from project explorer)
     * @param newContainer
     *            The new semantic container
     * @param newViewContainer
     *            The new view container
     * @param dragSource
     *            the drag source.
     * @param droppedDiagramElement
     *            The graphical dropped element.
     * @return he best drop description
     */
    public static ContainerDropDescription getBestDropDescription(final DragAndDropTargetDescription description, final EObject droppedElement, final EObject oldContainer, final EObject newContainer,
            final EObject newViewContainer, final DragSource dragSource, final EObject droppedDiagramElement) {

        final DDiagram diagram;
        if (newViewContainer instanceof DDiagram) {
            diagram = (DDiagram) newViewContainer;
        } else {
            diagram = ((DDiagramElement) newViewContainer).getParentDiagram();
        }

        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
        if (oldContainer != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_OLD, oldContainer);
        }
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_NEW, newContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW, newViewContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, droppedElement);
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

        ContainerDropDescription bestDropDescription = null;

        /* find valid candidates */
        final Collection<ContainerDropDescription> candidates = new ArrayList<>();
        for (final ContainerDropDescription dropTool : DDiagramElementContainerWithInterpreterOperations.getActivatedDropTools(diagram, description)) {
            if (DDiagramElementContainerWithInterpreterOperations.checkDragSource(dropTool, dragSource)
                    && DDiagramElementContainerWithInterpreterOperations.checkDroppedDiagramElement(dropTool, droppedDiagramElement, newViewContainer)) {
                if (DDiagramElementContainerWithInterpreterOperations.checkPrecondition(dropTool, safeInterpreter, droppedElement)) {
                    candidates.add(dropTool);
                }
            }
        }

        /*
         * if a candidate define a target mapping which matches he has priority
         */
        for (final ContainerDropDescription dropTool : candidates) {
            if (ContainerMappingWithInterpreterHelper.getBestMapping(dropTool, (DragAndDropTarget) newViewContainer, droppedElement) != null) {
                if (bestDropDescription == null) {
                    bestDropDescription = dropTool;
                } else {
                    SiriusPlugin.getDefault().warning(
                            MessageFormat.format(Messages.DDiagramElementContainerSpecOperations_tooMuchDropDescErrorMsg, droppedElement, bestDropDescription.getName(), dropTool),
                            new RuntimeException());
                }
            }
        }
        /*
         * if there is no target mapping, the drag may not create a diagram element but could be valid
         */
        if (dragSource == DragSource.PROJECT_EXPLORER_LITERAL && bestDropDescription == null && !candidates.isEmpty()) {
            bestDropDescription = (ContainerDropDescription) candidates.toArray()[0];
        }

        return bestDropDescription;
    }

    private static boolean checkDragSource(final ContainerDropDescription dropTool, final DragSource dragSource) {
        return dropTool.getDragSource() == DragSource.BOTH_LITERAL || dropTool.getDragSource() == dragSource;
    }

    private static boolean checkDroppedDiagramElement(final ContainerDropDescription dropTool, final EObject droppedDiagramElement, final EObject newViewContainer) {
        boolean valid = true;
        if (droppedDiagramElement instanceof DDiagramElement) {
            if (droppedDiagramElement.equals(newViewContainer)) {
                valid = dropTool.getContainers().contains(((DDiagramElement) droppedDiagramElement).getMapping());
            } else {
                valid = dropTool.getMappings().contains(((DDiagramElement) droppedDiagramElement).getMapping());
            }
        }
        return valid;
    }

    private static boolean checkPrecondition(final ContainerDropDescription dropTool, final RuntimeLoggerInterpreter safeInterpreter, final EObject droppedElement) {
        final String precondition = dropTool.getPrecondition();
        if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
            return safeInterpreter.evaluateBoolean(droppedElement, dropTool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }
        return true;
    }

    /**
     * Returns the drop tools of the mapping.
     * 
     * @param mapping
     * @return the drops tools of the mapping
     */
    private static Collection<ContainerDropDescription> getDropTools(final DragAndDropTargetDescription mapping) {
        Collection<ContainerDropDescription> dropTools;
        if (mapping instanceof DiagramElementMapping) {
            dropTools = Sets.newHashSet(new DiagramElementMappingQuery((DiagramElementMapping) mapping).getDropTools());
        } else {
            dropTools = Sets.newHashSet(mapping.getDropDescriptions());
        }
        return dropTools;
    }

    /**
     * Returns the list of the activated drop tools for the <code>mapping</code>. An activated drop tool is:
     * <UL>
     * <LI>in a default layer of any diagram,</LI>
     * <LI>or in an activated layer of the current diagram (contained in or reused by).</LI>
     * </UL>
     * 
     * @param diagram
     *            The current diagram
     * @param mapping
     *            The mapping of the current drag'n'droped element
     * @return the list of the activated drop tools.
     */
    private static Collection<ContainerDropDescription> getActivatedDropTools(final DDiagram diagram, final DragAndDropTargetDescription mapping) {
        Collection<ContainerDropDescription> dropTools = DDiagramElementContainerWithInterpreterOperations.getDropTools(mapping);
        return dropTools.stream().filter(desc -> isActive(desc, diagram)).collect(Collectors.toCollection(HashSet<ContainerDropDescription>::new));
    }

    /**
     * Tests whether a {@link ContainerDropDescription} belongs to a default layer or to an activated layer of the
     * current diagram.
     * 
     * @param dropDesc
     *            the current {@link ContainerDropDescription} of the drag'n'dropped element.
     * @param diagram
     *            the current diagram
     * @return <code>true</code> if the ContainerDropDescription is active, false otherwise.
     */
    private static boolean isActive(final ContainerDropDescription dropDesc, final DDiagram diagram) {
        boolean result = false;
        Optional<Layer> optionalParentLayer = getLayer(dropDesc);
        if (optionalParentLayer.isPresent()) {
            if (!(optionalParentLayer.get() instanceof AdditionalLayer)) {
                // This layer is a default layer.
                result = true;
            } else {
                List<Layer> activatedLayers = new DDiagramQuery(diagram).getAllActivatedLayers();
                if (activatedLayers.contains(optionalParentLayer.get()) || activatedLayers.stream().anyMatch(layer -> layer.getReusedTools().contains(dropDesc))) {
                    // The dropDesc is in an activated layer of the current diagram or it is reused by an activated
                    // layer of the current diagram.
                    result = true;
                }
            }
        } else {
            // If the tool is directly contained in the diagram description, it is considered as active.
            result = true;
        }
        return result;
    }

    private static Optional<Layer> getLayer(ContainerDropDescription containerDropDescription) {
        EObject current = containerDropDescription;
        while (current != null) {
            if (current instanceof Layer) {
                return Optional.of((Layer) current);
            }
            current = current.eContainer();
        }
        return Optional.empty();
    }
}
