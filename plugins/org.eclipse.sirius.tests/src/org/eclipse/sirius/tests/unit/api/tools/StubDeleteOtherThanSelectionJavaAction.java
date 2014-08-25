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
public class StubDeleteOtherThanSelectionJavaAction implements IExternalJavaAction {

    private static boolean hasBeenCalled;

    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        EObject selection = selections.iterator().next();
        try {
            EObject toDelete = getOtherSemanticElementThanSelection(selection.eContainer(), selection);
            EcoreUtil.delete(toDelete);
            hasBeenCalled = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean hasBeenCalled() {
        return hasBeenCalled;
    }

    private EObject getOtherSemanticElementThanSelection(EObject container, EObject selection) throws Exception {
        for (final EObject child : container.eContents()) {
            if (child != selection)
                return child;
        }
        throw new IllegalArgumentException();
    }

}
