/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.language;

import java.util.Collections;
import java.util.List;

/**
 * Instances of this class represent an expression that has been split into an arbitrary number of sub-components.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class SplitExpression {
    /** The expression as a whole, before any splitting took place. */
    private final Object fullExpression;

    /** Sub-steps computed for this expression. */
    private final List<SubExpression> subSteps;

    /** If this needs a prettier label than {@code fullExpression.toString()}. */
    private final String label;

    /**
     * Instantiates a split expression given its full expression and the list of its sub-steps. The expression itself
     * will be used as a label in the split expression view.
     * 
     * @param fullExpression
     *            The expression as a whole.
     * @param subSteps
     *            The list of sub-components of this expression.
     */
    public SplitExpression(Object fullExpression, List<SubExpression> subSteps) {
        this(fullExpression, subSteps, null);
    }

    /**
     * Instantiates a split expression given its full expression and the list of its sub-steps, along with a
     * human-readable label for the expression view.
     * 
     * @param fullExpression
     *            The expression as a whole.
     * @param subSteps
     *            The list of sub-components of this expression.
     * @param label
     *            Label to display in the split expression viewer. If this is <code>null</code> or empty, the
     *            expression's toString() will be used instead.
     */
    public SplitExpression(Object fullExpression, List<SubExpression> subSteps, String label) {
        this.fullExpression = fullExpression;
        this.subSteps = subSteps;
        if (label == null || label.length() == 0) {
            this.label = fullExpression.toString();
        } else {
            this.label = label;
        }
    }

    /**
     * Returns the full expression represented by this instance.
     * 
     * @return The full expression represented by this instance.
     */
    public Object getFullExpression() {
        return fullExpression;
    }

    /**
     * Returns an immutable view of the sub steps composing this expression.
     * 
     * @return An immutable view of the sub steps composing this expression.
     */
    public List<SubExpression> getSubSteps() {
        return Collections.unmodifiableList(subSteps);
    }

    /**
     * Returns the label to display in the split expression viewer for this expression.
     * 
     * @return The label to display in the split expression viewer for this expression.
     */
    public String getLabel() {
        return label;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getLabel();
    }
}
