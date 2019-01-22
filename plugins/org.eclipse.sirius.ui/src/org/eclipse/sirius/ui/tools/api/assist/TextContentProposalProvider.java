/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.assist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;
import org.eclipse.sirius.ext.swt.TextChangeListener;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * This class is used to attach content proposal behavior to a control.
 * 
 * @author ggebhart
 * 
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

    /**
     * Returns content proposal.
     * 
     * @param contents
     *            The content of the label
     * @param position
     *            the position of the cursor
     * 
     * @return list of proposals
     */
    @Override
    public IContentProposal[] getProposals(final String contents, final int position) {
        if (StringUtil.isEmpty(contents)) {
            /*
             * Get interpreter prefixes
             */
            List<ContentProposal> prefixes = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
            return new ContentProposalConverter("").convertToJFaceContentProposals(prefixes); //$NON-NLS-1$
        } else {
            /*
             * get interpreter proposal and viewpoint variables
             */
            final String prefix = CompoundInterpreter.INSTANCE.getVariablePrefix(contents);

            final ContentContext context = getContentContext(contents, position);
            String proposalStart = new ContentContextHelper(contents, position, prefix).getProposalStart();
            List<ContentProposal> proposals = new ArrayList<ContentProposal>();

            proposals.addAll(CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, context));

            /* remove duplicated proposals */
            proposals = TextContentProposalProvider.removeDuplicatedProposals(proposals);

            ContentProposalConverter contentProposalConverter = new ContentProposalConverter(proposalStart);
            return contentProposalConverter.convertToJFaceContentProposals(proposals);
        }

    }

    /**
     * Returns the content context from which proposal providers will make their completion proposals.
     * 
     * @param text
     *            user input.
     * @param position
     *            cursor position.
     * @return the content context.
     */
    protected ContentContext getContentContext(final String text, final int position) {
        final Object selectedElement = getSelectedElement();
        final EStructuralFeature f = getEStructuralFeature();
        IInterpreterContext interContext = SiriusInterpreterContextFactory.createInterpreterContext((EObject) selectedElement, f);
        return new ContentContext(text, position, interContext);

    }

    /**
     * Provides the selectedElement.
     * 
     * @return the selected elements.
     */
    protected Object getSelectedElement() {
        if (element == null && view != null && view.getSelection() instanceof IStructuredSelection) {
            return ((IStructuredSelection) view.getSelection()).getFirstElement();
        } else {
            return element;
        }
    }

    /**
     * Removes the duplicated proposals.
     * 
     * @param contents
     *            are the initial proposals
     * @return the valid proposals
     */
    protected static List<ContentProposal> removeDuplicatedProposals(final List<ContentProposal> contents) {

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

    /**
     * Action when the popup is closing.
     * 
     * @param adapter
     *            the ContentProposalAdater
     */
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

}
