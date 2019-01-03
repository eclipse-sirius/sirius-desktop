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
package org.eclipse.sirius.tests.unit.perf.diagram.refresh;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.unit.diagram.refresh.AbstractSynchronizerTest;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;

public class DiagramSynchronizerMappingScaleTest extends AbstractSynchronizerTest {

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testDummyToLoadPlugins() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Node Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);
        prepareSynchronizer(classDiag, "Test class diagram");
        getRefreshedDiagram();
    }

    /**
     * Really do the test with a given number of elements.
     * 
     * @param nbNewElements
     *            nb elements.
     */
    protected void doTestRefreshMappingScale(int nbNewElements) {
        final DiagramDescription classDiag = findDiagramDescription("Node Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);
        NodeMapping originalMapping = (NodeMapping) classDiag.getAllNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, originalMapping);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        prepareSynchronizer(classDiag, "Test class diagram");

        for (int j = 1; j < nbNewElements + 1; j++) {
           final NodeMapping newMapping = (NodeMapping) SiriusCopierHelper.copyWithNoUidDuplication(originalMapping);
            newMapping.setName("New Mapping" + j);
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    classDiag.getNodeMappings().add(newMapping);
                }
            });
            for (int i = 0; i < NB_ITERATIONS; i++) {
                getRefreshedDiagram();
            }
        }
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale5() throws Exception {
        doTestRefreshMappingScale(5);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale10() throws Exception {
        doTestRefreshMappingScale(10);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale15() throws Exception {
        doTestRefreshMappingScale(15);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale20() throws Exception {
        doTestRefreshMappingScale(20);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale25() throws Exception {
        doTestRefreshMappingScale(25);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale50() throws Exception {
        doTestRefreshMappingScale(50);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshMappingScale75() throws Exception {
        doTestRefreshMappingScale(75);
    }

}
