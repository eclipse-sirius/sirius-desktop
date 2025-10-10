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

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterPlugin;
import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationContext;
import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationResult;
import org.eclipse.sirius.ui.interpreter.internal.language.InterpreterContext;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.swt.widgets.Display;

/**
 * This implementation of a Thread will be used to wrap an evaluation task as returned by the LanguageInterpreter, then
 * asynchronously update the form with all error messages (if any) that were raised by this compilation task.
 * Afterwards, this Thread will update the result view of the interpreter form.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class EvaluationThread extends Thread {
    /**
     * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which could
     * happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
     */
    private boolean cancelled;

    /** The evaluation thread which result we are to wait for. */
    private Future<EvaluationResult> evaluationTask;

    /** Context of the interpreter as it was when this Thread has been created. */
    private final InterpreterContext interpreterContext;

    private InterpreterView interpreterView;

    /**
     * Instantiates our Thread given the initial interpreter context.
     * 
     * @param interpreterContext
     *            The initial interpreter context.
     * @param interpreterView
     */
    public EvaluationThread(InterpreterContext interpreterContext, InterpreterView interpreterView) {
        super("InterpreterEvaluationThread"); //$NON-NLS-1$
        this.interpreterContext = interpreterContext;
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
        if (evaluationTask != null) {
            evaluationTask.cancel(true);
        }
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
            checkCancelled();
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                    interpreterView.getForm().setBusy(true);
                }
            });
            // Cannot do anything before the current compilation thread stops.
            if (interpreterView.getCompilationThread() != null) {
                interpreterView.getCompilationThread().join();
            }
            checkCancelled();

            Callable<EvaluationResult> evaluationCallable = interpreterView.getCurrentLanguageInterpreter()
                    .getEvaluationTask(new EvaluationContext(interpreterContext, interpreterView.getCompilationResult()));
            evaluationTask = interpreterView.getEvaluationPool().submit(evaluationCallable);

            final EvaluationResult result = evaluationTask.get();
            checkCancelled();

            if (result != null) {
                Display.getDefault().asyncExec(new Runnable() {
                    /**
                     * {@inheritDoc}
                     * 
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        interpreterView.clearEvaluationMessages();
                        if (result.getStatus() != null) {
                            interpreterView.addStatusMessages(result.getStatus(), interpreterView.EVALUATION_MESSAGE_PREFIX);
                        }
                        // whether there were problems or not, try and update the result viewer.
                        interpreterView.setEvaluationResult(result);
                    }
                });
            }
        } catch (InterruptedException e) {
            // Thread is expected to be cancelled if another is started
        } catch (CancellationException e) {
            // Thread is expected to be cancelled if another is started
        } catch (ExecutionException e) {
            String message = e.getMessage();
            if (e.getCause() != null) {
                message = e.getCause().getMessage();
            }
            message = e.getClass().getName() + ' ' + message;
            final IStatus status = new Status(IStatus.ERROR, InterpreterPlugin.PLUGIN_ID, message);
            final EvaluationResult result = new EvaluationResult(status);

            Display.getDefault().asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    interpreterView.clearEvaluationMessages();
                    interpreterView.addStatusMessages(status, interpreterView.EVALUATION_MESSAGE_PREFIX);
                    interpreterView.setEvaluationResult(result);
                }
            });
        } finally {
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                    interpreterView.getForm().setBusy(false);
                }
            });
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
