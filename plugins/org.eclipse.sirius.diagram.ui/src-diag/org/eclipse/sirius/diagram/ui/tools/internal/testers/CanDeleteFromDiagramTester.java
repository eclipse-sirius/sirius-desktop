/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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

import java.util.Collections;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;

/**
 * A property tester that test if selection can be deleted from diagram.
 * 
 * @author fbarbin
 * 
 */
public class CanDeleteFromDiagramTester extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof IGraphicalEditPart) {
            return DeleteFromDiagramAction.shouldBeEnabledForEditParts(Collections.<IGraphicalEditPart> singletonList((IGraphicalEditPart) receiver));
        }
        return false;

    }

}
