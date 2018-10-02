/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Utility class used to match the path of the services and the path of the
 * requests.
 *
 * @author sbegaudeau
 */
public class SiriusServerPathMatcher {
    /**
     * The separator of the segments of the path.
     */
    private static final char SLASH = '/';

    /**
     * The start of a variable.
     */
    private static final char VARIABLE_START = '{';

    /**
     * The end of a variable.
     */
    private static final char VARIABLE_END = '}';

    /**
     * The regular expression used to capture the variables of the path.
     */
    private static final String VARIABLE_REGEXP = "([^/]+?)"; //$NON-NLS-1$

    /**
     * The regular expression used to capture the remaining content of the path.
     */
    private static final String REMAINING_PATH_REGEXP = "(.*)"; //$NON-NLS-1$

    /**
     * The list of the variables expected in the path.
     */
    private List<String> variableNames = new ArrayList<>();

    /**
     * The pattern used to match the path.
     */
    private Pattern pathPattern;

    /**
     * The constructor.
     *
     * @param path
     *            The path
     */
    public SiriusServerPathMatcher(String path) {
        this.initialize(path);
    }

    /**
     * Initializes the {@link SiriusServerPathMatcher}.
     *
     * @param path
     *            The path to match
     */
    private void initialize(String path) {
        StringBuilder stringBuilder = new StringBuilder();

        int startIndex = this.computeStartIndex(path);
        int index = startIndex;
        while (index < path.length()) {
            char character = path.charAt(index);
            if (VARIABLE_START == character) {
                int variableEndIndex = path.indexOf(VARIABLE_END, index);
                String variable = path.substring(index + 1, variableEndIndex);

                this.variableNames.add(variable);

                stringBuilder.append(VARIABLE_REGEXP);
                index = variableEndIndex + 1;
            } else {
                stringBuilder.append(character);
                index = index + 1;
            }
        }

        if (!path.endsWith(String.valueOf(SLASH))) {
            stringBuilder.append(SLASH);
        }
        stringBuilder.append(REMAINING_PATH_REGEXP);

        this.pathPattern = Pattern.compile(stringBuilder.toString());
    }

    /**
     * Returns the index to consider to start the analysis of the given path.
     *
     * @param path
     *            The path
     * @return 0 if the path does not start with a slash, 1 otherwise
     */
    private int computeStartIndex(String path) {
        if (path.length() > 0 && SLASH == path.charAt(0)) {
            return 1;
        }
        return 0;
    }

    /**
     * Matches the path of the request against the path of the matcher.
     *
     * @param requestPath
     *            The path of the request
     * @return A {@link SiriusServerMatchResult}
     */
    public SiriusServerMatchResult match(String requestPath) {
        String path = requestPath;
        if (path.length() > 0 && SLASH == path.charAt(0)) {
            path = path.substring(1);
        }
        if (path.length() > 0 && SLASH != path.charAt(path.length() - 1)) {
            path = path + SLASH;
        }

        Matcher matcher = this.pathPattern.matcher(path);

        Map<String, String> variables = new HashMap<>();
        String remainingPart = ""; //$NON-NLS-1$

        if (matcher.matches()) {
            if (this.variableNames.size() <= matcher.groupCount()) {
                IntStream.range(1, matcher.groupCount()).forEach(index -> {
                    String variable = this.variableNames.get(index - 1);
                    variables.put(variable, matcher.group(index));
                });
            }

            if (matcher.groupCount() > 0) {
                remainingPart = matcher.group(matcher.groupCount());
                if (remainingPart.length() > 0 && SLASH == remainingPart.charAt(remainingPart.length() - 1)) {
                    remainingPart = remainingPart.substring(0, remainingPart.length() - 1);
                }
            }
        }

        return new SiriusServerMatchResult(variables, remainingPart, matcher.matches());
    }
}
