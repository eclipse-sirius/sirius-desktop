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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Group;

public class SiriusRegistryListener2Tests extends SiriusDiagramTestCase implements EcoreModeler {

    /**
     * Add a blank {@link Group} to the VSM resource.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private final class AddBlankGroupResourceCommand extends RecordingCommand {
        private Resource vsmResource;

        public AddBlankGroupResourceCommand(TransactionalEditingDomain domain, Resource vsmResource) {
            super(domain);
            setLabel("Add a blank group in VSM resource");
            this.vsmResource = vsmResource;
        }

        @Override
        protected void doExecute() {
            vsmResource.getContents().add(DescriptionFactory.eINSTANCE.createGroup());
        }

    }

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

        final Resource odesignRes = createVsmResourceWithBlankGroup("file");
        odesignRes.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());
    }

    public void testMultipleVSMFileCreation() throws Exception {

        listener = new SiriusRegistryListener2WithCounter();
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource odesignRes1 = createVsmResourceWithBlankGroup("file1");
        odesignRes1.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, getNumberOfListenerCall());

        final Resource odesignRes2 = createVsmResourceWithBlankGroup("file2");
        odesignRes2.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(2, getNumberOfListenerCall());

        final Resource odesignRes3 = createVsmResourceWithBlankGroup("file3");
        odesignRes3.save(Collections.<Object, Object> emptyMap());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(3, getNumberOfListenerCall());
    }

    public void testVSMFileCreationFollowedByANotVSMFileCreation() throws Exception {

        listener = new SiriusRegistryListener2WithCounter();
        ViewpointRegistry.getInstance().addListener(listener);

        final Resource odesignRes = createVsmResourceWithBlankGroup("file");
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

    /**
     * Create a VSM resource with a blank {@link Group}.
     * 
     * @param fileName
     *            The name of the file (without extension).
     * @return A new resource
     */
    protected Resource createVsmResourceWithBlankGroup(String fileName) {
        final Resource odesignRes = session.getTransactionalEditingDomain().getResourceSet()
                .createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + fileName + "." + SiriusUtil.DESCRIPTION_MODEL_EXTENSION, true));
        session.getTransactionalEditingDomain().getCommandStack().execute(new AddBlankGroupResourceCommand(session.getTransactionalEditingDomain(), odesignRes));
        return odesignRes;
    }

    @Override
    protected void tearDown() throws Exception {
        if (listener != null)
            ViewpointRegistry.getInstance().removeListener(listener);

        TestsUtil.emptyEventsFromUIThread();
        super.tearDown();
    }
}
