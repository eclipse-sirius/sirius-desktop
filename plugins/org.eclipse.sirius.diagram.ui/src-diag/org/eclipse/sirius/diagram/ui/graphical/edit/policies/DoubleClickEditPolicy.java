/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.text.MessageFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractGeneratedDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.FilterUpdateTask;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.swt.widgets.Display;

/**
 * An edit policy which launch an action after double-clicking an edit part.
 * 
 * @author smonnier
 * 
 */
public class DoubleClickEditPolicy extends OpenEditPolicy {

    @Override
    protected Command getOpenCommand(Request request) {
        Command result = null;
        EditPart editPart = this.getHost();
        Object model = editPart.getModel();
        if (model instanceof View) {
            EObject element = ((View) model).getElement();
            if (element instanceof DDiagramElement) {
                final DDiagramElement ddiagramElement = (DDiagramElement) element;
                DiagramElementMapping diagramElementMapping = ddiagramElement.getDiagramElementMapping();
                DDiagram parentDiagram = ddiagramElement.getParentDiagram();
                if (parentDiagram.isIsInShowingMode()) {
                    org.eclipse.emf.common.command.Command cmd = null;
                    final IDiagramCommandFactory emfCommandFactory = getCommandFactory(editPart);
                    EditPart targetEditPart = this.getTargetEditPart(request);
                    if (targetEditPart instanceof AbstractGeneratedDiagramNameEditPart && !(targetEditPart instanceof DNodeListElementEditPart)) {
                        // label case
                        DDiagramElementQuery query = new DDiagramElementQuery(ddiagramElement);
                        // CHECKSTYLE:OFF
                        if (query.canHideLabel()) {
                            if (query.isLabelHidden() || !ddiagramElement.isVisible()) {
                                cmd = revealElement(parentDiagram, ddiagramElement, emfCommandFactory, true);
                            } else {
                                cmd = hideElement(ddiagramElement, emfCommandFactory, true);
                            }
                        }
                        // CHECKSTYLE:ON
                    } else {
                        if (!ddiagramElement.isVisible()) {
                            cmd = revealElement(parentDiagram, ddiagramElement, emfCommandFactory, false);
                        } else {
                            cmd = hideElement(ddiagramElement, emfCommandFactory, false);
                        }
                    }
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    result = new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
                } else if (diagramElementMapping.getDoubleClickDescription() != null) {
                    final IDiagramCommandFactory emfCommandFactory = getCommandFactory(editPart);
                    final org.eclipse.emf.common.command.Command cmd = emfCommandFactory.buildDoubleClickOnElementCommandFromTool(ddiagramElement, diagramElementMapping.getDoubleClickDescription());
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    result = new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
                }
            }
        }
        return result;
    }

    /**
     * Hides the given {@link DDiagramElement}.
     * 
     * @param ddiagramElement
     *            element to reveal.
     * @param emfCommandFactory
     *            factory for command creation.
     * @param hideLabel
     *            true if we are hiding a label. False for any other element.
     * @return the command doing the hiding.
     */
    private org.eclipse.emf.common.command.Command hideElement(final DDiagramElement ddiagramElement, final IDiagramCommandFactory emfCommandFactory, boolean hideLabel) {
        org.eclipse.emf.common.command.Command cmd;
        Set<EObject> elementSet = new HashSet<>();
        elementSet.add(ddiagramElement);
        if (hideLabel) {
            cmd = emfCommandFactory.buildHideLabelCommand(elementSet);
        } else {
            cmd = emfCommandFactory.buildHideCommand(elementSet);
        }
        return cmd;
    }

