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

import java.util.List;

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

}
