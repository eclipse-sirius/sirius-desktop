/**
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.utils.dnd;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.jface.util.Geometry;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A dnd utility class to help performing drag and drop. This code will be in
 * SWTbot in future but need to be polished before.
 * 
 * @author mchauvin
 */
public class DndUtil {

    /**
     * Number of pixels traversed before a drag starts. Ms : 10 OSX : 10(1.3.1),
     * 5(1.4.1) Linux/X11: delay+16
     */
    private static final int DRAG_THRESHOLD = "gtk".equals(SWT.getPlatform()) ? 16 : 10;

    /**
     * delay before moving after mouse is pressed. could be used for delay after
     * moving before mouse is released
     */
    private static final int DRAG_DELAY = 400;

    private final Display display;

    /**
     * Construct a new instance.
     * 
     * @param display
     *            the display to use
     */
    public DndUtil(Display display) {
        this.display = display;
    }

    /**
     * Performs a drag and drop operation from this widget to the given target.
     * The drag start location will be chosen depending on this widget's default
     * implementation.
     * 
     * @param source
     *            the source widget to drag
     * @param target
     *            To perform the drop on
     */
    public void dragAndDrop(final AbstractSWTBot<? extends Widget> source, final AbstractSWTBot<? extends Widget> target) {
        dragAndDrop(source, target, DndUtil.on(target));
    }

    /**
     * Performs a drag and drop operation from this widget to the given target
     * at the given location from target origin. The drag start location will be
     * chosen depending on this widget's default implementation.
     * 
     * @param source
     *            the source widget to drag
     * @param target
     *            To perform the drop on
     * @param locationOnTarget
     *            The target locations, from target origin, where the DND shall
     *            finish.
     */
    public void dragAndDrop(final AbstractSWTBot<? extends Widget> source, final AbstractSWTBot<? extends Widget> target, final Point locationOnTarget) {
        Rectangle targetRectangle = DndUtil.absoluteLocation(target);
        Point dropTarget = new Point(targetRectangle.x + locationOnTarget.x, targetRectangle.y + locationOnTarget.y);
        doDragAndDrop(source, dropTarget);
    }

    /**
     * Performs a drag and drop operation from this widget to the given target
     * at the given location from target origin. The drag start location will be
     * chosen depending on this widget's default implementation.
     * 
     * @param source
     *            the source widget to drag
     * @param target
     *            To perform the drop on
     * @param locationOnTarget
     *            The target locations, from target origin, where the DND shall
     *            finish.
     */
    public void dragAndDrop(final AbstractSWTBot<? extends Widget> source, final AbstractSWTBot<? extends Widget> target, final org.eclipse.draw2d.geometry.Point locationOnTarget) {
        dragAndDrop(source, target, new Point(locationOnTarget.x, locationOnTarget.y));
    }

    /**
     * Performs a DND operation to an arbitrary location. The drag start
     * location will be chosen depending on this widget's default
     * implementation.
     * 
     * @param source
     *            the source widget to drag
     * @param target
     *            The target locations where the DND shall finish.
     * @see #before(AbstractSWTBot)
     * @see #on(AbstractSWTBot)
     * @see #after(AbstractSWTBot)
     */
    public void dragAndDrop(final AbstractSWTBot<? extends Widget> source, final Point target) {
        doDragAndDrop(source, target);
    }

