/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.util;

import org.eclipse.gmf.runtime.common.ui.util.IPartSelector;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Tabbar selector to ensure that the selection concerns the right workbench
 * part.
 * 
 * @author fbarbin
 * 
 */
public class TabbarPartSelector implements IPartSelector {

    private IWorkbenchPart representationPart;

    /**
     * Default constructor.
     * 
     * @param part
     *            the workbench part for this selector. The given part will be
     *            use to get the corresponding representation id (his uri as a
     *            string)
     */
    public TabbarPartSelector(IWorkbenchPart part) {
        this.representationPart = part;
    }

    /**
     * {@inheritDoc}
     */
    public boolean selects(IWorkbenchPart part) {
        if (representationPart != null && !representationPart.equals(part)) {
            return false;
        }
        return true;
    }

}
