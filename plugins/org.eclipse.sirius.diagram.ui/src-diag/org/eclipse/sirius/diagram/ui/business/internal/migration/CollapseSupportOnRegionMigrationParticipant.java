/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Add a GMF {@link DrawerStyle} instance on existing {@link View} with an
 * element that is an instance of {@link DDiagramElementContainer} and is a
 * specified as a Region/compartiment.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 *
 */
public class CollapseSupportOnRegionMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("10.0.0.201505131200"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            EClass drawerStyleClass = NotationPackage.eINSTANCE.getDrawerStyle();

            // Step 1: get all view to update
            final Collection<View> allViewsToUpdate = Sets.newLinkedHashSet();
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(dView.getOwnedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                        Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                        for (Node node : Lists.newArrayList(Iterators.filter(Iterators.filter(gmfDiagram.eAllContents(), Node.class), new IsRegionCompartmentPredicate()))) {
                            Style style = node.getStyle(drawerStyleClass);
                            if (style == null) {
                                allViewsToUpdate.add(node);
                            }
                        }
                    }
                }
            }

            // Step 2: update views
            for (View viewToUpdate : allViewsToUpdate) {
                DrawerStyle style = (DrawerStyle) drawerStyleClass.getEPackage().getEFactoryInstance().create(drawerStyleClass);
                style.setCollapsed(false);
                viewToUpdate.getStyles().add(style);
            }
        }
    }

    /**
     * A predicate that checks if the element represents the compartiment node
     * of a region.
     * 
     * @author mporhel
     * 
     */
    private class IsRegionCompartmentPredicate implements Predicate<Node> {

        public boolean apply(Node node) {
            int id = SiriusVisualIDRegistry.getVisualID(node.getType());
            if (id == DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID || id == DNodeListViewNodeListCompartmentEditPart.VISUAL_ID) {
                EObject element = node.getElement();
                return element instanceof DDiagramElementContainer && new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) element).isRegion();
            }
            return false;
        }

    }
}
