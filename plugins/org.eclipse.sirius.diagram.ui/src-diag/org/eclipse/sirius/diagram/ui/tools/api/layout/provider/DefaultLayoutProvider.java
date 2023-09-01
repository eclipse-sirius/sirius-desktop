/*******************************************************************************
 * Copyright (c) 2007, 2008, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;

import com.google.common.collect.Iterables;

/**
 * Default layout provider. It delegates the operation using the {@link LayoutService}.
 * 
 * @author ymortier
 */
public class DefaultLayoutProvider extends AbstractLayoutProvider {
    /**
     * A layout configuration that the provider should use to configure its algorithm.
     */
    protected CustomLayoutConfiguration layoutConfiguration;

    @Override
    public boolean provides(IOperation operation) {
        if (operation instanceof ILayoutNodeOperation) {
            ILayoutNodeOperation layoutNodeOperation = (ILayoutNodeOperation) operation;
            IAdaptable layoutHint = layoutNodeOperation.getLayoutHint();
            String layoutType = layoutHint.getAdapter(String.class);
            boolean canHandleLayoutType = LayoutType.DEFAULT.equals(layoutType) || SiriusLayoutDataManager.LAYOUT_TYPE_ARRANGE_AT_OPENING.equals(layoutType)
                    || SiriusLayoutDataManager.KEEP_FIXED.equals(layoutType);
            return canHandleLayoutType && isLayoutForSiriusDiagram(layoutNodeOperation);
        }
        return false;
    }

    /**
     * Sets the layout configuration that the provider should use to configure its algorithm.
     * 
     * @param layoutConfiguration
     *            the layout configuration that the provider should use to configure its algorithm.
     */
    public void setLayoutConfiguration(CustomLayoutConfiguration layoutConfiguration) {
        this.layoutConfiguration = layoutConfiguration;
    }

    /**
     * Check that at least one of the nodes we'are asked to layout is part of a Sirius diagram.
     */
    private boolean isLayoutForSiriusDiagram(ILayoutNodeOperation layoutOperation) {
        for (ILayoutNode node : Iterables.filter(layoutOperation.getLayoutNodes(), ILayoutNode.class)) {
            Diagram diag = node.getNode() != null ? node.getNode().getDiagram() : null;
            if (diag != null && DiagramPackage.Literals.DDIAGRAM.isInstance(diag.getElement())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        DslCommonPlugin.PROFILER.startWork(ARRANGE_ALL);
        final CompoundCommand cc = new CompoundCommand();
        final Map<EditPart, List<EditPart>> allLayouts = this.split(selectedObjects);
        final Iterator<Entry<EditPart, List<EditPart>>> iterEntries = allLayouts.entrySet().iterator();
        while (iterEntries.hasNext()) {
            final Entry<EditPart, List<EditPart>> currentEntry = iterEntries.next();
            final IGraphicalEditPart container = (IGraphicalEditPart) currentEntry.getKey();
            final List<EditPart> children = currentEntry.getValue();
            if (container instanceof DiagramEditPart) {
                final DiagramEditPart diagramEditPart = (DiagramEditPart) container;
                children.addAll(diagramEditPart.getConnections());
            }
            final LayoutProvider airLayoutProvider = LayoutService.getProvider(container);
            if (airLayoutProvider != null) {
                final AbstractLayoutEditPartProvider gmfLayoutProvider = airLayoutProvider.getLayoutNodeProvider(container);
                if (gmfLayoutProvider != null) {
                    if (gmfLayoutProvider instanceof AbstractLayoutProvider) {
                        ((AbstractLayoutProvider) gmfLayoutProvider).setViewsToChangeBoundsRequest(this.getViewsToChangeBoundsRequest());
                    }
                    cc.add(gmfLayoutProvider.layoutEditParts(children, layoutHint));
                    if (gmfLayoutProvider instanceof AbstractLayoutProvider) {
                        this.getViewsToChangeBoundsRequest().putAll(((AbstractLayoutProvider) gmfLayoutProvider).getViewsToChangeBoundsRequest());
                    }
                }
            }
        }
        DslCommonPlugin.PROFILER.stopWork(ARRANGE_ALL);
        return cc;
    }

    /**
     * Split all edit parts according to their container. Returns a map (container -> children to layout).
     * 
     * @param editParts
     *            the
     * @return a map (container -> children to layout).
     */
    protected Map<EditPart, List<EditPart>> split(final List<EditPart> editParts) {
        final Map<EditPart, List<EditPart>> result = new HashMap<EditPart, List<EditPart>>();
        final Iterator<EditPart> iterEditParts = editParts.listIterator();
        while (iterEditParts.hasNext()) {
            final Object next = iterEditParts.next();
            if (next instanceof ConnectionEditPart) {
                final ConnectionEditPart connectionEditPart = (ConnectionEditPart) next;
                final EditPart container = this.getDiagramEditPart(connectionEditPart);
                DefaultLayoutProvider.addToMap(result, container, connectionEditPart);
            } else if (next instanceof IGraphicalEditPart) {
                final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) next;
                final EditPart container = graphicalEditPart.getParent();
                DefaultLayoutProvider.addToMap(result, container, graphicalEditPart);
            } else {
                iterEditParts.remove();
            }
        }
        return result;
    }

    /**
     * Add the object <code>value</code> to the list of objects that is in the map <code>map</code> for the key
     * <code>key</code>.
     * 
     * @param map
     *            the map.
     * @param key
     *            the key.
     * @param value
     *            the value to add.
     * @since 0.9.0
     */
    protected static void addToMap(final Map<EditPart, List<EditPart>> map, final EditPart key, final EditPart value) {
        List<EditPart> values = map.get(key);
        if (values == null) {
            values = new LinkedList<EditPart>();
            map.put(key, values);
        }
        values.add(value);
    }

    /**
     * Remove all elements that are not an instance of the specified type from the List <code>list</code>.
     * 
     * @param list
     *            the list to filter.
     * @param type
     *            the type to get.
     */
    protected static void retainType(final List<?> list, final Class<?> type) {
        final Iterator<?> iterElements = list.iterator();
        while (iterElements.hasNext()) {
            if (!type.isInstance(iterElements.next())) {
                iterElements.remove();
            }
        }
    }

    /**
     * Return the diagram edit part of the specified edit part or <code>null</code> if the diagram edit part can not be
     * retrieved.
     * 
     * @param editPart
     *            an edit part.
     * @return the diagram edit part of the specified edit part or <code>null</code> if the diagram edit part can not be
     *         retrieved.
     */
    protected DiagramEditPart getDiagramEditPart(final EditPart editPart) {
        EditPart current = editPart;
        while (current != null) {
            if (current instanceof DiagramEditPart) {
                return (DiagramEditPart) current;
            }
            if (current instanceof ConnectionEditPart) {
                current = ((ConnectionEditPart) current).getSource();
            } else {
                current = current.getParent();
            }
        }
        return null;
    }

    /**
     * Return all GMF views of the specified {@link EditPart}s. The key of the map is a view and the value is the
     * associated {@link EditPart}.
     * 
     * @param editParts
     *            the edit parts.
     * @return all GMF views of the specified {@link EditPart}s.
     * @param <T>
     *            a class which implements {@link EditPart}
     */
    protected <T extends EditPart> Map<View, T> getViews(final List<T> editParts) {
        final Map<View, T> result = new HashMap<View, T>();
        final Iterator<T> iterEditParts = editParts.iterator();
        while (iterEditParts.hasNext()) {
            final T next = iterEditParts.next();
            if (next.getModel() instanceof View) {
                result.put((View) next.getModel(), next);
            }
        }
        return result;
    }

}
