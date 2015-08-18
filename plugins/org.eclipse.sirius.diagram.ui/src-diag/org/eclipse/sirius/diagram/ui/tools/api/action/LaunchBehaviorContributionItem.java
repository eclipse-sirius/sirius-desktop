/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.action;

import java.util.Iterator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Add a button to launch all behaviors.
 * 
 * @author ymortier
 */
public class LaunchBehaviorContributionItem extends ContributionItem {

    /**
     * ID for concern contribution.
     */
    public static final String CONCERN_CONTRIBUTION_ID = "ConcernContributionBehavior"; //$NON-NLS-1$

    private final IPartService service;

    private IPartListener partListener;

    private EditPart viewPointEditPart;

    private Button button;

    private ToolItem toolitem;

    /**
     * Constructor for ComboToolItem.
     * 
     * @param partService
     *            used to add a PartListener
     */
    public LaunchBehaviorContributionItem(final IPartService partService) {
        super(CONCERN_CONTRIBUTION_ID);
        service = partService;
        Assert.isNotNull(partService);
        partListener = new IPartListener() {
            public void partActivated(final IWorkbenchPart part) {
                final EditPart editPArt = (EditPart) part.getAdapter(EditPart.class);
                if (editPArt instanceof RenderedDiagramRootEditPart) {
                    final RenderedDiagramRootEditPart root = (RenderedDiagramRootEditPart) editPArt;
                    final Iterator<?> iterChildren = root.getChildren().iterator();
                    while (iterChildren.hasNext()) {
                        final Object child = iterChildren.next();
                        if (child instanceof DDiagramEditPart) {
                            viewPointEditPart = (GraphicalEditPart) child;
                        }
                    }
                }

            }

            public void partBroughtToTop(final IWorkbenchPart p) {
            }

            public void partClosed(final IWorkbenchPart p) {
            }

            public void partDeactivated(final IWorkbenchPart p) {
            }

            public void partOpened(final IWorkbenchPart p) {
            }
        };
        partService.addPartListener(partListener);
    }

    /**
     * Creates and returns the control for this contribution item under the
     * given parent composite.
     * 
     * @param parent
     *            the parent composite
     * @return the new control
     */
    protected Control createControl(final Composite parent) {
        button = new Button(parent, SWT.PUSH);
        button.setText("Launch Behavior");
        button.setSize(30, 20);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Request request = new Request(RequestConstants.REQ_LAUNCH_RULE_TOOL);
                viewPointEditPart.performRequest(request);
            }
        });
        final Image image = DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.findImageDescriptor(DiagramImagesPath.GO_IMG));
        button.setImage(image);
        button.setSize(image.getBounds().width + button.getBorderWidth(), image.getBounds().height + button.getBorderWidth());
        return button;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.ContributionItem#dispose()
     */
    @Override
    public void dispose() {
        if (partListener == null) {
            return;
        }
        if (button != null && !button.isDisposed()) {
            button.dispose();
        }
        if (service != null) {
            service.removePartListener(this.partListener);
        }
        button = null;
        partListener = null;
    }

    /**
     * The control item implementation of this <code>IContributionItem</code>
     * method calls the <code>createControl</code> framework method. Subclasses
     * must implement <code>createControl</code> rather than overriding this
     * method.
     * 
     * @param parent
     *            The parent of the control to fill
     */
    @Override
    public final void fill(final Composite parent) {
        createControl(parent);
    }

    /**
     * The control item implementation of this <code>IContributionItem</code>
     * method throws an exception since controls cannot be added to menus.
     * 
     * @param parent
     *            The menu
     * @param index
     *            Menu index
     */
    @Override
    public final void fill(final Menu parent, final int index) {
        Assert.isTrue(false, "Can't add a control to a menu"); //$NON-NLS-1$
    }

    /**
     * The control item implementation of this <code>IContributionItem</code>
     * method calls the <code>createControl</code> framework method to create a
     * control under the given parent, and then creates a new tool item to hold
     * it. Subclasses must implement <code>createControl</code> rather than
     * overriding this method.
     * 
     * @param parent
     *            The ToolBar to add the new control to
     * @param index
     *            Index
     */
    @Override
    public void fill(final ToolBar parent, final int index) {
        toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
        final Control control = createControl(parent);
        toolitem.setControl(control);
    }
}
