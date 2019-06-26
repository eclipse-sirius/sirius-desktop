/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Test.
 * 
 * @author cbrun.
 * 
 */
public abstract class AbstractSynchronizerTest extends SiriusDiagramTestCase {

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private static final String PATH = "/data/unit/refresh/node/";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "noderefresh.odesign";

    private static final String SEMANTIC_MODEL_FILENAME = "noderefresh.uml";

    /**
     * NB of refresh iterations.
     */
    protected static final int NB_ITERATIONS = 20;

    protected DDiagramSynchronizer sync;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);

        final Viewpoint viewpoint = viewpoints.iterator().next();
        activateViewpoint(viewpoint.getName());
    }

    /**
     * prepare the synchronizer.
     * 
     * @param description
     *            a description.
     * @param diagramName
     *            the diagram name.
     */
    protected void prepareSynchronizer(final DiagramDescription description, final String diagramName) {
        sync = new DDiagramSynchronizer(interpreter, description, accessor);
        sync.initDiagram( semanticModel, new NullProgressMonitor());
        boolean syncOnCreation = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false, null);
        sync.getDiagram().setSynchronized(syncOnCreation);
    }

    /**
     * return the refreshed diagram.
     * 
     * @return the refreshed diagram.
     */
    protected DDiagram getRefreshedDiagram() {
        sync.refresh(new NullProgressMonitor());
        return sync.getDiagram();
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
            Viewpoint viewpoint = getViewpointFromName(vp.getName());
            for (final RepresentationDescription representation : viewpoint.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals(name))
                    return (DiagramDescription) representation;
            }

        }
        return null;
    }

    protected ContainerMapping findContainerMapping(final DiagramDescription diagram, final String name) {
        for (final ContainerMapping mapping : diagram.getContainerMappings()) {
            if (name.equals(mapping.getName()))
                return mapping;
        }
        throw new IllegalArgumentException(name + " is not a valid container mapping name");
    }

    /**
     * find an element by name.
     * 
     * @param container
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DNodeContainer container, final String name) {
        for (final DDiagramElement elem : container.getOwnedDiagramElements()) {
            if (elem.getName().equals(name))
                return elem;
        }
        return null;
    }

    /**
     * find an element by name.
     * 
     * @param container
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DNodeList container, final String name) {
        for (final DDiagramElement elem : container.getElements()) {
            if (elem.getName().equals(name))
                return elem;
        }
        return null;
    }

    /**
     * find an element by name.
     * 
     * @param diagram
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DDiagram diagram, final String name) {
        DDiagramElement found = null;
        final Iterator<DDiagramElement> it = diagram.getOwnedDiagramElements().iterator();
        while (it.hasNext() && found == null) {
            final DDiagramElement elem = it.next();
            if (elem.getName().equals(name))
                found = elem;
            if (elem instanceof DNodeContainer) {
                final DDiagramElement result = findElementNamed((DNodeContainer) elem, name);
                if (result != null)
                    found = result;
            } else if (elem instanceof DNodeList) {
                final DDiagramElement result = findElementNamed((DNodeList) elem, name);
                if (result != null)
                    found = result;
            }
        }
        return found;
    }

    protected void doCleanupSession() {
        if (session != null) {
            final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
            if (sessionUI != null) {
                SessionUIManager.INSTANCE.remove(sessionUI);
                sessionUI.close();
                TestsUtil.emptyEventsFromUIThread();
            }
            if (session.isOpen()) {
                session.close(new NullProgressMonitor());
            }
            for (final Session session2 : SessionManager.INSTANCE.getSessions()) {
                assertFalse("Remove failed", session2.equals(session));
            }
            session = null;
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // System.out.println(DslCommonPlugin.PROFILER.getStatus());
        doCleanupSession();
        // Delete the temporary project
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        try {
            if (project.exists())
                project.delete(true, new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
        super.tearDown();
    }

}
