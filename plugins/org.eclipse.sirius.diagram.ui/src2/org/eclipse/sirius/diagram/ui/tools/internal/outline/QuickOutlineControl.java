/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.outline;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlExtension;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.providers.FilteredTreeContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineLabelProvider;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.google.common.base.Predicates;

/**
 * This will be used to display the quick outline to the user.
 * 
 * @author nlepine
 * 
 */
public class QuickOutlineControl extends PopupDialog implements IInformationControl, IInformationControlExtension, IInformationControlExtension2, DisposeListener {
    /**
     * Sirius Editor to which this quick outline dialog is tied : selections
     * will take place in this editor.
     */
    protected final SiriusDiagramEditor editor;

    /** Adapter factory used by the content and label providers. */
    private AdapterFactory adapterFactory;

    /** The filtered tree we're displaying. */
    private FilteredTree filteredTree;

    /** Actual viewer displaying the outline. */
    private TreeViewer treeViewer;

    /**
     * Creates an information control with the given shell as parent.
     * 
     * @param parentShell
     *            the parent of this control's shell.
     * @param shellStyle
     *            The shell style.
     * @param editor
     *            The viewpoint editor
     * 
     */
    public QuickOutlineControl(Shell parentShell, int shellStyle, SiriusDiagramEditor editor) {
        super(parentShell, shellStyle, true, true, false, true, null, null);
        create();
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#addDisposeListener(org.eclipse.swt.events.DisposeListener)
     */
    public void addDisposeListener(DisposeListener listener) {
        getShell().addDisposeListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#addFocusListener(org.eclipse.swt.events.FocusListener)
     */
    public void addFocusListener(FocusListener listener) {
        getShell().addFocusListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#computeSizeHint()
     */
    public Point computeSizeHint() {
        return getShell().getSize();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#dispose()
     */
    public final void dispose() {
        filteredTree.dispose();
        close();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControlExtension#hasContents()
     */
    public boolean hasContents() {
        // FIXME check TreeViewer filtered content and return false if no
        // visible children
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#isFocusControl()
     */
    public boolean isFocusControl() {
        return getShell() == Display.getCurrent().getActiveShell();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#addDisposeListener(org.eclipse.swt.events.DisposeListener)
     */
    public void removeDisposeListener(DisposeListener listener) {
        getShell().removeDisposeListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#removeFocusListener(org.eclipse.swt.events.FocusListener)
     */
    public void removeFocusListener(FocusListener listener) {
        getShell().removeFocusListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setBackgroundColor(org.eclipse.swt.graphics.Color)
     */
    public void setBackgroundColor(Color background) {
        applyBackgroundColor(background, getContents());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setFocus()
     */
    public void setFocus() {
        getShell().forceFocus();
        filteredTree.setFocus();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setForegroundColor(org.eclipse.swt.graphics.Color)
     */
    public void setForegroundColor(Color foreground) {
        applyForegroundColor(foreground, getContents());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setInformation(java.lang.String)
     */
    public void setInformation(String information) {
        // We're implementing IInformationControlExtension2, this will not be
        // called
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControlExtension2#setInput(java.lang.Object)
     */
    public void setInput(Object input) {
        treeViewer.setInput(input);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setLocation(org.eclipse.swt.graphics.Point)
     */
    public void setLocation(Point location) {
        // Only override the shell's location if it's not persisted by the
        // PopupDialog
        // if (!getPersistLocation()) {
        getShell().setLocation(location);
        // }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setSize(int, int)
     */
    public void setSize(int width, int height) {
        getShell().setSize(width, height);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setSizeConstraints(int,
     *      int)
     */
    public void setSizeConstraints(int maxWidth, int maxHeight) {
        // We'll use the dialog's size
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.IInformationControl#setVisible(boolean)
     */
    public void setVisible(boolean visible) {
        if (visible) {
            open();
        } else {
            saveDialogBounds(getShell());
            getShell().setVisible(false);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
     */
    public void widgetDisposed(DisposeEvent event) {
        adapterFactory = null;
        filteredTree = null;
        treeViewer = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        createTreeViewer(parent);

        addDisposeListener(this);
        return treeViewer.getControl();
    }

    /**
     * Implementers can modify.
     * 
     * @return the selected element
     */
    protected Object getSelectedElement() {
        if (treeViewer == null) {
            return null;
        }

        return ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
    }

    private void gotoSelectedElement() {
        Object selectedElement = getSelectedElement();
        if (selectedElement instanceof DDiagramElement) {
            IGraphicalEditPart partToSelect = getEditPart((DDiagramElement) selectedElement);
            if (partToSelect != null) {
                editor.getDiagramGraphicalViewer().select(partToSelect);
                editor.getDiagramGraphicalViewer().reveal(partToSelect);
            }
            close();
        }
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagramElement diagramElement) {
        final IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
        return getEditPart(diagramElement, editorPart);
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * @param editorPart
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagramElement diagramElement, final IEditorPart editorPart) {
        IGraphicalEditPart result = null;
        final View gmfView = getGmfView(diagramElement);

        if (gmfView != null && editorPart instanceof DiagramEditor) {
            final Map<?, ?> editPartRegistry = ((DiagramEditor) editorPart).getDiagramGraphicalViewer().getEditPartRegistry();
            final Object editPart = editPartRegistry.get(gmfView);
            if (editPart instanceof IGraphicalEditPart) {
                result = (IGraphicalEditPart) editPart;
            }
        }
        return result;
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    protected View getGmfView(final DDiagramElement diagramElement) {
        final Session session = SessionManager.INSTANCE.getSession(diagramElement.getTarget());
        return SiriusGMFHelper.getGmfView(diagramElement, session);
    }

    /**
     * Creates the outline's tree viewer.
     * 
     * @param parent
     *            parent composite.
     */
    protected void createTreeViewer(Composite parent) {
        filteredTree = new FilteredTree(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);
        treeViewer = filteredTree.getViewer();
        final Tree tree = treeViewer.getTree();

        tree.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.character == SWT.ESC) {
                    dispose();
                }
                if (event.keyCode == 0x0D) {
                    gotoSelectedElement();
                }
                if (event.keyCode == SWT.ARROW_DOWN) {
                    tree.setFocus();
                }
                if (event.keyCode == SWT.ARROW_UP) {
                    tree.setFocus();
                }
                if (event.character == 0x1B) {
                    dispose();
                }
            }
        });

        tree.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                // do nothing
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                gotoSelectedElement();
            }
        });

        treeViewer.setContentProvider(new FilteredTreeContentProvider(getAdapterFactory(), Predicates.or(Predicates.instanceOf(DDiagramElement.class),
                Predicates.instanceOf(AbstractDDiagramElementLabelItemProvider.class))));
        treeViewer.setLabelProvider(new OutlineLabelProvider());

        // We want to remove everything that's not "top level elements" from the
        // outline view
        treeViewer.addFilter(new ViewerFilter() {

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element instanceof DDiagramElement;
            }
        });
    }

    /**
     * Returns the adapter factory used by this viewer.
     * 
     * @return The adapter factory used by this viewer.
     */
    protected AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            java.util.List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
            factories.add(new ViewpointItemProviderAdapterFactory());
            factories.add(new DiagramItemProviderAdapterFactory());
            factories.add(new ResourceItemProviderAdapterFactory());
            factories.add(new EcoreItemProviderAdapterFactory());
            factories.add(new ReflectiveItemProviderAdapterFactory());
            adapterFactory = new ComposedAdapterFactory(factories);
        }
        return adapterFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.PopupDialog#hasTitleArea()
     */
    @Override
    protected boolean hasTitleArea() {
        return false;
    }
}
