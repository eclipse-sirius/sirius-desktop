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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * Ensures that an arrangeAll performed at diagram creation works as expected,
 * in some critical use cases.
 * <p>
 * <li><b>Relevant tickets</b> : DOREMI-2332 & VP-1843</li>
 * <li><b>Concerned classes</b> :
 * LifeLineEditPart$SouthCenteredBorderItemLocator (override relocate to fix the
 * issue)</li>
 * </p>
 */
public class LifelinesSouthCenteredBorderItemLocatorTest extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "doremi-2332/";

    private static final String typesSemanticModel = "doremi-2332.ecore";

    private static final String sessionModel = "doremi-2332.aird";

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "doremi-2332.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return typesSemanticModel;
    }

    @Override
    protected String getSessionModel() {
        return sessionModel;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createSequenceDiagramOfType(REPRESENTATION_TYPE);
        // Arrange All
        arrangeAll(diagramEditPart);
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Ensures that an arrangeAll performed at diagram creation works as
     * expected.
     */
    public void testArrangeAllAfterSequenceDiagramCreationGeneratesValidBounds() {

        // Step 1 : getting the last Execution of the "b : B" participant
        Execution lastBExecution = interaction.getExecutions().get(3);
        assertEquals(lastBExecution.getName(), "e1");
        DDiagramElement lastBExecutionDiagramElement = getFirstDiagramElement(sequenceDDiagram, lastBExecution);
        View lastBExecutionView = SiriusGMFHelper.getGmfView(lastBExecutionDiagramElement, session);

        // Step 2 : getting the Draw2D and GMF bounds of this execution
        // Step 2.1 : getting the Draw2D bounds (potentially invalid)
        IGraphicalEditPart lastBExecutionEditPart = getEditPart(lastBExecutionDiagramElement, this.editorPart);
        Rectangle draw2DBounds = lastBExecutionEditPart.getFigure().getBounds();

        // Step 2.2 : getting the GMF bounds
        Rectangle gmfBounds = ISequenceElementAccessor.getExecution(lastBExecutionView).get().getProperLogicalBounds();

        // Step 3 : check that these two bounds are equals
        // if not, it means that the arrangeAll called at diagram creation did
        // not work
        assertEquals("Invalid Bounds : GMF and Draw2D bounds differ (Arrange All failure when creating diagram)", gmfBounds, draw2DBounds);
    }
}
