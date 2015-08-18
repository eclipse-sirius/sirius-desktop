/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.EditingSessionEvent;
import org.eclipse.sirius.ui.business.api.session.EditorNameAdapter;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.internal.dialect.editor.DialectEditorCloser;
import org.eclipse.sirius.ui.tools.internal.util.SessionCallBackWithUI;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.part.FileEditorInput;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * An {@link EditingSession} is responsible of keeping track of the opened
 * editors on a given model and cleaning stuffs when the every editor is closed.
 * 
 * @author cbrun
 */
public class EditingSession implements IEditingSession, ISaveablesSource, RefreshFilter {

    /** The Saveable for the Session. */
    protected Saveable saveable;

    /** Editors that focus a specific diagram file. */
    private final List<DialectEditor> editors = new ArrayList<DialectEditor>();

    private Map<DialectEditor, DialectEditorCloser> dialectEditorClosers = new HashMap<DialectEditor, DialectEditorCloser>();

    private NeedSaveOnCloseDetector needSaveOnCloseDetec = new NeedSaveOnCloseDetector();

    private boolean opened;

    private final Session session;

    /**
     * The adapter used to update name of editors on update of diagram's names
     */
    private EditorNameAdapter editorNameAdapter;

    private RestoreToLastSavePointListener restoreToSavePointListener;

    private SaveSessionWhenNoDialectEditorsListener saveSessionListener;

    /**
     * Create a new {@link EditingSession}.
     * 
     * @param session
     *            the session
     */
    public EditingSession(final Session session) {
        this.session = session;
        this.saveable = new SessionSaveable(session);
        editorNameAdapter = new EditorNameAdapter(this);
        session.setReloadingPolicy(new ReloadingPolicyImpl(new SessionCallBackWithUI()));
    }

    private void initListeners() {
        this.restoreToSavePointListener = new RestoreToLastSavePointListener(session);
        this.saveSessionListener = new SaveSessionWhenNoDialectEditorsListener(session);
        this.saveSessionListener.register();
    }

    private void removeListeners() {
        if (restoreToSavePointListener != null) {
            restoreToSavePointListener.dispose();
            restoreToSavePointListener = null;
        }

        if (this.saveSessionListener != null) {
            this.saveSessionListener.unregister();
            this.saveSessionListener = null;
        }
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Collection<DialectEditor> getEditors() {
        return editors;
    }

    @Override
    public void attachEditor(final DialectEditor dialectEditor) {
        if (!editors.contains(dialectEditor) && dialectEditor != null) {
            editors.add(dialectEditor);
            editorNameAdapter.registerEditor(dialectEditor);
            needSaveOnCloseDetec.reInit();

            reorderEditorsIfNeeded(dialectEditor);

            dialectEditorClosers.put(dialectEditor, new DialectEditorCloser(this, dialectEditor));

        }
    }

    private void reorderEditorsIfNeeded(DialectEditor justAddedEditor) {
        List<DialectEditor> reorderedList = Lists.newArrayList();
        IEditorReference[] editorReferences = null;

        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            editorReferences = page.getEditorReferences();
        }

        if (editorReferences != null) {
            for (IEditorReference ref : Lists.newArrayList(editorReferences)) {
                IEditorPart editor2 = ref.getEditor(false);

                IEditorInput refInput = null;
                try {
                    refInput = ref.getEditorInput();
                } catch (PartInitException e) {
                    // Can happen during eclipse launch.
                }
                if (editor2 == null && justAddedEditor.getEditorInput() == refInput) {
                    reorderedList.add(justAddedEditor);
                } else if (editors.contains(editor2)) {
                    reorderedList.add((DialectEditor) editor2);
                }
            }

            if (editors.size() == reorderedList.size() && !Iterables.elementsEqual(editors, reorderedList)) {
                editors.clear();
                editors.addAll(reorderedList);
            }
        }
    }

    @Override
    public void detachEditor(final DialectEditor dialectEditor) {
        editors.remove(dialectEditor);
        editorNameAdapter.unregisterEditor(dialectEditor);
        needSaveOnCloseDetec.reInit();

        DialectEditorCloser dialectEditorCloser = dialectEditorClosers.remove(dialectEditor);
        if (dialectEditorCloser != null) {
            dialectEditorCloser.dispose();
        }
    }

    @Override
    public void detachEditor(final DialectEditor dialectEditor, boolean revertChanges) {

        // We need to compute the closeAllDetected() before to execute the
        // detachEditor since this editor will be removed from the list.
        boolean returnToSyncState = revertChanges && (getEditors().size() == 1 || closeAllDetected());
        detachEditor(dialectEditor);
        if (returnToSyncState) {
            restoreToSavePointListener.returnToSyncState();
        }
    }

