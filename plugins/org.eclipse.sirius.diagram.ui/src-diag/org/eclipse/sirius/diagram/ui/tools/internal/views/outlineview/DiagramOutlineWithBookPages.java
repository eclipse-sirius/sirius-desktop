/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.outlineview;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.IObjectActionDelegateWrapper;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePageListener;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.filters.FiltersTableViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers.LayersTableViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineComparator;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineLabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.PageBook;

/**
 * A diagram outline page which has layer page, to hide or show layers, and a
 * filter page to hide or show layers.
 * 
 * @author mchauvin
 */
public class DiagramOutlineWithBookPages extends DiagramOutlinePage {

    /** The id of the layers. 3 */
    protected static final int ID_LAYERS = 3;

    /** The id of the layers. 4 */
    protected static final int ID_FILTERS = 4;

    /** the layers tooltip text. */
    private static final String LAYER_TIP_TEXT = "Layers";

    /** the layers tooltip text. */
    private static final String FILTER_TIP_TEXT = "Filters";

    /** The layers icon descriptor. */
    private static final ImageDescriptor DESC_LAYER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/layers.gif"); //$NON-NLS-1$

    /** The filters icon descriptor. */
    private static final ImageDescriptor DESC_FILTER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/filters.gif"); //$NON-NLS-1$

    /** The show layers action */
    private IAction showLayersAction;

    /** The show layers action */
    private IAction showFiltersAction;

    /** The layers SWT control */
    private Control layers;

    /** The filters SWT control */
    private Control filters;

    /**
     * Constructor.
     * 
     * @param input
     *            the input for the outline tree viewer
     * @param viewer
     *            the graphical viewer the graphical viewer reference
     * @param menuContributions
     *            the popup menu contribution for the outline tree viewer
     */
    public DiagramOutlineWithBookPages(final Object input, final GraphicalViewer viewer, final IObjectActionDelegateWrapper[] menuContributions) {
        super(input, new OutlineLabelProvider(), new OutlineContentProvider(), new OutlineComparator(), viewer, menuContributions);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage#createControls(org.eclipse.ui.part.PageBook)
     */
    @Override
    protected void createControls(final PageBook pb) {
        super.createControls(pb);
        layers = createLayersControl(pb);
        filters = createFiltersControl(pb);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage#configureControls(org.eclipse.jface.action.IToolBarManager)
     */
    @Override
    protected void configureControls(final IToolBarManager tbm) {
        super.configureControls(tbm);
        configureLayers(tbm);
        configureFilters(tbm);
    }

    private void configureLayers(final IToolBarManager tbm) {
        showLayersAction = new Action() {
            @Override
            public void run() {
                showPage(ID_LAYERS);
            }
        };
        showLayersAction.setImageDescriptor(DESC_LAYER);
        showLayersAction.setToolTipText(LAYER_TIP_TEXT);
        tbm.add(showLayersAction);
    }

    private void configureFilters(final IToolBarManager tbm) {
        showFiltersAction = new Action() {
            @Override
            public void run() {
                showPage(ID_FILTERS);
            }
        };
        showFiltersAction.setImageDescriptor(DESC_FILTER);
        showFiltersAction.setToolTipText(FILTER_TIP_TEXT);
        tbm.add(showFiltersAction);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage#showPage(int)
     */
    @Override
    protected void showPage(final int id) {
        switch (id) {
        case ID_FILTERS:
            super.uncheckProvidedActions();
            showLayersAction.setChecked(false);
            showFiltersAction.setChecked(true);
            super.showPage(filters);
            break;
        case ID_LAYERS:
            super.uncheckProvidedActions();
            showFiltersAction.setChecked(false);
            showLayersAction.setChecked(true);
            super.showPage(layers);
            break;
        default:
            showLayersAction.setChecked(false);
            showFiltersAction.setChecked(false);
            super.showPage(id);
            break;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.DiagramOutlinePage#dispose()
     */
    @Override
    public void dispose() {
        if (contentProvider instanceof DiagramOutlinePageListener) {
            this.removeListener((DiagramOutlinePageListener) contentProvider);
        }
        this.filters.dispose();
        this.layers.dispose();
        super.dispose();
    }

    /**
     * Create the main control for layers.
     * 
     * @param parent
     *            the parent
     * @return the created control.
     */
    protected Control createLayersControl(final Composite parent) {

        final Composite control = SWTUtil.createCompositeBothFill(parent, 1, false);
        LayersTableViewer.createLayersTableViewer(control, this.diagramWorkbenchPart);
        return control;
    }

    /**
     * Create the main control for layers.
     * 
     * @param parent
     *            the parent
     * @return the created control.
     */
    protected Control createFiltersControl(final Composite parent) {

        final Composite control = SWTUtil.createCompositeBothFill(parent, 1, false);
        FiltersTableViewer.createFiltersTableViewer(control, this.diagramWorkbenchPart);
        return control;
    }

}
