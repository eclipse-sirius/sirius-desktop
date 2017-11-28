/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;

/**
 * Provider for the completion on Request Interpreter View.
 * 
 * @author smonnier
 * 
 */
public class ContentInstanceProposalProvider implements IContentProposalProvider {

    private IInterpreter interpreter;

    private EObject currentEObject;

    private EditingDomain editingDomain;

    /**
     * *Constructor.
     * 
     * @param interpreter
     *            : used interpreter
     */
    public ContentInstanceProposalProvider(final IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void setCurrentEObject(final EObject currentEObject) {
        this.currentEObject = currentEObject;
    }

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /**
     * return the proposal for the arg0 expression.
     * 
     * @param arg0
     *            the expression
     * @param arg1
     *            the position of the cursor on the expression
     * @return the proposal for the arg0 expression at arg1 position
     */
    public IContentProposal[] getProposals(final String arg0, final int arg1) {
        if (currentEObject != null) {
            final List<ContentProposal> contentProposalsList;
            if (StringUtil.isEmpty(arg0)) {
                contentProposalsList = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
            } else {
                contentProposalsList = new ArrayList<>();
                if (interpreter instanceof IProposalProvider) {
                    contentProposalsList.addAll(((IProposalProvider) interpreter).getProposals(interpreter, new ContentInstanceContext(currentEObject, arg0, arg1, editingDomain)));
                }
                final List<IProposalProvider> providers = ProposalProviderRegistry.getProvidersFor(interpreter);
                for (IProposalProvider provider : providers) {
                    contentProposalsList.addAll(provider.getProposals(interpreter, new ContentInstanceContext(currentEObject, arg0, arg1, editingDomain)));
                }
            }
            String prefix = CompoundInterpreter.INSTANCE.getVariablePrefix(arg0);
            ContentContextHelper helper = new ContentContextHelper(arg0, arg1, prefix);
            return new ContentProposalConverter(helper.getProposalStart()).convertToJFaceContentProposals(contentProposalsList);
        } else {
            return null;
        }
    }
}
