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
package org.eclipse.sirius.business.api.repair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.resource.session.commands.MigrationCommandExecutor;
import org.eclipse.sirius.business.internal.migration.resource.session.diagram.data.LostEdgeData;
import org.eclipse.sirius.business.internal.migration.resource.session.diagram.data.LostElementFactory;
import org.eclipse.sirius.business.internal.migration.resource.session.diagram.data.LostNodeData;
import org.eclipse.sirius.business.internal.repair.commands.MoveAllRepresentationsFromHiddenToOwnedCommand;
import org.eclipse.sirius.business.internal.repair.commands.RemoveDiagramElementsCommand;
import org.eclipse.sirius.business.internal.repair.commands.RestoreModelElementStateCommand;
import org.eclipse.sirius.business.internal.repair.commands.SaveModelElementStateCommand;
import org.eclipse.sirius.business.internal.repair.resource.DiagramKey;
import org.eclipse.sirius.business.internal.repair.resource.RepairRepresentationRefresher;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.util.ResourceUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.FilterVariableValue;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.validation.ValidationRule;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;

/**
 * Repair process extract from
 * org.eclipse.sirius.ui.RepresentationFilesMigrationProcess.
 * 
 * @author fbarbin
 * 
 */
public class SiriusRepairProcess {

    /**
     * Message use when something goes wrong during repair process.
     */
    public static final String ERROR_MSG = "An error occurs during repair process.";

    /**
     * Quote for messages.
     */
    private static final String QUOTE = "\"";

    private ListMultimap<DiagramKey, LostNodeData> lostNodesByDelete;

    private ListMultimap<DiagramKey, LostEdgeData> lostEdgesByDelete;

    private List<IRepairParticipant> repairParticipants;

    private IFile file;

    private boolean backup;

    /**
     * constructor.
     * 
     * @param file
     *            the main aird file.
     * @param backup
     *            if true, repair will automatically backup aird files before.
     */
    public SiriusRepairProcess(IFile file, boolean backup) {
        repairParticipants = getRepairParticipants();
        this.file = file;
        lostNodesByDelete = ArrayListMultimap.create();
        lostEdgesByDelete = ArrayListMultimap.create();
        this.backup = backup;
    }