    /**
     * Reveals the given {@link DDiagramElement} and all its parents recursively. If the element in an edge, also reveal
     * the source and target and their parent recursively.
     * 
     * @param ddiagramElement
     *            element to reveal.
     * @param emfCommandFactory
     *            factory for command creation.
     * @param hideLabel
     *            true if we are hiding a label. False for any other element.
     * @return the command doing the revelation.
     */
    private org.eclipse.emf.common.command.Command revealElement(DDiagram parentDiagram, final DDiagramElement ddiagramElement, final IDiagramCommandFactory emfCommandFactory, boolean hideLabel) {
        CompoundCommand compoundCommand = new CompoundCommand();
        Set<DDiagramElement> elementSet = new HashSet<>();
        elementSet.add(ddiagramElement);
        if (ddiagramElement instanceof DEdge) {
            // we show source and target node as well as the edge.
            DEdge edge = (DEdge) ddiagramElement;
            EdgeTarget sourceNode = edge.getSourceNode();
            EdgeTarget targetNode = edge.getTargetNode();
            if (sourceNode instanceof DDiagramElement && targetNode instanceof DDiagramElement) {
                elementSet.add((DDiagramElement) sourceNode);
                elementSet.add((DDiagramElement) targetNode);
                addAllParentRecursively(elementSet, (DDiagramElement) sourceNode);
                addAllParentRecursively(elementSet, (DDiagramElement) targetNode);
            }
        } else {
            addAllParentRecursively(elementSet, ddiagramElement);
        }
        org.eclipse.emf.common.command.Command cmd;
        if (hideLabel) {
            compoundCommand.append(emfCommandFactory.buildRevealLabelCommand(ddiagramElement));
            compoundCommand.append(emfCommandFactory.buildRevealElementsCommand(elementSet));
        } else {
            Entry<String, org.eclipse.emf.common.command.Command> filterEntry = getCommandToDeactivateFiltersHidingElementAndParents(parentDiagram, elementSet);
            Entry<String, org.eclipse.emf.common.command.Command> layerEntry = getCommandToActivateLayerShowingElementAndPArents(parentDiagram, elementSet);

            if (filterEntry != null || layerEntry != null) {
                boolean confirm = prepareLayerActivationAndFilterDeactivationCommands(compoundCommand, filterEntry, layerEntry);
                if (confirm) {
                    compoundCommand.append(emfCommandFactory.buildRevealElementsCommand(elementSet));
                }
            } else {
                compoundCommand.append(emfCommandFactory.buildRevealElementsCommand(elementSet));
            }

        }
        return compoundCommand;

    }

