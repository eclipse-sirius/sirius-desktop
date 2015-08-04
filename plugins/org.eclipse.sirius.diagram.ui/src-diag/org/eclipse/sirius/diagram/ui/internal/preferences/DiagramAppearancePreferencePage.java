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
import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @was-generated
 */
public class DiagramAppearancePreferencePage extends AppearancePreferencePage {

    /**
     * Title of the popup opened when toggling ui style.
     */
    public static final String USER_INTERFACE = "User interface";

    /**
     * Message explaining user the action of toggling ui style.
     */
    public static final String OLD_STYLE_NO_TAB_BAR_LAYERS_AND_FILTERS_MANAGEMENT_IN_OUTLINE = "old style : no tab bar, layers and filters management in outline";

    /**
     * Message explaining user the action of hiding label icons for all
     * connectors.
     */
    public static final String HIDE_LABEL_ICONS_ON_CONNECTORS_MESSAGE = " Hide label icons on connectors"; //$NON-NLS-1$

    /**
     * Message explaining user the action of hiding label icons for all shapes.
     */
    public static final String HIDE_LABEL_ICONS_ON_SHAPES_MESSAGE = " Hide label icons on shapes"; //$NON-NLS-1$

    /**
     * Title of the group containing all options related to label icons hiding.
     */
    public static final String LABEL_ICONS_GROUP_TITLE = "Label icons (does not affect existing elements of opened diagrams)"; //$NON-NLS-1$

    /**
     * Title of the group containing display header option.
     */
    public static final String LABEL_DISPLAY_HEADER_TITLE = "Display header"; //$NON-NLS-1$ /**

    /**
     * Message explaining user the action of display header.
     */
    public static final String DISPLAY_HEADER_MESSAGE = "Display header"; //$NON-NLS-1$

    private BooleanFieldEditor hideLabelIconsOfShapes;

    private BooleanFieldEditor hideLabelIconsOfConnectors;

    private BooleanFieldEditor displayHeader;

    /**
     * @was-generated
     */
    public DiagramAppearancePreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

    @Override
    protected void addFields(Composite parent) {
        Composite main = createPageLayout(parent);
        createLabelIconsGroup(main);
        createFontAndColorGroup(main);
        createDisplayHeadergroup(main);
    }

    /**
     * Create the font and color group for the "Label Icons" group of this
     * preference page.
     * 
     * @param parent
     *            the composite on which create this group.
     * 
     */
    protected void createLabelIconsGroup(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setLayout(new GridLayout(3, false));
        Composite composite = new Composite(group, SWT.NONE);
        GridLayout gridLayout = new GridLayout(3, false);
        composite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 3;
        composite.setLayoutData(gridData);
        group.setText(LABEL_ICONS_GROUP_TITLE);

        addLabelIconsFields(composite);

        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 8;
        composite.setLayout(layout);
    }

    /**
     * @param parent
     *            the composite on which create this group.
     */
    protected void createDisplayHeadergroup(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setLayout(new GridLayout(3, false));
        Composite composite = new Composite(group, SWT.NONE);
        GridLayout gridLayout = new GridLayout(3, false);
        composite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 3;
        composite.setLayoutData(gridData);
        group.setText(LABEL_DISPLAY_HEADER_TITLE);

        addDisplayHeaderField(composite);

        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 8;
        composite.setLayout(layout);

    }

    /**
     * Adds field of the "Display header" group.
     * 
     * @param composite
     *            the group on which create the fields
     */
    protected void addDisplayHeaderField(Composite composite) {
        displayHeader = new BooleanFieldEditor(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), DISPLAY_HEADER_MESSAGE, composite);
        addField(displayHeader);

    }

    /**
     * Adds all fields of the "Label Icons" group.
     * 
     * @param composite
     *            the group on which create the fields
     */
    protected void addLabelIconsFields(Composite composite) {
        hideLabelIconsOfShapes = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name(), HIDE_LABEL_ICONS_ON_SHAPES_MESSAGE, composite);
        addField(hideLabelIconsOfShapes);

        hideLabelIconsOfConnectors = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name(), HIDE_LABEL_ICONS_ON_CONNECTORS_MESSAGE, composite);
        addField(hideLabelIconsOfConnectors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();

        // Set preference store to Diagram core plugin
        IPreferenceStore diagramCorePreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, DiagramPlugin.ID);
        displayHeader.setPreferenceStore(diagramCorePreferenceStore);
        displayHeader.load();
    }

    /**
     * Initializes the default preference values for this preference store.
     * 
     * @param store
     */
    public static void initDefaults(IPreferenceStore store) {
        AppearancePreferencePage.initDefaults(store);
        store.setDefault(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        store.setDefault(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name(), false);
        store.setDefault(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name(), false);
    }
}
