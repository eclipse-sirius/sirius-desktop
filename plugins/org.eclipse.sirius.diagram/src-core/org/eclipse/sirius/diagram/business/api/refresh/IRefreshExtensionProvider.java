/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
 * Provides extensions of the refresh mechanism.
 * 
 * @author ymortier
 */
public interface IRefreshExtensionProvider {

    /**
     * Returns <code>true</code> if this provider provides an extension of the
     * refresh mechanism for the specified viewpoint.
     * 
     * @param viewPoint
     *            the viewpoint to refresh.
     * @return <code>true</code> if this provider provides an extension of the
     *         refresh mechanism for the specified viewpoint.
     */
    boolean provides(DDiagram viewPoint);

    /**
     * Provides a an extension of the refresh mechanism for the specified
     * viewpoint.
     * 
     * @param viewPoint
     *            the viewpoint to refresh.
     * @return the extension of the refresh mechanism.
     */
    IRefreshExtension getRefreshExtension(DDiagram viewPoint);

}
