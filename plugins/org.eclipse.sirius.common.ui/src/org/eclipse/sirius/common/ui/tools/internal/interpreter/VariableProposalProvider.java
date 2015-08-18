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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

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
        return new ContentProposal(VariableInterpreter.PREFIX, VariableInterpreter.PREFIX, "New variable access expression.", VariableInterpreter.PREFIX.length());
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof VariableInterpreter)) {
            proposals = Collections.emptyList();
        } else {
            // Transform Entry<String,VariableType> to Entry<String,String>
            Iterator<Entry<String, String>> variablesIterator = Iterators.transform(context.getInterpreterContext().getVariables().entrySet().iterator(),
                    new Function<Map.Entry<String, VariableType>, Map.Entry<String, String>>() {

                        @Override
                        public Entry<String, String> apply(Entry<String, VariableType> input) {
                            return Maps.immutableEntry(input.getKey(), input.getValue().toString());
                        }

                    });
            proposals = getProposals(context.getContents(), context.getPosition(), variablesIterator);
        }
        return proposals;
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof VariableInterpreter)) {
            proposals = Collections.emptyList();
        } else {
            if (context.getCurrentSelected() == null) {
                proposals = Collections.singletonList(getNewEmtpyExpression());
            } else {
                VariableInterpreter variableInterpreter = (VariableInterpreter) interpreter;
                // Transform Entry<String, Object> to Entry<String, String>
                Iterator<Entry<String, String>> variablesIterator = Iterators.transform(variableInterpreter.getVariables().entrySet().iterator(),
                        new Function<Entry<String, Object>, Entry<String, String>>() {
                            public Map.Entry<String, String> apply(Map.Entry<String, Object> input) {
                                return Maps.immutableEntry(input.getKey(), input.getValue().toString());
                            };
                        });
                proposals = getProposals(context.getTextSoFar(), context.getCursorPosition(), variablesIterator);
            }
        }
        return proposals;
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param writtenExpression
     *            The complete expression with feature: prefix.
     * @param cursorPosition
     *            The current cursor position to consider only characters before
     *            it.
     * @param variablesIterator
     *            Iterator on entries corresponding to the available variables.
     * @return content proposal list.
     */
    private List<ContentProposal> getProposals(String writtenExpression, int cursorPosition, Iterator<Entry<String, String>> variablesIterator) {
        if (StringUtil.isEmpty(writtenExpression)) {
            return Collections.singletonList(getNewEmtpyExpression());
        } else {

            final List<ContentProposal> proposals = new ArrayList<ContentProposal>();
            // Keep only characters before cursor
            String variableNamePrefix = writtenExpression.substring(0, cursorPosition);
            // Remove not needed space characters.
            variableNamePrefix = variableNamePrefix.trim();
            // Remove "var:" prefix if the cursor position is after the prefix
            // If the cursor position is before the prefix, there is no proposal
            // returned.
            if (variableNamePrefix.length() >= VariableInterpreter.PREFIX.length()) {
                variableNamePrefix = variableNamePrefix.substring(VariableInterpreter.PREFIX.length());

                if (VariableInterpreter.SELF_VARIABLE_NAME.startsWith(variableNamePrefix)) {
                    proposals.add(new ContentProposal(VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME, VariableInterpreter.SELF_VARIABLE_NAME));
                }
                while (variablesIterator.hasNext()) {
                    Map.Entry<String, String> entry = variablesIterator.next();
                    String variableName = entry.getKey();
                    String variableType = entry.getValue();
                    if (variableName.startsWith(variableNamePrefix)) {
                        proposals.add(new ContentProposal(variableName, variableName + ": " + variableType, variableType)); //$NON-NLS-1$
                    }
                }
            }
            return proposals;
        }
    }

}
