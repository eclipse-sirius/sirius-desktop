/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.editor.properties.sections.template.tmessagestyle;

// Start of user code imports

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the lineStyle property of a TMessageStyle object.
 */
public class TMessageStyleLineStylePropertySection extends AbstractComboPropertySection {
    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "LineStyle"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#getFeature()
     */
    protected EAttribute getFeature() {
        return TemplatePackage.eINSTANCE.getTMessageStyle_LineStyle();
    }

    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index) {
        return getChoiceOfValues().get(index);
    }

    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#isEqual(int)
     */
    protected boolean isEqual(int index) {
        return getChoiceOfValues().get(index).equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.diagram.sequence.editor.properties.sections.AbstractComboPropertySection#getEnumerationFeatureValues()
     */
    protected List<?> getChoiceOfValues() {
        return LineStyle.VALUES;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        nameLabel.setToolTipText("Style of the message line.");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(nameLabel, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("Style of the message line.");

    }
}
