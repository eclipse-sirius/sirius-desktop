/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * A stub action which simply stores its parameters each time it is called, so
 * that tests can sense these parameters afterward.
 * 
 * @author pcdavid
 */
public class StubJavaAction implements IExternalJavaAction {
    public final static List<Object> CALL_ARGUMENTS = new ArrayList<Object>();

    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        CALL_ARGUMENTS.add(Arrays.asList(selections, parameters));
    }
}
