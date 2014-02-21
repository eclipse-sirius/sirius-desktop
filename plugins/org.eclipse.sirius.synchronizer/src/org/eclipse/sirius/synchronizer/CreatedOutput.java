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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * This interface represents an output element existing in the output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface CreatedOutput {

    OutputDescriptor getDescriptor();

    void setNewIndex(int nextIndex);

    EObject getCreatedElement();

    /**
     * Tells if we must synchronize the direct children of this created element.
     * 
     * @return true if we must synchronize the direct children of this created
     *         element
     */
    boolean synchronizeChildren();

    Maybe<? extends ChildCreationSupport> getChildSupport();

    void updateMapping();

    void refresh();

    void setNewMapping(Mapping map);

    /*
     * The return of the child mappings to consider might be parameterized
     * depending on the output instance.
     */
    List<? extends Mapping> getChildMappings();

    int getNewIndex();

}
