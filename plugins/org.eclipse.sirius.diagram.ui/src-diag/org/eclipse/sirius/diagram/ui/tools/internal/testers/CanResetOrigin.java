/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
