/*******************************************************************************
 * Copyright (c) 2010-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeMenuManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.ArrangeBorderedNodesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeAllOnlyLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An arrange menu manager which handle cleanly {@link IDisposableAction} and
 * set correctly handler for tabbar.
 * 
 * @author mchauvin
 */
@SuppressWarnings("restriction")
public class TabbarArrangeMenuManager extends ArrangeMenuManager implements ISelectionListener {
    private IWorkbenchPart representationPart;

    /**
     * constructor.
     * 
     * the current workbench page.
     * 
     * @param iDiagramWorkbenchPart
     *            the current workbench part.
     */
    public TabbarArrangeMenuManager(IDiagramWorkbenchPart iDiagramWorkbenchPart) {
        this.representationPart = iDiagramWorkbenchPart;
        EclipseUIUtil.addSelectionListener(this.representationPart, this);
    }

    @Override
    public void add(IAction action) {
        if (action instanceof DiagramAction) {
            add(new DiagramActionContributionItem((DiagramAction) action));
        } else {
            add(new ActionContributionItem(action));
        }
        if (action instanceof IDisposableAction) {
            ((IDisposableAction) action).init();
        }
    }

    @Override
    protected void itemRemoved(IContributionItem item) {
        if (item instanceof ActionContributionItem) {
            IAction action = ((ActionContributionItem) item).getAction();
            if (action instanceof IDisposableAction) {
                ((IDisposableAction) action).dispose();
            }
        }
    }

    @Override
    public void dispose() {
        if (representationPart != null) {
            EclipseUIUtil.removeSelectionListener(representationPart, this);
        }

        removeAll();
        representationPart = null;
        super.dispose();
    }

    /**
     * Set the default action id for this menu manager.
     * 
     * @param actionId
     *            the action id to set
     */
    public void setDefaultAction(String actionId) {
        for (final IContributionItem item : getItems()) {
            if (item instanceof ActionContributionItem) {
                if (actionId.equals(((ActionContributionItem) item).getAction().getId())) {
                    final IAction defaultAction = ((ActionContributionItem) item).getAction();
                    setHandler(defaultAction);
                    super.setDefaultAction(defaultAction);
                    return;
                }
            }
        }
    }

