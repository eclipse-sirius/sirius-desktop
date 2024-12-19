/*******************************************************************************
 * Copyright (c) CEA.
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

import java.util.Optional;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionContainer;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Check Interaction Container size depending on sequence diagram element. This test uses the default sequence VSM
 * interaction. The Interaction Container mapping is in an additional layer, not active by default. This test classes
 * uses a sample with sequence diagram that are 'unusual'.
 * 
 * @author smonnier
 */
public class InteractionContainerFromSampleTests extends AbstractSequenceDiagramTestCase {

    // protected static final String VIEWPOINT_NAME = "Interactions_local_vsm";

    private static final String PATH = DATA_UNIT_DIR + "interactionContainer/cornerCases/";

    private static final String REPRESENTATION_NAME1 = "Sequence Diagram on interaction1";

    private static final String REPRESENTATION_NAME2 = "Sequence Diagram on interaction2";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Sequence Diagram on Interaction";

    private static final String MODEL = "My.interactions";

    private static final String SESSION_FILE = "representations.aird";

    private static final String TYPES_FILE = "types.ecore";

    // private UIDiagramRepresentation diagram;

    protected static final String LIFELINE_2 = "newParticipant2 : ";

    protected static final String LIFELINE_3 = "newParticipant3 : ";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // diagram =
        // localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(getRepresentationId())
        // .selectRepresentationInstance(getDRepresentationName().get(), UIDiagramRepresentation.class);

        editor.reveal(LIFELINE_1);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);
    }

    /**
     * Check that on lifeline move, the interaction container bounds also move accordingly. Here the east bound depends on
     * the east bound of the main combined fragment.
     */
    public void testInteractionResizeOnInstanceRolePositionChangeWithMultipleMessages() {
        // Check that the Interaction Container east bound correspond to the combined  east bound + margin
        SWTBotGefEditPart interactionContainerEditPart = editor.getEditPart("interaction1", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        Optional<SWTBotGefConnectionEditPart> optionalm7SWTBotGefConnectionEditPart = editor.getConnectionsEditPart().stream().filter(cep-> ((DEdge)((Edge)cep.part().getModel()).getElement()).getName().equals("m7")).findFirst();
        assertTrue("message m7 has not been found on the diagram", optionalm7SWTBotGefConnectionEditPart.isPresent());
        Rectangle m7bounds = editor.getBounds(optionalm7SWTBotGefConnectionEditPart.get());
        assertEquals("Interaction Container east bound is not where expected", m7bounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 3);

        SWTBotGefEditPart instanceRole3EditPart = editor.getEditPart(LIFELINE_3, InstanceRoleEditPart.class);
        Rectangle instanceRole3Bounds = editor.getBounds(instanceRole3EditPart);
        
        // Move newParticipant3 further on the right to resize the interaction container
        editor.drag(instanceRole3EditPart, instanceRole3Bounds.x + 250, origin.y);

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        interactionContainerEditPart = editor.getEditPart("interaction1", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        optionalm7SWTBotGefConnectionEditPart = editor.getConnectionsEditPart().stream().filter(cep -> ((DEdge) ((Edge) cep.part().getModel()).getElement()).getName().equals("m7")).findFirst();
        m7bounds = editor.getBounds(optionalm7SWTBotGefConnectionEditPart.get());
        assertTrue("message m7 has not been found on the diagram", optionalm7SWTBotGefConnectionEditPart.isPresent());
        assertEquals("Interaction Container east bound is not where expected", m7bounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 3);
    }
    
    

    /**
     * Check that on lifeline move, the interaction container bounds also move accordingly. Here the east bound depends on
     * the east bound of the main combined fragment.
     */
    public void testInteractionResizeOnInstanceRolePositionChangeWithMultipleCombinedFragments() {
        // TODO open representation2
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_NAME2, DDiagram.class, true, true);
        // Check that the Interaction Container east bound correspond to the combined  east bound + margin
        SWTBotGefEditPart interactionContainerEditPart = editor.getEditPart("interaction2", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        SWTBotGefEditPart combinedFragmentEditPart = editor.getEditPart("alt.1", CombinedFragmentEditPart.class);
        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragmentEditPart);
        assertEquals("Interaction Container east bound is not where expected", combinedFragmentBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);

        SWTBotGefEditPart instanceRole2EditPart = editor.getEditPart(LIFELINE_2, InstanceRoleEditPart.class);
        Rectangle instanceRole2Bounds = editor.getBounds(instanceRole2EditPart);
        
        // Move newParticipant3 further on the right to resize the interaction container
        editor.drag(instanceRole2EditPart, instanceRole2Bounds.x + 250, origin.y);

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        interactionContainerEditPart = editor.getEditPart("interaction2", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        combinedFragmentEditPart = editor.getEditPart("alt.1", CombinedFragmentEditPart.class);
        combinedFragmentBounds = editor.getBounds(combinedFragmentEditPart);
        assertEquals("Interaction Container east bound is not where expected", combinedFragmentBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return MODEL;
    }

    @Override
    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    @Override
    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * @return the representationDescriptionName
     */
    public static String getRepresentationDescriptionName() {
        return REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME1);
    }

}
