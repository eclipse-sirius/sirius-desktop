/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.MarginBorder;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;

/**
 * Figure for the parallelogram shape.
 * 
 * @author cbrun
 * 
 */
public class ViewNodeContainerParallelogram extends ParallelogramFigure implements ViewNodeContainerFigureDesc {

    private SiriusWrapLabel fContainerLabelFigure;

    /**
     * Constructor.
     */
    public ViewNodeContainerParallelogram() {
        // setLayoutManager(new XYLayout());
        createContents();
        this.setBorder(new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0));
    }

    private void createContents() {
        fContainerLabelFigure = new SiriusWrapLabel();
        fContainerLabelFigure.setText("  "); //$NON-NLS-1$
        fContainerLabelFigure.setTextWrap(true);
        this.add(fContainerLabelFigure);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc#getLabelFigure()
     */
    public SiriusWrapLabel getLabelFigure() {
        return fContainerLabelFigure;
    }
}
