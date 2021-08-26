/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.business.internal.spec;

import org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl;

/**
 * Customization of FixedColorImpl.
 * 
 * @author mporhel
 */
public class FixedColorSpec extends FixedColorImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#setBlue(int)
     */
    @Override
    public void setBlue(final int newBlue) {
        if (newBlue >= 255) {
            super.setBlue(255);
        } else if (newBlue < 0) {
            super.setBlue(0);
        } else {
            super.setBlue(newBlue);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#setRed(int)
     */
    @Override
    public void setRed(final int newRed) {
        if (newRed >= 255) {
            super.setRed(255);
        } else if (newRed < 0) {
            super.setRed(0);
        } else {
            super.setRed(newRed);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#setGreen(int)
     */
    @Override
    public void setGreen(final int newGreen) {
        if (newGreen >= 255) {
            super.setGreen(255);
        } else if (newGreen < 0) {
            super.setGreen(0);
        } else {
            super.setGreen(newGreen);
        }
    }
}
