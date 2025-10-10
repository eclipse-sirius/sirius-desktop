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
package org.eclipse.acceleo.ui.interpreter.internal.view.wizards;

import java.util.Iterator;
import java.util.List;

import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.internal.SWTUtil;
import org.eclipse.acceleo.ui.interpreter.internal.view.VariableLabelProvider;
import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * Wizard page to create a new variable, or add new values to an existing variable.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class NewVariableWizardPage extends WizardPage {
    /** Number of columns our page spans. */
    private static final int COLUMN_COUNT = 3;

    /** Description of this wizard page. */
    private static final String PAGE_DESCRIPTION = InterpreterMessages.getString("interpreter.wizard.newvariable.description"); //$NON-NLS-1$

    /** Name of this wizard page. */
    private static final String PAGE_NAME = "NewVariablePage"; //$NON-NLS-1$

    /** Title of this wizard page. */
    private static final String PAGE_TITLE = InterpreterMessages.getString("interpreter.wizard.newvariable.title"); //$NON-NLS-1$

    /** The currently selected type. */
    protected Type currentType;

    /** This will contain the list of pre-existing variables, if any. */
    protected final List<Variable> existingVariables;

    /** The control allowing users to select a new variable value. */
    protected Control valueControl;

    /** The text widget allowing users to select the variable name. */
    protected Text variableText;

    /** The name of the variable initially selected in the interpreter view's variable viewer. */
    private final String initialVariableName;

    /** Will be updated with the final Variable name. */
    private String variableName;

    /** Validator we'll use for the variable name. */
    private IInputValidator variableValidator = new VariableNameValidator();

    /** Will be updated with the final Variable value. */
    private Object variableValue;

    /**
     * Instantiates our wizard page.
     * 
     * @param initialVariableName
     *            The name of the variable initially selected in the interpreter view's variable viewer.
     * @param existingVariables
     *            This will contain the list of pre-existing variables, if any.
     */
    public NewVariableWizardPage(String initialVariableName, List<Variable> existingVariables) {
        super(PAGE_NAME);
        setTitle(PAGE_TITLE);
        setDescription(PAGE_DESCRIPTION);
        setPageComplete(false);
        this.initialVariableName = initialVariableName;
        this.existingVariables = existingVariables;
    }

    /**
     * Returns a width hint for a button control.
     * 
     * @param button
     *            the button
     * @return the width hint
     */
    public static int getButtonWidthHint(Button button) {
        Font font = JFaceResources.getDialogFont();
        button.setFont(font);
        GC gc = new GC(font.getDevice());
        gc.setFont(font);
        FontMetrics fontMetrics = gc.getFontMetrics();
        gc.dispose();
        int widthHint = Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.BUTTON_WIDTH);
        return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setFont(parent.getFont());

        GridLayout layout = new GridLayout(COLUMN_COUNT, false);
        composite.setLayout(layout);

        createVariableControls(composite);

        createSeparator(composite);

        createTypeControls(composite);
        createValueControls(composite);

        setControl(composite);
    }

    /**
     * Returns the variable name, as entered in {@link #variableText}.
     * 
     * @return The variable name.
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Returns the value of the new variable, as select in {@link #valueControl}.
     * 
     * @return The value of the new variable.
     */
    public Object getVariableValue() {
        return variableValue;
    }

    /**
     * Sets the {@link #valueControl} field to a radio button group.
     * 
     * @param parent
     *            Parent control for the {@link Text}.
     */
    protected void createBooleanValueControl(Composite parent) {
        if (valueControl != null) {
            valueControl.dispose();
        }
        valueControl = new BooleanChooser(parent);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = COLUMN_COUNT - 1;
        valueControl.setLayoutData(gridData);
    }

    /**
     * Sets the {@link #valueControl} field to a Text accepting only decimals.
     * 
     * @param parent
     *            Parent control for the {@link Text}.
     */
    protected void createFloatValueControl(Composite parent) {
        if (valueControl != null) {
            valueControl.dispose();
        }
        valueControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = COLUMN_COUNT - 1;
        valueControl.setLayoutData(gridData);

        final RealValueValidator validator = new RealValueValidator();
        ((Text) valueControl).addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                String errorMessage = validator.isValid(((Text) valueControl).getText());
                setErrorMessage(errorMessage);
                setPageComplete(getErrorMessage() == null);
            }
        });
    }

    /**
     * Sets the {@link #valueControl} field to a Spinner.
     * 
     * @param parent
     *            Parent control for the {@link Spinner}.
     */
    protected void createIntegerValueControl(Composite parent) {
        if (valueControl != null) {
            valueControl.dispose();
        }
        valueControl = new Spinner(parent, SWT.NONE);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = COLUMN_COUNT - 1;
        valueControl.setLayoutData(gridData);
    }

    /**
     * Sets the {@link #valueControl} field to a Text.
     * 
     * @param parent
     *            Parent control for the {@link Text}.
     */
    protected void createStringValueControl(Composite parent) {
        if (valueControl != null) {
            valueControl.dispose();
        }
        valueControl = SWTUtil.createScrollableText(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = COLUMN_COUNT - 1;
        gridData.heightHint = 100;
        valueControl.setLayoutData(gridData);
    }

    /**
     * This will be used in order to validate the variable name.
     */
    protected void validateVariableName() {
        final String name = variableText.getText();
        String errorMessage = variableValidator.isValid(name);
        setErrorMessage(errorMessage);

        if (errorMessage == null) {
            boolean exists = false;
            Iterator<Variable> variables = existingVariables.iterator();
            while (variables.hasNext() && !exists) {
                exists = name.equals(variables.next().getName());
            }

            if (exists) {
                setMessage(InterpreterMessages.getString("interpreter.wizard.newvariable.info.existingvariable", name), SWT.NONE); //$NON-NLS-1$
            } else {
                setMessage(null);
            }
        }
    }

    /**
     * Creates a separator label under the given parent.
     * 
     * @param parent
     *            Parent composite for the widgets.
     */
    private void createSeparator(Composite parent) {
        final Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        final int eight = 8;
        gridData.minimumHeight = eight;
        gridData.heightHint = eight;
        gridData.horizontalSpan = COLUMN_COUNT;
        label.setLayoutData(gridData);
    }

    /**
     * This will create the radio button group for the variable type selection.
     * 
     * @param parent
     *            Parent control for the widgets.
     */
    private void createTypeControls(Composite parent) {
        final Label typeLabel = new Label(parent, SWT.LEFT | SWT.WRAP);
        typeLabel.setFont(parent.getFont());
        typeLabel.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.type.label") + ':'); //$NON-NLS-1$
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        typeLabel.setLayoutData(gridData);

        final Composite buttonGroup = new Composite(parent, SWT.NONE);
        buttonGroup.setLayout(new FillLayout());
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = COLUMN_COUNT - 1;
        buttonGroup.setLayoutData(gridData);

        Button stringButton = new Button(buttonGroup, SWT.RADIO);
        stringButton.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.type.string")); //$NON-NLS-1$
        stringButton.setSelection(true);
        stringButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentType != Type.STRING) {
                    final Composite composite = valueControl.getParent();
                    createStringValueControl(composite);
                    composite.layout();
                    currentType = Type.STRING;
                }
            }
        });

        Button booleanButton = new Button(buttonGroup, SWT.RADIO);
        booleanButton.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.type.boolean")); //$NON-NLS-1$
        booleanButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentType != Type.BOOLEAN) {
                    final Composite composite = valueControl.getParent();
                    createBooleanValueControl(composite);
                    composite.layout();
                    currentType = Type.BOOLEAN;
                }
            }
        });

        Button integerButton = new Button(buttonGroup, SWT.RADIO);
        integerButton.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.type.integer")); //$NON-NLS-1$
        integerButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentType != Type.INTEGER) {
                    final Composite composite = valueControl.getParent();
                    createIntegerValueControl(composite);
                    composite.layout();
                    currentType = Type.INTEGER;
                }
            }
        });

        Button floatButton = new Button(buttonGroup, SWT.RADIO);
        floatButton.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.type.float")); //$NON-NLS-1$
        floatButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentType != Type.FLOAT) {
                    final Composite composite = valueControl.getParent();
                    createFloatValueControl(composite);
                    composite.layout();
                    currentType = Type.FLOAT;
                }
            }
        });
    }

    /**
     * This will create the controls for the "value" line (label and text area) that will be used to prompt the user for
     * a new variable value.
     * 
     * @param parent
     *            Parent control for the widgets.
     */
    private void createValueControls(Composite parent) {
        final Label valueLabel = new Label(parent, SWT.LEFT | SWT.WRAP);
        valueLabel.setFont(parent.getFont());
        valueLabel.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.value.label") + ':'); //$NON-NLS-1$
        GridData gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
        valueLabel.setLayoutData(gridData);

        createStringValueControl(parent);
        currentType = Type.STRING;
    }

    /**
     * This will create the controls for the "variable name" line (label, text and browse button).
     * 
     * @param parent
     *            Parent control for the widgets.
     */
    private void createVariableControls(Composite parent) {
        final Label variableLabel = new Label(parent, SWT.LEFT | SWT.WRAP);
        variableLabel.setFont(parent.getFont());
        variableLabel.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.variable.label") + ':'); //$NON-NLS-1$
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        variableLabel.setLayoutData(gridData);

        variableText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        variableText.setLayoutData(gridData);
        variableText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                validateVariableName();
                setPageComplete(getErrorMessage() == null);
            }
        });
        if (initialVariableName != null) {
            variableText.setText(initialVariableName);
        }

        final Button browseButton = new Button(parent, SWT.PUSH);
        browseButton.setFont(parent.getFont());
        browseButton.setText(InterpreterMessages.getString("interpreter.wizard.newvariable.variable.browse")); //$NON-NLS-1$
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = false;
        gridData.widthHint = getButtonWidthHint(browseButton);
        browseButton.setLayoutData(gridData);
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementListSelectionDialog dialog = new ElementListSelectionDialog(browseButton.getShell(),
                        new VariableLabelProvider(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)));
                dialog.setElements(existingVariables.toArray());
                if (dialog.open() == Window.OK) {
                    final Object result = dialog.getFirstResult();
                    if (result instanceof Variable) {
                        variableText.setText(((Variable) result).getName());
                    }
                }
            }
        });
        if (existingVariables.isEmpty()) {
            browseButton.setEnabled(false);
        }
    }

    /**
     * Sets the variable name to the currently selected text.
     */
    public void setVariableName() {
        this.variableName = variableText.getText();
    }

    /**
     * Sets the variable value to the currently selected value.
     */
    public void setVariableValue() {
        if (currentType == Type.STRING) {
            variableValue = ((Text) valueControl).getText();
        } else if (currentType == Type.INTEGER) {
            variableValue = Integer.valueOf(((Spinner) valueControl).getSelection());
        } else if (currentType == Type.FLOAT) {
            variableValue = Float.valueOf(((Text) valueControl).getText());
        } else if (valueControl != null) {
            variableValue = Boolean.valueOf(((BooleanChooser) valueControl).getValue());
        } else {
            variableValue = ""; //$NON-NLS-1$
        }
    }

    /**
     * This validator will check that the entered String is a real number.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class RealValueValidator implements IInputValidator {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
         */
        public String isValid(String newText) {
            String errorMessage = null;
            if (newText != null && newText.length() > 0 && !isReal(newText)) {
                errorMessage = InterpreterMessages.getString("interpreter.wizard.newvariable.error.float.invalid", newText); //$NON-NLS-1$
            }
            return errorMessage;
        }

        /**
         * Returns <code>true</code> if the given String can be parsed as a Real.
         * 
         * @param value
         *            Value we need to try and parse as a real.
         * @return <code>true</code> if the given <code>value</code> can be parsed as a real, <code>false</code>
         *         otherwise.
         */
        private boolean isReal(String value) {
            try {
                Double.parseDouble(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    /**
     * This enum will be used in order to determine which "type" button is currently selected, and prevent the
     * replacement of the "valueControl" by another one of the same type.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected enum Type {
        /** Represents the Boolean type. */
        BOOLEAN,

        /** Represents the Float type. */
        FLOAT,

        /** Represents the Integer type. */
        INTEGER,

        /** Represents the String type. */
        STRING;
    }

    /**
     * This basic validator only checks that the variable name is a valid Java identifier.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class VariableNameValidator implements IInputValidator {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
         */
        public String isValid(String newText) {
            String errorMessage = null;
            if (newText == null || "".equals(newText)) { //$NON-NLS-1$
                errorMessage = InterpreterMessages.getString("interpreter.wizard.newvariable.error.noname"); //$NON-NLS-1$
            } else if (!isJavaIdentifier(newText)) {
                errorMessage = InterpreterMessages.getString("interpreter.wizard.newvariable.error.invalid", //$NON-NLS-1$
                        newText);
            }
            return errorMessage;
        }

        /**
         * Returns <code>true</code> if each of the given String's character is a valid Java identifier part.
         * 
         * @param name
         *            Name of which we need to check the validity.
         * @return <code>true</code> if the given <code>name</code> can be considered a valid Java identifier,
         *         <code>false</code> otherwise.
         */
        private boolean isJavaIdentifier(String name) {
            for (char character : name.toCharArray()) {
                if (!Character.isJavaIdentifierPart(character)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * This class will be used as the control displayed to the user for "boolean" value selection.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class BooleanChooser extends Composite {
        /** Selected boolean value. */
        protected boolean value = true;

        /**
         * Instantiates this Control given its parent.
         * 
         * @param parent
         *            Parent composite of this Control.
         */
        BooleanChooser(Composite parent) {
            super(parent, SWT.NONE);
            setLayout(new FillLayout(SWT.VERTICAL));
            createContents();
        }

        /**
         * Returns the selected boolean value.
         * 
         * @return The selected boolean value.
         */
        public boolean getValue() {
            return value;
        }

        /**
         * Creates the contents of this Composite.
         */
        private void createContents() {
            final Button trueButton = new Button(this, SWT.RADIO);
            trueButton.setText(Boolean.TRUE.toString());
            trueButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    value = true;
                }
            });
            trueButton.setSelection(true);

            final Button falseButton = new Button(this, SWT.RADIO);
            falseButton.setText(Boolean.FALSE.toString());
            falseButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    value = false;
                }
            });
        }
    }
}
