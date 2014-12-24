/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.resource.ResourceMigrationMarker;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.ui.business.internal.migration.FontStyleForDNodeListElementMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * Test for {@link FontStyleForDNodeListElementMigrationParticipant}. See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=424418
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class FontStyleForDNodeListElementMigrationTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/ListElementFontStyle/";

    private static final String SESSION_RESOURCE_NAME = "ListElement.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "ListElement.ecore";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + SESSION_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        sessionResource = resourceSet.getResource(sessionResourceURI, true);

    }

    /**
     * Test that the data were not migrated on the repository. It allows to
     * check the effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new FontStyleForDNodeListElementMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The list migration must be required on test data.", loadedVersion == null || migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Ensure that the editor should not be dirty after the diagram opening.
     */
    public void testMigration() {
        assertTrue("The resource should be migrated", ResourceMigrationMarker.hasMigrationMarker(sessionResource));
    }

    /**
     * Check node list element after migration.
     */
    public void testNodeListElementAfterMigration() {
        EClass fontStyleClass = NotationPackage.eINSTANCE.getFontStyle();
        DAnalysis analysis = (DAnalysis) sessionResource.getContents().get(0);
        TreeIterator<EObject> childIterator = analysis.eAllContents();
        while (childIterator.hasNext()) {
            EObject child = childIterator.next();
            if (child instanceof View && ((View) child).getElement() instanceof DNodeListElement) {
                View view = (View) child;
                DNodeListElement listElement = (DNodeListElement) view.getElement();
                Style style = view.getStyle(fontStyleClass);
                assertNotNull("The font style must be defined on the view for " + listElement.getName(), style);
            }
        }
    }
}
