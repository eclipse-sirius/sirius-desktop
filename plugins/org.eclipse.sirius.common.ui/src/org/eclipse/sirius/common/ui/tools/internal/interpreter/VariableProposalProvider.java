/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;

/**
 * A {@link IProposalProvider} to provide completion for the variable
 * interpreter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VariableProposalProvider implements IProposalProvider {

    /**
     * {@inheritDoc}
     */
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(VariableInterpreter.PREFIX, VariableInterpreter.PREFIX, "New variable expression.", 1);
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof VariableInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getContents() == null || context.getContents().length() == 0) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            proposals = new ArrayList<ContentProposal>();
            proposals.add(new ContentProposal(VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME));
            for (Map.Entry<String, String> variableEntry : context.getInterpreterContext().getVariables().entrySet()) {
                String variableName = variableEntry.getKey();
                String variableType = variableEntry.getValue();
                proposals.add(new ContentProposal(variableName, variableName + ":" + variableType, variableType));
            }
        }
        return proposals;
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof VariableInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getCurrentSelected() == null) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            VariableInterpreter variableInterpreter = (VariableInterpreter) interpreter;
            proposals = new ArrayList<ContentProposal>();
            proposals.add(new ContentProposal(VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME));
            for (Map.Entry<String, ?> variableEntry : variableInterpreter.getVariables().entrySet()) {
                String variableName = variableEntry.getKey();
                Object variableType = variableEntry.getValue();
                proposals.add(new ContentProposal(variableName, variableName + ":" + variableType.toString(), variableType.toString()));
            }
        }
        return proposals;
    }

}
