/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.sirius.properties.core.api.preferences.SiriusPropertiesCorePreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The properties view preference page.
 * 
 * @author mbats
 */
public class SiriusPropertiesViewGeneralPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * The widget used to edit if we should filter the semantic tab.
     */
    private Button filterSemanticTab;

    /**
     * The widget used to edit if we should filter the default tab.
     */
    private Button filterDefaultTab;

    /**
     * The widget used to edit the max length of the name of a tab.
     */
    private Text maxTabNameLength;

    @Override
    public void init(IWorkbench workbench) {
        // do nothing
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        composite.setFont(parent.getFont());

        Composite tabFilterComposite = this.createGroup(composite, Messages.SiriusPropertiesPreferencePage_filterGroup);
        this.addFilterSemanticTabField(tabFilterComposite);
        this.addFilterDefaultTabField(tabFilterComposite);

        Composite tabNameMaxLengthComposite = createGroup(composite, Messages.SiriusPropertiesPreferencePage_tabGroup);
        this.addTabNameMaxLengthTabFields(tabNameMaxLengthComposite);

        this.initialize();
        this.checkState();

        return composite;
    }

    /**
     * Adds a widget to let the user indicate if the semantic tab should be
     * filtered.
     * 
     * @param parent
     *            The parent composite
     */
    private void addFilterSemanticTabField(Composite parent) {
        this.filterSemanticTab = new Button(parent, SWT.CHECK | SWT.LEFT);
        this.filterSemanticTab.setText(Messages.SiriusPropertiesPreferencePage_semanticTab);
        this.filterSemanticTab.setFont(parent.getFont());
        this.filterSemanticTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isSemanticTabFiltered());
        this.filterSemanticTab.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SiriusPropertiesViewGeneralPreferencePage.this.checkState();
            }
        });
        GridData gridData = new GridData();
        gridData.horizontalSpan = 1;
        this.filterSemanticTab.setLayoutData(gridData);
    }

    /**
     * Adds a widget to let the user indicate if the default tab should be
     * filtered.
     * 
     * @param parent
     *            The parent composite
     */
    private void addFilterDefaultTabField(Composite parent) {
        this.filterDefaultTab = new Button(parent, SWT.CHECK | SWT.LEFT);
        this.filterDefaultTab.setText(Messages.SiriusPropertiesPreferencePage_defaultTab);
        this.filterDefaultTab.setFont(parent.getFont());
        this.filterDefaultTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isDefaultTabFiltered());
        this.filterDefaultTab.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SiriusPropertiesViewGeneralPreferencePage.this.checkState();
            }
        });
        GridData gridData = new GridData();
        gridData.horizontalSpan = 1;
        this.filterDefaultTab.setLayoutData(gridData);
    }

    /**
     * Adds a widget to let the user specify the max length of the tab name.
     * 
     * @param parent
     *            The parent composite
     */
    private void addTabNameMaxLengthTabFields(Composite parent) {
        Label label = new Label(parent, SWT.LEFT);
        label.setFont(parent.getFont());
        label.setText(Messages.SiriusPropertiesPreferencePage_maxLengthTabName);

        this.maxTabNameLength = new Text(parent, SWT.SINGLE | SWT.BORDER);
        this.maxTabNameLength.setFont(parent.getFont());
        this.maxTabNameLength.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                SiriusPropertiesViewGeneralPreferencePage.this.checkState();
            }
        });
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        this.maxTabNameLength.setLayoutData(gridData);
    }

    /**
     * Creates a group with the given name.
     * 
     * @param parent
     *            The parent composite
     * @param text
     *            The name of the group
     * @return The group created
     */
    private Group createGroup(Composite parent, String text) {
        Group group = new Group(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, false);
        group.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 1;
        group.setLayoutData(gridData);
        group.setText(text);

        return group;
    }

    /**
     * Initializes the preference page with the current values.
     */
    private void initialize() {
        this.filterSemanticTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isSemanticTabFiltered());
        this.filterDefaultTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isDefaultTabFiltered());
        this.maxTabNameLength.setText(Integer.toString(SiriusPropertiesCorePreferences.INSTANCE.getMaxLengthTabName()));
    }

    /**
     * Validates the state of the preference page.
     */
    private void checkState() {
        try {
            int number = Integer.valueOf(this.maxTabNameLength.getText()).intValue();
            if (number >= 0 && number <= Integer.MAX_VALUE) {
                this.setErrorMessage(null);
                this.setValid(true);
            } else {
                this.setErrorMessage(Messages.SiriusPropertiesPreferencePage_maxLengthTabName_invalidInteger);
                this.setValid(false);
            }
        } catch (NumberFormatException e1) {
            this.setErrorMessage(Messages.SiriusPropertiesPreferencePage_maxLengthTabName_invalidValue);
            this.setValid(false);
        }
    }

    @Override
    public boolean performCancel() {
        this.initialize();
        return super.performCancel();
    }

    @Override
    protected void performDefaults() {
        this.filterSemanticTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isSemanticTabFilteredDefaultValue());
        this.filterDefaultTab.setSelection(SiriusPropertiesCorePreferences.INSTANCE.isDefaultTabFilteredDefaultValue());
        this.maxTabNameLength.setText(Integer.toString(SiriusPropertiesCorePreferences.INSTANCE.getMaxLengthTabNameDefaultValue()));
        super.performDefaults();
    }

    @Override
    public boolean performOk() {
        SiriusPropertiesCorePreferences.INSTANCE.setFilterSemanticTab(this.filterSemanticTab.getSelection());
        SiriusPropertiesCorePreferences.INSTANCE.setFilterDefaultTab(this.filterDefaultTab.getSelection());
        SiriusPropertiesCorePreferences.INSTANCE.setMaxLengthTabName(Integer.parseInt(this.maxTabNameLength.getText()));
        return super.performOk();
    }
}
