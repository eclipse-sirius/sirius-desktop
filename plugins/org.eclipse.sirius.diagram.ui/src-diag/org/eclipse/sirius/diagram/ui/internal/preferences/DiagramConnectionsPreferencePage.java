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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.gmf.runtime.common.ui.preferences.CheckBoxFieldEditor;
import org.eclipse.gmf.runtime.common.ui.preferences.ComboFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesMessages;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * This preference page change the behavior of the default GMF preference page. <BR>
 * The default GMF preference page has only one combo field corresponding to IPreferenceConstants.PREF_LINE_STYLE. The
 * preference is used to draw the feedback during the creation of an edge and also to determine the routing of the
 * created edge.<BR>
 * Now this page allows user to override the VSM routing style for new edges. The existing GMF
 * IPreferenceConstants.PREF_LINE_STYLE is set to MANUAL if the override is disabled and to the combo value if the
 * override is enabled.
 * 
 * @was-generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {
    /**
     * An interface to declare {@link setPresentsDefaultValue} as in {@link FieldEditor#setPresentsDefaultValue}. But in
     * the current DiagramConnectionsPreferencePage we can not call the protected method, On contrary of
     * {@link ConnectionsPreferencePage}. So we must, override each field class to implement this interface.
     * 
     * @author lredor
     *
     */
    interface SpecificFieldEditor {
        void setPresentsDefaultValue(boolean booleanValue);
    }

    /**
     * A specific CheckBoxFieldEditor to have specific layout and a public setPresentsDefaultValue method.
     * 
     * @author lredor
     */
    class SpecificCheckBoxFieldEditor extends CheckBoxFieldEditor implements SpecificFieldEditor {
        int numColumns;

        public SpecificCheckBoxFieldEditor(String name, String label, Composite aParent, int numColumns) {
            super(name, label, aParent);
            this.numColumns = numColumns;
        }

        @Override
        protected void createControl(Composite parent) {
            // Do not override parent layout as super method
            doFillIntoGrid(parent, numColumns);
        }

        /**
         * Sets whether this field editor is presenting the default value.
         *
         * @param booleanValue
         *            <code>true</code> if the default value is being presented, and <code>false</code> otherwise
         */
        @Override
        public void setPresentsDefaultValue(boolean booleanValue) {
            super.setPresentsDefaultValue(booleanValue);
        }
    }

    /**
     * A specific RadioGroupFieldEditor to have specific layout and a public setPresentsDefaultValue method.
     * 
     * @author lredor
     */
    class SpecificRadioGroupFieldEditor extends RadioGroupFieldEditor implements SpecificFieldEditor {
        public SpecificRadioGroupFieldEditor(String name, String labelText, int numColumns, String[][] labelAndValues, Composite parent, boolean useGroup) {
            super(name, labelText, numColumns, labelAndValues, parent, useGroup);
        }

        @Override
        protected void createControl(Composite parent) {
            // Do not override parent layout as super method
            doFillIntoGrid(parent, 1);
        }

        /**
         * Sets whether this field editor is presenting the default value.
         *
         * @param booleanValue
         *            <code>true</code> if the default value is being presented, and <code>false</code> otherwise
         */
        @Override
        public void setPresentsDefaultValue(boolean booleanValue) {
            super.setPresentsDefaultValue(booleanValue);
        }
    }

    /**
     * A specific ComboFieldEditor to have specific layout and a public setPresentsDefaultValue method.
     * 
     * @author lredor
     */
    class SpecificComboFieldEditor extends ComboFieldEditor implements SpecificFieldEditor {
        public SpecificComboFieldEditor(String name, String labelText, Composite parent, int aType, boolean aSeparateLine, int anIndent, int aWidth, boolean aReadOnly) {
            super(name, labelText, parent, aType, aSeparateLine, anIndent, aWidth, aReadOnly);
        }

        @Override
        protected void createControl(Composite parent) {
            // Do not override parent layout as super method
            doFillIntoGrid(parent, 2);
        }

        /* Overridden to not create a label in front of the combo */
        @Override
        protected void doFillIntoGrid(Composite parent, int numColumns) {
            GridData gd = new GridData();
            gd.horizontalSpan = numColumns;

            // Width is affected only if it was specified and
            // not zero
            if (width == 0) {
                gd.horizontalAlignment = GridData.FILL;
                gd.grabExcessHorizontalSpace = true;
            } else {
                gd.widthHint = width;
            }

            int flags = SWT.CLIP_CHILDREN | SWT.CLIP_SIBLINGS | SWT.DROP_DOWN;
            if (readOnly) {
                flags = flags | SWT.READ_ONLY;
            }
            combo = new Combo(parent, flags);
            combo.setLayoutData(gd);
        }

        /**
         * Sets whether this field editor is presenting the default value.
         *
         * @param booleanValue
         *            <code>true</code> if the default value is being presented, and <code>false</code> otherwise
         */
        @Override
        public void setPresentsDefaultValue(boolean booleanValue) {
            super.setPresentsDefaultValue(booleanValue);
        }
    }

    @SuppressWarnings("restriction")
    private static final String REVERSE_JUMP_LINKS_LABEL = DiagramUIPropertiesMessages.ConnectionAppearanceDetails_ReverseJumpLinksLabel_Text;

    @SuppressWarnings("restriction")
    private static final String JUMP_LINKS_STATUS_LABEL = DiagramUIPropertiesMessages.ConnectionAppearanceDetails_JumpLinksLabel_Text;

    @SuppressWarnings("restriction")
    private static final String JUMP_LINKS_TYPE_LABEL = DiagramUIPropertiesMessages.ConnectionAppearanceDetails_JumpLinkTypeLabel_Text;

    @SuppressWarnings("restriction")
    private static final String JUMP_LINKS_GROUP_LABEL = DiagramUIPropertiesMessages.ConnectionAppearanceDetails_JumpLinkGroupLabel_Text;

    /**
     * This preference page displays preferences from 2 scopse (diagram.ui and diagram "core"). The diagram.ui is the
     * standard {@link @PreferencePage#preferenceStore}, and the following if for diagram "core".
     */
    IPreferenceStore diagramCorePreferenceStore;

    /**
     * The field editors corresponding to the above preference store, or <code>null</code> if not created yet.
     */
    private List<FieldEditor> coreFields = null;

    /**
     * The first invalid field editor corresponding to the above preference store, or <code>null</code> if all field
     * editors are valid.
     */
    private FieldEditor invalidCoreFieldEditor = null;

    /**
     * "Show link between label and edge" field.
     */
    protected CheckBoxFieldEditor showLinkFieldEditor = null;

    /**
     * LineStyle field.
     */
    protected ComboFieldEditor lineStyleFieldEditor = null;

    /**
     * The widget used to edit if we should override the line style.
     */
    protected CheckBoxFieldEditor enableLineStyleOverrideFieldEditor = null;

    /**
     * EnableJumpLinkOverride field.
     */
    protected CheckBoxFieldEditor enableJumpLinkOverrideFieldEditor = null;

    /**
     * ReversJumpLink field.
     */
    protected CheckBoxFieldEditor reverseJumpLinkFieldEditor = null;

    private Group specificDefaultValuesGroup;

    private RadioGroupFieldEditor jumpLinkStatusGroup;

    private RadioGroupFieldEditor jumpLinkTypeGroup;

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
        setCorePreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID));
    }

    /**
     * Override this method to customize this preference page.
     */
    @Override
    protected void addFieldEditors(Composite composite) {
        // Create the check box to enable or not the show link between label and edge on selection.
        showLinkFieldEditor = new CheckBoxFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name(), Messages.DiagramConnectionsPreferencePage_showEdgeLabelLinkOnSelect,
                composite);
        addField(showLinkFieldEditor);

        specificDefaultValuesGroup = createGroup(composite, Messages.DiagramConnectionsPreferencePage_defaultValuesGroup_title, 3, false);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 1;
        specificDefaultValuesGroup.setLayoutData(gridData);

        // Add fields concerning line style
        addLineStylePreferences(specificDefaultValuesGroup);

        // Add fields concerning jump links
        addJumpLinksPreferences(specificDefaultValuesGroup);
    }

    /**
     * Creates a group with the given name.
     * 
     * @param parent
     *            The parent composite
     * @param text
     *            The name of the group
     * @param numColumns
     *            the number of columns in the grid
     * @param makeColumnsEqualWidth
     *            whether or not the columns will have equal width
     * @return The group created
     */
    private Group createGroup(Composite parent, String text, int numColumns, boolean makeColumnsEqualWidth) {
        Group group = new Group(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(numColumns, makeColumnsEqualWidth);
        group.setLayout(gridLayout);
        group.setText(text);
        return group;
    }

    /**
     * Add widgets to let the user indicate if the line style should be overridden and with what value.
     * 
     * @param parent
     *            The parent composite
     */
    private void addLineStylePreferences(Composite parent) {
        enableLineStyleOverrideFieldEditor = new SpecificCheckBoxFieldEditor(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, Messages.DiagramConnectionsPreferencePage_enableLineStyleOverride_label,
                parent, 1);
        addCoreField(enableLineStyleOverrideFieldEditor);
        lineStyleFieldEditor = new SpecificComboFieldEditor(SiriusDiagramCorePreferences.PREF_LINE_STYLE, "", parent, ComboFieldEditor.INT_TYPE, false, 0, 0, true); //$NON-NLS-1$
        addCoreField(lineStyleFieldEditor);

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

    private void addJumpLinksPreferences(Composite parent) {
        // Create the check box to enable or not the override of jump links property.
        enableJumpLinkOverrideFieldEditor = new SpecificCheckBoxFieldEditor(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE, JUMP_LINKS_GROUP_LABEL, parent, 1);
        GridData gd = (GridData) enableJumpLinkOverrideFieldEditor.getCheckbox().getLayoutData();
        gd.verticalAlignment = SWT.TOP;
        addCoreField(enableJumpLinkOverrideFieldEditor);

        // Create the jump links status radio group
        String[][] labelAndValues = new String[JumpLinkStatus.VALUES.size()][2];
        // Add an entry for each JumpLinkStatus
        for (int i = 0; i < JumpLinkStatus.VALUES.size(); i++) {
            AbstractEnumerator literal = (AbstractEnumerator) JumpLinkStatus.VALUES.get(i);
            String propertyValueName = translate(literal);
            labelAndValues[i][0] = propertyValueName;
            labelAndValues[i][1] = Integer.toString(literal.getValue());
        }
        jumpLinkStatusGroup = new SpecificRadioGroupFieldEditor(SiriusDiagramCorePreferences.PREF_JUMP_LINK_STATUS, JUMP_LINKS_STATUS_LABEL, 2, labelAndValues, parent, true);
        addCoreField(jumpLinkStatusGroup);

        // Create the jump links type radio group
        labelAndValues = new String[JumpLinkType.VALUES.size()][2];
        // Add an entry for each JumpLinkType
        for (int i = 0; i < JumpLinkType.VALUES.size(); i++) {
            AbstractEnumerator literal = (AbstractEnumerator) JumpLinkType.VALUES.get(i);
            String propertyValueName = translate(literal);
            labelAndValues[i][0] = propertyValueName;
            labelAndValues[i][1] = Integer.toString(literal.getValue());
        }
        jumpLinkTypeGroup = new SpecificRadioGroupFieldEditor(SiriusDiagramCorePreferences.PREF_JUMP_LINK_TYPE, JUMP_LINKS_TYPE_LABEL, 2, labelAndValues, parent, true);
        addCoreField(jumpLinkTypeGroup);

        // Create a blank text just to align reverse jump link check box
        new Label(parent, SWT.NONE);

        // Create the check box for reverse jump link field
        reverseJumpLinkFieldEditor = new SpecificCheckBoxFieldEditor(SiriusDiagramCorePreferences.PREF_REVERSE_JUMP_LINK, REVERSE_JUMP_LINKS_LABEL, parent, 2);
        addCoreField(reverseJumpLinkFieldEditor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();
        if (coreFields != null) {
            Iterator<FieldEditor> e = coreFields.iterator();
            while (e.hasNext()) {
                FieldEditor pe = e.next();
                pe.setPage(this);
                pe.setPropertyChangeListener(this);
                pe.setPreferenceStore(getCorePreferenceStore());
                pe.load();
            }
        }

        // Set the initial state of the lineStyle field
        enableLineStyleField(diagramCorePreferenceStore.getBoolean(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE));
        enableJumpLinkField(diagramCorePreferenceStore.getBoolean(SiriusDiagramCorePreferences.PREF_JUMP_LINK_ENABLE_OVERRIDE));
    }

    /**
     * Listen changes of check-box to:
     * <UL>
     * <LI>Enable or not the line style field when the check-box status is changed</LI>
     * <LI>Set the value of GMF IPreferenceConstants.PREF_LINE_STYLE preference according to check-box and combo
     * values.</LI>
     * </UL>
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals(FieldEditor.IS_VALID)) {
            boolean newValue = ((Boolean) event.getNewValue()).booleanValue();
            // If the new value is true then we must check all field editors.
            // If it is false, then the page is invalid in any case.
            if (newValue) {
                checkState();
            } else {
                if (coreFields.contains(event.getSource())) {
                    invalidCoreFieldEditor = (FieldEditor) event.getSource();
                    setValid(newValue);
                } else {
                    super.propertyChange(event);
                }
            }
        }

        if (enableLineStyleOverrideFieldEditor.equals(event.getSource()) && event.getProperty().equals(BooleanFieldEditor.VALUE)) {
            boolean enabled = ((Boolean) event.getNewValue()).booleanValue();
            // Enable or disable the combo
            enableLineStyleField(enabled);
            // Change the GMF pref
            if (enabled) {
                // Use the set routing for feedback when the override is enabled.
                getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, lineStyleFieldEditor.getStringValue());
            } else {
                // Use a manual routing for feedback when the override is disabled.
                getPreferenceStore().setValue(IPreferenceConstants.PREF_LINE_STYLE, Routing.MANUAL);
            }
        }
        if (enableJumpLinkOverrideFieldEditor.equals(event.getSource()) && event.getProperty().equals(BooleanFieldEditor.VALUE)) {
            boolean enabled = ((Boolean) event.getNewValue()).booleanValue();
            // Enable or disable the combo
            enableJumpLinkField(enabled);
        }
    }

    /**
     * Enable or disable the combo to select the routing style.
     *
     * @param enabled
     *            true to enable the combo to select the routing style, false otherwise.
     */
    protected void enableLineStyleField(boolean enabled) {
        lineStyleFieldEditor.setEnabled(enabled, specificDefaultValuesGroup);
        lineStyleFieldEditor.getComboControl().setEnabled(enabled);
    }

    /**
     * Enable or disable the fields corresponding to the jump links properties.
     *
     * @param enabled
     *            true to enable the combo to select the routing style, false otherwise.
     */
    protected void enableJumpLinkField(boolean enabled) {
        jumpLinkStatusGroup.setEnabled(enabled, specificDefaultValuesGroup);
        jumpLinkTypeGroup.setEnabled(enabled, specificDefaultValuesGroup);
        reverseJumpLinkFieldEditor.setEnabled(enabled, specificDefaultValuesGroup);
    }

    /**
     * Adds the given field editor, corresponding to core preferences, to this page.
     *
     * @param editor
     *            the field editor
     */
    protected void addCoreField(FieldEditor editor) {
        if (coreFields == null) {
            coreFields = new ArrayList<>();
        }
        coreFields.add(editor);
    }

    /**
     * Returns the translated string representing the connection appearance properties. This is not a generic method; it
     * needs to be updated if it is to handle a new property.<BR>
     * Copied from
     * {@link org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ConnectionAppearancePropertySection#translate(AbstractEnumerator)}
     * 
     * @param literal
     *            the enumerator of literals
     * @return the translated string
     */
    private String translate(AbstractEnumerator literal) {
        if (JumpLinkType.SEMICIRCLE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksType_SemiCircle;
        } else if (JumpLinkType.SQUARE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksType_Square;
        } else if (JumpLinkType.CHAMFERED_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksType_Chamfered;
        } else if (JumpLinkType.TUNNEL_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksType_Tunnel;
        } else if (JumpLinkStatus.NONE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksStatus_None;
        } else if (JumpLinkStatus.ALL_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksStatus_All;
        } else if (JumpLinkStatus.BELOW_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksStatus_Below;
        } else if (JumpLinkStatus.ABOVE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_JumplinksStatus_Above;
        } else if (Smoothness.NONE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_Smoothness_SmoothNone;
        } else if (Smoothness.NORMAL_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_Smoothness_SmoothNormal;
        } else if (Smoothness.LESS_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_Smoothness_SmoothLess;
        } else if (Smoothness.MORE_LITERAL.equals(literal)) {
            return DiagramUIMessages.PropertyDescriptorFactory_Smoothness_SmoothMore;
        } else if (Routing.MANUAL_LITERAL.equals(literal)) {
            return DiagramUIMessages.ConnectionAppearancePropertySection_Router_Manual;
        } else if (Routing.RECTILINEAR_LITERAL.equals(literal)) {
            return DiagramUIMessages.ConnectionAppearancePropertySection_Router_Rectilinear;
        } else if (Routing.TREE_LITERAL.equals(literal)) {
            return DiagramUIMessages.ConnectionAppearancePropertySection_Router_Tree;
        }

        assert false : "No translated string available."; //$NON-NLS-1$
        return ""; //$NON-NLS-1$

    }

    @Override
    public boolean performOk() {
        boolean result = super.performOk();
        if (coreFields != null) {
            Iterator<FieldEditor> e = coreFields.iterator();
            while (e.hasNext()) {
                FieldEditor pe = e.next();
                pe.store();
                if (pe instanceof SpecificFieldEditor) {
                    ((SpecificFieldEditor) pe).setPresentsDefaultValue(false);
                }
            }
        }
        return result;
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        if (coreFields != null) {
            Iterator<FieldEditor> e = coreFields.iterator();
            while (e.hasNext()) {
                FieldEditor pe = e.next();
                pe.loadDefault();
            }
        }
        // Force a recalculation of my error state.
        checkState();
        // Force a recalculation of UI state
        enableLineStyleField(enableLineStyleOverrideFieldEditor.getBooleanValue());
        enableJumpLinkField(enableJumpLinkOverrideFieldEditor.getBooleanValue());
    }

    /**
     * Sets the core preference store for this preference page (2 preference stores are used for this page).
     * <p>
     *
     * @param store
     *            the preference store, or <code>null</code>
     * @see #getCorePreferenceStore
     */
    public void setCorePreferenceStore(IPreferenceStore store) {
        diagramCorePreferenceStore = store;
    }

    /**
     * Returns the core preference store of this preference page.
     *
     * @return the core preference store , or <code>null</code> if none
     */
    public IPreferenceStore getCorePreferenceStore() {
        if (diagramCorePreferenceStore == null) {
            diagramCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID);
        }
        return diagramCorePreferenceStore;
    }

    @Override
    protected void checkState() {
        super.checkState();
        boolean valid = isValid();
        if (valid) {
            invalidCoreFieldEditor = null;
            // The state can only be set to true if all
            // field editors contain a valid value. So we must check them all
            if (coreFields != null) {
                int size = coreFields.size();
                for (int i = 0; i < size; i++) {
                    FieldEditor editor = coreFields.get(i);
                    valid = valid && editor.isValid();
                    if (!valid) {
                        invalidCoreFieldEditor = editor;
                        break;
                    }
                }
            }
            setValid(valid);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (coreFields != null) {
            Iterator<FieldEditor> e = coreFields.iterator();
            while (e.hasNext()) {
                FieldEditor pe = e.next();
                pe.setPage(null);
                pe.setPropertyChangeListener(null);
                pe.setPreferenceStore(null);
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && invalidCoreFieldEditor != null) {
            invalidCoreFieldEditor.setFocus();
        }
    }
}
