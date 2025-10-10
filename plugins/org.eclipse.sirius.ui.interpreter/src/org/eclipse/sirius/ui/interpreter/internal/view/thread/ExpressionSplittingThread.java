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

import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationContext;
import org.eclipse.sirius.ui.interpreter.internal.language.InterpreterContext;
import org.eclipse.sirius.ui.interpreter.internal.language.SplitExpression;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.swt.widgets.Display;

/**
 * This implementation of a Thread will be used to wrap a splitting task as returned by the LanguageInterpreter, then
 * asynchronously update the form with all error messages (if any) that were raised by this compilation task.
 * Afterwards, this Thread will update the sub-expressions view of the interpreter form.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class ExpressionSplittingThread extends Thread {
    /**
     * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which could
     * happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
     */
    private boolean cancelled;

    /** The splitting thread which result we are to wait for. */
    private Future<SplitExpression> splittingTask;

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
    public ExpressionSplittingThread(InterpreterContext interpreterContext, InterpreterView interpreterView) {
        super("InterpreterExpressionSplittingThread"); //$NON-NLS-1$
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
        if (splittingTask != null) {
            splittingTask.cancel(true);
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
            // Cannot do anything before the current compilation thread stops.
            if (interpreterView.getCompilationThread() != null) {
                interpreterView.getCompilationThread().join();
            }
            checkCancelled();

            Callable<SplitExpression> splitExpressionCallable = interpreterView.getCurrentLanguageInterpreter()
                    .getExpressionSplittingTask(new EvaluationContext(interpreterContext, interpreterView.getCompilationResult()));

            if (splitExpressionCallable == null) {
                return;
            }

            splittingTask = interpreterView.getSplittingPool().submit(splitExpressionCallable);

            final SplitExpression splitExpression = splittingTask.get();
            checkCancelled();

            Display.getDefault().asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    interpreterView.setSubExpressions(splitExpression);
                }
            });
        } catch (InterruptedException e) {
            // Thread is expected to be cancelled if another is started
        } catch (CancellationException e) {
            // Thread is expected to be cancelled if another is started
        } catch (ExecutionException e) {
            Display.getDefault().asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    interpreterView.setSubExpressions(null);
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
