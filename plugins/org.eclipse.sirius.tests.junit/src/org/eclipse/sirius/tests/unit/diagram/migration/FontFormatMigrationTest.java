/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.internal.migration.FontFormatMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test the migration of label and label description font formats.
 * 
 * @author mporhel
 */
public class FontFormatMigrationTest extends SiriusDiagramTestCase {

    private String PATH = "/data/unit/migration/do_not_migrate/fontFormat/";

    private String VSM = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "fontFormat.odesign";

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "fontFormat.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "fontFormat.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL, VSM, AIRD);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(AIRD, true), true);

        // Check that the migration is needed.
        Version migration = new FontFormatMigrationParticipant().getMigrationVersion();
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test the migration effect.
     */
    public void testFontFormatDescriptionAfterMigration() {
        DiagramDescription diagDesc = (DiagramDescription) getRepresentationDescription("fontFormatDiag", getViewpointFromName("fontFormat", session));
        Iterable<AbstractNodeMapping> mappings = Iterables.filter(diagDesc.getDefaultLayer().eContents(), AbstractNodeMapping.class);
        assertEquals(4, Iterables.size(mappings));
        Collection<String> oldFontFormats = getOldFontFormats();
        for (AbstractNodeMapping mapping : mappings) {
            String expectedStyle = mapping.getName();
            Collection<FontFormat> labelFormat = getLabelFormat(mapping);
            checkFontFormat(expectedStyle, labelFormat);
            oldFontFormats.remove(expectedStyle);
        }
        assertTrue("The migration has not been tested on " + oldFontFormats.toString(),oldFontFormats.isEmpty());

        TableDescription tableDesc = (TableDescription) getRepresentationDescription("fontFormatTable", getViewpointFromName("fontFormat", session));
        Collection<LineMapping> lineMappings = tableDesc.getAllLineMappings();
        assertEquals(4, lineMappings.size());
        oldFontFormats = getOldFontFormats();
        for (LineMapping mapping : lineMappings) {
            String expectedStyle = mapping.getName();
            Collection<FontFormat> labelFormat = mapping.getDefaultForeground().getLabelFormat();
            checkFontFormat(expectedStyle, labelFormat);
            oldFontFormats.remove(expectedStyle);
        }
        assertTrue("The migration has not been tested on " + oldFontFormats.toString(),oldFontFormats.isEmpty());
    }

    /**
     * Test the migration effect.
     */
    public void testFontFormatAfterMigration() {
        DDiagram diag = (DDiagram) getRepresentations("fontFormatDiag").iterator().next();
        Collection<DDiagramElement> ddes = diag.getDiagramElements();
        assertEquals(4, Iterables.size(ddes));
        Collection<String> oldFontFormats = getOldFontFormats();
        for (DDiagramElement dde : ddes) {
            String expectedStyle = dde.getName();
            Collection<FontFormat> labelFormat = getLabelFormat(dde);
            checkFontFormat(expectedStyle, labelFormat);
            oldFontFormats.remove(expectedStyle);
        }
        assertTrue("The migration has not been tested on " + oldFontFormats.toString(),oldFontFormats.isEmpty());

        DTable table = (DTable) getRepresentations("fontFormatTable").iterator().next();
        Collection<DLine> lines = table.getLines();
        assertEquals(4, lines.size());
        oldFontFormats = getOldFontFormats();
        for (DLine line : lines) {
            String expectedStyle = line.getLabel();
            Collection<FontFormat> labelFormat = line.getCurrentStyle().getLabelFormat();
            checkFontFormat(expectedStyle, labelFormat);
            oldFontFormats.remove(expectedStyle);
        }
        assertTrue("The migration has not been tested on " + oldFontFormats.toString(),oldFontFormats.isEmpty());
    }

    private Collection<String> getOldFontFormats() {
      return Lists.newArrayList("normal", "bold", "italic", "bold_italic");
    }

    private Collection<FontFormat> getLabelFormat(AbstractNodeMapping mapping) {
        Collection<FontFormat> fontFormat = null;
        if (mapping instanceof NodeMapping) {
            fontFormat = ((NodeMapping) mapping).getStyle().getLabelFormat();
        } else if (mapping instanceof ContainerMapping) {
            fontFormat = ((ContainerMapping) mapping).getStyle().getLabelFormat();
        }
        return fontFormat;
    }

    private Collection<FontFormat> getLabelFormat(DDiagramElement dde) {
        Collection<FontFormat> fontFormat = null;
        if (dde instanceof DNode) {
            fontFormat = ((LabelStyle) ((DNode) dde).getStyle()).getLabelFormat();
        } else if (dde instanceof DNodeContainer) {
            fontFormat = ((LabelStyle) ((DNodeContainer) dde).getStyle()).getLabelFormat();
        }
        return fontFormat;
    }

    private void checkFontFormat(String name, Collection<FontFormat> labelFormat) {
        if ("normal".equals(name)) {
            assertTrue(labelFormat.isEmpty());
        } else if ("bold".equals(name)) {
            assertEquals(1, labelFormat.size());
            assertTrue(labelFormat.contains(FontFormat.BOLD_LITERAL));
        } else if ("italic".equals(name)) {
            assertEquals(1, labelFormat.size());
            assertTrue(labelFormat.contains(FontFormat.ITALIC_LITERAL));
        } else if ("bold_italic".equals(name)) {
            assertEquals(2, labelFormat.size());
            assertTrue(labelFormat.contains(FontFormat.BOLD_LITERAL));
            assertTrue(labelFormat.contains(FontFormat.ITALIC_LITERAL));
        } else {
            fail("The test data must contains only expected style ");
        }
    }
}
