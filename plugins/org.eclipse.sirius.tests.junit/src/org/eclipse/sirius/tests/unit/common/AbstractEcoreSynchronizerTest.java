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
package org.eclipse.sirius.tests.unit.common;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Test.
 * 
 * @author cbrun.
 * 
 */
public abstract class AbstractEcoreSynchronizerTest extends SiriusDiagramTestCase implements EcoreModeler {

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    /**
     * NB of refresh iterations.
     */
    protected static final int NB_ITERATIONS = 20;

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String PATH = "/data/unit/modelers/ecore/";

    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    protected DDiagram sync;
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(getSemanticResourcePath(), MODELER_PATH);
        
    }

    protected String getSemanticResourcePath(){
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        return TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;
    }

    /**
     * prepare the synchronizer.
     * 
     * @param description
     *            a description.
     * @param diagramName
     *            the diagram name.
     */
    protected void prepareSynchronizer(final DiagramDescription repDescription, final String diagramName) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                sync = (DDiagram) DialectManager.INSTANCE.createRepresentation(diagramName, semanticModel, repDescription, session, new NullProgressMonitor());
            }
        });
    }

    /**
     * return the refreshed diagram.
     * 
     * @return the refreshed diagram.
     */
    protected DDiagram getRefreshedDiagram() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, sync));
        return sync;
    }

    /**
     * Find a diagram description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram description or null if not found.
     */
    protected DiagramDescription findDiagramDescription(final String name) {

        for (final Viewpoint vp : viewpoints) {
            for (final RepresentationDescription representation : vp.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals(name))
                    return (DiagramDescription) representation;
            }

        }
        return null;
    }

    /**
     * find an element by name.
     * 
     * @param container
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DNodeContainer container, final String name) {
        for (final DDiagramElement elem : container.getOwnedDiagramElements()) {
            if (elem.getName().equals(name))
                return elem;
        }
        return null;
    }

    /**
     * find an element by name.
     * 
     * @param container
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DNodeList container, final String name) {
        for (final DDiagramElement elem : container.getElements()) {
            if (elem.getName().equals(name))
                return elem;
        }
        return null;
    }

    /**
     * find an element by name.
     * 
     * @param diagram
     *            container.
     * @param name
     *            name to look for.
     * @return the element or null.
     */
    protected DDiagramElement findElementNamed(final DDiagram diagram, final String name) {
        DDiagramElement found = null;
        final Iterator<DDiagramElement> it = diagram.getOwnedDiagramElements().iterator();
        while (it.hasNext() && found == null) {
            final DDiagramElement elem = it.next();
            if (elem.getName().equals(name))
                found = elem;
            if (elem instanceof DNodeContainer) {
                final DDiagramElement result = findElementNamed((DNodeContainer) elem, name);
                if (result != null)
                    found = result;
            } else if (elem instanceof DNodeList) {
                final DDiagramElement result = findElementNamed((DNodeList) elem, name);
                if (result != null)
                    found = result;
            }
        }
        return found;
    }
}
