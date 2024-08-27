/*******************************************************************************
 * Copyright (c) 2008, 2024 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.view.IExpandSelectionTarget;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Static useful eclipse ui methods.
 * 
 * @author mchauvin
 */
public final class EclipseUIUtil {

    /**
     * Avoid instantiation.
     */
    private EclipseUIUtil() {

    }

    private static IWorkbenchWindow getActiveWindow() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            final RunnableWithResult<IWorkbenchWindow> getActiveWorkbenchWindowRunnable = new RunnableWithResult.Impl<IWorkbenchWindow>() {
                @Override
                public void run() {
                    setResult(workbench.getActiveWorkbenchWindow());
                }
            };
            if (null == Display.getCurrent()) {
                // Getting the active workbench window through UI thread
                workbench.getDisplay().asyncExec(getActiveWorkbenchWindowRunnable);
            } else {
                // Already in the UI thread.
                getActiveWorkbenchWindowRunnable.run();
            }
            return getActiveWorkbenchWindowRunnable.getResult();
        }
        return null;
    }

    /**
     * Get the active page.
     * 
     * @return the active page or <code>null</code>
     */
    public static IWorkbenchPage getActivePage() {
        final IWorkbenchWindow window = EclipseUIUtil.getActiveWindow();
        if (window != null) {
            return window.getActivePage();
        }
        return null;
    }

    /**
     * Get the active editor.
     * 
     * @return the active editor or <code>null</code>
     */
    public static IEditorPart getActiveEditor() {
        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            return page.getActiveEditor();
        }
        return null;
    }

    /**
     * Shows the view identified by the given view id in this page and gives it
     * focus. If there is a view identified by the given view id (and with no
     * secondary id) already open in this page, it is given focus.
     * 
     * @param viewId
     *            the id of the view extension to use
     * @return the shown view
     */
    public static IViewPart showView(final String viewId) {
        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            try {
                return page.showView(viewId);
            } catch (final PartInitException e) {
                SiriusTransPlugin.getPlugin().error(MessageFormat.format(Messages.EclipseUIUtil_showView_error, viewId), e);
            }
        }
        return null;
    }

    /**
     * Hides the view identified by the given view id in this page.
     * 
     * @param viewId
     *            the id of the view extension to use
     */
    public static void hideView(final String viewId) {
        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            IViewPart view = page.findView(viewId);
            if (view != null) {
                page.hideView(view);
            }
        }
    }

    /**
     * Returns the view in the workbench's active window's active page with the
     * specified id.
     * 
     * @param viewId
     *            the id of the view extension to use
     * @return the view, or <code>null</code> if none is found
     */
    public static IViewPart getView(final String viewId) {

        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            return page.findView(viewId);
        }
        return null;
    }

    /**
     * Get the current Eclipse perspective descriptor.
     * 
     * @return the current perspective descriptor, null if it can not be found.
     */
    public static IPerspectiveDescriptor getCurrentPerspectiveDescriptor() {

        IPerspectiveDescriptor currentPerspectiveDescriptor = null;

        IWorkbenchWindow window = EclipseUIUtil.getActiveWindow();
        if (window != null) {
            currentPerspectiveDescriptor = EclipseUIUtil.getPerspectiveDescriptor(window);
        }

        if (currentPerspectiveDescriptor == null) {
            final IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
            for (IWorkbenchWindow window2 : windows) {
                window = window2;
                if (window != null) {
                    currentPerspectiveDescriptor = EclipseUIUtil.getPerspectiveDescriptor(window);
                    if (currentPerspectiveDescriptor != null) {
                        break;
                    }
                }
            }
        }
        return currentPerspectiveDescriptor;
    }

    private static IPerspectiveDescriptor getPerspectiveDescriptor(final IWorkbenchWindow window) {
        IPerspectiveDescriptor currentPerspectiveDescriptor = null;
        final IWorkbenchPage page = window.getActivePage();
        if (page != null) {
            currentPerspectiveDescriptor = page.getPerspective();
        }
        return currentPerspectiveDescriptor;
    }

    /**
     * Attempts to expand the resource in all parts within the supplied
     * workbench window's active page (as in
     * <code>BasicNewResourceWizard.selectAndReveal</code>).
     * <p>
     * Checks all parts in the active page to see if they implement
     * <code>IExpandSelectionTarget</code>, either directly or as an adapter. If
     * so, tells the part to expand the specified resource.
     * </p>
     * 
     * @param resource
     *            the resource to be expanded
     * @param window
     *            the workbench window to expand the resource
     * 
     * @see IExpandSelectionTarget
     */
    public static void expand(final IResource resource, IWorkbenchWindow window) {
        // validate the input
        if (window == null || resource == null) {
            return;
        }
        IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return;
        }

        // get all the view and editor parts
        List<IWorkbenchPart> parts = new ArrayList<>();
        IWorkbenchPartReference[] refs = page.getViewReferences();
        for (int i = 0; i < refs.length; i++) {
            IWorkbenchPart part = refs[i].getPart(false);
            if (part != null) {
                parts.add(part);
            }
        }
        refs = page.getEditorReferences();
        for (int i = 0; i < refs.length; i++) {
            if (refs[i].getPart(false) != null) {
                parts.add(refs[i].getPart(false));
            }
        }

        Iterator<IWorkbenchPart> itr = parts.iterator();
        while (itr.hasNext()) {
            IWorkbenchPart part = itr.next();

            // get the part's IExpandSelectionTarget implementation
            IExpandSelectionTarget target = null;
            if (part instanceof IExpandSelectionTarget) {
                target = (IExpandSelectionTarget) part;
            } else {
                target = part.getAdapter(IExpandSelectionTarget.class);
            }

            if (target != null) {
                // select and reveal resource
                final IExpandSelectionTarget finalTarget = target;
                window.getShell().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        finalTarget.expand(resource);
                    }
                });
            }
        }
    }

    /**
     * Add a selection listener to the site for the workbench part. Looks for a
     * {@link ISelectionService} by calling
     * {@link org.eclipse.ui.services.IServiceLocator#getService} on the site.
     * 
     * @param part
     *            the workbench part
     * @param listener
     *            the listener to add
     */
    public static void addSelectionListener(IWorkbenchPart part, ISelectionListener listener) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            ISelectionService selectionService = site.getService(ISelectionService.class);
            if (selectionService != null) {
                selectionService.addSelectionListener(listener);
            }
        }
    }

    /**
     * Remove a selection listener from the site for the workbench part. Looks
     * for a {@link ISelectionService} by calling
     * {@link org.eclipse.ui.services.IServiceLocator#getService} on the site.
     * 
     * @param part
     *            the workbench part
     * 
     * @param listener
     *            the listener to remove
     */
    public static void removeSelectionListener(IWorkbenchPart part, ISelectionListener listener) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            ISelectionService selectionService = site.getService(ISelectionService.class);
            if (selectionService != null) {
                selectionService.removeSelectionListener(listener);
            }
        }
    }

    /**
     * Post a runnable to be invoked by the user-interface thread at the next
     * reasonable opportunity.
     * 
     * If a workbench is started its Display instance will be used, otherwhise
     * Display.getDefault() will be used.
     * 
     * @param r
     *            the runnable to execute.
     */
    public static void displayAsyncExec(Runnable r) {
        getCurrentDisplay().asyncExec(r);
    }

    private static Display getCurrentDisplay() {
        Display d;
        if (PlatformUI.isWorkbenchRunning()) {
            d = PlatformUI.getWorkbench().getDisplay();
        } else {
            d = Display.getDefault();
        }
        return d;
    }

    /**
     * Post a runnable to be invoked by the user-interface thread at the next
     * reasonable opportunity. The thread which calls this method is suspended
     * until the runnable completes.
     * 
     * If a workbench is started its Display instance will be used, otherwhise
     * Display.getDefault() will be used.
     * 
     * @param r
     *            the runnable to execute.
     */
    public static void displaySyncExec(Runnable r) {
        getCurrentDisplay().syncExec(r);
    }

    /**
     * Process any event or runnables waiting in the user-interface queue.
     */
    public static void synchronizeWithUIThread() {
        Display d = getCurrentDisplay();
        while (d.readAndDispatch()) {
            /*
             * We wait for the UI thread to process all remaining events.
             */
        }
    }

}
