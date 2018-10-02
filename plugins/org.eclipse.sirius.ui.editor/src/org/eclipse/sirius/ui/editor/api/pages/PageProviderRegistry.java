/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.api.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.internal.pages.PageOrderer;
import org.eclipse.sirius.ui.editor.internal.pages.PageProviderListener;

/**
 * This component provides {@link PageProvider}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class PageProviderRegistry {
    /**
     * Listener to notify when page providers are added or removed from this
     * registry.
     */
    private final Set<PageProviderListener> pageProviderListeners;

    /**
     * A set of all {@link PageProvider} registered and usable to create new
     * pages for multi-page editor.
     */
    private final List<PageProvider> pageProviders;

    /**
     * Components ordering pages regarding positioning information.
     */
    private PageOrderer pageOrderer;

    /**
     * This enum specifies positioning type to apply when initializing an
     * {@link AbstractSessionEditorPage} in an Editor.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public enum PositioningKind {
        /**
         * The produced {@link AbstractAirdEditorCustomPage} should be placed
         * before the page with the id {@link PageProvider#locationId}.
         */
        BEFORE,
        /**
         * The produced {@link AbstractAirdEditorCustomPage} should be placed
         * after the page with the id {@link PageProvider#locationId}.
         */
        AFTER,
        /**
         * The produced {@link AbstractAirdEditorCustomPage} should replace the
         * page with the id {@link PageProvider#locationId}.
         */
        REPLACE;
    };

    /**
     * Default constructor. Initialize the set of page providers.
     */
    public PageProviderRegistry() {
        pageProviders = new CopyOnWriteArrayList<>();
        pageProviderListeners = new HashSet<>();
        pageOrderer = new PageOrderer();
    }

    /**
     * Adds a new page provider to this registry available for any editor.
     * 
     * @param newPageProvider
     *            the new page provider to add.
     */
    public void addPageProvider(PageProvider newPageProvider) {
        pageProviders.add(newPageProvider);
        for (PageProviderListener pageProviderListener : pageProviderListeners) {
            pageProviderListener.pageProviderChanged();
        }

    }

    /**
     * Register the given listener to this registry to be notified when page
     * providers are added or removed from this registry.
     * 
     * @param pageProviderListener
     *            the listener that will be notified when a {@link PageProvider}
     *            will be added or removed from this registry.
     */
    public void addRegistryListener(PageProviderListener pageProviderListener) {
        pageProviderListeners.add(pageProviderListener);
    }

    /**
     * Remove the given listener from this registry.
     * 
     * @param pageProviderListener
     *            the listener to remove.
     */
    public void removeRegistryListener(PageProviderListener pageProviderListener) {
        pageProviderListeners.remove(pageProviderListener);
    }

    /**
     * Remove the given page provider from thios registry.
     * 
     * @param pageProvider
     *            the page provider to remove from this registry.
     */
    public void removePageProvider(PageProvider pageProvider) {
        pageProviders.remove(pageProvider);
        for (PageProviderListener pageProviderListener : pageProviderListeners) {
            pageProviderListener.pageProviderChanged();
        }
    }

    /**
     * Returns a list of {@link AbstractSessionEditorPage} in the order computed
     * from all their location information retrieved from their parent creators
     * {@link PageProvider}. Obsolete pages are not returned. New available
     * pages fitting current context are returned.
     * 
     * @param editor
     *            the aird editor requesting custom pages.
     * @param session
     *            the aird editor's session from which page request is done.
     * @param displayedPages
     *            pages already displayed by the editor.
     * @param event
     *            the event triggering the call to this method. Can be null.
     * @return a list of {@link AbstractSessionEditorPage} in the order computed
     *         from all their location information retrieved from their parent
     *         creators {@link PageProvider}. Obsolete pages are not returned.
     *         New available pages fitting current context are returned.
     */
    public List<AbstractSessionEditorPage> getPagesOrdered(SessionEditor editor, Session session, List<AbstractSessionEditorPage> displayedPages, ResourceSetChangeEvent event) {
        List<AbstractSessionEditorPage> pagesOrdered = pageOrderer.getOrderedPages(pageProviders, editor, displayedPages, event);
        return pagesOrdered;
    }

}
