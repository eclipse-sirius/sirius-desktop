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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tests of changes of the id (name) or the label (of Sirius, RepresentationDescription and Layer) and check the
 * expected result.
 * 
 * @author lredor
 */
public class ChangeIdAndLabelTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/idAndLabels/test861.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/idAndLabels/ecore.odesign";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/idAndLabels/test861.aird";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
    }

    /**
     * Check that the modification of the id of a viewpoint break the existing diagram.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testModificationOfIdOfSirius() throws Exception {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        final Viewpoint viewpoint = (Viewpoint) diagram.getDescription().eContainer();
        final String newId = viewpoint.getName() + "Modify";
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                viewpoint.setName(newId);
            }
        });
        session.save(new NullProgressMonitor());
        session.close(new NullProgressMonitor());
        session = SessionManager.INSTANCE.getSession(URI.createPlatformPluginURI(REPRESENTATIONS_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());

        Collection<DRepresentation> representations = getRepresentations(REPRESENTATION_DESC_NAME);
        if (representations.size() == 1) {
            assertFalse("The rename of the id of the viewpoint should break the existing diagram (bad number of representations).",
                    newId.equals(((DView) new DRepresentationQuery(representations.iterator().next()).getRepresentationDescriptor().eContainer()).getViewpoint().getName()));
        } else if (representations.size() != 0) {
            fail("The rename of the id of the viewpoint should break the existing diagram (bad number of representations).");
        }
    }

    /**
     * Check that the modification of the label of a viewpoint have no incidence on the existing diagram.
     */
    public void testModificationOfLabelOfSirius() {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        final Viewpoint viewpoint = (Viewpoint) diagram.getDescription().eContainer();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                viewpoint.setLabel(viewpoint.getLabel() + "Modify");
            }
        });
        assertEquals("The rename of the label of the viewpoint break the existing diagram (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
    }

    /**
     * Check that the modification of the id of a representation description break the existing diagram.
     */
    public void testModificationOfIdOfRepresentationDescription() {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        final DiagramDescription diagramDescription = diagram.getDescription();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                diagramDescription.setName(diagramDescription.getName() + "Modify");
            }
        });
        assertEquals("The rename of the id of the diagram description should break the existing diagram (bad number of representations).", 0, getRepresentations(REPRESENTATION_DESC_NAME).size());
    }

    /**
     * Check that the modification of the label of a representation description have no incidence on the existing
     * diagram.
     */
    public void testModificationOfLabelOfRepresentationDescription() {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        final DiagramDescription diagramDescription = diagram.getDescription();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                diagramDescription.setLabel(diagramDescription.getLabel() + "Modify");
            }
        });
        refresh(diagram);
        assertEquals("The rename of the label of diagram description break the existing diagram (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
    }

    /**
     * Check that the modification of the id of a layer break the existing diagram.
     */
    public void testModificationOfIdOfLayer() {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        assertEquals("The data is incorrect (bad number of layers).", 3, LayerModelHelper.getAllLayers(diagram.getDescription()).size());
        int nbDiagramElementsExpected = diagram.getOwnedDiagramElements().size();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                Layer layer = LayerModelHelper.getAllLayers(diagram.getDescription()).get(1);
                layer.setName(layer.getName() + "Modify");
            }
        });
        refresh(diagram);
        assertFalse("The rename of the id of the layer should break the existing diagram (same number of diagram elements).", nbDiagramElementsExpected == diagram.getOwnedDiagramElements().size());
    }

    /**
     * Check that the modification of the label of a layer have no incidence on the existing diagram.
     */
    public void testModificationOfLabelOfLayer() {
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        assertEquals("The data is incorrect (bad number of layers).", 3, LayerModelHelper.getAllLayers(diagram.getDescription()).size());
        int nbDiagramElementsExpected = diagram.getOwnedDiagramElements().size();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                Layer layer = LayerModelHelper.getAllLayers(diagram.getDescription()).get(1);
                layer.setLabel(layer.getLabel() + "Modify");
            }
        });
        assertEquals("The rename of the label of the layer break the existing diagram (bad number of diagram elements).", nbDiagramElementsExpected, diagram.getOwnedDiagramElements().size());
    }
}
