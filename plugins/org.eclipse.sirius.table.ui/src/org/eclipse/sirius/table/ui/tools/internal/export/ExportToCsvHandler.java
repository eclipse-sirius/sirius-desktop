/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.export;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * An handler to export a table in csv file.
 * 
 * @author mchauvin
 */
public class ExportToCsvHandler extends AbstractHandler {
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);

        if (editorPart instanceof DialectEditor) {
            DRepresentation currentRepresentation = ((DialectEditor) editorPart).getRepresentation();
            if (currentRepresentation instanceof DTable) {
                final DTable table = (DTable) currentRepresentation;
                // Create the file dialog to request the location to save
                // the export.

                final Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

                final FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setFileName(table.getName() + ".csv"); //$NON-NLS-1$
                fileDialog.setFilterExtensions(new String[] { "*.csv" }); //$NON-NLS-1$
                fileDialog.setFilterNames(new String[] { "Comma Separated Values" }); //$NON-NLS-1$
                final String fileName = fileDialog.open();
                if (null != fileName) {
                    try {
                        DialectUIManager.INSTANCE.export(table, null, Path.fromOSString(fileName), new ExportFormat(ExportDocumentFormat.CSV, null), new NullProgressMonitor());
                    } catch (CoreException exception) {
                        SiriusPlugin.getDefault().error(exception.getMessage(), exception);
                    }
                }
            }
        }
        return null;
    }
}
