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

import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;

@SuppressWarnings("hiding")
public class LayerDefaultLayer {

    private Layer val;

    public LayerDefaultLayer(Layer val) {
        this.val = val;
    }

    public NodeMappingClassNode classnode() {
        return new NodeMappingClassNode((NodeMapping) val.getNodeMappings().get(0));
    }

    public NodeMappingOtherNode othernode() {
        return new NodeMappingOtherNode((NodeMapping) val.getNodeMappings().get(1));
    }

    public NodeMappingExtraNodeMapping extranodemapping() {
        return new NodeMappingExtraNodeMapping((NodeMapping) val.getNodeMappings().get(2));
    }

    public EdgeMappingClass2Other class2other() {
        return new EdgeMappingClass2Other((EdgeMapping) val.getEdgeMappings().get(0));
    }

    public ContainerMappingClassContainer classcontainer() {
        return new ContainerMappingClassContainer((ContainerMapping) val.getContainerMappings().get(0));
    }

    public ContainerMappingExtraContainerMapping extracontainermapping() {
        return new ContainerMappingExtraContainerMapping((ContainerMapping) val.getContainerMappings().get(1));
    }

    public ToolSectionTools tools() {
        return new ToolSectionTools((ToolSection) val.getToolSections().get(0));
    }

    public class NodeMappingClassNode {

        private NodeMapping val;

        public NodeMappingClassNode(NodeMapping val) {
            this.val = val;
        }

        public NodeMapping object() {
            return this.val;
        }
    }

    public class NodeMappingOtherNode {

        private NodeMapping val;

        public NodeMappingOtherNode(NodeMapping val) {
            this.val = val;
        }

        public class NodeMappingBorderOtherNode {

            private NodeMapping val;

            public NodeMappingBorderOtherNode(NodeMapping val) {
                this.val = val;
            }

            public NodeMapping object() {
                return this.val;
            }
        }

        public NodeMapping object() {
            return this.val;
        }
    }

    public class NodeMappingExtraNodeMapping {

        private NodeMapping val;

        public NodeMappingExtraNodeMapping(NodeMapping val) {
            this.val = val;
        }

        public NodeMapping object() {
            return this.val;
        }
    }

    public class EdgeMappingClass2Other {

        private EdgeMapping val;

        public EdgeMappingClass2Other(EdgeMapping val) {
            this.val = val;
        }

        public EdgeMapping object() {
            return this.val;
        }
    }

    public class ContainerMappingClassContainer {

        private ContainerMapping val;

        public ContainerMappingClassContainer(ContainerMapping val) {
            this.val = val;
        }

        public NodeMappingOtherNode othernode() {
            return new NodeMappingOtherNode((NodeMapping) val.getSubNodeMappings().get(0));
        }

        public class NodeMappingOtherNode {

            private NodeMapping val;

            public NodeMappingOtherNode(NodeMapping val) {
                this.val = val;
            }

            public NodeMapping object() {
                return this.val;
            }
        }

        public ContainerMapping object() {
            return this.val;
        }
    }

    public class ContainerMappingExtraContainerMapping {

        private ContainerMapping val;

        public ContainerMappingExtraContainerMapping(ContainerMapping val) {
            this.val = val;
        }

        public ContainerMapping object() {
            return this.val;
        }
    }

    public class ToolSectionTools {

        private ToolSection val;

        public ToolSectionTools(ToolSection val) {
            this.val = val;
        }

        public EdgeCreationDescriptionCreateClass2Other createclass2other() {
            return new EdgeCreationDescriptionCreateClass2Other((EdgeCreationDescription) val.getOwnedTools().get(0));
        }

        public EdgeCreationDescriptionCreateClass2OtherExtraSrcOnContainer createclass2otherextrasrconcontainer() {
            return new EdgeCreationDescriptionCreateClass2OtherExtraSrcOnContainer((EdgeCreationDescription) val.getOwnedTools().get(1));
        }

        public EdgeCreationDescriptionCreateClass2OtherExtraTgtOnContainer createclass2otherextratgtoncontainer() {
            return new EdgeCreationDescriptionCreateClass2OtherExtraTgtOnContainer((EdgeCreationDescription) val.getOwnedTools().get(2));
        }

        public NodeCreationDescriptionCreateClassNode createclassnode() {
            return new NodeCreationDescriptionCreateClassNode((NodeCreationDescription) val.getOwnedTools().get(3));
        }

        public NodeCreationDescriptionCreateClassContainerOtherNode createclasscontainerothernode() {
            return new NodeCreationDescriptionCreateClassContainerOtherNode((NodeCreationDescription) val.getOwnedTools().get(4));
        }

        public NodeCreationDescriptionCreateBorderNode createbordernode() {
            return new NodeCreationDescriptionCreateBorderNode((NodeCreationDescription) val.getOwnedTools().get(5));
        }

        public ContainerCreationDescriptionCreateClassContainer createclasscontainer() {
            return new ContainerCreationDescriptionCreateClassContainer((ContainerCreationDescription) val.getOwnedTools().get(6));
        }

        public class EdgeCreationDescriptionCreateClass2Other {

            private EdgeCreationDescription val;

            public EdgeCreationDescriptionCreateClass2Other(EdgeCreationDescription val) {
                this.val = val;
            }

            public class SourceEdgeCreationVariableSource {

                private SourceEdgeCreationVariable val;

