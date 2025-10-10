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

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterPlugin;
import org.eclipse.sirius.ui.interpreter.internal.language.CompilationResult;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.swt.widgets.Display;

/**
 * This implementation of a Thread will be used to wrap a compilation task as returned by the LanguageInterpreter, then
 * asynchronously update the form with all error messages (if any) that were raised by this compilation task.
 * Afterwards, this Thread will update the interpreter context with the compilation result.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class CompilationThread extends Thread {
    /**
     * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which could
     * happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
     */
    private boolean cancelled;

    /** The compilation thread which result we are to wait for. */
    private Future<CompilationResult> compilationTask;

    private InterpreterView interpreterView;

    /**
     * Instantiates a compilation thread given the compilation task of which we are to check the result.
     * 
     * @param compilationTask
     *            Thread which result we are to wait for.
     * @param interpreterView
     */
    public CompilationThread(Future<CompilationResult> compilationTask, InterpreterView interpreterView) {
        super("InterpreterCompilationThread"); //$NON-NLS-1$
        this.compilationTask = compilationTask;
        this.interpreterView = interpreterView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread#interrupt()
     */
    @Override
    public void interrupt() {
        cancelled = true;
        compilationTask.cancel(true);
        super.interrupt();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        try {
            final CompilationResult result = compilationTask.get();
            checkCancelled();

            if (result != null && result.getStatus() != null) {
                Display.getDefault().asyncExec(new Runnable() {
                    /**
                     * {@inheritDoc}
                     * 
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        interpreterView.clearCompilationMessages();
                        checkCancelled();
                        interpreterView.addStatusMessages(result.getStatus(), interpreterView.COMPILATION_MESSAGE_PREFIX);
                    }
                });
            }

            // Whether there were problems or not, update the context with this result.
            interpreterView.setCompilationResult(result);
        } catch (InterruptedException e) {
            // Thread is expected to be cancelled if another is started
        } catch (CancellationException e) {
            // Thread is expected to be cancelled if another is started
        } catch (ExecutionException e) {
            checkCancelled();
            String message = e.getMessage();
            if (e.getCause() != null) {
                message = e.getCause().getMessage();
            }
            final IStatus status = new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, message);
            final CompilationResult result = new CompilationResult(status);

            Display.getDefault().asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    interpreterView.clearCompilationMessages();
                    checkCancelled();
                    interpreterView.addStatusMessages(status, interpreterView.COMPILATION_MESSAGE_PREFIX);
                }
            });

            interpreterView.setCompilationResult(result);
        }
    }

    /**
     * Throws a new {@link CancellationException} if the current thread has been cancelled.
     */
    protected void checkCancelled() {
        if (cancelled) {
            throw new CancellationException();
        }
    }
}
