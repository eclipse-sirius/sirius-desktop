/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.dialect;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.RepresentationNotification;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQueryProvider;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * Class able to manage a set of dialects to provides the usual dialect services using the Eclipse environment.
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

    @Override
    public Collection<RepresentationDescription> getAvailableRepresentationDescriptions(final Collection<Viewpoint> vp, final EObject semantic) {
        final Collection<RepresentationDescription> descs = new ArrayList<RepresentationDescription>();
        for (final Dialect dialect : dialects.values()) {
            descs.addAll(dialect.getServices().getAvailableRepresentationDescriptions(vp, semantic));
        }
        return descs;
    }

    @Override
    public void refresh(final DRepresentation representation, final IProgressMonitor monitor) {
        refresh(representation, false, monitor);
    }

    @Override
    public void refresh(DRepresentation representation, boolean doFullRefresh, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DialectManagerImpl_refreshMsg, 1);
            for (final Dialect dialect : dialects.values()) {
                if (dialect.getServices().canRefresh(representation)) {
                    dialect.getServices().refresh(representation, doFullRefresh, new SubProgressMonitor(monitor, 1));
                }
            }
        } finally {
            monitor.done();
        }
    }

    @Override
    public void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DialectManagerImpl_refreshImpactedMsg, 1);
            for (final Dialect dialect : dialects.values()) {
                if (dialect.getServices().canRefresh(representation)) {
                    dialect.getServices().refreshImpactedElements(representation, notifications, new SubProgressMonitor(monitor, 1));
                }
            }
        } finally {
            monitor.done();
        }
    }

    @Override
    public DRepresentation createRepresentation(final String name, final EObject semantic, final RepresentationDescription description, final Session session, final IProgressMonitor monitor) {
        DRepresentation newRepresentation = null;
        try {
            monitor.beginTask(MessageFormat.format(Messages.AbstractRepresentationDialectServices_createRepresentationMsg, name), 12);
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
                    newRepresentation = invokedDialect.getServices().createRepresentation(name, semantic, description, session, new SubProgressMonitor(monitor, 10));
                }
                if (newRepresentation != null) {
                    final RepresentationNotification notif = new RepresentationNotification(newRepresentation, RepresentationNotification.ADD);
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
        return newRepresentation;
    }

    @Override
    public DRepresentation copyRepresentation(final DRepresentationDescriptor representationDescriptor, final String name, final Session session, final IProgressMonitor monitor) {
        Dialect invokedDialect = null;
        DRepresentation copy = null;

        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canRefresh(representationDescriptor.getRepresentation())) {
                invokedDialect = dialect;
            }
        }
        if (invokedDialect != null) {

            copy = invokedDialect.getServices().copyRepresentation(representationDescriptor, name, session, monitor);
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

    @Override
    public boolean canRefresh(final DRepresentation representation) {
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canRefresh(representation)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canCreate(final EObject semantic, final RepresentationDescription desc) {
        return canCreate(semantic, desc, true);
    }

    @Override
    public boolean canCreate(EObject semantic, RepresentationDescription desc, boolean checkSelectedViewpoint) {
        boolean canCreate = false;

        // Ask the dialect if we can create a representation of the
        // given type on the semantic element
        for (final Dialect dialect : dialects.values()) {
            if (dialect.getServices().canCreate(semantic, desc, checkSelectedViewpoint)) {
                canCreate = true;
                break;
            }
        }

        return canCreate;
    }

    @Override
    public void disableDialect(final Dialect dialect) {
        dialects.remove(dialect.getName());
    }

    @Override
    public void enableDialect(final Dialect dialect) {
        dialects.put(dialect.getName(), dialect);
    }

    @Override
    public void notify(final RepresentationNotification representation) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().notify(representation);
        }

    }

    @Override
    public synchronized Collection<DRepresentation> getRepresentations(final EObject semantic, final Session session) {
        Collection<DRepresentation> reps = null;
        if (semantic != null) {
            reps = findAllRepresentations(semantic, session);
        } else {
            reps = getAllRepresentations(session);
        }
        return reps;
    }

    @Override
    public synchronized Collection<DRepresentation> getLoadedRepresentations(final EObject semantic, final Session session) {
        Collection<DRepresentation> reps = null;
        if (semantic != null) {
            reps = findAllRepresentationDescriptors(semantic, session).stream().filter(DRepresentationDescriptor::isLoadedRepresentation).map(DRepresentationDescriptor::getRepresentation)
                    .collect(Collectors.toList());
        } else {
            reps = getAllLoadedRepresentations(session);
        }
        return reps;
    }

    private Collection<DRepresentation> findAllRepresentations(EObject semantic, Session session) {
        Collection<DRepresentation> result = new ArrayList<>();
        ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
        for (EStructuralFeature.Setting setting : xref.getInverseReferences(semantic)) {
            if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(setting.getEObject())
                    && setting.getEStructuralFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET) {
                DRepresentation representation = ((DRepresentationDescriptor) setting.getEObject()).getRepresentation();
                if (representation != null) {
                    result.add(representation);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<DRepresentation> getRepresentations(final RepresentationDescription representationDescription, final Session session) {
        Collection<DRepresentation> reps = getRepresentationDescriptors(representationDescription, session).stream().map(repDesc -> repDesc.getRepresentation()).filter(Objects::nonNull)
                .collect(Collectors.toList());
        return reps;
    }

    @Override
    public Collection<DRepresentation> getAllRepresentations(final Session session) {
        Collection<DRepresentation> reps = getAllRepresentationDescriptors(session).stream().map(repDesc -> repDesc.getRepresentation()).filter(Objects::nonNull).collect(Collectors.toList());
        return reps;
    }

    @Override
    public Collection<DRepresentation> getAllLoadedRepresentations(final Session session) {
        Collection<DRepresentation> reps = getAllRepresentationDescriptors(session).stream().filter(DRepresentationDescriptor::isLoadedRepresentation).map(DRepresentationDescriptor::getRepresentation)
                .collect(Collectors.toList());
        return reps;
    }

    @Override
    public Collection<DRepresentationDescriptor> getRepresentationDescriptors(EObject semantic, Session session) {
        Collection<DRepresentationDescriptor> repDescriptors = null;
        if (semantic != null) {
            repDescriptors = findAllRepresentationDescriptors(semantic, session);
        } else {
            repDescriptors = getAllRepresentationDescriptors(session);
        }
        return repDescriptors;
    }

    private Collection<DRepresentationDescriptor> findAllRepresentationDescriptors(EObject semantic, Session session) {
        Collection<DRepresentationDescriptor> result = new ArrayList<>();
        ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
        for (EStructuralFeature.Setting setting : xref.getInverseReferences(semantic)) {
            if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(setting.getEObject())
                    && setting.getEStructuralFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET) {
                result.add((DRepresentationDescriptor) setting.getEObject());
            }
        }
        return result;
    }

    @Override
    public Collection<DRepresentationDescriptor> getAllRepresentationDescriptors(Session session) {
        List<DRepresentationDescriptor> foundRepDescs = new ArrayList<>();
        if (session instanceof DAnalysisSession) {
            foundRepDescs = ((DAnalysisSession) session).allAnalyses().stream().flatMap(a -> a.getOwnedViews().stream()).flatMap(v -> v.getOwnedRepresentationDescriptors().stream())
                    .collect(Collectors.toList());
        }
        return foundRepDescs;
    }

    @Override
    public Collection<DRepresentationDescriptor> getRepresentationDescriptors(RepresentationDescription representationDescription, Session session) {

        List<DRepresentationDescriptor> repDescriptors = getAllRepresentationDescriptors(session).stream().filter(repDesc -> {
            return EqualityHelper.areEquals(repDesc.getDescription(), representationDescription);
        }).collect(Collectors.toList());
        return repDescriptors;
    }

    @Override
    public boolean deleteRepresentation(final DRepresentationDescriptor representationDescriptor, final Session session) {
        Dialect deleter = null;
        final Iterator<Dialect> it = dialects.values().iterator();
        while (deleter == null && it.hasNext()) {
            final Dialect dialect = it.next();
            if (dialect.getServices().deleteRepresentation(representationDescriptor, session)) {
                deleter = dialect;
            }
        }
        if (deleter != null) {
            final RepresentationNotification notif = new RepresentationNotification(representationDescriptor.getRepresentation(), RepresentationNotification.REMOVE);
            for (final Dialect dialect : dialects.values()) {
                if (dialect != deleter) {
                    dialect.getServices().notify(notif);
                }
            }
        }
        return deleter != null;

    }

    @Override
    public RepresentationDescription getDescription(final DRepresentation representation) {
        RepresentationDescription result = null;
        final Iterator<Dialect> it = dialects.values().iterator();
        while (result == null && it.hasNext()) {
            result = it.next().getServices().getDescription(representation);
        }
        return result;
    }

    @Override
    public void initRepresentations(final Viewpoint vp, final EObject semantic, IProgressMonitor monitor) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().initRepresentations(vp, semantic, monitor);
        }
    }

    @Override
    public void updateRepresentationsExtendedBy(final Session session, final Viewpoint viewpoint, final boolean activated) {
        for (final Dialect dialect : dialects.values()) {
            dialect.getServices().updateRepresentationsExtendedBy(session, viewpoint, activated);
        }
    }

    @Override
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        IInterpretedExpressionQuery returnedQuery = null;
        // Ask registered providers first
        for (IInterpretedExpressionQueryProvider provider : SiriusPlugin.getDefault().getInterpretedExpressionQueryProviders()) {
            Option<IInterpretedExpressionQuery> answer = provider.getExpressionQueryFor(target, feature);
            if (answer.some()) {
                returnedQuery = answer.get();
                break;
            }
        }
        if (returnedQuery == null) {
            // Step 1 : we search for a Dialect compatible with
            // the given target
            Dialect dialect = getDialectFromEObjectAccordingToRepresentationDescription(target);
            if (dialect != null) {
                // Step 2 : we delegate the query creation to the found
                // DialectDescription
                returnedQuery = dialect.getServices().createInterpretedExpressionQuery(target, feature);
            } else if (new EObjectQuery(target).getFirstAncestorOfType(DescriptionPackage.Literals.EXTENSION).some()) {
                // We are not inside a dialect, but inside an extension
            }
        }
        if (returnedQuery != null) {
            return returnedQuery;
        }
        // If no query can be built, we return a default query that will
        // avoid NPE
        return new DefaultInterpretedExpressionQuery(target, feature);
    }

    @Override
    public boolean handles(RepresentationDescription representationDescription) {
        for (Dialect dialect : dialects.values()) {
            if (dialect.getServices().handles(representationDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the {@link Dialect} managing the given target, according to its {@link RepresentationDescription}.
     * 
     * @param target
     *            the target to get the {@link Dialect} from
     * @return the first registered {@link Dialect} that is compatible with the {@link RepresentationDescription}
     *         containing the given target, null if none found
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

    @Override
    public boolean handles(RepresentationExtensionDescription representationExtensionDescription) {
        for (Dialect dialect : dialects.values()) {
            if (dialect.getServices().handles(representationExtensionDescription)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void invalidateMappingCache() {
        for (Dialect dialect : dialects.values()) {
            dialect.getServices().invalidateMappingCache();
        }
    }

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

    @Override
    public boolean allowsEStructuralFeatureCustomization(EObject element) {
        boolean customizationAllowed = false;
        Iterator<Dialect> dialectsValueIterator = dialects.values().iterator();
        while (dialectsValueIterator.hasNext() && !customizationAllowed) {
            customizationAllowed = dialectsValueIterator.next().getServices().allowsEStructuralFeatureCustomization(element);
        }
        return customizationAllowed;
    }

    @Override
    public Set<Viewpoint> getRequiredViewpoints(DRepresentation representation) {
        Set<Viewpoint> requiredViewpoints = null;
        for (Dialect dialect : dialects.values()) {
            if (dialect.getServices().handles(getDescription(representation))) {
                requiredViewpoints = dialect.getServices().getRequiredViewpoints(representation);
                break;
            }
        }
        return requiredViewpoints;
    }

}
