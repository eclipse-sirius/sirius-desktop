/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.interpreter.internal.interpreter;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalWithReplacement;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalWithReplacement.ImageKind;
import org.eclipse.swt.graphics.Image;
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
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        if (viewer instanceof SiriusInterpreterSourceViewer) {
            final Iterable<ContentProposal> vpProposals = ((SiriusInterpreterSourceViewer) viewer).getComputedProposals();
            if (vpProposals != null) {
                // @formatter:off
                return StreamSupport.stream(vpProposals.spliterator(), false)
                        .map(new CompletionProposalConverter(viewer.getSelectedRange()))
                        .collect(Collectors.toList())
                        .toArray(new ICompletionProposal[0]);
                // @formatter:off
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
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        // No context information for VP
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        /*
         * We do not trigger auto activation : we need contextual information from the interpreter which will only be
         * sent to us through manual activation.
         */
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        // No context information for VP
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    @Override
    public String getErrorMessage() {
        // For now, assume no error messages from completion.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    @Override
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

        CompletionProposalConverter(Point currentRange) {
            this.currentRange = currentRange;
        }

        @Override
        public ICompletionProposal apply(ContentProposal input) {
            final ICompletionProposal proposal;
            if (input instanceof ContentProposalWithReplacement) {
                ContentProposalWithReplacement proposalWithReplacement = (ContentProposalWithReplacement) input;

                Image image = null;

                if (ImageKind.SWT_IMAGE.equals(proposalWithReplacement.getImageKind()) && proposalWithReplacement.getImage() instanceof Image) {
                    // We have a SWT image already loaded, we can reuse it directly
                    image = (Image) proposalWithReplacement.getImage();
                }

                proposal = new SiriusCompletionProposal(proposalWithReplacement.getProposal(), proposalWithReplacement.getReplacementOffset(), proposalWithReplacement.getReplacementLength(),
                        proposalWithReplacement.getCursorPosition(), image, proposalWithReplacement.getDisplay(), null, proposalWithReplacement.getInformation());
            } else {
                proposal = new SiriusCompletionProposal(input.getProposal(), currentRange.x, currentRange.y, input.getCursorPosition(), null, input.getDisplay(), null, input.getInformation());
            }
            return proposal;
        }
    }
}
