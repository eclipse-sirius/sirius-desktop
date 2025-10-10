/*******************************************************************************
 * Copyright (c) 2013, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Instances of this class represent an arbitrary sub-component of an expression. A sub-expression can itself be
 * composed of an arbitrary count of sub-components.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class SubExpression {
    /** The expression representing this particular sub-step. */
    private final Object expression;

    /** The sub-steps composing this particular expression. */
    private List<SubExpression> subSteps;

    /** If this needs a prettier label than {@code fullExpression.toString()}. */
    private final String label;

    /**
     * Instantiates a sub-expression given the expression it represents. This will use {@code expression.toString()} as
     * the expression label in the split expressions viewer.
     * 
     * @param expression
     *            The expression representing this particular sub-expression.
     */
    public SubExpression(Object expression) {
        this(expression, null);
    }

    /**
     * Instantiates a sub-expression given the expression it represents.
     * 
     * @param expression
     *            The expression representing this particular sub-expression.
     * @param label
     *            Label to display in the split expression viewer. If this is <code>null</code> or empty, the
     *            expression's toString() will be used instead.
     */
    public SubExpression(Object expression, String label) {
        this.expression = expression;
        this.subSteps = new ArrayList<SubExpression>();
        if (label == null || label.length() == 0) {
            this.label = expression.toString();
        } else {
            this.label = label;
        }
    }

    /**
     * Returns the expression representing this sub-expression.
     * 
     * @return The expression representing this sub-expression.
     */
    public Object getExpression() {
        return expression;
    }

    /**
     * Adds a sub-step to this expression.
     * 
     * @param subStep
     *            The sub-step of this expression.
     */
    public void addSubStep(SubExpression subStep) {
        subSteps.add(subStep);
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
