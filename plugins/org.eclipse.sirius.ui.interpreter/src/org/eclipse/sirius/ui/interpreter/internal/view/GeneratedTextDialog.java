/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view;

import org.eclipse.sirius.ui.interpreter.internal.SWTUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This will be used in order to display long generated Strings to the user. This is a simple dialog that only consists
 * of a single Text area.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class GeneratedTextDialog extends Dialog {
    /** Title of our dialog. */
    private final String title;

    /** Text that is to be displayed in this dialog. */
    private final String text;

    /**
     * Instantiates our dialog given its parent shell and the text we are to display.
     * 
     * @param shell
     *            Parent shell of our dialog.
     * @param title
     *            Title of our dialog.
     * @param text
     *            Text that is to be displayed in this dialog.
     */
    public GeneratedTextDialog(Shell shell, String title, String text) {
        super(shell);
        this.title = title;
        this.text = text;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (title != null) {
            newShell.setText(title);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite body = (Composite) super.createDialogArea(parent);
        final Point dialogSize = new Point(500, 400);

        final Text textArea = SWTUtil.createScrollableText(body, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.widthHint = dialogSize.x;
        gridData.heightHint = dialogSize.y;
        textArea.setLayoutData(gridData);
        textArea.setText(text);
        textArea.setBackground(parent.getShell().getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

        return body;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#isResizable()
     */
    @Override
    protected boolean isResizable() {
        return true;
    }
}
