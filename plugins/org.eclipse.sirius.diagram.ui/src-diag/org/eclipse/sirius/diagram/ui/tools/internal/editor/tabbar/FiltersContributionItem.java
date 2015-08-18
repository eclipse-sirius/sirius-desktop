/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractMenuContributionItem#getMenuImage()
     */
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
            EObject diagram = gmfDiagram.getElement();
            if (diagram instanceof DDiagram) {
                super.setDiagram((DDiagram) diagram);
                if (!((DDiagram) diagram).getActivatedFilters().isEmpty()) {
                    return DiagramUIPlugin.Implementation.getDecoratedCheckedImage(DESC_FILTER);
                }
            }
        }
        return DiagramUIPlugin.getPlugin().getImage(DESC_FILTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getLabel() {
        return "Filters";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractMenuContributionItem#menuShow(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    protected void menuShow(IMenuManager manager) {
        for (final FilterDescription filter : getFilters()) {
            addFilterMenuItem(manager, filter);
        }
    }

    private Collection<FilterDescription> getFilters() {
        final DiagramDescription diagramDesc = diagram.getDescription();
        return diagramDesc.getFilters();
    }

    private void addFilterMenuItem(IMenuManager manager, final FilterDescription filter) {
        final boolean isActive = isActive(filter);
        IAction action = new Action(new IdentifiedElementQuery(filter).getLabel(), IAction.AS_CHECK_BOX) {
            @Override
            public void run() {
                final Runnable change = new ChangeFilterActivation(part, diagram, filter, !isActive);
                change.run();
            }
        };
        action.setChecked(isActive);
        manager.add(action);
    }

    private boolean isActive(FilterDescription filter) {
        final List<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        return activatedFilters.contains(filter);
    }

}
