/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to allow the user to give the values of the TypedVariable.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class TypedVariableValueDialog extends Dialog {

    /** Full path of the information icon. */
    public static final String ICON_INFORMATION = "icons/full/others/prefshelp.gif"; //$NON-NLS-1$

    /**
     * The full path of the resource.
     */
    private List<Text> valueTextList;

    private List<TypedVariable> typedVariableList;

    private List<String> values = new ArrayList<String>();

    private List<String> defaultValues;

    /**
     * Creates a dialog that prompts the user for giving the value for each
     * TypedVariable of typedVariableList.
     * 
     * @param typedVariableList
     *            the TypedVariable for which to user input the value
     * @param defaultValues
     *            used to initialize values. This list must have the
     *            typedVariableList size.
     * @param parentShell
     *            the parent shell of this dialog
     */
    public TypedVariableValueDialog(List<TypedVariable> typedVariableList, List<String> defaultValues, Shell parentShell) {
        super(parentShell);
        this.typedVariableList = typedVariableList;
        valueTextList = new ArrayList<Text>();
        this.defaultValues = defaultValues;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.TypedVariableValueDialog_title);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        Image image = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.findImageDescriptor(ICON_INFORMATION));

        for (int i = 0; i < typedVariableList.size(); i++) {
            GridLayout layout = new GridLayout(3, false);
            composite.setLayout(layout);
            composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

            TypedVariable typedVariable = typedVariableList.get(i);
            Label label = new Label(composite, SWT.NONE);
            GridData layoutData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1);
            label.setLayoutData(layoutData);
            label.setText(typedVariable.getName());

            Label labelImage = new Label(composite, SWT.NONE);
            labelImage.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
            if (image != null) {
                labelImage.setImage(image);
            }
            labelImage.setToolTipText(typedVariable.getUserDocumentation());

            Text text = new Text(composite, SWT.BORDER);
            valueTextList.add(text);
            layoutData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
            text.setLayoutData(layoutData);
            text.setText(defaultValues.get(i));
        }

        Dialog.applyDialogFont(composite);

        return composite;
    }

    @Override
    protected void okPressed() {
        for (Text text : valueTextList) {
            values.add(text.getText());
        }

        super.okPressed();
    }

    /**
     * Return the values.
     * 
     * @return the values
     */
    public List<String> getValues() {
        return values;
    }

}
