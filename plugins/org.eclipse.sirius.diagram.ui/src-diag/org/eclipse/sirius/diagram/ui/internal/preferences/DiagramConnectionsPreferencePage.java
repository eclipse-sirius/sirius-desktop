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
import org.eclipse.gmf.runtime.common.ui.preferences.CheckBoxFieldEditor;
import org.eclipse.gmf.runtime.common.ui.preferences.ComboFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * This preference page change the behavior of the default GMF preference page.
 * <BR>
 * The default GMF preference page has only one combo field corresponding to
 * IPreferenceConstants.PREF_LINE_STYLE. The preference is used to draw the
 * feedback during the creation of an edge and also to determine the routing of
 * the created edge.<BR>
 * Now this page allows user to override the VSM routing style for new edges.
 * The existing GMF IPreferenceConstants.PREF_LINE_STYLE is set to MANUAL if the
 * override is disabled and to the combo value if the override is enabled.
 * 
 * @was-generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

    /**
     * Label of the lineStyle field.
     */
    private static final String LINE_STYLE_LABEL = DiagramUIMessages.ConnectionsPreferencePage_lineStyle_label;

    /**
     * Label of the enableOverride field.
     */
    private static final String ENABLE_OVERRIDE_LABEL = "Enable user specifc default values. These setting apply to all diagrams (does not affect existing elements)."; //$NON-NLS-1$

    /**
     * LineStyle field.
     */
    protected ComboFieldEditor lineStyleFieldEditor = null;

    /**
     * EnableOverride field.
     */
    protected CheckBoxFieldEditor enableOverrideFieldEditor = null;

    protected Composite fieldsParent = null;

    /**
     * Initializes the default preference values for this preference store.
     * 
     * @param preferenceStore
     *            the preference store
     */
    public static void initDefaults(IPreferenceStore preferenceStore) {
        ConnectionsPreferencePage.initDefaults(preferenceStore);
    }

    /**
     * @was-generated
     */
    public DiagramConnectionsPreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

    /**
     * Override this method to customize this preference page.
     */
    @Override
    protected void addFieldEditors(Composite composite) {
        fieldsParent = composite;
        // Create the check box to enable or not the override.
        enableOverrideFieldEditor = new CheckBoxFieldEditor(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, ENABLE_OVERRIDE_LABEL, fieldsParent);
        addField(enableOverrideFieldEditor);

        // The original field of the GMF Connections preference page, is not
        // associated to the GMF constant because the behavior of this last is
        // now different.
        lineStyleFieldEditor = new ComboFieldEditor(SiriusDiagramCorePreferences.PREF_LINE_STYLE, LINE_STYLE_LABEL, fieldsParent, ComboFieldEditor.INT_TYPE, true, 0, 0, true);
        lineStyleFieldEditor.autoStorage = true;
        addField(lineStyleFieldEditor);
        Combo lineStyleCombo = lineStyleFieldEditor.getComboControl();
        lineStyleCombo.add(DiagramUIMessages.ConnectionsPreferencePage_ConnectionView_Manual_text);
        lineStyleCombo.add(DiagramUIMessages.ConnectionsPreferencePage_ConnectionView_Rectilinear_text);
        // Add the Tree value not managed by default by GMF in this preference
        // page.
        lineStyleCombo.add(Routing.TREE_LITERAL.getLiteral());
        // Listen changes of combo to change GMF preference value used for
        // creation feedback.
        lineStyleCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent evt) {
                String comboValue = ((Combo) evt.getSource()).getText();
                if (DiagramUIMessages.ConnectionsPreferencePage_ConnectionView_Manual_text.equals(comboValue)) {
                    // Adapt Oblique value to Manual value
                    comboValue = Routing.MANUAL_LITERAL.getLiteral();
                }
                getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, Routing.get(comboValue).getValue());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();

        // Set preference store to Diagram core plugin
        IPreferenceStore diagramCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID);
        enableOverrideFieldEditor.setPreferenceStore(diagramCorePreferenceStore);
        enableOverrideFieldEditor.load();
        lineStyleFieldEditor.setPreferenceStore(diagramCorePreferenceStore);
        lineStyleFieldEditor.load();

        // Set the initial state of the lineStyle field
        enableLineStyleField(diagramCorePreferenceStore.getBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE));
    }

    /**
     * Listen changes of check-box to:
     * <UL>
     * <LI>Enable or not the line style field when the check-box status is
     * changed</LI>
     * <LI>Set the value of GMF IPreferenceConstants.PREF_LINE_STYLE preference
     * according to check-box and combo values.</LI>
     * </UL>
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
        if (enableOverrideFieldEditor.equals(event.getSource()) && event.getProperty().equals(BooleanFieldEditor.VALUE)) {
            boolean enabled = ((Boolean) event.getNewValue()).booleanValue();
            // Enable or disable the combo
            enableLineStyleField(enabled);
            // Change the GMF pref
            if (enabled) {
                // Use the set routing for feedback when the override is
                // enabled.
                getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, lineStyleFieldEditor.getStringValue());
            } else {
                // Use a manual routing for feedback when the override is
                // disabled.
                getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, Routing.MANUAL);
            }
        }
    }

    /**
     * Enable or disable the combo to select the routing style.
     * 
     * @param enable
     *            true to enable the combo to select the routing style, false
     *            otherwise.
     */
    protected void enableLineStyleField(boolean enable) {
        lineStyleFieldEditor.setEnabled(enable, fieldsParent);
        lineStyleFieldEditor.getComboControl().setEnabled(enable);
    }
}
