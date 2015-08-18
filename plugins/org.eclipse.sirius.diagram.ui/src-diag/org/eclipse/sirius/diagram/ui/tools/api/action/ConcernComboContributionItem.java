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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SetCurrentConcernCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SetDefaultConcernCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * A ControlContribution that uses a {@link org.eclipse.swt.widgets.Combo} as
 * its control.
 * 
 * @author ymortier
 */
public class ConcernComboContributionItem extends ContributionItem {

    /**
     * ID for concern contribution.
     */
    public static final String CONCERN_CONTRIBUTION_ID = "ConcernContribution"; //$NON-NLS-1$

    private boolean forceSetText;

    private Combo combo;

    private final String[] initStrings;

    private ToolItem toolitem;

    private DDiagram diagram;

    private final IPartService service;

    private IPartListener partListener;

    private TransactionalEditingDomain domain;

    private ResourceSetListener listener;

    /**
     * Constructor for ComboToolItem.
     * 
     * @param partService
     *            used to add a PartListener
     * @param initString
     *            the initial string displayed in the combo
     */
    public ConcernComboContributionItem(final IPartService partService, final String initString) {
        this(partService, new String[] { initString });
    }

    /**
     * Constructor for ComboToolItem.
     * 
     * @param partService
     *            used to add a PartListener
     * @param initStrings
     *            the initial string displayed in the combo
     */
    public ConcernComboContributionItem(final IPartService partService, final String[] initStrings) {
        super(CONCERN_CONTRIBUTION_ID);
        this.initStrings = initStrings;
        service = partService;
        partListener = new IPartListener() {
            public void partActivated(final IWorkbenchPart part) {

                if (part instanceof DDiagramEditor) {
                    DDiagramEditor editor = (DDiagramEditor) part;
                    DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
                    domain = (TransactionalEditingDomain) editor.getAdapter(EditingDomain.class);
                    setDiagram(editorDiagram);
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
     * Get the current diagram.
     * 
     * @return current diagram
     */
    public DDiagram getDiagram() {
        return diagram;
    }

    private void diagramChanged() {
        if (Display.getCurrent() == null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    refresh(false);
                }
            });
        } else {
            refresh(false);
        }
    }

    private String[] getPickableConcerns() {
        if (getDiagram() != null && getDiagram().getDescription() != null && getDiagram().getDescription().getConcerns() != null) {
            final String[] data = new String[getDiagram().getDescription().getConcerns().getOwnedConcernDescriptions().size()];
            for (int i = 0; i < getDiagram().getDescription().getConcerns().getOwnedConcernDescriptions().size(); i++) {
                final ConcernDescription desc = getDiagram().getDescription().getConcerns().getOwnedConcernDescriptions().get(i);
                data[i] = desc.getName();
            }
            return data;

        } else {
            return new String[0];
        }
    }

    private void refresh(final boolean repopulateCombo) {
        if (combo == null || combo.isDisposed()) {
            return;
        }
        // $TODO GTK workaround
        try {
            if (diagram == null || domain == null) {
                combo.setEnabled(false);
                combo.setText(StringUtil.EMPTY_STRING);
            } else {
                if (repopulateCombo) {
                    combo.setItems(getPickableConcerns());
                }

                String currentConcern = StringUtil.EMPTY_STRING;
                if (getDiagram().getCurrentConcern() != null) {
                    currentConcern = getDiagram().getCurrentConcern().getName();
                }
                final int index = combo.indexOf(currentConcern);
                if (index == -1 || forceSetText) {
                    combo.setText(currentConcern);
                } else {
                    combo.select(index);
                }
                combo.setEnabled(true);
            }
        } catch (final SWTException exception) {
            if (!"gtk".equals(SWT.getPlatform())) { //$NON-NLS-1$
                throw exception;
            }
        }
    }

    /**
     * Computes the width required by control.
     * 
     * @param control
     *            The control to compute width
     * @return int The width required
     */
    protected int computeWidth(final Control control) {
        return control.computeSize(100, 20, true).x;
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
        combo = new Combo(parent, SWT.DROP_DOWN);
        combo.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                handleWidgetSelected(e);
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                handleWidgetDefaultSelected(e);
            }
        });
        combo.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                // do nothing
            }

            public void focusLost(final FocusEvent e) {
                refresh(false);
            }
        });

        // Initialize width of combo
        combo.setItems(initStrings);
        toolitem.setWidth(computeWidth(combo));
        combo.setToolTipText("Current concern");
        refresh(true);
        return combo;
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
        service.removePartListener(partListener);
        removeSemanticListener();
        diagram = null;
        combo = null;
        partListener = null;
        domain = null;
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

    /**
     * Sets the DDiagram.
     * 
     * @param dia
     *            The diagram
     */
    public void setDiagram(final DDiagram dia) {
        if (diagram == dia) {
            return;
        }

        if (domain == null) {
            domain = TransactionUtil.getEditingDomain(dia);
        }

        diagram = dia;
        removeSemanticListener();
        addSemanticListener();
        refresh(true);
    }

    private void addSemanticListener() {
        listener = new ResourceSetListenerImpl() {

            @Override
            public NotificationFilter getFilter() {
                return NotificationFilter.NOT_TOUCH.and(NotificationFilter.createNotifierFilter(diagram));
            }

            @Override
            public boolean isPostcommitOnly() {
                return true;
            }

            @Override
            public void resourceSetChanged(ResourceSetChangeEvent event) {
                diagramChanged();
            }
        };
        domain.addResourceSetListener(listener);
    }

    private void removeSemanticListener() {
        if (domain != null) {
            domain.removeResourceSetListener(listener);
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
     */
    private void handleWidgetDefaultSelected(final SelectionEvent event) {
        if (diagram != null) {
            if (combo.getSelectionIndex() >= 0) {
                setCurrentConcern(combo.getItem(combo.getSelectionIndex()));
            } else {
                setCurrentConcern(combo.getText());
            }
        }
        refresh(false);
    }

    private void setCurrentConcern(final String item) {
        boolean foundConcern = false;
        if (item != null) {
            if (getDiagram() != null && getDiagram().getDescription() != null && getDiagram().getDescription().getConcerns() != null) {
                final Iterator<ConcernDescription> it = getDiagram().getDescription().getConcerns().getOwnedConcernDescriptions().iterator();
                while (it.hasNext()) {
                    final ConcernDescription desc = it.next();
                    if (desc.getName().equals(item)) {
                        foundConcern = true;
                        domain.getCommandStack().execute(new SetCurrentConcernCommand(domain, diagram, desc));
                    }
                }
            }
        }
        if (!foundConcern) {
            domain.getCommandStack().execute(new SetDefaultConcernCommand(domain, diagram));
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
     */
    private void handleWidgetSelected(final SelectionEvent event) {
        forceSetText = true;
        handleWidgetDefaultSelected(event);
        forceSetText = false;
    }
}
