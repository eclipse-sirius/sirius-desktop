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
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface SessionInterpreterBug1411Modeler {

    String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/session/interpreter/1411/My.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/session/interpreter/1411/My.odesign";

    String TEST_VIEWPOINT_NAME = "a";

    String TEST_REPRESENTATION_NAME = "a";
}
