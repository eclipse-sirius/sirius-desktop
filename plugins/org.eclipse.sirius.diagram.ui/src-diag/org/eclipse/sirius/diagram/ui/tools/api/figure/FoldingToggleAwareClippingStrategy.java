/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A clipping strategy to avoid (0,0) sized clip area for
 * {@link FoldingToggleImageFigure} and allow repaint after user style
 * customization. For other figures type, it keep the
 * {@link org.eclipse.draw2d.Figure#paintChildren(org.eclipse.draw2d.Graphics)}
 * behavior.
 * 
 * @author mporhel
 */
public class FoldingToggleAwareClippingStrategy implements IClippingStrategy {

    /**
     * {@inheritDoc}
     */
    public Rectangle[] getClip(IFigure childFigure) {
        if (childFigure != null) {
            Rectangle bounds = childFigure.getBounds().getCopy();
            if (childFigure instanceof FoldingToggleImageFigure) {
                bounds.setSize(FoldingToggleImageFigure.FOLD_ICON_HEIGHT, FoldingToggleImageFigure.FOLD_ICON_WIDTH);
            }
            return new Rectangle[] { bounds };
        }
        return null;
    }
}