                public SourceEdgeCreationVariableSource(SourceEdgeCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeCreationVariableTarget {

                private TargetEdgeCreationVariable val;

                public TargetEdgeCreationVariableTarget(TargetEdgeCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class SourceEdgeViewCreationVariableSourceView {

                private SourceEdgeViewCreationVariable val;

                public SourceEdgeViewCreationVariableSourceView(SourceEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeViewCreationVariableTargetView {

                private TargetEdgeViewCreationVariable val;

                public TargetEdgeViewCreationVariableTargetView(TargetEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public EdgeCreationDescription object() {
                return this.val;
            }
        }

        public class EdgeCreationDescriptionCreateClass2OtherExtraSrcOnContainer {

            private EdgeCreationDescription val;

            public EdgeCreationDescriptionCreateClass2OtherExtraSrcOnContainer(EdgeCreationDescription val) {
                this.val = val;
            }

            public class SourceEdgeCreationVariableSource {

                private SourceEdgeCreationVariable val;

                public SourceEdgeCreationVariableSource(SourceEdgeCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeCreationVariableTarget {

                private TargetEdgeCreationVariable val;

                public TargetEdgeCreationVariableTarget(TargetEdgeCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class SourceEdgeViewCreationVariableSourceView {

                private SourceEdgeViewCreationVariable val;

                public SourceEdgeViewCreationVariableSourceView(SourceEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeViewCreationVariableTargetView {

                private TargetEdgeViewCreationVariable val;

                public TargetEdgeViewCreationVariableTargetView(TargetEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public EdgeCreationDescription object() {
                return this.val;
            }
        }

        public class EdgeCreationDescriptionCreateClass2OtherExtraTgtOnContainer {

            private EdgeCreationDescription val;

            public EdgeCreationDescriptionCreateClass2OtherExtraTgtOnContainer(EdgeCreationDescription val) {
                this.val = val;
            }

            public class SourceEdgeCreationVariableSource {

                private SourceEdgeCreationVariable val;

                public SourceEdgeCreationVariableSource(SourceEdgeCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeCreationVariableTarget {

                private TargetEdgeCreationVariable val;

                public TargetEdgeCreationVariableTarget(TargetEdgeCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeCreationVariable object() {
                    return this.val;
                }
            }

            public class SourceEdgeViewCreationVariableSourceView {

                private SourceEdgeViewCreationVariable val;

                public SourceEdgeViewCreationVariableSourceView(SourceEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public SourceEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public class TargetEdgeViewCreationVariableTargetView {

                private TargetEdgeViewCreationVariable val;

                public TargetEdgeViewCreationVariableTargetView(TargetEdgeViewCreationVariable val) {
                    this.val = val;
                }

                public TargetEdgeViewCreationVariable object() {
                    return this.val;
                }
            }

            public EdgeCreationDescription object() {
                return this.val;
            }
        }

        public class NodeCreationDescriptionCreateClassNode {

            private NodeCreationDescription val;

            public NodeCreationDescriptionCreateClassNode(NodeCreationDescription val) {
                this.val = val;
            }

            public class NodeCreationVariableContainer {

                private NodeCreationVariable val;

                public NodeCreationVariableContainer(NodeCreationVariable val) {
                    this.val = val;
                }

                public NodeCreationVariable object() {
                    return this.val;
                }
            }

            public class ContainerViewVariableContainerView {

                private ContainerViewVariable val;

                public ContainerViewVariableContainerView(ContainerViewVariable val) {
                    this.val = val;
                }

                public ContainerViewVariable object() {
                    return this.val;
                }
            }

            public NodeCreationDescription object() {
                return this.val;
            }
        }

        public class NodeCreationDescriptionCreateClassContainerOtherNode {

            private NodeCreationDescription val;

            public NodeCreationDescriptionCreateClassContainerOtherNode(NodeCreationDescription val) {
                this.val = val;
            }

            public class NodeCreationVariableContainer {

                private NodeCreationVariable val;

                public NodeCreationVariableContainer(NodeCreationVariable val) {
                    this.val = val;
                }

                public NodeCreationVariable object() {
                    return this.val;
                }
            }

            public class ContainerViewVariableContainerView {

                private ContainerViewVariable val;

                public ContainerViewVariableContainerView(ContainerViewVariable val) {
                    this.val = val;
                }

                public ContainerViewVariable object() {
                    return this.val;
                }
            }

            public NodeCreationDescription object() {
                return this.val;
            }
        }

        public class NodeCreationDescriptionCreateBorderNode {

            private NodeCreationDescription val;

            public NodeCreationDescriptionCreateBorderNode(NodeCreationDescription val) {
                this.val = val;
            }

            public class NodeCreationVariableContainer {

                private NodeCreationVariable val;

                public NodeCreationVariableContainer(NodeCreationVariable val) {
                    this.val = val;
                }

                public NodeCreationVariable object() {
                    return this.val;
                }
            }

            public class ContainerViewVariableContainerView {

                private ContainerViewVariable val;

                public ContainerViewVariableContainerView(ContainerViewVariable val) {
                    this.val = val;
                }

                public ContainerViewVariable object() {
                    return this.val;
                }
            }

            public NodeCreationDescription object() {
                return this.val;
            }
        }

        public class ContainerCreationDescriptionCreateClassContainer {

            private ContainerCreationDescription val;

            public ContainerCreationDescriptionCreateClassContainer(ContainerCreationDescription val) {
                this.val = val;
            }

            public class NodeCreationVariableContainer {

                private NodeCreationVariable val;

                public NodeCreationVariableContainer(NodeCreationVariable val) {
                    this.val = val;
                }

                public NodeCreationVariable object() {
                    return this.val;
                }
            }

            public class ContainerViewVariableContainerView {

                private ContainerViewVariable val;

                public ContainerViewVariableContainerView(ContainerViewVariable val) {
                    this.val = val;
                }

                public ContainerViewVariable object() {
                    return this.val;
                }
            }

            public ContainerCreationDescription object() {
                return this.val;
            }
        }

        public ToolSection object() {
            return this.val;
        }
    }

    public Layer object() {
        return this.val;
    }
}
