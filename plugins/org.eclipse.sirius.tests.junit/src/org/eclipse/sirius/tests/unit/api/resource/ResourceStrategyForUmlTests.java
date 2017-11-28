/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.resource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Check ResourceStrategy mechanisms for uml special case. Default resource
 * strategy should avoid leak on {@link CacheAdapter}
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ResourceStrategyForUmlTests extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/resource/";

    private static final String REPRESENTATIONS_FILE_NAME = "representationsUml.aird";

    private static final String MODEL_FILE_NAME = "My.uml";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        initSession();
    }

    private void initSession() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);

        genericSetUp(Collections.<URI> emptyList(), Collections.<URI> emptyList(), true, toURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, ResourceURIType.RESOURCE_PLATFORM_URI));
    }

    /**
     * Check the usage of the contributed resource strategy or the default
     * resource strategy.
     *
     * @throws Exception
     */
    public void testDefaultOptimizedResourceStrategy() throws Exception {
        Resource semRes = session.getSemanticResources().iterator().next();
        EObject eObject = semRes.getContents().get(0);

        // As CacheAdapter crossReferencer is static, it may contain elements
        // added with previous test in the suite
        // So we get only the crossReferenced object of this test
        List<EObject> refObbjects = new ArrayList<>();
        TreeIterator<EObject> eAllContents = eObject.eAllContents();
        while (eAllContents.hasNext()) {
            EObject eObj = (EObject) eAllContents.next();
            if (eObj instanceof NamedElement && "referenced".equals(((NamedElement) eObj).getName())) {
                refObbjects.add(eObj);
            }
        }
        ECrossReferenceAdapter crossReferenceAdapter = CacheAdapter.getCrossReferenceAdapter(eObject);
        EcoreUtil.CrossReferencer inverseCrossReferencer = getInverseCrossReferencer(crossReferenceAdapter);
        Collection<EObject> inverseRef = inverseCrossReferencer.keySet();
        assertTrue("The CacheAdapter does not retain the uml resource content", inverseRef != null && inverseRef.containsAll(refObbjects));

        session.close(new NullProgressMonitor());

        inverseRef = inverseCrossReferencer.keySet();
        assertTrue("The CacheAdapter still retains the uml resource content", inverseRef == null || (refObbjects.retainAll(inverseRef) && refObbjects.isEmpty()));

        // Check that this ResourceStrategy has been used
        assertTrue("The resource is unloaded : " + semRes, semRes.isLoaded());
    }

    private EcoreUtil.CrossReferencer getInverseCrossReferencer(ECrossReferenceAdapter crossReferenceAdapter) throws Exception {
        Field inverseCrossReferencerField = ECrossReferenceAdapter.class.getDeclaredField("inverseCrossReferencer");
        inverseCrossReferencerField.setAccessible(true);
        return (EcoreUtil.CrossReferencer) inverseCrossReferencerField.get(crossReferenceAdapter);
    }
}
