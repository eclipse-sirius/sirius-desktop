/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping;
import org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * Tests to verify that extensions to the core meta-model (new mappings and
 * tools) are available to the Sirius specifiers in the VSM editor menus.
 * 
 * @author pcdavid
 */
public class NewChildMenusExtensionTests extends SiriusDiagramTestCase {
    private SequenceDiagramDescription sdd;

    private Layer layer;

    private ToolSection section;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp();

        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        final Resource res = rs.createResource(URI.createPlatformResourceURI("/DesignerTestProject/test.odesign", true));

        final Group group = org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createGroup();
        group.setName("Test");

        Viewpoint vp = org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createViewpoint();
        vp.setName("Test for #2135");
        group.getOwnedViewpoints().add(vp);

        sdd = DescriptionFactory.eINSTANCE.createSequenceDiagramDescription();
        vp.getOwnedRepresentations().add(sdd);
        layer = org.eclipse.sirius.diagram.description.DescriptionFactory.eINSTANCE.createLayer();
        layer.setName("Default");
        sdd.setDefaultLayer(layer);
        section = ToolFactory.eINSTANCE.createToolSection();
        section.setName("Tool Section");
        layer.getToolSections().add(section);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                res.getContents().add(group);
            }
        });
    }

    public void testSequenceSpecificMappingsAvailable() throws Exception {
        Collection<?> descriptors = AdapterFactoryEditingDomain.getEditingDomainFor(layer).getNewChildDescriptors(layer, null);
        assertNotNull(descriptors);

        assertContainsCommandFor(descriptors, InstanceRoleMapping.class);
        assertContainsCommandFor(descriptors, BasicMessageMapping.class);
        assertContainsCommandFor(descriptors, ReturnMessageMapping.class);
        assertContainsCommandFor(descriptors, CreationMessageMapping.class);
        assertContainsCommandFor(descriptors, DestructionMessageMapping.class);
        assertContainsCommandFor(descriptors, CombinedFragmentMapping.class);
        assertContainsCommandFor(descriptors, InteractionUseMapping.class);
        assertContainsCommandFor(descriptors, ObservationPointMapping.class);
    }

    public void testSequenceSpecificToolsAvailable() throws Exception {
        Collection<?> descriptors = AdapterFactoryEditingDomain.getEditingDomainFor(section).getNewChildDescriptors(section, null);
        assertNotNull(descriptors);
        assertContainsCommandFor(descriptors, ExecutionCreationTool.class);
       /* assertContainsCommandFor(descriptors, LifelineCreationTool.class);*/
        assertContainsCommandFor(descriptors, MessageCreationTool.class);
        assertContainsCommandFor(descriptors, ReorderTool.class);
        assertContainsCommandFor(descriptors, StateCreationTool.class);
        assertContainsCommandFor(descriptors, CombinedFragmentCreationTool.class);
        assertContainsCommandFor(descriptors, OperandCreationTool.class);
        assertContainsCommandFor(descriptors, InteractionUseCreationTool.class);
        assertContainsCommandFor(descriptors, ObservationPointCreationTool.class);

    }

    private void assertContainsCommandFor(Collection<?> descriptors, Class<? extends EObject> klass) {
        Iterable<CommandParameter> cmds = Iterables.filter(descriptors, CommandParameter.class);
        Iterable<EObject> values = Iterables.transform(cmds, new Function<CommandParameter, EObject>() {
            public EObject apply(CommandParameter from) {
                return from.getEValue();
            }
        });
        Iterable<? extends EObject> matches = Iterables.filter(values, klass);
        assertEquals("Missing command for " + klass.getSimpleName(), 1, Iterables.size(matches));
    }
}
