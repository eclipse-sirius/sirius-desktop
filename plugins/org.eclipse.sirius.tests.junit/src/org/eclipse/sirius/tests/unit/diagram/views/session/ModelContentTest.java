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
package org.eclipse.sirius.tests.unit.diagram.views.session;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.unit.diagram.session.SessionTest;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionWrapperContentProvider;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * @author fmorel
 * 
 *         Test Model Content Eclipse View.
 */
public class ModelContentTest extends SessionTest {

    /**
     * Check that the eclipse view : Model Content, is correctly built.
     * 
     * @throws Exception
     */
    public void testModelContentView() throws Exception {
        // build the SessionWrapperContentProvider.
        final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        final ComposedAdapterFactory generic = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        factories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        factories.add(generic);
        final AdapterFactory factory = new ComposedAdapterFactory(factories);
        final SessionWrapperContentProvider contentProvider = new SessionWrapperContentProvider(new AdapterFactoryContentProvider(factory));

        // create a session and check that the SessionWrapperContentProvider
        // contains 2 childrens.
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
        semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);
        SessionManager.INSTANCE.getSession(semanticModel);

        final Object[] children = contentProvider.getChildren(session);
        assertEquals("Wrong count of viewpoint folder.", 2, children.length);
        assertTrue("Wrong type for the semantic children.", children[1] instanceof Resource);

        // check that if no viewpoint is initialized, the
        // "diagram by category" folder contains no items.
        Object[] viewpointObjects = contentProvider.getChildren(children[0]);
        assertEquals("Wrong count of viewpoints.", 0, viewpointObjects.length);
        final Group group = (Group) ModelUtils.load(URI.createPlatformPluginURI('/' + PLUGIN + "/model/docbook.odesign", true), session.getTransactionalEditingDomain().getResourceSet());
        viewpoints.addAll(group.getOwnedViewpoints());

        Viewpoint viewpoint = null;
        for (final Viewpoint vp : group.getOwnedViewpoints()) {
            for (final RepresentationDescription representation : vp.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals("obviousDiagram")) {
                    viewpoint = vp;
                    break;
                }
            }
        }

        final Viewpoint vp = getViewpointFromName(viewpoint.getName(), session);

        // initialize the viewpoint with the semantic element corresponding
        // to simple.docbook
        activateViewpoint(vp.getName());

        // check that the "diagram by category" folder contains one
        // viewpoint.
        viewpointObjects = contentProvider.getChildren(children[0]);
        assertEquals("Wrong count of viewpoints.", 1, viewpointObjects.length);

        // check that the "viewpoint" folder contains three diagrams.
        final Object[] descriptions = contentProvider.getChildren(viewpointObjects[0]);
        assertEquals("Wrong count of descriptions.", 3, descriptions.length);
    }
}
