/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
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

    private BooleanFieldEditor hideLabelIconsOfShapes;

    private BooleanFieldEditor hideLabelIconsOfConnectors;

    private BooleanFieldEditor displayHeader;

    private BooleanFieldEditor authorizeDecorationOverlap;

    private BooleanFieldEditor displayUserFixedColor;

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
        createDecorationGroup(main);
    }

    @Override
    protected void addFontAndColorFields(Composite composite) {
        super.addFontAndColorFields(composite);

        displayUserFixedColor = new BooleanFieldEditor(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name(), Messages.DiagramAppearancePreferencePage_displayUserFixedColorsInPaletteLabel,
                composite);

        addField(displayUserFixedColor);
    }

    /**
     * Create the font and color group for the "Label Icons" group of this preference page.
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
        group.setText(Messages.DiagramAppearancePreferencePage_labelIconsGroupText);

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
        group.setText(Messages.DiagramAppearancePreferencePage_displayHeaderGroupText);

        addDisplayHeaderField(composite);

        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 8;
        composite.setLayout(layout);

    }

    /**
     * Create the decoration group for the boolean property in this preference page.
     * 
     * @param parent
     *            the composite on which create this group.
     */
    protected void createDecorationGroup(Composite parent) {
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
        group.setText(Messages.DiagramAppearancePreferencePage_decorationGroupText);

        addDecorationField(composite);

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
        displayHeader = new BooleanFieldEditor(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), Messages.DiagramAppearancePreferencePage_displayHeaderLabel, composite);
        addField(displayHeader);

    }

    /**
     * Adds field of the "Decoration" group.
     * 
     * @param composite
     *            the group on which create the fields
     */
    protected void addDecorationField(Composite composite) {
        authorizeDecorationOverlap = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_AUTHORIZE_DECORATION_OVERLAP.name(), Messages.DiagramAppearancePreferencePage_authorizeOverlapGroupText,
                composite);
        addField(authorizeDecorationOverlap);

    }

    /**
     * Adds all fields of the "Label Icons" group.
     * 
     * @param composite
     *            the group on which create the fields
     */
    protected void addLabelIconsFields(Composite composite) {
        hideLabelIconsOfShapes = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name(), Messages.DiagramAppearancePreferencePage_hideShapeLabelIconsLabel,
                composite);
        addField(hideLabelIconsOfShapes);

        hideLabelIconsOfConnectors = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name(),
                Messages.DiagramAppearancePreferencePage_hideConnectorLabelIconsLabel, composite);
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

        displayUserFixedColor.setPreferenceStore(SiriusEditPlugin.getPlugin().getPreferenceStore());
        displayUserFixedColor.load();
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
        store.setDefault(SiriusDiagramUiPreferencesKeys.PREF_AUTHORIZE_DECORATION_OVERLAP.name(), false);
    }
}
