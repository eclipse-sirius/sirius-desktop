/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.internal.actions.repair.RepresentationFilesRepairAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tests for the repair process.
 * 
 * @author ymortier
 */
public class RepairTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/repair/";

    private static final String MODELER_MODEL_SOURCE_FILENAME = "source.odesign";

    private static final String MODELER_MODEL_TARGET_FILENAME = "target.odesign";

    private static final String SEMANTIC_MODEL_FILENAME = "MigrationCCE.uml";

    private Resource modelerSourceResource;

    private Resource modelerTargetResource;

    private Viewpoint viewpointSource;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_MODEL_SOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_SOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_MODEL_TARGET_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_TARGET_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);

        ResourceSet resourceSet = new ResourceSetImpl();

        URI modelerSourceResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_SOURCE_FILENAME, true);
        URI modelerTargetResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_TARGET_FILENAME, true);

        modelerSourceResource = resourceSet.getResource(modelerSourceResourceURI, true);
        modelerTargetResource = resourceSet.getResource(modelerTargetResourceURI, true);

        Group groupSource = (Group) modelerSourceResource.getContents().get(0);
        viewpointSource = groupSource.getOwnedViewpoints().get(0);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, Collections.<String> emptySet());
    }

    /**
     * Reproduces an issue. A class cast exception is thrown when a style
     * changes from Bundled Image to Square.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testStyleChangeFromBIToSquare() throws Exception {
        viewpoints.add(viewpointSource);
        activateViewpoint(viewpointSource.getName());

        final Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), semanticModel);

        assertEquals("Invalid number of descriptions", 1, descriptions.size());
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                final DRepresentation representation = DialectManager.INSTANCE.createRepresentation("test", semanticModel, descriptions.iterator().next(), session,
                        new NullProgressMonitor());
                DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
            }
        });
        URI sessionResourceURI = session.getSessionResource().getURI();
        session.close(new NullProgressMonitor());

        modelerSourceResource.getContents().clear();
        modelerSourceResource.getContents().add(modelerTargetResource.getContents().get(0));
        try {
            modelerSourceResource.save(Collections.<Object, Object> emptyMap());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        final RepresentationFilesRepairAction repairAction = new RepresentationFilesRepairAction();

        IPath aird = new Path(sessionResourceURI.toPlatformString(true));
        final ISelection selection = new StructuredSelection(ResourcesPlugin.getWorkspace().getRoot().findMember(aird));
        repairAction.selectionChanged(null, selection);
        repairAction.run(null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        modelerSourceResource = null;
        modelerTargetResource = null;
        viewpointSource = null;
        super.tearDown();
    }
}
