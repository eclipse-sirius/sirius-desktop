/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Factory class to create a layers table viewer.
 * 
 * @author mchauvin
 */
public final class LayersTableViewer {

    private static final String[] COLUMNS = { " ", " ", "Layer" }; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private LayersTableViewer() {
    }

    /**
     * Create a new Layers table viewer.
     * 
     * @param control
     *            the parent SWT control
     * @param diagramPart
     *            the workbench part to access diagram part
     * @return new viewer to hide or show layers.
     */
    public static TableViewer createLayersTableViewer(final Composite control, final IDiagramWorkbenchPart diagramPart) {

        final TableViewer tableViewer = new TableViewer(control, SWT.BORDER | SWT.FULL_SELECTION);
        ColumnViewerToolTipSupport.enableFor(tableViewer, ToolTip.RECREATE);

        final Table table = tableViewer.getTable();

        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        TableColumn tc = new TableColumn(table, SWT.LEFT, 0);
        tc.setText(COLUMNS[0]);
        tc.setWidth(30);
        tc.setImage(DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.LAYER_ACTIVATION_ICON));

        tc = new TableColumn(table, SWT.CENTER, 1);
        tc.setText(COLUMNS[1]);
        tc.setWidth(30);

        tc = new TableColumn(table, SWT.LEFT, 2);
        tc.setText(COLUMNS[2]);
        tc.setWidth(200);

        // Can only changes the first column - the visible column
        final CellEditor[] editors = new CellEditor[3];
        editors[0] = new CheckboxCellEditor(table);
        for (int i = 1; i < 3; i++) {
            editors[i] = null;
        }

        tableViewer.setColumnProperties(COLUMNS);

        tableViewer.setCellEditors(editors);
        final LayersActivationAdapter adapter = new LayersActivationAdapter();
        final LayersEventsListener sessionListener = new LayersEventsListener();
        tableViewer.setCellModifier(new LayersCellModifier(adapter, diagramPart, COLUMNS));
        tableViewer.setContentProvider(new LayersContentProvider(adapter, sessionListener, diagramPart));
        tableViewer.setLabelProvider(new LayersLabelProvider(diagramPart));

        if (diagramPart != null) {
            final EObject eObj = diagramPart.getDiagram().getElement();
            if (eObj instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) eObj;
                tableViewer.setInput(diagram);
            }
        }

        /* Lines are not visible in the specifications */
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        return tableViewer;
    }

}
