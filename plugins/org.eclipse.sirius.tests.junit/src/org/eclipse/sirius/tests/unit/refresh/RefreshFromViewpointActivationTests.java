/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.refresh;

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

/**
 * This class tests behavior of the refresh happening while activating viewpoint whereas no editors are opened.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class RefreshFromViewpointActivationTests extends SiriusDiagramTestCase {

    private static final String VSM_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/viewpointActivation/viewpointActivation.odesign";

    private static final String VSM_EXTENSION_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/viewpointActivation/viewpointActivation2.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/viewpointActivation/viewpointActivation.ecore";

    private static final String AIRD_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/viewpointActivation/representations.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        String[] semanticModelPaths = new String[] { SEMANTIC_MODEL_PATH };
        String[] vsmPaths = new String[] { VSM_PATH, VSM_EXTENSION_PATH };
        genericSetUp(Arrays.asList(semanticModelPaths), Arrays.asList(vsmPaths), AIRD_MODEL_PATH);
        initViewpoint("viewpointActivation");

    }

    /**
     * Tests that activating a viewpoint B containing a mandatory layer and extending another viewpoint A on a session
     * containing a diagram representation correctly refreshes the representation.
     * 
     * No diagram editors is opened when activating viewpoint. The diagram representation has the viewpoint A activated
     * before activating B.
     * 
     * @throws Exception
     */
    public void testRefreshAfterViewpointActivation() throws Exception {
        initViewpoint("ViewpointExtension");
        session.save(new NullProgressMonitor());

        Resource sessionResource = session.getSessionResource();
        DSemanticDiagram semanticDiagram = (DSemanticDiagram) sessionResource.getContents().stream().filter(DSemanticDiagram.class::isInstance).findFirst().get();
        AnnotationEntry annotationEntry = semanticDiagram.getOwnedAnnotationEntries().stream().filter(ann -> ann.getData() instanceof Diagram).findFirst().get();
        Diagram data = (Diagram) annotationEntry.getData();
        
        System.out.println();
        assertEquals("Gmf node has not been supressed.",0, data.getChildren().size());
    }

}
