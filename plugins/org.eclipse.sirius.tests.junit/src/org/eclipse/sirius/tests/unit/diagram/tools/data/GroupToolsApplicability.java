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
package org.eclipse.sirius.tests.unit.diagram.tools.data;

//Start of user code imports

import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

//End of user code

public class GroupToolsApplicability {

    private Group val;

    public GroupToolsApplicability(Group val) {
        this.val = val;
    }

    public SiriusDesign design() {
        return new SiriusDesign((Viewpoint) val.getOwnedViewpoints().get(0));
    }

    public Group object() {
        return this.val;
    }
}
