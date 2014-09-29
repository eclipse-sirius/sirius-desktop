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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * A Stub delete java action to simulate external deletion or removal.
 * 
 * @author mchauvin
 */
public class StubDeleteJavaAction implements IExternalJavaAction {

    private static boolean hasBeenCalled;

    private static boolean remove;

    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EObject semanticObject = (EObject) selections.iterator().next();
        if (remove)
            EcoreUtil.remove(semanticObject);
        else
            EcoreUtil.delete(semanticObject);
        hasBeenCalled = true;
    }

    public static void doASemanticRemove() {
        remove = true;
    }

    public static void doASemanticDelete() {
        remove = false;
    }

    public static boolean hasBeenCalled() {
        return hasBeenCalled;
    }

    public static void resetCalled() {
        hasBeenCalled = false;
    }

}
