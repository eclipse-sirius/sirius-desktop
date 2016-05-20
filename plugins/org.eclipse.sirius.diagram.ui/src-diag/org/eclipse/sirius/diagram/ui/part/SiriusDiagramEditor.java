/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.part;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * @not-generated
 */
public class SiriusDiagramEditor extends DiagramDocumentEditor implements IGotoMarker, IAdaptable {

    /**
     * @was-generated
     */
    public static final String CONTEXT_ID = "org.eclipse.sirius.diagram.ui.diagramContext"; //$NON-NLS-1$

    /**
     * @not-generated
     */
    public SiriusDiagramEditor() {
        super(true);
    }

    /**
     * @was-generated
     */
    @Override
    protected String getContextID() {
        return CONTEXT_ID;
    }

    private PaletteRoot previousPalette = null;

    /**
     * @not-generated
     */
    @Override
    protected PaletteRoot createPaletteRoot(final PaletteRoot existingPaletteRoot) {
        if (previousPalette == null)
            previousPalette = super.createPaletteRoot(existingPaletteRoot);
        return previousPalette;
    }

    /**
     * @was-generated
     */
    @Override
    protected PreferencesHint getPreferencesHint() {
        return DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT;
    }

    /**
     * @was-generated
     */
    @Override
    public String getContributorId() {
        return DiagramUIPlugin.ID;
    }

    /**
     * @was-generated
     */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {

        if (type == IShowInTargetList.class) {
            return new IShowInTargetList() {
                @Override
                public String[] getShowInTargetIds() {
                    return new String[] { ProjectExplorer.VIEW_ID, IModelExplorerView.ID };
                }
            };
        }
        if (type == IPropertySheetPage.class) {
            IPropertySheetPage contributedPage = SiriusEditPlugin.getPlugin().getPropertySheetPage(this, getContributorId());
            if (contributedPage != null) {
                return contributedPage;
            }
        }
        if (type == AdapterFactory.class) {
            return DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
        }
        return super.getAdapter(type);
    }

    /**
     * @was-generated
     */
    @Override
    public TransactionalEditingDomain getEditingDomain() {
        final IDocument document = getEditorInput() != null ? getDocumentProvider().getDocument(getEditorInput()) : null;
        if (document instanceof IDiagramDocument) {
            return ((IDiagramDocument) document).getEditingDomain();
        }
        return super.getEditingDomain();
    }

    /**
     * @was-generated
     */
    @Override
    public void gotoMarker(final IMarker marker) {
        MarkerNavigationService.getInstance().gotoMarker(this, marker);
    }

    /**
     * Overridden to not have superclass calls "ViewUtil.getIdStr(getDiagram())"
     * which cast InMemoryResourceImpl in XMLResource.
     */
    /**
     * Returns the workspace viewer <code>PreferenceStore</code>
     * 
     * @return the workspace viewer <code>PreferenceStore</code>
     */
    @Override
    public PreferenceStore getWorkspaceViewerPreferenceStore() {
        if (workspaceViewerPreferenceStore != null) {
            return workspaceViewerPreferenceStore;
        } else {
            // Try to load it
            IPath path = DiagramUIPlugin.getPlugin().getStateLocation();
            String viewId = SiriusGMFHelper.getViewId(getDiagram());

            String fileName = path.toString() + "/" + viewId;//$NON-NLS-1$
            java.io.File file = new File(fileName);
            workspaceViewerPreferenceStore = new PreferenceStore(fileName);
            if (file.exists()) {
                // Load it
                try {
                    workspaceViewerPreferenceStore.load();
                } catch (Exception e) {
                    // Create the default
                    addDefaultPreferences();
                }
            } else {
                // Create it
                addDefaultPreferences();
            }
            return workspaceViewerPreferenceStore;
        }
    }

    /**
     * @not-generated Disable "Save As" for Sirius diagrams.
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * @not-generated Disable "Save As" for Sirius diagrams.
     */
    @Override
    public void doSaveAs() {
        // Do nothing.
    }

    /**
     * @not-generated Disable "Save As" for Sirius diagrams.
     */
    @Override
    protected void performSaveAs(final IProgressMonitor progressMonitor) {
        // Do nothing.
    }

    /**
     * @was-generated
     */
    @Override
    public ShowInContext getShowInContext() {
        return new ShowInContext(getEditorInput(), getNavigatorSelection());
    }

    /**
     * @was-generated NOT
     */
    private ISelection getNavigatorSelection() {
        if (getEditorInput() instanceof SessionEditorInput) {
            SessionEditorInput sessionEditorInput = (SessionEditorInput) getEditorInput();
            Session session = sessionEditorInput.getSession(false);
            if (session != null) {
                Resource sessionResource = session.getSessionResource();
                if (sessionResource != null && sessionResource.getURI().isPlatformResource()) {
                    final IFile file = WorkspaceSynchronizer.getFile(sessionResource);
                    if (file != null) {
                        return new StructuredSelection(file);
                    }
                }
            }
        }
        return StructuredSelection.EMPTY;
    }
}
