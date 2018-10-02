/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
     * This operation is invoked before the refresh mechanism. Only needed changes must be done in this operation. The
     * refresh mechanism is complex and this operation can have unintended consequences if you don't control all
     * elements handled through the refresh. It should therefore be used with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     */
    void beforeRefresh(DDiagram dDiagram);

    /**
     * This operation is invoked after the refresh mechanism. Only needed changes must be done in this operation. The
     * refresh mechanism is complex and this operation can have unintended consequences if you don't control all
     * elements handled through the refresh. It should therefore be used with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     */
    void postRefresh(DDiagram dDiagram);

    /**
     * This operation is invoked before the refresh to give a chance to external code to override completely the default
     * refresh implementation. If true is returned by the implementer then the default implementation will not be
     * triggered at all.
     * 
     * The refresh mechanism is complex and this operation can have unintended consequences if you don't control all
     * elements handled through the refresh. It should therefore be used with caution.
     * 
     * @param dDiagram
     *            the diagram to refresh.
     * @return true if the refresh should not be done, false otherwise.
     */
    default boolean aroundRefresh(DDiagram dDiagram) {
        return false;
    }

}
