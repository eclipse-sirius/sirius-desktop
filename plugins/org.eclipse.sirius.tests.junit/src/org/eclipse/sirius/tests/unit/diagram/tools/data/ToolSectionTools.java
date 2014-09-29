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
public class ToolSectionTools {

    private ToolSection val;

    public ToolSectionTools(ToolSection val) {
        this.val = val;
    }

    public EdgeCreationDescriptionCreateEdgeFromSpecializedNodes createedgefromspecializednodes() {
        return new EdgeCreationDescriptionCreateEdgeFromSpecializedNodes((EdgeCreationDescription) val.getOwnedTools().get(0));
    }

    public EdgeCreationDescriptionCreateEdgeFromNonSpecializedNodes createedgefromnonspecializednodes() {
        return new EdgeCreationDescriptionCreateEdgeFromNonSpecializedNodes((EdgeCreationDescription) val.getOwnedTools().get(1));
    }

    public NodeCreationDescriptionCreateOtherRootSpecialization createotherrootspecialization() {
        return new NodeCreationDescriptionCreateOtherRootSpecialization((NodeCreationDescription) val.getOwnedTools().get(2));
    }

    public class EdgeCreationDescriptionCreateEdgeFromSpecializedNodes {

        private EdgeCreationDescription val;

        public EdgeCreationDescriptionCreateEdgeFromSpecializedNodes(EdgeCreationDescription val) {
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

    public class EdgeCreationDescriptionCreateEdgeFromNonSpecializedNodes {

        private EdgeCreationDescription val;

        public EdgeCreationDescriptionCreateEdgeFromNonSpecializedNodes(EdgeCreationDescription val) {
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

    public class NodeCreationDescriptionCreateOtherRootSpecialization {

        private NodeCreationDescription val;

        public NodeCreationDescriptionCreateOtherRootSpecialization(NodeCreationDescription val) {
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

    public ToolSection object() {
        return this.val;
    }
}
