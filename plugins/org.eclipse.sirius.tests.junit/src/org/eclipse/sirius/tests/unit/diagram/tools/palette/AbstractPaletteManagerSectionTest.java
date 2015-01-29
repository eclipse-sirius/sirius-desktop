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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.Collection;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Iterables;

/**
 * Comon code for palette tests
 * 
 * @author dlecan
 */
public abstract class AbstractPaletteManagerSectionTest extends AbstractPaletteManagerTest {

    protected static final String SEMANTIC_MODEL_FILENAME = "toolSection.ecore";

    protected static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME;
    
    protected static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "toolSection.odesign";

    protected static final String SESSION_MODEL_FILENAME = "toolSection.aird";
    
    protected static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        SessionUIManager.INSTANCE.createUISession(session);

        Collection<DRepresentation> representations = getRepresentations(getRepresentationDescriptionName());
        for (DRepresentation repr : representations) {
            if (repr.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) repr;
                break;
            }
        }
        assertNotNull("DDiagram not found", dDiagram);

        diagram = (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
