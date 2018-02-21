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
package org.eclipse.sirius.tests.unit.diagram.services;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Verify that operation is rollbacked when a java service throws an
 * {@link OperationCanceledException} containing "-RT-" in its message. Acceleo
 * is not tested. Indeed, the Acceleo interpreter silently catches this kind of
 * exception and would require internal modification, not in scope of bugzilla
 * 531487 but maybe done in more general bugzilla 495036.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ServiceCallsWithOperationCanceledExceptionTest extends SiriusDiagramTestCase {

    protected static final String FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/services/";

    private static final String SEMANTIC_MODEL_PATH = FOLDER_PATH + "My.ecore";

    private static final String REPRESENTATIONS_FILE_PATH = FOLDER_PATH + "representations.aird";

    private static final String MODELER_PATH = FOLDER_PATH + "ProjectWithOperationCanceledException.odesign";

    protected static final String REPRESENTATION_DECRIPTION_NAME = "MyDiagram";

    protected DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_FILE_PATH);
        assertEquals("Just one representation must be present in session", 1, getRepresentations(REPRESENTATION_DECRIPTION_NAME).size());
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DECRIPTION_NAME).toArray()[0];
        assertNotNull(diagram);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

    /**
     * Test that the tool is not rollbacked when the service is called through
     * AQL expression without the specific message in the
     * OperationCanceledException.
     */
    public void testCallingJavaServiceThroughAQLWithoutSpecificMessage() {
        testCallingJavaServiceWithoutSpecificMessage("AQL");
    }

    /**
     * Test that the tool is rollbacked when the service is called through AQL
     * expression with the specific message in the OperationCanceledException.
     */
    public void testCallingJavaServiceThroughAQLWithSpecificMessage() {
        testCallingJavaServiceWithSpecificMessage("AQL");
    }

    /**
     * Test that the tool is not rollbacked when the service is called through
     * service interpreter without the specific message in the
     * OperationCanceledException.
     */
    public void testCallingJavaServiceThroughServiceInterpreterWithoutSpecificMessage() {
        testCallingJavaServiceWithoutSpecificMessage("Service");
    }

    /**
     * Test that the tool is rollbacked when the service is called through
     * service interpreter with the specific message in the
     * OperationCanceledException.
     */
    public void testCallingJavaServiceThroughServiceInterpreterWithSpecificMessage() {
        testCallingJavaServiceWithSpecificMessage("Service");
    }

    /**
     * Test that the tool is not rollbacked when the service is called through a
     * specific interpreter without the specific message in the
     * OperationCanceledException. The interpreter is designed by the suffix of
     * the tool name: <code>interpreterSuffixToolName</code>.
     * 
     * @param interpreterSuffixToolName
     *            a suffix corresponding to the interpreter used by the tool.
     */
    private void testCallingJavaServiceWithoutSpecificMessage(String interpreterSuffixToolName) {
        String toolName = "NewClassWith" + interpreterSuffixToolName;
        assertEquals("Wrong initial nomber of nodes.", 0, diagram.getNodes().size());
        assertTrue("Class creation tool, " + toolName + ", fails.", applyNodeCreationTool(toolName, diagram, diagram));
        assertEquals("Class creation tool, " + toolName + ", does not create the first Class before the throw OperationCanceledException.", 1, diagram.getNodes().size());
    }

    /**
     * Test that the tool is rollbacked when the service is called through AQL
     * expression with the specific message in the OperationCanceledException.
     * The interpreter is designed by the suffix of the tool name:
     * <code>interpreterSuffixToolName</code>.
     * 
     * @param interpreterSuffixToolName
     *            a suffix corresponding to the interpreter used by the tool.
     */
    private void testCallingJavaServiceWithSpecificMessage(String interpreterSuffixToolName) {
        String toolName = "NewClassWith" + interpreterSuffixToolName + "WithSpecificMessage";
        assertEquals("Wrong initial nomber of nodes", 0, diagram.getNodes().size());
        assertTrue("Class creation tool, " + toolName + ", fails", applyNodeCreationTool(toolName, diagram, diagram));
        assertEquals("Class creation tool, " + toolName + ", is not fully rollbacked.", 0, diagram.getNodes().size());
    }

}
