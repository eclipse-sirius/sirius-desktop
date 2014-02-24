/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.impl.BundledImageImpl;

/**
 * Implementation of {@link org.eclipse.sirius.viewpoint.BundledImage}.
 * 
 * @author ymortier
 */
public class BundledImageSpec extends BundledImageImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.NodeStyleImpl#refresh()
     */
    @Override
    public void refresh() {
        StyleSpecOperations.refresh(this);
        BorderedStyleSpecOperation.refresh(this);
    }

}
