/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.ResetToDefaultFiltersAction;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.ChangeFilterActivation;
import org.eclipse.swt.graphics.Image;

/**
 * Contribute a menu to tab bar to activate and deactivate filters.
 * 
 * @author mchauvin
 */
public class FiltersContributionItem extends AbstractMenuContributionItem {

    /** The filters icon descriptor. */
    private static final ImageDescriptor DESC_FILTER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/filters.gif"); //$NON-NLS-1$

    @Override
    protected Image getMenuImage() {
        return getImage();
    }

    /**
     * @return the decorated image
     */
    private Image getImage() {
        final Diagram gmfDiagram = this.part.getDiagram();
        if (gmfDiagram != null) {
            try {
                EObject diagram = gmfDiagram.getElement();
                if (diagram instanceof DDiagram) {
                    super.setDiagram((DDiagram) diagram);
                    if (!((DDiagram) diagram).getActivatedFilters().isEmpty()) {
                        return DiagramUIPlugin.Implementation.getDecoratedCheckedImage(DESC_FILTER);
                    }
                }
            } catch (IllegalStateException e) {
                // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
            }   
        }
        return DiagramUIPlugin.getPlugin().getImage(DESC_FILTER);
    }

    @Override
    protected String getLabel() {
        return Messages.FiltersContributionItem_label;
    }

    @Override
    protected void menuShow(IMenuManager manager) {
        for (final FilterDescription filter : getFilters()) {
            addFilterMenuItem(manager, filter);
        }
        ConcernDescription defaultConcern = diagram.getDescription().getDefaultConcern();
        if (defaultConcern != null) {
            manager.add(new Separator());
            manager.add(new ResetToDefaultFiltersAction(session.getTransactionalEditingDomain(), diagram));
        }
    }

    private Collection<FilterDescription> getFilters() {
        final DiagramDescription diagramDesc = diagram.getDescription();
        return diagramDesc.getFilters();
    }

    private void addFilterMenuItem(IMenuManager manager, final FilterDescription filter) {
        final boolean isActive = isActive(filter);
        final String nameEntry = MessageTranslator.INSTANCE.getMessage(filter, new IdentifiedElementQuery(filter).getLabel());
        IAction action = new RunnableAction(nameEntry, IAction.AS_CHECK_BOX, new ChangeFilterActivation(part, diagram, filter, !isActive));
        action.setChecked(isActive);
        manager.add(action);
    }

    private boolean isActive(FilterDescription filter) {
        final List<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        return activatedFilters.contains(filter);
    }

    /**
     * An action whose behavior is defined by a plain {@link Runnable}.
     */
    private static class RunnableAction extends Action {
        private final Runnable runnable;

        RunnableAction(String text, int style, Runnable runnable) {
            super(text, style);
            this.runnable = runnable;
        }

        @Override
        public void run() {
            runnable.run();
        }
    }

}
