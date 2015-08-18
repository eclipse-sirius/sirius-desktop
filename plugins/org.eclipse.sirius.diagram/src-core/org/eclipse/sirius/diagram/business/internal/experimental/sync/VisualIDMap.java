/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.experimental.sync;

import java.util.HashMap;

/**
 * This map knows of the correspondence between the visual ids of containers and
 * their elements needed when a container switches to/from the "list" mode. The
 * visual IDs are referenced by hard coded strings because we do not have access
 * to the diagram plug-in from here where the edit parts are defined.
 * 
 * @author Mariot Chauvin
 */
public class VisualIDMap extends HashMap<String, String> {
    private static final long serialVersionUID = 748079557726425012L;

    /**
     * Construct a new instance.
     */
    public VisualIDMap() {
        // DNodeContainerEditPart <-> DNodeListEditPart
        addEntry("2002", "2003"); //$NON-NLS-1$ //$NON-NLS-2$
        // DNodeContainerNameEditPart <-> DNodeListNameEditPart
        addEntry("5006", "5007"); //$NON-NLS-1$ //$NON-NLS-2$
        // DNodeContainerViewNodeContainerCompartmentEditPart <->
        // DNodeListViewNodeContainerCompartment2EditPart
        addEntry("7001", "7004"); //$NON-NLS-1$ //$NON-NLS-2$
        // DNodeContainer2EditPart <-> DNodeList2EditPart
        addEntry("3008", "3009"); //$NON-NLS-1$ //$NON-NLS-2$
        // DNodeContainer2NameEditPart <-> DNodeListName2EditPart
        addEntry("5005", "5004"); //$NON-NLS-1$ //$NON-NLS-2$
        // DNodeContainerViewNodeListCompartment2EditPart <->
        // DNodeListViewNodeListCompartmentEditPart
        addEntry("7002", "7003"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void addEntry(String s1, String s2) {
        put(s1, s2);
        put(s2, s1);
    }
}
