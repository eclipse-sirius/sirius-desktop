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

import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;

public class TreeDescriptionEPackageContent {

    private TreeDescription val;

    public TreeDescriptionEPackageContent(TreeDescription val) {
        this.val = val;
    }

    public class TreeItemMappingEClasses {

        private TreeItemMapping val;

        public TreeItemMappingEClasses(TreeItemMapping val) {
            this.val = val;
        }

        public TreeItemMapping object() {
            return this.val;
        }
    }

    public class TreeItemMappingAbstractClasses {

        private TreeItemMapping val;

        public TreeItemMappingAbstractClasses(TreeItemMapping val) {
            this.val = val;
        }

        public TreeItemMapping object() {
            return this.val;
        }
    }

    public TreeDescription object() {
        return this.val;
    }
}
