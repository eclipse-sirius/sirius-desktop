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
package org.eclipse.sirius.tests.unit.api.componentization;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

public abstract class AbstractComponentizationTest extends SiriusDiagramTestCase {

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private static final String PATH = "/data/unit/componentization/";

    private static final String SEMANTIC_MODEL_FILENAME = "componentization.uml";

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME), Collections.<String> emptyList(), null);
    }

    protected DiagramDescription findDiagramDescription(final Viewpoint vp, final String name) {
        for (final RepresentationDescription desc : vp.getOwnedRepresentations()) {
            if (desc instanceof DiagramDescription && name.equals(desc.getName())) {
                return (DiagramDescription) desc;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid diagram description name");
    }

    protected ToolSection findSection(final Collection<ToolSection> sections, final String name) {
        for (final ToolSection section : sections) {
            if (name.equals(section.getName()))
                return section;
        }
        throw new IllegalArgumentException(name + "is not a valid section name");
    }

    protected ToolGroup findToolGroup(final ToolSection section, final String name) {
        for (final ToolEntry entry : DiagramComponentizationTestSupport.getToolEntries(session, section)) {
            if (entry instanceof ToolGroup && name.equals(entry.getName()))
                return (ToolGroup) entry;
        }
        throw new IllegalArgumentException(name + "is not a valid tool group name");
    }

    protected void doCleanupSession() {
        if (session != null) {
            final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
            if (sessionUI != null) {
                SessionUIManager.INSTANCE.remove(sessionUI);
                sessionUI.close();
            }
            session.close(new NullProgressMonitor());
            for (final Session session2 : SessionManager.INSTANCE.getSessions()) {
                assertFalse("Remove failed", session2.equals(session));
            }
            session = null;
        }
    }
}
