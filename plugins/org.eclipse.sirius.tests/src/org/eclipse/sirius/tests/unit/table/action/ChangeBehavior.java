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
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;

/**
 * Search the opaque behavior corresponding to the parameters
 * "classifierBehaviorName" in the parameters "currentClass" or in its parent,
 * and applied it to the parameters "currentClass".<BR>
 * If any opaque behavior if found, the behavior of the class is unset.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ChangeBehavior implements IExternalJavaAction {

    public ChangeBehavior() {
    }

    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    public void execute(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        Class currentClass = (Class) parameters.get("currentClass");
        String classifierBehaviorName = (String) parameters.get("classifierBehaviorName");
        /* Search the new behavior */
        OpaqueBehavior newOpaqueBehavior = null;
        Type type = currentClass.getOwnedBehavior(classifierBehaviorName);
        if (type == null) {
            type = ((Package) currentClass.eContainer()).getOwnedType(classifierBehaviorName);
        }
        if (type instanceof OpaqueBehavior) {
            newOpaqueBehavior = (OpaqueBehavior) type;
        }
        /* Set the new behavior */
        currentClass.setClassifierBehavior(newOpaqueBehavior);
    }

}
