/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Useful operations to manipulate a DDiagram model.
 * 
 * @author ymortier
 */
public final class SiriusUtil {

    /** The file extension for Designer diagram files. */
    public static final String SESSION_RESOURCE_EXTENSION = "aird"; //$NON-NLS-1$

    /** The file extension for Designer modeler description files. */
    public static final String DESCRIPTION_MODEL_EXTENSION = "odesign"; //$NON-NLS-1$

    /** The "environment:/" uri scheme. */
    public static final String ENVIRONMENT_URI_SCHEME = "environment"; //$NON-NLS-1$

    /** The "environment:/viewpoint" resource uri. */
    public static final String VIEWPOINT_ENVIRONMENT_RESOURCE_URI = ENVIRONMENT_URI_SCHEME + ":/viewpoint"; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private SiriusUtil() {
        // empty.
    }

    /**
     * Check if the given {@link Resource} is a Sirius modeler description file.
     * 
     * @param resource
     *            the {@link Resource} instance
     * @return <code>true</code> or <code>false</code>
     */
    public static boolean isModelerDescriptionFile(final Resource resource) {
        if (resource.getURI() != null && resource.getURI().fileExtension() != null) {
            return new FileQuery(resource.getURI().fileExtension()).isVSMFile();
        }
        return false;
    }

    /**
     * Return the first {@link DSemanticDecorator}. it tests the eObject
     * parameter and all parents of eObject.
     * 
     * @param eObject
     *            an {@link EObject}.
     * @return the first {@link DSemanticDecorator}.
     */
    public static DSemanticDecorator getNearestDecorateSemanticElement(final EObject eObject) {
        DSemanticDecorator result = null;
        EObject eObj = eObject;
        while (result == null && eObj != null) {
            if (eObj instanceof DSemanticDecorator) {
                result = (DSemanticDecorator) eObj;
            }
            eObj = eObj.eContainer();
        }
        return result;
    }

