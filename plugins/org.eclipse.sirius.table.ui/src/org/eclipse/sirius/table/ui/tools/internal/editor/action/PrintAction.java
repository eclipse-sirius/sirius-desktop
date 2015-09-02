/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.text.MessageFormat;

import org.eclipse.jface.action.Action;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.print.PaperClipsPrintHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.print.PrintHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

/**
 * An action to print tables.
 *
 * @author mchauvin
 */
public class PrintAction extends Action {

    private IEditorPart editorPart;

    private Control controlToPrint;

    /**
     * Set the active editor part.
     *
     * @param editor
     *            the current active editor part
     */
    public void setEditor(final IEditorPart editor) {
        this.editorPart = editor;
    }

    @Override
    public void run() {
        if (editorPart instanceof AbstractDTableEditor) {
            controlToPrint = ((AbstractDTableEditor) editorPart).getTableViewer().getControl();
            if (controlToPrint != null) {
                final Display display = controlToPrint.getDisplay();
                final Shell shell = display.getActiveShell();
                final String tableName = editorPart.getTitle() != null ? editorPart.getTitle() : Messages.PrintAction_tableWithoutName;
                try {
                    final PrinterData data = openPrintDialog(shell);
                    if (data == null) {
                        return;
                    }
                    launchPrintWithPaperclip(data, tableName);
                } catch (final SWTError e) {
                    final MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setMessage(MessageFormat.format(Messages.PrintAction_errorDuringPrinting, e.getMessage()));
                    box.open();
                }
            }
        }
    }

    private PrinterData openPrintDialog(final Shell shell) {
        final PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
        final PrinterData printerData = dialog.open();
        return printerData;
    }

    private void launchPrintWithPaperclip(final PrinterData data, final String tableName) {
        final PrintHelper helper = new PaperClipsPrintHelper(data, controlToPrint);
        launch(helper, tableName);
    }

    private void launch(final PrintHelper helper, final String tableName) {
        helper.launchPrintJob(tableName);
        helper.dispose();
    }

}
