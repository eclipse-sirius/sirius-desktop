/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.ui.tools.api.wizards.page.ViewpointsSelectionWizardPage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Class used to create the main page of the session editor which describe
 * Viewpoints used, model and representations.
 * 
 * @author jmallet
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DefaultSessionEditorPage extends FormPage implements SessionListener {

    /**
     * Delimiter used to separate text part for the page title.
     */
    private static final String DELIMITER = "/"; //$NON-NLS-1$

    /**
     * Session to describe and edit.
     */
    private Session session;

    /**
     * Wizard used to list Viewpoints for the session.
     */
    private ViewpointsSelectionWizardPage viewpointsSelectionWizardPage;

    /**
     * Label used to provides information regarding editor's context.
     */
    private Label informativeLabel;

    /**
     * Constructor.
     * 
     * @param editor
     *            the editor.
     * @param theSession
     *            the session.
     */
    public DefaultSessionEditorPage(SessionEditor editor, Session theSession) {
        super(editor, "sessionEditorId", "Main"); //$NON-NLS-1$ //$NON-NLS-2$
        this.session = theSession;
    }

    @Override
    protected void createFormContent(final IManagedForm managedForm) {
        super.createFormContent(managedForm);

        final ScrolledForm scrolledForm = managedForm.getForm();
        FormToolkit toolkit = managedForm.getToolkit();

        int sessionUriSegmentSize = session.getSessionResource().getURI().segmentsList().size();
        scrolledForm.setText(MessageFormat.format(Messages.UI_SessionEditor_header_title,
                session.getSessionResource().getURI().segmentsList().subList(1, sessionUriSegmentSize).stream().collect(Collectors.joining(DELIMITER)))); // $NON-NLS-1$
        toolkit.decorateFormHeading(scrolledForm.getForm());

        Composite body = managedForm.getForm().getBody();
        body.setLayout(GridLayoutFactory.swtDefaults().create());

        informativeLabel = toolkit.createLabel(body, "Informative message."); //$NON-NLS-1$
        informativeLabel.setForeground(body.getDisplay().getSystemColor(SWT.COLOR_RED));

        Composite subBody = toolkit.createComposite(body);
        subBody.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(false).create());
        subBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createModelsControl(toolkit, subBody);

        Composite rightComposite = toolkit.createComposite(subBody);
        rightComposite.setLayout(GridLayoutFactory.swtDefaults().margins(5, 0).create());
        rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createRepresentationsControl(toolkit, rightComposite);
        createViewpointSelectionControl(toolkit, rightComposite);
    }

    /**
     * Create the control allowing to view the semantic models of the session.
     * 
     * @param toolkit
     *            the tool allowing to create form UI component.
     * @param subBody
     *            the composite containing the viewpoint selection control.
     */
    protected void createModelsControl(FormToolkit toolkit, Composite subBody) {
        Section modelSection = toolkit.createSection(subBody, Section.DESCRIPTION | Section.TITLE_BAR);
        modelSection.setLayout(GridLayoutFactory.swtDefaults().create());
        modelSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        modelSection.setText("Models"); //$NON-NLS-1$
        modelSection.setDescription("Model Block description"); //$NON-NLS-1$

        Composite modelSectionClient = toolkit.createComposite(modelSection, SWT.NONE);
        modelSectionClient.setLayout(GridLayoutFactory.swtDefaults().create());
        modelSectionClient.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        modelSection.setClient(modelSectionClient);

        toolkit.createLabel(modelSectionClient, "Root element stub"); //$NON-NLS-1$
    }

    /**
     * Create the control allowing to view/create/remove representations of the
     * session.
     * 
     * @param toolkit
     *            the tool allowing to create form UI component.
     * @param rightComposite
     *            the composite containing the viewpoint selection control.
     */
    protected void createRepresentationsControl(FormToolkit toolkit, Composite rightComposite) {
        Section representationSection = toolkit.createSection(rightComposite, Section.DESCRIPTION | Section.TITLE_BAR);
        representationSection.setLayout(GridLayoutFactory.swtDefaults().create());
        representationSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        representationSection.setText("Representations"); //$NON-NLS-1$
        representationSection.setDescription("Representation Block description"); //$NON-NLS-1$

        Composite representationSectionClient = toolkit.createComposite(representationSection, SWT.NONE);
        representationSectionClient.setLayout(GridLayoutFactory.swtDefaults().create());
        representationSectionClient.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
        representationSection.setClient(representationSectionClient);

        toolkit.createLabel(representationSectionClient, "Root element stub"); //$NON-NLS-1$
    }

    /**
     * Create the control allowing to select/unselect active viewpoints of the
     * session.
     * 
     * @param toolkit
     *            the tool allowing to create form UI component.
     * @param rightComposite
     *            the composite containing the viewpoint selection control.
     */
    protected void createViewpointSelectionControl(FormToolkit toolkit, Composite rightComposite) {
        final Section viewpointSection = toolkit.createSection(rightComposite,
                Section.DESCRIPTION | Section.TITLE_BAR | ExpandableComposite.COMPACT | ExpandableComposite.EXPANDED | ExpandableComposite.TWISTIE);
        viewpointSection.setLayout(GridLayoutFactory.fillDefaults().create());
        viewpointSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        viewpointSection.setText("Viewpoints"); //$NON-NLS-1$
        viewpointSection.setDescription("Viewpoint Block description"); //$NON-NLS-1$

        Composite viewpointSectionClient = toolkit.createComposite(viewpointSection, SWT.NONE);
        GridLayout newLayout = GridLayoutFactory.fillDefaults().create();
        viewpointSectionClient.setLayout(newLayout);
        viewpointSection.setClient(viewpointSectionClient);

        List<String> viewpointsName = new ArrayList<String>();
        for (Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            viewpointsName.add(viewpoint.getName());
        }
        viewpointsSelectionWizardPage = new ViewpointsSelectionWizardPage(session, viewpointsName);
        viewpointsSelectionWizardPage.createControl(viewpointSectionClient);
        viewpointsSelectionWizardPage.setColumnWidthEquality(false);
        viewpointsSelectionWizardPage.setBrowserMinWidth(210);
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        super.doSave(monitor);
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void notify(int changeKind) {
    }

}
