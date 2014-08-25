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
package org.eclipse.sirius.tests.data;

import org.eclipse.sirius.viewpoint.description.Group;

public class GroupRefreshTreeOdesign {

    private Group val;

    public GroupRefreshTreeOdesign(Group val) {
        this.val = val;
    }

    public SiriusDesign design() {
        return new SiriusDesign(val.getOwnedViewpoints().get(0));
    }

    public Group object() {
        return this.val;
    }
}
