/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.compartment;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Interface to regroup common constant for tests on compartments.
 * 
 * @author mporhel
 *
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

    public static final String HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME = "HStackDiag";

    public static final String VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME = "VStackDiag";

    public static final String PACKAGE_CREATION_TOOL_NAME = "EPackage";

    public static final String CLASS_NODE_CREATION_TOOL_NAME = "EClassNode";

    public static final String CLASS_LIST_CREATION_TOOL_NAME = "EClassList";

    public static final String ATTRIBUTE_CREATION_TOOL_NAME = "EAttribute";

    public static final String REGION_CONTAINER_NAME = "P1";

    public static final String NEW_REGION_CONTAINER_NAME = "p3";

    public static final String LEFT_CLASS_NAME = "Left_class1";

    public static final String CENTER_CLASS_NAME = "Center_class2";

    public static final String RIGHT_CLASS_NAME = "Right_class3";

    public static final String LEFT_PKG_NAME = "Left_p3";

    public static final String CENTER_PKG_NAME = "Center_p4";

    public static final String RIGHT_PKG_NAME = "Right_p5";

    public static final String NEW_CLASS_LIST_4_NAME = "class4";

    public static final String NEW_CLASS_1_NAME = "class1";

    public static final String NEW_PACKAGE_1_NAME = "p1";

    public static final String NEW_ATTRIBUTE_NAME = "attr2";
}
