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
package org.eclipse.sirius.tests.unit.api.find;

import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.ui.tools.internal.find.BasicFindLabelEngine;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Sets;

public class FindTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/find/ecore-search.ecore";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/find/ecore-search.aird";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private BasicFindLabelEngine engine;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        DRepresentation firstRepresentation = DialectManager.INSTANCE.getAllRepresentations(session).iterator().next();
        DiagramEditor editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, firstRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        engine = new BasicFindLabelEngine(editor);
    }

    public void testSearchPrefix() throws Exception {
        Set<Object> result = search("Prefix");
        assertEquals(6, result.size());
    }

    public void testSearchSuffix() throws Exception {
        Set<Object> result = search("Suffix");
        assertEquals(6, result.size());
    }

    public void testSearchMiddle() throws Exception {
        Set<Object> result = search("Middle");
        assertEquals(5, result.size());
    }

    public void testSearchEdges() throws Exception {
        Set<Object> result = search("XMiddleY");
        assertEquals(1, result.size());
        result = search("BetweenContainedClasses");
        assertEquals(1, result.size());
    }

    private Set<Object> search(final String text) {
        engine.initFind(text);
        Set<Object> result = Sets.newHashSet();
        Object found = engine.getNext();
        while (found != null && !result.contains(found)) {
            result.add(found);
            found = engine.getNext();
        }
        return result;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
