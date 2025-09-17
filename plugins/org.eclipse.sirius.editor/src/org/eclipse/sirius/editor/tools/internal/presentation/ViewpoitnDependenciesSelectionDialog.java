/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListSelectionDialog;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * A dialog box which allows a viewpoint specifier to select a sub-set of the
 * available viewpoint to configure the <code>Viewpoint.reuses</code> and
 * <code>Viewpoint.customizes</code> references.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpoitnDependenciesSelectionDialog {
    /**
     * The viewpoint to configure.
     */
    private final Viewpoint viewpoint;

    /**
     * Constructor.
     * 
     * @param viewpoint
     *            the viewpoint to configure.
     */
    public ViewpoitnDependenciesSelectionDialog(Viewpoint viewpoint) {
        this.viewpoint = viewpoint;
    }

    /**
     * Opens a dialog box allowing the user to select the list of Viewpoints this
     * element will reuse.
     * 
     * @param shell
     *            the shell to use to open the dialog box.
     * @return the Sirius logical URIs of all the Viewpoints selected for reuse by
     *         the end-user, or {@link Options#newNone()} if the user canceled
     *         the dialog.
     */
    public Option<Set<URI>> selectReusedViewpoints(Shell shell) {
        return selectViewpoints(shell, DescriptionPackage.eINSTANCE.getViewpoint_Reuses(), "Reused Viewpoints", "Select the viewpoints from which this viewpoint will reuse elements:");
    }

    /**
     * Opens a dialog box allowing the user to select the list of Viewpoints this
     * element will customize.
     * 
     * @param shell
     *            the shell to use to open the dialog box.
     * @return the Sirius logical URIs of all the Viewpoints selected for
     *         customization by the end-user, or {@link Options#newNone()} if
     *         the user canceled the dialog.
     */
    public Option<Set<URI>> selectCustomizedViewpoints(Shell shell) {
        return selectViewpoints(shell, DescriptionPackage.eINSTANCE.getViewpoint_Customizes(), "Customized Viewpoints", "Select the viewpoints this viewpoint will customize:");
    }

    /**
     * Opens a dialog box allowing the user to select the list of Viewpoints this
     * element is in conflict with.
     * 
     * @param shell
     *            the shell to use to open the dialog box.
     * @return the Sirius logical URIs of all the Viewpoints selected for conflict
     *         by the end-user, or {@link Options#newNone()} if the user
     *         canceled the dialog.
     */
    public Option<Set<URI>> selectConflictsViewpoints(Shell shell) {
        return selectViewpoints(shell, DescriptionPackage.eINSTANCE.getViewpoint_Customizes(), "Conflicting Viewpoints", "Select the viewpoints this viewpoint is in conflict with:");
    }

    private Option<Set<URI>> selectViewpoints(Shell shell, EAttribute attribute, String title, String message) {
        List<URI> available = getAvailableViewpointsURIs();
        available.remove(new ViewpointQuery(viewpoint).getViewpointURI().get());
        Collections.sort(available, Ordering.usingToString());

        List<URI> selected = getSelectedSiriusURIs(viewpoint, attribute);

        ListSelectionDialog lsd = new ListSelectionDialog(shell, available, new SiriusURIContentProvider(), new LabelProvider(), message);
        lsd.setInitialElementSelections(selected);
        lsd.setTitle(title);
        if (lsd.open() == Window.OK) {
            Set<URI> result = ImmutableSet.copyOf(Iterators.filter(Iterators.forArray(lsd.getResult()), URI.class));
            return Options.newSome(result);
        } else {
            return Options.newNone();
        }
    }

    @SuppressWarnings("unchecked")
    private List<URI> getSelectedSiriusURIs(Viewpoint vp, EStructuralFeature feature) {
        return Lists.newArrayList(Iterables.filter((List<URI>) vp.eGet(feature), Predicates.notNull()));
    }

    private List<URI> getAvailableViewpointsURIs() {
        return Lists.newArrayList(Iterables.filter(Iterables.transform(ViewpointRegistry.getInstance().getViewpoints(), new Function<Viewpoint, URI>() {
            public URI apply(Viewpoint from) {
                Option<URI> uri = new ViewpointQuery(from).getViewpointURI();
                if (uri.some()) {
                    return uri.get();
                } else {
                    return null;
                }
            }
        }), Predicates.notNull()));
    }

    private static final class SiriusURIContentProvider implements IStructuredContentProvider {
        /**
         * {@inheritDoc}
         */
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List<?>) {
                Iterable<URI> uris = Iterables.filter((List<?>) inputElement, URI.class);
                return Iterables.toArray(uris, URI.class);
            } else {
                return new Object[0];
            }
        }

        /**
         * {@inheritDoc}
         */
        public void dispose() {
            // Nothing to do.
        }

        /**
         * {@inheritDoc}
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Nothing to do.
        }
    }
}
