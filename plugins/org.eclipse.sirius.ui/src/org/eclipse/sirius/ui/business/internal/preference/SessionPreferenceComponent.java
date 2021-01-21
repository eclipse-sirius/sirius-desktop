/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.internal.preference;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.sirius.ui.tools.internal.preference.SiriusPreferencePage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * UI component to manage the preferences at the Session level.
 * 
 * @author lfasani
 *
 */
public class SessionPreferenceComponent {

    private Button useProjectSettings;

    private Group propertiesGroup;

    private Hyperlink preferenceLink;

    private Button refreshAtOpeningButton;

    private Button autoRefreshbutton;

    /**
     * The {@link Composite} used to manage the preferences.
     * 
     * @param parent
     *            the parent Composite
     * @return the composite
     */
    public Composite createComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL);
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);

        createHeader(composite);
        createRefreshPreferencesGroup(composite);

        return composite;
    }

    private void createHeader(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setFont(parent.getFont());
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        useProjectSettings = new Button(composite, SWT.CHECK);
        useProjectSettings.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setProjectSettingsEnabled(useProjectSettings.getSelection());
            }
        });
        useProjectSettings.setText(Messages.SiriusPreferencesPropertyPage_enableProjectSpecificSettings);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(useProjectSettings);

        preferenceLink = new Hyperlink(composite, SWT.NONE);
        preferenceLink.setUnderlined(true);
        preferenceLink.setText(Messages.SiriusPreferencesPropertyPage_configureWorkspaceSettings);
        preferenceLink.addHyperlinkListener(new HyperlinkAdapter() {

            @Override
            public void linkActivated(HyperlinkEvent e) {
                PreferenceDialog dlg = PreferencesUtil.createPreferenceDialogOn(preferenceLink.getShell(), SiriusPreferencePage.PAGE_ID, new String[] { SiriusPreferencePage.PAGE_ID }, null);
                dlg.open();
            }
        });

        Label horizontalLine = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1));
    }

    private void setProjectSettingsEnabled(boolean enabled) {
        propertiesGroup.setEnabled(enabled);
        autoRefreshbutton.setEnabled(enabled);
        refreshAtOpeningButton.setEnabled(enabled);

        preferenceLink.setEnabled(!enabled);
        if (!enabled) {
            preferenceLink.setForeground(JFaceColors.getHyperlinkText(preferenceLink.getDisplay()));
        } else {
            preferenceLink.setForeground(preferenceLink.getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
        }
    }

    private Group createRefreshPreferencesGroup(Composite parent) {
        propertiesGroup = new Group(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, false);
        propertiesGroup.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 1;
        propertiesGroup.setLayoutData(gridData);
        propertiesGroup.setText(Messages.SiriusPreferencePage_refreshGroup);

        Composite refreshAtOpeningComposite = new Composite(propertiesGroup, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 2;
        refreshAtOpeningComposite.setLayout(layout);
        refreshAtOpeningComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        refreshAtOpeningButton = new Button(refreshAtOpeningComposite, SWT.CHECK);
        refreshAtOpeningButton.setText(org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_refreshOnRepresentationOpening);

        Composite autoRefreshcomposite = new Composite(propertiesGroup, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 2;
        autoRefreshcomposite.setLayout(layout);
        autoRefreshcomposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        autoRefreshbutton = new Button(autoRefreshcomposite, SWT.CHECK);
        autoRefreshbutton.setText(org.eclipse.sirius.viewpoint.provider.Messages.SiriusPreferencePage_autoRefresh);

        return propertiesGroup;
    }

    /**
     * setProjectSpecificSettings.
     * 
     * @param value
     *            the value
     */
    public void setProjectSpecificSettings(boolean value) {
        useProjectSettings.setSelection(value);
        setProjectSettingsEnabled(value);
    }

    /**
     * setAutoRefresh.
     * 
     * @param value
     *            the value
     */
    public void setAutoRefresh(boolean value) {
        autoRefreshbutton.setSelection(value);
    }

    /**
     * setRefreshAtOpening.
     * 
     * @param value
     *            the value
     */
    public void setRefreshAtOpening(boolean value) {
        refreshAtOpeningButton.setSelection(value);
    }

    public boolean isProjectSpecificSettings() {
        return useProjectSettings.getSelection();
    }

    public boolean isAutoRefresh() {
        return autoRefreshbutton.getSelection();
    }

    public boolean isRefreshAtOpening() {
        return refreshAtOpeningButton.getSelection();
    }
}
