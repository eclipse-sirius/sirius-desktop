/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * A special label that can be painted or not depending on the current context/layer.
 * 
 * @author pcdavid
 */
public final class OperandLabel extends SiriusWrapLabel {

    /**
     * Flag to control whether the labels should really be painted or not.
     */
    public static final AtomicBoolean PAINT_ENABLED = new AtomicBoolean(false);

    private final View view;

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public OperandLabel(View view) {
        this.view = view;
    }

    @Override
    public void paint(Graphics graphics) {
        if (!PAINT_ENABLED.get()) {
            return;
        }
        if (view != null) {
            ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, view);
            try {
                super.paint(graphics);
                graphics.restoreState();
            } finally {
                graphics.popState();
            }
        } else {
            super.paint(graphics);
        }
    }
}
