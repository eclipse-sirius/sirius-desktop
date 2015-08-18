/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.tools.api.action.ConcernComboContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.FiltersContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.LayersContribution;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 * ExtensionContributionFactory responsible for layer and filter tabbar item
 * creation.
 * 
 * @author fbarbin
 */
public class LayerFilterExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        super.createContributionItems(serviceLocator, additions);
        FiltersContributionItem filtersContribution = new FiltersContributionItem();
        ContributionItem filterItem = filtersContribution.createContributionItem(getManager());
        filtersContribution.setPart(getPart());

        LayersContribution layersContribution = new LayersContribution();
        ContributionItem layerItem = layersContribution.createContributionItem(getManager());
        layersContribution.setPart(getPart());

        additions.addContributionItem(layerItem, new DDiagramTabbarExpression());
        additions.addContributionItem(filterItem, new DDiagramTabbarExpression());

        if (getPart() instanceof DDiagramEditor) {
            final DDiagram editorDiagram = (DDiagram) ((DDiagramEditor) getPart()).getRepresentation();
            DiagramDescription description = null;
            if (editorDiagram != null) {
                description = editorDiagram.getDescription();
            }

            if (description != null) {
                addConcernItem(editorDiagram, description, additions);
            }
        }

    }

    private void addConcernItem(final DDiagram diagram, final DiagramDescription description, IContributionRoot additions) {
        if (description.getConcerns() != null && !description.getConcerns().getOwnedConcernDescriptions().isEmpty() && description.getConcerns().getOwnedConcernDescriptions().size() != 1) {
            ConcernComboContributionItem item = new ConcernComboContributionItem(getPage(), ""); //$NON-NLS-1$
            if (diagram != null) {
                item.setDiagram(diagram);
            }
            additions.addContributionItem(item, new DDiagramTabbarExpression());
        }
    }

}
