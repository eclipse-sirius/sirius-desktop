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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import com.google.common.collect.Lists;

/**
 * Test that the initial Header Height is not equal to 1 on Sequence Diagram
 * creation, if a participant have a long name.
 * 
 * @author jdupont
 * 
 */
public class HeaderHeightTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "header_height/";

    private static final String SEMANTIC_RESOURCE_NAME = "HeaderHeight.interactions";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return SEMANTIC_RESOURCE_NAME;
    }

    @Override
    protected String getTypesSemanticModel() {
        return null;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected String getSessionModel() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createSequenceDiagramOfType(InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
    }

    /**
     * Test that the header height is greater than 1, because in the use case,
     * the participant size is enough long to use 3 lines in the header.
     */
    public void testHeaderHeightSize() {
        // Compute the height needed for the current header data.
        LinkedList<HeaderData> headerDatas = Lists.newLinkedList();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(sequenceDDiagram.getDescription().eClass().getEPackage())) {
                headerDatas = diagramTypeDescriptor.getDiagramDescriptionProvider().getHeaderData(sequenceDDiagram);
                break;
            }
        }
        int expectedSize = getNbLinesNeeded(headerDatas);
        // Check that this expected size is the same in diagram (should be set
        // by SetBestHeightHeaderTrigger).
        assertEquals("The header height should be sufficient to display entirely all labels.", expectedSize, sequenceDDiagram.getHeaderHeight());
    }

    /**
     * Get number of lines that is needed to display all the header labels
     * according to the current widths.
     * 
     * @return the number of lines needed to display all labels.
     */
    private int getNbLinesNeeded(List<HeaderData> headerDatas) {
        int maxNbLines = 1;
        for (HeaderData headerData : headerDatas) {
            int nbLines = SWTUtil.getNbLines(headerData.getName(), headerData.getWidth());
            if (maxNbLines < nbLines) {
                maxNbLines = nbLines;
            }
        }
        return maxNbLines;
    }
}
