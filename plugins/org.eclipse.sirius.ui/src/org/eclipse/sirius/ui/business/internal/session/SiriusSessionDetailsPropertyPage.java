/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.internal.session;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.SessionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * {@link PropertyPage} presenting information related to the Sirius session of the selected aird file.
 * 
 * @author lfasani
 */
public class SiriusSessionDetailsPropertyPage extends PropertyPage {

    private Text text;

    @Override
    protected Control createContents(Composite parent) {
        noDefaultAndApplyButton();

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL);
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);

        createSessionDetailsSection(composite);

        initialize();

        return composite;
    }

    private void initialize() {
        IResource resourceAird = Adapters.adapt(getElement(), IResource.class);
        String airdName = resourceAird.getProject().getName() + "/" + resourceAird.getProjectRelativePath(); //$NON-NLS-1$

        URI airdURI = URI.createPlatformResourceURI(airdName, true);
        Session session = SessionManager.INSTANCE.getExistingSession(airdURI);
        if (session == null) {
            text.setText(Messages.SiriusSessionDetailsPropertyPage_sessionNotOpened);
        } else {
            Job job = new Job(Messages.SiriusSessionDetailsPropertyPage_computeSessionDetails) {
                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    String sessionInformation = getSessionInformation(session);
                    Display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            if (!text.isDisposed()) {
                                text.setText(sessionInformation);
                            }
                        }
                    });
                    return Status.OK_STATUS;
                }
            };
            job.setUser(true);
            job.schedule();
        }
    }

    private String getSessionInformation(Session session) {
        SessionQuery sessionQuery = new SessionQuery(session);
        String formattedInformation = sessionQuery.getSessionFormattedInformation();

        // Add part about the opened editors
        StringBuilder informations = new StringBuilder();
        List<DRepresentation> openedRepresentations = SessionUIManager.INSTANCE.getUISession(session).getEditors().stream().map(editor -> editor.getRepresentation()).collect(Collectors.toList());
        String cr = "\n"; //$NON-NLS-1$
        String tab = "  "; //$NON-NLS-1$
        informations.append(cr + Messages.SiriusSessionDetailsPropertyPage_repOpenedInEditor + openedRepresentations.size() + cr);
        openedRepresentations.stream().forEach(rep -> {
            informations.append(tab);
            sessionQuery.addRepresentationDescriptorSimpleInfo(informations, new DRepresentationQuery(rep).getRepresentationDescriptor());
            informations.append(cr);
        });

        formattedInformation = formattedInformation + informations.toString();

        return formattedInformation;
    }

    private void createSessionDetailsSection(Composite parent) {
        Composite sessionDetailsComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        sessionDetailsComposite.setLayout(layout);
        sessionDetailsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // CHECKSTYLE:OFF
        text = new Text(sessionDetailsComposite, SWT.MULTI | SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.NO_FOCUS | SWT.H_SCROLL);
        // CHECKSTYLE:ON
        text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
        gridData.grabExcessVerticalSpace = true;
        gridData.grabExcessHorizontalSpace = true;
        text.setLayoutData(gridData);
        text.setFont(JFaceResources.getTextFont());
        text.setText(Messages.SiriusSessionDetailsPropertyPage_computingSessionDetails);

        Button copyToClipboard = new Button(sessionDetailsComposite, SWT.NONE);
        copyToClipboard.setText(Messages.SiriusSessionDetailsPropertyPage_copyToClipboard);
        copyToClipboard.setLayoutData(new GridData(SWT.TRAIL, SWT.FILL, false, false));
        copyToClipboard.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Clipboard clipboard = null;
                try {
                    clipboard = new Clipboard(getShell().getDisplay());
                    clipboard.setContents(new Object[] { text.getText() }, new Transfer[] { TextTransfer.getInstance() });
                } finally {
                    if (clipboard != null) {
                        clipboard.dispose();
                    }
                }
            }
        });
    }

}
