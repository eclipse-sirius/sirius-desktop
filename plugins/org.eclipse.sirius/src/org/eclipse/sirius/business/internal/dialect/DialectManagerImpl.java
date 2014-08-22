/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.dialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.RepresentationNotification;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.identifier.RepresentationElementIdentifier;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

import com.google.common.collect.Lists;

/**
 * Class able to manage a set of dialects to provides the usual dialect services
 * using the Eclipse environment.
 * 
 * @author cbrun
 * 
 */
public class DialectManagerImpl implements DialectManager {

    Map<String, Dialect> dialects = new HashMap<String, Dialect>();

    /**
     * Init a default manager implementation.
     * 
     * @return a default manager implementation
     */
    public static DialectManager init() {
        final DialectManagerImpl manager = new DialectManagerImpl();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<Dialect> parsedDialects = EclipseUtil.getExtensionPlugins(Dialect.class, DialectManager.ID, DialectManager.CLASS_ATTRIBUTE);
            for (final Dialect dialect : parsedDialects) {
                manager.enableDialect(dialect);
            }
        }
        return manager;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Collection<RepresentationDescription> getAvailableRepresentationDescriptions(final Collection<Viewpoint> vp, final EObject semantic) {
        final Collection<RepresentationDescription> descs = new ArrayList<RepresentationDescription>();
        for (final Dialect dialect : dialects.values()) {
            descs.addAll(dialect.getServices().getAvailableRepresentationDescriptions(vp, semantic));
        }
        return descs;
    }

