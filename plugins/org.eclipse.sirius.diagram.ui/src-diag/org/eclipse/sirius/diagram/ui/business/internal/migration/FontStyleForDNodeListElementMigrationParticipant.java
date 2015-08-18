/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Add a GMF {@link FontStyle} instance on existing {@link View} with an element
 * that is an instance of {@link DNodeListElement}. See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=424418
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class FontStyleForDNodeListElementMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("10.0.0.201412231738"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            EClass fontStyleClass = NotationPackage.eINSTANCE.getFontStyle();

            // Step 1: get all view to update
            final Collection<View> allViewsToUpdate = Sets.newLinkedHashSet();
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(dView.getOwnedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                        Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                        TreeIterator<EObject> childIterator = gmfDiagram.eAllContents();
                        while (childIterator.hasNext()) {
                            EObject child = childIterator.next();
                            if (child instanceof View && ((View) child).getElement() instanceof DNodeListElement) {
                                View view = (View) child;
                                Style style = view.getStyle(fontStyleClass);
                                if (style == null) {
                                    allViewsToUpdate.add(view);
                                }

                                // Do not loop inner this view
                                childIterator.prune();
                            }
                        }
                    }
                }
            }

            // Step 2: update views
            for (View viewToUpdate : allViewsToUpdate) {
                FontStyle style = (FontStyle) fontStyleClass.getEPackage().getEFactoryInstance().create(fontStyleClass);
                viewToUpdate.getStyles().add(style);
            }
        }
    }
}
