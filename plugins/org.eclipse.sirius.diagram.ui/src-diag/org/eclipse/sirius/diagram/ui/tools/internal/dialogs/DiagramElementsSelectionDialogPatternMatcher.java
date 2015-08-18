/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import org.eclipse.gmf.runtime.common.core.util.StringMatcher;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;

import com.google.common.base.Predicate;

/**
 * <p>
 * A {@link StringMatcher} used to detect elements that match a regular
 * expression.
 * </p>
 * 
 * This Matcher creates a MatchPredicate, that can be used to determine if a
 * given Object matches a regular expression.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DiagramElementsSelectionDialogPatternMatcher {

    private Predicate<Object> matchPredicate;

    private StringMatcher stringMatcher;

    /**
     * Creates a new PatternMatcher.
     * 
     * 
     * @param expreg
     *            the regular expression (for example '?a?' or 'abc' or '*c') ;
     *            <code>null</code> or empty regular expression will be replaced
     *            by '*'
     * 
     */
    public DiagramElementsSelectionDialogPatternMatcher(String expreg) {
        String computedExpreg = expreg;
        if (expreg == null) {
            computedExpreg = ""; //$NON-NLS-1$
        }
        // If the regular expression ends with a space, we have to use the exact
        // value of the given expreg
        if (computedExpreg.endsWith(" ")) { //$NON-NLS-1$
            computedExpreg = computedExpreg.substring(0, computedExpreg.lastIndexOf(' '));
        } else {
            // Otherwise, we add a star to make 'XYZ' recognized by the 'X'
            // expreg (as in quick outline for example)
            computedExpreg = computedExpreg + "*"; //$NON-NLS-1$
        }
        this.stringMatcher = new StringMatcher(computedExpreg, true, false);

    }

    /**
     * Creates a {@link Predicate} that can be applied on any Object. This
     * predicates will return true if the tested element is a
     * {@link DDiagramElement} and that its name is matching the regular
     * expression used to construct this Matcher.
     * 
     * @return a {@link Predicate} that can be applied on any Object to
     *         determine if whether it's matching the regular expression used to
     *         construct this Matcher
     */
    public Predicate<Object> getMatchPredicate() {
        if (matchPredicate == null) {
            matchPredicate = new Predicate<Object>() {

                public boolean apply(Object input) {
                    String elementName = null;
                    if (input instanceof DDiagramElement) {
                        DDiagramElement element = (DDiagramElement) input;
                        elementName = element.getName();
                    } else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
                        AbstractDDiagramElementLabelItemProvider element = (AbstractDDiagramElementLabelItemProvider) input;
                        elementName = element.getText(element.getTarget());
                    }
                    return elementName != null && stringMatcher.match(elementName);
                }
            };
        }
        return matchPredicate;
    }
}
