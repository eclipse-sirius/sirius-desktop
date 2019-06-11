/*******************************************************************************
 * Copyright (c) 2017, 2018 Obeo.
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
package org.eclipse.sirius.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.editor.ISiriusEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder.PageUpdateCommand;
import org.eclipse.sirius.ui.editor.internal.pages.PageProviderListener;
import org.eclipse.sirius.ui.editor.internal.pages.RemovePageCommand;
import org.eclipse.sirius.ui.editor.internal.pages.RenameTabLabelCommand;
import org.eclipse.sirius.ui.editor.internal.pages.ReorderPageCommand;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Editor use to open Session file (.aird files) available from everywhere. This
 * editor shows different properties/data about session that can be modified :
 * <ul>
 * <li>Viewpoint selection
 * <li>Semantic model browsing and management
 * <li>Representation browsing and management.
 * </ul>
 * 
 * @author jmallet
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditor extends SharedHeaderFormEditor implements ITabbedPropertySheetPageContributor, IModelExplorerView, SessionListener, ISiriusEditor, PageProviderListener {
    /**
     * This listener listens to any change in the session's resource set and
     * notifies all pages when such change happens.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class SessionResourceSetListener extends ResourceSetListenerImpl {
        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {

            int pageCount = SessionEditor.this.getPageCount();
            List<Runnable> commandsToExecute = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
                Object page = SessionEditor.this.pages.get(i);
                if (page instanceof AbstractSessionEditorPage) {
                    AbstractSessionEditorPage customPage = (AbstractSessionEditorPage) page;
                    Optional<NotificationFilter> notificationFilter = customPage.getFilterForPageRequesting();
                    boolean reactToNotification = event.getNotifications().stream().anyMatch(notification -> notificationFilter.isPresent() ? true : notificationFilter.get().matches(notification));
                    if (reactToNotification) {
                        Optional<PageUpdateCommand> updateCommand = customPage.resourceSetChanged(event);
                        commandsToExecute.addAll(prepareUpdateCommands(customPage, updateCommand));
                    }
                }
            }
            if (commandsToExecute.size() > 0) {
                PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                    commandsToExecute.stream().forEach(Runnable::run);
                    updatePages(event);
                });
            }
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }
    }

    /**
     * The editor's id.
     */
    public static final String EDITOR_ID = "org.eclipse.sirius.ui.editor.session"; //$NON-NLS-1$

    /**
     * Session opened with editor.
     */
    private Session session;

    /**
     * The property sheet page used by this editor.
     */
    private TabbedPropertySheetPage propertySheetPage;

    /**
     * A command stack listener refreshing the property sheet page when a model
     * change occurs so it reflects current model values.
     */
    private CommandStackListener listener;

    private int choice = ISaveablePart2.DEFAULT;

    /**
     * The registry providing custom pages to this editor.
     */
    private PageProviderRegistry pageRegistry;

    /**
     * This listener listens to any change in the session's resource set and
     * notifies all pages when such change happens.
     */
    private SessionResourceSetListener resourceSetListener;

    @Override
    protected void addPages() {
        updatePages(null);
    }

    /**
     * Remove obsolete page, add new pages and reorder all pages currently
     * displayed like the new list.
     * 
     * @param event
     * 
     */
    private void updatePages(ResourceSetChangeEvent event) {
        if (pageRegistry == null) {
            // Can happen if we're called via a stale listener after the editor
            // has already been closed (and pageRegistry set to null).
            return;
        }
        List<AbstractSessionEditorPage> newOrderedPages = pageRegistry.getPagesOrdered(this, session,
                pages.stream().filter(AbstractSessionEditorPage.class::isInstance).map(AbstractSessionEditorPage.class::cast).collect(Collectors.toList()), event);

        CTabFolder cTabF = (CTabFolder) this.getContainer();
        IFormPage lastActivePage = getActivePageInstance();
        for (int i = 0; i < newOrderedPages.size(); i++) {
            AbstractSessionEditorPage page = newOrderedPages.get(i);
            int pageIndex = pages.indexOf(page);
            if (pageIndex != -1) {
                if (pageIndex != i) {
                    updatePageTabPosition(cTabF, page, pageIndex, i);
                }
            } else {
                try {
                    addPage(i, page);
                } catch (PartInitException e) {
                    String errorMessage = MessageFormat.format(Messages.UI_SessionEditor_page_loading_error_message, new Object[0]);
                    SessionEditorPlugin.getPlugin().error(errorMessage, e);
                    ErrorDialog.openError(getSite().getShell(), errorMessage, e.getMessage(), e.getStatus());
                }
            }
        }
        if (newOrderedPages.size() < pages.size()) {
            // obsolete pages must be removed.
            for (int i = newOrderedPages.size(); i < pages.size(); i++) {
                if (lastActivePage.equals(pages.get(i))) {
                    lastActivePage = null;
                }
                removePage(i);
            }
        }

        cTabF.getParent().layout();
        if (getActivePageInstance() == null && lastActivePage != null) {
            setActivePage(lastActivePage);
        }
    }

    /**
     * Returns the pages this editor displays.
     * 
     * @return the pages this editor displays.
     */
    public List<IFormPage> getPages() {
        return pages.stream().filter(IFormPage.class::isInstance).map(IFormPage.class::cast).collect(Collectors.toList());
    }

    /**
     * Update the page tab position at the given original index to the new
     * target index.
     * 
     * @param cTabF
     *            the {@link CTabFolder} containing the {@link TabItem}.
     * @param page
     *            the page associated to the tab item to update.
     * @param originalPositionIndex
     *            the position index of the tab item to update.
     * @param targetPositionIndex
     *            the target index the tab item should have. the
     * 
     * 
     */
    private void updatePageTabPosition(CTabFolder cTabF, AbstractSessionEditorPage page, int originalPositionIndex, int targetPositionIndex) {
        // page already exists so we reuse its control in a new
        // tab to avoid loosing states like expanded items.
        cTabF.getItem(originalPositionIndex).dispose();
        pages.remove(originalPositionIndex);

        CTabItem item = new CTabItem(cTabF, SWT.NONE, targetPositionIndex);
        item.setText(page.getTitle());
        item.setControl(page.getPartControl());
        pages.add(targetPositionIndex, page);

        updatePageIndices(0);
    }

    /**
     * Update page indices.
     * 
     * @param start
     *            start index from which index updating is done.
     */
    private void updatePageIndices(int start) {
        for (int i = start; i < pages.size(); i++) {
            Object page = pages.get(i);
            if (page instanceof IFormPage) {
                IFormPage fpage = (IFormPage) page;
                fpage.setIndex(i);
            }
        }
    }

    /**
     * Set the given page as this editor active page if such page exists.
     * 
     * @param activePage
     *            the page to set as the active page of this editor.
     */
    public void setActivePage(IFormPage activePage) {
        if (activePage != null) {
            int activePageIndex = pages.indexOf(activePage);
            if (activePageIndex != -1) {
                setActivePage(activePageIndex);
            } else {
                SessionEditorPlugin.getPlugin().error(MessageFormat.format(Messages.SessionEditor_PageActivation_Failure, activePage.getClass().getName()), null);
            }
        }
    }

    /**
     * Set the currently active page.
     * 
     * @param pageIndex
     *            the index of the page to set as the active page of this
     *            editor.
     */
    @Override
    public void setActivePage(int pageIndex) {
        // we notifies all pages the change of page visibility.
        Object pageToSelect = pages.get(pageIndex);
        if (pageToSelect instanceof AbstractSessionEditorPage) {
            AbstractSessionEditorPage theSelectedPage = (AbstractSessionEditorPage) pageToSelect;
            Optional<PageUpdateCommand> selectedPageCommand = theSelectedPage.pageChanged(true);
            List<Runnable> commandsToExecute = new ArrayList<>();
            commandsToExecute.addAll(prepareUpdateCommands(theSelectedPage, selectedPageCommand));
            for (Object page : pages) {
                if (page instanceof AbstractSessionEditorPage) {
                    AbstractSessionEditorPage sessionEditorPage = (AbstractSessionEditorPage) page;
                    if (sessionEditorPage.getIndex() != theSelectedPage.getIndex()) {
                        Optional<PageUpdateCommand> pageCommand = sessionEditorPage.pageChanged(false);
                        commandsToExecute.addAll(prepareUpdateCommands(sessionEditorPage, pageCommand));
                    }
                }
            }
            commandsToExecute.stream().forEach(Runnable::run);

            getContainer().getParent().layout();
            int selectedPageIndex = pages.indexOf(pageToSelect);
            if (selectedPageIndex > -1) {
                super.setActivePage(selectedPageIndex);
            } else {
                super.setActivePage(0);
            }
        }
    }

    @Override
    public void pageProviderChanged() {
        IWorkbenchPartSite site = getSite();
        if (site != null && site.getShell() != null && !site.getShell().isDisposed()) {
            site.getShell().getDisplay().syncExec(() -> updatePages(null));
        }
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
        IEditorInput editorInput = this.getEditorInput();
        URI sessionResourceURI = null;
        if (editorInput instanceof FileEditorInput) {
            // provided when opened from a no modeling project.
            IFile sessionResourceFile = ((FileEditorInput) editorInput).getFile();
            sessionResourceURI = URI.createPlatformResourceURI(sessionResourceFile.getFullPath().toOSString(), true);
        } else if (editorInput instanceof URIEditorInput) {
            // provided when opened from a modeling project.
            sessionResourceURI = ((URIEditorInput) editorInput).getURI();
        } else {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_session_loading_error_message, new Object[0]),
                    MessageFormat.format(Messages.UI_SessionEditor_inputNotHandled_error_message, editorInput.getClass().getSimpleName()), Status.CANCEL_STATUS);
        }
        try {
            if (sessionResourceURI != null) {
                final URI sessionResourceURIFinal = sessionResourceURI;
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sessionResourceURIFinal.toPlatformString(true)));
                IProject project = file.getProject();
                if (ModelingProject.hasModelingProjectNature(project)) {
                    // we show the modeling project name
                    setPartName(sessionResourceURIFinal.segments()[1]);
                } else {
                    // we show as editor name the aird name and as description
                    // the aird full path relatively to the project.
                    setPartName(sessionResourceURIFinal.lastSegment());
                    setContentDescription(sessionResourceURIFinal.toPlatformString(true));
                }
                // until we find a way to load session independently from the
                // editor, session loading blocks the editor opening with a
                // progress monitor.
                IRunnableWithProgress exportAllRepresentationsRunnable = new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        SubMonitor subMonitor = SubMonitor.convert(monitor, 1);
                        subMonitor.beginTask(MessageFormat.format(Messages.UI_SessionEditor_session_loading_task_title, new Object[0]), 1);
                        session = SessionManager.INSTANCE.openSession(sessionResourceURIFinal, subMonitor, SiriusEditPlugin.getPlugin().getUiCallback(), true);
                        session.addListener(SessionEditor.this);

                        final IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
                        editingSession.open();
                        editingSession.attachEditor(SessionEditor.this);

                        resourceSetListener = new SessionResourceSetListener();
                        session.getTransactionalEditingDomain().addResourceSetListener(resourceSetListener);

                        subMonitor.worked(1);
                        subMonitor.done();

                    }
                };

                final ProgressMonitorDialog pmd = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
                pmd.run(false, false, exportAllRepresentationsRunnable);
                listener = new CommandStackListener() {
                    @Override
                    public void commandStackChanged(final EventObject event) {
                        getContainer().getDisplay().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                firePropertyChange(IEditorPart.PROP_DIRTY);

                                if (propertySheetPage == null || propertySheetPage.getControl() == null || propertySheetPage.getControl().isDisposed()) {
                                    // we clear the sheet page if it has been
                                    // disposed.
                                    propertySheetPage = null;
                                } else if (propertySheetPage.getCurrentTab() != null) {
                                    propertySheetPage.refresh();
                                }
                            }
                        });
                    }
                };
                session.getTransactionalEditingDomain().getCommandStack().addCommandStackListener(listener);
            }
        } catch (InvocationTargetException e) {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_session_loading_error_message, new Object[0]), e.getMessage(), Status.CANCEL_STATUS);
            // We cannot continue if one of those exception has been thrown ( if
            // the Session cannot be loaded for instance). We throw a
            // PartInitException.
            throw new PartInitException(e.getTargetException().getMessage(), e.getTargetException());

        } catch (InterruptedException e) {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_session_loading_error_message, new Object[0]), e.getMessage(), Status.CANCEL_STATUS);
            throw new PartInitException(e.getMessage(), e);
        }
        pageRegistry = SessionEditorPlugin.getPlugin().getPageRegistry();
        pageRegistry.addRegistryListener(this);
    }

    @Override
    public void dispose() {
        if (resourceSetListener != null && session != null && session.getTransactionalEditingDomain() != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(resourceSetListener);
            resourceSetListener = null;
        }
        if (session != null) {
            session.removeListener(SessionEditor.this);
            final IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
            if (editingSession != null) {
                editingSession.detachEditor(this, choice == ISaveablePart2.NO);
            }
            if (session.getTransactionalEditingDomain() != null) {
                session.getTransactionalEditingDomain().getCommandStack().removeCommandStackListener(listener);
            }
        }
        if (pageRegistry != null) {
            pageRegistry.removeRegistryListener(this);
            pageRegistry = null;
        }
        session = null;
        propertySheetPage = null;
        super.dispose();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (pages != null) {
            for (int i = 0; i < pages.size(); i++) {
                Object page = pages.get(i);
                if (page instanceof IFormPage) {
                    ((IFormPage) page).doSave(monitor);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdapter(Class type) {
        Object result = null;
        if (type == IPropertySheetPage.class) {
            IPropertySheetPage contributedPage = SiriusEditPlugin.getPlugin().getPropertySheetPage(this, getContributorId());
            if (contributedPage != null) {
                result = contributedPage;
            } else {
                propertySheetPage = new TabbedPropertySheetPage(this);
                result = propertySheetPage;
            }
        }
        if (result == null) {
            result = super.getAdapter(type);
        }
        return result;
    }

    @Override
    public void doSaveAs() {
        // not used
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public String getContributorId() {
        return ID;
    }

    @Override
    public void notify(int changeKind) {
        switch (changeKind) {
        case SessionListener.CLOSING:
            this.close(false);
            break;
        default:
            break;
        }
    }

    @Override
    public void gotoMarker(IMarker marker) {
    }

    @Override
    public EditingDomain getEditingDomain() {
        return session.getTransactionalEditingDomain();
    }

    @Override
    public Saveable[] getSaveables() {
        if (SessionUIManager.INSTANCE != null && session != null && session.isOpen()) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession instanceof ISaveablesSource) {
                return ((ISaveablesSource) uiSession).getSaveables();
            }
        }

        return new Saveable[0];
    }

    @Override
    public Saveable[] getActiveSaveables() {
        return getSaveables();
    }

    @Override
    public int promptToSaveOnClose() {
        choice = ISaveablePart2.DEFAULT;
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            // Close all && Still open elsewhere detection.
            if (uiSession != null && uiSession.needToBeSavedOnClose(this)) {
                choice = uiSession.promptToSaveOnClose();
            }
        }
        return choice;
    }

    /**
     * Returns the {@link Session} edited by this editor.
     * 
     * @return the {@link Session} edited by this editor.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Prepare all commands as {@link Runnable} from the given
     * {@link PageUpdateCommand}. If a remove command is present, other update
     * command will be ignored.
     * 
     * @param customPage
     *            the page updated.
     * @param updateCommand
     *            the commands this editor must execute to update the page.
     */
    private List<Runnable> prepareUpdateCommands(AbstractSessionEditorPage customPage, Optional<PageUpdateCommand> updateCommand) {
        List<Runnable> runnables = new ArrayList<>(2);
        updateCommand.ifPresent(updateCommandTemp -> {
            boolean removeCommandExecuted = false;
            if (updateCommandTemp.getRemoveCommand() != null) {
                runnables.add(prepareRemoveCommand(updateCommandTemp.getRemoveCommand(), SessionEditor.this, customPage));
                removeCommandExecuted = true;
            }
            if (!removeCommandExecuted && updateCommandTemp.getReorderCommand() != null) {
                runnables.add(prepareReorderCommand(updateCommandTemp.getReorderCommand(), SessionEditor.this, customPage));
            } else if (updateCommandTemp.getReorderCommand() != null) {
                SessionEditorPlugin.getPlugin().error(MessageFormat.format(Messages.SessionEditor_PageCommand_Integrity_Error, customPage.getClass().getName()), null);
            }
            if (!removeCommandExecuted && updateCommandTemp.getRenameCommand() != null) {
                runnables.add(prepareRenameCommand(updateCommandTemp.getRenameCommand(), SessionEditor.this, customPage));
            } else if (updateCommandTemp.getRenameCommand() != null) {
                SessionEditorPlugin.getPlugin().error(MessageFormat.format(Messages.SessionEditor_PageCommand_Integrity_Error, customPage.getClass().getName()), null);
            }
        });
        return runnables;
    }

    /**
     * Prepare the reorder command by returning a {@link Runnable}.
     * 
     * @param pageUpdateCommand
     *            the command to execute containing execution information.
     * @param editor
     *            the editor calling for a page reordering.
     * @param page
     *            the page reordered.
     */
    private Runnable prepareReorderCommand(ReorderPageCommand pageUpdateCommand, SessionEditor editor, AbstractSessionEditorPage page) {
        return () -> {
            Composite container = editor.getContainer();
            if (container instanceof CTabFolder) {
                CTabFolder tabFolder = (CTabFolder) editor.getContainer();
                int targetPageIndex = -1;
                String targetPageId = pageUpdateCommand.getTargetPageId();
                for (Object object : pages) {
                    if (object instanceof AbstractSessionEditorPage && targetPageId.equals(((AbstractSessionEditorPage) object).getId())) {
                        targetPageIndex = ((AbstractSessionEditorPage) object).getIndex();
                        break;
                    }
                }
                int commandingPageIndex = pages.indexOf(page);
                if (commandingPageIndex != -1 && targetPageIndex != -1) {
                    switch (pageUpdateCommand.getPositioningKind()) {
                    case AFTER:
                        if (targetPageIndex + 1 != commandingPageIndex) {
                            updatePageTabPosition(tabFolder, page, commandingPageIndex, targetPageIndex);
                        }
                        break;
                    case BEFORE:
                        if (commandingPageIndex + 1 != targetPageIndex) {
                            updatePageTabPosition(tabFolder, page, commandingPageIndex, targetPageIndex);
                        }
                        break;
                    case REPLACE:
                        editor.removePage(targetPageIndex);
                        commandingPageIndex = pages.indexOf(page);
                        int targetPageIndexAfterRemoval = targetPageIndex > 0 && targetPageIndex >= pages.size() ? targetPageIndex - 1 : targetPageIndex;
                        updatePageTabPosition(tabFolder, page, commandingPageIndex, targetPageIndexAfterRemoval);
                        break;
                    default:
                        break;
                    }
                }
            }
        };
    }

    /**
     * Prepare the page removal command for the given page by returning a
     * {@link Runnable}.
     * 
     * @param pageUpdateCommand
     *            the command containing removal information.
     * @param editor
     *            the editor calling for a page removal.
     * @param page
     *            the page that will be removed from editor.
     */
    private Runnable prepareRemoveCommand(RemovePageCommand pageUpdateCommand, SessionEditor editor, AbstractSessionEditorPage page) {
        return () -> {
            int pageToRemoveIndex = editor.pages.indexOf(page);
            // we don't remove a page not existing anymore. This can occurs if
            // many notifications leads to the same page removal.
            if (pageToRemoveIndex != -1) {
                editor.removePage(pageToRemoveIndex);
            }
        };
    }

    /**
     * Prepare the tab renaming command for the given page by returning a
     * {@link Runnable}.
     * 
     * @param pageUpdateCommand
     *            the command containing rename information.
     * @param editor
     *            the editor calling for a page tab renaming.
     * @param page
     *            the page that will have its tab renamed.
     */
    private Runnable prepareRenameCommand(RenameTabLabelCommand pageUpdateCommand, SessionEditor editor, AbstractSessionEditorPage page) {
        return () -> {
            Composite container = editor.getContainer();
            if (container instanceof CTabFolder) {
                Vector<Object> pages = editor.pages;
                int index = pages.indexOf(page);
                if (index != -1) {
                    CTabFolder tabFolder = (CTabFolder) container;
                    CTabItem tabItem = tabFolder.getItem(index);
                    tabItem.setText(pageUpdateCommand.getNewLabel());
                }
            }
        };
    }
}
