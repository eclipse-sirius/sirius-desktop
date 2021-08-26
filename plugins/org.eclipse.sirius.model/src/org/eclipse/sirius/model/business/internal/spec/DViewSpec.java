/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.model.business.internal.spec;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.model.business.internal.query.DViewQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.DViewImpl;

/**
 * Implementation of {@link DViewSpec}.
 * 
 * @author cbrun
 */
public class DViewSpec extends DViewImpl {

    @Override
    public EList<EObject> getModels() {
        final Collection<EObject> models = new HashSet<EObject>(3);
        for (final DRepresentation representation : new DViewQuery(this).getLoadedRepresentations()) {
            if (representation instanceof DSemanticDecorator) {
                models.add(getModel(((DSemanticDecorator) representation).getTarget()));
            }
        }
        return new EcoreEList.UnmodifiableEList<EObject>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDView_Models(), models.size(), models.toArray());
    }

    private EObject getModel(final EObject target) {
        if (target != null) {
            Resource targetResource = target.eResource();
            if (targetResource != null) {
                return targetResource.getContents().iterator().next();
            }
        }
        return target;
    }
}
