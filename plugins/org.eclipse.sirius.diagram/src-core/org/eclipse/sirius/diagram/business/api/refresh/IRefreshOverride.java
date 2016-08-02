/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.refresh;

import org.eclipse.sirius.diagram.DDiagram;

/**
 * An interface which might be implemented by a {@link IRefreshExtension}
 * instance to bypass the default refresh implementation.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 *
 */
public interface IRefreshOverride {

    /**
     * This operation is invoked before the refresh to give a chance to external
     * code to override completely the default refresh implementation. If true
     * is returned by the implementer then the default implementation will not
     * be triggered at all.
     * 
     * The refresh mechanism is complex and this operation can have unintended
     * consequences if you don't control all elements handled through the
     * refresh. It should therefore be used with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     * @return true if the refresh should not be done, false otherwise.
     */
    boolean aroundRefresh(DDiagram dDiagram);

}
