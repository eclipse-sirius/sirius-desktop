/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to open a wizard to add a layout option override.
 */
public class AddOptionOverridePropertySection extends AbstractViewpointPropertySection {
    /**
     * The {@link Button} widget allowing to launch a wizard to select an option to override.
     */
    protected Button addOverrideButton;

    /**
     * The high level composite containing the widgets.
     */
    private Composite composite;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        Image addImage = ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.getPlugin().getImage(LayoutOptionPropertiesUtils.ADD_OPTION_IMAGE_NAME));
        addOverrideButton = getWidgetFactory().createButton(composite, "", SWT.PUSH);
        addOverrideButton.setImage(addImage);
        addOverrideButton.setText(Messages.AddOptionOverridePropertySection_overrideButtonLabel);
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(9, 0);
        addOverrideButton.setLayoutData(data);
        addOverrideButton.addSelectionListener(createButtonListener());

    }

    private SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // TODO
            }
        };
    }

    @Override
    public void dispose() {
        addOverrideButton.dispose();
        super.dispose();
    }

    @Override
    public EAttribute getFeature() {
        return null;
    }

    @Override
    protected void makeReadonly() {
    }

    @Override
    protected void makeWrittable() {
    }

}
