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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;

/**
 * @was-generated
 */
public class DNodeViewFactory extends AbstractDNodeViewFactory {

    @Override
    protected int getVisualId() {
        return DNodeEditPart.VISUAL_ID;
    }
    
    @Override
    protected int getNameVisualId() {
        return NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID;
    }

}
