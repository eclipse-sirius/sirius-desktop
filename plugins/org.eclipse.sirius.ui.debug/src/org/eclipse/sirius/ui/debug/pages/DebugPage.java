/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.pages;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder.PageUpdateCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * This page shows various information regarding a Sirius session.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DebugPage extends AbstractSessionEditorPage {

    /**
     * This page id.
     */
    public static final String PAGE_ID = "org.eclipse.sirius.ui.debug.page";

    private Session session;

    private FormText semanticValueText;

    private FormText viewpointNumberText;

    public DebugPage(SessionEditor editor, String id, String title) {
        super(editor, id, title);
        this.session = editor.getSession();
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        super.createFormContent(managedForm);
        final ScrolledForm scrolledForm = managedForm.getForm();

        FormToolkit toolkit = managedForm.getToolkit();

        scrolledForm.setText("Debug me"); // $NON-NLS-1$
        toolkit.decorateFormHeading(scrolledForm.getForm());

        Composite body = managedForm.getForm().getBody();
        body.setLayout(GridLayoutFactory.swtDefaults().create());

        Composite subBody = toolkit.createComposite(body);
        subBody.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(false).create());
        subBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        toolkit.createLabel(subBody, "Viewpoins enabled: ");
        viewpointNumberText = toolkit.createFormText(subBody, true);
        setViewpointNumber();

        toolkit.createLabel(subBody, "Semantic elements number: ");
        semanticValueText = toolkit.createFormText(subBody, true);
        setSemanticValue();
    }

    /**
     * Set the number of viewpoints activated.
     */
    private void setViewpointNumber() {
        viewpointNumberText.setText(String.valueOf(session.getSelectedViewpoints(false).size()), false, false);
    }

    /**
     * Set the number of semantic elements loaded in the session.
     */
    private void setSemanticValue() {
        Collection<Resource> semanticResources = session.getSemanticResources();
        int semanticResourcesNumber = 0;
        for (Resource resource : semanticResources) {
            TreeIterator<EObject> allContents = resource.getAllContents();
            while (allContents.hasNext()) {
                allContents.next();
                semanticResourcesNumber++;
            }
        }
        semanticValueText.setText(String.valueOf(semanticResourcesNumber), false, false);
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
    public Optional<PageUpdateCommand> resourceSetChanged(ResourceSetChangeEvent resourceSetChangeEvent) {
        return Optional.empty();
    }

    @Override
    public Optional<PageUpdateCommand> pageChanged(boolean isVisible) {
        if (isVisible) {
            setSemanticValue();
            setViewpointNumber();
        }
        return Optional.empty();
    }

}
