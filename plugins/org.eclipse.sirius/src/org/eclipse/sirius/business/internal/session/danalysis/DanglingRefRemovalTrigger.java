/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.emf.EReferencePredicate;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * A {@link ModelChangeTrigger} which remove all the potential dangling
 * references when an EObject is deleted using the Sirius logic (checking for
 * authorizations, "special" references and so on).
 * 
 * @author cbrun
 * 
 */
public class DanglingRefRemovalTrigger implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int DANGLING_REFERENCE_REMOVAL_PRIORITY = 0;

    /**
     * Filter {@link Notification}s which are not a detachment. A detachment is
     * : an EObject being removed from the reference it is contained in.
     */
    public static final Predicate<Notification> IS_DETACHMENT = new Predicate<Notification>() {

        /**
         * {@inheritDoc}
         */
        public boolean apply(Notification input) {
            boolean potentialExplicitDetachment = input.getEventType() == Notification.REMOVE || input.getEventType() == Notification.REMOVE_MANY || input.getEventType() == Notification.UNSET;
            boolean potentialImplicitDetachment = input.getEventType() == Notification.SET && input.getNewValue() == null;
            if (potentialExplicitDetachment || potentialImplicitDetachment) {
                return input.getFeature() instanceof EReference && ((EReference) input.getFeature()).isContainment();
            }
            return false;
        }

    };

    /**
     * Filter {@link Notification}s which are not an attachment. An attachment
     * is : an EObject being removed from the reference it is contained in.
     */
    public static final Predicate<Notification> IS_ATTACHMENT = new Predicate<Notification>() {

        /**
         * {@inheritDoc}
         */
        public boolean apply(Notification input) {
            if (input.getEventType() == Notification.ADD || input.getEventType() == Notification.ADD_MANY || input.getEventType() == Notification.SET) {
                // The input.getNewValue() check required to make IS_ATTACHMENT
                // and IS_DETACHMENT mutually exclusive.
                return input.getFeature() instanceof EReference && ((EReference) input.getFeature()).isContainment() && input.getNewValue() != null;
            }
            return false;
        }

    };

    /**
     * A predicate to ignore DSemanticDecorator references in the dangling
     * references deletion. Allows to avoid changes in non opened diagrams and
     * the corresponding abusive locks.
     */
    public static final EReferencePredicate DSEMANTICDECORATOR_REFERENCE_TO_IGNORE_PREDICATE = new EReferencePredicate() {

        /**
         * {@inheritDoc}
         */
        public boolean apply(EReference reference) {
            // We should ignore any EReference which container is a
            // DSemanticDecorator (or any subclass)
            return ViewpointPackage.Literals.DSEMANTIC_DECORATOR.isSuperTypeOf(reference.getEContainingClass());
        }
    };

    /**
     * A predicate to ignore org.eclipse.gmf.runtime.notation.View#element
     * references in the dangling references deletion.
     */
    public static final EReferencePredicate NOTATION_VIEW_ELEMENT_REFERENCE_TO_IGNORE_PREDICATE = new EReferencePredicate() {

        /**
         * {@inheritDoc}
         */
        public boolean apply(EReference eReference) {
            // We should ignore View.element reference because not doing
            // that have the effect to have the EditParts having
            // "semantic element" the DSemanticDiagram because
            // View.getElement() if View.element == null returns the
            // View.element of the parent
            // It is not a problem to leave a dangling reference on
            // View.element because the canonical refresh in precommit will
            // remove these Views referencing a deleted DDiagramElement

            // ignoring the View.element reference
            return NOTATION_VIEW_ELEMENT_REFERENCE_TO_IGNORE.equals(eReference.getName()) && NOTATION_VIEW_ELEMENT_REFERENCE_CONTAINER_TO_IGNORE.equals(eReference.getContainerClass().getName());
        }
    };

    /**
     * A predicate to ignore EPackage#eFactoryInstance references in the
     * dangling references deletion.
     */
    public static final Predicate<EReference> EPACKAGE_EFACTORYINSTANCE_REFERENCE_TO_IGNORE_PREDICATE = new Predicate<EReference>() {

        /**
         * {@inheritDoc}
         */
        public boolean apply(EReference eReference) {
            // ignoring the EPackage.eFactoryInstance reference
            return EcorePackage.eINSTANCE.getEPackage_EFactoryInstance().equals(eReference);
        }
    };

    /**
     * Name of the GMF View feature to ignore in the remove dangling references.
     */
    private static final String NOTATION_VIEW_ELEMENT_REFERENCE_TO_IGNORE = "element";

    /**
     * Container name of the GMF View feature to ignore in the remove dangling
     * references.
     */
    private static final String NOTATION_VIEW_ELEMENT_REFERENCE_CONTAINER_TO_IGNORE = "org.eclipse.gmf.runtime.notation.View";

    private TransactionalEditingDomain domain;

    private ModelAccessor accessor;

    private ECrossReferenceAdapter xRef;

    /**
     * Create a new instance which can be associated with a SessionEventBroker
     * to automatically remove the dangling references.
     * 
     * @param domain
     *            the current editing domain, used to build the command which
     *            will remove the dangling references.
     * @param accessor
     *            the model accessor used to actually remove the cross
     *            references.
     * @param xRef
     *            the cross-referencer to use to find the cross references.
     */
    public DanglingRefRemovalTrigger(TransactionalEditingDomain domain, ModelAccessor accessor, ECrossReferenceAdapter xRef) {
        this.domain = domain;
        this.accessor = accessor;
        this.xRef = xRef;
    }

    /**
     * {@inheritDoc}
     */
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        final Set<EObject> allDetachedObjects = getChangedEObjectsAndChildren(Iterables.filter(notifications, IS_DETACHMENT), null);
        if (allDetachedObjects.size() > 0) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);

            // Predicate to ignore attachments to detached elements.
            Predicate<EObject> ignoreNotifierInDetachedObjects = Predicates.in(allDetachedObjects);
            final Set<EObject> allAttachedObjects = getChangedEObjectsAndChildren(Iterables.filter(notifications, IS_ATTACHMENT), ignoreNotifierInDetachedObjects);
            final Set<EObject> toRemoveXRefFrom = Sets.difference(allDetachedObjects, allAttachedObjects);
            if (toRemoveXRefFrom.size() > 0) {
                EReferencePredicate refToIgnore = new EReferencePredicate() {
                    @Override
                    public boolean apply(EReference ref) {
                        return DSEMANTICDECORATOR_REFERENCE_TO_IGNORE_PREDICATE.apply(ref) || NOTATION_VIEW_ELEMENT_REFERENCE_TO_IGNORE_PREDICATE.apply(ref) || EPACKAGE_EFACTORYINSTANCE_REFERENCE_TO_IGNORE_PREDICATE.apply(ref);
                    }
                };
                        
                Command removeDangling = new RemoveDanglingReferencesCommand(domain, accessor, xRef, toRemoveXRefFrom, refToIgnore);
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);
                return Options.newSome(removeDangling);
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);
        }
        return Options.newNone();
    }

    /**
     * Return the EObjects which have been changed by the given notifications
     * and their childrens.
     * 
     * @param notifications
     *            notifications to process.
     * @param notifierToIgnore
     *            a predicate indicating if a given notification should be
     *            ignored regarding its EObject notifier or not (can be null if
     *            all notifications should be considered)
     * @return the EObjects which have been changed by the given notifications
     *         and their childrens.
     */
    protected Set<EObject> getChangedEObjectsAndChildren(Iterable<Notification> notifications, Predicate<EObject> notifierToIgnore) {
        final Set<EObject> changedEObjects = Sets.newLinkedHashSet();
        for (Notification notification : notifications) {
            if (notifierToIgnore == null || notification.getNotifier() instanceof EObject && !notifierToIgnore.apply((EObject) notification.getNotifier())) {
                for (EObject root : getNotificationValues(notification)) {
                    changedEObjects.add(root);
                    Iterators.addAll(changedEObjects, root.eAllContents());
                }
            }
        }
        return changedEObjects;
    }

    /**
     * Return the added or removed EObjects from an attachment or a detachment
     * notification.
     * 
     * @param notification
     *            the change.
     * @return the added or removed EObjects from an attachment or a detachment
     *         notification.
     */
    protected Set<EObject> getNotificationValues(Notification notification) {
        final Set<EObject> values = Sets.newLinkedHashSet();
        Object value = notification.getOldValue();
        if (IS_ATTACHMENT.apply(notification)) {
            // IS_DETACHMENT is the default case : notification.getOldValue and
            // the two predicates are mutually exclusive: see the SET case.
            value = notification.getNewValue();
        }
        if (value instanceof Collection) {
            Iterables.addAll(values, Iterables.filter((Collection<?>) value, EObject.class));
        } else if (value instanceof EObject) {
            values.add((EObject) value);
        }
        return values;
    }

    /**
     * {@inheritDoc}
     */
    public int priority() {
        return DANGLING_REFERENCE_REMOVAL_PRIORITY;
    }

    /**
     * Specific command to remove dangling references.
     */
    private static class RemoveDanglingReferencesCommand extends RecordingCommand {

        private final Collection<EObject> toRemoveXRefFrom = Sets.newLinkedHashSet();

        private final EReferencePredicate isReferenceToIgnorePredicate;

        private ModelAccessor modelAccessor;

        private ECrossReferenceAdapter xReferencer;

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain.
         * @param xRef
         *            the cross referencer.
         * @param accessor
         *            the model accessor.
         * @param toRemoveXRefFrom
         *            the elements to remove cross references.
         * @param isReferenceToIgnore
         *            a predicate indicating if a given reference should be
         *            ignored during deletion or not (can be null if all
         *            references should be considered)
         * 
         */
        public RemoveDanglingReferencesCommand(TransactionalEditingDomain domain, ModelAccessor accessor, ECrossReferenceAdapter xRef, Collection<EObject> toRemoveXRefFrom,
                EReferencePredicate isReferenceToIgnore) {
            super(domain, "Remove dangling references");
            this.modelAccessor = accessor;
            this.xReferencer = xRef;
            this.toRemoveXRefFrom.addAll(toRemoveXRefFrom);
            this.isReferenceToIgnorePredicate = isReferenceToIgnore;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);
            for (EObject eObject : toRemoveXRefFrom) {
                modelAccessor.eDelete(eObject, xReferencer, isReferenceToIgnorePredicate);
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY);

            toRemoveXRefFrom.clear();
            modelAccessor = null;
            xReferencer = null;
        }
    };
}