    /**
     * {@inheritDoc}
     */
    public void refreshEffectiveRepresentationDescription(DRepresentation representation, IProgressMonitor monitor) {
        if (Movida.isEnabled()) {
            for (Dialect dialect : dialects.values()) {
                dialect.getServices().refreshEffectiveRepresentationDescription(representation, monitor);
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void refresh(final DRepresentation representation, final IProgressMonitor monitor) {
        try {
            monitor.beginTask("Refresh representation", 1);
            for (final Dialect dialect : dialects.values()) {
                if (dialect.getServices().canRefresh(representation)) {
                    dialect.getServices().refresh(representation, new SubProgressMonitor(monitor, 1));
                }
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DRepresentation createRepresentation(final String name, final EObject semantic, final RepresentationDescription description, final IProgressMonitor monitor) {
        return createRepresentation(name, semantic, description, null, monitor);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public DRepresentation createRepresentation(final String name, final EObject semantic, final RepresentationDescription description, final Session session, final IProgressMonitor monitor) {
        DRepresentation created = null;
        try {
            monitor.beginTask("Create representation : " + name, 12);
            Dialect invokedDialect = null;

            for (final Dialect dialect : dialects.values()) {
                if (dialect.getServices().canCreate(semantic, description)) {
                    invokedDialect = dialect;
                    break;
                }
            }
            monitor.worked(1);
            if (invokedDialect != null) {
                if (session != null) {
                    created = invokedDialect.getServices().createRepresentation(name, semantic, description, session, new SubProgressMonitor(monitor, 10));
                }
                if (created != null) {
                    final RepresentationNotification notif = new RepresentationNotification(created, RepresentationNotification.ADD);
                    for (final Dialect dialect : dialects.values()) {
                        if (dialect != invokedDialect) {
                            dialect.getServices().notify(notif);
                        }
                    }
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }
        return created;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#copyRepresentation(org.eclipse.sirius.viewpoint.DRepresentation,
     *      java.lang.String, org.eclipse.sirius.business.api.session.Session,
     *      org.eclipse.core.runtime.IProgressMonitor)
     */
    public DRepresentation copyRepresentation(final DRepresentation representation, final String name, final Session session, final IProgressMonitor monitor) {
        Dialect invokedDialect = null;
        DRepresentation copy = null;

        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canRefresh(representation)) {
                invokedDialect = dialect;
            }
        }
        if (invokedDialect != null) {

            copy = invokedDialect.getServices().copyRepresentation(representation, name, session, monitor);
            if (copy != null) {
                final RepresentationNotification notif = new RepresentationNotification(copy, RepresentationNotification.ADD);
                for (final Dialect dialect : dialects.values()) {
                    if (dialect != invokedDialect) {
                        dialect.getServices().notify(notif);
                    }
                }
            }
        }
        return copy;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canRefresh(final DRepresentation representation) {
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canRefresh(representation)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCreate(final EObject semantic, final RepresentationDescription desc) {
        boolean canCreate = false;

        // Ask the dialect if we can create a representation of the
        // given type on the semantic element
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canCreate(semantic, desc)) {
                canCreate = true;
                break;
            }
        }

        return canCreate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectManager#disableDialect(org.eclipse.sirius.business.api.dialect.Dialect)
     */
    public void disableDialect(final Dialect dialect) {
        dialects.remove(dialect.getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectManager#enableDialect(org.eclipse.sirius.business.api.dialect.Dialect)
     */
    public void enableDialect(final Dialect dialect) {
        dialects.put(dialect.getName(), dialect);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#notify(org.eclipse.sirius.business.api.dialect.RepresentationNotification)
     */
    public void notify(final RepresentationNotification representation) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().notify(representation);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#getRepresentations(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    public synchronized Collection<DRepresentation> getRepresentations(final EObject semantic, final Session session) {
        if (semantic != null) {
            return findAllRepresentations(semantic, session);
        } else {
            final Collection<DRepresentation> reps = new ArrayList<DRepresentation>();
            for (final Dialect dialect : dialects.values()) {
                reps.addAll(dialect.getServices().getRepresentations(semantic, session));
            }
            return reps;
        }
    }
    
    private Collection<DRepresentation> findAllRepresentations(EObject semantic, Session session) {
        Collection<DRepresentation> result = Lists.newArrayList();
        ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
        for (EStructuralFeature.Setting setting : xref.getInverseReferences(semantic)) {
            if (ViewpointPackage.Literals.DREPRESENTATION.isInstance(setting.getEObject()) && setting.getEStructuralFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET) {
                result.add((DRepresentation) setting.getEObject());
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#getRepresentations(org.eclipse.sirius.viewpoint.description.RepresentationDescription,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    public Collection<DRepresentation> getRepresentations(final RepresentationDescription representationDescription, final Session session) {
        final Collection<DRepresentation> reps = new ArrayList<DRepresentation>();
        for (final Dialect dialect : dialects.values()) {
            reps.addAll(dialect.getServices().getRepresentations(representationDescription, session));
        }
        return reps;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#getAllRepresentations(org.eclipse.sirius.business.api.session.Session)
     */
    public Collection<DRepresentation> getAllRepresentations(final Session session) {
        final Collection<DRepresentation> reps = new ArrayList<DRepresentation>();
        for (final Dialect dialect : dialects.values()) {
            reps.addAll(dialect.getServices().getAllRepresentations(session));
        }
        return reps;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#deleteRepresentation(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    public boolean deleteRepresentation(final DRepresentation representation, final Session session) {
        Dialect deleter = null;
        final Iterator<Dialect> it = dialects.values().iterator();
        while (deleter == null && it.hasNext()) {
            final Dialect dialect = it.next();
            if (dialect.getServices().deleteRepresentation(representation, session)) {
                deleter = dialect;
            }
        }
        if (deleter != null) {
            final RepresentationNotification notif = new RepresentationNotification(representation, RepresentationNotification.REMOVE);
            for (final Dialect dialect : dialects.values()) {
                if (dialect != deleter) {
                    dialect.getServices().notify(notif);
                }
            }
        }
        return deleter != null;

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#getDescription(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public RepresentationDescription getDescription(final DRepresentation representation) {
        RepresentationDescription result = null;
        final Iterator<Dialect> it = dialects.values().iterator();
        while (result == null && it.hasNext()) {
            result = it.next().getServices().getDescription(representation);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void initRepresentations(Viewpoint vp, EObject semantic) {
        initRepresentations(vp, semantic, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void initRepresentations(final Viewpoint vp, final EObject semantic, IProgressMonitor monitor) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().initRepresentations(vp, semantic, monitor);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#canCreateIdentifier(org.eclipse.emf.ecore.EObject)
     */
    public boolean canCreateIdentifier(final EObject representationElement) {
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canCreateIdentifier(representationElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#createIdentifier(org.eclipse.emf.ecore.EObject,
     *      java.util.Map)
     */
    public RepresentationElementIdentifier createIdentifier(final EObject representationElement, final Map<EObject, RepresentationElementIdentifier> elementToIdentifier) {
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canCreateIdentifier(representationElement)) {
                return dialect.getServices().createIdentifier(representationElement, elementToIdentifier);
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#updateRepresentationsExtendedBy(Session,
     *      Viewpoint, boolean)
     */
    public void updateRepresentationsExtendedBy(final Session session, final Viewpoint viewpoint, final boolean activated) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().updateRepresentationsExtendedBy(session, viewpoint, activated);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#createInterpretedExpressionQuery(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        IInterpretedExpressionQuery returnedQuery = null;

        // Step 1 : we search for a Dialect compatible with
        // the given target
        Dialect dialect = getDialectFromEObjectAccordingToRepresentationDescription(target);
        if (dialect != null) {
            // Step 2 : we delegate the query creation to the found
            // DialectDescription
            returnedQuery = dialect.getServices().createInterpretedExpressionQuery(target, feature);
        }
        if (returnedQuery != null) {
            return returnedQuery;
        }
        // If no query can be built, we return a default query that will
        // avoid NPE
        return new DefaultInterpretedExpressionQuery(target, feature);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#handles(org.eclipse.sirius.viewpoint.description.RepresentationDescription)
     */
    public boolean handles(RepresentationDescription representationDescription) {
        for (Dialect dialect : dialects.values()) {
            if (dialect.getServices().handles(representationDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the {@link Dialect} managing the given target, according to its
     * {@link RepresentationDescription}.
     * 
     * @param target
     *            the target to get the {@link Dialect} from
     * @return the first registered {@link Dialect} that is compatible with the
     *         {@link RepresentationDescription} containing the given target,
     *         null if none found
     */
    private Dialect getDialectFromEObjectAccordingToRepresentationDescription(EObject target) {
        Dialect foundDialect = null;
        // Step 1 : getting the representation description containing this
        // element
        EObject container = target;
        while (container != null && !(container instanceof RepresentationDescription || container instanceof RepresentationExtensionDescription)) {
            container = container.eContainer();
        }

        if (container != null) {
            // Step 2 : returning the first Dialect that is
            // compatible with this representation description or this
            // representation extension description

            if (container instanceof RepresentationDescription) {
                for (Dialect candidateDialect : dialects.values()) {
                    if (candidateDialect.getServices().handles((RepresentationDescription) container)) {
                        foundDialect = candidateDialect;
                        break;
                    }
                }
            } else if (container instanceof RepresentationExtensionDescription) {
                for (Dialect candidateDialect : dialects.values()) {
                    if (candidateDialect.getServices().handles((RepresentationExtensionDescription) container)) {
                        foundDialect = candidateDialect;
                        break;
                    }
                }
            }

        }
        return foundDialect;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#handles(org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription)
     */
    public boolean handles(RepresentationExtensionDescription representationExtensionDescription) {
        for (Dialect dialect : dialects.values()) {
            if (dialect.getServices().handles(representationExtensionDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#invalidateMappingCache()
     */
    public void invalidateMappingCache() {
        for (Dialect dialect : dialects.values()) {
            dialect.getServices().invalidateMappingCache();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<? extends AbstractCommandTask> createTask(CommandContext context, ModelAccessor extPackage, ModelOperation op, Session session, UICallBack uiCallback) {
        Option<? extends AbstractCommandTask> task = Options.newNone();
        Iterator<Dialect> iterDialect = dialects.values().iterator();
        while (iterDialect.hasNext() && !task.some()) {
            Dialect dialect = iterDialect.next();
            task = dialect.getServices().createTask(context, extPackage, op, session, uiCallback);
        }
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allowsEStructuralFeatureCustomization(EObject element) {
        boolean customizationAllowed = false;
        Iterator<Dialect> dialectsValueIterator = dialects.values().iterator();
        while (dialectsValueIterator.hasNext() && !customizationAllowed) {
            customizationAllowed = dialectsValueIterator.next().getServices().allowsEStructuralFeatureCustomization(element);
        }
        return customizationAllowed;
    }
}
