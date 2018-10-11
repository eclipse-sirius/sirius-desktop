/**
 * Copyright (c) 2012, 2017 THALES GLOBAL SERVICES
 * This program and the accompanying materials
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

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;

/**
 * Specific FigureCanvas to:
 * <UL>
 * <LI>override mouseMoveLeftClick method to avoid to pass SWT.BUTTON1 in mouse
 * button down event. Indeed this disable the direct edit because the test
 * getCurrentInput().getModifiers() == 0 fails in SelectEditPartTracker l.168
 * (NoCopyDragEditPartsTrackerEx.performConditionalSelection)</LI>
 * <LI>add typeSuffixText method to allow to edit by adding text at the end of
 * the existing one.</LI>
 * <UL>
 * 
 * @author lredor
 */
public class SWTBotSiriusFigureCanvas extends SWTBotGefFigureCanvas {

    /**
     * Constructs a new instance from a {@link FigureCanvas}.
     * 
     * @param canvas
     *            the canvas to wrap
     * @throws WidgetNotFoundException
     *             if the widget is <code>null</code> or widget has been
     *             disposed.
     */
    public SWTBotSiriusFigureCanvas(FigureCanvas canvas) throws WidgetNotFoundException {
        super(canvas);
    }

    /**
     * Constructs a new instance from a {@link Canvas} and a
     * {@link LightweightSystem}. If the canvas is an instance of
     * {@link FigureCanvas}, use
     * {@link SWTBotGefFigureCanvas#SWTBotGefFigureCanvas(FigureCanvas)}
     * instead.
     * 
     * @param canvas
     *            the canvas to wrap
     * @param lightweightSystem
     *            the lightweight system to use
     * @throws WidgetNotFoundException
     *             if the widget is <code>null</code> or widget has been
     *             disposed.
     */
    public SWTBotSiriusFigureCanvas(Canvas canvas, LightweightSystem lightweightSystem) throws WidgetNotFoundException {
        super(canvas, lightweightSystem);
    }

