/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.RunnableWithResult.Impl;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.datatype.GradientData;

/**
 * A {@link RunnableWithResult} to get a {@link GradientData} from a
 * {@link NodeFigure}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class NodeFigureGradientDataGetter extends Impl<GradientData> implements RunnableWithResult<GradientData> {

    private final NodeFigure nodeFigure;

    /**
     * Default constructor.
     * 
     * @param nodeFigure
     *            the {@link NodeFigure} for which to get the
     *            {@link GradientData}
     */
    public NodeFigureGradientDataGetter(NodeFigure nodeFigure) {
        this.nodeFigure = nodeFigure;
    }

    /**
     * Overridden to get the {@link GradientData} corresponding to the specified
     * {@link NodeFigure}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (nodeFigure.isUsingGradient()) {
            GradientData figureGradientData = new GradientData(nodeFigure.getGradientColor1(), nodeFigure.getGradientColor2(), nodeFigure.getGradientStyle());
            setResult(figureGradientData);
        }
    }
}
