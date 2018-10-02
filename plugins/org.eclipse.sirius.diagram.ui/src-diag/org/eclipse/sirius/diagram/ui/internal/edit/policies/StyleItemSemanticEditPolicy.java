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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * Extracted from previous generated policies. Those policies were generated
 * from the style descriptions
 * <ul>
 * <li>BundledImageItemSemanticEditPolicy</li>
 * <li>CustomStyleItemSemanticEditPolicy</li>
 * <li>DotItemSemanticEditPolicy</li>
 * <li>EllipseItemSemanticEditPolicy</li>
 * <li>GaugeCompositeItemSemanticEditPolicy</li>
 * <li>LozengeItemSemanticEditPolicy</li>
 * <li>NoteItemSemanticEditPolicy</li>
 * <li>SquareItemSemanticEditPolicy</li>
 * <li>WorkspaceImageItemSemanticEditPolicy</li>
 * <li>QuadrypticItemSemanticPolicy</li>
 * </ul>
 */
public class StyleItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        CompoundCommand cc = getDestroyEdgesCommand();
        addDestroyShortcutsCommand(cc);
        cc.add(getGEFWrapper(new DestroyElementCommand(req)));
        return cc.unwrap();
    }

}
