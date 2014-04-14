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

import org.eclipse.sirius.ext.base.Option;


/**
 * A mapping is a transformation rule from something in the input model to
 * something in the output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface Mapping {
    /**
     * Returns the super mapping of the current mapping.
     * 
     * @return the super mapping of the current mapping
     */
    Option<? extends Mapping> getSuper();

    /**
     * Return the {@link AutomaticCreator} of this Mapping.
     * 
     * @return the {@link AutomaticCreator} of this Mapping
     */
    Option<? extends AutomaticCreator> getCreator();

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
