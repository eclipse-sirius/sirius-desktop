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

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.LabelProviderProviderService;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderDescriptor;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderRegistry;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.StandaloneLabelProviderProviderDescriptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Standalone tests for {@link LabelProviderProviderService}
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelProviderProviderServiceTests {

    /**
     * Save extension to avoid side effect of other plugin because this pur unit
     * test is executed as JUnit Plugin test
     **/
    private List<LabelProviderProviderDescriptor> savedRegisteredExtensions;

    private LabelProviderProviderService labelProviderProviderService;

    @Before
    public void setUp() {
        savedRegisteredExtensions = LabelProviderProviderRegistry.getRegisteredExtensions();
        LabelProviderProviderRegistry.clearRegistry();
        labelProviderProviderService = new LabelProviderProviderService();
    }

    /**
     * Test that {@link LabelProviderProviderService} without contributions
     * return null.
     */
    @Test
    public void test_getFirstProvidedLabelProvider_WithoutContribution() {
        String assertMessage = "Without contributions, the LabelProviderProviderService should not return a ILabelProvider";
        Assert.assertNull(assertMessage, labelProviderProviderService.getFirstProvidedLabelProvider(null));
        Assert.assertNull(assertMessage, labelProviderProviderService.getFirstProvidedLabelProvider(EcorePackage.eINSTANCE));
    }

    /**
     * Test that {@link LabelProviderProviderService} with contribution return a
     * {@link ILabelProvider}.
     */
    @Test
    public void test_getFirstProvidedLabelProvider_WithContribution() {
        LabelProviderProviderRegistry.addExtension(new StandaloneLabelProviderProviderDescriptor("1", new LabelProviderProvider1()));
        LabelProviderProviderRegistry.addExtension(new StandaloneLabelProviderProviderDescriptor("2", new LabelProviderProvider2()));
        Assert.assertTrue("As the specified object is null, the LabelProviderProvider2 should be used", labelProviderProviderService.getFirstProvidedLabelProvider(null) instanceof ColumnLabelProvider);
        Assert.assertTrue("As the specified object is not null, the LabelProviderProvider1 should be used", labelProviderProviderService.getFirstProvidedLabelProvider(this) instanceof LabelProvider);
    }

    @After
    public void tearDown() {
        LabelProviderProviderRegistry.clearRegistry();
        for (LabelProviderProviderDescriptor extension : savedRegisteredExtensions) {
            LabelProviderProviderRegistry.addExtension(extension);
        }
    }

    class LabelProviderProvider1 implements ILabelProviderProvider {

        public ILabelProvider getLabelProvider() {
            return new LabelProvider();
        }

        public boolean provides(Object selection) {
            return selection != null;
        }

    }

    class LabelProviderProvider2 implements ILabelProviderProvider {

        public ILabelProvider getLabelProvider() {
            return new ColumnLabelProvider();
        }

        public boolean provides(Object selection) {
            return true;
        }

    }

}
