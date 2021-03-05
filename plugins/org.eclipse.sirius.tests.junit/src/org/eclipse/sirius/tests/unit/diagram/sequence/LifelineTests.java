/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import java.util.Collections;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IDiagramPreferenceSupport;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;
import org.junit.Assert;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.common.collect.Iterables;

/**
 * Tests that GMF Text/Note are not creatable/droppable in a lifeline.
 * 
 * See VP-3776.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class LifelineTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "lifelines/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3776.interactions";

    private static final String SEMANTIC_TYPE_RESOURCE_NAME = "VP-3776.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3776.aird";

    private LifelineEditPart lifelineEditPart;

    private NoteEditPart noteEditPart;

    private TextEditPart textEditPart;

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
        return SEMANTIC_TYPE_RESOURCE_NAME;
    }

    @Override
    protected String getSessionModel() {
        return REPRESENTATIONS_RESOURCE_NAME;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        openSequenceDiagramOfType("Sequence Diagram on ", InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        DDiagramElement firstDiagramElement = getFirstDiagramElement(sequenceDDiagram, interaction.getParticipants().get(0));
        lifelineEditPart = (LifelineEditPart) getEditPart(firstDiagramElement).getChildren().get(0);
        DDiagramEditPart dDiagramEditPart = (DDiagramEditPart) lifelineEditPart.getRoot().getChildren().get(0);
        noteEditPart = Iterables.getOnlyElement(Iterables.filter(dDiagramEditPart.getChildren(), NoteEditPart.class));
        textEditPart = Iterables.getOnlyElement(Iterables.filter(dDiagramEditPart.getChildren(), TextEditPart.class));
    }

    /**
     * Test that GMF Note creation is not possible in a lifeline.
     */
    public void testGMFNoteCreationInALifelineNotPossible() {
        CreateViewRequest createViewRequest = new CreateViewRequest(
                Collections.singletonList(new CreateViewRequest.ViewDescriptor(null, Node.class, "Note", ((IDiagramPreferenceSupport) lifelineEditPart.getRoot()).getPreferencesHint())));
        Command createGMFNoteInLifelineCmd = lifelineEditPart.getCommand(createViewRequest);
        assertFalse("The GMF Note creation should not be allowed in lifeline", createGMFNoteInLifelineCmd.canExecute());
    }

    /**
     * Test that GMF Text creation is not possible in a lifeline.
     */
    public void testGMFTextCreationInALifelineNotPossible() {
        CreateViewRequest createViewRequest = new CreateViewRequest(
                Collections.singletonList(new CreateViewRequest.ViewDescriptor(null, Node.class, "Text", ((IDiagramPreferenceSupport) lifelineEditPart.getRoot()).getPreferencesHint())));
        Command createGMFTextInLifelineCmd = lifelineEditPart.getCommand(createViewRequest);
        assertFalse("The GMF Text creation should not be allowed in lifeline", createGMFTextInLifelineCmd.canExecute());
    }

    /**
     * Test that GMF Note drop is not possible in a lifeline.
     */
    public void testGMFNoteDropInALifelineNotPossible() {
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        changeBoundsRequest.setEditParts(noteEditPart);
        Command dropGMFNoteInLifelineCmd = lifelineEditPart.getCommand(changeBoundsRequest);
        assertFalse("The GMF Note should not be droppable in lifeline", dropGMFNoteInLifelineCmd.canExecute());
    }

    /**
     * Unsetting the element reference of a Note when it is null, should not set it to a semantic element. This test
     * depends on a change which is not currently in official released versions of GMF Notation (see the patch attached
     * to #412078); it is not executed if we detect we run with such an official version.
     * 
     * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=412078
     */
    public void testGMFNoteUnset() {
        final Shape shape = (Shape) noteEditPart.getModel();
        Assert.assertNull("Element should be null for a note", shape.getElement());
        Bundle bundle = FrameworkUtil.getBundle(shape.getClass());
        boolean isForkedGMFNotation = bundle.getSignerCertificates(Bundle.SIGNERS_ALL).isEmpty();
        if (isForkedGMFNotation) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                @Override
                protected void doExecute() {
                    shape.unsetElement();

                }
            });
            Assert.assertNull("Element should be null for a note", shape.getElement());
        }
    }

    /**
     * Test that GMF Text drop is not possible in a lifeline.
     */
    public void testGMFTextDropInALifelineNotPossible() {
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        changeBoundsRequest.setEditParts(textEditPart);
        Command dropGMFNoteInLifelineCmd = lifelineEditPart.getCommand(changeBoundsRequest);
        assertFalse("The GMF Text should not be droppable in lifeline", dropGMFNoteInLifelineCmd.canExecute());
    }

    /**
     * Test that ZOrderAndInstanceRolePartRefresher detects thats the current event broker is used in Sirius.
     */
    public void testSequenceDiagramEditPartBrokerDetection() {
        SequenceDiagramEditPart sequenceDiagramPart = (SequenceDiagramEditPart) diagramEditPart;
        Class<?>[] sdepSubClassed = SequenceDiagramEditPart.class.getDeclaredClasses();
        assertEquals("Review test definition to get the expected declared class for SequenceDiagramEditPart.ZOrderAndInstanceRolePartRefresher", 1, sdepSubClassed.length);
        Class<?> zOrderAndInstanceRolePartRefresherClass = sdepSubClassed[0];
        Object listener = ReflectionHelper.getFieldValueWithoutException(sequenceDiagramPart, "zOrderAndInstanceRolePartRefresher").get();
        Object result = ReflectionHelper.invokeMethodWithoutExceptionWithReturn(listener, zOrderAndInstanceRolePartRefresherClass, "isDefaultSiriusDiagramEventBroker",
                new Class[] { TransactionalEditingDomain.class }, // $NON-NLS-1$
                new Object[] { session.getTransactionalEditingDomain() }, true);

        Boolean shouldDetectDefaultSiriusDiagramEventBroker = Boolean.TRUE;
        if (DAnalysisSessionEObjectImpl.class != session.getClass()) {
            shouldDetectDefaultSiriusDiagramEventBroker = Boolean.FALSE;
        }
        assertEquals("SequenceDiagramEditPart should be able to detect if the diagram event broker is the default one or not.", shouldDetectDefaultSiriusDiagramEventBroker, result);
    }

    @Override
    public void tearDown() throws Exception {
        lifelineEditPart = null;
        noteEditPart = null;
        textEditPart = null;
        super.tearDown();
    }
}
