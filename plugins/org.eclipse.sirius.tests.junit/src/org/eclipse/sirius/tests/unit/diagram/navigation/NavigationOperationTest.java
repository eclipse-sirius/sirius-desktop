/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.navigation;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;

import java.util.Arrays;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.model.tools.internal.DiagramModelPlugin;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.ui.IEditorPart;

/**
 * Test for the "Navigation" model operation, in particular when it need to create a new representation.
 * 
 * @author pcdavid
 */
public class NavigationOperationTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/navigation/vp-1851/vp-1851.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/navigation/vp-1851/vp-1851.odesign";

    private static final String VIEWPOINT_NAME = "VP-1851";

    private static final String REPRESENTATION_DESC_NAME = "Source";

    private static final String ECLASS_NAME = "A";

    private static final String OPERATION_NAME = "Test VP-1851";

    private DDiagram diagram;

    private IEditorPart editorPart;

    private ILogListener logListener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        logListener = createMock(ILogListener.class);
        replay(logListener);
        DiagramPlugin.getDefault().getLog().addLogListener(logListener);
        DiagramModelPlugin.getDefault().getLog().addLogListener(logListener);
        DiagramUIPlugin.getPlugin().getLog().addLogListener(logListener);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
        DiagramModelPlugin.getDefault().getLog().removeLogListener(logListener);
        DiagramPlugin.getDefault().getLog().removeLogListener(logListener);
        DiagramUIPlugin.getPlugin().getLog().removeLogListener(logListener);
        logListener = null;
        editorPart = null;
        diagram = null;
        super.tearDown();
    }

    /**
     * Test for VP-1851 / VP-1852 (NPE due to uninitialized UiCallback).
     * 
     * @throws Exception
     */
    public void test_create_and_open_new_representation_from_tool() throws Exception {
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        EClassifier classA = ((EPackage) semanticModel).getEClassifier(ECLASS_NAME);
        assertNotNull(classA);
        DDiagramElement aDiagramElement = getFirstNodeElement(diagram, classA);
        assertNotNull(aDiagramElement);

        AbstractToolDescription tool = getTool(diagram, OPERATION_NAME);
        assertTrue(tool instanceof OperationAction);
        Command cmd = getCommand(diagram, tool, Arrays.asList((EObject) aDiagramElement));
        setErrorCatchActive(true);
        // This will use the UiCallback (in the context of tests, a
        // NoUICallback) to ask for the new representation name. VP-1851
        // triggered an NPE here.
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

    }
}
