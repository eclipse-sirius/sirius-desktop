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
package org.eclipse.sirius.tests.unit.api.session;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;

import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

public class SiriusRegistryListener2Tests extends SiriusDiagramTestCase implements EcoreModeler {

    private ViewpointRegistryListener2 listener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
        listener = null;
    }

    public void testNotVSMFileCreation() throws Exception {
        listener = new ViewpointRegistryListener2() {
            public void modelerDesciptionFilesLoaded() {
                fail("our listener should not be called");
            }
        };
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource ecoreRes = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file.ecore", true));
        ecoreRes.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();
    }

    public void testVSMFileCreation() throws Exception {

        listener = new SiriusRegistryListener2WithCounter();
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource odesignRes = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file.odesign", true));
        odesignRes.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());
    }

    public void testMultipleVSMFileCreation() throws Exception {

        listener = new SiriusRegistryListener2WithCounter();
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource odesignRes1 = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file1.odesign", true));
        odesignRes1.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());

        final Resource odesignRes2 = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file2.odesign", true));
        odesignRes2.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(2, getNumberOfListenerCall());

        final Resource odesignRes3 = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file3.odesign", true));
        odesignRes3.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(3, getNumberOfListenerCall());
    }

    public void testVSMFileCreationFollowedByANotVSMFileCreation() throws Exception {

        listener = new SiriusRegistryListener2WithCounter();
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource odesignRes = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file.odesign", true));
        odesignRes.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());

        final Resource ecoreRes = session.getTransactionalEditingDomain().getResourceSet().createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/file.ecore", true));
        ecoreRes.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());
    }

    private int getNumberOfListenerCall() {
        return ((SiriusRegistryListener2WithCounter) listener).getCount();
    }

    private final class SiriusRegistryListener2WithCounter implements ViewpointRegistryListener2 {

        private int i;

        public int getCount() {
            return i;
        }

        public void modelerDesciptionFilesLoaded() {
            i++;
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (listener != null)
            ViewpointRegistry.getInstance().removeListener(listener);

        TestsUtil.emptyEventsFromUIThread();
        super.tearDown();
    }
}
