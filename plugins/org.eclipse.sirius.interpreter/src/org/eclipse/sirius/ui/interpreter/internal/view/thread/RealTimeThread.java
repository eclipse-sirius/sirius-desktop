/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.thread;

import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.swt.widgets.Display;

/**
 * This daemon thread will be launched whenever the "real-time" toggle is activated, and will only be stopped when the
 * view is disposed or the "real-time" toggle is disabled.
 * <p>
 * This Thread will be constantly reset on modifications of the expression viewer, and will only really start its work
 * if the expression is left untouched for a given count of seconds.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class RealTimeThread extends Thread {
    /** Time to wait before launching the evaluation (0.1 second by default). */
    private static final int DELAY = 100;

    /** This will be set to <code>true</code> whenever we need to recompile the expression. */
    private boolean dirty;

    /** The lock we'll acquire for this thread's work. */
    private final Object lock = new Object();

    /** This will be set to <code>true</code> whenever we should reset this thread's timer. */
    private boolean reset;

    private InterpreterView interpreterView;

    /**
     * Instantiates the real-time evaluation thread.
     * 
     * @param interpreterView
     */
    public RealTimeThread(InterpreterView interpreterView) {
        super("InterpreterRealTimeThread"); //$NON-NLS-1$
        this.interpreterView = interpreterView;
        setPriority(Thread.MIN_PRIORITY);
        setDaemon(true);
    }

    /**
     * Resets this thread's timer.
     */
    public void reset() {
        synchronized (this) {
            reset = true;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (lock) {
                try {
                    lock.wait(DELAY);
                } catch (InterruptedException e) {
                    // This is expected
                }
            }

            synchronized (this) {
                if (reset) {
                    reset = false;
                    // If a reset has been asked for, stop this iteration
                    continue;
                }
                if (dirty) {
                    dirty = false;
                } else {
                    // The expression does not need to be recompiled
                    continue;
                }
            }

            Display.getDefault().asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    interpreterView.compileAndEvaluate();
                }
            });
        }
    }

    /**
     * Sets the "dirty" state of this thread to indicate the expression needs to be recompiled.
     */
    public void setDirty() {
        synchronized (this) {
            dirty = true;
        }
    }
}
