/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo
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
package org.eclipse.sirius.ui.editor.internal.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;

/**
 * Components ordering aird editor pages regarding positioning information
 * provided by this pages.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class PageOrderer {
    /**
     * A page wrapper with positioning information regarding other page and the
     * pages that should be to the left or the right of it.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private class PagePositioning {
        /**
         * The page positioned.
         */
        AbstractSessionEditorPage page;

        /**
         * The id of the page to its left.
         */
        String leftPortPageId;

        /**
         * The id of the page to its right.
         */
        String rightPortPageId;

        /**
         * The id of the page this page should replace.
         */
        String replacePortPageId;

        /**
         * The real page to its left.
         */
        PagePositioning leftPortPage;

        /**
         * The real page to its right.
         */
        PagePositioning rightPortPage;

        /**
         * The real page to replace.
         */
        PagePositioning replacePortPage;

        @Override
        public String toString() {
            String toString = "Page: " + page.getClass().getSimpleName(); //$NON-NLS-1$
            toString += leftPortPageId != null ? "\nLeft id: " + leftPortPageId : ""; //$NON-NLS-1$//$NON-NLS-2$
            toString += rightPortPageId != null ? "\nRight id: " + rightPortPageId : ""; //$NON-NLS-1$//$NON-NLS-2$
            toString += replacePortPageId != null ? "\nReplace id: " + replacePortPageId : ""; //$NON-NLS-1$//$NON-NLS-2$
            return toString;
        }
    }

    /**
     * Returns all pages the caller should display in the order defined by all
     * their positioning information.
     * 
     * @param pageProviders
     *            the {@link PageProvider} to consider to know what pages can be
     *            displayed.
     * @param editor
     *            the aird editor asking for ordered pages.
     * 
     * @param alreadyInitializedPages
     *            the pages the editor currently contains.
     * @param event
     *            the event triggering the call to this method. Can be null.
     * @return all pages the caller should display in the order defined by all
     *         their positioning information.
     */
    public List<AbstractSessionEditorPage> getOrderedPages(List<PageProvider> pageProviders, SessionEditor editor, List<AbstractSessionEditorPage> alreadyInitializedPages,
            ResourceSetChangeEvent event) {
        List<AbstractSessionEditorPage> pagesToKeep = new ArrayList<>();
        List<PagePositioning> pagePositioningElements = new ArrayList<>();
        Map<String, PagePositioning> pageIdToPageMap = new HashMap<>();
        initializePagePositioning(pageProviders, editor, alreadyInitializedPages, pagesToKeep, pagePositioningElements, pageIdToPageMap, event);
        positionPagesRegardingOthers(pagePositioningElements, pageIdToPageMap);
        Set<PagePositioning> replacedPages = new HashSet<>();
        replacePages(pagePositioningElements, pageIdToPageMap, replacedPages);
        LinkedList<PagePositioning> newPageOrdered = linkAllPageRoot(pagePositioningElements, replacedPages);
        // we extract pages in the computed order of their PagePositioning
        // container.
        List<AbstractSessionEditorPage> pagesResult = newPageOrdered.stream().map(pageP -> pageP.page).collect(Collectors.toList());
        return pagesResult;
    }

    /**
     * This method returns a flattened list of all isolated PagePositioning and
     * trees of PagePositioning linked to other PagePositioning to have an
     * ordered list of pages to display in their right order.
     * 
     * @param pagePositioningElements
     *            the list containing elements isolated or related to others or
     *            replaced by others.
     * @param replacedPages
     *            all pages replaced to ignore when building the ordered list to
     *            return.
     * @return a flattened list of all isolated PagePositioning and trees of
     *         PagePositioning linked to other PagePositioning to have an
     *         ordered list of page to display in their right order.
     */
    private LinkedList<PagePositioning> linkAllPageRoot(List<PagePositioning> pagePositioningElements, Set<PagePositioning> replacedPages) {
        LinkedList<PagePositioning> newPageOrdered = new LinkedList<>();
        for (PagePositioning pagePositioning : pagePositioningElements) {
            if (!replacedPages.contains(pagePositioning)) {
                if (pagePositioning.leftPortPage == null && pagePositioning.rightPortPage != null) {
                    // a root page having other pages attached to its right but
                    // not to its left.
                    recursivelyAddLinkedPages(newPageOrdered, pagePositioning);
                } else if (pagePositioning.leftPortPage == null && pagePositioning.rightPortPage == null) {
                    // isolated page.
                    newPageOrdered.addLast(pagePositioning);
                } else if (pagePositioning.leftPortPage.rightPortPage != pagePositioning) {
                    // in case of conflict a page can be related to another one
                    // but not on the right of this one because it has been
                    // replaced because of left of right positioning
                    // information conflict. Still we want to display it because
                    // it has not been replaced by a replace information.
                    newPageOrdered.addLast(pagePositioning);
                }
            }
        }
        return newPageOrdered;
    }

    /**
     * Pages target of other pages wanting to replace it are effectively
     * replaced. A page replacing another page takes its left and right
     * relations.
     * 
     * @param pagePositioningElements
     *            the list containing elements isolated or related to others or
     *            replaced by others.
     * @param pageIdToPageMap
     *            a map of page id to the corresponding {@link PagePositioning}
     * @param replacedPages
     *            all pages replaced to ignore when building the ordered list to
     *            return.
     */
    private void replacePages(List<PagePositioning> pagePositioningElements, Map<String, PagePositioning> pageIdToPageMap, Set<PagePositioning> replacedPages) {
        for (PagePositioning pagePositioning : pagePositioningElements) {
            if (pagePositioning.replacePortPageId != null) {
                PagePositioning replacedPage = pageIdToPageMap.get(pagePositioning.replacePortPageId);
                if (replacedPage != null) {
                    pagePositioning.replacePortPage = replacedPage;
                    // the page replace the target. I.e it connects to target
                    // left and right pages.
                    if (replacedPage.leftPortPage != null) {
                        if (pagePositioning.leftPortPage == null) {
                            pagePositioning.leftPortPage = replacedPage.leftPortPage;
                        } else {
                            // we have a conflict because the replaced page has
                            // node to the left and the page replacing has also
                            // node to the left. In this case we attach node to
                            // the left of the page replacing to the left of the
                            // leftmost node of the replaced page. And the left
                            // node of the replaced page is attached to the left
                            // of the page replacing.
                            PagePositioning leftMostPage = getLeftMostPage(replacedPage);
                            leftMostPage.leftPortPage = pagePositioning.leftPortPage;
                            pagePositioning.leftPortPage.rightPortPage = leftMostPage;

                            pagePositioning.leftPortPage = replacedPage.leftPortPage;
                            replacedPage.leftPortPage.rightPortPage = pagePositioning;
                        }
                    }
                    if (replacedPage.rightPortPage != null) {
                        if (pagePositioning.rightPortPage == null) {
                            pagePositioning.rightPortPage = replacedPage.rightPortPage;
                        } else {
                            // we have a conflict because the replaced page has
                            // node to the right and the page replacing has also
                            // node to the right. In this case we attach node
                            // to the right of the page replacing to the right
                            // of the rightmost node of the replaced page. And
                            // the right node of the replaced page is attached
                            // to the right of the page replacing.
                            PagePositioning rightMostPage = getRightMostPage(replacedPage);
                            rightMostPage.rightPortPage = pagePositioning.rightPortPage;
                            pagePositioning.rightPortPage.leftPortPage = rightMostPage;

                            pagePositioning.rightPortPage = replacedPage.rightPortPage;
                            replacedPage.rightPortPage.leftPortPage = pagePositioning;
                        }
                    }
                    replacedPages.add(pagePositioning.replacePortPage);
                }
            }
        }
    }

    /**
     * Return the {@link PagePositioning} at the rightmost position of the given
     * one.
     * 
     * @param pagePositioning
     *            the {@link PagePositioning} from which we want to retrieve the
     *            rightmost one.
     * @return the {@link PagePositioning} at the rightmost position of the
     *         given one.
     */
    private PagePositioning getRightMostPage(PagePositioning pagePositioning) {
        if (pagePositioning.rightPortPage == null) {
            return pagePositioning;
        } else {
            return getLeftMostPage(pagePositioning.rightPortPage);
        }
    }

    /**
     * Return the {@link PagePositioning} at the leftmost position of the given
     * one.
     * 
     * @param pagePositioning
     *            the {@link PagePositioning} from which we want to retrieve the
     *            leftmost one.
     * @return the {@link PagePositioning} at the leftmost position of the given
     *         one.
     */
    private PagePositioning getLeftMostPage(PagePositioning pagePositioning) {
        if (pagePositioning.leftPortPage == null) {
            return pagePositioning;
        } else {
            return getLeftMostPage(pagePositioning.leftPortPage);
        }
    }

    /**
     * We build relations between pages by replacing the positioning kind + id
     * by the real pages at the left and right of each {@link PagePositioning}.
     * 
     * Pages without positioning information and without other pages placed
     * regarding it are isolated without left and right pages.
     * 
     * This can be the case for pages placed regarding another one that are not
     * provided by providers or for pages without positioning information
     * pointing on it or on other pages.
     * 
     * @param pagePositioningElements
     *            the list containing all {@link PagePositioning} with
     *            positioning information as id and {@link PositioningKind}.
     * @param pageIdToPageMap
     *            a map of page id to the corresponding {@link PagePositioning}
     */
    private void positionPagesRegardingOthers(List<PagePositioning> pagePositioningElements, Map<String, PagePositioning> pageIdToPageMap) {
        // for all pages to display, we replace their target location page id
        // for left and right positioning computed before. This
        // create a tree with many roots because some pages can have no
        // location. All roots cannot have a page to its left. Only to its
        // right. In case of conflict (a page A is placed after page B and
        // a page C is placed before A), the last positioned element will have
        // its location fulfilled to the cost of the other that becomes
        // isolated.
        for (PagePositioning pagePositioning : pagePositioningElements) {
            if (pagePositioning.leftPortPageId != null) {
                PagePositioning targetPagePositioning = pageIdToPageMap.get(pagePositioning.leftPortPageId);
                if (targetPagePositioning != null) {
                    pagePositioning.leftPortPage = targetPagePositioning;
                    if (targetPagePositioning.rightPortPage != null) {
                        // We put this page in between the target page and the
                        // page
                        // already to its right. This is a conflict.
                        targetPagePositioning.rightPortPage.leftPortPage = pagePositioning;
                        pagePositioning.rightPortPage = targetPagePositioning.rightPortPage;

                    }
                    targetPagePositioning.rightPortPage = pagePositioning;
                }
            }
            if (pagePositioning.rightPortPageId != null) {
                PagePositioning targetPagePositioning = pageIdToPageMap.get(pagePositioning.rightPortPageId);
                if (targetPagePositioning != null) {
                    pagePositioning.rightPortPage = targetPagePositioning;
                    if (targetPagePositioning.leftPortPage != null) {
                        // We put this page in between the target page and the
                        // page
                        // already to its left. This is a conflict.
                        targetPagePositioning.leftPortPage.rightPortPage = pagePositioning;
                        pagePositioning.leftPortPage = targetPagePositioning.leftPortPage;
                    }
                    targetPagePositioning.leftPortPage = pagePositioning;
                }
            }
        }
    }

    /**
     * Constructs {@link PagePositioning} containing the page that should be
     * displayed by the given editor and fills pagePositioningElements Set and
     * pageIdToPageMap map with it. Also fill the pagesToKeep set with the pages
     * of the editor that should be kept. All {@link PagePositioning} have their
     * corresponding page and the the positioning information if such
     * information exists (if the page should be placed to the left or right of
     * another one or replace it and the id of this concerned page.)
     * 
     * @param pageProviders
     *            providers use to know what pages should be displayed.
     * @param editor
     *            the editor from which we compute pages that should be
     *            displayed.
     * @param alreadyInitializedPages
     *            the pages already initialized by the editor.
     * @param pagesToKeep
     *            the pages of the editor that should be kept.
     * @param pagePositioningElements
     *            the set containing all {@link PagePositioning} containing the
     *            pages to display in the given editor.
     * @param pageIdToPageMap
     *            a map of page id to the corresponding {@link PagePositioning}
     *            containing a page to display in the given editor.
     * @param event
     *            the event triggering the call to this method. Can be null.
     */
    private void initializePagePositioning(List<PageProvider> pageProviders, SessionEditor editor, List<AbstractSessionEditorPage> alreadyInitializedPages, List<AbstractSessionEditorPage> pagesToKeep,
            List<PagePositioning> pagePositioningElements, Map<String, PagePositioning> pageIdToPageMap, ResourceSetChangeEvent event) {
        Set<String> alreadyInitializedPagesId = alreadyInitializedPages.stream().map(page -> page.getId()).collect(Collectors.toSet());
        for (PageProvider pageProvider : pageProviders) {
            List<Notification> notifications = event != null ? event.getNotifications() : Collections.emptyList();
            NotificationFilter filterForInitialCondition = pageProvider.getFilterForPageRequesting();
            boolean providePages = notifications.isEmpty()
                    || notifications.stream().anyMatch(notification -> filterForInitialCondition == null ? true : filterForInitialCondition.matches(notification));
            if (providePages) {
                Map<String, Supplier<AbstractSessionEditorPage>> pagesToAdd = pageProvider.getPages(editor);
                if (pagesToAdd != null) {
                    // For each page provided by the provider we initialize a
                    // PagePositionning defining the page and the location it
                    // should have regarding other page.
                    for (Entry<String, Supplier<AbstractSessionEditorPage>> pageEntry : pagesToAdd.entrySet()) {
                        if (!alreadyInitializedPagesId.contains(pageEntry.getKey())) {
                            AbstractSessionEditorPage page = pageEntry.getValue().get();
                            page.initialize(editor);
                            PagePositioning pagePositioning = new PagePositioning();
                            pageIdToPageMap.put(page.getId(), pagePositioning);
                            pagePositioningElements.add(pagePositioning);
                            Optional<PositioningKind> positioningKind = page.getPositioning();
                            Optional<String> locationId = page.getLocationId();
                            if (positioningKind.isPresent() && locationId.isPresent()) {
                                setPortsId(positioningKind.get(), locationId.get(), pagePositioning);
                            }
                            pagePositioning.page = page;

                        }
                    }
                }
            }
            if (pagesToKeep.size() != alreadyInitializedPages.size()) {
                // if we have not yet confirm that all pages of the editor
                // should be kept, we ask this provider to know if some
                // additional already initialized pages should be kept.
                for (AbstractSessionEditorPage page : alreadyInitializedPages) {
                    if (pageProvider.provides(page.getId(), editor)) {
                        pagesToKeep.add(page);
                        PagePositioning pagePositioning = new PagePositioning();
                        pageIdToPageMap.put(page.getId(), pagePositioning);
                        Optional<PositioningKind> positioningKind = page.getPositioning();
                        Optional<String> locationId = page.getLocationId();
                        if (positioningKind.isPresent() && locationId.isPresent()) {
                            setPortsId(positioningKind.get(), locationId.get(), pagePositioning);
                        }
                        pagePositioning.page = page;
                        pagePositioningElements.add(pagePositioning);
                    }
                }
            }
        }
    }

    /**
     * Set the given id to the right port id of the given
     * {@link PagePositioning}.
     * 
     * @param positioningKind
     *            the {@link PositioningKind} to set for the given
     *            {@link PagePositioning}.
     * @param targetPageId
     *            the id of the page that will be positioned regarding the given
     *            {@link PagePositioning} 's page.
     * @param pagePositioning
     *            the component containing the page to position regarding the
     *            given page id.
     */
    private void setPortsId(PositioningKind positioningKind, String targetPageId, PagePositioning pagePositioning) {
        if (positioningKind != null && !targetPageId.isEmpty()) {
            switch (positioningKind) {
            case BEFORE:
                pagePositioning.rightPortPageId = targetPageId;
                break;
            case AFTER:
                pagePositioning.leftPortPageId = targetPageId;
                break;
            case REPLACE:
                pagePositioning.replacePortPageId = targetPageId;
                break;
            default:
                break;
            }
        }
    }

    /**
     * Recursively constructs the ordered page list by putting the roots and
     * their sub tree next to the others. Roots not positioned regarding another
     * page are placed at the beginning of the list.
     * 
     * @param newPageOrdered
     *            the list containing pages in the order specified by all
     *            positioning information.
     * @param pagePositioning
     *            a page to add to the list that can contains pages to its right
     */
    private void recursivelyAddLinkedPages(LinkedList<PagePositioning> newPageOrdered, PagePositioning pagePositioning) {
        if (pagePositioning.rightPortPage != null) {
            recursivelyAddLinkedPages(newPageOrdered, pagePositioning.rightPortPage);
        }
        if (!newPageOrdered.contains(pagePositioning)) {
            newPageOrdered.addFirst(pagePositioning);
        }
    }

}
