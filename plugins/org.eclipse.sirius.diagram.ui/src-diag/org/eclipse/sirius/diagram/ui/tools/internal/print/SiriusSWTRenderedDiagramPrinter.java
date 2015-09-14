/******************************************************************************
 * Copyright (c) 2005, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Esteban Dugueperoux (Obeo) <esteban.dugueperoux@obeo.fr> - manage also diagram print diagrams for diagram stored in any resource not only XMLResource
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.PrintHelperUtil;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.util.RenderedDiagramPrinter;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Shell;

/**
 * A specialized <code>RenderedDiagramPrinter</code> that manage also diagram
 * print for diagram stored in any resource not only XMLResource.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class SiriusSWTRenderedDiagramPrinter extends RenderedDiagramPrinter {

    /**
     * Creates a new instance.
     *
     * @param preferencesHint
     *            the preferences hint to use for initializing the preferences
     * @param mm
     *            <code>IMapMode</code> to do the coordinate mapping
     */
    public SiriusSWTRenderedDiagramPrinter(final PreferencesHint preferencesHint, final IMapMode mm) {
        super(preferencesHint, mm);
    }

    /**
     * Copied from org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.
     * SWTDiagramPrinter.run() with change to not use
     * DiagramEditorUtil.findOpenedDiagramEditorForID(ViewUtil.getIdStr()) which
     * works only for diagram stored in XMLResource
     */
    @Override
    public void run() {
        assert null != printer : Messages.SiriusSWTRenderedDiagramPrinter_printerNotSetMsg;
        if (!(printer.startJob(Messages.SiriusSWTRenderedDiagramPrinter_jobLabel))) {
            return;
        }

        assert diagrams != null;
        Iterator<Diagram> it = diagrams.iterator();

        Shell shell = new Shell();
        try {
            while (it.hasNext()) {
                Object obj = it.next();
                // the diagrams List is only supposed to have Diagram objects
                Assert.isTrue(obj instanceof Diagram);
                Diagram diagram = (Diagram) obj;
                DiagramEditor openedDiagramEditor = getDiagramEditor(diagram);
                DiagramEditPart dgrmEP = openedDiagramEditor == null ? PrintHelperUtil.createDiagramEditPart(diagram, preferencesHint, shell) : openedDiagramEditor.getDiagramEditPart();

                boolean loadedPreferences = PrintHelperUtil.initializePreferences(dgrmEP, preferencesHint);

                RootEditPart rep = dgrmEP.getRoot();
                if (rep instanceof DiagramRootEditPart) {
                    this.mapMode = ((DiagramRootEditPart) rep).getMapMode();
                }

                initialize();

                IPreferenceStore pref = null;

                assert dgrmEP.getViewer() instanceof DiagramGraphicalViewer;

                pref = ((DiagramGraphicalViewer) dgrmEP.getViewer()).getWorkspaceViewerPreferenceStore();

                if (pref.getBoolean(WorkspaceViewerProperties.PREF_USE_WORKSPACE_SETTINGS)) {

                    // get workspace settings...
                    if (dgrmEP.getDiagramPreferencesHint().getPreferenceStore() != null) {
                        pref = (IPreferenceStore) dgrmEP.getDiagramPreferencesHint().getPreferenceStore();
                    }
                }

                // Ensure the preference value is properly updated when the
                // user overrides the preference store with values from the
                // print settings.
                // Printing and preview use the preference settings, to
                // calculate page size.
                PrinterData printerData = printer.getPrinterData();
                if (printerData != null) {
                    boolean useLandscape = printerData.orientation == PrinterData.LANDSCAPE;
                    if (pref.getBoolean(WorkspaceViewerProperties.PREF_USE_LANDSCAPE) != useLandscape) {
                        pref.setValue(WorkspaceViewerProperties.PREF_USE_LANDSCAPE, useLandscape);
                    }
                    if (pref.getBoolean(WorkspaceViewerProperties.PREF_USE_PORTRAIT) == useLandscape) {
                        pref.setValue(WorkspaceViewerProperties.PREF_USE_PORTRAIT, !useLandscape);
                    }
                }

                doPrintDiagram(dgrmEP, loadedPreferences, pref);
            }
            dispose();
            printer.endJob();
        } finally {
            shell.dispose();
        }
    }

    private DiagramEditor getDiagramEditor(Diagram diagram) {
        DiagramEditor result = null;
        List<?> diagramEditors = EditorService.getInstance().getRegisteredEditorParts();
        Iterator<?> it = diagramEditors.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof DiagramEditor) {
                DiagramEditor diagramEditor = (DiagramEditor) obj;
                if (diagramEditor.getDiagram() == diagram) {
                    result = diagramEditor;
                    break;
                }
            }
        }
        return result;
    }
}
