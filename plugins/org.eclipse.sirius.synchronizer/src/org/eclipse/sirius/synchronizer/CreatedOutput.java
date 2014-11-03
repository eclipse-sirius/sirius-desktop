/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
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
import org.eclipse.sirius.ext.base.Option;

/**
 * This interface represents an output element existing in the output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public interface CreatedOutput {

    OutputDescriptor getDescriptor();

    void setNewIndex(int nextIndex);

    EObject getCreatedElement();

    Option<? extends ChildCreationSupport> getChildSupport();

    /**
     * Tells if we must create and refresh view model elements related to this
     * created element. Returning false allows doing lazy synchronization, for
     * example to not create and refresh children view elements not yet visible.
     * 
     * @param refreshPlan
     *            the {@link RefreshPlan}
     * 
     * @return true if we must synchronize the direct children of this created
     *         element, false otherwise
     */
    boolean synchronizeChildren(RefreshPlan refreshPlan);

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
