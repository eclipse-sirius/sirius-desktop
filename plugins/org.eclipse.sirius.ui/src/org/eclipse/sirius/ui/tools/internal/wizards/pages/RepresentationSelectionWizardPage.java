/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.internal.graphicalcomponents.GraphicalRepresentationHandler;
import org.eclipse.sirius.ui.tools.internal.graphicalcomponents.GraphicalRepresentationHandler.GraphicalRepresentationHandlerBuilder;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.collect.Lists;

/**
 * This page allows selection of a representation descriptor from which a representation instance will be created.
 *
 * @author nlepine
 */
public class RepresentationSelectionWizardPage extends WizardPage {

    /** The table viewer. */
    private TreeViewer treeViewer;

    /** The filter. */
    private final Session session;

    private Composite pageComposite;

    /**
     * The representation description to select by default in this page.
     */
    private RepresentationDescription representation;

    /**
     * The {@link Viewpoint} parent of the selected representation description.
     */
    private Viewpoint viewpoint;

    private SemanticElementSelectionWizardPage semanticSelectionWizard;

    /**
     * The representation descriptor that should be selected by default when opening the page.
     */
    private RepresentationDescriptionItemImpl representationDescriptionItem;

    /**
     * The semantic root element of the representation description that must be chosen from user.
     */
    private EObject semanticElement;

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code>.
     *
     * @param root
     *            the root object
     */
    public RepresentationSelectionWizardPage(final Session root) {
        super(Messages.RepresentationSelectionWizardPage_title);
        this.setTitle(Messages.RepresentationSelectionWizardPage_title);
        this.session = root;
        setMessage(Messages.RepresentationSelectionWizardPage_message);
    }

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code> with the given representation description selected
     * during creation.
     * 
     * @param theSession
     *            the session from which representations will be created.
     * @param theRepresentationDescriptionItem
     *            The representation descriptor that should be selected by default when opening the page.
     */
    public RepresentationSelectionWizardPage(Session theSession, RepresentationDescriptionItemImpl theRepresentationDescriptionItem) {
        super(Messages.RepresentationSelectionWizardPage_title);
        this.setTitle(Messages.RepresentationSelectionWizardPage_title);
        this.session = theSession;
        setMessage(Messages.RepresentationSelectionWizardPage_message);
        representationDescriptionItem = theRepresentationDescriptionItem;
    }

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code> providing representation descriptions compatible with
     * the given semantic element.
     * 
     * @param theSession
     *            the session from which representations will be created.
     * @param theSemanticSelection
     *            A semantic element that should be compatible with the representation description selected on this
     *            page.
     */
    public RepresentationSelectionWizardPage(Session theSession, EObject theSemanticSelection) {
        super(Messages.RepresentationSelectionWizardPage_title);
        this.session = theSession;
        this.semanticElement = theSemanticSelection;
    }

    /**
     * Returns the {@link Viewpoint} parent of the selected representation description.
     * 
     * @return the {@link Viewpoint} parent of the selected representation description.
     */
    public Viewpoint getViewpoint() {
        return viewpoint;
    }

    @Override
    public boolean isPageComplete() {
        boolean result = false;
        ISelection selection = treeViewer.getSelection();
        if (semanticElement != null && selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof RepresentationDescriptionItemImpl) {
            RepresentationDescriptionItem selectedRepresentationDescriptionItem = (RepresentationDescriptionItemImpl) ((StructuredSelection) selection).getFirstElement();
            if (selectedRepresentationDescriptionItem.getWrappedObject() != null) {
                RepresentationDescription representationDescription = (RepresentationDescription) selectedRepresentationDescriptionItem.getWrappedObject();
                viewpoint = ((RepresentationDescriptionItemImpl) ((StructuredSelection) selection).getFirstElement()).getViewpoint();
                representation = representationDescription;
                result = true;
            }
        } else if (semanticElement == null) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean canFlipToNextPage() {
        boolean result = false;

        setErrorMessage(null); // clear previous error if exists
        ISelection selection = treeViewer.getSelection();
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof RepresentationDescriptionItemImpl) {
            RepresentationDescription representationDescription = (RepresentationDescription) ((RepresentationDescriptionItemImpl) ((StructuredSelection) selection).getFirstElement())
                    .getWrappedObject();
            result = true; // set to true before permission authority check

            if (session instanceof DAnalysisSessionImpl) {
                Collection<DView> containers = ((DAnalysisSessionImpl) session).getAvailableRepresentationContainers(representationDescription);

                // If containers is empty, a new one will be created, so the
                // wizard is available
                if (!containers.isEmpty()) {
                    // Try to find one valid container candidate
                    result = false;
                    for (DView container : containers) {
                        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                        if (permissionAuthority == null || permissionAuthority.canCreateIn(container)) {
                            result = true;
                            break;
                        }
                    } // for
                }
            }
            if (result) {
                representation = representationDescription;
                viewpoint = ((RepresentationDescriptionItemImpl) ((StructuredSelection) selection).getFirstElement()).getViewpoint();
            } else {
                setErrorMessage(Messages.RepresentationSelectionWizardPage_errorReadonlyContainer);
            }
        }
        return result;
    }

