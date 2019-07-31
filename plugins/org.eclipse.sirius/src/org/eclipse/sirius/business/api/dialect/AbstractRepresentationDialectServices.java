/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.dialect;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Basic class to extend for each dialect services implementation.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractRepresentationDialectServices implements DialectServices {

    /**
     * All standard references to find {@link org.eclipse.sirius.viewpoint.DRepresentationElement} from corresponding
     * semantic elements by cross reference.
     */
    protected static final Set<EReference> REPRESENTATION_ELEMENTS_INVERSE_REFERENCES = new HashSet<>();

    static {
        REPRESENTATION_ELEMENTS_INVERSE_REFERENCES.add(ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target());
        REPRESENTATION_ELEMENTS_INVERSE_REFERENCES.add(ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements());
    }

    /**
     * Checks whether a specific representation is supported by this dialect, i.e. it can delete it, copy it, refresh
     * it, etc.
     * 
     * @param representation
     *            the representation.
     * @return <code>true</code> if this dialect supports the representation.
     */
    protected abstract boolean isSupported(DRepresentation representation);

    /**
     * Checks whether a specific representation is supported by this dialect, i.e. it can delete it, copy it, refresh
     * it, etc.
     * 
     * @param representationDescriptor
     *            the descriptor of the representation.
     * @return <code>true</code> if this dialect supports the representation.
     */
    protected abstract boolean isSupported(DRepresentationDescriptor representationDescriptor);

    /**
     * Checks whether a specific representation description is supported by this dialect, i.e. it can create concrete
     * representations from it, and support the result.
     * 
     * @param description
     *            the representation description.
     * @return <code>true</code> if this dialect supports the representation description.
     */
    protected abstract boolean isSupported(RepresentationDescription description);

    @Override
    public void notify(RepresentationNotification notification) {
        // Empty default implementation.
    }

    @Override
    public void updateRepresentationsExtendedBy(Session session, Viewpoint viewpoint, boolean activated) {
        // No support for representation extension by default.
    }

    @Override
    public void refresh(DRepresentation representation, IProgressMonitor monitor) {
        refresh(representation, false, monitor);
    }

    @Override
    public void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor) {
        // Do nothing by default, not all dialects have capability to refresh
        // only impacted elements.
    }

    @Override
    public boolean canRefresh(DRepresentation representation) {
        return isSupported(representation) && areRequiredViewpointsSelected(representation);
    }

    private boolean areRequiredViewpointsSelected(DRepresentation representation) {
        boolean areRequiredViewpointsSelected = false;
        if (representation != null) {
            Collection<Viewpoint> requiredViewpoints = getRequiredViewpoints(representation);
            if (!requiredViewpoints.isEmpty()) {
                Session session = new EObjectQuery(representation).getSession();
                if (session == null) {
                    // To manage case representation is in creation then not yet
                    // added to session, for example on viewpoint first
                    // selection
                    EObject target = ((DSemanticDecorator) representation).getTarget();
                    session = new EObjectQuery(target).getSession();
                }
                if (session != null) {
                    areRequiredViewpointsSelected = session.getSelectedViewpoints(false).containsAll(requiredViewpoints);
                }
            }
        }
        return areRequiredViewpointsSelected;
    }

    /**
     * Tell if the {@link Viewpoint} owner of the {@link RepresentationDescription} is enabled on {@link Session}.
     * 
     * @param session
     *            the {@link Session}
     * @param representationDescription
     *            a {@link RepresentationDescription} in the context of the {@link Session}
     * @return true if the {@link Viewpoint} owner of the {@link RepresentationDescription} is enabled on
     *         {@link Session}
     */
    protected boolean isRelatedViewpointSelected(Session session, RepresentationDescription representationDescription) {
        boolean isRelatedViewpointSelected = false;
        Viewpoint parentViewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
        isRelatedViewpointSelected = session != null && parentViewpoint != null && session.getSelectedViewpoints(false).contains(parentViewpoint);
        return isRelatedViewpointSelected;
    }

    @Override
    public Set<Viewpoint> getRequiredViewpoints(DRepresentation representation) {
        Set<Viewpoint> requiredViewpoints = new LinkedHashSet<Viewpoint>();
        RepresentationDescription representationDescription = getDescription(representation);
        if (representationDescription != null) {
            Viewpoint parentViewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
            if (parentViewpoint != null) {
                requiredViewpoints.add(parentViewpoint);
            }
        }
        return requiredViewpoints;
    }

    @Override
    public DRepresentation copyRepresentation(final DRepresentationDescriptor representationDescriptor, final String name, final Session session, final IProgressMonitor monitor) {
        // Copy the representation and get new uid values for copies.
        DRepresentation newRepresentation = SiriusCopierHelper.copyWithNoUidDuplication(representationDescriptor.getRepresentation());

        /* Set the correct name */
        representationDescriptor.setName(name);
        return newRepresentation;
    }

    @Override
    public boolean deleteRepresentation(DRepresentationDescriptor representationDescriptor, Session session) {
        if (isSupported(representationDescriptor)) {
            DRepresentation representation = representationDescriptor.getRepresentation();
            Optional<Resource> resOpt = Optional.ofNullable(representation).map(EObject::eResource);
            EcoreUtil.remove(representationDescriptor);
            if (representation != null) {
                SiriusUtil.delete(representation, session);
            }

            // delete the resource if it is empty
            resOpt.filter(res -> res.getContents().isEmpty()).ifPresent(res -> {
                try {
                    res.delete(Collections.emptyMap());
                } catch (IOException e) {
                    SiriusPlugin.getDefault().error(Messages.SiriusUncontrolCommand_resourceDeletionFailedMsg, e);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, Session session, IProgressMonitor monitor) {
        DRepresentation representation = null;
        try {
            monitor.beginTask(MessageFormat.format(Messages.AbstractRepresentationDialectServices_createRepresentationMsg, name), 2);
            representation = createRepresentation(name, semantic, description, new SubProgressMonitor(monitor, 1));
            if (representation != null) {
                DRepresentationDescriptorInternalHelper.createDRepresentationDescriptor(representation, (DAnalysisSessionImpl) session, semantic.eResource(), name, ""); //$NON-NLS-1$
            }
            monitor.worked(1);
        } finally {
            monitor.done();
        }
        return representation;
    }

    /**
     * Create a new representation using the representation description. As no session is passed to this method the
     * created representation will not be kept.
     * 
     * @param name
     *            name of the representation to create.
     * 
     * @param semantic
     *            semantic root used to create the representation.
     * @param description
     *            representation description to use.
     * @param monitor
     *            to track progress.
     * @return the new representation .
     */
    protected abstract DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, IProgressMonitor monitor);

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<RepresentationDescription> getAvailableRepresentationDescriptions(Collection<Viewpoint> vps, EObject semantic) {
        final Collection<RepresentationDescription> result = new ArrayList<>();
        for (Viewpoint vp : vps) {
            Iterables.addAll(result, getAvailableRepresentationDescriptions(vp, semantic));
        }
        return result;
    }

    /**
     * Return all RepresentationDescription available in the specified viewpoint the user might use to create a new
     * DRepresentation.
     * 
     * @param vp
     *            the viewpoint to look into for candidate {@link RepresentationDescription}.
     * @param semantic
     *            the semantic element on which the user wants to create a representation.
     * @return the {@link RepresentationDescription}s defined in the specified viewpoint which can apply to the semantic
     *         element.
     */
    protected Iterable<RepresentationDescription> getAvailableRepresentationDescriptions(Viewpoint vp, final EObject semantic) {
        Iterable<RepresentationDescription> candidates = new ViewpointQuery(vp).getAllRepresentationDescriptions();
        return Iterables.filter(candidates, new Predicate<RepresentationDescription>() {
            @Override
            public boolean apply(RepresentationDescription input) {
                return canCreate(semantic, input);
            }
        });
    }

    /**
     * Checks that the specified semantic element matches the precondition.
     * 
     * @param semantic
     *            the evaluation context
     * @param condition
     *            the precondition to evaluate, may be <code>null</code> or empty.
     * @return <code>true</code> if the semantic element matches the precondition or there is no precondition,
     *         <code>false</code> otherwise.
     */
    protected boolean checkPrecondition(EObject semantic, String condition) {
        boolean canCreate;
        if (StringUtil.isEmpty(condition)) {
            // An empty pre-condition means we can always create a
            // representation.
            canCreate = true;
        } else {
            try {
                IInterpreter interpreter = InterpreterUtil.getInterpreter(semantic);
                canCreate = interpreter.evaluateBoolean(semantic, condition);
            } catch (EvaluationException e) {
                canCreate = false;
            }
        }
        return canCreate;
    }

    /**
     * Checks that the semantic element is compatible with the specified domain class.
     * 
     * @param accessor
     *            the model accessor to use.
     * @param semantic
     *            the semantic element to check.
     * @param domainClass
     *            the name (simple or qualified) of the domain class to check.
     * @return <code>true</code> if the semantic element is considered an instance of of the designated domain class by
     *         the accessor.
     */
    protected boolean checkDomainClass(ModelAccessor accessor, EObject semantic, String domainClass) {
        return !StringUtil.isEmpty(domainClass) && accessor.eInstanceOf(semantic, domainClass);
    }

    /**
     * Checks that we can create new elements inside the specified semantic element.
     * <p>
     * When a representation is created we execute an initial operation if specified in the VSM. This operation is
     * typically used to initialize the semantic model, so here we also make sure than "filling" the semantic model is
     * authorized.
     * 
     * @param accessor
     *            the model accessor to use for the checks.
     * @param semantic
     *            the semantic element to check.
     * @return <code>true</code> if we can create new elements inside the specified element.
     */
    protected boolean checkSemanticElementCanBeFilled(ModelAccessor accessor, EObject semantic) {
        return accessor.getPermissionAuthority().canCreateIn(semantic);
    }

    /**
     * Create a new {@link Representation} for the specified semantic element and all its children for all
     * {@link RepresentationDescription} of the specified type of the specified {@link Viewpoint}.
     * 
     * @param semantic
     *            the specified semantic element
     * @param vp
     *            the specified {@link Viewpoint}
     * @param representationDescriptionType
     *            the specified {@link RepresentationDescription} type
     */
    protected void initRepresentations(EObject semantic, Viewpoint vp, Class<? extends RepresentationDescription> representationDescriptionType) {
        initRepresentations(semantic, vp, representationDescriptionType, new NullProgressMonitor());
    }

    /**
     * Create a new {@link Representation} for the specified semantic element and all its children for all
     * {@link RepresentationDescription} of the specified type of the specified {@link Viewpoint}.
     * 
     * @param semantic
     *            the specified semantic element
     * @param vp
     *            the specified {@link Viewpoint}
     * @param representationDescriptionType
     *            the specified {@link RepresentationDescription} type
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of representations initialization
     */
    protected void initRepresentations(EObject semantic, Viewpoint vp, Class<? extends RepresentationDescription> representationDescriptionType, IProgressMonitor monitor) {
        Collection<? extends RepresentationDescription> descriptions = collectRepresentationDescriptions(vp, representationDescriptionType);
        initRepresentations(descriptions, semantic, monitor);
    }

    /**
     * Returns all the {@link RepresentationDescription} contained in the given viewpoint of the given Representation
     * Description type (e.g. DiagramDescription.class).
     * 
     * @param <T>
     *            the expected representation type (e.g. DiagramDescription)
     * @param viewpoint
     *            the viewpoint in which representation descriptions should be collected
     * @param expectedRepresentationDescriptionType
     *            the expected representation type (e.g. DiagramDescription.class)
     * @return all the {@link RepresentationDescription} contained in the given viewpoint of the given Representation
     *         Description type (e.g. DiagramDescription.class)
     */
    private <T extends RepresentationDescription> Collection<T> collectRepresentationDescriptions(final Viewpoint viewpoint, Class<T> expectedRepresentationDescriptionType) {
        final Collection<T> descriptions = new ArrayList<T>();
        for (final RepresentationDescription representationDescription : new ViewpointQuery(viewpoint).getAllRepresentationDescriptions()) {
            if (expectedRepresentationDescriptionType.isAssignableFrom(representationDescription.getClass())) {
                if (representationDescription.isInitialisation()) {
                    descriptions.add((T) representationDescription);
                }
            }
        }
        return descriptions;
    }

    /**
     * Inits all tables with the specified table descriptions.
     * 
     * @param descriptions
     *            the descriptions
     * @param rootSemanticElement
     *            the root model element.
     * @param view
     *            the view.
     */
    private void initRepresentations(final Collection<? extends RepresentationDescription> descriptions, final EObject rootSemanticElement, IProgressMonitor monitor) {
        if (descriptions.isEmpty()) {
            return;
        }
        try {
            monitor.beginTask(Messages.AbstractRepresentationDialectServices_initRepresentationMsg, descriptions.size());
            for (final RepresentationDescription desc : descriptions) {
                initRepresentationForElement(desc, rootSemanticElement, monitor);
            }
            final Iterator<EObject> iterElements = rootSemanticElement.eAllContents();
            while (iterElements.hasNext()) {
                final EObject currentSemanticElement = iterElements.next();
                for (final RepresentationDescription desc : descriptions) {
                    initRepresentationForElement(desc, currentSemanticElement, monitor);
                }
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Initialize a new {@link Representation} from a specified {@link RepresentationDescription} on a specified
     * semantic element.
     * 
     * @param representationDescription
     *            the specified {@link RepresentationDescription}
     * @param semanticElement
     *            the specified semantic element
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of representations initialization
     * @param <T>
     *            the real sub type of {@link RepresentationDescription}
     */
    protected abstract <T extends RepresentationDescription> void initRepresentationForElement(T representationDescription, EObject semanticElement, IProgressMonitor monitor);

    /**
     * Indicates whether a representation should be initialized on the given semantic element, according to the given
     * {@link RepresentationDescription}.
     * 
     * @param semanticElement
     *            the semantic element on which representations should be initialized
     * @param description
     *            the description of the candidate representation for an initialization
     * @param domainClass
     *            the expected domain class for this {@link RepresentationDescription}
     * @return true if a representation should be initialized on the given semantic element, false if :
     *         <ul>
     *         <li>the {@link RepresentationDescription}'s Domain Class does not match the given semantic element's
     *         type</li>
     *         <li>the {@link RepresentationDescription} does not require initialization (see
     *         {@link RepresentationDescription#isInitialisation()})</li>
     *         <li>a representation with the {@link RepresentationDescription} name and on the same semantic element
     *         already exists</li>
     *         </ul>
     */
    protected boolean shouldInitializeRepresentation(final EObject semanticElement, RepresentationDescription description, String domainClass) {
        boolean shouldInitializeRepresentation = !StringUtil.isEmpty(domainClass) && description.isInitialisation();
        return shouldInitializeRepresentation;
    }

    @Override
    public void invalidateMappingCache() {
        // No cache to invalidate by default
    }

    @Override
    public Option<? extends AbstractCommandTask> createTask(CommandContext context, ModelAccessor extPackage, ModelOperation op, Session session, UICallBack uiCallback) {
        return Options.newNone();
    }

    @Override
    public boolean allowsEStructuralFeatureCustomization(EObject choice) {
        // Do not support structural feature customization by default
        return false;
    }
}
