/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;

/**
 * @was-generated
 */
public class DNode4ViewFactory extends AbstractDNodeViewFactory {

    @Override
    protected int getVisualId() {
        return DNode4EditPart.VISUAL_ID;
    }
    
    @Override
    protected int getNameVisualId() {
        return NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID;
    }
}
