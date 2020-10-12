/*******************************************************************************
 * Copyright (c) 2011, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ProjectDependenciesItem;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.AddModelDependencyAction;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.AddSemanticResourceAction;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.RemoveRepresentationResourceAction;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.RemoveSemanticResourceAction;
import org.eclipse.sirius.ui.tools.internal.actions.control.DesignerControlAction;
import org.eclipse.sirius.ui.tools.internal.actions.copy.CopyRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationFromSessionAction;
import org.eclipse.sirius.ui.tools.internal.actions.creation.OtherRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.actions.export.ExportRepresentationsAction;
import org.eclipse.sirius.ui.tools.internal.actions.session.CloseSessionsAction;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationsAction;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenViewpointSelectionAction;
import org.eclipse.sirius.ui.tools.internal.views.ViewHelperImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.action.DeleteRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.action.ExtractRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.action.MoveRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.action.RenameRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ControlledRoot;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.IContextMenuActionProvider;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Helper to fill context menu with viewpoint actions.
 * 
 * @author mporhel
 * 
 */
public class ContextMenuFiller implements IMenuListener, IMenuListener2 {

    private static final String NEW_REPRESENTATION_MENU_ID = "menu.viewpoint.new.representation"; //$NON-NLS-1$

    private static final String SESSION_MANAGEMENT_SEPARATOR = "group.viewpoint.session"; //$NON-NLS-1$

    private static final String REPRESENTATION_MANAGEMENT_SEPARATOR = "group.viewpoint.representation"; //$NON-NLS-1$

    private static final String SEMANTIC_MANAGEMENT_SEPARATOR = "group.viewpoint.semantic"; //$NON-NLS-1$

    private static final String SESSION_SEPARATOR = "group.viewpoint"; //$NON-NLS-1$

    private static final String GROUP_NEW = "group.new"; //$NON-NLS-1$

    private static final String GROUP_PORT = "group.port"; //$NON-NLS-1$

    private static final String GROUP_OPEN = "group.open"; //$NON-NLS-1$

    private static final String GROUP_REORGANIZE = "group.reorganize"; //$NON-NLS-1$

    private static final String GROUP_EDIT = "group.edit"; //$NON-NLS-1$

    private ILabelProvider labelProvider;

    private DesignerControlAction controlAction;

    private StructuredViewer modelViewer;

    private WeakHashMap<IContributionItem, Object> addedIContributionItemCache = new WeakHashMap<IContributionItem, Object>();

    /**
     * Constructor.
     * 
     * @param modelViewer
     *            a structured viewer.
     * @param labelProvider
     *            the label provider.
     */
    public ContextMenuFiller(StructuredViewer modelViewer, ILabelProvider labelProvider) {
        this.labelProvider = labelProvider;
        this.controlAction = new DesignerControlAction();
        this.modelViewer = modelViewer;
        modelViewer.addSelectionChangedListener(controlAction);
    }

    /**
     * Fill the context menu for the given selection.
     * 
     * @param menu
     *            the menu to fill.
     * @param selection
     *            the current selection.
     */
    public void fillContextMenu(IMenuManager menu, ISelection selection) {
        menu.addMenuListener(this);

        safeAddGroups(menu);

        if (selection instanceof IStructuredSelection) {
            Collection<?> selectedObjects = ((IStructuredSelection) selection).toList();

            if (!selectedObjects.isEmpty()) {
                computeSessionContextMenu(menu, selectedObjects);
                computeModelDependenciesContextMenu(menu, selectedObjects);
                computeControlledRootContextMenu(menu, selectedObjects);

                Collection<EObject> selectedEObjects = getEObjects(selectedObjects);
                computeSemanticContextMenu(menu, selectedEObjects);
                computeRepresentationContextMenu(menu, getRepresentationDescriptors(selectedObjects), selectedEObjects);
                computeRepresentationsResourcesContextMenu(menu, getRepresentationResources(selectedObjects));
                computeSemanticResourcesContextMenu(menu, getSemanticResources(selectedObjects), selection);

                // Add contributions
                computeContextMenuContribution(menu, selection);
            }
        }
    }

