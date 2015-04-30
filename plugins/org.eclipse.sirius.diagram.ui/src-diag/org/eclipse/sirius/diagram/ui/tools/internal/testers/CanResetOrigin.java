/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;

/**
 * A property tester to know whether we should enable or not the "Reset origin"
 * action.
 * 
 * @author mporhel
 *
 */
public class CanResetOrigin extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean enabled = receiver instanceof DDiagramEditPart;
        if (receiver instanceof AbstractDiagramContainerEditPart) {
            enabled = !((AbstractDiagramContainerEditPart) receiver).isRegionContainer();
        }
        return enabled;
    }

}
