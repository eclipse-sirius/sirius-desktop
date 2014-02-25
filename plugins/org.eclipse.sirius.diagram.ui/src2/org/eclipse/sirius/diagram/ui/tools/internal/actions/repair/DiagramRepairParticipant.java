/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.repair.IRepairParticipant;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.migration.resource.session.commands.MigrationCommandExecutor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.ui.business.api.view.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.repair.commands.RemoveInvalidViewsCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;

/**
 * General diagram repair participant.
 * 
 * @author fbarbin
 * 
 */
public class DiagramRepairParticipant implements IRepairParticipant {

    /** Saves the state of the model's SiriusElements. */
    private final Map<Identifier, IDiagramElementState<DDiagramElement>> elementStatesMap = Maps.newHashMap();

    private DiagramElementStateFactory diagramElementStateFactory;

    private List<GMFDiagramUpdater> gmfDiagramUpdaters = new ArrayList<GMFDiagramUpdater>();

    private TransactionalEditingDomain editingDomain;

    /**
     * {@inheritDoc}
     */
    public void repairStarted() {
        diagramElementStateFactory = DiagramElementStateFactory.newInstance();
    }

    /**
     * {@inheritDoc}
     */
    public void repairCompeleted() {
        Collection<IDiagramElementState<DDiagramElement>> values = elementStatesMap.values();

        for (IDiagramElementState<?> diagramElementState : values) {
            diagramElementState.dispose();
        }
        diagramElementStateFactory.dispose();

        elementStatesMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void restoreModelElementState(DView view, IProgressMonitor monitor) {
        monitor.subTask("Restoring model state");
        // Creates a new CrossReferencer before restoring the model state (This
        // cross referencer will return all the references named "element" on
        // GMF Nodes)
        DiagramCrossReferencer crossReferencer = new DiagramCrossReferencer(view.eResource());
        UnmodifiableIterator<DDiagramElement> analysisIterator = Iterators.filter(view.eAllContents(), DDiagramElement.class);

        Map<IDiagramElementState<DDiagramElement>, DDiagramElement> states = new LinkedHashMap<IDiagramElementState<DDiagramElement>, DDiagramElement>();
        while (analysisIterator.hasNext()) {
            final DDiagramElement diagramElement = analysisIterator.next();

            final IDiagramElementState<DDiagramElement> state = getElementState(diagramElement);

            if (state == null) {
                // SiriusPlugin.getDefault().warning("No state for " +
                // diagramElement, null);
            } else {
                states.put(state, diagramElement);
            }
            monitor.worked(1);
        }

        for (Entry<IDiagramElementState<DDiagramElement>, DDiagramElement> entry : states.entrySet()) {
            IDiagramElementState<DDiagramElement> state = entry.getKey();
            DDiagramElement dDiagramElement = entry.getValue();
            state.restoreElementState(dDiagramElement);
        }

        this.setDefaultConcern(view);
        if (crossReferencer != null) {
            crossReferencer.clear();
            crossReferencer.clean();
        }
        if (editingDomain != null) {
            refreshVisibility(view);
        }
        // Synchronize the GMF views to remove orphan and create new after
        // migration
        List<Diagram> diagrams = Lists.newArrayList();
        Iterators.addAll(diagrams, Iterators.filter(view.eResource().getAllContents(), Diagram.class));
        for (Diagram currentDiagram : diagrams) {
            if (ViewUtil.resolveSemanticElement(currentDiagram) != null) {
                CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(currentDiagram);
                canonicalSynchronizer.storeViewsToArrange(false);
                canonicalSynchronizer.synchronize();
            }
        }

    }

    /**
     * @param view
     */
    private void refreshVisibility(DView view) {
        for (final DRepresentation representation : view.getAllRepresentations()) {
            if (representation instanceof DDiagram) {

                Command refreshAllElementsVisibilityCommand = new IdentityCommand() {
                    @Override
                    public void execute() {
                        DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) representation);
                    }
                };
                new MigrationCommandExecutor().execute(editingDomain, refreshAllElementsVisibilityCommand);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void postRefreshOperations(TransactionalEditingDomain domain, Resource resource) {

        this.editingDomain = domain;
        Command removeInvalidViewsCommand = new RemoveInvalidViewsCommand(resource);
        new MigrationCommandExecutor().execute(domain, removeInvalidViewsCommand);

    }

    /**
     * {@inheritDoc}
     */
    public void saveModelElementState(DView view, IProgressMonitor monitor) {
        monitor.subTask("Saving model state");

        // Creates a new CrossReferencer before saving the model state (This
        // cross referencer will return all the references named "element" on
        // GMF Nodes)
        DiagramCrossReferencer crossReferencer = new DiagramCrossReferencer(view.eResource());

        // Work only with DDiagramElement
        final UnmodifiableIterator<DDiagramElement> analysisIterator = Iterators.filter(view.eAllContents(), DDiagramElement.class);
        final List<EObject> elementsToRemove = Lists.newArrayList();

        while (analysisIterator.hasNext()) {
            final DDiagramElement diagramElement = analysisIterator.next();

            if (diagramElementStateFactory.isValid(diagramElement)) {
                Identifier identifier = Identifier.createIdentifier(diagramElement);

                // Element is duplicated, remove it
                if (elementStatesMap.containsKey(identifier)) {
                    elementsToRemove.add(diagramElement);
                } else {
                    final IDiagramElementState<DDiagramElement> elementState = (IDiagramElementState<DDiagramElement>) diagramElementStateFactory.buildDiagramElementState(identifier, diagramElement,
                            crossReferencer);

                    elementStatesMap.put(elementState.getIdentifier(), elementState);
                }
            } else {
                // Element is not valid, remove it
                elementsToRemove.add(diagramElement);
            }

            monitor.worked(1);
        }

        for (EObject eObject : elementsToRemove) {
            EcoreUtil.remove(eObject);

        }
        if (crossReferencer != null) {
            crossReferencer.clear();
            crossReferencer.clean();
        }

    }

    private void setDefaultConcern(final DView view) {
        for (final DRepresentation representation : view.getAllRepresentations()) {
            if (representation instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) representation;
                final DiagramDescription description = diagram.getDescription();
                if (description != null && description.getDefaultConcern() != null && diagram.getCurrentConcern() != null && diagram.getCurrentConcern().equals(description.getDefaultConcern())) {
                    // Clean the context if the current context doesn't exist
                    // any more.
                    if (diagram.getCurrentConcern().eResource() == null || !diagram.getCurrentConcern().eResource().isLoaded()) {
                        ConcernService.setCurrentConcern(diagram, description.getDefaultConcern());
                    }
                }
            }
        }
    }

    /**
     * Returns the state of the DDiagramElement.
     * 
     * @param element
     *            the diagram element.
     * @return The state of the DDiagramElement or <code>null</code> if none can
     *         be found.
     */
    private IDiagramElementState<DDiagramElement> getElementState(final DDiagramElement element) {
        IDiagramElementState<DDiagramElement> result = null;

        if (diagramElementStateFactory.isValid(element)) {
            Identifier identifier = Identifier.createIdentifier(element);
            result = elementStatesMap.get(identifier);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void startRepairOnView(Session session, DView view) {

        for (DRepresentation representation : view.getAllRepresentations()) {
            if (representation instanceof DDiagram) {
                GMFDiagramUpdater updater = new GMFDiagramUpdater(session, (DDiagram) representation);
                gmfDiagramUpdaters.add(updater);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public void endRepairOnView() {
        for (GMFDiagramUpdater gmfDiagramUpdater : gmfDiagramUpdaters) {
            gmfDiagramUpdater.dispose();
        }
        gmfDiagramUpdaters.clear();

    }

}