    private void safeAddGroups(IMenuManager menu) {
        if (menu != null) {
            if (menu.find(GROUP_NEW) == null) {
                menu.add(new GroupMarker(GROUP_NEW));
            }
            if (menu.find(SESSION_MANAGEMENT_SEPARATOR) == null) {
                menu.insertBefore(GROUP_NEW, new Separator(SESSION_MANAGEMENT_SEPARATOR));
            }
            if (menu.find(REPRESENTATION_MANAGEMENT_SEPARATOR) == null) {
                menu.insertAfter(SESSION_MANAGEMENT_SEPARATOR, new Separator(REPRESENTATION_MANAGEMENT_SEPARATOR));
            }
            if (menu.find(SEMANTIC_MANAGEMENT_SEPARATOR) == null) {
                menu.insertAfter(REPRESENTATION_MANAGEMENT_SEPARATOR, new Separator(SEMANTIC_MANAGEMENT_SEPARATOR));
            }

            if (menu.find(SESSION_SEPARATOR) == null) {
                menu.insertAfter(SEMANTIC_MANAGEMENT_SEPARATOR, new Separator(SESSION_SEPARATOR));
            }

            if (menu.find(GROUP_OPEN) == null) {
                menu.insertAfter(GROUP_NEW, new GroupMarker(GROUP_OPEN));
            }
            if (menu.find(GROUP_EDIT) == null) {
                menu.insertAfter(GROUP_OPEN, new GroupMarker(GROUP_EDIT));
            }
            if (menu.find(GROUP_REORGANIZE) == null) {
                menu.insertAfter(GROUP_EDIT, new GroupMarker(GROUP_REORGANIZE));
            }
            if (menu.find(GROUP_PORT) == null) {
                menu.insertAfter(GROUP_REORGANIZE, new GroupMarker(GROUP_PORT));
            }
        }
    }

    private void computeControlledRootContextMenu(IMenuManager menu, Collection<?> selectedObjects) {
        Iterable<ControlledRoot> roots = Iterables.filter(selectedObjects, ControlledRoot.class);
        if (Iterables.size(roots) == 1) {
            Object selected = roots.iterator().next().getWrappedObject();
            if (selected instanceof DRepresentationDescriptor && !new DRepresentationDescriptorQuery((DRepresentationDescriptor) selected).isRepresentationValid()) {
                return;
            }
            if (selected instanceof EObject) {
                EObject selectedEObject = (EObject) selected;
                Session session = SessionManager.INSTANCE.getSession((EObject) selected);
                if (session != null) {
                    /* control menu */
                    final StructuredSelection newSelection = new StructuredSelection(selected);
                    if (!hasSharedObjectInSelection(getEObjects(Collections.singletonList(selectedEObject)))) {
                        controlAction.selectionChanged(newSelection);
                        addActionToMenu(menu, GROUP_REORGANIZE, controlAction);

                    }

                    addActionToMenu(menu, SEMANTIC_MANAGEMENT_SEPARATOR, new Action(Messages.ContextMenuFiller_showInHierarchy) {
                        @Override
                        public void run() {
                            modelViewer.setSelection(newSelection, true);
                        }
                    });
                }
            }
        }
    }

    private void addActionToMenu(IMenuManager menu, String group, IAction action) {
        addContributionToMenu(menu, group, new ActionContributionItem(action));

    }

    private void addContributionToMenu(IMenuManager menu, String group, IContributionItem item) {
        menu.appendToGroup(group, item);
        addedIContributionItemCache.put(item, null);

    }

