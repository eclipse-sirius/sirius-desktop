/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES and others.
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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * Ensures that deleting diagram elements containing a note correctly deletes the corresponding note.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class DeleteElementsLinkedToNoteTest extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "doremi-4336/";

    private static final String typesSemanticModel = "doremi-4336.ecore";

    private static final String sessionModel = "doremi-4336.aird";

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private SequenceDiagram sequenceDiagram;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "doremi-4336.interactions";
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
        sequenceDiagram = openSequenceDiagramOfType("Sequence Diagram on I1", REPRESENTATION_TYPE).get();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that deleting the Combined Fragment also deletes the note.
     */
    public void testDeleteCombinedFragmentContainingNote() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        CombinedFragment combinedFragment = getCombinedFragmentByName(sequenceDiagram, "alt.1").get();
        Node note = getNote(diagramView.eAllContents());

        IGraphicalEditPart cfEditPart = getEditPart((DDiagramElement) combinedFragment.getNotationView().getElement());

        assertNotNull("The diagram should own a note", note);
        assertEquals("There should be five gmf diagram elements", 5, diagramEditPart.getPrimaryEditParts().size());
        delete(cfEditPart);
        TestsUtil.synchronizationWithUIThread();
        assertNull("The Combined Fragment should be deleted", getCombinedFragmentByName(sequenceDiagram, "alt.1").get());
        assertNull("The Combined Fragment should be deleted from the edit part", cfEditPart.getParent());
        assertNull("The note should be deleted from the diagram", getNote(diagramView.eAllContents()));
        assertEquals("There should be two gmf diagram elements", 2, diagramEditPart.getPrimaryEditParts().size());
    }

    /**
     * Tests that deleting the first operand also deletes the note.
     */
    public void testDeleteOperandContainingNote() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        CombinedFragment combinedFragment = getCombinedFragmentByName(sequenceDiagram, "alt.1").get();
        Node note = getNote(diagramView.eAllContents());
        Operand firstOperand = combinedFragment.getOperands().get(0);
        Operand secondOperand = combinedFragment.getOperands().get(1);

        IGraphicalEditPart cfEditPart = getEditPart((DDiagramElement) combinedFragment.getNotationView().getElement());
        List<?> operandsEditPart = ((IGraphicalEditPart) cfEditPart.getChildren().get(1)).getChildren();
        GraphicalEditPart o1EditPart = (GraphicalEditPart) operandsEditPart.get(0);
        GraphicalEditPart o2EditPart = (GraphicalEditPart) operandsEditPart.get(1);

        assertNotNull("The diagram should own a note", note);
        assertEquals("There should be five gmf diagram elements", 5, diagramEditPart.getPrimaryEditParts().size());
        assertTrue("The first operand should exist", combinedFragment.getOperands().contains(firstOperand));
        assertTrue("The second operand should exist", combinedFragment.getOperands().contains(secondOperand));
        delete(o1EditPart);
        TestsUtil.synchronizationWithUIThread();
        assertFalse("The first operand should be deleted", combinedFragment.getOperands().contains(firstOperand));
        assertNull("The first operand should be deleted from the edit part", o1EditPart.getParent());
        assertTrue("The second operand should not be deleted", combinedFragment.getOperands().contains(secondOperand));
        assertNotNull("The second operand should not be deleted from the edit part", o2EditPart.getParent());
        assertNull("The note should be deleted from the diagram", getNote(diagramView.eAllContents()));
        assertEquals("There should be three gmf diagram elements", 3, diagramEditPart.getPrimaryEditParts().size());
    }

    // Returns a note
    private Node getNote(Iterator<EObject> it) {
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof Node && GMFNotationHelper.isNote((Node) obj)) {
                return (Node) obj;
            }
        }
        return null;
    }
}
