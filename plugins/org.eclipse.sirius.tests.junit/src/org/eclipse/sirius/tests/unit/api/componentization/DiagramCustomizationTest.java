/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.componentization;

import java.text.MessageFormat;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tests for diagram customization through diagram extension descriptions.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class DiagramCustomizationTest extends SiriusDiagramTestCase {
    private static final String PATH = "/data/unit/componentization/";

    private static final String SEMANTIC_MODEL = "vp-1653.ecore";

    private static final String SESSION_MODEL = "vp-1653.aird";

    private static final String VSM = "vp-1653.odesign";

    private Viewpoint baseSirius;

    private Viewpoint extSirius;

    private DNode node;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + VSM, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL, TEMPORARY_PROJECT_NAME + "/" + VSM, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL);

        baseSirius = findViewpoint("Base").get();
        extSirius = findViewpoint("Extension").get();

        // The test model contains a single diagram with one class "A".
        DDiagram diagram = (DDiagram) getRepresentations("BaseDiagram").iterator().next();
        node = (DNode) getDiagramElementsFromLabel(diagram, "A").get(0);
    }

    /**
     * Tests that enabling a Sirius which contributes an import mapping to a
     * node in a layer which is enabled by default correctly updates the node's
     * mapping to point to the import from the extension.
     */
    public void testEnablingSiriusWithExtension() {
        /*
         * With just the "Base" Sirius enabled initially, the node's mapping
         * should obviously come from that Sirius.
         */
        assertSiriusOrigin(baseSirius, node.getActualMapping());
        assertInstanceOf(NodeMapping.class, node.getActualMapping());
        /*
         * Enable the Sirius with the import mapping.
         */
        enabledExtensionSirius();
        /*
         * The diagram extension contains (in a layer enabled by default) a node
         * mapping import which overrides the A's mapping from "Base". The
         * node's mapping should now be the import from the "Extension"
         * Sirius.
         */
        assertSiriusOrigin(extSirius, node.getActualMapping());
        assertInstanceOf(NodeMappingImport.class, node.getActualMapping());
    }

    /**
     * It used to be that the behavior tested by
     * {@link #testEnablingSiriusWithExtension()} worked only the first time
     * the extension Sirius was enabled in an analysis. This test makes sure
     * it continues to work afterwards.
     * 
     * See VP-1653
     */
    public void testEnablingSiriusWithExtensionTwice() {
        testEnablingSiriusWithExtension();
        disableExtensionSirius();
        testEnablingSiriusWithExtension();
    }

    private void assertSiriusOrigin(Viewpoint expectedOrigin, EObject element) {
        Option<EObject> vp = new EObjectQuery(element).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getViewpoint());
        if (vp.some() && vp.get() instanceof Viewpoint) {
            Viewpoint actualSirius = (Viewpoint) vp.get();
            Viewpoint expectedInstanceInSession = SiriusResourceHelper.getCorrespondingViewpoint(session, expectedOrigin);
            assertSame(MessageFormat.format("The element {0} does not come from the expected Sirius definition ({1}).", element, expectedOrigin.getName()), expectedInstanceInSession,
                    actualSirius);
        } else {
            fail("The element does not belong to a Sirius definition");
        }
    }

    private void assertInstanceOf(Class<?> expectedType, Object object) {
        assertTrue(MessageFormat.format("Object {0} is not of the expected type {1}.", object, expectedType), expectedType.isInstance(object));
    }

    private void enabledExtensionSirius() {
        Command changeSiriussSelectionCmd = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(extSirius), Collections.<Viewpoint> emptySet(),
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelectionCmd);
    }

    private void disableExtensionSirius() {
        Command changeSiriussSelectionCmd = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(extSirius),
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelectionCmd);
    }
}
