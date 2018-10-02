/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
