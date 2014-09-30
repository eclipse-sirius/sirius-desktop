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
package org.eclipse.sirius.tests.unit.diagram.layers;

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;

public abstract class AbtsractLayerTests extends GenericTestCase  {

    protected static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID;
    
    protected DDiagram diagram;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        init();
    }
    
    protected abstract void init() throws Exception;
    
    protected void refreshDiagram() {
        diagram = getRefreshedDiagram();
    }

    protected int getNumberOfElementsInDiagram() {
        return diagram.getOwnedDiagramElements().size();
    }

    protected DDiagramElement getFirstElement() {
        return diagram.getOwnedDiagramElements().get(0);
    }
    
    protected void setLayerVisibility(final DDiagram diagram, final Layer layer, final boolean visible) {

        final List<Layer> activatedLayers = diagram.getActivatedLayers();

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                if (visible)
                    activatedLayers.add(layer);
                else
                    activatedLayers.remove(layer);
            }
        });
    }

    /**
     * Refresh visibility of all diagram elements in the current diagram.
     * 
     * @param diagram
     *            the diagram
     */
    protected void refreshVisibility(final DDiagram diagram) {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(diagram);
            }
        });
    }
    
    protected static void compareElements(final List<? extends DDiagramElement> elements1, final List<? extends DDiagramElement> elements2) {
        assertTrue("the two lists should have the same size", elements1.size() == elements2.size());
        for (int i = 0; i < elements1.size(); i++) {
            assertTrue("the elements should be the same", elements2.contains(elements1.get(i)));
        }
    }
}


