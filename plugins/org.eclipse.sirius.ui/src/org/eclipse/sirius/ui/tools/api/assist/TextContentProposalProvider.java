/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.assist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;
import org.eclipse.sirius.ext.swt.TextChangeListener;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * This class is used to attach content proposal behavior to a control.
 * 
 * @author ggebhart
 * @author lfasani impact of ContentProposalConverter signature change
 */
public class TextContentProposalProvider implements IAssistContentProvider {
    /**
     * The current view.
     */
    private AbstractPropertySection view;

    private EObject element;

    private EStructuralFeature feature;

    /**
     * Constructor.
     */
    public TextContentProposalProvider() {
        super();
    }

    @Override
    public IContentProposal[] getProposals(final String contents, final int position) {
        if (StringUtil.isEmpty(contents)) {
            /*
             * Get interpreter prefixes
             */
            List<ContentProposal> prefixes = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
            return new ContentProposalConverter(contents, position).convertToJFaceContentProposals(prefixes);
        } else {
            /*
             * get interpreter proposal and viewpoint variables
             */

            final ContentContext context = getContentContext(contents, position);
            List<ContentProposal> proposals = new ArrayList<ContentProposal>();
            proposals.addAll(CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, context));

            /* remove duplicated proposals */
            proposals = TextContentProposalProvider.removeDuplicatedProposals(proposals);

            ContentProposalConverter contentProposalConverter = new ContentProposalConverter(contents, position);

            return contentProposalConverter.convertToJFaceContentProposals(proposals);
        }

    }

    private ContentContext getContentContext(final String text, final int position) {
        final Object selectedElement = getSelectedElement();
        final EStructuralFeature f = getEStructuralFeature();
        IInterpreterContext interContext = SiriusInterpreterContextFactory.createInterpreterContext((EObject) selectedElement, f);
        return new ContentContext(text, position, interContext);

    }

    private Object getSelectedElement() {
        if (element == null && view != null) {
            return ((TreeSelection) view.getSelection()).getFirstElement();
        } else {
            return element;
        }
    }

    /**
     * Removes the duplicated proposals.
     * 
     * @param matches
     *            are the initial proposals
     * @return the valid proposals
     */
    private static List<ContentProposal> removeDuplicatedProposals(final List<ContentProposal> contents) {

        final List<ContentProposal> resultProposals = new ArrayList<ContentProposal>();
        final Iterator<ContentProposal> it = contents.iterator();

        while (it.hasNext()) {
            final ContentProposal entry = it.next();
            if (!resultProposals.contains(entry)) {
                resultProposals.add(entry);
            }
        }
        return resultProposals;
    }

    private EStructuralFeature getEStructuralFeature() {
        EStructuralFeature f = null;

        if (feature != null) {
            f = feature;
        } else if (view instanceof ContentProposalClient) {
            f = ((ContentProposalClient) view).getFeature();
        }

        return f;
    }

    @Override
    public void setView(final AbstractPropertySection view) {
        this.view = view;
    }


    @Override
    public void initContext(EObject selectedElement, EStructuralFeature selectedFeature) {
        this.element = selectedElement;
        this.feature = selectedFeature;
    }

    @Override
    public void proposalPopupClosed(final ContentProposalAdapter adapter) {

        final Text text = (Text) adapter.getControl();

        /* restart to listen to key events */
        if (view instanceof ContentProposalClient) {
            final TextChangeListener helper = ((ContentProposalClient) view).getListener();
            helper.startListeningTo(text);
            helper.startListeningForEnter(text);
        }
    }


    @Override
    public void proposalPopupOpened(final ContentProposalAdapter adapter) {

        final Text text = (Text) adapter.getControl();

        /* stop to listen key events */
        if (view instanceof ContentProposalClient) {
            final TextChangeListener helper = ((ContentProposalClient) view).getListener();
            helper.stopListeningTo(text);
        }

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

        List<IAssistContentProvider> extension = EclipseUtil.getExtensionPlugins(IAssistContentProvider.class, IAssistContentProvider.ID, IAssistContentProvider.CLASS_ATTRIBUTE);
        if (!(extension.size() == 0)) {
            IAssistContentProvider contentProposalAdapter = extension.get(0);
            contentProposalAdapter.setView(section);
            // gives the user content assist binding
            IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
            TriggerSequence[] activeBindinds = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
            if (activeBindinds != null && activeBindinds.length > 0) {
                TriggerSequence sequence = activeBindinds[0];
                KeyStroke keyStroke = null;
                for (Trigger trigger : sequence.getTriggers()) {
                    if (trigger instanceof KeyStroke) {
                        keyStroke = (KeyStroke) trigger;
                    }
                }

                TextContentAdapter textContentAdapter = new TextContentAdapter();
                ContentProposalAdapter adapter = new ContentProposalAdapter(text, textContentAdapter, contentProposalAdapter, keyStroke, IAssistContentProvider.AUTO_ACTIVATION_CHARACTERS);
                adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
                adapter.setPopupSize(new Point(300, 100)); // set content
                                                           // proposal popup
                                                           // size
                adapter.addContentProposalListener(contentProposalAdapter); // close
                                                                            // popup
            }
        }
    }
}
