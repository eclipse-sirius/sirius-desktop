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
package org.eclipse.sirius.diagram.business.internal.repair.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.migration.resource.session.commands.MigrationCommandExecutor;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.repair.commands.RefreshAllElementsVisibilityCommand;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostEdgeData;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostElementDataState;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostElementDataWithMapping;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostNodeData;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;

/**
 * Provides refresh representation action in repair context.
 * 
 * @author fbarbin
 * 
 */
public class RepairRepresentationRefresher {

    private ListMultimap<DiagramKey, LostNodeData> lostNodesByDelete;

    private ListMultimap<DiagramKey, LostEdgeData> lostEdgesByDelete;

    /**
     * Instantiate RepairRepresentationRefresher.
     * 
     * @param lostNodesByDelete
     *            node deleted.
     * @param lostEdgesByDelete
     *            edge deleted.
     */
    public RepairRepresentationRefresher(ListMultimap lostNodesByDelete, ListMultimap lostEdgesByDelete) {
        this.lostNodesByDelete = lostNodesByDelete;
        this.lostEdgesByDelete = lostEdgesByDelete;
    }

    /**
     * refresh representations.
     * 
     * @param dAnalysis
     *            {@link DAnalysis} to refresh.
     * @param view
     *            current {@link DView}
     */
    public void refreshRepresentations(final DAnalysis dAnalysis, final DView view) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dAnalysis);
        boolean hasAlreadyModel = false;
        // The old representations have no a model.
        if (view instanceof DRepresentationContainer) {
            if (!((DAnalysis) view.eContainer()).getModels().isEmpty()) {
                // Set hasAlreadyModel to true only if almost one of the models
                // is not a proxy
                for (EObject model : ((DAnalysis) view.eContainer()).getModels()) {
                    if (!model.eIsProxy()) {
                        hasAlreadyModel = true;
                    }
                }
            } else if (!hasAlreadyModel) {
                final Map<EObject, AnyType> extension = ((XMIResource) dAnalysis.eResource()).getEObjectToExtensionMap();
                final AnyType viewSettings = extension.get(view);
                if (viewSettings != null) {
                    for (final FeatureMap.Entry feature : viewSettings.getMixed()) {
                        if ("model".equals(feature.getEStructuralFeature().getName())) { //$NON-NLS-1$
                            ((DAnalysis) view.eContainer()).getModels().add(EcoreUtil.resolve((EObject) feature.getValue(), view.eResource().getResourceSet()));
                            hasAlreadyModel = true;
                        }
                    }
                }
            }
        }

        // Command prepareImportsFromSessionCommand = new
        // PrepareImportsFromSessionCommand(this, view);
        MigrationCommandExecutor migrationCommandExecutor = new MigrationCommandExecutor();

        Command prepareImportsFromSessionCommand = new IdentityCommand() {
            @Override
            public void execute() {
                // Not seem to be necessary (done by handle view command)
                // initAnalysis(view);
                for (final EObject model : ((DAnalysis) view.eContainer()).getModels()) {
                    if (!model.eIsProxy()) {
                        InterpreterRegistry.prepareImportsFromSession(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(model), SessionManager.INSTANCE.getSession(model));
                    }
                }
                for (DRepresentation dRepresentation : view.getOwnedRepresentations()) {
                    DialectManager.INSTANCE.refresh(dRepresentation, new NullProgressMonitor());
                }
            }

            @Override
            public boolean canUndo() {
                return false;
            }
        };

        migrationCommandExecutor.execute(transactionalEditingDomain, prepareImportsFromSessionCommand);

        // This method calls a RecordingCommand
        refreshLostDiagramElements(view);

        refreshAllElementsVisibility(view, transactionalEditingDomain, migrationCommandExecutor);

        // if (!hasAlreadyModel) {
        // deinformModel((DRepresentationContainer) view);
        // }
        // Recompute the models references after the refresh.
        // Command cleanModelsCommand = new CleanModelsCommand(this, dAnalysis,
        // true);
        // migrationCommandExecutor.execute(transactionalEditingDomain,
        // cleanModelsCommand);
    }

    /**
     * Refresh the visibility of all dview diagrams elements.
     * 
     * @param view
     *            the view containing diagrams to refresh elements.
     * @param transactionalEditingDomain
     *            the editing domain.
     * @param migrationCommandExecutor
     *            the command executor to execute the
     *            {@link RefreshAllElementsVisibilityCommand}
     */
    public void refreshAllElementsVisibility(final DView view, TransactionalEditingDomain transactionalEditingDomain, MigrationCommandExecutor migrationCommandExecutor) {
        for (final DRepresentation representation : view.getOwnedRepresentations()) {
            if (representation instanceof DDiagram) {
                Command refreshAllElementsVisibilityCommand = new RefreshAllElementsVisibilityCommand((DDiagram) representation);
                migrationCommandExecutor.execute(transactionalEditingDomain, refreshAllElementsVisibilityCommand);
            }
        }
    }

    private void refreshLostDiagramElements(final DView view) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_LOST_ELEMENTS_KEY);
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(view);
        Command refreshLostDiagramElementsCommand = new IdentityCommand() {
            @Override
            public void execute() {
                for (final DSemanticDiagram diagram : Iterables.filter(view.getOwnedRepresentations(), DSemanticDiagram.class)) {
                    final DiagramKey diagramKey = DiagramKey.createDiagramKey(diagram);

                    final List<LostNodeData> lostNodes = lostNodesByDelete.get(diagramKey);
                    final List<LostEdgeData> lostEdges = lostEdgesByDelete.get(diagramKey);
                    final Iterable<LostElementDataWithMapping> allLostElements = Iterables.concat(lostNodes, lostEdges);

                    refreshLostElementsThenDiagram(diagram, allLostElements);
                }
            }

            @Override
            public boolean canUndo() {
                return false;
            }
        };
        new MigrationCommandExecutor().execute(transactionalEditingDomain, refreshLostDiagramElementsCommand);

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_LOST_ELEMENTS_KEY);

        // displayWarningForRemainingLostElementData();
    }

    /**
     * Refresh LostElements.
     * 
     * @param designerDiagram
     *            {@link DSemanticDiagram}
     * @param lostElements
     *            lostElements
     */
    private void refreshLostElementsThenDiagram(final DSemanticDiagram designerDiagram, final Iterable<? extends LostElementDataWithMapping> lostElements) {
        final boolean needRefresh = refreshLostElements(designerDiagram, lostElements);
        if (needRefresh) {
            final boolean doContinue = refreshDiagramAndCheckIfNumberOfElementsHasChanged(designerDiagram);

            // More elements could be created. Try again.
            if (doContinue) {
                refreshLostElementsThenDiagram(designerDiagram, lostElements);
            }
        }
    }

    private boolean refreshLostElements(final DSemanticDiagram designerDiagram, final Iterable<? extends LostElementDataWithMapping> lostElements) {
        boolean needRefresh = false;

        final Iterator<? extends LostElementDataWithMapping> iterator = lostElements.iterator();

        while (iterator.hasNext()) {
            final LostElementDataWithMapping data = iterator.next();
            final LostElementDataState created = data.reCreateLostElement(designerDiagram);

            switch (created) {
            case NOT_CREATED:
                // Something was wrong, do nothing
                break;
            case EXISTING:
                // Diagram element already exists, just remove it from
                // candidates
                iterator.remove();
                break;
            case CREATED:
            default:
                // Diagram element has been created and add in diagram, remove
                // it from candidates and ask for a diagram refresh.
                iterator.remove();
                needRefresh = true;
                break;
            }
        }
        return needRefresh;
    }

    /**
     * @return boolean <code>true</code> indicates that diagram has changed
     *         after refresh. <code>false</code> otherwise.
     */
    private boolean refreshDiagramAndCheckIfNumberOfElementsHasChanged(final DSemanticDiagram designerDiagram) {
        boolean doContinue;

        final int sizeBeforeRefresh = designerDiagram.getDiagramElements().size();
        designerDiagram.refresh();
        final int sizeAfterRefresh = designerDiagram.getDiagramElements().size();

        doContinue = sizeAfterRefresh > sizeBeforeRefresh;
        return doContinue;
    }

}
