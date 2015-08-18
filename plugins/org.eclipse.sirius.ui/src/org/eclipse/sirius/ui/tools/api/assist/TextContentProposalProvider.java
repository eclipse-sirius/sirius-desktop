/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;
import org.eclipse.sirius.ext.swt.TextChangeListener;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
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

            if (StringUtil.isEmpty(proposalStart) || !StringUtil.isEmpty(prefix) && proposalStart.contains(prefix)) {
                // proposals.addAll(getVariableProposals(context, prefix,
                // proposalStart));
            }
            proposals.addAll(CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, context));

            /* remove duplicated proposals */
            proposals = TextContentProposalProvider.removeDuplicatedProposals(proposals);

            ContentProposalConverter contentProposalConverter = new ContentProposalConverter(proposalStart);
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

    public void setView(final AbstractPropertySection view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
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
    public void proposalPopupClosed(final ContentProposalAdapter adapter) {

        final Text text = (Text) adapter.getControl();

        /* restart to listen to key events */
        if (view instanceof ContentProposalClient) {
            final TextChangeListener helper = ((ContentProposalClient) view).getListener();
            helper.startListeningTo(text);
            helper.startListeningForEnter(text);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalListener2#proposalPopupOpened(org.eclipse.jface.fieldassist.ContentProposalAdapter)
     */
    public void proposalPopupOpened(final ContentProposalAdapter adapter) {

        final Text text = (Text) adapter.getControl();

        /* stop to listen key events */
        if (view instanceof ContentProposalClient) {
            final TextChangeListener helper = ((ContentProposalClient) view).getListener();
            helper.stopListeningTo(text);
        }

    }

}
