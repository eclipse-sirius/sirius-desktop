/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeAllOnlyLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * SelectionListener that reacts to any change made on the selection by disable
 * or enable actions from the "Diagram" menu.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DiagramMenuUpdater implements ISelectionListener {

    /**
     * The diagram workbench part.
     */
    private IDiagramWorkbenchPart part;

    /**
     * Creates a new DiagramMenuUpdater.
     * 
     * @param part
     *            the diagram workbench part
     */
    public DiagramMenuUpdater(IDiagramWorkbenchPart part) {
        this.part = part;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IWorkbenchPart partSelected, ISelection selection) {
        if (partSelected == part && selection instanceof IStructuredSelection) {
            final Collection<?> selectedElements = ((IStructuredSelection) selection).toList();
            // We only update the DiagramMenu if more than one element is
            // selected
            if (isRelevantSelection(selectedElements)) {
                // we disable the Arrange Selection Action if needed
                if (arrangeSelectionActionShouldBeDisable(selectedElements)) {
                    disableArrangeSelectionActionFromDiagramMenu();
                }
            }
        }
    }

    /**
     * Indicates if the given selectedElements influence the "Arrange Selection"
     * status.
     * 
     * @param selectedElements
     *            the current selected elements
     * @return true if the given selected elements are relevant, false otherwise
     */
    private boolean isRelevantSelection(Collection<?> selectedElements) {
        return !(selectedElements.size() == 1 && selectedElements.iterator().next() instanceof DiagramEditPart);
    }

    /**
     * Indicates if the given selectedElements implies that the
     * ArrangeSelectionAction should be disable.
     * 
     * @param selectedElements
     *            the current selected elements
     * @return true if the ArrangeSelectionAction should be disable, false
     *         otherwise
     */
    private boolean arrangeSelectionActionShouldBeDisable(Collection<?> selectedElements) {
        boolean arrangeSelectionActionShouldBeDisable = false;
        // Step 1 : We get the diagram's edit part associated to the selected
        // elements
        IGraphicalEditPart graphicalElement = null;
        Iterator<?> iterator = selectedElements.iterator();
        while (iterator.hasNext() && (graphicalElement == null)) {
            Object next = iterator.next();
            if (next instanceof IGraphicalEditPart) {
                graphicalElement = (IGraphicalEditPart) next;
                break;
            }
        }
        if (graphicalElement != null) {
            RootEditPart root = graphicalElement.getRoot();
            if (root != null && (IGraphicalEditPart) root.getChildren().iterator().next() != null) {
                IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) root.getChildren().iterator().next();

                // Step 2 : we get the LayoutNodeProvider associated to this
                // diagramEditPart
                AbstractLayoutEditPartProvider layoutNodeProvider = LayoutService.getProvider(diagramEditPart).getLayoutNodeProvider(diagramEditPart);
                // Step 3 : we disable the action if the LayoutNodeProvider
                // associated to this editPart implements
                // ArrangeAllOnlyLayoutProvider
                arrangeSelectionActionShouldBeDisable = layoutNodeProvider instanceof ArrangeAllOnlyLayoutProvider;
            }
        }
        return arrangeSelectionActionShouldBeDisable;
    }

    /**
     * 
     * Disables "Arrange Selection" Action from the Diagram Menu.
     */
    private void disableArrangeSelectionActionFromDiagramMenu() {
        // Step 1 : we get the arrange Menu manager
        Option<IMenuManager> arrangeMenuManager = getArrangeMenuManager();
        if (arrangeMenuManager.some()) {
            // Step 2 : we get the "Arrange Selection" Action
            IContributionItem arrangeSelectionItem = arrangeMenuManager.get().find(ActionIds.ACTION_ARRANGE_SELECTION);
            if (arrangeSelectionItem instanceof SubContributionItem) {
                IContributionItem arrangeSelectionInnerItem = ((SubContributionItem) arrangeSelectionItem).getInnerItem();
                if (arrangeSelectionInnerItem instanceof ActionContributionItem) {
                    // Step 3 : we disable this action
                    ((ActionContributionItem) arrangeSelectionInnerItem).getAction().setEnabled(false);
                }
            }
        }
    }

    /**
     * Returns the Arrange Menu Manager (contained in the "Diagram" Menu).
     * 
     * @return the Arrange Menu Manager, containing all the items related to
     *         arrange actions
     */
    @SuppressWarnings("restriction")
    private Option<IMenuManager> getArrangeMenuManager() {

        Option<IMenuManager> arrangeMenuManagerOption = Options.newNone();
        MenuManager menuManager = null;

        // Step 1 : we get the active workbench's window's menuManager
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            if (window instanceof WorkbenchWindow) {
                menuManager = ((WorkbenchWindow) window).getMenuManager();
            }
        }

        if (menuManager == null) {
            return arrangeMenuManagerOption;
        }
        // Step 2 : we get the arrangeMenu Manager
        IContributionItem diagramMenu = menuManager.find(ActionIds.MENU_DIAGRAM);
        if (diagramMenu instanceof SubContributionItem) {
            IContributionItem diagramMenuContributionItem = ((SubContributionItem) diagramMenu).getInnerItem();
            if (diagramMenuContributionItem instanceof IMenuManager) {
                IMenuManager diagMenuManager = (IMenuManager) diagramMenuContributionItem;
                IContributionItem arrangeMenu = diagMenuManager.find(ActionIds.MENU_ARRANGE);
                if (arrangeMenu instanceof SubContributionItem) {
                    IContributionItem arrangeMenuContributionItem = ((SubContributionItem) arrangeMenu).getInnerItem();
                    if (arrangeMenuContributionItem instanceof IMenuManager) {
                        arrangeMenuManagerOption = Options.newSome((IMenuManager) arrangeMenuContributionItem);
                    }
                }
            }
        }

        return arrangeMenuManagerOption;
    }

}
