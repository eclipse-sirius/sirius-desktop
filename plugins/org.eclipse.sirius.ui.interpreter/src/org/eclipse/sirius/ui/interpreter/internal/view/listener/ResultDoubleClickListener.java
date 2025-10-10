package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.sirius.ui.interpreter.internal.view.GeneratedTextDialog;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterFile;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterFileStorage;
import org.eclipse.sirius.ui.interpreter.internal.view.StorageEditorInput;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;

/**
 * This will allow us to react to double click events in the result view in order to display the long Strings and
 * generated files in a more suitable way.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResultDoubleClickListener implements IDoubleClickListener {
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
