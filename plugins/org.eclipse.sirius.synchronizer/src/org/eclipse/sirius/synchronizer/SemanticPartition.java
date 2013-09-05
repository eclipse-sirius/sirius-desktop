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

        public EvaluatedSemanticPartition evaluate(EObject context) {
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

    EvaluatedSemanticPartition evaluate(EObject context);

}
