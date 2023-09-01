/*******************************************************************************
 * Copyright (c) 2009, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.view;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.internal.view.AbstractLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLabelLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.SiriusLayoutDataManagerImpl;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;

/**
 * Manage the AbstractLayoutData during node creation or drag'n'drop. LayoutData are removed after a
 * OperationHistoryEvent#DONE.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @since 0.9.0
 */
public interface SiriusLayoutDataManager {
    /**
     * The shared instance.
     */
    SiriusLayoutDataManager INSTANCE = SiriusLayoutDataManagerImpl.init();

    /**
     * Padding for several created view in same time.
     */
    int PADDING = 30;

    /**
     * Replace the {@link LayoutType#DEFAULT} during the opening of the diagram, if the layout used handle this kind of
     * type (see
     * {@link org.eclipse.sirius.diagram.ui.business.internal.view.SiriusLayoutDataManagerImpl#getArrangeCreatedViewsCommand(List, List, IGraphicalEditPart, String)})...
     */
    String LAYOUT_TYPE_ARRANGE_AT_OPENING = "OPENING"; //$NON-NLS-1$

    /**
     * Default value for the {@link SiriusDiagramUiPreferencesKeys#PREF_NEWLY_CREATED_ELEMENTS_LAYOUT} preference. When
     * elements are not directly positioned by the user on the diagram, they will be arranged diagonally with this
     * value.
     */
    int DIAGONAL_ARRANGEMENT = 0;

    /**
     * When elements are not directly positioned by the user on the diagram, they will be arranged vertically with this
     * value.
     */
    int VERTICAL_ARRANGEMENT = 1;

    /**
     * When elements are not directly positioned by the user on the diagram, they will be arranged horizontally with
     * this value.
     */
    int HORIZONTAL_ARRANGEMENT = 2;

    /**
     * Replace the {@link LayoutType#DEFAULT} to tag an element and avoid moving it during layout, if the layout used
     * handle this kind of type (see
     * {@link org.eclipse.sirius.diagram.ui.business.internal.view.SiriusLayoutDataManagerImpl#getArrangeCreatedViewsCommand(List, List, IGraphicalEditPart, String)})...
     */
    String KEEP_FIXED = "KEEP FIXED"; //$NON-NLS-1$

    /**
     * Add a new AbstractLayoutData of this SiriusLayoutDataManagerImpl.
     * 
     * @param layoutData
     *            A new LayoutData
     */
    void addData(AbstractLayoutData layoutData);

    /**
     * Search recursively in all the LayoutData is there is one which have the element for target.
     * 
     * @param node
     *            The search element
     * @return the corresponding LayoutData or null if not found.
     */
    LayoutData getData(AbstractDNode node);

    /**
     * Search recursively in all the LayoutData is there is one which have the node for target.
     * 
     * @param node
     *            The search element
     * @param searchForParent
     *            true if the data must be retrieve from the node parent (the data must be retrieve from parent for a
     *            creation of an object).
     * @return the corresponding LayoutData or null if not found.
     */
    LayoutData getData(AbstractDNode node, boolean searchForParent);

    /**
     * Search recursively in all the LayoutData is there is one which have the edge for target.
     * 
     * @param edge
     *            The search element
     * @param searchParent
     *            true if the data must be retrieve from the node parent (the data must be retrieve from parent for a
     *            creation of an object)
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    EdgeLayoutData getData(DEdge edge, boolean searchParent);

    /**
     * Search recursively in all the LayoutData if there is one which have the edge of <code>edgeLayoutData</code> for
     * target and is different from <code>edgeLayoutData</code>. An edge layout data stored in the
     * incomingEdgeLayoutDatas ref of its parent data can have an opposite edge layout data in the
     * outgoingEdgeLayoutDatas of the other ends of the edge.
     * 
     * Detail: In case of a move of the source and the target of an edge, the edge has two layout data, one for the
     * source move and another one for the target move. The one for the source move has the points impacted by the
     * source move (the first point and in case of rectilinear routing the second) and the other for the target move
     * (last point and in case of rectilinear routing the second to last).
     * 
     * @param edgeLayoutData
     *            The searched element
     * @param searchParent
     *            true if the data must be retrieve from the node parent (the data must be retrieve from parent for a
     *            creation of an object)
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    EdgeLayoutData getOppositeEdgeLayoutData(EdgeLayoutData edgeLayoutData, boolean searchParent);

    /**
     * Get the Adapter marker to mark GMF View as not to arrange.
     * 
     * @return the Adapter marker to mark GMF View as not to arrange
     */
    Adapter getAdapterMarker();

    /**
     * Get the center Adapter marker to mark GMF View as to arrange in the center of container.
     * 
     * @return the center Adapter marker to mark GMF View as to arrange in the center of container
     */
    Adapter getCenterAdapterMarker();

