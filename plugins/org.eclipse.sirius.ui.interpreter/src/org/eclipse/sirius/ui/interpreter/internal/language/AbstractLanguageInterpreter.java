/*******************************************************************************
 * Copyright (c) 2010, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.language;

import java.util.concurrent.Callable;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

/**
 * This class describes the necessary contract for a language to be considered usable in the interpreter view.
 * <p>
 * A Language Interpreter will need to be able to provide the necessary tooling for the language edition (completion,
 * syntax highlighting...), a parser that can check for syntax errors and return them for display, and an evaluation
 * engine that can be called on given EObjects for a given expression.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
@SuppressWarnings("unused")
public abstract class AbstractLanguageInterpreter {
    /**
     * If the language interpreter needs to add custom actions to the interpreter form, do it from here.
     * <p>
     * Do note that this will be called each time the interpreter is set. Clients are not expected to dispose of their
     * actions, but are expected to create them each time this is called.
     * </p>
     * 
     * @param interpreterView
     *            The interpreter view
     * @param toolBarManager
     *            The manager for the form tool bar.
     */
    public void addToolBarActions(InterpreterView interpreterView, IToolBarManager toolBarManager) {
        // Do nothing
    }

    /**
     * This will be called by the interpreter view in order to determine whether the "work in editor context" action
     * should be enabled for the given editor.
     * 
     * @param editorPart
     *            The editor part that has just been given focus.
     * @return <code>true</code> if this language interpreter can accept the current editor's context,
     *         <code>false</code> otherwise.
     */
    public boolean canLinkWithEditor(IEditorPart editorPart) {
        return false;
    }

    /**
     * This can be used to configure the source viewer to fit the language needs. Document scanner, document
     * partitioner, source viewer configuration... can be set accordingly.
     * 
     * @param viewer
     *            The viewer that will be displayed to the user for the edition of expressions in this language.
     */
    public void configureSourceViewer(SourceViewer viewer) {
        // Do nothing
    }

    /**
     * If this language interpreter requires a specific form for the "result" viewer, this method can be used to
     * instantiate it.
     * 
     * @param parent
     *            The parent composite for this result viewer.
     * @return The viewer that is to be used to display the result of this language's evaluations. Can be
     *         <code>null</code>, in which case a default {@link org.eclipse.jface.viewers.TreeViewer} will be
     *         instantiated.
     */
    public Viewer createResultViewer(Composite parent) {
        return null;
    }

    /**
     * If editing this language needs a custom implementation of a SourceViewer, this method can be used to instantiate
     * it.
     * 
     * @param parent
     *            The parent composite for this source viewer.
     * @return The source viewer that is to be used for this language. Can be <code>null</code>, in which case a default
     *         {@link SourceViewer} will be instantiated.
     */
    public SourceViewer createSourceViewer(Composite parent) {
        return null;
    }

    /**
     * This will be called when the user has selected a new language from the interpreter view. If this interpreter has
     * registered listeners or keeps references to one of the Viewers it has created, they should be disposed of here.
     */
    public void dispose() {
        // Do nothing
    }

    /**
     * This will be called when the interpreter view needs the current expression to be compiled. It will be called
     * whenever the evaluation is needed : explicit call to the evaluate action, real time evaluation...
     * <p>
     * The returned task must be cancellable; real time compilations might not be run to the end before the user changes
     * the expression. Do note that this task must be thread-safe as it will be called asynchronously and we do not
     * guarantee that each task will be canceled before the subsequent is started.
     * </p>
     * <p>
     * The {@link CompilationResult} object returned by this Callable should hold both the compiled expression (if any)
     * and the problem(s) encountered during the compilation (if any). This(These) problem(s) will be displayed on the
     * interpreter UI.
     * </p>
     * 
     * @param context
     *            The current interpreter context.
     * @return Cancellable task that can be run by the interpreter view to know whether the expression is well-formed.
     *         Can be <code>null</code> if this language cannot (or does not need to) be compiled.
     * @see org.eclipse.sirius.ui.interpreter.internal.language.CompilationResult
     */
    public Callable<CompilationResult> getCompilationTask(InterpreterContext context) {
        return null;
    }

    /**
     * This will be called when the user requests that his expression be evaluated against the currently selected
     * EObjects. It will be called whenever the evaluation is needed : explicit call to the evaluate action, real time
     * evaluation...
     * <p>
     * The returned task must be cancellable; real time evaluations might not be run to the end before the user changes
     * the expression. Do note that this task must be thread-safe as it will be called asynchronously and we do not
     * guarantee that each task will be canceled before the subsequent is started.
     * </p>
     * <p>
     * The {@link EvaluationResult} object returned by this Callable should hold both the actual result of the
     * evaluation (if any) that will be displayed in the "result" part of the interpreter view, and the problem(s)
     * encountered during the evaluation (if any). This(These) problem(s) will be displayed on the interpreter UI.
     * </p>
     * 
     * @param context
     *            The current interpreter context.
     * @return Cancellable task that can be run by the interpreter view to compute the results of a given evaluation.
     *         Cannot be <code>null</code>.
     */
    public abstract Callable<EvaluationResult> getEvaluationTask(EvaluationContext context);

    /**
     * If this language knows how to split its expressions into sub-expressions, this should return a task capable of
     * doing so. Note that this will be executed in parallel to the evaluation task and will not block the results from
     * being displayed. This can thus be a long running task.
     * <p>
     * The returned task must be cancellable. A new evaluation may be launched by the user of by the real-time thread
     * before this task can reach completion. Do note that this task must be thread-safe as it will be called
     * asynchronously and we do not guarantee that each task will be canceled before the subsequent is started.
     * </p>
     * 
     * @param context
     *            The current interpreter context.
     * @return Cancellable task that can be run by the interpreter to split an expression into its sub-components. Can
     *         be <code>null</code> if the language cannot be split.
     */
    public Callable<SplitExpression> getExpressionSplittingTask(EvaluationContext context) {
        return null;
    }

    /**
     * This will be called by the interpreter view when the "work in editor context" action is run by the user.
     * <p>
     * When the toggle get activated, the <em>editorPart</em> passed to this method will be the currently active editor.
     * When the toggle gets deactivated, <em>editorPart</em> will be <code>null</code>.
     * </p>
     * 
     * @param editorPart
     *            The currently active editor if the link toggle was just activated, <code>null</code> if it was just
     *            disabled.
     */
    public void linkWithEditor(IEditorPart editorPart) {
        // Do nothing
    }
}
