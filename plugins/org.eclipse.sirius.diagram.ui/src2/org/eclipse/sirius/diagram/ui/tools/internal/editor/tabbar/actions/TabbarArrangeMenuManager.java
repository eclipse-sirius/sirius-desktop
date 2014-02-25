/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
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
public class TabbarArrangeMenuManager extends ArrangeMenuManager implements ISelectionListener {

    private IWorkbenchPage page;

    private IWorkbenchPart representationPart;

    private ISelection currentSelection;

    private ArrangeAction toolbarArrangeSelectionAction;

    private ArrangeAction toolbarArrangeAllAction;

    private ArrangeBorderedNodesAction toolBarArrangeBorderedNodesAction;

    /**
     * constructor.
     * 
     * @param page
     *            the current workbench page.
     * @param iDiagramWorkbenchPart
     *            the current workbench part.
     */
    public TabbarArrangeMenuManager(IWorkbenchPage page, IDiagramWorkbenchPart iDiagramWorkbenchPart) {
        this.page = page;
        this.representationPart = iDiagramWorkbenchPart;
        page.addSelectionListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.ContributionManager#add(org.eclipse.jface.action.IAction)
     */
    @Override
    public void add(IAction action) {
        super.add(action);
        if (action instanceof IDisposableAction) {
            ((IDisposableAction) action).init();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager#itemRemoved(org.eclipse.jface.action.IContributionItem)
     */
    @Override
    protected void itemRemoved(IContributionItem item) {
        if (item instanceof ActionContributionItem) {
            IAction action = ((ActionContributionItem) item).getAction();
            if (action instanceof IDisposableAction) {
                ((IDisposableAction) action).dispose();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        currentSelection = null;
        if (page != null) {
            page.removeSelectionListener(this);
            page = null;
        }
        removeAll();
        toolbarArrangeAllAction = null;
        toolBarArrangeBorderedNodesAction = null;
        toolbarArrangeSelectionAction = null;
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
            method = MenuCreatorAction.class.getDeclaredMethod("setActionHandler", IAction.class);
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

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (representationPart != null && representationPart.equals(part)) {
            // arrangeMenu.removeAll();
            if (currentSelection == null || (currentSelection != null && !currentSelection.equals(selection))) {
                currentSelection = selection;
                refreshArrangeMenu(currentSelection);
            }
        }

    }

    private void refreshArrangeMenu(ISelection selection) {
        boolean arrangeSelection = false;
        if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstElement instanceof IGraphicalEditPart && !(firstElement instanceof DDiagramEditPart)) {
                arrangeSelection = true;
            }
        }

        if (arrangeSelection) {
            cleanArrangeDiagram();
            if (find(ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION) == null) {
                toolbarArrangeSelectionAction = ArrangeAction.createToolbarArrangeSelectionAction(page);
                add(toolbarArrangeSelectionAction);
            }
            disableArrangeSelectionIfNotSupported(toolbarArrangeSelectionAction, (IStructuredSelection) selection);
            setDefaultAction(toolbarArrangeSelectionAction.getId());
        } else {
            addDefaultArrangeActions();
        }
    }

    private void addDefaultArrangeActions() {
        cleanArrangeDDiagramElement();
        if (find(ActionIds.ACTION_TOOLBAR_ARRANGE_ALL) == null) {
            toolbarArrangeAllAction = ArrangeAction.createToolbarArrangeAllAction(page);
            add(toolbarArrangeAllAction);
        }
        if (find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ARRANGE_BORDERED_NODES_TOOLBAR) == null) {
            toolBarArrangeBorderedNodesAction = ArrangeBorderedNodesAction.createToolBarArrangeBorderedNodesAction(page);
            add(toolBarArrangeBorderedNodesAction);
        }
        setDefaultAction(toolbarArrangeAllAction.getId());
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isEmpty() && visible && page != null) {

            refreshArrangeMenu(page.getSelection());
        }
    }

    private void cleanArrangeDDiagramElement() {
        if (toolbarArrangeSelectionAction != null) {
            remove(toolbarArrangeSelectionAction.getId());
            toolbarArrangeSelectionAction = null;
        }
    }

    private void cleanArrangeDiagram() {
        if (toolbarArrangeAllAction != null) {
            remove(toolbarArrangeAllAction.getId());
            toolbarArrangeAllAction = null;
        }
        if (toolBarArrangeBorderedNodesAction != null) {
            remove(toolBarArrangeBorderedNodesAction.getId());

            toolBarArrangeBorderedNodesAction = null;
        }
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
     * @param createToolbarArrangeSelectionAction
     */
    private void disableArrangeSelectionIfNotSupported(ArrangeAction createToolbarArrangeSelectionAction, IStructuredSelection selection) {
        // Step 1 : We get the diagram's edit part associated to the selected
        // elements
        IGraphicalEditPart graphicalElement = null;

        Iterator<Object> iterator = selection.iterator();
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
