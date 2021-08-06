/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.internal.metamodel.spec;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.model.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.impl.DTreeImpl;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Implementation od DTree.
 *
 * @author nlepine
 *
 */
public class DTreeSpec extends DTreeImpl {
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        EList<DTreeItem> result = getOwnedTreeItems();
        final EReference feature = ViewpointPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), feature, result.size(), result.toArray());
    }

    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        List<DRepresentationElement> result = Lists.newArrayList(Iterators.filter(eAllContents(), DRepresentationElement.class));
        final EReference feature = ViewpointPackage.eINSTANCE.getDRepresentation_RepresentationElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), feature, result.size(), result.toArray());
    }

    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }
}
