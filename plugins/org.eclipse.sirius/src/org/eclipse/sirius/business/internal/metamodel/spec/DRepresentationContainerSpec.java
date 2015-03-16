/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.spec;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.DRepresentationContainerImpl;

/**
 * Implementation of {@link DRepresentationContainerSpec}.
 * 
 * @author cbrun
 */
public class DRepresentationContainerSpec extends DRepresentationContainerImpl {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DViewImpl#getAllRepresentations()
     */
    @Override
    public EList<DRepresentation> getAllRepresentations() {
        final Collection<DRepresentation> result = new ArrayList<DRepresentation>(getOwnedRepresentations());
        result.addAll(getReferencedRepresentations());
        return new EcoreEList.UnmodifiableEList<DRepresentation>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDView_AllRepresentations(), result.size(), result.toArray());
    }

    /**
     * Refresh the functionnal analysis.
     * 
     * @see org.eclipse.sirius.impl.DRefreshableImpl#refresh()
     */
    @Override
    public void refresh() {
        final Set<DRepresentation> representationsToDelete = new HashSet<DRepresentation>();
        Iterator<DRepresentation> it = getOwnedRepresentations().iterator();
        while (it.hasNext()) {
            final DRepresentation representation = it.next();
            /*
             * detect dangling representations.
             */
            if (representation instanceof DSemanticDecorator && ((DSemanticDecorator) representation).getTarget() == null) {
                representationsToDelete.add(representation);
            }
            if (!representationsToDelete.contains(representation)) {
                representation.refresh();
            }
        }
        /*
         * delete dangling representations
         */
        it = representationsToDelete.iterator();
        while (it.hasNext()) {
            final EObject next = it.next();
            final Session session;
            if (next instanceof DSemanticDecorator) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) next).getTarget());
            } else {
                session = SessionManager.INSTANCE.getSession(next);
            }
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(next).eDelete(next, session != null ? session.getSemanticCrossReferencer() : null);
        }
    }

    @Override
    public EList<EObject> getModels() {
        final Collection<EObject> models = new HashSet<EObject>(3);
        for (final DRepresentation representation : this.getOwnedRepresentations()) {
            if (representation instanceof DSemanticDecorator) {
                models.add(getModel(((DSemanticDecorator) representation).getTarget()));
            }
        }
        return new EcoreEList.UnmodifiableEList<EObject>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentationContainer_Models(), models.size(), models.toArray());
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
