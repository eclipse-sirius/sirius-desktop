/*******************************************************************************
 * Copyright (c) 2016 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.dialect;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.Assert;

public class DiagramDialectServicesTests extends SiriusDiagramTestCase {

    private List<DRepresentation> representations = new ArrayList<DRepresentation>();

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint("aqlDomainClassDef");
    }

    /**
     * Find a diagram description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram description or null if not found.
     */
    protected DiagramDescription findDiagramDescription(final String name) {
        for (final Viewpoint vp : viewpoints) {
            for (final RepresentationDescription representation : vp.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals(name)) {
                    return (DiagramDescription) representation;
                }
            }

        }
        return null;
    }

    /**
     * Verify that a representation were created from the given description.
     * 
     * @param description
     *            the description from which we want initialize new
     *            representation.
     */
    private void doCreateRepresentations(final DiagramDescription description) {
        final TransactionalEditingDomain editingDomain = session.getTransactionalEditingDomain();
        final RepresentationDescription representationDescription = description;
        if (DialectManager.INSTANCE.canCreate(semanticModel, representationDescription)) {
            CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, representationDescription, semanticModel, "Diagram for " + semanticModel,
                    new NullProgressMonitor());
            editingDomain.getCommandStack().execute(createRepresentationCommand);
            DRepresentation rep = createRepresentationCommand.getCreatedRepresentation();
            assertNotNull("The representation has not been created ! ", rep);
            representations.add(rep);
        }
        assertEquals("One representation should have been removed.", 1, representations.size());
    }

    /**
     * Check that a representation can be created from a representation
     * description containing as root domain class a domain class using the AQL
     * syntax "ecore::EPackage"
     * 
     */
    public void testDescriptionCreationWithRootElementDomainClassWIthAQLSyntax() {
        DiagramDescription description = findDiagramDescription("EcoreDiag");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description);
    }

    @Override
    protected void tearDown() throws Exception {
        doCleanup();
        super.tearDown();
    }

    private void doCleanup() {
        final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
        if (sessionUI != null) {
            SessionUIManager.INSTANCE.remove(sessionUI);
            sessionUI.close();
            TestsUtil.emptyEventsFromUIThread();
        }
        if (session != null) {
            doRemoveSession();
            doCloseSession();
            session = null;
        }
        representations = new ArrayList<DRepresentation>();
        viewpoints.clear();
    }

    private void doCloseSession() {
        session.close(new NullProgressMonitor());
        Assert.assertFalse("Can't close the session", session.isOpen());
    }

    private void doRemoveSession() {
        SessionManager.INSTANCE.remove(session);
        for (final Session session2 : SessionManager.INSTANCE.getSessions()) {
            Assert.assertFalse("Remove failed", session2.equals(session));
        }
    }

}
