/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * A dialog containing a text field with content proposal from plugins.
 * 
 * @author bgrouhan
 * 
 */
public class TextWithContentProposalDialog extends Dialog {

    /**
     * The text that will be returned.
     */
    private String resultText;

    /**
     * The background color of the text area.
     */
    private AbstractPropertySection section;

    /**
     * The text area of the dialog.
     */
    private Text textArea;

    /**
     * Constructor without background color for the text area.
     * 
     * @param parentShell
     *            the parent shell.
     * @param propertySection
     *            the property section where this dialog is created.
     * @param initialText
     *            the initial text.
     */
    public TextWithContentProposalDialog(Shell parentShell, AbstractPropertySection propertySection, String initialText) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        resultText = initialText;
        section = propertySection;
    }

    /**
     * Method to get the resulting text.
     * 
     * @return the text in the Text control if the "Ok" button was pressed, the
     *         previous text otherwise.
     */
    public String getResult() {
        return resultText;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite contents = (Composite) super.createDialogArea(parent);
        textArea = new Text(contents, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
        final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = 200;
        data.widthHint = 600;
        textArea.setLayoutData(data);
        textArea.setBackground(SiriusEditor.getColorRegistry().get("yellow"));
        TypeContentProposalProvider.bindPluginsCompletionProcessors(section, textArea);
        textArea.setText(resultText);
        return contents;
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Type your expression");
    }

    @Override
    protected void okPressed() {
        resultText = textArea.getText().replaceAll("\n", "").replaceAll("\t", "");
        super.okPressed();
    }
}
