/*******************************************************************************
 * Copyright (c) 2016, 2019 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.representationdescription;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Common superclass of all the metamodel property sections.
 * 
 * @author sbegaudeau
 */
public abstract class AbstractMetamodelPropertySectionSpec extends AbstractViewpointPropertySection {

    private CLabel help;

    /** Main composite **/
    protected Composite composite;

    protected Label listHeader;

    protected Table metamodelsTable;

    protected Button addFromRegistryButton;

    protected Button addFromWorkspaceButton;

    protected Button addFromFilesystemButton;

    protected Button removeMetamodelsSelectionButton;

    protected DescriptionMetamodelsUpdater descriptionMetamodelsUpdater;

    @Override
    protected void makeReadonly() {
        metamodelsTable.setEnabled(false);
        addFromRegistryButton.setEnabled(false);
        addFromWorkspaceButton.setEnabled(false);
        addFromFilesystemButton.setEnabled(false);
        removeMetamodelsSelectionButton.setEnabled(false);
    }

    @Override
    protected void makeWrittable() {
        metamodelsTable.setEnabled(true);
        addFromRegistryButton.setEnabled(true);
        addFromWorkspaceButton.setEnabled(true);
        addFromFilesystemButton.setEnabled(true);
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);

        if (aTabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) aTabbedPropertySheetPage);
        else
            super.createControls(parent, aTabbedPropertySheetPage);
        composite = getWidgetFactory().createFlatFormComposite(parent);

        help = getWidgetFactory().createCLabel(composite, "");
        help.setImage(getHelpIcon());
        help.setToolTipText("Select the Metamodels (EPackage) which must be used in the current description");

        listHeader = getWidgetFactory().createLabel(composite, "Selected Metamodels : ");
        FormData data = new FormData();
        data.left = new FormAttachment(0, 25);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        listHeader.setLayoutData(data);

        // CHECKSTYLE:OFF
        int style = SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL;
        // CHECKSTYLE:ON
        metamodelsTable = getWidgetFactory().createTable(composite, style);
        metamodelsTable.setLinesVisible(true);
        metamodelsTable.setHeaderVisible(true);
        TableColumn ePackageNameColumn = new TableColumn(metamodelsTable, SWT.NONE);
        ePackageNameColumn.setText("Name");
        TableColumn ePackageNSURIColumn = new TableColumn(metamodelsTable, SWT.NONE);
        ePackageNSURIColumn.setText("nsURI");
        TableColumn ecoreResourceURIColumn = new TableColumn(metamodelsTable, SWT.NONE);
        ecoreResourceURIColumn.setText("metamodel URI");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(80, 0);
        data.top = new FormAttachment(listHeader, 0, ITabbedPropertyConstants.VSPACE);
        data.height = 120;
        data.width = 200;
        metamodelsTable.setLayoutData(data);

        addButtons();
    }

    protected void addButtons() {
        EStructuralFeature eStructuralFeature = this.getFeature();
        if (eStructuralFeature instanceof EReference) {
            this.descriptionMetamodelsUpdater = new DescriptionMetamodelsUpdater(eObject, (EReference) eStructuralFeature);
        }

        addFromRegistryButton = getWidgetFactory().createButton(composite, "Add from registry", SWT.PUSH);
        addFromRegistryButton.addSelectionListener(new AddFromRegistryButtonListener(this, descriptionMetamodelsUpdater));
        FormData data = new FormData();
        data.top = new FormAttachment(listHeader, 0, SWT.RIGHT);
        data.left = new FormAttachment(metamodelsTable, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.right = new FormAttachment(100);
        data.width = 60;
        addFromRegistryButton.setLayoutData(data);

        addFromWorkspaceButton = getWidgetFactory().createButton(composite, "Add from workspace", SWT.PUSH);
        addFromWorkspaceButton.addSelectionListener(new AddFromWorkspaceButtonListener(this, descriptionMetamodelsUpdater));
        data = new FormData();
        data.top = new FormAttachment(addFromRegistryButton, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.left = new FormAttachment(metamodelsTable, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.right = new FormAttachment(100);
        data.width = 60;
        addFromWorkspaceButton.setLayoutData(data);

        addFromFilesystemButton = getWidgetFactory().createButton(composite, "Add from filesystem", SWT.PUSH);
        addFromFilesystemButton.addSelectionListener(new AddFromFilesystemButtonListener(this, descriptionMetamodelsUpdater));
        data = new FormData();
        data.top = new FormAttachment(addFromWorkspaceButton, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.left = new FormAttachment(metamodelsTable, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.right = new FormAttachment(100);
        data.width = 60;
        addFromFilesystemButton.setLayoutData(data);

        removeMetamodelsSelectionButton = getWidgetFactory().createButton(composite, "Remove", SWT.PUSH);
        removeMetamodelsSelectionButton.addSelectionListener(new RemoveMetamodelsSelectionButtonListener(this, removeMetamodelsSelectionButton, metamodelsTable, descriptionMetamodelsUpdater));
        removeMetamodelsSelectionButton.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(addFromFilesystemButton, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.left = new FormAttachment(metamodelsTable, ITabbedPropertyConstants.HMARGIN, SWT.RIGHT);
        data.right = new FormAttachment(100);
        data.width = 60;
        removeMetamodelsSelectionButton.setLayoutData(data);
    }

    @Override
    public void refresh() {
        descriptionMetamodelsUpdater.setDescription(eObject);
        List<EPackage> ePackages = getValue();
        if (ePackages != null) {
            List<EPackage> previousEPackages = getEPackages(metamodelsTable.getItems());
            int previousSelectionIndex = metamodelsTable.getSelectionIndex();
            metamodelsTable.removeAll();
            List<TableItem> newItems = new ArrayList<TableItem>();
            for (EPackage ePackage : ePackages) {
                TableItem item = new TableItem(metamodelsTable, 0);
                item.setData(ePackage);
                if (ePackage.eResource() != null) {
                    String ePackageName = ePackage.getName();
                    if (ePackageName == null) {
                        ePackageName = "null";
                    }
                    item.setText(0, ePackageName);
                    String ePackageNSURI = ePackage.getNsURI();
                    if (ePackageNSURI == null) {
                        ePackageNSURI = "null";
                    }
                    item.setText(1, ePackageNSURI);
                    String ePackageURI = null;
                    URI completeURIToEPackage = descriptionMetamodelsUpdater.getCompleteURIToEPackage(ePackage);
                    if (completeURIToEPackage != null) {
                        ePackageURI = URI.decode(completeURIToEPackage.toString());
                    } else {
                        URI genModelResourceURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap().get(ePackage.getNsURI());
                        if (genModelResourceURI != null) {
                            ePackageURI = genModelResourceURI.trimFileExtension().appendFileExtension("ecore").toString();
                        }
                    }
                    if (ePackageURI == null) {
                        ePackageURI = "unknow metamodel resource URI";
                    }
                    item.setText(2, ePackageURI);
                    if (!previousEPackages.contains(ePackage)) {
                        newItems.add(item);
                    }
                } else {
                    URI proxyURI = ((InternalEObject) ePackage).eProxyURI();
                    String segment = proxyURI.lastSegment();
                    // See https://dev.eclipse.org/recommenders/committers/aeri/v2/#%21/problems/59721630e4b062478a150878
                    String ePackageName = segment;
                    if (segment.indexOf('.') != -1) {
                        ePackageName = segment.substring(0, segment.indexOf('.'));
                    }
                    item.setText(0, ePackageName);
                    item.setText(1, "null");
                    String ePackageURI = proxyURI.trimFragment().toString();
                    item.setText(2, ePackageURI);
                    // Adding decorator
                    FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
                    Image img = fieldDecoration.getImage();
                    item.setImage(img);
                    // Log an error in the "Error log" view.
                    SiriusEditorPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID, "Invalid ressource access for the metamodel " + ePackageURI));
                }
            }
            if (!newItems.isEmpty()) {
                metamodelsTable.setSelection(newItems.toArray(new TableItem[0]));
                removeMetamodelsSelectionButton.setEnabled(true);
            } else {
                metamodelsTable.select(previousSelectionIndex - 1);
            }
        }
        if (metamodelsTable.getSelectionCount() == 0) {
            removeMetamodelsSelectionButton.setEnabled(false);
        }
        metamodelsTable.getColumn(0).pack();
        metamodelsTable.getColumn(1).pack();
        metamodelsTable.getColumn(2).pack();
    }

    /**
     * Get the current {@link EditingDomain}.
     * 
     * @return the current {@link EditingDomain}
     */
    public EditingDomain getEditingDomain() {
        EditingDomain editingDomain = null;
        if (getPart() instanceof IEditingDomainProvider) {
            editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
        }
        return editingDomain;
    }

    /**
     * Get the list a {@link EPackage}s associated to the specified array of
     * {@link TableItem}.
     * 
     * @param selection
     *            array of {@link TableItem}
     * @return list a {@link EPackage}s associated
     */
    public List<EPackage> getEPackages(TableItem[] selection) {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        for (TableItem item : selection) {
            if (item.getData() instanceof EPackage) {
                ePackages.add((EPackage) item.getData());
            }
        }
        return ePackages;
    }

    /**
     * Get the {@link RepresentationDescription#getMetamodel()} value.
     * 
     * @return the {@link RepresentationDescription#getMetamodel()} value
     */
    @SuppressWarnings("unchecked")
    protected List<EPackage> getValue() {
        List<EPackage> ePackages = new ArrayList<EPackage>();
        Object object = eObject.eGet(getFeature());
        if (object instanceof List<?>) {
            java.util.List<?> values = (java.util.List<?>) object;
            if (!values.isEmpty() && values.get(0) instanceof EPackage) {
                ePackages = (List<EPackage>) values;
            }
        }
        return ePackages;
    }

    @Override
    public void dispose() {
        if (descriptionMetamodelsUpdater != null) {
            descriptionMetamodelsUpdater.dispose();
            descriptionMetamodelsUpdater = null;
        }
        removeMetamodelsSelectionButton = null;
        addFromFilesystemButton = null;
        addFromWorkspaceButton = null;
        addFromRegistryButton = null;
        metamodelsTable = null;
        listHeader = null;
        composite = null;
        help = null;
        super.dispose();
    }
}
