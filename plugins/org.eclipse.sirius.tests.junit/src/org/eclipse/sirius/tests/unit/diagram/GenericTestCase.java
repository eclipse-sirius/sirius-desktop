/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class GenericTestCase extends SiriusDiagramTestCase {

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    protected DDiagramSynchronizer sync;

    protected void initSynchronizer(final DiagramDescription description, final String diagramName) {
        sync = new DDiagramSynchronizer(interpreter, description, accessor);
        sync.initDiagram(semanticModel, new NullProgressMonitor());
        boolean syncOnCreation = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false, null);
        sync.getDiagram().setSynchronized(syncOnCreation);
        DRepresentationDescriptorInternalHelper.createDRepresentationDescriptor(sync.getDiagram(), (DAnalysisSessionImpl) session, sync.getDiagram().eResource(), diagramName, "");
    }

    /**
     * return the refreshed diagram.
     * 
     * @return the refreshed diagram.
     */
    protected DDiagram getRefreshedDiagram() {
        // Launch the refresh in a recording command because it can change the
        // model.
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                sync.refresh(new NullProgressMonitor());

            }
        });

        return sync.getDiagram();
    }

    /**
     * Find a diagram description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram description or null if not found.
     */
    protected DiagramDescription findDiagramDescription(final String name) {
        for (final Viewpoint vp : viewpoints) {
            Viewpoint viewpoint = getViewpointFromName(vp.getName());
            for (final RepresentationDescription representation : viewpoint.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals(name))
                    return (DiagramDescription) representation;
            }
        }
        return null;
    }

    /**
     * Find a layer by name.
     * 
     * @param description
     *            the parent diagram description.
     * @param name
     *            name to look for.
     * @return the layer or null if not found.
     */
    protected Layer findLayer(final DiagramDescription description, final String name) {
        for (final Layer layer : DiagramComponentizationTestSupport.getAllLayers(session, description)) {
            if (layer.getName().equals(name))
                return layer;
        }
        throw new IllegalArgumentException(name + "  is not valid layer name");
    }

    /**
     * Find a diagram extension description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram extension description or null if not found.
     */
    protected DiagramExtensionDescription findDiagramExtensionDescription(final String name) {
        for (final Viewpoint vp : viewpoints) {
            for (final RepresentationExtensionDescription representationExt : vp.getOwnedRepresentationExtensions()) {
                if (representationExt instanceof DiagramExtensionDescription && ((DiagramExtensionDescription) representationExt).getName().equals(name))
                    return (DiagramExtensionDescription) representationExt;
            }
        }
        return null;
    }

    protected DDiagram getFirstDiagram(final DiagramDescription desc, final Session theSession) {
        final Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(desc, theSession);
        return (DDiagram) representations.toArray()[0];
    }

    protected Diagram getGMFDiagram(final DDiagram diagram, final Session theSession) {
        final Collection<EObject> data = theSession.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
        assertTrue(!data.isEmpty());
        return (Diagram) data.toArray()[0];
    }

    protected int getNumberOfDiagrams(final DiagramDescription description) {
        return DialectManager.INSTANCE.getRepresentations(description, session).size();
    }

    protected int getNumberOfDiagramElements(final DDiagram diagram) {
        return diagram.getOwnedDiagramElements().size();
    }

    protected int getNumberOfNodes(final DDiagram diagram) {
        int sum = 0;
        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            if (element instanceof AbstractDNode)
                sum++;
        }
        return sum;
    }

    protected Collection<DEdge> getEdges(final DDiagram diagram) {
        final Collection<DEdge> edges = new HashSet<DEdge>();
        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            if (element instanceof DEdge)
                edges.add((DEdge) element);
        }
        return edges;
    }

    protected int getNumberOfEdges(final DDiagram diagram) {
        int sum = 0;
        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            if (element instanceof DEdge)
                sum++;
        }
        return sum;
    }

    protected void launchRefreshRepresentationCommand(final DRepresentation representation) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, representation));
    }

    protected void launchRefreshCommand() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
                for (final DRepresentation representation : allRepresentations) {
                    DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                }
            }
        });
    }

    protected void launchRefreshCommand(final Session theSession, final TransactionalEditingDomain theDomain) {
        theDomain.getCommandStack().execute(new RecordingCommand(theDomain) {
            @Override
            protected void doExecute() {
                final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(theSession);
                for (final DRepresentation representation : allRepresentations) {
                    DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                }
            }
        });
    }

    protected void doCleanupSession() {
        closeSession(session);
        session = null;
    }
}
