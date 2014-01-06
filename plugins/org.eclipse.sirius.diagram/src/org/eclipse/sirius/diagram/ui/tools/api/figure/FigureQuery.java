/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

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
}
