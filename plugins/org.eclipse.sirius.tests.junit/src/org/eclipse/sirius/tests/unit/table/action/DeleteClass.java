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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

public class DeleteClass implements IExternalJavaAction {
    public boolean canExecute(Collection<? extends EObject> selections) {
        boolean result = true;
        for (EObject selectedElement : selections) {
            if (!(selectedElement instanceof Class && selectedElement.eContainer() instanceof Package)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        for (EObject selectedElement : selections) {
            ((Package) selectedElement.eContainer()).getPackagedElements().remove(selectedElement);
        }
    }

}
