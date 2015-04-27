/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.tool.menuitemdescription;

// Start of user code imports

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTextWithButtonPropertySection;
import org.eclipse.sirius.editor.tools.internal.presentation.WorkspaceAndPluginsResourceDialog;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the icon property of a MenuItemDescription object.
 */
public class MenuItemDescriptionIconPropertySection extends AbstractTextWithButtonPropertySection {

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractTextWithButtonPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Icon"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractTextWithButtonPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeature()
     */
    public EAttribute getFeature() {
        return ToolPackage.eINSTANCE.getMenuItemDescription_Icon();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeatureValue(String)
     */
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractTextWithButtonPropertySection#isEqual(String)
     */
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        // Start of user code create controls

        // End of user code create controls

    }

    @Override
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String imagePath = WorkspaceAndPluginsResourceDialog.openDialogForImages(composite.getShell());
                if (imagePath != null) {
                    text.setText(imagePath);
                    handleTextModified();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    protected String getPropertyDescription() {
        return "";
    }

    // Start of user code user operations

    // End of user code user operations
}
