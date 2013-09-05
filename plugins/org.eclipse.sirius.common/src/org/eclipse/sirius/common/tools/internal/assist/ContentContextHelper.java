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
        String proposalStart = "";
        int numberOfalreadyWrittenCharacters = 0;
        if (!StringUtil.isEmpty(contents) && position > 0 && position <= contents.length()) {
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

}
