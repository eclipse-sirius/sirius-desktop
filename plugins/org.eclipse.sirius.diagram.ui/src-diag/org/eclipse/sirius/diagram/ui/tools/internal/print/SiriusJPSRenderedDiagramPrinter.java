/******************************************************************************
 * Copyright (c) 2008, 2014 IBM Corporation and others.
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
import java.util.Locale;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.PrintHelperUtil;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.JPSDiagramPrinter;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

/**
 * A specialized <code>JPSDiagramPrinter</code> that manage also diagram print
 * for diagram stored in any resource not only XMLResource.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class SiriusJPSRenderedDiagramPrinter extends JPSDiagramPrinter {

    // The print service used during printing.
    private PrintService printService;

    /**
     * Creates a new instance.
     * 
     * @param preferencesHint
     *            the preferences hint to use for initializing the preferences
     * @param mm
     *            <code>IMapMode</code> to do the coordinate mapping
     */
    public SiriusJPSRenderedDiagramPrinter(final PreferencesHint preferencesHint, final IMapMode mm) {
        super(preferencesHint, mm);
    }

    @Override
    public void setPrinter(String printerName) {
        AttributeSet attributes = new HashPrintServiceAttributeSet(new PrinterName(printerName, Locale.getDefault()));
        PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE, attributes);
        printService = services[0];
    }

    /**
     * Copied from org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.
     * JPSDiagramPrinter.run() with change to not use
     * DiagramEditorUtil.findOpenedDiagramEditorForID(ViewUtil.getIdStr()) which
     * works only for diagram stored in XMLResource
     */
    @Override
    public void run() {
        Iterator<Diagram> it = diagrams.iterator();
        Shell shell = new Shell();
        try {
            while (it.hasNext()) {
                Diagram diagram = it.next();

                DiagramEditor openedDiagramEditor = getDiagramEditor(diagram);
                DiagramEditPart dgrmEP = openedDiagramEditor == null ? PrintHelperUtil.createDiagramEditPart(diagram, preferencesHint, shell) : openedDiagramEditor.getDiagramEditPart();

                boolean loadedPreferences = openedDiagramEditor != null || PrintHelperUtil.initializePreferences(dgrmEP, preferencesHint);

                RootEditPart rep = dgrmEP.getRoot();
                if (rep instanceof DiagramRootEditPart) {
                    this.mapMode = ((DiagramRootEditPart) rep).getMapMode();
                }

                IPreferenceStore preferenceStore = ((DiagramGraphicalViewer) dgrmEP.getViewer()).getWorkspaceViewerPreferenceStore();
                if (preferenceStore.getBoolean(WorkspaceViewerProperties.PREF_USE_WORKSPACE_SETTINGS)) {
                    if (dgrmEP.getDiagramPreferencesHint().getPreferenceStore() != null) {
                        preferenceStore = (IPreferenceStore) dgrmEP.getDiagramPreferencesHint().getPreferenceStore();
                    }
                }
                doPrintDiagram(printService.createPrintJob(), dgrmEP, loadedPreferences, preferenceStore);
            }
        } finally {
            dispose();
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
