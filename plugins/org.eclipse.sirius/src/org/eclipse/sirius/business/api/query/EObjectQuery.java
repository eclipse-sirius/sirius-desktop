/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.emf.EClassQuery;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A class aggregating all the queries (read-only!) having a {@link EObject} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class EObjectQuery {

    private static final String BRACKET_OPEN = "{"; //$NON-NLS-1$

    private static final String BRACKET_CLOSE = "}"; //$NON-NLS-1$

    private static final String COMMA = ", "; //$NON-NLS-1$

    private static final String COLON = ": "; //$NON-NLS-1$

    private static final String DOUBLE_COLON = "::"; //$NON-NLS-1$

    private static final String ECLASS = "eClass"; //$NON-NLS-1$

    private static final String NAME_ATTR = "name"; //$NON-NLS-1$

    private static final String ID_ATTR = "id"; //$NON-NLS-1$

    private static final String UID_ATTR = "uid"; //$NON-NLS-1$

    /** The concerned {@link EObject}. */
    protected EObject eObject;

    /**
     * The {@link ECrossReferenceAdapter} to use for all inverse references queries.
     */
    protected ECrossReferenceAdapter xref;

    /**
     * Create a new query.
     * 
     * @param eObject
     *            the element to query.
     */
    public EObjectQuery(EObject eObject) {
        this.eObject = eObject;
    }

    /**
     * Create a new query. Prefer this constructor if in the context of the call, you already have access to the cross
     * referencer of the session containing the queried EObject.
     * 
     * @param eObject
     *            the element to query.
     * @param xref
     *            ECrossReferenceAdapter to use for all queries about inverse references.
     */
    public EObjectQuery(EObject eObject, ECrossReferenceAdapter xref) {
        this.eObject = eObject;
        this.xref = xref;
    }

    /**
     * Browse the model upward (from the leaf to the root) and return the first representation found.
     * 
     * @return the representation if found, null otherwise.
     */
    public Option<DRepresentation> getRepresentation() {
        EObject current = eObject;
        while (current != null) {
            if (current instanceof DRepresentation) {
                return Options.newSome((DRepresentation) current);
            }
            current = current.eContainer();
        }
        return Options.newNone();
    }

    /**
     * Get the resource container i.e the first parent container serialized in its own resource.
     * 
     * @return should be not <code>null</code>.
     */
    public EObject getResourceContainer() {
        EObject result = eObject;
        if (eObject instanceof InternalEObject) {
            if (((InternalEObject) eObject).eDirectResource() != null) {
                result = eObject;
            } else {
                result = new EObjectQuery(eObject.eContainer()).getResourceContainer();
            }
        }
        return result;
    }

    /**
     * Finds the first (closest) ancestor of this element which is of the specified type (or a sub-type).
     * 
     * @param klass
     *            the type of element to look for.
     * @return the closest ancestor of the specified type, if one was found.
     */
    public Option<EObject> getFirstAncestorOfType(EClass klass) {
        EObject current = eObject.eContainer();
        while (current != null && !klass.isInstance(current)) {
            current = current.eContainer();
        }
        if (current != null) {
            return Options.newSome(current);
        } else {
            return Options.newNone();
        }
    }

    /**
     * Finds all the objects in the session which point to this EObject through a reference of the specified name. The
     * queried EObject must be part of an opened Sirius session.
     * 
     * @param featureName
     *            the name of the pointing reference.
     * @return all the EObjects in the same session as this EObject which point to it through a reference of the
     *         specified name.
     */
    public Collection<EObject> getInverseReferences(final String featureName) {
        Preconditions.checkNotNull(featureName);
        return getInverseReferences(new Predicate<EStructuralFeature.Setting>() {
            @Override
            public boolean apply(Setting input) {
                return input != null && input.getEStructuralFeature() != null && featureName.equals(input.getEStructuralFeature().getName());
            }
        });
    }

    /**
     * Finds all the objects in the session which point to this EObject through a specific reference type. The queried
     * EObject must be part of an opened Sirius session.
     * 
     * @param ref
     *            the pointing reference.
     * @return all the EObjects in the same session as this EObject which point to it through the specified reference.
     */
    public Collection<EObject> getInverseReferences(final EReference ref) {
        Preconditions.checkNotNull(ref);
        return getInverseReferences(new Predicate<EStructuralFeature.Setting>() {
            @Override
            public boolean apply(Setting input) {
                return input != null && ref.equals(input.getEStructuralFeature());
            }
        });
    }

    /**
     * Finds all the objects in the session which point to this EObject through one of the specific references type. The
     * queried EObject must be part of an opened Sirius session.
     * 
     * @param refs
     *            the pointing references.
     * @return all the EObjects in the same session as this EObject which point to it through one of the specified
     *         references.
     */
    public Collection<EObject> getInverseReferences(final Set<EReference> refs) {
        Preconditions.checkNotNull(refs);
        return getInverseReferences(new Predicate<EStructuralFeature.Setting>() {
            @Override
            public boolean apply(Setting input) {
                return input != null && refs.contains(input.getEStructuralFeature());
            }
        });
    }

    /**
     * Finds all the objects in the session which reference this EObject through a setting matching the specified
     * predicate. The queried EObject must be part of an opened Sirius session.
     * 
     * @param predicate
     *            the predicate to use to match incoming references to this object.
     * @return all the EObjects in the same session as this EObject which point to it through a matching setting.
     */
    private Collection<EObject> getInverseReferences(Predicate<Setting> predicate) {
        Preconditions.checkNotNull(predicate);
        if (xref == null) {
            Session session = getSession();
            if (session != null) {
                xref = session.getSemanticCrossReferencer();
            }
        }
        if (xref == null) {
            return Collections.emptySet();
        } else {
            Collection<EObject> result = new HashSet<>();
            for (EStructuralFeature.Setting setting : Iterables.filter(xref.getInverseReferences(eObject), predicate)) {
                result.add(setting.getEObject());
            }
            return result;
        }
    }

    /**
     * Finds all the {@link DRepresentationDescriptor} in the session which the queried object references one of
     * DAnnotation.references of all {@link DModelElement}.eAnnotations}. The queried EObject must be part of an opened
     * Sirius session.
     * 
     * @param sourceKey
     *            Allow to return only DRepresentationDescriptor that references queried object through a DAnnotation
     *            that has this key for source field. If null, this parameter is ignored as filter.
     * 
     * @return all the searched EObjects in the same session as this EObject.
     */
    public List<DRepresentationDescriptor> getImpactedRepDescriptorFromDAnnotationData(String sourceKey) {
        Collection<EObject> inverseReferences = getInverseReferences(DescriptionPackage.eINSTANCE.getDAnnotation_References());
        return inverseReferences.stream().map(DAnnotation.class::cast).filter(annot -> sourceKey != null ? sourceKey.equals(annot.getSource()) : true).map(annot -> annot.eContainer())
                .filter(DRepresentationDescriptor.class::isInstance).map(DRepresentationDescriptor.class::cast).collect(Collectors.toList());
    }

    /**
     * Try to return the session corresponding to the current {@link EObject} (this object can be a semantic EObject or
     * an element of the aird Resource).
     * 
     * @return the corresponding session.
     */
    public Session getSession() {
        Session found = SessionManager.INSTANCE.getSession(eObject);
        if (found == null) {
            try {
                final EObject root = EcoreUtil.getRootContainer(eObject);
                final Resource res = root != null ? root.eResource() : null;
                if (res != null) {
                    ResourceQuery resourceQuery = new ResourceQuery(res);
                    if (resourceQuery.isRepresentationsResource()) {
                        found = getSessionFromAirdResource(res);
                    } else if (resourceQuery.isSrmResource()) {
                        found = getSessionFromSrmResource(res);
                    }
                }
            } catch (IllegalStateException e) {
                // Silent catch: this can happen when trying to get the session
                // on a disposed Eobject
            }
        }
        return found;
    }

    private Session getSessionFromSrmResource(Resource srmResource) {
        return Optional.ofNullable(new EObjectQuery(srmResource.getContents().get(0)).getDAnalysis()).map(EObject::eResource).map(res -> getSessionFromAirdResource(res)).orElse(null);
    }

    /**
     * Try to return the session corresponding to an {@link Resource} of a session (*.aird).
     * 
     * @param airdResource
     *            a {@link Resource} corresponding to an aird resource.
     * @return the corresponding session.
     */
    private Session getSessionFromAirdResource(final Resource airdResource) {
        Session found = null;
        for (Iterator<? extends Session> sessions = SessionManager.INSTANCE.getSessions().iterator(); sessions.hasNext() && found == null; /* */) {
            Session sess = sessions.next();
            if (sess instanceof DAnalysisSession && sess.getAllSessionResources().contains(airdResource)) {
                found = sess;
            }
        }
        return found;
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this object depend and which are
     * loaded/resolved. Calling this method does not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources through containment references.
     * 
     * @return the URIs of all the Resources this element and its proper descendants depends on and which are
     *         loaded/resolved.
     */
    public Collection<URI> getResolvedDependencies() {
        Collection<URI> dependencies = new HashSet<>();
        TreeIterator<Object> iter = EcoreUtil.getAllProperContents(eObject, false);
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof EObject) {
                addResolvedDependencies((EObject) obj, dependencies);
            }
        }
        addResolvedDependencies(eObject, dependencies);
        Resource eObjectResource = eObject.eResource();
        if (eObjectResource != null) {
            dependencies.remove(eObjectResource.getURI());
        }
        return dependencies;
    }

    private void addResolvedDependencies(EObject eObj, Collection<URI> dependencies) {
        for (EReference ref : Iterables.filter(new EClassQuery(eObj.eClass()).getAllNonContainmentFeatures(), EReference.class)) {
            Object value = eObj.eGet(ref, false);
            if (value instanceof Collection<?>) {
                for (EObject atomicValue : Iterables.filter((Collection<?>) value, EObject.class)) {
                    addNonProxyResourceURI(atomicValue, dependencies);
                }
            } else if (value instanceof EObject) {
                addNonProxyResourceURI((EObject) value, dependencies);
            }
        }
    }

    private void addNonProxyResourceURI(EObject value, Collection<URI> dependencies) {
        if (value != null && !value.eIsProxy()) {
            Resource valueResource = value.eResource();
            if (valueResource != this.eObject.eResource() && valueResource != null) {
                dependencies.add(valueResource.getURI());
            }
        }
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this resource depend but which are not yet
     * loaded/resolved. Calling this method does not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources through containment references.
     * 
     * @return the URIs of all the Resources this one depends on but which is not yet loaded/resolved.
     */
    public Collection<URI> getUnresolvedDependencies() {
        Collection<URI> dependencies = new HashSet<>();
        TreeIterator<Object> iter = EcoreUtil.getAllProperContents(eObject, false);
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof EObject) {
                addUnresolvedDependencies((EObject) obj, dependencies);
            }
        }
        addUnresolvedDependencies(eObject, dependencies);
        return dependencies;
    }

    /**
     * Get all the EOject referenced by this one through the specified reference.
     * 
     * @param ref
     *            the reference. Must be a valid reference for the queried elements.
     * @return all the EOject referenced by this one through the specified reference. May be a singleton list, or even
     *         an empty list.
     */
    @SuppressWarnings("unchecked")
    public List<EObject> getValues(EReference ref) {
        List<EObject> result = Collections.emptyList();
        if (ref.isMany()) {
            Object rawValue = eObject.eGet(ref);
            if (rawValue != null && !(rawValue instanceof EList<?>)) {
                throw new RuntimeException(MessageFormat.format(Messages.EObjectQuery_valuesErrorMsg, ref.getName(), rawValue.getClass()));
            }
            if (rawValue != null) {
                result = new ArrayList<EObject>((EList<EObject>) rawValue);
            }
        } else {
            EObject rawValue = (EObject) eObject.eGet(ref);
            if (rawValue != null) {
                result = Collections.singletonList(rawValue);
            }
        }
        return result;
    }

    private void addUnresolvedDependencies(EObject eObj, Collection<URI> dependencies) {
        for (EReference ref : Iterables.filter(new EClassQuery(eObj.eClass()).getAllNonContainmentFeatures(), EReference.class)) {
            Object value = eObj.eGet(ref, false);
            if (value instanceof Collection<?>) {
                for (EObject atomicValue : Iterables.filter((Collection<?>) value, EObject.class)) {
                    addProxyResourceURI(atomicValue, dependencies);
                }
            } else if (value instanceof EObject) {
                addProxyResourceURI((EObject) value, dependencies);
            }
        }
    }

    private void addProxyResourceURI(EObject value, Collection<URI> dependencies) {
        if (value != null && value.eIsProxy()) {
            dependencies.add(((InternalEObject) value).eProxyURI().trimFragment());
        }
    }

    /**
     * Check if this EObject has in its hierarchy the <code>parentToCheck</code> .
     * 
     * @param parentToCheck
     *            The parent to check.
     * @return true if this EObject is contained in the parentToCheck, false otherwise.
     */
    public boolean isContainedIn(EObject parentToCheck) {
        EObject current = eObject;
        while (current.eContainer() != null) {
            if (current.eContainer().equals(parentToCheck)) {
                return true;
            }
            current = current.eContainer();
        }
        return false;
    }

    /**
     * Get all the available viewpoints contained in the resource set of the object to query.
     * 
     * @return all the available viewpoints
     */
    public Collection<Viewpoint> getAvailableViewpointsInResourceSet() {
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            final ResourceSet resourceSet = eResource.getResourceSet();
            final Collection<Viewpoint> viewpoints = new HashSet<Viewpoint>();
            for (final Resource resource : resourceSet.getResources()) {
                if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Group) {
                    final Group group = (Group) resource.getContents().get(0);
                    viewpoints.addAll(group.getOwnedViewpoints());
                }
            }
            return viewpoints;
        }
        return Collections.emptySet();
    }

    /**
     * Allow to retrieve the father {@link DAnalysis} from an element contained in a {@link DRepresentation} or from a
     * {@link DRepresentation} itself.</br>
     * Browse the model upward (from the leaf to the root) to return the {@link DAnalysis}</br>
     * This method manages the fact that the DRepresentation is not owned by the DView using the session
     * crossReferencer.
     * 
     * @return the DAnalysis if found, null otherwise.
     */
    public DAnalysis getDAnalysis() {
        DAnalysis dAnalysis = null;
        Option<DRepresentation> representation = getRepresentation();
        if (representation.some()) {
            DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation.get()).getRepresentationDescriptor();
            EObject rootContainer = EcoreUtil.getRootContainer(representationDescriptor, false);
            if (rootContainer instanceof DAnalysis) {
                dAnalysis = (DAnalysis) rootContainer;
            }
        }
        return dAnalysis;
    }

    /**
     * Provides a mean to get SiriusReferenceFinder to some Sirius Elements (such as DRepresentationElement,
     * DRepresentation or DRepresentationDescriptor) from some semantic objects within the scope of the Sirius
     * {@link Session} the semanticObject belongs to.
     * 
     * @return the API
     */
    public SiriusReferenceFinder getSiriusReferenceFinder() {
        SiriusReferenceFinder inverseCrossRef = Optional.ofNullable(getSession()).filter(DAnalysisSessionImpl.class::isInstance).map(DAnalysisSessionImpl.class::cast)
                .map(DAnalysisSessionImpl::getSiriusReferenceFinder).orElse(null);
        return inverseCrossRef;
    }

    /**
     * Returns a generic description of an EObject.<br/>
     * This method will try to find a name, id and uid attributes and display their value if it exists.<br/>
     * Warning: This method is readOnly and must not lead to resource loading or proxy resolution.
     * 
     * @return the description on the form {eClass: <eClass>, name: <name>, id: <id>, uid: <uid>}
     */
    public String getGenericDecription() {
        StringBuilder sb = new StringBuilder();
        sb.append(BRACKET_OPEN).append(ECLASS).append(COLON).append(eObject.eClass().getEPackage().getName()).append(DOUBLE_COLON).append(eObject.eClass().getName());
        String name = getAttributeValue(NAME_ATTR);
        if (name != null) {
            sb.append(COMMA).append(NAME_ATTR).append(COLON).append(name);
        }
        String id = getAttributeValue(ID_ATTR);
        if (id != null) {
            sb.append(COMMA).append(ID_ATTR).append(COLON).append(id);
        }
        String uid = getAttributeValue(UID_ATTR);
        if (uid != null) {
            sb.append(COMMA).append(UID_ATTR).append(COLON).append(uid);
        }
        sb.append(BRACKET_CLOSE);
        return sb.toString();
    }

    /**
     * Tries to find an attribute which name contains attributeName among all EAttribute.
     * 
     * @return the attribute value if found otherwise null;
     */
    private String getAttributeValue(String attributeName) {
        String valueStr = null;
        EList<EAttribute> eAllAttributes = eObject.eClass().getEAllAttributes();
        Optional<EAttribute> optAttribute = eAllAttributes.stream().filter(att -> {
            return att.getName() != null && att.getName().contains(attributeName);
        }).findFirst();
        if (optAttribute.isPresent()) {
            try {
                Object value = eObject.eGet(optAttribute.get());
                if (value != null) {
                    valueStr = value.toString();
                }
            } catch (IllegalArgumentException e) {
            }
        }
        return valueStr;
    }
}
