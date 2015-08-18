/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES
 * All rights reserved.
 *
 * Contributors:
 *      Obeo - Initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.BasicDecorationNode;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A {@link AbstractRepresentationsFileMigrationParticipant} that makes sure
 * that the {@link NotationPackage#getView_Element()} feature is set to NIL
 * (i.e. eIsSet() returns true but value is null). Otherwise, such views would
 * have the SemanticDiagram as semantic element, and hence a delete would delete
 * the diagram.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class SetGMFViewsToNillMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("6.9.0.201309101020"); //$NON-NLS-1$

    /**
     * 
     * {@inheritDoc}
     * 
     * @see fr.obeo.dsl.viewpoint.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see fr.obeo.dsl.viewpoint.business.api.migration.AbstractRepresentationsFileMigrationParticipant#postLoad(fr.obeo.dsl.viewpoint.DAnalysis,
     *      org.osgi.framework.Version)
     */
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            // Step 1: get all views to update
            final Collection<View> allViewsToUpdate = Sets.newLinkedHashSet();
            for (DView view : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(view.getOwnedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                        Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                        allViewsToUpdate.addAll(getViewsToUpdate(gmfDiagram));
                    }
                }
            }
            // Step 2: update views
            for (View viewToUpdate : allViewsToUpdate) {
                viewToUpdate.setElement(null);
            }
        }
        super.postLoad(dAnalysis, loadedVersion);
    }

    /**
     * Returns all {@link View} which {@link NotationPackage#getView_Element()}
     * feature is null, by explicitly setting this feature to null so the
     * eIsSet() returns true. This simulates the behavior that would occur if
     * '<element xsi:nil="true"/>' was set (which is the expected behavior).
     * 
     * @param gmdDiagram
     *            the {@link Diagram} in which Views should be updated
     * @return all views to update
     */
    private Collection<View> getViewsToUpdate(Diagram gmdDiagram) {
        Collection<View> viewsToUpdate = Sets.newLinkedHashSet();
        TreeIterator<EObject> childIterator = gmdDiagram.eAllContents();
        EReference elementReference = NotationPackage.eINSTANCE.getView_Element();
        while (childIterator.hasNext()) {
            EObject child = childIterator.next();
            if (child instanceof View && !child.eIsSet(elementReference)) {
                boolean childIsANoteOrAText = child instanceof Shape && (ViewType.NOTE.equals(((Shape) child).getType()) || ViewType.TEXT.equals(((Shape) child).getType()));
                boolean childIsANoteAttachement = child instanceof Connector && ViewType.NOTEATTACHMENT.equals(((Connector) child).getType());
                boolean childIsADecorationNode = child instanceof BasicDecorationNode;
                if (childIsANoteOrAText || childIsANoteAttachement || childIsADecorationNode) {
                    viewsToUpdate.add((View) child);
                }
            }
        }
        return viewsToUpdate;
    }
}
