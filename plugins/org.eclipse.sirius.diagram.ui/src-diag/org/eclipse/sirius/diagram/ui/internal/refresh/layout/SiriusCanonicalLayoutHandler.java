/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
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
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
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
                    Set<View> set = SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout().get(diagramEditPart.getDiagramView());
                    if (set != null) {
                        set.clear();
                    }
                    Set<View> set2 = SiriusLayoutDataManager.INSTANCE.getCreatedViewWithCenterLayout().get(diagramEditPart.getDiagramView());
                    if (set2 != null) {
                        set2.clear();
                    }

                }
            }

            if (!specificArrangeAtFirstOpening) {
                Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart, SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout());
                Map<IGraphicalEditPart, List<IAdaptable>> centeredCreatedViewsToLayoutMap = getCreatedViewsToLayoutByContainerPart(diagramEditPart,
                        SiriusLayoutDataManager.INSTANCE.getCreatedViewWithCenterLayout());
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
                Command layoutCommand = getLayoutCommand(createdViewsToLayoutMap, centeredCreatedViewsToLayoutMap, editingDomain, layoutType);
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

    /**
     * Gets BorderNodes of an adapted view.
     * 
     * @param viewAdapter
     *            the adapted view
     * @return the set of BorderNodes of the adapted view.
     */
    private static Set<DNode> getBorderNodes(IAdaptable viewAdapter) {
        Set<DNode> borderNodes;
        View createdView = viewAdapter.getAdapter(View.class);
        if (createdView != null && createdView.getElement() instanceof AbstractDNode dnode) {
            borderNodes = new HashSet<>(dnode.getOwnedBorderedNodes());
        } else {
            borderNodes = Collections.emptySet();
        }
        return borderNodes;
    }

    /**
     * Gets BorderNodes of an EditPart.
     * 
     * @param elementEditPart
     *            the EditPart
     * @return the set of BorderNodes of the EditPart.
     */
    private static Set<DNode> getBorderNodes(IDiagramElementEditPart elementEditPart) {
        Set<DNode> borderNodes;
        if (elementEditPart.resolveDiagramElement() instanceof AbstractDNode dnode) {
            borderNodes = new HashSet<>(dnode.getOwnedBorderedNodes());
        } else {
            borderNodes = Collections.emptySet();
        }
        return borderNodes;
    }

    /**
     * Check if an EditPart has BorderNodes.
     * 
     * @param elementEditPart
     *            the EditPart
     * @return {@code true} if the EditPart has BorderNodes; {@code false} otherwise.
     */
    private static boolean hasBorderNodes(IDiagramElementEditPart elementEditPart) {
        return !getBorderNodes(elementEditPart).isEmpty();
    }

    /**
     * Check if an adapted view has BorderNodes.
     * 
     * @param viewAdapter
     *            the adapted view
     * @return {@code true} if the adapted view has BorderNodes; {@code false} otherwise.
     */
    private static boolean hasBorderNodes(IAdaptable viewAdapter) {
        return !getBorderNodes(viewAdapter).isEmpty();
    }

    /**
     * Used to get all adapted views which has border nodes and can be associated to the specified compartment.
     * 
     * @param compartmentEP
     *            the compartment
     * @param viewAdapters
     *            all adapted views associated to the compartment
     * @return the map of the Compartment EP which contains the BorderedNode EditPart associated to its adapted view
     */
    private static Map<IGraphicalEditPart, List<IAdaptable>> getBorderedNodesFromCompartment(AbstractDNodeContainerCompartmentEditPart compartmentEP, List<IAdaptable> viewAdapters) {
        List<IAdaptable> existingList = new ArrayList<>();
        for (IAdaptable viewAdapter : viewAdapters) {
            if (hasBorderNodes(viewAdapter)) {
                View createdView = viewAdapter.getAdapter(View.class);
                if (createdView != null && createdView.getElement() instanceof AbstractDNode) {
                    existingList.add(viewAdapter);
                }
            }
        }

        Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        borderedNodesToLayoutMap.put(compartmentEP, existingList);
        return borderedNodesToLayoutMap;
    }

    private static List<View> getViews(Collection<IAdaptable> viewAdapters) {
        return viewAdapters.stream().map(viewAdapter -> viewAdapter.getAdapter(View.class)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Used to get the BorderedNode from a BorderNode.
     * 
     * @param borderNodeEP
     *            the border node
     * @return the map of the BorderedNode EditPart associated to its adapted view
     */
    private static Map<IGraphicalEditPart, List<IAdaptable>> getBorderedNodesFromBorderNode(AbstractDiagramBorderNodeEditPart borderNodeEP) {
        Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        if (borderNodeEP.getParent() instanceof IDiagramElementEditPart parentEP && hasBorderNodes(parentEP) && parentEP.getModel() instanceof View parentView) {
            List<IAdaptable> viewAdapters = List.of(new EObjectAdapter(parentView));
            borderedNodesToLayoutMap.put(parentEP, viewAdapters);
        }
        return borderedNodesToLayoutMap;
    }

    /**
     * Used to perform an intelligible "borderedNodesToLayout.putAll(newBorderedNodesToLayout)". Since the value of the
     * map is a {@code List<IAdaptable>}, we need to check that the adapted view we want to add is not present in the
     * list.
     * 
     * @param borderedNodesToLayout
     *            the map to complete
     * @param newBorderedNodesToLayout
     *            the Map for which we want to put all the elements in the other
     */
    private static void addBorderedNodeToLayout(Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesToLayout, Map<IGraphicalEditPart, List<IAdaptable>> newBorderedNodesToLayout) {
        List<View> existingViews = getViews(borderedNodesToLayout.values().stream().flatMap(List::stream).toList());
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entryToLayout : newBorderedNodesToLayout.entrySet()) {
            IGraphicalEditPart newEditPart = entryToLayout.getKey();

            List<IAdaptable> existingAdapters = borderedNodesToLayout.get(newEditPart);
            List<View> potentialNewViews = getViews(entryToLayout.getValue());
            for (View view : potentialNewViews) {
                if (!existingViews.contains(view)) {
                    if (existingAdapters == null) {
                        existingAdapters = new ArrayList<>();
                        borderedNodesToLayout.put(newEditPart, existingAdapters);
                    }
                    existingAdapters.add(new EObjectAdapter(view));
                }
            }
        }
    }

    /**
     * Make a copy of "allViewsToLayout" parameter without the elements whose layout is already managed. The
     * allViewsToLayout parameter cannot be modified with a remove, as it is potentially being iterated over.
     * 
     * @param managedLayout
     *            elements whose layout is already managed
     * @param allViewsToLayout
     *            all views to layout
     * @return a copy of "allViewsToLayout" parameter without the elements whose layout is already managed
     */
    private static Map<IGraphicalEditPart, List<IAdaptable>> removeViewsAdaptersManagedLayout(Map<IGraphicalEditPart, List<IAdaptable>> managedLayout,
            Map<IGraphicalEditPart, List<IAdaptable>> allViewsToLayout) {
        Map<IGraphicalEditPart, List<IAdaptable>> remainingViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        List<View> managedViews = getViews(managedLayout.values().stream().flatMap(List::stream).toList());
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entryToLayout : allViewsToLayout.entrySet()) {
            List<IAdaptable> viewsNotManaged = new ArrayList<>();
            for (IAdaptable viewAdapter : entryToLayout.getValue()) {
                View view = viewAdapter.getAdapter(View.class);
                if (!managedViews.contains(view)) {
                    viewsNotManaged.add(viewAdapter);
                }
            }
            if (!viewsNotManaged.isEmpty()) {
                remainingViewsToLayoutMap.put(entryToLayout.getKey(), viewsNotManaged);
            }
        }
        return remainingViewsToLayoutMap;
    }

    /**
     * This method helps to layout bordered nodes with border nodes. Border nodes are not laid out, but their parent
     * bordered node or the compartment containing their parent is laid out.
     * 
     * @param createdViewsToLayoutMap
     *            the map of all adaptable GMF views associated to the edit part to layout
     * @param editingDomain
     *            the editing domain
     * @param compoundCommand
     *            the command
     * @return the remaining views and edit part to lay out
     */
    private static Map<IGraphicalEditPart, List<IAdaptable>> layoutBorderAndBorderedNodes(Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap, TransactionalEditingDomain editingDomain,
            CompoundCommand compoundCommand) {
        Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesToLayout = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>();
        Map<IGraphicalEditPart, List<IAdaptable>> remainingViewsToLayoutMap = new LinkedHashMap<IGraphicalEditPart, List<IAdaptable>>(createdViewsToLayoutMap);
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entryToLayout : createdViewsToLayoutMap.entrySet()) {
            IGraphicalEditPart editPart = entryToLayout.getKey();
            List<IAdaptable> viewAdapters = entryToLayout.getValue();
            if (editPart instanceof AbstractDNodeContainerCompartmentEditPart compartmentEP) {
                // CompartmentEditPart case: we should only layout GMF Views with BorderNodes
                Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesFromCompartment = getBorderedNodesFromCompartment(compartmentEP, viewAdapters);
                remainingViewsToLayoutMap = removeViewsAdaptersManagedLayout(borderedNodesFromCompartment, remainingViewsToLayoutMap);
                addBorderedNodeToLayout(borderedNodesToLayout, borderedNodesFromCompartment);
            } else if (editPart instanceof IDiagramElementEditPart elementEP && hasBorderNodes(elementEP)) {
                // BorderedNode case: laid out if there is BorderNodes.
                remainingViewsToLayoutMap = removeViewsAdaptersManagedLayout(Map.of(elementEP, viewAdapters), remainingViewsToLayoutMap);
                addBorderedNodeToLayout(borderedNodesToLayout, Map.of(elementEP, viewAdapters));
            } else if (editPart instanceof AbstractDiagramBorderNodeEditPart borderNodeEP) {
                // BorderNode case: they are not laid out, but their parent may.
                remainingViewsToLayoutMap = removeViewsAdaptersManagedLayout(Map.of(borderNodeEP, viewAdapters), remainingViewsToLayoutMap);
                Map<IGraphicalEditPart, List<IAdaptable>> borderedNodesFromBorderNode = getBorderedNodesFromBorderNode(borderNodeEP);
                remainingViewsToLayoutMap = removeViewsAdaptersManagedLayout(borderedNodesFromBorderNode, remainingViewsToLayoutMap);
                addBorderedNodeToLayout(borderedNodesToLayout, borderedNodesFromBorderNode);
            }
        }
        // Layout BorderedNodes or their Compartment.
        for (Entry<IGraphicalEditPart, List<IAdaptable>> borderedNodeEntry : borderedNodesToLayout.entrySet()) {
            IGraphicalEditPart editPart = borderedNodeEntry.getKey();
            List<IAdaptable> childViewsAdapters = borderedNodeEntry.getValue();
            // The KEEP_FIXED layout is used to avoid moving the BorderedNode, it should be located where the user
            // clicked.
            Command siriusCanonicalLayoutCommand = new SiriusCanonicalLayoutCommand(editingDomain, editPart, childViewsAdapters, null, SiriusLayoutDataManager.KEEP_FIXED);
            compoundCommand.append(siriusCanonicalLayoutCommand);
        }
        return remainingViewsToLayoutMap;
    }

    private static Command getLayoutCommand(Map<IGraphicalEditPart, List<IAdaptable>> createdViewsToLayoutMap, Map<IGraphicalEditPart, List<IAdaptable>> centeredCreatedViewsToLayoutMap,
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
        Map<IGraphicalEditPart, List<IAdaptable>> remainingViewsToLayoutMap = layoutBorderAndBorderedNodes(createdViewsToLayoutMap, editingDomain, compoundCommand);
        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : remainingViewsToLayoutMap.entrySet()) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            Command viewpointLayoutCanonicalSynchronizerCommand = new SiriusCanonicalLayoutCommand(editingDomain, parentEditPart, childViewsAdapters, null, specificLayoutType);
            compoundCommand.append(viewpointLayoutCanonicalSynchronizerCommand);
        }

        for (Entry<IGraphicalEditPart, List<IAdaptable>> entry : Iterables.filter(centeredCreatedViewsToLayoutMap.entrySet(), typeOfElementToLayout)) {
            IGraphicalEditPart parentEditPart = entry.getKey();
            List<IAdaptable> childViewsAdapters = entry.getValue();
            Command viewpointLayoutCanonicalSynchronizerCommand = new SiriusCanonicalLayoutCommand(editingDomain, parentEditPart, null, childViewsAdapters, specificLayoutType);
            compoundCommand.append(viewpointLayoutCanonicalSynchronizerCommand);
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
        Command layoutCommand = getLayoutCommand(createdViewsToLayoutMap, createdViewsWithSpecialLayoutMap, editingDomain, layoutType);
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
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout();

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
        Map<Diagram, Set<View>> createdViewsToLayout = SiriusLayoutDataManager.INSTANCE.getCreatedViewWithCenterLayout();

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
