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

import java.util.Collection;

/**
 * Contract providing the information we need to get before a synchronization
 * call.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface PreRefreshStatus {

    Iterable<? extends CreatedOutput> getExistingOutputs();

    void computeStatus(CreatedOutput container, Collection<? extends Mapping> childMappings);

}