    /**
     * Search recursively in all the LayoutData is there is one which have the edge for target.
     * 
     * @param edge
     *            The search element
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    EdgeLabelLayoutData getLabelData(DEdge edge);

    /**
     * Initializes a new transactional command with the editing domain in which the marker adapter is added to the GMF
     * view. This marker is added to avoid the arrange of the new view that are already layout by the SiriusLayoutData.
     * 
     * @param domain
     *            my editing domain
     * @param viewAdapter
     *            A {@link IAdaptable} of a {@link View}
     * @return A new transactional command.
     */
    AbstractTransactionalCommand getAddAdapterMakerCommand(TransactionalEditingDomain domain, IAdaptable viewAdapter);

    /**
     * Initializes a new transactional command with the editing domain in which the marker adapter is added to the GMF
     * view. This marker is added to launch the arrange of the new view using center of the container.
     * 
     * @param domain
     *            my editing domain
     * @param viewAdapter
     *            A {@link IAdaptable} of a {@link View}
     * @return A new transactional command.
     */
    AbstractTransactionalCommand getAddCenterAdapterMakerCommand(TransactionalEditingDomain domain, IAdaptable viewAdapter);

    /**
     * Initializes a new transactional command with the editing domain in which the marker adapter is added to the GMF
     * view. This marker is added to launch the arrange of the new view when editor is opening.
     * 
     * @param domain
     *            my editing domain
     * @param createdView
     *            A view
     * @return A new transactional command.
     */
    AbstractTransactionalCommand getAddAdapterMakerOnOpeningCommand(TransactionalEditingDomain domain, View createdView);

    /**
     * layout the new created views.
     * 
     * @param createdViews
     *            the new created views
     * @param centeredCreatedViews
     *            the new created views which must be layouted in the center of their containers
     * @param host
     *            container edit part
     * @return the layout command
     */
    Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> centeredCreatedViews, IGraphicalEditPart host);

    /**
     * layout the new created views.
     * 
     * @param createdViews
     *            the new created views
     * @param centeredCreatedViews
     *            the new created views which must be layouted in the center of their containers
     * @param host
     *            container edit part
     * @param useSpecificLayoutType
     *            true if we have to use a specific layout type to differentiate an arrange selection from the end-user
     *            and an arrange from the opening and a refresh.
     * @return the layout command
     */
    Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> centeredCreatedViews, IGraphicalEditPart host, boolean useSpecificLayoutType);

    /**
     * Layout the new created views.
     * 
     * @param createdViews
     *            the new created views
     * @param centeredCreatedViews
     *            the new created views which must be layouted in the center of their containers
     * @param host
     *            container edit part
     * @param specificLayoutType
     *            the layout type (see {@link LayoutType#DEFAULT},
     *            {@link SiriusLayoutDataManager#LAYOUT_TYPE_ARRANGE_AT_OPENING},
     *            {@link SiriusLayoutDataManager#KEEP_FIXED})
     * @return the layout command
     */
    Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> centeredCreatedViews, IGraphicalEditPart host, String specificLayoutType);

    /**
     * layout the new created views after opening the editor.
     * 
     * @param host
     *            container edit part
     * @return the layout command
     */
    Command getArrangeCreatedViewsOnOpeningCommand(IGraphicalEditPart host);

    /**
     * Return if the view contains a layout adapter, if it contains, layout is launch on the view.
     * 
     * @param view
     *            the view
     * @return if the view contains a layout adapter, if it contains, layout is launch on the view
     */
    boolean hasToArrange(View view);

    /**
     * Create arrange command for one View.
     * 
     * @param request
     *            arrange request
     * @param host
     *            editpart
     * @return the arrange command
     */
    Command getArrangeCommand(ArrangeRequest request, EditPart host);

    /**
     * Store a set of views to layout for a next command in another Transaction.
     * 
     * @param gmfDiagram
     *            the {@link Diagram} for which childs views must be layouted
     * @param createdViewsToLayout
     *            a set of views to layout
     */
    void addCreatedViewsToLayout(Diagram gmfDiagram, LinkedHashSet<View> createdViewsToLayout);

    /**
     * Get the set of created views to layout per Diagram to be layouted. After use of this map the client must clear it
     * to avoid other subsequent arrange command to be executed on the same already created views.
     * 
     * @return the set of created views to layout per Diagram to be layouted
     */
    Map<Diagram, Set<View>> getCreatedViewsToLayout();

    /**
     * Change the mode of the consumption. The data can be consume even if there is already consume. This can be useful,
     * for example, in case of several preCommitListener that want to access the data.<BR>
     * This change must be temporary and must be restored to default value (false).
     * 
     * @param ignoreConsumeState
     *            true to access to data already consumed.
     */
    void setIgnoreConsumeState(boolean ignoreConsumeState);

    /**
     * Return the first data if it exists.
     * 
     * @return the first data if it exists.
     */
    Option<AbstractLayoutData> getData();

    /**
     * Get the list of created views with center layout.
     * 
     * @return the list of views
     */
    Map<Diagram, Set<View>> getCreatedViewWithCenterLayout();

    /**
     * Add a view in the list.
     * 
     * @param gmfDiagram
     *            the {@link Diagram} for which children views must be layouted in the center of the container
     * @param view
     *            the view to set
     */
    void addCreatedViewWithCenterLayout(Diagram gmfDiagram, LinkedHashSet<View> view);

}
