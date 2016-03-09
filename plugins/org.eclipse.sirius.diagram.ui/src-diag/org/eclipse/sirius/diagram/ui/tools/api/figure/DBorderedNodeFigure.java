/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.util.BitSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;

/**
 * This figure overrides {@link BorderedNodeFigure} in order to force layout
 * because of an issue where resizing a node will not relocate its bordered
 * nodes.
 * 
 * @author smonnier
 * 
 */
public class DBorderedNodeFigure extends BorderedNodeFigure {

    private BitSet authorizedSides = new BitSet(PositionConstants.NSEW);

    /**
     * Creates a new DBorderedNodeFigure figure.
     * 
     * @param mainFigure
     *            the figure to use with this figure
     */
    public DBorderedNodeFigure(IFigure mainFigure) {
        super(mainFigure);
        initAuthorizedSides();
    }

    /**
     * Set the borderItem forbidden sides. By default, NORTH, SOUTH, EAST and
     * WEST are authorized.
     * 
     * @param forbiddenSidesPositionConstants
     *            list of forbidden Positions Constants (NORTH, SOUTH, EAST or
     *            WEST)
     */
    public void setForbiddenSides(int... forbiddenSidesPositionConstants) {
        initAuthorizedSides();
        for (int side : forbiddenSidesPositionConstants) {
            this.authorizedSides.clear(side);
        }
    }

    /**
     * Returns the authorized sides in {@link PositionConstants}.
     * 
     * @return a BitSet with {@link PositionConstants} values as key.
     */
    public BitSet getAuthorizedSides() {
        return authorizedSides;
    }

    /**
     * This method has been overridden to avoid comparing bounds of this and
     * getMainFigure(). {@inheritDoc}
     */
    @Override
    protected void layout() {
        getMainFigure().setBounds(this.getBounds().getCopy());
        getBorderItemContainer().invalidateTree();
        erase();
    }

    private void initAuthorizedSides() {
        this.authorizedSides.clear();
        this.authorizedSides.set(PositionConstants.WEST);
        this.authorizedSides.set(PositionConstants.SOUTH);
        this.authorizedSides.set(PositionConstants.EAST);
        this.authorizedSides.set(PositionConstants.NORTH);
    }
}
