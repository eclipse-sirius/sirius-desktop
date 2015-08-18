/*******************************************************************************
 * Copyright (c) 2008, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.interpreterview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.contentassist.ContentInstanceProposalProvider;
import org.eclipse.sirius.ui.tools.api.views.interpreterview.InterpreterView;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.adapters.ModelDragTargetAdapter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.part.ViewPart;

/**
 * An Eclipse view to launch model requests language requests and see the
 * results.
 * 
 * @author cbrun
 */
public class DesignerInterpreterView extends ViewPart implements InterpreterView {

    private static final Transfer TRANSFER = LocalSelectionTransfer.getTransfer();

    ITreeContentProvider contentProvider;

    private String contentAssistBinding = "org.eclipse.ui.edit.text.contentAssist.proposals"; //$NON-NLS-1$

    private String variableTag = "$"; //$NON-NLS-1$

    /* user interface */
    private Composite top;

    private FormToolkit formToolkit;

    private Section intepreterSection;

    private Section variablesSection;

    private Form interpreterForm;

    private Text acceleoExpression;

    private Tree valuesTree;

    private TreeViewer valuesViewer;

    private Form variablesForm;

    private Button setVariableButton;

    private Button unSetVariableButton;

    private Tree variablesTree;

    private TreeViewer variablesViewer;

    private ILabelProvider labelProvider;

    /** Action to add a dependency. */
    private Action addDependencyAction;

    /* business object */
    private EObject current;

    private final IInterpreter interpreter = CompoundInterpreter.createGenericInterpreter();

    private ContentInstanceProposalProvider contentInstanceProposalProvider;

    private final IVariableStatusListener variableListener = new IVariableStatusListener() {
        public void notifyChanged(final Map<?, ?> variables) {
            refreshVariables(variables);
        }
    };

