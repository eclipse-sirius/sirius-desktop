/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.preference;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;

/**
 * A field editor for an integer type preference. The field editor is represented by a scale to specify the integer
 * value. This class may be used as is, or subclassed as required.
 * 
 * @author jmallet
 */
public class ScaleWithLegendFieldEditor extends FieldEditor {

    /**
     * The scale, or <code>null</code> if none.
     */
    protected Scale scale;

    /**
     * Value that will feed ScaleWithLegend.setIncrement(int).
     */
    private int incrementValue;

    /**
     * Value that will feed ScaleWithLegend.setMaximum(int).
     */
    private int maxValue;

    /**
     * Value that will feed ScaleWithLegend.setMinimum(int).
     */
    private int minValue;

    /**
     * Old integer value.
     */
    private int oldValue;

    /**
     * Value that will feed ScaleWithLegend.setPageIncrement(int).
     */
    private int pageIncrementValue;

    /**
     * Creates a new ScaleWithLegend field editor.
     */
    protected ScaleWithLegendFieldEditor() {
    }

    /**
     * Creates a ScaleWithLegend field editor.
     * 
     * @param name
     *            the name of the preference this field editor works on
     * @param labelText
     *            the label text of the field editor
     * @param parent
     *            the parent of the field editor's control
     */
    public ScaleWithLegendFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
        setDefaultValues();
    }

    /**
     * Creates a ScaleWithLegend field editor with particular ScaleWithLegend values.
     * 
     * @param name
     *            the name of the preference this field editor works on
     * @param labelText
     *            the label text of the field editor
     * @param parent
     *            the parent of the field editor's control
     * @param min
     *            the value used for ScaleWithLegend.setMinimum(int).
     * @param max
     *            the value used for ScaleWithLegend.setMaximum(int).
     * @param increment
     *            the value used for ScaleWithLegend.setIncrement(int).
     * @param pageIncrement
     *            the value used for ScaleWithLegend.setPageIncrement(int).
     */
    public ScaleWithLegendFieldEditor(String name, String labelText, Composite parent, int min, int max, int increment, int pageIncrement) {
        super(name, labelText, parent);
        setValues(min, max, increment, pageIncrement);
    }

    @Override
    protected void adjustForNumColumns(int numColumns) {
        ((GridData) scale.getLayoutData()).horizontalSpan = numColumns - 1;
    }

    @Override
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 3, false);

        SWTUtil.createLabel(composite, Messages.ExportAsImage_nominalLevelLabel, SWT.LEFT);

        scale = getScaleControl(composite);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.grabExcessHorizontalSpace = true;
        scale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        SWTUtil.createLabel(composite, Messages.ExportAsImage_maxLevelLabel, SWT.RIGHT);

        updateScale();

    }

    @Override
    protected void doLoad() {
        if (scale != null) {
            int value = getPreferenceStore().getInt(getPreferenceName());
            scale.setSelection(value);
            oldValue = value;
        }
    }

    @Override
    protected void doLoadDefault() {
        if (scale != null) {
            int value = getPreferenceStore().getDefaultInt(getPreferenceName());
            scale.setSelection(value);
        }
        valueChanged();
    }

    @Override
    protected void doStore() {
        getPreferenceStore().setValue(getPreferenceName(), scale.getSelection());
    }

    /**
     * Returns the value that will be used for ScaleWithLegend.setIncrement(int).
     * 
     * @return the value.
     */
    public int getIncrement() {
        return incrementValue;
    }

    /**
     * Returns the value that will be used for ScaleWithLegend.setMaximum(int).
     * 
     * @return the value.
     */
    public int getMaximum() {
        return maxValue;
    }

    /**
     * Returns the value that will be used for ScaleWithLegend.setMinimum(int).
     * 
     * @return the value.
     */
    public int getMinimum() {
        return minValue;
    }

    @Override
    public int getNumberOfControls() {
        return 1;
    }

    /**
     * Returns the value that will be used for ScaleWithLegend.setPageIncrement(int).
     * 
     * @return the value.
     */
    public int getPageIncrement() {
        return pageIncrementValue;
    }

    /**
     * Returns this field editor's scale control.
     *
     * @return the scale control, or <code>null</code> if no scale field is created yet
     */
    public Scale getScaleControl() {
        return scale;
    }

    /**
     * Returns this field editor's scale control. The control is created if it does not yet exist.
     *
     * @param parent
     *            the parent
     * @return the scale control
     */
    protected Scale getScaleControl(Composite parent) {
        if (scale == null) {
            scale = new Scale(parent, SWT.HORIZONTAL);
            scale.setFont(parent.getFont());
            scale.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    valueChanged();
                }
            });
            scale.addDisposeListener(event -> scale = null);
        } else {
            checkParent(scale, parent);
        }
        return scale;
    }

    /**
     * Set default values for the various ScaleWithLegend fields. These defaults are:<br>
     * <ul>
     * <li>Minimum = 0
     * <li>Maximum = 10
     * <li>Increment = 1
     * <li>Page Increment = 1
     * </ul>
     */
    private void setDefaultValues() {
        setValues(0, 10, 1, 1);
    }

    @Override
    public void setFocus() {
        if (scale != null && !scale.isDisposed()) {
            scale.setFocus();
        }
    }

    /**
     * Set the value to be used for ScaleWithLegend.setIncrement(int) and update the ScaleWithLegend.
     * 
     * @param increment
     *            a value greater than 0.
     */
    public void setIncrement(int increment) {
        this.incrementValue = increment;
        updateScale();
    }

    /**
     * Set the value to be used for ScaleWithLegend.setMaximum(int) and update the ScaleWithLegend.
     * 
     * @param max
     *            a value greater than 0.
     */
    public void setMaximum(int max) {
        this.maxValue = max;
        updateScale();
    }

    /**
     * Set the value to be used for ScaleWithLegend.setMinumum(int) and update the ScaleWithLegend.
     * 
     * @param min
     *            a value greater than 0.
     */
    public void setMinimum(int min) {
        this.minValue = min;
        updateScale();
    }

    /**
     * Set the value to be used for ScaleWithLegend.setPageIncrement(int) and update the ScaleWithLegend.
     * 
     * @param pageIncrement
     *            a value greater than 0.
     */
    public void setPageIncrement(int pageIncrement) {
        this.pageIncrementValue = pageIncrement;
        updateScale();
    }

    /**
     * Set all ScaleWithLegend values.
     * 
     * @param min
     *            the value used for ScaleWithLegend.setMinimum(int).
     * @param max
     *            the value used for ScaleWithLegend.setMaximum(int).
     * @param increment
     *            the value used for ScaleWithLegend.setIncrement(int).
     * @param pageIncrement
     *            the value used for ScaleWithLegend.setPageIncrement(int).
     */
    private void setValues(int min, int max, int increment, int pageIncrement) {
        this.incrementValue = increment;
        this.maxValue = max;
        this.minValue = min;
        this.pageIncrementValue = pageIncrement;
        updateScale();
    }

    /**
     * Update the scale particulars with set values.
     */
    private void updateScale() {
        if (scale != null && !scale.isDisposed()) {
            scale.setMinimum(getMinimum());
            scale.setMaximum(getMaximum());
            scale.setIncrement(getIncrement());
            scale.setPageIncrement(getPageIncrement());
        }
    }

    /**
     * Informs this field editor's listener, if it has one, about a change to the value (<code>VALUE</code> property)
     * provided that the old and new values are different.
     * <p>
     * This hook is <em>not</em> called when the ScaleWithLegend is initialized (or reset to the default value) from the
     * preference store.
     * </p>
     */
    protected void valueChanged() {
        setPresentsDefaultValue(false);

        int newValue = scale.getSelection();
        if (newValue != oldValue) {
            fireStateChanged(IS_VALID, false, true);
            fireValueChanged(VALUE, new Integer(oldValue), new Integer(newValue));
            oldValue = newValue;
        }
    }
}