    /**
     * Asks user if element must be made visible by activating needed layers and deactivating needed filters and if yes,
     * add required command in the given compound command.
     * 
     * @param compoundCommand
     *            the command where to put layer and filter commands if user confirms.
     * @param filterEntry
     *            filters needed to be deactivated and their names.
     * @param layerEntry
     *            layers needed to be activated and their names.
     */
    private boolean prepareLayerActivationAndFilterDeactivationCommands(CompoundCommand compoundCommand, Entry<String, org.eclipse.emf.common.command.Command> filterEntry,
            Entry<String, org.eclipse.emf.common.command.Command> layerEntry) {
        String message = ""; //$NON-NLS-1$
        if (filterEntry != null) {
            message += MessageFormat.format(Messages.DoubleClickEditPolicy_filterConfirmDialogBody, filterEntry.getKey());
        }
        if (layerEntry != null) {
            if (filterEntry != null) {
                message += "\n"; //$NON-NLS-1$
            }
            message += MessageFormat.format(Messages.DoubleClickEditPolicy_layerConfirmDialogBody, layerEntry.getKey());

        }
        message += "\n" + Messages.DoubleClickEditPolicy_confirmDialogAsking; //$NON-NLS-1$
        boolean confirm = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), Messages.DoubleClickEditPolicy_confirmDialogTitle, message);
        if (confirm) {
            if (layerEntry != null) {
                compoundCommand.append(layerEntry.getValue());
            }
            if (filterEntry != null) {
                compoundCommand.append(filterEntry.getValue());
            }

        }
        return confirm;
    }

    /**
     * Return the command activating layers that are needed to show all elements in the given set and the name of the
     * layer to activate.
     * 
     * @param parentDiagram
     *            the parent diagram.
     * @param elementSet
     *            the element to show as well as all its parents.
     * @return the command activating layers that are needed to show all element in the given set and the name of the
     *         layers to activate.
     */
    private Entry<String, org.eclipse.emf.common.command.Command> getCommandToActivateLayerShowingElementAndPArents(DDiagram parentDiagram, final Set<DDiagramElement> elementSet) {
        CompoundCommand layerCompoundCommand = new CompoundCommand();
        Optional<Session> optionalSession = Session.of(parentDiagram);
        Set<String> layersToActivateSet = new LinkedHashSet<>();
        if (optionalSession.isPresent()) {
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(optionalSession.get(), parentDiagram);
            for (DDiagramElement dDiagramElement : elementSet) {
                Collection<Layer> layersToActivate = getLayerToActivate(parentDiagram, dDiagramElement, mappingManager);
                for (Layer layerToActivate : layersToActivate) {
                    if (layerToActivate != null) {
                        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(parentDiagram);
                        if (!layersToActivateSet.contains(layerToActivate.getName())) {
                            layerCompoundCommand.append(new ChangeLayerActivationCommand(domain, parentDiagram, layerToActivate, new NullProgressMonitor()));
                            layersToActivateSet.add(layerToActivate.getName());
                        }
                    }
                }
            }
        }
        return layerCompoundCommand.getCommandList().size() > 0
                ? new SimpleEntry<String, org.eclipse.emf.common.command.Command>(layersToActivateSet.stream().collect(Collectors.joining(", ")), layerCompoundCommand) //$NON-NLS-1$
                : null;
    }

    /**
     * Return the command deactivating filters to show all elements in the given set and the name of the filters to
     * deactivate.
     * 
     * @param parentDiagram
     *            the parent diagram.
     * @param elementSet
     *            the element to show as well as all its parents.
     * @return the command deactivating filters to show all elements in the given set and the name of the filters to
     *         deactivate.
     */
    private Entry<String, org.eclipse.emf.common.command.Command> getCommandToDeactivateFiltersHidingElementAndParents(DDiagram parentDiagram, Set<DDiagramElement> elementSet) {
        Set<GraphicalFilter> graphicalFilters = elementSet.stream().flatMap(element -> element.getGraphicalFilters().stream()).filter(elmt -> elmt instanceof AppliedCompositeFilters)
                .collect(Collectors.toSet());
        DCommand command = null;
        String filterStrings = null;
        if (graphicalFilters.size() > 0) {
            filterStrings = graphicalFilters.stream().flatMap(elmt -> ((AppliedCompositeFilters) elmt).getCompositeFilterDescriptions().stream()).map(elt -> elt.getName())
                    .collect(Collectors.joining(", ")); //$NON-NLS-1$
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(parentDiagram);
            command = new SiriusCommand(domain, null);
            for (GraphicalFilter graphicalFilter : graphicalFilters) {
                EList<CompositeFilterDescription> compositeFilterDescriptions = ((AppliedCompositeFilters) graphicalFilter).getCompositeFilterDescriptions();
                for (CompositeFilterDescription compositeFilterDescription : compositeFilterDescriptions) {
                    command.getTasks().add(new FilterUpdateTask(parentDiagram, compositeFilterDescription, false));
                }
            }
            command.setLabel(MessageFormat.format(Messages.ChangeFilterActivation_deactivateFilter, filterStrings));
        }
        return command != null ? new SimpleEntry<String, org.eclipse.emf.common.command.Command>(filterStrings, command) : null;
    }

    private Collection<Layer> getLayerToActivate(DDiagram parentDiagram, final DDiagramElement ddiagramElement, DiagramMappingsManager mappingManager) {
        Collection<Layer> layers = mappingManager.getActiveParentLayers(ddiagramElement.getDiagramElementMapping());
        List<Layer> allLayers = LayerHelper.getAllLayers(parentDiagram.getDescription());
        Set<Layer> deactivatedLayers = allLayers.stream().filter(elmt -> !layers.contains(elmt)).collect(Collectors.toSet());

        Collection<Layer> diagramElementLayers = LayerHelper.getParentLayers(ddiagramElement.getDiagramElementMapping());
        Collection<Layer> keptElementLayers = new LinkedHashSet<>();
        for (Layer deactivatedLayer : deactivatedLayers) {
            for (Layer diagramElementLayer : diagramElementLayers) {
                if (EqualityHelper.areEquals(deactivatedLayer, diagramElementLayer)) {
                    keptElementLayers.add(deactivatedLayer);
                }
            }
        }
        return keptElementLayers;
    }

    /**
     * Add all parents recursively of the given {@link DDiagramElement} in the given set.
     * 
     * @param elementSet
     *            the set where to add parents.
     * @param ddiagramElement
     *            the element from which parents will be added.
     */
    private void addAllParentRecursively(Set<DDiagramElement> elementSet, DDiagramElement ddiagramElement) {
        EObject eContainer = ddiagramElement.eContainer();
        if (eContainer instanceof DDiagramElement) {
            elementSet.add((DDiagramElement) eContainer);
            addAllParentRecursively(elementSet, (DDiagramElement) eContainer);
        }
    }

    private IDiagramCommandFactory getCommandFactory(EditPart editPart) {
        final TransactionalEditingDomain transactionalEditingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        final DDiagramEditor diagramEditor = (DDiagramEditor) editPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        return emfCommandFactory;
    }

}
