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
package org.eclipse.sirius.tests.unit.api.editors.traceability;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Interface used by GoToMarkerTests.
 * 
 * @author alagarde
 */
public interface TraceabilityTestsModeler {

    String TEST_DIR = "/data/unit/editors/traceability/vp1038/";

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_DIR + "vp1038.ecore";

    String SEMANTIC_MODEL_PATH_2 = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_DIR + "vp1038-2.ecore";

    String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_DIR + "vp1038.aird";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_DIR + "vp1038.odesign";

    String REPRESENTATION_DIAGRAM = "Trace Diagram";

    String REPRESENTATION_TABLE = "Trace Table";

    String REPRESENTATION_CROSSTABLE = "Trace CrossTable";

    String REPRESENTATION_TREE = "Trace Tree";

    String SEMANTIC_ELEMENT_A = "A";

    String SEMANTIC_ELEMENT_MYECLASS = "myEClass";

    String REPRESENTATION_INSTANCE_TABLE1 = "table";

    String REPRESENTATION_INSTANCE_CROSSTABLE1 = "crossTable";

    String REPRESENTATION_INSTANCE_DIAGRAM1 = "diagram";

    String REPRESENTATION_INSTANCE_DIAGRAM2 = "diagram2";

    String REPRESENTATION_INSTANCE_TABLE2 = "table2";

    String REPRESENTATION_INSTANCE_TREE = "tree";
}
