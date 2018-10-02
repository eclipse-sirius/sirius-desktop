/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.workflow.ui.page;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.server.backend.internal.services.workflow.WorkflowHelper;
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
     * Listener refreshing the {@link Browser} when Sirius resource changes occur.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class BrowserRefresher extends SessionManagerListener.Stub {
        @Override
        public void notify(Session updated, int notification) {
            if (session == updated) {
                switch (notification) {
                case SessionListener.REPRESENTATION_CHANGE:
                case SessionListener.SEMANTIC_CHANGE:
                case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
                case SessionListener.VSM_UPDATED:
                case SessionListener.REPLACED:
                    Display.getDefault().asyncExec(() -> {
                        if (browser != null && !browser.isDisposed()) {
                            browser.refresh();
                        }
                    });
                    break;
                default:
                    // do nothing as we will be notified in other way
                    break;
                }
            }
        }
    }

    /**
     * The session.
     */
    private final Session session;

    /**
     * The browser component used to display the workflow web application.
     */
    private Browser browser;

    /**
     * Listener refreshing the {@link Browser} when Sirius resource changes occur.
     */
    private BrowserRefresher sessionManagerListenerForBrowserRefresh;

    /**
     * Creates a new {@link WorkflowPage} for the specified session.
     * 
     * @param s
     *            the session.
     */
    public WorkflowPage(Session s) {
        super(s.getID(), Messages.WorkflowPage_tab_name);
        this.session = Objects.requireNonNull(s);
        sessionManagerListenerForBrowserRefresh = new BrowserRefresher();
        SessionManager.INSTANCE.addSessionsListener(sessionManagerListenerForBrowserRefresh);
    }

    @Override
    public void dispose() {
        SessionManager.INSTANCE.removeSessionsListener(sessionManagerListenerForBrowserRefresh);
        super.dispose();
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
        browser = new Browser(subBody, SWT.FILL);
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
            java.net.URI serverUri = SiriusServerPlugin.getPlugin().getServerURI();
            // Use this URL when using the frontend in dev mode.
            // return "http://localhost:3000/projects/" + uri.segment(1); //$NON-NLS-1$
            return String.format("http://%s:%d/workflow/projects/%s?fullscreen=true", serverUri.getHost(), serverUri.getPort(), uri.segment(1)); //$NON-NLS-1$
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
        return Optional.empty();
    }
}
