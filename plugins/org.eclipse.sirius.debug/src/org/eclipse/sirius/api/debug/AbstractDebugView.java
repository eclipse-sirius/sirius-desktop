/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.api.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/**
 * A simple view synchronized with the Eclipse selection, used to show arbitrary computed information from the currently
 * selected edit part of a diagram and launch actions on them. This view is targeted to developers to help debugging, it
 * should not be packaged in the final product and should not be available to end users.
 * 
 * @author pcdavid
 */
@SuppressWarnings("nls")
public abstract class AbstractDebugView extends ViewPart implements ISelectionListener {

    private static class Action {
        final String label;

        final Runnable body;

        public Action(String label, Runnable body) {
            this.label = label;
            this.body = body;
        }
    }

    /**
     * The text area in which information is placed.
     */
    private Text info;

    private List<Action> actions = new ArrayList<>();

    private Combo actionSelector;

    /**
     * The currently selection object.
     */
    public Object selection;

    @Override
    public void createPartControl(Composite parent) {
        getSite().getPage().addSelectionListener(this);
        GridLayout layout = new GridLayout(1, false);
        parent.setLayout(layout);

        info = new Text(parent, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        info.setText("Sirius Debug View");
        info.setLayoutData(new GridData(GridData.FILL_BOTH));
        info.setFont(JFaceResources.getFont("org.eclipse.debug.ui.consoleFont"));

        Group buttons = new Group(parent, SWT.SHADOW_ETCHED_IN);
        buttons.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        buttons.setLayout(new RowLayout(SWT.HORIZONTAL));
        actionSelector = new Combo(buttons, SWT.DROP_DOWN | SWT.READ_ONLY);
        Button actionLauncher = new Button(buttons, SWT.PUSH);
        actionLauncher.setText("Run");
        actionLauncher.addSelectionListener(SelectionListener.widgetSelectedAdapter(evt -> {
            int idx = actionSelector.getSelectionIndex();
            actions.get(idx).body.run();
        }));
        createActionButtons();
    }

    /**
     * Helper method to add an action button to the view.
     */
    protected void addAction(String name, final Runnable body) {
        Action action = new Action(name, body);
        actions.add(action);
        actionSelector.add(action.label);
    }

    @Override
    public void dispose() {
        getSite().getPage().removeSelectionListener(this);
        super.dispose();
    }

    @Override
    public void setFocus() {
        // Do nothing.
    }

    /**
     * Update the <code>selection</code> field and fill the info area with the corresponding details.
     */
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        Optional<Object> selected = getSelectedElement(selection);
        if (selected.isPresent()) {
            this.selection = selected.get();
            this.info.setText(getTextFor(this.selection));
        }
    }

    /**
     * Get the main object selected from the Eclipse ISelection.
     */
    private Optional<Object> getSelectedElement(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection iss = (IStructuredSelection) selection;
            return Optional.of(iss.getFirstElement());
        }
        return Optional.empty();
    }

    /**
     * Opens a dialog box to ask the user for a string. Useful for actions which need some additional data.
     */
    protected String askStringFromUser(String title, String message, String initialValue) {
        InputDialog dlg = new InputDialog(getSite().getShell(), title, message, initialValue, null);
        if (dlg.open() == Window.OK) {
            return dlg.getValue();
        } else {
            return null;
        }
    }

    protected AdapterFactory getAdapterFactory() {
        List<AdapterFactory> factories = new ArrayList<>();
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(factories);
    }

    /**
     * Sets the text shown in the view's text area.
     * 
     * @param text
     *            the text to show in the view's text area.
     */
    public void setText(String text) {
        info.setText(text);
    }

    /**
     * Returns the text to show in the main text area for the specified object.
     */
    protected abstract String getTextFor(Object obj);

    /**
     * Contribute the action buttons using {@link #addAction(String, Runnable)}.
     */
    protected abstract void createActionButtons();
}
