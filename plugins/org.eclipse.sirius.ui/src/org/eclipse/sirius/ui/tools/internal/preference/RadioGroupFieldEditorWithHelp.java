/*******************************************************************************
 * Copyright (c) 2000, 2022 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Bjorn Michael <b.michael@gmx.de> - bug 129722 [JFace]
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.preference;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * A field editor for an enumeration type preference.<BR/>
 * The choices are presented as a list of radio buttons (with an help tooltip icon next to each radio button).<BR/>
 * This class is inspired from {@link RadioGroupFieldEditor} and {@link BooleanFieldEditorWithHelp}.
 * 
 * @author lredor
 */
public class RadioGroupFieldEditorWithHelp extends FieldEditor {

    /** Full path of the help icon. */
    public static final String ICONS_PREFERENCES_HELP = "icons/full/others/prefshelp.gif"; //$NON-NLS-1$

    /**
     * List of radio button entries of the form [label,value].
     */
    private String[][] labelsAndValues;

    /**
     * List of tooltips for all radio button.
     */
    private String[] tooltips;

    /**
     * Number of columns into which to arrange the radio buttons.
     */
    private int numColumns;

    /**
     * Indent used for the first column of the radion button matrix.
     */
    private int indent = HORIZONTAL_GAP;

    /**
     * The current value, or <code>null</code> if none.
     */
    private String value;

    /**
     * The box of radio buttons, or <code>null</code> if none (before creation and after disposal).
     */
    private Composite radioBox;

    /**
     * The radio buttons, or <code>null</code> if none (before creation and after disposal).
     */
    private Button[] radioButtons;

    /** This label will display an help icon. */
    private Label[] tooltipImages;

    /**
     * Whether to use a Group control.
     */
    private boolean useGroup;

    /**
     * Creates a new radio group field editor.
     */
    protected RadioGroupFieldEditorWithHelp() {
    }

    /**
     * Creates a radio group field editor. This constructor does not use a <code>Group</code> to contain the radio
     * buttons. It is equivalent to using the following constructor with <code>false</code> for the
     * <code>useGroup</code> argument.
     * <p>
     * Example usage:
     * </p>
     *
     * <pre>
     * <code>
     *      RadioGroupFieldEditor editor= new RadioGroupFieldEditor(
     *          "GeneralPage.DoubleClick", resName, 1,
     *          new String[][] {{"Open Browser", "open"}, {"Expand Tree", "expand"}},
     *          new String[] {"Tooltip for Open Browser", "Tooltip for open"},
     *          parent);
     * </code>
     * </pre>
     *
     * @param name
     *            the name of the preference this field editor works on
     * @param labelText
     *            the label text of the field editor
     * @param numColumns
     *            the number of columns for the radio button presentation
     * @param labelAndValues
     *            list of radio button [label, value] entries; the value is returned when the radio button is selected
     * @param tooltips
     *            list of tooltips associated to each label. This list must have the same size than labelAndValues list.
     * @param parent
     *            the parent of the field editor's control
     */
    public RadioGroupFieldEditorWithHelp(String name, String labelText, int numColumns, String[][] labelAndValues, String[] tooltips, Composite parent) {
        this(name, labelText, numColumns, labelAndValues, tooltips, parent, false);
    }

    /**
     * Creates a radio group field editor.
     * <p>
     * Example usage:
     * </p>
     *
     * <pre>
     * <code>
     * RadioGroupFieldEditor editor= new RadioGroupFieldEditor(
     *	"GeneralPage.DoubleClick", resName, 1,
     *	new String[][] {
     *		{"Open Browser", "open"},
     *		{"Expand Tree", "expand"}
     *	},
     *  new String[] {"Tooltip for Open Browser", "Tooltip for open"},
     * 	parent, true);
     * </code>
     * </pre>
     *
     * @param name
     *            the name of the preference this field editor works on
     * @param labelText
     *            the label text of the field editor
     * @param numColumns
     *            the number of columns for the radio button presentation
     * @param labelAndValues
     *            list of radio button [label, value] entries; the value is returned when the radio button is selected
     * @param tooltips
     *            list of tooltips associated to each label. This list must have the same size than labelAndValues list.
     * @param parent
     *            the parent of the field editor's control
     * @param useGroup
     *            whether to use a Group control to contain the radio buttons
     */
    public RadioGroupFieldEditorWithHelp(String name, String labelText, int numColumns, String[][] labelAndValues, String[] tooltips, Composite parent, boolean useGroup) {
        init(name, labelText);
        Assert.isTrue(checkArray(labelAndValues));
        Assert.isTrue(labelAndValues != null && tooltips != null && tooltips.length == labelAndValues.length);
        this.labelsAndValues = labelAndValues;
        this.tooltips = tooltips;
        // One is added to the number of columns for the help icons.
        this.numColumns = numColumns + 1;
        this.useGroup = useGroup;
        createControl(parent);
    }

    @Override
    protected void adjustForNumColumns(int numOfColumns) {
        Control control = getLabelControl();
        if (control != null) {
            ((GridData) control.getLayoutData()).horizontalSpan = numOfColumns;
        }
        ((GridData) radioBox.getLayoutData()).horizontalSpan = numOfColumns;
    }

    /**
     * Checks whether given <code>String[][]</code> contains sub arrays with minimum size 2
     * 
     * @param table
     *
     * @return <code>true</code> if it is ok, and <code>false</code> otherwise
     */
    // CHECKSTYLE:OFF
    private static boolean checkArray(String[][] table) {
        if (table == null) {
            return false;
        }
        for (String[] array : table) {
            if (array == null || array.length < 2) {
                return false;
            }
        }
        return true;
    }
    // CHECKSTYLE:ON

