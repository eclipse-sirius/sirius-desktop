/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.CreationTool;

/**
 * 
 * @author ymortier
 */
public class DummyCreationTool extends CreationTool {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.tools.TargetingTool#setTargetEditPart(org.eclipse.gef.EditPart)
     */
    @Override
    public void setTargetEditPart(EditPart editpart) {
        super.setTargetEditPart(editpart);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.tools.TargetingTool#getTargetRequest()
     */
    @Override
    public Request getTargetRequest() {
        return super.getTargetRequest();
    }

}
