/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.editor.properties.sections.description.intersectionmapping;

// Start of user code imports

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTextPropertySection;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.ui.tools.api.assist.ContentProposalClient;
import org.eclipse.sirius.ui.tools.api.assist.IAssistContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the semanticCandidatesExpression property of a
 * IntersectionMapping object.
 */
public class IntersectionMappingSemanticCandidatesExpressionPropertySection extends AbstractTextPropertySection implements ContentProposalClient {

    /** Help control of the section. */
    protected CLabel help;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    public void refresh() {
        super.refresh();

        final String tooltip = getToolTipText();
        if (tooltip != null && help != null) {
            help.setToolTipText(getToolTipText());
        }
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractTextPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "SemanticCandidatesExpression"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractTextPropertySection#getFeature()
     */
    public EAttribute getFeature() {
        return DescriptionPackage.eINSTANCE.getIntersectionMapping_SemanticCandidatesExpression();
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractTextPropertySection#getFeatureValue(String)
     */
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractTextPropertySection#isEqual(String)
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

        text.setToolTipText(getToolTipText());

        help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText(getToolTipText());

        List<IAssistContentProvider> extension = EclipseUtil.getExtensionPlugins(IAssistContentProvider.class, IAssistContentProvider.ID, IAssistContentProvider.CLASS_ATTRIBUTE);
        if (!(extension.size() == 0)) {
            IAssistContentProvider contentProposalAdapter = extension.get(0);
            contentProposalAdapter.setView(this);
            IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class); // gives
                                                                                                                            // the
                                                                                                                            // user
                                                                                                                            // content
                                                                                                                            // assist
                                                                                                                            // binding
            TriggerSequence[] activeBindinds = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
            if (activeBindinds != null && activeBindinds.length > 0) {
                TriggerSequence sequence = activeBindinds[0];
                KeyStroke keyStroke = getKeyStroke(sequence);

                TextContentAdapter textContentAdapter = new TextContentAdapter();
                ContentProposalAdapter adapter = new ContentProposalAdapter(text, textContentAdapter, contentProposalAdapter, keyStroke, IAssistContentProvider.AUTO_ACTIVATION_CHARACTERS);
                adapter.setPopupSize(new Point(300, 100)); // set content
                                                           // proposal popup
                                                           // size
                adapter.addContentProposalListener(contentProposalAdapter); // close
                                                                            // popup
            }
        }

        // Start of user code create controls

        // End of user code create controls

    }

    private KeyStroke getKeyStroke(TriggerSequence sequence) {
        for (Trigger trigger : sequence.getTriggers()) {
            if (trigger instanceof KeyStroke) {
                return (KeyStroke) trigger;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    protected String getPropertyDescription() {
        return "Restrict the list of elements to consider before creating the graphical elements. If it is not set,\nthen the whole model hiearchy will be browsed and any element of the given type validating\nthe precondition expression will cause the creation of a graphical element. If you set this\nattribute then only the elements returned by the expression evaluation will be considered.";
    }

    // Start of user code user operations

    // End of user code user operations
}
