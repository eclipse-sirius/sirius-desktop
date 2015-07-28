/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.assist;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.sections.common.ModelViewBinding;
import org.eclipse.sirius.editor.tools.internal.assist.TypeAssistant;
import org.eclipse.sirius.editor.tools.internal.assist.TypeContentProposal;
import org.eclipse.sirius.ui.tools.api.assist.IAssistContentProvider;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * Content Proposal Provider for Type names.
 * 
 * @author cbrun
 * 
 */
public class TypeContentProposalProvider implements IContentProposalProvider {

    private final TypeAssistant assistant;

    /**
     * Create a new TypeContentProposal provider from an assistant.
     * 
     * @param assistant
     *            assistant to use to provide proposals.
     */
    public TypeContentProposalProvider(final TypeAssistant assistant) {
        this.assistant = assistant;
    }

    /**
     * {@inheritDoc}
     */
    public IContentProposal[] getProposals(final String contents, final int position) {
        final String incompleteText = contents.substring(0, position);
        final List<EClassifier> proposals = assistant.proposal(incompleteText);
        final IContentProposal[] result = new IContentProposal[proposals.size()];
        for (int i = 0; i < proposals.size(); i++) {
            final IContentProposal newProposal = new TypeContentProposal(proposals.get(i), incompleteText);
            result[i] = newProposal;
        }
        return result;
    }

    /**
     * Bind a completion processor to a given text element.
     * 
     * @param section
     *            section to give, if it implements {@link ModelViewBinding}
     *            then the section update will be disabled during proposal
     *            settings.
     * @param text
     *            text to bind a completion processor to.
     */
    public static void bindCompletionProcessor(final AbstractPropertySection section, final Text text) {

        final IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);

        TriggerSequence[] contentAssistBindings = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        if (contentAssistBindings != null && contentAssistBindings.length > 0) {

            KeyStroke keyStroke = TypeContentProposalProvider.getKeyStroke(contentAssistBindings[0]);
            TypeAssistant typeAssistant = new TypeAssistant(SiriusEditorPlugin.getPlugin().getWorkspaceEPackageRegistry(), section);
            final ContentProposalAdapter adapter = new ContentProposalAdapter(text, new TextContentAdapter(), new TypeContentProposalProvider(typeAssistant), keyStroke, null);

            adapter.addContentProposalListener(new IContentProposalListener2() {

                public void proposalPopupClosed(final ContentProposalAdapter arg0) {
                    if (section instanceof ModelViewBinding) {
                        ((ModelViewBinding) section).enableModelUpdating();
                    }
                }

                public void proposalPopupOpened(final ContentProposalAdapter arg0) {
                    if (section instanceof ModelViewBinding) {
                        ((ModelViewBinding) section).disableModelUpdating();
                    }
                }

            }); // close popup
        }
    }

    /**
     * Bind the completion processors available in plugins to a given text
     * element.
     * 
     * @param section
     *            the property section where the text element come from.
     * @param text
     *            text to bind a completion processors to.
     */
    public static void bindPluginsCompletionProcessors(final AbstractPropertySection section, final Text text) {
        List<IAssistContentProvider> extension = EclipseUtil.getExtensionPlugins(IAssistContentProvider.class, IAssistContentProvider.ID, IAssistContentProvider.CLASS_ATTRIBUTE);
        if (!(extension.size() == 0)) {
            IAssistContentProvider contentProposalAdapter = extension.get(0);
            contentProposalAdapter.setView(section);
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
                adapter.setPopupSize(new Point(500, 300)); // set content
                                                           // proposal popup
                                                           // size
                adapter.addContentProposalListener(contentProposalAdapter); // close
                                                                            // popup
            }
        }
    }

    private static KeyStroke getKeyStroke(TriggerSequence sequence) {
        for (Trigger trigger : sequence.getTriggers()) {
            if (trigger instanceof KeyStroke) {
                return (KeyStroke) trigger;
            }
        }
        return null;
    }
}
