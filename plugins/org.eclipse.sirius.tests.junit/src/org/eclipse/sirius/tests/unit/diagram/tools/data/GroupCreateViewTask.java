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

//Start of user code imports

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

//End of user code

@SuppressWarnings("hiding")
public class GroupCreateViewTask {

    private Group val;

    public GroupCreateViewTask(Group val) {
        this.val = val;
    }

    public SiriusSirius1 viewpoint1() {
        return new SiriusSirius1((Viewpoint) val.getOwnedViewpoints().get(0));
    }

    public class SiriusSirius1 {

        private Viewpoint val;

        public SiriusSirius1(Viewpoint val) {
            this.val = val;
        }

        public DiagramDescriptionDiagram1 diagram1() {
            return new DiagramDescriptionDiagram1((DiagramDescription) val.getOwnedRepresentations().get(0));
        }

        public class DiagramDescriptionDiagram1 {

            private DiagramDescription val;

            public DiagramDescriptionDiagram1(DiagramDescription val) {
                this.val = val;
            }

            public LayerMyDefaultLayer mydefaultlayer() {
                return new LayerMyDefaultLayer((Layer) val.getDefaultLayer());
            }

            public class LayerMyDefaultLayer {

                private Layer val;

                public LayerMyDefaultLayer(Layer val) {
                    this.val = val;
                }

                public NodeMappingNodeMapping1 nodemapping1() {
                    return new NodeMappingNodeMapping1((NodeMapping) val.getNodeMappings().get(0));
                }

                public class NodeMappingNodeMapping1 {

                    private NodeMapping val;

                    public NodeMappingNodeMapping1(NodeMapping val) {
                        this.val = val;
                    }

                    public NodeMapping object() {
                        return this.val;
                    }
                }

                public Layer object() {
                    return this.val;
                }
            }

            public DiagramDescription object() {
                return this.val;
            }
        }

        public Viewpoint object() {
            return this.val;
        }
    }

    public Group object() {
        return this.val;
    }
}
