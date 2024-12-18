/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;

/**
 * Test class for creation message management
 * 
 * @author smonnier
 */
public class SequenceExecutionBasicAndReturnMessageObervationPointDisplayedTest extends SequenceExecutionBasicAndReturnMessageTest {

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        DDiagram diagram = (DDiagram) ((DialectEditor) editor.getReference().getEditor(false)).getRepresentation();
        int prevActivatedLayers = diagram.getActivatedLayers().size();

        changeDurationLayerActivation();

        // Check activation/deactivation
        int postActivatedLayers = diagram.getActivatedLayers().size();
        int delta = postActivatedLayers - prevActivatedLayers;
        assertTrue("Observation layer was not activated.", delta == 1);
    }
}