    /**
     * Duplicate : Only SWT.None is passed instead of SWT.BUTTON1 in mouse down
     * event.
     * 
     * @param xPosition
     *            the relative x position
     * @param yPosition
     *            the relative y position
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas#mouseMoveLeftClick(int,
     *      int)
     */
    @Override
    public void mouseMoveLeftClick(final int xPosition, final int yPosition) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                org.eclipse.swt.events.MouseEvent meMove = wrapMouseEvent(xPosition, yPosition, 0, 0, 0);
                eventDispatcher.dispatchMouseMoved(meMove);
                org.eclipse.swt.events.MouseEvent meDown = wrapMouseEvent(xPosition, yPosition, 1, SWT.None, 1);
                eventDispatcher.dispatchMousePressed(meDown);
                org.eclipse.swt.events.MouseEvent meUp = wrapMouseEvent(xPosition, yPosition, 1, SWT.BUTTON1, 1);
                eventDispatcher.dispatchMouseReleased(meUp);
            }
        });
    }

    /**
     * Contrary to {@link #mouseMoveLeftClick(int, int)}, this method allows to
     * display the feedback during the creation: Useful for edge creation.
     * 
     * @param xPosition
     *            the relative x position
     * @param yPosition
     *            the relative y position
     * @param displayFeedback
     *            true to display feedback, false otherwise.
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas#mouseMoveLeftClick(int,
     *      int)
     */
    public void mouseMoveLeftClick(final int xPosition, final int yPosition, boolean displayFeedback) {
        if (!displayFeedback) {
            mouseMoveLeftClick(xPosition, yPosition);
        } else {
            UIThreadRunnable.asyncExec(new VoidResult() {
                @Override
                public void run() {
                    org.eclipse.swt.events.MouseEvent meMove = wrapMouseEvent(xPosition, yPosition, 0, 0, 0);
                    eventDispatcher.dispatchMouseMoved(meMove);
                    // Force an update of viewport (necessary to have a correct
                    // feedback of edge in case of edge creation for example)
                    if (widget instanceof FigureCanvas) {
                        ((FigureCanvas) widget).getViewport().getUpdateManager().performUpdate();
                    }
                    org.eclipse.swt.events.MouseEvent meDown = wrapMouseEvent(xPosition, yPosition, 1, SWT.None, 1);
                    eventDispatcher.dispatchMousePressed(meDown);
                    org.eclipse.swt.events.MouseEvent meUp = wrapMouseEvent(xPosition, yPosition, 1, SWT.BUTTON1, 1);
                    eventDispatcher.dispatchMouseReleased(meUp);
                }
            });
        }

    }

    private org.eclipse.swt.events.MouseEvent wrapMouseEvent(int x, int y, int button, int stateMask, int count) {
        return new org.eclipse.swt.events.MouseEvent(createMouseEvent(x, y, button, stateMask, count));
    }

    /**
     * Type the given text at the end of the given textControl.
     * 
     * @param textControl
     *            The Text field to modify
     * @param text
     *            The suffix to add
     */
    public void typeSuffixText(final Text textControl, final String text) {
        for (int x = 0; x < text.length(); ++x) {
            final char c = text.charAt(x);
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    textControl.setFocus();
                    textControl.notifyListeners(SWT.KeyDown, keyEvent(SWT.NONE, c, 0));
                    textControl.notifyListeners(SWT.KeyUp, keyEvent(SWT.NONE, c, 0));
                    textControl.setText(textControl.getText() + c);
                }
            });
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }

        // apply the value with a default selection event
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                textControl.setFocus();
                textControl.notifyListeners(SWT.DefaultSelection, createEvent());
            }
        });
    }

    private Event keyEvent(int modificationKey, char c, int keyCode) {
        Event keyEvent = createEvent();
        keyEvent.stateMask = modificationKey;
        keyEvent.character = c;
        keyEvent.keyCode = keyCode;
        return keyEvent;
    }

    /**
     * This method emits mouse events that handle drags within the canvas.
     * Compared to {@link #mouseDrag(int, int, int, int)}, this method also
     * calls an update of the viewport and makes a second calls to drag event.
     * These new events allow to have a feedback drawn for edges and a correct
     * behavior when moving bendpoints of edge.
     * 
     * @param fromXPosition
     *            the relative x position within the canvas to drag from
     * @param fromYPosition
     *            the relative y position within the canvas to drag from
     * @param toXPosition
     *            the relative x position within the canvas to drag to
     * @param toYPosition
     *            the relative y position within the canvas to drag to
     * @param keyCode
     *            the key code of the key that was typed, as defined by the key
     *            code constants in class <code>SWT</code>, or {@link SWT#None}
     *            if no key. @see org.eclipse.swt.SWT
     * @param dragFinished
     *            An AtomicBoolean allows to add a waiting condition. It was set
     *            to true when the drag is finished.<BR>
     *            Warning: When the drag is finished, the associated figures
     *            have not their "moved" bound. Another
     *            {@link org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils#waitAllUiEvents()}
     *            is needed.
     * @deprecated replaced by  {@link SWTBotSiriusFigureCanvas#mouseDragWithKeys(int, int, int, int, AtomicBoolean, int...)}
     */
    @Deprecated 
    public void mouseDragWithKey(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition, final int keyCode, final AtomicBoolean dragFinished) {
        final Result<Boolean> toExecute = new Result<Boolean>() {

            @Override
            public Boolean run() {
                org.eclipse.swt.events.MouseEvent meMove = wrapMouseEvent(fromXPosition, fromYPosition, 0, 0, 0);
                eventDispatcher.dispatchMouseMoved(meMove);
                org.eclipse.swt.events.MouseEvent meDown = wrapMouseEvent(fromXPosition, fromYPosition, 1, SWT.BUTTON1, 1);
                eventDispatcher.dispatchMousePressed(meDown);

                KeyEvent keyEvent = null;
                if (keyCode != SWT.None) {
                    keyEvent = new KeyEvent(createEvent());
                    keyEvent.keyCode = keyCode;
                    keyEvent.doit = true;
                    eventDispatcher.dispatchKeyPressed(keyEvent);
                }

                org.eclipse.swt.events.MouseEvent meMoveTarget = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.BUTTON1, 0);
                eventDispatcher.dispatchMouseMoved(meMoveTarget);

                // Force an update of viewport (necessary to have a correct
                // feedback of edge in case of moving bendpoint of edge for
                // example)
                if (widget instanceof FigureCanvas) {
                    ((FigureCanvas) widget).getViewport().getUpdateManager().performUpdate();
                    // Sent the mouse drag event a second time to consider the
                    // correct feedback in the policy/command
                    eventDispatcher.dispatchMouseMoved(meMoveTarget);
                }

                org.eclipse.swt.events.MouseEvent meUp = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.BUTTON1, 1);
                eventDispatcher.dispatchMouseReleased(meUp);
                if (keyCode != SWT.None) {
                    eventDispatcher.dispatchKeyReleased(keyEvent);
                }
                return true;
            }
        };
        SWTUtils.display().asyncExec(new Runnable() {
            @Override
            public void run() {
                dragFinished.set(toExecute.run());
            }
        });
    }

    /**
     * This method emits mouse events that handle drags within the canvas.
     * Compared to {@link #mouseDrag(int, int, int, int)}, this method also
     * calls an update of the viewport and makes a second calls to drag event.
     * These new events allow to have a feedback drawn for edges and a correct
     * behavior when moving bendpoints of edge.
     * 
     * @param fromXPosition
     *            the relative x position within the canvas to drag from
     * @param fromYPosition
     *            the relative y position within the canvas to drag from
     * @param toXPosition
     *            the relative x position within the canvas to drag to
     * @param toYPosition
     *            the relative y position within the canvas to drag to
     * @param dragFinished
     *            An AtomicBoolean allows to add a waiting condition. It was set
     *            to true when the drag is finished.<BR>
     *            Warning: When the drag is finished, the associated figures
     *            have not their "moved" bound. Another
     *            {@link org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils#waitAllUiEvents()}
     *            is needed.
     * @param keyModifiers
     *            the key codes as defined by the key code constants in class
     *            <code>SWT</code> of the keys that should be pressed when doing
     *            the mouse drag.
     */
    public void mouseDragWithKeys(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition, final AtomicBoolean dragFinished, final int... keyModifiers) {
        // CHECKSTYLE:OFF
        final Result<Boolean> toExecute = new Result<Boolean>() {
            // CHECKSTYLE:ON
            @Override
            public Boolean run() {
                int stateMask = SWT.NONE;
                if (keyModifiers.length > 0) {
                    stateMask = keyModifiers[0];
                    for (int i = 1; i < keyModifiers.length; i++) {
                        stateMask = stateMask | keyModifiers[i];
                    }
                }
                org.eclipse.swt.events.MouseEvent meMove = wrapMouseEvent(fromXPosition, fromYPosition, 0, stateMask, 0);
                eventDispatcher.dispatchMouseMoved(meMove);
                org.eclipse.swt.events.MouseEvent meDown = wrapMouseEvent(fromXPosition, fromYPosition, 1, SWT.BUTTON1 | stateMask, 1);
                eventDispatcher.dispatchMousePressed(meDown);

                org.eclipse.swt.events.MouseEvent meMoveTarget = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.BUTTON1 | stateMask, 0);
                eventDispatcher.dispatchMouseMoved(meMoveTarget);

                // Force an update of viewport (necessary to have a correct
                // feedback of edge in case of moving bendpoint of edge for
                // example)
                if (widget instanceof FigureCanvas) {
                    ((FigureCanvas) widget).getViewport().getUpdateManager().performUpdate();
                    // Sent the mouse drag event a second time to consider the
                    // correct feedback in the policy/command
                    eventDispatcher.dispatchMouseMoved(meMoveTarget);
                }

                org.eclipse.swt.events.MouseEvent meUp = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.BUTTON1, 1);
                eventDispatcher.dispatchMouseReleased(meUp);

                return true;
            }
        };
        SWTUtils.display().asyncExec(new Runnable() {
            @Override
            public void run() {
                dragFinished.set(toExecute.run());
            }
        });
    }

    /**
     * This method applies a zoom by mouse wheel scroll with the given key
     * pressed at the given coordinates.
     * 
     * This method is asynchronous so make sure you wait the finishing of all UI
     * events before testing the effect of this method.
     * 
     * @param xPosition
     *            x absolute position of the mouse from which we do the zoom by
     *            mouse wheel scroll.
     * @param yPosition
     *            y absolute position of the mouse from which we do the zoom by
     *            mouse wheel scroll.
     * @param keyCode
     *            the keyboard key that should be pressed when doing the zoom.
     * @param zoomIncrement
     *            the zoom power from original zoom. A positive value for
     *            zoom-in. A negative value for zoom out.
     */
    public void mouseScrollWithKey(final int xPosition, final int yPosition, final int keyCode, final int zoomIncrement) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {

                KeyEvent keyEvent = null;
                if (keyCode != SWT.None) {
                    keyEvent = new KeyEvent(createEvent());
                    keyEvent.keyCode = keyCode;
                    keyEvent.doit = true;
                    eventDispatcher.dispatchKeyPressed(keyEvent);
                }

                final Event event = new Event();
                event.type = SWT.MouseWheel;
                event.keyCode = SWT.MouseHorizontalWheel;
                event.detail = SWT.SCROLL_LINE;
                event.stateMask = SWT.CTRL;
                event.x = xPosition;
                event.y = yPosition;
                event.count = zoomIncrement;

                eventDispatcher.dispatchMouseWheelScrolled(event);

                if (keyCode != SWT.None) {
                    eventDispatcher.dispatchKeyReleased(keyEvent);
                }
            }
        });
    }
}
