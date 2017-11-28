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
package org.eclipse.sirius.tests.unit.common;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.LinkedHashSet;

import org.easymock.EasyMock;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.command.AbstractSWTCallback;

import junit.framework.TestCase;

/**
 * Check the result of the method getSessionNameToDisplayWhileSaving of
 * AbstractSWTCallback. VP-2612
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SessionLabelTest extends TestCase {

    /**
     * Test for a normal representations file using a platform resource URI.
     */
    public void testSessionNameToDisplayWhileSavingWithNormalRepresentationsFile() {
        AbstractSWTCallback callBack = new AbstractSWTCallback() {
            @Override
            protected String getVariableNameForRepresentation() {
                return null;
            }
        };
        Session session = initializeMockObjects(URI.createPlatformResourceURI("/myProject/my.aird", true));
        String sessionName = callBack.getSessionNameToDisplayWhileSaving(session);
        assertEquals("The session name is not correct for a classical session.", "Representations for project myProject", sessionName);
    }

    /**
     * Test for a representations file using InMemory URI.
     */
    public void testSessionNameToDisplayWhileSavingWithInMemoryRepresentationsFile() {
        AbstractSWTCallback callBack = new AbstractSWTCallback() {
            @Override
            protected String getVariableNameForRepresentation() {
                return null;
            }
        };
        Session session = initializeMockObjects(URI.createGenericURI(URIQuery.INMEMORY_URI_SCHEME, "newFile.aird", null));
        String sessionName = callBack.getSessionNameToDisplayWhileSaving(session);
        assertEquals("The session name is not correct for a classical session.", "Representations for \"/newFile.aird\"", sessionName);
    }

    /**
     * Test for a representations file that not using InMemory URI or platform
     * resource URI.
     */
    public void testSessionNameToDisplayWhileSavingWithRepresentationsFileWithSpecificURI() {
        AbstractSWTCallback callBack = new AbstractSWTCallback() {
            @Override
            protected String getVariableNameForRepresentation() {
                return null;
            }
        };
        Session session = initializeMockObjects(URI.createGenericURI("scheme", "id", null));
        String sessionName = callBack.getSessionNameToDisplayWhileSaving(session);
        assertEquals("The session name is not correct for a classical session.", "Representations for \"scheme:id\"", sessionName);
    }

    /**
     * This method initializes the mock objects that return what is expected.
     * 
     * @return a mock Session
     */
    protected Session initializeMockObjects(URI uriToCheck) {
        Resource sessionResource = EasyMock.createMock(Resource.class);
        ResourceSet resourceSet = EasyMock.createMock(ResourceSet.class);
        Session session = EasyMock.createMock(Session.class);
        // AbstractSWTCallback.getSessionNameToDisplayWhileSaving() -->
        // session.getSessionResource()
        expect(session.getSessionResource()).andReturn(sessionResource);
        // AbstractSWTCallback.getSessionNameToDisplayWhileSaving() -->
        // representationsFileResource.getURI()
        expect(sessionResource.getURI()).andReturn(uriToCheck);
        // AbstractSWTCallback.getSessionNameToDisplayWhileSaving() -->
        // ResourceSetSync.getStatus(representationsFileResource)
        // ResourceSetSync.getStatus(representationsFileResource) -->
        // res.isModified()
        expect(sessionResource.isModified()).andReturn(false);
        // ResourceSetSync.getStatus(representationsFileResource) -->
        // res.getResourceSet();
        expect(sessionResource.getResourceSet()).andReturn(null);
        // AbstractSWTCallback.getSessionNameToDisplayWhileSaving() -->
        // semanticResourcesDirty(session)
        // semanticResourcesDirty(session) --> getAllSemanticResources(session)
        expect(session.getSemanticResources()).andReturn(new LinkedHashSet<Resource>());

        replay(session);
        replay(sessionResource);
        replay(resourceSet);
        return session;
    }
}
