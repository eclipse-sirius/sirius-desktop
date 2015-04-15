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
package org.eclipse.sirius.tests.unit.api.mappings;

import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Compartments-related tests.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class CompartmentsTests extends SiriusDiagramTestCase {

    private static final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compartments/My.ecore";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compartments/My.aird";

    private static final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compartments/compartments.odesign";

    private static final String HORIZONTAL_STACK_REPRESENTATION_NAME = "Diag with HStack";

    private static final String VERTICAL_STACK_REPRESENTATION_NAME = "Diag with VStack";

    private static final String VERTICAL_STACK = "VerticalStack";

    private static final String HORIZONTAL_STACK = "HorizontalStack";

    private DDiagram dDiagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Ensure that the modeler is valid. It contains 3 kinds of container
     * mappings : FreeForm, List, Vertical Stack and Horizontal Stack.
     */
    public void testVSMValidation() {
        ResourceSet set = new ResourceSetImpl();

        Group modeler = null;

        try {
            modeler = (Group) ModelUtils.load(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/unit/vsm/valideVSM.odesign", true), set);
        } catch (IOException e) {
            fail("Check the test data.");
        }

        // In this case the modeler is valid, so test if diagnostic is ok.
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(modeler);
        assertEquals("The VSM is valid, it should not have popup error message", Diagnostic.OK, diagnostic.getSeverity());
    }

    /**
     * Ensure that a compartment does not contain another one.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testCompartmentMappingsRecursion() throws Exception {
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = null;

        DDiagram representation = getRepresentation(VERTICAL_STACK);
        ContainerMapping regionContainerMapping = getContainerMapping(representation);

        assertEquals("The '" + regionContainerMapping.getName() + "' should displays its children in vertical stack", ContainerLayout.VERTICAL_STACK, regionContainerMapping.getChildrenPresentation());

        final ContainerMapping regionMapping = regionContainerMapping.getAllContainerMappings().get(0);

        // set mapping to be a vertical stack container mapping
        changeChildrenPresentation(regionMapping, ContainerLayout.VERTICAL_STACK);

        diagnostic = diagnostician.validate(representation.getDescription());
        assertEquals("The VSM is not valid, a region container mapping should not contain another region container mapping", Diagnostic.ERROR, diagnostic.getSeverity());
        undo();

        // set mapping to be a horizontal stack container mapping
        changeChildrenPresentation(regionMapping, ContainerLayout.HORIZONTAL_STACK);
        diagnostic = diagnostician.validate(representation.getDescription());
        assertEquals("The VSM is not valid, a region container mapping should not contain another region container mapping", Diagnostic.ERROR, diagnostic.getSeverity());

    }

    /**
     * Change container children presentation mapping.
     */
    private void changeChildrenPresentation(final ContainerMapping containerMapping, final ContainerLayout containerLayout) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerMapping.setChildrenPresentation(containerLayout);
            }
        });
    }

    /**
     * Get the first container mapping.
     * 
     * @param representation
     *            the given diagram representation
     * @return a container mapping
     */
    private ContainerMapping getContainerMapping(DDiagram representation) {
        DDiagramElement dDiagramElement = representation.getContainers().get(0);
        DiagramElementMapping diagramElementMapping = dDiagramElement.getDiagramElementMapping();

        assertTrue("The '" + diagramElementMapping.getLabel() + "' mapping should be a ContainerMapping", diagramElementMapping instanceof ContainerMapping);
        ContainerMapping containerMapping = (ContainerMapping) diagramElementMapping;
        return containerMapping;
    }

    /**
     * Get the diagram representation which owns the given label.
     */
    private DDiagram getRepresentation(String representationName) {
        if (representationName == HORIZONTAL_STACK) {
            dDiagram = (DDiagram) getRepresentations(HORIZONTAL_STACK_REPRESENTATION_NAME).iterator().next();
        } else {
            dDiagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        }
        return dDiagram;
    }
}
