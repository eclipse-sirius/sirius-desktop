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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Layers tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramCustomizationsTests extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    public void testBackgroundInterpolatedColor() {

        final EPackage root = (EPackage) semanticModel;

        final DDiagram rootdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, root).iterator().next();

        deactivateLayer(rootdiagram, "Package");

        refresh(rootdiagram);

        assertEquals("Can't find the root EClass nodelist", 1, getDiagramElementsFromLabel(rootdiagram, "Root").size());

        final RGBValues beforeVpActivation = ((FlatContainerStyle) ((DNodeList) getDiagramElementsFromLabel(rootdiagram, "Root").get(0)).getStyle()).getBackgroundColor();

        assertEquals("We should have a black default color", 255, beforeVpActivation.getRed());
        assertEquals("We should have a black default color", 255, beforeVpActivation.getGreen());
        assertEquals("We should have a black default color", 255, beforeVpActivation.getBlue());

        initViewpoint("Quality");

        // Special case, transaction is not needed because 'Quality' Sirius
        // is already contained in current DAnalysisSession
        // previous initSirius method has added this Sirius
        // DAnalysisSession will be unchanged, so transaction is not necessary
        final ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
        selection.selectViewpoint(getSiriusFromRegistry("Quality").iterator().next(), session, new NullProgressMonitor());
        activateLayer(rootdiagram, "Size");

        refresh(rootdiagram);
        refresh(rootdiagram);

        final RGBValues afterVpActivation = ((FlatContainerStyle) ((DNodeList) getDiagramElementsFromLabel(rootdiagram, "Root").get(0)).getStyle()).getBackgroundColor();

        assertEquals("We should have a kinda green color", 51, afterVpActivation.getRed());
        assertEquals("We should have a kinda green color", 204, afterVpActivation.getGreen());
        assertEquals("We should have a kinda green color", 0, afterVpActivation.getBlue());
    }

    private Iterable<Viewpoint> getSiriusFromRegistry(final String name) {
        return Iterables.filter(ViewpointRegistry.getInstance().getViewpoints(), new Predicate<Viewpoint>() {

            public boolean apply(final Viewpoint input) {
                return name.equals(input.getName()) && MODELER_PATH.equals(input.eResource().getURI().toPlatformString(false));
            }
        });
    }
}
