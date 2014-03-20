/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * The default provider. Singleton.
 * 
 * 
 * @author ymortier
 */
public final class DefaultBorderItemLocatorProvider implements BorderItemLocatorProvider {

    /** The shared instance. */
    private static DefaultBorderItemLocatorProvider instance = new DefaultBorderItemLocatorProvider();

    /**
     * Avoid instantiation from external.
     */
    private DefaultBorderItemLocatorProvider() {
        // empty.
    }

    /**
     * Return the shared instance.
     * 
     * @return the shared instance.
     */
    public static DefaultBorderItemLocatorProvider getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.BorderItemLocatorProvider#getBorderItemLocator(org.eclipse.draw2d.IFigure,
     *      org.eclipse.sirius.diagram.DDiagramElement,
     *      org.eclipse.sirius.diagram.DDiagramElement)
     */
    public IBorderItemLocator getBorderItemLocator(final IFigure figure, final DDiagramElement vpElementOwner, final DDiagramElement diagramElementBorderItem) {
        final DBorderItemLocator locator = new DBorderItemLocator(figure, PositionConstants.NSEW);
        if (new DDiagramElementQuery(diagramElementBorderItem).isIndirectlyCollapsed()) {
            locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
        } else {
            locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
        }
        return locator;
    }

}
