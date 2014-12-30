/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.editor.properties.sections.description.treeitemdragtool;

// Start of user code imports

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the containers property of a TreeItemDragTool object.
 */
public class TreeItemDragToolContainersPropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.tree.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Containers"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.tree.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.tree.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getTreeItemDragTool_Containers();
    }

    /**
     * @see org.eclipse.sirius.tree.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext())
                    string += ", ";
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.tree.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setToolTipText("The TreeItemMapping in which this TreeItemMapping can be dropped");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("The TreeItemMapping in which this TreeItemMapping can be dropped");

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls

        // End of user code create controls
    }

    // Start of user code user operations

    // End of user code user operations
}
