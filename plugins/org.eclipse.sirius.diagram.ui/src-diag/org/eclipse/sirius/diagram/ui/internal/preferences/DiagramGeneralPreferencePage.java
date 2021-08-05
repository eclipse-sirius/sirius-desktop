/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.internal.preference.ScaleWithLegendFieldEditorWithHelp;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * Defines the main "Sirius > Sirius Diagram" preferences page.
 */
@SuppressWarnings("deprecation")
public class DiagramGeneralPreferencePage extends DiagramsPreferencePage {

    /**
     * Max quality level used to export diagram as image
     */
    private static final int MAX_QUALITY = 10;

    /**
     * Min quality level used to export diagram as image
     */
    private static final int MIN_QUALITY = 0;

    /**
     * Quality increment used to export diagram as image
     */
    private static final int INCREMENT_QUALITY = 1;

    private BooleanFieldEditor moveUnlinkedNotesDuringLayout;

    private BooleanFieldEditor synchronizeOnDiagramCreation;

    private ScaleWithLegendFieldEditorWithHelp scaleWithLegendFieldEditor;

    public DiagramGeneralPreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

    @Override
    protected void createFieldEditors() {
        super.createFieldEditors();

        addQualityExportField(getFieldEditorParent());

        addBooleanPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), Messages.DiagramGeneralPreferencePage_arrangeAndAutoSizeContainersLabel);
        moveUnlinkedNotesDuringLayout = addBooleanPreference(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), Messages.DiagramGeneralPreferencePage_moveUnlinkedNodeLabel);
        addBooleanPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), Messages.DiagramGeneralPreferencePage_pinMovedElementsLabel);
        synchronizeOnDiagramCreation = addBooleanPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(),
                Messages.DiagramGeneralPreferencePage_synchronizedModeLabel);
        addBooleanPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name(), Messages.DiagramGeneralPreferencePage_showSynchronizeStatusDecoratorLabel);
        addBooleanPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), Messages.DiagramGeneralPreferencePage_removeHideNoteLabel);
    }

    private BooleanFieldEditor addBooleanPreference(String key, String label) {
        BooleanFieldEditor fieldEditor = new BooleanFieldEditor(key, label, getFieldEditorParent());
        addField(fieldEditor);
        return fieldEditor;
    }

    @Override
    protected void initialize() {
        super.initialize();

        // Set preference store to Diagram core plugin
        IPreferenceStore diagramCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID);
        moveUnlinkedNotesDuringLayout.setPreferenceStore(diagramCorePreferenceStore);
        moveUnlinkedNotesDuringLayout.load();

        synchronizeOnDiagramCreation.setPreferenceStore(diagramCorePreferenceStore);
        synchronizeOnDiagramCreation.load();

        // Set preference store to Sirius UI core plugin
        IPreferenceStore siriusUiCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, SiriusEditPlugin.ID);
        scaleWithLegendFieldEditor.setPreferenceStore(siriusUiCorePreferenceStore);
        scaleWithLegendFieldEditor.load();
    }

    public static void initDefaults(final IPreferenceStore preferenceStore) {
        DiagramsPreferencePage.initDefaults(preferenceStore);

        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name(), false);
        preferenceStore.setDefault(SiriusDiagramUiPreferencesKeys.PREF_SCALE_DIAGRAMS_ON_EXPORT.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiPreferencesKeys.PREF_MAXIMUM_EXPORT_BUFFER_SIZE.name(), 4125000);
        preferenceStore.setDefault(SiriusDiagramUiPreferencesKeys.PREF_MAXIMUM_EXPORT_BUFFER_SIZE_WINDOWS.name(), 50000000);
    }

    private void addQualityExportField(Composite parent) {

        Group imageSizeGroup = new Group(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, false);
        imageSizeGroup.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 2;
        imageSizeGroup.setLayoutData(gridData);
        imageSizeGroup.setText(Messages.DiagramGeneralPreferencePage_sizeGroupLabel);

        scaleWithLegendFieldEditor = new ScaleWithLegendFieldEditorWithHelp(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name(), Messages.DiagramGeneralPreferencePage_sizeGroupLabel,
                org.eclipse.sirius.viewpoint.provider.Messages.ExportAsImage_sizeTooltip, imageSizeGroup);
        scaleWithLegendFieldEditor.setMinimum(MIN_QUALITY);
        scaleWithLegendFieldEditor.setMaximum(MAX_QUALITY);
        scaleWithLegendFieldEditor.setPageIncrement(INCREMENT_QUALITY);

        addField(scaleWithLegendFieldEditor);
    }
}
