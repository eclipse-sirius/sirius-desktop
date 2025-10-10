/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view.actions;

import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * This action will allow us to have "direct edit" support for the interpreter variables.
 * <p>
 * Inspired from org.eclipse.ui.actions.RenameResourceAction.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class RenameVariableAction extends Action {
    /** Will be set to <code>true</code> whenever we start saving a Variable change. */
    protected boolean saving;

    /** The actual text editor widget. */
    protected Text textEditor;

    /** Parent of our text editor. */
    protected Composite textEditorParent;

    /** Keeps a reference towards the TreeViewer displaying our variables. */
    protected final TreeViewer variableViewer;

    /** The tree editing widget. */
    private TreeEditor treeEditor;

    /**
     * Instantiates a rename action for the given variable TreeViewer.
     * 
     * @param variableViewer
     *            The TreeViewer displaying the variables to rename.
     */
    public RenameVariableAction(TreeViewer variableViewer) {
        super(InterpreterMessages.getString("interpreter.action.renamevariable.name")); //$NON-NLS-1$
        this.variableViewer = variableViewer;
        this.treeEditor = new TreeEditor(getTree());
    }

    /**
     * Returns the Tree this action will affect.
     * 
     * @return The Tree this action will affect.
     */
    public Tree getTree() {
        if (variableViewer == null) {
            return null;
        }
        return variableViewer.getTree();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return getCurrentVariable() != null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Variable currentVariable = getCurrentVariable();

        if (currentVariable != null) {
            runRename(currentVariable);
        }
    }

    /**
     * Close the text widget and reset the editorText field.
     */
    protected void disposeTextWidget() {
        if (textEditorParent != null) {
            textEditorParent.dispose();
            textEditorParent = null;
            textEditor = null;
            treeEditor.setEditor(null, null);
        }
    }

    /**
     * Returns the currently selected Variable, or the first Variable of the current selection. <code>null</code> if
     * none.
     * 
     * @return The currently selected Variable, or the first Variable of the current selection. <code>null</code> if
     *         none.
     */
    protected Variable getCurrentVariable() {
        if (getTree() != null && !getTree().isDisposed()) {
            TreeItem[] selectedtems = getTree().getSelection();
            if (selectedtems != null && selectedtems.length > 0) {
                for (int i = 0; i < selectedtems.length; i++) {
                    TreeItem item = selectedtems[i];
                    if (item.getData() instanceof Variable) {
                        return (Variable) item.getData();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Save the changes and dispose of the text widget.
     * 
     * @param variable
     *            The resource which name is to change.
     */
    protected void saveChangesAndDispose(Variable variable) {
        if (saving) {
            return;
        }

        saving = true;

        final Variable renamedVariable = variable;
        final String newName = textEditor.getText();

        Runnable renameQuery = new Runnable() {
            public void run() {
                try {
                    if (!newName.equals(renamedVariable.getName())) {
                        renamedVariable.setName(newName);
                    }

                    // Dispose the text widget regardless
                    disposeTextWidget();

                    // Ensure the Navigator tree has focus, which it may not if
                    // the text widget previously had focus.
                    if (getTree() != null && !getTree().isDisposed()) {
                        variableViewer.refresh();
                        getTree().setFocus();
                    }
                } finally {
                    saving = false;
                }
            }
        };
        getTree().getShell().getDisplay().asyncExec(renameQuery);
    }

    /**
     * Creates the parent of our text editor.
     * 
     * @return The parent of our text editor.
     */
    private Composite createParent() {
        Tree tree = getTree();
        Composite result = new Composite(tree, SWT.NONE);
        TreeItem[] selectedItems = tree.getSelection();
        treeEditor.horizontalAlignment = SWT.LEFT;
        treeEditor.grabHorizontal = true;
        treeEditor.setEditor(result, selectedItems[0]);
        return result;
    }

    /**
     * Create the text editor widget that will be used for the given Variable.
     * 
     * @param variable
     *            The variable that is to be renamed
     * @see org.eclipse.ui.actions.RenameResourceAction#createTextEditor(org.eclipse.core.resources.IResource)
     */
    private void createTextEditor(final Variable variable) {
        // Create text editor parent. This draws a nice bounding rect.
        textEditorParent = createParent();
        textEditorParent.setVisible(false);

        // border width
        final int inset = 1;
        textEditorParent.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                Point textSize = textEditor.getSize();
                Point parentSize = textEditorParent.getSize();
                e.gc.drawRectangle(0, 0, Math.min(textSize.x + 4, parentSize.x - 1), parentSize.y - 1);
            }
        });

        // Create inner text editor.
        textEditor = new Text(textEditorParent, SWT.NONE);
        textEditor.setFont(getTree().getFont());
        textEditorParent.setBackground(textEditor.getBackground());

        textEditor.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                textSize.x += textSize.y; // Add extra space for new
                // characters.
                Point parentSize = textEditorParent.getSize();
                textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
                textEditorParent.redraw();
            }
        });

        textEditor.addTraverseListener(new TraverseListener() {
            public void keyTraversed(TraverseEvent event) {
                // Workaround for Bug 20214 due to extra
                // traverse events
                switch (event.detail) {
                case SWT.TRAVERSE_ESCAPE:
                    // Do nothing in this case
                    disposeTextWidget();
                    event.doit = true;
                    event.detail = SWT.TRAVERSE_NONE;
                    break;
                case SWT.TRAVERSE_RETURN:
                    saveChangesAndDispose(variable);
                    event.doit = true;
                    event.detail = SWT.TRAVERSE_NONE;
                    break;
                default:
                    break;
                }
            }
        });

        textEditor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                saveChangesAndDispose(variable);
            }
        });
    }

    /**
     * Runs the actual rename action.
     * 
     * @param variable
     *            Variable that is to be renamed.
     */
    private void runRename(Variable variable) {
        if (textEditorParent == null) {
            createTextEditor(variable);
        }
        String currentName = variable.getName();
        if (currentName == null) {
            currentName = ""; //$NON-NLS-1$
        }
        textEditor.setText(currentName);

        // Open text editor with initial size.
        textEditorParent.setVisible(true);
        Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        textSize.x += textSize.y; // Add extra space for new characters.
        Point parentSize = textEditorParent.getSize();
        // border width
        int inset = 1;
        textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
        textEditorParent.redraw();
        textEditor.selectAll();
        textEditor.setFocus();
    }
}
