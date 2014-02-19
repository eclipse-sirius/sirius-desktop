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
package org.eclipse.sirius.business.internal.contribution;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRelations;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Locates all the representation extensions which apply to some representation
 * description, depending on the context.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class RepresentationExtensionsFinder {
    /**
     * The representation for which we want to find extensions.
     */
    private final RepresentationDescription extensionTarget;

    /**
     * Constructor.
     * 
     * @param extensionTarget
     *            the representation for which we want to find extensions.
     */
    public RepresentationExtensionsFinder(RepresentationDescription extensionTarget) {
        this.extensionTarget = Preconditions.checkNotNull(extensionTarget);
    }

    /**
     * Finds all the representations extensions which can be applied to this
     * target in the specified context.
     * 
     * @param context
     *            the set of viewpoints in which to look for extensions.
     * @return all the representation extensions which apply to this target
     *         representation in the specified context. The
     *         RepresentationExtensionDescriptions returned are the instances
     *         loaded in the session's ResourceSet.
     */
    public List<RepresentationExtensionDescription> findApplicableExtensions(Iterable<Viewpoint> context) {
        String targetSiriusURI = getTargetSiriusURI();
        String targetRepresentationName = extensionTarget.getName();
        if (targetSiriusURI != null && targetRepresentationName != null) {
            return findApplicableExtensions(context, targetSiriusURI, targetRepresentationName);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Tests whether the specified viewpoint contains anything that can affect
     * this representation. If it is the case, this means the representation
     * must be updated whenever the Sirius is enabled or disabled.
     * 
     * @param vp
     *            the viewpoint why may affect the representation.
     * @return <code>true</code> if the viewpoint contains any representation
     *         description extension which applies to the representation.
     */
    public boolean isAffectedBy(Viewpoint vp) {
        return !findApplicableExtensions(Collections.singleton(vp)).isEmpty();
    }

    /**
     * Returns the subset of the viewpoints currently selected in the session
     * which are relevant for the specified representation description. This
     * includes the representation description's parent Sirius, all the
     * Viewpoints it reuses, all the Viewpoints which extend any of those, and
     * recursively all the Viewpoints those reuses or are extended by.
     * 
     * @param session
     *            the session in which the representation description is used.
     * @return all the Viewpoints which are relevant for the computation of the
     *         effective representation description.
     */
    public LinkedHashSet<Viewpoint> findAllRelevantViewpoints(Session session) {
        RepresentationDescription mainRepresentationDescription = extensionTarget;
        LinkedHashSet<Viewpoint> result = Sets.newLinkedHashSet();
        Viewpoint mainVP = new RepresentationDescriptionQuery(mainRepresentationDescription).getParentViewpoint();
        if (mainVP != null) {
            BiMap<URI, Viewpoint> candidates = HashBiMap.create();
            for (Viewpoint vp : session.getSelectedViewpoints(false)) {
                Option<URI> uri = new ViewpointQuery(vp).getViewpointURI();
                if (uri.some()) {
                    candidates.put(uri.get(), vp);
                }
            }
            ViewpointRelations relations = ((org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry) ViewpointRegistry.getInstance()).getRelations();
            /*
             * Seed the result with the representation's parent Sirius and
             * augment it with the viewpoints we reuse or are extended by until
             * we reach a fixpoint. Guaranteed to terminate as the set of
             * Viewpoints we can add is finite (at the most, all the selected
             * Viewpoints).
             */
            boolean changed = result.add(mainVP);
            while (changed) {
                changed = false;
                // Add all the Viewpoints we reuse.
                for (Viewpoint v1 : Lists.newArrayList(result)) {
                    URI uri = candidates.inverse().get(v1);
                    changed = changed || Iterables.addAll(result, Iterables.transform(relations.getReuse().apply(uri), Functions.forMap(candidates)));
                }
                // Add all the Viewpoints which extend any of us.
                for (Viewpoint v : session.getSelectedViewpoints(false)) {
                    URI extenderUri = candidates.inverse().get(v);
                    for (URI extendeeUri : relations.getCustomize().apply(extenderUri)) {
                        if (result.contains(candidates.get(extendeeUri))) {
                            changed = changed || result.add(v);
                        }
                    }
                }
            }
        }
        return result;
    }

    private String getTargetSiriusURI() {
        Option<EObject> parentVp = new EObjectQuery(extensionTarget).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getViewpoint());
        if (parentVp.some()) {
            Option<URI> viewpointURI = new ViewpointQuery((Viewpoint) parentVp.get()).getViewpointURI();
            if (viewpointURI.some()) {
                return viewpointURI.get().toString();
            }
        }
        return null;
    }

    private List<RepresentationExtensionDescription> findApplicableExtensions(Iterable<Viewpoint> context, String targetSiriusURI, String targetRepresentationName) {
        List<RepresentationExtensionDescription> result = Lists.newArrayList();
        for (Viewpoint vp : context) {
            for (RepresentationExtensionDescription ext : vp.getOwnedRepresentationExtensions()) {
                if (appliesTo(ext, targetSiriusURI, targetRepresentationName)) {
                    result.add(ext);
                }
            }
        }
        return result;
    }

    private boolean appliesTo(RepresentationExtensionDescription ext, String targetViewpointURI, String targetRepresentationName) {
        return targetViewpointURI.matches(ext.getViewpointURI()) && targetRepresentationName.matches(ext.getRepresentationName());
    }
}