    private List<IRepairParticipant> getRepairParticipants() {
        List<IRepairParticipant> participants = new ArrayList<IRepairParticipant>();
        IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.sirius.repairParticipant");
        for (IConfigurationElement configurationElement : config) {
            try {

                Object contribution = configurationElement.createExecutableExtension("class");
                if (contribution instanceof IRepairParticipant) {
                    participants.add((IRepairParticipant) contribution);
                }

            } catch (CoreException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, "Cannot instanciate migration contribution", e));
            }
        }
        return participants;
    }

    /**
     * Launch the repair.
     * 
     * @param monitor
     *            : monitor to track the progress.
     * @throws InterruptedException
     *             if action has been interrupted by user.
     */
    public void run(final IProgressMonitor monitor) throws InterruptedException {
        final URI resourceURI = URI.createPlatformResourceURI(this.file.getFullPath().toOSString(), true);
        try {
            monitor.beginTask("Repair in progress...", 1);
            repair(resourceURI, new SubProgressMonitor(monitor, 1));
        } catch (CoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, ERROR_MSG, e));
        } finally {
            monitor.done();
        }
    }

    /**
     * Launch repair process on main session resource.
     * 
     * @param sessionResourceURI
     *            the URI of the main representations file to repair.
     * @param monitor
     *            the progress monitor to provide process feedback.
     * @throws CoreException
     *             can be throws during session creation or saving.
     * @throws InterruptedException
     *             if user canceled operation.
     */
    protected void repair(final URI sessionResourceURI, IProgressMonitor monitor) throws CoreException, InterruptedException {
        Session session = null;
        // List of couples <Original file, Backup file>
        Map<IFile, IFile> originalToBackupFiles = new HashMap<IFile, IFile>();
        try {
            monitor.beginTask("", 4);
            monitor.subTask("Loading model");
            session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new SubProgressMonitor(monitor, 1));
            session.setReloadingPolicy(new ReloadingPolicy() {
                public List<Action> getActions(Session session, Resource resource, ResourceStatus newStatus) {
                    return Collections.emptyList();
                }

            });
            checkCancel(monitor);
            monitor.subTask("Opening session");
            session.open(new SubProgressMonitor(monitor, 1));
            checkCancel(monitor);

            monitor.subTask("Resolving references");
            ModelUtils.resolveAll(session.getSessionResource());
            workMonitorAndCheckCancel(monitor, 1);

            final List<Resource> fragmentedResources = getFragmentedResources(session.getSessionResource());
            IProgressMonitor monitorForMainModel = new SubProgressMonitor(monitor, fragmentedResources.size() + 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
            try {
                monitorForMainModel.beginTask("Repair model " + QUOTE + session.getSessionResource().getURI().lastSegment() + QUOTE, 10);

                if (backup) {
                    monitorForMainModel.subTask("--> Backup file");
                    originalToBackupFiles.put(this.file, backupFile(this.file, new SubProgressMonitor(monitorForMainModel, 1)));
                }
                checkCancel(monitorForMainModel);

                repairModel(session, session.getSessionResource(), new SubProgressMonitor(monitorForMainModel, 9, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
                for (Resource fragment : fragmentedResources) {
                    IProgressMonitor monitorForFragmentedModel = new SubProgressMonitor(monitorForMainModel, 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
                    try {
                        monitorForFragmentedModel.beginTask("Repair model " + QUOTE + fragment.getURI().lastSegment() + QUOTE, 10);
                        if (backup) {
                            monitorForFragmentedModel.subTask("--> Backup file");
                            IFile fragmentedFile = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(fragment.getURI().toPlatformString(true));
                            IFile fragmentedFileBackup = backupFile(fragmentedFile, new SubProgressMonitor(monitorForFragmentedModel, 1));
                            originalToBackupFiles.put(fragmentedFile, fragmentedFileBackup);
                        }
                        checkCancel(monitorForFragmentedModel);
                        repairModel(session, fragment, new SubProgressMonitor(monitorForFragmentedModel, 9, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
                    } finally {
                        monitorForFragmentedModel.done();
                    }
                }

                // In case the
                // {DesignerUIPreferencesKeys#PREF_SAVE_WHEN_NO_EDITOR}
                // pref is at true, wait the SaveSessionJob before calling
                // session.save() to avoid concurrent save.
                Job.getJobManager().join(SaveSessionJob.FAMILY, new SubProgressMonitor(monitorForMainModel, 1));
                monitorForMainModel.subTask("Saving session");
                session.save(new SubProgressMonitor(monitorForMainModel, 1));

            } finally {
                monitorForMainModel.subTask("Closing session");
                // In case the
                // {DesignerUIPreferencesKeys#PREF_SAVE_WHEN_NO_EDITOR}
                // pref is at true, wait the SaveSessionJob before closing it to
                // avoid NPE on session save because it is already closed
                Job.getJobManager().join(SaveSessionJob.FAMILY, new SubProgressMonitor(monitorForMainModel, 1));
                session.close(new SubProgressMonitor(monitorForMainModel, 1));
                monitorForMainModel.done();
            }

        } catch (InterruptedException e) {
            if (backup) {
                revertAllBackup(new SubProgressMonitor(monitor, 1), originalToBackupFiles);
            }
            throw e;

        } finally {
            monitor.done();
        }
    }

    /**
     * Notifies that a given number of work unit of the main task has been
     * completed and check if the user cancel the current task.
     * 
     * @param monitor
     *            The monitor to deal with.
     * @param work
     *            a non-negative number of work units just completed
     * @throws InterruptedException
     *             if user canceled operation.
     */
    private void workMonitorAndCheckCancel(IProgressMonitor monitor, int work) throws InterruptedException {
        monitor.worked(work);
        if (monitor.isCanceled()) {
            throw new InterruptedException();
        }
    }

    /**
     * Check if the user cancel the current task.
     * 
     * @param monitor
     *            The monitor to deal with.
     */
    private void checkCancel(IProgressMonitor monitor) {
        if (monitor.isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    /**
     * The repair of the model :
     * <UL>
     * <LI>Remove the representation that have empty target (target == null or
     * target not in a eResource)</LI>
     * <LI>Store the element states</LI>
     * <LI>Remove all elements that have mapping with createElements value to
     * true,</LI>
     * <LI>Refresh the diagram (to automatically re-create above elements)</LI>
     * <LI>Call external migration participant</LI>
     * <LI>Restore the element states</LI>
     * <LI>All representations, even those owned by disabled viewpoints, are
     * displayed.</LI>
     * </UL>
     * 
     * @param session
     *            the session opened on aird to repair.
     * @param model
     *            the resource model to repair.
     * @param monitor
     *            the progress monitor to provide feedback.
     * @throws InterruptedException
     *             in case of cancel button is pressed.
     */

    protected void repairModel(final Session session, final Resource model, final IProgressMonitor monitor) throws InterruptedException {
        if (model.getContents().size() > 0) {
            for (final IRepairParticipant participant : this.repairParticipants) {
                participant.repairStarted();
            }
            final TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
            final DAnalysis dAnalysis = (DAnalysis) model.getContents().get(0);

            MigrationCommandExecutor migrationCommandExecutor = new MigrationCommandExecutor();

            List<DView> ownedViews = dAnalysis.getOwnedViews();
            monitor.beginTask("", 7 * ownedViews.size());
            for (final DView view : ownedViews) {
                for (IRepairParticipant participant : repairParticipants) {
                    participant.startRepairOnView(session, view);
                }
                IdentityCommand handleViewCommand = new IdentityCommand() {

                    @Override
                    public void execute() {
                        handleView(view);
                    }

                    @Override
                    public boolean canUndo() {
                        return false;
                    }
                };

                // handle view: see #handleView method comments for more
                // details.
                monitor.subTask("--> Handling view");
                migrationCommandExecutor.execute(transactionalEditingDomain, handleViewCommand);
                monitor.worked(1);

                // save elements state
                monitor.subTask("--> Saving elements state");
                saveModelElementState(view, new SubProgressMonitor(monitor, 1));

                // monitor.subTask("Migrating elements");

                // Remove diagram elements (this method call a recording
                // command)
                monitor.subTask("--> Removing diagram elements");
                final List<EObject> toBeRemoved = computeElementsToDeleteOrDeletedElements(view);
                removeDiagramElements(new SubProgressMonitor(monitor, 1), transactionalEditingDomain, toBeRemoved);

                if (monitor.isCanceled()) {
                    throw new InterruptedException();
                }

                // refreshes diagrams (this method called a recording command)
                monitor.subTask("--> Refreshing representations");
                RepairRepresentationRefresher refresher = new RepairRepresentationRefresher(lostNodesByDelete, lostEdgesByDelete);
                refresher.refreshRepresentations(dAnalysis, view);
                monitor.worked(1);

                if (monitor.isCanceled()) {
                    throw new InterruptedException();
                }

                // call post refresh on each participants
                monitor.subTask("--> Post refresh");
                postRefresh(model);
                monitor.worked(1);

                // Restore model element state. This method called a recording
                // command
                monitor.subTask("--> Restoring element stats");
                restoreModelElementState(view, new SubProgressMonitor(monitor, 1));

                /*
                 * Move all representations from hidden- to owned-
                 * representations
                 */
                if (view instanceof DRepresentationContainer) {
                    Command moveAllRepresentationsFromHiddenToOwnedCommand = new MoveAllRepresentationsFromHiddenToOwnedCommand((DRepresentationContainer) view);
                    monitor.subTask("--> Move all representations from hidden to owned representations");
                    migrationCommandExecutor.execute(transactionalEditingDomain, moveAllRepresentationsFromHiddenToOwnedCommand);
                    monitor.worked(1);

                }
                for (IRepairParticipant participant : repairParticipants) {
                    participant.endRepairOnView();
                }
            }
            for (final IRepairParticipant participant : this.repairParticipants) {
                participant.repairCompeleted();
            }
        }
        /* Reset to null the metamodel validation */
        // this.validNewMM.clear();
        monitor.done();
    }

    /**
     * @param migrationMonitor
     * @param originalToBackupFiles
     */
    private void revertAllBackup(final IProgressMonitor migrationMonitor, Map<IFile, IFile> originalToBackupFiles) {
        try {
            for (Entry<IFile, IFile> originalToBackupFile : originalToBackupFiles.entrySet()) {
                revertBackupFile(originalToBackupFile.getKey(), originalToBackupFile.getValue(), migrationMonitor);
            }
        } catch (IOException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, "", e));
        }
    }

    /**
     * Remove elements with isCreatedElement sets to true. Store data about the
     * other.
     * 
     * @param view
     * @return the list of elements that can be safety removed.
     */
    private List<EObject> computeElementsToDeleteOrDeletedElements(final DView view) {
        final List<EObject> toBeRemoved = Lists.newArrayList();

        lostNodesByDelete.clear();
        lostEdgesByDelete.clear();

        // If an element has its mapping configured with isCreatedElements sets
        // to true, it will be removed and then recreated by diagram refresh.
        // But an element with isCreated sets to false won't be recreated by
        // refresh. Another process will recreate them after.

        final Iterator<DDiagramElement> viewIterator = Iterators.filter(view.eAllContents(), DDiagramElement.class);
        while (viewIterator.hasNext()) {
            final DDiagramElement diagElement = viewIterator.next();
            if (new DiagramElementMappingQuery(diagElement.getDiagramElementMapping()).isSynchronizedAndCreateElement(diagElement)) {
                toBeRemoved.add(diagElement);
            } else {

                if (LostElementFactory.isCompleteDiagramElement(diagElement)) {
                    final DiagramKey diagramKey = DiagramKey.createDiagramKey(diagElement.getParentDiagram());
                    if (diagElement instanceof AbstractDNode) {
                        createLostNodeData(diagramKey, diagElement);

                    } else if (diagElement instanceof DEdge) {
                        createLostEdgeData(diagramKey, diagElement);
                    }
                }
            }
        }
        return toBeRemoved;
    }

    private void createLostEdgeData(final DiagramKey diagramKey, final DDiagramElement diagElement) {
        final LostEdgeData data = LostElementFactory.createLostEdgeData(diagElement);
        lostEdgesByDelete.put(diagramKey, data);
    }

    private void createLostNodeData(final DiagramKey diagramKey, final DDiagramElement diagElement) {
        final LostNodeData data = LostElementFactory.createLostNodeData(diagElement);
        lostNodesByDelete.put(diagramKey, data);
    }

    private void removeDiagramElements(final IProgressMonitor monitor, TransactionalEditingDomain transactionalEditingDomain, final List<EObject> toBeRemoved) {
        monitor.beginTask("remove Diagram Elements", 1);
        Command removeDiagramElementsCommand = new RemoveDiagramElementsCommand(new NullProgressMonitor(), toBeRemoved);

        new MigrationCommandExecutor().execute(transactionalEditingDomain, removeDiagramElementsCommand);
        monitor.done();
    }

    /**
     * Do some post refresh operations.
     * 
     * @param resource
     *            the resource
     */
    private void postRefresh(final Resource resource) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(resource);
        for (final IRepairParticipant participant : this.repairParticipants) {
            participant.postRefreshOperations(transactionalEditingDomain, resource);
        }
    }

    /**
     * Restores the state of all the model's DRepresentationElement.
     * 
     * @param view
     *            The view which state is to be restored.
     */
    private void restoreModelElementState(final DView view, final IProgressMonitor monitor) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(view);

        Command restoreModelElementStateCommand = new RestoreModelElementStateCommand(view, repairParticipants, monitor);

        new MigrationCommandExecutor().execute(domain, restoreModelElementStateCommand);
    }

    /**
     * Inform model. The DAnalysis must reference all semantic root elements to
     * correctly resolved the SessionManager.getSession(semanticElement).
     * 
     * @param view
     *            {@link DView} to inform
     */
    private void informModel(final DRepresentationContainer view) {
        if (view.eContainer() instanceof DAnalysis) {
            DAnalysis analysis = (DAnalysis) view.eContainer();
            // Add all semantic root elements pointed by the target of all
            // DSemanticDecorator of this representation (except if they are the
            // main model of a referenced analysis).
            DAnalysisSessionHelper.updateModelsReferences(analysis, Iterators.filter(view.eAllContents(), DSemanticDecorator.class));
        }
    }

    /**
     * Inform viewpoint.
     * 
     * @param view
     *            the view to inform
     */
    private void informSirius(final DView view) {
        if (view.getAllRepresentations() != null && !view.getAllRepresentations().isEmpty()) {
            final DRepresentation representation = view.getAllRepresentations().get(0);
            final RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
            if (description != null) {
                Viewpoint vp = new RepresentationDescriptionQuery(description).getParentSirius();
                if (vp != null) {
                    view.setViewpoint(vp);
                }
            }
            ((DAnalysis) view.eContainer()).getSelectedViews().add(view);
        }
    }

    /**
     * Save the state of all the model's DRepresentationElement.
     * 
     * @param view
     *            The view which state is to be saved.
     */
    private void saveModelElementState(final DView view, final IProgressMonitor monitor) {
        monitor.beginTask("save Model Element State", this.repairParticipants.size());
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(view);
        for (final IRepairParticipant participant : this.repairParticipants) {
            Command saveModelElementStateCommand = new SaveModelElementStateCommand(participant, view, new NullProgressMonitor());

            new MigrationCommandExecutor().execute(transactionalEditingDomain, saveModelElementStateCommand);
            monitor.worked(1);
        }
        monitor.done();
    }

    private List<Resource> getFragmentedResources(final Resource modelResource) {
        return Lists.newArrayList(Collections2.filter(modelResource.getResourceSet().getResources(), new Predicate<Resource>() {
            public boolean apply(Resource resource) {
                return !resource.equals(modelResource) && resource instanceof AirdResource && resource.getURI().isPlatformResource();
            }
        }));
    }

    /**
     * Make a backup of this file.
     * 
     * @param fileToBackup
     *            file to backup
     * @param monitor
     *            The progress monitor
     * @return The backup file.
     */
    private IFile backupFile(final IFile fileToBackup, final IProgressMonitor monitor) {
        // Creates the backup file.
        try {
            return ResourceUtil.createBackupFile(fileToBackup, monitor);
        } catch (final CoreException e) {
            SiriusPlugin.getDefault().error("Error while creating backup file", e);
        }
        return null;
    }

    /**
     * Replace the file with the backup and delete the backup.
     * 
     * @param backupedFile
     *            file to restore
     * @param backupFile
     *            backup file
     * @param monitor
     *            The progress monitor
     * @throws IOException
     *             fileException
     */
    private void revertBackupFile(final IFile backupedFile, final IFile backupFile, final IProgressMonitor monitor) throws IOException {
        try {
            // Restore the backup
            InputStream inpstrBackupFile = backupFile.getContents();
            try {
                backupedFile.setContents(inpstrBackupFile, IResource.FORCE, new SubProgressMonitor(monitor, 1));
            } catch (CoreException e) {
                SiriusPlugin.getDefault().error("Error while restoring backup file", e);
            } finally {
                inpstrBackupFile.close();
            }
            // Delete the backup
            backupFile.delete(IResource.FORCE, new SubProgressMonitor(monitor, 1));
            // Refresh local container
            backupedFile.getParent().refreshLocal(IResource.DEPTH_ONE, new SubProgressMonitor(monitor, 1));
        } catch (final CoreException e) {
            SiriusPlugin.getDefault().error("Error while restoring backup file", e);
        }
        monitor.done();
    }

    /**
     * Handle {@link DView}.
     * <ul>
     * <li>Cleans filters variables, activated filters, activated behaviors,
     * activated rules</li>
     * <li>Deletes representations without description.</li>
     * <li>Deletes representations without target element or without a target
     * element into an eResource.</li>
     * </ul>
     * 
     * @param view
     *            {@link DView}
     */
    private void handleView(final DView view) {
        informSirius(view);
        // Compute the models references before cleaning the diagram.
        if (view instanceof DRepresentationContainer) {
            informModel((DRepresentationContainer) view);
        }

        final List<DRepresentation> representationsToRemove = new LinkedList<DRepresentation>();

        for (final DRepresentation representation : view.getAllRepresentations()) {
            if (representation instanceof DDiagram) {
                final DDiagram next = (DDiagram) representation;
                // migration code, useless here.
                // handleNeedVisibilityMigration(localNeedVisibilityMigration,
                // next);

                // Clean filters variables, activated filters,
                // activated behaviors, activated rules
                cleanReferences(next);

                // Even if Description has cardinality [0:1] in
                // DDiagram, diagrams without description should not
                // be allowed
                if (representation instanceof DSemanticDiagram && ((DSemanticDiagram) representation).getDescription() == null) {
                    representationsToRemove.add(representation);
                }

            }
            if (representation instanceof DSemanticDecorator && (((DSemanticDecorator) representation).getTarget() == null || ((DSemanticDecorator) representation).getTarget().eResource() == null)) {
                representationsToRemove.add(representation);
            }
        }

        removeRepresentations(representationsToRemove);
    }

    /**
     * Clean the references which could be problematic for the migration ((proxy
     * not resolved or element not in a eResource):
     * <UL>
     * <LI>Remove the filter variables cache if the element points by this
     * variable is no longer exists</LI>
     * <LI>Disables the behaviors that were activated but no longer exists,</LI>
     * <LI>Disables the filters that were activated but no longer exists,</LI>
     * <LI>Disables the rules that were activated but no longer exists.</LI>
     * </UL>
     * 
     * @param diagram
     *            The diagram to clean.
     */
    private void cleanReferences(final DDiagram diagram) {
        // remove the variable caches..
        if (diagram.getFilterVariableHistory() != null && diagram.getFilterVariableHistory().getOwnedValues() != null) {
            // diagram.getFilterVariableHistory().getOwnedValues().clear();
            final Iterator<FilterVariableValue> filterVariablesIterator = diagram.getFilterVariableHistory().getOwnedValues().iterator();
            while (filterVariablesIterator.hasNext()) {
                final FilterVariableValue filterVariable = filterVariablesIterator.next();
                if (filterVariable.eIsProxy() || filterVariable.eResource() == null) {
                    filterVariablesIterator.remove();
                }
            }
        }
        // de-activate filters and behaviors (if needed)
        if (diagram.getActivateBehaviors() != null) {
            // next.getActivateBehaviors().clear();
            final Iterator<BehaviorTool> activatedBehaviorsIterator = diagram.getActivateBehaviors().iterator();
            while (activatedBehaviorsIterator.hasNext()) {
                final BehaviorTool activatedBehaviors = activatedBehaviorsIterator.next();
                if (activatedBehaviors.eIsProxy() || activatedBehaviors.eResource() == null) {
                    activatedBehaviorsIterator.remove();
                }
            }
        }
        if (diagram.getActivatedFilters() != null) {
            // next.getActivatedFilters().clear();
            final Iterator<FilterDescription> activatedFiltersIterator = diagram.getActivatedFilters().iterator();
            while (activatedFiltersIterator.hasNext()) {
                final FilterDescription filterDescription = activatedFiltersIterator.next();
                if (filterDescription.eIsProxy() || filterDescription.eResource() == null) {
                    activatedFiltersIterator.remove();
                }
            }
        }
        if (diagram.getActivatedRules() != null) {
            // next.getActivatedRules().clear();
            final Iterator<ValidationRule> activatedRulesIterator = diagram.getActivatedRules().iterator();
            while (activatedRulesIterator.hasNext()) {
                final ValidationRule activatedRule = activatedRulesIterator.next();
                if (activatedRule.eIsProxy() || activatedRule.eResource() == null) {
                    activatedRulesIterator.remove();
                }
            }
        }
    }

    private void removeRepresentations(final List<DRepresentation> representationsToRemove) {

        for (final DRepresentation representation : representationsToRemove) {
            SiriusUtil.delete(representation);
        }
    }

    /**
     * Ends the repair.
     */
    public void dispose() {
        file = null;
        lostEdgesByDelete.clear();
        lostNodesByDelete.clear();
        repairParticipants.clear();

    }

}