    @Override
    protected void doFillIntoGrid(Composite parent, int numOfColumns) {
        if (useGroup) {
            Control control = getRadioBoxControl(parent);
            GridData gd = new GridData(GridData.FILL_HORIZONTAL);
            control.setLayoutData(gd);
        } else {
            Control control = getLabelControl(parent);
            GridData gd = new GridData();
            gd.horizontalSpan = numOfColumns;
            control.setLayoutData(gd);
            control = getRadioBoxControl(parent);
            gd = new GridData();
            gd.horizontalSpan = numOfColumns;
            gd.horizontalIndent = indent;
            control.setLayoutData(gd);
        }

    }

    @Override
    protected void doLoad() {
        updateValue(getPreferenceStore().getString(getPreferenceName()));
    }

    @Override
    protected void doLoadDefault() {
        updateValue(getPreferenceStore().getDefaultString(getPreferenceName()));
    }

    @Override
    protected void doStore() {
        if (value == null) {
            getPreferenceStore().setToDefault(getPreferenceName());
            return;
        }

        getPreferenceStore().setValue(getPreferenceName(), value);
    }

    /**
     * Returns this field editor's current selected value.
     *
     * @return current selected value
     * @since 3.18
     */
    public String getSelectionValue() {
        return value;
    }

    @Override
    public int getNumberOfControls() {
        return 1;
    }

    /**
     * Returns this field editor's radio group control.
     * 
     * @param parent
     *            The parent to create the radioBox in
     * @return the radio group control
     */
    public Composite getRadioBoxControl(Composite parent) {
        if (radioBox == null) {

            Font font = parent.getFont();

            if (useGroup) {
                Group group = new Group(parent, SWT.NONE);
                group.setFont(font);
                String text = getLabelText();
                if (text != null) {
                    group.setText(text);
                }
                radioBox = group;
                GridLayout layout = new GridLayout();
                layout.horizontalSpacing = HORIZONTAL_GAP;
                layout.numColumns = numColumns;
                radioBox.setLayout(layout);
            } else {
                radioBox = new Composite(parent, SWT.NONE);
                GridLayout layout = new GridLayout();
                layout.marginWidth = 0;
                layout.marginHeight = 0;
                layout.horizontalSpacing = HORIZONTAL_GAP;
                layout.numColumns = numColumns;
                radioBox.setLayout(layout);
                radioBox.setFont(font);
            }

            radioButtons = new Button[labelsAndValues.length];
            tooltipImages = new Label[tooltips.length];
            for (int i = 0; i < labelsAndValues.length; i++) {
                Button radio = new Button(radioBox, SWT.RADIO | SWT.LEFT);
                radioButtons[i] = radio;
                String[] labelAndValue = labelsAndValues[i];
                radio.setText(labelAndValue[0]);
                radio.setData(labelAndValue[1]);
                radio.setFont(font);
                radio.addSelectionListener(widgetSelectedAdapter(event -> {
                    String oldValue = value;
                    value = (String) event.widget.getData();
                    setPresentsDefaultValue(false);
                    fireValueChanged(VALUE, oldValue, value);
                }));

                Label tooltipImage = new Label(radioBox, SWT.NONE);
                tooltipImages[i] = tooltipImage;
                tooltipImage.setImage(getHelpIcon());
                tooltipImage.setToolTipText(tooltips[i]);
                GridData gd = new GridData();
                gd.horizontalSpan = 1;
                gd.grabExcessHorizontalSpace = true;
                tooltipImage.setLayoutData(gd);
            }
            radioBox.addDisposeListener(event -> {
                radioBox = null;
                radioButtons = null;
            });
        } else {
            checkParent(radioBox, parent);
        }
        return radioBox;
    }

    /**
     * Sets the indent used for the first column of the radio button matrix.
     *
     * @param indent
     *            the indent (in pixels)
     */
    public void setIndent(int indent) {
        if (indent < 0) {
            this.indent = 0;
        } else {
            this.indent = indent;
        }
    }

    /**
     * Select the radio button that conforms to the given value.
     *
     * @param selectedValue
     *            the selected value
     */
    // CHECKSTYLE:OFF
    private void updateValue(String selectedValue) {
        this.value = selectedValue;
        if (radioButtons == null) {
            return;
        }

        if (this.value != null) {
            boolean found = false;
            for (Button radio : radioButtons) {
                boolean selection = false;
                if (((String) radio.getData()).equals(this.value)) {
                    selection = true;
                    found = true;
                }
                radio.setSelection(selection);
            }
            if (found) {
                return;
            }
        }

        // We weren't able to find the value. So we select the first
        // radio button as a default.
        if (radioButtons.length > 0) {
            radioButtons[0].setSelection(true);
            this.value = (String) radioButtons[0].getData();
        }
        return;
    }
    // CHECKSTYLE:ON

    /*
     * @see FieldEditor.setEnabled(boolean,Composite).
     */
    @Override
    public void setEnabled(boolean enabled, Composite parent) {
        if (!useGroup) {
            super.setEnabled(enabled, parent);
        }
        for (Button radioButton : radioButtons) {
            radioButton.setEnabled(enabled);
        }

    }

    /**
     * Creates and return the help icon to show in our label.
     * 
     * @return The help icon to show in our label.
     */
    protected Image getHelpIcon() {
        ImageDescriptor findImageDescriptor = SiriusEditPlugin.Implementation.findImageDescriptor(ICONS_PREFERENCES_HELP);
        return SiriusEditPlugin.getPlugin().getImage(findImageDescriptor);
    }
}
