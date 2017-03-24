/*******************************************************************************
 * Copyright (c) 2009, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.assist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;
import org.eclipse.sirius.ui.tools.api.assist.IAssistContentProvider;
import org.eclipse.sirius.ui.tools.api.assist.TextContentProposalProvider;

/**
 * Provides proposal from {@link FeatureProposalProvider} only.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SiriusFeatureContentProposalProvider extends TextContentProposalProvider implements IAssistContentProvider {

    @Override
    public IContentProposal[] getProposals(String contents, int position) {
        final String prefix = "";

        final ContentContext context = getContentContext(contents, position);
        String proposalStart = new ContentContextHelper(contents, position, prefix).getProposalStart();
        List<ContentProposal> proposals = new ArrayList<ContentProposal>();
        proposals.addAll(new FeatureProposalProvider().getProposals(null, context));

        ContentProposalConverter contentProposalConverter = new ContentProposalConverter(proposalStart);
        return contentProposalConverter.convertToJFaceContentProposals(proposals);
    }

}
