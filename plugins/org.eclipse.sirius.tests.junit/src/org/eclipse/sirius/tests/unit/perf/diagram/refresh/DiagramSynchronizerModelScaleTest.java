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
package org.eclipse.sirius.tests.unit.perf.diagram.refresh;

import org.eclipse.emf.common.util.AbstractEList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.unit.diagram.refresh.AbstractSynchronizerTest;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;

public class DiagramSynchronizerModelScaleTest extends AbstractSynchronizerTest {

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testDummyToLoadPlugins() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Node Class Diagram");
        assertNotNull("The unit test data seems incorrect", classDiag);
        prepareSynchronizer(classDiag, "Test class diagram");
        getRefreshedDiagram();
    }

    /**
     * Really do the test.
     * 
     * @param nbNewElements
     *            nb elements.
     */
    protected void doTestRefreshModelScale(int nbNewElements) {
        DiagramDescription classDiag = findDiagramDescription("Model Scaling Class Diagram");
        assertNotNull("The unit test data seems incorrect", classDiag);

        prepareSynchronizer(classDiag, "Test class diagram");
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ((org.eclipse.uml2.uml.Model) semanticModel).getPackagedElements().clear();
            }
        });

        org.eclipse.uml2.uml.Class previousClass = null;

        for (int j = 1; j < nbNewElements + 1; j++) {
            DDiagram diagram = null;
            final org.eclipse.uml2.uml.Class newClass = UMLFactory.eINSTANCE.createClass();
            newClass.setName("Class" + j);
            if (previousClass == null) {
                Property prop = UMLFactory.eINSTANCE.createProperty();
                prop.setType(previousClass);
                ((AbstractEList) newClass.getOwnedAttributes()).addUnique(prop);
            }
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    ((AbstractEList) ((org.eclipse.uml2.uml.Model) semanticModel).getPackagedElements()).addUnique(newClass);
                }
            });
            for (int i = 0; i < NB_ITERATIONS; i++) {
                diagram = getRefreshedDiagram();
            }
            previousClass = newClass;
            assertEquals("We should have " + j + " nodes here", j, diagram.getOwnedDiagramElements().size());
        }
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale10() throws Exception {
        doTestRefreshModelScale(10);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale20() throws Exception {
        doTestRefreshModelScale(20);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale30() throws Exception {
        doTestRefreshModelScale(30);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale40() throws Exception {
        doTestRefreshModelScale(40);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale50() throws Exception {
        doTestRefreshModelScale(50);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale60() throws Exception {
        doTestRefreshModelScale(60);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale70() throws Exception {
        doTestRefreshModelScale(70);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale100() throws Exception {
        doTestRefreshModelScale(100);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale150() throws Exception {
        doTestRefreshModelScale(150);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale200() throws Exception {
        doTestRefreshModelScale(200);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale250() throws Exception {
        doTestRefreshModelScale(250);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale300() throws Exception {
        doTestRefreshModelScale(300);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale350() throws Exception {
        doTestRefreshModelScale(350);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale500() throws Exception {
        doTestRefreshModelScale(500);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale1000() throws Exception {
        doTestRefreshModelScale(1000);
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testRefreshModelScale2000() throws Exception {
        doTestRefreshModelScale(2000);
    }

}
