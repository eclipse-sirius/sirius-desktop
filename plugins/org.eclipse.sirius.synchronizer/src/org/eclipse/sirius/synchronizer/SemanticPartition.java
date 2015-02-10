/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

/**
 * A {@link SemanticPartition} represents a set of elements in the input model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface SemanticPartition {

    /**
     * Constant representing no SemanticPartition.
     */
    SemanticPartition NONE = new SemanticPartition() {

        public EvaluatedSemanticPartition evaluate(EObject context, CreatedOutput parentElement) {
            return new EvaluatedSemanticPartition() {

                public boolean isElementOf(EObject sem) {
                    return false;
                }

                public Iterator<EObject> elements() {
                    return Lists.<EObject> newArrayList().iterator();
                }
            };
        }
    };

    /**
     * Evaluate the semantic partition on the given context for the given parent
     * output.
     * 
     * @param context
     *            the EObject instance to evaluate on.
     * @param parentElement
     *            the output element which will become the parent of output
     *            elements created from the evaluation result.
     * @return the semantic partition evaluation result.
     */
    EvaluatedSemanticPartition evaluate(EObject context, CreatedOutput parentElement);

}
