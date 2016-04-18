/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graphics.ScaledGraphics;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusGraphicsToGraphics2DAdaptor;

/**
 * .
 * 
 * @author mchauvin
 */
public final class GraphicsUtilities {

    private static Method method;

    private static Method zoomMethod;

    static {
        try {
            method = ScaledGraphics.class.getDeclaredMethod("getGraphics"); //$NON-NLS-1$
            method.setAccessible(true);
            zoomMethod = ScaledGraphics.class.getDeclaredMethod("zoomFillRect", int.class, int.class, int.class, int.class); //$NON-NLS-1$
            zoomMethod.setAccessible(true);
        } catch (final NoSuchMethodException e) {
            // Cannot happen here
        }
    }

    private GraphicsUtilities() {
    }

    /**
     * Get the SWT graphic instance.
     * 
     * @param graphics
     *            the wrapped SWT graphics instance
     * @return the wrapped graphic instance
     */
    public static SWTGraphics getSWTGraphics(final Graphics graphics) {

        SWTGraphics swtGrpahics = null;

        Graphics internalGraphics = null;
        if (graphics instanceof ScaledGraphics) {
            internalGraphics = GraphicsUtilities.getInternalGraphics((ScaledGraphics) graphics);
        }

        if (internalGraphics instanceof SWTGraphics) {
            swtGrpahics = (SWTGraphics) internalGraphics;
            /* we need to recheck in case of zoom see trac #1065 */
        } else if (internalGraphics instanceof ScaledGraphics) {
            swtGrpahics = GraphicsUtilities.getSWTGraphics(internalGraphics);
        } else if (internalGraphics instanceof org.eclipse.draw2d.ScaledGraphics) {
            swtGrpahics = GraphicsUtilities.getSWTGraphics(internalGraphics);
        }
        return swtGrpahics;
    }

    /**
     * Get the SiriusGraphicsToGraphics2DAdaptor graphic instance.
     * 
     * @param graphics
     *            the wrapped SiriusGraphicsToGraphics2DAdaptor graphics
     *            instance
     * @return the wrapped graphic instance
     */
    public static SiriusGraphicsToGraphics2DAdaptor getSiriusGraphicsToGraphics2DAdaptor(Graphics graphics) {
        Graphics internalGraphics = null;
        if (graphics instanceof ScaledGraphics) {
            internalGraphics = GraphicsUtilities.getInternalGraphics((ScaledGraphics) graphics);
        }
        if (internalGraphics instanceof SiriusGraphicsToGraphics2DAdaptor) {
            return (SiriusGraphicsToGraphics2DAdaptor) internalGraphics;
        }
        return null;
    }

    private static Graphics getInternalGraphics(final ScaledGraphics graphics) {
        try {
            method = ScaledGraphics.class.getDeclaredMethod("getGraphics"); //$NON-NLS-1$
            method.setAccessible(true);
            return (Graphics) method.invoke(graphics);
        } catch (final SecurityException e) {
            // Cannot happen here
        } catch (final IllegalArgumentException e) {
            // // Cannot happen here
        } catch (final IllegalAccessException e) {
            // // Cannot happen here
        } catch (final InvocationTargetException e) {
            // // Cannot happen here
        } catch (final NoSuchMethodException e) {
            // Cannot happen here
        }
        return null;
    }

    /**
     * Get zoom fill rectangle.
     * 
     * @param graphics
     *            the graphics
     * @param bounds
     *            the bounds
     * @return the rectangle
     */
    public static Rectangle zoomFillRectangle(final Graphics graphics, Rectangle bounds) {
        try {
            zoomMethod = ScaledGraphics.class.getDeclaredMethod("zoomFillRect", int.class, int.class, int.class, int.class); //$NON-NLS-1$
            zoomMethod.setAccessible(true);
            return (Rectangle) zoomMethod.invoke(graphics, bounds.x, bounds.y, bounds.width, bounds.height);
        } catch (final SecurityException e) {
            // Cannot happen here
        } catch (final IllegalArgumentException e) {
            // // Cannot happen here
        } catch (final IllegalAccessException e) {
            // // Cannot happen here
        } catch (final InvocationTargetException e) {
            // // Cannot happen here
        } catch (final NoSuchMethodException e) {
            // Cannot happen here
        }
        return null;
    }
}