    private final ISelectionListener listener = new ISelectionListener() {
        public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
            if (selection instanceof IStructuredSelection) {
                final Iterator<?> it = ((IStructuredSelection) selection).iterator();
                while (it.hasNext()) {
                    final Object obj = it.next();
                    EObject foundEObject = null;
                    if (obj instanceof EObject) {
                        foundEObject = (EObject) obj;
                    }
                    if (foundEObject == null && obj instanceof IAdaptable) {
                        foundEObject = (EObject) ((IAdaptable) obj).getAdapter(EObject.class);
                    }
                    if (foundEObject != null) {
                        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(foundEObject);
                        if (current == null) {
                            addContentAssist(transactionalEditingDomain);
                        }
                        current = foundEObject;
                        contentInstanceProposalProvider.setCurrentEObject(current);
                        handleNewExpression();
                        break;
                    }
                }
            }
        }
    };

    private Action copyToClipboardAction;

    ITreeContentProvider getContentProvider() {
        if (contentProvider == null) {
            contentProvider = new VariableContentProvider(SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory());
        }
        return contentProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(final Composite parent) {
        getSite().getPage().addSelectionListener(listener);
        final GridData gridData7 = new GridData();
        gridData7.horizontalAlignment = GridData.FILL;
        gridData7.grabExcessHorizontalSpace = true;
        gridData7.horizontalSpan = 2;
        gridData7.verticalAlignment = GridData.CENTER;
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        top = new Composite(parent, SWT.NONE);
        top.setLayout(gridLayout);

        labelProvider = new AdapterFactoryLabelProvider(SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory());

        createIntepreterSection();
        createVariablesSection();

        //
        // Creates the actions
        createActions();
        createToolbarButtons();

        //
        // Init interpreter
        interpreter.addVariableStatusListener(this.variableListener);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        if (this.top != null) {
            this.top.getParent().setFocus();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        getSite().getPage().removeSelectionListener(listener);
        if (interpreter != null) {
            interpreter.removeVariableStatusListener(variableListener);
        }
        interpreter.dispose();
        top.dispose();
        formToolkit.dispose();
        intepreterSection.dispose();
        variablesSection.dispose();
        interpreterForm.dispose();
        acceleoExpression.dispose();
        valuesTree.dispose();
        variablesForm.dispose();
        setVariableButton.dispose();
        unSetVariableButton.dispose();
        variablesTree.dispose();
        labelProvider.dispose();
    }

    /**
     * Creates the action of the view.
     */
    private void createActions() {
        this.addDependencyAction = new Action("Add Dependency") {
            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.jface.action.Action#run()
             */
            @Override
            public void run() {
                final InputDialog inputDependency = new InputDialog(getSite().getShell(), "Add Dependency", "Enter the new dependency to add", "", null);
                inputDependency.setBlockOnOpen(true);
                final int result = inputDependency.open();
                if (result == Window.OK) {
                    final String dependency = inputDependency.getValue();
                    if (dependency != null && !StringUtil.isEmpty(dependency)) {
                        // Look for available odesign file paths, to be able to
                        // resolve available dependencies.
                        final List<String> filePaths = new ArrayList<String>();
                        for (final Viewpoint vp : ViewpointRegistry.getInstance().getViewpoints()) {
                            Resource vpResource = vp.eResource();
                            if (vpResource != null) {
                                filePaths.add(vpResource.getURI().toPlatformString(true));
                            }
                        }
                        interpreter.setProperty(IInterpreter.FILES, filePaths);
                        interpreter.addImport(dependency);
                    }
                }
            }

        };
        this.copyToClipboardAction = new Action("Copy to Clipboard") {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("Result of: ").append(acceleoExpression.getText()).append(" (").append(valuesTree.getItemCount()).append(" elements)\n\n");
                for (TreeItem item : valuesTree.getItems()) {
                    sb.append("- ").append(item.getText()).append("\n");
                }
                Clipboard cb = new Clipboard(getSite().getShell().getDisplay());
                TextTransfer textTransfer = TextTransfer.getInstance();
                cb.setContents(new Object[] { sb.toString() }, new Transfer[] { textTransfer });
            }
        };
    }

    /**
     * Create toolbar buttons.
     */
    private void createToolbarButtons() {
        getViewSite().getActionBars().getToolBarManager().add(this.addDependencyAction);
        getViewSite().getActionBars().getToolBarManager().add(this.copyToClipboardAction);
    }

    /**
     * This method initializes formToolkit
     * 
     * @return org.eclipse.ui.forms.widgets.FormToolkit
     */
    private FormToolkit getFormToolkit() {
        if (formToolkit == null) {
            formToolkit = new FormToolkit(Display.getCurrent());
        }
        return formToolkit;
    }

    /**
     * This method initializes intepreterSection
     * 
     */
    private void createIntepreterSection() {
        final GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.FILL;
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.grabExcessVerticalSpace = true;
        gridData1.verticalAlignment = GridData.FILL;
        intepreterSection = getFormToolkit().createSection(top, 0);
        createInterpreterForm();
        intepreterSection.setLayoutData(gridData1);
        intepreterSection.setClient(interpreterForm);
    }

    /**
     * This method initializes variablesSection
     * 
     */
    private void createVariablesSection() {
        final GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = false;
        gridData.verticalAlignment = GridData.FILL;
        gridData.widthHint = 150;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        variablesSection = getFormToolkit().createSection(top, 0);
        variablesSection.setExpanded(true);
        variablesSection.setText("Variables");
        createVariablesForm();
        variablesSection.setLayoutData(gridData);
        variablesSection.setClient(variablesForm);
    }

    /**
     * This method initializes interpreterForm
     */
    private void createInterpreterForm() {
        final GridData gridData3 = new GridData();
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = GridData.FILL;
        gridData3.verticalAlignment = GridData.FILL;
        gridData3.grabExcessVerticalSpace = true;
        final GridData gridData2 = new GridData();
        gridData2.grabExcessHorizontalSpace = true;
        gridData2.verticalAlignment = GridData.CENTER;
        gridData2.horizontalAlignment = GridData.FILL;
        interpreterForm = getFormToolkit().createForm(intepreterSection);
        getFormToolkit().decorateFormHeading(interpreterForm);
        interpreterForm.setText("Requests interpreter");
        acceleoExpression = getFormToolkit().createText(interpreterForm.getBody(), null, SWT.SINGLE | SWT.BORDER);
        acceleoExpression.setLayoutData(gridData2);
        acceleoExpression.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                handleNewExpression();
            }
        });

        valuesTree = new Tree(interpreterForm.getBody(), SWT.NONE | SWT.BORDER | SWT.MULTI);
        valuesTree.setLayoutData(gridData3);
        valuesViewer = new TreeViewer(valuesTree);
        interpreterForm.getBody().setLayout(new GridLayout());
        valuesViewer.setContentProvider(getContentProvider());
        valuesViewer.setLabelProvider(labelProvider);
        valuesViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(final DoubleClickEvent event) {
                if (event.getSelection() instanceof IStructuredSelection) {
                    addVariable(((IStructuredSelection) event.getSelection()).toList());
                }
            }
        });

        /* Configure viewer drag behavior */
        final Transfer[] transfers = new Transfer[] { TRANSFER };
        final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        valuesViewer.addDragSupport(dndOperations, transfers, new ModelDragTargetAdapter(valuesViewer));
    }

    private void addVariable(final List<?> list) {
        if (list.size() > 0 && interpreter != null) {
            final InputDialog askSiriusName = new InputDialog(Display.getDefault().getActiveShell(), "Variable name", "Type variable name", IInterpreterSiriusVariables.ELEMENT, new IInputValidator() {
                public String isValid(final String newText) {
                    return null;
                }
            });
            if (askSiriusName.open() == Window.OK) {
                if (list.size() == 1) {
                    if (askSiriusName.getValue().startsWith(variableTag)) {
                        interpreter.setVariable(askSiriusName.getValue().substring(1), list.get(0));
                    } else {
                        interpreter.setVariable(askSiriusName.getValue(), list.get(0));
                    }
                } else {
                    if (askSiriusName.getValue().startsWith(variableTag)) {
                        interpreter.setVariable(askSiriusName.getValue().substring(1), list);
                    } else {
                        interpreter.setVariable(askSiriusName.getValue(), list);
                    }
                }
            }
        }
    }

    private void refreshVariables(final Map<?, ?> variables) {
        variablesViewer.setInput(variables);
    }

    private void handleNewExpression() {
        if (interpreter != null && current != null) {
            try {
                final ECrossReferenceAdapter crosser = retrieveCrosser(current);
                if (crosser != null) {
                    interpreter.setCrossReferencer(crosser);
                }
                final Resource resource = current.eResource();
                if (resource != null) {
                    final String path = resource.getURI().toPlatformString(true);
                    interpreter.setProperty("file", path); //$NON-NLS-1$
                }

                final long now = new Date().getTime();

                final Object result = interpreter.evaluate(current, acceleoExpression.getText());
                final long ellapseTime = new Date().getTime() - now;
                final int numberOfResults = handleExpressionResult(result);
                final DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###"); //$NON-NLS-1$
                this.interpreterForm.setMessage("Evaluation successful. Number of returned elements : " + numberOfResults + ". Time to evaluate : " + decimalFormat.format(ellapseTime / (double) 1000)
                        + " second(s)", IMessageProvider.INFORMATION);
            } catch (final EvaluationException e) {
                this.interpreterForm.setMessage("Invalid expression. " + e.getMessage(), IMessageProvider.ERROR);
            }
        }
    }

    private ECrossReferenceAdapter retrieveCrosser(final EObject cur) {
        ECrossReferenceAdapter result = null;
        EObject semantic = cur;
        Session sess = null;
        if (cur instanceof DSemanticDecorator) {
            semantic = ((DSemanticDecorator) cur).getTarget();
        }
        if (semantic != null) {
            sess = SessionManager.INSTANCE.getSession(semantic);
        }
        if (sess != null) {
            result = sess.getSemanticCrossReferencer();
        }
        if (result == null) {
            result = ECrossReferenceAdapter.getCrossReferenceAdapter(cur);
        }
        return result;
    }

    private Integer handleExpressionResult(final Object result) {
        if (result != null) {
            final Collection<Object> input;
            if (result instanceof Collection) {
                input = (Collection) result;
            } else {
                input = new ArrayList<Object>(1);
                input.add(result);
            }
            this.valuesViewer.setInput(input);
            return input.size();
        } else {
            this.valuesViewer.setInput(null);
            return 0;
        }

    }

    /**
     * This method initializes variablesForm
     * 
     */
    private void createVariablesForm() {
        final GridData gridData6 = new GridData();
        gridData6.horizontalAlignment = GridData.FILL;
        gridData6.grabExcessHorizontalSpace = true;
        gridData6.grabExcessVerticalSpace = true;
        gridData6.horizontalSpan = 2;
        gridData6.verticalAlignment = GridData.FILL;
        gridData6.widthHint = 150;
        final GridData gridData5 = new GridData();
        gridData5.horizontalAlignment = GridData.CENTER;
        gridData5.verticalAlignment = GridData.CENTER;
        final GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.CENTER;
        gridData4.verticalAlignment = GridData.CENTER;
        final GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 2;
        gridLayout1.makeColumnsEqualWidth = true;

        variablesForm = getFormToolkit().createForm(variablesSection);
        variablesForm.getBody().setLayout(gridLayout1);
        variablesForm.setText("Variables");
        getFormToolkit().decorateFormHeading(variablesForm);

        setVariableButton = getFormToolkit().createButton(variablesForm.getBody(), "Set", SWT.PUSH);
        setVariableButton.setLayoutData(gridData4);
        setVariableButton.addMouseListener(new MouseListener() {
            public void mouseDoubleClick(final MouseEvent arg0) {
                // Nothing happen
            }

            public void mouseDown(final MouseEvent arg0) {
                // Nothing happen
            }

            public void mouseUp(final MouseEvent arg0) {
                // Behaviour of the Set button is to save as a variable the
                // selection in the valuesViewer
                final ISelection selection = valuesViewer.getSelection();
                if (!selection.isEmpty() && selection instanceof TreeSelection) {
                    final TreeSelection treeSelection = (TreeSelection) selection;
                    addVariable(treeSelection.toList());
                }
            }
        });
        unSetVariableButton = getFormToolkit().createButton(variablesForm.getBody(), "Unset", SWT.PUSH);
        unSetVariableButton.setLayoutData(gridData5);
        unSetVariableButton.addMouseListener(new MouseListener() {
            public void mouseDoubleClick(final MouseEvent arg0) {
                // Nothing happens
            }

            public void mouseDown(final MouseEvent arg0) {
                // Nothing happens
            }

            public void mouseUp(final MouseEvent arg0) {
                // Behaviour of the unSet button is to remove the variable
                // selected in the variablesViewer
                final ISelection selection = variablesViewer.getSelection();

                if (!selection.isEmpty() && selection instanceof TreeSelection) {
                    final TreeSelection treeSelection = (TreeSelection) selection;
                    final Object[] selections = treeSelection.toArray();
                    for (Object object : selections) {
                        if (object instanceof Entry) {
                            final Entry<?, ?> entry = (Entry<?, ?>) object;
                            if (entry.getKey().toString().startsWith(variableTag)) {
                                interpreter.unSetVariable(entry.getKey().toString().substring(1));
                            } else {
                                interpreter.unSetVariable(entry.getKey().toString());
                            }
                        }
                    }
                }
            }

        });
        variablesTree = getFormToolkit().createTree(variablesForm.getBody(), SWT.BORDER);
        variablesTree.setLayoutData(gridData6);
        variablesViewer = new TreeViewer(variablesTree);
        variablesViewer.setContentProvider(getContentProvider());
        variablesViewer.setLabelProvider(labelProvider);
    }

    private void addContentAssist(TransactionalEditingDomain transactionalEditingDomain) {
        // Add the completion for OCL and Acceleo expression
        contentInstanceProposalProvider = new ContentInstanceProposalProvider(interpreter);
        contentInstanceProposalProvider.setEditingDomain(transactionalEditingDomain);

        final IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
        if (bindingService.getActiveBindingsFor(contentAssistBinding) != null && bindingService.getActiveBindingsFor(contentAssistBinding).length > 0) {
            final TriggerSequence sequence = bindingService.getActiveBindingsFor(contentAssistBinding)[0];
            KeyStroke keyStroke = getKeyStroke(sequence);
            new ContentProposalAdapter(acceleoExpression, new TextContentAdapter(), contentInstanceProposalProvider, keyStroke, null);
        }
    }

    private KeyStroke getKeyStroke(TriggerSequence sequence) {
        for (Trigger trigger : sequence.getTriggers()) {
            if (trigger instanceof KeyStroke) {
                return (KeyStroke) trigger;
            }
        }
        return null;
    }

}
