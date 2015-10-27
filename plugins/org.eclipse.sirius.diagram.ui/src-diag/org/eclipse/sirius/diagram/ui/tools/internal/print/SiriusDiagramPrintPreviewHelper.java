/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.PageBreakEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.printpreview.RenderedPrintPreviewHelper;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

/**
 * A specialized <code>RenderedPrintPreviewHelper</code> that supports printing
 * of Sirius diagrams.
 * 
 * @author mPorhel
 * 
 */
public class SiriusDiagramPrintPreviewHelper extends RenderedPrintPreviewHelper {

    private Shell vpTempShell;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.
     *      printpreview.RenderedPrintPreviewHelper ;
     */
    @Override
    protected DiagramEditPart getDiagramEditPart() {
        if (diagramEditPart == null && getDiagramEditorPart() != null) {
            final Diagram diagram = getDiagramEditorPart().getDiagram();
            if (diagram != null) {
                final PreferencesHint preferencesHint = getPreferencesHint(getDiagramEditorPart());
                final DiagramEditPartService tool = new DiagramEditPartService();
                diagramEditPart = tool.createDiagramEditPart(diagram, getVpTempShell(), preferencesHint);
                SiriusDiagramPrintPreviewHelper.initializePreferences(diagramEditPart, preferencesHint);
            }
        }
        return super.getDiagramEditPart();
    }

    private Shell getVpTempShell() {
        if (vpTempShell == null) {
            vpTempShell = new Shell();
        }
        return vpTempShell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispose() {
        vpTempShell.dispose();
        vpTempShell = null;
        super.dispose();
    }

    private DiagramEditor getDiagramEditorPart() {
        final IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
        if (editorPart instanceof SiriusDiagramEditor) {
            return (SiriusDiagramEditor) editorPart;
        }
        return null;
    }

    /*
     * the following code comes from
     * org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.PrintHelper
     * from gmf 1.0 so that we can get the code compiling with Eclipse 3.4
     * CHECKSTYLE:OFF
     */

    /**
     * Initialize the preferences for a diagram edit part, specifically
     * including page breaks and margins.
     * 
     * Typically, the diagram edit part is created using createDiagramEditPart()
     * and the diagram edit part is passed in as the first parameter of this
     * method.
     * 
     * @param diagramEditPart
     *            the DiagramEditPart to pass in
     * @param preferencesHint
     *            the preferences hint to use for intiializing the preferences
     * 
     * @return true if the preferences could be loaded, false if they weren't
     *         loaded and defaults had to be used instead
     */
    public static boolean initializePreferences(DiagramEditPart diagramEditPart, PreferencesHint preferencesHint) {
        assert diagramEditPart.getViewer() instanceof DiagramGraphicalViewer;

        DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) diagramEditPart.getViewer();

        boolean loadedPreferences = true;

        IPreferenceStore fPreferences = SiriusDiagramPrintPreviewHelper.getPreferenceStoreForDiagram(diagramEditPart);

        if (fPreferences == null) {
            loadedPreferences = false;
            // leave at default x and y
            PreferenceStore defaults = new PreferenceStore();
            DiagramEditor.addDefaultPreferences(defaults, preferencesHint);

            fPreferences = SiriusDiagramPrintPreviewHelper.getWorkspacePreferenceStore(preferencesHint);
        } else if (!fPreferences.getBoolean(WorkspaceViewerProperties.PREF_USE_DIAGRAM_SETTINGS)) {
            // if we aren't supposed to use the diagram settings, switch to the
            // workspace settings

            // we have to use the page break x and y settings from the diagram
            int x = fPreferences.getInt(WorkspaceViewerProperties.PAGEBREAK_X), y = fPreferences.getInt(WorkspaceViewerProperties.PAGEBREAK_Y);

            // minor performance optimization, use the existing
            // preferences from the workspace instead of making a new one
            fPreferences = SiriusDiagramPrintPreviewHelper.getWorkspacePreferenceStore(preferencesHint);
            fPreferences.setValue(WorkspaceViewerProperties.PAGEBREAK_X, x);
            fPreferences.setValue(WorkspaceViewerProperties.PAGEBREAK_Y, y);
        }

        viewer.hookWorkspacePreferenceStore(fPreferences);

        diagramEditPart.refreshPageBreaks();

        return loadedPreferences;
    }

    /**
     * Returns the workspace viewer <code>PreferenceStore</code> for a given
     * diagram edit part.
     * 
     * @param diagramEP
     *            the DiagramEditPart to obtain the preference store for
     * 
     * @return the <code>PreferenceStore</code> for the given diagram edit part
     *         Could return null if it couldn't be loaded
     */
    private static IPreferenceStore getPreferenceStoreForDiagram(DiagramEditPart diagramEP) {
        // Try to load it
        String id = ViewUtil.getIdStr(diagramEP.getDiagramView());

        // try and get preferences from the open diagrams first
        // loadedPreferences will be set to true only if the preferences could
        // be
        // successfully loaded
        IPreferenceStore fPreferences = SiriusDiagramPrintPreviewHelper.loadPreferencesFromOpenDiagram(id);
        if (fPreferences != null) {
            // loadPreferencesFromOpenDiagram will have set preferences
            return fPreferences;
        }

        IPath path = DiagramUIPlugin.getInstance().getStateLocation();

        String fileName = path.toString() + "/" + id;//$NON-NLS-1$
        java.io.File file = new File(fileName);
        fPreferences = new PreferenceStore(fileName);
        if (file.exists()) {
            // Load it
            try {
                ((PreferenceStore) fPreferences).load();

                return fPreferences;
            } catch (Exception e) {
                return null;
            }
        }
        return null; // fPreferences couldn't be loaded
    }

    /**
     * Return the preference store for the given PreferenceHint
     * 
     * @param preferencesHint
     *            to return the preference store for.
     * 
     * @return preference store for the given PreferenceHint
     */
    private static IPreferenceStore getWorkspacePreferenceStore(PreferencesHint preferencesHint) {
        return (IPreferenceStore) preferencesHint.getPreferenceStore();
    }

    /**
     * Load the preferences from an open diagram that has the given guid.
     * 
     * @param id
     *            guid of the open diagram to load the preferences for
     */
    private static IPreferenceStore loadPreferencesFromOpenDiagram(String id) {

        List diagramEditors = EditorService.getInstance().getRegisteredEditorParts();
        Iterator it = diagramEditors.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof DiagramEditor) {
                DiagramEditor diagramEditor = (DiagramEditor) obj;

                // diagram edit part and view should not be null for an open
                // diagram
                if (id.equals(ViewUtil.getIdStr(diagramEditor.getDiagramEditPart().getDiagramView()))) {
                    IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
                    if (diagramEditor.getDiagramEditPart().getRoot() instanceof DiagramRootEditPart) {
                        PageBreakEditPart pageBreakEditPart = ((DiagramRootEditPart) diagramEditor.getDiagramEditPart().getRoot()).getPageBreakEditPart();
                        pageBreakEditPart.resize(diagramEditor.getDiagramEditPart().getChildrenBounds());
                        pageBreakEditPart.updatePreferenceStore();
                    }
                    if (viewer instanceof DiagramGraphicalViewer) {
                        DiagramGraphicalViewer diagramGraphicalViewer = (DiagramGraphicalViewer) viewer;

                        // preferences loaded
                        return diagramGraphicalViewer.getWorkspaceViewerPreferenceStore();
                    }

                    // id was equal, but we couldn't load it, so don't continue
                    return null;
                }
            }
        }

        // no matching guid found
        return null;
    }

}
