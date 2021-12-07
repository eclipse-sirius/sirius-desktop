/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

import com.google.common.collect.Iterables;

/**
 * A class aggregating all the queries (read-only!) having a {@link IFigure} as
 * starting point.
 * 
 * @author lredor
 * 
 */
public class FigureQuery {

    private IFigure figure;

    /**
     * Create a new query.
     * 
     * @param figure
     *            the element to query.
     */
    public FigureQuery(IFigure figure) {
        this.figure = figure;
    }

    /**
     * Return the label figure of this figure. Search in all chidren the first
     * figure of kind {@link Label}, {@link WrapLabel} or
     * {@link SiriusWrapLabel}.
     * 
     * @return the first label figure or null if any
     */
    public Option<IFigure> getLabelFigure() {
        Option<IFigure> result = Options.newNone();
        if (figure instanceof SiriusWrapLabel || figure instanceof WrapLabel || figure instanceof Label) {
            result = Options.newSome(figure);
        } else {
            for (IFigure childFigure : Iterables.filter(figure.getChildren(), IFigure.class)) {
                Option<IFigure> temp = new FigureQuery(childFigure).getLabelFigure();
                if (temp.some()) {
                    result = temp;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Return the label of the first label figure of this figure. Search in all
     * chidren the first figure of kind {@link Label}, {@link WrapLabel} or
     * {@link SiriusWrapLabel}.
     * 
     * @return the label of the first label figure or null if any
     */
    public Option<String> getText() {
        Option<String> result = Options.newNone();
        Option<IFigure> labelFigure = getLabelFigure();
        if (labelFigure.some()) {
            if (labelFigure.get() instanceof SiriusWrapLabel) {
                result = Options.newSome(((SiriusWrapLabel) labelFigure.get()).getText());
            }
            if (labelFigure.get() instanceof WrapLabel) {
                result = Options.newSome(((WrapLabel) labelFigure.get()).getText());
            } else if (labelFigure.get() instanceof Label) {
                result = Options.newSome(((Label) labelFigure.get()).getText());
            }
        }
        return result;
    }

    /**
     * Convert the LabelViewConstant value to the equivalent EditPart VisualID.
     * 
     * @param labelViewConstant
     *            the value to convert
     * @return the conversion to VisualID
     */
    public int edgeLabelViewConstantToVisualID(int labelViewConstant) {
        int result = -1;
        switch (labelViewConstant) {
        case LabelViewConstants.SOURCE_LOCATION:
            result = DEdgeBeginNameEditPart.VISUAL_ID;
            break;
        case LabelViewConstants.MIDDLE_LOCATION:
            result = DEdgeNameEditPart.VISUAL_ID;
            break;
        case LabelViewConstants.TARGET_LOCATION:
            result = DEdgeEndNameEditPart.VISUAL_ID;
            break;
        default:
            break;
        }
        return result;
    }
}
