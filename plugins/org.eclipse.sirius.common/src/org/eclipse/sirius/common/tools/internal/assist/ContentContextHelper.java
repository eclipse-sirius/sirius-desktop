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
package org.eclipse.sirius.common.tools.internal.assist;

import java.util.List;

import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * This class contains some informations about the context.
 * 
 * @author mchauvin
 */

public class ContentContextHelper {

    private String contents;

    private int position;

    private final String prefix;

    /**
     * This class contains some informations about the context.
     * 
     * 
     * @param text
     *            the text content.
     * @param position
     *            the cursor position.
     * @param prefix
     *            the current variable prefix.
     */
    public ContentContextHelper(final String text, final int position, final String prefix) {
        this.contents = text;
        this.position = position;
        this.prefix = prefix;
    }

    /**
     * Return the entered proposal start.
     * 
     * @return the proposal start.
     */
    public String getProposalStart() {
        String proposalStart = ""; //$NON-NLS-1$

        // There are two cases considered here. In the first case there is no
        // selected interpreter, so the completion is done on interpreters
        // empty expressions, we return the entire contents for the proposal
        // start. In the second case the proposal start is based on some
        // special characters in the contents.
        if (!StringUtil.isEmpty(contents)) {

            if (matchEmptyExpressions(contents, CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions())) {
                // First case
                proposalStart = contents;
            } else if (position > 0 && position <= contents.length()) {
                // Second case
                int numberOfalreadyWrittenCharacters = 0;
                String substring = contents.substring(0, position);
                numberOfalreadyWrittenCharacters = findNumberOfAlreadyWrittenCharacters(substring);
                if (numberOfalreadyWrittenCharacters != 0 && numberOfalreadyWrittenCharacters <= substring.length()) {
                    int propStart = substring.length() - numberOfalreadyWrittenCharacters;
                    proposalStart = substring.substring(propStart);

                    // variable ?
                    String exprStart = substring.substring(0, propStart);
                    if (!StringUtil.isEmpty(prefix) && exprStart.endsWith(prefix)) {
                        proposalStart = prefix + proposalStart;
                    }
                }
            }
        }

        return proposalStart;
    }

    /**
     * This method find out how many characters, of the expression we want
     * proposals, are already written
     * 
     * @param evaluationString
     * @return the number of already written characters
     */
    private int findNumberOfAlreadyWrittenCharacters(final String evaluationString) {
        // Find out how many characters have been written for the expression
        // we want proposals

        int numberOfalreadyWrittenCharacters = evaluationString.length();
        if (evaluationString.trim().length() > 0) {
            char charac;
            numberOfalreadyWrittenCharacters = 0;
            boolean isLetter = true;
            // Inspect all characters from the end of the expression we want
            // to evaluate
            while (isLetter && (numberOfalreadyWrittenCharacters < evaluationString.length())) {
                charac = evaluationString.charAt(evaluationString.length() - numberOfalreadyWrittenCharacters - 1);
                if (Character.isJavaIdentifierPart(charac)) {
                    numberOfalreadyWrittenCharacters++;
                } else {
                    isLetter = false;
                }
            }
        }
        return numberOfalreadyWrittenCharacters;
    }

    /**
     * Return true if the contents matches any interpreters empty expressions.
     * 
     * @param contents
     *            contents to tests
     * @param emptyExpressions
     *            interpreters empty expressions to match
     * @return true if the content matches any interpreters empty expressions
     */
    public static boolean matchEmptyExpressions(String contents, List<ContentProposal> emptyExpressions) {
        for (ContentProposal emptyExpression : emptyExpressions) {
            if (matchEmptyExpression(contents, emptyExpression)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return true if the contents matches the interpreter empty expression.
     * 
     * @param contents
     *            contents to tests
     * @param emptyExpression
     *            one interpreter empty expression to match
     * @return true if the content matches the interpreter empty expression
     */
    public static boolean matchEmptyExpression(String contents, ContentProposal emptyExpression) {
        String proposal = emptyExpression.getProposal();
        return proposal.startsWith(contents) && proposal.length() != contents.length();
    }

}
