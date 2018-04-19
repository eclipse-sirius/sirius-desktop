/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.ui.page;

import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.server.internal.SiriusServerPlugin;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder.PageUpdateCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * A page in the session editor that displays the workflow configured for a session and allows the user to trigger the
 * corresponding actions.
 * <p>
 * The implementations is based on a web application; the page simply embed a {@link Browser} component and points it to
 * the URI of the application.
 * 
 * @author pcdavid
 */
public class WorkflowPage extends AbstractSessionEditorPage {
    /**
     * The session.
     */
    private final Session session;

    /**
     * The browser component used to display the workflow web application.
     */
    private Browser browser;

    /**
     * Creates a new {@link WorkflowPage} for the specified session.
     * 
     * @param s
     *            the session.
     */
    public WorkflowPage(Session s) {
        super(s.getID(), Messages.WorkflowPage_tab_name);
        this.session = Objects.requireNonNull(s);
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        super.createFormContent(managedForm);
        FormToolkit toolkit = managedForm.getToolkit();
        ScrolledForm scrolledForm = managedForm.getForm();
        scrolledForm.setText(MessageFormat.format(Messages.WorkflowPage_header_title, session.getID()));
        toolkit.decorateFormHeading(scrolledForm.getForm());

        Composite body = managedForm.getForm().getBody();
        body.setLayout(GridLayoutFactory.swtDefaults().create());

        Composite subBody = toolkit.createComposite(body);
        subBody.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(false).margins(0, 0).create());
        subBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        browser = new Browser(subBody, SWT.WEBKIT | SWT.FILL);
        GridData browserGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        browserGridData.widthHint = 0;
        browserGridData.heightHint = 0;
        browser.setLayoutData(browserGridData);
        browser.setUrl(getRootURL(this.session));
    }

    private String getRootURL(Session session) {
        URI uri = session.getSessionResource().getURI();
        if (uri.isPlatformResource()) {
            @SuppressWarnings("restriction")
            InetSocketAddress addr = SiriusServerPlugin.getPlugin().getServerAddress();
            // Use this URL when using the frontend in dev mode.
            //return "http://localhost:3000/projects/" + uri.segment(1); //$NON-NLS-1$
            return String.format("http://%s:%d/projects/%s", addr.getHostString(),addr.getPort(), uri.segment(1)); //$NON-NLS-1$
        } else {
            return "http://localhost:8080/"; //$NON-NLS-1$
        }
    }

    @Override
    public Optional<String> getLocationId() {
        return Optional.of(SessionEditorPlugin.DEFAULT_PAGE_ID);
    }

    @Override
    public Optional<PositioningKind> getPositioning() {
        return Optional.of(PositioningKind.AFTER);
    }

    @Override
    public Optional<PageUpdateCommand> pageChanged(boolean isVisible) {
        return Optional.empty();
    }

    @Override
    public Optional<PageUpdateCommand> resourceSetChanged(ResourceSetChangeEvent resourceSetChangeEvent) {
        Display.getDefault().asyncExec(() -> {
            if (browser != null && !browser.isDisposed()) {
                browser.refresh();
            }
        });
        return Optional.empty();
    }
}
