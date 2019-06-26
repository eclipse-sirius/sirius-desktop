/*******************************************************************************
 * Copyright (c) 2008, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog which lets user select representations among a list of candidates.
 * <p>
 * Use {@link #setTitle(String)} and {@link #setMessage(String)} to customize
 * the dialog to your use case.
 *
 * @author pcdavid
 */
public class RepresentationsSelectionDialog extends TitleAreaDialog implements ICheckStateListener {
    /**
     * The complete list of candidates among which the user can choose, sorted
     * by name for easy selection by the user.
     */
    private final List<DRepresentation> candidateRepresentations;

    /**
     * The list of representations the user actually selected. A subset of
     * {@link #candidateRepresentations}, also sorted by name.
     */
    private final List<DRepresentation> selectedRepresentations;

    /**
     * The check-list control.
     */
    private CheckboxTableViewer checkList;

    /**
     * Creates a new dialog to let the user choose a subset of existing
     * representations from a collection of candidates.
     *
     * @param parentShell
     *            the dialog's shell.
     * @param candidates
     *            the collection of candidates among which to choose.
     */
    public RepresentationsSelectionDialog(final Shell parentShell, final Collection<DRepresentation> candidates) {
        super(parentShell);
        this.candidateRepresentations = new ArrayList<DRepresentation>(candidates);
        Collections.sort(candidateRepresentations, new Comparator<DRepresentation>() {
            @Override
            public int compare(final DRepresentation r1, final DRepresentation r2) {
                return new DRepresentationQuery(r1).getRepresentationDescriptor().getName().compareTo(new DRepresentationQuery(r2).getRepresentationDescriptor().getName());
            }
        });
        this.selectedRepresentations = new ArrayList<DRepresentation>(candidates);
    }

    /**
     * Returns the list of representations the user selected among the
     * candidates presented.
     *
     * @return the list of representations selected by the candidate. May be
     *         empty, but not <code>null</code>.
     */
    public List<DRepresentation> getSelectedRepresentations() {
        return new ArrayList<DRepresentation>(selectedRepresentations);
    }

    /**
     * Create the selection list.
     *
     * @param parent
     *            the parent control
     * @return the dialog area's content
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        createSelectionList(composite);
        return composite;
    }

    private void createSelectionList(final Composite parent) {
        checkList = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
        checkList.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

        checkList.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(final Object inputElement) {
                return candidateRepresentations.toArray();
            }

            @Override
            public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
                // Ignore.
            }

            @Override
            public void dispose() {
                // Ignore.
            }
        });

        checkList.setLabelProvider(new AdapterFactoryLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
        checkList.addCheckStateListener(this);
        checkList.setInput(candidateRepresentations);
        checkList.setAllChecked(true);
    }

    /**
     * Update {@link #selectedRepresentations} according to the event.
     *
     * @param event
     *            an event indicate a state change in a check-box.
     */
    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        if (event.getChecked()) {
            selectedRepresentations.add((DRepresentation) event.getElement());
        } else {
            selectedRepresentations.remove(event.getElement());
        }
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.RepresentationsSelectionDialog_shellTitle);
    };
}
