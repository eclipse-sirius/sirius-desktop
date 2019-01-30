/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.RepresentationLinkMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Test that all representation links have the special representation link marker annotation key after migration.
 * 
 * @see RepresentationLinkMigrationParticipant
 */
public class RepresentationLinkMigrationParticipantTest extends SiriusDiagramTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/linkNote/";

    private static final String MODEL_FILE_NAME = "linkNote.ecore";

    private static final String REPRESENTATIONS_FILE_NAME = "representations.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);
    }

    /**
     * Test that the model still needs to be migrated
     */
    public void testMigrationIsNeededOnData() {
        Version migration = RepresentationLinkMigrationParticipant.MIGRATION_VERSION;
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test the migration itself.
     * 
     * @throws Exception
     */
    public void testMigration() throws Exception {

        ResourceSet set = new ResourceSetImpl();

        // Just loading the model will automatically trigger the migration ...
        DAnalysis analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);

        // .. so that the expected results can now be verified
        for (DView dView : analysis.getOwnedViews()) {
            for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                    Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                    Iterator<EObject> it = gmfDiagram.eAllContents();
                    while (it.hasNext()) {
                        EObject input = it.next();
                        if (input instanceof View) {
                            View view = (View) input;
                            boolean marked = false;
                            EAnnotation annot = view.getEAnnotation(ViewQuery.SPECIFIC_STYLES);
                            if (annot != null) {
                                marked = annot.getDetails().containsKey(ViewQuery.REPRESENTATION_LINK_NOTE);
                            }
                            // The view is marked if and only if it is a link
                            // note
                            if (marked) {
                                assertTrue("View has representation link annotation, but is not a representation link ", isNoteWithDRepresentationDescriptorTarget(view));
                            }
                            if (isNoteWithDRepresentationDescriptorTarget(view)) {
                                assertTrue("View is a representation link, but has no representation link annotation", marked);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isNoteWithDRepresentationDescriptorTarget(View view) {
        return ViewType.NOTE.equals(view.getType()) && view.getElement() instanceof DRepresentationDescriptor;
    }

}
