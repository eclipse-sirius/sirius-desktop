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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.unit.common.AbstractEcoreSynchronizerTest;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class EntitiesDiagramModelScaleTest extends AbstractEcoreSynchronizerTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        final Viewpoint viewpoint = viewpoints.iterator().next();
        activateViewpoint(viewpoint.getName());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testDummyToLoadPlugins() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Entities");
        assertNotNull("The unit test data seems incorrect", classDiag);
        prepareSynchronizer(classDiag, "Test class diagram");
        @SuppressWarnings("unused")
        DDiagram diagram = getRefreshedDiagram();
    }

    /**
     * Really do the test.
     * 
     * @param nbNewElements
     *            nb elements.
     */
    protected void doTestRefreshModelScale(int nbNewElements) {
        DiagramDescription classDiag = findDiagramDescription("Entities");
        assertNotNull("The unit test data seems incorrect", classDiag);

        prepareSynchronizer(classDiag, "Test class diagram");
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                ((EPackage) semanticModel).getEClassifiers().clear();
            }
        });

        EClass previousClass = null;

        for (int j = 1; j < nbNewElements + 1; j++) {
            DDiagram diagram = null;
            final EClass newClass = EcoreFactory.eINSTANCE.createEClass();
            newClass.setName("Class" + j);
            if (previousClass == null) {
                EReference prop = EcoreFactory.eINSTANCE.createEReference();
                prop.setEType(previousClass);
                newClass.getEStructuralFeatures().add(prop);
            }
            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                @Override
                protected void doExecute() {
                    ((EPackage) semanticModel).getEClassifiers().add(newClass);
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
    public void testRefreshModelScale200() throws Exception {
        doTestRefreshModelScale(200);
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
}
