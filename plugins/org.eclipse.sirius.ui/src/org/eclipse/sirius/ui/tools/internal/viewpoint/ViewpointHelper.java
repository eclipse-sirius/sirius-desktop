/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.viewpoint;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallbackWithConfimation;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.PlatformUI;

/**
 * Utility class containing method to handle viewpoints.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class ViewpointHelper {

    /**
     * Private constructor for utility class.
     */
    private ViewpointHelper() {
    }

    /**
     * Returns a set of all given viewpoints that are missing dependencies to be activated.
     * 
     * @param viewpoints
     *            the viewpoints from which we want to know if they are missing some dependencies to be activated.
     * @return a set of all given viewpoints that are missing dependencies to be activated. An empty set if no such
     *         element exists.
     */
    public static Set<Viewpoint> getViewpointsMissingDependencies(Collection<Viewpoint> viewpoints) {
        Set<String> selectedURIs = viewpoints.stream().map(viewpoint -> {
            Option<URI> uri = new ViewpointQuery(viewpoint).getViewpointURI();
            String result = null;
            if (uri.some()) {
                result = uri.get().toString();
            }
            return result;
        }).filter(viewpoint -> viewpoint != null).collect(Collectors.toSet());

        Set<Viewpoint> viewpointsMissingDependencies = new HashSet<Viewpoint>();
        for (Viewpoint viewpoint : viewpoints) {
            for (RepresentationExtensionDescription extension : new ViewpointQuery(viewpoint).getAllRepresentationExtensionDescriptions()) {
                String extended = extension.getViewpointURI();
                Pattern pattern = Pattern.compile(extended);
                if (!ViewpointHelper.atLeastOneUriMatchesPattern(selectedURIs, pattern)) {
                    viewpointsMissingDependencies.add(viewpoint);
                }
            }
        }
        return viewpointsMissingDependencies;
    }

    /**
     * Returns true if one of the given URI respects the given pattern. False otherwise.
     * 
     * @param selectedURIs
     *            URIs from which we want to know if one of these respects the given pattern.
     * @param pattern
     *            the pattern one of the URIs should respects.
     * @return true if one of the given URI respects the given pattern. False otherwise.
     */
    public static boolean atLeastOneUriMatchesPattern(Set<String> selectedURIs, Pattern pattern) {
        for (String uriToMatch : selectedURIs) {
            Matcher matcher = pattern.matcher(uriToMatch);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update the session to reflect the viewpoint activation status defined by given maps.
     * 
     * @param originalMap
     *            the map containing viewpoint activation status before the change.
     * @param newMap
     *            the map containing viewpoint activation status after the change.
     * @param session
     *            the session to update
     * @param createNewRepresentations
     *            true to create new DRepresentation for RepresentationDescription having their initialization attribute
     *            at true for selected viewpoints.
     */
    public static void applyNewViewpointSelection(final Map<Viewpoint, Boolean> originalMap, final Map<Viewpoint, Boolean> newMap, final Session session, final boolean createNewRepresentations) {

        // newMap is a copy of originalMap with modifications on values.
        // No elements should have been added.
        if (originalMap.size() != newMap.size()) {
            throw new IllegalArgumentException(Messages.ViewpointSelection_viewpointsMapNotReused);
        }

        final Set<Viewpoint> newSelectedViewpoints = new HashSet<Viewpoint>();
        final Set<Viewpoint> newDeselectedViewpoints = new HashSet<Viewpoint>();

        /*
         * newMap and originalMap are sorted with the same comparator and keys haven't changed. We can iterate on the 2
         * maps together.
         */
        final Iterator<Entry<Viewpoint, Boolean>> originalIterator = originalMap.entrySet().iterator();
        final Iterator<Entry<Viewpoint, Boolean>> newIterator = newMap.entrySet().iterator();

        while (originalIterator.hasNext() && newIterator.hasNext()) {
            final Entry<Viewpoint, Boolean> originalEntry = originalIterator.next();
            final Entry<Viewpoint, Boolean> newEntry = newIterator.next();

            /* XOR : only if original and new booleans are different */
            if (originalEntry.getValue().booleanValue() ^ newEntry.getValue().booleanValue()) {

                // originalEntry and newEntry booleans are differents
                // Just need to test one of them

                // true : has been selected
                if (newEntry.getValue().booleanValue()) {
                    // We can use here originalEntry or newEntry indifferently
                    newSelectedViewpoints.add(originalEntry.getKey());
                } else {
                    // We can use here originalEntry or newEntry indifferently
                    newDeselectedViewpoints.add(originalEntry.getKey());
                }
            }
        }

        final ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();

        // Only if there is something to do
        if (!newSelectedViewpoints.isEmpty() || !newDeselectedViewpoints.isEmpty()) {

            try {
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
                    @Override
                    public void run(final IProgressMonitor monitor) {
                        Command command = new ChangeViewpointSelectionCommand(session, callback, newSelectedViewpoints, newDeselectedViewpoints, createNewRepresentations, monitor);
                        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                        domain.getCommandStack().execute(command);
                    }

                };
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false, runnable);
            } catch (final InvocationTargetException e) {
                if (e.getCause() instanceof RuntimeException) {
                    throw (RuntimeException) e.getCause();
                }
                throw new RuntimeException(e);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
