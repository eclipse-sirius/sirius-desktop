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
package org.eclipse.acceleo.ui.interpreter.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.acceleo.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterImages;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.internal.SWTUtil;
import org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.FormMessageManagerFactory;
import org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager;
import org.eclipse.acceleo.ui.interpreter.internal.optional.InterpreterDependencyChecks;
import org.eclipse.acceleo.ui.interpreter.internal.optional.debug.DebugViewHelper;
import org.eclipse.acceleo.ui.interpreter.internal.view.GeneratedTextDialog;
import org.eclipse.acceleo.ui.interpreter.internal.view.InterpreterFileStorage;
import org.eclipse.acceleo.ui.interpreter.internal.view.ResultDragListener;
import org.eclipse.acceleo.ui.interpreter.internal.view.StorageEditorInput;
import org.eclipse.acceleo.ui.interpreter.internal.view.VariableContentProvider;
import org.eclipse.acceleo.ui.interpreter.internal.view.VariableDropListener;
import org.eclipse.acceleo.ui.interpreter.internal.view.VariableLabelProvider;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ClearExpressionViewerAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ClearResultViewerAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ClearVariableViewerAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.DeleteVariableOrValueAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.EvaluateAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.LexicalSortAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.LinkWithEditorContextAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.NewVariableAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.NewVariableWizardAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.RenameVariableAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ToggleRealTimeAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ToggleStepByStepVisibilityAction;
import org.eclipse.acceleo.ui.interpreter.internal.view.actions.ToggleVariableVisibilityAction;
import org.eclipse.acceleo.ui.interpreter.language.AbstractLanguageInterpreter;
import org.eclipse.acceleo.ui.interpreter.language.CompilationResult;
import org.eclipse.acceleo.ui.interpreter.language.EvaluationContext;
import org.eclipse.acceleo.ui.interpreter.language.EvaluationResult;
import org.eclipse.acceleo.ui.interpreter.language.IInterpreterSourceViewer;
import org.eclipse.acceleo.ui.interpreter.language.InterpreterContext;
import org.eclipse.acceleo.ui.interpreter.language.SplitExpression;
import org.eclipse.acceleo.ui.interpreter.language.SubExpression;
import org.eclipse.acceleo.ui.interpreter.view.providers.ResultContentProvider;
import org.eclipse.acceleo.ui.interpreter.view.providers.ResultLabelProvider;
import org.eclipse.acceleo.ui.interpreter.view.providers.StepByStepContentProvider;
import org.eclipse.acceleo.ui.interpreter.view.providers.StepLabelProvider;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.acceleo.interpreter.InterpreterPlugin;
import org.eclipse.sirius.common.acceleo.interpreter.SiriusInterpreter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * The Actual "Interpreter" view that will be displayed in the Eclipse workbench.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class InterpreterView extends ViewPart implements ISelectionProvider {
    /** Prefix of the messages corresponding to compilation problems. */
    protected static final String COMPILATION_MESSAGE_PREFIX = "compilation.message"; //$NON-NLS-1$

    /** Prefix of the messages corresponding to evaluation problems. */
    protected static final String EVALUATION_MESSAGE_PREFIX = "evaluation.message"; //$NON-NLS-1$

    /**
     * This will be used as the key for the "information" message that the interpreter will display for compilation "OK"
     * status. There will only be one.
     */
    private static final String COMPILATION_INFO_MESSAGE_KEY = "interpreter.compilation.info.message"; //$NON-NLS-1$

    /**
     * This will be used as the key for the "information" message that the interpreter will display for evaluation "OK"
     * status. There will only be one.
     */
    private static final String EVALUATION_INFO_MESSAGE_KEY = "interpreter.evaluation.info.message"; //$NON-NLS-1$

    /** ID of the interpreter view context. Must be kept in sync with the plugin.xml declaration. */
    private static final String INTERPRETER_VIEW_CONTEXT_ID = "org.eclipse.acceleo.ui.interpreter.interpreterview"; //$NON-NLS-1$

    /** ID of the toolbar's group in which we'll add language specific actions. */
    private static final String LANGUAGE_SPECIFIC_ACTION_GROUP = "LanguageSpecificActions"; //$NON-NLS-1$

    /** Key for the expression as stored in this view's memento. */
    private static final String MEMENTO_EXPRESSION_KEY = "org.eclipse.acceleo.ui.interpreter.memento.expression"; //$NON-NLS-1$

    /** Key for the real-time compilation state as stored in this view's memento. */
    private static final String MEMENTO_REAL_TIME_KEY = "org.eclipse.acceleo.ui.interpreter.memento.realtime"; //$NON-NLS-1$

    /** Key for the hidden state of the variable viewer as stored in this view's memento. */
    private static final String MEMENTO_VARIABLES_VISIBLE_KEY = "org.eclipse.acceleo.ui.interpreter.memento.variables.hide"; //$NON-NLS-1$

    /** Key for the hidden state of the Sub-Expressions viewer as stored in this view's memento. */
    private static final String MEMENTO_SUB_EXPRESSIONS_VISIBLE_KEY = "org.eclipse.acceleo.ui.interpreter.memento.subexpressions.hide"; //$NON-NLS-1$

    /** Key for the sorted state of the result viewer as stored in this view's memento. */
    private static final String MEMENTO_RESULT_SORTED_KEY = "org.eclipse.acceleo.ui.interpreter.memento.result.sorted"; //$NON-NLS-1$

    /** Key for the sorted state of the Sub-Expressions viewer as stored in this view's memento. */
    private static final String MEMENTO_SUB_EXPRESSIONS_SORTED_KEY = "org.eclipse.acceleo.ui.interpreter.memento.subexpressions.sorted"; //$NON-NLS-1$

    /** We'll use this as the id of our viewers' menus. */
    private static final String MENU_ID = "#PopupMenu"; //$NON-NLS-1$

    /**
     * Id for command "Redo" in category "Edit". This should be directly referenced from
     * org.eclipse.ui.IWorkbenchCommandConstants.EDIT_REDO ... though that would break our Eclipse 3.4 compatibility.
     */
    private static final String WORKBENCH_CONSTANT_EDIT_REDO = "org.eclipse.ui.edit.redo"; //$NON-NLS-1$

    /**
     * Id for command "Undo" in category "Edit". This should be directly referenced from
     * org.eclipse.ui.IWorkbenchCommandConstants.EDIT_UNDO ... though that would break our Eclipse 3.4 compatibility.
     */
    private static final String WORKBENCH_CONSTANT_EDIT_UNDO = "org.eclipse.ui.edit.undo"; //$NON-NLS-1$

    /** This will be added to the result view for OclVoid instances. */
    private static final String NULL_RESULT_OBJECT = "null"; //$NON-NLS-1$

    /**
     * If we have a compilation result, this will contain it (note that some language are not compiled, thus an
     * evaluation task can legally be created while this is <code>null</code>.
     */
    protected CompilationResult compilationResult;

    /** Thread which purpose is to compile the expression and update the context with the result. */
    protected CompilationThread compilationThread;

    /** Keeps a reference to the "link with editor" action. */
    protected LinkWithEditorContextAction linkWithEditorContextAction;

    /** This executor service will be used in order to launch the evaluation tasks of this interpreter. */
    /* package */ExecutorService evaluationPool = Executors.newSingleThreadExecutor();

    /** This executor service will be used in order to launch the splitting tasks of this interpreter. */
    /* package */ExecutorService splittingPool = Executors.newSingleThreadExecutor();

    /** This will hold the real-time evaluation thread. */
    /* package */RealTimeThread realTimeThread;

    /** Listener reacting to activations of this view. */
    private IPartListener activationListener;

    /** Content assist activation token. This will be needed to deactivate our handler. */
    private IHandlerActivation activationTokenContentAssist;

    /** Redo activation token. This will be needed to deactivate our handler. */
    private IHandlerActivation activationTokenRedo;

    /** Undo action activation token. This will be needed to deactivate our handler. */
    private IHandlerActivation activationTokenUndo;

    /** This executor service will be used in order to launch the compilation tasks of this interpreter. */
    private ExecutorService compilationPool = Executors.newSingleThreadExecutor();

    /** Context activation token. This will be needed to deactivate it. */
    private IContextActivation contextActivationToken;

    /** Currently selected language interpreter. */
    private AbstractLanguageInterpreter currentLanguageInterpreter;

    /** This will be used to listen to editor focus changes in the workbench. */
    private IPartListener2 editorPartListener = new InterpreterEditorPartListener();

    /** This will be used to listen to EObject selection within the workbench. */
    private ISelectionListener eobjectSelectionListener;

    /** Thread which purpose is to evaluate the expression and update the view with the result. */
    private EvaluationThread evaluationThread;

    /** Thread which purpose is to split the expression and update the view with the sub-expressions tree. */
    private ExpressionSplittingThread expressionSplittingThread;

    /**
     * Keeps a reference to the "expression" section of the interpreter form. This will be used to re-create the result
     * viewer when changing language.
     */
    private Section expressionSection;

    /**
     * This source viewer will be used in order to display the "expression" area in which the user can type the
     * expression that is to be evaluated.
     */
    private SourceViewer expressionViewer;

    /** We'll create this {@link SashForm} as the main body of the interpreter form. */
    private SashForm formBody;

    /** We'll create this {@link SashForm} as the sub-expression and result body of the interpreter form. */
    private SashForm bottomLeftColumn;

    /** The composite that will display the sub-expressions. */
    private Composite subExpressionComposite;

    /**
     * Keeps a reference to the toolkit used to create our form. This will be used when switching languages.
     */
    private FormToolkit formToolkit;

    /** Form that will contain the interpreter itself (left part of the view). */
    private Form interpreterForm;

    /** Kept as an instance member, this will allow us to set unique identifiers to the status messages. */
    private int messageCount;

    /**
     * We'll use this indirection layer for the form's message manager in order to bypass the API breakage for Ganymede.
     */
    private IFormMessageManager messageManager;

    /** Memento from which to restore this view's state. */
    private IMemento partMemento;

    /** This indicates whether we should be compiling the expression in real-time. */
    private boolean realTime;

    /**
     * Keeps a reference to the "result" section of the interpreter form. This will be used to re-create the result
     * viewer when changing language.
     */
    private Section resultSection;

    /** Viewer in which we'll display the result of the evaluations. */
    private Viewer resultViewer;

    /** Viewer in which we'll display sub-expressions if the current language has a splitter. */
    private TreeViewer subExpressionViewer;

    /**
     * This will hold the current selection of EObjects (in the workspace).
     * 
     * @deprecated use {@link #selectedNotifiers} instead.
     */
    @Deprecated
    private List<EObject> selectedEObjects;

    /** This will hold the current selection of ENotifiers (in the workspace). */
    private List<Notifier> selectedNotifiers;

    /** The "right column" composite of the interpreter form, displaying the variables when not hidden. */
    private Composite variableColumn;

    /** Viewer in which we'll display the accessible variables. */
    private TreeViewer variableViewer;

    /** Indicates whether the variable viewer is visible. */
    private boolean variableVisible;

    /** Indicates whether the step-by-step viewer is visible. */
    private boolean subExpressionsVisible;

    /**
     * Creates a tool bar for the given section.
     * 
     * @param section
     *            The section for which we need a tool bar.
     * @return The created tool bar.
     */
    protected static final ToolBarManager createSectionToolBar(Section section) {
        final ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
        final ToolBar toolBar = toolBarManager.createControl(section);

        final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
        toolBar.setCursor(handCursor);
        // Cursor needs to be explicitly disposed
        toolBar.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                if (!handCursor.isDisposed()) {
                    handCursor.dispose();
                }
            }
        });

        section.setTextClient(toolBar);
        toolBar.setData(toolBarManager);
        toolBar.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                toolBar.setData(null);
            }
        });

        return toolBarManager;
    }

    /**
     * Returns the toolbar of the given section if any.
     * 
     * @param section
     *            The section of which we need the toolbar.
     * @return The toolbar of the given section if any.
     */
    protected static final ToolBarManager getSectionToolBar(Section section) {
        Control textClient = section.getTextClient();
        if (textClient instanceof ToolBar) {
            ToolBar toolBar = (ToolBar) textClient;
            Object data = toolBar.getData();
            if (data instanceof ToolBarManager) {
                return (ToolBarManager) data;
            }
        }
        return null;
    }

    /**
     * Opens the "add variable" wizard in order to create a new variable with the given value.
     * 
     * @param variableValue
     *            The variable value
     */
    public void addVariables(Object variableValue) {
        NewVariableAction action = new NewVariableAction(variableViewer, variableValue);
        action.run();
    }

    /**
     * Asks for the compilation of the current expression.
     * <p>
     * This will take a snapshot of the current interpreter context and launch a new thread for the compilation. This
     * thread can and will be cancelled whenever a new compilation is required.
     * </p>
     */
    private void compileExpression() {
        final InterpreterContext context = getInterpreterContext();
        final Callable<CompilationResult> compilationTask = getCurrentLanguageInterpreter().getCompilationTask(context);

        clearCompilationMessages();

        if (compilationTask != null) {
            Future<CompilationResult> compilationFuture = compilationPool.submit(compilationTask);
            compilationThread = new CompilationThread(compilationFuture);
            compilationThread.start();
        }
    }

    /**
     * This evaluates the selected sub-step.
     * 
     * @param expression
     *            The expression to evaluate.
     */
    protected void evaluateSubExpression(final Object expression) {
        if (this.expressionViewer == null || this.expressionViewer.getTextWidget() == null || this.expressionViewer.getTextWidget().isDisposed()) {
            return;
        }

        // Cancel previous compilation task
        if (compilationThread != null && !compilationThread.isInterrupted()) {
            compilationThread.interrupt();
        }
        // Cancel running evaluation task
        if (evaluationThread != null && !evaluationThread.isInterrupted()) {
            evaluationThread.interrupt();
        }

        clearCompilationMessages();
        Future<CompilationResult> compilationFuture = compilationPool.submit(new Callable<CompilationResult>() {
            public CompilationResult call() throws Exception {
                return new CompilationResult(expression);
            }
        });
        compilationThread = new CompilationThread(compilationFuture);
        compilationThread.start();

        InterpreterContext interpreterContext = getInterpreterContext();
        if (interpreterContext != null) {
            evaluationThread = new EvaluationThread(interpreterContext);
            evaluationThread.start();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        Composite rootContainer = new SashForm(parent, SWT.HORIZONTAL);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 0;
        rootContainer.setLayout(layout);

        formToolkit = new FormToolkit(rootContainer.getDisplay());

        createInterpreterForm(formToolkit, rootContainer);

        // The view's state has been restored on-the-fly. We can now discard the memento.
        partMemento = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        if (this.compilationThread != null && !this.compilationThread.isInterrupted()) {
            this.compilationThread.interrupt();
        }
        if (this.evaluationThread != null && !this.evaluationThread.isInterrupted()) {
            this.evaluationThread.interrupt();
        }

        if (contextActivationToken != null) {
            IContextService contextService = (IContextService) getSite().getService(IContextService.class);
            contextService.deactivateContext(contextActivationToken);
        }

        IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
        if (activationTokenContentAssist != null) {
            handlerService.deactivateHandler(activationTokenContentAssist);
        }
        if (activationTokenRedo != null) {
            handlerService.deactivateHandler(activationTokenRedo);
        }
        if (activationTokenUndo != null) {
            handlerService.deactivateHandler(activationTokenUndo);
        }

        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workbenchWindow != null && workbenchWindow.getActivePage() != null && eobjectSelectionListener != null) {
            workbenchWindow.getActivePage().removeSelectionListener(eobjectSelectionListener);
        }
        clearSelection();

        IWorkbenchPage currentPage = getSite().getPage();
        if (currentPage != null) {
            currentPage.removePartListener(editorPartListener);
        }

        getSite().getPage().removePartListener(activationListener);

        super.dispose();
    }

    /**
     * Compiles the current expression and launches its evaluation.
     */
    public void compileAndEvaluate() {
        boolean cancelRequest = false;
        if (this.expressionViewer == null || this.expressionViewer.getTextWidget() == null || this.expressionViewer.getTextWidget().isDisposed()) {
            cancelRequest = true;
        } else {
            final String text = this.expressionViewer.getTextWidget().getText();
            if (text == null || text.length() == 0) {
                cancelRequest = true;
            }
        }
        if (cancelRequest) {
            return;
        }

        // Cancel previously running threads
        if (compilationThread != null && !compilationThread.isInterrupted()) {
            compilationThread.interrupt();
        }
        if (evaluationThread != null && !evaluationThread.isInterrupted()) {
            evaluationThread.interrupt();
        }
        if (expressionSplittingThread != null && !expressionSplittingThread.isInterrupted()) {
            expressionSplittingThread.interrupt();
        }

        compileExpression();
        evaluate();
        splitExpression();
    }

    /**
     * Evaluates the currently entered expression with the current context.
     */
    private void evaluate() {
        clearEvaluationMessages();

        InterpreterContext interpreterContext = getInterpreterContext();
        if (interpreterContext != null) {
            evaluationThread = new EvaluationThread(interpreterContext);
            evaluationThread.start();
        }
    }

    /**
     * This split the expressions into sub-steps.
     */
    private void splitExpression() {
        // populates the sub-expressions tree viewer
        InterpreterContext interpreterContext = getInterpreterContext();
        if (interpreterContext != null) {
            expressionSplittingThread = new ExpressionSplittingThread(interpreterContext);
            expressionSplittingThread.start();
        }
    }

    /**
     * This sets sub expressions in the expected viewer.
     * 
     * @param splitExpression
     *            The split expression.
     */
    protected final void setSubExpressions(SplitExpression splitExpression) {
        TreeViewer viewer = subExpressionViewer;
        if (splitExpression != null) {
            viewer.setInput(Collections.singleton(splitExpression));
        } else {
            viewer.setInput(null);
        }
    }

    /**
     * Returns the currently selected language.
     * 
     * @return The currently selected language.
     */
    public final AbstractLanguageInterpreter getCurrentLanguageInterpreter() {
        if (currentLanguageInterpreter == null) {
            currentLanguageInterpreter = new SiriusInterpreter();
        }
        return currentLanguageInterpreter;
    }

    /**
     * Returns the current interpreter context. This will mainly be used by the compilation tasks of the language
     * interpreters.
     * 
     * @return The current interpreter context.
     */
    @SuppressWarnings("unchecked")
    public InterpreterContext getInterpreterContext() {
        if (expressionViewer == null || expressionViewer.getTextWidget() == null) {
            return null;
        }

        String fullExpression = expressionViewer.getTextWidget().getText();

        List<Notifier> targetNotifiers = selectedNotifiers;
        if (targetNotifiers == null) {
            targetNotifiers = Collections.emptyList();
        }

        final List<Variable> variables;
        Object variableViewerInput = variableViewer.getInput();
        if (variableViewerInput instanceof List<?>) {
            variables = new ArrayList<Variable>((List<Variable>) variableViewerInput);
        } else {
            variables = Collections.emptyList();
        }

        ISelection selection = expressionViewer.getSelection();
        if (selection == null || (selection instanceof ITextSelection && ((ITextSelection) selection).getLength() == 0)) {
            selection = new TextSelection(expressionViewer.getDocument(), 0, fullExpression.length());
        }

        // Is the "debug" view currently active and showing an Acceleo thread?
        if (InterpreterDependencyChecks.isDebugAccessible()) {
            List<Variable> debugVariables = DebugViewHelper.getCurrentDebugThreadVariables();
            for (Variable var : debugVariables) {
                boolean duplicate = false;
                for (int i = 0; i < variables.size() && !duplicate; i++) {
                    duplicate = variables.get(i).getName().equals(var.getName());
                }
                if (!duplicate) {
                    variables.addAll(debugVariables);
                }
            }
        }

        return new InterpreterContext(fullExpression, selection, targetNotifiers, variables);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite, org.eclipse.ui.IMemento)
     */
    @Override
    public void init(IViewSite site, IMemento memento) throws PartInitException {
        super.init(site, memento);
        this.partMemento = memento;

        IContextService contextService = (IContextService) site.getService(IContextService.class);
        contextActivationToken = contextService.activateContext(INTERPRETER_VIEW_CONTEXT_ID);

        eobjectSelectionListener = new NotifierSelectionListener(this);
        activationListener = new ActivationListener(this);
        site.getPage().addPartListener(activationListener);
        site.getPage().addSelectionListener(eobjectSelectionListener);

        if (site.getPart() != null && site.getPage().getSelection() != null) {
            eobjectSelectionListener.selectionChanged(site.getPart(), site.getPage().getSelection());
        }
    }

    /**
     * Indicates if the variables are visible in the view.
     * 
     * @return <code>true</code> if the variables are visible, <code>false</code> otherwise.
     */
    public boolean isVariableVisible() {
        return variableVisible;
    }

    /** Link the current language interpreter with the current editor. */
    public void linkWithEditorContext() {
        IWorkbenchPage page = getSite().getPage();
        if (!linkWithEditorContextAction.isEnabled() || page == null || page.getActiveEditor() == null) {
            return;
        }

        IEditorPart activeEditor = page.getActiveEditor();
        if (linkWithEditorContextAction.isChecked()) {
            getCurrentLanguageInterpreter().linkWithEditor(activeEditor);
            linkWithEditorContextAction.changeTooltip(activeEditor);
        } else {
            getCurrentLanguageInterpreter().linkWithEditor(null);
            linkWithEditorContextAction.changeTooltip(null);
            /*
             * Re-compute enablement : we may have left the toggle "enabled" while there is no active "linkable with"
             * editors
             */
            if (activeEditor == null) {
                linkWithEditorContextAction.setEnabled(false);
            } else {
                linkWithEditorContextAction.setEnabled(getCurrentLanguageInterpreter().canLinkWithEditor(activeEditor));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.ViewPart#saveState(org.eclipse.ui.IMemento)
     */
    @Override
    public void saveState(IMemento memento) {
        if (partMemento != null) {
            // Part had not been restored, keep old state
            memento.putMemento(partMemento);
        } else {
            memento.putString(MEMENTO_EXPRESSION_KEY, expressionViewer.getTextWidget().getText());
            memento.putBoolean(MEMENTO_REAL_TIME_KEY, Boolean.valueOf(realTime));
            memento.putBoolean(MEMENTO_VARIABLES_VISIBLE_KEY, Boolean.valueOf(variableViewer.getControl().isVisible()));
            memento.putBoolean(MEMENTO_SUB_EXPRESSIONS_VISIBLE_KEY, Boolean.valueOf(subExpressionViewer.getControl().isVisible()));
            if (resultViewer instanceof StructuredViewer) {
                memento.putBoolean(MEMENTO_RESULT_SORTED_KEY, Boolean.valueOf(((StructuredViewer) resultViewer).getComparator() != null));
            }
            memento.putBoolean(MEMENTO_SUB_EXPRESSIONS_SORTED_KEY, Boolean.valueOf(subExpressionViewer.getComparator() != null));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        if (expressionViewer != null) {
            expressionViewer.getControl().setFocus();
        }
    }

    /** Enables (or disables) the real-time evaluation of expressions. */
    public synchronized void toggleRealTime() {
        realTime = !realTime;
        if (realTime) {
            realTimeThread = new RealTimeThread();

            // Launch a compilation right from the get-go
            compileAndEvaluate();

            realTimeThread.start();
        } else {
            if (realTimeThread != null) {
                realTimeThread.interrupt();
                realTimeThread = null;
            }
        }
    }

    /**
     * Shows or hides the variables viewer.
     */
    public void toggleVariableVisibility() {
        if (variableColumn != null && !variableColumn.isDisposed()) {
            variableVisible = !variableVisible;
            variableColumn.setVisible(variableVisible);
            final int[] newWeights;
            if (variableVisible) {
                newWeights = new int[] { 3, 1, };
            } else {
                newWeights = new int[] { 1, 0, };
            }
            formBody.setWeights(newWeights);
            getForm().layout();
        }
    }

    /**
     * Shows or hides the step-by-step viewer.
     */
    public void toggleStepByStepVisibility() {
        if (subExpressionComposite != null && !subExpressionComposite.isDisposed()) {
            subExpressionsVisible = !subExpressionsVisible;
            subExpressionComposite.setVisible(subExpressionsVisible);
            final int[] newWeights;
            if (subExpressionsVisible) {
                newWeights = new int[] { 1, 1, };
            } else {
                newWeights = new int[] { 0, 1, };
            }
            bottomLeftColumn.setWeights(newWeights);
            getForm().layout();
        }
    }

    /**
     * Adds a new message to the form.
     * 
     * @param messageKey
     *            Key of the message. Will be used to find and remove it later on.
     * @param message
     *            Text of the message that is to be added to the form.
     * @param messageType
     *            Type of the message as defined in {@link IMessageProvider}.
     */
    protected final void addMessage(String messageKey, String message, int messageType) {
        Control targetControl = null;
        if (messageType != IMessageProvider.NONE && messageKey.startsWith(COMPILATION_MESSAGE_PREFIX)) {
            targetControl = expressionSection;
        } else if (messageType != IMessageProvider.NONE && messageKey.startsWith(EVALUATION_MESSAGE_PREFIX)) {
            targetControl = resultSection;
        }

        if (!getForm().isDisposed()) {
            if (targetControl != null) {
                getMessageManager().addMessage(messageKey, message, messageType, targetControl);
            } else {
                getMessageManager().addMessage(messageKey, message, messageType);
            }
        }
    }

    /**
     * This will be used by the {@link CompilationThread}s in order to parse the compilation or evaluation results'
     * status and add the necessary problem message to the form.
     * 
     * @param status
     *            Status which messages is to be added to the form.
     * @param keyPrefix
     *            Prefix of the message key.
     */
    protected final void addStatusMessages(IStatus status, String keyPrefix) {
        if (status instanceof MultiStatus) {
            for (IStatus child : status.getChildren()) {
                addStatusMessages(child, keyPrefix);
            }
        } else {
            String messageKey;
            if (status.getSeverity() == IStatus.OK) {
                if (keyPrefix.equals(COMPILATION_MESSAGE_PREFIX)) {
                    messageKey = COMPILATION_INFO_MESSAGE_KEY;
                } else {
                    messageKey = EVALUATION_INFO_MESSAGE_KEY;
                }
            } else {
                messageKey = keyPrefix + "." + messageCount++; //$NON-NLS-1$
            }

            addMessage(messageKey, status.getMessage(), convertStatusToMessageSeverity(status.getSeverity()));
        }
    }

    /**
     * Creates a new list for the selection if needed, and adds the given object to it.
     * 
     * @param object
     *            The element that is to be added to the current selection.
     * @deprecated use {@link #addToSelection(Notifier)} instead.
     */
    @Deprecated
    protected void addToSelection(EObject object) {
        if (selectedNotifiers == null) {
            // Assumes the "usual" selection is always 1 element long
            selectedNotifiers = new ArrayList<Notifier>(1);
            selectedEObjects = new ArrayList<EObject>(1);
        }
        selectedNotifiers.add(object);
        selectedEObjects.add(object);
    }

    /**
     * Creates a new list for the selection if needed, and adds the given notifier into it.
     * 
     * @param notifier
     *            The element that is to be added to the current selection.
     */
    protected void addToSelection(Notifier notifier) {
        if (selectedNotifiers == null) {
            // Assumes the "usual" selection is always 1 element long
            selectedNotifiers = new ArrayList<Notifier>(1);
            selectedEObjects = new ArrayList<EObject>(1);
        }
        selectedNotifiers.add(notifier);
        if (notifier instanceof EObject) {
            selectedEObjects.add((EObject) notifier);
        }
    }

    /**
     * Clears all compilation messages out of the interpreter view.
     */
    protected void clearCompilationMessages() {
        /*
         * add a dummy message to ensure there is always one while we clear the rest (we'll need to reset the color
         * without having _all_ messages removed, lest the color stays at its previous state : bug 357906 in
         * FormHeading.MessageRegion#showMessage().
         */
        final String dummyMessageKey = "none"; //$NON-NLS-1$
        getMessageManager().addMessage(dummyMessageKey, "", IMessageProvider.ERROR); //$NON-NLS-1$
        // Remove all actual messages
        getMessageManager().removeMessage(COMPILATION_MESSAGE_PREFIX);
        getMessageManager().removeMessages(expressionSection);
        // Now, reset the color by modifying our (existing) dummy message to a lesser severity
        getMessageManager().addMessage(dummyMessageKey, "", IMessageProvider.NONE); //$NON-NLS-1$
        // Finally, remove our dummy
        getMessageManager().removeMessage(dummyMessageKey);
    }

    /**
     * Clears all evaluation messages out of the interpreter view.
     */
    protected void clearEvaluationMessages() {
        /*
         * add a dummy message to ensure there is always one while we clear the rest (we'll need to reset the color
         * without having _all_ messages removed, lest the color stays at its previous state : bug 357906 in
         * FormHeading.MessageRegion#showMessage().
         */
        final String dummyMessageKey = "none"; //$NON-NLS-1$
        getMessageManager().addMessage(dummyMessageKey, "", IMessageProvider.ERROR); //$NON-NLS-1$
        // Remove all actual messages
        getMessageManager().removeMessage(EVALUATION_INFO_MESSAGE_KEY);
        getMessageManager().removeMessages(resultSection);
        // Now, reset the color by modifying our (existing) dummy message to a lesser severity
        getMessageManager().addMessage(dummyMessageKey, "", IMessageProvider.NONE); //$NON-NLS-1$
        // Finally, remove our dummy
        getMessageManager().removeMessage(dummyMessageKey);
    }

    /**
     * If we currently have EObjects selected, this will clear the whole list.
     */
    protected void clearSelection() {
        if (selectedNotifiers != null) {
            selectedNotifiers.clear();
            selectedNotifiers = null;
            selectedEObjects.clear();
            selectedEObjects = null;
        }
    }

    /**
     * Creates the adapter factory that will be used for our label and content providers.
     * 
     * @return The adapter factory that will be used for our label and content providers.
     */
    protected AdapterFactory createAdapterFactory() {
        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        return adapterFactory;
    }

    /**
     * Creates the expression viewer menu listener. This listener is in charge of filling the menu's actions.
     * 
     * @param viewer
     *            The expression viewer.
     * @return The newly created listener.
     */
    protected IMenuListener createExpressionMenuListener(SourceViewer viewer) {
        return new ExpressionMenuListener(viewer);
    }

    /**
     * This will be called to create the "Expression" section (top part of the left column) of the "Interpreter" form.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param leftColumn
     *            Left column of the "Interpreter" form.
     */
    protected void createExpressionSection(FormToolkit toolkit, Composite leftColumn) {
        expressionSection = toolkit.createSection(leftColumn, ExpandableComposite.TITLE_BAR);
        expressionSection.setText(InterpreterMessages.getString("interpreter.view.expression.section.name")); //$NON-NLS-1$

        Composite expressionSectionBody = toolkit.createComposite(expressionSection);
        GridLayout expressionSectionLayout = new GridLayout();
        expressionSectionBody.setLayout(expressionSectionLayout);

        expressionViewer = createExpressionViewer(expressionSectionBody);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        expressionViewer.getControl().setLayoutData(gridData);

        createSectionToolBar(expressionSection);
        populateExpressionSectionToolbar(expressionSection);

        expressionSection.setClient(expressionSectionBody);

        expressionSectionBody.layout();
        expressionSection.layout();
    }

    /**
     * Creates the source viewer for the currently selected language.
     * 
     * @param parent
     *            Parent Composite of the result viewer.
     * @return The source viewer for the currently selected language.
     */
    protected SourceViewer createExpressionViewer(Composite parent) {
        SourceViewer viewer = getCurrentLanguageInterpreter().createSourceViewer(parent);
        if (viewer == null) {
            viewer = SWTUtil.createScrollableSourceViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
            viewer.configure(new TextSourceViewerConfiguration());
            viewer.setDocument(new Document());
        }
        getCurrentLanguageInterpreter().configureSourceViewer(viewer);

        if (viewer instanceof IInterpreterSourceViewer) {
            setUpContentAssist((IInterpreterSourceViewer) viewer);
        }
        setUpRealTimeCompilation(viewer);
        setUpDefaultTextAction(viewer);

        createExpressionMenu(viewer);

        if (partMemento != null) {
            String expression = partMemento.getString(MEMENTO_EXPRESSION_KEY);
            if (expression != null) {
                viewer.getDocument().set(expression);
            }
        }
        return viewer;
    }

    /**
     * This will be called in order to create the "Interpreter" form.
     * 
     * @param toolkit
     *            Toolkit that can be used to create the form.
     * @param parent
     *            Parent composite of the form.
     */
    protected void createInterpreterForm(FormToolkit toolkit, Composite parent) {
        interpreterForm = toolkit.createForm(parent);
        messageManager = FormMessageManagerFactory.createFormMessageManager(interpreterForm);
        getMessageManager().setDecorationPosition(SWT.LEFT | SWT.TOP);
        toolkit.decorateFormHeading(getForm());

        String languageName = InterpreterMessages.getString("SiriusInterpreter_label"); //$NON-NLS-1$
        getForm().setText(InterpreterMessages.getString("interpreter.view.title", languageName)); //$NON-NLS-1$

        final Image titleImage = InterpreterImages.getImageDescriptor(IInterpreterConstants.INTERPRETER_VIEW_DEFAULT_ICON).createImage();
        setTitleImage(titleImage);
        getForm().setImage(titleImage);

        Composite mainBody = getForm().getBody();
        mainBody.setLayout(new GridLayout());
        formBody = new SashForm(mainBody, SWT.HORIZONTAL | SWT.SMOOTH);
        toolkit.adapt(formBody);
        formBody.setLayoutData(new GridData(GridData.FILL_BOTH));

        SashForm leftColumn = new SashForm(formBody, SWT.VERTICAL | SWT.SMOOTH);
        toolkit.adapt(leftColumn);

        createExpressionSection(toolkit, leftColumn);

        bottomLeftColumn = new SashForm(leftColumn, SWT.HORIZONTAL | SWT.SMOOTH);
        toolkit.adapt(bottomLeftColumn);

        subExpressionComposite = toolkit.createComposite(bottomLeftColumn);
        subExpressionComposite.setLayout(new FillLayout());
        subExpressionComposite.setVisible(false);
        Composite resultComposite = toolkit.createComposite(bottomLeftColumn);
        resultComposite.setLayout(new FillLayout());

        createSubExpressionsSection(toolkit, subExpressionComposite);
        createResultSection(toolkit, resultComposite);

        bottomLeftColumn.setWeights(new int[] { 1, 1, });

        leftColumn.setWeights(new int[] { 2, 3, });

        variableColumn = toolkit.createComposite(formBody);
        variableColumn.setLayout(new FillLayout());
        /*
         * Variables are invisible by default. The toolbar initialization will restore their state, making them visible
         * if they were previously.
         */
        variableColumn.setVisible(false);

        createVariableSection(toolkit, variableColumn);

        formBody.setWeights(new int[] { 3, 1, });

        createToolBar(getForm());
    }

    /**
     * This allows us to avoid a visual glitch on windows where the expression section has no borders and a "black
     * square" in place of its vertical scroll bar. This glitch happened when closing and reopening the view without
     * restarting the runtime.
     */
    protected void refreshExpressionSection() {
        if (!expressionSection.isDisposed()) {
            expressionSection.layout();
        }
    }

    /**
     * Creates the result viewer menu listener. This listener is in charge of filling the menu's actions.
     * 
     * @param viewer
     *            The result viewer.
     * @return The newly created listener.
     */
    protected IMenuListener createResultMenuListener(Viewer viewer) {
        return new ResultMenuListener(viewer);
    }

    /**
     * This will be called to create the "Evaluation Result" section (bottom part of the left column) of the
     * "Interpreter" form.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param leftColumn
     *            Left column of the "Interpreter" form.
     */
    protected void createResultSection(FormToolkit toolkit, Composite leftColumn) {
        resultSection = toolkit.createSection(leftColumn, ExpandableComposite.TITLE_BAR);
        resultSection.setText(InterpreterMessages.getString("interpreter.view.result.section.name")); //$NON-NLS-1$

        Composite resultSectionBody = toolkit.createComposite(resultSection);
        resultSectionBody.setLayout(new GridLayout());

        resultViewer = createResultViewer(resultSectionBody);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        resultViewer.getControl().setLayoutData(gridData);

        createSectionToolBar(resultSection);
        populateResultSectionToolbar(resultSection);

        toolkit.paintBordersFor(resultSectionBody);
        resultSection.setClient(resultSectionBody);

        getSite().setSelectionProvider(this.resultViewer);
    }

    /**
     * Creates the result viewer for the currently selected language.
     * 
     * @param parent
     *            Parent Composite of the result viewer.
     * @return The result viewer for the currently selected language.
     */
    protected Viewer createResultViewer(Composite parent) {
        Viewer viewer = getCurrentLanguageInterpreter().createResultViewer(parent);
        if (viewer == null) {
            viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
            ColumnViewerToolTipSupport.enableFor((TreeViewer) viewer);

            AdapterFactory adapterFactory = createAdapterFactory();
            TreeViewer treeViewer = (TreeViewer) viewer;
            treeViewer.setContentProvider(new ResultContentProvider(adapterFactory));
            treeViewer.setLabelProvider(new ResultLabelProvider(adapterFactory));
        }

        if (viewer instanceof TreeViewer) {
            setUpResultDragSupport((TreeViewer) viewer);

            ((TreeViewer) viewer).addDoubleClickListener(new ResultDoubleClickListener());
        }

        createResultMenu(viewer);

        return viewer;
    }

    /**
     * Creates the toolbar of our interpreter form.
     * 
     * @param form
     *            The interpreter form.
     */
    protected void createToolBar(Form form) {
        IAction realTimeAction = new ToggleRealTimeAction(this);
        if (partMemento != null) {
            Boolean isRealTime = partMemento.getBoolean(MEMENTO_REAL_TIME_KEY);
            if (isRealTime != null && isRealTime.booleanValue()) {
                toggleRealTime();
                realTimeAction.setChecked(realTime);
            }
        } else {
            // Real-time is active by default
            toggleRealTime();
            realTimeAction.setChecked(realTime);
        }

        linkWithEditorContextAction = new LinkWithEditorContextAction(this);
        final IWorkbenchPage currentPage = getSite().getPage();
        if (currentPage != null) {
            editorPartListener = new InterpreterEditorPartListener();
            getSite().getPage().addPartListener(editorPartListener);

            IEditorPart currentEditor = currentPage.getActiveEditor();
            if (currentEditor == null) {
                linkWithEditorContextAction.setEnabled(false);
            } else {
                linkWithEditorContextAction.setEnabled(getCurrentLanguageInterpreter().canLinkWithEditor(currentEditor));
            }
        } else {
            linkWithEditorContextAction.setEnabled(false);
        }

        IAction variableVisibilityAction = new ToggleVariableVisibilityAction(this);
        if (partMemento != null) {
            Boolean isVariableVisible = partMemento.getBoolean(MEMENTO_VARIABLES_VISIBLE_KEY);
            if (isVariableVisible != null && isVariableVisible.booleanValue()) {
                toggleVariableVisibility();
                variableVisibilityAction.setChecked(variableVisible);
            }
        }

        IAction subExpressionsVisibilityAction = new ToggleStepByStepVisibilityAction(this);
        if (partMemento != null) {
            Boolean isSubExpressionsVisible = partMemento.getBoolean(MEMENTO_SUB_EXPRESSIONS_VISIBLE_KEY);
            if (isSubExpressionsVisible != null && isSubExpressionsVisible.booleanValue()) {
                toggleStepByStepVisibility();
                subExpressionsVisibilityAction.setChecked(subExpressionsVisible);
            }
        }

        IToolBarManager toolBarManager = form.getToolBarManager();
        toolBarManager.add(linkWithEditorContextAction);
        toolBarManager.add(realTimeAction);
        toolBarManager.add(variableVisibilityAction);
        toolBarManager.add(subExpressionsVisibilityAction);

        toolBarManager.add(new Separator(LANGUAGE_SPECIFIC_ACTION_GROUP));

        getCurrentLanguageInterpreter().addToolBarActions(this, toolBarManager);

        toolBarManager.update(true);
    }

    /**
     * Creates the variable viewer menu listener. This listener is in charge of filling the menu's actions.
     * 
     * @param viewer
     *            The variable viewer.
     * @return The newly created listener.
     */
    protected IMenuListener createVariableMenuListener(TreeViewer viewer) {
        return new VariableMenuListener(viewer);
    }

    /**
     * This will be called in order to create the "Variables" section (right column) of the "Interpreter" form.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param rightColumn
     *            Right column of the "Interpreter" form.
     */
    protected void createVariableSection(FormToolkit toolkit, Composite rightColumn) {
        Section variableSection = toolkit.createSection(rightColumn, ExpandableComposite.TITLE_BAR);
        variableSection.setText(InterpreterMessages.getString("interpreter.view.variable.section.name")); //$NON-NLS-1$

        Composite variableSectionBody = toolkit.createComposite(variableSection);
        variableSectionBody.setLayout(new FillLayout());

        variableViewer = createVariableViewer(toolkit, variableSectionBody);

        ToolBarManager toolBarManager = createSectionToolBar(variableSection);
        toolBarManager.add(new ClearVariableViewerAction(variableViewer));
        toolBarManager.update(true);

        toolkit.paintBordersFor(variableSectionBody);
        variableSection.setClient(variableSectionBody);
    }

    /**
     * This will be called in order to create the TreeViewer used to display variables.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param sectionBody
     *            Parent composite of the TreeViewer.
     * @return The newly created viewer.
     */
    protected TreeViewer createVariableViewer(FormToolkit toolkit, Composite sectionBody) {
        Tree variableTree = toolkit.createTree(sectionBody, SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);

        TreeViewer viewer = new TreeViewer(variableTree);
        ColumnViewerToolTipSupport.enableFor(viewer);
        AdapterFactory adapterFactory = createAdapterFactory();
        viewer.setContentProvider(new VariableContentProvider(adapterFactory));
        viewer.setLabelProvider(new VariableLabelProvider(adapterFactory));

        setUpVariableDropSupport(viewer);
        createVariableMenu(viewer);
        setUpVariableActions(viewer);

        viewer.setInput(new ArrayList<Variable>());

        return viewer;
    }

    /**
     * This will be called in order to create the "Sub-Expressions" section (bottom left column) of the form.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param parentComposite
     *            The sub-expression composite.
     */
    protected void createSubExpressionsSection(FormToolkit toolkit, Composite parentComposite) {
        Section subExpressionsSection = toolkit.createSection(parentComposite, ExpandableComposite.TITLE_BAR);
        subExpressionsSection.setText(InterpreterMessages.getString("interpreter.view.subexpression.section.name")); //$NON-NLS-1$

        Composite subExpressionsSectionBody = toolkit.createComposite(subExpressionsSection);
        subExpressionsSectionBody.setLayout(new GridLayout());

        subExpressionViewer = createSubExpressionsViewer(toolkit, subExpressionsSectionBody);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        subExpressionViewer.getControl().setLayoutData(gridData);

        createSectionToolBar(subExpressionsSection);
        populateSubExpressionSectionToolbar(subExpressionsSection);

        toolkit.paintBordersFor(subExpressionsSectionBody);
        subExpressionsSection.setClient(subExpressionsSectionBody);
    }

    /**
     * This will be called in order to create the TreeViewer used to display Sub-Expressions.
     * 
     * @param toolkit
     *            Toolkit that can be used to create form parts.
     * @param sectionBody
     *            Parent composite of the TreeViewer.
     * @return The newly created viewer.
     */
    protected TreeViewer createSubExpressionsViewer(FormToolkit toolkit, Composite sectionBody) {
        Tree subExpressionsTree = toolkit.createTree(sectionBody, SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);

        TreeViewer viewer = new TreeViewer(subExpressionsTree);
        ColumnViewerToolTipSupport.enableFor(viewer);
        AdapterFactory adapterFactory = createAdapterFactory();
        viewer.setContentProvider(new StepByStepContentProvider(adapterFactory));
        viewer.setLabelProvider(new StepLabelProvider());
        viewer.addSelectionChangedListener(new SubExpressionListener());
        viewer.addDoubleClickListener(new SubExpressionDoubleClickListener());

        return viewer;
    }

    /**
     * Returns the interpreter form.
     * 
     * @return The interpreter form.
     */
    protected Form getForm() {
        return interpreterForm;
    }

    /**
     * Returns the indirection layer for the form's message manager.
     * 
     * @return The indirection layer for the form's message manager.
     */
    protected IFormMessageManager getMessageManager() {
        return messageManager;
    }

    /**
     * Returns the current source viewer.
     * <p>
     * Note that the source viewer can change and be disposed over time; don't keep references to it.
     * </p>
     * 
     * @return The current source viewer.
     */
    protected SourceViewer getSourceViewer() {
        return expressionViewer;
    }

    /**
     * Populates the {@link #expressionSection}'s toolbar.
     * 
     * @param section
     *            the expresssion section.
     */
    protected void populateExpressionSectionToolbar(Section section) {
        ToolBarManager toolBarManager = getSectionToolBar(section);
        if (toolBarManager != null) {
            toolBarManager.removeAll();
            toolBarManager.add(new EvaluateAction(this));
            toolBarManager.add(new ClearExpressionViewerAction(getSourceViewer()));
            toolBarManager.update(true);
        }
    }

    /**
     * Populates the {@link #resultSection}'s toolbar.
     * 
     * @param section
     *            the result section.
     */
    protected void populateResultSectionToolbar(Section section) {
        ToolBarManager toolBarManager = getSectionToolBar(section);
        if (toolBarManager == null) {
            return;
        }
        toolBarManager.removeAll();
        if (resultViewer instanceof StructuredViewer) {
            final Action sortAction = new LexicalSortAction((StructuredViewer) resultViewer);
            toolBarManager.add(sortAction);
            if (partMemento != null && partMemento.getBoolean(MEMENTO_RESULT_SORTED_KEY) != null) {
                boolean sortEnabled = partMemento.getBoolean(MEMENTO_RESULT_SORTED_KEY).booleanValue();
                sortAction.setChecked(sortEnabled);
                if (sortEnabled) {
                    sortAction.run();
                }
            }
        }
        toolBarManager.add(new ClearResultViewerAction(resultViewer));
        toolBarManager.update(true);
    }

    /**
     * Populates the sub expression section toolbar.
     * 
     * @param section
     *            The section for which the toolbar should be populated
     */
    protected void populateSubExpressionSectionToolbar(Section section) {
        ToolBarManager toolBarManager = getSectionToolBar(section);
        if (toolBarManager != null) {
            toolBarManager.removeAll();
            final Action sortAction = new LexicalSortAction(subExpressionViewer);
            toolBarManager.add(sortAction);
            if (partMemento != null && partMemento.getBoolean(MEMENTO_SUB_EXPRESSIONS_SORTED_KEY) != null) {
                boolean sortEnabled = partMemento.getBoolean(MEMENTO_SUB_EXPRESSIONS_SORTED_KEY).booleanValue();
                sortAction.setChecked(sortEnabled);
                if (sortEnabled) {
                    sortAction.run();
                }
            }
            toolBarManager.add(new ClearResultViewerAction(subExpressionViewer));
            toolBarManager.update(true);
        }
    }

    /**
     * Sets the result of the compilation to the given instance.
     * 
     * @param compilationResult
     *            The current expression's compilation result.
     */
    protected final void setCompilationResult(CompilationResult compilationResult) {
        this.compilationResult = compilationResult;
    }

    /**
     * Update the result viewer.
     * 
     * @param result
     *            The current expressions's evaluation result.
     */
    protected final void setEvaluationResult(EvaluationResult result) {
        List<Object> input = new ArrayList<Object>();
        Object evaluationResult = result.getEvaluationResult();
        if (evaluationResult instanceof Collection<?>) {
            for (Object child : (Collection<?>) evaluationResult) {
                if (child != null) {
                    input.add(child);
                } else {
                    input.add(NULL_RESULT_OBJECT);
                }
            }
        } else if (evaluationResult != null) {
            input.add(evaluationResult);
        }

        if (!resultViewer.getControl().isDisposed()) {
            resultViewer.setInput(input);
        }
    }

    /**
     * Sets up the content assist action for the given source viewer.
     * 
     * @param viewer
     *            The viewer we need content assist on.
     */
    protected final void setUpContentAssist(final IInterpreterSourceViewer viewer) {
        IHandlerService service = (IHandlerService) getSite().getService(IHandlerService.class);
        if (activationTokenContentAssist != null) {
            service.deactivateHandler(activationTokenContentAssist);
        }

        IAction contentAssistAction = new Action() {
            @Override
            public void run() {
                viewer.showContentAssist(getInterpreterContext());
            }
        };
        IHandler contentAssistHandler = new ActionHandler(contentAssistAction);

        activationTokenContentAssist = service.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, contentAssistHandler);
    }

    /**
     * Sets up the real-time compilation action on the given viewer. The real-time thread specifically needs to be told
     * whenever the expression is dirty as well as when the timer should be reset (in order not to compile when the user
     * is entering the expression).
     * 
     * @param viewer
     *            The viewer for which to set up the real-time compilation thread.
     */
    protected void setUpRealTimeCompilation(final SourceViewer viewer) {
        // XText tends to throw us into an infinite loop.
        // Double-check that text actually changed.
        viewer.addTextListener(new ITextListener() {
            private String lastText;

            public void textChanged(TextEvent event) {
                if (realTimeThread != null) {
                    boolean ignore = false;
                    String currentText = viewer.getDocument().get();
                    if (lastText != null) {
                        ignore = lastText.equals(currentText);
                    }
                    if (!ignore) {
                        lastText = currentText;
                        realTimeThread.reset();
                        realTimeThread.setDirty();
                    }
                }
            }
        });
    }

    /**
     * Sets up the drag and drop support for the result viewer.
     * 
     * @param viewer
     *            The result viewer.
     */
    protected void setUpResultDragSupport(TreeViewer viewer) {
        int operations = DND.DROP_COPY | DND.DROP_LINK | DND.DROP_MOVE;
        Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer(), };

        viewer.addDragSupport(operations, transfers, new ResultDragListener(viewer));
    }

    /**
     * This will be called in order to set up the actions available on the given variable viewer.
     * 
     * @param viewer
     *            The viewer on which to activate actions.
     */
    protected void setUpVariableActions(final TreeViewer viewer) {
        Tree tree = viewer.getTree();
        final Action renameAction = new RenameVariableAction(viewer);
        final Action deleteAction = new DeleteVariableOrValueAction(viewer);

        tree.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.keyCode == SWT.DEL && event.stateMask == 0) {
                    if (deleteAction.isEnabled()) {
                        deleteAction.run();
                    }
                } else if (event.keyCode == SWT.F2 && event.stateMask == 0) {
                    if (renameAction.isEnabled()) {
                        renameAction.run();
                    }
                }
            }
        });
    }

    /**
     * Sets up the drag and drop support for the variable viewer.
     * 
     * @param viewer
     *            The variable viewer.
     */
    protected void setUpVariableDropSupport(TreeViewer viewer) {
        int operations = DND.DROP_DEFAULT | DND.DROP_COPY | DND.DROP_LINK | DND.DROP_MOVE;
        Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(), };

        viewer.addDropSupport(operations, transfers, new VariableDropListener(viewer));
    }

    /**
     * Converts an IStatus severity to a IMessageProviderSeverity.
     * 
     * @param statusSeverity
     *            The status severity to be converted.
     * @return The corresponding IMessageProvider severity.
     */
    private int convertStatusToMessageSeverity(int statusSeverity) {
        int severity = IMessageProvider.NONE;
        switch (statusSeverity) {
        case IStatus.INFO:
            severity = IMessageProvider.INFORMATION;
            break;
        case IStatus.WARNING:
            severity = IMessageProvider.WARNING;
            break;
        case IStatus.ERROR:
            severity = IMessageProvider.ERROR;
            break;
        default:
            severity = IMessageProvider.NONE;
        }
        return severity;
    }

    /**
     * Creates the right-click menu that will be displayed for the expression viewer.
     * 
     * @param viewer
     *            The expression viewer.
     */
    private void createExpressionMenu(SourceViewer viewer) {
        Menu menu = viewer.getTextWidget().getMenu();
        boolean createMenu = true;
        if (menu != null) {
            MenuManager manager = (MenuManager) menu.getData("org.eclipse.jface.action.MenuManager.managerKey"); //$NON-NLS-1$
            if (manager != null) {
                manager.addMenuListener(createExpressionMenuListener(viewer));
                createMenu = false;
            }
        }

        if (createMenu) {
            MenuManager menuManager = new MenuManager(MENU_ID);
            menuManager.setRemoveAllWhenShown(true);
            menuManager.addMenuListener(createExpressionMenuListener(viewer));
            menu = menuManager.createContextMenu(viewer.getTextWidget());
            viewer.getTextWidget().setMenu(menu);
            getSite().registerContextMenu(menuManager, viewer);
        }
    }

    /**
     * Creates the right-click menu that will be displayed for the result viewer.
     * 
     * @param viewer
     *            The result viewer.
     */
    private void createResultMenu(Viewer viewer) {
        MenuManager menuManager = new MenuManager(MENU_ID);
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(createResultMenuListener(viewer));
        Menu menu = menuManager.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuManager, viewer);
    }

    /**
     * Creates the right-click menu that will be displayed for the variable viewer.
     * 
     * @param viewer
     *            The variable viewer.
     */
    private void createVariableMenu(TreeViewer viewer) {
        MenuManager menuManager = new MenuManager(MENU_ID);
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(createVariableMenuListener(viewer));
        Menu menu = menuManager.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuManager, viewer);
    }

    /**
     * Sets up the default text actions (undo, redo) on the given source viewer.
     * 
     * @param viewer
     *            The viewer on which to activate default text actions.
     */
    private void setUpDefaultTextAction(final SourceViewer viewer) {
        IHandlerService service = (IHandlerService) getSite().getService(IHandlerService.class);
        if (activationTokenRedo != null) {
            service.deactivateHandler(activationTokenRedo);
        }
        if (activationTokenUndo != null) {
            service.deactivateHandler(activationTokenUndo);
        }

        IAction redoAction = new Action() {
            @Override
            public void run() {
                viewer.doOperation(ITextOperationTarget.REDO);
            }
        };
        IHandler redoHandler = new ActionHandler(redoAction);

        IAction undoAction = new Action() {
            @Override
            public void run() {
                viewer.doOperation(ITextOperationTarget.UNDO);
            }
        };
        IHandler undoHandler = new ActionHandler(undoAction);

        activationTokenRedo = service.activateHandler(WORKBENCH_CONSTANT_EDIT_REDO, redoHandler);
        activationTokenUndo = service.activateHandler(WORKBENCH_CONSTANT_EDIT_UNDO, undoHandler);
    }

    /**
     * This class will be used in order to populate the right-click menu of the Expression viewer.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class ExpressionMenuListener implements IMenuListener {
        /** The viewer on which this menu listener operates. */
        private final SourceViewer sourceViewer;

        /**
         * Creates this menu listener given the viewer on which it operates.
         * 
         * @param sourceViewer
         *            The viewer on which this menu listener operates.
         */
        public ExpressionMenuListener(SourceViewer sourceViewer) {
            this.sourceViewer = sourceViewer;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
         */
        public void menuAboutToShow(IMenuManager manager) {
            manager.add(new Separator("org.eclipse.ui.interpreter.view.expression.menu")); //$NON-NLS-1$
            manager.add(new EvaluateAction(InterpreterView.this));
            manager.add(new ClearExpressionViewerAction(sourceViewer));
            manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        }
    }

    /**
     * This implementation of a part listener will allow us to determine at all times whether the "work in editor
     * context" action should be enabled.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class InterpreterEditorPartListener implements IPartListener2 {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partActivated(IWorkbenchPartReference partRef) {
            // If the toggle is checked, defer enablement computing till we uncheck it
            if (!linkWithEditorContextAction.isChecked() && partRef instanceof IEditorReference) {
                linkWithEditorContextAction.setEnabled(getCurrentLanguageInterpreter().canLinkWithEditor(((IEditorReference) partRef).getEditor(false)));
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partBroughtToTop(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partClosed(IWorkbenchPartReference partRef) {
            // If the toggle is checked, defer enablement computing till we uncheck it
            if (!linkWithEditorContextAction.isChecked() && partRef instanceof IEditorReference && getSite().getPage() != null) {
                final IEditorPart editorPart = getSite().getPage().getActiveEditor();
                if (editorPart == null) {
                    linkWithEditorContextAction.setEnabled(false);
                } else {
                    linkWithEditorContextAction.setEnabled(getCurrentLanguageInterpreter().canLinkWithEditor(editorPart));
                }
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partDeactivated(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partHidden(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partInputChanged(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partOpened(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
         */
        public void partVisible(IWorkbenchPartReference partRef) {
            // No need to react to this event
        }
    }

    /**
     * This will allow us to react to double click events in the result view in order to display the long Strings and
     * generated files in a more suitable way.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected static class ResultDoubleClickListener implements IDoubleClickListener {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
         */
        public void doubleClick(DoubleClickEvent event) {
            ISelection selection = event.getSelection();
            if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
                return;
            }
            final Object target = ((IStructuredSelection) selection).getFirstElement();
            if (target instanceof InterpreterFile) {
                showGeneratedFile((InterpreterFile) target);
            } else if (target instanceof String && (((String) target).indexOf('\n') >= 0 || ((String) target).indexOf('\r') >= 0)) {
                GeneratedTextDialog dialog = new GeneratedTextDialog(Display.getCurrent().getActiveShell(), "Evaluation Result", (String) target); //$NON-NLS-1$
                dialog.open();
            } else if (event.getViewer() instanceof TreeViewer && ((TreeViewer) event.getViewer()).isExpandable(target)) {
                final TreeViewer viewer = (TreeViewer) event.getViewer();
                if (selection instanceof ITreeSelection) {
                    TreePath[] paths = ((ITreeSelection) selection).getPathsFor(target);
                    for (int i = 0; i < paths.length; i++) {
                        viewer.setExpandedState(paths[i], !viewer.getExpandedState(paths[i]));
                    }
                } else {
                    viewer.setExpandedState(target, !viewer.getExpandedState(target));
                }
            }
        }

        /**
         * Displays the given generated file in its own read-only editor.
         * 
         * @param file
         *            The file we are to display.
         */
        private void showGeneratedFile(InterpreterFile file) {
            final IWorkbench workbench = PlatformUI.getWorkbench();
            if (workbench.getActiveWorkbenchWindow() == null || workbench.getActiveWorkbenchWindow().getActivePage() == null) {
                return;
            }
            final IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            final IStorage storage = new InterpreterFileStorage(file);
            final IEditorDescriptor editor = workbench.getEditorRegistry().getDefaultEditor(file.getFileName());
            final IEditorInput input = new StorageEditorInput(storage);

            try {
                final String editorID;
                if (editor == null) {
                    editorID = EditorsUI.DEFAULT_TEXT_EDITOR_ID;
                } else {
                    editorID = editor.getId();
                }
                page.openEditor(input, editorID);
            } catch (PartInitException e) {
                // swallow : we just won't open editors
            }
        }
    }

    /**
     * This will allow us to react to double click events in the sub-expressions view in order to expand when needed.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected static class SubExpressionDoubleClickListener implements IDoubleClickListener {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
         */
        public void doubleClick(DoubleClickEvent event) {
            ISelection selection = event.getSelection();
            if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
                return;
            }
            final Object target = ((IStructuredSelection) selection).getFirstElement();
            if (event.getViewer() instanceof TreeViewer && ((TreeViewer) event.getViewer()).isExpandable(target)) {
                final TreeViewer viewer = (TreeViewer) event.getViewer();
                if (selection instanceof ITreeSelection) {
                    TreePath[] paths = ((ITreeSelection) selection).getPathsFor(target);
                    for (int i = 0; i < paths.length; i++) {
                        viewer.setExpandedState(paths[i], !viewer.getExpandedState(paths[i]));
                    }
                } else {
                    viewer.setExpandedState(target, !viewer.getExpandedState(target));
                }
            }
        }
    }

    /**
     * This class will be used in order to populate the right-click menu of the result viewer.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class ResultMenuListener implements IMenuListener {
        /** The viewer on which this menu listener operates. */
        private Viewer resultViewer;

        /**
         * Creates this menu listener given the viewer on which it operates.
         * 
         * @param resultViewer
         *            The viewer on which this menu listener operates.
         */
        public ResultMenuListener(Viewer resultViewer) {
            this.resultViewer = resultViewer;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
         */
        public void menuAboutToShow(IMenuManager manager) {
            manager.add(new ClearResultViewerAction(resultViewer));
            manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        }
    }

    /**
     * This class will be used in order to populate the right-click menu of the Variable viewer.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected class VariableMenuListener implements IMenuListener {
        /** The viewer on which this menu listener operates. */
        private TreeViewer variableViewer;

        /**
         * Creates this menu listener given the viewer on which it operates.
         * 
         * @param variableViewer
         *            The viewer on which this menu listener operates.
         */
        public VariableMenuListener(TreeViewer variableViewer) {
            this.variableViewer = variableViewer;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
         */
        public void menuAboutToShow(IMenuManager manager) {
            final Variable variable = getCurrentVariable();
            manager.add(new NewVariableWizardAction(variableViewer, variable));
            manager.add(new ClearVariableViewerAction(variableViewer));
            manager.add(new Separator());
            manager.add(new DeleteVariableOrValueAction(variableViewer));
            manager.add(new RenameVariableAction(variableViewer));
            manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        }

        /**
         * Returns the first of the currently selected Variable(s).
         * 
         * @return The first of the currently selected Variable(s)
         */
        private Variable getCurrentVariable() {
            if (variableViewer == null || variableViewer.getTree() == null || variableViewer.getTree().isDisposed()) {
                return null;
            }
            Tree tree = variableViewer.getTree();

            TreeItem[] selectedItems = tree.getSelection();
            Variable selectedVariable = null;
            if (selectedItems != null && selectedItems.length > 0) {
                for (int i = 0; i < selectedItems.length && selectedVariable == null; i++) {
                    TreeItem item = selectedItems[i];
                    if (item.getData() instanceof Variable) {
                        selectedVariable = (Variable) item.getData();
                    }
                }
                for (int i = 0; i < selectedItems.length && selectedVariable == null; i++) {
                    TreeItem item = selectedItems[i].getParentItem();
                    while (item != null && selectedVariable == null) {
                        if (item.getData() instanceof Variable) {
                            selectedVariable = (Variable) item.getData();
                        }
                        item = item.getParentItem();
                    }
                }
            }
            return selectedVariable;
        }
    }

    /**
     * This implementation of a Thread will be used to wrap a compilation task as returned by the LanguageInterpreter,
     * then asynchronously update the form with all error messages (if any) that were raised by this compilation task.
     * Afterwards, this Thread will update the interpreter context with the compilation result.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class CompilationThread extends Thread {
        /**
         * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which
         * could happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
         */
        private boolean cancelled;

        /** The compilation thread which result we are to wait for. */
        private Future<CompilationResult> compilationTask;

        /**
         * Instantiates a compilation thread given the compilation task of which we are to check the result.
         * 
         * @param compilationTask
         *            Thread which result we are to wait for.
         */
        CompilationThread(Future<CompilationResult> compilationTask) {
            super("InterpreterCompilationThread"); //$NON-NLS-1$
            this.compilationTask = compilationTask;
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
                            clearCompilationMessages();
                            checkCancelled();
                            addStatusMessages(result.getStatus(), COMPILATION_MESSAGE_PREFIX);
                        }
                    });
                }

                // Whether there were problems or not, update the context with this result.
                setCompilationResult(result);
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
                        clearCompilationMessages();
                        checkCancelled();
                        addStatusMessages(status, COMPILATION_MESSAGE_PREFIX);
                    }
                });

                setCompilationResult(result);
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

    /**
     * This will be installed on the workbench page on which this view is displayed in order to listen to selection
     * events corresponding to Notifiers.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class NotifierSelectionListener implements ISelectionListener {

        /**
         * This interpreter view.
         */
        private final InterpreterView view;

        /**
         * Constructs this listener.
         * 
         * @param view
         *            The view on which the selection should not be triggered.
         */
        NotifierSelectionListener(InterpreterView view) {
            this.view = view;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
         *      org.eclipse.jface.viewers.ISelection)
         */
        @SuppressWarnings("unchecked")
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            if (!selection.isEmpty() && selection instanceof IStructuredSelection && part != this.view) {
                boolean cleared = false;
                final Iterator<Object> selectionIterator = ((IStructuredSelection) selection).iterator();
                while (selectionIterator.hasNext()) {
                    final Object next = selectionIterator.next();
                    final Notifier nextNotifier;
                    if (next instanceof Notifier) {
                        nextNotifier = (Notifier) next;
                    } else {
                        final Notifier adaptedNotifier = adaptAs(next, Notifier.class);
                        if (adaptedNotifier != null) {
                            nextNotifier = adaptedNotifier;
                        } else {
                            nextNotifier = adaptAs(next, EObject.class);
                        }
                    }
                    if (nextNotifier != null) {
                        // At least one of the selected objects is a Notifier, clear current selection
                        if (!cleared) {
                            clearSelection();
                            cleared = true;
                        }
                        addToSelection(nextNotifier);
                    }
                }
                // If the selection changed somehow, relaunch the real-time evaluation
                if (cleared && realTimeThread != null) {
                    realTimeThread.setDirty();
                }
            }
        }

        /**
         * Adapts the given object to the given class.
         * 
         * @param object
         *            The object to adapt
         * @param clazz
         *            The class to which the object should be adapted
         * @param <T>
         *            The type of the class to obtain
         * @return The adapted object
         */
        @SuppressWarnings("unchecked")
        private <T> T adaptAs(Object object, Class<T> clazz) {
            if (object instanceof IAdaptable) {
                return (T) ((IAdaptable) object).getAdapter(clazz);
            }
            return (T) Platform.getAdapterManager().getAdapter(object, clazz);
        }
    }

    /**
     * This implementation of a Thread will be used to wrap an evaluation task as returned by the LanguageInterpreter,
     * then asynchronously update the form with all error messages (if any) that were raised by this compilation task.
     * Afterwards, this Thread will update the result view of the interpreter form.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class EvaluationThread extends Thread {
        /**
         * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which
         * could happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
         */
        private boolean cancelled;

        /** The evaluation thread which result we are to wait for. */
        private Future<EvaluationResult> evaluationTask;

        /** Context of the interpreter as it was when this Thread has been created. */
        private final InterpreterContext interpreterContext;

        /**
         * Instantiates our Thread given the initial interpreter context.
         * 
         * @param interpreterContext
         *            The initial interpreter context.
         */
        EvaluationThread(InterpreterContext interpreterContext) {
            super("InterpreterEvaluationThread"); //$NON-NLS-1$
            this.interpreterContext = interpreterContext;
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
                        getForm().setBusy(true);
                    }
                });
                // Cannot do anything before the current compilation thread stops.
                if (compilationThread != null) {
                    compilationThread.join();
                }
                checkCancelled();

                Callable<EvaluationResult> evaluationCallable = getCurrentLanguageInterpreter().getEvaluationTask(new EvaluationContext(interpreterContext, compilationResult));
                evaluationTask = evaluationPool.submit(evaluationCallable);

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
                            clearEvaluationMessages();
                            if (result.getStatus() != null) {
                                addStatusMessages(result.getStatus(), EVALUATION_MESSAGE_PREFIX);
                            }
                            // whether there were problems or not, try and update the result viewer.
                            setEvaluationResult(result);
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
                        clearEvaluationMessages();
                        addStatusMessages(status, EVALUATION_MESSAGE_PREFIX);
                        setEvaluationResult(result);
                    }
                });
            } finally {
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        getForm().setBusy(false);
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

    /**
     * This implementation of a Thread will be used to wrap a splitting task as returned by the LanguageInterpreter,
     * then asynchronously update the form with all error messages (if any) that were raised by this compilation task.
     * Afterwards, this Thread will update the sub-expressions view of the interpreter form.
     * 
     * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
     */
    private class ExpressionSplittingThread extends Thread {
        /**
         * We will set this flag on {@link #interrupt()} in order to determine whether the thread was cancelled (which
         * could happen <b>after</b> the thread has completed, which would make the "interrupted" flag quiet.
         */
        private boolean cancelled;

        /** The splitting thread which result we are to wait for. */
        private Future<SplitExpression> splittingTask;

        /** Context of the interpreter as it was when this Thread has been created. */
        private final InterpreterContext interpreterContext;

        /**
         * Instantiates our Thread given the initial interpreter context.
         * 
         * @param interpreterContext
         *            The initial interpreter context.
         */
        ExpressionSplittingThread(InterpreterContext interpreterContext) {
            super("InterpreterExpressionSplittingThread"); //$NON-NLS-1$
            this.interpreterContext = interpreterContext;
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
                if (compilationThread != null) {
                    compilationThread.join();
                }
                checkCancelled();

                Callable<SplitExpression> splitExpressionCallable = getCurrentLanguageInterpreter().getExpressionSplittingTask(new EvaluationContext(interpreterContext, compilationResult));

                if (splitExpressionCallable == null) {
                    return;
                }

                splittingTask = splittingPool.submit(splitExpressionCallable);

                final SplitExpression splitExpression = splittingTask.get();
                checkCancelled();

                Display.getDefault().asyncExec(new Runnable() {
                    /**
                     * {@inheritDoc}
                     * 
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        setSubExpressions(splitExpression);
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
                        setSubExpressions(null);
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

    /**
     * This daemon thread will be launched whenever the "real-time" toggle is activated, and will only be stopped when
     * the view is disposed or the "real-time" toggle is disabled.
     * <p>
     * This Thread will be constantly reset on modifications of the expression viewer, and will only really start its
     * work if the expression is left untouched for a given count of seconds.
     * </p>
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class RealTimeThread extends Thread {
        /** Time to wait before launching the evaluation (0.1 second by default). */
        private static final int DELAY = 100;

        /** This will be set to <code>true</code> whenever we need to recompile the expression. */
        private boolean dirty;

        /** The lock we'll acquire for this thread's work. */
        private final Object lock = new Object();

        /** This will be set to <code>true</code> whenever we should reset this thread's timer. */
        private boolean reset;

        /**
         * Instantiates the real-time evaluation thread.
         */
        RealTimeThread() {
            super("InterpreterRealTimeThread"); //$NON-NLS-1$
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
                        compileAndEvaluate();
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

    /**
     * This listener will react to activation events on this view in order to avoid some visual glitches appearing when
     * closing then reopening the view.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private static class ActivationListener implements IPartListener {
        /** This interpreter view. */
        private final InterpreterView view;

        /**
         * Constructs this listener.
         * 
         * @param view
         *            The view which activation we are interested in.
         */
        ActivationListener(InterpreterView view) {
            this.view = view;
        }

        /**
         * {@inheritDoc}
         * 
         * @see IPartListener#partActivated(IWorkbenchPart)
         */
        public void partActivated(IWorkbenchPart part) {
            if (part == view) {
                view.refreshExpressionSection();
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see IPartListener#partDeactivated(IWorkbenchPart)
         */
        public void partDeactivated(IWorkbenchPart part) {
            // Empty implementation
        }

        /**
         * {@inheritDoc}
         * 
         * @see IPartListener#partClosed(IWorkbenchPart)
         */
        public void partClosed(IWorkbenchPart part) {
            // Empty implementation
        }

        /**
         * {@inheritDoc}
         * 
         * @see IPartListener#partBroughtToTop(IWorkbenchPart)
         */
        public void partBroughtToTop(IWorkbenchPart part) {
            // Empty implementation
        }

        /**
         * {@inheritDoc}
         * 
         * @see IPartListener#partOpened(IWorkbenchPart)
         */
        public void partOpened(IWorkbenchPart part) {
            // Empty implementation
        }
    }

    /**
     * This will be added to the sub-expression viewer in order to launch evaluation of parts of the expression entered
     * by the user as split through the expression splitting thread.
     * 
     * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
     */
    protected class SubExpressionListener implements ISelectionChangedListener {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
         */
        public void selectionChanged(SelectionChangedEvent event) {
            if (event.getSelection() instanceof IStructuredSelection) {
                Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selection instanceof SubExpression) {
                    evaluateSubExpression(((SubExpression) selection).getExpression());
                } else if (selection instanceof SplitExpression) {
                    evaluateSubExpression(((SplitExpression) selection).getFullExpression());
                }
            }
        }
    }

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        if (resultViewer != null) {
            this.resultViewer.addSelectionChangedListener(listener);
        }

    }

    @Override
    public ISelection getSelection() {
        if (resultViewer != null) {
            return this.resultViewer.getSelection();
        } else {
            return null;
        }
    }

    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        if (resultViewer != null) {
            this.resultViewer.removeSelectionChangedListener(listener);
        }

    }

    @Override
    public void setSelection(ISelection selection) {
        if (resultViewer != null) {
            this.resultViewer.setSelection(selection);
        }
    }
}
