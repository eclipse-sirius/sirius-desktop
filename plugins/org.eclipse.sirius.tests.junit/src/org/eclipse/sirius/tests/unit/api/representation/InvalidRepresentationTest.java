/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.representation;

import java.util.Collections;
import java.util.Optional;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Check the behavior of invalid representations.
 * 
 * @author lfasani
 */
public class InvalidRepresentationTest extends GenericTestCase {

    private static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID;

    private static final String SEMANTIC_MODEL = "My.ecore";

    private static final String AIRD_MODEL = "representations.aird";

    private static final String PATH_PROJECT_SPLIT = PLUGIN + "/data/unit/representation/invalid/split/";

    private static final String PATH_PROJECT_NON_SPLIT = PLUGIN + "/data/unit/representation/invalid/nonSplit/";

    private static final String DIAGRAM_NAME_1 = "P0 package entities Correct";

    private static final String DIAGRAM_NAME_2 = "P0 package entities with bad repPath fragment";

    private static final String DIAGRAM_NAME_3 = "P0 package entities with bad segment URI";

    private static final String DIAGRAM_NAME_4 = "P0 package entities with no existing rep";

    private static final String DIAGRAM_NAME_5 = "P0 package entities with null RepPath";

    private static final String DIAGRAM_NAME_6 = "newPackage1 package entities with NULL target";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Checks that invalid representations are considered as such.<br/>
     * The representations are stored in the aird file.
     * 
     * @throws Exception
     */
    public void testInvalidRepresentationWithNonSplitRep() throws Exception {
        genericSetUp(Collections.singleton(PATH_PROJECT_NON_SPLIT + SEMANTIC_MODEL), Collections.<String> emptyList(), PATH_PROJECT_NON_SPLIT + AIRD_MODEL);

        // check valid representation
        checkRepresentationValidity(DIAGRAM_NAME_1, true);

        // check invalid representations
        checkRepresentationValidity(DIAGRAM_NAME_2, false);
        checkRepresentationValidity(DIAGRAM_NAME_3, false);
        checkRepresentationValidity(DIAGRAM_NAME_4, false);
        checkRepresentationValidity(DIAGRAM_NAME_5, false);
        checkRepresentationValidity(DIAGRAM_NAME_6, false);
    }

    /**
     * Checks that invalid representations are considered as such.<br/>
     * The representations are stored in separated srm files.
     * 
     * @throws Exception
     */
    public void testInvalidRepresentationWithSplitRep() throws Exception {
        genericSetUp(Collections.singleton(PATH_PROJECT_SPLIT + SEMANTIC_MODEL), Collections.<String> emptyList(), PATH_PROJECT_SPLIT + AIRD_MODEL);

        // check valid representation
        checkRepresentationValidity(DIAGRAM_NAME_1, true);

        // check invalid representations
        // Ideally, representation with bad repPath fragment (DIAGRAM_NAME_2 case) should be considered as invalid
        // but to know if the fragment is valid we should load the resource that we do not want to do to avoid breaking
        // the representation lazy loading
        checkRepresentationValidity(DIAGRAM_NAME_2, true); // limitation case
        checkRepresentationValidity(DIAGRAM_NAME_3, false);
        checkRepresentationValidity(DIAGRAM_NAME_4, false);
        checkRepresentationValidity(DIAGRAM_NAME_5, false);
        checkRepresentationValidity(DIAGRAM_NAME_6, false);
    }

    private DRepresentationDescriptor getRepresentationDescriptor(String repDescName) {
        Optional<DRepresentationDescriptor> repDescOpt = DialectManager.INSTANCE.getAllRepresentationDescriptors(session).stream().filter(repDesc -> repDescName.equals(repDesc.getName())).findFirst();
        if (repDescOpt.isPresent()) {
            return repDescOpt.get();
        } else {
            fail("The representation descriptor " + repDescName + " was not found");
        }
        return null;
    }

    private void checkRepresentationValidity(String repDescName, boolean expectedValidity) {
        DRepresentationDescriptor repDescriptor = getRepresentationDescriptor(repDescName);
        assertEquals(repDescriptor.getName() + " DRepresentationDescriptor has bad validity state", expectedValidity, new DRepresentationDescriptorQuery(repDescriptor).isRepresentationValid());
    }
}
