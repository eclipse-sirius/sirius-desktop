/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.provider.SiriusEditPlugin;
import org.eclipse.sirius.ui.business.api.preferences.DesignerUIPreferencesKeys;

/**
 * Page for viewpoint preferences.
 * 
 * @author ymortier
 */
public class DesignerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Label of the check box emptyAirdFragmentOnControl.
     */
    public static final String EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL = "Always create an aird fragment on control";

    private BooleanFieldEditor refreshOnRepresentationOpening;

    private BooleanFieldEditor autoRefresh;

    private BooleanFieldEditor emptyAirdFragmentOnControl;

    private BooleanFieldEditor defensiveFileEdit;

    private BooleanFieldEditor traceMode;

    private BooleanFieldEditor groupEnable;

    private IntegerFieldEditor groupTrigger;

    private IntegerFieldEditor groupSize;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        setPreferenceStore(SiriusEditPlugin.getPlugin().getPreferenceStore());

        Composite parent = getFieldEditorParent();
        if (parent.getLayout() == null) {
            GridLayout gridLayout = new GridLayout(1, false);
            parent.setLayout(gridLayout);
        }

        addRefreshFields(parent);
        addFilesFields(parent);
        addProfilingField(parent);
        addGroupTreeItemsField(parent);

    }

    private void addFilesFields(Composite parent) {
        Composite fileComposite = createGroup(parent, "Files");

        emptyAirdFragmentOnControl = new BooleanFieldEditorWithHelp(DesignerPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL,
                "Allow the creation of an empty aird fragment if there is no selected representation", new Composite(fileComposite, SWT.NONE));
        addField(emptyAirdFragmentOnControl);

        defensiveFileEdit = new BooleanFieldEditor(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, "Validate file edits on command application.", new Composite(fileComposite, SWT.NONE));
        addField(defensiveFileEdit);

    }

    private void addRefreshFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, "Refresh");

        refreshOnRepresentationOpening = new BooleanFieldEditor(DesignerUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), "Do refresh on representation opening", new Composite(
                refreshComposite, SWT.NONE));
        addField(refreshOnRepresentationOpening);

        autoRefresh = new BooleanFieldEditor(DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(), "Automatic Refresh", new Composite(refreshComposite, SWT.NONE));
        addField(autoRefresh);
    }

    private void addProfilingField(Composite parent) {
        Composite profilerComposite = createGroup(parent, "Profiler");

        traceMode = new BooleanFieldEditorWithHelp(CommonPreferencesConstants.PREF_TRACE_ON, "Profiling", "Open the profiler view: Sirius Profiler > Time Profiler View.", new Composite(
                profilerComposite, SWT.NONE));
        addField(traceMode);
    }

    private void addGroupTreeItemsField(Composite parent) {
        Composite groupComposite = createGroup(parent, "Group tree items");

        groupEnable = new BooleanFieldEditorWithHelp(CommonPreferencesConstants.PREF_GROUP_ENABLE, "Enable",
                "This feature handles a new intermediary tree level if the children size is above the threshold.", new Composite(groupComposite, SWT.NONE));

        Composite groupTriggerComposite = new Composite(groupComposite, SWT.NONE);
        groupTriggerComposite.setLayout(new GridLayout(2, false));
        groupTriggerComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupTrigger = new IntegerFieldEditor(CommonPreferencesConstants.PREF_GROUP_TRIGGER, "Threshold", groupTriggerComposite);
        groupTrigger.setValidRange(0, Integer.MAX_VALUE);

        Composite groupSizeComposite = new Composite(groupComposite, SWT.NONE);
        groupSizeComposite.setLayout(new GridLayout(2, false));
        groupSizeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupSize = new IntegerFieldEditor(CommonPreferencesConstants.PREF_GROUP_SIZE, "Group size", groupSizeComposite);
        groupSize.setValidRange(0, Integer.MAX_VALUE);
        addField(groupEnable);
        addField(groupTrigger);
        addField(groupSize);
    }

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
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(final IWorkbench workbench) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();

        // Set preference store to common ui plugin, FileStatusPrecommitListener
        // and profiler
        // will take the value there.
        defensiveFileEdit.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        defensiveFileEdit.load();

        traceMode.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        traceMode.load();

        groupEnable.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        groupEnable.load();

        groupTrigger.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        groupTrigger.load();

        groupSize.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        groupSize.load();
    }

}