    /**
     * We should use reflection to access the default handler method
     * 
     * @param defaultAction
     *            the default action to set
     */
    private void setHandler(final IAction defaultAction) {
        Method method;
        try {
            method = MenuCreatorAction.class.getDeclaredMethod("setActionHandler", IAction.class); //$NON-NLS-1$
            method.setAccessible(true);
            method.invoke(super.action, defaultAction);
        } catch (SecurityException e) {
            /* do nothing should not happen */
        } catch (NoSuchMethodException e) {
            /* do nothing should not happen */
        } catch (IllegalArgumentException e) {
            /* do nothing should not happen */
        } catch (IllegalAccessException e) {
            /* do nothing should not happen */
        } catch (InvocationTargetException e) {
            /* do nothing should not happen */
        }
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null && representationPart != null && representationPart.equals(part)) {
            refreshArrangeMenu(page, selection);
        }
    }

    /**
     * Refresh the "arrange" menu.
     * 
     * @param page
     *            the workbench page (not null)
     * @param selection
     *            the current selection
     */
    private void refreshArrangeMenu(IWorkbenchPage page, ISelection selection) {
        boolean arrangeSelection = false;
        if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstElement instanceof IGraphicalEditPart && !(firstElement instanceof DDiagramEditPart)) {
                arrangeSelection = true;
            }
        }

        if (arrangeSelection) {
            if (!isArrangeSelection()) {
                // change to Arrange Selection
                removeArrangeActions();

                ArrangeAction toolbarArrangeSelectionAction = ArrangeAction.createToolbarArrangeSelectionAction(page);
                add(toolbarArrangeSelectionAction);

                disableArrangeSelectionIfNotSupported(toolbarArrangeSelectionAction, (IStructuredSelection) selection);
                setDefaultAction(toolbarArrangeSelectionAction.getId());
            }
        } else {
            if (!isArrangeAllAndBorderedNodes()) {
                // change to Arrange All / Arrange Bordered Nodes
                removeArrangeActions();

                ArrangeAction toolbarArrangeAllAction = ArrangeAction.createToolbarArrangeAllAction(page);
                add(toolbarArrangeAllAction);

                ArrangeBorderedNodesAction toolBarArrangeBorderedNodesAction = ArrangeBorderedNodesAction.createToolBarArrangeBorderedNodesAction(page);
                add(toolBarArrangeBorderedNodesAction);

                setDefaultAction(toolbarArrangeAllAction.getId());
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            // refresh available actions
            refreshArrangeMenu(page, page.getSelection());

            if (visible && representationPart == null) {
                // With Eclipse 4.x, if the menu manager is invisible,
                // it may be disposed
                // Here, the menu manager has been disposed incorrectly.
                // Get the representation part and register again the
                // ISelectionListener.
                representationPart = page.getActivePart();
                if (representationPart != null) {
                    EclipseUIUtil.addSelectionListener(representationPart, this);
                }
            }
        }
    }

    /**
     * Remove all "Arrange" actions: - Arrange Selection - Arrange All - Arrange
     * Linked Bordered Nodes
     */
    private void removeArrangeActions() {
        remove(ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION);
        remove(ActionIds.ACTION_TOOLBAR_ARRANGE_ALL);
        remove(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ARRANGE_BORDERED_NODES_TOOLBAR);
    }

    /**
     * Check that the only available arrange action is "Arrange Selection"
     * 
     * @return true if the only available action is "Arrange Selection"
     */
    private boolean isArrangeSelection() {
        return find(ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION) != null;
    }

    /**
     * Check that there are only two arrange actions when the diagram is
     * selected.
     * 
     * @return true id there are only two arrange actions when the diagram is
     *         selected
     */
    private boolean isArrangeAllAndBorderedNodes() {
        return find(ActionIds.ACTION_TOOLBAR_ARRANGE_ALL) != null && find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ARRANGE_BORDERED_NODES_TOOLBAR) != null;
    }

    /**
     * <p>
     * Disables the given action if the diagram containing the selected elements
     * doesn't support arrange selection Action.
     * </p>
     * <p>
     * <b>Note</b> : any diagram associated to a LayoutNodeProvider which
     * implements ArrangeAllOnlyLayoutProvider does not support ArrangeSelection
     * Action.
     * 
     * @param toolbarArrangeSelectionAction
     *            the action
     * @param selection
     *            the current selection
     */
    private void disableArrangeSelectionIfNotSupported(ArrangeAction createToolbarArrangeSelectionAction, IStructuredSelection selection) {
        // Step 1 : We get the diagram's edit part associated to the selected
        // elements
        IGraphicalEditPart graphicalElement = null;

        Iterator<?> iterator = selection.iterator();
        while (iterator.hasNext() && (graphicalElement == null)) {
            Object next = iterator.next();
            if (next instanceof IGraphicalEditPart) {
                graphicalElement = (IGraphicalEditPart) next;
                break;
            }
        }

        if (graphicalElement != null && graphicalElement.isActive() && graphicalElement.getRoot() != null) {
            RootEditPart root = graphicalElement.getRoot();
            if (!root.getChildren().isEmpty() && root.getChildren().iterator().next() instanceof IGraphicalEditPart) {
                IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) root.getChildren().iterator().next();

                // Step 2 : we get the LayoutNodeProvider associated to this
                // diagramEditPart
                AbstractLayoutEditPartProvider layoutNodeProvider = LayoutService.getProvider(diagramEditPart).getLayoutNodeProvider(diagramEditPart);
                // Step 3 : we disable the action if the LayoutNodeProvider
                // associated to this editPart implements
                // ArrangeAllOnlyLayoutProvider
                if (layoutNodeProvider instanceof ArrangeAllOnlyLayoutProvider) {
                    createToolbarArrangeSelectionAction.setEnabled(false);
                }
            }
        }
    }
}
