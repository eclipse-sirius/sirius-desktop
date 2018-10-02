/*******************************************************************************
 * Copyright (c) 2009, 2015 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

/**
 * The edit policy provider for the <i>apply layout</i> edit policy.
 * 
 * Copied from org.eclipse.elk.conn.gmf.LayoutEditPolicyProvider of commit
 * e99248e44c71a5a02fe45bc4cd5150cd7f50c559.
 * 
 * @author haf
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class LayoutEditPolicyProvider extends AbstractProvider implements IEditPolicyProvider {

    /** the key used to install an <i>apply layout</i> edit policy. */
    public static final String APPLY_LAYOUT_ROLE = "ApplyLayoutEditPolicy";

    /**
     * {@inheritDoc}
     */
    @Override
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof DiagramEditPart) {
            editPart.installEditPolicy(APPLY_LAYOUT_ROLE, new GmfLayoutEditPolicy());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean provides(final IOperation operation) {
        return operation instanceof CreateEditPoliciesOperation;
    }

}
