/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.ordering;

import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl;

/**
 * Customization of the default {@link SingleEventEndImpl} implementation.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class SingleEventEndSpec extends SingleEventEndImpl {

    /**
     * {@inheritDoc}
     * 
     * @not-generated
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (this == obj) {
            equals = true;
        } else if (obj instanceof SingleEventEnd) {
            SingleEventEnd that = (SingleEventEnd) obj;
            equals = this.start == that.isStart() && this.getSemanticEnd().equals(that.getSemanticEnd()) && this.getSemanticEvent().equals(that.getSemanticEvent());
        }
        return equals;
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated
     */
    @Override
    public int hashCode() {
        return 17 * this.getSemanticEnd().hashCode() * this.getSemanticEvent().hashCode();
    }
}
