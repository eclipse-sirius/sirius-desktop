/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.sections.properties.textwidgetstyle;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the fontFormat property of a TextWidgetStyle object.
 */
public class TextWidgetStyleFontFormatPropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "FontFormat"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontFormat();
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    @Override
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                string += getAdapterFactoryLabelProvider().getText(iterator.next());
                if (iterator.hasNext()) {
                    string += ", ";
                }
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(List)
     */
    @Override
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        return FontFormat.VALUES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
    }
}
