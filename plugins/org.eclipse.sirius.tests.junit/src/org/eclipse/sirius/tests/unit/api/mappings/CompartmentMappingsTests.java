/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
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
package org.eclipse.sirius.tests.unit.api.mappings;

import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.compartment.ICompartmentTests;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Compartments-related tests.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class CompartmentMappingsTests extends SiriusDiagramTestCase implements ICompartmentTests {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Ensure that the modeler is valid. It contains 3 kinds of container mappings : FreeForm, List, Vertical Stack and
     * Horizontal Stack.
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

        DDiagram dDiagram = (DDiagram) getRepresentations(VERTICAL_STACK_REPRESENTATION_NAME).iterator().next();
        ContainerMapping regionContainerMapping = getContainerMapping(dDiagram);

        assertEquals("The '" + regionContainerMapping.getName() + "' should displays its children in vertical stack", ContainerLayout.VERTICAL_STACK, regionContainerMapping.getChildrenPresentation());

        final ContainerMapping regionMapping = MappingHelper.getAllContainerMappings(regionContainerMapping).get(0);

        diagnostic = diagnostician.validate(dDiagram.getDescription());
        checkDiagnostic(diagnostic, Diagnostic.OK, 0, 0, "The VSM should be valid.");

        // set mapping to be a vertical stack container mapping
        changeChildrenPresentation(regionMapping, ContainerLayout.VERTICAL_STACK);

        diagnostic = diagnostician.validate(dDiagram.getDescription());
        checkDiagnostic(diagnostic, Diagnostic.ERROR, 2, 1, "The VSM is not valid, a region container mapping can now contains other regions containers but user must be warned.");

        undo();

        // set mapping to be a horizontal stack container mapping
        changeChildrenPresentation(regionMapping, ContainerLayout.HORIZONTAL_STACK);
        diagnostic = diagnostician.validate(dDiagram.getDescription());
        checkDiagnostic(diagnostic, Diagnostic.ERROR, 2, 1, "The VSM is not valid, a region container mapping can now contains other regions containers but user must be warned.");
    }

    private void checkDiagnostic(Diagnostic diagnostic, int globalSeverity, int expectedErrors, int expectedWarning, String message) {
        assertEquals("This diagnostic severity is not expected.", globalSeverity, diagnostic.getSeverity());
        int errors = 0;
        int warnings = 0;
        for (Diagnostic d : diagnostic.getChildren()) {
            if (Diagnostic.ERROR == d.getSeverity()) {
                errors++;
            } else if (Diagnostic.WARNING == d.getSeverity()) {
                warnings++;
            }
        }
        assertEquals(message, expectedErrors, errors);
        assertEquals(message, expectedWarning, warnings);
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
}
