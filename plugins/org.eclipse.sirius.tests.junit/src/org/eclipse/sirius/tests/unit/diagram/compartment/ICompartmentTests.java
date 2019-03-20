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

    String DATA_UNIT_DIR = "/data/unit/compartments/";

    String MODEL = "My.ecore";

    String SESSION_FILE = "My.aird";

    String VSM_FILE = "compartments.odesign";

    String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + MODEL;

    String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + SESSION_FILE;

    String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + VSM_FILE;

    String HORIZONTAL_STACK_REPRESENTATION_NAME = "Diag with HStack";

    String VERTICAL_STACK_REPRESENTATION_NAME = "Diag with VStack";

    String VERTICAL_STACK_DND_REPRESENTATION_NAME = "Diag with VStack and DnD";

    String HORIZONTAL_STACK_DND_REPRESENTATION_NAME = "Diag with HStack and DnD";

    String REGION_WITH_EDGES_REPRESENTATION_NAME = "RegionWithEdges";

    String HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME = "HStackDiag";

    String VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME = "VStackDiag";

    String VERTICAL_STACK_DND_REPRESENTATION_INSTANCE_NAME = "VStackDiag with DnD";

    String HORIZONTAL_STACK_DND_REPRESENTATION_INSTANCE_NAME = "HStackDiag with DnD";

    String REGION_WITH_EDGES_REPRESENTATION_INSTANCE_NAME = "regionWithEdges";

    String PACKAGE_CREATION_TOOL_NAME = "EPackage";

    String CLASS_NODE_CREATION_TOOL_NAME = "EClassNode";

    String CLASS_LIST_CREATION_TOOL_NAME = "EClassList";

    String ATTRIBUTE_CREATION_TOOL_NAME = "EAttribute";

    String FIRST_REGION_CONTAINER_NAME = "P1";

    String SECOND_REGION_CONTAINER_NAME = "az";

    String SECOND_REGION_CONTAINER_FIRST_PKG_NAME = "aaa";

    String FIRST_REGION_NAME = "cccc";

    String NEW_REGION_CONTAINER_NAME = "p3";

    String NEW_REGION_CONTAINER_WITH_DEFINED_SIZE_NAME = "Init_p3";

    String PACKAGE_CREATION_DEFINED_SIZE_TOOL_NAME = "EPackageWithDefinedSize";

    String PACKAGE_CREATION_HIDE_LABEL_TOOL_NAME = "EPackageWithHideLabel";

    String PACKAGE_ONE_CLASS_CREATION_TOOL_NAME = "OneClassInEPackage";

    String PACKAGE_TWO_CLASS_CREATION_TOOL_NAME = "TwoClassesInEPackage";

    String PACKAGE_ONE_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME = "OneClassInEPackageWithDefinedSize";

    String PACKAGE_TWO_CLASS_CREATION_WITH_DEFINED_SIZE_TOOL_NAME = "TwoClassesInEPackageWithDefinedSize";

    String LEFT_CLASS_NAME = "Left_class1";

    String LEFT_CLASS2_NAME = "Left_class2";

    String LEFT_CLASS_C0_NAME = "Left_C0";

    String LEFT_CLASS_C1_NAME = "Left_C1";

    String CENTER_CLASS_NAME = "Center_class2";

    String RIGHT_CLASS_NAME = "Right_class3";

    String LEFT_PKG_NAME = "Left_p3";

    String CENTER_PKG_NAME = "Center_p4";

    String RIGHT_PKG_NAME = "Right_p5";

    String NEW_CLASS_LIST_4_NAME = "class4";

    String NEW_CLASS_1_NAME = "class1";

    String NEW_PACKAGE_1_NAME = "p1";

    String NEW_ATTRIBUTE_NAME = "attr2";

    // The expected bounds can be slightly different according to the size of
    // Font, so the tests must consider this and not use these bounds as
    // strictly expected.
    Rectangle INITIAL_VERTICAL_FIRST_REGION_CONTAINER_BOUNDS = new Rectangle(64, 16, 141, 414);

    Rectangle INITIAL_VERTICAL_LEFT_CLASS_BOUNDS = new Rectangle(0, 0, 129, 91);

    Rectangle INITIAL_VERTICAL_LEFT_CLASS2_BOUNDS = new Rectangle(0, 91, 129, 40);

    Rectangle INITIAL_VERTICAL_CENTER_CLASS_BOUNDS = new Rectangle(0, 91, 129, 92);

    Rectangle INITIAL_VERTICAL_RIGHT_CLASS_BOUNDS = new Rectangle(0, 183, 129, 44);

    Rectangle INITIAL_VERTICAL_LEFT_PKG_BOUNDS = new Rectangle(0, 227, 129, 41);

    Rectangle INITIAL_VERTICAL_CENTER_PKG_BOUNDS = new Rectangle(0, 268, 129, 67);

    Rectangle INITIAL_VERTICAL_RIGHT_PKG_BOUNDS = new Rectangle(0, 335, 129, 41);

    Rectangle INITIAL_HORIZONTAL_FIRST_REGION_CONTAINER_BOUNDS = new Rectangle(0, 0, 831, 247);

    Rectangle INITIAL_HORIZONTAL_LEFT_CLASS_BOUNDS = new Rectangle(0, 0, 165, 211);

    Rectangle INITIAL_HORIZONTAL_CENTER_CLASS_BOUNDS = new Rectangle(165, 0, 136, 211);

    Rectangle INITIAL_HORIZONTAL_RIGHT_CLASS_BOUNDS = new Rectangle(301, 0, 130, 211);

    Rectangle INITIAL_HORIZONTAL_LEFT_PKG_BOUNDS = new Rectangle(431, 0, 122, 211);

    Rectangle INITIAL_HORIZONTAL_CENTER_PKG_BOUNDS = new Rectangle(553, 0, 156, 211);

    Rectangle INITIAL_HORIZONTAL_RIGHT_PKG_BOUNDS = new Rectangle(709, 0, 112, 211);
}
