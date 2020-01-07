/*******************************************************************************
 * Copyright (c) 2008, 2020 THALES GLOBAL SERVICES and others.
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

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Page for viewpoint preferences.
 *
 * @author ymortier
 */
public class SiriusPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Label of the check box emptyAirdFragmentOnControl.
     */
    public static final String EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL = Messages.SiriusPreferencePage_emptyAirdOnControl;

    private BooleanFieldEditor refreshOnRepresentationOpening;

    private BooleanFieldEditor autoRefresh;

    private BooleanFieldEditor emptyAirdFragmentOnControl;

    private BooleanFieldEditor defensiveFileEdit;

    private BooleanFieldEditor traceMode;

    private BooleanFieldEditor groupEnable;

    private BooleanFieldEditor autoSessionEditorOpening;

    private BooleanFieldEditor askUserToSaveAfterMigration;

    private IntegerFieldEditor groupTrigger;

    private IntegerFieldEditor groupSize;

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
        addSessionEditorFields(parent);
        addMigrationFields(parent);
    }

    private void addFilesFields(Composite parent) {
        Composite fileComposite = createGroup(parent, Messages.SiriusPreferencePage_filesGroup);

        emptyAirdFragmentOnControl = new BooleanFieldEditorWithHelp(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), SiriusPreferencePage.EMPTY_AIRD_ON_CONTROL_CHECKBOX_LABEL,
                Messages.SiriusPreferencePage_emptyAirdOnControl_help, new Composite(fileComposite, SWT.NONE));
        addField(emptyAirdFragmentOnControl);

        defensiveFileEdit = new BooleanFieldEditor(CommonPreferencesConstants.PREF_DEFENSIVE_EDIT_VALIDATION, Messages.SiriusPreferencePage_defensiveEditValidation,
                new Composite(fileComposite, SWT.NONE));
        addField(defensiveFileEdit);

    }

    private void addSessionEditorFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, Messages.SiriusPreferencePage_sessionEditorGroup);

        autoSessionEditorOpening = new BooleanFieldEditor(SessionEditorUIPreferencesKeys.PREF_OPEN_SESSION_EDITOR_ON_SESSION_OPEN.name(), Messages.SiriusPreferencePage_autoSessionEditorOpening,
                new Composite(refreshComposite, SWT.NONE));
        addField(autoSessionEditorOpening);

    }

    private void addMigrationFields(Composite parent) {
        Composite migrationComposite = createGroup(parent, Messages.SiriusPreferencePage_migrationGroup);

        askUserToSaveAfterMigration = new BooleanFieldEditor(CommonPreferencesConstants.PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION, Messages.SiriusPreferencePage_askUserToSaveAfterMigration,
                new Composite(migrationComposite, SWT.NONE));
        addField(askUserToSaveAfterMigration);

    }

    private void addRefreshFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, Messages.SiriusPreferencePage_refreshGroup);

        refreshOnRepresentationOpening = new BooleanFieldEditor(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), Messages.SiriusPreferencePage_refreshOnRepresentationOpening,
                new Composite(refreshComposite, SWT.NONE));
        addField(refreshOnRepresentationOpening);

        autoRefresh = new BooleanFieldEditor(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), Messages.SiriusPreferencePage_autoRefresh, new Composite(refreshComposite, SWT.NONE));
        addField(autoRefresh);
    }

    private void addProfilingField(Composite parent) {
        Composite profilerComposite = createGroup(parent, Messages.SiriusPreferencePage_profilerGroup);

        traceMode = new BooleanFieldEditorWithHelp(CommonPreferencesConstants.PREF_TRACE_ON, Messages.SiriusPreferencePage_profiling, Messages.SiriusPreferencePage_profiling_help,
                new Composite(profilerComposite, SWT.NONE));
        addField(traceMode);
    }

    private void addGroupTreeItemsField(Composite parent) {
        Composite groupComposite = createGroup(parent, Messages.SiriusPreferencePage_groupingGroup);

        groupEnable = new BooleanFieldEditorWithHelp(CommonPreferencesConstants.PREF_GROUP_ENABLE, Messages.SiriusPreferencePage_enableGrouping, Messages.SiriusPreferencePage_enableGrouping_help,
                new Composite(groupComposite, SWT.NONE));

        Composite groupTriggerComposite = new Composite(groupComposite, SWT.NONE);
        groupTriggerComposite.setLayout(new GridLayout(3, false));
        groupTriggerComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupTrigger = new IntegerFieldEditorWithHelp(CommonPreferencesConstants.PREF_GROUP_TRIGGER, Messages.SiriusPreferencePage_groupingTheshold,
                Messages.SiriusPreferencePage_groupingTheshold_help, groupTriggerComposite);
        groupTrigger.setValidRange(0, Integer.MAX_VALUE);

        Composite groupSizeComposite = new Composite(groupComposite, SWT.NONE);
        groupSizeComposite.setLayout(new GridLayout(3, false));
        groupSizeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupSize = new IntegerFieldEditorWithHelp(CommonPreferencesConstants.PREF_GROUP_SIZE, Messages.SiriusPreferencePage_groupingGroupSize, Messages.SiriusPreferencePage_groupingGroupSize_help,
                groupSizeComposite);
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

    @Override
    public void init(final IWorkbench workbench) {
    }

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

        askUserToSaveAfterMigration.setPreferenceStore(SiriusTransPlugin.getPlugin().getPreferenceStore());
        askUserToSaveAfterMigration.load();

        IPreferenceStore siriusPluginPreferences = SiriusEditPlugin.getPlugin().getCorePreferenceStore();
        autoRefresh.setPreferenceStore(siriusPluginPreferences);
        autoRefresh.load();
        emptyAirdFragmentOnControl.setPreferenceStore(siriusPluginPreferences);
        emptyAirdFragmentOnControl.load();
    }
}
