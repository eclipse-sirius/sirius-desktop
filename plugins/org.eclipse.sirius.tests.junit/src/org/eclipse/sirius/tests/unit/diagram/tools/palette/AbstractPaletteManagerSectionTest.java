/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.Collection;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Iterables;

/**
 * Common code for palette tests
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

        diagram = getDiagram(getRepresentationDescriptionName(), getRepresentationDescriptionInstanceName());
    }

    /**
     * Get the representation with the given representation description name and
     * the given name.
     * 
     * @param diagramDescriptionName
     *            the name of the diagram description. <code>null</code> is not
     *            excepted.
     * @param diagramName
     *            the name of the diagram. <code>null</code> is not excepted.
     * @return The corresponding diagram
     */
    protected Diagram getDiagram(String diagramDescriptionName, String diagramName) {
        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(diagramDescriptionName);
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals(diagramName)) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        assertNotNull("DDiagram not found", dDiagram);

        return (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);
    }
}
