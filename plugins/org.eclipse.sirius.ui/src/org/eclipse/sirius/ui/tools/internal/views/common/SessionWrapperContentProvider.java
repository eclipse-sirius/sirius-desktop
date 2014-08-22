/*******************************************************************************
 * Copyright (c) 2008, 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ControlledRoot;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ResourcesFolderItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointsFolderItemImpl;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * ContentProvider handling sessions and mixing semantic/representations.
 * 
 * @author cbrun
 * 
 */
public class SessionWrapperContentProvider implements ITreeContentProvider {

    private ITreeContentProvider wrapped;

    private Collection<ITreeContentProvider> extensions;

    /**
     * create a new wrapper content provider.
     * 
     * @param wrapped
     *            the original content provider.
     */
    public SessionWrapperContentProvider(final ITreeContentProvider wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * Set extensions to use to provide custom children.
     * 
     * @param extensions
     *            the extensions to use
     */
    public void setExtensions(Collection<ITreeContentProvider> extensions) {
        this.extensions = extensions;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        Collection<Object> allChildren = Lists.newArrayList();
        try {
            allChildren.addAll(doGetChildren(parentElement));
            allChildren.addAll(getChildrenFromExtensions(parentElement));
        } catch (IllegalStateException e) {
            // Nothing to do, can happen with CDO
        }

        return allChildren.toArray();
    }

    /**
     * Get custom children from declared extensions.
     * 
     * @param parentElement
     *            the parent element.
     * 
     * @return custom children computed by declared extensions.
     */
    public Collection<Object> getChildrenFromExtensions(final Object parentElement) {
        Collection<Object> extensionsChildren = Lists.newArrayList();
        if (extensions != null) {
            for (final ITreeContentProvider extension : extensions) {
                extensionsChildren.addAll(Arrays.asList(extension.getChildren(parentElement)));
            }
        }
        return extensionsChildren;
    }

    private Collection<Object> doGetChildren(final Object parentElement) {
        Collection<Object> result = Lists.newArrayList();

        if (parentElement instanceof Session) {
            result.addAll(getSessionChildren((Session) parentElement));
        } else if (parentElement instanceof CommonSessionItem) {
            result.addAll(((CommonSessionItem) parentElement).getChildren());
        } else if (parentElement instanceof Collection) {
            result.addAll((Collection<?>) parentElement);
        } else if (parentElement instanceof Resource) {
            Resource res = (Resource) parentElement;
            Session session = SessionManager.INSTANCE.getSession(res);
            if (res.isLoaded()) {
                if (session instanceof DAnalysisSessionEObject && ((DAnalysisSessionEObject) session).getControlledResources().contains(parentElement)) {
                    for (EObject obj : res.getContents()) {
                        result.add(new ControlledRoot(obj, parentElement));
                    }
                } else {
                    result.addAll(getWrappedChildren(parentElement));
                }
            }
        } else if (parentElement instanceof EObject && !(parentElement instanceof DRepresentation)) {
            // Look for representation with current element as semantic
            // target.
            result.addAll(getWrappedChildren(parentElement));
            result.addAll(getRepresentationsAssociatedToEObject((EObject) parentElement));
        }

        return result;
    }

    private Collection<Object> getWrappedChildren(Object parentElement) {
        Object[] structuralChildren = wrapped.getChildren(parentElement);
        if (structuralChildren != null) {
            return Arrays.asList(structuralChildren);
        }
        return Collections.emptyList();
    }

    private Collection<Object> getSessionChildren(final Session session) {
        final List<Object> all = Lists.newArrayList();

        if (session instanceof DAnalysisSession && !session.getReferencedSessionResources().isEmpty()) {
            all.add(new ResourcesFolderItemImpl(session, session));
        }

        all.add(new ViewpointsFolderItemImpl(session, session));
        all.addAll(getSemanticResources(session));
        return all;
    }

    /**
     * Return all the semantic resources of the session : <LI>
     * <UL>
     * except the aird and GMFResource resources
     * </UL>
     * <UL>
     * plus the parent of the semantic resources that are controlled.
     * </UL>
     * </LI>
     * 
     * @param session
     *            the concerned session
     * @return list of semantic resources
     */
    private Collection<Resource> getSemanticResources(final Session session) {
        final Collection<Resource> allSemanticRes = Lists.newArrayList();

        // Add semantic resources
        allSemanticRes.addAll(session.getSemanticResources());

        // Add controlled resources
        if (session instanceof DAnalysisSessionEObject) {
            allSemanticRes.addAll(((DAnalysisSessionEObject) session).getControlledResources());
        }

        return allSemanticRes;
    }

    private Collection<DRepresentation> getRepresentationsAssociatedToEObject(final EObject eObject) {
        final Session session = SessionManager.INSTANCE.getSession(eObject);
        if (session != null) {
            Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getRepresentations(eObject, session);
            List<DRepresentation> filteredReps = Lists.newArrayList(Iterables.filter(allRepresentations, new InViewpointPredicate(session.getSelectedViewpoints(false))));
            // Sort the available representations alphabetically by name
            Collections.sort(filteredReps, Ordering.natural().onResultOf(new Function<DRepresentation, String>() {
                public String apply(DRepresentation from) {
                    return from.getName();
                }
            }));
            return filteredReps;
        }
        return Collections.emptyList();
    }

    private Collection<Resource> filter(final Collection<Resource> resources) {
        final Collection<Resource> result = new ArrayList<Resource>(resources.size());
        for (final Resource res : resources) {
            if (!isFiltered(res)) {
                result.add(res);
            }

        }
        return result;
    }

    private boolean isFiltered(final Resource resource) {
        return new ResourceQuery(resource).isRepresentationsResource();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        Object parent = getParentFromExtensions(element);

        if (parent == null) {
            if (element instanceof DRepresentation && element instanceof DSemanticDecorator) {
                parent = ((DSemanticDecorator) element).getTarget();
            } else if (element instanceof Resource) {
                parent = SessionManager.INSTANCE.getSession((Resource) element);
            } else if (element instanceof CommonSessionItem) {
                parent = ((CommonSessionItem) element).getParent();
            } else if (element instanceof EObject) {
                parent = wrapped.getParent(element);
            }
        }

        return parent;
    }

    /**
     * Get custom parent from declared extensions.
     * 
     * @param element
     *            the element.
     * 
     * @return custom parent computed by declared extensions.
     */
    public Object getParentFromExtensions(final Object element) {
        if (extensions != null) {
            for (final ITreeContentProvider extension : extensions) {
                Object parent = extension.getParent(element);
                if (parent != null) {
                    return parent;
                }
            }
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        Object[] children = getChildren(element);
        return children != null && children.length != 0;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        Object[] result = null;
        if (inputElement instanceof Session) {
            result = filter(((Session) inputElement).getSemanticResources()).toArray();
        }
        if (inputElement instanceof Collection) {
            result = ((Collection<?>) inputElement).toArray();
        }
        if (result == null) {
            return wrapped.getElements(inputElement);
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void dispose() {
        try {
            wrapped.dispose();
            if (wrapped instanceof AdapterFactoryContentProvider) {
                if (((AdapterFactoryContentProvider) wrapped).getAdapterFactory() instanceof IDisposable) {
                    ((IDisposable) ((AdapterFactoryContentProvider) wrapped).getAdapterFactory()).dispose();
                }
            }
        } catch (NullPointerException e) {
            // can occur when using CDO (if the view is
            // closed when transactions have been closed )
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        if (wrapped != null) {
            wrapped.inputChanged(viewer, oldInput, newInput);
        }
    }

    private class InViewpointPredicate implements Predicate<DRepresentation> {

        private final Collection<Viewpoint> scope;

        /**
         * Filter representations <code>allRepresentations</code> whose
         * description belongs to one the specified viewpoints.
         * 
         * @param scope
         *            the viewpoints to test.
         */
        public InViewpointPredicate(Collection<Viewpoint> scope) {
            this.scope = scope;
        }

        /**
         * {@inheritDoc}
         */
        public boolean apply(DRepresentation input) {
            return isFromActiveSirius(input);
        }

        /**
         * Tests whether a representation belongs to a viewpoint which is
         * currently active in the session.
         * 
         * @param representation
         *            the representation to check.
         * @return <code>true</code> if the representation belongs to a
         *         viewpoint which is currently active in the session.
         */
        private boolean isFromActiveSirius(DRepresentation representation) {
            if (representation.eResource() != null) {
                RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
                if (description != null) {
                    Viewpoint reprSirius = ViewpointRegistry.getInstance().getViewpoint(description);
                    // representationDescription.eContainer() can be null in the
                    // case that the viewpoint has been renamed after the aird
                    // creation
                    if (reprSirius != null && !reprSirius.eIsProxy() && reprSirius.eResource() != null) {
                        for (final Viewpoint viewpoint : scope) {
                            if (EqualityHelper.areEquals(viewpoint, reprSirius)) {
                                return true;
                            }
                        }
                    }

                }
            }
            return false;
        }
    }
}
