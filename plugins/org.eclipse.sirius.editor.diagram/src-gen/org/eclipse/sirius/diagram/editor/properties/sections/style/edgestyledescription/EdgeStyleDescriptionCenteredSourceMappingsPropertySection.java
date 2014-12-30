/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.style.edgestyledescription;

// Start of user code imports

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

// End of user code imports

/**
 * A section for the centeredSourceMappings property of a EdgeStyleDescription
 * object.
 */
public class EdgeStyleDescriptionCenteredSourceMappingsPropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "CenteredSourceMappings"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return StylePackage.eINSTANCE.getEdgeStyleDescription_CenteredSourceMappings();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
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
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setToolTipText("The mappings for which the edge source will be centered. Deactivated if ends Centering value is \"Both\" or \"Source\" (that means the source is always centered)");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("The mappings for which the edge source will be centered. Deactivated if ends Centering value is \"Both\" or \"Source\" (that means the source is always centered)");

        // Start of user code create controls

        // End of user code create controls
    }

    // Start of user code user operations

    @Override
    protected boolean shouldBeReadOnly() {
        boolean value = super.shouldBeReadOnly();
        if (!value) {
            value = !isActivated();
        }
        return value;
    }

    @Override
    public void refresh() {
        super.refresh();
        updateReadOnlyStatus();
    }

    private boolean isActivated() {
        if (eObject instanceof EdgeStyleDescription) {
            CenteringStyle current = ((EdgeStyleDescription) eObject).getEndsCentering();
            return current != CenteringStyle.BOTH && current != CenteringStyle.SOURCE;
        }
        return true;
    }

    @Override
    protected List<?> getChoiceOfValues(List<?> currentValues) {
        List<?> choiceOfValues = super.getChoiceOfValues(currentValues);
        removeUnrelatedMappings(choiceOfValues);
        Collection<?> collection = Collections2.filter(choiceOfValues, Predicates.instanceOf(AbstractNodeMapping.class));
        return new ArrayList<Object>(collection);
    }

    private void removeUnrelatedMappings(List<?> choiceOfValues) {
        EObject container = eObject.eContainer();
        if (container instanceof EdgeMapping) {
            EList<DiagramElementMapping> sourceMappingsList = ((EdgeMapping) container).getSourceMapping();
            choiceOfValues.retainAll(sourceMappingsList);
        }
    }

    // End of user code user operations
}
