/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - for some methods taken from
 *                      org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.FileSessionFinder;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionWrapperContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.item.AnalysisResourceItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ControlledRoot;
import org.eclipse.sirius.ui.tools.internal.views.common.item.InternalCommonItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ProjectDependenciesItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.SiriusDialectLinkWithEditorSelectionListener;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.progress.UIJob;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Sirius content provider for common navigator.
 * 
 * Some methods have been copied from
 * org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider to
 * handle runnable execution and be able to postpone them when the viewer is
 * busy.
 * 
 * @author mporhel
 */
public class SiriusCommonContentProvider implements ICommonContentProvider {
    private ITreeContentProvider defaultContentProvider;

    private Viewer myViewer;

    private Collection<Runnable> pendingUpdates;

    private UIJob updateJob;

    private SessionManagerListener sessionManagerListener;

    private IDoubleClickListener doubleClickListener;

    private Collection<RefreshViewerTrigger> viewerRefreshTriggers = Sets.newHashSet();

    // This field will be set to false when a call to default content provider
    // is done, it allows to call children from extensions only once.
    private boolean shouldAskExtension;

    private ITreeViewerListener expandListener;

    private SiriusDialectLinkWithEditorSelectionListener linkWithEditorSelectionListener;

    /**
     * Constructor.
     */
    public SiriusCommonContentProvider() {
        defaultContentProvider = ViewHelper.INSTANCE.getContentProvider();

        initSessionManager();
        initDoubleClickListener();
        initExpandListener();
    }

    private void initDoubleClickListener() {
        doubleClickListener = new OpenRepresentationListener();
    }

    private void initExpandListener() {
        expandListener = new OpenSessionOnExpandListener();
    }

    private void initSessionManager() {
        sessionManagerListener = new CommonSessionManagerListener();
        SessionManager.INSTANCE.addSessionsListener(sessionManagerListener);

        for (Session alreadyManagedSession : SessionManager.INSTANCE.getSessions()) {
            if (alreadyManagedSession.isOpen()) {
                addRefreshViewerTrigger(alreadyManagedSession);
            }
        }
    }

    private void addRefreshViewerTrigger(final Session openedSession) {
        if (openedSession != null) {
            RefreshViewerTrigger viewerRefreshTrigger = new RefreshViewerTrigger(openedSession);
            openedSession.getTransactionalEditingDomain().addResourceSetListener(viewerRefreshTrigger);
            viewerRefreshTriggers.add(viewerRefreshTrigger);
        }
    }

