package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This listener will react to activation events on this view in order to avoid some visual glitches appearing when
 * closing then reopening the view.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ActivationListener implements IPartListener {
    /** This interpreter view. */
    private final InterpreterView view;

    /**
     * Constructs this listener.
     * 
     * @param view
     *            The view which activation we are interested in.
     */
    public ActivationListener(InterpreterView view) {
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