    /**
     * Return the first {@link Group} that has the specified name in the
     * resource set <code>resourceSet</code>.
     * 
     * @param resourceSet
     *            the resource set to browse.
     * @param name
     *            the name of the group.
     * @return the first {@link Group} that has the specified name in the
     *         resource set <code>resourceSet</code>.
     */
    public static Group getGroupByName(final ResourceSet resourceSet, final String name) {
        Group result = null;
        final Iterator<Resource> iterResources = resourceSet.getResources().iterator();
        while (iterResources.hasNext() && result == null) {
            final Resource currentResource = iterResources.next();
            final Iterator<EObject> iterContents = currentResource.getContents().iterator();
            while (iterContents.hasNext() && result == null) {
                final Object currentRoot = iterContents.next();
                if (currentRoot instanceof Group) {
                    final Group group = (Group) currentRoot;
                    if (group.getName() != null && group.getName().equals(name)) {
                        result = group;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find the designer view that owns the specified representation element.
     * 
     * @param element
     *            the element.
     * @return the found view or <code>null</code> if no view is found.
     */
    public static DView findView(final DRepresentationElement element) {
        EObject current = element;
        while (current != null) {
            if (current instanceof DView) {
                return (DView) current;
            }
            current = current.eContainer();
        }
        return null;
    }

    /**
     * Find the designer representation that owns the specified representation
     * element.
     * 
     * @param element
     *            the element.
     * @return the found representation or <code>null</code> if no view is
     *         found.
     */
    public static DRepresentation findRepresentation(final DRepresentationElement element) {
        EObject current = element;
        while (current != null) {
            if (current instanceof DRepresentation) {
                return (DRepresentation) current;
            }
            current = current.eContainer();
        }
        return null;
    }

    /**
     * Return <code>true</code> if the specified object is an instance of a
     * class coming from Sirius. Using this method has strong implications
     * regarding extensibility as subtypes of classes or interfaces coming from
     * Sirius will return false here. You might want to pause a minute before
     * using it.
     * 
     * @param ep
     *            : any {@link Object}
     * @return true if this object class is from the Sirius project false
     *         otherwise.
     */
    public static boolean isFromSirius(final Object ep) {
        if (ep != null) {
            final String packageName = ep.getClass().getPackage().getName();
            return packageName.startsWith("org.eclipse.sirius"); //$NON-NLS-1$
        }
        return false;
    }

    /**
     * Check if a resource with the uri is already present in the resourceSet.
     * 
     * @param resourceSet
     *            The resource set
     * @param uri
     *            The uri to check
     * @return true if presents, false otherwise
     */
    public static boolean hasAlreadyResource(final ResourceSet resourceSet, final URI uri) {
        boolean result = false;
        for (final Resource resource : resourceSet.getResources()) {
            if (uri != null && uri.equals(resource.getURI())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Find the viewpoint that has the specified name in the resource.
     * 
     * @param resource
     *            The resource in which search
     * @param viewpointName
     *            The viewpoint name to search
     * @return the found viewpoint or <code>null</code> if no viewpoint is found
     */
    public static Viewpoint findViewpoint(final Resource resource, final String viewpointName) {
        if (resource.getContents() != null && !resource.getContents().isEmpty()) {
            Iterator<Viewpoint> it = getViewpoints(resource);
            while (it.hasNext()) {
                Viewpoint editingDomainViewpoint = it.next();
                if ((viewpointName == null && editingDomainViewpoint.getName() == null) || (viewpointName != null && viewpointName.equals(editingDomainViewpoint.getName()))) {
                    return editingDomainViewpoint;
                }
            }
        }
        return null;
    }

    /**
     * Deletes the object from its {@link EObject#eResource containing} resource
     * and/or its {@link EObject#eContainer containing} object as well as from
     * any other feature that references it (by using the session semantic cross
     * referencer) within the enclosing resource set, resource, or root object.
     * 
     * @param eObject
     *            the object to delete. We try to retrieve the session from this
     *            eObject.
     */
    public static void delete(EObject eObject) {
        delete(eObject, null);
    }

    /**
     * Deletes the object from its {@link EObject#eResource containing} resource
     * and/or its {@link EObject#eContainer containing} object as well as from
     * any other feature that references it (by using the session semantic cross
     * referencer) within the enclosing resource set, resource, or root object.
     * 
     * @param eObject
     *            the object to delete.
     * @param session
     *            the eObject session. Used to retrieve the semantic cross
     *            referencer.
     */
    public static void delete(EObject eObject, Session session) {
        Session currentEObjectSession = session;
        if (currentEObjectSession == null) {
            EObject semanticRootElement = EcoreUtil.getRootContainer(eObject);
            if (semanticRootElement instanceof DAnalysis) {
                EObject model = Iterables.getFirst(((DAnalysis) semanticRootElement).getModels(), null);
                if (model != null) {
                    semanticRootElement = model;
                }
            }
            currentEObjectSession = SessionManager.INSTANCE.getSession(semanticRootElement);
        }
        if (currentEObjectSession != null) {
            Collection<EStructuralFeature.Setting> usages = currentEObjectSession.getSemanticCrossReferencer().getInverseReferences(eObject);
            for (EStructuralFeature.Setting setting : usages) {
                if (setting.getEStructuralFeature().isChangeable()) {
                    EcoreUtil.remove(setting, eObject);
                }
            }
            EcoreUtil.remove(eObject);
        } else {
            EcoreUtil.delete(eObject);
        }

    }

    private static Iterator<Viewpoint> getViewpoints(final Resource resource) {
        if (DESCRIPTION_MODEL_EXTENSION.equals(resource.getURI().fileExtension())) {
            Collection<Viewpoint> result = Lists.newArrayList();
            for (Group group : Iterables.filter(resource.getContents(), Group.class)) {
                result.addAll(group.getOwnedViewpoints());
            }
            return result.iterator();
        } else {
            return Iterators.filter(resource.getAllContents(), Viewpoint.class);
        }
    }

}
