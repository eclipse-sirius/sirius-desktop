/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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

import java.util.Map.Entry;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.NoteShapeDefaultLabelAlignmentMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Test for {@link NoteShapeDefaultLabelAlignmentMigrationParticipant}.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 *
 */
public class NoteShapeDefaultLabelAlignmentMigrationTest extends SiriusTestCase {

    private static final String SESSION_RESOURCE_NAME = "noteAttachmentAlignment.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "noteAttachmentAlignment.ecore";

    private static final String VSM_RESOURCE_NAME = "noteAttachmentAlignment.odesign";

    protected String dataPath = "data/unit/migration/do_not_migrate/noteAttachmentAlignment/";

    protected String errorMessage = "One note should be found in this session to check the migration of a Note with the problem of bugzilla 515044.";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, dataPath, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, VSM_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        sessionResource = resourceSet.getResource(sessionResourceURI, true);

    }

    /**
     * Test that the data were not migrated on the repository. It allows to
     * check the effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new NoteShapeDefaultLabelAlignmentMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", loadedVersion == null || migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Check note shape with eAnnotation after migration.
     */
    public void testNoteShapeAlignmentAfterMigration() {
        TreeIterator<EObject> allContents = sessionResource.getAllContents();
        UnmodifiableIterator<EObject> shapes = Iterators.filter(allContents, input -> input instanceof Shape && ViewType.NOTE.equals(((Shape) input).getType()));
        int nbTestedShapes = 0;
        while (shapes.hasNext()) {
            nbTestedShapes++;
            Shape note = (Shape) shapes.next();
            Iterable<TextStyle> textStyles = Iterables.filter(note.getStyles(), TextStyle.class);
            assertEquals("The text style must be defined on the view for the note", 1, Iterables.size(textStyles));
            assertSame("The text style must be center aligned for the note", TextAlignment.CENTER_LITERAL, textStyles.iterator().next().getTextAlignment());
            EAnnotation specificStyles = note.getEAnnotation(ViewQuery.SPECIFIC_STYLES);
            assertNotNull("The note should have an eAnnotation for the vertical alignment", specificStyles);
            EMap<String, String> details = specificStyles.getDetails();
            assertEquals("The eAnnotation detail for the vertical alignment is missing", 1, details.size());
            Entry<String, String> entry = details.iterator().next();
            assertSame("The eAnnotation detail for the vertical alignment is missing", ViewQuery.VERTICAL_ALIGNMENT, entry.getKey());
            assertEquals("The vertical alignment of the note should be set to TOP", String.valueOf(PositionConstants.TOP), entry.getValue());
        }
        assertEquals(errorMessage, 1, nbTestedShapes);
    }
}
