/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

/**
 * @was-generated
 */
public class DiagramPrintingPreferencePage extends PrintingPreferencePage {

    private BooleanFieldEditor printDecorationsField;

    /**
     * @was-generated
     */
    public DiagramPrintingPreferencePage() {
        setPreferenceStore(DiagramUIPlugin.getPlugin().getPreferenceStore());
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        createDisplayHeadergroup(parent);
        initialize();

        return control;
    }

    /**
     * Create the composite containing other preferences in addition to super
     * 
     * @param parent
     *            the composite on which create this group.
     */
    protected void createDisplayHeadergroup(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setLayoutData(new GridData(GridData.FILL, 0, true, false));
        group.setLayout(new GridLayout(3, false));
        Composite composite = new Composite(group, SWT.NONE);
        GridLayout gridLayout = new GridLayout(3, false);
        composite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL, 0, true, false);
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalSpan = 3;
        composite.setLayoutData(gridData);
        group.setText(Messages.DiagramPrintingPreferencePage_optionsGroupText);

        addPrintDecorationsField(composite);

        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 8;
        composite.setLayout(layout);

    }

    /**
     * Adds field of the "Options" group.
     * 
     * @param composite
     *            the group on which create the fields
     */
    protected void addPrintDecorationsField(Composite composite) {
        printDecorationsField = new BooleanFieldEditor(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), Messages.DiagramPrintingPreferencePage_printDecorations, composite);
        addField(printDecorationsField);
    }

    /**
     * Initializes the default preference values for this preference store.
     * 
     * @param store
     *            preference store
     */
    public static void initDefaults(IPreferenceStore store) {
        PrintingPreferencePage.initDefaults(store);
        store.setDefault(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), false);
    }

}
