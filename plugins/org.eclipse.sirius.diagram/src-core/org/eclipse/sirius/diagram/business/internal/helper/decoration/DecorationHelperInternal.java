/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.GenericDecorationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A Helper for diagram decoration.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class DecorationHelperInternal {

    /**
     * The interpreter.
     */
    private IInterpreter interpreter;

    /**
     * The diagram.
     */
    private DDiagram diagram;

    /**
     * The model accessor.
     */
    private ModelAccessor accessor;

    /**
     * Create a new DecorationHelper for the given diagram.
     * 
     * @param diagram
     *            diagram to synchronize.
     * @param interpreter
     *            current interpreter.
     * @param accessor
     *            model accessor
     */
    public DecorationHelperInternal(DDiagram diagram, IInterpreter interpreter, ModelAccessor accessor) {
        this.diagram = diagram;
        this.interpreter = interpreter;
        this.accessor = accessor;
    }

    /**
     * Create a new DecorationHelper for the given diagram.
     * 
     * @param diagram
     *            diagram to synchronize.
     */
    public DecorationHelperInternal(DDiagram diagram) {
        this.diagram = diagram;
        this.interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(diagram);
        this.accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(diagram);
    }

    /**
     * Add a decoration to a DdiagramElement.
     * 
     * @param element
     *            element to decorate
     * @param decorationDescription
     *            description of the decoration
     */
    public void addDecoration(final DDiagramElement element, final DecorationDescription decorationDescription) {
        for (final Decoration decoration : element.getDecorations()) {
            if (EcoreUtil.equals(decorationDescription, decoration.getDescription())) {
                return;
            }
        }
        for (final Decoration decoration : element.getTransientDecorations()) {
            if (EcoreUtil.equals(decorationDescription, decoration.getDescription())) {
                return;
            }
        }
        // new decoration
        if (checkDecoratorPrecondition(element.getTarget(), element, decorationDescription)) {
            final Decoration decoration = ViewpointFactory.eINSTANCE.createDecoration();
            decoration.setDescription(decorationDescription);
            if (LayerModelHelper.isTransientLayer((Layer) decorationDescription.eContainer().eContainer())) {
                element.getTransientDecorations().add(decoration);
            } else {
                element.getDecorations().add(decoration);
            }
        }
    }

    /**
     * Check that the {@link DecorationDescription} conditions (and not preconditions) are valid for the
     * {@link DDiagramElement}.
     * 
     * @param element
     *            the {@link DDiagramElement} that is investigated for decoration update.
     * @param decorationDescription
     *            the {@link DecorationDescription} to be applied or not on the {@link DDiagramElement}.
     * @return if this {@link DecorationDescription} should be applied on the given {@link DDiagramElement}.
     */
    private boolean checkDecoratorCondition(final DDiagramElement element, DecorationDescription decorationDescription) {
        // True if it is a GenericDecorationDescription
        Boolean result = decorationDescription instanceof GenericDecorationDescription;
        // True if it is a MappingBasedDecoration define on the same mapping as
        // the DDiagramElement
        result = result || decorationDescription instanceof MappingBasedDecoration && ((MappingBasedDecoration) decorationDescription).getMappings().contains(element.getDiagramElementMapping());
        // True if it is a SemanticBasedDecoration define on the same domain
        // class
        // as the DDiagramElement
        result = result || decorationDescription instanceof SemanticBasedDecoration && accessor.eInstanceOf(element.getTarget(), ((SemanticBasedDecoration) decorationDescription).getDomainClass());
        return result;
    }

    private boolean checkDecoratorPrecondition(final EObject semantic, final DSemanticDecorator view, final DecorationDescription decorationDescription) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        boolean result = false;
        if (decorationDescription != null && !decorationDescription.eIsProxy()) {
            result = true;
            final String preconditionExpression = decorationDescription.getPreconditionExpression();
            if (!StringUtil.isEmpty(preconditionExpression)) {
                DSemanticDecorator container = view != null ? (DSemanticDecorator) view.eContainer() : null;

                this.interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semantic);
                this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, view);
                this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, container);
                this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container != null ? container.getTarget() : null);
                this.interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, this.diagram);
                this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
                try {
                    result = interpreter.evaluateBoolean(semantic, preconditionExpression);
                } catch (final EvaluationException e) {
                    result = false;
                    RuntimeLoggerManager.INSTANCE.error(decorationDescription, org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getDecorationDescription_PreconditionExpression(),
                            e);
                }
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            }
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

    private void computeDecoration(final GenericDecorationDescription decorationDescription, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        for (final Collection<EdgeTarget> collection : mappingsToEdgeTargets.values()) {
            for (final DDiagramElement element : Iterables.filter(collection, DDiagramElement.class)) {
                addDecoration(element, decorationDescription);
            }
        }
    }

    /**
     * Compute the decorations from a {@link MappingBasedDecoration}.
     * 
     * @param decorationDescription
     *            the {@link MappingBasedDecoration}
     * @param mappingsToEdgeTargets
     *            structure to access the {@link DDiagramElement} to decorate
     * @param edgeToMappingBasedDecoration
     *            structure to access the {@link DDiagramElement} to decorate
     */
    private void computeDecoration(final MappingBasedDecoration decorationDescription, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration) {
        for (final DiagramElementMapping mapping : decorationDescription.getMappings()) {
            if (mapping instanceof AbstractNodeMapping) {
                final Collection<EdgeTarget> targets = mappingsToEdgeTargets.get(mapping);
                if (targets != null) {
                    for (final DDiagramElement element : Iterables.filter(targets, DDiagramElement.class)) {
                        addDecoration(element, decorationDescription);
                    }
                }
            } else if (mapping instanceof EdgeMapping) {
                if (!edgeToMappingBasedDecoration.containsKey(mapping)) {
                    edgeToMappingBasedDecoration.put((EdgeMapping) mapping, new HashSet<MappingBasedDecoration>());
                }
                edgeToMappingBasedDecoration.get(mapping).add(decorationDescription);
            }
        }

    }

    private void computeDecoration(final SemanticBasedDecoration decorationDescription, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {
        final String domainClass = decorationDescription.getDomainClass();
        for (final Collection<EdgeTarget> collection : mappingsToEdgeTargets.values()) {
            for (final DDiagramElement element : Iterables.filter(collection, DDiagramElement.class)) {
                if (accessor.eInstanceOf(element.getTarget(), decorationDescription.getDomainClass())) {
                    addDecoration(element, decorationDescription);
                }
            }
        }
        if (!edgeToSemanticBasedDecoration.containsKey(domainClass)) {
            edgeToSemanticBasedDecoration.put(domainClass, new HashSet<SemanticBasedDecoration>());
        }
        edgeToSemanticBasedDecoration.get(domainClass).add(decorationDescription);
    }

    /**
     * compute all decorations.
     * 
     * @param mappingsToEdgeTargets
     *            the mapping to edge targets
     * @param edgeToSemanticBasedDecoration
     *            an empty map
     * @param edgeToMappingBasedDecoration
     *            an empty map
     */
    public void computeDecorations(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration) {
        final List<Layer> activatedLayers = new ArrayList<>();
        activatedLayers.addAll(diagram.getActivatedLayers());
        activatedLayers.addAll(diagram.getActivatedTransientLayers());

        for (final Layer layer : activatedLayers) {
            if (layer.getDecorationDescriptionsSet() != null && layer.getDecorationDescriptionsSet().getDecorationDescriptions().size() > 0) {
                for (final DecorationDescription decorationDescription : layer.getDecorationDescriptionsSet().getDecorationDescriptions()) {
                    if (decorationDescription instanceof MappingBasedDecoration) {
                        computeDecoration((MappingBasedDecoration) decorationDescription, mappingsToEdgeTargets, edgeToMappingBasedDecoration);
                    } else if (decorationDescription instanceof SemanticBasedDecoration) {
                        computeDecoration((SemanticBasedDecoration) decorationDescription, mappingsToEdgeTargets, edgeToSemanticBasedDecoration);
                    } else if (decorationDescription instanceof GenericDecorationDescription) {
                        computeDecoration((GenericDecorationDescription) decorationDescription, mappingsToEdgeTargets);
                    }
                }
            }
        }
    }

    /**
     * Deletes or reset decoration.
     * 
     * @param element
     *            the {@link DDiagramElement}
     * @param decorations
     *            the decorations to process
     * @param activatedLayers
     *            the layers to consider
     */
    public void deleteOrResetDecoration(DDiagramElement element, List<Decoration> decorations, List<? extends Layer> activatedLayers) {
        Iterator<Decoration> it = decorations.iterator();
        while (it.hasNext()) {
            Decoration decoration = it.next();
            final DecorationDescription description = decoration.getDescription();
            if (!activatedLayers.contains(LayerModelHelper.getParentLayer(description)) || !checkDecoratorPrecondition(element.getTarget(), element, description)) {
                it.remove();
            } else {
                // reset image in cache
                DRepresentation parentRepresentation = new DRepresentationElementQuery(element).getParentRepresentation();
                if (parentRepresentation != null) {
                    UIState uiState = parentRepresentation.getUiState();
                    uiState.getDecorationImage().remove(decoration);
                }
            }
        }
    }

    /**
     * Update decorations of the given {@link DDiagramElement}.
     * 
     * @param element
     *            the {@link DDiagramElement} to decorate
     */
    public void updateDecoration(final DDiagramElement element) {
        deleteOrResetDecoration(element, element.getDecorations(), diagram.getActivatedLayers());
        deleteOrResetDecoration(element, element.getTransientDecorations(), diagram.getActivatedTransientLayers());

        List<Layer> layers = Lists.newArrayList(Iterables.concat(diagram.getActivatedLayers(), diagram.getActivatedTransientLayers()));
        for (Layer layer : layers) {
            updateDecorationToAdd(element, layer);
        }
    }

    /**
     * Investigate {@link DecorationDescription} from the given {@link Layer} and call addDecoration with it.
     * 
     * @param element
     *            current {@link DDiagramElement} to decorate
     * @param layer
     *            current {@link Layer} to investigate
     */
    public void updateDecorationToAdd(final DDiagramElement element, Layer layer) {
        if (layer.getDecorationDescriptionsSet() != null) {
            EList<DecorationDescription> decorationDescriptions = layer.getDecorationDescriptionsSet().getDecorationDescriptions();
            for (DecorationDescription decorationDescription : decorationDescriptions) {
                if (checkDecoratorCondition(element, decorationDescription)) {
                    addDecoration(element, decorationDescription);
                }
            }
        }
    }

}
