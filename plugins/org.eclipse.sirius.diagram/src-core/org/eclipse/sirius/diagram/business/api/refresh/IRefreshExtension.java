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
 * The contract of an extension of the refresh mechanism.
 * 
 * @author ymortier
 */
public interface IRefreshExtension {

    /**
     * This operation is invoked before the refresh mechanism. Only needed
     * changes must be done in this operation. The refresh mechanism is complex
     * and this operation can have unintended consequences if you don't control
     * all elements handled through the refresh. It should therefore be used
     * with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     */
    void beforeRefresh(DDiagram dDiagram);

    /**
     * This operation is invoked after the refresh mechanism. Only needed
     * changes must be done in this operation. The refresh mechanism is complex
     * and this operation can have unintended consequences if you don't control
     * all elements handled through the refresh. It should therefore be used
     * with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     */
    void postRefresh(DDiagram dDiagram);

}