    public RepresentationDescription getRepresentation() {
        return representation;
    }

    public void setSelectionWizard(SemanticElementSelectionWizardPage selectionWizard) {
        this.semanticSelectionWizard = selectionWizard;
    }

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout());
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        SessionContentProvider theContentProvider = new SessionContentProvider(semanticElement);
        GraphicalRepresentationHandlerBuilder graphicalRepresentationHandlerBuilder = new GraphicalRepresentationHandler.GraphicalRepresentationHandlerBuilder(session);
        GraphicalRepresentationHandler graphicalRepresentationHandler = graphicalRepresentationHandlerBuilder.customizeContentAndLabel(theContentProvider, new SiriusRepresentationLabelProvider())
                .filterEmptyViewpoints().activateBrowserWithViewpointAndRepresentationDescriptionInformation().build();
        graphicalRepresentationHandler.createControl(pageComposite);
        treeViewer = graphicalRepresentationHandler.getTreeViewer();
        Collection<Viewpoint> availableViewpoints = ViewpointHelper.getAvailableViewpoints(session);
        Collection<ViewpointItem> viewpointItemList = new ArrayList<>();
        for (Viewpoint availableViewpoint : availableViewpoints) {
            viewpointItemList.add(new ViewpointItemImpl(session, availableViewpoint, this));
        }
        graphicalRepresentationHandler.initInput();
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setPageComplete(isPageComplete());
                if (semanticSelectionWizard != null) {
                    semanticSelectionWizard.setRepresentation(getRepresentation());
                    semanticSelectionWizard.update();
                }
            }
        });
        if (representationDescriptionItem != null) {
            // we select the representation description item among all other items.
            int itemCount = treeViewer.getTree().getItemCount();
            for (int i = 0; i < itemCount; i++) {
                TreeItem treeItem = treeViewer.getTree().getItem(i);
                TreeItem[] subItems = treeItem.getItems();
                for (TreeItem subItem : subItems) {
                    if (subItem.getData() instanceof RepresentationDescriptionItemImpl) {
                        RepresentationDescriptionItemImpl representationDescriptionItemTemp = (RepresentationDescriptionItemImpl) subItem.getData();
                        if (EqualityHelper.areEquals((RepresentationDescription) representationDescriptionItem.getWrappedObject(),
                                (RepresentationDescription) representationDescriptionItemTemp.getWrappedObject())) {
                            treeViewer.setSelection(new StructuredSelection(representationDescriptionItemTemp), true);
                            representation = (RepresentationDescription) representationDescriptionItemTemp.getWrappedObject();
                            viewpoint = representationDescriptionItemTemp.getViewpoint();
                            semanticSelectionWizard.setRepresentation(representation);
                            semanticSelectionWizard.update();
                        }
                    }
                }
            }
        }
        setControl(pageComposite);
    }

    /**
     * return if the page is the current page.
     *
     * @return if the page is the current page.
     */
    public boolean isCurrentPageOnWizard() {
        return super.isCurrentPage();
    }

    /**
     * Session content provider providing all viewpoints registered with their representation descriptions when no
     * semantic element is provided.
     * 
     * If a semantic element is provided, the provider only shows viewpoints and their descriptions if the descriptions
     * specify as root element type the type of the semantic element.
     */
    private static final class SessionContentProvider implements ITreeContentProvider {

        private static Object[] empty = new Object[0];

        private EObject semanticSelection;

        SessionContentProvider(EObject theSemanticSelection) {
            this.semanticSelection = theSemanticSelection;
        }

        @Override
        public Object[] getChildren(final Object parentElement) {
            Object[] children = SessionContentProvider.empty;
            if (parentElement instanceof ViewpointItemImpl) {
                if (semanticSelection != null) {
                    Collection<?> subElement = ((ViewpointItem) parentElement).getChildren();
                    List<Object> filteredList = new ArrayList<>();
                    for (Object object : subElement) {
                        if (object instanceof RepresentationDescriptionItem) {
                            RepresentationDescriptionItem descriptionItem = (RepresentationDescriptionItem) object;
                            if (DialectManager.INSTANCE.canCreate(semanticSelection, (RepresentationDescription) descriptionItem.getWrappedObject(), false)) {
                                filteredList.add(descriptionItem);
                            }
                        }
                    }
                    children = filteredList.toArray();
                } else {
                    children = ((ViewpointItemImpl) parentElement).getChildren().toArray();
                }

            }
            return children;
        }

        @Override
        public Object getParent(final Object element) {
            return null;
        }

        @Override
        public boolean hasChildren(final Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public Object[] getElements(final Object inputElement) {
            Collection<Object> allChildren = Lists.newArrayList();
            if (inputElement instanceof Collection<?>) {
                allChildren.addAll((Collection<?>) inputElement);
            }
            return allChildren.toArray();
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // empty
        }
    }
}
