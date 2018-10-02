/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.Assert;

/**
 * A condition on tools availability on the palette.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class PaletteToolsAvailabilityCondition extends DefaultCondition {

    /**
     * The current {@link UIDiagramRepresentation}.
     */
    private final UIDiagramRepresentation diagram;

    /**
     * Tool availability on the palette expectation
     */
    private final boolean shouldHaveDisplayedTools;

    /**
     * Default constructor.
     * 
     * @param diagram
     *            current {@link UIDiagramRepresentation}
     * @param shouldHaveDisplayedTools
     *            Tool availability on the palette expectation
     */
    public PaletteToolsAvailabilityCondition(UIDiagramRepresentation diagram, boolean shouldHaveDisplayedTools) {
        super();
        this.diagram = diagram;
        this.shouldHaveDisplayedTools = shouldHaveDisplayedTools;
    }

    @Override
    public boolean test() throws Exception {
        PaletteViewer paletteViewer = diagram.getEditor().rootEditPart().part().getViewer().getEditDomain().getPaletteViewer();
        PaletteContainer viewpointPaletteContainer = (PaletteContainer) paletteViewer.getPaletteRoot().getChildren().get(1);
        int nbToolInThePalette = viewpointPaletteContainer.getChildren().size();
        Assert.assertEquals("The palette was expected to be empty", 0, nbToolInThePalette);
        if (shouldHaveDisplayedTools) {
            return nbToolInThePalette > 0;
        } else {
            return nbToolInThePalette == 0;
        }
    }

    @Override
    public String getFailureMessage() {
        if (shouldHaveDisplayedTools) {
            return "It was expected to have tools in the palette";
        } else {
            return "It was expected to have no tool in the palette";
        }
    }

}
