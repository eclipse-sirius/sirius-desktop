/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import org.eclipse.sirius.diagram.business.internal.metamodel.operations.BorderedStyleSpecOperation;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.StyleSpecOperations;
import org.eclipse.sirius.diagram.impl.EllipseImpl;

/**
 * Implementation of {@link org.eclipse.sirius.viewpoint.Ellipse}.
 * 
 * @author mporhel
 */
public class EllipseSpec extends EllipseImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.NodeStyleImpl#refresh()
     */
    @Override
    public void refresh() {
        StyleSpecOperations.refresh(this);
        BorderedStyleSpecOperation.refresh(this);
        // FIXME PCD: check if it is OK to comment these lines
        // It seems refreshColors() is already invoked in
        // StyleSpecOperation.refresh() through StyleHelper.refresh
        // if (this.getColor() != null)
        // StyleHelper.refreshColor(getContext(), this.getColor());
    }
}
