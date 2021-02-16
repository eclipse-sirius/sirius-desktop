/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased.MappingBasedTestConfiguration;
import org.junit.Before;

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager}. Inspired from
 * {@link SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest}.
 * 
 * @author lredor
 */
public class MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramDiffSessionTest extends MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest {

    public MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramDiffSessionTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    protected Session targetSession;

    protected EObject targetSemanticModel;

    protected EObject targetSemanticTargetModel;

    /** Name of the file containing the session target mapped elements */
    protected static final String SESSION_TARGET_MODEL_NAME = "fixture2.aird";

    @Override
    @Before
    public void setUp() throws Exception {

        genericSetUp(getSemanticTargetModelPaths(), getModelerPathAsList(), getSessionTargetModelPath());

        targetSession = session;
        targetSemanticModel = semanticModel;
        targetSemanticTargetModel = getModelFromPath(getSemanticTargetModelPaths().get(0), targetSession);

        super.setUp();
    }

    protected List<String> getSemanticTargetModelPaths() {
        return Collections.singletonList(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticTargetModelName());
    }

    protected String getSessionTargetModelPath() {
        return PLUGIN_PATH + getPlatformRelatedDataPath() + SESSION_TARGET_MODEL_NAME;
    }

    protected MappingBasedTestConfiguration getFullTestConfiguration(EObject root) {
        Map<String, String> full_map = new HashMap<String, String>();
        String sourceRootID = MappingBasedTestConfiguration.getID(root);
        String targetRootID = MappingBasedTestConfiguration.getID(root, "target");
        full_map.put(sourceRootID, targetRootID);

        root.eAllContents().forEachRemaining(element -> {
            String sourceID = MappingBasedTestConfiguration.getID(element);
            String targetID = MappingBasedTestConfiguration.getID(element, "target");
            full_map.put(sourceID, targetID);
        });

        return new MappingBasedTestConfiguration(semanticModel, targetSemanticTargetModel, full_map, targetRootID, "full");
    }

    @Override
    protected Session getTargetSession() {
        return targetSession;
    }
}
