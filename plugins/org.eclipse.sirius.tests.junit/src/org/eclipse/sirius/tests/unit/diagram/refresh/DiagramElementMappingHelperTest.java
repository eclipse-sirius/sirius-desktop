/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramElementMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.NodeMapping;

import com.google.common.collect.Iterators;

import junit.framework.TestCase;

/**
 * Test the {@link DiagramElementMappingHelper} class.
 * 
 * @author lredor
 */
public class DiagramElementMappingHelperTest extends TestCase {
    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private String representationsFilePath = "/org.eclipse.sirius.tests.junit/data/unit/refresh/sessionWith2SemanticModels/My.aird";

    /**
     * Test the
     * {@link DiagramElementMappingHelper#getSemanticIterator(org.eclipse.sirius.diagram.description.AbstractNodeMapping, EObject, EObject)}
     * method.
     * 
     * @throws Exception
     *             on error.
     */
    public void testGetSemanticIterator() throws Exception {
        // Initialization
        URI sessionResourceURI = URI.createPlatformPluginURI(representationsFilePath, true);
        final Session session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 2, session.getSemanticResources().size());
        EPackage rootPackage = null;
        DSemanticDiagram diagram = null;
        NodeMapping nodeMapping = null;
        try {
            rootPackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
            diagram = (DSemanticDiagram) DialectManager.INSTANCE.getRepresentations(rootPackage, session).iterator().next();
            nodeMapping = LayerModelHelper.getAllLayers(diagram.getDescription()).get(0).getNodeMappings().get(0);
        } catch (Exception e) {
            fail(THE_UNIT_TEST_DATA_SEEMS_INCORRECT + ":" + e.getMessage());
        }
        // Test
        Iterator<EObject> iter = DiagramElementMappingHelper.getSemanticIterator(nodeMapping, rootPackage, diagram);
        assertEquals("Wrong number of classes. It should be one in the first resource and one in the second resource.", 2, Iterators.size(iter));
    }
}
