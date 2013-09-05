/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.interpreter;

import org.eclipse.sirius.common.acceleo.mtl.ide.AcceleoContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import org.eclipse.acceleo.internal.ide.ui.editors.template.AcceleoCompletionProposal;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Point;

/**
 * Allows us to provide content assist for the viewpoint dialect.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SiriusContentAssistProcessor implements IContentAssistProcessor {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        if (viewer instanceof SiriusInterpreterSourceViewer) {
            final Iterable<ContentProposal> vpProposals = ((SiriusInterpreterSourceViewer) viewer).getComputedProposals();
            if (vpProposals != null) {
                final Iterable<ICompletionProposal> proposals = Iterables.transform(vpProposals, new CompletionProposalConverter(viewer.getSelectedRange()));
                return Iterables.toArray(proposals, ICompletionProposal.class);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        // No context information for VP
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    public char[] getCompletionProposalAutoActivationCharacters() {
        /*
         * We do not trigger auto activation : we need contextual information
         * from the interpreter which will only be sent to us through manual
         * activation.
         */
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    public char[] getContextInformationAutoActivationCharacters() {
        // No context information for VP
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    public String getErrorMessage() {
        // For now, assume no error messages from completion.
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    public IContextInformationValidator getContextInformationValidator() {
        // No context information for VP
        return null;
    }

    /**
     * Will be used to convert the Sirius-specific proposals to JFace ones.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class CompletionProposalConverter implements Function<ContentProposal, ICompletionProposal> {
        final Point currentRange;

        public CompletionProposalConverter(Point currentRange) {
            this.currentRange = currentRange;
        }

        public ICompletionProposal apply(ContentProposal input) {
            final ICompletionProposal proposal;
            if (input instanceof AcceleoContentProposal) {
                final ICompletionProposal original = ((AcceleoContentProposal) input).getOriginal();
                if (original instanceof AcceleoCompletionProposal) {
                    /*
                     * Acceleo proposals are made so that they can replace
                     * everything up till the last "dot" or "arrow" in order to
                     * avoid case issues.
                     */
                    final int start = currentRange.x - ((AcceleoCompletionProposal) original).getReplacementLength();
                    final int length = ((AcceleoCompletionProposal) original).getReplacementLength() + currentRange.y;
                    proposal = new SiriusCompletionProposal(((AcceleoCompletionProposal) original).getReplacementString(), start, length, input.getCursorPosition(), original.getImage(),
                            original.getDisplayString(), original.getContextInformation(), original.getAdditionalProposalInfo());
                } else {
                    proposal = new SiriusCompletionProposal(input.getProposal(), currentRange.x, currentRange.y, input.getCursorPosition(), original.getImage(), original.getDisplayString(),
                            original.getContextInformation(), original.getAdditionalProposalInfo());
                }
            } else {
                proposal = new SiriusCompletionProposal(input.getProposal(), currentRange.x, currentRange.y, input.getCursorPosition(), null, input.getDisplay(), null, input.getInformation());
            }
            return proposal;
        }
    }
}
