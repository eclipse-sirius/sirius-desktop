/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.sirius.ui.properties.api.preferences.SiriusPropertiesViewPreferencesKeys;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The properties view preference page.
 * 
 * @author mbats
 */
public class SiriusPropertiesViewGeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private BooleanFieldEditor filterSemanticTab;

    private BooleanFieldEditor filterDefaultTab;

    private IntegerFieldEditor maxTabNameLength;

    @Override
    protected void createFieldEditors() {
        setPreferenceStore(SiriusUIPropertiesPlugin.getPlugin().getPreferenceStore());

        Composite parent = getFieldEditorParent();
        if (parent.getLayout() == null) {
            GridLayout gridLayout = new GridLayout(1, false);
            parent.setLayout(gridLayout);
        }

        addFilterFields(parent);

        addTabFields(parent);

    }

    private void addFilterFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, Messages.SiriusPropertiesPreferencePage_filterGroup);

        filterSemanticTab = new BooleanFieldEditor(SiriusPropertiesViewPreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_SEMANTIC_TAB.name(), Messages.SiriusPropertiesPreferencePage_semanticTab,
                new Composite(refreshComposite, SWT.NONE));
        addField(filterSemanticTab);

        filterDefaultTab = new BooleanFieldEditor(SiriusPropertiesViewPreferencesKeys.PREF_FILTER_PROPERTIES_VIEW_DEFAULT_TAB.name(), Messages.SiriusPropertiesPreferencePage_defaultTab,
                new Composite(refreshComposite, SWT.NONE));
        addField(filterDefaultTab);
    }

    private void addTabFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, Messages.SiriusPropertiesPreferencePage_tabGroup);

        maxTabNameLength = new IntegerFieldEditor(SiriusPropertiesViewPreferencesKeys.PREF_MAX_LENGTH_TAB_NAME.name(), Messages.SiriusPropertiesPreferencePage_maxLengthTabName,
                new Composite(refreshComposite, SWT.NONE));
        addField(maxTabNameLength);
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
}
