/*******************************************************************************
 * Copyright (c) 2012, 2014, 2022 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Vincent LORENZO (CEA LIST) - vincent.lorenzo@cea.fr - Bug 580836
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.IToolBarContextService;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * Abstract {@link ExtensionContributionFactory} extension. Provides context
 * access for all extension contribution factory.
 * 
 * @author fbarbin
 */
public abstract class SiriusTabbarExtensionContributionFactory extends ExtensionContributionFactory {

    /**
     * constructor.
     */
    protected SiriusTabbarExtensionContributionFactory() {
        super();
    }

    /**
     * Provides
     * {@link IPermissionAuthority#canEditInstance(org.eclipse.emf.ecore.EObject)}
     * value.
     * 
     * @return true if can edit current editor instance.
     */
    protected boolean canEdit() {
        boolean canEditInstance = true;
        if (getPart() != null) {
            final DDiagramEditor editor = (DDiagramEditor) getPart();

            if (editor != null && editor.getRepresentation() != null) {
                final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editor.getSession().getSessionResource().getResourceSet());
                canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
            }
        }
        return canEditInstance;
    }

    /**
     * Provides the current {@link DDiagram} from {@link DDiagramEditor}.
     * 
     * @return the diagram or null if cannot be retrieve.
     */
    protected DDiagram getDDiagram() {
        final DDiagramEditor editor = (DDiagramEditor) getPart();
        if (editor != null && editor.getRepresentation() instanceof DDiagram) {
            return (DDiagram) editor.getRepresentation();
        }
        return null;
    }

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

    }

    protected IDiagramWorkbenchPart getPart() {
        return IToolBarContextService.INSTANCE.getPart();
    }

    protected IWorkbenchPage getPage() {
        return IToolBarContextService.INSTANCE.getPage();
    }

    protected ToolBarManager getManager() {
        return IToolBarContextService.INSTANCE.getMenuManager();
    }

    /**
     * ActionContributionItem extension to update the action on selection
     * changes, if a given part specified and if the selection changed occurs in
     * this part. It also dispose the action if it extends {@link Disposable}.
     * 
     * @author fbarbin
     * 
     */
    public static class TabbarActionContributionItem extends ActionContributionItem implements ISelectionListener {

        /** The representation part. */
        protected IWorkbenchPart representationPart;

        /**
         * Constructor.
         * 
         * @param action
         *            the action to wrap
         * @param part
         *            the workbench part
         */
        public TabbarActionContributionItem(IAction action, IWorkbenchPart part) {
            super(action);

            this.representationPart = part;
            if (representationPart != null) {
                ISelectionService selectionService = representationPart.getSite().getService(ISelectionService.class);
                if (selectionService != null) {
                    selectionService.addSelectionListener(this);
                }
            }
        }

        /**
         * Constructor.
         * 
         * @param action
         *            the action to wrap
         */
        public TabbarActionContributionItem(IAction action) {
            super(action);
        }

        /**
         * {@inheritDoc}
         * 
         * @See {@link ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)}
         */
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            // we refresh action only if selection is in the same part than
            // current action tabbar.
            if (representationPart != null && part != null && part.getAdapter(representationPart.getClass()) != null) {
                 //try to adapt the part to the current representationPart
                 //see bug 580836, to work in a multipage editor
                 part = part.getAdapter(this.representationPart.getClass());
            }
            if (representationPart != null && representationPart.equals(part)) {
                IAction action = getAction();
                if (action instanceof IActionDelegate) {
                    ((IObjectActionDelegate) action).selectionChanged(action, selection);
                } else if (action instanceof ISelectionListener) {
                    ((ISelectionListener) action).selectionChanged(part, selection);
                }
                update();
            }
        }

        /**
         * {@inheritDoc}
         * 
         */
        @Override
        public void dispose() {
            super.dispose();

            if (representationPart != null) {
                ISelectionService selectionService = representationPart.getSite().getService(ISelectionService.class);
                if (selectionService != null) {
                    selectionService.removeSelectionListener(this);
                }
            }
            representationPart = null;

            IAction action = getAction();
            if (action instanceof IDisposableAction) {
                ((IDisposableAction) action).dispose();
            } else if (action instanceof IWorkbenchAction) {
                ((IWorkbenchAction) action).dispose();
            } else if (action instanceof Disposable) {
                ((Disposable) action).dispose();
            }
        }
    }
}