    private void doDragAndDrop(final AbstractSWTBot<? extends Widget> source, final Point dest) {
        // log.debug(MessageFormat.format("Drag-and-dropping from ({0},{1}) to
        // ({2},{3})",
        // source.x, source.y, dest.x, dest.y));
        final Rectangle sourceLocation = DndUtil.absoluteLocation(source);
        final Point slightOffset = Geometry.add(Geometry.getLocation(sourceLocation), new Point(DndUtil.DRAG_THRESHOLD, DndUtil.DRAG_THRESHOLD));
        final Point sourcePoint = Geometry.min(Geometry.centerPoint(sourceLocation), slightOffset);
        try {
            final Robot awtRobot = new Robot();
            // the x+10 motion is needed to let native functions register a drag
            // detect. It did not work under Windows
            // otherwise and has been reported to be required for linux, too.
            // But I could not test that.
            syncExec(new VoidResult() {
                @Override
                public void run() {
                    awtRobot.mouseMove(sourcePoint.x, sourcePoint.y);
                    awtRobot.mousePress(InputEvent.BUTTON1_MASK);
                    awtRobot.mouseMove(sourcePoint.x + DndUtil.DRAG_THRESHOLD, sourcePoint.y);
                }
            });
            SWTBotWrapper botWrapper = new SWTBotWrapper(source);
            botWrapper.notifyDragDetect();

            /* drag delay */
            SWTUtils.sleep(DndUtil.DRAG_DELAY);

            syncExec(new VoidResult() {
                @Override
                public void run() {
                    awtRobot.mouseMove(dest.x + DndUtil.DRAG_THRESHOLD, dest.y);
                    awtRobot.mouseMove(dest.x, dest.y);
                }
            });

            /* drop delay */
            SWTUtils.sleep(DndUtil.DRAG_DELAY);

            syncExec(new VoidResult() {
                @Override
                public void run() {
                    awtRobot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
            });
        } catch (final AWTException e) {
            // log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Invokes {@link VoidResult#run()} on the UI thread.
     * 
     * @param toExecute
     *            the object to be invoked in the UI thread.
     */
    private void syncExec(VoidResult toExecute) {
        UIThreadRunnable.syncExec(display, toExecute);
    }

    /**
     * Calculates a position which can be used to insert an item <em>before</em>
     * the given one by a DND operation.
     * 
     * @param targetItem
     *            Before which the new item shall appear
     * @param <T>
     *            .
     * @return A point suitable to drop an item before {@code targetItem}
     */
    public static <T extends Widget> Point before(final AbstractSWTBot<T> targetItem) {
        return DndUtil.pointOnUpperBorder(DndUtil.absoluteLocation(targetItem));
    }

    /**
     * Calculates a position which can be used to drop something <em>onto</em>
     * the given widget by a DND operation. For tree structures, this will most
     * likely result in another child node being added. But how this is handled
     * in detail ultimately depends on the "drop action"'s implementation.
     * 
     * @param targetItem
     *            On which to drop
     * @param <T>
     *            .
     * @return The {@code targetItem}'s center
     */
    public static <T extends Widget> Point on(final AbstractSWTBot<T> targetItem) {
        return Geometry.centerPoint(DndUtil.absoluteLocation(targetItem));
    }

    /**
     * Calculates a position which can be used to insert an item after the given
     * one by a DND operation.
     * 
     * @param targetItem
     *            After which the new item shall appear
     * @param <T>
     *            .
     * @return A point suitable to drop an item after {@code targetItem}
     */
    public static <T extends Widget> Point after(final AbstractSWTBot<T> targetItem) {
        return DndUtil.pointOnLowerBorder(DndUtil.absoluteLocation(targetItem));
    }

    private static Point pointOnLowerBorder(Rectangle rect) {
        return new Point(Geometry.centerPoint(rect).x, rect.y + rect.height - 1);
    }

    private static Point pointOnUpperBorder(Rectangle rect) {
        return new Point(Geometry.centerPoint(rect).x, rect.y + 1);
    }

    private static <T extends Widget> Rectangle absoluteLocation(final AbstractSWTBot<T> item) {
        AbstractSWTBot<?> bot = null;
        if (item instanceof SWTBotTreeItem) {
            bot = new SWTBotTreeItemForDnd(((SWTBotTreeItem) item).widget);
        } else if (item instanceof SWTBotGefFigureCanvas) {
            bot = new SWTBotGefFigureCanvasForDnd((FigureCanvas) ((SWTBotGefFigureCanvas) item).widget);
        } else {
            bot = item;
        }
        Object result = null;
        try {
            Method m = AbstractSWTBot.class.getDeclaredMethod("absoluteLocation");
            m.setAccessible(true);
            result = m.invoke(bot);
        } catch (SecurityException e) {
            // do nothing
        } catch (NoSuchMethodException e) {
            // do nothing
        } catch (IllegalArgumentException e) {
            // do nothing
        } catch (IllegalAccessException e) {
            // do nothing
        } catch (InvocationTargetException e) {
            // do nothing
        }
        return (Rectangle) result;
    }

    /**
     * Subclass to return the correct absolute location.
     * 
     * @author mchauvin
     */
    private static class SWTBotTreeItemForDnd extends SWTBotTreeItem {

        SWTBotTreeItemForDnd(TreeItem treeItem) throws WidgetNotFoundException {
            super(treeItem);
        }

        @Override
        protected Rectangle absoluteLocation() {
            return UIThreadRunnable.syncExec(new Result<Rectangle>() {
                @Override
                public Rectangle run() {
                    return display.map(widget.getParent(), null, widget.getBounds());
                }
            });
        }

    }

    /**
     * Subclass to return the correct absolute location.
     * 
     * @author mchauvin
     */
    private static class SWTBotGefFigureCanvasForDnd extends SWTBotGefFigureCanvas {

        SWTBotGefFigureCanvasForDnd(FigureCanvas canvas) throws WidgetNotFoundException {
            super(canvas);
        }

        @Override
        protected Rectangle absoluteLocation() {
            return UIThreadRunnable.syncExec(new Result<Rectangle>() {
                @Override
                public Rectangle run() {
                    return display.map(widget.getParent(), null, widget.getBounds());
                }
            });
        }

    }

    /**
     * Check if Xvnc exists on the current environment.<BR>
     * Copied from
     * {@link org.eclipse.swtbot.swt.finder.widgets.DnDTreeTest#isUsingXvnc()}
     * 
     * @return true if Xvnc exists on the current environment, false otherwise.
     */
    public static boolean isUsingXvnc() {
        String os = System.getProperty("os.name").toLowerCase();
        if (!(os.indexOf("nix") < 0 && os.indexOf("nux") < 0 && os.indexOf("aix") < 0)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            String xdisplay = System.getenv("DISPLAY"); //$NON-NLS-1$
            // command is like pgrep -l -f "Xvnc.*:0"
            StringBuilder commandBuilder = new StringBuilder();
            commandBuilder.append("/usr/bin/pgrep -l -f "); //$NON-NLS-1$
            commandBuilder.append("Xvnc.*"); //$NON-NLS-1$
            commandBuilder.append(xdisplay);
            Process proc;
            try {
                proc = Runtime.getRuntime().exec(commandBuilder.toString());
                proc.waitFor();
                // If pgrep found something, it will return 0;
                return proc.exitValue() == 0;
            } catch (IOException e) {
                // Do nothing
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
        return false;
    }

    /**
     * An AbstractSWTBot wrapper to make public some protected fields.
     * 
     * @author fbarbin
     *
     */
    private class SWTBotWrapper extends AbstractSWTBot<Widget> {
        AbstractSWTBot<? extends Widget> wrappedSWTBot;
        SWTBotWrapper(AbstractSWTBot<? extends Widget> w) throws WidgetNotFoundException {
            super(w.widget);
            wrappedSWTBot = w;
        }

        /**
         * From org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot.notifyDragDetect(Event).
         */
        public void notifyDragDetect() {
            Event dragDetectEvent = createMouseEvent(1, SWT.NONE, 0);
            // Don't send SWT.DragDetect to the DragSource listener,
            // otherwise the cursor gets stuck in drag mode
            final Control control = getDNDControl();
            final Listener dragSourceListener = syncExec(new Result<Listener>() {
                @Override
                public Listener run() {
                    // The DragSource listener is an anonymous class of DragSource
                    for (Listener listener : control.getListeners(SWT.DragDetect)) {
                        if (DragSource.class.equals(listener.getClass().getEnclosingClass())) {
                            return listener;
                        }
                    }
                    return null;
                }
            });
            try {
                if (dragSourceListener != null) {
                    syncExec(new VoidResult() {
                        @Override
                        public void run() {
                            control.removeListener(SWT.DragDetect, dragSourceListener);
                        }
                    });
                }
                notify(SWT.DragDetect, dragDetectEvent, control);
            } finally {
                if (dragSourceListener != null) {
                    syncExec(new VoidResult() {
                        @Override
                        public void run() {
                            control.addListener(SWT.DragDetect, dragSourceListener);
                        }
                    });
                }
            }
        }

        @Override
        protected Control getDNDControl() {
            return (Control) ReflectionHelper.invokeMethodWithoutExceptionWithReturn(wrappedSWTBot, AbstractSWTBot.class, "getDNDControl", new Class[0], new Object[0], true);
        }

        @Override
        protected Rectangle getBounds() {
            return (Rectangle) ReflectionHelper.invokeMethodWithoutExceptionWithReturn(wrappedSWTBot, AbstractSWTBot.class, "getBounds", new Class[0], new Object[0], true);
        }

    }

}
