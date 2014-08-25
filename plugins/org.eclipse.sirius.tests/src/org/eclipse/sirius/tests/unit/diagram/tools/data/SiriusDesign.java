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

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

@SuppressWarnings("hiding")
public class SiriusDesign {

    private Viewpoint val;

    public SiriusDesign(Viewpoint val) {
        this.val = val;
    }

    public DiagramDescriptionEntities entities() {
        return new DiagramDescriptionEntities((DiagramDescription) val.getOwnedRepresentations().get(0));
    }

    public DiagramImportDescriptionDiagramSpecialization diagramspecialization() {
        return new DiagramImportDescriptionDiagramSpecialization((DiagramImportDescription) val.getOwnedRepresentations().get(1));
    }

    public class DiagramImportDescriptionDiagramSpecialization {

        private DiagramImportDescription val;

        public DiagramImportDescriptionDiagramSpecialization(DiagramImportDescription val) {
            this.val = val;
        }

        public DiagramImportDescription object() {
            return this.val;
        }
    }

    public Viewpoint object() {
        return this.val;
    }
}
