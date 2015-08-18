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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Filters table viewer.
 * 
 * @author mchauvin
 */
public final class FiltersTableViewer {

    private static final String[] COLUMNS = { " ", "Filter" }; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private FiltersTableViewer() {

    }

    /**
     * Create a new Filters table viewer.
     * 
     * @param control
     *            the parent composite
     * @param workbenchPart
     *            the workbench part to access diagram part
     * @return new viewer to enable or disable filters
     */
    public static TableViewer createFiltersTableViewer(final Composite control, final IDiagramWorkbenchPart workbenchPart) {

        final TableViewer tableViewer = new TableViewer(control, SWT.BORDER | SWT.FULL_SELECTION);
        final Table table = tableViewer.getTable();

        table.setLayoutData(new GridData(GridData.FILL_BOTH));

        TableColumn tc = new TableColumn(table, SWT.LEFT, 0);
        tc.setText(COLUMNS[0]);
        tc.setWidth(30);
        tc.setImage(DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.FILTER_ACTIVATION_ICON));

        tc = new TableColumn(table, SWT.LEFT, 1);
        tc.setText(COLUMNS[1]);
        tc.setWidth(200);

        // Can only changes the first column - the visible column
        final CellEditor[] editors = new CellEditor[2];
        editors[0] = new CheckboxCellEditor(table);
        for (int i = 1; i < 2; i++) {
            editors[i] = null;
        }

        tableViewer.setColumnProperties(COLUMNS);

        tableViewer.setCellEditors(editors);
        final FiltersActivationAdapter adapter = new FiltersActivationAdapter();
        tableViewer.setCellModifier(new FiltersCellModifier(adapter, workbenchPart, COLUMNS));
        tableViewer.setContentProvider(new FiltersContentProvider(adapter, workbenchPart));
        tableViewer.setLabelProvider(new FiltersLabelProvider(workbenchPart));

        if (workbenchPart != null) {
            final EObject eObj = workbenchPart.getDiagram().getElement();
            if (eObj instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) eObj;
                tableViewer.setInput(diagram.getDescription());
            }
        }

        /* Lines are not visible in the specifications */
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        return tableViewer;

    }

}
