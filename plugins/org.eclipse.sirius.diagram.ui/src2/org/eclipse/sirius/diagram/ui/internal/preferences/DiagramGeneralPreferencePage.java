/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.part.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;

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
        setPreferenceStore(DiagramPlugin.getInstance().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        super.createFieldEditors();

        autosizeOnArrangeAll = new BooleanFieldEditor(SiriusDiagramInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), "Auto-size containers during arrange-all action.", getFieldEditorParent());
        addField(autosizeOnArrangeAll);

        moveUnlinkedNotesDuringLayout = new BooleanFieldEditor(org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(),
                "Move unlinked notes during layout", getFieldEditorParent());
        addField(moveUnlinkedNotesDuringLayout);

        autoPinOnMove = new BooleanFieldEditor(SiriusDiagramInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), "Automatically mark moved elements as pinned", getFieldEditorParent());
        addField(autoPinOnMove);

        synchronizeOnDiagramCreation = new BooleanFieldEditor(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), "Synchronized mode for new diagrams", getFieldEditorParent());
        addField(synchronizeOnDiagramCreation);

        removeHideNoteWhenAnnotatedElementRemovedHidden = new BooleanFieldEditor(SiriusDiagramInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(),
                "Remove/hide note when the annotated element is removed/hidden", getFieldEditorParent());
        addField(removeHideNoteWhenAnnotatedElementRemovedHidden);
    }

    public static void initDefaults(final IPreferenceStore preferenceStore) {
        DiagramsPreferencePage.initDefaults(preferenceStore);
        preferenceStore.setDefault(SiriusDiagramInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), true);
        preferenceStore.setDefault(org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false);
        preferenceStore.setDefault(org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.name(), false);
        preferenceStore.setDefault(SiriusDiagramInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), true);
        preferenceStore.setDefault(SiriusDiagramInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
        preferenceStore.setDefault(SiriusDiagramInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);
        preferenceStore.setDefault(org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), true);
    }
}
