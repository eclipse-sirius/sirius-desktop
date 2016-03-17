/******************************************************************************
 * Copyright (c) 2004, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimationModel;
import org.eclipse.gmf.runtime.gef.ui.internal.editparts.AnimatedZoomListener;

/**
 * Sirius zoom manager zooming on given point and not on view center. The code
 * is the same than
 * {@link org.eclipse.gmf.runtime.gef.ui.internal.editparts.AnimatableZoomManager}
 * except the method primAnimateSetZoom() that has been updated to zoom on point
 * and not center of view.
 * 
 * We don't extend AnimatableZoomManager because the updated method contains a
 * call to super that would make super super code duplication. Moreover due to
 * private method using the updated method a lot of override must be done if we
 * were extending this class.
 *
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
@SuppressWarnings("restriction")
public class SiriusAnimatableZoomManager extends ZoomManager {
    private static final int DURATION_INCREMENT = 400;

    private int zoomAnimationStyle = ANIMATE_NEVER;

    private List<Object> animationListeners = new ArrayList<Object>();

    /**
     * Constructor.
     * 
     * @param pane
     *            the scalable graphic element.
     * @param viewport
     *            the current viewport.
     */
    public SiriusAnimatableZoomManager(ScalableFigure pane, Viewport viewport) {
        super(pane, viewport);
    }

    /**
     * Returns the zoom animation style.
     * 
     * @return Returns the zoomAnimationStyle.
     */
    public int getZoomAnimationStyle() {
        return zoomAnimationStyle;
    }

    /**
     * Sets which zoom methods get animated.
     * 
     * @param style
     *            the style bits determining the zoom methods to be animated.
     */
    @Override
    public void setZoomAnimationStyle(int style) {
        zoomAnimationStyle = style;
    }

    /**
     * Adds the given ZoomListener to this ZoomManager's list of listeners.
     * 
     * @param listener
     *            the ZoomListener to be added
     */
    @Override
    public void addZoomListener(ZoomListener listener) {
        super.addZoomListener(listener);
        if (listener instanceof AnimatedZoomListener) {
            animationListeners.add(listener);
        }
    }

    /**
     * Notifies listeners that the animated zoom has started.
     */
    protected void fireAnimatedZoomStarted() {
        Iterator<Object> iter = animationListeners.iterator();
        while (iter.hasNext())
            ((AnimatedZoomListener) iter.next()).animatedZoomStarted();
    }

    /**
     * Notifies listeners that the animated zoom has ended.
     */
    protected void fireAnimatedZoomEnded() {
        Iterator<Object> iter = animationListeners.iterator();
        while (iter.hasNext())
            ((AnimatedZoomListener) iter.next()).animatedZoomEnded();
    }

    /**
     * Allows implementators to zoom to a certain level centered around a given
     * point.
     * 
     * @param zoom
     *            <code>double</code> value where 1.0 represents 100%.
     * @param zoomedPoint
     *            <code>Point</code> around which the zoom will be centered in
     *            absolute coordinates
     */
    public void zoomTo(double zoom, Point zoomedPoint) {
        Point zoomedPointCopy = zoomedPoint.getCopy();
        getScalableFigure().translateToRelative(zoomedPointCopy);
        primSetZoom(zoom, zoomedPointCopy);
    }

    /**
     * Allows implementors to zoom into or out to a rectangular area.
     * 
     * @param rect
     *            <code>Rectangle</code> that the edit part will zoom into our
     *            out to in absolute coordinates.
     */
    @Override
    public void zoomTo(Rectangle rect) {
        Dimension available = getViewport().getClientArea().getSize();
        Dimension desired = rect.getSize();

        double scaleX = available.width * getZoom() / desired.width;
        double scaleY = available.height * getZoom() / desired.height;

        double zoom = Math.min(getMaxZoom(), Math.max(getMinZoom(), Math.min(scaleX, scaleY)));
        zoomTo(zoom, rect.getCenter());
    }

    /**
     * Sets the zoom level to the given value. Min-max range check is not done.
     * 
     * @param zoom
     *            the new zoom level
     */
    @Override
    protected void primSetZoom(double zoom) {
        Point center = getViewport().getClientArea().getCenter();
        primSetZoom(zoom, center);
    }

    /**
     * Calculate the animation duration based on the number of zoom increments
     * being traversed.
     * 
     * @param zoom
     * @return <code>AnimationModel</code> that is appropriate for the zoom
     *         difference between requested and the current zoom level.
     */
    private AnimationModel calculateAnimationModel(double zoom) {
        double dmod = Math.pow(zoom / getZoom(), (double) 1 / 8);
        int steps = (int) Math.round(dmod > 1 ? dmod : 1 / dmod);

        int duration = Math.max(DURATION_INCREMENT, steps * DURATION_INCREMENT);
        AnimationModel animationModel = new AnimationModel(duration, true);
        animationModel.animationStarted();
        return animationModel;
    }

    /**
     * Sets the zoom level to the given value. Min-max range check is not done.
     * 
     * @param zoom
     *            the new zoom level
     */
    private void primSetZoom(double zoom, Point finalCenterAbs) {
        primAnimateSetZoom(zoom, finalCenterAbs, getZoomAnimationStyle() == ANIMATE_ZOOM_IN_OUT ? calculateAnimationModel(zoom) : null);
    }

    /**
     * Performs the zoom with animation on the given point. I.e the given point
     * stay at the same position in the viewport after zoom.
     * 
     * @param zoomLevel
     *            the zoom level.
     * @param pointToZoom
     *            The point to zoom.
     * @param animationModel
     *            the animation model to use when doing the zoom.
     */
    private void primAnimateSetZoom(double zoomLevel, Point pointToZoom, AnimationModel animationModel) {

        double initialZoom = getZoom();
        double finalZoom = zoomLevel;

        Point scaledMousePoint = pointToZoom.getCopy();
        Point originalViewLocation = getViewport().getViewLocation();
        Dimension zoomDifference = originalViewLocation.getDifference(pointToZoom);
        Point finalViewLocation = scaledMousePoint.scale(finalZoom / initialZoom).getTranslated(zoomDifference);
        LineSeg scrollVector = new LineSeg(originalViewLocation, finalViewLocation);

        float progress = 1.0f;
        if (animationModel != null) {
            animationModel.animationStarted();
            progress = animationModel.getProgress();
        }
        boolean finished = false;

        fireAnimatedZoomStarted();

        while (!finished) {

            if (animationModel == null || animationModel.isFinished())
                finished = true;

            double currentZoom = initialZoom + ((finalZoom - initialZoom) * progress);

            super.primSetZoom(currentZoom);

            Point currentViewLocation = scrollVector.locatePoint(progress, 0, LineSeg.Sign.POSITIVE);
            setViewLocation(currentViewLocation);

            getViewport().getUpdateManager().performUpdate();

            if (animationModel != null)
                progress = animationModel.getProgress();
        }

        fireAnimatedZoomEnded();
    }
}
