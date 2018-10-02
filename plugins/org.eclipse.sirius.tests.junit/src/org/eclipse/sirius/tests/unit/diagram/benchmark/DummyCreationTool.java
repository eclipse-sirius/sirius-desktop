/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
