/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.layout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.LayoutUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Helper to execute a ArrangeRequest's {@link Command} for created views (in the DDiagramCanonicalSynchronizer ) to
 * arrange.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class SiriusCanonicalLayoutHandler {

    private SiriusCanonicalLayoutHandler() {
        // Helper to not instantiate
    }

    /**
     * Execute ArrangeRequest's {@link Command} for created views (in the DDiagramCanonicalSynchronizer) to arrange.
     * 
     * @param diagramEditPart
     *            The {@link DiagramEditPart} used to get parent {@link IGraphicalEditPart} of created {@link View}s to
     *            layout.
     */
    public static void launchArrangeCommand(DiagramEditPart diagramEditPart) {
        if (LayoutUtil.isArrangeAtOpeningChangesDisabled()) {
            oldLaunchArrangeCommand(diagramEditPart);
        } else {
            TransactionalEditingDomain editingDomain = diagramEditPart.getEditingDomain();

            boolean specificArrangeAtFirstOpening = false;
            EObject resolvedSemanticElement = diagramEditPart.resolveSemanticElement();
            if (resolvedSemanticElement instanceof DDiagram) {
                if (resolvedSemanticElement.eAdapters().contains(NotYetOpenedDiagramAdapter.INSTANCE)) {
                    // This is the first diagram opening, we launch a global arrange all
                    if (diagramEditPart != null) {
                        specificArrangeAtFirstOpening = true;
                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                LayoutUtil.arrange(diagramEditPart);
                            }
                        });
                    }
                    // Remove the "arrange adapter" to potentially avoid a second arrange
                    resolvedSemanticElement.eAdapters().remove(NotYetOpenedDiagramAdapter.INSTANCE);
                    // Also clean the created views from SiriusLayoutDataManager as they are layouted with the global
                    // arrange all. Otherwise it could be arrange "again" in a next opening for example.
                    SiriusLayoutDataManager.INSTANCE.removeLayoutViews(diagramEditPart.getDiagramView());
                }
            }

            if (!specificArrangeAtFirstOpening) {
                Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart, SiriusLayoutDataManager.INSTANCE.getCreatedViewForLayoutAll());
                Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToBorderNodeLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart,
                        SiriusLayoutDataManager.INSTANCE.getCreatedViewForBorderNodeLayout());
                Map<IGraphicalEditPart, List<IAdaptable>> centeredCreatedViewsToLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart,
                        SiriusLayoutDataManager.INSTANCE.getCreatedViewForInitPositionLayout());
                Map<IGraphicalEditPart, List<IAdaptable>> createdViewsAsReferenceLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart,
                        SiriusLayoutDataManager.INSTANCE.getCreatedViewReferenceLayout());
                LayoutProvider layoutProvider = LayoutService.getProvider(diagramEditPart);
                String layoutType = LayoutType.DEFAULT;
                if (layoutProvider instanceof GenericLayoutProvider && ((GenericLayoutProvider) layoutProvider).shouldReverseLayoutsOrder(diagramEditPart)) {
                    // Reverse order as contrary to classic layout. For example for ELK, it is better to do it from the
                    // lowest level to the highest.
                    LinkedHashMap<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap_reverse = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
                    ArrayList<IGraphicalEditPart> keys = new ArrayList<IGraphicalEditPart>(createdViewsToLayoutMap.keySet());
                    for (int i = keys.size() - 1; i >= 0; i--) {
                        createdViewsToLayoutMap_reverse.put(keys.get(i), createdViewsToLayoutMap.get(keys.get(i)));
                    }
                    createdViewsToLayoutMap = createdViewsToLayoutMap_reverse;
                    layoutType = SiriusLayoutDataManager.LAYOUT_TYPE_ARRANGE_AT_OPENING;
                }
                Command layoutCommand = getLayoutCommand(createdViewsToLayoutMap, createdViewsToBorderNodeLayoutMap, centeredCreatedViewsToLayoutMap, createdViewsAsReferenceLayoutMap, editingDomain,
                        layoutType);
                if (layoutCommand.canExecute()) {
                    editingDomain.getCommandStack().execute(layoutCommand);
                }
            }
        }
    }

    /**
     * Create a new Map, with as key a common parent edit part and as value a list of new GMF views (with this parent).
     * The new GMF views are collected from <code>createdViewsToLayout</code> if they are contained in the current
     * diagram, according to <code>diagramEditPart</code>.
     * 
     * @param diagramEditPart
     *            The ancestor of all returned views.
     * @param createdViewsToLayout
     *            The created GMF views to layout, grouped by Diagram.
     */
    private static Map<IGraphicalEditPart, List<IAdaptable>> getCreatedViewsToLayoutByContainerPart(DiagramEditPart diagramEditPart, Map<Diagram, Set<View>> createdViewsToLayout) {
        // For a more predictable result (and constant), the hashMap must be sorted from the highest level container to
        // the lowest level container. The viewAdapters seems to be already sorted so we must just keep this order by
        // using a linked Hashmap.
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        if (!createdViewsToLayout.isEmpty()) {

            Diagram diagramOfOpenedEditor = diagramEditPart.getDiagramView();
            if (diagramOfOpenedEditor != null && createdViewsToLayout.containsKey(diagramOfOpenedEditor)) {

                if (diagramEditPart != null) {
                    Map<?, ?> editPartRegistry = diagramEditPart.getViewer().getEditPartRegistry();
                    List<IAdaptable> viewAdapters = getAdapters(createdViewsToLayout.get(diagramOfOpenedEditor));
                    Map<View, List<IAdaptable>> splitedViewAdapters = splitViewAdaptersAccordingToParent(viewAdapters);
                    for (Entry<View, List<IAdaptable>> viewAdaptersWithSameParent : splitedViewAdapters.entrySet()) {
                        View parentView = viewAdaptersWithSameParent.getKey();
                        List<IAdaptable> childViewsAdapters = viewAdaptersWithSameParent.getValue();
                        IGraphicalEditPart parentEditPart = (IGraphicalEditPart) editPartRegistry.get(parentView);
                        if (parentEditPart != null) {
                            createdViewsToLayoutMap.put(parentEditPart, childViewsAdapters);
                        }
                    }
                }
                createdViewsToLayout.remove(diagramOfOpenedEditor);
            }
        }
        return createdViewsToLayoutMap;
    }

    private static Map<View, List<IAdaptable>> splitViewAdaptersAccordingToParent(List<IAdaptable> viewAdapters) {
        // For a more predictable result (and constant), the hashMap must be sorted from the highest level container to
        // the lowest level container. The viewAdapters seems to be already sorted so we must just keep this order by
        // using a linked Hashmap.
        Map<View, List<IAdaptable>> splitedViewAdaptersAccordingToParent = new LinkedHashMap<View, List<IAdaptable>>();
        for (IAdaptable viewAdapter : viewAdapters) {
            View createdViewToLayout = viewAdapter.getAdapter(View.class);
            EObject eContainer = createdViewToLayout.eContainer();
            if (eContainer instanceof View) {
                View parentView = (View) eContainer;
                List<IAdaptable> viewAdaptersWithSameParent = splitedViewAdaptersAccordingToParent.get(parentView);
                if (viewAdaptersWithSameParent == null) {
                    viewAdaptersWithSameParent = new ArrayList<IAdaptable>();
                    splitedViewAdaptersAccordingToParent.put(parentView, viewAdaptersWithSameParent);
                }
                viewAdaptersWithSameParent.add(viewAdapter);
            }
        }

        return splitedViewAdaptersAccordingToParent;
    }

    private static List<IAdaptable> getAdapters(Set<View> createdViewsToLayout) {
        List<IAdaptable> viewAdapters = new ArrayList<IAdaptable>();
        for (View createdViewToLayout : createdViewsToLayout) {
            viewAdapters.add(new EObjectAdapter(createdViewToLayout));
        }
        return viewAdapters;
    }

    private static Command getLayoutCommand(Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap, Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToBorderNodeLayoutMap,
            Map<IGraphicalEditPart, List<IAdaptable>> centeredCreatedViewsToLayoutMap, Map<IGraphicalEditPart, List<IAdaptable>> createdViewsAsReferenceLayoutMap,
            TransactionalEditingDomain editingDomain, String specificLayoutType) {

        final CompoundCommand compoundCommand = new CompoundCommand();
        // A label is explicitly added to avoid to rely on the first command of the list and also to facilitate
        // debugging.
        compoundCommand.setLabel(Messages.SiriusCanonicalLayoutCommand_label);
        // Filter type of element to layout to avoid having elements layout
        // computed multiple times
        Predicate<Entry<IGraphicalEditPart, List<IAdaptable>>> typeOfElementToLayout = new Predicate<Map.Entry<IGraphicalEditPart, List<IAdaptable>>>() {
            @Override
            public boolean apply(Entry<IGraphicalEditPart, List<IAdaptable>> input) {
                return input.getKey() instanceof DDiagramEditPart || input.getKey() instanceof DNodeContainerViewNodeContainerCompartmentEditPart
                        || (input.getKey() instanceof DNodeContainerViewNodeContainerCompartment2EditPart
                                && !(input.getKey().getParent().getParent() instanceof DNodeContainerViewNodeContainerCompartment2EditPart));
            }
        };
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : Iterables.filter(centeredCreatedViewsToLayoutMap.entrySet(), typeOfElementToLayout)) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            List<IAdaptable> referenceViews = createdViewsAsReferenceLayoutMap.get(parentEditPart);
            Command layoutCommand = SiriusCanonicalLayoutCommand.initial(editingDomain, parentEditPart, childViewsAdapters, referenceViews, specificLayoutType);
            compoundCommand.append(layoutCommand);
        }
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : Iterables.filter(createdViewsToLayoutMap.entrySet(), typeOfElementToLayout)) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            Command layoutCommand = SiriusCanonicalLayoutCommand.normal(editingDomain, parentEditPart, childViewsAdapters, specificLayoutType);
            compoundCommand.append(layoutCommand);
        }
        Map<IGraphicalEditPart, List<IAdaptable>> filteredCreatedViewsToBorderNodeLayoutMap = createdViewsToBorderNodeLayoutMap.entrySet().stream() //
                .filter(typeOfElementToLayout) //
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        for (Entry<IGraphicalEditPart, List<IAdaptable>> borderedNodeEntry : filteredCreatedViewsToBorderNodeLayoutMap.entrySet()) {
            IGraphicalEditPart parentEditPart = borderedNodeEntry.getKey();
            List<IAdaptable> childViewsAdapters = borderedNodeEntry.getValue();
            Command layoutCommand = SiriusCanonicalLayoutCommand.border(editingDomain, parentEditPart, childViewsAdapters);
            compoundCommand.append(layoutCommand);
        }

        return compoundCommand;
    }

    /**
     * Execute ArrangeRequest's {@link Command} for created views (in the DDiagramCanonicalSynchronizer) to arrange.
     * 
     * @param diagramEditPart
     *            The {@link DiagramEditPart} used to get parent {@link IGraphicalEditPart} of created {@link View}s to
     *            layout.
     * @deprecated Only here as security if user activates {@link LayoutUtil#isArrangeAtOpeningChangesDisabled()}.
     */
    @Deprecated
    public static void oldLaunchArrangeCommand(DiagramEditPart diagramEditPart) {
        TransactionalEditingDomain editingDomain = diagramEditPart.getEditingDomain();
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = oldGetCreatedViewsToLayoutMap(diagramEditPart);
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsWithSpecialLayoutMap = oldGetCreatedViewsWithSpecialLayoutMap(diagramEditPart);
        LayoutProvider layoutProvider = LayoutService.getProvider(diagramEditPart);
        String layoutType = LayoutType.DEFAULT;
        if (layoutProvider instanceof GenericLayoutProvider && ((GenericLayoutProvider) layoutProvider).shouldReverseLayoutsOrder(diagramEditPart)) {
            // Reverse order as contrary to classic layout. For example for ELK, it is better to do it from the lowest
            // level
            // to the
            // highest.
            LinkedHashMap<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap_reverse = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
            ArrayList<IGraphicalEditPart> keys = new ArrayList<IGraphicalEditPart>(createdViewsToLayoutMap.keySet());
            for (int i = keys.size() - 1; i >= 0; i--) {
                createdViewsToLayoutMap_reverse.put(keys.get(i), createdViewsToLayoutMap.get(keys.get(i)));
            }
            createdViewsToLayoutMap = createdViewsToLayoutMap_reverse;
            layoutType = SiriusLayoutDataManager.LAYOUT_TYPE_ARRANGE_AT_OPENING;
        }
        Command layoutCommand = getLayoutCommand(createdViewsToLayoutMap, Map.of(), createdViewsWithSpecialLayoutMap, Map.of(), editingDomain, layoutType);
        if (layoutCommand.canExecute()) {
            editingDomain.getCommandStack().execute(layoutCommand);
        }
    }

    @Deprecated
    private static Map<IGraphicalEditPart, List<IAdaptable>> oldGetCreatedViewsToLayoutMap(DiagramEditPart diagramEditPart) {
        // For a more predictable result (and constant), the hashMap must be
        // sorted from the highest level container to the lowest level
        // container. The viewAdapters seems to be already sorted so we must
        // just keep this order by using a linked Hashmap.
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE.getCreatedViewForLayoutAll();

        oldGetCreatedViewToLayoutMap(diagramEditPart, createdViewsToLayoutMap, createdViewsToLayout);
        return createdViewsToLayoutMap;
    }

    @Deprecated
    private static Map<IGraphicalEditPart, List<IAdaptable>> oldGetCreatedViewsWithSpecialLayoutMap(DiagramEditPart diagramEditPart) {
        // For a more predictable result (and constant), the hashMap must be
        // sorted from the highest level container to the lowest level
        // container. The viewAdapters seems to be already sorted so we must
        // just keep this order by using a linked Hashmap.
        Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE.getCreatedViewForInitPositionLayout();

        oldGetCreatedViewToLayoutMap(diagramEditPart, createdViewsToLayoutMap, createdViewsToLayout);
        return createdViewsToLayoutMap;
    }

    @Deprecated
    private static void oldGetCreatedViewToLayoutMap(DiagramEditPart diagramEditPart, Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap, Map<Diagram, Set<View>> createdViewsToLayout) {
        if (!createdViewsToLayout.isEmpty()) {

            Diagram diagramOfOpenedEditor = diagramEditPart.getDiagramView();
            if (diagramOfOpenedEditor != null && createdViewsToLayout.containsKey(diagramOfOpenedEditor)) {

                if (diagramEditPart != null) {
                    Map<?, ?> editPartRegistry = diagramEditPart.getViewer().getEditPartRegistry();
                    List<IAdaptable> viewAdapters = getAdapters(createdViewsToLayout.get(diagramOfOpenedEditor));
                    Map<View, List<IAdaptable>> splitedViewAdapters = splitViewAdaptersAccordingToParent(viewAdapters);
                    for (Entry<View, List<IAdaptable>> viewAdaptersWithSameParent : splitedViewAdapters.entrySet()) {
                        View parentView = viewAdaptersWithSameParent.getKey();
                        List<IAdaptable> childViewsAdapters = viewAdaptersWithSameParent.getValue();
                        IGraphicalEditPart parentEditPart = (IGraphicalEditPart) editPartRegistry.get(parentView);
                        if (parentEditPart != null) {
                            createdViewsToLayoutMap.put(parentEditPart, childViewsAdapters);
                        }
                    }
                }
                createdViewsToLayout.remove(diagramOfOpenedEditor);
            }
        }
    }
}
