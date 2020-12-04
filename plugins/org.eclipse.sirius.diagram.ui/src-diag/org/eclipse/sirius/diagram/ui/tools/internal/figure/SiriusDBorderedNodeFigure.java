/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.util.Objects;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.DBorderedNodeFigure;

/**
 * Sirius-specific extension of GMF's BorderedNodeFigure. Support the "Visiblity" mode and semantic traceability (when
 * rendering/exporting to SVG).
 * 
 * @author pcdavid
 *
 */
public class SiriusDBorderedNodeFigure extends DBorderedNodeFigure {
    private final IDiagramElementEditPart owner;

    /**
     * Constructor.
     * 
     * @param mainFigure
     *            the maing figure to use with this figure.
     * @param owner
     *            the edit part owning this figure.
     */
    public SiriusDBorderedNodeFigure(IFigure mainFigure, IDiagramElementEditPart owner) {
        super(mainFigure);
        this.owner = Objects.requireNonNull(owner);
    }

    @Override
    public void paint(Graphics graphics) {
        ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) owner.getModel());
        try {
            CommonEditPartOperation.setGraphicsTraceabilityId(graphics, () -> owner.resolveTargetSemanticElement());
            super.paint(graphics);
            CommonEditPartOperation.setGraphicsTraceabilityId(graphics, null);
            graphics.restoreState();
        } finally {
            graphics.popState();
        }
    }
}
