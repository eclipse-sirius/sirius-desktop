/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.validation.validationrule;

// Start of user code imports

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the level property of a ValidationRule object.
 */
public class ValidationRuleLevelPropertySection extends AbstractComboPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Level"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeature()
     */
    protected EAttribute getFeature() {
        return ValidationPackage.eINSTANCE.getValidationRule_Level();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index) {
        return getChoiceOfValues().get(index);
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#isEqual(int)
     */
    protected boolean isEqual(int index) {
        return getChoiceOfValues().get(index).equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getEnumerationFeatureValues()
     */
    protected List<?> getChoiceOfValues() {
        return ERROR_LEVEL.VALUES;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));

    }
}
