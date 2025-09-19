/**
 * Copyright (c) 2017, 2025 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.tools.internal.wizards.newmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.tools.internal.ecore.EPackageHelper;
import org.eclipse.sirius.ui.tools.api.wizards.CreateEMFModelWizard;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page allowing to select a root element ({@link EClass}) from an {@link EPackage}.
 * 
 * @see CreateEMFModelWizard
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SelectRootElementWizardPage extends WizardPage implements PropertyChangeListener {

    /**
     * .
     */
    protected Composite container;

    /**
     * The icon used to represent EClasses.
     */
    private Image eClassIcon = ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EClass")); //$NON-NLS-1$

    /** The text field allowing to filter the list of root elements. */
    private Text rootElementText;

    /**
     * The checkbox allowing to only display root elements that are not child of other elements.
     */
    private Button rootElementCheckbox;

    /** The list of root elements. */
    private TableViewer rootElementFilteredList;

    /** The filter used by the text field and the filtered list. */
    private String rootElementFilter;

    /** The data model used by the wizard and its pages. */
    private CreateEMFModelWizardDataModel dataModel;

    /**
     * Filter selecting elements if they match a given regular expression.
     */
    private RegexpViewerFilter filter;

    /**
     * Default constructor for this page.
     * 
     * @param dataModel
     *            the data model used by this page.
     */
    public SelectRootElementWizardPage(CreateEMFModelWizardDataModel dataModel) {
        super("SelectRootElementWizardPage"); //$NON-NLS-1$
        this.dataModel = dataModel;
        setTitle(Messages.SelectRootElementWizardPage_title);
        setDescription(Messages.SelectRootElementWizardPage_description);
    }

    /**
     * Sets the filter pattern.
     *
     * @param theFilter
     *            the filter pattern.
     */
    public void setRootElementFilter(String theFilter) {
        if (this.rootElementText == null) {
            this.rootElementFilter = theFilter;
        } else {
            this.rootElementText.setText(theFilter);
        }
    }

    /**
     * Create contents of the wizard.
     * 
     * @param parent
     *            the parent composite.
     */
    @Override
    public void createControl(Composite parent) {
        container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(1, false));

        CLabel lblSelectRootElement = new CLabel(container, SWT.NONE);
        lblSelectRootElement.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblSelectRootElement.setText(Messages.SelectRootElementWizardPage_label);

        createRootElementFilterText(container);
        createRootElementCheckbox(container);
        createRootElementFilteredList(container);
        setRootElementFilter("*"); //$NON-NLS-1$
        this.rootElementCheckbox.setSelection(true);
    }

    /**
     * Create the text widget used to filter elements in the filtered list.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementFilterText(Composite parent) {
        this.rootElementText = new Text(parent, SWT.BORDER);
        this.rootElementText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.rootElementText.setText(this.rootElementFilter == null ? "" : this.rootElementFilter); //$NON-NLS-1$
        this.rootElementText.addListener(SWT.Modify, event -> {
            filter.setFilter(this.rootElementText.getText());
            rootElementFilteredList.refresh(true);

        });
        this.rootElementText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN) {
                    rootElementFilteredList.getTable().setFocus();
                }
            }
        });
    }

    /**
     * Create the checkbox widget used to display only elements that are not child of other elements in the filtered
     * list.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementCheckbox(Composite parent) {
        this.rootElementCheckbox = new Button(parent, SWT.CHECK);
        this.rootElementCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        this.rootElementCheckbox.setText(Messages.SelectRootElementWizardPage_checkboxLabel);
        this.rootElementCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateRootElementFilteredList();
            }
        });
    }

    /**
     * Create the filtered list widget used to display the root elements contained in the given
     * {@link #selectedPackage}.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementFilteredList(Composite parent) {
        this.rootElementFilteredList = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        this.rootElementFilteredList.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        this.filter = new RegexpViewerFilter();
        this.filter.setFilter(this.rootElementFilter == null ? "" : this.rootElementFilter); //$NON-NLS-1$
        this.rootElementFilteredList.setFilters(filter);
        this.rootElementFilteredList.addSelectionChangedListener((event) -> {
            updateSelectedRootElement();
        });
        this.rootElementFilteredList.setContentProvider(new ITreeContentProvider() {

            @Override
            public boolean hasChildren(Object element) {
                return false;
            }

            @Override
            public Object getParent(Object element) {
                return null;
            }

            @Override
            public Object[] getElements(Object inputElement) {
                if (inputElement instanceof Object[]) {
                    return (Object[]) inputElement;
                }
                return new Object[0];
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                return new Object[0];
            }
        });
        this.rootElementFilteredList.setLabelProvider(new RootElementsListLabelProvider());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (CreateEMFModelWizardDataModel.SELECTED_PACKAGE_EVENT.equals(evt.getPropertyName())) {
            updateRootElementFilteredList();
            updateSelectedRootElement();
        }
    }

    /**
     * Update the selected root element and validate the page.
     */
    private void updateSelectedRootElement() {
        ISelection selection = this.rootElementFilteredList.getSelection();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            Object[] selectionArray = structuredSelection.toArray();
            if (selectionArray.length == 1) {
                this.dataModel.setSelectedRootElement((EClass) selectionArray[0]);
                setPageComplete(true);
            } else {
                EClass preferredRootElement = EPackageHelper.getPreferredRootElementFromEPackageExtraData(this.dataModel.getSelectedPackage());
                if (preferredRootElement != null) {
                    this.dataModel.setSelectedRootElement(preferredRootElement);
                    setPageComplete(true);
                } else {
                    this.dataModel.setSelectedRootElement(null);
                    setPageComplete(false);
                }
            }
        }
    }

    /**
     * Update the root element filtered list according to the value of the {@link #rootElementCheckbox}.
     */
    private void updateRootElementFilteredList() {
        if (this.rootElementCheckbox != null) {
            Object[] rootElements;
            if (this.rootElementCheckbox.getSelection()) {
                List<EClass> eClassRootElements = EPackageHelper.getEClassRootElements(this.dataModel.getSelectedPackage());
                rootElements = eClassRootElements.toArray();
            } else {
                rootElements = EPackageHelper.getConcreteClasses(this.dataModel.getSelectedPackage()).toArray();
            }
            this.rootElementFilteredList.setInput(rootElements);
            EClass preferredRootElement = EPackageHelper.getPreferredRootElementFromEPackageExtraData(this.dataModel.getSelectedPackage());
            if (preferredRootElement != null) {
                this.rootElementFilteredList.setSelection(new StructuredSelection(preferredRootElement));
            } else if (rootElements.length > 0) {
                this.rootElementFilteredList.setSelection(new StructuredSelection(rootElements[0]));
            }
            this.rootElementFilteredList.refresh(true);
        }
    }

    /**
     * Filter selecting elements if they match a given regular expression.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class RegexpViewerFilter extends ViewerFilter {
        /**
         * The text filter to use when applying filtering on given elements.
         */
        private String filter;

        /**
         * Set the text filter to use when applying filtering on given elements.
         * 
         * @param theFilter
         *            the text filter to use when applying filtering on given elements.
         */
        public void setFilter(String theFilter) {
            this.filter = theFilter;
        }

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (!filter.equals("*") && element instanceof EClass) { //$NON-NLS-1$
                EClass eClass = (EClass) element;
                return eClass.getName() != null && eClass.getName().toLowerCase().contains(filter.toLowerCase());
            }
            return true;
        }
    }

    /**
     * A label provider for the {@link SelectRootElementWizardPage#rootElementFilteredTree}.
     */
    private final class RootElementsListLabelProvider extends LabelProvider {

        @Override
        public String getText(Object element) {
            if (element instanceof EClass) {
                return ((EClass) element).getName();
            }
            return super.getText(element);
        }

        @Override
        public Image getImage(Object element) {
            return eClassIcon;
        }
    }

}
