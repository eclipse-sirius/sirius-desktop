/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.common.tools.api.editing.DefaultEditingDomainFactory;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryDescriptor;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryRegistry;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.common.tools.api.editing.IEditingDomainFactory;
import org.eclipse.sirius.common.tools.api.editing.StandaloneEditingDomainFactoryDescriptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * Unit tests for {@link EditingDomainFactoryService}
 * 
 * @author edugueperoux
 */
public class EditingDomainFactoryServiceTests {

    /**
     * Save extension to avoid side effect of other plugin because this pur unit
     * test is executed as JUnit Plugin test
     **/
    private List<EditingDomainFactoryDescriptor> savedRegisteredExtensions;

    @Before
    public void setUp() {
        savedRegisteredExtensions = EditingDomainFactoryRegistry.getRegisteredExtensions();
        EditingDomainFactoryRegistry.clearRegistry();
    }

    /**
     * Test that {@link EditingDomainFactoryService} without
     * {@link IEditingDomainFactory} extension return the
     * {@link DefaultEditingDomainFactory}.
     */
    @Test
    public void test_getEditingDomainFactory_WithoutExtension() {
        Assert.assertEquals(DefaultEditingDomainFactory.class, EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().getClass());
    }

    /**
     * Test that {@link EditingDomainFactoryService} with
     * {@link IEditingDomainFactory} extension return the extension
     */
    @Test
    public void test_getEditingDomainFactory_WithExtension() {
        IEditingDomainFactory defaultEditingDomainFactory = new DefaultTestEditingDomainFactory();
        EditingDomainFactoryDescriptor defaultEditingDomainFactoryDescriptor = new StandaloneEditingDomainFactoryDescriptor("org.eclipse.sirius.defaultEditingDomainFactoryExtension", null,
                defaultEditingDomainFactory);
        EditingDomainFactoryRegistry.addExtension(defaultEditingDomainFactoryDescriptor);

        IEditingDomainFactory firstMostOverriderExtension = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory();
        Assert.assertEquals(DefaultTestEditingDomainFactory.class, firstMostOverriderExtension.getClass());
    }

    /**
     * Test override of {@link IEditingDomainFactory} extension through
     * {@link EditingDomainFactoryDescriptor}.
     */
    @Test
    public void test_getEditingDomainFactory_Override_WithExtension() {
        IEditingDomainFactory defaultEditingDomainFactory = new DefaultTestEditingDomainFactory();
        EditingDomainFactoryDescriptor defaultEditingDomainFactoryDescriptor = new StandaloneEditingDomainFactoryDescriptor("org.eclipse.sirius.defaultEditingDomainFactoryExtension", null,
                defaultEditingDomainFactory);
        EditingDomainFactoryRegistry.addExtension(defaultEditingDomainFactoryDescriptor);

        IEditingDomainFactory overriddingEditingDomainFactory = new OverriddingTestEditingDomainFactory();
        EditingDomainFactoryDescriptor overriddingEditingDomainFactoryDescriptor = new StandaloneEditingDomainFactoryDescriptor("org.eclipse.sirius.overriddingEditingDomainFactoryExtension",
                defaultEditingDomainFactoryDescriptor.getId(), overriddingEditingDomainFactory);
        EditingDomainFactoryRegistry.addExtension(overriddingEditingDomainFactoryDescriptor);

        IEditingDomainFactory firstMostOverriderExtension = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory();
        Assert.assertEquals(OverriddingTestEditingDomainFactory.class, firstMostOverriderExtension.getClass());
    }

    /**
     * Test the AirdCrossReferencer installation with
     * {@link EditingDomainFactoryService} providing a shared editing domain and
     * resource set.
     */
    @Test
    public void test_SessionWithSharedDomain() {
        SharedTestEditingDomainFactory sharedEditingDomainFactory = new SharedTestEditingDomainFactory();
        EditingDomainFactoryDescriptor sharedEditingDomainFactoryDescriptor = new StandaloneEditingDomainFactoryDescriptor("org.eclipse.sirius.sharedEditingDomainFactoryExtension", null,
                sharedEditingDomainFactory);
        EditingDomainFactoryRegistry.addExtension(sharedEditingDomainFactoryDescriptor);

        IEditingDomainFactory firstMostOverriderExtension = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory();
        Assert.assertEquals(SharedTestEditingDomainFactory.class, firstMostOverriderExtension.getClass());

        assertExpectedGMFCrossReferencerAdapterNumber(sharedEditingDomainFactory, 0);

        URI sessionResourceURI = URI.createPlatformPluginURI("org.eclipse.sirius.tests.junit/data/unit/session/VP-3829/test.aird", true);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        try {
            sessionCreationOperation.execute();
        } catch (CoreException e) {
            fail(e.getMessage());
        }
        Session session = sessionCreationOperation.getCreatedSession();

        assertNotNull(session);
        assertEquals(sharedEditingDomainFactory.sharedDomain, session.getTransactionalEditingDomain());
        assertExpectedGMFCrossReferencerAdapterNumber(sharedEditingDomainFactory, 0);

        session.close(new NullProgressMonitor());
        assertExpectedGMFCrossReferencerAdapterNumber(sharedEditingDomainFactory, 0);
    }

    private void assertExpectedGMFCrossReferencerAdapterNumber(SharedTestEditingDomainFactory sharedEditingDomainFactory, int expected) {
        assertEquals(expected, Iterables.size(Iterables.filter(sharedEditingDomainFactory.sharedDomain.getResourceSet().eAdapters(), CrossReferenceAdapter.class)));
    }

    @After
    public void tearDown() {
        EditingDomainFactoryRegistry.clearRegistry();
        for (EditingDomainFactoryDescriptor extension : savedRegisteredExtensions) {
            EditingDomainFactoryRegistry.addExtension(extension);
        }
    }

    class DefaultTestEditingDomainFactory extends DefaultEditingDomainFactory {

    }

    class OverriddingTestEditingDomainFactory extends DefaultEditingDomainFactory {

    }

    class SharedTestEditingDomainFactory extends DefaultEditingDomainFactory {

        final TransactionalEditingDomain sharedDomain = super.createEditingDomain();

        @Override
        public TransactionalEditingDomain createEditingDomain() {
            return sharedDomain;
        }

        @Override
        public synchronized TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
            return sharedDomain;
        }

    }

}
