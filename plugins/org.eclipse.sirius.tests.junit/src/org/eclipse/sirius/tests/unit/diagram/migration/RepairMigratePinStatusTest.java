/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Test class to test removal of DDiagramSet during repair/migrate action of
 * aird file.
 * 
 * @author lredor
 */
public class RepairMigratePinStatusTest extends AbstractRepairMigrateTest {

    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/repair/VP-1928/";

    private static final String SEMANTIC_MODEL = "VP-1928.ecore";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + SEMANTIC_MODEL;

    private static final String SESSION_NAME = "VP-1928.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String PLUGIN_PATH = "/data/unit/repair/VP-1928";

    private static final String[] FILES = { SESSION_NAME, SEMANTIC_MODEL };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(Collections.singleton(SEMANTIC_MODEL_PATH), Collections.<String> emptySet(), SESSION_PATH);

        for (String path : FILES) {
            String pluginPath = PLUGIN_PATH + "/" + path;
            String wksPath = TEMPORARY_PROJECT_NAME + "/" + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testPinStatusConservation() throws Exception {
        String path = "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_NAME;
        List<DAnalysis> data = loadFile(path);

        checkData(data);

        runRepairProcess(SESSION_NAME);

        data = loadFile(path);

        checkData(data);
    }

    /**
     * @param data
     */
    private void checkData(List<DAnalysis> data) {
        for (DAnalysis dAnalysis : data) {
            for (DView view : dAnalysis.getOwnedViews()) {
                List<DRepresentation> loadedRepresentations = new DViewQuery(view).getLoadedRepresentations();
                Collection<DNodeList> dNodeLists = Lists.newArrayList();
                for (DRepresentation dRepresentation : loadedRepresentations) {
                    Iterators.addAll(dNodeLists, Iterators.filter(dRepresentation.eAllContents(), DNodeList.class));
                }
                Iterator<DNodeList> dNodeListIterator = dNodeLists.iterator();
                // Check that there is only one DNodeList element
                assertTrue("It should be at least have one DNodeList element.", dNodeListIterator.hasNext());
                DNodeList dNodeList = dNodeListIterator.next();
                assertFalse("Only one DNodeList element should exist.", dNodeListIterator.hasNext());
                // Check that the dNodeList element is pinned
                assertTrue("The DNodeList element must be pinned.", new PinHelper().isPinned(dNodeList));
            }
        }
    }

    private List<DAnalysis> loadFile(final String path) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createPlatformResourceURI(path, true);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        resource.load(Collections.EMPTY_MAP);

        return Lists.newArrayList(Iterables.filter(resource.getContents(), DAnalysis.class));
    }
}
