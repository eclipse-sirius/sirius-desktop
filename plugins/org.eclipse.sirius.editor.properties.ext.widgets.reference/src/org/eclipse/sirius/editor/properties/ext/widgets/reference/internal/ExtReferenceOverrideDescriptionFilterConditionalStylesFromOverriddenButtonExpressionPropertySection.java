/*******************************************************************************
 * Copyright (c) 2017, 2025 Obeo.
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
package org.eclipse.sirius.editor.properties.ext.widgets.reference.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.sirius.editor.tools.internal.presentation.TextWithContentProposalDialog;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.ui.tools.api.assist.ContentProposalClient;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

@SuppressWarnings("restriction")
public class ExtReferenceOverrideDescriptionFilterConditionalStylesFromOverriddenButtonExpressionPropertySection
        extends org.eclipse.sirius.editor.properties.sections.common.AbstractTextWithButtonPropertySection implements ContentProposalClient {

    @Override
    protected String getDefaultLabelText() {
        return "FilterConditionalStylesFromOverriddenExtReferenceExpression"; //$NON-NLS-1$
    }

    @Override
    protected String getLabelText() {
        return super.getLabelText() + ":"; //$NON-NLS-1$
    }

    @Override
    public EAttribute getFeature() {
        return PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceOverrideDescription_FilterConditionalStylesFromOverriddenExtReferenceExpression();
    }

    @Override
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    @Override
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setBackground(SiriusEditor.getColorRegistry().get("yellow")); //$NON-NLS-1$
        text.setData("org.eclipse.e4.ui.css.CssClassName", "siriusVSMEditorInterpretedExpression"); //$NON-NLS-1$ //$NON-NLS-2$
        TypeContentProposalProvider.bindPluginsCompletionProcessors(this, text);
    }

    @Override
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TextWithContentProposalDialog dialog = new TextWithContentProposalDialog(composite.getShell(),
                        ExtReferenceOverrideDescriptionFilterConditionalStylesFromOverriddenButtonExpressionPropertySection.this, text.getText());
                dialog.open();
                text.setText(dialog.getResult());
                handleTextModified();
            }
        };
    }

    @Override
    protected String getPropertyDescription() {
        return ""; //$NON-NLS-1$
    }
}
