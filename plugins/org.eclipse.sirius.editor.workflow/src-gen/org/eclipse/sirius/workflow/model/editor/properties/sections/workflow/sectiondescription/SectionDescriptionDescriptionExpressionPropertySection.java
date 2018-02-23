/*******************************************************************************
 *  Copyright (c) 2018 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *  Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.model.editor.properties.sections.workflow.sectiondescription;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.internal.navigation.NavigationByKeyListener;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTextWithButtonPropertySection;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.sirius.editor.tools.internal.presentation.TextWithContentProposalDialog;
import org.eclipse.sirius.ui.tools.api.assist.ContentProposalClient;
import org.eclipse.sirius.workflow.WorkflowPackage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the descriptionExpression property of a SectionDescription object.
 */
public class SectionDescriptionDescriptionExpressionPropertySection extends AbstractTextWithButtonPropertySection implements ContentProposalClient {

    /**
     * @see org.eclipse.sirius.workflow.model.editor.properties.sections.AbstractTextWithButtonPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "DescriptionExpression"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.workflow.model.editor.properties.sections.AbstractTextWithButtonPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.workflow.model.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeature()
     */
    public EAttribute getFeature() {
        return WorkflowPackage.eINSTANCE.getSectionDescription_DescriptionExpression();
    }

    /**
     * @see org.eclipse.sirius.workflow.model.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeatureValue(String)
     */
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    /**
     * @see org.eclipse.sirius.workflow.model.editor.properties.sections.AbstractTextWithButtonPropertySection#isEqual(String)
     */
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        /*
         * We set the color as it's a InterpretedExpression
         */
        text.setBackground(SiriusEditor.getColorRegistry().get("yellow"));

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));

        TypeContentProposalProvider.bindPluginsCompletionProcessors(this, text);

        text.addKeyListener(new NavigationByKeyListener(this, text, eObject));

        // Start of user code create controls

        // End of user code create controls

    }

    @Override
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TextWithContentProposalDialog dialog = new TextWithContentProposalDialog(composite.getShell(), SectionDescriptionDescriptionExpressionPropertySection.this, text.getText());
                dialog.open();
                text.setText(dialog.getResult());
                handleTextModified();
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
