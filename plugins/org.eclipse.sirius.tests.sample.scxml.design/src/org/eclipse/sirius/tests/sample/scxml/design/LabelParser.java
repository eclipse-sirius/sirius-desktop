/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.scxml.design;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * Label parser.
 * 
 * @author Benjamin GROUHAN
 *
 */
public class LabelParser {
    /**
     * Transition labels are in the form : "[cond]event" where [cond] and event
     * are optionnal.
     */
    private static Pattern REGEX1 = Pattern.compile("^(\\s*\\[\\s*\\w+\\s*\\])?\\s*[\\w\\.]*\\s*$");

    /**
     * Data labels are in the form : "id=expr".
     */
    private static Pattern REGEX2 = Pattern.compile("^\\s*\\w+\\s*=\\s*\\w+\\s*$");

    /**
     * Default constructor.
     */
    public LabelParser() {
    }

    /**
     * Get the transition event from the label.
     * 
     * @param transition
     *            the current transition
     * @param label
     *            the label to parse
     * @return the transition event
     */
    public String getTransitionEvent(ScxmlTransitionType transition, String label) {
        String ret = transition.getEvent();
        Matcher m = REGEX1.matcher(label);
        if (m.matches()) {
            String event = label.substring(label.indexOf("]") + 1);
            ret = event.trim();
        }
        return ret;
    }

    /**
     * Get the transition condition from the label.
     * 
     * @param transition
     *            the current transition
     * @param label
     *            the label to parse
     * @return the transition condition
     */
    public String getTransitionCondition(ScxmlTransitionType transition, String label) {
        String ret = transition.getCond();
        Matcher m = REGEX1.matcher(label);
        if (m.matches()) {
            if (label.indexOf("[") < 0) {
                ret = "";
            } else {
                String cond = label.substring(label.indexOf("[") + 1, label.indexOf("]"));
                ret = cond.trim();
            }
        }
        return ret;
    }

    /**
     * Get the data id from the label.
     * 
     * @param data
     *            the current data
     * @param label
     *            the label to parse
     * @return the data id
     */
    public String getDataID(ScxmlDataType data, String label) {
        String ret = data.getId();
        Matcher m = REGEX2.matcher(label);
        if (m.matches()) {
            String id = label.substring(0, label.indexOf("="));
            ret = id.trim();
        }
        return ret;
    }

    /**
     * Get the data expression from the label.
     * 
     * @param data
     *            the current data
     * @param label
     *            the label to parse
     * @return the data expression
     */
    public String getDataExpression(ScxmlDataType data, String label) {
        String ret = data.getExpr();
        Matcher m = REGEX2.matcher(label);
        if (m.matches()) {
            String expr = label.substring(label.indexOf("=") + 1);
            ret = expr.trim();
        }
        return ret;
    }
}
