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
package org.eclipse.sirius.tests.unit.diagram.command;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.util.PayloadMarkerAdapter;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Ensure that there is no unwanted access on some semantic element during the
 * construction of the delete command.
 * 
 * This test uses the component meta-model that logs all accesses to unwanted
 * components.
 *
 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=458822
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class BuildDeleteCommandTest extends SiriusDiagramTestCase {
    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.sample.component.design/description/component.odesign";//$NON-NLS-1$

    private static final String PATH = "/data/unit/payload/";//$NON-NLS-1$

    private static final String SEMANTIC_MODEL_PATH = "component.component";//$NON-NLS-1$

    private static final String SESSION_MODEL_PATH = "representations.aird";//$NON-NLS-1$

    private DDiagram componentDiagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initCustomPermissionAuthority();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_PATH, SESSION_MODEL_PATH);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_PATH, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_PATH);

        componentDiagram = (DDiagram) getRepresentations("Diagram").iterator().next();
        assertNotNull("Couldn't find the component diagram.", componentDiagram);
    }

    /**
     * Test IDiagramCommandFactory.buildDeleteDiagramElement(DDiagramElement)
     */
    public void testBuildDeleteCommandElement() {
        DNodeContainer container = getComponentDNodeContainer("c.1");

        clearPayloadAccessLog();
        getCommandFactory().buildDeleteDiagramElement(container);
        assertNoPayloadAccessLog();
    }

    /**
     * Test
     * IDiagramCommandFactory.buildDeleteFromDiagramCommand(DDiagramElement)
     */
    public void testBuildDeleteFromDiagramCommand() {
        DNodeContainer container = getComponentDNodeContainer("c.1");

        clearPayloadAccessLog();
        getCommandFactory().buildDeleteFromDiagramCommand(container);
        assertNoPayloadAccessLog();
    }

    /**
     * Test IDiagramCommandFactory.buildDeleteDiagram(DDiagram)
     */
    public void testBuildDeleteDiagram() {
        clearPayloadAccessLog();
        getCommandFactory().buildDeleteDiagram(componentDiagram);
        assertNoPayloadAccessLog();
    }

    /**
     * Clear all access logs to unwanted semantic elements registered during the
     * test initialization.
     */
    private void clearPayloadAccessLog() {
        PayloadMarkerAdapter.INSTANCE.clearAccessLog();
    }

    /**
     * Ensure that there is no access to unwanted semantic elements.
     */
    private void assertNoPayloadAccessLog() {
        assertTrue("No access for payload should be registered", PayloadMarkerAdapter.INSTANCE.getAccessLog().isEmpty());
    }

    /**
     * Get the {@link DNodeContainer} for one component.
     * 
     * @param componentName
     *            component name
     * @return the container
     */
    private DNodeContainer getComponentDNodeContainer(final String componentName) {
        Iterable<DNodeContainer> containers = Iterables.filter(componentDiagram.getDiagramElements(), DNodeContainer.class);
        DNodeContainer container = Iterables.find(containers, new Predicate<DNodeContainer>() {
            @Override
            public boolean apply(DNodeContainer element) {
                if (element.getTarget() instanceof Component) {
                    Component component = (Component) element.getTarget();
                    if (componentName.equals(component.getName())) {
                        return true;
                    }
                }

                return false;
            }
        });

        assertNotNull("The DNodeContainer for the component " + componentName + " should exist", container);
        return container;
    }

    /**
     * Init Sirius with a {@link ReadOnlyPermissionAuthority}.
     */
    private void initCustomPermissionAuthority() {
        IPermissionAuthority permissionAuthority = PermissionService.createDefaultPermissionAuthority();
        IPermissionProvider permissionProvider = new DefaultPermissionProvider(permissionAuthority);
        StandalonePermissionProviderDescriptor permissionProviderDescriptor = new StandalonePermissionProviderDescriptor("org.eclipse.sirius.tree.tests.forbiddenPermissionAuthorityProvider",
                ExtenderConstants.HIGHEST_PRIORITY, permissionProvider);
        PermissionService.addExtension(permissionProviderDescriptor);
    }

}