    private void removeRefreshViewerTriggers(Session removedSession) {
        if (removedSession != null && removedSession.getTransactionalEditingDomain() != null) {
            for (RefreshViewerTrigger trigger : Lists.newArrayList(viewerRefreshTriggers)) {
                if (removedSession.equals(trigger.getSession())) {
                    removedSession.getTransactionalEditingDomain().removeResourceSetListener(trigger);
                    viewerRefreshTriggers.remove(trigger);
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        Collection<Object> allChildren = Lists.newArrayList();

        // Init view extension getChildren call detection.
        shouldAskExtension = true;

        if (parentElement instanceof IProject) {
            Option<ModelingProject> modelingProj = ModelingProject.asModelingProject((IProject) parentElement);
            if (modelingProj.some()) {
                allChildren.add(new ProjectDependenciesItemImpl(modelingProj.get()));
            }
        } else if (parentElement instanceof IFile) {
            allChildren.addAll(doGetFileChildren((IFile) parentElement));
        } else if (defaultContentProvider != null) {
            return defaultContentProvider.getChildren(parentElement);
        }

        // Provided ISessionViewExtension extensions.
        if (shouldAskExtension) {
            SessionWrapperContentProvider sessionWrapperContentProvider = null;
            if (defaultContentProvider instanceof SessionWrapperContentProvider) {
                sessionWrapperContentProvider = (SessionWrapperContentProvider) defaultContentProvider;
            } else if (defaultContentProvider instanceof GroupingContentProvider
                    && ((GroupingContentProvider) defaultContentProvider).getDelegateTreeContentProvider() instanceof SessionWrapperContentProvider) {
                sessionWrapperContentProvider = (SessionWrapperContentProvider) ((GroupingContentProvider) defaultContentProvider).getDelegateTreeContentProvider();
            }
            if (sessionWrapperContentProvider != null) {
                allChildren.addAll(sessionWrapperContentProvider.getChildrenFromExtensions(parentElement));
            }
        }

        return allChildren.toArray();
    }

    private Collection<Object> doGetFileChildren(IFile parentFile) {
        final Collection<Object> fileChildren = Lists.newArrayList();
        IProject parentProject = parentFile.getProject();
        if (parentProject == null) {
            return fileChildren;
        }

        // Look for opened sessions on parent file : detect main aird for non
        // modeling projects, all aird for modeling ones, semantic file for
        // transient sessions.
        List<Session> openedSessions = Lists.newArrayList(Iterables.filter(FileSessionFinder.getSelectedSessions(Collections.singletonList(parentFile)), new Predicate<Session>() {
            @Override
            public boolean apply(Session input) {
                return input.isOpen();
            }
        }));

        // Modeling project case.
        Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(parentProject);
        if (modelingProject.some()) {
            /* retrieve the session associated to this project */
            Session modelingProjectSession = modelingProject.get().getSession();
            if (modelingProjectSession == null || !modelingProjectSession.isOpen()) {
                // it can maybe have transient session or the modeling porject's
                // session is not opened yet.
            } else if (!SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(parentFile.getFileExtension())) {
                Resource resource = getControlledResource(parentFile, modelingProjectSession);
                if (resource != null && resource.isLoaded()) {
                    for (EObject obj : resource.getContents()) {
                        fileChildren.add(new ControlledRoot(obj, parentFile));
                    }
                } else {
                    resource = getSemanticResource(parentFile, modelingProjectSession);
                    if (resource != null && resource.isLoaded()) {
                        fileChildren.addAll(safeGetDefaultContentProviderChildren(resource, parentFile));
                    }
                }
            } else {
                // Aird case
                Resource resource = getAnalysisResource(parentFile, modelingProjectSession);
                if (resource != null) {
                    AnalysisResourceItemImpl childrenComputer = new AnalysisResourceItemImpl(modelingProjectSession, resource, parentFile);
                    childrenComputer.setSpecialMode(true);
                    fileChildren.addAll(childrenComputer.getChildren());
                }
            }
        } else if (!openedSessions.isEmpty() && SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(parentFile.getFileExtension())) {
            // legacy case
            if (openedSessions.size() > 1) {
                fileChildren.addAll(openedSessions);
                shouldAskExtension = false;
            } else {
                fileChildren.addAll(safeGetDefaultContentProviderChildren(openedSessions.iterator().next(), parentFile));
            }

        }

        // Transient case
        if (!SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(parentFile.getFileExtension())) {
            Iterable<Session> transientSessions = Iterables.filter(openedSessions, new TransientSessionPredicate());
            if (!Iterables.isEmpty(transientSessions)) {
                if (modelingProject.some() || Iterables.size(transientSessions) > 1) {
                    Iterables.addAll(fileChildren, transientSessions);
                    shouldAskExtension = false;
                } else {
                    fileChildren.addAll(doGetChildrenForTransientSession(transientSessions.iterator().next(), parentFile));
                }
            }
        }

        return fileChildren;
    }

    private Collection<Object> safeGetDefaultContentProviderChildren(Object computationParent, Object parentInView) {
        Object[] children = defaultContentProvider.getChildren(computationParent);

        // getChildren call on default content provider, it will provide
        // children from extensions, no need to do it twice.
        shouldAskExtension = false;

        if (children != null) {
            List<Object> childrenList = Arrays.asList(children);

            if (parentInView != null && parentInView != computationParent) {
                for (InternalCommonItem wrapper : Iterables.filter(childrenList, InternalCommonItem.class)) {
                    wrapper.setParent(parentInView);
                }
            }

            return childrenList;
        }
        return Collections.emptyList();
    }

    private List<Object> doGetChildrenForTransientSession(Session transientSession, IFile semanticFile) {
        List<Object> children = Lists.newArrayList();
        if (defaultContentProvider != null && semanticFile != null && semanticFile.getFullPath() != null) {
            URI semUri = getFileUri(semanticFile);
            for (Object child : safeGetDefaultContentProviderChildren(transientSession, semanticFile)) {
                if (child instanceof Resource) {
                    if (semUri.equals(((Resource) child).getURI())) {
                        children.addAll(safeGetDefaultContentProviderChildren(child, semanticFile));
                    }
                } else {
                    children.add(child);
                }
            }
        }
        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParent(Object element) {
        Object parent = null;

        // extensions ?
        if (defaultContentProvider instanceof SessionWrapperContentProvider) {
            parent = ((SessionWrapperContentProvider) defaultContentProvider).getParentFromExtensions(element);
        }

        // handle viewpoint content management cases
        try {
            if (parent == null) {
                if (element instanceof Session) {
                    parent = getSessionParent((Session) element);
                } else if (element instanceof Resource) {
                    parent = getResourceParent((Resource) element);
                } else if (element instanceof EObject && ((EObject) element).eContainer() == null) {
                    parent = getRootParent((EObject) element);
                } else {
                    // delegates other cases to the default content provider.
                    parent = defaultContentProvider.getParent(element);

                }
            }
        } catch (IllegalStateException e) {
            // Happens when cdo server is unreachable
        } catch (NullPointerException e) {
            // Happens when cdo server is unreachable
        }
        return parent;
    }

    private Object getRootParent(EObject element) {
        Resource res = element.eResource();
        Session session = res == null ? null : SessionManager.INSTANCE.getSession(res);
        if (session != null && session.isOpen() && session.getSessionResource().getURI().isPlatformResource()) {
            IFile mainAirdFile = WorkspaceSynchronizer.getFile(session.getSessionResource());

            Option<ModelingProject> modelingProj = ModelingProject.asModelingProject(mainAirdFile.getProject());
            if (modelingProj.some() && res.getURI().isPlatformResource()) {
                IFile file = WorkspaceSynchronizer.getFile(res);
                if (file != null && file.exists() && modelingProj.get().getProject().equals(file.getProject())) {
                    return file;
                }
            }
        }

        return res;
    }

    private Object getResourceParent(Resource res) {
        Object parent = null;
        Session session = SessionManager.INSTANCE.getSession(res);
        if (session != null && session.isOpen() && session.getSessionResource().getURI().isPlatformResource()) {
            IFile mainAirdFile = WorkspaceSynchronizer.getFile(session.getSessionResource());

            Option<ModelingProject> modelingProj = ModelingProject.asModelingProject(mainAirdFile.getProject());
            if (modelingProj.some()) {
                parent = new ProjectDependenciesItemImpl(modelingProj.get());
            } else if (new TransientSessionPredicate().apply(session)) {
                parent = res.getURI().isPlatformResource() ? WorkspaceSynchronizer.getFile(res) : null;
            } else {
                parent = mainAirdFile;
            }
        }
        return parent;
    }

    private Object getSessionParent(Session session) {
        Object parent = null;
        if (new TransientSessionPredicate().apply(session)) {
            for (Resource res : session.getSemanticResources()) {
                if (res.getURI().isPlatformResource()) {
                    parent = WorkspaceSynchronizer.getFile(res);
                    break;
                }
            }
        } else if (session.getSessionResource().getURI().isPlatformResource()) {
            parent = WorkspaceSynchronizer.getFile(session.getSessionResource());
        }
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof IFile) {
            // Show expansion arrows during session load.
            Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(((IFile) element).getProject());
            if (modelingProject.some() && modelingProject.get().getSession() == null && modelingProject.get().isValid()) {
                return true;
            }
        }
        return getChildren(element).length > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (defaultContentProvider != null) {
            defaultContentProvider.inputChanged(viewer, oldInput, newInput);
        }

        if (viewer != myViewer) {
            removeDoubleClickListener();
            removeExpandListener();
            if (linkWithEditorSelectionListener != null) {
                linkWithEditorSelectionListener.dispose();
            }

            myViewer = viewer;

            addDoubleClickListener();
            addExpandListener();
            createLWESelectionListener();
        }
    }

    /**
     * Creates and initializes the
     * {@link SiriusDialectLinkWithEditorSelectionListener}.
     */
    private void createLWESelectionListener() {
        if (myViewer instanceof CommonViewer) {
            CommonNavigator commonNavigator = ((CommonViewer) myViewer).getCommonNavigator();
            linkWithEditorSelectionListener = new SiriusDialectLinkWithEditorSelectionListener(commonNavigator);
            linkWithEditorSelectionListener.init();
        }
    }

    private void addDoubleClickListener() {
        if (myViewer instanceof StructuredViewer) {
            ((StructuredViewer) myViewer).addDoubleClickListener(doubleClickListener);
        }
    }

    private void removeDoubleClickListener() {
        if (myViewer instanceof StructuredViewer) {
            ((StructuredViewer) myViewer).removeDoubleClickListener(doubleClickListener);
        }
    }

    private void addExpandListener() {
        if (myViewer instanceof AbstractTreeViewer) {
            ((AbstractTreeViewer) myViewer).addTreeListener(expandListener);
        }
    }

    private void removeExpandListener() {
        if (myViewer instanceof StructuredViewer) {
            ((AbstractTreeViewer) myViewer).removeTreeListener(expandListener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        removeDoubleClickListener();
        removeExpandListener();
        myViewer = null;

        defaultContentProvider = null;

        for (RefreshViewerTrigger trigger : viewerRefreshTriggers) {
            Session session = trigger.getSession();
            if (session != null && session.getTransactionalEditingDomain() != null) {
                session.getTransactionalEditingDomain().removeResourceSetListener(trigger);
            }
        }
        viewerRefreshTriggers.clear();

        if (sessionManagerListener != null) {
            SessionManager.INSTANCE.removeSessionsListener(sessionManagerListener);
            sessionManagerListener = null;
        }
        linkWithEditorSelectionListener.dispose();
        linkWithEditorSelectionListener = null;
    }

    /**
     * Refresh the current view to make sure that changes in element's
     * permissions causes the refresh of expected decorations.
     * 
     * Try to refresh only the impacted projects : look for projects containing
     * at least one file linked to a resource handled by the session (aird,
     * semantics).
     * 
     * If the list of element to refresh is null or empty or if the viewer is
     * not capable to update a collection of object : refresh all the viewer.
     * 
     * @param session
     *            session to refresh
     */
    private void refreshViewer(Session session) {
        refreshViewer(Collections.singleton(session));
    }

    /**
     * Update the current view to make sure that changes in element's
     * permissions causes the update of expected decorations.
     * 
     * Try to Update only the impacted projects : look for projects containing
     * at least one file linked to a resource handled by the session (aird,
     * semantics).
     * 
     * If the list of element to refresh is null or empty or if the viewer is
     * not capable to update a collection of object : refresh all the viewer.
     * 
     * @see StructuredViewer#update(Object, String[])
     * 
     * @param session
     *            session to refresh
     */
    private void updateViewer(Session session) {
        Collection<IResource> resourcesToUpdate = Sets.newLinkedHashSet();

        if (session != null) {
            Iterable<Resource> resources = Iterables.concat(session.getAllSessionResources(), session.getSemanticResources());
            if (session instanceof DAnalysisSessionEObject) {
                resources = Iterables.concat(resources, ((DAnalysisSessionEObject) session).getControlledResources());
            }

            for (Resource res : resources) {
                if (res.getURI() != null && res.getURI().isPlatformResource()) {
                    IFile file = WorkspaceSynchronizer.getFile(res);
                    if (file != null && file.exists() && file.getProject() != null) {
                        resourcesToUpdate.add(file);
                        resourcesToUpdate.add(file.getProject());
                    }
                }
            }
        }

        updateViewer(resourcesToUpdate);
    }

    /**
     * Update the current view to make sure that changes in element's
     * permissions causes the refresh of expected decorations.
     * 
     * If the list of element to refresh is null or empty or if the viewer is
     * not capable to update a collection of object : refresh all the viewer.
     * 
     * @param elementsToRefresh
     *            the list of elements to refresh
     */
    private void updateViewer(final Collection<?> toUpdate) {
        Runnable updateViewer = new Runnable() {
            @Override
            public void run() {
                if (myViewer instanceof StructuredViewer && toUpdate != null && !toUpdate.isEmpty()) {
                    ((StructuredViewer) myViewer).update(toUpdate.toArray(), null);
                } else {
                    myViewer.refresh();
                }
            }
        };
        executeRunnables(Lists.newArrayList(updateViewer));
    }

    /**
     * Refresh the current view to make sure that changes in element's
     * permissions causes the refresh of expected decorations.
     * 
     * If the list of element to refresh is null or empty or if the viewer is
     * not capable to update a collection of object : refresh all the viewer.
     * 
     * @param toRefresh
     *            the list of elements to refresh
     */
    private void refreshViewer(final Collection<?> toRefresh) {
        Runnable refreshViewer = new Runnable() {
            @Override
            public void run() {
                try {
                    if (myViewer instanceof StructuredViewer && toRefresh != null && !toRefresh.isEmpty()) {
                        for (Object toRef : toRefresh) {
                            if (toRef instanceof Session) {
                                Session session = (Session) toRef;
                                Set<IProject> projectsToRefresh = getProjectsToRefresh(session);
                                for (IProject projectToRefresh : projectsToRefresh) {
                                    ((StructuredViewer) myViewer).refresh(projectToRefresh, true);
                                }
                            } else {
                                ((StructuredViewer) myViewer).refresh(toRef, true);
                            }
                        }
                    } else if (myViewer != null) {
                        myViewer.refresh();
                    }
                } catch (IllegalStateException e) {
                    // Can occur when trying to refresh the content of a
                    // Collaborative Session being closed (silent catch)
                } catch (NullPointerException e) {
                    // Can occur when trying to refresh the content of a
                    // Collaborative Session being closed (silent catch)
                }
            }
        };
        executeRunnables(Lists.newArrayList(refreshViewer));
    }

    private Set<IProject> getProjectsToRefresh(Session session) {
        Set<IProject> projectsToRefresh = Sets.newHashSet();

        if (session != null) {
            Iterable<Resource> resources = Iterables.concat(session.getAllSessionResources(), session.getSemanticResources());
            if (session instanceof DAnalysisSessionEObject) {
                resources = Iterables.concat(resources, ((DAnalysisSessionEObject) session).getControlledResources());
            }
            // Can occurs during session close : the previous lists were already
            // emptied.
            if (!Iterables.contains(resources, session.getSessionResource())) {
                resources = Iterables.concat(resources, Collections.singletonList(session.getSessionResource()));
            }

            for (Resource res : resources) {
                try {
                    if (res.getURI() != null && res.getURI().isPlatformResource()) {
                        IFile file = WorkspaceSynchronizer.getFile(res);
                        if (file != null && file.exists() && file.getProject() != null) {
                            projectsToRefresh.add(file.getProject());
                        }
                    }
                } catch (IllegalStateException e) {
                    // In case cdoView is closed
                }
            }
        }
        return projectsToRefresh;
    }

    private void executeRunnables(final Collection<Runnable> runnables) {
        if (myViewer == null) {
            synchronized (this) {
                pendingUpdates = null;
            }
            return;
        }

        // now post all collected runnables
        Control ctrl = myViewer.getControl();
        if (ctrl != null && !ctrl.isDisposed()) {

            // Are there some pending updates ?
            final boolean hasPendingUpdates;
            synchronized (this) {
                hasPendingUpdates = pendingUpdates != null && !pendingUpdates.isEmpty();
            }

            // Are we in the UIThread? If so spin it until we are done
            boolean inUIThread = ctrl.getDisplay().getThread() == Thread.currentThread();

            if (!hasPendingUpdates && inUIThread && !isViewerBusy()) {
                runUpdates(runnables);
            } else {
                synchronized (this) {
                    if (pendingUpdates == null) {
                        pendingUpdates = runnables;
                    } else {
                        pendingUpdates.addAll(runnables);
                    }
                }
                postAsyncUpdate(ctrl.getDisplay());
            }
        }
    }

    private boolean isViewerBusy() {
        boolean viewerIsBusy = false;

        // ColumnViewer.isBusy was not public before 3.4
        if (myViewer != null) {
            Method method = null;
            try {
                method = myViewer.getClass().getMethod("isBusy"); //$NON-NLS-1$
            } catch (SecurityException e) {
                // No method, no data
            } catch (NoSuchMethodException e) {
                // No method, no data
            }

            if (method != null) {
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                try {
                    Object data = method.invoke(myViewer);
                    if (data instanceof Boolean) {
                        viewerIsBusy = ((Boolean) data).booleanValue();
                    }
                } catch (IllegalArgumentException e) {
                    // No access, no data
                } catch (IllegalAccessException e) {
                    // No access, no data
                } catch (InvocationTargetException e) {
                    // No access, no data
                }
            }

        }
        return viewerIsBusy;
    }

    private void postAsyncUpdate(final Display display) {
        if (updateJob == null) {
            updateJob = new UIJob(display, "Async viewer updates") {
                @Override
                public IStatus runInUIThread(IProgressMonitor monitor) {
                    if (isViewerBusy()) {
                        schedule(100);
                    } else {
                        runPendingUpdates();
                    }
                    return Status.OK_STATUS;
                }
            };
            updateJob.setSystem(true);
        }
        updateJob.schedule();
    }

    /**
     * Run all of the runnables that are the widget updates. Must be called in
     * the display thread.
     */
    private void runPendingUpdates() {
        Collection<Runnable> updates;
        synchronized (this) {
            updates = pendingUpdates;
            pendingUpdates = null;
        }
        if (updates != null && myViewer != null) {
            Control control = myViewer.getControl();
            if (control != null && !control.isDisposed()) {
                runUpdates(updates);
            }
        }
    }

    private void runUpdates(Collection<Runnable> runnables) {
        if (runnables != null) {
            for (Runnable r : runnables) {
                r.run();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveState(IMemento aMemento) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreState(IMemento aMemento) {
        // Do nothing
    }

    private Resource getSemanticResource(IFile file, Session session) {
        if (session != null) {
            URI fileURI = getFileUri(file);
            for (Resource resource : Lists.newArrayList(session.getSemanticResources())) {
                if (resource.getURI().equals(fileURI)) {
                    return resource;
                }
            }
        }
        return null;
    }

    private Resource getControlledResource(IFile file, Session session) {
        if (session instanceof DAnalysisSessionEObject) {
            URI fileURI = getFileUri(file);
            for (Resource resource : ((DAnalysisSessionEObject) session).getControlledResources()) {
                if (resource.getURI().equals(fileURI)) {
                    return resource;
                }
            }
        }
        return null;
    }

    /**
     * Get the EMF URI associated to the given workspace file.
     * 
     * @param file
     *            the workspace file
     * @return an EMF URI
     */
    private URI getFileUri(IFile file) {
        return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
    }

    private Resource getAnalysisResource(IFile file, Session session) {
        if (session != null) {
            final URI fileURI = getFileUri(file);
            for (final Resource resource : session.getAllSessionResources()) {
                if (resource.getURI().equals(fileURI)) {
                    return resource;
                }
            }
        }
        return null;
    }

    /**
     * Session Manager Listener.
     */
    private class CommonSessionManagerListener implements SessionManagerListener {
        @Override
        public void notifyAddSession(Session newSession) {
            /* does nothing. */
        }

        @Override
        public void notifyRemoveSession(Session removedSession) {
            removeRefreshViewerTriggers(removedSession);
            refreshViewer(removedSession);
        }

        @Override
        public void viewpointSelected(Viewpoint selectedSirius) {
            /* does nothing. */
        }

        @Override
        public void viewpointDeselected(Viewpoint deselectedSirius) {
            // does nothing.
        }

        @Override
        public void notify(Session updated, int notification) {
            switch (notification) {
            case SessionListener.REPRESENTATION_CHANGE:
            case SessionListener.SEMANTIC_CHANGE:
            case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
            case SessionListener.VSM_UPDATED:
            case SessionListener.REPLACED:
                refreshViewer(updated);
                break;

            case SessionListener.SYNC:
            case SessionListener.DIRTY:
                updateViewer(updated);
                break;

            case SessionListener.OPENED:
                refreshViewer(updated);
                addRefreshViewerTrigger(updated);
                break;

            default:
                // do nothing as we will be notified in other way
                break;
            }
        }
    }

    /**
     * Predicate to test if a session is transient.
     */
    public static class TransientSessionPredicate implements Predicate<Session> {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(Session input) {
            return new URIQuery(input.getSessionResource().getURI()).isInMemoryURI();
        }
    }

    /**
     * Post commit listener to trigger viewer update/refresh after semantic
     * changes or representation renaming.
     */
    private class RefreshViewerTrigger extends ResourceSetListenerImpl {
        private final Session session;

        public RefreshViewerTrigger(Session openedSession) {
            super(NotificationFilter.NOT_TOUCH);
            session = openedSession;
        }

        public Session getSession() {
            return session;
        }

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            Collection<Notification> notifications = Lists.newArrayList(Iterables.filter(Iterables.filter(event.getNotifications(), Notification.class), new RefreshViewerTriggerScope(session)));

            Function<Notification, Object> notifToNotifier = new Function<Notification, Object>() {
                @Override
                public Object apply(Notification from) {
                    return from.getNotifier();
                }
            };
            Collection<EObject> impactedElements = Lists.newArrayList(Iterables.filter(Iterables.transform(notifications, notifToNotifier), EObject.class));

            if (!impactedElements.isEmpty()) {
                boolean needRefresh = shouldRefresh(notifications, impactedElements);

                if (needRefresh) {
                    refreshViewer(impactedElements);
                } else {
                    updateViewer(impactedElements);
                }
            }
        }

        private boolean shouldRefresh(Iterable<Notification> notifications, Collection<EObject> impactedElements) {
            for (Notification n : notifications) {
                if (n.getNotifier() != null && impactedElements.contains(n.getNotifier())) {
                    switch (n.getEventType()) {
                    case Notification.ADD:
                    case Notification.ADD_MANY:
                    case Notification.REMOVE:
                    case Notification.REMOVE_MANY:
                    case Notification.MOVE:
                    case Notification.RESOLVE:
                    case Notification.SET:
                    case Notification.UNSET:
                        if (n.getFeature() instanceof EReference && ((EReference) n.getFeature()).isContainment()) {
                            return true;
                        }
                        break;

                    default:
                        break;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }
    }

    /**
     * Scope to trigger viewer update/refresh after semantic changes or
     * representation renaming.
     */
    private class RefreshViewerTriggerScope implements Predicate<Notification> {
        private final Session session;

        private Set<Resource> allSemanticResources = Sets.newHashSet();

        public RefreshViewerTriggerScope(Session session) {
            this.session = session;
            allSemanticResources = Sets.newHashSet();
            allSemanticResources.addAll(session.getSemanticResources());
            if (session instanceof DAnalysisSessionEObject) {
                allSemanticResources.addAll(((DAnalysisSessionEObject) session).getControlledResources());
            }
        }

        @Override
        public boolean apply(Notification notification) {
            Object notifier = notification.getNotifier();
            boolean result = false;

            if (notifier instanceof DRepresentation && ViewpointPackage.eINSTANCE.getDRepresentation_Name().equals(notification.getFeature())) {
                result = true;
            }
            if (!result && notifier instanceof Resource && Resource.RESOURCE__CONTENTS == notification.getFeatureID(Resource.class)) {
                result = isSemanticChange((Resource) notifier);
            } else if (!result && notifier instanceof EObject) {
                Resource notifierResource = ((EObject) notifier).eResource();
                if (notifierResource != null && session != null && session.isOpen()) {
                    result = isSemanticChange(notifierResource);
                }
            }

            // semantic has changed, viewer should be refreshed.
            return !notification.isTouch() && result;
        }

        private boolean isSemanticChange(Resource resource) {
            boolean result = false;
            if (resource != null) {
                allSemanticResources.contains(resource);
            }
            return result;
        }

    }
}
