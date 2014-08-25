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
package org.eclipse.sirius.tests.unit.table.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

public class ChangeUseCases implements IExternalJavaAction {
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }
}
