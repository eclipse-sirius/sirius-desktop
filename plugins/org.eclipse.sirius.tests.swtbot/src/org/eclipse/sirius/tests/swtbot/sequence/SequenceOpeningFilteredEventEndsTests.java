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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Sets;

/**
 * Test class for VP-1546. Test open diagram without refresh.
 * 
 * @author mporhel
 */
public class SequenceOpeningFilteredEventEndsTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "vp-1546/";

    private static final String REPRESENTATION_NAME = "Diagram2";

    /**
     * Odesign is not valid, services & tools have been deleted
     */
    private static final String MODEL = "vp-1546.interactions";

    private static final String SESSION_FILE = "vp-1546.aird";

    private final Set<Viewpoint> viewpoints = Sets.newHashSet();

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getTypesSemanticModel() {
        return null;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return "Sequence Diagram Example with Combined Fragments Support";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(Activator.PLUGIN_ID + PATH + "interactionVP1546.odesign"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        super.onSetUpAfterOpeningDesignerPerspective();
    }

    /**
     * .
     */
    public void testDiagramOpeningAndSessionNonDirty() {

        // InstanceRoles
        SWTBotGefEditPart instanceRoleEditPartABot = editor.getEditPart("A : ").parent();
        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart("B : ").parent();

        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefEditPart lifelineBBot = instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);

        assertTrue(lifelineABot.descendants(IsInstanceOf.instanceOf(ISequenceEventEditPart.class)).isEmpty());
        assertTrue(lifelineBBot.descendants(IsInstanceOf.instanceOf(ISequenceEventEditPart.class)).isEmpty());

        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        ISequenceEvent lifelineA = ((ISequenceEventEditPart) lifelineABot.part()).getISequenceEvent();
        assertTrue(lifelineA.getSubEvents().isEmpty());

        ISequenceEvent lifelineB = ((ISequenceEventEditPart) lifelineBBot.part()).getISequenceEvent();
        assertTrue(lifelineB.getSubEvents().isEmpty());

        Option<EObject> interactionOption = lifelineB.getDiagram().getSemanticTargetElement();
        assertTrue(interactionOption.some());

        Interaction interaction = (Interaction) interactionOption.get();
        assertEquals(1, interaction.getExecutions().size());
        assertEquals(2, interaction.getMessages().size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        for (Viewpoint vp : viewpoints) {
            ViewpointRegistry.getInstance().disposeFromPlugin(vp);
        }
        viewpoints.clear();

        super.tearDown();
    }
}
