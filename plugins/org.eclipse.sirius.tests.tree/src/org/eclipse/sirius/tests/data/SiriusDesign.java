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

import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class SiriusDesign {

    private Viewpoint val;

    public SiriusDesign(Viewpoint val) {
        this.val = val;
    }

    public TreeDescriptionEPackageContent epackagecontent() {
        return new TreeDescriptionEPackageContent((TreeDescription) val.getOwnedRepresentations().get(0));
    }

    public TreeDescriptionGenericEMFTree genericemftree() {
        return new TreeDescriptionGenericEMFTree((TreeDescription) val.getOwnedRepresentations().get(1));
    }

    public TreeDescriptionGenericEMFTreeWithInheritance genericemftreewithinheritance() {
        return new TreeDescriptionGenericEMFTreeWithInheritance((TreeDescription) val.getOwnedRepresentations().get(2));
    }

    public TreeDescriptionInfiniteRecursion infiniterecursion() {
        return new TreeDescriptionInfiniteRecursion((TreeDescription) val.getOwnedRepresentations().get(3));
    }

    public TreeDescriptionInfiniteRecursionWithInheritance infiniterecursionwithinheritance() {
        return new TreeDescriptionInfiniteRecursionWithInheritance((TreeDescription) val.getOwnedRepresentations().get(4));
    }

    public Viewpoint object() {
        return this.val;
    }

}
