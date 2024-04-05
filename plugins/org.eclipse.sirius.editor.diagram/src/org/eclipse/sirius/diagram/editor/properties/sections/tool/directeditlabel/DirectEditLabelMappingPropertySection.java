/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.properties.sections.tool.directeditlabel;

import java.util.ArrayList;

// Start of user code imports

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogWithListPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the mapping property of a DirectEditLabel object.
 */
public class DirectEditLabelMappingPropertySection extends AbstractEditorDialogWithListPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "Mapping"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    @Override
    protected String getFeatureAsText() {
        String string = new String();

        if (((DirectEditLabel) eObject).getMapping() != null) {
            List values = getCurrentValue();
            for (Iterator iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext()) {
                    string += ", ";
                }
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    @Override
    protected boolean isEqual(List newList) {
        return newList.equals(((DirectEditLabel) eObject).getMapping());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setToolTipText("Mappings on which this edit label tool is applicable.");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("Mappings on which this edit label tool is applicable.");

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls

        // End of user code create controls
    }

    // Start of user code user operations
    @Override
    protected List getCurrentValue() {
        return ((DirectEditLabel) eObject).getMapping();
    }

    @Override
    protected boolean getSortChoice() {
        return true;
    }

    /**
     * Fetches the list of available values for the feature.
     * 
     * @return The list of available values for the feature.
     */
    @Override
    protected List getChoiceOfValues() {
        List<Object> result = new ArrayList<>();
        eObject.eResource().getResourceSet().getAllContents().forEachRemaining(notifier -> {
            if (notifier instanceof DiagramElementMapping mapping) {
                result.add(mapping);
            }
        });
        return result;
    }

    /**
     * Handle the modification event given the result of the section's
     * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog
     * FeatureEditorDialog}.
     */
    @Override
    protected void handleFeatureModified(List result) {
        boolean equals = isEqual(result);

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            Object value = result;

            if (eObjectList.size() == 1) {
                // if (getFeature().isTransient()) {
                CompoundCommand compoundCommand = new CompoundCommand();
                Object eGet = getCurrentValue();
                if (eGet instanceof EList) {
                    for (Object object : (EList) eGet) {
                        compoundCommand.append(SetCommand.create(editingDomain, object, DescriptionPackage.eINSTANCE.getDiagramElementMapping_LabelDirectEdit(), null));
                    }
                }
                if (value instanceof EList) {
                    for (Iterator iterator = ((EList) value).iterator(); iterator.hasNext();) {
                        Object object = iterator.next();
                        compoundCommand.append(SetCommand.create(editingDomain, object, DescriptionPackage.eINSTANCE.getDiagramElementMapping_LabelDirectEdit(), eObject));
                    }
                }
                editingDomain.getCommandStack().execute(compoundCommand);
                // }
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : eObjectList) {
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }
    // End of user code user operations
}
