/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.editor.properties.sections.style.nodestyledescription;

// Start of user code imports

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.internal.navigation.NavigationByKeyListener;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTextWithButtonPropertySection;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.sirius.editor.tools.internal.presentation.TextWithContentProposalDialog;
import org.eclipse.sirius.ui.tools.api.assist.ContentProposalClient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the sizeComputationExpression property of a NodeStyleDescription object.
 */
public class NodeStyleDescriptionSizeComputationExpressionPropertySection extends AbstractTextWithButtonPropertySection implements ContentProposalClient {

    /** Help control of the section. */
    protected CLabel help;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    @Override
    public void refresh() {
        super.refresh();

        final String tooltip = getToolTipText();
        if (tooltip != null && help != null) {
            help.setToolTipText(getToolTipText());
        }
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextWithButtonPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "SizeComputationExpression"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextWithButtonPropertySection#getLabelText()
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
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeature()
     */
    @Override
    public EAttribute getFeature() {
        return StylePackage.eINSTANCE.getNodeStyleDescription_SizeComputationExpression();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextWithButtonPropertySection#getFeatureValue(String)
     */
    @Override
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextWithButtonPropertySection#isEqual(String)
     */
    @Override
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        /*
         * We set the color as it's a InterpretedExpression
         */
        text.setBackground(SiriusEditor.getColorRegistry().get("yellow"));
        text.setData("org.eclipse.e4.ui.css.CssClassName", "siriusVSMEditorInterpretedExpression");

        text.setToolTipText(getToolTipText());

        help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText(getToolTipText());

        TypeContentProposalProvider.bindPluginsCompletionProcessors(this, text);

        text.addKeyListener(new NavigationByKeyListener(this, text, eObject));

        // Start of user code create controls

        // End of user code create controls

    }

    @Override
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TextWithContentProposalDialog dialog = new TextWithContentProposalDialog(composite.getShell(), NodeStyleDescriptionSizeComputationExpressionPropertySection.this, text.getText());
                dialog.open();
                text.setText(dialog.getResult());
                handleTextModified();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyDescription() {
        return "";
    }

    // Start of user code user operations

    @Override
    protected String getToolTipText() {
        String superTooltip = super.getToolTipText();
        String tooltip;

        if (needsWorkspaceImageCustomTooltip()) {
            StringBuilder sb = new StringBuilder();
            sb.append("-1 will force the node to have the real image size.");

            sb.append("\n");
            sb.append(superTooltip);
            tooltip = sb.toString();
        } else if (needsSquareStyleCustomTooltip()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Special cases:\n");
            sb.append("  - size expression will not be computed if empty or if both width and height are greater than 0,\n");
            sb.append("  - result lower than 0 will be corrected to 0,\n");
            sb.append("  - if width and height are both set to -1, size expression result will be used as minimal/preferred size,"
                    + " but concrete size will be auto-sized to the label content unless manually resized by the user.\n");
            sb.append("\n");
            sb.append(superTooltip);
            tooltip = sb.toString();
        } else

        {
            tooltip = superTooltip;
        }

        return tooltip;
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        // update tooltip and help
        if (needsWorkspaceImageCustomTooltip() || needsSquareStyleCustomTooltip()) {
            String toolTipText = getToolTipText();

            text.setToolTipText(toolTipText);
            help.setToolTipText(toolTipText);
        }
    }

    private boolean needsWorkspaceImageCustomTooltip() {
        boolean customEObjectTooltip = eObject instanceof WorkspaceImageDescription;
        boolean customEObjectListTooltip = eObjectList != null && !eObjectList.isEmpty() && eObjectList.stream().anyMatch(WorkspaceImageDescription.class::isInstance);
        return customEObjectTooltip || customEObjectListTooltip;
    }

    private boolean needsSquareStyleCustomTooltip() {
        boolean customEObjectTooltip = eObject instanceof SquareDescription;
        boolean customEObjectListTooltip = eObjectList != null && !eObjectList.isEmpty() && eObjectList.stream().anyMatch(SquareDescription.class::isInstance);
        return customEObjectTooltip || customEObjectListTooltip;
    }

    // End of user code user operations
}
