/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.refresh.listeners;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.metamodel.helper.BestStyleDescriptionRegistry;
import org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Computed {@link StyleDescription} cache cleaner of a {@link DDiagram}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ComputedStyleDescriptionCacheCleaner extends ResourceSetListenerImpl {

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain}
     * @param dDiagram
     *            the {@link DDiagram} for which to update the computed
     *            {@link StyleDescription} cache
     */
    public ComputedStyleDescriptionCacheCleaner(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super();
        domain.addResourceSetListener(this);
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        Command command = null;
        Set<StyleDescription> orphanedComputedStyleDescriptions = getOrphanedComputedStyleDescriptions(event.getNotifications());
        if (!orphanedComputedStyleDescriptions.isEmpty()) {
            CompoundCommand compoundCommand = new CompoundCommand();
            for (StyleDescription orphanedComputedStyleDescription : orphanedComputedStyleDescriptions) {
                Command removeOrphanedComputedStyleDescriptionCmd = RemoveCommand.create(getTarget(), orphanedComputedStyleDescription.eContainer(),
                        ViewpointPackage.Literals.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS, orphanedComputedStyleDescription);
                compoundCommand.append(removeOrphanedComputedStyleDescriptionCmd);
            }
            ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = BestStyleDescriptionRegistry.getComputedStyleDescriptionRegistry(dDiagram, false);
            if (computedStyleDescriptionRegistry != null) {
                addCmdsToCleanMaps(computedStyleDescriptionRegistry, orphanedComputedStyleDescriptions, compoundCommand);
            }
            if (!compoundCommand.isEmpty()) {
                command = compoundCommand;
            }
        }
        return command;
    }

    private void addCmdsToCleanMaps(ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry, Collection<StyleDescription> orphanedComputedStyleDescriptions, CompoundCommand compoundCommand) {
        for (Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> diagramElementMapping2ModelElementMapEntry : computedStyleDescriptionRegistry.getCache()
                .entrySet()) {
            EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> modelElement2ViewVariableMap = diagramElementMapping2ModelElementMapEntry.getValue();
            int nbModelElement2ViewVariableMapEntriesToRemove = 0;
            for (Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> modelElement2ViewVariableMapEntry : modelElement2ViewVariableMap.entrySet()) {
                EMap<EObject, EMap<EObject, StyleDescription>> viewVariable2ContainerVariableMap = modelElement2ViewVariableMapEntry.getValue();
                int nbViewVariable2ContainerVariableMapEntriesToRemove = 0;
                for (Map.Entry<EObject, EMap<EObject, StyleDescription>> viewVariable2ContainerVariableMapEntry : viewVariable2ContainerVariableMap.entrySet()) {
                    EMap<EObject, StyleDescription> containerVariable2StyleDescriptionMap = viewVariable2ContainerVariableMapEntry.getValue();
                    int nbContainerVariable2StyleDescriptionMapEntriesToRemove = 0;
                    for (Map.Entry<EObject, StyleDescription> containerVariable2StyleDescriptionMapEntry : containerVariable2StyleDescriptionMap.entrySet()) {
                        StyleDescription value = containerVariable2StyleDescriptionMapEntry.getValue();
                        if (value == null || orphanedComputedStyleDescriptions.contains(value)) {
                            Command removeMapEntryCmd = new RemoveMapEntryCommand(getTarget(), containerVariable2StyleDescriptionMap, containerVariable2StyleDescriptionMapEntry);
                            compoundCommand.append(removeMapEntryCmd);
                            nbContainerVariable2StyleDescriptionMapEntriesToRemove++;
                        }
                    }
                    if (containerVariable2StyleDescriptionMap.size() == nbContainerVariable2StyleDescriptionMapEntriesToRemove) {
                        Command removeMapEntryCmd = new RemoveMapEntryCommand(getTarget(), viewVariable2ContainerVariableMap, viewVariable2ContainerVariableMapEntry);
                        compoundCommand.append(removeMapEntryCmd);
                        nbViewVariable2ContainerVariableMapEntriesToRemove++;
                    }
                }
                if (viewVariable2ContainerVariableMap.size() == nbViewVariable2ContainerVariableMapEntriesToRemove) {
                    Command removeMapEntryCmd = new RemoveMapEntryCommand(getTarget(), modelElement2ViewVariableMap, modelElement2ViewVariableMapEntry);
                    compoundCommand.append(removeMapEntryCmd);
                    nbModelElement2ViewVariableMapEntriesToRemove++;
                }
            }
            if (modelElement2ViewVariableMap.size() == nbModelElement2ViewVariableMapEntriesToRemove) {
                Command removeMapEntryCmd = new RemoveMapEntryCommand(getTarget(), computedStyleDescriptionRegistry.getCache(), diagramElementMapping2ModelElementMapEntry);
                compoundCommand.append(removeMapEntryCmd);
            }
        }
    }

    private Set<StyleDescription> getOrphanedComputedStyleDescriptions(List<Notification> notifications) {
        Set<StyleDescription> orphanedComputedStyleDescriptions = new HashSet<StyleDescription>();
        for (Notification notification : notifications) {
            Style removedStyle = null;
            if (notification.getEventType() == Notification.REMOVE && notification.getOldValue() instanceof DDiagramElement) {
                DDiagramElement removedDDiagramElement = (DDiagramElement) notification.getOldValue();
                removedStyle = removedDDiagramElement.getStyle();
            } else if (notification.getEventType() == Notification.SET && notification.getOldValue() instanceof Style) {
                removedStyle = (Style) notification.getOldValue();
            }
            if (removedStyle != null && removedStyle.getDescription() != null) {
                StyleDescription styleDescription = removedStyle.getDescription();
                if (styleDescription.eContainingFeature() == ViewpointPackage.Literals.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS && concernCurrentDDiagram(notification)) {
                    orphanedComputedStyleDescriptions.add(removedStyle.getDescription());
                }
            }
        }
        return orphanedComputedStyleDescriptions;
    }

    private boolean concernCurrentDDiagram(Notification notification) {
        boolean concernCurrentDDiagram = notification.getNotifier() instanceof EObject && dDiagram == new EObjectQuery((EObject) notification.getNotifier()).getParentDiagram().get();
        return concernCurrentDDiagram;
    }

    /**
     * A command to remove an entry of a map.
     * 
     * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban
     *         Dugueperoux</a>
     */
    final class RemoveMapEntryCommand extends RecordingCommand {

        private EMap<?, ?> map;

        private Map.Entry<?, ?> entry;

        public RemoveMapEntryCommand(TransactionalEditingDomain domain, EMap<?, ?> map, Map.Entry<?, ?> entry) {
            super(domain);
            this.map = map;
            this.entry = entry;
        }

        @Override
        protected void doExecute() {
            map.remove(entry);
        }

        @Override
        public void dispose() {
            map = null;
            entry = null;
            super.dispose();
        }

    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dDiagram = null;
    }
}
