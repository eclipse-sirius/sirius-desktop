/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @was-generated
 */
public class DiagramGeneralPreferencePage extends DiagramsPreferencePage {

    private BooleanFieldEditor autosizeOnArrangeAll;

    private BooleanFieldEditor moveUnlinkedNotesDuringLayout;

    private BooleanFieldEditor autoPinOnMove;

    private BooleanFieldEditor synchronizeOnDiagramCreation;

    private BooleanFieldEditor removeHideNoteWhenAnnotatedElementRemovedHidden;

    /**
     * @was-generated
     */
    public DiagramGeneralPreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        super.createFieldEditors();

        autosizeOnArrangeAll = new BooleanFieldEditor(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), "Auto-size containers during arrange-all action.",
                getFieldEditorParent());
        addField(autosizeOnArrangeAll);

        moveUnlinkedNotesDuringLayout = new BooleanFieldEditor(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), "Move unlinked notes during layout", getFieldEditorParent());
        addField(moveUnlinkedNotesDuringLayout);

        autoPinOnMove = new BooleanFieldEditor(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), "Automatically mark moved elements as pinned", getFieldEditorParent());
        addField(autoPinOnMove);

        synchronizeOnDiagramCreation = new BooleanFieldEditor(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), "Synchronized mode for new diagrams",
                getFieldEditorParent());
        addField(synchronizeOnDiagramCreation);

        removeHideNoteWhenAnnotatedElementRemovedHidden = new BooleanFieldEditor(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(),
                "Remove/hide note when the annotated element is removed/hidden", getFieldEditorParent());
        addField(removeHideNoteWhenAnnotatedElementRemovedHidden);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();

        // Set preference store to Diagram core plugin
        IPreferenceStore diagramCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID);
        moveUnlinkedNotesDuringLayout.setPreferenceStore(diagramCorePreferenceStore);
        moveUnlinkedNotesDuringLayout.load();

        synchronizeOnDiagramCreation.setPreferenceStore(diagramCorePreferenceStore);
        synchronizeOnDiagramCreation.load();
    }

    public static void initDefaults(final IPreferenceStore preferenceStore) {
        DiagramsPreferencePage.initDefaults(preferenceStore);

        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
    }
}
