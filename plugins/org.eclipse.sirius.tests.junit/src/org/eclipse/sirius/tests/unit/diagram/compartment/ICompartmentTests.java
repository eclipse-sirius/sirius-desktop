/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.compartment;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Interface to regroup common constant for tests on compartments.
 * 
 * @author mporhel
 */
public interface ICompartmentTests {

    public static final String DATA_UNIT_DIR = "/data/unit/compartments/";

    public static final String MODEL = "My.ecore";

    public static final String SESSION_FILE = "My.aird";

    public static final String VSM_FILE = "compartments.odesign";

    public static final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + MODEL;

    public static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + SESSION_FILE;

    public static final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + VSM_FILE;

    public static final String HORIZONTAL_STACK_REPRESENTATION_NAME = "Diag with HStack";

    public static final String VERTICAL_STACK_REPRESENTATION_NAME = "Diag with VStack";

    public static final String VERTICAL_STACK_DND_REPRESENTATION_NAME = "Diag with VStack and DnD";

    public static final String HORIZONTAL_STACK_DND_REPRESENTATION_NAME = "Diag with HStack and DnD";

    public static final String REGION_WITH_EDGES_REPRESENTATION_NAME = "RegionWithEdges";

    public static final String REGION_WITH_EDGE2EDGE_REPRESENTATION_NAME = "RegionWithEdgeToEdge";

    public static final String REGION_WITH_EDGES_AND_NODES_REPRESENTATION_NAME = "RegionWithEdgesAndNodes";

    public static final String HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME = "HStackDiag";

    public static final String VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME = "VStackDiag";

    public static final String VERTICAL_STACK_DND_REPRESENTATION_INSTANCE_NAME = "VStackDiag with DnD";

    public static final String HORIZONTAL_STACK_DND_REPRESENTATION_INSTANCE_NAME = "HStackDiag with DnD";

    public static final String REGION_WITH_EDGES_REPRESENTATION_INSTANCE_NAME = "regionWithEdges";

    public static final String REGION_WITH_EDGE2EDGE_REPRESENTATION_INSTANCE_NAME = "regionWithEdgeToEdge";

    public static final String REGION_WITH_EDGES_AND_NODES_REPRESENTATION_INSTANCE_NAME = "regionWithEdgesAndNodes";

    public static final String PACKAGE_CREATION_TOOL_NAME = "EPackage";

    public static final String CLASS_NODE_CREATION_TOOL_NAME = "EClassNode";

    public static final String CLASS_LIST_CREATION_TOOL_NAME = "EClassList";

    public static final String ATTRIBUTE_CREATION_TOOL_NAME = "EAttribute";

    public static final String FIRST_REGION_CONTAINER_NAME = "P1";

    public static final String SECOND_REGION_CONTAINER_NAME = "az";

    public static final String FIRST_REGION_NAME = "cccc";

    public static final String NEW_REGION_CONTAINER_NAME = "p3";

    public static final String NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME = "Init_p3";

    public static final String PACKAGE_CREATION_DEFINED_SIZE_TOOL_NAME = "EPackageWithDefinedSize";

    public static final String PACKAGE_CREATION_HIDE_LABEL_TOOL_NAME = "EPackageWithHideLabel";

    public static final String PACKAGE_ONE_CLASS_CREATION_TOOL_NAME = "OneClassInEPackage";

    public static final String PACKAGE_TWO_CLASS_CREATION_TOOL_NAME = "TwoClassesInEPackage";

    public static final String PACKAGE_ONE_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME = "OneClassInEPackageWithDefinedSize";

    public static final String PACKAGE_TWO_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME = "TwoClassesInEPackageWithDefinedSize";

    public static final String LEFT_CLASS_NAME = "Left_class1";

    public static final String LEFT_CLASS2_NAME = "Left_class2";

    public static final String LEFT_CLASS_C0_NAME = "Left_C0";

    public static final String LEFT_CLASS_C1_NAME = "Left_C1";

    public static final String CENTER_CLASS_NAME = "Center_class2";

    public static final String RIGHT_CLASS_NAME = "Right_class3";

    public static final String LEFT_PKG_NAME = "Left_p3";

    public static final String CENTER_PKG_NAME = "Center_p4";

    public static final String RIGHT_PKG_NAME = "Right_p5";

    public static final String NEW_CLASS_LIST_4_NAME = "class4";

    public static final String NEW_CLASS_1_NAME = "class1";

    public static final String NEW_PACKAGE_1_NAME = "p1";

    public static final String NEW_ATTRIBUTE_NAME = "attr2";

    // The expected bounds can be slightly different according to the size of
    // Font, so the tests must consider this and not use these bounds as
    // strictly expected.
    public static final Rectangle INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS = new Rectangle(64, 16, 141, 414);

    public static final Rectangle INITIAL_VERTICAL_LEFT_CLASS_BOUNDS = new Rectangle(0, 0, 129, 91);

    public static final Rectangle INITIAL_VERTICAL_LEFT_CLASS2_BOUNDS = new Rectangle(0, 91, 129, 40);

    public static final Rectangle INITIAL_VERTICAL_CENTER_CLASS_BOUNDS = new Rectangle(0, 91, 129, 92);

    public static final Rectangle INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS = new Rectangle(0, 183, 129, 44);

    public static final Rectangle INITIAL_VERTICAL_LEFT_PKG_BOUNDS = new Rectangle(0, 227, 129, 41);

    public static final Rectangle INITIAL_VERTICAL_CENTER_PKG_BOUNDS = new Rectangle(0, 268, 129, 67);

    public static final Rectangle INITIAL_VERTICAL_RIGHT_PKG_BOUNDS = new Rectangle(0, 335, 129, 41);

    public static final Rectangle INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS = new Rectangle(0, 0, 831, 247);

    public static final Rectangle INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS = new Rectangle(0, 0, 165, 211);

    public static final Rectangle INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS = new Rectangle(165, 0, 136, 211);

    public static final Rectangle INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS = new Rectangle(301, 0, 130, 211);

    public static final Rectangle INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS = new Rectangle(431, 0, 122, 211);

    public static final Rectangle INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS = new Rectangle(553, 0, 156, 211);

    public static final Rectangle INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS = new Rectangle(709, 0, 112, 211);
}
