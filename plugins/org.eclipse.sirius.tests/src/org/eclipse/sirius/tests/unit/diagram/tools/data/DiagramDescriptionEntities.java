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
package org.eclipse.sirius.tests.unit.diagram.tools.data;

import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;

public class DiagramDescriptionEntities {

    private DiagramDescription val;

    public DiagramDescriptionEntities(DiagramDescription val) {
        this.val = val;
    }

    public LayerDefaultLayer defaultlayer() {
        return new LayerDefaultLayer((Layer) val.getDefaultLayer());
    }

    public AdditionalLayerDocumentation documentation() {
        return new AdditionalLayerDocumentation((AdditionalLayer) val.getAdditionalLayers().get(0));
    }

    public DiagramDescription object() {
        return this.val;
    }
}
