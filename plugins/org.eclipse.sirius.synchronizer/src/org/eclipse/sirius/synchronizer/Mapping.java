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

import com.google.common.base.Predicate;

/**
 * A mapping is a transformation rule from something in the input model to
 * something in the output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface Mapping {

    /**
     * Predicates indicating if the mapping is check only.
     */
    Predicate<Mapping> IS_CHECK_ONLY = new Predicate<Mapping>() {

        public boolean apply(Mapping input) {
            return !input.getCreator().some();
        }
    };

    /**
     * Returns the super mapping of the current mapping.
     * 
     * @return the super mapping of the current mapping
     */
    Maybe<? extends Mapping> getSuper();

    /**
     * Return the {@link AutomaticCreator} of this Mapping.
     * 
     * @return the {@link AutomaticCreator} of this Mapping
     */
    Maybe<? extends AutomaticCreator> getCreator();

    /**
     * Returns the semantic partition associated to this mapping.
     * 
     * @return the semantic partition associated to this mapping
     */
    SemanticPartition getSemanticPartition();

    /**
     * Indicates if this mapping is enabled.
     * 
     * @return true if this mapping is enabled, false otherwise
     */
    boolean isEnabled();

}