    private void computeModelDependenciesContextMenu(IMenuManager menu, Collection<?> selection) {
        Collection<ProjectDependenciesItem> items = getModelDependencies(selection);
        if (items.size() == 1 && selection.size() == 1) {
            Option<Session> session = items.iterator().next().getSession();
            if (session.some()) {
                /* Add Model. */

                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildAddModelAction(session.get()));
            }
        }
    }

    private void computeRepresentationContextMenu(IMenuManager menu, final Collection<DRepresentationDescriptor> selectedRepDescriptors, Collection<EObject> selectedEObjects) {
        if (selectedRepDescriptors == null || selectedEObjects == null) {
            return;
        }

        Session session = null;
        EObject firstEObject = null;
        for (DRepresentationDescriptor repDesc : selectedRepDescriptors) {
            firstEObject = repDesc.getTarget();
            if (firstEObject != null)
                break;
        }

        if (firstEObject == null && !selectedEObjects.isEmpty()) {
            firstEObject = selectedEObjects.iterator().next();
        }

        session = firstEObject == null ? null : SessionManager.INSTANCE.getSession(firstEObject);

        List<DRepresentationDescriptor> reachableRepresentations = selectedRepDescriptors.stream().filter(repDesc -> new DRepresentationDescriptorQuery(repDesc).isRepresentationValid())
                .collect(Collectors.toList());
        boolean atLeastOneEObjectOtherThanDRepDesc = selectedEObjects.stream().filter(obj -> !(obj instanceof DRepresentationDescriptor)).filter(EObject.class::isInstance).count() > 0;

        /* session should not be null for the following actions */
        if (session != null) {
            if (!reachableRepresentations.isEmpty()) {
                /*
                 * representation menu
                 */
                addActionToMenu(menu, GROUP_OPEN, buildOpenRepresentationAction(reachableRepresentations));
                addActionToMenu(menu, GROUP_REORGANIZE, buildRenameRepresentationAction(reachableRepresentations));
                addActionToMenu(menu, GROUP_REORGANIZE, buildCopyRepresentationsAction(reachableRepresentations, session));
                if (session.getAllSessionResources().size() >= 1) {
                    final Collection<Resource> targetResources = new LinkedHashSet<Resource>(session.getAllSessionResources());
                    final Collection<Resource> originResources = collectOriginResources(reachableRepresentations);
                    if (originResources.size() == 1) {
                        targetResources.removeAll(originResources);
                    }
                    // The move action will be available only if the selected representation can be edited
                    boolean locked = false;
                    for (DRepresentationDescriptor repDesc : reachableRepresentations) {
                        if (!((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(repDesc)).canEditInstance(repDesc)) {
                            locked = true;
                            break;
                        } else if (!((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(repDesc.eContainer())).canEditInstance(repDesc.eContainer())) {
                            locked = true;
                            break;
                        }
                    }
                    if (targetResources.size() > 0) {
                        Action buildMoveRepresentationsActions = buildMoveRepresentationsActions(session, reachableRepresentations);
                        buildMoveRepresentationsActions.setEnabled(!locked);
                        addActionToMenu(menu, GROUP_REORGANIZE, buildMoveRepresentationsActions);
                    }
                    addActionToMenu(menu, GROUP_REORGANIZE, buildExtractRepresentationsAction(session, reachableRepresentations));
                }
            }

            if (!reachableRepresentations.isEmpty() || atLeastOneEObjectOtherThanDRepDesc) {
                ExportRepresentationsAction actionExportImage = new ExportRepresentationsAction(session, selectedEObjects, reachableRepresentations);
                addActionToMenu(menu, GROUP_PORT, actionExportImage);
            }
        }

        if (!selectedRepDescriptors.isEmpty()) {
            addActionToMenu(menu, GROUP_EDIT, buildDeleteRepresentationAction(selectedRepDescriptors));
        }
    }

    private void computeRepresentationsResourcesContextMenu(IMenuManager menu, final Collection<AnalysisResourceItem> diagramResources) {
        Session session = null;
        if (!diagramResources.isEmpty()) {
            for (AnalysisResourceItem diagramResource : diagramResources) {
                Option<Session> s = diagramResource.getSession();
                if (s.some()) {
                    session = s.get();
                    break;
                }
            }
        }

        if (!diagramResources.isEmpty() && session != null) {
            addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildRemoveRepresentationsResourceAction(diagramResources, session));
        }
    }

    private void computeSemanticContextMenu(IMenuManager menu, final Collection<EObject> selection) {
        if (selection != null && !selection.isEmpty()) {
            final EObject firstSelected = (EObject) selection.toArray()[0];
            if (firstSelected instanceof DRepresentationDescriptor && !new DRepresentationDescriptorQuery((DRepresentationDescriptor) firstSelected).isRepresentationValid()) {
                return;
            }
            Session session = SessionManager.INSTANCE.getSession(firstSelected);
            if (session != null) {
                /* control menu */
                if (!hasSharedObjectInSelection(selection)) {

                    addActionToMenu(menu, GROUP_REORGANIZE, controlAction);
                }

                /* New representations menu */
                final MenuManager addNewRepresentation = new MenuManager(Messages.ContextMenuFiller_newRepresentation, NEW_REPRESENTATION_MENU_ID);
                final Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), firstSelected);
                if (descriptions.size() > 0) {
                    for (final RepresentationDescription representationDescription : descriptions) {
                        if (DialectManager.INSTANCE.canCreate(firstSelected, representationDescription)) {
                            addNewRepresentation.add(buildCreateRepresentationAction(firstSelected, representationDescription, session));
                        }
                    }
                }
                addNewRepresentation.add(buildOtherRepresentationAction(firstSelected, session));
                addContributionToMenu(menu, GROUP_NEW, addNewRepresentation);
            }
        }
    }

    /**
     * Create the {@link OtherRepresentationAction} from given selection and session.
     * 
     * @param semanticElement
     *            the semantic element to take in consideration.
     * @param session
     *            the session to take in consideration.
     * @return the newly created {@link OtherRepresentationAction} from given selection and session.
     */
    private Action buildOtherRepresentationAction(final EObject semanticElement, final Session session) {
        return new OtherRepresentationAction(session, semanticElement);
    }

    private boolean hasSharedObjectInSelection(Collection<EObject> selection) {
        boolean hasSharedObjectInSelection = false;
        for (EObject selectedEObject : selection) {
            Resource selectedEObjectResource = selectedEObject.eResource();
            if (selectedEObjectResource != null && URIQuery.CDO_URI_SCHEME.equals(selectedEObjectResource.getURI().scheme())) {
                hasSharedObjectInSelection = true;
                break;
            }
        }
        return hasSharedObjectInSelection;
    }

    private void computeSemanticResourcesContextMenu(IMenuManager menu, final Collection<Resource> resources, ISelection selection) {
        Session session = null;
        boolean allResourcesAreExternal = true;
        if (!resources.isEmpty()) {
            for (final Resource resource : resources) {
                if (!resource.getContents().isEmpty()) {
                    final EObject object = resource.getContents().iterator().next();
                    if (session == null) {
                        session = SessionManager.INSTANCE.getSession(object);
                    }
                    allResourcesAreExternal = allResourcesAreExternal && isExternalDependency(resource, selection);
                }
            }
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(session.getSessionResource().getURI().toPlatformString(true))).getProject();
            boolean addAction = !resources.isEmpty() && session != null;
            addAction = addAction && ((ModelingProject.hasModelingProjectNature(project) && allResourcesAreExternal) || !ModelingProject.hasModelingProjectNature(project));
            if (addAction) {
                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildRemoveSemanticResourceAction(resources, session));
            }
        }
    }

    /**
     * Returns true if the given element is an external dependency of the session.
     * 
     * @param element
     *            the element from which we want to know if it is an external dependency.
     * @param selection
     *            the selection from which we want to know if it is an external dependency.
     * @return true if the given element is an external dependency of the session. False otherwise.
     */
    private boolean isExternalDependency(Object element, ISelection selection) {
        boolean result = false;
        if (selection instanceof TreeSelection) {
            if (!(element instanceof ProjectDependenciesItem) && !(element instanceof DRepresentationDescriptor)) {
                TreePath pathsForSelection = ((TreeSelection) selection).getPathsFor(element)[0];
                int segmentCount = pathsForSelection.getSegmentCount();
                for (int i = 0; i < segmentCount; i++) {
                    Object segment = pathsForSelection.getSegment(i);
                    if (segment instanceof ProjectDependenciesItem) {
                        result = true;
                    }
                }
            }
        } else {
            result = true;
        }
        return result;

    }

    private void computeSessionContextMenu(IMenuManager menu, Collection<?> selection) {
        Collection<IProject> modelingProjects = getModelingProjects(selection);
        if (modelingProjects.size() == 1) {
            computeModelingProjectMenu(menu, modelingProjects.iterator().next());
        } else {
            Set<IProject> impactedProjects = new HashSet<>();
            for (IResource res : Iterables.filter(selection, IResource.class)) {
                impactedProjects.add(res.getProject());
            }

            for (IProject p : impactedProjects) {
                if (ModelingProject.hasModelingProjectNature(p)) {
                    return;
                }
            }
            computeSessionLegacyContextMenu(menu, selection);
        }
    }

    private void computeSessionLegacyContextMenu(IMenuManager menu, Collection<?> selection) {
        List<Session> selectedSessions = FileSessionFinder.getSelectedSessions(selection);
        if (!selectedSessions.isEmpty()) {
            if (selectedSessions.size() == 1) {

                /* Init viewpoints item. */
                final Session session = selectedSessions.get(0);
                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildViewpointsSelectionDialogAction(session));

                /* Add new representations. */
                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildCreateRepresentationAction(session));
            }

            /* Close session item. */
            addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildCloseSessionAction(selectedSessions));

            /* Save item. */
            boolean needSave = false;
            for (Session s : selectedSessions) {
                if (s.getStatus() == SessionStatus.DIRTY) {
                    needSave = true;
                    break;
                }
            }
            if (needSave) {
                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildSaveSessionAction(selectedSessions));
            }

            /* Add semantic Resource. */
            addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildAddSemanticResourceAction(selectedSessions));
        }
    }

    private void computeModelingProjectMenu(IMenuManager menu, IProject project) {
        Session session = null;
        Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
        if (modelingProject.some()) {
            session = modelingProject.get().getSession();
        }

        if (session != null) {
            /* Select viewpoints. */
            addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildViewpointsSelectionDialogAction(session));

            /* Add new representations. */
            addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildCreateRepresentationAction(session));

            /*
             * The following code is here for debug on session management.
             */
            // Close session item.
            // menu.appendToGroup(SESSION_MANAGEMENT_SEPARATOR,
            // buildCloseSessionAction(Collections.singletonList(session)));

            // Save item.
            if (session.getStatus() == SessionStatus.DIRTY && SiriusCommonLabelProvider.shoudlShowSessionDirtyStatus(session)) {
                addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, buildSaveSessionAction(Collections.singletonList(session)));
            }
        }
    }

    private void computeContextMenuContribution(IMenuManager menu, ISelection selection) {
        if (ViewHelper.INSTANCE instanceof ViewHelperImpl) {
            final Collection<IContextMenuActionProvider> providers = ((ViewHelperImpl) ViewHelper.INSTANCE).getContextMenuActionsProviders();
            for (final IContextMenuActionProvider provider : providers) {
                for (final IAction action : provider.getContextMenuActions(selection)) {
                    addActionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, action);
                }
            }
            for (final IContextMenuActionProvider provider : providers) {
                for (final IContributionItem contributionItem : provider.getContextualMenuContributionItems(selection)) {
                    addContributionToMenu(menu, SESSION_MANAGEMENT_SEPARATOR, contributionItem);
                }
            }
        }
    }

    private IAction buildOpenRepresentationAction(final Collection<DRepresentationDescriptor> selection) {
        return new OpenRepresentationsAction(selection);
    }

    private IAction buildRemoveSemanticResourceAction(final Collection<Resource> selection, final Session session) {
        return new RemoveSemanticResourceAction(selection, session);
    }

    private IAction buildRemoveRepresentationsResourceAction(final Collection<AnalysisResourceItem> diagramResourceItems, final Session session) {
        final Collection<Resource> diagramResources = new HashSet<Resource>();
        for (AnalysisResourceItem diagramResourceItem : diagramResourceItems) {
            diagramResources.add((Resource) diagramResourceItem.getWrappedObject());
        }
        return new RemoveRepresentationResourceAction(diagramResources, session);
    }

    private IAction buildExtractRepresentationsAction(final Session session, final Collection<DRepresentationDescriptor> movableRepresentations) {
        return new ExtractRepresentationAction(session, movableRepresentations);
    }

    private Action buildMoveRepresentationsActions(final Session session, final Collection<DRepresentationDescriptor> movableRepresentations) {
        return new MoveRepresentationAction(session, movableRepresentations);
    }

    private Action buildViewpointsSelectionDialogAction(final Session session) {
        return new OpenViewpointSelectionAction(session.getSessionResource().getURI());
    }

    private Action buildCopyRepresentationsAction(final Collection<DRepresentationDescriptor> selection, final Session session) {
        return new CopyRepresentationAction(session, selection);
    }

    private Action buildRenameRepresentationAction(final Collection<DRepresentationDescriptor> selection) {
        Action renameAction = new RenameRepresentationAction(selection);
        renameAction.setActionDefinitionId(IWorkbenchCommandConstants.FILE_RENAME);
        return renameAction;
    }

    private IAction buildAddSemanticResourceAction(final List<Session> selection) {
        return new AddSemanticResourceAction(selection);
    }

    private IAction buildAddModelAction(Session selectedSession) {
        return new AddModelDependencyAction(selectedSession);
    }

    private Action buildDeleteRepresentationAction(final Collection<DRepresentationDescriptor> repDescriptors) {
        Action deleteAction = new DeleteRepresentationAction(repDescriptors);
        deleteAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_DELETE);
        return deleteAction;
    }

    private Action buildCreateRepresentationAction(final EObject selection, final RepresentationDescription representationDescription, final Session session) {
        return new CreateRepresentationAction(session, selection, representationDescription, labelProvider);
    }

    private Action buildCreateRepresentationAction(final Session session) {
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/addRepresentation.gif"); //$NON-NLS-1$
        return new CreateRepresentationFromSessionAction(session, descriptor);
    }

    private Action buildCloseSessionAction(final List<Session> selection) {
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/close.gif"); //$NON-NLS-1$
        CloseSessionsAction closeSessionsAction = new CloseSessionsAction(Messages.ContextMenuFiller_closeSession);
        closeSessionsAction.setImageDescriptor(descriptor);
        closeSessionsAction.selectionChanged(new StructuredSelection(selection));
        return closeSessionsAction;
    }

    private Action buildSaveSessionAction(final List<Session> selection) {
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/save.gif"); //$NON-NLS-1$
        return new Action(Messages.ContextMenuFiller_saveSession, descriptor) {
            @Override
            public void run() {
                super.run();

                for (final Session session : selection) {
                    final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
                    try {
                        new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, new WorkspaceModifyOperation() {

                            @Override
                            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                                if (uiSession instanceof ISaveablesSource) {
                                    ISaveablesSource saveablesSource = (ISaveablesSource) uiSession;
                                    for (Saveable saveable : saveablesSource.getActiveSaveables()) {
                                        try {
                                            saveable.doSave(monitor);
                                        } catch (CoreException e) {
                                            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, Messages.ContextMenuFiller_saveSessionError, e));
                                        }
                                    }
                                } else {
                                    session.save(monitor);
                                }
                            }
                        });

                    } catch (final InterruptedException e) {
                        IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e);
                        SiriusEditPlugin.getPlugin().getLog().log(status);
                    } catch (final InvocationTargetException e) {
                        IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e.getTargetException());
                        SiriusEditPlugin.getPlugin().getLog().log(status);
                    }
                }
            }
        };
    }

    /**
     * Dispose the context.
     */
    public void dispose() {
        modelViewer.removeSelectionChangedListener(controlAction);
        modelViewer = null;

        controlAction = null;
        labelProvider = null;
    }

    private Collection<Resource> collectOriginResources(final Collection<DRepresentationDescriptor> movableRepresentations) {
        final Collection<Resource> result = new LinkedHashSet<Resource>();
        for (final DRepresentationDescriptor representation : movableRepresentations) {
            result.add(representation.eResource());
        }
        return result;
    }

    private Collection<EObject> getEObjects(final Collection<?> selection) {
        Collection<EObject> eObjects = Collections.emptyList();
        if (selection != null) {
            // Keep only EObjects, not Resources (CDOResources are EObjects).
            eObjects = Sets.newLinkedHashSet(Iterables.filter(Iterables.filter(selection, EObject.class), Predicates.not(Predicates.instanceOf(Resource.class))));
        }
        return eObjects;
    }

    private Collection<ProjectDependenciesItem> getModelDependencies(Collection<?> selection) {
        Collection<ProjectDependenciesItem> deps = Collections.emptyList();
        if (selection != null) {
            deps = Sets.newLinkedHashSet(Iterables.filter(selection, ProjectDependenciesItem.class));
        }
        return deps;
    }

    private Collection<IProject> getModelingProjects(Collection<?> selection) {
        Collection<IProject> projects = Collections.emptyList();
        if (selection != null) {
            projects = Sets.newLinkedHashSet(Iterables.filter(Iterables.filter(selection, IProject.class), new Predicate<IProject>() {
                @Override
                public boolean apply(IProject input) {
                    return ModelingProject.hasModelingProjectNature(input);
                }
            }));
        }
        return projects;
    }

    private Collection<DRepresentationDescriptor> getRepresentationDescriptors(Collection<?> selection) {
        Collection<DRepresentationDescriptor> selectedRepDescriptors = Collections.emptyList();
        if (selection != null) {
            selectedRepDescriptors = new LinkedHashSet<>();
            Iterables.addAll(selectedRepDescriptors, Iterables.filter(selection, DRepresentationDescriptor.class));
            Iterables.addAll(selectedRepDescriptors, Iterables.transform(Iterables.filter(selection, RepresentationItemImpl.class), RepresentationItemImpl.REPRESENTATION_ITEM_TO_REPRESENTATION));
        }
        return selectedRepDescriptors;
    }

    private Collection<AnalysisResourceItem> getRepresentationResources(final Collection<?> selection) {
        Collection<AnalysisResourceItem> airdResItems = Collections.emptyList();
        if (selection != null) {
            airdResItems = Sets.newLinkedHashSet(Iterables.filter(selection, AnalysisResourceItem.class));
        }
        return airdResItems;
    }

    private Collection<Resource> getSemanticResources(final Collection<?> selection) {
        Collection<Resource> semRes = Collections.emptyList();
        if (selection != null) {
            semRes = Sets.newLinkedHashSet(Iterables.filter(selection, Resource.class));
        }
        return semRes;
    }

    @Override
    public void menuAboutToShow(IMenuManager manager) {
        // Nothing to do
    }

    @Override
    public void menuAboutToHide(IMenuManager manager) {
        // As the menu is about to be hidden, and because all actions filled by
        // this menu filler will be re-calculated,
        // we dispose all the contributed actions
        Collection<IContributionItem> toRemove = new LinkedHashSet<>();
        for (IContributionItem item : manager.getItems()) {
            if (addedIContributionItemCache.keySet().contains(item)) {
                toRemove.add(item);
            }
        }
        for (IContributionItem item : toRemove) {
            manager.remove(item);
        }
        // No need to listen to the manager any more
        manager.removeMenuListener(this);

        addedIContributionItemCache.clear();

    }

}
