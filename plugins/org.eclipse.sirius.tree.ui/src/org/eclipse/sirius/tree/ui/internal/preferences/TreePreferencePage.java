/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.internal.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.api.preferences.SiriusTreeUiPreferencesKeys;
import org.eclipse.sirius.ui.tools.internal.preference.BooleanFieldEditorWithHelp;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Contains all preferences related to Sirius tree.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class TreePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private BooleanFieldEditor alwaysUseStandardFontSize;

    @Override
    protected void createFieldEditors() {
        setPreferenceStore(TreeUIPlugin.getPlugin().getPreferenceStore());

        Composite parent = getFieldEditorParent();
        if (parent.getLayout() == null) {
            GridLayout gridLayout = new GridLayout(1, false);
            parent.setLayout(gridLayout);
        }

        addFields(parent);

    }

    private void addFields(Composite parent) {
        Composite refreshComposite = createGroup(parent, Messages.SiriusTreePreferencePage_globalGroupName);

        alwaysUseStandardFontSize = new BooleanFieldEditorWithHelp(SiriusTreeUiPreferencesKeys.PREF_ALWAYS_USE_STANDARD_FONT_SIZE.name(), Messages.SiriusTreePreferencePage_alwaysUseStandardFont,
                Messages.SiriusTreePreferencePage_alwaysUseStandardFont_help, new Composite(refreshComposite, SWT.NONE));
        addField(alwaysUseStandardFontSize);
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
