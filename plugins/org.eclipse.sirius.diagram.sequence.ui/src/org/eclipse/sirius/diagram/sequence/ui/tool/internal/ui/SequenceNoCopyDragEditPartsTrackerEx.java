/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;

/**
 * Specific implementation of {@link NoCopyDragEditPartsTrackerEx}. <br/>
 * 
 * @author nlepine
 */
public class SequenceNoCopyDragEditPartsTrackerEx extends NoCopyDragEditPartsTrackerEx {

    /**
     * Constructor.
     * 
     * @param sourceEditPart
     *            EditPart
     */
    public SequenceNoCopyDragEditPartsTrackerEx(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        CacheHelper.clearCaches();
        return super.handleButtonUp(button);
    }

    @Override
    protected boolean handleButtonDown(int button) {
        boolean handleButtonDown = super.handleButtonDown(button);
        CacheHelper.initCaches();
        return handleButtonDown;
    }

}
