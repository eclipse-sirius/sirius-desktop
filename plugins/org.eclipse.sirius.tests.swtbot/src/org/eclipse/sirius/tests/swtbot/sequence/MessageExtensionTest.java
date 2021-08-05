/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterables;

/**
 * Test class for message mapping extensions with EdgeMappingImport.
 * 
 * See VP-3754.
 * 
 * @author mporhel
 */
public class MessageExtensionTest extends AbstractDefaultModelSequenceTests {

    private static final String M1 = "m1";

    private static final String EXTENSION_SCENARIO_LAYER_NAME = "Extension Scenario";

    private static final String M1_EXTENDED = "m1_extended";

    private static final String M12_EXTENDED = "m12_extended";

    private static final String A = "a : ";

    private static final String B = "b : ";

    private static final String PATH = DATA_UNIT_DIR + "message_extension/";

    private UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getTypesSemanticModel() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        // Copy the extension VSM.
        copyFileToTestProject(Activator.PLUGIN_ID, getPath(), "message-extension.odesign");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(getRepresentationId())
                .selectRepresentationInstance(getDRepresentationName().get(), UIDiagramRepresentation.class);
    }

    /**
     * Test the opening of a diagram containing edges described with an
     * EdgeMappingImport does not make the session dirty.
     * 
     * See VP-3805.
     */
    public void testSessionSyncOnDiagramWithEdgemappingImportOpening() {
        IPreferencesService prefs = Platform.getPreferencesService();
        boolean autoRefresh = prefs.getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        boolean refreshOnOpening = prefs.getBoolean(SiriusEditPlugin.ID, SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false, null);

        assertTrue("The auto refresh should be enabled for this test.", autoRefresh);
        assertTrue("The refresh on opening should be enabled for this test.", refreshOnOpening);

        // The default and the optional layer should be selected.
        assertEquals("The default and optional layers should be selected", 2, ((SequenceDiagramEditPart) editor.mainEditPart().part()).resolveDDiagram().get().getActivatedLayers().size());

        assertFalse("The editor should not be dirty after the diagram opening.", editor.isDirty());
        assertFalse("The session should not be dirty after the diagram opening.", localSession.isDirty());

        // Refresh the diagram.
        ICondition done = new OperationDoneCondition();
        editor.click(0, 0);
        editor.refresh();
        bot.waitUntil(done);

        assertFalse("The editor should not be dirty after the manual refresh.", editor.isDirty());
        assertFalse("The session should not be dirty after the manual refresh.", localSession.isDirty());
    }

    /**
     * Test that a Message described by an EdgeMappingImport importing a
     * MessageMapping is well identified.
     */
    public void testExtendedMessageIdentification() {
        SequenceMessageEditPart extendedMessagePart = (SequenceMessageEditPart) editor.getEditPart(M1_EXTENDED).parent().part();
        Edge edge = (Edge) extendedMessagePart.getModel();
        DEdge dEdge = (DEdge) extendedMessagePart.resolveDiagramElement();

        ISequenceEvent iSequenceEvent = extendedMessagePart.getISequenceEvent();
        assertTrue("The current edge should be identified as a Message.", iSequenceEvent instanceof Message);

        assertTrue("The current edge should be identified as a Message.", ISequenceElementAccessor.getISequenceElement(edge).some());
        assertTrue("The current edge should be identified as a Message.", ISequenceElementAccessor.getMessage(edge).some());
        assertSame("The corresponding Message should be unique.", iSequenceEvent, ISequenceElementAccessor.getMessage(edge).get());

        assertTrue("The current edge should be identified as a Message.", Message.viewpointElementPredicate().apply(dEdge));
        assertTrue("The current edge view should be identified as a Message.", Message.notationPredicate().apply(edge));

        Set<Message> allMessages = iSequenceEvent.getDiagram().getAllMessages();
        assertTrue("The current Message should be accessible from the SequencDiagram.", allMessages.contains(iSequenceEvent));
        assertTrue("The current Message should be accessible from the SequencDiagram.", Iterables.contains(iSequenceEvent.getDiagram().getAllDelimitedSequenceEvents(), iSequenceEvent));
        assertEquals(11, allMessages.size());
    }

    /**
     * Test that edge style is well impacted by the activation/deactivation of
     * the optional layer providing the edge mapping import.
     */
    public void testLayerActivationChangeAndExtendedMessage() {
        assertNoEditPartWithLabel(M1);
        assertNotNull(editor.getEditPart(M1_EXTENDED));
        int m1 = getSequenceMessageVerticalPosition(M1_EXTENDED);

        // Deactivate the extension layer.
        ICondition done = new OperationDoneCondition();
        diagram.changeLayerActivation(EXTENSION_SCENARIO_LAYER_NAME);
        bot.waitUntil(done);

        assertNoEditPartWithLabel(M1_EXTENDED);
        assertNotNull(editor.getEditPart(M1));
        assertMessageVerticalPosition(M1, m1);

        // Activate the extension layer.
        done = new OperationDoneCondition();
        diagram.changeLayerActivation(EXTENSION_SCENARIO_LAYER_NAME);
        bot.waitUntil(done);

        assertNoEditPartWithLabel(M1);
        assertNotNull(editor.getEditPart(M1_EXTENDED));
        assertMessageVerticalPosition(M1_EXTENDED, m1);
    }

    /**
     * Test that a tool referencing an extended message mapping creates the
     * message with extension style.
     */
    public void testExtendedMessageCreation() {
        maximizeEditor(editor);
        editor.reveal(A);
        ICondition done = new OperationDoneCondition();
        createMessage(InteractionsConstants.CALL_TOOL_ID, A, 220, B, 220);
        bot.waitUntil(done);
        assertMessageVerticalPosition(M12_EXTENDED, 220);

        // The edge has the extended label, now check the style and mapping.
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(M12_EXTENDED);
        DEdge dEdge = (DEdge) sequenceMessageEditPart.resolveDiagramElement();
        EdgeMapping actualEdgeMapping = (EdgeMapping) dEdge.getMapping();
        assertTrue("The edge's mapping is not the import mapping, check the data.", actualEdgeMapping instanceof EdgeMappingImportWrapper);

        IEdgeMapping wrappedEdgeMapping = ((EdgeMappingImportWrapper) actualEdgeMapping).getWrappedEdgeMappingImport();
        EObject styleDescriptionParentMapping = dEdge.getOwnedStyle().getDescription().eContainer().eContainer();
        assertEquals("The edge's style is not provided by the import mapping, check the data.", wrappedEdgeMapping, styleDescriptionParentMapping);

        editor.click(getSequenceMessageScreenCenteredPosition(M12_EXTENDED));
        deleteSelectedElement();
        assertNoEditPartWithLabel(M12_EXTENDED);
    }

    /**
     * Test that an extended message can be moved and reordered.
     */
    public void testExtendedMessageMoveAndReorder() {
        int lifelineA_x = getLifelineScreenX(A);
        maximizeEditor(editor);
        editor.reveal(A);
        int m12 = 220;
        ICondition done = new OperationDoneCondition();
        createMessage(InteractionsConstants.CALL_TOOL_ID, A, m12, B, 220);
        bot.waitUntil(done);
        assertMessageVerticalPosition(M12_EXTENDED, 220);

        Rectangle e1Bounds = getExecutionScreenBounds(A, 0);
        Rectangle execA0 = assertExecutionHasValidScreenBounds(A, 0, e1Bounds);
        int m1 = getSequenceMessageVerticalPosition(M1_EXTENDED);

        // Move m12 up
        done = new OperationDoneCondition();
        editor.drag(lifelineA_x + 50, m12, lifelineA_x + 50, m1 - 10);
        bot.waitUntil(done);
        // Move graphical effect
        m12 = m1 - 10;

        // Check stability : reorder check is implicitly done thanks to the
        // graphical position stability and the updated m12 position.
        assertExecutionHasValidScreenBounds(A, 0, execA0);
        assertMessageVerticalPosition(M1_EXTENDED, m1);
        assertMessageVerticalPosition(M12_EXTENDED, m12);

        // Move m12 down
        done = new OperationDoneCondition();
        editor.drag(lifelineA_x + 50, m12, lifelineA_x + 50, execA0.bottom() + 10);
        bot.waitUntil(done);
        // Move graphical effect
        m12 = execA0.bottom() + 10;

        // Check stability : reorder check is implicitly done thanks to the
        // graphical position stability and the updated m12 position.
        assertExecutionHasValidScreenBounds(A, 0, execA0);
        assertMessageVerticalPosition(M1_EXTENDED, m1);
        assertMessageVerticalPosition(M12_EXTENDED, m12);
    }
}