    @Override
    public void closeEditors(final boolean save, final Collection<? extends DialectEditor> editorParts) {
        for (final DialectEditor editor : new ArrayList<DialectEditor>(editorParts)) {
            closeEditor(editor, save);
        }
    }

    @Override
    public void closeEditors(final boolean save, final DialectEditor... editorParts) {
        closeEditors(save, Arrays.asList(editorParts));
    }

    private void closeEditor(final DialectEditor editor, final boolean save) {
        if (DialectUIManager.INSTANCE.canHandleEditor(editor)) {
            try {
                detachEditor(editor);
            } catch (IllegalStateException e) {
                // In case of CDO server shutdown
                SiriusEditPlugin.getPlugin().log(e);
            } finally {
                DialectUIManager.INSTANCE.closeEditor(editor, save);
            }
        } else {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editor, save);
        }
    }

    @Override
    public boolean isEmpty() {
        return editors.size() == 0;
    }

    @Override
    public boolean isLastOpenedEditor() {
        return editors.size() == 1;
    }

    @Override
    public boolean needToBeSavedOnClose(final IEditorPart editor) {
        return needSaveOnCloseDetec.needToBeSavedOnClose(editor);
    }

    @Override
    public int promptToSaveOnClose() {
        int choice = ISaveablePart2.DEFAULT;
        if (saveable != null) {
            boolean stillOpenElsewhere = getEditors().size() > 1 && !closeAllDetected();
            boolean promptStandardDialog = !restoreToSavePointListener.isAllowedToReturnToSyncState();

            choice = SWTUtil.showSaveDialog(session, saveable.getName(), true, stillOpenElsewhere, promptStandardDialog);

            if (choice == ISaveablePart2.CANCEL) {
                needSaveOnCloseDetec.reInit();
            }
        }
        return choice;
    }

    private boolean closeAllDetected() {
        return needSaveOnCloseDetec.closeAllDetected();
    }

    @Override
    public void close() {
        if (opened) {
            close(true);
        }
    }

    @Override
    public void close(final boolean save) {
        if (opened) {
            if (!isEmpty()) {
                closeEditors(save, editors);
            }
            RefreshFilterManager.INSTANCE.removeRefreshFilter(this);
            removeListeners();
            opened = false;
        }
        /*
         * close editors not yet registered with an UI session
         */
        closeOthersEditors(save);
    }

    private void closeOthersEditors(final boolean save) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                final IWorkbenchPage page = EclipseUIUtil.getActivePage();
                if (page != null) {
                    final IEditorReference[] editorReferences = page.getEditorReferences();
                    final List<IEditorReference> editorsToClose = new ArrayList<IEditorReference>();
                    for (final IEditorReference editor : editorReferences) {
                        try {
                            final IEditorInput input = editor.getEditorInput();
                            final IEditorPart editorPart = editor.getEditor(false);
                            if (editorPart == null && input instanceof URIEditorInput) {
                                if (shouldCloseEditor((URIEditorInput) input)) {
                                    editorsToClose.add(editor);
                                }
                            }
                        } catch (final PartInitException e) {
                            // do nothing
                        }
                    }

                    if (!editorsToClose.isEmpty()) {
                        final IEditorReference[] toClose = editorsToClose.toArray(new IEditorReference[editorsToClose.size()]);
                        page.closeEditors(toClose, save);
                    }
                }
            }

            private boolean shouldCloseEditor(URIEditorInput input) {
                if (session instanceof DAnalysisSession) {
                    for (final Resource resource : ((DAnalysisSession) session).getAllSessionResources()) {
                        if (resource.getURI() != null && resource.getURI().equals(input.getURI().trimFragment())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void open() {
        if (!opened) {
            RefreshFilterManager.INSTANCE.addRefreshFilter(this);
            initListeners();
            opened = true;
        }
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public boolean isSessionFor(final IEditorInput editorInput) {
        boolean result = false;
        final String key = keyFromInput(editorInput);
        if (key != null) {
            for (final IEditorPart editorPart : this.editors) {
                if (key.equals(keyFromInput(editorPart.getEditorInput()))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Returns a key with the editor input.
     * 
     * @param input
     *            the input.
     * @return a key with the editor input.
     */
    private String keyFromInput(final IEditorInput input) {
        final String separator = "/"; //$NON-NLS-1$
        String result = null;
        if (input instanceof URIEditorInput) {
            final URI uri = ((URIEditorInput) input).getURI();
            final URI deresolved = uri.deresolve(URI.createURI("platform:/resource/")); //$NON-NLS-1$
            final String workspacePAth = separator + deresolved.path();
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePAth));
            if (file != null && file.isAccessible() && file.exists()) {
                String filePath = file.getLocation().toString();
                if (filePath.startsWith(separator)) {
                    filePath = filePath.substring(1);
                }
                result = filePath;
            }
            if (result == null) {
                String path = uri.path();
                /*
                 * On windows we get some "/C:" uri ! remove that leading "/"
                 */
                if (path.startsWith(separator)) {
                    path = path.substring(1);
                }
                result = path;
            }
        }
        if (result == null && input instanceof IPathEditorInput) {
            result = ((IPathEditorInput) input).getPath().toString();
        }
        if (result == null && input instanceof FileEditorInput) {
            final java.net.URI uri = ((FileEditorInput) input).getURI();
            String path = uri.getPath();
            /*
             * On windows we get some "/C:" uri ! remove that leading "/"
             */
            if (path.startsWith(separator)) {
                path = path.substring(1);
            }
            result = path;
        }
        if (result == null) {
            result = input.toString();
        }
        return result;
    }

    @Override
    public DialectEditor getEditor(final DRepresentation representation) {
        for (final DialectEditor editorPart : this.editors) {
            if (DialectUIManager.INSTANCE.isRepresentationManagedByEditor(representation, editorPart)) {
                return editorPart;
            }
        }
        return null;
    }

    @Override
    public boolean handleEditor(final IEditorPart editor) {
        return this.editors.contains(editor);
    }

    @Override
    public Collection<DRepresentation> getOpenedRepresantationsToRefresh() {
        Collection<DRepresentation> openedRepresantationsToRefresh = new ArrayList<DRepresentation>();
        for (DialectEditor dialectEditor : getEditors()) {
            DRepresentation dRepresentation = dialectEditor.getRepresentation();
            if (dRepresentation != null) {
                openedRepresantationsToRefresh.add(dRepresentation);
            }
        }
        return openedRepresantationsToRefresh;
    }

    @Override
    public boolean shouldRefresh(DRepresentation representation) {
        return getOpenedRepresantationsToRefresh().contains(representation);
    }

    /**
     * Helper to detect whether the contents of this part should be saved when
     * the given part is closed.
     * 
     * The needToBeSavedOnClose method is called just before closing the
     * editors, re-initialization of the detector on attachment/detachment of an
     * editor with its EditingSession allow to safely detect close all, close
     * other and close workbench events.
     * 
     * @see IEditingSession#needToBeSavedOnClose(IEditorPart)
     * 
     * @author mporhel
     */
    private class NeedSaveOnCloseDetector {

        private Set<IEditorPart> closingEditors = Sets.newHashSet();

        /**
         * 
         * @see IEditingSession#needToBeSavedOnClose(IEditorPart)
         * 
         */
        public boolean needToBeSavedOnClose(IEditorPart editor) {
            boolean needToBeSavedOnClose = isLastOpenedEditor();

            // Closing the workbench case is also handled by the counter
            // || ((PlatformUI.getWorkbench().isClosing()) &&
            // editors.indexOf(editor) == 0);

            if (!needToBeSavedOnClose) {
                IPreferenceStore platformUIPrefStore = PlatformUI.getPreferenceStore();
                boolean closeAll = checkCloseAllEditor(editor);
                needToBeSavedOnClose = platformUIPrefStore.getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN) || closeAll;
            }

            return needToBeSavedOnClose;
        }

        public boolean closeAllDetected() {
            return closingEditors.size() == editors.size();
        }

        /**
         * Try to detect closing of all editors on a same session from system
         * actions:
         * <ol>
         * <li>close all</li>
         * <li>close other</li>
         * <li>close workbench</li>
         * </ol>
         * 
         * It check that all editor of the given session are receiving the
         * 
         * @param editor
         *            the editor being closed.
         * @return true if "close all" editors of the given session is detected.
         */
        private boolean checkCloseAllEditor(IEditorPart editor) {
            if (editors.indexOf(editor) == 0) {
                reInit();
                closingEditors.add(editor);
            } else if (closingEditors.size() > 0) {
                closingEditors.add(editor);
            }
            return closeAllDetected();
        }

        /**
         * Re-init detector.
         */
        public void reInit() {
            closingEditors.clear();
            closingEditors = Sets.newHashSet();
        }

    }

    @Override
    public Saveable[] getSaveables() {
        return saveable != null ? new Saveable[] { saveable } : new Saveable[0];
    }

    @Override
    public Saveable[] getActiveSaveables() {
        return getSaveables();
    }

    @Override
    public void notify(EditingSessionEvent event) {
        saveSessionListener.notify(event);
    }
}
